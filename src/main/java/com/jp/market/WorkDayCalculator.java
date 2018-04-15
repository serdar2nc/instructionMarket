package com.jp.market;

import java.util.Calendar;
import java.util.Date;

public class WorkDayCalculator {
    private boolean western = true;

    public  WorkDayCalculator(String currency){
        western = !(currency.equals("AED") || currency.equals("SAR"));
    }

    public boolean isWestern(){
        return western;
    }

    public int getFirstDayOfWeek(){
        return isWestern() ? Calendar.MONDAY : Calendar.SUNDAY;
    }

    public Date convertToWorkDay(Date date){
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

        if(getFirstDayOfWeek() == Calendar.MONDAY){
            int days = dayOfWeek == Calendar.SUNDAY ? 1
                    : dayOfWeek == Calendar.SATURDAY ? 2
                    : 0;
            c.add(Calendar.DATE, days);
        } else {
            int days = dayOfWeek == Calendar.SATURDAY ? 1
                    : dayOfWeek == Calendar.FRIDAY ?  2
                    : 0;
            c.add(Calendar.DATE, days);
        }
        return c.getTime();
    }
}
