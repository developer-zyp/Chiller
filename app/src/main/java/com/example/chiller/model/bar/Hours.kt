package com.example.chiller.model.bar

data class Hours(
    val timezone: String,
    val week_ranges: List<List<WeekRange>>
)