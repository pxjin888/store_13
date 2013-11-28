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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="store_user"
    ,catalog="store"
)

public class User  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 2315819918927569929L;
	private Integer userId;
     private String userName;
     private String password;
     private Set<Log> logs = new HashSet<Log>(0);
     private Set<Group> groups = new HashSet<Group>(0);


    // Constructors

    /** default constructor */
    public User() {
    }

    
    /** full constructor */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="user_id", unique=true, nullable=false)

    public Integer getUserId() {
        return this.userId;
    }
    
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    
    @Column(name="user_name", length=25)

    public String getUserName() {
        return this.userName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    @Column(name="password", length=25)

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="user")

    public Set<Log> getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set<Log> logs) {
        this.logs = logs;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="group_user_relation", catalog="store", joinColumns = { 
        @JoinColumn(name="user_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="group_id", nullable=false, updatable=false) })

    public Set<Group> getGroups() {
        return this.groups;
    }
    
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
   








}