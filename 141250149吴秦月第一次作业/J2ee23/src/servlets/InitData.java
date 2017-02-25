package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import connection.SQLHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 在运行学生作业查询系统前，先手动run一下导入一下数据
 */
@WebServlet("/InitData")
public class InitData extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;  
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitData() {
		dataSource=SQLHelper.getDataSource();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		createTable();
		insertData();
	}

	private void createTable(){
		try {
			connection = SQLHelper.getConnection(dataSource);
			String sql = "DROP TABLE IF EXISTS student;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			sql = "CREATE TABLE student(" +
					"sid varchar(20) NOT NULL PRIMARY KEY," +
					"username varchar(20) NOT NULL,"+
					"password varchar(20) NOT NULL)"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();

			String sql1 = "DROP TABLE IF EXISTS homework;";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.execute();

			sql1 = "CREATE TABLE homework(" +
					"sid varchar(20) NOT NULL," +
					"cid varchar(50) NOT NULL," +
					"result varchar(20) NOT NULL DEFAULT 'unsubmitted',"
					+ "primary key(sid,cid))"
					+ "ENGINE=InnoDB DEFAULT CHARSET=utf8;";
			preparedStatement = connection.prepareStatement(sql1);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			SQLHelper.closeMySql(null, preparedStatement, connection);
		}
	}

	private void insertData(){
		String[] studentIDs = {"141250001","141250002","141250003"};
		String[] username = {"李华","王丽","魏忠贤"};
		String[] passwds = {"123456", "666666", "654321"};
		String[] cid = { "离散数学", "微积分", "Linux系统基础" };
		String[] result = { "passed", "failed", "unsubmitted" };

		try {
			connection = SQLHelper.getConnection(dataSource);
			connection.setAutoCommit(false);
			String sql = "insert into student(sid,username,password) values(?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<studentIDs.length;i++){
				preparedStatement.setString(1, studentIDs[i]);
				preparedStatement.setString(2, username[i]);
				preparedStatement.setString(3, passwds[i]);
				preparedStatement.addBatch();
			}
			preparedStatement.executeBatch();
			connection.commit();

			sql = "insert into homework(sid,cid,result) values(?,?,?);";
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<studentIDs.length;i++){
				for(int j=0;j<cid.length;j++){
					preparedStatement.setString(1, studentIDs[i]);
					preparedStatement.setString(2, cid[j]);
					preparedStatement.setString(3, result[j]);
					preparedStatement.addBatch();
				}
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			SQLHelper.closeMySql(null, preparedStatement, connection);
		}
	}
}
