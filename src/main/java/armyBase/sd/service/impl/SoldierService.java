package armyBase.sd.service.impl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.SoldierDAO;
import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.exceptions.InvalidDataException;
import armyBase.sd.model.Rank;
import armyBase.sd.model.Soldier;
import armyBase.sd.service.ISoldierService;



@Component
public class SoldierService implements ISoldierService {


    private final SoldierDAO soldierDAO;

    @Autowired
    public SoldierService(SoldierDAO soldierDAO) {
        this.soldierDAO = soldierDAO;
    }

	public Soldier create(SoldierDTO soldier) throws InvalidDataException {
		if(soldier.getTagNumber().length() != 7)
			throw new InvalidDataException("The tag number field should have 7 digits");
		try{
			Integer.parseInt(soldier.getTagNumber());
		}catch(NumberFormatException e){
			throw new InvalidDataException("The tag number should contain digits only");		
		}
			
		Soldier s = new Soldier();
		s.setAlias(soldier.getAlias());
		s.setTagNumber(soldier.getTagNumber());
		s.setBase(soldier.getBase());
		s.setDob(soldier.getDob());
		s.setFullName(soldier.getFullName());
		s.setOperationsInCharge(soldier.getOperationsInCharge());
		s.setRank(soldier.getRank());
		s.setTrainingsInCharge(soldier.getTrainingsInCharge());
		s.setSoldierOperations(soldier.getSoldierOperations());
		s.setUser(soldier.getUser());
		
		return soldierDAO.save(s);
	}

	public List<Soldier> getAll() {
		return soldierDAO.findAll();
	}

	public List<Soldier> getByRank(Rank rank) {
		
		List<Soldier> all = getAll();
		List<Soldier> result = all.stream().filter(s -> s.getRank().getRankName().equals(rank.getRankName())).collect(Collectors.toList());
		return result;
	}

	public Soldier getById(Long id) {
		return soldierDAO.findByIdSoldier(id);
	}

	public Soldier update(SoldierDTO soldier) {
		System.out.println(soldier.getBase()); 
		Soldier s = getById(soldier.getIdSoldier());
		if(soldier.getAlias()!=null)
			s.setAlias(soldier.getAlias());
		if(soldier.getUser()!=null)
			s.setUser(soldier.getUser());
		if(soldier.getBase()!=null)
			s.setBase(soldier.getBase());
		if(soldier.getTagNumber()!=null)
			s.setTagNumber(soldier.getTagNumber());
		if(soldier.getDob()!=null)
			s.setDob(soldier.getDob());
		if(soldier.getFullName()!=null)
			s.setFullName(soldier.getFullName());
	    if(soldier.getOperationsInCharge()!=null)		
			s.setOperationsInCharge(soldier.getOperationsInCharge());
	    if(soldier.getRank()!=null)
	    	s.setRank(soldier.getRank());
	    if(soldier.getTrainingsInCharge()!=null)
	    	s.setTrainingsInCharge(soldier.getTrainingsInCharge());
	    if(soldier.getSoldierOperations()!=null)
	    	s.setSoldierOperations(soldier.getSoldierOperations());
		return soldierDAO.save(s);
	}

	public void deleteById(Long id) {

		soldierDAO.deleteByIdSoldier(id);
	}

	
	public Soldier getByIdUser(Long id) {
		return soldierDAO.findByUserIdUser(id);
	}

	@Override
	public Soldier getByTagNumber(String tag) {
		return soldierDAO.findByTagNumber(tag);
	}

	

}
