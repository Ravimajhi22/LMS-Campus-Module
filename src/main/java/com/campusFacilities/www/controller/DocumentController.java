package com.campusFacilities.www.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.campusFacilities.www.model.Documents.Document;
import com.campusFacilities.www.model.Documents.DocumentAccessLog;
import com.campusFacilities.www.model.Documents.DocumentCategory;
import com.campusFacilities.www.model.Documents.DocumentShare;
import com.campusFacilities.www.model.Documents.DocumentVersion;
import com.campusFacilities.www.model.Documents.User;
import com.campusFacilities.www.service.Imp.DocumentServiceImpl;
@RestController
	@RequestMapping("/documents")
	
	public class DocumentController {
	   
	@Autowired
	private DocumentServiceImpl service;

	  
	    /* ==========================
	                DOCUMENT 
	       ========================== */

	    @PostMapping
	    public Document uploadDocument(@RequestBody Document document) {
	        return service.uploadDocument(document);
	    }

	    @GetMapping
	    public List<Document> getAllDocuments() {
	        return service.getAllDocuments();
	    }

	    @GetMapping("/{id}")
	    public Document getDocumentById(@PathVariable Long id) {
	        return service.getDocumentById(id);
	    }

	    @PutMapping("/{id}")
	    public Document updateDocument(
	            @PathVariable Long id,
	            @RequestBody Document document) {
	        return service.updateDocument(id, document);
	    }

	    @DeleteMapping("/{id}")
	    public String deleteDocument(@PathVariable Long id) {
	        service.deleteDocument(id);
	        return "Document deleted successfully";
	    }
	    
	    /* ==========================
	            DOCUMENT CATEGORY 
	       ========================== */

	    @PostMapping("/categories")
	    public DocumentCategory createCategory(@RequestBody DocumentCategory category) {
	        return service.createCategory(category);
	    }

	    @GetMapping("/categories")
	    public List<DocumentCategory> getAllCategories() {
	        return service.getAllCategories();
	    }

	    @PutMapping("/categories/{id}")
	    public DocumentCategory updateCategory(
	            @PathVariable Long id,
	            @RequestBody DocumentCategory category) {
	        return service.updateCategory(id, category);
	    }

	    @DeleteMapping("/categories/{id}")
	    public String deleteCategory(@PathVariable Long id) {
	        service.deleteCategory(id);
	        return "Category deleted successfully";
	    }

	    /* ==========================
	       DOCUMENT VERSION
	       ========================== */

	    @PostMapping("/versions")
	    public DocumentVersion addDocumentVersion(
	            @RequestBody DocumentVersion version) {
	        return service.addDocumentVersion(version);
	    }

	    @GetMapping("/{documentId}/versions")
	    public List<DocumentVersion> getVersions(
	            @PathVariable Long documentId) {
	        return service.getVersionsByDocument(documentId);
	    }

	    /* ==========================
	       DOCUMENT ACCESS LOG
	       ========================== */

	    @PostMapping("/logs")
	    public String logDocumentAction(
	            @RequestParam Long documentId,
	            @RequestParam Long userId,
	            @RequestParam DocumentAccessLog.Action action) {

	        Document document = service.getDocumentById(documentId);
	       User user = new User();
	       user.setUserId(userId);  
	        return "Action logged successfully";
	    }

	    @GetMapping("/logs")
	    public List<DocumentAccessLog> getAllLogs() {
	        return service.getAllLogs();
	    }

	    /* ==========================
	              DOCUMENT SHARE 
	       ========================== */

	    @PostMapping("/share")
	    public DocumentShare shareDocument(
	            @RequestBody DocumentShare share) {
	        return service.shareDocument(share);
	    }

	    @GetMapping("/shared/{userId}")
	    public List<DocumentShare> getSharedDocuments(
	            @PathVariable Long userId) {
	        return service.getDocumentsSharedWithUser(userId);
	    }
	}


