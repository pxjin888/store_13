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
@Table(name="store_material"
    ,catalog="store"
)

public class Material  implements java.io.Serializable {


    // Fields    
	private static final long serialVersionUID = 8313954122505056329L;
	private Integer materialId;
     private String materialName;
     private String materialUnit;
     private Float materialNum;
     private String materialCode;
     private String materialType;
     private Float materialMinNum;
     private Float materialBatchNum;
     private Float materialBatchPrice;
     private Long materialSequence;
     private Integer materialPassNum;
     private Integer materialPassNo;
     private String materialVersion;
     private String materialPosition;
     private String materialUse;
     private Set<MaterialPrice> materialPrices = new HashSet<MaterialPrice>(0);
     private Set<Material> materialsForMaterialchildId = new HashSet<Material>(0);
     private Set<Material> materialsForMaterialId = new HashSet<Material>(0);
     private Set<LogMaterial> logs = new HashSet<LogMaterial>(0);


    // Constructors

    /** default constructor */
    public Material() {
    }

    
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="material_id", unique=true, nullable=false)

    public Integer getMaterialId() {
        return this.materialId;
    }
    
    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }
    
    @Column(name="material_name", length=25)

    public String getMaterialName() {
        return this.materialName;
    }
    
    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }
    
    @Column(name="material_unit", length=10)

    public String getMaterialUnit() {
        return this.materialUnit;
    }
    
    public void setMaterialUnit(String materialUnit) {
        this.materialUnit = materialUnit;
    }
    
    @Column(name="material_num", precision=12, scale=0)

    public Float getMaterialNum() {
        return this.materialNum;
    }
    
    public void setMaterialNum(Float materialNum) {
        this.materialNum = materialNum;
    }
    
    @Column(name="material_code", length=25)

    public String getMaterialCode() {
        return this.materialCode;
    }
    
    public void setMaterialCode(String materialCode) {
        this.materialCode = materialCode;
    }
    
    @Column(name="material_type", length=25)

    public String getMaterialType() {
        return this.materialType;
    }
    
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
    
    @Column(name="material_min_num", precision=12, scale=0)

    public Float getMaterialMinNum() {
        return this.materialMinNum;
    }
    
    public void setMaterialMinNum(Float materialMinNum) {
        this.materialMinNum = materialMinNum;
    }
    
    @Column(name="material_batch_num", precision=12, scale=0)

    public Float getMaterialBatchNum() {
        return this.materialBatchNum;
    }
    
    public void setMaterialBatchNum(Float materialBatchNum) {
        this.materialBatchNum = materialBatchNum;
    }
    
    @Column(name="material_batch_price", precision=12, scale=0)

    public Float getMaterialBatchPrice() {
        return this.materialBatchPrice;
    }
    
    public void setMaterialBatchPrice(Float materialBatchPrice) {
        this.materialBatchPrice = materialBatchPrice;
    }
    
    @Column(name="material_sequence")

    public Long getMaterialSequence() {
        return this.materialSequence;
    }
    
    public void setMaterialSequence(Long materialSequence) {
        this.materialSequence = materialSequence;
    }
    
    @Column(name="material_pass_num")

    public Integer getMaterialPassNum() {
        return this.materialPassNum;
    }
    
    public void setMaterialPassNum(Integer materialPassNum) {
        this.materialPassNum = materialPassNum;
    }
    
    @Column(name="material_pass_no")

    public Integer getMaterialPassNo() {
        return this.materialPassNo;
    }
    
    public void setMaterialPassNo(Integer materialPassNo) {
        this.materialPassNo = materialPassNo;
    }
    
    @Column(name="material_version", length=25)

    public String getMaterialVersion() {
        return this.materialVersion;
    }
    
    public void setMaterialVersion(String materialVersion) {
        this.materialVersion = materialVersion;
    }
    
    @Column(name="material_position", length=25)

    public String getMaterialPosition() {
        return this.materialPosition;
    }
    
    public void setMaterialPosition(String materialPosition) {
        this.materialPosition = materialPosition;
    }
    
    @Column(name="material_use", length=25)

    public String getMaterialUse() {
        return this.materialUse;
    }
    
    public void setMaterialUse(String materialUse) {
        this.materialUse = materialUse;
    }
@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="material" )

    public Set<MaterialPrice> getMaterialPrices() {
        return this.materialPrices;
    }
    
    public void setMaterialPrices(Set<MaterialPrice> materialPrices) {
        this.materialPrices = materialPrices;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="materialsForMaterialId")

    public Set<Material> getMaterialsForMaterialchildId() {
        return this.materialsForMaterialchildId;
    }
    
    public void setMaterialsForMaterialchildId(Set<Material> materialsForMaterialchildId) {
        this.materialsForMaterialchildId = materialsForMaterialchildId;
    }
@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="material_materialchild_relation", catalog="store", joinColumns = { 
        @JoinColumn(name="materialchild_id", nullable=false, updatable=false) }, inverseJoinColumns = { 
        @JoinColumn(name="material_id", nullable=false, updatable=false) })

    public Set<Material> getMaterialsForMaterialId() {
        return this.materialsForMaterialId;
    }
    
    public void setMaterialsForMaterialId(Set<Material> materialsForMaterialId) {
        this.materialsForMaterialId = materialsForMaterialId;
    }

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "material")
    public Set<LogMaterial> getLogs() {
        return this.logs;
    }
    
    public void setLogs(Set<LogMaterial> logs) {
        this.logs = logs;
    }
}