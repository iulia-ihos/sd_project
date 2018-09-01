package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import armyBase.sd.dto.OperationDTO;
import armyBase.sd.model.Operation;



@Service
public interface IOperationService{
	
	public Operation create(OperationDTO op);
	public List<Operation> getAll();
	public Operation getById(Long id);
	public Operation update(OperationDTO op);
    public void deleteById(Long id);
    public List<Operation> getBySoldierInCharge(Long soldierId);

    
}
