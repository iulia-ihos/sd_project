package armyBase.sd.service;




import org.springframework.stereotype.Service;


import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.UserDetailed;



@Service
public interface IUserService {
	public UserDetailed create(UserDTO user);
	public UserDetailed getByEmail(String email);
	public UserDetailed getById(Long id);
    public void deleteByEmail(String email);
    UserDetailed changePassword(String email,String password);
	public boolean login(String email, String password);
	public UserDetailed updateRole(UserDTO user);
    
}
