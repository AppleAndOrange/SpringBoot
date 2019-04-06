package com.periodical.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.periodical.bean.Record;
import com.periodical.service.PeriodicalService;
import com.periodical.service.UseService;
import com.user.bean.User;
import com.user.controller.UserController;
import com.util.ResponseCode;
import com.util.Result;
import com.util.UpdatePeriodicalNums;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;

@RestController
@RequestMapping(value = "using",method = {RequestMethod.GET,RequestMethod.POST})
public class UseController {
    private static final Logger logger= LoggerFactory.getLogger(UserController.class);
    private UseService service;
    private PeriodicalService periodicalService;

    public UseController(UseService service,PeriodicalService periodicalService) {
        this.service = service;
        this.periodicalService=periodicalService;
    }

    /**
     * 根据用户Id查询借还期刊的记录
     * @return
     */
    @RequestMapping(value = "search")
    public ModelAndView findRecordsByUserId(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }

    @RequestMapping(value = "SearchById")
    public ModelAndView findRecords(HttpServletRequest request, HttpSession session){
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            String jsonData=null;
            ObjectMapper mapper=new ObjectMapper();
            Result result=new Result();
            User user = mapper.readValue(session.getAttribute("login") + "", User.class);
            int userId=user.getUserId();
            Record record=service.findRecordsByUserId(userId);
            if(record!=null){
                jsonData=result.getData(record, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("12");//需要小姐姐自己写对应html的路径，对应html是指在查找记录成功后，要跳转至哪个页面
            }else {
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("");//需要小姐姐自己写对应html的路径，对应html是指在查找记录失败后，要跳转至哪个页面
            }
            session.setAttribute("search",result);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return view;
    }

    /**
     * 用户借期刊
     * 用户借完书需要更新期刊信息表
     * @return
     */
    @RequestMapping(value = "borrow")
    public ModelAndView borrow(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }

    @RequestMapping(value = "doBorrow")
    public ModelAndView doBorrow(HttpServletRequest request, HttpSession session){
        ModelAndView view=null;
        try {
            UpdatePeriodicalNums periodicalNums=new UpdatePeriodicalNums();
            view = new ModelAndView();
            ObjectMapper mapper=new ObjectMapper();
            String jsonData=null;
            Result result=new Result();
            int periodicalId=Integer.parseInt(request.getParameter("periodical_id"));
            User user=mapper.readValue(session.getAttribute("login")+"", User.class);
            int userId=user.getUserId();
            int num=service.selectNum(periodicalId)+1;
            boolean flag=service.borrow(periodicalId,userId,num);
            if(flag){
                boolean temp=periodicalNums.update(periodicalService,periodicalId,1);
                if(!temp){
                    //获取json对象，下同
                    jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                }else {
                    jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                }
                view.setViewName("12");//需要小姐姐自己写对应html的路径，对应html是指在用户借书成功后，要跳转至哪个页面
            }else {
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("");//需要小姐姐自己写对应html的路径，对应html是指在用户借书失败后，要跳转至哪个页面
            }
            session.setAttribute("borrow",result);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return view;

    }

    /**
     * 用户还期刊
     * 用户还完期刊需要更新期刊信息表
     * @return
     */
    @RequestMapping(value = "return")
    public ModelAndView returnPeriodical(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }

    @RequestMapping(value = "doReturn")
    public ModelAndView doReturn(HttpServletRequest request, HttpSession session){
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            UpdatePeriodicalNums periodicalNums=new UpdatePeriodicalNums();
            String jsonData=null;
            boolean flag;
            Result result=new Result();
            int periodicalId=Integer.parseInt(request.getParameter("periodicalId"));
            int num=service.selectNum(periodicalId)-1;
            if(num<=0){
                flag=service.returnPeriodical(periodicalId);
            }else {
                flag=service.update(periodicalId,num);
            }
            if(flag){
                boolean temp=periodicalNums.update(periodicalService,periodicalId,0);
                if(!temp){
                    jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                }else {
                    jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                }
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("15");//需要小姐姐自己写对应html的路径，对应html是指在用户还书成功后，要跳转至哪个页面
            }else {
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("");//需要小姐姐自己写对应html的路径，对应html是指在用户还书失败后，要跳转至哪个页面
            }
            session.setAttribute("search",result);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return view;
    }

    /**
     * 用户续借
     * @return
     */
    @RequestMapping(value = "renewal")
    public ModelAndView renewal(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }

    @RequestMapping(value = "doRenewal")
    public ModelAndView doRenewal(HttpServletRequest request, HttpSession session){
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            String jsonData=null;
            Result result=new Result();
            ObjectMapper mapper=new ObjectMapper();
            int periodicalId=Integer.parseInt(request.getParameter("periodical_id"));
            User user = mapper.readValue(session.getAttribute("login") + "", User.class);
            int userId=user.getUserId();
            boolean flag=service.renewal(periodicalId,userId);
            if(flag){
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("16");//需要小姐姐自己写对应html的路径，对应html是指在用户续借成功后，要跳转至哪个页面
            }else {
                jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("");//需要小姐姐自己写对应html的路径，对应html是指在用户续借失败后，要跳转至哪个页面
            }
            session.setAttribute("search",result);
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return  view;
    }

}
