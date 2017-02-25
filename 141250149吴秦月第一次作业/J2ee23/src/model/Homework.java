package model;

import java.util.ArrayList;

/**
* @author 141250149吴秦月
* @date 创建时间：2016年12月22日 下午2:06:58
*/
public class Homework {
private String sid;
private ArrayList<Result> result;
public String getSid() {
	return sid;
}
public void setSid(String sid) {
	this.sid = sid;
}
public ArrayList<Result> getResult() {
	return result;
}
public void setResult(ArrayList<Result> result) {
	this.result = result;
}
public Homework(String sid) {
	super();
	this.sid = sid;
	
}


}
