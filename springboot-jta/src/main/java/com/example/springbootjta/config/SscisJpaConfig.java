package com.example.springbootjta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.springbootjta.config.properties.PrimaryDataSourceProperties;
import com.example.springbootjta.config.properties.SecondDataSourceProperties;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.util.HashMap;

@DependsOn("transactionManager")
@Configuration
@EnableJpaRepositories(
        entityManagerFactoryRef = "sscsiEntityManager",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages = {"com.example.springbootjta.service.sscsi"}//设置持久层所在位置
)
public class SscisJpaConfig {



    //注入sscsi数据源信息
    @Autowired
    private PrimaryDataSourceProperties primaryDataSourceProperties;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;



    /**
     * sscsi数据源
     * @return
     * @throws SQLException
     */
    @Bean(name = "sscsiDataSource")
    public DataSource sscsiDataSource() throws SQLException {
        // 新建数据源，并将数据源配置信息装置
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(primaryDataSourceProperties.getUrl());
        mysqlXADataSource.setUser(primaryDataSourceProperties.getUsername());
        mysqlXADataSource.setPassword(primaryDataSourceProperties.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("sscsiDataSource");

        return xaDataSource;
    }


    /**
     * sscsi库的实体管理器
     * @return
     * @throws Throwable
     */
    @Bean(name = "sscsiEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean computerEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(sscsiDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.springboot.model.dto.local.sscsi");
        entityManager.setPersistenceUnitName("sscsiPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }


}
