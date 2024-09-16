package org.example.backend.dto;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class DocumentCreateDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 100, message = "Name should be between 2 and 100 characters")
    private String documentName;

    @NotEmpty(message = "Author's login should not be empty")
    @Size(min = 2, max =50, message = "Author's login should be between 2 and 50 characters")
    private String author;

    @NotEmpty(message = "documentIntroNumber should not be empty")
    private String documentIntroNumber;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime dateIntro;


    public String getDocumentName() {
        return documentName;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }

    public LocalDateTime getDateIntro() {
        return dateIntro;
    }

    public void setDateIntro(LocalDateTime dateIntro) {
        this.dateIntro = dateIntro;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
