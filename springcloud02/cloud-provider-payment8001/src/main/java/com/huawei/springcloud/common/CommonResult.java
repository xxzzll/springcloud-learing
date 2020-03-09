package com.huawei.springcloud.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author xixi
 * @Description： 前端公共返回实体类
 * @create 2020/3/9
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> implements Serializable {
    private int code;
    private String message;

    private T data;

    public CommonResult(int code, String message){
        this(code, message, null);
    }
}
