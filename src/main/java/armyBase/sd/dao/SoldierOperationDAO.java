package armyBase.sd.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.SoldierOperation;


@Transactional
@Repository

public interface SoldierOperationDAO extends JpaRepository<SoldierOperation,Long> {
	SoldierOperation findByIdSoldierOperation(Long id);
	List<SoldierOperation> findAllBySoldierToOperation(Soldier soldier);
	List<SoldierOperation> findAllByOperationToSoldier(Operation op);
}
