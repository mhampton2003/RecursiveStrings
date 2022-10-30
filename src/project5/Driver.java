//Maya Hampton
//This is my own work

package project5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {
	
	int counterB = 0;
	int counterC = 0;
	
	//checks if string is a slip - O(n)
	public boolean isSlip(String str) {
		
		//not a slip if the last character isn't a G
		if (str.charAt(str.length() - 1) != 'G') {
			return false;
		}
		
		//returns true for DFG or EFG
		if ((str.charAt(0) == 'D' || str.charAt(0) == 'E') && (str.charAt(1) == 'F') && (str.charAt(2) == 'G') && (str.length() == 3)) {
			return true;
		}
		
		//checks if the first two characters are DF or EF
		if ((str.charAt(0) == 'D' || str.charAt(0) == 'E') && str.charAt(1) == 'F') {
			
			int i = 2;
			
			//iterate through string until there are no more Fs
			while (str.charAt(i) == 'F') {
				i++;
			}
			
			//returns true if the next and last character is G
			if (str.charAt(i) == 'G' && i == str.length() - 1) {
				return true;
			}
			
			//if next character is not an F checks if it is another slip
			if (str.charAt(i) != 'F') {
				return isSlip(str.substring(i, str.length()));
			}
		}
		
		else {
			return false;
		}
		
		return false;
	}
	
	//checks if string is a slap - O(n)
	public boolean isSlap(String str) {
		
		//returns true for AH
		if (str.charAt(0) == 'A' && str.charAt(1) == 'H' && str.length() == 2) {
			return true;
		}
		
		//not a slap if the last character isn't a C
		if (str.charAt(str.length() - 1) != 'C') {
			return false;
		}
		
		//if first character is an A
		if (str.charAt(0) == 'A') {
			
			//if the next character is a B check if there is another slap
			if (str.charAt(1) == 'B') {
				counterB++;
				
				return isSlap(str.substring(2, str.length()));
			}
			
			//if next character is an H check how many Cs are in the string
			else if (str.charAt(1) == 'H') {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == 'C') {
						counterC++;
					}
				}

				//if there are the same number of Cs as Bs then return true
				if (counterC == counterB) {
					return true;
				}
			}
			
			//if next character is a D or E count the number of Cs and check for a slip
			else if (str.charAt(1) == 'D' || str.charAt(1) == 'E') {

				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) == 'C') {
						counterC++;
					}
				}
				
				if (isSlip(str.substring(1, str.length() - counterC))) {
					if (counterC - 1 == counterB) {
						return true;
					}
				}
			}
		}
		
		return false;
	}
	
	//checks if string is a slop - O(n)
	public boolean isSlop(String str) {

		//if the first two characters are AH
		if (isSlap(str.substring(0, 2))) {
			//check if it is followed by a slip
			if (isSlip(str.substring(2, str.length()))) {
				return true;
			}
			else {
				return false;
			}
		}
		
		int i = 0;
		
		//iterate through string while the character is not a C
		while (str.charAt(i) != 'C') {
			i++;
			
			//if end of string is reached with no Cs return false
			if (i == str.length()) {
				return false;
			}
		}
		
		//once there are Cs iterate through until the last one is reached
		while (str.charAt(i) == 'C') {
			i++;
			
			//if end of string is reached and the last character is a C return false
			if (i == str.length()) {
				return false;
			}
		}
		
		//if the string up to the last C is a slap and the following characters are a slip return true
		if (isSlap(str.substring(0, i)) && isSlip(str.substring(i, str.length()))) {
			return true;
		}
		
		return false;
	}

	public static void main(String[] args) throws FileNotFoundException {
		
		Driver d = new Driver();
		
		Scanner fin = new Scanner(new File("text.in"));

		int slopNumber = fin.nextInt();
		
		System.out.println("SLOPS OUTPUT");
		
		//checks for as many slops as was indicated
		for (int i = 0; i < slopNumber; i++) {
			
			String slopTest = fin.next();
			
			//checks for slop
			if (d.isSlop(slopTest)) {
				System.out.println(slopTest + " - YES");
			}
			
			else {
				System.out.println(slopTest + " - NO");
			}
			
		}
		
	}

}
