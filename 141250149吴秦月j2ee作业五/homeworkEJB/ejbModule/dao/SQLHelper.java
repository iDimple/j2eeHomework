package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


/**
* @author 141250149吴秦月
* @date 创建时间：2017年1月2日 下午1:58:46
*  连接数据库，相应数据写在了jboss配置文件里
*  单例模式
*/
public class SQLHelper {

		private DataSource datasource = null;
		private InitialContext jndiContext = null;
		private Connection connection = null;

		private static SQLHelper sqlHelper = new SQLHelper();

		private SQLHelper() {

		}

		public static SQLHelper getSqlHelper() {
			return sqlHelper;
		}

		public Connection getConnection() {
			try {
				 final Hashtable properties = new Hashtable();
				properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

				try {
					jndiContext = new InitialContext(properties);
					datasource = (DataSource) jndiContext.lookup("java:/MySqlDS");
					if (datasource==null)
							System.out.println("datasource null");
				} catch (NamingException e) {
					e.printStackTrace();
				}	
				System.out.println("got context");
				System.out.println("About to get ds---DaoHelper");
				connection = datasource.getConnection();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return connection;
		}

		public void closeMySql(ResultSet resultSet,
				PreparedStatement preparedStatement, Connection connection) {
			try {
				if (resultSet != null) {
					resultSet.close();
					resultSet = null;
				}
				if (preparedStatement != null) {
					preparedStatement.close();
					preparedStatement = null;
				}
				if (connection != null) {
					connection.close();
					connection = null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
