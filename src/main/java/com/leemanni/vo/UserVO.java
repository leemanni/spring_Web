package com.leemanni.vo;

public class UserVO {
	private String hobby;
	private int weight;
	private int height;
	private int age;
	
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String toString() {
		return "UserVO [hobby=" + hobby + ", weight=" + weight + ", height=" + height + ", age=" + age + "]";
	}
	
	
}
