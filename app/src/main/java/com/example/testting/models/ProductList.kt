package com.example.testting.models

import com.example.testting.models.ItemDetails

data class ProductList(
    val products: List<ItemDetails>,
    val total: Int,
    val skip: Int,
    val limit: Int
)