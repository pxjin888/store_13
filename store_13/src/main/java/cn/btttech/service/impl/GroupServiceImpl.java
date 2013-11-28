package cn.btttech.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.btttech.dao.BaseDao;
import cn.btttech.entity.Group;
import cn.btttech.entity.Module;
import cn.btttech.service.GroupService;
import cn.btttech.service.ModuleService;
import cn.btttech.service.OperationService;

@Service("groupService")
@Transactional(readOnly = true)
public class GroupServiceImpl implements GroupService {  
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
	public List<Group> listByPage(final int firstResult, final int maxResult) {
		return listByPage(new Object[]{}, new Object[]{}, firstResult, maxResult);
	}

	@Override
	public List<Group> listByPage(Object condition, Object param, final int firstResult, final int maxResult) {
		return listByPage(new Object[]{condition}, new Object[]{param}, firstResult, maxResult);
	}

	@Override
	public List<Group> listByPage(Object[] conditions, Object[] params, final int firstResult, final int maxResult) {
		
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
		List<Group> list = baseDao.findByPage(
				"from Group "+cond, Group.class, params,firstResult,maxResult);
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
		int count = baseDao.findByPageCount("from Group "+cond, Group.class, params);
		return count;
	}

	@Override
	public Group getById(int id) {
		Group group = baseDao.get(Group.class, id);
		return group;
	}



	@Override
	@Transactional(readOnly=false)
	public void save(String group_name, String[] module_id, String[] operation_id) {
		// TODO Auto-generated method stub
		for (int i = 0; i < module_id.length; i++) {
			String module = module_id[i];
			int moduleInt = Integer.parseInt(module);
			String operations = operation_id[i];
			Module m = moduleService.getById(moduleInt);
			if(operations.contains(",")){
				String[] oper = operations.split(",");
				for (String o : oper) {
					int operationInt = Integer.parseInt(o);
					Group groupTemp = new Group();
					groupTemp.setGroupName(group_name);
					groupTemp.setModule(m);
					groupTemp.setOperation(operationService.getById(operationInt));
					baseDao.save(groupTemp);
				}
			}else {
				int operationInt = Integer.parseInt(operations);
				Group groupTemp = new Group();
				groupTemp.setGroupName(group_name);
				groupTemp.setModule(m);
				groupTemp.setOperation(operationService.getById(operationInt));
				baseDao.save(groupTemp);
			}
		}
	}

}  