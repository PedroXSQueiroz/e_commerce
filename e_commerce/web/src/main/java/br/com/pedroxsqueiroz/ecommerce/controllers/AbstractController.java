package br.com.pedroxsqueiroz.ecommerce.controllers;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pedroxsqueiroz.ecommerce.models.AbstractModel;
import br.com.pedroxsqueiroz.ecommerce.services.AbstractService;

public abstract class AbstractController<T extends AbstractModel> extends HttpServlet {

	protected abstract AbstractService<T> getService();

	protected abstract String getTitle(Boolean isListing);

	protected abstract String getResourceName();

	private T getNewResource() throws InstantiationException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		
		Class<T> type = this.getResourceClass();

		T newInstance = type.getConstructor().newInstance();

		return newInstance;
	}

	private Class<T> getResourceClass() {
		ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
		Class<T> type = (Class<T>) superClass.getActualTypeArguments()[0];
		return type;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String resourceName = this.getResourceName();

		req.setAttribute("resourceName", resourceName);

		AbstractService<T> service = this.getService();

		String productId = req.getParameter("id");
		
		if ( productId != null && !productId.isEmpty() ) {
			req.setAttribute("title", this.getTitle(false));
			
			try {
				
				T product = "new".equals(productId) ? this.getNewResource() : service.get(Integer.valueOf(productId));

				req.setAttribute("resource", product);
				
			} catch (InstantiationException e) {

				e.printStackTrace();

				// TODO: redirecionar para página de erro

			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			req.getRequestDispatcher("/WEB-INF/pages/form/" + resourceName + ".jsp").forward(req, resp);

		} else {
			req.setAttribute("title", this.getTitle(true));

			List<T> list = service.get();

			req.setAttribute("resources", list);

			req.getRequestDispatcher("/WEB-INF/pages/grid/" + resourceName + ".jsp").forward(req, resp);
		}

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		AbstractService<T> service = this.getService();
		
		String idStr = req.getParameter("id");
		
		String action = req.getParameter("action");
		
		if("delete".equals(action)) 
		{
			service.delete( Integer.valueOf(idStr));
		}
		else
		{
			try {
				
				T resource = idStr != null && !idStr.isEmpty() ? service.get(Integer.valueOf(idStr)) : this.getNewResource();
				
				Map<String, String[]> parameterMap = req.getParameterMap();
				
				populateResource(resource, parameterMap);
				
				service.save(resource);
				
				
				
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		
		WebServlet annotation = this.getClass().getAnnotation(WebServlet.class);
		String listRoute = annotation.urlPatterns()[0];
		
		resp.sendRedirect(listRoute + "?id=new");
		
	}

	protected void populateResource(T resource, Map<String, String[]> parameterMap)
	{
		for(Entry<String, String[]> parameter : parameterMap.entrySet()) 
		{
			try {
				String property = parameter.getKey();
				PropertyDescriptor propertyDescriptor = new PropertyDescriptor(property, this.getResourceClass());
				Class<?> propertyType = propertyDescriptor.getPropertyType();
				
				Object value = this.parse(parameter.getValue()[0], propertyType);
				
				propertyDescriptor.getWriteMethod().invoke(resource, value);
				
			} catch (IntrospectionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				continue;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Object parse(String value, Class<?> propertyType) 
	{
		if(value.isEmpty()) 
		{
			return null;
		}
		
		if(Integer.class.equals(propertyType)) 
		{
			return Integer.valueOf(value);
		}else if(Float.class.equals(propertyType)) 
		{
			return Float.valueOf(value);
		}else
		{
			return value;
		}
	}

}
