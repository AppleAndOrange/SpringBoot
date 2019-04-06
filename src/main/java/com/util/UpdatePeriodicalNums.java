package com.util;

import com.periodical.service.PeriodicalService;

public class UpdatePeriodicalNums {
    private PeriodicalService periodicalService;

    /**
     *
     * @param service
     * @param periodicalId
     * @param flag  0:还期刊   1：借期刊
     * @return
     */
    public boolean update(PeriodicalService service,int periodicalId,int flag){
        int remaining=service.findRemainingNum(periodicalId);
        int used=service.findUsedNum(periodicalId);
        if(flag==0){
            remaining=remaining+1;
            used=used-1;
        }else if(flag==1){
            remaining=remaining-1;
            used=used+1;
        }
        boolean flag1=service.updateRemaining(periodicalId,remaining);
        boolean flag2=service.updateUsed(periodicalId,used);
        return flag1&&flag2;
    }

}
