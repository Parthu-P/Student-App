package in.zkteco.services;

import java.util.List;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.zkteco.entity.StudentEntity;
import in.zkteco.repositories.StudentRepository;
import in.zkteco.util.InvalidDataException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	private final StudentRepository studentRepository;

	@Override
	public StudentEntity createStudent(StudentEntity studentEntity) {
		Optional<StudentEntity> findById = studentRepository.findById(studentEntity.getStudentId());
		if(findById.isEmpty()) {
			return studentRepository.save(studentEntity);
		}else {
			logger.error("This id"+studentEntity.getStudentId()+" is already exists");
			throw new InvalidDataException("This id "+studentEntity.getStudentId()+" is already exists");
		}
	}

	@Override
	public List<StudentEntity> fetchAllStudents() {
		List<StudentEntity> allStudents = studentRepository.findAll();
		if (allStudents.isEmpty()) {
			logger.error("Student data is not found");
			throw new EntityNotFoundException("Student data is not found");
		} else {
			logger.info("Fetch all records");
			return allStudents;
		}
	}

	@Override
	public String deleteStudentById(Integer studentId) {

		studentRepository.findById(studentId).orElseThrow(() -> {
			logger.error("Id is "+studentId+" not found");
			throw new EntityNotFoundException("Id is "+studentId+" not found");
		});
		studentRepository.deleteById(studentId);
		return "Deleted";
	}

	@Override
	public StudentEntity updateStudentInfo(StudentEntity studentEntity, Integer studentId) {

		studentRepository.findById(studentId).orElseThrow(() -> {
			logger.error("Id is "+studentId+" not found");
			throw new InvalidDataException("Id is "+studentId+" not found");
		});
		return studentRepository.save(studentEntity);

	}

	@Override
	public StudentEntity getStudentById(Integer studentId) {
		return studentRepository.findById(studentId)
		.orElseThrow(()->{
			logger.error("Student_Id "+studentId+" is not found");
			return new EntityNotFoundException("Student_Id "+studentId+" is not found");
		});
		 
		}

	@Override
	public List<StudentEntity> createMultipleStudents(List<StudentEntity> studentEntity) {

		try {
			studentRepository.saveAll(studentEntity);
			logger.info(" Students are created");
			return studentEntity;
		} catch (InvalidDataException ex) {
			logger.error("Failed to create the Students", ex.getMessage());
			throw new InvalidDataException("Failed to create the Students");
		}
	}

}
