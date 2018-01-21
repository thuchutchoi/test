package jp.tst.audittool.apiresource;

import java.io.FileNotFoundException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import jp.tst.audittool.apiresource.common.APIConstant;
import jp.tst.audittool.apiresource.common.APIUtil;
import jp.tst.audittool.apiresource.model.DatabaseConfig;

@Configuration
@EnableTransactionManagement
@ComponentScan({"jp.tst.audittool.apiresource"})
public class HibernateConfiguration {

  private final static Logger logger = LoggerFactory.getLogger(HibernateConfiguration.class);

  private static final String PROPERTY_NAME_DATABASE_DRIVER = "jdbc.driverClassName";
  private static final String PROPERTY_NAME_DATABASE_URL = "jdbc.url";
  private static final String PROPERTY_NAME_DATABASE_USERNAME = "jdbc.username";
  private static final String PROPERTY_NAME_DATABASE_PASS = "jdbc.password";

  private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
  private static final String PROPERTY_NAME_HIBERNATE_SHOW_SQL = "hibernate.show_sql";
  private static final String PROPERTY_NAME_HIBERNATE_FORMAT_SQL = "hibernate.format_sql";

  private static final String PROPERTY_NAME_HIBERNATE_HIKARI_MAX_POOL_SIZE =
      "hibernate.hikari.maximumPoolSize";
  private static final String PROPERTY_NAME_HIBERNATE_HIKARI_CONECTION_TIMEOUT =
      "hibernate.hikari.connectionTimeout";
  private static final String PROPERTY_NAME_HIBERNATE_HIKARI_MINIMUM_IDLE =
      "hibernate.hikari.minimumIdle";
  private static final String PROPERTY_NAME_HIBERNATE_HIKARI_IDLET_IMEOUT =
      "hibernate.hikari.idleTimeout";

  private static final String MODEL_PACKAGE_TO_SCAN = "jp.tst.audittool.apiresource.entity";

  /** Database Configuration class */
  private DatabaseConfig databaseConfig = null;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {
    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
    // Load database config
    loadDatabaseConfig();

    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(new String[] {MODEL_PACKAGE_TO_SCAN});
    sessionFactory.setHibernateProperties(hibernateProperties());
    return sessionFactory;
  }

  @Bean(destroyMethod = "close")
  public DataSource dataSource() {
    HikariConfig dataSourceConfig = new HikariConfig();

    dataSourceConfig.setDriverClassName(
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_DATABASE_DRIVER));
    dataSourceConfig
        .setJdbcUrl(databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_DATABASE_URL));
    dataSourceConfig.setUsername(
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_DATABASE_USERNAME));
    dataSourceConfig.setPassword(
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_DATABASE_PASS));

    int maxPoolSize = Integer.parseInt(databaseConfig.getHibProperties()
        .getProperty(PROPERTY_NAME_HIBERNATE_HIKARI_MAX_POOL_SIZE));
    dataSourceConfig.setMaximumPoolSize(maxPoolSize);

    int connectionTimeoutMs = Integer.parseInt(databaseConfig.getHibProperties()
        .getProperty(PROPERTY_NAME_HIBERNATE_HIKARI_CONECTION_TIMEOUT));
    dataSourceConfig.setConnectionTimeout(connectionTimeoutMs);

    int minIdle = Integer.parseInt(
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_HIBERNATE_HIKARI_MINIMUM_IDLE));
    dataSourceConfig.setMinimumIdle(minIdle);

    int idleTimeoutMs = Integer.parseInt(
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_HIBERNATE_HIKARI_IDLET_IMEOUT));
    dataSourceConfig.setIdleTimeout(idleTimeoutMs);

    return new HikariDataSource(dataSourceConfig);
  }

  private Properties hibernateProperties() {
    Properties properties = new Properties();
    properties.put(PROPERTY_NAME_HIBERNATE_DIALECT,
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
    properties.put(PROPERTY_NAME_HIBERNATE_SHOW_SQL,
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_HIBERNATE_SHOW_SQL));
    properties.put(PROPERTY_NAME_HIBERNATE_FORMAT_SQL,
        databaseConfig.getHibProperties().getProperty(PROPERTY_NAME_HIBERNATE_FORMAT_SQL));
    return properties;
  }

  @Bean
  @Autowired
  public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
    HibernateTransactionManager txManager = new HibernateTransactionManager();
    txManager.setSessionFactory(sessionFactory);
    return txManager;
  }

  /**
   * Load database config.
   *
   * @throws ExceptionInInitializerError the exception in initializer error
   * @throws FileNotFoundException
   */
  private void loadDatabaseConfig() throws ExceptionInInitializerError {
    logger.info("IN - loadDatabaseConfig()");
    databaseConfig = new DatabaseConfig();
		String fileConfig = APIUtil.getBaseDirectory() + APIConstant.DB_CONFIG;
    Properties properties = APIUtil.readPropertiesFile(fileConfig);
    if (properties != null) {
      databaseConfig.setHibProperties(properties);
    } else {
      logger.error("Read file config error: {}", fileConfig);
      throw new ExceptionInInitializerError();
    }

    logger.info("OUT - loadDatabaseConfig()");
  }
}
