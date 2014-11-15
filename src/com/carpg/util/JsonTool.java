package com.carpg.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.carpg.dto.User;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTool {
	
	
	//��Object����ת��Ϊjson�ַ���
	public String toJsonString(Object obj){
		return JSONObject.fromObject(obj).toString();
	}
	
	//��Object����ת��ΪJsonArray�ַ���
	public String toJsonArrayString(List<Object> objs){
		return JSONArray.fromObject(objs).toString();
	}
	
	//��json�ַ���ת��ΪObject����
	public Object toObject(String jsonString, Class method){
        Object bean = (User)JSONObject.toBean(JSONObject.fromObject(jsonString), method.getClass());
        
        return bean;
	}
	
	//��jsonArray�ַ���ת��ΪList<Object>����
	public List<Object> toObjects(String jsonArrayString, Class method){
		List<Object> beans = (List<Object>)JSONArray.toList(JSONArray.fromObject(jsonArrayString), method.getClass());
	
		return beans;
	}

}
