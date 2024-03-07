package in.zkteco.services;

import java.util.List;

import in.zkteco.entity.ClassEntity;

public interface ClassService {

	public ClassEntity createClass(ClassEntity classEntity);

	public  List<ClassEntity> fetchAllClasses();

	public String deleteClassById(Integer studentId);

	public ClassEntity updateClassInfo(ClassEntity classEntity, Integer classId);
	
	public ClassEntity fetchClassById(Integer classId);
	
	public List<ClassEntity> createMultipleClasses(List<ClassEntity> classEntities);
}
