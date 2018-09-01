package armyBase.sd.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;


@Repository
@Transactional

public interface TrainingDAO extends JpaRepository<Training,Long>{
	Training findByIdTraining(Long id);
	List<Training> findAllByInstructor(Soldier instructor);
}
