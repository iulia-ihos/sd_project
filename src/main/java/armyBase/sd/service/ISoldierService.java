package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;

import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.Rank;
import armyBase.sd.model.Soldier;

@Service
public interface ISoldierService {
	
	public Soldier create(SoldierDTO soldier) throws InvalidDataException;
	public List<Soldier> getAll();
	public List<Soldier> getByRank(Rank rank);
	public Soldier getById(Long Id);
	public Soldier update(SoldierDTO soldier);
    public void deleteById(Long id);
    public Soldier getByIdUser(Long id);
    public Soldier getByTagNumber(String tag);

    
}