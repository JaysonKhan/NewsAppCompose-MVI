package uz.gita.newsappcompose.presentation.screen.home.pages.top

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.remote.response.Result

interface TopPageContract {
    interface Model: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }
    sealed interface Intent{
        object LoadTopNews : Intent
        object TopScreenNextButtonClicked : Intent
        data class OpenDetailScreen(val item: Result):Intent
    }
    sealed interface UiState{
        object Loading : UiState
        object FreeSpace : UiState
        data class GiveList(val list:List<Result>):UiState
    }
    sealed interface SideEffect{
        data class ShowToast(val message: String) : SideEffect
    }
}