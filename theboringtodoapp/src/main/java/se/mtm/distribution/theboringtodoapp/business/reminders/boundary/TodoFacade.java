package se.mtm.distribution.theboringtodoapp.business.reminders.boundary;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import se.mtm.distribution.theboringtodoapp.business.reminders.entity.Todo;

/**
 *
 * @author luga
 */
@Stateless
@Remote(TodoFacadeRemote.class)
@Local(TodoFacadeLocal.class)
public class TodoFacade implements TodoFacadeRemote, TodoFacadeLocal {

    @PersistenceContext(unitName = "PU")
    private EntityManager em;
    protected EntityManager getEntityManager() {
        return em;
    }

    public TodoFacade() {
    }
    
    public void create(Todo entity) {
        getEntityManager().persist(entity);
    }

    public void edit(Todo entity) {
        getEntityManager().merge(entity);
    }

    public void remove(Todo entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public Todo find(Object id) {
        return getEntityManager().find(Todo.class, id);
    }
    
    //@NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t")
    public List<Todo> findAll(){
 	   return em.createNamedQuery("Todo.findAll", Todo.class).getResultList();
    }
    
    
    
    
    
    
}