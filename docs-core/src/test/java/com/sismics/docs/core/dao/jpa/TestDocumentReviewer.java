package com.sismics.docs.core.dao.jpa;

import com.sismics.docs.BaseTransactionalTest;
import com.sismics.docs.core.dao.UserDao;
import com.sismics.docs.core.model.jpa.User;
import com.sismics.docs.core.dao.DocumentDao;
import com.sismics.docs.core.model.jpa.Document;
import com.sismics.docs.core.dao.DocumentReviewerDao;
import com.sismics.docs.core.model.jpa.DocumentReviewer;
import com.sismics.docs.core.dao.dto.DocumentReviewerDto;

import com.sismics.docs.core.util.TransactionUtil;
import com.sismics.docs.core.util.authentication.InternalAuthenticationHandler;
import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Tests the persistance layer.
 * 
 * @author jtremeaux
 */
public class TestDocumentReviewer extends BaseTransactionalTest {
    @Test
    public void testDocumentReviewer() throws Exception {
        // Create a user
        UserDao userDao = new UserDao();
        User user = new User();
        user.setUsername("username");
        user.setPassword("12345678");
        user.setEmail("toto@docs.com");
        user.setRoleId("admin");
        user.setStorageQuota(10L);
        String userId = userDao.create(user, "me");

        // Create a document
        DocumentDao docDao = new DocumentDao();
        Document doc = new Document();
        doc.setTitle("Test");
        doc.setUserId(userId);
        doc.setCreateDate(new Date());
        doc.setUpdateDate(new Date());
        doc.setLanguage("English");
        String docId = docDao.create(doc, "me");

        // Create a document reviewer
        DocumentReviewerDao docRevDao = new DocumentReviewerDao();
        DocumentReviewer docRev = new DocumentReviewer();
        docRev.setDocumentId(doc.getId());
        docRev.setUserId(user.getId());
        docRev.setScore(5);
        String docRevId = docRevDao.create(docRev, userId, docId);
        
        TransactionUtil.commit();

        // Search a document reviewer by documentId
        List<DocumentReviewerDto> docRevList = docRevDao.findByDocumentId(docId);
        Assert.assertNotNull(docRevList);
        Assert.assertEquals(docRevList.size(), 1);
        Assert.assertEquals(userId, docRevList.get(0).getUserId());
    }
}