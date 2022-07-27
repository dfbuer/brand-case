package com.buer.web.servlet;

import com.alibaba.fastjson.JSON;
import com.buer.pojo.Brand;
import com.buer.pojo.PageBean;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

//web层

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService = new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //调用service查询
        List<Brand> brands = brandService.selectAll();

        //把数据转为JSON
        String jsonString = JSON.toJSONString(brands);

        //写数据
        //处理中文
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据（前端是以JSON的格式提交过来的）
        BufferedReader br = request.getReader();

        //读取，获得JSON的字符串
        String params = br.readLine();

        //将JSON转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service实现添加
        brandService.add(brand);

        //响应一个成功的标识
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据（前端是以JSON的格式提交过来的）
        BufferedReader br = request.getReader();

        //读取，获得JSON的字符串
        String params = br.readLine();
        ;
        //将JSON转为int类型数组
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service实现添加
        brandService.deleteByIds(ids);

        //响应一个成功的标识
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收 当前页码和显示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //把数据转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        //处理中文
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收 当前页码和显示条数 url?currentPage=1&pageSize=5
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);


        //获取对应的条件对象
        BufferedReader br = request.getReader();

        //读取，获得JSON的字符串
        String params = br.readLine();
        ;
        //将JSON转为int类型数组
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage,pageSize,brand);

        //把数据转为JSON
        String jsonString = JSON.toJSONString(pageBean);

        //写数据
        //处理中文
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String id = request.getParameter("id");
        System.out.println(id);

        //调用service实现添加
        brandService.deleteById(Integer.parseInt(id));

        //响应一个成功的标识
        response.getWriter().write("success");
    }

    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //接收品牌数据（前端是以JSON的格式提交过来的）
        BufferedReader br = request.getReader();

        //读取，获得JSON的字符串
        String params = br.readLine();

        //将JSON转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service实现添加
        brandService.update(brand);

        //响应一个成功的标识
        response.getWriter().write("success");
    }
}
