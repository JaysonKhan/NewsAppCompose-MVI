package uz.gita.newsappcompose.presentation.screen.readnews

import org.orbitmvi.orbit.ContainerHost
import uz.gita.newsappcompose.data.remote.response.Result

interface DetailScreenContract {
    sealed interface Intent {
        data class Save(val article: Result) : Intent
        data class Remove(val article: Result) : Intent
    }

    sealed interface UIState {
        object Loading : UIState
    }

    sealed interface SideEffect {
        data class HasError(val errorMessage: String) : SideEffect
    }

    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatcher(intent: Intent)
    }
}