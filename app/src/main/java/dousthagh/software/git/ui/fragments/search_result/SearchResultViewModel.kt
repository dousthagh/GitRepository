package dousthagh.software.git.ui.fragments.search_result

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import dousthagh.software.git.data.model.search.request.SearchRequestModel
import dousthagh.software.git.data.model.search.result.SearchResultModel
import dousthagh.software.git.data.repository.SearchRepository
import dousthagh.software.git.util.Resource

class SearchResultViewModel @ViewModelInject constructor(private val searchRepository: SearchRepository) :
    ViewModel() {
    private val _searchRequestModel = MutableLiveData<SearchRequestModel>()

    private val _repositories = _searchRequestModel.switchMap {
        searchRepository.searchInRepository(it)
    }

    val repositories : LiveData<Resource<SearchResultModel>> = _repositories

    fun setRequestModel(searchRequestModel: SearchRequestModel){
        _searchRequestModel.value = searchRequestModel
    }
}