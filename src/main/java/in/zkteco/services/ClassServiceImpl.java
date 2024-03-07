package in.zkteco.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.zkteco.entity.ClassEntity;
import in.zkteco.repositories.ClassRepository;
import in.zkteco.util.InvalidDataException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClassServiceImpl implements ClassService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	private final ClassRepository classRepository;

	@Override
	public ClassEntity createClass(ClassEntity classEntity) {
		Optional<ClassEntity> findById = classRepository.findById(classEntity.getClassId());
		if(findById.isEmpty()) {
			return classRepository.save(classEntity);
		}else {
			logger.error("This id"+classEntity.getClassId()+" is already exists");
			throw new InvalidDataException("This id "+classEntity.getClassId()+" is already exists");
		}
	}

	@Override
	public List<ClassEntity> fetchAllClasses() {
		List<ClassEntity> allClasses = classRepository.findAll();
		if (allClasses.isEmpty()) {
			logger.error("Class data is not found");
			throw new EntityNotFoundException("Class data is not found");
		} else {
			logger.info("Fetch all records");
			return allClasses;
		}
	}

	@Override
	public String deleteClassById(Integer classId) {

		classRepository.findById(classId).orElseThrow(() -> {
			logger.error("Id is "+classId+" not found");
			throw new EntityNotFoundException("Id is "+classId+" not found");
		});
		classRepository.deleteById(classId);
		return "Deleted";
	}

	@Override
	public ClassEntity updateClassInfo(ClassEntity classEntity, Integer classId) {
		classRepository.findById(classId).orElseThrow(() -> {
			logger.error("Id is "+classId+" not found");
			throw new InvalidDataException("Id is"+classId+" not found");
		});
		return classRepository.save(classEntity);

	}

	@Override
	public List<ClassEntity> createMultipleClasses(List<ClassEntity> classEntities) {
		
		try {
			List<ClassEntity> savedClasses = classRepository.saveAll(classEntities);
			logger.info("Multiple classes are created successfully");
			return savedClasses;
		} catch (InvalidDataException ex) {
			logger.error("Failed to create the class", ex.getMessage());
			throw new InvalidDataException("Failed to create the class"+ex.getMessage());
		}
	}

	@Override
	public ClassEntity fetchClassById(Integer classId) {
		return classRepository.findById(classId)
				.orElseThrow(()->{
					logger.error("Class_Id "+classId+" is not found");
					return new EntityNotFoundException("Class_Id "+classId+" is not found");
				});
	}

}
