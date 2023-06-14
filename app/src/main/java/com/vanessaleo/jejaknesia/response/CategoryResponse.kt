package com.vanessaleo.jejaknesia.response

import com.google.gson.annotations.SerializedName

data class CategoryResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItemItem>
)

data class DataItemItem(

	@field:SerializedName("desc_place")
	val descPlace: String,

	@field:SerializedName("place_name")
	val placeName: String,

	@field:SerializedName("address")
	val address: String,

	@field:SerializedName("city")
	val city: String,

	@field:SerializedName("image_url")
	val imageUrl: String,

	@field:SerializedName("rating")
	val rating: Any,

	@field:SerializedName("Lon")
	val lon: Any,

	@field:SerializedName("category_7")
	val category7: String,

	@field:SerializedName("category_6")
	val category6: String,

	@field:SerializedName("category_5")
	val category5: String,

	@field:SerializedName("category_4")
	val category4: String,

	@field:SerializedName("category_3")
	val category3: String,

	@field:SerializedName("price")
	val price: Int,

	@field:SerializedName("category_2")
	val category2: String,

	@field:SerializedName("category_1")
	val category1: String,

	@field:SerializedName("combine_category")
	val combineCategory: String,

	@field:SerializedName("place_id")
	val placeId: String,

	@field:SerializedName("Lat")
	val lat: Any
)
