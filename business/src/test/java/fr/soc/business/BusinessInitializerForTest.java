package fr.soc.business;


import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;


/**
 * <h1>The Application run initialization</h1>
 * 
 * <p>
 * This manages the run actions of the springboot Application.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = { "fr" })
@EnableJpaRepositories("fr")
public class BusinessInitializerForTest extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(BusinessInitializerForTest.class);
	
	public static void main(String[] args) {

		SpringApplication.run(BusinessInitializerForTest.class, args);
		
		LOGGER.info("The application has been run");

	}

	/**
	 * Configuration and start of the DataSource
	 * 
	 * @return The started DataSource
	 */
	@Bean
    public DataSource dataSource() {
		DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.url("jdbc:hsqldb:mem:test-local");
        dataSourceBuilder.username("sa");
        dataSourceBuilder.password("");
        dataSourceBuilder.driverClassName("org.hsqldb.jdbcDriver");
        return dataSourceBuilder.build();
    }

	/**
	 * configure JPA with Hibernate in Spring
	 * @param dataSource
	 * @return
	 */
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
    	LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan(new String[]{"fr"});

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.HSQLDialect");
        properties.setProperty("hibernate.hbm2ddl.auto", "create-drop");
        properties.setProperty("spring.jpa.properties.hibernate.format_sql", "true");
        em.setJpaProperties(properties);

        return em;
    }

}
