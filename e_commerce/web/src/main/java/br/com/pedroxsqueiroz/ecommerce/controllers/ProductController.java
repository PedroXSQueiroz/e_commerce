package br.com.pedroxsqueiroz.ecommerce.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pedroxsqueiroz.ecommerce.models.ProductModel;
import br.com.pedroxsqueiroz.ecommerce.models.ShoppingCart;
import br.com.pedroxsqueiroz.ecommerce.services.AbstractService;
import br.com.pedroxsqueiroz.ecommerce.services.ProductService;

@WebServlet(urlPatterns = {"products"})
public class ProductController extends AbstractController<ProductModel> {

	private final static String CATEGORY_PARAMETER_KEY = "category";
	
	@Inject
	private ShoppingCart shoppingCart;
	
	@Inject
	private ProductService service;
	
	@Override
	protected AbstractService<ProductModel> getService() {
		return this.service;
	}
	
	@Override
	protected String getResourceName() {
		return "product";
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Map<ProductModel, Integer> products = this.shoppingCart.getProducts();
		
		req.setAttribute("productsInCart", products);
		
		req.setAttribute("totalInCart", this.shoppingCart.getTotal());
		
		String productId = req.getParameter("id");
		
		if ( productId != null && !productId.isEmpty() ) 
		{
			List<ProductModel> list = this.service.get();
			
			if(!"new".equals(productId)) 
			{
				list.removeIf( prod -> prod.getId() == Integer.valueOf( productId ) );				
			}
			
			req.setAttribute("resources", list);			
		}
		
		
		super.doGet(req, resp);
	}

	@Override
	protected String getTitle(Boolean isListing) {
		return isListing ? "Produtos" : "produto";
	}

	
	
}
