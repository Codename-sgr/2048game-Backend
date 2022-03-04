package com.sagar.lld.service;

import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameService gameService=new GameService(3);
		gameService.setUpInitialCell();
		gameService.startGame();
		Scanner sc=new Scanner(System.in);
		boolean isGameCompleted=false;
		while(!isGameCompleted) {
			int swipeValue=sc.nextInt();
			isGameCompleted= gameService.moveCells(swipeValue);
		}

	}

}
