package armyBase.sd;

import java.util.Date;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import armyBase.sd.controller.rest.SoldierController;
import armyBase.sd.dto.SoldierDTO;
import armyBase.sd.dto.UserDTO;
import armyBase.sd.model.MilitaryBase;
import armyBase.sd.model.Rank;
import armyBase.sd.model.Role;
import armyBase.sd.model.UserDetailed;
import armyBase.sd.request.Encryption;
import armyBase.sd.service.impl.SoldierService;
import armyBase.sd.service.impl.UserService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
/**
 * Unit test for simple App.
 */


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
		DbUnitTestExecutionListener.class })
public class AppTest {

	@Autowired
    WebApplicationContext context;
	
    @Autowired
    private MockMvc mockMvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
    @DatabaseSetup("empty.xml")
    public void shouldHaveResponseNotFound() throws Exception {
    	mockMvc.perform(get("http://localhost:8081/rest/soldier/getById?id=100")
                .accept(MediaType.APPLICATION_JSON))
    			.andDo(print())
                .andExpect(content().string(containsString("")));
                ;
    }

    
 
	@Test
	public void testAddSoldier() throws Exception {
	    ObjectMapper mapper = new ObjectMapper();
	   // mapper.setSerializationInclusion(Include.NON_NULL);

		SoldierDTO soldier = new SoldierDTO();
		soldier.setAlias("alias");
		MilitaryBase base = new MilitaryBase();
		base.setIdMilitaryBase(1L);
		soldier.setBase(base);
		soldier.setDob(new Date());
		soldier.setFullName("name");
		Rank rank = new Rank();
		rank.setIdRank(1L);
		soldier.setRank(rank);

		UserDTO user = new UserDTO();
		user.setEmail("emailAddress");
		user.setPass("pass");
		Role role = new Role();
		role.setIdRole(1L);
		
		user.setRol(role);
		user.setSoldier(null);
		
		
		UserDetailed us = new UserDetailed();
		us.setIdUser(1L);
		soldier.setUser(us);
		
		
		mockMvc.perform(put("http://localhost:8080/soldier/add")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
		        .content(mapper.writeValueAsString(soldier)))
				.andDo(print()).andExpect(status().isOk()).
				andExpect(jsonPath("$.fullName",is(soldier.getFullName())));
		
		mockMvc.perform(put("http://localhost:8080/user/register")
				.contentType(MediaType.APPLICATION_JSON)
				.characterEncoding("UTF-8")
		        .content(mapper.writeValueAsString(user)))
				.andExpect(status().isOk())
				.andDo(print()).andExpect(jsonPath("$.email",is(user.getEmail())));
		


	}
}


