package cn.btttech.service;

import java.util.List;

import cn.btttech.entity.Operation;
import cn.btttech.util.Page;
import cn.btttech.util.Result;


public interface OperationService {  
    
    public Result<Operation> listByPage(Page page);
    
    public Result<Operation> listByPage(Object condition, Object param, Page page);
    
    public Result<Operation> listByPage(Object[] conditions, Object [] params, Page page);
    
    public Operation getById(int id);
    
    public List<Operation> findAll();
}  