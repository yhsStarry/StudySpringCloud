package com.yhs.order.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhs.order.dto.OrderDetail;
import com.yhs.order.dto.OrderMaster;
import com.yhs.order.enums.OrderStatusEnum;
import com.yhs.order.enums.PayStatusEnum;
import com.yhs.order.form.OrderForm;
import com.yhs.order.repository.OrderDetailRepository;
import com.yhs.order.repository.OrderMasterRepository;
import com.yhs.order.service.IOrderService;
import com.yhs.order.tmpl.OrderTmpl;
import com.yhs.order.utils.KeyUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderTmpl create(OrderTmpl orderTmpl) {

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderTmpl.getOrderMaster().setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderTmpl.getOrderMaster(), orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        return null;
    }
    //orderForm转换为OrderTmpl的方法
    public OrderTmpl orderFormToOrderTmpl(OrderForm orderForm){
        OrderTmpl orderTmpl = new OrderTmpl();
        orderTmpl.getOrderMaster().setBuyerName(orderForm.getName());
        orderTmpl.getOrderMaster().setBuyerPhone(orderForm.getPhone());
        orderTmpl.getOrderMaster().setBuyerAddress(orderForm.getAddress());
        orderTmpl.getOrderMaster().setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetails = new ArrayList<>();

        Gson gson = new Gson();
        try {
            gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){

        }
        return null;
    }

}
