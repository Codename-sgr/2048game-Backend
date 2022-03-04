package com.sagar.lld.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Grid {
	private int size;
	private Cell[][] grid;
	private List<Cell> emptyCells;
	
	public List<Cell> getEmptyCells() {
		return emptyCells;
	}

	private int score;
	public Grid(int size) {
		this.size = size;
		this.grid=new Cell[size][size];
	}
	
	public void initializeGrid() {
		
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				grid[i][j]=new Cell();
				grid[i][j].setX(i);
				grid[i][j].setY(j);
			}
		}
		emptyCells=getEmptyCellsInGrid();
		fillInitialTwoCells(emptyCells);
	}
	
	private void fillInitialTwoCells(List<Cell> emptyCells) {
		// TODO Auto-generated method stub
		int i=0;
		while(i<2) {
			int x=new Random().nextInt(emptyCells.size());
			Cell cell=emptyCells.get(x);
			emptyCells.remove(x);
			cell.setValue(2);
			i++;
		}
	}

	public List<Cell> getEmptyCellsInGrid() {
		List<Cell> list=new ArrayList<>();
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(grid[i][j].getValue()==0) {
					list.add(grid[i][j]);
				}
			}
		}
		return list;
		
	}
	
	public void displayGrid() {
		for(int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				System.out.print(grid[i][j].getValue()+" ");
			}
			System.out.println();
		}
	}
	
	public boolean addNewRandomCell() {
		if(emptyCells.size()==0)
			return false;
		int x=new Random().nextInt(emptyCells.size());
		Cell cell=emptyCells.remove(x);
		cell.setValue(generateNewRandomValue());
		
		return true;
		
	}

	private int generateNewRandomValue() {
		// TODO Auto-generated method stub
		int probability=new Random().nextInt(10);
		if(probability<8)
			return 2;
		return 4;
	}
	
	
	public boolean isEmptyCellAvailable() {
		if(emptyCells.size()>0)
			return true;
		return false;
	}
	
	public void swipe(SwipeDirection swipeDirecttion) {
		// TODO Auto-generated method stub
		switch (swipeDirecttion) {
		case LEFT:
			for(int i=0;i<size;i++) {
				List<Cell> list=new ArrayList<Cell>();
				for(int j=0;j<size;j++) {
					list.add(grid[i][j]);
				}
				moveCells(list);
				mergeCells(list);
				moveCells(list);

			}
			break;
		case RIGHT:
			for(int i=0;i<size;i++) {
				List<Cell> list=new ArrayList<Cell>();
				for(int j=size-1;j>=0;j--) {
					list.add(grid[i][j]);
				}
				moveCells(list);
				mergeCells(list);
				moveCells(list);
			}
			break;
		case UP:
			for(int i=0;i<size;i++) {
				List<Cell> list=new ArrayList<Cell>();
				for(int j=0;j<size;j++) {
					list.add(grid[j][i]);
				}
				moveCells(list);
				mergeCells(list);
				moveCells(list);
			}
			break;
		case DOWN:
			for(int i=0;i<size;i++) {
				List<Cell> list=new ArrayList<Cell>();
				for(int j=size-1;j>=0;j--) {
					list.add(grid[j][i]);
				}
				moveCells(list);
				mergeCells(list);
				moveCells(list);
			}
			break;

		default:
			break;
		}
		
	}
	
	private void moveCells(List<Cell> list) {
		int currEmpty=0;
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getValue()!=0) {
				list.get(currEmpty).setValue(list.get(i).getValue());
				emptyCells.remove(list.get(currEmpty));
				if(currEmpty!=i) {
					list.get(i).clear();
					emptyCells.add(list.get(i));
				}
				currEmpty++;
			}
		}		
	}

	private void mergeCells(List<Cell> list) {
		// TODO Auto-generated method stub
		for(int i=0;i<list.size()-1;i++) {
			if(list.get(i).getValue()==list.get(i+1).getValue()) {
				list.get(i).merge(list.get(i+1));
				if(list.get(i).getValue()>score) {
					score=list.get(i).getValue();
				}
				emptyCells.add(list.get(i+1));
 			}
		}		
		
	}

	public int getScore() {
		return score;
	}
	
	public boolean twoAdjacentWithSameValue() {
		for(int i=0;i<size-1;i++) {
			for(int j=0;j<size-1;j++) {
				if(grid[i][j].getValue()==grid[i][j+1].getValue() || grid[i][j].getValue()==grid[i+1][j].getValue()) {
					return true;
				}
			}
		}
		
		for(int i=0;i<size-1;i++) {
			if(grid[size-1][i].getValue()==grid[size-1][i+1].getValue() || grid[i][size-1].getValue()==grid[i+1][size-1].getValue()) {
				return true;
			}
		}
		return false;
		
	}


	
	
	
	
	
	

}
