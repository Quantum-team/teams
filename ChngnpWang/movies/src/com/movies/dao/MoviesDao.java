package com.movies.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.movies.model.Movies;
import com.movies.utilis.BaseDao;

public class MoviesDao {
	Connection connection=null;
	PreparedStatement preparedStatement=null;
	ResultSet resultSet=null;
	public List<Movies> selectMovies() throws SQLException{
		connection=BaseDao.getConnection();
		String sql="select * from tb_movies";
		preparedStatement=connection.prepareStatement(sql);
		resultSet=preparedStatement.executeQuery();
		List<Movies> list=new ArrayList<Movies>();
			while(resultSet.next()){
				Movies movies=new Movies();
				movies.setId(resultSet.getInt("id"));
				movies.setName(resultSet.getString("name"));
				movies.setDuration(resultSet.getInt("duration"));
				movies.setArea(resultSet.getString("area"));
				movies.setMoviedate(resultSet.getDate("moviedate"));
				movies.setPrice(resultSet.getDouble("price"));
				list.add(movies);
			}
			BaseDao.close(connection, preparedStatement, resultSet);
			return list;
	}
	
	public int add(Movies movies) throws SQLException{
		connection=BaseDao.getConnection();
		String sql="insert into tb_movies values(0,?,?,?,?,?)";
		try{
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, movies.getName());
			preparedStatement.setInt(2, movies.getDuration());
			preparedStatement.setString(3, movies.getArea());
			preparedStatement.setDate(4, new Date(movies.getMoviedate().getTime()));
			preparedStatement.setDouble(5, movies.getPrice());
			return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
	
	public int delete(int id) throws SQLException{
		connection=BaseDao.getConnection();
		try{
		String sql="delete from tb_movies where id=?";
		preparedStatement=connection.prepareStatement(sql);
		preparedStatement.setInt(1, id);
		return preparedStatement.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			BaseDao.close(connection, preparedStatement, resultSet);
		}
		return 0;
	}
}
