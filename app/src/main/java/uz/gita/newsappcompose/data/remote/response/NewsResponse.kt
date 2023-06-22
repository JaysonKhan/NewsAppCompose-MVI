package uz.gita.newsappcompose.data.remote.response

data class NewsResponse(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)