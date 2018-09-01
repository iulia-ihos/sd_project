package armyBase.sd.service;

import java.util.List;


import org.springframework.stereotype.Service;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Soldier;
import armyBase.sd.model.Training;



@Service
public interface IMilitaryBaseService{
	
	public MilitaryBase create(MilitaryBaseDTO base) throws InvalidDataException;
	public List<MilitaryBase> getAll();
	public MilitaryBase getById(Long id);
	public MilitaryBase update(MilitaryBaseDTO base);
    public void deleteById(Long id);
    public List<Soldier> getSoldiers(Long id);
    public List<Training> getTrainings(Long id);

}
