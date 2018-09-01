package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import armyBase.sd.dto.TrainingDTO;
import armyBase.sd.model.Training;



@Service
public interface ITrainingService{
	
	public Training create(TrainingDTO training);
	public List<Training> getAll();
	public Training getById(Long id);
	public Training update(TrainingDTO training);
    public void deleteById(Long id);
    public List<Training> getBySoldierInCharge(Long soldierId);

    
}
