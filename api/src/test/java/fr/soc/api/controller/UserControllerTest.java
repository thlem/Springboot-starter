package fr.soc.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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

import fr.soc.api.ApiInitializer;


/**
 * Test for the controller UserController
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApiInitializer.class)
@WebAppConfiguration
public class UserControllerTest {
	
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
    public void getUsersTest() throws Exception {
        mockMvc.perform(get("/api/users")).andExpect(status().isOk());
    }
}
