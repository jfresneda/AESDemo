package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import standard.AESStages;

public class SubMatrixTest {

	String[][] state;
	String[][] result;

	@Before
	public void setUp() throws Exception {
		state = new String[][] { 
				{ "EA", "04", "65", "85" },
				{ "83", "45", "5D", "96" }, 
				{ "5C", "33", "98", "B0" },
				{ "F0", "2D", "AD", "C5" } };

		result = new String[][] { 
				{ "87", "F2", "4D", "97" },
				{ "EC", "6E", "4C", "90" }, 
				{ "4A", "C3", "46", "E7" },
				{ "8C", "D8", "95", "A6" } };
	}

	@Test
	public void test() {
		boolean correct = true;
		String[][] ret1 = AESStages.subMatrix(state);
		String[][] ret2 = AESStages.invSubMatrix(result);
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result.length; j++) {
				correct = correct && (result[i][j].equalsIgnoreCase(ret1[i][j]));
				correct = correct && (state[i][j].equalsIgnoreCase(ret2[i][j]));
			}
		assertTrue(correct);
	}
}
