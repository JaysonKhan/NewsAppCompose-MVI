package uz.gita.newsappcompose.data.remote.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import uz.gita.newsappcompose.data.remote.response.NewsResponse
import uz.gita.newsappcompose.data.remote.response.Result

interface NewsApi {

    @GET("/api/1/news")
    suspend fun getNews(
       @Query("q") tema:String,
       @Query("from_date") from:String,
       @Query("language") language:String,
       @Query("page") page:String,
       @Query("category") category:String
    ):Response<NewsResponse>


}