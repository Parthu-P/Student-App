package in.zkteco.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import in.zkteco.entity.SubjectEntity;

public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

}
