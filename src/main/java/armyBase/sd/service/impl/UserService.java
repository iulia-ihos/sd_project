package armyBase.sd.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import armyBase.sd.dao.UserDAO;
import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.UserDetailed;
import armyBase.sd.request.Encryption;
import armyBase.sd.service.IUserService;



@Component
public class UserService implements IUserService {

	 private final UserDAO userDAO;

	    @Autowired
	    public UserService(UserDAO userDAO) {
	        this.userDAO = userDAO;
	    }
	    
	public UserDetailed create(UserDTO user) {
		UserDetailed usr = new UserDetailed();
		usr.setEmail(user.getEmail());
		usr.setPass(Encryption.encryptPassword(user.getPass()));
		usr.setRol(user.getRol());
		return userDAO.save(usr);
	}

	public UserDetailed getByEmail(String email) {
		return userDAO.findByEmail(email);
	}

	public void deleteByEmail(String email) {
		UserDetailed user = getByEmail(email);
		userDAO.deleteById(user.getIdUser());
	}

	public UserDetailed changePassword(String email, String password) {
		UserDetailed user = getByEmail(email);
		user.setPass(Encryption.encryptPassword(password));
		userDAO.save(user);
		return user;
	}

	public boolean login(String email, String password) {
		UserDetailed user = getByEmail(email);
		if (user.getPass().equals(Encryption.encryptPassword(password)))
			return true;
		return false;
	}

	@Override
	public UserDetailed getById(Long id) {
		return userDAO.findByIdUser(id);
	}

	@Override
	public UserDetailed updateRole(UserDTO user) {
		UserDetailed u = getById(user.getIdUser());
		if(user.getRol()!=null)
			u.setRol(user.getRol());
		userDAO.save(u);
		return u;
	}

}
