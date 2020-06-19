package br.com.pedroxsqueiroz.ecommerce.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import br.com.pedroxsqueiroz.ecommerce.models.ShoppingCart;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "e_ commerce_queue"),
		@ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge") })
public class PaymentService implements MessageListener {
	
	@Override
	public void onMessage(Message message) {
		
		try {
			System.out.println( String.format(" payment invoked %s", message.getBody(String.class)));
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
