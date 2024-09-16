package org.example.backend.service;

import org.example.backend.dao.RegCardDAO;
import org.example.backend.model.RegCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RegCardService {
    private final RegCardDAO regCardDAO;

    @Autowired
    public RegCardService(RegCardDAO regCardDAO) {
        this.regCardDAO = regCardDAO;
    }

    @Transactional(readOnly = true)
    public List<RegCard> findAll() {
        return regCardDAO.index();
    }

    @Transactional(readOnly = true)
    public RegCard findById(int id) {
        return regCardDAO.show(id);
    }

    @Transactional
    public void save(RegCard regCard) {
        regCardDAO.save(regCard);
    }

    @Transactional
    public void update(int id, RegCard updatedRegCard) {
        regCardDAO.update(id, updatedRegCard);
    }

    @Transactional
    public void delete(int id) {
        regCardDAO.delete(id);
    }
}
