package org.example;

import java.util.ArrayList;

import static org.example.Funcional.*;

public class Helper {

	public static int[] deepCopy(int[] mas){
		int[] newMas = new int[mas.length];
		for(int i = 0; i < mas.length; i++){
			newMas[i] = mas[i];
		}
		return newMas;
	}

	public static void printMas(int[][] mas){
		for(int i = 0; i < mas.length; i++){
			for(int j = 0; j < mas[i].length; j++){
				System.out.print(mas[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}


	public static void printTaskMas(int[] taskMas){
		for(int i : taskMas){
			System.out.print(i + " ");
		}
		System.out.println();
	}

	public static void printFenMatrix(ArrayList<Integer>[] fenMatrix) {

		for(ArrayList<Integer> i : fenMatrix){
			for(int j : i){
				System.out.print(j + "\t");
			}
			System.out.println();
		}
		System.out.println();

	}

	public static int supportSearchGroup(int elem, int n) {
		for (int j = 0; j < n; j++) {
			if (elem < (255 / n) * (j + 1)) {
				return j;
			}
		}
		return 0;
	}

	public static int supportSearchElemInFinMas(int[] genMas, int[] taskMas, int index, int n){

		ArrayList<Integer>[] fenMatrix = genFenMatrix(n);

		for(int i = 0; i < genMas.length; i++){
			for(int j = 0; j < n; j++){
				if(genMas[i] < (255/n)*(j+1)){
					fenMatrix[j].add(taskMas[i]);
					break;
				}
			}
		}
		int[] taskSum = countingTaskSum(fenMatrix);
		return taskSum[index];
	}

	public static void printFenMatrix(ArrayList<Integer>[] fenMatrix, int[] taskSum) {

		for(int i = 0; i < fenMatrix.length; i++){
			for(int j : fenMatrix[i]){
				System.out.print(j + "\t");
			}
			System.out.println(" | " + taskSum[i]);
		}
		System.out.println();
	}

	public static void printMas(int[] generation){
		for(int i : generation){
			System.out.print(i + "\t");
		}
		System.out.println();
	}
}
