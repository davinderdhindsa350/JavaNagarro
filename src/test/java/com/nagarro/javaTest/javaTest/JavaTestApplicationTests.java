package com.nagarro.javaTest.javaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.helper.EncryptionDecryption;
import com.nagarro.javaTest.models.Statement;

@SpringBootTest
@AutoConfigureMockMvc
class JavaTestApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private StatementDao statementDao;

	@Test
	void contextLoads() {
	}

	@Test
	public void statementWithOnlyAccoutId_thenStatus200() throws Exception {
		/*
		 * mvc.perform(get("api/getStatement?accountId=3")
		 * .contentType(MediaType.APPLICATION_JSON)) .andExpect(status().isOk())
		 * .andExpect(content() .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
		 * .andExpect(jsonPath("$[0].name", is("bob")));
		 */
	}

	@Test
	public void testDecryption() {

		assertEquals("Password@1", EncryptionDecryption.decrypt("+uHQsXKNr5Qbv8o4awkUdA=="));
	}

	@Test
	public void testEncryption() {

		assertEquals("+uHQsXKNr5Qbv8o4awkUdA==", EncryptionDecryption.encrypt("Password@1"));
	}
	
	

	// Test DAO Layer
	// http://localhost:8888/api/getStatement?accountId=3
	@Test
	public void findStatement_thenReturnSizeFor3() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE()) ");

		// then
		assertEquals(found.size(), 8);
	}

	// http://localhost:8888/api/getStatement?accountId=2
	@Test
	public void findStatement_thenReturnSizeFor2() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 2 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE())");

		// then
		assertEquals(found.size(), 0);
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262
	@Test
	public void findStatementWithAmountRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262");

		// then
		assertEquals(found.size(), 18);
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021
	@Test
	public void findStatementWithDateRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')");

		// then
		assertEquals(found.size(), 11);
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021&&fromAmount=12&&toAmount=623.461804295262
	@Test
	public void findStatementWithAmountAndDateRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262");

		// then
		assertEquals(found.size(), 9);
	}

}
