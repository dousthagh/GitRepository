package dousthagh.software.git.data.repository

import dousthagh.software.git.data.model.search.request.SearchRequestModel
import dousthagh.software.git.data.source.SearchInRepositorySource
import dousthagh.software.git.util.searchOperation
import javax.inject.Inject

class SearchRepository @Inject constructor(private val dataSource: SearchInRepositorySource) {
    fun searchInRepository(searchRequestModel: SearchRequestModel) =
        searchOperation(networkCall = { dataSource.search(searchRequestModel) })
}