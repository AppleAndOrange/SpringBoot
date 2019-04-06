package com.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class  DataUtil{
    public String Object2Json(Response userResult){
        String json=null;
        try {
            ObjectMapper mapper=new ObjectMapper();
            json=mapper.writeValueAsString(userResult);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public String judge(boolean flag){

        /*if(!flag){
            jsonData=result.getData(null,ResponseCode.FAILURE.getResultCode(),ResponseCode.FAILURE.getResultMsg());
        }else {
            jsonData=result.getData(null, ResponseCode.SUCCESS.getResultCode(),ResponseCode.SUCCESS.getResultMsg());
        }*/
        return null;
    }
}
