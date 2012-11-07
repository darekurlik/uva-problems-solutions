package com.daur.uva.engine;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class GlassSorterTest {

	private GlassSorter glassSorter = new GlassSorter();

	@DataProvider
	public Object[][] prepareData() {
		return new Object[][] {
			{ "1 2 3 4 5 6 7 8 9", 30, "BCG" },
			{ "5 10 5 20 10 5 10 20 10", 50, "CBG" },
			{ "5 10 5   20 10 5   10 20 10", 50, "CBG" }
		};
	}

	@Test(dataProvider="prepareData")
	public void findBestCombination(String glassBins, int expectedMovesCount, String expectedSequence) {
		Answer answer = glassSorter.sortGlass(glassBins);
		int actualMovesCount = answer.getMinMoves();
		String actualSequence = answer.getBinsSequence();

		assertEquals(actualMovesCount, expectedMovesCount);
		assertEquals(actualSequence, expectedSequence);
	}

}
