package edu.hit.LvWenXuan.web.Servlet;

import edu.hit.LvWenXuan.domain.ResultInfo;
import edu.hit.LvWenXuan.domain.User;
import edu.hit.LvWenXuan.service.UserService;
import edu.hit.LvWenXuan.service.Impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 有了这个UserServlet类就是包含了前面的几个类
 * RegisterUserServlet、LoginServlet、 ExitServlet
 */

@WebServlet("/user/*") // /user/add /user/find
public class UserServlet extends BaseServlet {

    //声明UserService业务对象
    private UserService service = new UserServiceImpl();

    /**
     * 注册功能
     */
    public void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取数据
        Map<String, String[]> map = request.getParameterMap();

        //2.封装对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //3.调用service完成注册
        //UserService service = new UserServiceImpl();
        boolean flag = service.regist(user);
        ResultInfo info = new ResultInfo();
        //4.响应结果
        if(flag){
            //注册成功
            info.setFlag(true);
        }else{
            //注册失败
            info.setFlag(false);
            info.setErrorMsg("注册失败!");
        }

        //将info对象序列化为json,然后返回，这个方法已经在BaseServlet中封装好了
        writeJsonValue(response,info);
    }


    /**
     * 登录功能
     */

    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取用户名和密码数据
        Map<String, String[]> map = request.getParameterMap();
        //2.封装User对象
        User user = new User();
        try {
            //根据输入的参数对user对象进行赋值
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //3.调用Service查询，这里实现的是根据用户名和密码进行查询
        User u  = service.login(user);
        //用来保存结果提示
        ResultInfo info = new ResultInfo();

        //4.判断用户对象是否为null
        if(u == null){
            //用户名密码或错误
            info.setFlag(false);
            info.setErrorMsg("用户名密码或错误");
        }
            //登录成功，把user对象存放到session域中
            request.getSession().setAttribute("user",u);
            //设置登录成功的标记
            info.setFlag(true);

        //将信息返回
        writeJsonValue(response,info);
    }


    public void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.销毁session
        request.getSession().invalidate();
        //2.跳转登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 查询单个对象
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从session中获取登录用户
        Object user = request.getSession().getAttribute("user");
        //将user写回客户端
        writeJsonValue(response,user);
    }



}
