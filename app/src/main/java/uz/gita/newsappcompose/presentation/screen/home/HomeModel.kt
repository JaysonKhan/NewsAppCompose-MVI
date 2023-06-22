package uz.gita.newsappcompose.presentation.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class HomeModel @Inject constructor(
    private val useCase: UseCase,
    private val direction: HomeDirection
):HomeContract.Model, ViewModel() {
    override val container =
        container<HomeContract.UiState, HomeContract.SideEffect>(HomeContract.UiState.Loading)

    override fun eventDispatcher(intent: HomeContract.Intent) {

    }
}