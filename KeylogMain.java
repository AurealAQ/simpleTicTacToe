package keb;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.random.*;

public class KeylogMain {

	public KeylogMain() { // sets up all the hashmaps
		for (int i = 0; i < 9; i++) {
			board.put(i, 0);
		}
		upDown.put(0, upDown1);
		upDown.put(1, upDown2);
		upDown.put(2, upDown3);
		diagonal.put(1, diagonal1);
		diagonal.put(-1, diagonal2);
	}

	public static final int X = 1; // constants
	public static final int O = 2;

	public static final int[] upDown1 = { 0, 3, 6 };
	public static final int[] upDown2 = { 1, 4, 7 };
	public static final int[] upDown3 = { 2, 5, 8 }; // win conditions
	public static final int[] diagonal1 = { 0, 4, 8 };
	public static final int[] diagonal2 = { 2, 4, 6 };

	static HashMap<Integer, int[]> upDown = new HashMap<>();

	static HashMap<Integer, int[]> diagonal = new HashMap<>();

	static HashMap<Integer, Integer> board = new HashMap<>();

	public static boolean winCheck(int[] boarder) {
		if ((board.get(boarder[0]) & board.get(boarder[1] & board.get(boarder[2]))) != 0) {
			return true;
		}
		return false;

	}

	public static void winCalc(int lastMove) {
		board.put(lastMove, X);
		int localPos = lastMove % 3;
		if ((lastMove & 1) == 0) { // all diagonal tiles are even, thus the check
			int posCheck = 1 - localPos;
			if (posCheck == 0) { // special exception for middle tile
				if (winCheck(diagonal.get(-1)) && winCheck(diagonal.get(1))) {
					System.out.println("Won");
				}
			} else {
				if (winCheck(diagonal.get(posCheck))) {
					System.out.println("Won");
				}
			}
		}
		if (winCheck(upDown.get(localPos))) {
			System.out.println("Won");
		}
	}

	public static boolean validationHell(int processed) {
		if (board.get(processed) == 0) {
			return true;
		}
		return false;

	}

	public static int humanHell() {
		Scanner sc = new Scanner(System.in);
		for (;;) {
			if (sc.hasNextInt()) {
				int scan = sc.nextInt();
				if (validationHell(scan)) {
					sc.close();
					return scan;
				}
			}
		}
	}

	public static int robotHell() {
		Random randy = new Random();
		for (;;) {
			int rando = randy.nextInt(9);
			if (validationHell(rando)) {
				return rando;
			}
		}
	}

	public static void main(String[] args) {
		KeylogMain wa = new KeylogMain();
		int playCount = 0;

		while (playCount < 5) {
			winCalc(humanHell());
			playCount++;
			winCalc(robotHell());

		}

	}
}
