package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import standard.AESStages;

public class MixColumnsTest {

	String[][] state;
	String[][] result;

	@Before
	public void setUp() throws Exception {
		state = new String[][] { 
				{ "87", "F2", "4D", "97" }, 
				{ "6E", "4C", "90", "EC" }, 
				{ "46", "E7", "4A", "C3" },
				{ "A6", "8C", "D8", "95" } };

		result = new String[][] { 
				{ "47", "40", "A3", "4C" }, 
				{ "37", "D4", "70", "9F" }, 
				{ "94", "E4", "3A", "42" },
				{ "ED", "A5", "A6", "BC" } };
	}

	@Test
	public void test() {
		boolean correct = true;
		String[][] ret1 = AESStages.mixColumns(state);
		String[][] ret2 = AESStages.invMixColumns(result);
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result.length; j++) {
				correct = correct && (result[i][j].equalsIgnoreCase(ret1[i][j]));
				correct = correct && (state[i][j].equalsIgnoreCase(ret2[i][j]));
			}
		assertTrue(correct);
	}

}
