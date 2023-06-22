package uz.gita.newsappcompose.presentation.screen.home

import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.navigation.AppNavigator
import uz.gita.newsappcompose.presentation.screen.readnews.DetailScreen
import javax.inject.Inject

interface HomeDirection {
    suspend fun openReadNews(item_news:uz.gita.newsappcompose.data.remote.response.Result)

}
class HomeDirectionImpl @Inject constructor(
    private val navigator: AppNavigator
):HomeDirection{
    override suspend fun openReadNews(item_news: Result) {
        navigator.navigateTo(DetailScreen(item_news))
    }


}