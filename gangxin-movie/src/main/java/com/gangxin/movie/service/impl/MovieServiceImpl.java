package com.gangxin.movie.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gangxin.movie.dao.MovieMapper;
import com.gangxin.movie.domain.Movie;
import com.gangxin.movie.service.MovieService;
import com.gangxin.movie.vo.MovieVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class MovieServiceImpl implements MovieService{

	@Autowired
	private MovieMapper movieMapper;

	@Override
	public PageInfo<Movie> list(MovieVO vo, Integer page, Integer pageSize) {
		PageHelper.startPage(page, pageSize);
		List<Movie> list=movieMapper.list(vo);
		return new PageInfo<Movie>(list);
	}

	@Override
	public int deleteBatch(Integer[] ids) {
		
		try {
			movieMapper.deleteBatch(ids);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("删除失败");
		}
	}

	@Override
	public int update(Integer id, String status) {
		try {
			movieMapper.update(id,status);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("修改失败");
		}
		
	}
}
