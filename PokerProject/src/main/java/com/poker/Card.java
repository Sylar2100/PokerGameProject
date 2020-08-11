package com.poker;
public class Card{
    private int number, suit;

    //Is a array with the high priority in the case of suits and numbers in this order I'll know what card is the high
    //The rank is from left to right (low to high value)
    private static String[] suits = { "C", "H", "D", "S" };
    private static String[] numbers  = { "A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K", "A" };

    public static String rankAsString( int _number ) {
        return numbers[_number];
    }

    public Card(int number, int suit)
    {
    	this.suit=suit;
        this.number=number;
    }

    //Is for the Unit Test to display the hand with the cards of every player
    public @Override String toString()
    {
          return numbers[number] + suits[suit];
    }

    public int getNumber() {
         return number;
    }

    public int getSuit() {
        return suit;
    }
}