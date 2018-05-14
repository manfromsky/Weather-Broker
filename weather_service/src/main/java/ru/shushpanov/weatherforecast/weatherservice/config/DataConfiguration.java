package ru.shushpanov.weatherforecast.weatherservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jndi.JndiTemplate;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;
import ru.shushpanov.weatherforecast.weatherservice.dao.WeatherDao;

import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Конфигурация для работы с базой даннных
 */
@PropertySource({"classpath:persistence-jndi.properties"})
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses = WeatherDao.class)
@ComponentScan(value = {"ru.shushpanov.weatherforecast.weatherservice"})
@Configuration
public class DataConfiguration {

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() throws NamingException {
        return (DataSource) new JndiTemplate().lookup(env.getProperty("jdbc.url"));
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() throws NamingException {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
        emf.setDataSource(dataSource());
        return emf;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new JtaTransactionManager();
    }
}


