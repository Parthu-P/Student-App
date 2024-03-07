package in.zkteco.entity;

import java.time.LocalDate;

import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name"}),@UniqueConstraint(columnNames = { "code"}) })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "classId")
public class ClassEntity {

    @Id
    @Column(name = "CLASS_ID")
    private Integer classId;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "CODE")
    private String code;

    @CreationTimestamp
    @Column(name = "CREATED_DATE",updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "UPDATED_DATE",insertable = false)
    private LocalDate updatedDate;

    //@JsonManagedReference
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<StudentEntity> studentList;

    //@JsonManagedReference
    @OneToMany(mappedBy = "classEntity", cascade = CascadeType.ALL)
    private List<SubjectEntity> subjectList;
}
