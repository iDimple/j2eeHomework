package com.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.HomeworkDao;

/**
 * Session Bean implementation class ResultServiceBean
 */
@Service
public class ResultServiceBean implements ResultService{
	@Autowired
	HomeworkDao homeworkDao;

	@Override
	public String getResult(String sid, String cid) {
		// TODO o-generated method stub
		return homeworkDao.getResult(sid, cid);
	}

}
