package in.zkteco.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.zkteco.entity.StudentEntity;
import in.zkteco.services.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/student")
@RequiredArgsConstructor
@Tag(name="Student")
public class StudentController {

	private final StudentService studentService;

	@Operation(description = "This end point used for Student", summary = "This end point is used to create Student", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/create")
	public ResponseEntity<StudentEntity> studentSignUp(@Valid @RequestBody StudentEntity studentEntity) {
		studentService.createStudent(studentEntity);
		return new ResponseEntity<>(studentEntity,HttpStatus.CREATED);
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to update Student", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PutMapping("/update/{studentId}")
	@ResponseStatus(HttpStatus.CREATED)
	public StudentEntity updateStudentInfo(@Valid @RequestBody StudentEntity studentEntity, @PathVariable("studentId") Integer studentId) {
		 return studentService.updateStudentInfo(studentEntity, studentId);
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to fetch student by id", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetch/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public StudentEntity fetchStudentById(@PathVariable("studentId") Integer studentId) {
		return studentService.getStudentById(studentId);
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to delete student", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@DeleteMapping("/delete/{studentId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteStudent(@PathVariable("studentId") Integer studentId) {
		studentService.deleteStudentById(studentId);
		return "Student "+studentId+"  Deleted";
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to fetch all student", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetchAll")
	@ResponseStatus(HttpStatus.OK)
	public List<StudentEntity> fetchAllStudents(){
		return studentService.fetchAllStudents();
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to create multiple student", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/multiple/students")
	@ResponseStatus(HttpStatus.CREATED)
	public List<StudentEntity> createMultipleStudents(@Valid @RequestBody List<StudentEntity> studentEntities){
		return studentService.createMultipleStudents(studentEntities);
	}
	
}
