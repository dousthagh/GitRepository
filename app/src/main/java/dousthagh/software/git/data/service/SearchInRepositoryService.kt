package dousthagh.software.git.data.service

import dousthagh.software.git.data.model.search.result.SearchResultModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchInRepositoryService {
    @GET("search/repositories?")
    suspend fun search(
        @Query("q") q: String,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc",
    ): Response<SearchResultModel>
}