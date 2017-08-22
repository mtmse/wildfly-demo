package se.mtm.distribution.theboringtodoapp.business.messages.boundary;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.Queue;
/**
*
* @author luga
*/
@Remote(MessageSenderRemote.class)
@Local(MessageSenderLocal.class)
@Stateless
public class MessageSender implements MessageSenderLocal, MessageSenderRemote {

	@Inject
	JMSContext context;

	@Resource(mappedName = TodoQueueDefinition.QUEUE)
	private Queue syncQueue;

	public void sendMessage(String message) {
		context.createProducer().send(syncQueue, message);
		System.out.println("Sending " + message);
	}
}
