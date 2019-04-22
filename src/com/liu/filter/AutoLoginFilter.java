package com.liu.filter;

import com.liu.entity.User;
import com.liu.util.CookieUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author LiuXin
 * @create 2019-04-20 12:42
 */
@WebFilter("/*")
public class AutoLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        filterLogin (req, resp, chain);
    }

    public void filterLogin(ServletRequest req, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try {
            HttpServletRequest request = (HttpServletRequest) req;

            //先判断，现在session中还有没有那个user.
             User user =  (User) request.getSession().getAttribute("user");
            //还有，有效。
            if(user != null){
                chain.doFilter(request, response);
            }else{
                //代表session失效了。
                //2. 看cookie。
                //1. 来请求的时候，先从请求里面取出cookie , 但是cookie有很多的key-value
                Cookie[] cookies = request.getCookies();
                //2. 从一堆的cookie里面找出我们以前给浏览器发的那个cookie
                Cookie cookie = CookieUtil.findCookie(cookies, "autoLogin");

                //第一次来
                if(cookie  == null){
                    System.out.println ("第一次登录");
                    chain.doFilter(request, response);
                    System.out.println ("放行");
                }else{
                    //不是第一次。
                    String value = cookie.getValue();
                    String username = value.split("#")[0];
                    String password = value.split("#")[1];
                    User user1 = new User (username,password);
                    //完成登录
                    //使用session存这个值到域中，方便下一次未过期前还可以用。
                    request.getSession().setAttribute("user", user1);

                    System.out.println ("第二次登录");
                    chain.doFilter(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            chain.doFilter(req, response);
        }
    }
    public void init(FilterConfig config) throws ServletException {

    }

}
