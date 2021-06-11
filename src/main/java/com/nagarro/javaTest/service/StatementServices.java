package com.nagarro.javaTest.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.models.Statement;

@Service
public class StatementServices {
	@Autowired
	private StatementDao dao;
	
	public List<Statement> getStatement( int id,Optional<String> fromDate, Optional<String> toDate,Optional<String> fromAmount, Optional<String> toAmount) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sqlQuery=new StringBuilder();
		sqlQuery.append("SELECT ID , ACCOUNT_ID , DATEFIELD , AMOUNT FROM STATEMENT WHERE ACCOUNT_ID = "+id);
		if(!fromDate.isPresent() && !toDate.isPresent() && !fromAmount.isPresent() && !toAmount.isPresent())
			sqlQuery.append(" AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= DATEADD('m', -3, DATE()) ");
		else {
			
			if(fromDate.isPresent())
			{
				String[] frmDate=fromDate.get().split("\\.");
				if(frmDate.length!=3 || frmDate[0].length()!=2|| frmDate[1].length()!=2|| frmDate[2].length()!=4)
					throw new Exception("From Date Format is incorrect please provide date in 'DD.MM.YYYY'");
				sqlQuery.append(" AND cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) >= cdate('"+frmDate[1]+"/"+frmDate[0]+"/"+frmDate[2]+"')");
				}
			if(toDate.isPresent())
			{
				String[] toDates=toDate.get().split("\\.");
				if(toDates.length!=3 || toDates[0].length()!=2|| toDates[1].length()!=2|| toDates[2].length()!=4)
					throw new Exception("To Date Format is incorrect please provide date in 'DD.MM.YYYY'");
				sqlQuery.append("  AND  cdate(MID(DATEFIELD,4,2)&'/'& MID(DATEFIELD,1,2)&'/'&MID(DATEFIELD,7,4)) < cdate('"+toDates[1]+"/"+toDates[0]+"/"+toDates[2]+"')");
				}
			if(fromAmount.isPresent())
			{
				sqlQuery.append("  AND  Val(STATEMENT.AMOUNT) >="+fromAmount.get() );
			}
				
			if(toAmount.isPresent())
				sqlQuery.append("  AND  Val(STATEMENT.AMOUNT) <"+toAmount.get() );
			
		}
		return dao.getStatement(sqlQuery.toString());
	}
	
	public List<Statement> getStatement( String query){
		return dao.getStatement(query);
	}
}
