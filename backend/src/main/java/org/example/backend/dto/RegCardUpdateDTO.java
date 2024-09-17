package org.example.backend.dto;

import javax.validation.constraints.NotEmpty;

public class RegCardUpdateDTO {

    @NotEmpty(message = "documentExternNumber should not be empty")
    private String documentExternNumber;

    public String getDocumentExternNumber() {
        return documentExternNumber;
    }

    public void setDocumentExternNumber(String documentExternNumber) {
        this.documentExternNumber = documentExternNumber;
    }
}
