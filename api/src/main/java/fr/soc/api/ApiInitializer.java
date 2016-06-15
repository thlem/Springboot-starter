package fr.soc.api;

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

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <h1>The Application's run initialization</h1>
 * 
 * <p>
 * This manages some of actions that should be done on Application startup.
 * </p>
 * 
 * @author thomas.lemercier.pro@gmail.com
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "fr" })
@EnableJpaRepositories("fr")
public class ApiInitializer extends SpringBootServletInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApiInitializer.class);

	/**
	 * The entry point of the app.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		SpringApplication.run(ApiInitializer.class, args);

		LOGGER.info("The application has been run");

	}

	/**
	 * Configuration of Swagger mainly centers around the Docket bean.
	 *
	 * @return A builder which is intended to be the primary interface into the
	 *         swagger-springmvc framework
	 */
	@Bean
	public Docket swaggerPlugin() {

		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().pathMapping("/");

	}

	/**
	 * Configuration and startup of the DataSource.
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
	 * Configuration of JPA and Hibernate Properties.
	 * 
	 * @param dataSource
	 * @return
	 */
	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan(new String[] { "fr" });

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
