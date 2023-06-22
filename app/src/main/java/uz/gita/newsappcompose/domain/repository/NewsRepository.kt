package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.remote.response.NewsResponse

interface NewsRepository {
    fun loadAllNews(nextPage:Int): Flow<Result<NewsResponse>>
    fun loadTopNews(nextPage:Int): Flow<Result<NewsResponse>>
    fun loadCountryNews(nextPage:Int): Flow<Result<NewsResponse>>
}