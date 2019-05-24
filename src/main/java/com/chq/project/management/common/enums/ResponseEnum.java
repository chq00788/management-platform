package com.chq.project.management.common.enums;

/**
 * @author CHQ
 * @Description
 * @date 2019/5/15
 */
public enum ResponseEnum {

    /**
     * 请求成功
     */
    OK(200, "OK"),

    /**
     * 请求错误
     */
    BAD_REQUEST(400, "请求错误"),

    /**
     * 未认证
     */
    UNAUTHORIZED(401, "未认证,请重新登录"),


    /**
     * 无权访问
     */
    FORBIDDEN(403, "暂无权限"),


    /**
     * 访问路径不存在
     */
    NOT_FOUND(404, "访问路径不存在"),

    /**
     * 服务器错误
     */
    INTERNAL_SERVER_ERROR(500, "请求异常,请稍后再试");


    private Integer code;

    private String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
