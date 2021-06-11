package com.nagarro.javaTest.javaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.javaTest.helper.EncryptionDecryption;

@RunWith(SpringRunner.class)
class EncryptionDecryptionTest {
	@Test
	void testDecryption() {

		assertNotEquals("Password@1", EncryptionDecryption.decrypt("Password@1"));
	}

	@Test
	void testEncryption() {
		
		assertNotEquals("Password@1", EncryptionDecryption.encrypt("Password@1"));
	}

}
