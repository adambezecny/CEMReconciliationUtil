package com.catechnologies.apm.cem.cemdbreconciliation.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 * Singleton class wrapping references to JPA entity managers for source and target database. All classes
 * must access JPA via this service.
 * @author bezad01
 *
 */
public class JPAFactory {
	
    private EntityManagerFactory emfSource = null;
    private EntityManagerFactory emfTarget = null;
    
    private static final String PERSISTANCE_UNIT_NAME_SOURCE = "CEMReconciliationUtilSource";	
    private static final String PERSISTANCE_UNIT_NAME_TARGET = "CEMReconciliationUtilTarget";
    

    private static volatile JPAFactory  _instance = null;
    
    private JPAFactory() {
    	emfSource = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME_SOURCE, ResourceUtil.getDBProperties("sourcedb"));
    	emfTarget = Persistence.createEntityManagerFactory(PERSISTANCE_UNIT_NAME_TARGET, ResourceUtil.getDBProperties("targetdb"));
	}
    
    public static JPAFactory getInstance() {
    	
		if (_instance == null) {
			synchronized (JPAFactory.class) {
				if (_instance == null) {
					_instance = new JPAFactory();
				}
			}
		}
		return _instance;    	
    	
    }

    
    public EntityManager getSourceEntityManager() { 
    	EntityManager em = emfSource.createEntityManager(); 
    	return em;
    }
    
    public EntityManager getTargetEntityManager() { 
    	EntityManager em = emfTarget.createEntityManager(); 
    	return em;
    }
    
    
    public void closeEMF() {
       
		if (emfSource != null && emfSource.isOpen()) {
			emfSource.close();    
		}      
		
		if (emfTarget != null && emfTarget.isOpen()) {
			emfTarget.close();    
		}
		
		emfSource = null;
		emfTarget = null;
       
    }
    
}
