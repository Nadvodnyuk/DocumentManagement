package org.example.backend.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class DocumentVersionCreateDTO {
    private int documentId;

    @NotEmpty(message = "Author's login should not be empty")
    @Size(min = 2, max = 50, message = "Author's login should be between 2 and 50 characters")
    private String versionAuthor;

    public int getDocumentId() {
        return documentId;
    }

    public void setDocumentId(int documentId) {
        this.documentId = documentId;
    }

    public String getVersionAuthor() {
        return versionAuthor;
    }

    public void setVersionAuthor(String versionAuthor) {
        this.versionAuthor = versionAuthor;
    }
}
