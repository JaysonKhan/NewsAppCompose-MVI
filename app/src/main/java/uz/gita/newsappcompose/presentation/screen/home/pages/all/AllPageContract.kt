package uz.gita.newsappcompose.presentation.screen.home.pages.all

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.remote.response.Result

interface AllPageContract {
    interface Model: ContainerHost<UiState, SideEffect> {
        fun eventDispatcher(intent: Intent)
    }
    sealed interface Intent{
        object LoadAllNews : Intent
        object AllScreenNextButtonClicked : Intent
        data class OpenDetailScreen(val item:Result):Intent
    }
    sealed interface UiState{
        object Loading : UiState
        object FreeSpace : UiState
        data class GiveList(val list:List<Result>):UiState
    }
    sealed interface SideEffect{
        data class ShowToast(val message:String):SideEffect
    }
}