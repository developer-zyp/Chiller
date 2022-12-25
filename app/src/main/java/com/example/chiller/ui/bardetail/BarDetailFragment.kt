package com.example.chiller.ui.bardetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.chiller.R
import com.example.chiller.common.BaseRecyclerViewAdapter
import com.example.chiller.common.GlideUtils
import com.example.chiller.common.Utils
import com.example.chiller.databinding.FragmentBarDetailBinding
import com.example.chiller.model.BarInfo

class BarDetailFragment : Fragment() {

    private lateinit var binding: FragmentBarDetailBinding
    private lateinit var viewModel: BarDetailViewModel

    lateinit var trailerMovieAdapter: BaseRecyclerViewAdapter<BarInfo>
    lateinit var itemDetail: BarInfo

    private var isFavourite: Boolean? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBarDetailBinding.inflate(inflater, container, false)
        itemDetail = arguments?.getSerializable("data") as BarInfo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[BarDetailViewModel::class.java]

        setUpUI()

        isSaved()
        binding.fabFavorite.setOnClickListener {
            saveFavorite()
        }

    }

    private fun setUpUI() {
        binding.apply {
            tvTitle.text = itemDetail.name
            tvOverview.text = itemDetail.contact
            tvPhone.text = itemDetail.phone
//            tvRating.text = itemDetail.type
        }

        binding.ivWeb.setOnClickListener {
            context?.let { it1 -> Utils.openURL(it1, itemDetail.website) }
        }

        binding.ivFacebook.setOnClickListener {
            context?.let { it1 -> Utils.openURL(it1, itemDetail.facebook) }
        }

        GlideUtils.showImage(
            itemDetail.image,
            android.R.drawable.ic_menu_gallery,
            binding.imageView
        )

    }


    private fun isSaved() {
        viewModel.getSingleItem(itemDetail.resid).observe(viewLifecycleOwner) {
            isFavourite = if (it != null) {
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite)
                true
            } else {
                binding.fabFavorite.setImageResource(R.drawable.ic_favorite_border)
                false
            }
        }
    }

    private fun saveFavorite() {
        if (isFavourite != true) {
            viewModel.insertData(itemDetail)
            Utils.showToast("Added to favorites")
        } else {
            viewModel.deleteData(itemDetail)
            Utils.showToast("Removed from favorites")
        }
    }

}