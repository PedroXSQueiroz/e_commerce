package br.com.pedroxsqueiroz.e_commerce_client.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.pedroxsqueiroz.e_commerce_client.dtos.AuthForm;
import br.com.pedroxsqueiroz.e_commerce_client.models.Client;
import br.com.pedroxsqueiroz.e_commerce_client.services.ClientService;

@RestController
@RequestMapping(value = "clients")
public class ClientController {

	@Autowired
	private ClientService service;
	
	@PostMapping(value = "/authenticate")
	@ResponseBody
	public Client getByCredentials( AuthForm authform ) 
	{
		String email = authform.getEmail();
		String password = authform.getPassword();
		
		return this.service.getByCredentials(email, password);
	}
	
	@PostMapping()
	@ResponseBody
	public Client create(Client client) 
	{
		return this.service.create(client);
	}
	
}
