package org.example.backend.service;

import org.example.backend.dao.RegCardDAO;
import org.example.backend.dto.RegCardResponseDTO;
import org.example.backend.dto.RegCardUpdateDTO;
import org.example.backend.model.Document;
import org.example.backend.model.RegCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class RegCardService {
    private final RegCardDAO regCardDAO;
    private final DocumentService documentService;

    @Autowired
    public RegCardService(RegCardDAO regCardDAO, DocumentService documentService) {
        this.regCardDAO = regCardDAO;
        this.documentService = documentService;
    }

    @Transactional(readOnly = true)
    public List<RegCardResponseDTO> findAll() {
        List<RegCard> documents = regCardDAO.index();
        return documents.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private RegCardResponseDTO convertToDTO(RegCard regCard) {
        RegCardResponseDTO dto = new RegCardResponseDTO();

        dto.setRegCardId(regCard.getRegCardId());
        dto.setDocument(regCard.getDocument());
        dto.setDocumentIntroNumber(regCard.getDocumentIntroNumber());
        dto.setDocumentExternNumber(regCard.getDocumentExternNumber());
        dto.setDateIntro(regCard.getDateIntro());
        dto.setDateExtern(regCard.getDateExtern());
        return dto;
    }

    @Transactional(readOnly = true)
    public RegCardResponseDTO findById(int id) {
        RegCard regCard = regCardDAO.show(id);
        System.out.println(regCard);
        return convertToDTO(regCard);
    }

    @Transactional(readOnly = true)
    public RegCardResponseDTO findByDocumentId(int documentId) {
        RegCard regCard = regCardDAO.showByDocumentId(documentId);
        if (regCard == null) {
            throw new NoSuchElementException("No RegCard found with document ID: " + documentId);
        }
        return convertToDTO(regCard);
    }

    @Transactional
    public void save(RegCard regCard) {
        regCardDAO.save(regCard);
    }

    @Transactional
    public void saveRegCard(String documentIntroNumber,
                            LocalDateTime dateIntro,
                            int documentId) throws IOException {
        if (documentIntroNumber == null || dateIntro == null || documentId < 0) {
            throw new IllegalArgumentException("Invalid input data");
        }

        Document document = documentService.findDocumentById(documentId);

        RegCard regCard = new RegCard();

        regCard.setDocument(document);
        regCard.setDocumentIntroNumber(documentIntroNumber);
        regCard.setDateIntro(dateIntro);

        regCardDAO.save(regCard);
    }

    @Transactional
    public void update(int id, RegCardUpdateDTO regCardUpdateDTO) {
        RegCard regCard = regCardDAO.show(id);
        regCard.setDocumentExternNumber(regCardUpdateDTO.getDocumentExternNumber());
        regCard.setDateExtern(LocalDateTime.now());
        regCardDAO.update(id, regCard);
    }

    @Transactional
    public void delete(int id) {
        regCardDAO.delete(id);
    }
}
