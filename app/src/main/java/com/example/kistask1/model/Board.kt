package com.example.kistask1.model

import android.media.Image
import com.google.gson.annotations.SerializedName

data class Board (
    @SerializedName("id")
    var id : String,
    @SerializedName("title")
    var title : String,
    @SerializedName("birthday")
    var birthday : String,
    // 사진
    @SerializedName("img_name")
    var imgName : String,
    @SerializedName("img_url")
    var imgUrl : String
)