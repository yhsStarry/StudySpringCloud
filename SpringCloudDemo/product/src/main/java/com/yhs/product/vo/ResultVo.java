package com.yhs.product.vo;

import lombok.Data;

@Data
public class ResultVo<T> {
    /**
     * 错误码
     */
    private String code;
    /**
     * 提示信息
     */
    private String msg;
    /**
     * 具体内容
     */
    private T data;
}
