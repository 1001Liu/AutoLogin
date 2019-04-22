package com.liu.servlet;

import com.liu.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author LiuXin
 * @create 2019-04-20 10:18
 */
@WebServlet( "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding ("utf-8");
        //根据传过来的标记进行选择执行方法
        String op = request.getParameter ("op");
        String username = request.getParameter ("username");
        String password = request.getParameter ("password");
        User user = new User (username,password);
        //获取验证码
        String verification = request.getParameter ("verification");
        //获取自动登录状态
        String autoLogin = request.getParameter ("autoLogin");

        //获取记住密码状态
        String repassword = request.getParameter ("repassword");
        if (op.equals ("ajax")){
            ajaxLogin (request, response,username);
        }else if ("on".equals (autoLogin) && op.equals ("1")){
            autoLogin (request,response,user);
        }else if( autoLogin==null&& op.equals ("1")){
            login (request,response,user);
        }

    }
    //直接登录,存在session中就行
    public void login(HttpServletRequest request, HttpServletResponse response,User user) throws IOException {
        request.getSession ().setAttribute ("user",user);
        System.out.println (user.getUsername ());
        response.sendRedirect ("success_login.jsp");
    }
    //自动登录状态
    public void autoLogin(HttpServletRequest request, HttpServletResponse response,User user) throws IOException {
        //如果是on选择了自动登录
        //把用户名密码存在cookie中
        Cookie cookie = new Cookie ("autoLogin",user.getUsername ()+"#"+user.getPassword ());
        cookie.setMaxAge (60*60*24);
        cookie.setPath ("/");
        response.addCookie (cookie);
        //把用户名存在session中,方便前台取
        request.getSession ().setAttribute ("user",user);
        System.out.println (user.getUsername ());
        response.sendRedirect ("success_login.jsp");
    }


    //判断用户名是否可用
    public void ajaxLogin(HttpServletRequest request, HttpServletResponse response,String username) throws IOException {

        if (username.equals ("admin")){
            response.getWriter ().print (false);
        }else response.getWriter ().print (true);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request,response);
    }
}
