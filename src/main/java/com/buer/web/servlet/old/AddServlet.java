package com.buer.web.servlet.old;

import com.alibaba.fastjson.JSON;
import com.buer.pojo.Brand;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
//web层
//@WebServlet("/addServlet")
public class AddServlet extends HttpServlet {

    private BrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收品牌数据（前端是以JSON的格式提交过来的）
        BufferedReader br = request.getReader();

        //读取，获得JSON的字符串
        String params = br.readLine();
;
        //将JSON转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service实现添加
        brandService.add(brand);

        //响应一个成功的标识
        response.getWriter().write("success");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request,response);
    }
}
