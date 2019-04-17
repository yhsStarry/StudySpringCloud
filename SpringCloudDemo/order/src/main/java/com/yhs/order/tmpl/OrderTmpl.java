package com.yhs.order.tmpl;

import com.yhs.order.dto.OrderDetail;
import com.yhs.order.dto.OrderMaster;
import lombok.Data;

import java.util.List;

@Data
public class OrderTmpl {
    private OrderMaster orderMaster;
    private OrderDetail orderDetail;
    private List<OrderDetail> orderDetailList;
}
