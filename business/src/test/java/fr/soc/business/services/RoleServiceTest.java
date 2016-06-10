package fr.soc.business.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;

import fr.soc.business.BusinessInitializerForTest;
import fr.soc.data.model.Role;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BusinessInitializerForTest.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
	TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class })
public class RoleServiceTest {

	
	@Autowired
	private RoleService roleService;
	
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRolesTest() {
		
		List<Role> roleList = roleService.getRoles();
		
		assertNotNull(roleList);
		assertFalse(roleList.isEmpty());
		assertTrue(roleList.size() == 2);
		
	}
	
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRoleByIdTest() {
		
		Role role = roleService.getRoleById(1L);
		
		assertNotNull(role);
		assertTrue(1L == role.getRoleId());
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRoleByIdTestFail() {
		
		roleService.getRoleById(8L);
		
	}
	
	@Test
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void getRoleByLabelTest() {
		
		Role role = roleService.getRoleByLabel("ADMIN");
		
		assertNotNull(role);
		assertTrue(1L == role.getRoleId());
		
	}
	
	@Test
	public void createRoleTest() {
		
		Role role = new Role();
		role.setRoleLabel("LABEL");
		role.setRoleLevel(1);
		
		Role createdRole = roleService.createRole(role);
		
		assertNotNull(createdRole);
		assertTrue(1 == createdRole.getRoleLevel());
		assertEquals("LABEL", createdRole.getRoleLabel());
		
	}
	
	@Test
	public void createRolesTest() {
		
		
		List<Role> roleList = new ArrayList<>();
		Role role1 = new Role();
		role1.setRoleLabel("LABEL1");
		role1.setRoleLevel(1);
		Role role2 = new Role();
		role2.setRoleLabel("LABEL2");
		role2.setRoleLevel(2);
		roleList.add(role1);
		roleList.add(role2);
		
		List<Role> createdRoles = roleService.createRoles(roleList);
		
		assertNotNull(createdRoles);
		assertFalse(createdRoles.isEmpty());
		assertTrue(createdRoles.size() == 2);
		
	}
	
	@Test(expected = EntityNotFoundException.class)
	@DatabaseSetup("/db-unit-data/role/role-valid.xml")
	public void deleteRoleTest() {
		
		Role role = roleService.getRoleById(1L);
		roleService.deleteRole(role);
		
		roleService.getRoleById(1L);
		
	}
	
}
