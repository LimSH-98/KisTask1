package com.example.kistask1.model

import com.google.gson.annotations.SerializedName

data class Board (
    @SerializedName("id")
    var id : Int,
    @SerializedName("title")
    var title : String,
    @SerializedName("birthday")
    var birthday : String,
    // 사진
    @SerializedName("url")
    var url: String
)