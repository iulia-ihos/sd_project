package armyBase.sd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.UserDetailed;



@Transactional
@Repository
public interface UserDAO extends JpaRepository<UserDetailed,Long> {
	UserDetailed findByEmail(String email);
	UserDetailed findByIdUser(Long id);

}
