package com.zzqx.mvc.service;

import com.zzqx.mvc.entity.Order;

public interface OrderService extends BaseService<Order> {
	Order getCurrent();
}