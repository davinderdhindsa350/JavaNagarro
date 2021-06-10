package com.nagarro.javaTest.daoImplementation;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.nagarro.javaTest.dao.StatementDao;
import com.nagarro.javaTest.helper.EncryptionDecryption;
import com.nagarro.javaTest.models.Statement;

@Component
public class StatementDaoImp implements StatementDao {

	@Autowired
	private JdbcTemplate jdbc;
	@Override
	public List<Statement> getStatement(String sql) {
		// TODO Auto-generated method stub
		return jdbc.query(sql, getMap());
	}

	private RowMapper<Statement> getMap(){

		RowMapper<Statement> map=( rs,  rowNum) -> {
			Statement statement =new Statement();
			statement.setId(rs.getInt("ID"));
			statement.setAccount_id(EncryptionDecryption.encrypt(String.valueOf(rs.getInt("ACCOUNT_ID"))));
			statement.setDatefield(rs.getString("DATEFIELD"));
			statement.setAmount(rs.getString("AMOUNT"));
			return statement;
		};
		
		return map;
		
		
	}
}
