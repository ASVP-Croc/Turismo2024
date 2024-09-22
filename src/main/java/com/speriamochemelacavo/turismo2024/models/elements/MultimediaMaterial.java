package com.speriamochemelacavo.turismo2024.models.elements;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class MultimediaMaterial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;

    private boolean isValidate;
    
    @ManyToOne
    private Tour tour;

    @ElementCollection
    @CollectionTable(name = "fileData", joinColumns = @JoinColumn(name = "materialId"))
    @Column(name = "file")
    private List<String> fileList;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt;

}
