package fr.soc.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
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
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
		TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
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
	 * Test for the REST resource that give all role
	 * 
	 * @throws Exception
	 */
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRolesTest() throws Exception {

		List<Role> roleList = roleService.getRoles();

		ObjectMapper mapper = new ObjectMapper();

		String roleListJson = mapper.writeValueAsString(roleList);

		mockMvc.perform(get("/api/roles")).andExpect(status().isOk()).andExpect(content().json(roleListJson));
	}

	/**
	 * Test for the REST resource that give one role
	 * 
	 * @throws Exception
	 */
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRoleTest() throws Exception {

		Role role = roleService.getRoleById(1l);

		ObjectMapper mapper = new ObjectMapper();

		String roleJson = mapper.writeValueAsString(role);

		mockMvc.perform(get("/api/roles/1")).andExpect(status().isOk()).andExpect(content().json(roleJson));
	}

	/**
	 * Test for the REST resource that create one role
	 * 
	 * @throws Exception
	 */
	@Test
	public void createRoleTest() throws Exception {

		Role role = new Role();
		role.setRoleLabel("LABEL");
		role.setRoleLevel(1);

		ObjectMapper mapper = new ObjectMapper();

		String roleJson = mapper.writeValueAsString(role);

		mockMvc.perform(post("/api/roles").contentType(MediaType.APPLICATION_JSON).content(roleJson))
				.andExpect(status().isOk());

		Role roleAfterCreation = roleService.getRoleByLabel("LABEL");

		String roleAfterCreationJson = mapper.writeValueAsString(roleAfterCreation);

		mockMvc.perform(get("/api/roles/" + roleAfterCreation.getRoleId())).andExpect(status().isOk())
				.andExpect(content().json(roleAfterCreationJson));
	}

	/**
	 * Test for the REST resource that update one role
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateRoleTest() throws Exception {

		Role role = roleService.getRoleById(1l);
		role.setRoleLabel("LABEL-UPDATE");

		ObjectMapper mapper = new ObjectMapper();

		String roleJson = mapper.writeValueAsString(role);

		mockMvc.perform(put("/api/roles/1").contentType(MediaType.APPLICATION_JSON).content(roleJson))
				.andExpect(status().isOk());

		Role roleAfterCreation = roleService.getRoleByLabel("LABEL-UPDATE");

		String roleAfterCreationJson = mapper.writeValueAsString(roleAfterCreation);

		mockMvc.perform(get("/api/roles/" + roleAfterCreation.getRoleId())).andExpect(status().isOk())
				.andExpect(content().json(roleAfterCreationJson));
	}

	/**
	 * Test for the REST resource that delete all role
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteRoleTest() throws Exception {

		Role role = roleService.getRoleById(1l);

		ObjectMapper mapper = new ObjectMapper();

		String roleJson = mapper.writeValueAsString(role);

		mockMvc.perform(delete("/api/roles/1").contentType(MediaType.APPLICATION_JSON).content(roleJson))
				.andExpect(status().isOk());

		mockMvc.perform(get("/api/roles/" + role.getRoleId())).andExpect(status().isNotFound());
	}
}
