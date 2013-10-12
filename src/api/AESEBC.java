package api;

import utilities.Functions;
import alternate.AESCore2;

/**
 * Class to test the implementation of AES with EBC.
 * 
 */
public class AESEBC {

	/**
	 * Encrypts an instantiated plaintext and decrypts the given ciphertext in
	 * order to obtain the plaintext with a given key.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {

		String plaintext = "0123456789ABCDEFFEDCBA9876543210ABCDEF0123456789";
		String ciphertext = "FF0B844A0853BF7C6934AB4364148FB9FC38B8E2D3DB93813A44FDADE8B5851C";
		String key = "0F1571C947D9E8590CB7ADD6AF7F6798";

		// Key to use when encrypting and decrypting.

		System.out.println(AESEncrypt(plaintext, key));
		System.out.println();
		System.out.println(AESDecrypt(ciphertext, key));
		System.exit(0);
	}

	/**
	 * Encrypts a given plaintext using given key
	 * 
	 * @param plaintext - input to be encrypted
	 * @param ekey - encryption key = * @return ciphertext
	 */
	public static String AESEncrypt(String plaintext, String ekey) {
		String ret = "";
		while (plaintext.length() % 32 != 0)
			plaintext += "0";
		ret += "---------------------------------------------------------------------------";
		ret += "\nEncryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nPlaintext";
		for (int i = 0; i < plaintext.length(); i += 32)
			ret += "\n" + plaintext.substring(i, i + 32);

		ret += "\n\nKey\n" + ekey;
		ret += "\n\nCipherText";
		for (int i = 0; i < plaintext.length(); i += 32) {
			String state = plaintext.substring(i, i + 32);
			String[] eblock = AESCore2.encrypt(Functions.toArray(state), Functions.toArray(ekey));
			ret += "\n" + eblock[eblock.length - 1];
		}
		return ret;
	}

	/**
	 * Decrypts a given ciphertext using given key
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param dkey - decryption key
	 * @return plaintext
	 */
	public static String AESDecrypt(String ciphertext, String dkey) {
		String ret = "";
		while (ciphertext.length() % 32 != 0)
			ciphertext += "0";
		ret += "---------------------------------------------------------------------------";
		ret += "\nDecryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nCiphertext";
		for (int i = 0; i < ciphertext.length(); i += 32)
			ret += "\n" + ciphertext.substring(i, i + 32);

		ret += "\n\nKey\n" + dkey;
		ret += "\n\nPlaintext";
		for (int i = 0; i < ciphertext.length(); i += 32) {
			String state = ciphertext.substring(i, i + 32);
			String[] dblock = AESCore2.decrypt(Functions.toArray(state), Functions.toArray(dkey));
			ret += "\n" + dblock[dblock.length - 1];
		}
		return ret;
	}
}
