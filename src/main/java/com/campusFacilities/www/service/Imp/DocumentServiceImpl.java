package com.campusFacilities.www.service.Imp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Documents.Document;
import com.campusFacilities.www.model.Documents.DocumentAccessLog;
import com.campusFacilities.www.model.Documents.DocumentCategory;
import com.campusFacilities.www.model.Documents.DocumentShare;
import com.campusFacilities.www.model.Documents.DocumentVersion;
import com.campusFacilities.www.model.Documents.User;
import com.campusFacilities.www.repository.Documnets.DocumentAccessLogRepository;
import com.campusFacilities.www.repository.Documnets.DocumentCategoryRepository;
import com.campusFacilities.www.repository.Documnets.DocumentRepository;
import com.campusFacilities.www.repository.Documnets.DocumentShareRepository;
import com.campusFacilities.www.repository.Documnets.DocumentVersionRepository;

@Service
public class DocumentServiceImpl {
	
	
	    @Autowired
	    private DocumentCategoryRepository categoryRepository;

	    @Autowired
	    private DocumentRepository documentRepository;

	    @Autowired
	    private DocumentVersionRepository versionRepository;

	    @Autowired
	    private DocumentAccessLogRepository logRepository;

	    @Autowired
	    private DocumentShareRepository shareRepository;
	   
	    /* ==========================
	       DOCUMENT METHODS
	       ========================== */

	    public Document uploadDocument(Document document) {
	        return documentRepository.save(document);
	    }

	    public List<Document> getAllDocuments() {
	        return documentRepository.findByIsDeletedFalse();
	    }

	    public Document getDocumentById(Long id) {
	        return documentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Document not found"));
	    }

	    public Document updateDocument(Long id, Document updated) {
	        Document document = getDocumentById(id);
	        document.setTitle(updated.getTitle());
	        document.setAccessLevel(updated.getAccessLevel());
	        document.setStatus(updated.getStatus());
	        return documentRepository.save(document);
	    }

	    public void deleteDocument(Long id) {
	        Document document = getDocumentById(id);
	        document.setIsDeleted(true);
	        documentRepository.save(document);
	    }

	    /* ==========================
	       DOCUMENT CATEGORY METHODS
	       ========================== */

	    public DocumentCategory createCategory(DocumentCategory category) {
	        return categoryRepository.save(category);
	    }

	    public List<DocumentCategory> getAllCategories() {
	        return categoryRepository.findAll();
	    }

	    public DocumentCategory updateCategory(Long id, DocumentCategory updated) {
	        DocumentCategory category = categoryRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Category not found"));

	        category.setCategoryName(updated.getCategoryName());
	        category.setDescription(updated.getDescription());
	        category.setStatus(updated.getStatus());

	        return categoryRepository.save(category);
	    }

	    public void deleteCategory(Long id) {
	        DocumentCategory category = categoryRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	        category.setIsDeleted(true);
	        categoryRepository.save(category);
	    }

	   

	    /* ==========================
	       DOCUMENT VERSION
	       ========================== */

	    public DocumentVersion addDocumentVersion(DocumentVersion version) {
	        return versionRepository.save(version);
	    }

	    public List<DocumentVersion> getVersionsByDocument(Long documentId) {
	        return versionRepository.findByDocumentDocumentId(documentId);
	    }

	    /* ==========================
	             DOCUMENT ACCESS 
	       ========================== */

	    public void logDocumentAction(
	            Document document,
	          	 User user,
	            DocumentAccessLog.Action action) {

	        DocumentAccessLog log = new DocumentAccessLog();
	        log.setDocument(document);
	        //log.setAccessedBy(user);
	        log.setAction(action);

	        logRepository.save(log);
	    }

	    public List<DocumentAccessLog> getAllLogs() {
	        return logRepository.findAll();
	    }

	    /* ==========================
	       DOCUMENT SHARE METHODS
	       ========================== */

	    public DocumentShare shareDocument(DocumentShare share) {
	        return shareRepository.save(share);
	    }

	    public List<DocumentShare> getDocumentsSharedWithUser(Long userId) {
	        return shareRepository.findBySharedWithUserId(userId);
	    }
	}
	

