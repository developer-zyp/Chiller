package com.example.chiller.ui.barlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chiller.R
import com.example.chiller.common.BaseRecyclerViewAdapter
import com.example.chiller.common.GlideUtils
import com.example.chiller.databinding.FragmentBarSearchBinding
import com.example.chiller.databinding.LsvItemBarPopularBinding
import com.example.chiller.model.BarInfo


class BarSearchFragment : Fragment() {

    private lateinit var binding: FragmentBarSearchBinding
    private lateinit var viewModel: BarListViewModel

    lateinit var searchAdapter: BaseRecyclerViewAdapter<BarInfo>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBarSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BarListViewModel::class.java]

        searchAdapter = BaseRecyclerViewAdapter(R.layout.lsv_item_bar_popular)
        { item, view -> bindViewHolder(item, view) }
        binding.rvSearch.adapter = searchAdapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query.isNotBlank()) {
                    viewModel.getSearchList(query)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        viewModel.searchItemList.observe(viewLifecycleOwner) {
            searchAdapter.setItem(it)
        }

    }

    private fun bindViewHolder(item: BarInfo, view: View) {
        val itemBinding = LsvItemBarPopularBinding.bind(view)
        itemBinding.itemName.text = item.name
        GlideUtils.showImage(
            item.image,
            android.R.drawable.ic_menu_gallery,
            itemBinding.itemImage
        )

        itemBinding.itemCard.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable("data", item)
            findNavController().navigate(R.id.action_barSearchFragment_to_barDetailFragment, bundle)
        }
    }

}