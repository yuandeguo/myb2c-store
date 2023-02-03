package com.yuan.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuanyuan
 * @version V1.0
 * @date 2023/2/2 0:43
 * @Description 返回结果通用对象  Map
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class R implements Serializable {
    public static final Long serialVersionUID = 1L;


    /**
     * 通用成功状态码
     */
    public static final String SUCCESS_CODE = "001";
    /**
     * 失败状态码
     */
    public static final String FAIL_CODE = "004";
    /**
     * 未登录
     */
    public static final String USER_NO_LOGIN = "401";


    private String code;

//  JsonJsonInclude.Include.NON_NULL这个最常用，即如果加该注解的字段为null,那么就不序列化这个字段了。
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String msg;
    //不加 ：{"password":null,"age":null}
    //加了 就什么都没有



    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long   total;


    /**
     * 成功
     * @param msg
     * @param data
     * @return
     */
    public static R ok(String msg,Object data,Long total){

        return new R(SUCCESS_CODE,msg,data,total);
    }

    /**
     * 成功
     * @param data
     * @return
     */
    public static R ok(String msg,Object data){

        return ok(msg,data,null);
    }

    /**
     * 成功
     * @return
     */
    public static R ok(String msg){

        return ok(msg,null);
    }


    /**
     * 成功
     * @return
     */
    public static R ok(Object data){

        return ok(null,data);
    }



    /**
     * 失败
     * @param msg
     * @param data
     * @return
     */
    public static R fail(String msg,Object data,Long total){

        return new R(FAIL_CODE,msg,data,total);
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static R fail(String msg,Object data){

        return fail(msg,data,null);
    }

    /**
     * 失败
     * @return
     */
    public static R fail(String msg){

        return fail(msg,null);
    }


    /**
     * 失败
     * @return
     */
    public static R fail(Object data){

        return fail(null,data);
    }


    /**
     * 未登录
     * @return
     */
    public static R NO_LOGIN(){

        return fail(USER_NO_LOGIN,"用户未登录!");
    }





}
