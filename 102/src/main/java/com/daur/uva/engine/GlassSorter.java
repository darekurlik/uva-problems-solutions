package com.daur.uva.engine;

import java.util.ArrayList;

public class GlassSorter {

	private String[] sequences = new String[] {
		"BCG",
		"BGC",
		"CBG",
		"CGB",
		"GBC",
		"GCB"
	};

	private int[][] colorMatrix = new int[][] {
		{ 0, 3, 6},
		{ 1, 4, 7},
		{ 2, 5, 8}
	};

	public Answer sortGlass(String input) {
		Integer[] glassBins = convertInput(input);
		return processInput(glassBins);
	}

	private Integer[] convertInput(String input) {
		String[] tokens = input.split("\\s+");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (String each : tokens) {
			list.add(Integer.valueOf(each));
		}

		return list.toArray(new Integer[list.size()]);
	}

	private Answer processInput(Integer[] glassBins) {
		int min = -1;
		String bestSeq = null;
		Answer result = new Answer();
		for (String each : sequences) {
			int temp = findBestOrder(each, glassBins);
			if (temp < min || min == -1) {
				min = temp;
				bestSeq = each;
			}
		}
		result.setMinMoves(min);
		result.setBinsSequence(bestSeq);
		return result;
	}

	private int findBestOrder(String sequence, Integer[] glassBins) {
		int result = 0;
		for (int i=0; i<3; i++) {
			if (sequence.charAt(i) == 'B') {
				result += countBottles(0, i, glassBins);
			} else if (sequence.charAt(i) == 'G') {
				result += countBottles(1, i, glassBins);
			} else if (sequence.charAt(i) == 'C') {
				result += countBottles(2, i, glassBins);
			}
		}
		return result;
	}

	private int countBottles(int color, int index, Integer[] glassBins) {
		int result = 0;
		for (int i=0; i<3; i++) {
			if (index == i) {
				continue;
			}
			result += glassBins[colorMatrix[color][i]];
		}
		return result;
	}

}
