package service;

import java.util.List;

import model.CartItem;

public interface CartItemService {
	void insert(CartItem cartItem);

	void delete(String id);

	List<CartItem> get(int id);
	
	List<CartItem> getAll();

	List<CartItem> search(String keyword);
}
