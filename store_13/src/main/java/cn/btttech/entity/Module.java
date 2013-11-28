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
@Table(name="store_module"
    ,catalog="store"
)

public class Module  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 8840226184458827368L;
	private Integer moduleId;
     private String moduleName;
     private Integer parentId;
     private Integer leaf;
     private String link;
     private String icon;
     private Set<Group> groups = new HashSet<Group>(0);


    // Constructors

    /** default constructor */
    public Module() {
    }

    
    /** full constructor */
    public Module(String moduleName, Integer parentId, Integer leaf, String link, String icon, Set<Group> groups) {
        this.moduleName = moduleName;
        this.parentId = parentId;
        this.leaf = leaf;
        this.link = link;
        this.icon = icon;
        this.groups = groups;
    }

   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="module_id", unique=true, nullable=false)

    public Integer getModuleId() {
        return this.moduleId;
    }
    
    public void setModuleId(Integer moduleId) {
        this.moduleId = moduleId;
    }
    
    @Column(name="module_name", length=25)

    public String getModuleName() {
        return this.moduleName;
    }
    
    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }
    
    @Column(name="parent_id")

    public Integer getParentId() {
        return this.parentId;
    }
    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }
    
    @Column(name="leaf")

    public Integer getLeaf() {
        return this.leaf;
    }
    
    public void setLeaf(Integer leaf) {
        this.leaf = leaf;
    }
    
    @Column(name="link", length=50)

    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    @Column(name="icon", length=50)

    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="module")

    public Set<Group> getGroups() {
        return this.groups;
    }
    
    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }
   








}