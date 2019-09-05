package com.example.rxjavafuck.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Hit (

    @SerializedName("created_at") @Expose val createdAt: String,
    @SerializedName("title") @Expose val title: String,
    @SerializedName("url") @Expose val url: String,
    @SerializedName("author") @Expose val author: String,
    @SerializedName("points") @Expose val points: Int,
    @SerializedName("story_text") @Expose val storyText: Any,
    @SerializedName("comment_text") @Expose val commentText: Any,
    @SerializedName("num_comments") @Expose val numComments: Int,
    @SerializedName("story_id") @Expose val storyId: Any,
    @SerializedName("story_title") @Expose val storyTitle: Any,
    @SerializedName("story_url") @Expose val storyUrl: Any,
    @SerializedName("parent_id") @Expose val parentId: Any,
    @SerializedName("created_at_i") @Expose val createdAtI: Int,
    @SerializedName("relevancy_score") @Expose val relevancyScore: Int,
    @SerializedName("_tags") @Expose val tags: List<String>,
    @SerializedName("objectID") @Expose val objectID: String,
    @SerializedName("_highlightResult") @Expose val highlightResult: HighlightResult

)
