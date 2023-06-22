package uz.gita.newsappcompose.presentation.screen.home

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import uz.gita.newsappcompose.data.remote.response.Result
import uz.gita.newsappcompose.navigation.MyScreen
import uz.gita.newsappcompose.presentation.screen.home.pages.all.AllNewsPage
import uz.gita.newsappcompose.presentation.screen.home.pages.extra.CountryNewsPage
import uz.gita.newsappcompose.presentation.screen.home.pages.top.TopNewsPage

class HomeScreen:MyScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val context = LocalContext.current
        val viewmodel = getViewModel<HomeModel>()
        val uiState = viewmodel.collectAsState().value

        TabNavigator(tab = AllNewsPage()){
                tab ->
            Scaffold(
                bottomBar = {
                    NavigationBar(modifier = Modifier) {
                        TabNavigationItem(tab = AllNewsPage())
                        TabNavigationItem(tab = CountryNewsPage())
                        TabNavigationItem(tab = TopNewsPage())
                    }
                },
                topBar = {
                    TopAppBar(
                        title = { Text(text = tab.current.options.title) },
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                },
                content = { paddingValues ->
                    Box(
                        Modifier
                            .fillMaxSize()
                            .padding(paddingValues)) {
                        CurrentTab()
                    }

                }
            )
        }

        viewmodel.collectSideEffect{ sideEffect ->
            when(sideEffect){
                else -> {
                    Toast.makeText(context, "Fail....", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    @Composable
    fun RowScope.TabNavigationItem(tab: Tab) {
        val tabNavigator = LocalTabNavigator.current

        NavigationBarItem(
            selected = tabNavigator.current == tab,
            onClick = { tabNavigator.current = tab; },
            icon = { Icon(painter = tab.options.icon!!, contentDescription = tab.options.title) }
        )
    }
}