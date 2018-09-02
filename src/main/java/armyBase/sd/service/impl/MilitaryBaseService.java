package armyBase.sd.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.MilitaryBaseDAO;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;
import armyBase.sd.service.IMilitaryBaseService;

@Component
public class MilitaryBaseService implements IMilitaryBaseService{

	private final MilitaryBaseDAO militaryBaseDAO;

    @Autowired
    public MilitaryBaseService(MilitaryBaseDAO militaryBaseDAO) {
        this.militaryBaseDAO = militaryBaseDAO;
    }
    
	@Override
	public MilitaryBase create(MilitaryBaseDTO base) throws InvalidDataException {
		if(base.getDescription()!=null)
		if(base.getDescription().length()>255)
			throw new InvalidDataException("The description field should not be longer than 255 characters");
		if(base.getLatitude()!=null && ( base.getLatitude()>90 || base.getLatitude()<-90))
			throw new InvalidDataException("The latitude degrees should be in range 90 to -90");
		if(base.getLongitude()!=null && ( base.getLongitude()>180 || base.getLongitude()<-180))
			throw new InvalidDataException("The longitude degrees should be in range 180 to -180");
		MilitaryBase b = new MilitaryBase();
		b.setDescription(base.getDescription());
		b.setName(base.getName());
		b.setLatitude(base.getLatitude());
		b.setLongitude(base.getLongitude());
		b.setSoldiers(base.getSoldiers());
		b.setTraining(base.getTraining());
	
		return militaryBaseDAO.save(b);
	}

	@Override
	public List<MilitaryBase> getAll() {
		return militaryBaseDAO.findAll();
	}

	@Override
	public MilitaryBase getById(Long id) {
		return militaryBaseDAO.findByIdMilitaryBase(id);
	}

	@Override
	public MilitaryBase update(MilitaryBaseDTO base) {
		MilitaryBase b = getById(base.getIdMilitaryBase());
		
		if(base.getDescription()!=null)
			b.setDescription(base.getDescription());
		if(base.getName()!=null)
			b.setName(base.getName());
		if(base.getLatitude()!=null)
			b.setLatitude(base.getLatitude());
		if(base.getLongitude()!=null)
			b.setLongitude(base.getLongitude());
		if(base.getSoldiers()!=null)
			b.setSoldiers(base.getSoldiers());
		if(base.getTraining()!=null)
			b.setTraining(base.getTraining());
	
		return militaryBaseDAO.save(b);
	}

	@Override
	public void deleteById(Long id) {
		militaryBaseDAO.deleteById(id);
	}

	@Override
	public List<Soldier> getSoldiers(Long id) {
		MilitaryBase base = militaryBaseDAO.findByIdMilitaryBase(id);
		List<Soldier> soldiers = new ArrayList<>();
		soldiers.addAll(base.getSoldiers());
		return soldiers;
	}

	@Override
	public List<Training> getTrainings(Long id) {
		MilitaryBase base = militaryBaseDAO.findByIdMilitaryBase(id);
		List<Training> trainings = new ArrayList<>();
		trainings.addAll(base.getTraining());
		return trainings;
	}

}
