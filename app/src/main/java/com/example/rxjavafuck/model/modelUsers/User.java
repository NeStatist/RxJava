
package com.example.rxjavafuck.model.modelUsers;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class User {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("about")
    @Expose
    private String about;
    @SerializedName("karma")
    @Expose
    private Integer karma;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("avg")
    @Expose
    private Double avg;
    @SerializedName("delay")
    @Expose
    private Object delay;
    @SerializedName("submitted")
    @Expose
    private Integer submitted;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("submission_count")
    @Expose
    private Integer submissionCount;
    @SerializedName("comment_count")
    @Expose
    private Integer commentCount;
    @SerializedName("created_at_i")
    @Expose
    private Integer createdAtI;
    @SerializedName("objectID")
    @Expose
    private String objectID;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public Integer getKarma() {
        return karma;
    }

    public void setKarma(Integer karma) {
        this.karma = karma;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }

    public Object getDelay() {
        return delay;
    }

    public void setDelay(Object delay) {
        this.delay = delay;
    }

    public Integer getSubmitted() {
        return submitted;
    }

    public void setSubmitted(Integer submitted) {
        this.submitted = submitted;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getSubmissionCount() {
        return submissionCount;
    }

    public void setSubmissionCount(Integer submissionCount) {
        this.submissionCount = submissionCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public Integer getCreatedAtI() {
        return createdAtI;
    }

    public void setCreatedAtI(Integer createdAtI) {
        this.createdAtI = createdAtI;
    }

    public String getObjectID() {
        return objectID;
    }

    public void setObjectID(String objectID) {
        this.objectID = objectID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getUsername().equals(user.getUsername()) &&
                getKarma().equals(user.getKarma());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getKarma());
    }
}
