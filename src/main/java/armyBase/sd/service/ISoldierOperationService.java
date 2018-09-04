package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import armyBase.sd.dto.SoldierOperationDTO;
import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.SoldierOperation;


@Service
public interface ISoldierOperationService{
	
	public SoldierOperation create(SoldierOperationDTO soldierOp);
	public List<SoldierOperation> getAll();
	public SoldierOperation getById(Long id);
	public SoldierOperation update(SoldierOperationDTO soldierOp);
    public void deleteById(Long id);
    public List<Soldier> getAllSoldiersByOperation(Long opId);
    public List<Operation> getAllOperationsBySoldier(Long soldierId);  
	public void delete(SoldierOperationDTO soldierOperation);
}