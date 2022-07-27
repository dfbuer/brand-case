package com.buer.web.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//web层

/**
 * 用来替换HttpServlet,根据请求的最后一段路径进行方法分发
 */
public class BaseServlet extends HttpServlet {

    //根据请求的最后一段路径进行方法分发
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取请求路径
        String uri = request.getRequestURI();
        //获取最后一段路径，即方法名
        int index = uri.lastIndexOf("/");
        String methodName = uri.substring(index + 1);

        //执行方法
            //获取BrandServlet或者UserServlet字节码对象 class
            //谁调用我（this 所在的方法），我（this）代表谁
        Class<? extends BaseServlet> cls = this.getClass();

            //获取方法method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);

            //执行方法
            try {
                method.invoke(this,request,response);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
