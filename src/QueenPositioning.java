import java.io.IOException;
import java.io.PrintWriter;

/**
 * Proiectarea Algoritmilor, 2013
 * Lab 4: Backtracking si optimizari
 * Task 1: Queen Positioning Problem - Simple Backtracking
 *
 * @author 	Cristian Condurache
 * @email	cristian.condurache@cti.pub.ro
 */

public class QueenPositioning {

	public static int bktCounter = 0;
	public static int solutionCounter = 0;

	/**
	 * Metoda care intoarce true daca doua regine se ataca
	 */
	public static boolean areQueensAttacking(int row1, int column1, int row2, int column2) {
		// daca reginele sunt pe acelasi rand sau coloana
		boolean sameRow = (row1 == row2);
		boolean sameCol = (column1 == column2);
		// daca reginele sunt pe aceeasi diagonala paralela cu
		boolean sameFstDiagonal = (column2 - column1 == row2 - row1); // prima diagonala
		boolean sameSndDiagonal = (row1 + column1 == row2 + column2); // a doua diagonala

		return sameRow || sameCol || sameFstDiagonal || sameSndDiagonal;
	}

	/**
	 * Intoarce true daca regina adaugata la pozitia (row, column)
	 * nu se ataca cu celelalte regine pozitionate pe coloanele [0, column - 1]
	 */
	public static boolean nonAttacking(int[] queens, int row, int column) {
		boolean test = true;
		// TODO 1: Verifica daca reginele din queens pozitionate anterior
		// se ataca cu regina pozitionata la row si column
		for(int i = 0; i < row; i++) {
			if(areQueensAttacking(i, queens[i], row, column)) {
				test = false;
			}
		}
		return test;
	}

	/**
	 * Implementarea backtracking-ului simplu
	 * in array-ul queens, la pozitia queens[i] se afla coloana aleasa pentru
	 * regina aflata pe linia i
	 */
	public static void doBKT(int[] queens, int current, int n, PrintWriter out) {

		bktCounter++; // incrementam numarul total de intrari in recursivitate

		// TODO 2: Implementarea algoritmului de backtracking simplu
		// TODO 3: Afisarea tuturor solutiilor gasite
		// TODO 4: Incrementarea variabilei solutionCounter pentru fiecare solutie
		if(current == n) {
			//afisez solutie
			solutionCounter++;
			out.println("Solutia cu nr : " + solutionCounter );
			for(int i = 0; i < n; i++) {
				for(int j = 0; j < n; j++) {
					if(j == queens[i]) {
						out.print(" * ");
					} else {
						out.print(" o ");
					}
				}
				out.println();
			}
		} else {
			for(int i = 0; i < n; i++) {
				if(nonAttacking(queens, current, i)) {
					queens[current] = i;
					doBKT(queens, current+1, n, out);
				}
			}
		}
	}

	public static void main(String[] args) {
		int maxDim = 10;
		try {
			PrintWriter out = new PrintWriter("queens.out");
			
			for(int i = 8; i < maxDim; i++) {
				bktCounter = 0;
				solutionCounter = 0;

				doBKT(new int[i], 0, i,out);

				System.out.println("Numar regine : " + i);
				System.out.println("Numar de intrari in recursivitare :" + bktCounter);
				System.out.println("Numar de solutii gasite: " + solutionCounter);
				System.out.println();
				out.println();
			}
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		


	}
}
