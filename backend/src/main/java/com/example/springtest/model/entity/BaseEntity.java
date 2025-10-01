package com.example.springtest.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "create_at"  )
    private LocalDate createdAt;

    @Column(name = "last_update", updatable = false)
    private LocalDate lastUpdate;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.lastUpdate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdate = LocalDate.now();
    }

    public abstract String toString();
}
