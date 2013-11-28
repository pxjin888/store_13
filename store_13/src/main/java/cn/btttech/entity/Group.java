package cn.btttech.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="store_group"
    ,catalog="store"
)

public class Group  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = -7529645266630166287L;
	private Integer groupId;
     private Module module;
     private Operation operation;
     private String groupName;
     private Set<Log> logs = new HashSet<Log>(0);
     private Set<User> users = new HashSet<User>(0);


    // Constructors

    /** default constructor */
    public Group() {
    }

    
    /** full constructor */
    public Group(Module module, Operation operation, String groupName, Set<Log> logs, Set<User> users) {
        this.module = module;
        this.operation = operation;
        this.groupName = groupName;
        this.logs = logs;
        this.users = users;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="group_id", unique=true, nullable=false)

    public Integer getGroupId() {
        return this.groupId;
    }
    
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="module_id")

    public Module getModule() {
        return this.module;
    }
    
    public void setModule(Module module) {
        this.module = module;
    }
	@ManyToOne(fetch=FetchType.LAZY)
        @JoinColumn(name="operation_id")

    public Operation getOperation() {
        return this.operation;
    }
    
    public void setOperation(Operation operation) {
        this.operation = operation;
    }
    
    @Column(name="group_name", length=25)

    public String getGroupName() {
        return this.groupName;
    }
    
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="group")

    public Set<Log> getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="groups")

    public Set<User> getUsers() {
        return this.users;
    }
    
    public void setUsers(Set<User> users) {
        this.users = users;
    }
   








}