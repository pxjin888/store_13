package cn.btttech.entity.view;

public class LastStore {

	
	private int materialPriceId;
	private int materialFactoryId;
	private String materialFactoryName;
	private Float inputPrice;
	private Float materialPartNum;
	
	public LastStore(int materialPriceId, int materialFactoryId,
			String materialFactoryName, Float inputPrice, Float materialPartNum) {
		super();
		this.materialPriceId = materialPriceId;
		this.materialFactoryId = materialFactoryId;
		this.materialFactoryName = materialFactoryName;
		this.inputPrice = inputPrice;
		this.materialPartNum = materialPartNum;
	}
	
	public int getMaterialPriceId() {
		return materialPriceId;
	}
	public void setMaterialPriceId(int materialPriceId) {
		this.materialPriceId = materialPriceId;
	}
	public int getMaterialFactoryId() {
		return materialFactoryId;
	}
	public void setMaterialFactoryId(int materialFactoryId) {
		this.materialFactoryId = materialFactoryId;
	}
	public String getMaterialFactoryName() {
		return materialFactoryName;
	}
	public void setMaterialFactoryName(String materialFactoryName) {
		this.materialFactoryName = materialFactoryName;
	}
	public Float getInputPrice() {
		return inputPrice;
	}
	public void setInputPrice(Float inputPrice) {
		this.inputPrice = inputPrice;
	}
	public Float getMaterialPartNum() {
		return materialPartNum;
	}
	public void setMaterialPartNum(Float materialPartNum) {
		this.materialPartNum = materialPartNum;
	}
	
	
}
