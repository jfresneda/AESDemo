package alternate;

import utilities.Functions;

/**
 * This class provides the encryption and decryption methods for AES assuming
 * 128bits using 4 extra tables.
 * 
 */
public class AESCore2 {

	/**
	 * Encrypts given plaintext.
	 * 
	 * @param state - plaintext to encrypt
	 * @param key - key to use during encryption
	 * @return ciphertext
	 */
	public static String[] encrypt(String[][] state, String[][] key) {
		String[] key_set = AESStages2.expandKey(key);
		String[] result = new String[11];
		String roundKey = key_set[0] + key_set[1] + key_set[2] + key_set[3];
		String[][] temp = AESStages2.addRoundKey( state, Functions.toArray(roundKey));
		
		result[0] = Functions.toString(temp);

		for (int i = 1; i < 10; i++) {
			roundKey = key_set[4 * i] + key_set[4 * i + 1] + key_set[4 * i + 2] + key_set[4 * i + 3];
			String proc = AESStages2.xorALL(temp[0][0], temp[1][1], temp[2][2], temp[3][3], roundKey.substring(0, 8));
			proc = proc + AESStages2.xorALL(temp[0][1], temp[1][2], temp[2][3], temp[3][0], roundKey.substring(8, 16));
			proc = proc + AESStages2.xorALL(temp[0][2], temp[1][3], temp[2][0], temp[3][1], roundKey.substring(16, 24));
			proc = proc + AESStages2.xorALL(temp[0][3], temp[1][0], temp[2][1], temp[3][2], roundKey.substring(24, 32));
			temp = Functions.toArray(proc);
			result[i] = Functions.toString(temp);
		}
		temp = AESStages2.subMatrix(temp);
		temp = AESStages2.shiftRows(temp);
		roundKey = key_set[40] + key_set[41] + key_set[42] + key_set[43];
		temp = AESStages2.addRoundKey( temp, Functions.toArray(roundKey));
		result[10] = Functions.toString(temp);

		return result;
	}

	/**
	 * Decrypts given ciphertext.
	 * 
	 * @param state - ciphertext to decrypt
	 * @param key - key to use during decryption
	 * @return original plaintext
	 */
	public static String[] decrypt(String[][] state, String[][] key) {
		String[] key_set = AESStages2.expandKey(key);
		String[] result = new String[11];
		String roundKey = key_set[40] + key_set[41] + key_set[42] + key_set[43];
		String[][] temp = AESStages2.addRoundKey(state, Functions.toArray(roundKey));
		int k = 40;

		result[0] = Functions.toString(temp);

		for (int i = 1; i < 10; i++, k-=4) {
			roundKey = key_set[k - 4] + key_set[k - 3] + key_set[k - 2] + key_set[k - 1];
			roundKey = Functions.toString(AESStages2.invMixColumns(Functions .toArray(roundKey)));
			String tempI = AESStages2.xorALLPrime(temp[0][0], temp[1][3], temp[2][2], temp[3][1], roundKey.substring(0, 8));
			tempI = tempI + AESStages2.xorALLPrime(temp[0][1], temp[1][0], temp[2][3], temp[3][2], roundKey.substring(8, 16));
			tempI = tempI + AESStages2.xorALLPrime(temp[0][2], temp[1][1], temp[2][0], temp[3][3], roundKey.substring(16, 24));
			tempI = tempI + AESStages2.xorALLPrime(temp[0][3], temp[1][2], temp[2][1], temp[3][0], roundKey.substring(24, 32));
			temp = Functions.toArray(tempI);
			result[i] = Functions.toString(temp);
		}
		temp = AESStages2.invShiftRows(temp);
		temp = AESStages2.invSubMatrix(temp);
		roundKey = key_set[0] + key_set[1] + key_set[2] + key_set[3];
		temp = AESStages2.addRoundKey( temp, Functions.toArray(roundKey));
		result[10] = Functions.toString(temp);

		return result;
	}

}
