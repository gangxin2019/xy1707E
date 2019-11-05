package com.gangxin.movie.controller;


import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gangxin.movie.domain.Movie;
import com.gangxin.movie.service.MovieService;
import com.gangxin.movie.util.PageUtil;
import com.gangxin.movie.vo.MovieVO;
import com.github.pagehelper.PageInfo;

@Controller
public class MovieController {
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("list")
	public String list(MovieVO vo,@RequestParam(defaultValue = "1")Integer page,@RequestParam(defaultValue = "3")Integer pageSize,
			HttpServletRequest request) {
		
		if(vo.getOrderMethod()==null) {
			vo.setOrderMethod("asc");
		}
		
		/*
		 * PageInfo<Movie> info=movieService.list(vo,page,pageSize);
		 * PageUtil.page(request, "/list", pageSize, info.getList(), (int)
		 * info.getTotal(), page);
		 */
		PageInfo<Movie> list = movieService.list(vo, page, pageSize);
		PageUtil.page(request, "/list", pageSize, list.getList(), (int) list.getTotal(), page);
		request.setAttribute("pg", list);
		request.setAttribute("vo", vo);
		
				return "list";
		
	}
	
	@ResponseBody
	@RequestMapping("/deleteBatch")
	public String deleteBatch(@Param("ids[]")Integer[] ids) {
		int i=movieService.deleteBatch(ids);
		if(i>0) {
			return "ok";
		}
		
		return "no";
		
	}
	
	@ResponseBody
	@RequestMapping("update")
	public String update(@Param("id")Integer id,@Param("status")String status) {
		int i=movieService.update(id,status);
		if(i>0) {
			return "ok";
		}
		
		return "no";
		
	}

}
