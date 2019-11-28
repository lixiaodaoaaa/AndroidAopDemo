package com.ailianlian.base.utils;
/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2017/11/29
       Time     :  14:23
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SimpleDateUtils{
    
    
    /**
     * @param someDay 几天
     * @return 得到过去几天的str
     * 举例：输入1  返回昨天的日期格式 17-11-28
     * 输入2  返回前天的简单日期格式    17-11-27
     */
    public static String getBeforeSomeDayStr( int someDay ){
        Calendar cal = Calendar.getInstance( );
        cal.add(Calendar.DATE, -someDay);
        Date time = cal.getTime( );
        return new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault( )).format(time);
    }
    
    
    /**
     * @param someDay 几天
     * @return 得到过去几天的str的集合
     * 举例 输入 2 ，得到从昨天到前天两天的一个日期集合
     * 输入3， 得到 3天内的日期集合；
     * 17-11-27
     * 17-11-26
     * 17-11-25
     */
    
    public static List<String> getTodayToBeforeSomeDayListStr( int someDay ){
        List<String> listStrDate = new ArrayList<>( );
        for (int i = 1; i <= someDay; i++) {
            listStrDate.add(getBeforeSomeDayStr(i));
        }
        return listStrDate;
    }
    
}
