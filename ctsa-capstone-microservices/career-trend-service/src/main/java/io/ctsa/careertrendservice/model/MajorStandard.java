package io.ctsa.careertrendservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MajorStandard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int majorId;
    private double standard;
    private String unit;
    private String source;
}
