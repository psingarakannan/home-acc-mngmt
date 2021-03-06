package org.pradeep.platform.hibernate;

import com.zaxxer.hikari.HikariDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

/**
 * @author psingarakannan on 9/12/18
 **/
@Configuration
/*
@EnableTransactionManagement
*/
/*
@EnableJpaRepositories(basePackages = {"org.pradeep.exp.mngmt.entities","org.pradeep.exp.mngmt.entities","org.pradeep.acc.mngmt.entities"})
*/
/*
@EnableJpaRepositories(basePackages = {"org.pradeep.exp.mngmt.entities","org.pradeep.exp.mngmt.entities","org.pradeep.acc.mngmt.entities"})
*/

public class HibernateConfig {


    @Value("${database.url:jdbc:mysql://localhost/pradeep?characterEncoding=UTF-8}")       private String databaseUrl;
    @Value("${database.user:root}")      private String databaseUser;
    @Value("${database.password:deep}")  private String databasePassword;

    @Value("${database.driver:com.mysql.jdbc.Driver}")	private String databaseDriver;
    @Value("${database.initialPoolSize:50}")  private Integer databaseInitialPoolSize;
    @Value("${database.minPoolSize:50}")  private Integer databaseMinPoolSize;
    @Value("${database.maxPoolSize:200}")  private Integer databaseMaxPoolSize;
    @Value("${database.idleConnectionTestPeriod:300}")  private Integer databaseIdleConnectionTestPeriod;
    @Value("${database.acquireIncrement:16}")  private Integer databaseAcquireIncrement;
    @Value("${database.maxStatements:1024}")  private Integer databaseMaxStatements;
    @Value("${database.numHelperThreads:3}")  private Integer databaseNumHelperThreads;
    @Value("${database.checkoutTimeout:1000}")  private Integer databaseCheckoutTimeout;
    @Value("${database.maxIdleTime:600}")  private Integer databaseMaxIdleTime;
    @Value("${database.idleTimeExcessConnections:300}")  private Integer databaseIdleTimeExcessConnections;
    @Value("${database.testConnectionOnCheckin:true}")  private Boolean databaseTestConnectionOnCheckin;


    @Value("${database.releaseConnectionAfterTransaction:false}")  private Boolean releaseConnectionAfterTransaction;

    //Changes Done during Performance
    @Value("${database.maxConnectionAge:1800}")  private Integer maxConnectionAge;
    @Value("${database.maxStatementsPerConnection:0}")  private Integer databaseMaxStatementsPerConnection;

    //Hibernate Properties
    @Value("${hibernate.show_sql:false}")     private Boolean hibernateShowSql;
    @Value("${hibernate.format_sql:false}")   private Boolean hibernateFormatSql;
    @Value("${hibernate.use_sql_comments:false}")   private Boolean hibernateUseSqlComments;
    @Value("${hibernate.hbm2ddl.auto:validate}")   private String hibernateHbm2ddlAuto;
    @Value("${hibernate.generate_statistics:false}") private Boolean hibernateGenerateStatistics;

    //Debugging
    @Value("${database.unreturnedConnectionTimeout:0}") private int unreturnedConnectionTimeout;
    @Value("${database.debugUnreturnedConnectionStackTraces:false}") private Boolean debugUnreturnedConnectionStackTraces;

    @Bean
    @ConfigurationProperties("app.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

/*
    @Bean
    @ConfigurationProperties("spring.datasource")
    public HikariDataSource dataSource() {
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    public String[] getPackagesToScan(){

        return new String[]{"org.pradeep.exp.mngmt.entities","org.pradeep.exp.mngmt.entities","org.pradeep.acc.mngmt.entities"};
    }

    @Bean(name = "entityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean ();
        emf.setDataSource(dataSource ());
//        emf.setJpaVendorAdapter(jpaVendorAdapter);
        emf.setPackagesToScan(getPackagesToScan ());
        emf.setPersistenceUnitName("default");
        emf.afterPropertiesSet();
        return emf.getObject();
    }

    @Bean
    public SessionFactory getSessionFactory() {
        if (entityManagerFactory().unwrap(SessionFactory.class) == null) {
            throw new NullPointerException("factory is not a hibernate factory");
        }
        return entityManagerFactory().unwrap(SessionFactory.class);
    }
*/

/*    @Bean
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource ();
        ds.setMaximumPoolSize(100);
        ds.setDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlDataSource");
        ds.addDataSourceProperty("url", "jdbc:mysql://localhost:3306/test");
        ds.addDataSourceProperty("user", "root");
        ds.addDataSourceProperty("password", "password");
        ds.addDataSourceProperty("cachePrepStmts", true);
        ds.addDataSourceProperty("prepStmtCacheSize", 250);
        ds.addDataSourceProperty("prepStmtCacheSqlLimit", 2048);
        ds.addDataSourceProperty("useServerPrepStmts", true);
        return ds;
    }*/
/*
    @Bean
    @Autowired
    public DataSource dataSource(){
        try {
            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setDriverClass(databaseDriver);
            dataSource.setJdbcUrl(databaseUrl);
            dataSource.setUser(databaseUser);
            dataSource.setPassword(databasePassword);
            dataSource.setInitialPoolSize(databaseInitialPoolSize);
            dataSource.setMinPoolSize(databaseMinPoolSize);
            dataSource.setMaxPoolSize(databaseMaxPoolSize);
            dataSource.setIdleConnectionTestPeriod(databaseIdleConnectionTestPeriod);
            dataSource.setAcquireIncrement(databaseAcquireIncrement);
            dataSource.setMaxStatements(databaseMaxStatements);
            dataSource.setNumHelperThreads(databaseNumHelperThreads);
            dataSource.setCheckoutTimeout(databaseCheckoutTimeout);
            dataSource.setMaxIdleTime(databaseMaxIdleTime);
            dataSource.setMaxIdleTimeExcessConnections(databaseIdleTimeExcessConnections);
            dataSource.setTestConnectionOnCheckin(databaseTestConnectionOnCheckin);
            dataSource.setUnreturnedConnectionTimeout(unreturnedConnectionTimeout);
            dataSource.setDebugUnreturnedConnectionStackTraces(debugUnreturnedConnectionStackTraces);
            dataSource.setMaxConnectionAge(maxConnectionAge);
            dataSource.setMaxStatementsPerConnection(databaseMaxStatementsPerConnection);
            return dataSource;
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        return null;
    }*/

/*    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");

        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings","false");
        return properties;
    }
*/
/*    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {

        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder (dataSource);
        sessionBuilder.addProperties(getHibernateProperties());
        sessionBuilder.addAnnotatedClasses(SecurityProperties.User.class);

        return sessionBuilder.buildSessionFactory();
    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.show_sql", "true");
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.put("hibernate.id.new_generator_mappings","false");
        return properties;
    }*/
}
