package uz.gita.newsappcompose.navigation

import kotlinx.coroutines.flow.SharedFlow
import uz.gita.newsappcompose.navigation.NavigationArg

interface NavigationHandler {
    val navigatorBuffer:SharedFlow<NavigationArg>
}