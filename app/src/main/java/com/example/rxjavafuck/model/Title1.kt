package com.example.rxjavafuck.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title (

    @SerializedName("value") @Expose var value: String,
    @SerializedName("matchLevel") @Expose var matchLevel: String,
    @SerializedName("matchedWords") @Expose var matchedWords: List<Any>

)
