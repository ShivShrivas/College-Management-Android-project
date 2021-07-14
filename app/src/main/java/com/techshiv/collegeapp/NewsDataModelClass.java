package com.techshiv.collegeapp;

public class NewsDataModelClass {
    String Author,News,NewsTitle,PostId,PostImage,NewsTimeDate;

    NewsDataModelClass(){

    }

    public String getNewsTimeDate() {
        return NewsTimeDate;
    }

    public void setNewsTimeDate(String newsTimeDate) {
        NewsTimeDate = newsTimeDate;
    }
    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getNews() {
        return News;
    }

    public void setNews(String news) {
        News = news;
    }

    public String getNewsTitle() {
        return NewsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        NewsTitle = newsTitle;
    }

    public String getPostId() {
        return PostId;
    }

    public void setPostId(String postId) {
        PostId = postId;
    }

    public String getPostImage() {
        return PostImage;
    }

    public void setPostImage(String postImage) {
        PostImage = postImage;
    }

    public NewsDataModelClass(String author, String news, String newsTitle, String postId, String postImage, String newsTimeDate) {
        Author = author;
        News = news;
        NewsTitle = newsTitle;
        PostId = postId;
        PostImage = postImage;
        NewsTimeDate = newsTimeDate;
    }
}
