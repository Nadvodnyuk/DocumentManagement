package org.example.backend.dto;

import org.example.backend.model.Document;


public class DocumentVersionResponseDTO {

    private int documentVersionId;

    private Document document;

    private String versionAuthor;

    private byte[] content;

    public int getDocumentVersionId() {
        return documentVersionId;
    }

    public void setDocumentVersionId(int documentVersionId) {
        this.documentVersionId = documentVersionId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getVersionAuthor() {
        return versionAuthor;
    }

    public void setVersionAuthor(String versionAuthor) {
        this.versionAuthor = versionAuthor;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }
}
