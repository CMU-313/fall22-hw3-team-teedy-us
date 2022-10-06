package com.sismics.docs.core.dao;

import com.sismics.docs.core.model.jpa.DocumentReviewer;
import com.sismics.docs.core.dao.dto.DocumentReviewerDto;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.util.context.ThreadLocalContext;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Document Reviewer DAO.
 * 
 * @author YiHanna
 */

public class DocumentReviewerDao {
    /**
     * Creates a new document reviewer.
     * 
     * @param documentReviewer Document Reviewer
     * @param userId id of reviewer user
     * @param documentId id of document being reviewed
     * @return New ID
     */
    public String create(DocumentReviewer docRev, String userId, String documentId) {
        // Create the UUID
        docRev.setId(UUID.randomUUID().toString());

        // Sets associated user and document ids
        docRev.setDocumentId(documentId);
        docRev.setUserId(userId);
        
        // Create DocumentReviewer
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        em.persist(docRev);
        
        // Create audit log
        // AuditLogUtil.create(docRev, AuditLogType.CREATE, userId);

        return docRev.getId();
    }

    /**
     * Gets an active document by its ID.
     * 
     * @param id Document ID
     * @return Document
     */
    public DocumentReviewer findById(String id) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        Query q = em.createQuery("select t from DocumentReviewer t where t.id = :id");
        q.setParameter("id", id);
        try {
            DocumentReviewer docRevDb = (DocumentReviewer) q.getSingleResult();
            return docRevDb;
        } catch (NoResultException e) {
            return null;
        }
    }

    /**
     * Returns the list of document reviewers for a document
     *
     * @param documentId document id
     * @return List of groups
     */
    public List<DocumentReviewerDto> findByDocumentId(String documentId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();

        // Database Query
        StringBuilder sb = new StringBuilder("select distinct t.DOR_ID_C as c0, t.DOR_IDDOCUMENT_C as c1, t.DOR_IDUSER_C as c2, t.DOR_SCORE as c3 ");
        sb.append(" from T_DOCUMENT_REVIEWER t where t.DOR_IDDOCUMENT_C = :documentId");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("documentId", documentId);

        List<Object[]> l = q.getResultList();

        // Assemble results
        List<DocumentReviewerDto> docRevDtoList = new ArrayList<>();
        for (Object[] o : l) {
            int i = 0;
            DocumentReviewerDto docRevDto = new DocumentReviewerDto()
                                            .setId((String) o[i++])
                                            .setDocumentId((String) o[i++])
                                            .setUserId((String) o[i++])
                                            .setScore((int) o[i++]);
            docRevDtoList.add(docRevDto);
        }
        return docRevDtoList;
    }

    /**
     * Returns the list of document reviewers for user reviewer.
     *
     * @param userId User ID
     * @return List of groups
     */
    public List<DocumentReviewerDto> findByUserId(String userId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();

        // Database Query
        StringBuilder sb = new StringBuilder("select distinct t.DOR_ID_C as c0, t.DOR_IDDOCUMENT_C as c1, t.DOR_IDUSER_C as c2, t.DOR_SCORE as c3 ");
        sb.append(" from T_DOCUMENT_REVIEWER t where t.DOR_IDUSER_C = :userId");
        Query q = em.createNativeQuery(sb.toString());
        q.setParameter("userId", userId);

        List<Object[]> l = q.getResultList();

        // Assemble results
        List<DocumentReviewerDto> docRevDtoList = new ArrayList<>();
        for (Object[] o : l) {
            int i = 0;
            DocumentReviewerDto docRevDto = new DocumentReviewerDto()
                                            .setId((String) o[i++])
                                            .setDocumentId((String) o[i++])
                                            .setUserId((String) o[i++])
                                            .setScore((int) o[i++]);
            docRevDtoList.add(docRevDto);
        }
        return docRevDtoList;
    }

    /*
     * Updates scores.
     * 
     * @param id documentReviewer id
     * @param score new score to be updated
     * @return updated documentReviewer
     */
    public DocumentReviewer update(DocumentReviewer docRev, String userId) {
        EntityManager em = ThreadLocalContext.get().getEntityManager();
        
        // Get the tag
        Query q = em.createQuery("select t from DocumentReviewer t where t.id = :id");
        q.setParameter("id", docRev.getId());
        DocumentReviewer docRevDb = (DocumentReviewer) q.getSingleResult();
        
        // Update the tag
        docRevDb.setDocumentId(docRev.getDocumentId());
        docRevDb.setUserId(docRev.getUserId());
        docRevDb.setScore(docRev.getScore());
        
        // Create audit log
        // AuditLogUtil.create(docRevDb, AuditLogType.UPDATE, userId);
        
        return docRevDb;
    }
}
