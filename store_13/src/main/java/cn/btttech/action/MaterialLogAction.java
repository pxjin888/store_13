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
		String materialCombineMaterialProviderName = request.getParameter("materialCombine.materialProviderName");
		String materialCombineMaterialProviderId = request.getParameter("materialCombine.materialProviderId");
		String materialCombineMaterialNum = request.getParameter("materialCombine.materialNum");
		String materialCombineMaterialPartNum = request.getParameter("materialCombine.materialPartNum");
		String materialCombineMaterialPriceId = request.getParameter("materialCombine.materialPriceId");
		String materialCombineMaterialInputPrice = request.getParameter("materialCombine.materialInputPrice");
		
		System.out.println("materialCombineMaterialId:"+materialCombineMaterialId);
			
//		String [] materialIdArray = materialCombineMaterialId.split(",");
//		String [] materialProviderIdArray = materialCombineMaterialProviderId.split(",");
//		String [] materialNumArray = materialCombineMaterialNum.split(",");
//		String [] materialPriceArray = materialCombineMaterialInputPrice.split(",");
//		String [] materialUseArray = logMaterialUse.split(",");
//		String [] materialProjectArray = logMaterialProject.split(",");
//		String [] logBuyRequirecodeArray = logBuyRequirecode.split(",");
//		String [] logBuyAgreementcodeArray = logBuyAgreementcode.split(",");
//		String [] logRemarkArray = logRemark.split(",");
//		String [] materialPartNumArray = null;
//		String [] materialPriceIdArray = null;
//		System.out.println("materialCombineMaterialPartNum"+materialCombineMaterialPartNum);
//		if(!materialCombineMaterialPartNum.equals("")){
//			materialPartNumArray = materialCombineMaterialPartNum.split(",");
//			materialPriceIdArray = materialCombineMaterialPriceId.split(",");
//		}
//		
		String uuid = UuidGenerator.generate();
		
		if(logDo.equals("in")){
			
				LogMaterial logMaterial = new LogMaterial();
				logMaterial.setLogBuyAgreementcode(logBuyAgreementcode);
				logMaterial.setLogBuyRequirecode(logBuyRequirecode);
				logMaterial.setLogDo(logDo);
				logMaterial.setLogMaterialInputPrice(Float.parseFloat(materialCombineMaterialInputPrice));
				logMaterial.setLogMaterialNum(Float.parseFloat(materialCombineMaterialNum));
				logMaterial.setLogMaterialProject(logMaterialProject);
				logMaterial.setLogMaterialUse(logMaterialUse);
				logMaterial.setLogRemark(logRemark);
				logMaterial.setLogTime(new Date());
				logMaterial.setLogCode(uuid);
				
				materialService.firstMaterialInput(logMaterial, Integer.parseInt(materialCombineMaterialId), Integer.parseInt(materialCombineMaterialProviderId), Float.parseFloat(materialCombineMaterialNum), Float.parseFloat(materialCombineMaterialInputPrice));
				
			
		}else if(logDo.equals("out")){
			
			LogMaterial logMaterial = new LogMaterial();
			logMaterial.setLogBuyAgreementcode(logBuyAgreementcode);
			logMaterial.setLogBuyRequirecode(logBuyRequirecode);
			logMaterial.setLogDo(logDo);
			logMaterial.setLogMaterialNum(Float.parseFloat(materialCombineMaterialNum));
			logMaterial.setLogMaterialProject(logMaterialProject);
			logMaterial.setLogMaterialUse(logMaterialUse);
			logMaterial.setLogRemark(logRemark+"{"+"价格："+materialCombineMaterialInputPrice+",数量："+materialCombineMaterialPartNum+",厂商："+materialCombineMaterialProviderName+"}");
			logMaterial.setLogTime(new Date());
			logMaterial.setLogCode(uuid);
			
			String [] materialCombineMaterialProviderIdArray = materialCombineMaterialProviderId.split("&");
			
			String [] materialCombineMaterialPartNumArray = materialCombineMaterialPartNum.split("&");
			
			String [] materialCombineMaterialPriceIdArray = materialCombineMaterialPriceId.split("&");
			materialService.materialOutput(logMaterial, Integer.parseInt(materialCombineMaterialId), materialCombineMaterialProviderIdArray, materialCombineMaterialPriceIdArray, materialCombineMaterialPartNumArray, Float.parseFloat(materialCombineMaterialNum));
			
			
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
