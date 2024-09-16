package org.example.backend.model;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    @Column(name = "documentId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int documentId;

    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    @Column(name = "documentName")
    private String documentName;


    @Column(name = "author")
    @NotEmpty(message = "Author's login should not be empty")
    @Size(min = 2, max =50, message = "Author's login should be between 2 and 50 characters")
    private String author;

    public Document() {

    }

    public Document(String documentName, String author) {
        this.documentName = documentName;
        this.author = author;
    }

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

    @Override
    public String toString() {
        return "Document{" +
                "id=" + documentId +
                ", documentName='" + documentName +
                ", author=" + author +
                '}';
    }
}