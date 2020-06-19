package br.com.pedroxsqueiroz.e_commerce_client.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pedroxsqueiroz.e_commerce_client.daos.ClientDao;
import br.com.pedroxsqueiroz.e_commerce_client.models.Client;

@Service
public class ClientService {

	@Autowired
	private ClientDao clientDao;
	
	public Client getByCredentials(final String email, final String password)
	{
		return this.clientDao.findAll((root, cb, qb) -> {
			return qb.and(
						qb.equal(root.get("email"), email),
						qb.equal(root.get("password"), password)
					);
		}).get(0);
	}

	@Transactional
	public Client create(Client client) {
		return this.clientDao.save(client);
	}
	
}
