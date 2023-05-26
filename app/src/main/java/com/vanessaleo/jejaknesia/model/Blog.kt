package com.vanessaleo.jejaknesia.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Blog(
    val title: String,
    val author: String,
    val date: String,
    val content: String,
    val photo: Int
) : Parcelable
