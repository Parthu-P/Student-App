package in.zkteco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.zkteco.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {

	public StudentRepository findByEmail(String email);
}
