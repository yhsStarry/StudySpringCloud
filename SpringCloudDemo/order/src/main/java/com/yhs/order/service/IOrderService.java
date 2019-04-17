package com.yhs.order.service;

import com.yhs.order.tmpl.OrderTmpl;

public interface IOrderService {

    /**
     * 创建订单
     * @param orderTmpl 包装类
     * @return 订单包装类
     */
    OrderTmpl create(OrderTmpl orderTmpl);
}
