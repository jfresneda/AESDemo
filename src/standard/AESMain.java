package standard;

import utilities.Functions;

/**
 * Class to test the implementation of AES using a 128-bit key.
 * 
 */
public class AESMain {

	/**
	 * Encrypts an instantiated plaintext and decrypts the given ciphertext in
	 * order to obtain the plaintext with a given key.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String plaintext = "0123456789ABCDEFFEDCBA9876543210"; // plaintext
		String ciphertext = "FF0B844A0853BF7C6934AB4364148FB9"; // ciphertext
		String key = "0F1571C947D9E8590CB7ADD6AF7F6798"; // Key to use when encrypting and decrypting.
		
		System.out.println(AESEncrypt(plaintext, key));
		System.out.println();
		System.out.println(AESDecrypt(ciphertext, key));
		System.exit(0);
	}
	
	/**
	 * Encrypts given plaintext
	 * @param plaintext - input to be encrypted.
	 * @param ekey - key to use during encryption
	 * @return ciphertext based on input
	 */
	public static String AESEncrypt(String plaintext, String ekey){
		// Encryption using given Plaintext and encryption key
		String ret = "---------------------------------------------------------------------------";
		ret += "\nEncryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nPlaintext";
		ret += "\n" + plaintext;
		ret += "\n\nKey\n"+ ekey;
		ret += "\n\nCiphertext";
		String[][] plainMatrix = Functions.toArray(plaintext);
		String[][] keyMatrix = Functions.toArray(ekey);
		String[] temp2 = AESCore.encrypt(plainMatrix, keyMatrix);		
		ret += "\n"+temp2[temp2.length - 1];
		return ret;
	}
	
	/**
	 * Decrypts given ciphertext.
	 * @param ciphertext - input to be decrypted.
	 * @param dkey - key to use during decryption.
	 * @return plaintext based on input
	 */
	public static String AESDecrypt(String ciphertext, String dkey){
		// Decryption using given Cyphertext and decryption key
		String ret = "---------------------------------------------------------------------------";
		ret += "\nDecryption";
		ret += "\n---------------------------------------------------------------------------";
		ret += "\nCiphertext";
		ret += "\n" + ciphertext;
		ret += "\n\nKey\n"+ dkey;
		ret += "\n\nPlaintext";
		String[][] cipherMatrix = Functions.toArray(ciphertext);
		String[][] keyMatrix = Functions.toArray(dkey);
		String[] temp2 = AESCore.decrypt(cipherMatrix, keyMatrix);
		ret += "\n"+temp2[temp2.length - 1];
		return ret;
	}
}
