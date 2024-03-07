package in.zkteco.services;

import java.util.List;

import in.zkteco.entity.StudentEntity;

public interface StudentService {

	public StudentEntity createStudent(StudentEntity studentEntity);
	
	public List<StudentEntity> fetchAllStudents();
	
	public String deleteStudentById(Integer studentId);
	
	public StudentEntity updateStudentInfo(StudentEntity studentEntity, Integer studentId);
	
	public List<StudentEntity> createMultipleStudents(List<StudentEntity> studentEntity);
	
	public StudentEntity getStudentById(Integer studentId);
}
