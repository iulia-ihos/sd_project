package armyBase.sd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import armyBase.sd.model.Role;


@Transactional
@Repository

public interface RoleDAO extends JpaRepository<Role,Long> {
	Role findByIdRole(Long id);

}