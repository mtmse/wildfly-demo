package se.mtm.distribution.theboringclient.boundary;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
 
import se.mtm.distribution.theboringtodoapp.business.messages.boundary.MessageSenderRemote;
import se.mtm.distribution.theboringtodoapp.business.reminders.boundary.TodoFacadeRemote;
import se.mtm.distribution.theboringtodoapp.business.reminders.entity.Todo;
/**
*
* @author luga
*/
public class TheBoringClient {

	public static void main(String[] args) throws NamingException{
		invokeTodos();
	}

	private static void invokeTodos() throws NamingException {
		final TodoFacadeRemote remotefacade = lookupRemoteTodos();
		List<Todo> todos = remotefacade.findAll();
		System.out.println("\n\n\n");
		System.out.println("Total Remainders : "+todos.size());
		for (Todo todo : todos) {
			System.out.format("\nCaption : %s \nDescription : %s \nPriority : %d \nDone : %s \n", todo.getCaption(), todo.getDescription(), todo.getPriority(), todo.getDone());
		}
		final MessageSenderRemote message = lookupMessageSender();
		message.sendMessage("Hello from the other side");
	}

	private static TodoFacadeRemote lookupRemoteTodos() throws NamingException {
		final Properties jndiProperties = new Properties();
		jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		return (TodoFacadeRemote) context.lookup(
				"ejb:/theboringtodoapp/TodoFacade!se.mtm.distribution.theboringtodoapp.business.reminders.boundary.TodoFacadeRemote");
	}
	
	private static MessageSenderRemote lookupMessageSender() throws NamingException {
		final Properties jndiProperties = new Properties();
		jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		final Context context = new InitialContext(jndiProperties);
		return (MessageSenderRemote) context.lookup(
				"ejb:/theboringtodoapp/MessageSender!se.mtm.distribution.theboringtodoapp.business.messages.boundary.MessageSenderRemote");
	}

}
