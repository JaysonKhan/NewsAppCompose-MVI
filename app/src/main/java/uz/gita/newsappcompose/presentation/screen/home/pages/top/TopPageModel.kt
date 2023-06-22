package uz.gita.newsappcompose.presentation.screen.home.pages.top

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class TopPageModel @Inject constructor(
    private val useCase: UseCase
): ViewModel(), TopPageContract.Model {
    override val container = container<TopPageContract.UiState, TopPageContract.SideEffect>(TopPageContract.UiState.Loading)

    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    var topPageState = 0

    var top = arrayListOf<Result>()

    override fun eventDispatcher(intent: TopPageContract.Intent) {
        when(intent){
            TopPageContract.Intent.TopScreenNextButtonClicked -> {
                Log.d("TTT", topPageState.toString())
                intent {
                    useCase.loadTopNews(topPageState++).onEach {
                        it.onFailure {
                            postSideEffect(TopPageContract.SideEffect.ShowToast(it.message.toString()))
                        }
                        it.onSuccess {list->
                            reduce {
                                TopPageContract.UiState.GiveList(list.results)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }
            TopPageContract.Intent.LoadTopNews -> {
                intent {
                    useCase.loadTopNews(topPageState).onEach {
                        it.onFailure {
                            postSideEffect(TopPageContract.SideEffect.ShowToast(it.message.toString()))
                        }
                        it.onSuccess {list->
                            reduce {
                                TopPageContract.UiState.GiveList(list.results)
                            }

                        }

                    }.launchIn(viewModelScope)
                }
            }
            is TopPageContract.Intent.OpenDetailScreen -> {

            }
        }
    }
}