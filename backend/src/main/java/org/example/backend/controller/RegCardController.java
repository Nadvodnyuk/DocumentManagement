package org.example.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.example.backend.dao.RegCardDAO;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/regCard")
public class RegCardController {
    private final RegCardDAO regCardDAO;

    @Autowired
    public RegCardController(RegCardDAO regCardDAO) {
        this.regCardDAO = regCardDAO;
    }
}
