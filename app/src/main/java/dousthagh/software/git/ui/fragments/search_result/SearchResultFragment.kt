package dousthagh.software.git.ui.fragments.search_result

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dousthagh.software.git.R
import dousthagh.software.git.data.model.search.request.SearchRequestModel
import dousthagh.software.git.databinding.FragmentSearchResultBinding
import dousthagh.software.git.ui.fragments.search_result.adapter.IRepositoryItemListener
import dousthagh.software.git.util.Resource
import javax.inject.Inject

@AndroidEntryPoint
class SearchResultFragment : Fragment(), IRepositoryItemListener {
    private lateinit var binding: FragmentSearchResultBinding
    private val viewModel by viewModels<SearchResultViewModel>()
    @Inject lateinit var adapter: SearchResultAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prepareLayout(inflater, container)
        setupRecyclerView()
        arguments?.getString("topic")?.let {
            binding.tvTopic.text = it
            prepareRecyclerView(it)
        }


        return binding.root
    }

    private fun setupRecyclerView() {
        adapter.setItemListener(this)
        binding.rvSearchResult.adapter = adapter
    }

    private fun prepareLayout(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentSearchResultBinding.inflate(inflater, container, false);
    }

    private fun prepareRecyclerView(topic:String) {
        viewModel.setRequestModel(SearchRequestModel(_topic = topic))
        viewModel.repositories.observe(viewLifecycleOwner) {
            when (it.status) {
                Resource.Status.LOADING -> {
                    binding.prgSearch.visibility = View.VISIBLE
                    binding.rvSearchResult.visibility = View.GONE

                }
                Resource.Status.ERROR -> {
                    binding.prgSearch.visibility = View.GONE
                    binding.rvSearchResult.visibility = View.GONE
                }
                Resource.Status.SUCCESS -> {
                    binding.prgSearch.visibility = View.GONE
                    if (it.data != null) {
                        binding.rvSearchResult.visibility = View.VISIBLE
                        binding.tvResult.text =
                            getString(R.string.total_result, it.data.total_count.toString())
                        adapter.setItems(ArrayList(it.data.items))
                    }
                }
            }
        }

    }

    override fun onClicked(itemId: Int) {
        Log.d("asd", itemId.toString())
    }
}