package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T> {

	//ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	public Connection getConnection() {
		try {
//			Class.forName(resourceBundle.getString("driverName"));
//			String url = resourceBundle.getString("url");
//			String user = resourceBundle.getString("user");
//			String password = resourceBundle.getString("password");
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newservlet";
			String user = "root";
			String password = "yeuget135";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<T>();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Connection connection = null;
		try {
			// open connection
			connection = getConnection();
			preparedStatement = connection.prepareStatement(sql);
			// set parameter
			setParameter(preparedStatement, parameters);
			// query
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
			// TODO: handle exception
			return null;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	private void setParameter(PreparedStatement preparedStatement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long)
					preparedStatement.setLong(index, (Long) parameter);
				else if (parameter instanceof String)
					preparedStatement.setString(index, (String) parameter);
				else if (parameter instanceof Integer)
					preparedStatement.setInt(index, (Integer) parameter);
				else if (parameter instanceof Timestamp)
					preparedStatement.setTimestamp(index, (Timestamp) parameter);
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement pstatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			pstatement = connection.prepareStatement(sql);
			setParameter(pstatement, parameters);
			pstatement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			// TODO: handle exception
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pstatement != null) {
					pstatement.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		ResultSet rs = null;
		Long id = null;
		Connection connection = null;
		PreparedStatement pstatement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			pstatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(pstatement, parameters);
			pstatement.executeUpdate();
			rs = pstatement.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			// TODO: handle exception
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pstatement != null) {
					pstatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		ResultSet rs = null;
		int count = 0;
		Connection connection = null;
		PreparedStatement pstatement = null;
		try {
			connection = getConnection();
			pstatement = connection.prepareStatement(sql);
			setParameter(pstatement, parameters);
			rs = pstatement.executeQuery(sql);
			if (rs.next()) {
				count = rs.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (pstatement != null) {
					pstatement.close();
				}
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
				return 0;
			}
		}
	}

}
