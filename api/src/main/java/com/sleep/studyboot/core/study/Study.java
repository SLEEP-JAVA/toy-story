package com.sleep.studyboot.core.study;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;

@Entity
@Getter
public class Study {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 512)
    private String description;

    @Column(length = 20, nullable = false)
    private String place;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    // TODO: Create User Table
//    @CreatedBy
//    private User leader;

    private OffsetDateTime createdOn;
    private OffsetDateTime modifiedOn;
    private OffsetDateTime removedOn;

    @PrePersist
    protected void onCreate() {
        var now = OffsetDateTime.now();

        this.createdOn = now;
        this.modifiedOn = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.modifiedOn = OffsetDateTime.now();
    }

    public Study() {
    }

    @Builder
    public Study(String name, Category category, String description, String place, int capacity, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.place = place;
        this.capacity = capacity;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected void rename(String name) {
        this.name = name;
    }

    protected void changePeriod(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    protected void remove() {
        this.removedOn = OffsetDateTime.now();
    }
}