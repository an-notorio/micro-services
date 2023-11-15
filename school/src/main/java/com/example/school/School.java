package com.example.school;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@SQLDelete(sql = "UPDATE school SET deleted = true WHERE id=?")
@FilterDef(name = "deletedSchoolFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedSchoolFilter", condition = "deleted = :isDeleted")
public class School {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String email;

    private boolean deleted = Boolean.FALSE;
}
