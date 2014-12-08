package uk.co.myfootballclub.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Persistence Configuration for JPA
 *
 * @author Benjamin O'Flaherty
 * @date Created on: 08/12/2014
 * @project MyFootballClub
 */
@Configuration
@ComponentScan({"uk.co.myfootballclub.persistence.*"})
@EnableJpaRepositories(basePackages = "uk.co.myfootballclub.persistence.dao")
@EnableTransactionManagement
@PropertySource(value ="/resources/database.properties")
public class PersistenceConfig {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {

        // HikariCP is a fast, simple and reliable 'zero-overhead' JDBC connection pool.  http://brettwooldridge.github.io/HikariCP/

        HikariConfig dS = new HikariConfig();
        dS.setMaximumPoolSize(100);  // Controls the maximum size that the pool is allowed to reach (idel and in-user connections)
        /*
            Name of the DataSource class provided by the JDBC driver. Specific JDBC driver names can be found wtihin the
            HikariCP documentation https://github.com/brettwooldridge/HikariCP#popular-datasource-class-names.
         */
        dS.setDriverClassName(env.getProperty("dataSource.driverClassName"));
        dS.setJdbcUrl(env.getProperty("dataSource.databaseUrl")); // Database url
        dS.setUsername(env.getProperty("dataSource.username"));  // Database username
        dS.setPassword(env.getProperty("dataSource.password"));    // Database password

        return new HikariDataSource(dS);
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {

        final LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();

        emf.setDataSource(dataSource);
        emf.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        emf.setPackagesToScan(new String[] {"uk.co.myfootballclub.persistence"});

        Properties props = new Properties();
        props.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        props.put("hibernate.format_sql", env.getProperty("hibernate.formatSql"));
        props.put("hibernate.hbm2ddl.auto", "update");
        props.put("hibernate.ejb.naming_strategy", env.getProperty("hibernate.namingStrategy"));
        props.put("hibernate.show_sql", env.getProperty("hibernate.showSql")); // Shows Hibernate SQL queries for development mode

        emf.setJpaProperties(props);

        return emf;
    }

    @Bean
    public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;

    }

}
