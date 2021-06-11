package com.nagarro.javaTest.javaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.models.Statement;
import com.nagarro.javaTest.service.StatementServices;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
class StatementServiceTest {

	@Autowired
	private StatementServices statementServices;

	@MockBean
	private StatementDao statementDao;


	public List<Statement> setUpList() {
		Statement s1 = new Statement(1, "3pvlqkcL2Gy3M0r+9cWbgQ==", "03.03.2021", "623.461804295262");
		Statement s2 = new Statement(2, "3pvlqkcL2Gy3M0r+9cWbgQ==", "03.04.2021", "523.461804295262");
		Statement s3 = new Statement(3, "3pvlqkcL2Gy3M0r+9cWbgQ==", "03.05.2021", "223.461804295262");
		Statement s4 = new Statement(4, "3pvlqkcL2Gy3M0r+9cWbgQ==", "03.06.2021", "323.461804295262");
		List<Statement> lst = new ArrayList<>();
		lst.add(s1);
		lst.add(s2);
		lst.add(s3);
		lst.add(s4);

		return lst;
	}

	@Test
	void findStatement_thenReturnSizeFor3() {

		List<Statement> lst;
		try {
			Mockito.when(statementDao.getStatement(
					"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE()) "))
					.thenReturn(setUpList());

			lst = statementServices.getStatement(3, Optional.empty(), Optional.empty(), Optional.empty(),
					Optional.empty());
			
			assertEquals( 4 , lst.size());

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// http://localhost:8888/api/getStatement?accountId=2
	@Test
	void findStatement_thenReturnSizeFor2() {

		List<Statement> lst;
		try {
			Mockito.when(statementDao.getStatement(
					"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 2 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE()) "))
					.thenReturn(setUpList());

			lst = statementServices.getStatement(2, Optional.empty(), Optional.empty(), Optional.empty(),
					Optional.empty());
			assertEquals( 4 , lst.size());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262
	@Test
	void findStatementWithAmountRang_thenReturnSizeFor() {

		List<Statement> lst;
		try {
			Mockito.when(statementDao.getStatement(
					"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262"))
					.thenReturn(setUpList());

			Optional<String> fromAmount = Optional.of("12");
			Optional<String> toAmount = Optional.of("623.461804295262");

			lst = statementServices.getStatement(3, Optional.empty(), Optional.empty(), fromAmount, toAmount);
			assertEquals( 4 , lst.size());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// http://localhost:8888/api/getStatement?accountId=3&&fromDate=29.11.2020&&toDate=15.09.2021
	@Test
	void findStatementWithDateRang_thenReturnSizeFor() {

		List<Statement> lst;
		try {
			Mockito.when(statementDao.getStatement(
					"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')"))
					.thenReturn(setUpList());

			Optional<String> fromdate = Optional.of("29.11.2020");
			Optional<String> todate = Optional.of("15.09.2021");

			lst = statementServices.getStatement(3, fromdate, todate, Optional.empty(), Optional.empty());
			assertEquals( 4 , lst.size());

			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void findStatementWithAmountAndDateRang_thenReturnSizeFor() {

		List<Statement> lst;
		try {
			Mockito.when(statementDao.getStatement(
					"SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = 3 AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('11/29/2020')  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('09/15/2021')  AND  Val(STATEMENT.AMOUNT) >=12  AND  Val(STATEMENT.AMOUNT) <623.461804295262"))
					.thenReturn(setUpList());
			Optional<String> fromdate = Optional.of("29.11.2020");
			Optional<String> todate = Optional.of("15.09.2021");
			Optional<String> fromAmount = Optional.of("12");
			Optional<String> toAmount = Optional.of("623.461804295262");
			lst = statementServices.getStatement(3, fromdate, todate, fromAmount, toAmount);
			assertEquals( 4 , lst.size());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
