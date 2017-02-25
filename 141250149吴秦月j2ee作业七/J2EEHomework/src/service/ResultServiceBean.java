package service;


import dao.HomeworkDao;
import dao.HomeworkDaoImpl;

/**
 * Session Bean implementation class ResultServiceBean
 */
public class ResultServiceBean implements ResultService{
	HomeworkDao homeworkDao=HomeworkDaoImpl.getInstance();
	private static ResultServiceBean resultService=new ResultServiceBean();
	public static  ResultService getInstance(){
		return resultService;
	}
	/**
	 * Default constructor. 
	 */
	private ResultServiceBean() {

	}
	@Override
	public String getResult(String sid, String cid) {
		// TODO o-generated method stub
		return homeworkDao.getResult(sid, cid);
	}

}
