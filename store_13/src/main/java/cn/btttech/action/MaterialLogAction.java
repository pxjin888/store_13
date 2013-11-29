package cn.btttech.action;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import cn.btttech.entity.LogMaterial;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.entity.MaterialPrice;
import cn.btttech.service.MaterialFactoryService;
import cn.btttech.service.MaterialLogService;
import cn.btttech.service.MaterialPriceService;
import cn.btttech.service.MaterialService;
import cn.btttech.util.UuidGenerator;

public class MaterialLogAction extends ActionBase {

	private static final long serialVersionUID = 638241257683967654L;
	private String firstResult;
    private String maxResult;
    
    private String logDo;
    private String logMaterialUse;
    private String logMaterialProject;
    private String logBuyRequirecode;
    private String logBuyAgreementcode;
    private String logRemark;
    
    private InputStream inputStream;
    public InputStream getInputStream() {
        return inputStream;
    }
    
    @Resource(name="materialLogService")
	private MaterialLogService materialLogService;
    @Resource(name="materialService")
	private MaterialService materialService;
    @Resource(name="materialPriceService")
	private MaterialPriceService materialPriceService;
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
    	
    	List<LogMaterial> list = materialLogService.listByPage(firstResultInt, maxResultInt);
    	request.put("group_list", list);
    	
    	return SUCCESS;
    }
    
    public String add(){
		ActionContext context = ActionContext.getContext();  
		HttpServletRequest request = (HttpServletRequest) context.get(ServletActionContext.HTTP_REQUEST); 
		
		String materialCombineMaterialId = request.getParameter("materialCombine.materialId");
//		String materialCombineMaterialName = request.getParameter("materialCombine.materialName");
//		String materialCombineMaterialProviderName = request.getParameter("materialCombine.materialProviderId");
		String materialCombineMaterialProviderId = request.getParameter("materialCombine.materialProviderId");
		String materialCombineMaterialNum = request.getParameter("materialCombine.materialNum");
		String materialCombineMaterialPartNum = request.getParameter("materialCombine.materialPartNum");
		String materialCombineMaterialPriceId = request.getParameter("materialCombine.materialPriceId");
		String materialCombineMaterialInputPrice = request.getParameter("materialCombine.materialInputPrice");
		
		System.out.println("materialCombineMaterialId:"+materialCombineMaterialId);
			
		String [] materialIdArray = materialCombineMaterialId.split(",");
		String [] materialProviderIdArray = materialCombineMaterialProviderId.split(",");
		String [] materialNumArray = materialCombineMaterialNum.split(",");
		String [] materialPriceArray = materialCombineMaterialInputPrice.split(",");
		String [] materialUseArray = logMaterialUse.split(",");
		String [] materialProjectArray = logMaterialProject.split(",");
		String [] logBuyRequirecodeArray = logBuyRequirecode.split(",");
		String [] logBuyAgreementcodeArray = logBuyAgreementcode.split(",");
		String [] logRemarkArray = logRemark.split(",");
		String [] materialPartNumArray = null;
		String [] materialPriceIdArray = null;
		System.out.println("materialCombineMaterialPartNum"+materialCombineMaterialPartNum);
		if(!materialCombineMaterialPartNum.equals("")){
			materialPartNumArray = materialCombineMaterialPartNum.split(",");
			materialPriceIdArray = materialCombineMaterialPriceId.split(",");
		}
		
		String uuid = UuidGenerator.generate();
		
		if(logDo.equals("in")){
			
			for (int i=0;i<materialIdArray.length;i++) {
				LogMaterial logMaterial = new LogMaterial();
				logMaterial.setLogBuyAgreementcode(logBuyAgreementcodeArray[i]);
				logMaterial.setLogBuyRequirecode(logBuyRequirecodeArray[i]);
				logMaterial.setLogDo(logDo);
				logMaterial.setLogMaterialInputPrice(Float.parseFloat(materialPriceArray[i]));
				logMaterial.setLogMaterialNum(Float.parseFloat(materialNumArray[i]));
				logMaterial.setLogMaterialProject(materialProjectArray[i]);
				logMaterial.setLogMaterialUse(materialUseArray[i]);
				logMaterial.setLogRemark(logRemarkArray[i]);
				logMaterial.setLogTime(new Date());
				logMaterial.setLogCode(uuid);
				
				Material material = materialService.getById(Integer.parseInt(materialIdArray[i]));
				
				System.out.println(materialIdArray[i]+material);
				Float num = material.getMaterialNum();
				Set<LogMaterial> logMaterials = new HashSet<LogMaterial>();
				logMaterials.add(logMaterial);
				
				System.out.println("num:"+materialNumArray[i]);
				
				
				
				MaterialPrice materialPrice = new MaterialPrice();
				materialPrice.setMaterial(material);
				MaterialFactory materialFactory = materialFactoryService.getById(Integer.parseInt(materialProviderIdArray[i]));
				materialPrice.setMaterialFactory(materialFactory);
				materialPrice.setMaterialPartNum(Float.parseFloat(materialNumArray[i]));
				materialPrice.setMaterialPriceInputprice(Float.parseFloat(materialPriceArray[i]));
				materialPrice.setMaterialPriceTime(new Date());
				HashSet<MaterialPrice> materialPrices = new HashSet<MaterialPrice>();
				
				MaterialPrice materialPriceTemp = materialPriceService.combineMaterialItem(material, materialFactory, Float.parseFloat(materialPriceArray[i]));
				if(materialPriceTemp == null){
					materialPrices.add(materialPrice);
					material.setMaterialPrices(materialPrices);
				}else{
					materialPriceTemp.setMaterialPartNum(materialPriceTemp.getMaterialPartNum() + Float.parseFloat(materialNumArray[i]));
					materialPriceTemp.setMaterialPriceTime(new Date());
					materialPrices.add(materialPriceTemp);
					materialPriceService.update(materialPriceTemp);
				}
				
				
				material.setMaterialNum(Float.parseFloat(materialNumArray[i])+num);
				material.setLogs(logMaterials);
				
				materialService.update(material);
			}
			
		}else if(logDo.equals("out")){
			
			for (int i=0;i<materialIdArray.length;i++) {
				Set<LogMaterial> logMaterials = new HashSet<LogMaterial>();
				LogMaterial logMaterial = null;
				if(materialPriceArray[i].contains("&")){
					String[] materialPriceArrayTempArray = materialPriceArray[i].split("&");
					String[] materialPartNumArrayTempArray = materialPartNumArray[i].split("&");
					for (int j = 0; j < materialPriceArrayTempArray.length; j++) {
						logMaterial = new LogMaterial();
						logMaterial.setLogBuyAgreementcode(logBuyAgreementcodeArray[i]);
						logMaterial.setLogBuyRequirecode(logBuyRequirecodeArray[i]);
						logMaterial.setLogDo(logDo);
						logMaterial.setLogMaterialProject(materialProjectArray[i]);
						logMaterial.setLogMaterialUse(materialUseArray[i]);
						logMaterial.setLogRemark(logRemarkArray[i]);
						logMaterial.setLogTime(new Date());
						logMaterial.setLogCode(uuid);
						logMaterial.setLogMaterialInputPrice(Float.parseFloat(materialPriceArrayTempArray[j]));
						logMaterial.setLogMaterialNum(Float.parseFloat(materialPartNumArrayTempArray[j]));
						logMaterials.add(logMaterial);
					}
				}
				
				
				Material material = materialService.getById(Integer.parseInt(materialIdArray[i]));
				
				System.out.println(materialIdArray[i]+material);
				
				logMaterials.add(logMaterial);
				
				System.out.println("num:"+materialNumArray[i]);
				
				
				System.out.println(""+materialPriceIdArray[i]);
				HashSet<MaterialPrice> materialPrices = new HashSet<MaterialPrice>();
				if (materialPriceIdArray[i].contains("&")) {
					String[] materialPriceIdArrayTempArray = materialPriceIdArray[i].split("&");
					String[] materialPartNumArrayTempArray = materialPriceIdArray[i].split("&");
					for (int j = 0; j < materialPriceIdArrayTempArray.length; j++) {
						MaterialPrice materialPrice = materialPriceService.getById(Integer.parseInt(materialPriceIdArrayTempArray[j]));
						materialPrice.setMaterialPartNum(materialPrice.getMaterialPartNum() - Float.parseFloat(materialPartNumArrayTempArray[j]));
						materialPrices.add(materialPrice);
					}
				}
				
				material.setMaterialPrices(materialPrices);
				material.setMaterialNum(material.getMaterialNum() - Float.parseFloat(materialNumArray[i]));
				material.setLogs(logMaterials);
				
				materialService.update(material);
			}
			
		}else if(logDo.equals("mix")){
			
		}
		
		String result = "{\"statusCode\":\"200\",\"message\":\"添加成功\",\"navTabId\":\"\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";
			
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

	public String getLogDo() {
		return logDo;
	}

	public void setLogDo(String logDo) {
		this.logDo = logDo;
	}

	public String getLogMaterialUse() {
		return logMaterialUse;
	}

	public void setLogMaterialUse(String logMaterialUse) {
		this.logMaterialUse = logMaterialUse;
	}

	public String getLogMaterialProject() {
		return logMaterialProject;
	}

	public void setLogMaterialProject(String logMaterialProject) {
		this.logMaterialProject = logMaterialProject;
	}

	public String getLogBuyRequirecode() {
		return logBuyRequirecode;
	}

	public void setLogBuyRequirecode(String logBuyRequirecode) {
		this.logBuyRequirecode = logBuyRequirecode;
	}

	public String getLogBuyAgreementcode() {
		return logBuyAgreementcode;
	}

	public void setLogBuyAgreementcode(String logBuyAgreementcode) {
		this.logBuyAgreementcode = logBuyAgreementcode;
	}

	public String getLogRemark() {
		return logRemark;
	}

	public void setLogRemark(String logRemark) {
		this.logRemark = logRemark;
	}

}
