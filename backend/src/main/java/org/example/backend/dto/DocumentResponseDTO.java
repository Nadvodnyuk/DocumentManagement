package org.example.backend.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DocumentResponseDTO {
    private int documentId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String documentName;


    @NotEmpty(message = "Author's login should not be empty")
    @Size(min = 2, max = 50, message = "Author's login should be between 2 and 50 characters")
    private String author;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
