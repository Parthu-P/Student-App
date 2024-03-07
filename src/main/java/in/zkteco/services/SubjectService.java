package in.zkteco.services;

import java.util.List;

import in.zkteco.entity.SubjectEntity;

public interface SubjectService {

	public SubjectEntity createSubject(SubjectEntity subjectEntity);

	public List<SubjectEntity> fetchAllSubjects();

	public String deleteSubjectById(Integer studentId);

	public List<SubjectEntity> createMultipleSubjects(List<SubjectEntity> subjectEntity);
	
	public SubjectEntity fetchSubjectById(Integer subjectId);
	
	public SubjectEntity updateSubjectInfo(SubjectEntity subjectEntity, Integer subjectId);
}
