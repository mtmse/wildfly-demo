package se.mtm.distribution.theboringtodoapp.business.messages.boundary;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(name="MessagesQueueReceiver", activationConfig= {
		@ActivationConfigProperty(propertyName="destinationLookup", propertyValue=TodoQueueDefinition.QUEUE),
		@ActivationConfigProperty( propertyName="destinationType", propertyValue="javax.jms.Queue"),})
public class MessagesQueueReceiver implements MessageListener {
	

	@Override
	public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                final String msg = message.getBody(String.class);
                System.out.println("Received message " + msg);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
            
        }
		
	}
	

}
