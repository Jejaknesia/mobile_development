package com.vanessaleo.jejaknesia.response

import com.google.gson.annotations.SerializedName

data class DataResponse(

	@field:SerializedName("result")
	val result: List<String>,

	@field:SerializedName("error")
	val error: Boolean,

	@field:SerializedName("status")
	val status: String
)
