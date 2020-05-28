package cn.itlou.peisong.mapper;

import cn.itlou.peisong.dto.HotelESDTO;
import cn.itlou.peisong.dto.HotelImportDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 酒店信息Mapper
 *
 * @author yuanyl
 * @date 2020/5/18 16:32
 **/
@Mapper
public interface HotelMapper {

//    @Insert("INSERT INTO t_hotel_info(name, city, address, graph_location)\n" +
//            "VALUES (#{name}, #{city}, #{address}, point(#{coordinate}));")
    @Insert("<script> INSERT INTO t_hotel_info(id, name, city, address, graph_location) values" +
            "<foreach collection='list' item='item' separator=','> " +
            "(#{item.id}, #{item.name, jdbcType=VARCHAR}, #{item.city, jdbcType=VARCHAR}, #{item.address, jdbcType=VARCHAR}, point(#{item.x}, #{item.y}))" +
            "</foreach> </script>")
    Integer insertHotelInfo(List<HotelImportDTO> list);

//    @Select("select name, address from t_hotel_info where id in()")
    @Select("<script> select name, address from t_hotel_info where id in(" +
            "<foreach collection='list' item='item' separator=','> " +
            "#{item}" +
            "</foreach>)" +
            "order by field(id, <foreach collection='list' item='item' separator=','>" +
            "#{item}</foreach>)</script>")
    List<HotelImportDTO> selectByIdList(List<String> list);

    @Select("select id, name, address, concat(x(graph_location), ',', y(graph_location)) location from t_hotel_info")
    List<HotelESDTO> selectAll();

}
