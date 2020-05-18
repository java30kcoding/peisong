package cn.itlou.peisong.controller;

import cn.itlou.peisong.dto.HotelImportDTO;
import cn.itlou.peisong.service.HotelService;
import cn.itlou.peisong.utils.ExcelImportDataListener;
import com.alibaba.excel.EasyExcel;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStream;

/**
 * excel导入类
 *
 * @author yuanyl
 * @date 2020/5/18 16:10
 **/
@RestController
public class ExcelImportController {

    @Resource
    private HotelService hotelService;

    @PostMapping("/insertHotelInfo")
    public void insertHotelInfo(@RequestBody MultipartFile multipartFile){
        try {
            InputStream inputStream = multipartFile.getInputStream();
            EasyExcel.read(inputStream, HotelImportDTO.class, new ExcelImportDataListener(hotelService)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
