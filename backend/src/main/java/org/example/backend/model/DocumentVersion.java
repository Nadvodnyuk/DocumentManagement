package org.example.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "DocumentVersion")
public class DocumentVersion {
    @Id
    @Column(name = "documentVersionId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentVersionId;

    @ManyToOne
    @JoinColumn(name = "documentId")
    private Document document;

    @Column(name = "versionAuthor")
    @NotEmpty(message = "Author's login should not be empty")
    @Size(min = 2, max =50, message = "Author's login should be between 2 and 50 characters")
    private String versionAuthor;

    @Lob
    @Column(name = "content")
    private byte[] content;

    public DocumentVersion() {

    }

    public DocumentVersion(int documentVersionId,
                           Document documentId,
                           String versionAuthor,
                           byte[] content) {
        this.documentVersionId = documentVersionId;
        this.document = documentId;
        this.versionAuthor = versionAuthor;
        this.content = content;
    }

    public int getDocumentVersionId() {
        return documentVersionId;
    }

    public void setDocumentVersionId(int documentVersionId) {
        this.documentVersionId = documentVersionId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document documentId) {
        this.document = documentId;
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

    @Override
    public String toString() {
        return "DocumentVersion{" +
                "documentVersionId=" + documentVersionId +
                ", document='" + document +
                ", versionAuthor=" + versionAuthor +
                ", content=" + content +
                '}';
    }
}