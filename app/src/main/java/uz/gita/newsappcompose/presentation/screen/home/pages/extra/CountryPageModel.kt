package uz.gita.newsappcompose.presentation.screen.home.pages.extra

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class CountryPageModel @Inject constructor(
    private val useCase: UseCase
): ViewModel(), CountryPageContract.Model {
    override val container = container<CountryPageContract.UiState, CountryPageContract.SideEffect>(CountryPageContract.UiState.Loading)

    var countryPageState = 0

    override fun eventDispatcher(intent: CountryPageContract.Intent) {
        when(intent){
            CountryPageContract.Intent.CountryScreenNextButtonClicked -> {
                Log.d("TTT", countryPageState.toString())
                intent {
                    useCase.loadCountryNews(countryPageState++).onEach {
                        it.onFailure {
                            postSideEffect(CountryPageContract.SideEffect.ShowToast(it.message.toString()))
                        }
                        it.onSuccess {list->
                            reduce {
                                CountryPageContract.UiState.GiveList(list.results)
                            }

                        }

                    }.launchIn(viewModelScope)
                }
            }
            CountryPageContract.Intent.LoadCountryNews -> {
                intent {
                    useCase.loadCountryNews(countryPageState).onEach {
                        it.onFailure {
                            postSideEffect(CountryPageContract.SideEffect.ShowToast(it.message.toString()))
                        }
                        it.onSuccess {list->
                            reduce {
                                CountryPageContract.UiState.GiveList(list.results)
                            }

                        }

                    }.launchIn(viewModelScope)
                }
            }
            is CountryPageContract.Intent.OpenDetailScreen -> {

            }
        }
    }
}