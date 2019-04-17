package com.yhs.order.repository;

import com.yhs.order.OrderApplicationTests;
import com.yhs.order.dto.OrderMaster;
import com.yhs.order.enums.OrderStatusEnum;
import com.yhs.order.enums.PayStatusEnum;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OrderMasterRepositoryTest extends OrderApplicationTests {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Test
    public void testSave(){
        OrderMaster orderMaster = new OrderMaster();
        orderMaster.setOrderId("1234567");
        orderMaster.setBuyerName("yhs");
        orderMaster.setBuyerPhone("12345654345");
        orderMaster.setBuyerAddress("三亚");
        orderMaster.setBuyerOpenid("1101110");
        orderMaster.setOrderAmount(new BigDecimal(22.5));
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());


        OrderMaster result = orderMasterRepository.save(orderMaster);
        Assert.assertTrue(result != null);

    }
}