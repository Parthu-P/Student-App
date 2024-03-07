package in.zkteco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.zkteco.entity.ClassEntity;

public interface ClassRepository extends JpaRepository<ClassEntity, Integer> {
	
}
