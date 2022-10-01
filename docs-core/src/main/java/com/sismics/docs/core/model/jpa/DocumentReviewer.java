package com.sismics.docs.core.model.jpa;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.common.base.MoreObjects;

/**
 * Link between a document and a reviewer.
 * 
 * @author YiHanna
 */
@Entity
@Table(name = "T_DOCUMENT_REVIEWER")
public class DocumentReviewer implements Serializable {
    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 1L;

    /**
     * Document user ID.
     */
    @Id
    @Column(name = "DOR_ID_C", length = 36)
    private String id;
    
    /**
     * Document ID.
     */
    @Column(name = "DOR_IDDOCUMENT_C", nullable = false, length = 36)
    private String documentId;
    
    /**
     * User ID.
     */
    @Column(name = "DOR_IDUSER_C", nullable = false, length = 36)
    private String userId;
    
    /**
     * Score given to candidate.
     */
    @Column(name = "DOR_SCORE", nullable = true)
    private int score;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("documentId", documentId)
                .add("score", score)
                .toString();
    }
}
