package standard;

import utilities.Functions;

/**
 * This class provides the encryption and decryption methods for AES assuming 128bits.
 *  
 */
public class AESCore {

	/**
	 * Encrypts given plaintext.
	 * 
	 * @param state - plaintext to encrypt
	 * @param key - key to use during encryption
	 * @return ciphertext
	 */
	public static String[] encrypt(String[][] state, String[][] key) {
		String[] key_set = AESStages.expandKey(key);
		String[] result = new String[11];
		String[][] roundKey = Functions.toArray(key_set[0] + key_set[1] + key_set[2] + key_set[3]);
		String[][] temp = AESStages.addRoundKey(state, roundKey);
		result[0] = Functions.toString(temp);
		
		for (int i = 1; i < 10; i++) {
			temp = AESStages.subMatrix(temp);
			temp = AESStages.shiftRows(temp);
			temp = AESStages.mixColumns(temp);
			roundKey = Functions.toArray(key_set[4 * i] + key_set[4 * i + 1] + key_set[4 * i + 2] + key_set[4 * i + 3]);
			temp = AESStages.addRoundKey(temp, roundKey);
			result[i] = Functions.toString(temp);
		}
		
		temp = AESStages.subMatrix(temp);
		temp = AESStages.shiftRows(temp);
		roundKey = Functions.toArray(key_set[40] + key_set[41] + key_set[42] + key_set[43]);
		temp = AESStages.addRoundKey(temp, roundKey);
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
		String[] key_set = AESStages.expandKey(key);
		String[] result = new String[11];
		String[][] roundKey = Functions.toArray(key_set[40] + key_set[41] + key_set[42] + key_set[43]);
		String[][] temp = AESStages.addRoundKey(state, roundKey);
		int k = 40;
		result[0] = Functions.toString(temp);
		
		for (int i = 1; i < 10; i++, k-=4) {
			temp = AESStages.invShiftRows(temp);
			temp = AESStages.invSubMatrix(temp);
			roundKey = Functions.toArray(key_set[k - 4] + key_set[k - 3] + key_set[k - 2] + key_set[k - 1]);
			temp = AESStages.addRoundKey(temp, roundKey);
			temp = AESStages.invMixColumns(temp);
			result[i] = Functions.toString(temp);
		}
		
		temp = AESStages.invShiftRows(temp);
		temp = AESStages.invSubMatrix(temp);
		roundKey = Functions.toArray(key_set[0] + key_set[1] + key_set[2] + key_set[3]);
		temp = AESStages.addRoundKey(temp, roundKey);
		result[10] = Functions.toString(temp);
		return result;
	}
}
