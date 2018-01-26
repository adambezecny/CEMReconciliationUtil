package com.catechnologies.apm.cem.cemdbreconciliation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.*;
import javax.persistence.NoResultException;
import com.catechnologies.apm.cem.cemdbreconciliation.exceptions.AppDAOException;

/**
 * Main data access object of the application. Contains methods used to access and populate database entities.
 * @author bezad01
 *
 */
public class AppDAO {

	private EntityManager em;
    static final Logger log = LogManager.getLogger(AppDAO.class.getName());
	
	
	public AppDAO(EntityManager em){
		this.em=em;
	}
	
	/**
	 * Finds business application by its name. 
	 * @param baName - name of business application to find
	 * @return CEMBusinessApplication or null if nothing was found
	 */
	public CEMBusinessApplication findCEMBusinessApplicationByName(String baName){	
		
		try{
			TypedQuery<CEMBusinessApplication> query = null;
			query = em.createNamedQuery("CEMBusinessApplication.findByName", CEMBusinessApplication.class);
			query.setParameter("baName", baName);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}

	}

	/**
	 * Finds business application by ts_id
	 * @param tsId
	 * @return
	 */
	public CEMBusinessApplication findCEMBusinessApplicationById(Long tsId){	
		
		try{
			TypedQuery<CEMBusinessApplication> query = null;
			query = em.createQuery("SELECT c FROM CEMBusinessApplication c WHERE c.tsSoftDelete=false AND c.tsId=:tsId", CEMBusinessApplication.class);
			query.setParameter("tsId", tsId);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}

	}
	
	
	/**
	 * Finds business service. Fully qualified name including name of parent business application is needed.
	 * @param baName - name of business application 
	 * @param bsName - name of business service to find
	 * @return CEMBusinessService or null if nothing was found
	 */
	public CEMBusinessService findCEMBusinessServiceByName(String baName, String bsName){
		
		try{
			TypedQuery<CEMBusinessService> query = null;
			query = em.createNamedQuery("CEMBusinessService.findByName", CEMBusinessService.class);
			query.setParameter("baName", baName);
			query.setParameter("bsName", bsName);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}
		
	}

	
	/**
	 * Finds business application by ts_id
	 * @param tsId
	 * @return
	 */
	public CEMBusinessService findCEMBusinessServiceById(Long tsId){	
		
		try{
			TypedQuery<CEMBusinessService> query = null;
			query = em.createQuery("SELECT c FROM CEMBusinessService c WHERE c.tsSoftDelete=false AND c.tsId=:tsId", CEMBusinessService.class);
			query.setParameter("tsId", tsId);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}

	}
	
	
	/**
	 * Finds business transaction. Fully qualified name including name of parent business application and business service is needed.
	 * @param baName - name of business application 
	 * @param bsName - name of business service 
	 * @param btName - name of business transaction to find
	 * @return CEMBusinessTransaction or null if nothing was found
	 */
	public CEMBusinessTransaction findCEMBusinessTransactionByName(String baName, String bsName, String btName){
		
		try{
			TypedQuery<CEMBusinessTransaction> query = null;
			query = em.createNamedQuery("CEMBusinessTransaction.findByName", CEMBusinessTransaction.class);
			query.setParameter("baName", baName);
			query.setParameter("bsName", bsName);
			query.setParameter("btName", btName);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}
		
	}
	
	/**
	 * Finds business application by ts_id
	 * @param tsId
	 * @return
	 */
	public CEMBusinessTransaction findCEMBusinessTransactionById(Long tsId){	
		
		try{
			TypedQuery<CEMBusinessTransaction> query = null;
			query = em.createQuery("SELECT c FROM CEMBusinessTransaction c WHERE c.tsSoftDelete=false AND c.tsId=:tsId", CEMBusinessTransaction.class);
			query.setParameter("tsId", tsId);
			return query.getSingleResult();
		}catch(NoResultException e){
			log.warn("Nothing was found, returning null");
			return null;
		}

	}
	
	/**
	 * Finds CEMBusinessService by tsId contained in passed object. 
	 * @param cemBusinessService - CEMBusinessService from which tsId for search will be taken
	 * @return - 
	 * @throws AppDAOException
	 */
	public CEMBusinessService findCEMBusinessServiceById(CEMBusinessService cemBusinessService) throws AppDAOException{
		
		CEMBusinessService obj = findCEMBusinessServiceById(cemBusinessService.getTsId());
		if(obj == null) 
			throw new AppDAOException("findCEMBusinessServiceById found nothing.", AppDAOException.REASON_BS_NOT_FOUND_BY_ID);
		return obj;
	}
	
	/**
	 * Finds CEMBusinessTransaction by tsId contained in passed object
	 * @param cemBusinessTransaction - CEMBusinessTransaction from which tsId for search will be taken
	 * @return
	 * @throws AppDAOException
	 */
	public CEMBusinessTransaction findCEMBusinessTransactionById(CEMBusinessTransaction cemBusinessTransaction) throws AppDAOException{

		CEMBusinessTransaction obj = findCEMBusinessTransactionById(cemBusinessTransaction.getTsId());
		if(obj == null) 
			throw new AppDAOException("findCEMBusinessTransactionById found nothing.", AppDAOException.REASON_BT_NOT_FOUND_BY_ID);
		return obj;
		
	}
	
}
