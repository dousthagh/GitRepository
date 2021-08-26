package dousthagh.software.git.ui.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import dousthagh.software.git.R
import dousthagh.software.git.databinding.FragmentSearchBinding
import dousthagh.software.git.ui.fragments.search_result.SearchResultFragment

@AndroidEntryPoint
class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        prepareLayout(inflater, container)


        binding.btnSearch.setOnClickListener {
            val topic = binding.etTopic.text
            if (topic != null) {
                findNavController().navigate(
                    R.id.action_searchFragment_to_searchResultFragment,
                    bundleOf("topic" to topic.toString())
                )
            }

        }
        return binding.root
    }

    private fun prepareLayout(inflater: LayoutInflater, container: ViewGroup?) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
    }


}