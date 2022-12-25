package com.example.chiller.ui.bardetail

import android.os.Bundle
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
import com.example.chiller.databinding.FragmentBarFavBinding
import com.example.chiller.databinding.LsvItemBarPopularBinding
import com.example.chiller.model.BarInfo

class BarFavFragment : Fragment() {

    private lateinit var binding: FragmentBarFavBinding
    private lateinit var viewModel: BarDetailViewModel

    lateinit var favoriteAdapter: BaseRecyclerViewAdapter<BarInfo>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBarFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BarDetailViewModel::class.java)

        initUI()
        viewModel.getAllData().observe(viewLifecycleOwner) {
            try {
                favoriteAdapter.setItem(it)
            } catch (ex: Exception) {
                ex.message?.let { it1 -> Utils.showToast(it1) }
            }
        }
    }

    private fun initUI() {
        favoriteAdapter = BaseRecyclerViewAdapter(R.layout.lsv_item_bar_popular)
        { item, view -> bindViewHolder(item, view) }
        binding.rvFavourite.adapter = favoriteAdapter
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
            findNavController().navigate(R.id.action_barFavFragment_to_barDetailFragment, bundle)
        }
    }

}