package com.sagar.lld.model;

public class Cell {
	private int x;
	private int y;
	private int value;
	
	public Cell() {
		this(0);
	}
	
	public Cell(int value) {
		this.value=value;
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	public void clear() {
		this.setValue(0);
	}
	
	public void merge(Cell cell) {
		this.setValue(value+cell.getValue());
		cell.clear();
		
	}
	
	

}
