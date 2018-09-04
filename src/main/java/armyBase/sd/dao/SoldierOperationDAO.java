package armyBase.sd.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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
	
	@Modifying
	@Query("DELETE FROM SoldierOperation s WHERE s.operationToSoldier.idOperation = ?1 "
			+ "AND s.soldierToOperation.idSoldier = ?2")
	void delete(Long idOperation, Long idSoldier);
}
