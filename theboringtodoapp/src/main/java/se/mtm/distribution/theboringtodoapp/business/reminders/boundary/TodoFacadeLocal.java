package se.mtm.distribution.theboringtodoapp.business.reminders.boundary;

import java.util.List;

import se.mtm.distribution.theboringtodoapp.business.reminders.entity.Todo;

public interface TodoFacadeLocal {
	public void create(Todo entity);
	public void edit(Todo entity);
	public void remove(Todo entity);
	public Todo find(Object id);
	public List<Todo> findAll();

}
