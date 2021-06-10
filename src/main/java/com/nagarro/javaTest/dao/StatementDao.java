package com.nagarro.javaTest.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.nagarro.javaTest.models.Statement;
@Component
public interface StatementDao {

	public List<Statement> getStatement(String sql);
}
