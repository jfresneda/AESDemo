package api;

import utilities.Functions;
import alternate.AESCore2;

/**
 * Class to test the implementation of AES with CBC.
 * 
 */
public class AESCBC {

	/**
	 * Encrypts an instantiated plaintext and decrypts the given ciphertext in
	 * order to obtain the plaintext with a given key.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		String plaintext = "0123456789ABCDEFFEDCBA9876543210ABCDEF0123456789";
		String ciphertext = "027C0E3D178DE82B26586C80D88AA9C4FEEE7BCDAE105CE527443B556338E559";
		String key = "0F1571C947D9E8590CB7ADD6AF7F6798"; // key to use when encrypting and decrypting.
		String IV = "000102030405060708090A0B0C0D0E0F";

		System.out.println(AESEncrypt(plaintext, key, IV));
		System.out.println();
		System.out.println(AESDecrypt(ciphertext, key, IV));
		System.exit(0);
	}

	/**
	 * Encrypts a given plaintext using given key
	 * 
	 * @param plaintext - input to be encrypted
	 * @param ekey - encryption key
	 * @param IV - initialization vector
	 * @return ciphertext
	 */
	public static String AESEncrypt(String plaintext, String ekey, String IV) {
		String ret = "";
		while (plaintext.length() % 32 != 0)
			plaintext += "0";

		ret += "---------------------------------------------------------------------------";
		ret += "\nEncryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nPlaintext";

		for (int i = 0; i < plaintext.length(); i += 32)
			ret += "\n" + plaintext.substring(i, i + 32);

		ret += "\n\nIV\n" + IV;
		ret += "\n\nKey\n" + ekey;
		ret += "\n\nCipherText";
		for (int i = 0; i < plaintext.length(); i += 32) {
			String ptext = Functions.xorWord(IV, plaintext.substring(i, i + 32));
			String[] eblock = AESCore2.encrypt(Functions.toArray(ptext),
					Functions.toArray(ekey));
			IV = eblock[eblock.length - 1];
			ret += "\n" + IV;
		}
		return ret;
	}

	/**
	 * Decrypts a given ciphertext using given key
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param dkey - decryption key
	 * @param IV - initialization vector
	 * @return plaintext
	 */
	public static String AESDecrypt(String ciphertext, String dkey, String IV) {
		String ret = "";
		while (ciphertext.length() % 32 != 0)
			ciphertext += "0";

		ret += "---------------------------------------------------------------------------";
		ret += "\nDecryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nCiphertext";
		for (int i = 0; i < ciphertext.length(); i += 32)
			ret += "\n" + ciphertext.substring(i, i + 32);

		ret += "\n\nIV\n" + IV;
		ret += "\n\nKey\n" + dkey;
		ret += "\n\nPlaintext";
		for (int i = 0; i < ciphertext.length(); i += 32) {
			String dtext = ciphertext.substring(i, i + 32);
			String[] dblock = AESCore2.decrypt(Functions.toArray(dtext), Functions.toArray(dkey));
			String result = Functions.xorWord(dblock[dblock.length - 1], IV);
			IV = dtext;
			ret += "\n" + result;
		}
		return ret;
	}
}
