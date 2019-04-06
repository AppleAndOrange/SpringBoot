package com.util;

import com.user.bean.User;

public class Result {

    public String getData(Object user,String code,String msg){
        Response result=new Response();
        DataUtil util=new DataUtil();
        result.setResultCode(code);
        result.setResultMsg(msg);
        result.setUser(user);
        String json = util.Object2Json(result);
        return json;
    }
}
