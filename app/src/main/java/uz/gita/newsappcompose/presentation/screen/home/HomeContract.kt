package uz.gita.newsappcompose.presentation.screen.home

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.remote.response.Result

interface HomeContract {
    interface Model:ContainerHost<UiState, SideEffect>{
        fun eventDispatcher(intent: Intent)
    }
    sealed interface Intent{

    }
    sealed interface UiState{
        object Loading : UiState

    }
    sealed interface SideEffect{

    }
}