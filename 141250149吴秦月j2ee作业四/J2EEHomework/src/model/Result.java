package model;
/**
* @author 141250149吴秦月
* @date 创建时间：2017年1月2日 下午2:30:08
*/
public class Result {
	private String cid;
	private String result;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public Result(String cid, String result) {
		super();
		this.cid = cid;
		this.result = result;
	}
	public Result(){
		
	}
}
