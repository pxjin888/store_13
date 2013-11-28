package cn.btttech.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.LogMaterial;
import cn.btttech.service.MaterialLogService;
import cn.btttech.service.ModuleService;
import cn.btttech.service.OperationService;

@Service("materialLogService")
@Transactional(readOnly = true)
public class MaterialLogServiceImpl implements MaterialLogService {  
    @Autowired
    private BaseDao baseDao; 

    @Resource(name="moduleService")
	private ModuleService moduleService;
	
	@Resource(name="operationService")
	private OperationService operationService;
  
    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
  
	@Override
	public List<LogMaterial> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<LogMaterial> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<LogMaterial> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
		String cond = "";
		if(conditions.length != 0){
			cond = " where";
		}
		for(int i=0;i<conditions.length;i++){
			cond+=" "+conditions[i]+" and";
		}
		if(cond.endsWith("and")){
			cond = cond.substring(0, cond.length()-3);
		}
		List<LogMaterial> list = baseDao.findByPage(
				"from LogMaterial "+cond, LogMaterial.class, params,firstResult,maxResult);
		return list;
	}

	@Override
	public int listByPageCount() {
		return listByPageCount(new Object[]{}, new Object[]{});
	}

	@Override
	public int listByPageCount(Object condition, Object param) {
		// TODO Auto-generated method stub
		return listByPageCount(new Object[]{condition}, new Object[]{param});
	}

	@Override
	public int listByPageCount(Object[] conditions, Object[] params) {
		String cond = "";
		for(int i=0;i<conditions.length;i++){
			cond+=" "+conditions[i];
		}
		int count = baseDao.findByPageCount("from LogMaterial "+cond, LogMaterial.class, params);
		return count;
	}

	@Override
	public LogMaterial getById(int id) {
		LogMaterial logMaterial = baseDao.get(LogMaterial.class, id);
		return logMaterial;
	}



	@Override
	@Transactional(readOnly = false)
	public void save(LogMaterial logMaterial) {
		baseDao.save(logMaterial);
	}

}  