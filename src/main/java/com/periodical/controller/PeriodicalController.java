package com.periodical.controller;

import com.periodical.bean.Periodical;
import com.periodical.service.PeriodicalService;
import com.util.ResponseCode;
import com.util.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;

@RestController
@RequestMapping(value = "periodical",method = {RequestMethod.POST,RequestMethod.GET})
public class PeriodicalController {
    private static final Logger logger= LoggerFactory.getLogger(PeriodicalController.class);
    private PeriodicalService service;

    public PeriodicalController(PeriodicalService service) {
        this.service = service;
    }

    /**
     * 按照编号或者名称查找期刊
     */
    @RequestMapping(value = "searchPeriodical")
    public ModelAndView search(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }


    @RequestMapping(value = "doSearch")
    public ModelAndView findPeriodical(HttpServletRequest request,HttpSession session){
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            String jsonData=null;
            int num;
            Periodical periodicals=null;
            Result result=new Result();
            String periodicalId=request.getParameter("periodical_id");
            String periodicalName=request.getParameter("periodicalName");
            if(periodicalId!=null){
                periodicals = service.findPeriodicalById(Integer.parseInt(periodicalId));
            }else if(periodicalName!=null){
                periodicals=service.findPeriodicalByName(periodicalName);
            }
            if(periodicals!=null){
                //获取json对象，下同
                jsonData=result.getData(periodicals, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());

                view.setViewName("right");//自己填写路径哦，参照UseController类的相应注释
            }else {
                jsonData=result.getData(null, ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
                view.setViewName("error");//自己填写路径哦，参照UseController类的相应注释
            }
            session.setAttribute("search",jsonData);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return view;
    }

    /**
     *
     * 有新的期刊入库
     */
    @RequestMapping(value = "add")
    public ModelAndView add(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }
    @RequestMapping(value = "doAdd")
    public ModelAndView addPeriodicals(HttpServletRequest request,HttpSession session) {
        ModelAndView view=null;
        try {

            view = new ModelAndView();
            String jsonData=null;
            Result result =new Result();
            String periodicalName=request.getParameter("periodical_name");
            String author=request.getParameter("author");
            String print=request.getParameter("print");
            Date date=Date.valueOf(request.getParameter("publish_date"));
            int remaining=Integer.parseInt(request.getParameter("remaining"));
            int used= Integer.parseInt(request.getParameter("used"));
            String type=request.getParameter("type");
            boolean flag=service.addPeriodicals(periodicalName,author,print,date,remaining,used,type);
            if(flag){
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("");//自己填写路径哦，参照UseController类的相应注释
            }else {
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("");//自己填写路径哦，参照UseController类的相应注释
            }
            session.setAttribute("add",jsonData);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return view;
    }


    /**
     *
     * 删除已经不需要的期刊
     * 删除时需要注意，如果已经借出去，但是还有没有还回来的期刊，不能删除
     *
     */
    @RequestMapping(value = "delete")
    public ModelAndView delete(){
        ModelAndView view=new ModelAndView();
        view.setViewName("");
        return view;
    }
    @RequestMapping(value = "doDelete")
    public ModelAndView deletePeriodicals(HttpServletRequest request,HttpSession session) {
        ModelAndView view=null;
        try {
            view = new ModelAndView();
            String jsonData=null;
            Result result =new Result();
            int periodicalId=Integer.parseInt(request.getParameter("periodical_id"));
            boolean flag=service.deletePeriodical(periodicalId);
            if(flag){
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("test");//自己填写路径哦，参照UseController类的相应注释
            }else {
                jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
                view.setViewName("");//自己填写路径哦，参照UseController类的相应注释
            }
            session.setAttribute("delete",jsonData);
        }catch (Exception e){
            logger.error(e.toString());
        }
        return view;
    }
}
