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

import in.zkteco.entity.ClassEntity;
import in.zkteco.services.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/class")
@RequiredArgsConstructor
@Tag(name = "Class")
public class ClassController {

	private final ClassService classService;

	@Operation(description = "This end point used for Class", summary = "This end point is used to create Class", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public ClassEntity createClass(@Valid @RequestBody ClassEntity classEntity) {
		classService.createClass(classEntity);
		return classEntity;

	}

	@Operation(description = "This end point used for Class", summary = "This end point is used to fetch all Class", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetchAll")
	public ResponseEntity<List<ClassEntity>> fetchAllClasses() {
		List<ClassEntity> fetchAllClasses = classService.fetchAllClasses();
		return ResponseEntity.ok(fetchAllClasses);
	}

	@Operation(description = "This end point used for Class", summary = "This end point is used to delete Class", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@DeleteMapping("/delete/{classId}")
	public ResponseEntity<String> deleteClass(@PathVariable("classId") Integer classId) {
		classService.deleteClassById(classId);
		return ResponseEntity.ok("Class "+classId+" deleted");
	}

	@Operation(description = "This end point used for Class", summary = "This end point is used to update Class", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PutMapping("/update/{classId}")
	@ResponseStatus(HttpStatus.CREATED)
	public ClassEntity updateClassInfo(@RequestBody ClassEntity classEntity, @PathVariable("classId") Integer classId) {
		classService.updateClassInfo(classEntity, classId);
		return classEntity;
	}

	@Operation(description = "This end point used for Class", summary = "This end point is used to create multiple Class", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/multiple/students")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ClassEntity> createMultipleClasses(@RequestBody List<ClassEntity> classEntities) {
		classService.createMultipleClasses(classEntities);
		return classEntities;

	}
	
	@Operation(description = "This end point used for Class", summary = "This end point is used to fetch class by id", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetch/{classId}")
	@ResponseStatus(HttpStatus.OK)
	public ClassEntity fetchClassById(@PathVariable("classId") Integer classId) {
		return classService.fetchClassById(classId);
	}

}
