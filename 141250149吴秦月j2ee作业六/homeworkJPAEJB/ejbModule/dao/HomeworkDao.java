package dao;

import java.util.ArrayList;

import javax.ejb.Remote;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年2月21日 下午3:42:57
*/
@Remote
public interface HomeworkDao {
	public String getResult(String sid,String cid);
	public ArrayList<String> getCourseList(String sid);
	public boolean isValid(String sid,String password);
	public String getUsername(String sid);
}
