package com.poker;

import java.util.List;

public class Hand {
    private Card[] cards;
    private int[] value;

    public Hand(List<Card> cardList)
    {
        value = new int[6];//Is for save the value in this array every hand in sort for know what is the ranked
        cards = new Card[5];//Is the number of the cards in hand
        
        //Iterate for populate the array cards with the cardsList
        for (int x = 0; x < 5; x++)
        {
        	cards[x] = cardList.get(x);
        }

        int[] numbers = new int[14];
        int[] orderedNumbers = new int[5];
        
        boolean flush=true, straight=false, royalFlush = false;
        
        int sameCards=1,sameCards2=1;
        
        //largeGroupNumber get the bigNumber and smallGroupNumber get the lower number
        int maxNumber=0,lowerNumber=0;
        int index=0;

        //Initialized arrays values
        for (int x=0; x <= 13; x++)
        {
        	numbers[x]=0;
        }
        
        //Get the number and set what is the number available and count the same number
        for (int x=0; x <= 4; x++)
        {
        	numbers[ cards[x].getNumber() ]++;
        }
        
        //Iterate and get all hand to see if are the same suit
        for (int x=0; x < 4; x++) {
            if ( cards[x].getSuit() != cards[x+1].getSuit() )
                flush=false;
        }

        //Iterate and check if the cards are the same Number
        for (int x = 13; x >= 0; x--)
        {
             if (numbers[x] > sameCards) {
            	 //if sameCards was not the default value
                 if (sameCards != 1) {
                     sameCards2 = sameCards;
                     lowerNumber = maxNumber;
                 }

                 sameCards = numbers[x];
                 maxNumber = x;

             } else if (numbers[x] > sameCards2) {
                 sameCards2 = numbers[x];
                 lowerNumber = x;
             }
        }

        //if ace, run this before because ace is highest card
        if (numbers[0] == 1) {
            orderedNumbers[index] = 13;
            index++;
        }

        for (int x = 13; x >= 1; x--) {
            if (numbers[x] == 1)
            {
            	orderedNumbers[index] = x;
                index++;
            }
        }
        
        //can't have straight with lowest value of more than 10
        for (int x = 0; x <= 8; x++) {
            if (numbers[x]==1 && numbers[x+1]==1 && numbers[x+2]==1 && numbers[x+3]==1 && numbers[x+4]==1) {
                straight=true;
                break;
            }
        }

        //ace High and get the royal FLush
        if (numbers[9] == 1 && numbers[10] == 1 && numbers[11] == 1 && numbers[12] == 1 && numbers[13] == 1) {
        	royalFlush=true;
        }
        
        for (int x = 0; x <= 5; x++) {
            value[x] = 0;
        }

        int rankedHand = 0;
        if (flush) {
        	//royal flush
            if (royalFlush) {
            	rankedHand = 10;
            } else if (!straight) {
            	//flush
            	rankedHand = 6;
            } else if (straight) {
            	//straight flush
            	rankedHand = 9;
            }
            
        } else {
        	if (sameCards == 1) {
            	rankedHand = 1;
                value[1]=orderedNumbers[0];
                value[2]=orderedNumbers[1];
                value[3]=orderedNumbers[2];
                value[4]=orderedNumbers[3];
                value[5]=orderedNumbers[4];
            }

            //pair
            if (sameCards == 2 && sameCards2 == 1) {
            	rankedHand = 2;
                value[1]=maxNumber; 
                value[2]=orderedNumbers[0];
                value[3]=orderedNumbers[1];
                value[4]=orderedNumbers[2];
            }

            //two pairs
            if (sameCards == 2 && sameCards2 == 2) {
            	rankedHand = 3;
                //number of greater pair
                value[1]= maxNumber > lowerNumber ? maxNumber : lowerNumber;
                value[2]= maxNumber < lowerNumber ? maxNumber : lowerNumber;
                value[3]=orderedNumbers[0]; //extra card
            }

            //three of a kind
            if (sameCards == 3 && sameCards2 != 2) {
            	rankedHand = 4;
                value[1]= maxNumber;
                value[2]=orderedNumbers[0];
                value[3]=orderedNumbers[1];
            }

            //straight
            if (straight && !flush) {
            	rankedHand = 5;
            	value[1]=orderedNumbers[0];
                value[2]=orderedNumbers[1];
                value[3]=orderedNumbers[2];
                value[4]=orderedNumbers[3];
                value[5]=orderedNumbers[4];
            }

            //full house
            if (sameCards == 3 && sameCards2 == 2) {
            	rankedHand = 7;
                value[1] = maxNumber;
                value[2] = lowerNumber;
            }

            //four a kind
            if (sameCards == 4) {
            	rankedHand = 8;
                value[1] = maxNumber;
            }
        }
        value[0] = rankedHand;
    }

    public void show()
    {
        String msg;
        switch( value[0])
        {

            case 1:
            	msg = "High card " + Card.rankAsString(value[1]);
                break;
            case 2:
            	msg = "Pair of " + Card.rankAsString(value[1]);
                break;
            case 3:
            	msg = "Two pair " + Card.rankAsString(value[1]) + " " + Card.rankAsString(value[2]);
                break;
            case 4:
            	msg = "Three of a kind " + Card.rankAsString(value[1]);
                break;
            case 5:
            	msg = "High straight";
                break;
            case 6:
            	msg = "Flush";
                break;
            case 7:
            	msg = "full house " + Card.rankAsString(value[1]) + " with " + Card.rankAsString(value[2]);
                break;
            case 8:
            	msg = "Four of a kind " + Card.rankAsString(value[1]);
                break;
            case 9:
            	msg = "Straight flush";
                break;
            case 10:
            	msg = "Royal flush";
                break;
            default:
            	msg="something was wrong";
        }
        System.out.println(msg);
    }

    //This method show the hand of the player
    public void showHand() {
        for (int x=0; x<5; x++)
            System.out.println(cards[x]);
    }

    //This method compare the hand
    public int compareTo(Hand that) {
        for (int x = 0; x < 6; x++) {
            if (this.value[x] > that.value[x])
                return 1;
            else if (this.value[x] < that.value[x])
                return -1;
        }
        return 0; //if hands are equal
    }
}