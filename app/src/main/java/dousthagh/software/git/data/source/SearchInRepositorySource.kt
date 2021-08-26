package dousthagh.software.git.data.source

import dousthagh.software.git.data.model.search.request.SearchRequestModel
import dousthagh.software.git.data.service.SearchInRepositoryService
import javax.inject.Inject

class SearchInRepositorySource @Inject constructor(private val searchInRepositoryService: SearchInRepositoryService) :
    BaseDataSource() {
    suspend fun search(searchRequestModel: SearchRequestModel) = getResponse { searchInRepositoryService.search(searchRequestModel.topic) }
}