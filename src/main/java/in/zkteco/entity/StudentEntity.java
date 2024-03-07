package in.zkteco.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Table(name = "Student_entity", uniqueConstraints = { @UniqueConstraint(columnNames = { "email" }),
		@UniqueConstraint(columnNames = { "phone" }), @UniqueConstraint(columnNames = { "password" }) })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "studentId")
public class StudentEntity {

	@Id
	@Column(name = "STUDENT_ID")
	private Integer studentId;

	@NotNull
	@Size(min = 3, max = 30, message = "Firstname must be between 3 and 30 ")
	@Column(name = "FIRST_NAME")
	private String firstname;

	@NotNull
	@Column(name = "LAST_NAME")
	private String lastname;

	@Column(name = "GENDER")
	private String gender;

	@NotNull
	@Column(name = "EMAIL")
	@Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}", message = "Email should be case-sencitive")
	private String email;

	@NotNull
	@Column(name = "PHONE")
	private Long phone;

	@NotNull
	@Size(min = 8, max = 30)
	@Column(name = "PASSWORD")
	private String password;

	@Column(name = "DOB")
	private LocalDate dob;

	@CreationTimestamp
	@Column(name = "CREATED_DATE", updatable = false)
	private LocalDate createdDate;

	@UpdateTimestamp
	@Column(name = "UPDATED_DATE", insertable = false)
	private LocalDate updatedDate;

	// @JsonBackReference
	@ManyToOne()
	@JoinColumn(name = "class_id")
	private ClassEntity classEntity;
}
