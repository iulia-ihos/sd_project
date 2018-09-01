package armyBase.sd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.TrainingDAO;
import armyBase.sd.dto.TrainingDTO;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;
import armyBase.sd.service.ITrainingService;



@Component
public class TrainingService implements ITrainingService{

	private final TrainingDAO trainingDAO;

    @Autowired
    public TrainingService(TrainingDAO trainingDAO) {
        this.trainingDAO = trainingDAO;
    }

	@Override
	public Training create(TrainingDTO training) {
		Training t = new Training();
		t.setDescription(training.getDescription());
		t.setInstructor(training.getInstructor());
		t.setStartTime(training.getStartTime());
		t.setEndTime(training.getEndTime());
		t.setTrainingBase(training.getTrainingBase());
		return trainingDAO.save(t);
	}

	@Override
	public List<Training> getAll() {
		return trainingDAO.findAll();
	}

	@Override
	public Training getById(Long id) {
		return trainingDAO.findByIdTraining(id);
	}

	@Override
	public Training update(TrainingDTO training) {
		Training t = getById(training.getIdTraining());
		if(training.getDescription()!=null)
			t.setDescription(training.getDescription());
		if(training.getInstructor()!=null)
			t.setInstructor(training.getInstructor());
		if(training.getStartTime()!=null)
			t.setStartTime(training.getStartTime());
		if(training.getEndTime()!=null)
			t.setEndTime(training.getEndTime());
		if(training.getTrainingBase()!=null)
			t.setTrainingBase(training.getTrainingBase());
		return trainingDAO.save(t);
	}

	@Override
	public void deleteById(Long id) {
		trainingDAO.deleteById(id);
		
	}

	@Override
	public List<Training> getBySoldierInCharge(Long soldierId) {
		Soldier instructor = new Soldier();
		instructor.setIdSoldier(soldierId);
		return trainingDAO.findAllByInstructor(instructor);
	}

}