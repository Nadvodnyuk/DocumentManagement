package org.example.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;


@Entity
public class RegCard {
    @Id
    @Column(name = "regCardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int regCardId;

    @OneToOne
    @JoinColumn(name = "documentId")
    private Document documentId;

    @Column(name = "documentIntroNumber")
    @NotEmpty(message = "documentIntroNumber should not be empty")
    private String documentIntroNumber;

    @Column(name = "documentExternNumber")
    @NotEmpty(message = "documentExternNumber should not be empty")
    private String documentExternNumber;

    @Column(name = "dateIntro")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateIntro;

    @Column(name = "dateExtern")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime dateExtern;

    public RegCard() {

    }

    public RegCard(int regCardId,
                   Document documentId,
                   String documentIntroNumber,
                   String documentExternNumber,
                   LocalDateTime dateIntro,
                   LocalDateTime dateExtern) {
        this.regCardId = regCardId;
        this.documentId = documentId;
        this.documentIntroNumber = documentIntroNumber;
        this.documentExternNumber = documentExternNumber;
        this.dateIntro = dateIntro;
        this.dateExtern = dateExtern;
    }

    @PrePersist
    protected void onCreate() {
        this.dateIntro = LocalDateTime.now();
    }

    public int getRegCardId() {
        return regCardId;
    }

    public void setRegCardId(int regCardId) {
        this.regCardId = regCardId;
    }

    public Document getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Document documentId) {
        this.documentId = documentId;
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

    @Override
    public String toString() {
        return "DocumentVersion{" +
                "regCardId=" + regCardId +
                ", documentId='" + documentId +
                ", documentIntroNumber=" + documentIntroNumber +
                ", documentExternNumber=" + documentExternNumber +
                ", dateIntro=" + dateIntro +
                ", dateExtern=" + dateExtern +
                '}';
    }
}
