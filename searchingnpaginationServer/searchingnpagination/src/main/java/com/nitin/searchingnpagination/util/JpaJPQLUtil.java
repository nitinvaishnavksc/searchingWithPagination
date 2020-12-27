package com.nitin.searchingnpagination.util;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JpaJPQLUtil {
	
	private static EntityManager em;

    @Autowired
    public void setEntityManager(EntityManager em){
    	JpaJPQLUtil.em = em;
    }
	
	@SuppressWarnings("rawtypes") 
	public static <T> HashMap<String, Object> JPQLNitinQueryFeatures(String queryString, String queryCountString, 
			HashMap<String, Object> paramter, Class<T> className, Integer page, Integer size)
	{ 
		Query query = em.createQuery(queryString); 
		//Declaration Part For Local Scope 
		Query queryTotal = null; 
		int pageNumber = 0,pageSize = 0,totalpageNumber = 0;
		long countResult = 0L; 
		//Declaration Part For Local Scope
		if(page!=null && size!=null && queryCountString!=null){
			queryTotal = em.createQuery(queryCountString); 
			pageNumber = page+1;
			pageSize = size;
			query.setFirstResult((pageNumber-1)* pageSize); 
			query.setMaxResults(pageSize);
			}  
		if(paramter!=null) {
			Set set = paramter.entrySet();
			Iterator iterator = set.iterator(); 
			while(iterator.hasNext()) {
			Map.Entry mentry = (Map.Entry)iterator.next(); 
			query.setParameter(mentry.getKey().toString(), mentry.getValue()); 
			if(page!=null && size!=null && queryCountString!=null) { 
			queryTotal.setParameter(mentry.getKey().toString(), mentry.getValue()); 
			}
		  }
		} 
		if(page!=null && size!=null && queryCountString!=null) { 
			countResult = (long)queryTotal.getSingleResult(); 
			totalpageNumber = (int) ((countResult / pageSize) + 1); 
		}
		List<Object> results = new ArrayList<Object>(); 
		try {
		@SuppressWarnings("unchecked")
		List<Object[]> list = query.getResultList();
		em.close();
		for (Object[] obj :list) {
			Object[] objDom = {obj};
			Class<?> params[] =new Class[objDom.length];
			for(int i=0; i<objDom.length;i++)
			{
				if(objDom[i] instanceof Object[]) {
				params[i] = Object [].class;
			}}
				Object ClassObjectWithCons = className.getDeclaredConstructor(params).newInstance(objDom);
				results.add(ClassObjectWithCons);
		}
		}
		catch(Exception ex)
		{ex.printStackTrace();}
		HashMap<String, Object> finalResult = new HashMap<String, Object> ();
		finalResult.put ("datatable", results);
		if (page!=null && size!=null && queryCountString!=null) {
			finalResult.put("offset", ((pageNumber-1)*pageSize) );
			finalResult.put("onset", (pageNumber-1)==(totalpageNumber-1)?countResult: ((( (pageNumber-1) *pageSize)) +pageSize));
			finalResult.put("pagenumber", pageNumber); 
			finalResult.put("pagesize", pageSize); 
			finalResult.put("totalpages", totalpageNumber);
			finalResult.put("numberofrecords", countResult); 
		}
		return finalResult;
		
	}


}
