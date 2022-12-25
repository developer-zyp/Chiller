package com.example.chiller.model.bar

import java.io.Serializable

data class Bar(
    val address: String,
    val address_obj: AddressObj,
    val api_detail_url: String,
    val awards: List<Any>,
    val bearing: Any,
    val category: Category,
    val description: String,
    val distance: Any,
    val distance_string: Any,
    val doubleclick_zone: String,
    val hours: Hours,
    val is_closed: Boolean,
    val is_jfy_enabled: Boolean,
    val is_long_closed: Boolean,
    val latitude: String,
    val location_id: String,
    val location_string: String,
    val longitude: String,
    val name: String,
    val nearest_metro_station: List<Any>,
    val num_reviews: String,
    val open_now_text: String,
    val parent_display_name: String,
    val phone: String,
    val photo: Photo,
    val preferred_map_engine: String,
    val price: String,
    val price_level: String,
    val ranking: String,
    val ranking_category: String,
    val ranking_denominator: String,
    val ranking_geo: String,
    val ranking_geo_id: String,
    val ranking_position: String,
    val rating: String,
    val raw_ranking: String,
    val subcategory: List<Subcategory>,
    val timezone: String,
    val web_url: String,
    val website: String,
    val write_review: String
) : Serializable