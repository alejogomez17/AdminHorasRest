
package com.desarrollo.adminhoras.main;

import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDao;
import com.desarrollo.adminhoras.api.datos.dao.AdminHorasDaoImpl;
import com.desarrollo.adminhoras.api.negocio.impl.AdministradorIngresosServiceImpl;
import com.desarrollo.adminhoras.api.negocio.impl.AdministradorRegistroServiceImpl;
import com.desarrollo.adminhoras.api.negocio.impl.ManejoArchivoServiceImpl;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorConflictoService;
import java.util.Properties;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import com.desarrollo.adminhoras.api.negocio.control.ManejoArchivoService;
import com.desarrollo.adminhoras.api.negocio.impl.AdministradorConflictoServiceImpl;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorRegistroService;
import com.desarrollo.adminhoras.api.negocio.control.AdministradorIngresosService;
import com.desarrollo.adminhoras.api.negocio.control.CalculoHorasService;
import com.desarrollo.adminhoras.api.negocio.impl.CalculoHorasServiceImpl;

/**
 *
 * @author Alejo Gomez
 */
@Configuration
@EnableTransactionManagement
public class PersistenceJPAConfig {
    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[]{"com.desarrollo.adminhoras.api.datos.dominio"});
        em.setPersistenceUnitName("PersistenceContext");
        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());
        return em;
    }
    
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
//        dataSource.setUrl("jdbc:postgresql://35.192.168.203:5432/ogmios_prueba");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/bd_calculohoras");
        dataSource.setUsername("postgres");
        dataSource.setPassword("admin");
        return dataSource;
    }
    
    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);
        return transactionManager;
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.temp.use_jdbc_metadata_defaults", "false");
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.show_sql", "true");
        return properties;
    }
    
    @Bean
    public AdminHorasDao operacionesDao() {
        return new AdminHorasDaoImpl();
    }

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public ManejoArchivoService validadorService() {
        return new ManejoArchivoServiceImpl();
    }
    
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AdministradorIngresosService administracionIngresosService() {
        return new AdministradorIngresosServiceImpl();
    }
    
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AdministradorRegistroService administracionRegistroService() {
        return new AdministradorRegistroServiceImpl();
    }
    
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AdministradorConflictoService administradorConflictoService() {
        return new AdministradorConflictoServiceImpl();
    }
    
    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public CalculoHorasService calculoHorasService() {
        return new CalculoHorasServiceImpl();
    }
}
