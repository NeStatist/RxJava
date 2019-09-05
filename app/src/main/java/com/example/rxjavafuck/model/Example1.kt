package com.example.rxjavafuck.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Example (
        @SerializedName("hits") @Expose val hits: List<Hit>,
        @SerializedName("nbHits") @Expose val nbHits: Int,
        @SerializedName("page") @Expose val page: Int,
        @SerializedName("nbPages") @Expose val nbPages: Int,
        @SerializedName("hitsPerPage") @Expose val hitsPerPage: Int,
        @SerializedName("processingTimeMS") @Expose val processingTimeMS: Int,
        @SerializedName("exhaustiveNbHits") @Expose val exhaustiveNbHits: Boolean,
        @SerializedName("query") @Expose val query: String,
        @SerializedName("params") @Expose val params: String)

