package cn.btttech.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.Module;
import cn.btttech.service.ModuleService;

@Service("moduleService")
@Transactional(readOnly = true)
public class ModuleServiceImpl implements ModuleService {  
    @Autowired
    private BaseDao baseDao;  
  
    public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
  
	@Override
	public List<Module> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<Module> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<Module> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
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
		List<Module> list = baseDao.findByPage(
				"from Module "+cond, Module.class, params,firstResult,maxResult);
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
		int count = baseDao.findByPageCount("from Module "+cond, Module.class, params);
		return count;
	}

	@Override
	public Module getById(int id) {
		Module module = baseDao.get(Module.class, id);
		return module;
	}

	@Override
	public List<Module> findAll() {
		List<Module> list = baseDao.findAll("from Module", Module.class);
		return list;
	}

}  