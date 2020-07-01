package cn.itlou.peisong.mapper;

import cn.itlou.peisong.dto.UserDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * 用户信息Mapper
 *
 * @author yuanyl
 * @date 2020/5/13 10:13
 **/
@Mapper
public interface UserMapper {

    @Insert("insert into t_sys_user values (#{id}, #{openid} ,#{nickname}, #{gender}," +
            "#{country}, #{province}, #{city})")
    Integer insertUserInfo(UserDTO userDTO);

    @Select("select count(*) from t_sys_user where openid = #{openid}")
    Integer countByOpenid(UserDTO userDTO);

}
