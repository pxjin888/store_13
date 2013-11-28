package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.entity.view.LastStore;
import cn.btttech.service.MaterialFactoryService;
import cn.btttech.service.MaterialService;

public class MaterialAction extends ActionBase {

	private static final long serialVersionUID = -3302139967485953797L;
	private String firstResult;
    private String maxResult;
    
    private String materialName;
    private String materialCode;
    private String materialType;
    private String materialUnit;
    private Float materialMinNum;
    private String materialBatchNum;
    private String materialBatchPrice;
    private String materialSequence;
    private String materialPassNum;
    private String materialPassNo;
    private String materialVersion;
    private String materialUse;
    private String materialPosition;
    private InputStream inputStream;
    
    private String logDo;
    
    public InputStream getInputStream() {
        return inputStream;
    }
    
	
    @Resource(name="materialService")
    private MaterialService materialService;
    
    @Resource(name="materialFactoryService")
    private MaterialFactoryService materialFactoryService;
    
	public String list(){
    	int firstResultInt = 1;
    	if(firstResult != null){
    		firstResultInt = Integer.parseInt(firstResult);
    	}
    	int maxResultInt = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	if(maxResult != null){
    		maxResultInt = Integer.parseInt(maxResult);
    	}
    	
    	List<Material> list = materialService.listByPage(firstResultInt, maxResultInt);
    	request.put("material_list", list);
    	
    	return SUCCESS;
    }
	
	
	public String listAndFactory(){
		
    	int firstResultInt = 1;
    	if(firstResult != null){
    		firstResultInt = Integer.parseInt(firstResult);
    	}
    	int maxResultInt = Integer.parseInt(ServletActionContext.getServletContext().getInitParameter("maxPageResult"));
    	if(maxResult != null){
    		maxResultInt = Integer.parseInt(maxResult);
    	}
    	
    	List<Material> list = materialService.listByPage(firstResultInt, maxResultInt);
    	
    	//获取该物料的供应商
    	HashMap<Material, List<LastStore>> materialMap = new HashMap<Material, List<LastStore>>();
    	if(logDo.equals("out")){
    		for (Material material : list) {
    			List<MaterialPrice> materialPrices = materialService.getMaterialFactories(material);
    			List<LastStore> lastStores = new ArrayList<LastStore>();
    			for (MaterialPrice materialPrice : materialPrices) {
					int materialFactoryId = materialPrice.getMaterialFactory().getMaterialFactoryId();
					MaterialFactory materialFactory = materialFactoryService.getById(materialFactoryId);
					String materialFactoryName = materialFactory.getMaterialFactoryName();
					Float materialPartNum = materialPrice.getMaterialPartNum();
					int materialPriceId = materialPrice.getMaterialPriceId();
					Float inputPrice = materialPrice.getMaterialPriceInputprice();
					LastStore lastStore = new LastStore(materialPriceId, materialFactoryId, materialFactoryName, inputPrice, materialPartNum);
					lastStores.add(lastStore);
				}
    			materialMap.put(material, lastStores);
			}
    		request.put("materialMap", materialMap);
    	}
    	
    	
    	//获取所有的供应商
    	else if(logDo.equals("in")){
    		List<MaterialFactory> materialFactories = materialFactoryService.list();
    		request.put("materialFactories", materialFactories);
    		request.put("material_list", list);
    	}
    	
    	
    	System.out.println("logDo:"+logDo);
    	return logDo;
    }
	
	public String add(){
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		String materialCombineMaterialId = request.getParameter("materialCombine.materialId");
		System.out.println("materialCombineMaterialId:"+materialCombineMaterialId);

		Material material = new Material();
		if (materialBatchNum!=null&&materialBatchNum.length()>0) {
			material.setMaterialBatchNum(Float.parseFloat(materialBatchNum));
		}
		if (materialBatchPrice!=null&&materialBatchPrice.length()>0) {
			material.setMaterialBatchPrice(Float.valueOf(materialBatchPrice));
		}
		material.setMaterialCode(materialCode);
		if (materialMinNum!=null&&materialMinNum>0) {
			material.setMaterialMinNum(Float.valueOf(materialMinNum));
		}
		material.setMaterialName(materialName);
		if (materialPassNo!=null&&materialPassNo.length()>0) {
			material.setMaterialPassNo(Integer.parseInt(materialPassNo));
		}
		material.setMaterialPosition(materialPosition);
		material.setMaterialType(materialType);
		material.setMaterialUnit(materialUnit);
		material.setMaterialUse(materialUse);
		material.setMaterialVersion(materialVersion);
		if(materialCombineMaterialId!=null&&materialCombineMaterialId.length()>0){
			HashSet<Material> materialSet = new HashSet<Material>();
			if(materialCombineMaterialId.contains(",")){
				String [] m = materialCombineMaterialId.split(",");
				for (String str : m) {
					materialSet.add(materialService.getById(Integer.parseInt(str)));
				}
			}else{
				materialSet.add(materialService.getById(Integer.parseInt(materialCombineMaterialId)));
			}
			material.setMaterialsForMaterialId(materialSet);
		}
		material.setMaterialNum(0F);
		materialService.save(material);
		String result = "{\"statusCode\":\"200\",\"message\":\"物料添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
		try {
			inputStream = new ByteArrayInputStream(result.getBytes("utf-8"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		return SUCCESS;
	}

	public String getFirstResult() {
		return firstResult;
	}

	public void setFirstResult(String firstResult) {
		this.firstResult = firstResult;
	}

	public String getMaxResult() {
		return maxResult;
	}

	public void setMaxResult(String maxResult) {
		this.maxResult = maxResult;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getMaterialUnit() {
		return materialUnit;
	}

	public void setMaterialUnit(String materialUnit) {
		this.materialUnit = materialUnit;
	}

	public Float getMaterialMinNum() {
		return materialMinNum;
	}

	public void setMaterialMinNum(Float materialMinNum) {
		this.materialMinNum = materialMinNum;
	}

	public String getMaterialBatchNum() {
		return materialBatchNum;
	}

	public void setMaterialBatchNum(String materialBatchNum) {
		this.materialBatchNum = materialBatchNum;
	}

	public String getMaterialBatchPrice() {
		return materialBatchPrice;
	}

	public void setMaterialBatchPrice(String materialBatchPrice) {
		this.materialBatchPrice = materialBatchPrice;
	}

	public String getMaterialSequence() {
		return materialSequence;
	}

	public void setMaterialSequence(String materialSequence) {
		this.materialSequence = materialSequence;
	}

	public String getMaterialPassNum() {
		return materialPassNum;
	}

	public void setMaterialPassNum(String materialPassNum) {
		this.materialPassNum = materialPassNum;
	}

	public String getMaterialPassNo() {
		return materialPassNo;
	}

	public void setMaterialPassNo(String materialPassNo) {
		this.materialPassNo = materialPassNo;
	}

	public String getMaterialVersion() {
		return materialVersion;
	}

	public void setMaterialVersion(String materialVersion) {
		this.materialVersion = materialVersion;
	}

	public String getMaterialUse() {
		return materialUse;
	}

	public void setMaterialUse(String materialUse) {
		this.materialUse = materialUse;
	}

	public String getMaterialPosition() {
		return materialPosition;
	}

	public void setMaterialPosition(String materialPosition) {
		this.materialPosition = materialPosition;
	}

	public MaterialService getMaterialService() {
		return materialService;
	}

	public void setMaterialService(MaterialService materialService) {
		this.materialService = materialService;
	}


	public String getLogDo() {
		return logDo;
	}


	public void setLogDo(String logDo) {
		this.logDo = logDo;
	}
}
