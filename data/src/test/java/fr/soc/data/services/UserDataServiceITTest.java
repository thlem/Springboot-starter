package fr.soc.data.services;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import fr.soc.data.SpringInitForTest;
import fr.soc.data.model.User;
import fr.soc.data.repository.UserRepository;

/**
 * Test Class for the UserDataService interface
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = SpringInitForTest.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class,
    DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class,
    DbUnitTestExecutionListener.class})
@Transactional
public class UserDataServiceITTest {

	@Autowired
	private UserRepository userDataService;
	
	/**
	 * Test for the method findAll. Load in DB the data configured in the xml file before the test start
	 */
	@Test
	@DatabaseSetup(value = "/db-data-for-tests/user/2-full-user.xml")
	public void findAllTest() {
		
		// Retrieve all user
		// Declare final due to the stream below
		final Iterable<User> userIterable = userDataService.findAll();
		
		List<User> users = new ArrayList<>();
		
		// For each iterable User object, add it to a List
		userIterable.forEach(user -> {
			if(null != user){
				users.add(user);
			}
		});
		
		// Test the List to be sure that 2 User were inserted and retrieve from the DB
		assertEquals(2, users.size());
		
	}
	
}
