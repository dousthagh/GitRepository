package dousthagh.software.git.data.model.search.result

data class SearchResultModel(
    val incomplete_results: Boolean,
    val items: List<RepositoryItem>,
    val total_count: Int
)