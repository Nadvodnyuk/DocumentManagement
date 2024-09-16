package org.example.backend.dto;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class RegCardUpdateDTO {

    private int regCardId;

    @NotEmpty(message = "documentExternNumber should not be empty")
    private String documentExternNumber;

    private LocalDateTime dateExtern;

    public int getRegCardId() {
        return regCardId;
    }

    public void setRegCardId(int regCardId) {
        this.regCardId = regCardId;
    }

    public String getDocumentExternNumber() {
        return documentExternNumber;
    }

    public void setDocumentExternNumber(String documentExternNumber) {
        this.documentExternNumber = documentExternNumber;
    }

    public LocalDateTime getDateExtern() {
        return dateExtern;
    }

    public void setDateExtern(LocalDateTime dateExtern) {
        this.dateExtern = dateExtern;
    }
}
