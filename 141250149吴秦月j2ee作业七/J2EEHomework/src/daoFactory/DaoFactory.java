package daoFactory;

import dao.HomeworkDao;
import dao.HomeworkDaoImpl;

/**
* @author 141250149吴秦月
* @date 创建时间：2017年1月2日 下午2:31:39
*/
public class DaoFactory {
	public static HomeworkDao getHomeworkDao() {
		return HomeworkDaoImpl.getInstance();
	}
}
