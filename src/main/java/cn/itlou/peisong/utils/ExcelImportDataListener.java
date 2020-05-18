package cn.itlou.peisong.utils;

import cn.itlou.peisong.dto.HotelImportDTO;
import cn.itlou.peisong.mapper.HotelMapper;
import cn.itlou.peisong.service.HotelService;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Excel读取监听器
 *
 * @author yuanyl
 * @date 2020/5/18 16:13
 **/
@Slf4j
public class ExcelImportDataListener extends AnalysisEventListener<HotelImportDTO> {
    
    private static final int BATCH_COUNT = 500;
    List<HotelImportDTO> list = Lists.newArrayList();

    private HotelService hotelService;

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param hotelService
     */
    public ExcelImportDataListener(HotelService hotelService) {
        this.hotelService = hotelService;
    }
    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(HotelImportDTO data, AnalysisContext context) {
//        log.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
        log.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        for (HotelImportDTO hotelImportDTO : list) {
            String[] split = hotelImportDTO.getCoordinate().split(",");
            hotelImportDTO.setX(Double.valueOf(split[0]));
            hotelImportDTO.setY(Double.valueOf(split[1]));
        }
        Integer integer = hotelService.insertHotelInfo(list);
        log.info("成功存储数据库{}条！", integer);
        Long count = hotelService.insertHotelInfoToRedis(list);
        log.info("成功存储Redis{}条！", count);
    }
}
