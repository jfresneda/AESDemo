AES Demo README
--------------

    author:	    Janoxe  
    date:		10/11/2013  
    project: 	AES Java Implementation  
	link:    	https://github.com/Janoxe/AESDemo


This is the README for a Java implementation of the Advanced Encryption Standard for a course in Cryptography and Network Security. As such, the README assumes that the user has partial technical knowledge in order to run the program. For more information on AES, view and research the provided keywords on the following section if needed. 

The project consisted on four AES implementations, one where values are calculated on execution time,  a second where the values are on previously generated lookup tables, an EBC operating mode, and a CBC mode.

The user interface should facilitate operation and comes with this updated README to guide the user. The interface for the demonstration was implemented after the course was finished due to time constraints so, from a programmer's view, there are various ways to utilize and run the given code. 

KEYWORDS
-------------------------

    Advanced Encryption Standard	
    Cipher-block Chaining	  	Encryption	
    Ciphertext			     	Initialization Vector	
    Decryption			     	Key	
    Electronic Codebook			Plaintext	

FILE MANIFEST
-------------------------------

	AESDemo.jar  
	README.txt  
	README.md  
 
CODE
------------

    AddRoundKeyTest.java	AESMain.java		IOHandler.java	
    AESController.java  	AESMain2.java   	JTextFieldCharLimit.java
    AESCBC.java 			AESModel.java   	MixColumnTest.java	
    AESCore.java			AESStages.java  	README.txt
    AESCore2.java   		AESStages2.java 	ShiftRowsTest.java	
    AESDemo.java			AESView.java		ShowCaretOnFocus.java	
    AESEBC.java 			Functions.java  	SubMatrixTest.java

SETUP INSTRUCTIONS
------------

1. Install the Java runtime environment if not installed.

2. Double click on the jar file provided.

OPERATING INSTRUCTIONS
------------

1. Select a configuration from the list by clicking on desired option:  
AES 128-bit Standard - Implementation of AES where values are calculated as needed.  
AES 128-bit Lookup Table - Implementation of AES where everything is read from four previously   generated tables.  
AES EBC Mode - Electronic codebook implementation of AES.  
AES CBC Mode - Cipher-block chaining implementation of AES.  

2. Select operation to perform by clicking on one of the radio buttons:  
Example - Runs a predefined example of encryption and decryption of selected configuration.  
Encrypt - Encrypts given plaintext using the needed input.  
Decrypt - Decrypts given ciphertext using the required input.  

3. Enter required input, explained in detail on the following sub-section.

4. Press Submit button to execute the selected operation.

5. View the operation outputs in the output text area.

INPUT SPECIFICATIONS
----------------------

1. Required input specifications:    

  Before explaining the required inputs, it must be noted that a valid hexadecimal number must be entered in any input used.
  
  The AES 128-bit configurations need a 32 hex-digit key and plaintext as input. The key must be entered in the Key labeled 
  input field. The plaintext must be entered in the large text area labeled as Input. 
  
  The AES EBC mode needs 32 hex-digit key and at least a 1 hex-digit or more long plaintext as input. 
  The key must be entered in the Key labeled input field. The plaintext must be entered in the large text area labeled as Input. 
  
  The AES CBC mode needs 32 hex-digit Key and IV, and at least a 1 hex-digit or more long plaintext as input. 
  The key  must be entered in the Key labeled input field, while the iv must be entered in the IV labeled input field.
  The plaintext must be entered in the large text area labeled as Input. 

2. Opening a File:  

  This implementation is able to use a text file as input. The file is interpreted depending on the previously selected options.
  The file will then be used to populate the various fields depending on selected configuration. The actual text will not be 
  validated until the inputs are submitted by using the Submit button.
  
  If AES CBC Mode is selected, the file should have the key in its first line without linebreaks and  the iv in the second line
  followed by the input text, to be used as plaintext or ciphertext depending if encrypted or decrypted, in the subsequent lines.
  
  For the other configurations, the file is expected to have the key in the first line of text followed by the input text, to be 
  used as plaintext or ciphertext depending if encrypted or decrypted, in the subsequent lines.


CONTACT INFORMATION
-------------

For any questions, contact me through twitter [@janoxepr](https://twitter.com/janoxepr).
