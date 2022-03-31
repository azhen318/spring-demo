package com.example.springbootjta.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import com.example.springbootjta.config.properties.SecondDataSourceProperties;
import com.mysql.cj.jdbc.MysqlXADataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(
        entityManagerFactoryRef = "mcsEntityManager",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManager", //配置 事物管理器  transactionManager
        basePackages = {"com.example.springbootjta.service.mcs"}//设置持久层所在位置
)
public class McsJpaConfig {

    //注入mcs数据源信息
    @Autowired
    private SecondDataSourceProperties secondDataSourceProperties;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;



    /**
     * mcs数据源
     * @return
     * @throws SQLException
     */
    @Bean(name = "mcsDataSource")
    public DataSource mcsDataSource() throws SQLException {
        // 新建数据源，并将数据源配置信息装置
        MysqlXADataSource mysqlXADataSource = new MysqlXADataSource();
        mysqlXADataSource.setURL(secondDataSourceProperties.getUrl());
        mysqlXADataSource.setUser(secondDataSourceProperties.getUsername());
        mysqlXADataSource.setPassword(secondDataSourceProperties.getPassword());
        mysqlXADataSource.setPinGlobalTxToPhysicalConnection(true);

        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXADataSource);
        xaDataSource.setUniqueResourceName("mcsDataSource");

        return xaDataSource;
    }


    /**
     * mcs库的实体管理器
     * @return
     * @throws Throwable
     */
    @Bean(name = "mcsEntityManager")
    @DependsOn("transactionManager")
    public LocalContainerEntityManagerFactoryBean computerEntityManager() throws Throwable {

        HashMap<String, Object> properties = new HashMap();
        properties.put("hibernate.transaction.jta.platform", AtomikosJtaPlatform.class.getName());
        properties.put("javax.persistence.transactionType", "JTA");

        LocalContainerEntityManagerFactoryBean entityManager = new LocalContainerEntityManagerFactoryBean();
        entityManager.setJtaDataSource(mcsDataSource());
        entityManager.setJpaVendorAdapter(jpaVendorAdapter);
        entityManager.setPackagesToScan("com.example.springboot.model.dto.local.mcs");
        entityManager.setPersistenceUnitName("mcsPersistenceUnit");
        entityManager.setJpaPropertyMap(properties);
        return entityManager;
    }



}
