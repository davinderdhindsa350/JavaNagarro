package com.nagarro.javaTest.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.models.Statement;

@Service
public class StatementServices {
	@Autowired
	private StatementDao dao;
	
	public List<Statement> getStatement( int id,Optional<String> fromDate, Optional<String> toDate,Optional<String> fromAmount, Optional<String> toAmount) {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery=new StringBuilder();
		sqlQuery.append("SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = "+id);
		if(!fromDate.isPresent() && !toDate.isPresent() && !fromAmount.isPresent() && !toAmount.isPresent())
			sqlQuery.append(" AND DateValue(STATEMENT.DATEFIELD) >");
		else {
			if(fromDate.isPresent())
				sqlQuery.append(" AND DateValue(STATEMENT.DATEFIELD) >"+fromDate );
			if(toDate.isPresent())
				sqlQuery.append("  AND  DateValue(STATEMENT.DATEFIELD) <"+toDate);
			if(fromAmount.isPresent())
				sqlQuery.append("  AND  Val(STATEMENT.AMOUNT) <"+fromAmount);
			if(toAmount.isPresent())
				sqlQuery.append("  AND  Val(STATEMENT.AMOUNT) <"+toAmount);
		}
		return dao.getStatement(sqlQuery.toString());
	}
}
