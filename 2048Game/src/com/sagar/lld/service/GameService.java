package com.sagar.lld.service;

import com.sagar.lld.model.Grid;
import com.sagar.lld.model.SwipeDirection;

public class GameService {
	private Grid grid;
	public GameService(int size) {
		// TODO Auto-generated constructor stub
		this.grid=new Grid(size);	
	}
	
//	Setting UP GAME
	
	public void setUpInitialCell() {
		grid.initializeGrid();		
	}
	
//	GAME LOGIC
	
	public void startGame() {
			grid.displayGrid();
	}
	
	public boolean moveCells(int swipeValue) {
		// TODO Auto-generated method stub

		 SwipeDirection swipeDirecttion=getSwipeDirection(swipeValue);
		 if(swipeDirecttion==null) {
			 System.out.println("INVALID MOVE");
		 }else{ 
			grid.swipe(swipeDirecttion);
			if(!grid.addNewRandomCell()) {
				System.out.println("No more empty Cells");
				return true;
			}
			System.out.println("Empty cells-"+grid.getEmptyCells().size());
			grid.displayGrid();
		 }
		 return isGameCompleted();
		
	}


	private SwipeDirection getSwipeDirection(int swipeValue) {
		// TODO Auto-generated method stub
		switch (swipeValue) {
		case 1:
			return SwipeDirection.LEFT;
		case 2:
			return SwipeDirection.RIGHT;
		case 3:
			return SwipeDirection.UP;
		case 4:
			return SwipeDirection.DOWN;
		
		default:
			return null;
		}
	}

	public boolean isGameCompleted() {
		// TODO Auto-generated method stub
		if(grid.getScore()==16) {
			System.out.println("Congratulations!");
			return true;
		}
		if(!grid.isEmptyCellAvailable()) {
			if(!grid.twoAdjacentWithSameValue()) {
				System.out.println("GAME OVER!");
				System.out.println(grid.getScore());
				return true;				
			}
		}
		return false;
	}

	
}
