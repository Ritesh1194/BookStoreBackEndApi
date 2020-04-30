package in.yadav.springboot.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import in.yadav.springboot.model.Order;

@Service
public interface OrderService {

	List<Order> findAllOrders();

	Optional<Order> findOrderById(Long orderId);

	Order saveOrder(Order order);

	void deleteOrder(Order order);

	List<Order> findByUserName(String userName);
}