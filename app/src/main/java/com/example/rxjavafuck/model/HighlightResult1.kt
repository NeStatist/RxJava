package com.example.rxjavafuck.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class HighlightResult(

    @SerializedName("title") @Expose val title: Title,
    @SerializedName("url") @Expose val url: Url,
    @SerializedName("author") @Expose val author: Author

)
