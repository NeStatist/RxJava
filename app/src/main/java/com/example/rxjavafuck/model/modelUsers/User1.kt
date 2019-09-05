package com.example.rxjavafuck.model.modelUsers

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

import java.util.Objects

data class User (

    @SerializedName("id") @Expose var id: Int? = null,
    @SerializedName("username") @Expose var username: String? = null,
    @SerializedName("about") @Expose var about: String? = null,
    @SerializedName("karma") @Expose var karma: Int? = null,
    @SerializedName("created_at") @Expose var createdAt: String? = null,
    @SerializedName("avg") @Expose var avg: Double? = null,
    @SerializedName("delay") @Expose var delay: Any? = null,
    @SerializedName("submitted") @Expose var submitted: Int? = null,
    @SerializedName("updated_at") @Expose var updatedAt: String? = null,
    @SerializedName("submission_count") @Expose var submissionCount: Int? = null,
    @SerializedName("comment_count") @Expose var commentCount: Int? = null,
    @SerializedName("created_at_i") @Expose var createdAtI: Int? = null,
    @SerializedName("objectID") @Expose var objectID: String? = null


  /*  override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o !is User) return false
        val user = o as User?
        return id == user!!.id &&
                username == user.username &&
                karma == user.karma
    }

    override fun hashCode(): Int {
        return Objects.hash(id, username, karma)
    }*/
)
