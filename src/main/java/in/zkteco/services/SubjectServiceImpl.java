package in.zkteco.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import in.zkteco.entity.SubjectEntity;
import in.zkteco.repositories.SubjectRepository;
import in.zkteco.util.InvalidDataException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

	private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

	private final SubjectRepository subjectRepository;

	@Override
	public SubjectEntity createSubject(SubjectEntity subjectEntity) {
		Optional<SubjectEntity> findById = subjectRepository.findById(subjectEntity.getSubjectId());
		if (findById.isEmpty()) {
			return subjectRepository.save(subjectEntity);
		} else {
			logger.error("This id" + subjectEntity.getSubjectId() + " is already exists");
			throw new InvalidDataException("This id " + subjectEntity.getSubjectId() + " is already exists");
		}
	}

	@Override
	public List<SubjectEntity> fetchAllSubjects() {
		List<SubjectEntity> list = subjectRepository.findAll();
		if (list.isEmpty()) {
			logger.error("Subject Data is not found");
			throw new EntityNotFoundException("Subject Data is not found");
		} else {
			logger.info("Fetched all subjects");
			return list;
		}
	}

	@Override
	public String deleteSubjectById(Integer subjectId) {
		subjectRepository.findById(subjectId).orElseThrow(() -> {
			logger.error("Id is "+subjectId+" not found");
			throw new EntityNotFoundException("Id is "+subjectId+" not found");
		});
		subjectRepository.deleteById(subjectId);
		return "Deleted";
	}

	@Override
	public SubjectEntity updateSubjectInfo(SubjectEntity subjectEntity, Integer subjectId) {

		subjectRepository.findById(subjectId).orElseThrow(() -> {
			logger.error("Id is "+subjectId+" not found");
			throw new InvalidDataException("Id is "+subjectId+" not found");
		});
		return subjectRepository.save(subjectEntity);

	}

	@Override
	public List<SubjectEntity> createMultipleSubjects(List<SubjectEntity> subjectEntity) {
		try {
			logger.info("Multiple subjects are created");
			return subjectRepository.saveAll(subjectEntity);
		} catch (InvalidDataException ex) {
			logger.error("Failed to create the subject", ex.getMessage());
			throw new InvalidDataException("Failed to create the subject");
		}
	}

	@Override
	public SubjectEntity fetchSubjectById(Integer subjectId) {
		return subjectRepository.findById(subjectId).orElseThrow(() -> {
			logger.error("Subject_Id " + subjectId + " is not found");
			return new EntityNotFoundException("Subject_Id " + subjectId + " is not found");
		});
	}

}
