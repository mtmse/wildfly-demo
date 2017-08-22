package se.mtm.distribution.theboringtodoapp.business.messages.boundary;

import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition(name="TodoQueueDefinition.QUEUE", interfaceName="javax.jms.Queue" )
public class TodoQueueDefinition {
	public static final String QUEUE = "java:jboss/jms/queue/todoMessageQueue";
}
