package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import standard.AESStages;

public class AddRoundKeyTest {

	String[][] state;
	String[][] round_key;
	String[][] result;

	@Before
	public void setUp() throws Exception {
		state = new String[][] { 
				{ "07", "40", "A3", "4C" },
				{ "37", "D4", "70", "9F" }, 
				{ "94", "E4", "3A", "42" },
				{ "ED", "A5", "A6", "BC" } };
		round_key = new String[][] { 
				{ "0C", "19", "28", "57" },
				{ "77", "FA", "D1", "5C" }, 
				{ "66", "DC", "29", "00" },
				{ "F3", "21", "41", "6A" } };
		result = new String[][] { 
				{ "0B", "59", "8B", "1B" },
				{ "40", "2E", "A1", "C3" }, 
				{ "F2", "38", "13", "42" },
				{ "1E", "84", "E7", "D6" } };
	}

	@Test
	public void test() {
		boolean correct = true;
		String[][] ret = AESStages.addRoundKey(state, round_key);
		for (int i = 0; i < result.length; i++)
			for (int j = 0; j < result.length; j++)
				correct = correct && (result[i][j].equalsIgnoreCase(ret[i][j]));
		assertTrue(correct);
	}
}
