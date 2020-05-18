package cn.itlou.peisong.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户信息Mapper
 *
 * @author yuanyl
 * @date 2020/5/13 10:13
 **/
@Mapper
public interface UserMapper {

    @Insert("")
    Integer insertUserInfo();

}
