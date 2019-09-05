package com.example.rxjavafuck.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Author(
        @SerializedName("value") @Expose val value: String,
        @SerializedName("matchLevel") @Expose val matchLevel: String,
        @SerializedName("matchedWords") @Expose val matchedWords: List<Any>
)




