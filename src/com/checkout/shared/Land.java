package com.checkout.shared;

import java.io.Serializable;

public class Land implements Serializable{
	
	public int id;
	public String name;
	
	public Land(){
		this.id = 0;
		this.name = "";
	}
	
	public Land(int id,String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
