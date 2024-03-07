package in.zkteco.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import in.zkteco.entity.SubjectEntity;
import in.zkteco.services.SubjectService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/subject")
@RequiredArgsConstructor
@Tag(name="Subject")
public class SubjectController {
	
	
	private final SubjectService subjectService;
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to create Subject", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/create")
	@ResponseStatus(HttpStatus.CREATED)
	public SubjectEntity createSubject(@RequestBody SubjectEntity subjectEntity) {
		subjectService.createSubject(subjectEntity);
		return subjectEntity;
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to fetch all Subject", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetchAll")
	@ResponseStatus(HttpStatus.OK)
	public List<SubjectEntity> fetchAllSubjects(){
		List<SubjectEntity> fetchAllSubjects = subjectService.fetchAllSubjects();
		return fetchAllSubjects;
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to delete Subject", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@DeleteMapping("/delete/{subjectId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteSubject(@PathVariable("subjectId") Integer subjectId) {
		subjectService.deleteSubjectById(subjectId);
		return "Subject "+subjectId+" Deleted";
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to update Subject", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PutMapping("/update/{subjectId}")
	@ResponseStatus(HttpStatus.CREATED)
	public SubjectEntity updateSubjectInfo(@PathVariable("subjectId") Integer subjectId, @RequestBody SubjectEntity subjectEntity) {
		return subjectService.updateSubjectInfo(subjectEntity, subjectId);
	}
	
	@Operation(description = "This end point used for Student", summary = "This end point is used to create multiple Subject", responses = {
			@ApiResponse(description = "Success", responseCode = "201"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@PostMapping("/multiple/subjects")
	@ResponseStatus(HttpStatus.CREATED)
	public List<SubjectEntity> createMultipleSubjects(@RequestBody List<SubjectEntity> subjectEntity){
		subjectService.createMultipleSubjects(subjectEntity);
		return subjectEntity;
	}
	
	@Operation(description = "This end point used for Subject", summary = "This end point is used to fetch subject by id", responses = {
			@ApiResponse(description = "Success", responseCode = "200"),
			@ApiResponse(description = "UnAuthorized / invalid token", responseCode = "403") })
	@GetMapping("/fetch/{subjectId}")
	@ResponseStatus(HttpStatus.OK)
	public SubjectEntity fetchSubjectById(@PathVariable("subjectId") Integer subjectId) {
		return subjectService.fetchSubjectById(subjectId);
	}

}
