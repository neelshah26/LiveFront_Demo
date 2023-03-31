package com.example.testting.models

import java.io.Serializable

data class ItemDetails (
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Int,
    val rating: Float,
    val stock: Int,
    val brand: String,
    val category: String,
    val thumbnail: String,
    val images: List<String>?
) : Serializable