package com.poker;

import java.util.HashMap;
import java.util.List;

public class ProcessHand {
	public List<Card> processHand(List<Card> cards, String pokerGame) {
		
		//Create a map for identify the position value of each card and suit
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		//map.put("A", 0);
		map.put("2", 1);
		map.put("3", 2);
		map.put("4", 3);
		map.put("5", 4);
		map.put("6", 5);
		map.put("7", 6);
		map.put("8", 7);
		map.put("9", 8);
		map.put("T", 9);
		map.put("J", 10);
		map.put("Q", 11);
		map.put("K", 12);
		map.put("A", 13);
		map.put("C", 0);
		map.put("H", 1);
		map.put("D", 2);
		map.put("S", 3);
		int suit,number,gameArray=0;
		
		String[] pokerGameArray = new String[10];
	    String[] playerHand1 = new String[5];
    	String[] playerHand2 = new String[5];
    	
    	//Iterate the all poker hand and divide in 2 groups of 5
    	for(String cardHand : pokerGame.split(" ")) {
    		pokerGameArray[gameArray] = cardHand;
    		gameArray++;
    	}
    	
    	for(int p = 0; p < pokerGameArray.length; p++) {
    		if(p >= 0 && p < 5) {
    			playerHand1[p] = pokerGameArray[p];
    		}
    		if(p >= 5 && p < 10) {
    			playerHand2[p - 5] = pokerGameArray[p];
    		}
    	}
    	
    	//Iterate the playerHand for create a object Card for each card
    	for(int j = 0; j < playerHand1.length; j++) {
    		number = map.get(Character.toString(playerHand1[j].charAt(0)));
    		suit = map.get(Character.toString(playerHand1[j].charAt(1)));
    		cards.add( new Card(number,suit));
    	}
    	
    	for(int j = 0; j < playerHand2.length; j++) {
    		number = map.get(Character.toString(playerHand2[j].charAt(0)));
    		suit = map.get(Character.toString(playerHand2[j].charAt(1)));
    		cards.add( new Card(number,suit));
    	} 
    	return cards;
	}
}