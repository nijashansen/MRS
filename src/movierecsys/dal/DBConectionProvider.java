/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierecsys.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Nijas Hansen
 */
public class DBConectionProvider
{
        private static final String setServerName = "10.176.111.31";
        private static final String setDatabaseName = ("mrsnwh");
        private static final String setUser = ("CS2018A_25");
        private static final String setPassword = ("CS2018A_25");
        private SQLServerDataSource ds;
        
        public void DB()
        {
            ds = new SQLServerDataSource();
            ds.setServerName(DBConectionProvider.setServerName);
            ds.setDatabaseName(DBConectionProvider.setDatabaseName);
            ds.setUser(DBConectionProvider.setUser);
            ds.setPassword(DBConectionProvider.setPassword);
        }
        
        public Connection getConnection() throws SQLServerException
        {
            return ds.getConnection();
        }
}
