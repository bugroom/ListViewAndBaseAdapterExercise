package com.example.listviewandbaseadapterexercise;

public class People {
	private int imgId;
	private String name;
	private String sex;
	
	public People(int imgId, String name, String sex) {
		super();
		this.imgId = imgId;
		this.name = name;
		this.sex = sex;
	}
	
	public int getImgId() {
		return imgId;
	}
	public void setImgId(int imgId) {
		this.imgId = imgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
}
