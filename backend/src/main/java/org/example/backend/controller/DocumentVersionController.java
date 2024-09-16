package org.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.backend.dao.DocumentVersionDAO;


@Controller
@RequestMapping("/documentVersion")
public class DocumentVersionController {
    private final DocumentVersionDAO documentVersionDAO;

    @Autowired
    public DocumentVersionController(DocumentVersionDAO documentVersionDAO) {
        this.documentVersionDAO = documentVersionDAO;
    }

}
