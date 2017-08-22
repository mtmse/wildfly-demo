package se.mtm.distribution.theboringtodoapp.presentation.reminders;

import java.io.Serializable;
import java.util.List;


import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import se.mtm.distribution.theboringtodoapp.business.messages.boundary.MessageSenderLocal;
import se.mtm.distribution.theboringtodoapp.business.reminders.boundary.TodoFacadeLocal;
import se.mtm.distribution.theboringtodoapp.business.reminders.entity.Todo;
/**
*
* @author luga
*/
@Named("todopresenter")
@SessionScoped
public class TodoPresenter implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	TodoFacadeLocal facade;
	@Inject
	MessageSenderLocal messageSender;
	private List<Todo> items = null;
	private Todo selected;
	private String message = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public TodoPresenter() {
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
