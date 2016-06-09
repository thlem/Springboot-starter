package fr.soc.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import fr.soc.api.ApiInitializer;
import fr.soc.business.services.RoleService;
import fr.soc.data.model.Role;


/**
 * Test for the controller RoleController
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiInitializer.class)
@WebAppConfiguration
public class RoleControllerTest {
	
	@Autowired
	private RoleService roleService;
	
	// Main entry point for server-side Spring MVC test support
	private MockMvc mockMvc;
	
	// Interface to provide configuration for a web application
	@Autowired
    private WebApplicationContext webApplicationContext;
	
	@Before
    public void mvc() {
		/**
		 * Build a MockMvc using the given, fully initialized, i.e. refreshed, 
		 * WebApplicationContext. The DispatcherServlet will use the context to 
		 * discover Spring MVC infrastructure and application controllers in it.
		 * The context must have been configured with a ServletContext
		 */
		mockMvc = webAppContextSetup(webApplicationContext).build();
    }
	
	@After
    public void downUp() {
        mockMvc = null;
    }
	
	/**
	 * Test for the REST resource that give all user
	 * 
	 * @throws Exception
	 */
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
    public void getRolesTest() throws Exception {
		
		List<Role> roleList = roleService.getRoles();
		
		ObjectMapper mapper = new ObjectMapper();
		
		String roleListJson = mapper.writeValueAsString(roleList);
		
        mockMvc.perform(get("/api/roles"))
        .andExpect(status().isOk())
        .andExpect(content().json(roleListJson));
    }
}
