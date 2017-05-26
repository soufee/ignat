package main.java;




import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

import java.sql.Connection;
import java.sql.SQLException;


public class ConnectionFactory {
  //  static final Logger userLogger = LogManager.getLogger(ConnectionFactory.class);
    public static Connection conn = null;
public static DataSource datasource;
   static {
       datasource = new DataSource();
      // userLogger.debug("Trying to make connection in ConnectionFactory ");
       PoolProperties p = new PoolProperties();
       p.setUrl("jdbc:postgresql://localhost/postgres");
       p.setDriverClassName("org.postgresql.Driver");
       p.setUsername("postgres");
       p.setPassword("postgres");
       p.setJmxEnabled(true);
       p.setTestWhileIdle(false);
       p.setTestOnBorrow(true);
       p.setValidationQuery("SELECT 1");
       p.setTestOnReturn(false);
       p.setValidationInterval(30000);
       p.setTimeBetweenEvictionRunsMillis(300000);
       p.setMaxActive(100);
       p.setInitialSize(10);
       p.setMaxWait(10000);
       p.setRemoveAbandonedTimeout(60);
       p.setMinEvictableIdleTimeMillis(300000);
       p.setMinIdle(100);
       p.setLogAbandoned(true);
       p.setRemoveAbandoned(true);
       p.setJdbcInterceptors(
               "org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;" +
                       "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");
       datasource.setPoolProperties(p);

    //   userLogger.debug("Setting properties to datasource "+datasource);
       }

    public static Connection getConnection() {
        try {

            conn = datasource.getConnection();
       //     userLogger.debug("Getting connection with properties "+conn);

        } catch (SQLException e) {
    //        userLogger.debug("A mistake with SQL "+e.getMessage());
        }

        return conn;
    }


}


