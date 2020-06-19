package br.com.pedroxsqueiroz.e_commerce_client.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import br.com.pedroxsqueiroz.e_commerce_client.models.Client;

@Repository
public interface ClientDao extends JpaRepository<Client, Integer>, JpaSpecificationExecutor<Client>{

}
