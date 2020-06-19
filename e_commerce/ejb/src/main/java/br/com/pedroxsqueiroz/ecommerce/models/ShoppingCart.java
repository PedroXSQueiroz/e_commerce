package br.com.pedroxsqueiroz.ecommerce.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;

@Stateful
@SessionScoped
public class ShoppingCart {

	public ShoppingCart() {
		this.products = new HashMap<ProductModel, Integer>();
	}
	
	private Map<ProductModel, Integer> products;
	
	public Map<ProductModel, Integer> getProducts()
	{
		Map auxiliar = new HashMap<ProductModel, Integer>();
		
		auxiliar.putAll(products);
		
		return auxiliar;
	}
	
	public void add(ProductModel product)
	{
		Integer quantity = !products.containsKey(product) ? 
				1 :
				products.get(product) + 1;
		
		this.products.put(product, quantity);
	}
	
	public void remove(ProductModel product) 
	{
		Integer quantity = this.products.get(product);
		
		if(quantity > 1) 
		{
			this.products.put(product, quantity - 1);
		}
		else
		{
			this.products.remove(product);			
		}
		
	}
	
	public Double getTotal() 
	{
		return this.products.entrySet().stream().mapToDouble( (productToquantity) -> { 
			
			ProductModel product = productToquantity.getKey();
			
			double price = (double) product.getPrice();
			double quantity = (double) productToquantity.getValue();
			
			return price * quantity;
		}).sum();
		
	}
	
	public void clear() 
	{
		this.products.clear();
	}
}
