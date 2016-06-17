package fr.soc.business.services;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;

import fr.soc.business.BusinessInitializerForTest;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BusinessInitializerForTest.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
@Ignore
public class XxServiceTest {

	
//	@Test(expected = EntityNotFoundException.class)
//	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
//	public void deleteRoleTest() {
//		
//		Role role = roleService.getRoleById(1L);
//		roleService.deleteRole(role);
//		
//		roleService.getRoleById(1L);
//		
//	}
	
}
