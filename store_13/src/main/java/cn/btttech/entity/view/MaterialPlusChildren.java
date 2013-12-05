package cn.btttech.entity.view;

public class MaterialPlusChildren {

	public MaterialPlusChildren(Integer materialId, String materialName,
			String materialUnit, Float materialNum, String materialChildrenId,
			String materialChildrenName, String materialChildrenNum) {
		super();
		this.materialId = materialId;
		this.materialName = materialName;
		this.materialUnit = materialUnit;
		this.materialNum = materialNum;
		this.materialChildrenId = materialChildrenId;
		this.materialChildrenName = materialChildrenName;
		this.materialChildrenNum = materialChildrenNum;
	}
	private Integer materialId;
    private String materialName;
    private String materialUnit;
    private Float materialNum;
	
	private String materialChildrenId;
	private String materialChildrenName;
	private String materialChildrenNum;
	@Override
	public String toString() {
		return "MaterialPlusChildren [materialId=" + materialId
				+ ", materialName=" + materialName + ", materialUnit="
				+ materialUnit + ", materialNum=" + materialNum
				+ ", materialChildrenId=" + materialChildrenId
				+ ", materialChildrenName=" + materialChildrenName
				+ ", materialChildrenNum=" + materialChildrenNum + "]";
	}
}
