package cn.btttech.entity;


import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="store_material_price"
    ,catalog="store"
)

public class MaterialPrice  implements java.io.Serializable {


    // Fields    

	 private static final long serialVersionUID = -2129433969284904604L;
	 private Integer materialPriceId;
     private Material material;
     private Float materialPriceInputprice;
     private Float materialPartNum;
     private Date materialPriceTime;
     private Set<MaterialFactory> materialFactories = new HashSet<MaterialFactory>(0);

    // Constructors

    /** default constructor */
    public MaterialPrice() {
    }
   
    // Property accessors
    @Id @GeneratedValue
    
    @Column(name="material_price_id", unique=true, nullable=false)

    public Integer getMaterialPriceId() {
        return this.materialPriceId;
    }
    
    public void setMaterialPriceId(Integer materialPriceId) {
        this.materialPriceId = materialPriceId;
    }
	@ManyToOne(fetch=FetchType.LAZY,cascade={CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
        @JoinColumn(name="material_id")

    public Material getMaterial() {
        return this.material;
    }
    
    public void setMaterial(Material material) {
        this.material = material;
    }
    
    @Column(name="material_price_inputprice", precision=12, scale=0)

    public Float getMaterialPriceInputprice() {
        return this.materialPriceInputprice;
    }
    
    public void setMaterialPriceInputprice(Float materialPriceInputprice) {
        this.materialPriceInputprice = materialPriceInputprice;
    }
    
    @Column(name="material_part_num", precision=12, scale=0)

    public Float getMaterialPartNum() {
        return this.materialPartNum;
    }
    
    public void setMaterialPartNum(Float materialPartNum) {
        this.materialPartNum = materialPartNum;
    }
    @Temporal(TemporalType.DATE)
    @Column(name="material_price_time", length=10)

    public Date getMaterialPriceTime() {
        return this.materialPriceTime;
    }
    
    public void setMaterialPriceTime(Date materialPriceTime) {
        this.materialPriceTime = materialPriceTime;
    }

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "materialPrices", cascade={CascadeType.MERGE,CascadeType.REFRESH})
	public Set<MaterialFactory> getMaterialFactories() {
		return this.materialFactories;
	}

	public void setMaterialFactories(Set<MaterialFactory> materialFactories) {
		this.materialFactories = materialFactories;
	}
}