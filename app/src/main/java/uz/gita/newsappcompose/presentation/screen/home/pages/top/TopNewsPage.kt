package uz.gita.newsappcompose.presentation.screen.home.pages.top

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita.dima.xabarlarqr.presenter.screens.home.component.ArticleItem
import uz.gita.newsappcompose.R
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.presentation.screen.home.HomeScreen

class TopNewsPage() : Tab {
    override val options: TabOptions
        @Composable
        get() {
            val title = stringResource(R.string.top_news)
            val icon = rememberVectorPainter(Icons.Default.Warning)

            return remember {
                TabOptions(
                    index = 1u,
                    title = title,
                    icon = icon
                )
            }
        }

    @Composable
    override fun Content() {

        var controlFirst = true
        val listState = rememberLazyListState()

        val parentScreen = LocalNavigator.current?.parent?.lastItem // HomeScreen
        if(parentScreen !is HomeScreen) return
        val viewModel = parentScreen.getViewModel<TopPageModel>()
        val uiState = viewModel.collectAsState().value

        val list by remember { mutableStateOf(arrayListOf<Result>()) }

        Surface {
            when(uiState){
                TopPageContract.UiState.Loading -> {
                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                        CircularProgressIndicator()
                    }
                    viewModel.eventDispatcher(TopPageContract.Intent.LoadTopNews)
                }
                TopPageContract.UiState.FreeSpace -> {

                }
                is TopPageContract.UiState.GiveList -> {
                    controlFirst = true
                    list.addAll(uiState.list)
                }
            }
            LazyColumn(state = listState) {
                items(list) { item ->
                    ArticleItem(item_news = item){
                        viewModel.eventDispatcher(TopPageContract.Intent.OpenDetailScreen(item))
                    }
                }
                item {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Bottom) {
                        CircularProgressIndicator(modifier = Modifier.padding(vertical = 16.dp), color = Color.Green, strokeWidth = 10.dp)
                        if (listState.isScrolledToTheEnd() && controlFirst){
                            viewModel.eventDispatcher(TopPageContract.Intent.TopScreenNextButtonClicked)
                            controlFirst = false
                        }
                    }
                }
            }
        }
    }
    fun LazyListState.isScrolledToTheEnd() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
}