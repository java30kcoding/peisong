package cn.itlou.peisong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * ID生成器
 *
 * @author yuanyl
 * @date 2020/7/1 15:03
 **/
public class IdUtil {

    private IdUtil() {
    }

    public static String genId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String newDate = sdf.format(new Date());
        StringBuilder result = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            result.append(random.nextInt(10));
        }
        return newDate + System.currentTimeMillis() + result;
    }

}
