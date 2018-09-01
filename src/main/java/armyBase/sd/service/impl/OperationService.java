package armyBase.sd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.OperationDAO;
import armyBase.sd.dto.OperationDTO;

import armyBase.sd.model.Operation;
import armyBase.sd.model.Soldier;
import armyBase.sd.service.IOperationService;

@Component
public class OperationService implements IOperationService{

	private final OperationDAO operationDAO;

    @Autowired
    public OperationService(OperationDAO operationDAO) {
        this.operationDAO = operationDAO;
    }

	@Override
	public Operation create(OperationDTO op) {
		Operation operation = new Operation();
		operation.setCommander(op.getCommander());
		operation.setDescription(op.getDescription());
		operation.setEndDate(op.getEndDate());
		operation.setOperationSoldiers(op.getOperationSoldiers());
		operation.setStartDate(op.getStartDate());
		operation.setStatus(op.getStatus());
		return operationDAO.save(operation);
	}

	@Override
	public List<Operation> getAll() {
		return operationDAO.findAll();
	}

	@Override
	public Operation getById(Long id) {
		return operationDAO.findByIdOperation(id);
	}

	@Override
	public Operation update(OperationDTO op) {
		Operation operation = getById(op.getIdOperation());
		if(op.getCommander()!=null)
			operation.setCommander(op.getCommander());
		if(op.getDescription()!=null)
			operation.setDescription(op.getDescription());
		if(op.getEndDate()!=null)
			operation.setEndDate(op.getEndDate());
		if(op.getOperationSoldiers()!=null)
			operation.setOperationSoldiers(op.getOperationSoldiers());
		if(op.getStartDate()!=null)
			operation.setStartDate(op.getStartDate());
		if(op.getStatus()!=null)
			operation.setStatus(op.getStatus());
		return operationDAO.save(operation);
	}

	@Override
	public void deleteById(Long id) {
		operationDAO.deleteById(id);
		
	}

	@Override
	public List<Operation> getBySoldierInCharge(Long soldierId) {
		Soldier commander = new Soldier();
		commander.setIdSoldier(soldierId);
		return operationDAO.findByCommander(commander);
	}
    
	

}