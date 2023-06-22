package uz.gita.newsappcompose.domain.usecase

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.remote.response.NewsResponse
import uz.gita.newsappcompose.domain.repository.NewsRepository
import javax.inject.Inject

class UseCaseImpl @Inject constructor(
    private val newsRepository: NewsRepository
):UseCase {
    override fun loadAllNews(nextPage:Int): Flow<Result<NewsResponse>>  = newsRepository.loadAllNews(nextPage)

    override fun loadTopNews(nextPage:Int): Flow<Result<NewsResponse>>  = newsRepository.loadTopNews(nextPage)

    override fun loadCountryNews(nextPage:Int): Flow<Result<NewsResponse>>  = newsRepository.loadCountryNews(nextPage)

}