package standard;

import utilities.Functions;

/**
 * Class providing the basic stages for AES encryption and decryption.
 *
 */
public class AESStages {

	/**
	 * Table lookup for substitution.
	 */
	public static final String[][] s_box = {
			{ "63", "7C", "77", "7B", "F2", "6B", "6F", "C5", "30", "01", "67", "2B", "FE", "D7", "AB", "76" },
			{ "CA", "82", "C9", "7D", "FA", "59", "47", "F0", "AD", "D4", "A2", "AF", "9C", "A4", "72", "C0" },
			{ "B7", "FD", "93", "26", "36", "3F", "F7", "CC", "34", "A5", "E5", "F1", "71", "D8", "31", "15" },
			{ "04", "C7", "23", "C3", "18", "96", "05", "9A", "07", "12", "80", "E2", "EB", "27", "B2", "75" },
			{ "09", "83", "2C", "1A", "1B", "6E", "5A", "A0", "52", "3B", "D6", "B3", "29", "E3", "2F", "84" },
			{ "53", "D1", "00", "ED", "20", "FC", "B1", "5B", "6A", "CB", "BE", "39", "4A", "4C", "58", "CF" },
			{ "D0", "EF", "AA", "FB", "43", "4D", "33", "85", "45", "F9", "02", "7F", "50", "3C", "9F", "A8" },
			{ "51", "A3", "40", "8F", "92", "9D", "38", "F5", "BC", "B6", "DA", "21", "10", "FF", "F3", "D2" },
			{ "CD", "0C", "13", "EC", "5F", "97", "44", "17", "C4", "A7", "7E", "3D", "64", "5D", "19", "73" },
			{ "60", "81", "4F", "DC", "22", "2A", "90", "88", "46", "EE", "B8", "14", "DE", "5E", "0B", "DB" },
			{ "E0", "32", "3A", "0A", "49", "06", "24", "5C", "C2", "D3", "AC", "62", "91", "95", "E4", "79" },
			{ "E7", "C8", "37", "6D", "8D", "D5", "4E", "A9", "6C", "56", "F4", "EA", "65", "7A", "AE", "08" },
			{ "BA", "78", "25", "2E", "1C", "A6", "B4", "C6", "E8", "DD", "74", "1F", "4B", "BD", "8B", "8A" },
			{ "70", "3E", "B5", "66", "48", "03", "F6", "0E", "61", "35", "57", "B9", "86", "C1", "1D", "9E" },
			{ "E1", "F8", "98", "11", "69", "D9", "8E", "94", "9B", "1E", "87", "E9", "CE", "55", "28", "DF" },
			{ "8C", "A1", "89", "0D", "BF", "E6", "42", "68", "41", "99", "2D", "0F", "B0", "54", "BB", "16" } };

	/**
	 * Table lookup for inverting the substitution.
	 */
	public static final String[][] i_s_box = {
			{ "52", "09", "6A", "D5", "30", "36", "A5", "38", "BF", "40", "A3", "9E", "81", "F3", "D7", "FB" },
			{ "7C", "E3", "39", "82", "9B", "2F", "FF", "87", "34", "8E", "43", "44", "C4", "DE", "E9", "CB" },
			{ "54", "7B", "94", "32", "A6", "C2", "23", "3D", "EE", "4C", "95", "0B", "42", "FA", "C3", "4E" },
			{ "08", "2E", "A1", "66", "28", "D9", "24", "B2", "76", "5B", "A2", "49", "6D", "8B", "D1", "25" },
			{ "72", "F8", "F6", "64", "86", "68", "98", "16", "D4", "A4", "5C", "CC", "5D", "65", "B6", "92" },
			{ "6C", "70", "48", "50", "FD", "ED", "B9", "DA", "5E", "15", "46", "57", "A7", "8D", "9D", "84" },
			{ "90", "D8", "AB", "00", "8C", "BC", "D3", "0A", "F7", "E4", "58", "05", "B8", "B3", "45", "06" },
			{ "D0", "2C", "1E", "8F", "CA", "3F", "0F", "02", "C1", "AF", "BD", "03", "01", "13", "8A", "6B" },
			{ "3A", "91", "11", "41", "4F", "67", "DC", "EA", "97", "F2", "CF", "CE", "F0", "B4", "E6", "73" },
			{ "96", "AC", "74", "22", "E7", "AD", "35", "85", "E2", "F9", "37", "E8", "1C", "75", "DF", "6E" },
			{ "47", "F1", "1A", "71", "1D", "29", "C5", "89", "6F", "B7", "62", "0E", "AA", "18", "BE", "1B" },
			{ "FC", "56", "3E", "4B", "C6", "D2", "79", "20", "9A", "DB", "C0", "FE", "78", "CD", "5A", "F4" },
			{ "1F", "DD", "A8", "33", "88", "07", "C7", "31", "B1", "12", "10", "59", "27", "80", "EC", "5F" },
			{ "60", "51", "7F", "A9", "19", "B5", "4A", "0D", "2D", "E5", "7A", "9F", "93", "C9", "9C", "EF" },
			{ "A0", "E0", "3B", "4D", "AE", "2A", "F5", "B0", "C8", "EB", "BB", "3C", "83", "53", "99", "61" },
			{ "17", "2B", "04", "7E", "BA", "77", "D6", "26", "E1", "69", "14", "63", "55", "21", "0C", "7D" } };

	/**
	 * Table lookup for the round constant.
	 */
	public static final String[] round_constant = { "8d", "01", "02", "04",
			"08", "10", "20", "40", "80", "1b", "36", "6c", "d8", "ab", "4d",
			"9a", "2f", "5e", "bc", "63", "c6", "97", "35", "6a", "d4", "b3",
			"7d", "fa", "ef", "c5", "91", "39", "72", "e4", "d3", "bd", "61",
			"c2", "9f", "25", "4a", "94", "33", "66", "cc", "83", "1d", "3a",
			"74", "e8", "cb", "8d", "01", "02", "04", "08", "10", "20", "40",
			"80", "1b", "36", "6c", "d8", "ab", "4d", "9a", "2f", "5e", "bc",
			"63", "c6", "97", "35", "6a", "d4", "b3", "7d", "fa", "ef", "c5",
			"91", "39", "72", "e4", "d3", "bd", "61", "c2", "9f", "25", "4a",
			"94", "33", "66", "cc", "83", "1d", "3a", "74", "e8", "cb", "8d",
			"01", "02", "04", "08", "10", "20", "40", "80", "1b", "36", "6c",
			"d8", "ab", "4d", "9a", "2f", "5e", "bc", "63", "c6", "97", "35",
			"6a", "d4", "b3", "7d", "fa", "ef", "c5", "91", "39", "72", "e4",
			"d3", "bd", "61", "c2", "9f", "25", "4a", "94", "33", "66", "cc",
			"83", "1d", "3a", "74", "e8", "cb", "8d", "01", "02", "04", "08",
			"10", "20", "40", "80", "1b", "36", "6c", "d8", "ab", "4d", "9a",
			"2f", "5e", "bc", "63", "c6", "97", "35", "6a", "d4", "b3", "7d",
			"fa", "ef", "c5", "91", "39", "72", "e4", "d3", "bd", "61", "c2",
			"9f", "25", "4a", "94", "33", "66", "cc", "83", "1d", "3a", "74",
			"e8", "cb", "8d", "01", "02", "04", "08", "10", "20", "40", "80",
			"1b", "36", "6c", "d8", "ab", "4d", "9a", "2f", "5e", "bc", "63",
			"c6", "97", "35", "6a", "d4", "b3", "7d", "fa", "ef", "c5", "91",
			"39", "72", "e4", "d3", "bd", "61", "c2", "9f", "25", "4a", "94",
			"33", "66", "cc", "83", "1d", "3a", "74", "e8", "cb", "8d" };

	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Encryption Stage 1 - Substitution 
	//
	////////////////////////////////////////////////////////////////////////////////
		
	/**
	 * Performs the substitution for the given text.
	 * 
	 * @param state - text to substitute
	 * @return 2D array with substituted text
	 */
	public static String[][] subMatrix(String[][] state) {
		String[][] result = new String[state.length][state.length];
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				result[i][j] = subByte(state[i][j]);
		return result;
	}
	
	/**
	 * Get the substitution for a byte
	 * 
	 * @param A - byte to be substituted
	 * @return String with the substituted Byte
	 */
	public static String subByte(String A) {
		return s_box[Functions.getXValue(A)][Functions.getYValue(A)];
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Encryption Stage 2 - Shift Rows
	//
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Shift the rows of a given text.
	 * 
	 * @param state - text to be shifted
	 * @return 2D array with shifted text
	 */
	public static String[][] shiftRows(String[][] state) {
		String[][] result = new String[state.length][state.length];
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				result[i][j] = state[i][j];
		for (int i = 1; i < state.length; i++)
			for (int j = i; j > 0; j--)
				result[i] = Functions.leftCircularShift(result[i]);
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////
	//
	// Encryption Stage 3 - Mix Columns
	//
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Mixes the columns of the given text.
	 * 
	 * @param state - text to be mixed
	 * @return 2D array with mixed text
	 */
	public static String[][] mixColumns(String[][] state) {
		String[][] result = new String[state.length][state.length];
		String A, B, C, D;
		for (int j = 0; j < state.length; j++) {
			A = Functions.multiplyBy02(state[0][j]);
			B = Functions.multiplyBy03(state[1][j]);
			C = state[2][j];
			D = state[3][j];
			result[0][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy02(state[1][j]);
			B = Functions.multiplyBy03(state[2][j]);
			C = state[0][j];
			D = state[3][j];
			result[1][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy02(state[2][j]);
			B = Functions.multiplyBy03(state[3][j]);
			C = state[0][j];
			D = state[1][j];
			result[2][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy02(state[3][j]);
			B = Functions.multiplyBy03(state[0][j]);
			C = state[1][j];
			D = state[2][j];
			result[3][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);
		}
		return result;
	}

	////////////////////////////////////////////////////////////////////////////////
	//
	// Decryption Stage 1 - Inverse Substitution 
	//
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Undoes the substitution for the given text.
	 * 
	 * @param state - text to undo the substitution
	 * @return 2D array with original text
	 */
	public static String[][] invSubMatrix(String[][] state) {
		String[][] result = new String[state.length][state.length];
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				result[i][j] = invSubByte(state[i][j]);
		return result;
	}
	
	/**
	 * Undo the substitution for a byte
	 * 
	 * @param A - byte to be recovered
	 * @return String with the original Byte
	 */
	public static String invSubByte(String A) {
		return i_s_box[Functions.getXValue(A)][Functions.getYValue(A)];
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Decryption Stage 2 - Inverse Shift Rows
	//
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Shift the rows of a given text.
	 * 
	 * @param state - text to undo the shifting
	 * @return 2D array with original text
	 */
	public static String[][] invShiftRows(String[][] state) {
		String[][] result = new String[state.length][state.length];
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				result[i][j] = state[i][j];
		for (int i = 1; i < state.length; i++)
			for (int j = i; j > 0; j--)
				result[i] = Functions.rightCircularShift(result[i]);
		return result;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Decryption Stage 3 - Inverse Mix Columns
	//
	////////////////////////////////////////////////////////////////////////////////

	/**
	 * Undoes the column mixing of a given text.
	 * 
	 * @param state - text to undo the mixing in
	 * @return 2D array with original text
	 */
	public static String[][] invMixColumns(String[][] state) {
		String[][] result = new String[state.length][state.length];
		String A, B, C, D;
		for (int j = 0; j < state.length; j++) {
			A = Functions.multiplyBy0E(state[0][j]);
			B = Functions.multiplyBy0B(state[1][j]);
			C = Functions.multiplyBy0D(state[2][j]);
			D = Functions.multiplyBy09(state[3][j]);
			result[0][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy09(state[0][j]);
			B = Functions.multiplyBy0E(state[1][j]);
			C = Functions.multiplyBy0B(state[2][j]);
			D = Functions.multiplyBy0D(state[3][j]);
			result[1][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy0D(state[0][j]);
			B = Functions.multiplyBy09(state[1][j]);
			C = Functions.multiplyBy0E(state[2][j]);
			D = Functions.multiplyBy0B(state[3][j]);
			result[2][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);

			A = Functions.multiplyBy0B(state[0][j]);
			B = Functions.multiplyBy0D(state[1][j]);
			C = Functions.multiplyBy09(state[2][j]);
			D = Functions.multiplyBy0E(state[3][j]);
			result[3][j] = Functions.xorByte(Functions.xorByte(Functions.xorByte(A, B), C), D);
		}
		return result;
	}
	
	////////////////////////////////////////////////////////////////////////////////
	//
	// Final Stage - Add Round Key
	//
	////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Add (XOR) Round Key step for AES.
	 * 
	 * @param state - current state
	 * @param round_key - key to be added
	 * @return 2D array with the result.
	 */
	public static String[][] addRoundKey(String[][] state, String[][] round_key) {
		String[][] result = new String[state.length][state.length];
		for (int i = 0; i < state.length; i++)
			for (int j = 0; j < state.length; j++)
				result[i][j] = Functions.xorByte(state[i][j], round_key[i][j]);
		return result;
	}

	/**
	 * Expands the given key.
	 * 
	 * @param key - key to be expanded
	 * @return Array of the expansions of the key
	 */
	public static String[] expandKey(String[][] key) {
		String[] w = new String[44];
		String keyS = Functions.toString(key);
		for (int i = 0; i < 4; i++)
			w[i] = keyS.substring(8 * i, 8 * i + 8);
		for (int i = 4; i < w.length; i++) {
			String temp = w[i - 1];
			if (i % 4 == 0)
				temp = Functions.xorWord(subWord(Functions.rotateWord(temp)), getRoundConstant(i));
			w[i] = Functions.xorWord(w[i - 4], temp);
		}
		return w;
	}
	
	/**
	 * Passes a word A through the SBox
	 * 
	 * @param A - input
	 * @return Byte-wise substitution of A.
	 */
	private static String subWord(String A) {
		String ret = "";
		for (int i = 0; i < A.length(); i += 2) 
			ret += subByte(A.substring(i, i + 2));
		return ret;
	}

	/**
	 * Get the round constant for AES round.
	 * 
	 * @param round - current round
	 * @return String with the round constant needed padding to have 8 Bytes
	 */
	private static String getRoundConstant(int round) {
		return round_constant[round / 4] + "000000";
	}
}
