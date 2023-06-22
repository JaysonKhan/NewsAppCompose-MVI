package uz.gita.newsappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.remote.response.NewsResponse

interface UseCase {
    fun loadAllNews(nextPage:Int): Flow<Result<NewsResponse>>
    fun loadTopNews(nextPage:Int): Flow<Result<NewsResponse>>
    fun loadCountryNews(nextPage:Int): Flow<Result<NewsResponse>>
}