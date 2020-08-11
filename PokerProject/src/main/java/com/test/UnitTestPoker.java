package com.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.poker.Card;
import com.poker.Hand;
import com.poker.ProcessHand;

public class UnitTestPoker {
	
	@Test
	public void test() {
		//Test both hands
		String pokerGame = "6C 6D 6H QH 6S TD QD KD 9D JD";
		List<Card> cards = new ArrayList<Card>();
		
		ProcessHand processHand = new ProcessHand();
		processHand.processHand(cards, pokerGame);
		Hand hand= new Hand(cards.subList(0, 5));
        Hand hand2= new Hand(cards.subList(5, 10));
		//Testing hand both players
        System.out.println("----------");
        hand.show();
        hand.showHand();
        System.out.println("..........");
        hand2.show();
        hand2.showHand();
        if(hand.compareTo(hand2) == 1) {
        	System.out.println("Player1 Win");
        } else if(hand.compareTo(hand2) == -1) {
        	System.out.println("Player2 Win");
        } else {
        	System.out.println("Neither Player Win");
        }
	}
}
