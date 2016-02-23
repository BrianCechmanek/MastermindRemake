package nerdyChallenge.IndianaJones;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class IndianaJones {

    private static char[] treasures = {'a', 'b', 'c', 'd'};	
    private static char[] shuffled = new char[4];
    private static int tries = 0;
    private static int tryLimit = 7;
    private static Boolean win = false;
    
    // Strings
    private final static String begin = "Time is running out and the ceiling is falling on you! ";
    private final static String wrongInput = "You don't have such a treasure! ";
    private final static String incompleteInput = "The number of treasures to place is 4. no more. no fewer. ";
    private final static String wrong = "Your attempt is wrong. ";
    private final static String lowers = "The ceiling lowers ever closer. Keep trying!";
    private final static String winner = "You did it! Indiana managed to escape the room!";
    private final static String loser = "The celining lower too much and crushes our hero.";

    
    public static void main(String[] args) {
    	    //System.out.println(Arrays.toString(treasures));
    	    randomize();
    	    System.out.println(begin);
    	    while (!win) {
    	    	//main game loop
    	        Scanner input = new Scanner(System.in);
    	        System.out.println("input the four treasures...");
    	        char[] order=input.nextLine().replaceAll("\\s","").toLowerCase().toCharArray();
    	        System.out.println("your order is " + Arrays.toString(order));
    		   // input.close();     causes EOF next line failure. curious

		    	if (order.length != 4) {
		    	     System.out.println(incompleteInput + lowers);
		    	     tries++;
		     }  
	   		else if (invalidChar(order)) {
	    		    System.out.println(wrongInput + lowers);
	   	        tries++;
		     } 
	   		else if (Arrays.equals(order, shuffled)) {
    		    	    System.out.println(winner);
    		    	    input.close();
    		    	    win = true;
    		    }
	   		else if(tries == tryLimit) {
	   			System.out.println(loser);
	   			input.close();	   			
	   			break;
	   		}
    		    else {
    		    	    // I'd really like to use lambdas to print treasure(s) plural based on numCorrect. but can't seem to find
    		    	    // if they can be used in print formatting. 
    		    	    // so for now it's just plural all the time
    		    	    System.out.println("you placed " + numCorrect(order, shuffled) + " treasure" + ((numCorrect(order, shuffled) > 1) || ((numCorrect(order, shuffled)==0)) ? "s" : "") + " correctly!");
    		    	    System.out.println(wrong + lowers);
    		    	    tries++;
    		    }    	    	    
    	    } 
    	}
    
    private static void randomize() {      	
    	    List<Character> tempList = new ArrayList<>();
    	    for (char c : treasures) {
    	    	    tempList.add(c);
    	    }
    	    Collections.shuffle(tempList);
    	    for (int i = 0; i < 4; i++) {
    	    	    shuffled[i] = tempList.get(i);
    	    }
    	    //System.out.println("Treasures shuffled is now: " + Arrays.toString(shuffled));
     }    
	
    private static Boolean invalidChar(char[] order) {

    	    boolean validInput = false; 
    	    char[] temp = new char[4];
    	    System.arraycopy(order, 0, temp, 0, 4);
    	    Arrays.sort(temp);
    	    validInput = !Arrays.equals(treasures, temp);    
        return validInput;
    }
    
    private static int numCorrect(char[] order, char[] shuffled) {
    	    int count = 0;
    	    for (int i = 0; i < 4; i++) {
    	    	    if( order[i] == shuffled[i]) {
    	    	    	    count++;
    	    	    }
    	    }
    	    return count;
    }
}
