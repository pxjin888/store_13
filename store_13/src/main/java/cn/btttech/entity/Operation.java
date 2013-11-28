package cn.btttech.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="store_operation"
    ,catalog="store"
)

public class Operation  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = -2288994483786239188L;
	private Integer operationId;
     private String operationName;
     private String operationColumnInvisible;
     private Integer operationType;
     private Set<Group> groups = new HashSet<Group>(0);


    // Constructors

    /** default constructor */
    public Operation() {
    }

    
    /** full constructor */
    public Operation(String operationName, String operationColumnInvisible, Integer operationType, Set<Group> groups) {
        this.operationName = operationName;
        this.operationColumnInvisible = operationColumnInvisible;
        this.operationType = operationType;
        this.groups = groups;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="operation_id", unique=true, nullable=false)

    public Integer getOperationId() {
        return this.operationId;
    }
    
    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }
    
    @Column(name="operation_name", length=25)

    public String getOperationName() {
        return this.operationName;
    }
    
    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
    
    @Column(name="operation_column_invisible", length=25)

    public String getOperationColumnInvisible() {
        return this.operationColumnInvisible;
    }
    
    public void setOperationColumnInvisible(String operationColumnInvisible) {
        this.operationColumnInvisible = operationColumnInvisible;
    }
    
    @Column(name="operation_type")

    public Integer getOperationType() {
        return this.operationType;
    }
    
    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="operation")

    public Set<Group> getGroups() {
        return this.groups;
    }
    
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
   








}