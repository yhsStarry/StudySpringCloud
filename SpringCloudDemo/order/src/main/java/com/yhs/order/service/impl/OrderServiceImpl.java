package com.yhs.order.service.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yhs.order.client.ProductClient;
import com.yhs.order.dto.OrderDetail;
import com.yhs.order.dto.OrderMaster;
import com.yhs.order.dto.Product;
import com.yhs.order.enums.OrderStatusEnum;
import com.yhs.order.enums.PayStatusEnum;
import com.yhs.order.enums.ResultEnum;
import com.yhs.order.exception.OrderException;
import com.yhs.order.form.OrderForm;
import com.yhs.order.repository.OrderDetailRepository;
import com.yhs.order.repository.OrderMasterRepository;
import com.yhs.order.service.IOrderService;
import com.yhs.order.tmpl.CartTmpl;
import com.yhs.order.tmpl.OrderTmpl;
import com.yhs.order.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private OrderMasterRepository orderMasterRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductClient productClient;

    @Override
    public OrderTmpl create(OrderTmpl orderTmpl) {

        String orderId = KeyUtil.genUniqueKey();
        //查询商品信息
        List<String> productIdList = orderTmpl.getOrderDetailList().stream()
                .map(OrderDetail::getProductId).collect(Collectors.toList());
        List<Product> productList = productClient.selectForOrder(productIdList);

        //计算总价
        BigDecimal amount = BigDecimal.ZERO;
        for (OrderDetail orderDetail : orderTmpl.getOrderDetailList()) {
            for (Product product : productList) {
                if (product.getProductId().equals(orderDetail.getProductId())) {
                    amount = product.getProductPrice()
                            .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                            .add(amount);
                    BeanUtils.copyProperties(product, orderDetail);
                    orderDetail.setOrderId(orderId);
                    orderDetail.setDetailId(KeyUtil.genUniqueKey());
                    //订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }
        //扣库存
        List<CartTmpl> cartTmpls = orderTmpl.getOrderDetailList().stream().map(e -> new CartTmpl(e.getProductId(),
                e.getProductQuantity())).collect(Collectors.toList());
        productClient.decreastStock(cartTmpls);
        //订单入库
        OrderMaster orderMaster = new OrderMaster();
        orderTmpl.setOrderId(orderId);
        BeanUtils.copyProperties(orderTmpl, orderMaster);
        orderMaster.setOrderAmount(amount);
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
