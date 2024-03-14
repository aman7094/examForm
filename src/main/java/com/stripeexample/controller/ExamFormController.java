package com.stripeexample.controller;

import com.stripeexample.entities.ExamForm;
import com.stripeexample.service.ExamFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/exam-forms")
public class ExamFormController {

    @Autowired
    private ExamFormService examFormService;

    // Endpoint to save an exam form
    @PostMapping
    public ResponseEntity<String> saveExamForm(@RequestBody ExamForm examForm) {
        examFormService.saveOrUpdateExamForm(examForm);
        return new ResponseEntity<>("Exam form saved successfully", HttpStatus.CREATED);
    }

    // Endpoint to update an exam form
    @PutMapping("/{id}")
    public ResponseEntity<String> updateExamForm(@PathVariable Long id, @RequestBody ExamForm examForm) {
        if (!examFormService.existsById(id)) {
            return new ResponseEntity<>("Exam form not found", HttpStatus.NOT_FOUND);
        }
        examForm.setId(id);
        examFormService.saveOrUpdateExamForm(examForm);
        return new ResponseEntity<>("Exam form updated successfully", HttpStatus.OK);
    }

    // Endpoint to get an exam form by ID
    @GetMapping("/{id}")
    public ResponseEntity<ExamForm> getExamFormById(@PathVariable Long id) {
        ExamForm examForm = examFormService.getExamFormById(id);
        if (examForm == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(examForm, HttpStatus.OK);
    }

    // Endpoint to list exam forms with pagination
    @GetMapping
    public ResponseEntity<Page<ExamForm>> listExamForms(@RequestParam(defaultValue = "0") int page,
                                                        @RequestParam(defaultValue = "10") int size) {
       Pageable pageable = PageRequest.of(page, size);
       Page<ExamForm> examForms = examFormService.listAllExamForms(pageable);
        return new ResponseEntity<>(examForms, HttpStatus.OK);
    }

    // Endpoint to render the exam form HTML page
    @GetMapping("/")
    public String home() {
        return "examForm"; // Assuming you have an HTML file named exam_Form.html in your templates directory
    }

    // Endpoint to handle file uploads
    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>("Please select a file to upload", HttpStatus.BAD_REQUEST);
        }
        try {
            // Process the file (save it to disk, etc.)
            // Example:
            // byte[] fileBytes = file.getBytes();
            // saveFileToDisk(fileBytes);
            return new ResponseEntity<>("File uploaded successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Failed to upload file", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

