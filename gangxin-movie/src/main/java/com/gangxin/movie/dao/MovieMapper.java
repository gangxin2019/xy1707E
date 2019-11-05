package com.gangxin.movie.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.gangxin.movie.domain.Movie;
import com.gangxin.movie.vo.MovieVO;

public interface MovieMapper {

	List<Movie> list(MovieVO vo);

	int deleteBatch(Integer[] ids);

	void update(@Param("id")Integer id,@Param("status") String status);

	

}
