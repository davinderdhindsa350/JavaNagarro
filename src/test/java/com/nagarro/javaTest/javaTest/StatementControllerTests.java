package com.nagarro.javaTest.javaTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.nagarro.javaTest.models.Statement;
import com.nagarro.javaTest.service.StatementServices;

@AutoConfigureMockMvc
@SpringBootTest
@RunWith(SpringRunner.class)
 class StatementControllerTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private StatementServices statementServices;

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
	 void statementWithOnlyAccoutId_thenStatusUnauthorized() throws Exception {
		Mockito.when(statementServices.getStatement(3, Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty())).thenReturn(setUpList());

		mvc.perform(get("/api/getStatement?accountId=3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "admin", roles = { "ADMIN" })
	 void statementWithOnlyAccoutId_thenStatus200() throws Exception {
		Mockito.when(statementServices.getStatement(3, Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty())).thenReturn(setUpList());

		mvc.perform(get("/api/getStatement?accountId=3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	 void statementWithOnlyAccoutIdAndFilters_thenStatus200() throws Exception {
		Mockito.when(statementServices.getStatement(3, Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty())).thenReturn(setUpList());

		mvc.perform(get("http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}

	@Test
	@WithMockUser(username = "user", roles = { "USER" })
	 void statementWithOnlyAccoutIdAndFiltersForUser_thenStatusBad() throws Exception {
		Mockito.when(statementServices.getStatement(3, Optional.empty(), Optional.empty(), Optional.empty(),
				Optional.empty())).thenReturn(setUpList());

		mvc.perform(get("http://localhost:8888/api/getStatement?accountId=3&&fromAmount=12&&toAmount=623.461804295262")
				.contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}

}
