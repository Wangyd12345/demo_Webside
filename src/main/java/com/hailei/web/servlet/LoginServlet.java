package com.hailei.web.servlet;

import com.hailei.pojo.User;
import com.hailei.service.UserService;
import com.hailei.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    private UserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        //获取复选框数据
        String remember = request.getParameter("remember");

        //2. 调用service查询
        User user = service.login(username, password);

        //3. 判断
        if(user != null){
            //登录成功，跳转到查询所有的BrandServlet

            //判断用户是否勾选记住我
            if("1".equals(remember)){
                //勾选了，发送Cookie

                //1. 创建Cookie对象
                Cookie c_username = new Cookie("username",username);
                Cookie c_password = new Cookie("password",password);
                // 设置Cookie的存活时间
                c_username.setMaxAge( 60 * 60 * 24 * 7);
                c_password.setMaxAge( 60 * 60 * 24 * 7);
                //2. 发送
                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //将登陆成功后的user对象，存储到session，并将用户重定向到指定的页面。

            //request.getSession()获取当前请求的Session对象
            HttpSession session = request.getSession();

            //将"user"的属性添加到Session中，并将登录成功后的user对象作为属性值进行存储。
            session.setAttribute("user",user);

            //request.getContextPath()获取当前Web应用的上下文路径
            String contextPath = request.getContextPath();

            //response.sendRedirect()将用户重定向到指定的页面。
            //contextPath+"/brand.html"重定向到根目录下的brand.html页面。重定向会导致浏览器发起一个新的请求，将用户带到指定的页面。
            response.sendRedirect(contextPath+"/brand.html");
        }else {
            // 登录失败,

            // 存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误");

            // 跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            //在request域存的数据，只能用转发才能获取数据
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}