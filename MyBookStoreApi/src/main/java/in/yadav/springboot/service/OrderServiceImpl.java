package in.yadav.springboot.service;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.yadav.springboot.model.Order;
import in.yadav.springboot.model.OrderBook;
import in.yadav.springboot.model.User;
import in.yadav.springboot.repository.IUserRepository;
import in.yadav.springboot.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository orderRepository;

	private final IUserRepository userRepository;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, IUserRepository userRepository) {
		this.orderRepository = orderRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Order> findAllOrders() {
		return (List<Order>) orderRepository.findAll();
	}

	@Override
	public java.util.Optional<Order> findOrderById(Long orderId) {
		return this.orderRepository.findById(orderId);
	}

	@Override
	public Order saveOrder(Order order) {
		for (OrderBook orderBook : order.getBooks()) {
			orderBook.setOrder(order);
			orderBook.getBook().getOrders().add(orderBook);
		}

		order.setOrderDate(Calendar.getInstance().getTime());

		return orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Order order) {
		orderRepository.delete(order);
	}

	@Override
	public List<Order> findByUserName(String userName) {
		User user = userRepository.findByUserName(userName);
		return orderRepository.findByUser(user);
	}
}