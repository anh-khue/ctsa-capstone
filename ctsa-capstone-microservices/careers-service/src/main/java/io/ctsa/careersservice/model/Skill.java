package io.ctsa.careersservice.model;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "image_url")
    private String imageUrl;
    @Column(name = "skill_type_id")
    private int skillTypeId;

    @ManyToOne
    @JoinColumn(name = "skill_type_id", referencedColumnName = "id", nullable = false,
                insertable = false, updatable = false)
    private SkillType skillType;
}
