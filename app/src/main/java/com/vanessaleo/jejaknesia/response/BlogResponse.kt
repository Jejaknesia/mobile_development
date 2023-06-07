package com.vanessaleo.jejaknesia.response

import com.google.gson.annotations.SerializedName

data class BlogResponse(

	@field:SerializedName("data")
	val data: ArrayList<DataItem>,

	@field:SerializedName("status")
	val status: String
)

data class DataItem(

	@field:SerializedName("date")
	val date: String,

	@field:SerializedName("author")
	val author: String,

	@field:SerializedName("photo")
	val photo: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("title")
	val title: String,

	@field:SerializedName("content")
	val content: String
)
