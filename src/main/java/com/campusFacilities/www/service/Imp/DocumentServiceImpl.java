package com.campusFacilities.www.service.Imp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.campusFacilities.www.model.Documents.Document;
import com.campusFacilities.www.model.Documents.DocumentAccessLog;
import com.campusFacilities.www.model.Documents.DocumentCategory;
import com.campusFacilities.www.model.Documents.DocumentShare;
import com.campusFacilities.www.model.Documents.DocumentVersion;
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
	   
	    //==========================DOCUMENTS========================== //

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

	    public Document updateDocument(Long id, Document updated, Long userId) {
	        Document document = getDocumentById(id);
	        if (!document.getOwnerUserId().equals(userId)) {
	            throw new RuntimeException("Unauthorized to update this document");
	        }
	        document.setTitle(updated.getTitle());
	        document.setAccessLevel(updated.getAccessLevel());
	        document.setStatus(updated.getStatus());
	        return documentRepository.save(document);
	    }

	    public Document patchDocument(Long id, Document updated, Long userId) {
	        Document document = getDocumentById(id);
	        if (!document.getOwnerUserId().equals(userId)) {
	            throw new RuntimeException("Unauthorized to patch this document");
	        }
	        if (updated.getTitle() != null) document.setTitle(updated.getTitle());
	        if (updated.getAccessLevel() != null) document.setAccessLevel(updated.getAccessLevel());
	        if (updated.getStatus() != null) document.setStatus(updated.getStatus());
	        return documentRepository.save(document);
	    }

	    public void deleteDocument(Long id, Long userId) {
	        Document document = getDocumentById(id);
	        if (!document.getOwnerUserId().equals(userId)) {
	            throw new RuntimeException("Unauthorized to delete this document");
	        }
	        document.setIsDeleted(true);
	        documentRepository.save(document);
	    }
	    
     // ========================== DOCUMENT CATEGORY ========================== //
	    

	    public DocumentCategory createCategory(DocumentCategory category) {
	        return categoryRepository.save(category);
	    }

	    public List<DocumentCategory> getAllCategories() {
	        return categoryRepository.findByIsDeletedFalse();
	    }

	    public DocumentCategory getCategoryById(Long id) {
	        return categoryRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Category not found"));
	    }

	    public DocumentCategory updateCategory(Long id, DocumentCategory updated) {
	        DocumentCategory category = getCategoryById(id);
	        category.setCategoryName(updated.getCategoryName());
	        category.setDescription(updated.getDescription());
	        category.setStatus(updated.getStatus());
	        return categoryRepository.save(category);
	    }

	    public DocumentCategory patchCategory(Long id, DocumentCategory updated) {
	        DocumentCategory category = getCategoryById(id);
	        if (updated.getCategoryName() != null) category.setCategoryName(updated.getCategoryName());
	        if (updated.getDescription() != null) category.setDescription(updated.getDescription());
	        if (updated.getStatus() != null) category.setStatus(updated.getStatus());
	        return categoryRepository.save(category);
	    }

	    public void deleteCategory(Long id) {
	        DocumentCategory category = getCategoryById(id);
	        category.setIsDeleted(true);
	        categoryRepository.save(category);
	    }

	//========================  DOCUMENT VERSION ==========================//
	    
	    public DocumentVersion addDocumentVersion(DocumentVersion version) {
	        return versionRepository.save(version);
	    }

	    public List<DocumentVersion> getVersionsByDocument(Long documentId) {
	        return versionRepository.findByDocumentDocumentId(documentId);
	    }

	    public DocumentVersion getVersionById(Long id) {
	        return versionRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Version not found"));
	    }

	    public DocumentVersion updateVersion(Long id, DocumentVersion updated) {
	        DocumentVersion version = getVersionById(id);
	        version.setVersionId(updated.getVersionId());
	        version.setFilePath(updated.getFilePath());
	        version.setFileSize(updated.getFileSize());
	        return versionRepository.save(version);
	    }

	    public DocumentVersion patchVersion(Long id, DocumentVersion updated) {
	        DocumentVersion version = getVersionById(id);
	        if (updated.getVersionId() != null) version.setVersionId(updated.getVersionId());
	        if (updated.getFilePath() != null) version.setFilePath(updated.getFilePath());
	        if (updated.getFileSize() != null) version.setFileSize(updated.getFileSize());
	        return versionRepository.save(version);
	    }

	    public void deleteVersion(Long id) {
	        DocumentVersion version = getVersionById(id);
	        versionRepository.delete(version);
	    }
	    
	  //========================== DOCUMENT ACCESS ==========================//

	    public DocumentAccessLog logAction(DocumentAccessLog log) {
	        return logRepository.save(log);
	    }

	    public List<DocumentAccessLog> getAllLogs() {
	        return logRepository.findAll();
	    }

	    public DocumentAccessLog getLogById(Long id) {
	        return logRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Log not found"));
	    }

	    public DocumentAccessLog updateLog(Long id, DocumentAccessLog updated) {
	        DocumentAccessLog log = getLogById(id);
	        log.setAction(updated.getAction());
	        return logRepository.save(log);
	    }

	    public DocumentAccessLog patchLog(Long id, DocumentAccessLog updated) {
	        DocumentAccessLog log = getLogById(id);
	        if (updated.getAction() != null) log.setAction(updated.getAction());
	        return logRepository.save(log);
	    }

	    public void deleteLog(Long id) {
	        DocumentAccessLog log = getLogById(id);
	        logRepository.delete(log);
	    }
	    
	   // =================== DOCUMENT SHARE  ========================== //

	    public DocumentShare shareDocument(DocumentShare share) {
	        return shareRepository.save(share);
	    }

	    public List<DocumentShare> getDocumentsSharedWithUser(Long userId) {
	        return shareRepository.findBySharedWithUserId(userId);
	    }

	    public DocumentShare getSharedDocumentById(Long id) {
	        return shareRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Shared document not found"));
	    }

	    public DocumentShare updateSharedDocument(Long id, DocumentShare updated) {
	        DocumentShare share = getSharedDocumentById(id);
	        share.setSharedWithUserId(updated.getSharedWithUserId());
	        return shareRepository.save(share);
	    }

	    public DocumentShare patchSharedDocument(Long id, DocumentShare updated) {
	        DocumentShare share = getSharedDocumentById(id);
	        if (updated.getSharedWithUserId() != null) share.setSharedWithUserId(updated.getSharedWithUserId());
	        return shareRepository.save(share);
	    }

	    public void deleteSharedDocument(Long id) {
	        DocumentShare share = getSharedDocumentById(id);
	        shareRepository.delete(share);
	    }
}

