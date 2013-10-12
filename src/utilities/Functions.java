package utilities;

/**
 * Class containing functions used in the different AES Implementations.
 * 
 */
public class Functions {
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Multiply a byte times 2, 3, 9, 11, 13, or 14 depending on method used.
	//
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Multiplies A times (02) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (02) in GF(2^8)
	 */
	public static String multiplyBy02(String A) {
		Integer op1 = Integer.valueOf(A, 16);
		Integer temp1 = op1 << 1;
		temp1 = temp1 & 0xFF;
		Integer temp2 = op1 & 0x80;
		if (temp2 != 0)
			temp1 = temp1 ^ 0x1B;
		String ret = Integer.toHexString(temp1).toUpperCase();
		if (ret.length() == 1)
			ret = "0" + ret;
		return ret;
	}

	/**
	 * Multiplies A times (03) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (03) in GF(2^8)
	 */
	public static String multiplyBy03(String A) {
		return xorByte(multiplyBy02(A), A);
	}

	/**
	 * Multiplies A times (09) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (09) in GF(2^8)
	 */
	public static String multiplyBy09(String A) {
		return xorByte(multiplyBy02(multiplyBy02(multiplyBy02(A))), A);
	}

	/**
	 * Multiplies A times (0B) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (0B) in GF(2^8)
	 */
	public static String multiplyBy0B(String A) {
		return xorByte(multiplyBy02(xorByte(multiplyBy02(multiplyBy02(A)), A)), A);
	}

	/**
	 * Multiplies A times (0D) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (0D) in GF(2^8)
	 */
	public static String multiplyBy0D(String A) {
		return xorByte(multiplyBy02(multiplyBy02(xorByte(multiplyBy02(A), A))), A);
	}

	/**
	 * Multiplies A times (0E) in GF(2^8)
	 * 
	 * @param A - Byte to be multiplied
	 * @return A times (0E) in GF(2^8)
	 */
	public static String multiplyBy0E(String A) {
		return multiplyBy02(xorByte(multiplyBy02(xorByte(multiplyBy02(A), A)),A));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Helper methods for substitution to retrieve the values the nibbles that make up byte A.
	//
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Helper method to use during substitution.
	 * 
	 * @param A - Byte to substitute
	 * @return decimal representation of the MSB of A
	 */
	public static int getXValue(String A) {
		return Integer.valueOf(String.valueOf(A.charAt(0)), 16);
	}

	/**
	 * Helper method to use during substitution.
	 * 
	 * @param A - Byte to substitute
	 * @return decimal representation of the LSB of A
	 */
	public static int getYValue(String A) {
		return Integer.valueOf(String.valueOf(A.charAt(1)), 16);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Helper methods to manipulate bytes and words.
	//
	///////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Bitwise XORs a pair of Bytes
	 * 
	 * @param A - Byte A
	 * @param B - Byte B
	 * @return A XOR B
	 */
	public static String xorByte(String A, String B) {
		Integer op1 = Integer.valueOf(A, 16);
		Integer op2 = Integer.valueOf(B, 16);
		String ret = Integer.toHexString(op1 ^ op2).toUpperCase();
		if (ret.length() == 1)
			ret = "0" + ret;
		return ret;
	}
	
	/**
	 * Bitwise xor of the given pair of words of the same length.
	 * 
	 * @param A - first input
	 * @param B - second input
	 * @return bitwise xor of A and B
	 */
	public static String xorWord(String A, String B) {
		String ret = "";
		for (int i = 0; i < A.length(); i += 2)
			ret += xorByte(A.substring(i, i + 2), B.substring(i, i + 2));
		return ret.toUpperCase();
	}

	/**
	 * Shifts Row to the left A by 1
	 * 
	 * @param A - row to be shifted to the left
	 * @return shifted row by 1
	 */
	public static String[] leftCircularShift(String[] A) {
		String[] result = new String[A.length];
		for (int i = 0; i < A.length - 1; i++)
			result[i] = A[i + 1];
		result[A.length - 1] = A[0];
		return result;
	}

	/**
	 * Shifts Row to the right A by 1
	 * 
	 * @param A - row to be shifted to the right
	 * @return shifted row by 1
	 */
	public static String[] rightCircularShift(String[] A) {
		String[] result = new String[A.length];
		for (int i = 1; i < A.length; i++)
			result[i] = A[i - 1];
		result[0] = A[A.length - 1];
		return result;
	}


	/**
	 * Circularly shift the word A by 2 Bytes
	 * 
	 * @param A - a word of 8 Bytes
	 * @return rotated A
	 */
	public static String rotateWord(String A) {
		return A.substring(2, A.length()) + A.substring(0, 2);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	//
	// Helper methods to transform the current state between a matrix and a String.
	//
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Returns a 2D nxn Array representation of A
	 * 
	 * @param A - String to transform into array
	 * @return - 2D array representation of A
	 */
	public static String[][] toArray(String A) {
		String[][] result = new String[4][4];
		int k = 0;
		for (int j = 0; j < result.length; j++)
			for (int i = 0; i < result.length; i++, k+=2) 
				result[i][j] = A.substring(k, k + 2);
		return result;
	}

	/**
	 * Returns a String representation of A where A is a 2D nxn Array
	 * 
	 * @param A - 2D array representation of A
	 * @return - String representation of A
	 */
	public static String toString(String[][] A) {
		String result = "";
		for (int j = 0; j < A.length; j++) 
			for (int i = 0; i < A.length; i++)
				result += A[i][j];
		return result;
	}
	


}
