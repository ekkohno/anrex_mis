package com.ekkohno.anrex_back.dto;

import com.ekkohno.anrex_back.entities.Address;
import com.ekkohno.anrex_back.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {

    private UUID id;
    private Date orderDate;
    private Address address;
    private Double totalAmount;
    private OrderStatus orderStatus;
    private String shipmentNumber;
    private Date expectedDeliveryDate;
    private List<OrderItemDetail> orderItemList;

}