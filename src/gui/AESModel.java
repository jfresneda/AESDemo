package gui;

import standard.AESMain;
import alternate.AESMain2;
import api.AESCBC;
import api.AESEBC;

public class AESModel {

	/**
	 * Example key for AES Demo.
	 */
	private final String key = "0F1571C947D9E8590CB7ADD6AF7F6798";

	/**
	 * Example Initialization Vector for AES Demo.
	 */
	private final String IV = "000102030405060708090A0B0C0D0E0F";

	/**
	 * Example plaintext for AES Demo.
	 */
	private final String plaintext = "0123456789ABCDEFFEDCBA9876543210";

	/**
	 * Example plaintext for block operations for AES Demo.
	 */
	private final String blockPlaintext = "0123456789ABCDEFFEDCBA9876543210ABCDEF0123456789";

	/**
	 * Example ciphertext for AES Demo.
	 */
	private final String ciphertext = "FF0B844A0853BF7C6934AB4364148FB9";

	/**
	 * Example ciphertext for EBC block operations for AES Demo.
	 */
	private final String ebcCiphertext = "FF0B844A0853BF7C6934AB4364148FB9FC38B8E2D3DB93813A44FDADE8B5851C";

	/**
	 * Example ciphertext for CBC block operations for AES Demo.
	 */
	private final String cbcCiphertext = "027C0E3D178DE82B26586C80D88AA9C4FEEE7BCDAE105CE527443B556338E559";

	/**
	 * AES Standard Encryption and Decryption
	 * 
	 * @return encryption and decryption of example input
	 */
	public String standardExample() {
		String ret = AESMain.AESEncrypt(plaintext, key);
		ret += "\n\n" + AESMain.AESDecrypt(ciphertext, key);
		return ret;
	}

	/**
	 * AES Lookup Table Encryption and Decryption
	 * 
	 * @return encryption and decryption of example input
	 */
	public String tableExample() {
		String ret = AESMain2.AESEncrypt(plaintext, key);
		ret += "\n\n" + AESMain2.AESDecrypt(ciphertext, key);
		return ret;
	}

	/**
	 * AES EBC Mode Encryption and Decryption
	 * 
	 * @return encryption and decryption of example input
	 */
	public String ebcExample() {
		String ret = AESEBC.AESEncrypt(blockPlaintext, key);
		ret += "\n\n" + AESEBC.AESDecrypt(ebcCiphertext, key);
		return ret;
	}

	/**
	 * AES CBC Mode Encryption and Decryption
	 * 
	 * @return encryption and decryption of example input
	 */
	public String cbcExample() {
		String ret = AESCBC.AESEncrypt(blockPlaintext, key, IV);
		ret += "\n\n" + AESCBC.AESDecrypt(cbcCiphertext, key, IV);
		return ret;
	}

	/**
	 * AES Standard Encryption
	 * 
	 * @param plaintext - input to be encrypted
	 * @param key - encryption key
	 * @return Encryption of user input
	 */
	public String standardEncrypt(String plaintext, String key) {
		return AESMain.AESEncrypt(plaintext, key);
	}

	/**
	 * AES Standard Decryption
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param key - decryption key
	 * @return Decryption of user input
	 */
	public String standardDecrypt(String ciphertext, String key) {
		return AESMain.AESDecrypt(ciphertext, key);
	}

	/**
	 * AES Table Lookup Encryption
	 * 
	 * @param plaintext - input to be encrypted
	 * @param key - encryption key
	 * @return Encryption of user input
	 */
	public String tableEncrypt(String plaintext, String key) {
		return AESMain2.AESEncrypt(plaintext, key);
	}

	/**
	 * AES Table Lookup Decryption
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param key - decryption key
	 * @return Decryption of user input
	 */
	public String tableDecrypt(String ciphertext, String key) {
		return AESMain2.AESDecrypt(ciphertext, key);
	}

	/**
	 * AES EBC Mode Encryption
	 * 
	 * @param plaintext - input to be encrypted
	 * @param key - encryption key
	 * @return Encryption of user input
	 */
	public String ebcEncrypt(String plaintext, String key) {
		return AESEBC.AESEncrypt(plaintext, key);
	}

	/**
	 * AES EBC Mode Decryption
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param key - decryption key
	 * @return Decryption of user input
	 */
	public String ebcDecrypt(String ciphertext, String key) {
		return AESEBC.AESDecrypt(ciphertext, key);
	}

	/**
	 * AES CBC Mode Encryption
	 * 
	 * @param plaintext - input to be encrypted
	 * @param key - encryption key
	 * @param IV - initialization vector
	 * @return Encryption of user input
	 */
	public String cbcEncrypt(String plaintext, String key, String IV) {
		return AESCBC.AESEncrypt(plaintext, key, IV);
	}

	/**
	 * AES CBC Mode Decryption
	 * 
	 * @param ciphertext - input to be decrypted
	 * @param key - decryption key
	 * @param IV - initialization vector
	 * @return Decryption of user input
	 */
	public String cbcDecrypt(String ciphertext, String key, String IV) {
		return AESCBC.AESDecrypt(ciphertext, key, IV);
	}
}
