package br.com.pedroxsqueiroz.ecommerce.services;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.ejb.Stateless;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.pedroxsqueiroz.ecommerce.models.User;

@Stateless
public class ClientService {
	
	public User login(String email, String password) throws IOException 
	{
		URL url = new URL("http://localhost:8081/e_commerce_client/clients/authenticate");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ObjectNode loginParams = JsonNodeFactory.instance.objectNode();
		loginParams.put("email", email);
		loginParams.put("password", password);
		byte[] loginParamsBytes = objectMapper.writeValueAsBytes(loginParams);
		
		String responseMessage = conn.getResponseMessage();
		User user = objectMapper.readValue(responseMessage, User.class);
		
		return user;
		
	}
	
	public User register(User user) throws IOException 
	{
		URL url = new URL("http://localhost:8081/e_commerce_client/clients");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod("POST");
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		ObjectNode loginParams = JsonNodeFactory.instance.objectNode();
		loginParams.put("name", user.getName());
		loginParams.put("password", user.getPassword());
		loginParams.put("birthdate", user.getBirthdate().toLocaleString());
		loginParams.put("gender", user.getGender());
		loginParams.put("address", user.getAddress());
		loginParams.put("postcode", user.getPassword());
		
		byte[] loginParamsBytes = objectMapper.writeValueAsBytes(loginParams);
		
		String responseMessage = conn.getResponseMessage();
		User registeredUser = objectMapper.readValue(responseMessage, User.class);
		
		return registeredUser;
	}
	
}
