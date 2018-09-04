package armyBase.sd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.SoldierOperationDAO;
import armyBase.sd.dto.SoldierOperationDTO;
import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.SoldierOperation;
import armyBase.sd.service.ISoldierOperationService;





@Component
public class SoldierOperationService implements ISoldierOperationService{

	private final SoldierOperationDAO soldierOpDAO;

    @Autowired
    public SoldierOperationService(SoldierOperationDAO soldierOPDAO) {
        this.soldierOpDAO = soldierOPDAO;
    }

	@Override
	public SoldierOperation create(SoldierOperationDTO soldierOp) {
		SoldierOperation so = new SoldierOperation();
		so.setOperationToSoldier(soldierOp.getOperationToSoldier());
		so.setSoldierToOperation(soldierOp.getSoldierToOperation());
		return soldierOpDAO.save(so);
	}

	@Override
	public List<SoldierOperation> getAll() {
		return soldierOpDAO.findAll();
	}

	@Override
	public SoldierOperation getById(Long id) {
		return soldierOpDAO.findByIdSoldierOperation(id);
	}

	@Override
	public SoldierOperation update(SoldierOperationDTO soldierOp) {
		SoldierOperation so = getById(soldierOp.getIdSoldierOperation());
		so.setOperationToSoldier(soldierOp.getOperationToSoldier());
		so.setSoldierToOperation(soldierOp.getSoldierToOperation());
		return soldierOpDAO.save(so);
	}

	@Override
	public void deleteById(Long id) {
		soldierOpDAO.deleteById(id);
		
	}

	@Override
	public List<Soldier> getAllSoldiersByOperation(Long  opId) {
		List<Soldier> soldiers = new ArrayList<>();
		Operation operation = new Operation();
		operation.setIdOperation(opId);
		List<SoldierOperation> soldOps = soldierOpDAO.findAllByOperationToSoldier(operation);
		soldOps.stream().forEach(x -> soldiers.add(x.getSoldierToOperation()));
		return soldiers;
	}

	@Override
	public List<Operation> getAllOperationsBySoldier(Long soldierId) {
		List<Operation> ops = new ArrayList<>();
		Soldier soldier = new Soldier();
		soldier.setIdSoldier(soldierId);
		List<SoldierOperation> soldOps = soldierOpDAO.findAllBySoldierToOperation(soldier);
		soldOps.stream().forEach(x -> ops.add(x.getOperationToSoldier()));
		return ops;
	}

	@Override
	public void delete(SoldierOperationDTO so) {
//		SoldierOperation soldierOperation = new SoldierOperation();
//		soldierOperation.setSoldierToOperation(so.getSoldierToOperation());
//		soldierOperation.setOperationToSoldier(so.getOperationToSoldier());
		this.soldierOpDAO.delete(so.getOperationToSoldier().getIdOperation(),
				so.getSoldierToOperation().getIdSoldier());
	}

	
}