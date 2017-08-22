package se.mtm.distribution.theboringtodoapp.business.reminders.entity;


import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author luga
 */
@Entity
@Table(name = "todo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Todo.findAll", query = "SELECT t FROM Todo t")
    , @NamedQuery(name = "Todo.findById", query = "SELECT t FROM Todo t WHERE t.id = :id")
    , @NamedQuery(name = "Todo.findByCaption", query = "SELECT t FROM Todo t WHERE t.caption = :caption")
    , @NamedQuery(name = "Todo.findByPriority", query = "SELECT t FROM Todo t WHERE t.priority = :priority")
    , @NamedQuery(name = "Todo.findByDescription", query = "SELECT t FROM Todo t WHERE t.description = :description")
    , @NamedQuery(name = "Todo.findByDone", query = "SELECT t FROM Todo t WHERE t.done = :done")})
public class Todo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "caption")
    private String caption;
    @Column(name = "priority")
    private Integer priority;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "done")
    private Boolean done;

    public Todo() {
    }

    public Todo(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Todo)) {
            return false;
        }
        Todo other = (Todo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ""+id ;
    }
    
}

