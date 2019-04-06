package com.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.bean.Relation;
import com.user.service.RelationService;
import com.util.ResponseCode;
import com.user.bean.User;
import com.user.service.UserService;
import com.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/main",method = {RequestMethod.GET,RequestMethod.POST})
public class UserController {
    private Logger logger= LoggerFactory.getLogger(UserController.class);
    private UserService service;
    private RelationService relationService;

    @Autowired
    public UserController(UserService service,RelationService relationService) {
        this.service = service;
        this.relationService=relationService;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "login")
    public ModelAndView index(){
        ModelAndView view=new ModelAndView();
        view.setViewName("login");//登录页面的路径

        return view;
    }

    /**
     * 用户登录处理,返回给前端的数据放在session中
     * 在进行用户权限管理时，不同的权限进入不同的页面
     * 其中权限类型，0：超级管理员，1：管理员，2：普通读者
     * 暂时没有使用spring security进行权限管理。。。。看有没有时间咯=====
     * @param request
     * @param session
     * @return
     */
    @RequestMapping(value = "doLogin")
    public ModelAndView doLogin(HttpServletRequest request, HttpSession session){
        ModelAndView view=null;
        try {
            String jsonData=null;
            view = new ModelAndView();
            Result result=new Result();
            int userId=Integer.parseInt(request.getParameter("user_id"));
            String password=request.getParameter("password");
            User user=service.login(userId,password);
            if(user!=null){
                //登录成功
                jsonData=result.getData(user,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                int privilegeType=service.findPrivilege(user.getUserId());
                //登陆后根据不同权限跳转到不同的页面
                if(privilegeType==0){
                    view.setViewName("superAdministrator");
                }else if(privilegeType==1){
                    view.setViewName("administrator");
                }else if(privilegeType==2){
                    view.setViewName("reader");
                }
            }else {
                //登录失败
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("login");
            }
            session.setAttribute("login",jsonData);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return view;
    }

    /**
     * 用户注册处理
     * @return
     */

    @RequestMapping(value = "register")
    public ModelAndView register(){
        ModelAndView view=new ModelAndView();
        view.setViewName("register");//用户注册的html页面
        return view;
    }

    @RequestMapping(value = "doRegister")
    public ModelAndView doRegister(HttpServletRequest request,HttpSession session){
        ModelAndView view=null;
        try {
            String jsonData=null;
            view = new ModelAndView();
            Result result=new Result();
            String userName=request.getParameter("user_name");
            String password=request.getParameter("password");
            int age=Integer.parseInt(request.getParameter("age"));
            String sex=request.getParameter("sex");
            String phone=request.getParameter("phone");
            boolean flag=service.register(userName,password,age,sex,phone);
            if(flag){
                //注册成功
                int userId=service.findUserId();
                String role=request.getParameter("type");//注册时选择角色的name属性值
                int roleId;
                if(role.equals("超级管理员")){
                    roleId=0;
                }else if(role.equals("管理员")){
                    roleId=1;
                }else {
                    roleId=2;
                }
                relationService.insert(userId,roleId);//往关系表中插入关系对照
                jsonData=result.getData(null,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("reader");
            }else {
                //注册失败
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("error");
            }
            session.setAttribute("register",result);
            System.out.println("login="+session.getAttribute("login"));
        }catch (Exception e){
            logger.error(e.toString());
        }
        return  view;
    }

    /**
     * 修改用户密码
     * @return
     */
    @RequestMapping(value = "changePassword")
    public ModelAndView update(){
        ModelAndView view=new ModelAndView();
        view.setViewName("register");
        return view;
    }

    @RequestMapping(value = "doChangePassword")
    public ModelAndView doUpdate(HttpServletRequest request,HttpSession session){
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            String jsonData=null;
            Result result=new Result();
            int userId=Integer.parseInt(request.getParameter("user_id"));
            String password=request.getParameter("password");
            String phone=request.getParameter("phone");
            boolean flag=service.updatePassword(userId,password,phone);
            if(flag){
                //修改成功
                jsonData=result.getData(null,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("login");
            }else {
                //修改失败
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("error");
            }
            session.setAttribute("update",jsonData);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return  view;
    }

    /**
     * 修改相应的权限，仅超级管理员可干
     */

    @RequestMapping(value = "updatePrivilege")
    public ModelAndView updatePrivilege(){
        ModelAndView view=new ModelAndView();
        view.setViewName("updatePrivilege");//用户注册的html页面
        return view;
    }
    @RequestMapping(value = "doUpdatePrivilege")
    public ModelAndView updatePrivilege(HttpServletRequest request,HttpSession session){
        ModelAndView view=null;
        try {
            String jsonData=null;
            ObjectMapper mapper=new ObjectMapper();
            view = new ModelAndView();
            Result result=new Result();
            int userId= Integer.parseInt(request.getParameter("user_id"));
            int roleId=Integer.parseInt("role_id");
            boolean flag=relationService.update(userId,roleId);
            if(flag){
                //修改权限成功
                jsonData=result.getData(null,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("reader");
            }else {
                //修改权限失败
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("error");
            }
            session.setAttribute("updatePrivilege",result);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return  view;
    }

    /**
     * 查找用户
     */

    @RequestMapping(value = "findUser")
    public ModelAndView findUser(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }

    @RequestMapping(value = "doFindUser")
    public ModelAndView doFindUser(HttpServletRequest request,HttpSession session){
        ModelAndView view=null;
        try {
            String jsonData=null;
            ObjectMapper mapper=new ObjectMapper();
            view = new ModelAndView();
            Result result=new Result();
            int userId= Integer.parseInt(request.getParameter("user_id"));
            User user=service.findByUserId(userId);
            if(user!=null){
                //修改权限成功
                jsonData=result.getData(user,ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("reader");
            }else {
                //修改权限失败
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("error");
            }
            session.setAttribute("updatePrivilege",result);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return  view;
    }


}
