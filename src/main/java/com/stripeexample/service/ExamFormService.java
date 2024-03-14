package com.stripeexample.service;

import com.stripeexample.entities.ExamForm;
import com.stripeexample.repository.ExamFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamFormService {

    @Autowired
    private ExamFormRepository examFormRepository;

    // Save or update an exam form
    public void saveOrUpdateExamForm(ExamForm examForm) {
        examFormRepository.save(examForm);
    }

    // Get an exam form by ID
    public ExamForm getExamFormById(Long id) {
        Optional<ExamForm> optionalExamForm = examFormRepository.findById(id);
        return optionalExamForm.orElse(null);
    }

    // Check if an exam form exists by ID
    public boolean existsById(Long id) {
        return examFormRepository.existsById(id);
    }

    // List all exam forms with pagination
    public Page<ExamForm> listAllExamForms(Pageable pageable) {
        return examFormRepository.findAll(pageable);
    }
}
