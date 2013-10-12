package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import standard.AESStages;

public class ShiftRowsTest {
	String[][] state;
	String[][] result;

	@Before
	public void setUp() throws Exception {

		state = new String[][] { 
				{ "87", "F2", "4D", "97" },
				{ "EC", "6E", "4C", "90" }, 
				{ "4A", "C3", "46", "E7" },
				{ "8C", "D8", "95", "A6" } };

		result = new String[][] { 
				{ "87", "F2", "4D", "97" },
				{ "6E", "4C", "90", "EC" }, 
				{ "46", "E7", "4A", "C3" },
				{ "A6", "8C", "D8", "95" } };
	}

	@Test
	public void test() {
		boolean correct = true;
		String[][] ret1 = AESStages.shiftRows(state);
		String[][] ret2 = AESStages.invShiftRows(result);
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result.length; j++) {
				correct = correct && (result[i][j].equalsIgnoreCase(ret1[i][j]));
				correct = correct && (state[i][j].equalsIgnoreCase(ret2[i][j]));
			}
		assertTrue(correct);
	}
}
