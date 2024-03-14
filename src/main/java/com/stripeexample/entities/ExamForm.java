package com.stripeexample.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class ExamForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String studentName;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date examDate;

    @Column(nullable = false)
    private String selectBoxValue;

    // Define a field to store the image path or you can use byte[] to store the image directly
    // @Lob
    // private byte[] image1;
    // @Lob
    // private byte[] image2;

    // For simplicity, let's assume image paths are stored as strings
    @Column
    private String image1Path;

    @Column
    private String image2Path;

    @Column(nullable = false)
    private int updateCount; // To keep track of the number of updates
}
