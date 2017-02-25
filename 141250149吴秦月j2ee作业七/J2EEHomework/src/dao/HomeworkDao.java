package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import model.Result;




/**
 * @author 141250149吴秦月
 * @date 创建时间：2017年1月2日 下午2:01:41
 * 单例模式
 */
public class HomeworkDao {
	private static SQLHelper sqlHelper = SQLHelper.getInstance();
	private static HomeworkDao homeworkDao = new HomeworkDao();
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String username="";

	private HomeworkDao(){}
	public static HomeworkDao getInstance(){
		return homeworkDao;
	}
	public Result getResult(String sid,String cid){
		Result result = null;
		connection=sqlHelper.getConnection();
		try {
			preparedStatement=connection.prepareStatement("select * from homework where sid=? and cid=?");
			preparedStatement.setString(1,sid);
			preparedStatement.setString(2, cid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				result=new Result(cid,resultSet.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlHelper.closeMySql(resultSet, preparedStatement, connection);
		}
		return result;
	}
	
	public ArrayList<String> getCourseList(String sid){
		ArrayList<String> courseList=new ArrayList<String>();
		connection=sqlHelper.getConnection();
		try {
			preparedStatement=connection.prepareStatement("select * from homework where sid=?");
			preparedStatement.setString(1,sid);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				courseList.add(resultSet.getString(2));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlHelper.closeMySql(resultSet, preparedStatement, connection);
		}
		return courseList;
	}
	public boolean isValid(String sid,String password){
		connection = sqlHelper.getConnection();
		try {
			preparedStatement = connection.prepareStatement("select * from student where sid=? and password=?");
			preparedStatement.setString(1,sid);
			preparedStatement.setString(2,password);
			resultSet = preparedStatement.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//学号为主键，只可能有一条纪录
		try {
			if(resultSet.next()){
				//得到用户名
				username=resultSet.getString(2);
			}else{//结果集为空，说明用户输入的用户名密码错误
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			sqlHelper.closeMySql(resultSet, preparedStatement, connection);
		}
		return true;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
}
