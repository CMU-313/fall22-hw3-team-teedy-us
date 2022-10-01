package com.sismics.docs.core.dao.dto;

public class DocumentReviewerDto {
    /**
     * DocumentReviewer ID.
     */
    private String id;

    /**
     * Document ID.
     */
    private String documentId;

    /**
     * User ID.
     */
    private String userId;

    /**
     * Score of Document.
     */
    private int score;


    public String getId() {
        return id;
    }

    public DocumentReviewerDto setId(String id) {
        this.id = id;
        return this;
    }
    
    public String getDocumentId() {
        return documentId;
    }

    public DocumentReviewerDto setDocumentId(String documentId) {
        this.documentId = documentId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public DocumentReviewerDto setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public int getScore() {
        return score;
    }

    public DocumentReviewerDto setScore(int score) {
        this.score = score;
        return this;
    }
}
