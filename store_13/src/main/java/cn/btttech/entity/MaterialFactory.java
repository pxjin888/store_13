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
@Table(name="store_material_factory"
    ,catalog="store"
)

public class MaterialFactory  implements java.io.Serializable {


    // Fields    
	 private static final long serialVersionUID = 6373344643201908903L;
	 private Integer materialFactoryId;
     private String materialFactoryCode;
     private String materialFactoryName;
     private String materialFactoryAddress;
     private String materialFactoryContactName;
     private String materialFactoryTitle;
     private Long materialFactoryContactphone;
     private String materialFactoryContactOther;
     private String materialFactoryMail;
     private String materialFactoryWeb;
     private Set<MaterialPrice> materialPrices = new HashSet<MaterialPrice>(0);
     private Set<LogMaterial> logs = new HashSet<LogMaterial>(0);


    // Constructors

    /** default constructor */
    public MaterialFactory() {
    }

    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="material_factory_id", unique=true, nullable=false)

    public Integer getMaterialFactoryId() {
        return this.materialFactoryId;
    }
    
    public void setMaterialFactoryId(Integer materialFactoryId) {
        this.materialFactoryId = materialFactoryId;
    }
    
    @Column(name="material_factory_code", length=25)

    public String getMaterialFactoryCode() {
        return this.materialFactoryCode;
    }
    
    public void setMaterialFactoryCode(String materialFactoryCode) {
        this.materialFactoryCode = materialFactoryCode;
    }
    
    @Column(name="material_factory_name", length=25)

    public String getMaterialFactoryName() {
        return this.materialFactoryName;
    }
    
    public void setMaterialFactoryName(String materialFactoryName) {
        this.materialFactoryName = materialFactoryName;
    }
    
    @Column(name="material_factory_address", length=25)

    public String getMaterialFactoryAddress() {
        return this.materialFactoryAddress;
    }
    
    public void setMaterialFactoryAddress(String materialFactoryAddress) {
        this.materialFactoryAddress = materialFactoryAddress;
    }
    
    @Column(name="material_factory_contact_name", length=25)

    public String getMaterialFactoryContactName() {
        return this.materialFactoryContactName;
    }
    
    public void setMaterialFactoryContactName(String materialFactoryContactName) {
        this.materialFactoryContactName = materialFactoryContactName;
    }
    
    @Column(name="material_factory_title", length=25)

    public String getMaterialFactoryTitle() {
        return this.materialFactoryTitle;
    }
    
    public void setMaterialFactoryTitle(String materialFactoryTitle) {
        this.materialFactoryTitle = materialFactoryTitle;
    }
    
    @Column(name="material_factory_contactphone")

    public Long getMaterialFactoryContactphone() {
        return this.materialFactoryContactphone;
    }
    
    public void setMaterialFactoryContactphone(Long materialFactoryContactphone) {
        this.materialFactoryContactphone = materialFactoryContactphone;
    }
    
    @Column(name="material_factory_contact_other", length=25)

    public String getMaterialFactoryContactOther() {
        return this.materialFactoryContactOther;
    }
    
    public void setMaterialFactoryContactOther(String materialFactoryContactOther) {
        this.materialFactoryContactOther = materialFactoryContactOther;
    }
    
    @Column(name="material_factory_mail", length=25)

    public String getMaterialFactoryMail() {
        return this.materialFactoryMail;
    }
    
    public void setMaterialFactoryMail(String materialFactoryMail) {
        this.materialFactoryMail = materialFactoryMail;
    }
    
    @Column(name="material_factory_web", length=50)

    public String getMaterialFactoryWeb() {
        return this.materialFactoryWeb;
    }
    
    public void setMaterialFactoryWeb(String materialFactoryWeb) {
        this.materialFactoryWeb = materialFactoryWeb;
    }

    @ManyToMany(fetch = FetchType.LAZY,cascade = {CascadeType.MERGE,CascadeType.REFRESH})
	@JoinTable(name = "materialprice_materialfactory_relation", catalog = "store", joinColumns = { @JoinColumn(name = "material_factory_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "material_price_id", nullable = false, updatable = false) })
	public Set<MaterialPrice> getMaterialPrices() {
		return this.materialPrices;
	}

	public void setMaterialPrices(Set<MaterialPrice> materialPrices) {
		this.materialPrices = materialPrices;
	}
    
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "materialFactory")
    public Set<LogMaterial> getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set<LogMaterial> logs) {
        this.logs = logs;
    }
   








}