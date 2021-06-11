package com.nagarro.javaTest.javaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.models.Statement;

@SpringBootTest
@RunWith(SpringRunner.class)
class StatementDaoTests {

	@Autowired
	private StatementDao statementDao;

	// Test DAO Layer
	// http://localhost:8888/api/getStatement?accountId=3
	@Test
	void findStatement_thenReturnSizeFor3() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE()) ");

		// then
		System.out.println(found.size());
		assertEquals( 8,found.size());
	}

	// http://localhost:8888/api/getStatement?accountId=2
	@Test
	void findStatement_thenReturnSizeFor2() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 2 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE())");

		// then
		assertEquals( 0,found.size());
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262
	@Test
	void findStatementWithAmountRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262");

		// then
		assertEquals(18,found.size());
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021
	@Test
	void findStatementWithDateRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')");

		// then
		assertEquals( 11,found.size());
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021&&fromAmount=12&&toAmount=623.461804295262
	@Test
	void findStatementWithAmountAndDateRang_thenReturnSizeFor() {

		// when
		List<Statement> found = statementDao.getStatement(
				"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262");

		// then
		assertEquals( 9,found.size());
	}
}
