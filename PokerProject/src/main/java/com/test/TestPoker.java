package com.test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.poker.Card;
import com.poker.Hand;
import com.poker.ProcessHand;

public class TestPoker {
	public static void main(String[] args) {
		try {
			FileInputStream fstream = new FileInputStream("src/files/pokerdata.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			String strLine;
			//This map contain how many hands the player1 win or player2 win
			HashMap<String, BigDecimal> winnerList = new HashMap<String, BigDecimal>();
			
			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				List<Card> cards = new ArrayList<Card>();
				ProcessHand processHand = new ProcessHand();
				processHand.processHand(cards, strLine);
				Hand hand= new Hand(cards.subList(0, 5));
		        Hand hand2= new Hand(cards.subList(5, 10));
		        String playerKey = "";
		        if(hand.compareTo(hand2) == 1) {
		        	playerKey = "player1";
		        } else if(hand.compareTo(hand2) == -1) {
		        	playerKey = "player2";
		        } else {
		        	playerKey = "playersDraw";
		        }
		        if(winnerList.get(playerKey) != null) {
		        	BigDecimal playerWin = winnerList.get(playerKey).add(BigDecimal.ONE);
		        	winnerList.put(playerKey, playerWin);
		        } else {
		        	winnerList.put(playerKey, BigDecimal.ONE);
		        }		 
			}
			 // Print the content on the console
			System.out.println("1:" + winnerList.get("player1"));
			System.out.println("2:" + winnerList.get("player2"));
			System.out.println(winnerList.get("playersDraw") == null ? "3:" + "0" : "3:" + winnerList.get("playersDraw"));
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
