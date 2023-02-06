package com.yuan.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/5 23:27
 * @Description null
 */
@Data
@TableName("admin_user")
public class AdminUser  implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userAccount;
    private String userPassword;
    private String userPhone;
    private Date createTime;
    private Integer userRole;

}
