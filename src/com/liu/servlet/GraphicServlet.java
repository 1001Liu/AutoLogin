package com.liu.servlet;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * @author LiuXin
 * @create 2019-04-20 19:52
 */
@WebServlet("/GraphicServlet")
public class GraphicServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        graphic (request, response);
    }

    public void graphic(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //画布
        BufferedImage image = new BufferedImage (100, 50, BufferedImage.TYPE_INT_RGB);
        //得到画笔
        Graphics graphics = image.getGraphics ();
        //画的起始位置和大小
        graphics.drawRect (0,0,100,50);
        //背景颜色
        graphics.setColor (Color.LIGHT_GRAY);
        //填充颜色
        graphics.fillRect (0,0,100,50);
        //随机生成验证码
        String str ="";
        Random r =new Random ();
        for (int i=1;i<=4;i++){
            switch (r.nextInt(3)){
                case 0: str+=(char)(r.nextInt(26)+97);
                    break;
                case 1: str+=(char)(r.nextInt(26)+65);
                    break;
                case 2: str+=r.nextInt(10);
                    break;
            }
        }
        //设置干扰的图形
        graphics.setColor(Color.black);
        for(int i=0;i<750;i++){
            graphics.drawString(".",r.nextInt(100),r.nextInt(50));
        }
        graphics.setColor(Color.black);
        graphics.setFont(new Font("宋体",Font.BOLD,20));
        graphics.drawString(str,25,25);
        Cookie cookie = new Cookie ("str",str);
        cookie.setMaxAge (60*60);
        cookie.setPath ("/");
        response.addCookie (cookie);
        request.getSession ().setAttribute ("str1",str);
        //将画好的验证码写到页面上
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image,"png",out);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost (request, response);
    }
}
