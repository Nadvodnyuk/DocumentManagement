package org.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.example.backend.dao.DocumentDAO;


@Controller
@RequestMapping("/document")
public class DocumentController {
    private final DocumentDAO documentDAO;

    @Autowired
    public DocumentController(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }
}
