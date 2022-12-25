package com.example.chiller.ui.barlist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.chiller.R
import com.example.chiller.common.BaseRecyclerViewAdapter
import com.example.chiller.common.GlideUtils
import com.example.chiller.common.Utils
import com.example.chiller.databinding.FragmentBarListBinding
import com.example.chiller.databinding.LsvItemBarNewBinding
import com.example.chiller.databinding.LsvItemBarPopularBinding
import com.example.chiller.model.BarInfo

class BarListFragment : Fragment() {

    private lateinit var binding: FragmentBarListBinding
    private lateinit var viewModel: BarListViewModel

    lateinit var popularList: MutableList<BarInfo>
    lateinit var popularAdapter: BaseRecyclerViewAdapter<BarInfo>
    lateinit var newAdapter: BaseRecyclerViewAdapter<BarInfo>
    lateinit var categoryAdapter: BaseRecyclerViewAdapter<BarInfo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBarListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BarListViewModel::class.java]

        popularList = mutableListOf()

        setUpUI()
        setUpViewModel()

    }

    private fun setUpUI() {
        popularAdapter = BaseRecyclerViewAdapter(R.layout.lsv_item_bar_popular)
        { item, view -> bindViewHolder(item, view) }
        binding.rvPopular.adapter = popularAdapter

        newAdapter = BaseRecyclerViewAdapter(R.layout.lsv_item_bar_new)
        { item, view -> bindViewHolder(item, view) }
        binding.rvNew.adapter = newAdapter

        categoryAdapter = BaseRecyclerViewAdapter(R.layout.lsv_item_bar_new)
        { item, view -> bindViewHolder(item, view) }
        binding.rvCategory.adapter = categoryAdapter


    }

    private fun bindViewHolder(item: BarInfo, view: View) {
        if (view.rootView.id == R.layout.lsv_item_bar_popular) {

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
                findNavController().navigate(
                    R.id.action_barListFragment_to_barDetailFragment,
                    bundle
                )
            }

        } else {

            val itemBinding = LsvItemBarNewBinding.bind(view)
            itemBinding.itemName.text = item.name
            GlideUtils.showImage(
                item.image,
                android.R.drawable.ic_menu_gallery,
                itemBinding.itemImage
            )

            itemBinding.itemCard.setOnClickListener {
                val bundle = Bundle()
                bundle.putSerializable("data", item)
                findNavController().navigate(
                    R.id.action_barListFragment_to_barDetailFragment,
                    bundle
                )
            }
        }


    }

    private fun setUpViewModel() {
        viewModel.getPopularBarList()
        viewModel.getNewBarList()
        viewModel.getCategoryBarList()

        viewModel.popularBarList.observe(viewLifecycleOwner) {
            popularAdapter.setItem(it)
        }

        viewModel.newBarList.observe(viewLifecycleOwner) {
            newAdapter.setItem(it)
        }

        viewModel.categoryBarList.observe(viewLifecycleOwner) {
            categoryAdapter.setItem(it)
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) {
            Utils.showToast(it)
            Log.i("barlist", it)
        }

    }

}