package com.tdd.livestream;

/**
 * Created by y34h1a on 2/14/17.
 */

public class LinkInfo {
    String videoId;
    String discription;
    String fullLink;

    public LinkInfo(String videoId, String discription, String fullLink) {
        this.videoId = videoId;
        this.discription = discription;
        this.fullLink = fullLink;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getDiscription() {
        return discription;
    }

    public void setDiscription(String discription) {
        this.discription = discription;
    }

    public String getFullLink() {
        return fullLink;
    }

    public void setFullLink(String fullLink) {
        this.fullLink = fullLink;
    }
}
