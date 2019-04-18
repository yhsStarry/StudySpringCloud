package com.yhs.order.utils;

import com.yhs.order.vo.ResultVo;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;

public class ResultVoUtil {
    public static ResultVo success(Object object){
        ResultVo resultVo = new ResultVo();
        resultVo.setData(object);
        String string = ObjectUtils.identityToString(object);
        if (StringUtils.isNotEmpty(string)) {
            resultVo.setCode("0");
            resultVo.setMsg("成功");
        } else {
            resultVo.setCode("1");
            resultVo.setMsg("未查询到有效数据");
        }
        return resultVo;
    }
}
