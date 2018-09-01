package armyBase.sd.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.RankDAO;
import armyBase.sd.model.Rank;
import armyBase.sd.service.IRankService;

@Component
public class RankService implements IRankService {
	private final RankDAO rankDAO;

    @Autowired
    public RankService(RankDAO rankDAO) {
        this.rankDAO = rankDAO;
    }

	@Override
	public List<Rank> getAll() {
		return rankDAO.findAll();
	}

	@Override
	public Rank getById(Long id) {
		return rankDAO.findByIdRank(id);
	}
}
