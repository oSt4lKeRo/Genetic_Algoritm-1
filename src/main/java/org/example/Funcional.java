package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.SplittableRandom;

import static org.example.Helper.*;

public class Funcional {

	static class Test{
		boolean min;
		boolean swipe;
		boolean end;
		int beforeElem;
		int beforeIndex;
		int afterElem;
		int afterIndex;
		Test(){}
	}


	public static int[][] generateMas(int result){

		if (result == 1) {
			int n = 11;
			int m = 4;
			int T1 = 10;
			int T2 = 20;
			int diff = T2 - T1;

			int[][] mas = new int[n][m];
			Random random = new Random();

			for (int i = 0; i < mas.length; i++) {
				int value = random.nextInt(diff + 1);
				value += T1;
				for (int j = 0; j < mas[i].length; j++) {
					mas[i][j] = value;
				}
			}
			return mas;
		} else if(result == 2) {

			int m = 3;
			int[] masElem = {10, 15, 20, 16, 11, 13, 14, 19};
			int[][] mas = new int[masElem.length][m];

			for(int i = 0; i < mas.length; i++){
				for(int j = 0; j < mas[i].length; j++){
					mas[i][j] = masElem[i];
				}
			}
			return mas;
		} else {
			int[][] mas = {{11, 16, 10}, {13, 15, 19}, {16, 15, 17}, {10, 13, 14}, {12, 19, 12}, {19, 20, 19}, {17, 19, 17}, {17, 15, 20}, {11, 13, 11}};
			return mas;
		}
	}

	public static int[] generateElemToGenMas(int n){

		int T1 = 0;
		int T2 = 255;
		int diff = T2 - T1;

		int[] mas = new int[n];
		Random random = new Random();

		for (int i = 0; i < mas.length; i++) {
			int value = random.nextInt(diff + 1);
			value += T1;

			mas[i] = value;
		}
		return mas;
	}

	public static int[] generateTaskMas(int[][] mas){
		int[] taskMas = new int[mas.length];
		for(int i = 0; i < taskMas.length; i++){
			taskMas[i] = mas[i][0];
		}
		return taskMas;
	}

	public static int[][] generateGenMas(int[] taskMas, int n, int index){

		if(index == 0) {
			int[][] mas = new int[n][taskMas.length];
			for (int i = 0; i < mas.length; i++) {
				mas[i] = generateElemToGenMas(taskMas.length);
			}
			return mas;
		} else {
			int mas[][] = {{31, 52, 183, 200, 49, 72, 115, 5},{152, 54, 201, 87, 21, 21, 160, 213}, {89, 51, 173, 240, 10, 5,  36, 81}};
			return mas;
		}
	}

	public static ArrayList<Integer>[] genFenMatrix(int n){
		ArrayList<Integer>[] fenMatrix = new ArrayList[n];
		for(int i = 0; i < fenMatrix.length; i++){
			fenMatrix[i] = new ArrayList<>();
		}
		return fenMatrix;
	}


	public static int searchMax(int[] taskSum){
		int max = taskSum[0];
		for(int i : taskSum){
			if(max < i){
				max = i;
			}
		}
		return max;
	}

	public static int searchMax(int[] taskSum, Test test, int param){
		int max = taskSum[0];
		for(int i = 0; i < taskSum.length; i++){
			if(max < taskSum[i]){
				max = taskSum[i];
				if(param == 1) {
					test.beforeElem = max;
					test.beforeIndex = i;
				} else if(param == 2){
					test.afterElem = max;
					test.afterIndex = i;
				}
			}
		}
		return max;
	}

	public static int searchMin(int[] taskSum){
		int min = taskSum[0];
		for(int i : taskSum){
			if(min > i){
				min = i;
			}
		}
		return min;
	}

	public static int searchMaxIndex(int[] genMas){
		int max = genMas[0];
		int indexMax = 0;
		for(int i = 0; i < genMas.length; i++){
			if(genMas[i] > max){
				max = genMas[i];
				indexMax = i;
			}
		}
		return indexMax;
	}


	public static int[] crossover(int[] mas1, int[] mas2, int delimiter){
		int[] newGen = new int[mas1.length];
		for(int i = 0; i < delimiter; i++){
			newGen[i] = mas1[i];
		}
		for(int i = delimiter; i < mas1.length; i++){
			newGen[i] = mas2[i];
		}
		System.out.print("Crossover ");
		return newGen;
	}

	public static int[] countingTaskSum(ArrayList<Integer>[] fenMatrix){
		int[] taskSum = new int[fenMatrix.length];
		for(int i = 0; i < fenMatrix.length; i++){
			for(int j : fenMatrix[i]){
				taskSum[i] += j;
			}
		}
		return taskSum;
	}


	public static int countingBestElem(int[] taskMas, int[] genMas, int n){

		ArrayList<Integer>[] fenMatrix = genFenMatrix(n);

		for(int i = 0; i < genMas.length; i++){
			for(int j = 0; j < n; j++){
				int borders = (255/n)*(j+1);
				if(genMas[i] <= borders){
					fenMatrix[j].add(taskMas[i]);
					break;
				}
			}
		}

		int[] taskSum = countingTaskSum(fenMatrix);
		printFenMatrix(fenMatrix, taskSum);
		return searchMax(taskSum);
	}

	public static int countingBestElem(int[] taskMas, int[] genMas, int n, Test test, int param){

		ArrayList<Integer>[] fenMatrix = genFenMatrix(n);

		for(int i = 0; i < genMas.length; i++){
			for(int j = 0; j < n; j++){
				int borders = (255/n)*(j+1);
				if(genMas[i] <= borders ){
//					System.out.println(genMas[i] + " <= " + borders + "  | " + j);
					fenMatrix[j].add(taskMas[i]);
					break;
				}
				if(j == n-1) {
					fenMatrix[j].add(taskMas[i]);
				}
			}
		}

		int[] taskSum = countingTaskSum(fenMatrix);
		printFenMatrix(fenMatrix, taskSum);

		return searchMax(taskSum, test, param);
	}



	public static void mutation(int[] gen, Test test){

		Random random = new Random();

		int indexElem = random.nextInt(gen.length);


		indexElem = searchMaxIndex(gen);
		int beforeElem = gen[indexElem];
		int beforeIndex = supportSearchGroup(beforeElem, 4);
		if(beforeIndex == supportSearchGroup(test.beforeElem, 4)){
			test.end = true;
		}


		System.out.print(gen[indexElem] + " => ");

		String str = Integer.toBinaryString(gen[indexElem]);
		StringBuffer stringBuffer = new StringBuffer(str);

		for(int i = stringBuffer.length(); i < 8; i++){
			stringBuffer.insert(0, 0);
		}

		str = String.valueOf(stringBuffer);
		char[] chars = str.toCharArray();
		System.out.print(str);

		int indexBin = random.nextInt(chars.length);
		System.out.print("[" + indexBin + "] => ");

		if(chars[indexBin] == '0'){
			chars[indexBin] = '1';
		} else {
			chars[indexBin] = '0';
		}

		str = "";
		for(char i : chars){
			str += i;
		}
		System.out.print(str + " = ");

		int elem = Integer.parseInt(str, 2);
		System.out.println(elem);


		if(beforeElem > elem && beforeIndex != supportSearchGroup(elem, 4)){
			test.min = true;
		}


		gen[indexElem] = elem;
		printMas(gen);
	}

	public static int[] searchNewGeneration(int[] taskMas, int[][] genMas, int[] generation, int countPros) {

		int[] newGenerations = new int[genMas.length];
		Random random = new Random();
		for (int i = 0; i < genMas.length; i++) {

			int delimiter = random.nextInt(taskMas.length - 1);
			int indexSecondGenMas = 0;
			while (true) {
				indexSecondGenMas = random.nextInt(genMas.length);
				if (indexSecondGenMas != i) {
					break;
				}
			}

			int[] newGen1 = genMas[i];
			int[] newGen2 = genMas[indexSecondGenMas];

			int count = 0;
			if (random.nextInt(100) <= 70) {
				newGen1 = crossover(genMas[i], genMas[indexSecondGenMas], delimiter);
				count++;
			}
			if (random.nextInt(100) <= 70) {
				newGen2 = crossover(genMas[indexSecondGenMas], genMas[i], delimiter);
				count++;
			}
//			if (count == 0) {
//
//				printMas(genMas[i]);
//
//				mutation(newGen1);
//
//				printMas(newGen1);
//				System.out.println("-----");
//
//				int[] resultMas = new int[2];
//
//				resultMas[0] = generation[i];
//				resultMas[1] = countingBestElem(taskMas, newGen1, countPros);
//
//				printMas(resultMas);
//				System.out.println("   " + searchMin(resultMas));
//				System.out.println();
//
//				newGenerations[i] = searchMin(resultMas);
//
//			} else {

			printMas(genMas[i]);
			printMas(genMas[indexSecondGenMas]);
			System.out.println("-----");

			System.out.print("О" + i + " + O" + indexSecondGenMas + ": ");

			printMas(newGen1);
			Test test1 = new Test();
			if (random.nextInt(100) <= 100) {
				System.out.println();
				countingBestElem(taskMas, newGen1, countPros, test1, 1);

				System.out.print("Мутация: ");
				mutation(newGen1, test1);
			}

			System.out.print("О" + indexSecondGenMas + " + O" + i + ": ");
			printMas(newGen2);

			Test test2 = new Test();
			if (random.nextInt(100) <= 100) {
				System.out.println();
				countingBestElem(taskMas, newGen2, countPros, test2, 1);

				System.out.print("Мутация: ");
				mutation(newGen2, test2);
//				}

				System.out.println();

				int[] resultMas = new int[genMas.length - 1];

				resultMas[0] = generation[i];
				resultMas[1] = countingBestElem(taskMas, newGen1, countPros, test1, 2);
				resultMas[2] = countingBestElem(taskMas, newGen2, countPros, test2, 2);

				/////////////////-------------------------------------------------Вывод
				if (test1.min && (test1.beforeIndex != test1.afterIndex) && (supportSearchElemInFinMas(newGen1, taskMas, test1.beforeIndex, countPros) <= supportSearchElemInFinMas(newGen1, taskMas, test1.afterIndex, countPros)) && test1.end) {
					System.out.println("Truuuuuee1");
				}
				if (test2.min && (test2.beforeIndex != test2.afterIndex) &&  (supportSearchElemInFinMas(newGen2, taskMas, test2.beforeIndex, countPros) <= supportSearchElemInFinMas(newGen2, taskMas, test2.afterIndex, countPros)) && test2.end) {
					System.out.println("Truuuuuee2");
				}

				printMas(resultMas);
				System.out.println("   " + searchMin(resultMas));
				System.out.println();

				newGenerations[i] = searchMin(resultMas);
			}
			System.out.println("\n");
		}
		return newGenerations;
	}


}
