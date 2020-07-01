package cn.itlou.peisong.dto;

import lombok.Data;

/**
 * 用户DTO
 *
 * @author yuanyl
 * @date 2020/7/1 8:58
 **/
@Data
public class UserDTO {

    private String id;
    private String openid;
    private String nickname;
    private Integer gender;
    private String country;
    private String province;
    private String city;

}
