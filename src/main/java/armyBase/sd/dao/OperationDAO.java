package armyBase.sd.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;

@Transactional
@Repository
public interface OperationDAO extends JpaRepository<Operation,Long> {
	Operation findByIdOperation(Long id);
	List<Operation> findByCommander(Soldier commander);
}
