package uz.gita.newsappcompose.domain.repository

import android.util.Log
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.newsappcompose.data.remote.api.NewsApi
import uz.gita.newsappcompose.data.remote.response.NewsResponse
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject


class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
):NewsRepository {
    private val from:String = getThreeDaysAgo()


    var allNewsPage = arrayListOf<String>("")
    var topNewsPage = arrayListOf<String>("")
    var extendNewsPage = arrayListOf<String>("")

    override fun loadAllNews(
        nextPage:Int
    ): Flow<Result<NewsResponse>> = callbackFlow {
        val tema:String = ""
        val language:String = "en"
        val category:String = "top"

        val response = newsApi.getNews(tema, from, language, allNewsPage[nextPage], category)
        when (response.code()){
            in 200..300 ->{
                val page = response.body() as NewsResponse
                if (!allNewsPage.contains(page.nextPage)){
                    allNewsPage.add(page.nextPage)
                }
                trySend(Result.success(response.body() as NewsResponse))
            }else -> {
            Log.d("TTTA", response.errorBody().toString())
            trySend(Result.failure(Exception("(response.errorBody() as ErrorResponse).results.message")))
            }
        }
        awaitClose()
    }

    override fun loadTopNews(
        nextPage:Int
    ): Flow<Result<NewsResponse>>  = callbackFlow {
        val tema:String = ""
        val language:String = "uz"
        val category:String = "top"

        val response = newsApi.getNews(tema, from, language, topNewsPage[nextPage], category)
        when (response.code()){
            in 200..300 ->{
                val page = response.body() as NewsResponse
                if (!topNewsPage.contains(page.nextPage)){
                    topNewsPage.add(page.nextPage)
                }
                trySend(Result.success(response.body() as NewsResponse))
            }else -> {
            Log.d("TTTB", response.errorBody().toString())
            trySend(Result.failure(Exception("(response.errorBody() as ErrorResponse).results.message")))
        }
        }
        awaitClose()
    }

    override fun loadCountryNews(
        nextPage:Int
    ): Flow<Result<NewsResponse>>  = callbackFlow {
        val tema:String = ""
        val language:String = "en"
        val category:String = "technology"

        val response = newsApi.getNews(tema, from, language, extendNewsPage[nextPage], category)
        when (response.code()){
            in 200..300 ->{
                val page = response.body() as NewsResponse
                if (!extendNewsPage.contains(page.nextPage)){
                    extendNewsPage.add(page.nextPage)
                }
                trySend(Result.success(response.body() as NewsResponse))
            }else -> {
            Log.d("TTTC", response.errorBody().toString())
            trySend(Result.failure(Exception("Failed :(")))
        }
        }
        awaitClose()
    }
    fun getThreeDaysAgo(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -30)  // 30 kun oldingi sanaga o'tkazamiz
        val threeDaysAgo = calendar.time
        return dateFormat.format(threeDaysAgo)
    }
}
