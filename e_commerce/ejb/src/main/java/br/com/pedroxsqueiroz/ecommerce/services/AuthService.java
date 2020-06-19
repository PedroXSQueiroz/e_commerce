package br.com.pedroxsqueiroz.ecommerce.services;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.Stateless;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.io.DataOutputAsStream;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

import br.com.pedroxsqueiroz.ecommerce.models.User;
import io.netty.handler.ssl.JdkSslServerContext;

@Stateless
public class AuthService {

	public static String getParamsString(Map<String, String> params) 
      throws UnsupportedEncodingException{
        StringBuilder result = new StringBuilder();
 
        for (Map.Entry<String, String> entry : params.entrySet()) {
          result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
          result.append("=");
          result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
          result.append("&");
        }
 
        String resultString = result.toString();
        return resultString.length() > 0
          ? resultString.substring(0, resultString.length() - 1)
          : resultString;
    }
	
	public User auth(String email, String password) 
	{
		 try {
			
			 HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8081/clients/authenticate").openConnection();
			 
			 connection.setRequestMethod("POST");
			 connection.setDoOutput(true);
			 
			 Map<String, String> params = new HashMap<String, String>();
			 params.put("email", email);
			 params.put("password", password);
			 
			 settingParameters(connection, params);
			 
			 return getResponse(connection, User.class);
		 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
		
		
	}
	
	public User register(User user) 
	{
		try {
			
			 HttpURLConnection connection = (HttpURLConnection) new URL("http://localhost:8081/clients").openConnection();
			 
			 connection.setRequestMethod("POST");
			 connection.setDoOutput(true);
			 
			Map<String, String> params = new HashMap<String, String>();
			params.put("name", user.getName());
			params.put("password", user.getPassword());
			params.put("email", user.getEmail());
//			params.put("birthdate", user.getBirthdate().toLocaleString());
			params.put("gender", user.getGender());
			params.put("address", user.getAddress());
			params.put("postcode", user.getPassword());
			 
			 settingParameters(connection, params);
			 
			 return getResponse(connection, User.class);
		 
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return null;
	}

	private <T> T getResponse(HttpURLConnection connection, Class<T> responseType)
			throws IOException, JsonParseException, JsonMappingException {
		
		if(connection.getResponseCode() == 200) 
		{
			BufferedReader responseReader = new BufferedReader(new InputStreamReader(  connection.getInputStream() ));
			
			StringBuffer responseBuffer = new StringBuffer();
			
			String currentLine = null;
			
			while( (currentLine = responseReader.readLine()) != null ) 
			{
				responseBuffer.append(currentLine);
			}
			
			String response = responseBuffer.toString();
			
			ObjectMapper deserializer = new ObjectMapper();
			
			return deserializer.readValue(response, responseType);			
		}
		
		return null;
		
	}

	private void settingParameters(HttpURLConnection connection, Map<String, String> params)
		throws IOException, UnsupportedEncodingException {
		
		connection.setDoOutput(true);
		connection.setUseCaches(false);
		connection.setAllowUserInteraction(false);
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		
		DataOutputStream out = new DataOutputStream( connection.getOutputStream() );
		 
		 out.writeBytes(getParamsString(params));
		 
		 out.flush();
		 out.close();
	}
	
}
