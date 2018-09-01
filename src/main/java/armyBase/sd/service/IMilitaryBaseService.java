package armyBase.sd.service;

import java.util.List;


import org.springframework.stereotype.Service;

import armyBase.sd.dto.MilitaryBaseDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.MilitaryBase;



@Service
public interface IMilitaryBaseService{
	
	public MilitaryBase create(MilitaryBaseDTO base) throws InvalidDataException;
	public List<MilitaryBase> getAll();
	public MilitaryBase getById(Long id);
	public MilitaryBase update(MilitaryBaseDTO base);
    public void deleteById(Long id);

}
