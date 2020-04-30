package in.yadav.springboot.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import in.yadav.springboot.model.Order;
import in.yadav.springboot.service.IUserServices;
import in.yadav.springboot.service.OrderService;

/**
 * REST controller for managing Order.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class OrderController {

	private final OrderService orderService;
	private final IUserServices userService;

	@Autowired
	public OrderController(OrderService orderService, IUserServices userService) {
		this.orderService = orderService;
		this.userService = userService;
	}

	
	@PostMapping("/orders")
	public ResponseEntity<Order> createOrder(@RequestBody Order order, Principal principal) {
		if (order.getOrderId() != null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		order.setUser(userService.findByUserName(principal.getName()));
		Order resultOrder = orderService.saveOrder(order);

		return new ResponseEntity<>(resultOrder, HttpStatus.CREATED);
	}

	
	@PutMapping("/orders")
	// @Secured(SecurityConstants.ADMIN)
	public ResponseEntity<Order> updateOrder(@RequestBody Order order/* , Principal principal */) {
//        if (order.getOrderId() == null) {
//            return createOrder(order, principal);
//        }
		orderService.saveOrder(order);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	
	@GetMapping("/orders")
	// @Secured(SecurityConstants.ADMIN)
	public ResponseEntity<List<Order>> getAllOrders() {
		List<Order> orders = orderService.findAllOrders();
		if (orders.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	
//	@RequestMapping("/orders/{id}")
//	@Secured(SecurityConstants.ADMIN)
//	public ResponseEntity<Order> getOrder(@PathVariable Long id) {
//		Order order = orderService.findOrderById(id);
//		if (order == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}

	
//	@DeleteMapping("/orders/{id}")
//	@Secured(SecurityConstants.ADMIN)
//	public ResponseEntity<Order> deleteOrder(@PathVariable Long id) {
//		Order order = orderService.findOrderById(id);
//		if (order == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		}
//
//		orderService.deleteOrder(order);
//		return new ResponseEntity<>(order, HttpStatus.OK);
//	}

	@GetMapping("/users/orders")
	// @Secured(SecurityConstants.ADMIN)
	public ResponseEntity<List<Order>> getOrdersByUser(@RequestParam String userName) {
		List<Order> orders = orderService.findByUserName(userName);
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/user/orders")
	public ResponseEntity<List<Order>> getOrdersByCurrentUser(Principal principal) {
		if (principal == null) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}

		List<Order> orders = orderService.findByUserName(principal.getName());
		return new ResponseEntity<>(orders, HttpStatus.OK);
	}
}