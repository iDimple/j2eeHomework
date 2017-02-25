package service;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import dao.HomeworkDao;

/**
 * Session Bean implementation class ResultServiceBean
 */
@Stateless
public class ResultServiceBean implements ResultService{
	@EJB
	HomeworkDao homeworkDao;
    /**
     * Default constructor. 
     */
    public ResultServiceBean() {

    }
	@Override
	public String getResult(String sid, String cid) {
		// TODO o-generated method stub
		return homeworkDao.getResult(sid, cid);
	}

}
