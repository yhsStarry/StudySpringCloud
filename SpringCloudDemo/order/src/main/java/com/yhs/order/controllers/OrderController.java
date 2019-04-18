package com.yhs.order.controllers;

import com.yhs.order.enums.ResultEnum;
import com.yhs.order.exception.OrderException;
import com.yhs.order.form.OrderForm;
import com.yhs.order.service.impl.OrderServiceImpl;
import com.yhs.order.tmpl.OrderTmpl;
import com.yhs.order.utils.ResultVoUtil;
import com.yhs.order.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/order")
@Slf4j
public class OrderController {

    @Autowired
    private OrderServiceImpl orderService;
    /**
     * 1. 参数检验
     * 2. 查询商品信息(调用商品服务)
     * 3. 计算总价
     * 4. 扣库存(调用商品服务)
     * 5. 订单入库
     */
    @PostMapping("/create")
    public ResultVo<Map<String, String>> createOrder(@Valid OrderForm orderForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            log.error("【创建订单】参数不正确, orderForm={}", orderForm);
            throw new OrderException(ResultEnum.PARAM_ERROR.getCode(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        //数据转换
        OrderTmpl orderTmpl = orderService.orderFormToOrderTmpl(orderForm);
        if (CollectionUtils.isEmpty(orderTmpl.getOrderDetailList())) {
            log.error("");
            throw new OrderException(ResultEnum.CART_EMPTY);
        }

        OrderTmpl result = orderService.create(orderTmpl);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", result.getOrderMaster().getOrderId());
        return ResultVoUtil.success(map);
    }
}
