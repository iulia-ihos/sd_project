package armyBase.sd.service;

import java.util.List;

import org.springframework.stereotype.Service;


import armyBase.sd.model.Rank;

@Service
public interface IRankService{

	public List<Rank> getAll();
	public Rank getById(Long id);
 
}
