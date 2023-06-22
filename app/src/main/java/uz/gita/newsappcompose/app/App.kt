package uz.gita.newsappcompose.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import uz.gita.newsappcompose.data.remote.api.NewsApi
import javax.inject.Inject

@HiltAndroidApp
class App:Application() {

}