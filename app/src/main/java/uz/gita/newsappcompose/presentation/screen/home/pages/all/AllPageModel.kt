package uz.gita.newsappcompose.presentation.screen.home.pages.all

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
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class AllPageModel @Inject constructor(
    private val useCase: UseCase
): ViewModel(), AllPageContract.Model {
    override val container = container<AllPageContract.UiState, AllPageContract.SideEffect>(AllPageContract.UiState.Loading)

//    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    var allPageState = 0

    override fun eventDispatcher(intent: AllPageContract.Intent) {
       when(intent){
           AllPageContract.Intent.AllScreenNextButtonClicked -> {
                   intent {
                       useCase.loadAllNews(allPageState++).onEach {
                           it.onFailure {
                               postSideEffect(AllPageContract.SideEffect.ShowToast(it.message.toString()))
                           }
                           it.onSuccess { list ->
                               reduce {
                                   AllPageContract.UiState.GiveList(list.results)
                               }
                           }

                       }.launchIn(viewModelScope)
                   }
           }
           AllPageContract.Intent.LoadAllNews -> {
               intent {
                   useCase.loadAllNews(allPageState).onEach {
                       it.onFailure {
                           postSideEffect(AllPageContract.SideEffect.ShowToast(it.message.toString()))
                       }
                       it.onSuccess {list->
                           reduce {
                               AllPageContract.UiState.GiveList(list.results)
                           }

                       }

                   }.launchIn(viewModelScope)
               }
           }
           is AllPageContract.Intent.OpenDetailScreen -> {

           }
       }
    }


}