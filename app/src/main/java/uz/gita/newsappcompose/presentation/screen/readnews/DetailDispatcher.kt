package uz.gita.newsappcompose.presentation.screen.readnews

import uz.gita.newsappcompose.navigation.AppNavigator
import javax.inject.Inject

interface ReadDispatcher {
    suspend fun back()
}
class ReadDispatcherImpl @Inject constructor(
    private val navigator: AppNavigator
):ReadDispatcher{
    override suspend fun back() {
        navigator.back()
    }

}