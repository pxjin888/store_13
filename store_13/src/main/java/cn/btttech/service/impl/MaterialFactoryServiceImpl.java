package cn.btttech.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.Material;
import cn.btttech.entity.MaterialFactory;
import cn.btttech.service.MaterialFactoryService;
import cn.btttech.service.ModuleService;
import cn.btttech.service.OperationService;

@Service("materialFactoryService")
@Transactional(readOnly = true)
public class MaterialFactoryServiceImpl implements MaterialFactoryService {  
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
	public List<MaterialFactory> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<MaterialFactory> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<MaterialFactory> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
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
		List<MaterialFactory> list = baseDao.findByPage(
				"from MaterialFactory "+cond, MaterialFactory.class, params,firstResult,maxResult);
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
		int count = baseDao.findByPageCount("from Material "+cond, Material.class, params);
		return count;
	}

	@Override
	public MaterialFactory getById(int id) {
		MaterialFactory materialFactory = baseDao.get(MaterialFactory.class, id);
		return materialFactory;
	}

	@Override
	@Transactional(readOnly = false)
	public void save(MaterialFactory materialFactory) {
		baseDao.save(materialFactory);
	}



	@Override
	public List<MaterialFactory> list() {
		List<MaterialFactory> list = baseDao.findAll("from MaterialFactory", MaterialFactory.class);
		return list;
	}

}  