package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import connection.SQLHelper;

/**
 * @author 141250149吴秦月
 * @date 创建时间：2016年12月22日 上午11:25:15
 */
public class Data {
	private DataSource dataSource = null;
	private Connection connection = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	private String username="";
	private Homework homework;
	public Homework getHomework() {
		return homework;
	}
	public void setHomework(Homework homework) {
		this.homework = homework;
	}
	public Data(DataSource dataSource){
		this.dataSource=dataSource;
	}
	public boolean isValid(String sid,String password){
		connection = SQLHelper.getConnection(dataSource);
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
				//在homework表里取得作业情况
				preparedStatement = connection.prepareStatement("select * from homework where sid=?");
				preparedStatement.setString(1,sid);
				resultSet = preparedStatement.executeQuery();
				homework =new Homework(sid);
				ArrayList<Result> result=new ArrayList<Result>(); 
				while(resultSet.next()){
					result.add(new Result(resultSet.getString(2),resultSet.getString(3)));
				}
				homework.setResult(result);
			}else{//结果集为空，说明用户输入的用户名密码错误
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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