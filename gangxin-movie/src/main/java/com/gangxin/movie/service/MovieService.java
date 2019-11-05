package com.gangxin.movie.service;

import com.gangxin.movie.domain.Movie;
import com.gangxin.movie.vo.MovieVO;
import com.github.pagehelper.PageInfo;

public interface MovieService {

	PageInfo<Movie> list(MovieVO vo, Integer page, Integer pageSize);

	int deleteBatch(Integer[] ids);

	int update(Integer id, String status);

}
