package cn.itlou.peisong.utils;

import cn.itlou.peisong.dto.DatePickerDTO;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 时间选择器构造工具
 *
 * @author yuanyl
 * @date 2020/5/3 21:10
 **/
public class DatePickerGenerate {

    private DatePickerGenerate(){}

    public static DatePickerDTO generateDate(){
        List<String> monthAndDayList = Lists.newArrayList();
        List<String> hourList = Lists.newArrayList();
        List<String> timeList = Lists.newArrayList("00", "30");
        Calendar cal = Calendar.getInstance();
        for (int i = 0; i < 7; i++) {
            int hour = i + 7;
            String hourStr = hour < 10 ? "0" + hour : hour + "";
            hourList.add(hourStr);
        }
        for (int i = 0; i < 30; i++) {
            int day = cal.get(Calendar.DATE);
            int month = cal.get(Calendar.MONTH) + 1;
            int dow = cal.get(Calendar.DAY_OF_WEEK);
            String monthStr = month < 10 ? "0" + month : month + "";
            String dayStr = day < 10 ? "0" + day : day + "";
            String monthAndDay = monthStr + "月" + dayStr + "日";
            if (i == 0) {
                monthAndDay += " 今天";
            } else if (i == 1) {
                monthAndDay += " 明天";
            } else {
                monthAndDay += " 星期" + weekAndDate[dow - 1];
            }
            monthAndDayList.add(monthAndDay);
            cal.add(Calendar.DATE, 1);
        }
        DatePickerDTO datePickerDTO = new DatePickerDTO();
        datePickerDTO.setMonthAndDay(monthAndDayList);
        datePickerDTO.setHour(hourList);
        datePickerDTO.setTime(timeList);
        return datePickerDTO;
    }

    private static String[] weekAndDate = new String[]{"日", "一", "二", "三", "四", "五", "六"};

    public static void main(String[] args) {
        System.out.println(generateDate().toString());
    }

}
