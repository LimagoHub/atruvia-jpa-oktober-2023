package com.example.jpaweb.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "tbl_personen2")
public class Person {

    @Builder.Default
    @Id
    private UUID id = UUID.randomUUID();
    private String vorname;
    private String nachname;
}
