package com.vanessaleo.jejaknesia.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("status")
	val status: String
)
