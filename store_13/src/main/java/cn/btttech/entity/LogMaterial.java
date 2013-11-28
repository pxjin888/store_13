package cn.btttech.entity;



import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


@Entity
@DiscriminatorValue("logMaterial")

public class LogMaterial extends Log {

	private static final long serialVersionUID = -7221540132810022547L;
	private Float logMaterialNum;
     private Float logMaterialInputPrice;
     private String logMaterialUse;
     private String logMaterialPosition;
     private String logMaterialProject;
     private String logBuyRequirecode;
     private String logBuyAgreementcode;
     
     private MaterialFactory materialFactory;
     private Material material;
     

    // Constructors

    /** default constructor */
    public LogMaterial() {
    }

    
    @Column(name="log_material_num", precision=12, scale=0)

    public Float getLogMaterialNum() {
        return this.logMaterialNum;
    }
    
    public void setLogMaterialNum(Float logMaterialNum) {
        this.logMaterialNum = logMaterialNum;
    }
    
    @Column(name="log_material_input_price", precision=12, scale=0)

    public Float getLogMaterialInputPrice() {
        return this.logMaterialInputPrice;
    }
    
    public void setLogMaterialInputPrice(Float logMaterialInputPrice) {
        this.logMaterialInputPrice = logMaterialInputPrice;
    }
    
    @Column(name="log_material_use", length=25)

    public String getLogMaterialUse() {
        return this.logMaterialUse;
    }
    
    public void setLogMaterialUse(String logMaterialUse) {
        this.logMaterialUse = logMaterialUse;
    }
    
    @Column(name="log_material_position", length=25)

    public String getLogMaterialPosition() {
        return this.logMaterialPosition;
    }
    
    public void setLogMaterialPosition(String logMaterialPosition) {
        this.logMaterialPosition = logMaterialPosition;
    }
    
    @Column(name="log_material_project", length=25)

    public String getLogMaterialProject() {
        return this.logMaterialProject;
    }
    
    public void setLogMaterialProject(String logMaterialProject) {
        this.logMaterialProject = logMaterialProject;
    }
    
    @Column(name="log_buy_requirecode", length=25)

    public String getLogBuyRequirecode() {
        return this.logBuyRequirecode;
    }
    
    public void setLogBuyRequirecode(String logBuyRequirecode) {
        this.logBuyRequirecode = logBuyRequirecode;
    }
    
    @Column(name="log_buy_agreementcode", length=25)

    public String getLogBuyAgreementcode() {
        return this.logBuyAgreementcode;
    }
    
    public void setLogBuyAgreementcode(String logBuyAgreementcode) {
        this.logBuyAgreementcode = logBuyAgreementcode;
    }
    
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_factory_id")
	public MaterialFactory getMaterialFactory() {
		return this.materialFactory;
	}

	public void setMaterialFactory(MaterialFactory materialFactory) {
		this.materialFactory = materialFactory;
	}
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "material_id")
	public Material getMaterial() {
		return this.material;
	}

	public void setMaterial(Material material) {
		this.material = material;
	}

}