package org.example.backend.dto;

import org.example.backend.model.Document;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class RegCardResponseDTO {
    private int regCardId;

    private Document document;

    @NotEmpty(message = "documentIntroNumber should not be empty")
    private String documentIntroNumber;

    @NotEmpty(message = "documentExternNumber should not be empty")
    private String documentExternNumber;

    private LocalDateTime dateIntro;

    private LocalDateTime dateExtern;

    public int getRegCardId() {
        return regCardId;
    }

    public void setRegCardId(int regCardId) {
        this.regCardId = regCardId;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getDocumentIntroNumber() {
        return documentIntroNumber;
    }

    public void setDocumentIntroNumber(String documentIntroNumber) {
        this.documentIntroNumber = documentIntroNumber;
    }

    public String getDocumentExternNumber() {
        return documentExternNumber;
    }

    public void setDocumentExternNumber(String documentExternNumber) {
        this.documentExternNumber = documentExternNumber;
    }

    public LocalDateTime getDateIntro() {
        return dateIntro;
    }

    public void setDateIntro(LocalDateTime dateIntro) {
        this.dateIntro = dateIntro;
    }

    public LocalDateTime getDateExtern() {
        return dateExtern;
    }

    public void setDateExtern(LocalDateTime dateExtern) {
        this.dateExtern = dateExtern;
    }
}
