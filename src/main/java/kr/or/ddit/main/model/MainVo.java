package kr.or.ddit.main.model;

import java.util.List;

public class MainVo {
	
	private List<String> userId;
	private List<String> name;
	
//	private List<String> addr1;
//	private List<String> addr2;
	private List<AddrVo> addr;

	public List<AddrVo> getAddr() {
		return addr;
	}

	public void setAddr(List<AddrVo> addr) {
		this.addr = addr;
	}

	public List<String> getName() {
		return name;
	}

	public void setName(List<String> name) {
		this.name = name;
	}

	public List<String> getUserId() {
		return userId;
	}

	public void setUserId(List<String> userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "MainVo [userId=" + userId + ", name=" + name + ", addr=" + addr + "]";
	}



	
	

}
