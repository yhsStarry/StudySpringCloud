package com.yhs.order.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhs.order.dto.OrderDetail;
import com.yhs.order.dto.OrderMaster;
import com.yhs.order.enums.OrderStatusEnum;
import com.yhs.order.enums.PayStatusEnum;
import com.yhs.order.enums.ResultEnum;
import com.yhs.order.exception.OrderException;
import com.yhs.order.form.OrderForm;
import com.yhs.order.repository.OrderDetailRepository;
import com.yhs.order.repository.OrderMasterRepository;
import com.yhs.order.service.IOrderService;
import com.yhs.order.tmpl.OrderTmpl;
import com.yhs.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Override
    public OrderTmpl create(OrderTmpl orderTmpl) {

        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderTmpl.setOrderId(KeyUtil.genUniqueKey());
        BeanUtils.copyProperties(orderTmpl, orderMaster);
        orderMaster.setOrderAmount(new BigDecimal(5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());

        orderMasterRepository.save(orderMaster);
        return orderTmpl;
    }
    //orderForm转换为OrderTmpl的方法
    public OrderTmpl orderFormToOrderTmpl(OrderForm orderForm){
        OrderTmpl orderTmpl = new OrderTmpl();


        orderTmpl.setBuyerName(orderForm.getName());
        orderTmpl.setBuyerPhone(orderForm.getPhone());
        orderTmpl.setBuyerAddress(orderForm.getAddress());
        orderTmpl.setBuyerOpenid(orderForm.getOpenid());

        List<OrderDetail> orderDetails = new ArrayList<>();

        Gson gson = new Gson();
        try {
            orderDetails = gson.fromJson(orderForm.getItems(),
                    new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e){
            log.error("【json转换】错误, string={}", orderForm.getItems());
            throw new OrderException(ResultEnum.PARAM_ERROR);
        }
        orderTmpl.setOrderDetailList(orderDetails);
        return orderTmpl;
    }

}
