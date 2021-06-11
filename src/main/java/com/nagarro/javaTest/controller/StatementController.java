package com.nagarro.javaTest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.javaTest.helper.AbstractRestController;
import com.nagarro.javaTest.helper.GlobalApiResponseEntity;
import com.nagarro.javaTest.models.Statement;
import com.nagarro.javaTest.service.StatementServices;

@RestController
@RequestMapping("/api")
public class StatementController extends AbstractRestController {

	@Autowired
	private StatementServices statementServices;

	@GetMapping("/getStatement")
	public ResponseEntity<GlobalApiResponseEntity> getStatement(Authentication authentication,
			@RequestParam(value = "accountId") int id,
			@RequestParam(value = "fromDate", required = false) Optional<String> fromDate,
			@RequestParam(value = "toDate", required = false) Optional<String> toDate,
			@RequestParam(value = "fromAmount", required = false) Optional<String> fromAmount,
			@RequestParam(value = "toAmount", required = false) Optional<String> toAmount) {
		if (authentication.getAuthorities().stream().noneMatch(x -> x.getAuthority().equalsIgnoreCase("ROLE_ADMIN"))
				&& (fromDate.isPresent() || toDate.isPresent() || fromAmount.isPresent() || toAmount.isPresent())) {
			return getBadRequestResponse(
					" The ‘user’ can only do a request without parameters for three months back statement only");
		}
		if (fromDate.isPresent() && !toDate.isPresent())
			return getBadRequestResponse(" To date is missing");
		if (!fromDate.isPresent() && toDate.isPresent())
			return getBadRequestResponse(" From date is missing");
		if (fromAmount.isPresent() && !toAmount.isPresent())
			return getBadRequestResponse(" To Amount is missing");
		if (!fromAmount.isPresent() && toAmount.isPresent())
			return getBadRequestResponse(" From Amount is missing");
		List<Statement> statements;
		try {
			statements = statementServices.getStatement(id, fromDate, toDate, fromAmount, toAmount);
		} catch (Exception e) {
			return getBadRequestResponse(e.getMessage());
		}

		if (statements.isEmpty())
			return getOKResponse(statements, "No Statements DataFound");
		else
			return getOKResponse(statements);
	}

	@GetMapping("/hello")
	public ResponseEntity<GlobalApiResponseEntity> hello() {

		return getOKResponse("hello");

	}

	@GetMapping("/executeQuery")
	public ResponseEntity<GlobalApiResponseEntity> executeQuery(@RequestParam(value = "query") String query) {

		return getOKResponse(statementServices.getStatement(query));

	}

}
