package connection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2016年12月21日 下午10:34:49
 *  连接数据库，需要有相应的context.xml
 */
public class SQLHelper {
	// 要连接的数据库名称
	public static final String DBName = "student_homework";
	/**
	 * 设置数据源，在servlet的init方法中调用
	 * @return 数据源DataSource
	 */
	public static DataSource getDataSource(){
		DataSource dataSource=null;
		InitialContext jndiContext = null;

		Properties properties = new Properties();
		properties.put(javax.naming.Context.PROVIDER_URL, "jnp:///");
		properties.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY,
				"org.apache.naming.java.javaURLContextFactory");
		try {
			jndiContext = new InitialContext(properties);
			String url = "java:comp/env/jdbc/" + DBName;
			dataSource = (DataSource) jndiContext.lookup(url);
			System.out.println("success to get datasource");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dataSource;
	}
	
	public static Connection getConnection(DataSource dataSource) {
		Connection connection = null;
		try {
			connection = dataSource.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeMySql(ResultSet resultSet,
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
