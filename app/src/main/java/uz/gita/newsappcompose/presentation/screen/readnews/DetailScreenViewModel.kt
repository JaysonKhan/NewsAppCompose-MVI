package uz.gita.newsappcompose.presentation.screen.readnews

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.orbitmvi.orbit.viewmodel.container
import uz.gita.newsappcompose.domain.usecase.UseCase
import javax.inject.Inject

@HiltViewModel
class DetailScreenViewModel @Inject constructor(
    private val appUsesCase: UseCase
): DetailScreenContract.ViewModel, ViewModel() {
    override val container = container<DetailScreenContract.UIState, DetailScreenContract.SideEffect>(
        DetailScreenContract.UIState.Loading)

    override fun onEventDispatcher(intent: DetailScreenContract.Intent) {
     /*   when(intent) {
            is DetailScreenContract.Intent.Save -> {
                appUsesCase.addNews(intent.article)
            }

            is DetailScreenContract.Intent.Remove -> {
                appUsesCase.delete(intent.article)
            }
        }*/
    }
}