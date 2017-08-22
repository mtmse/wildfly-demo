package se.mtm.distribution.theremotetodoapp.presentation.reminders;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import se.mtm.distribution.theboringtodoapp.business.messages.boundary.MessageSenderRemote;
import se.mtm.distribution.theboringtodoapp.business.reminders.boundary.TodoFacadeRemote;
import se.mtm.distribution.theboringtodoapp.business.reminders.entity.Todo;

@Named("todospresenter")
@SessionScoped
public class TodosPresenter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	TodoFacadeRemote facade;
	MessageSenderRemote messageSender;
	private List<Todo> items = null;
	private Todo selected;
	private String message = "";

	@PostConstruct
	public void init() {
		facade = lookupRemoteTodos();
		messageSender = lookupMessageSender();

	}

	private MessageSenderRemote lookupMessageSender() {

		try {
			final Properties jndiProperties = new Properties();
			jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			return (MessageSenderRemote) context.lookup(
					"ejb:/theboringtodoapp/MessageSender!se.mtm.distribution.theboringtodoapp.business.messages.boundary.MessageSenderRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private TodoFacadeRemote lookupRemoteTodos() {

		try {
			final Properties jndiProperties = new Properties();
			jndiProperties.setProperty(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			final Context context = new InitialContext(jndiProperties);
			return (TodoFacadeRemote) context.lookup(
					"ejb:/theboringtodoapp/TodoFacade!se.mtm.distribution.theboringtodoapp.business.reminders.boundary.TodoFacadeRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public TodosPresenter() {
	}

	public Todo getSelected() {
		return selected;
	}

	public void setSelected(Todo selected) {
		this.selected = selected;
	}

	public Todo prepareCreate() {
		selected = new Todo();
		return selected;
	}

	public List<Todo> getItems() {
		if (items == null) {
			items = facade.findAll();
		}
		return items;
	}

	public void create() {
		facade.create(selected);
		items = null;
	}

	public void update() {
		facade.edit(selected);
		items = null;
	}

	public void destroy() {
		facade.remove(selected);
		items = null;
	}

	public void setItems(List<Todo> items) {
		this.items = items;
	}

	public void sendMessage() {
		messageSender.sendMessage(message);
		FacesMessage facesMessage = new FacesMessage("Sent message: " + message);
		FacesContext.getCurrentInstance().addMessage(null, facesMessage);
	}

}
