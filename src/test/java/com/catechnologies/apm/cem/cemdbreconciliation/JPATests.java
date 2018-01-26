package com.catechnologies.apm.cem.cemdbreconciliation;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.*;
import com.catechnologies.apm.cem.cemdbreconciliation.util.JPAFactory;

import static org.junit.Assert.assertTrue;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;



import java.util.List;


public class JPATests {
	
	static final Logger log = LogManager.getLogger(JPATests.class.getName());

    private static EntityManager em;	
	
    @BeforeClass
    public static void setUp(){
    	em = JPAFactory.getInstance().getSourceEntityManager();
    	log.info("setUp()");
    }
    
    @AfterClass
    public static void tearDown() {
    	JPAFactory.getInstance().closeEMF();
    	em=null;
    	log.info("tearDown()");
    }
    
    
	@Test
	@Ignore("This is just template!")
    public void testTemplate() {
        assertTrue(true);
    }    
	
	@Test
	@Ignore
	public void findByNameCEMBusinessApplication(){	
	
		TypedQuery<CEMBusinessApplication> query = null;
		CEMBusinessApplication cemBusinessApplication = null;
		
		query = em.createNamedQuery("CEMBusinessApplication.findByName", CEMBusinessApplication.class);
		query.setParameter("baName", "TomcatSample");
		cemBusinessApplication = query.getSingleResult();
		
		log.info("cemBusinessApplication = "+cemBusinessApplication);
				
		
	}

	
	@Test
	@Ignore
	public void findAllCEMBusinessApplicationInterimSessionIdentification(){	
	
		TypedQuery<CEMBusinessApplicationInterimSessionIdentification> query = null;
		List<CEMBusinessApplicationInterimSessionIdentification> cemBusinessApplicationInterimSessionIdentificationList = null;
		CEMBusinessApplicationInterimSessionIdentification cemBusinessApplicationInterimSessionIdentification = null;
		
		query = em.createNamedQuery("CEMBusinessApplicationInterimSessionIdentification.findAll", CEMBusinessApplicationInterimSessionIdentification.class);
		cemBusinessApplicationInterimSessionIdentificationList = query.getResultList();
		
		for(int i=0;i<cemBusinessApplicationInterimSessionIdentificationList.size();i++){
			cemBusinessApplicationInterimSessionIdentification = cemBusinessApplicationInterimSessionIdentificationList.get(i);
			log.info("cemBusinessApplicationInterimSessionIdentificationList["+i+"] = "+cemBusinessApplicationInterimSessionIdentification);
		}
		
	}

	@Test
	@Ignore
	public void findAllCEMBusinessApplicationSessionIdentification(){	
	
		TypedQuery<CEMBusinessApplicationSessionIdentification> query = null;
		List<CEMBusinessApplicationSessionIdentification> cemBusinessApplicationSessionIdentificationList = null;
		CEMBusinessApplicationSessionIdentification cemBusinessApplicationSessionIdentification = null;
		
		query = em.createNamedQuery("CEMBusinessApplicationSessionIdentification.findAll", CEMBusinessApplicationSessionIdentification.class);
		cemBusinessApplicationSessionIdentificationList = query.getResultList();
		
		for(int i=0;i<cemBusinessApplicationSessionIdentificationList.size();i++){
			cemBusinessApplicationSessionIdentification = cemBusinessApplicationSessionIdentificationList.get(i);
			log.info("cemBusinessApplicationSessionIdentificationList["+i+"] = "+cemBusinessApplicationSessionIdentification);
		}
		
	}

	@Test
	@Ignore
	public void findAllCEMBusinessApplicationUserGroupIdentification(){	
	
		TypedQuery<CEMBusinessApplicationUserGroupIdentification> query = null;
		List<CEMBusinessApplicationUserGroupIdentification> cemBusinessApplicationUserGroupIdentificationList = null;
		CEMBusinessApplicationUserGroupIdentification cemBusinessApplicationUserGroupIdentification = null;
		
		query = em.createNamedQuery("CEMBusinessApplicationUserGroupIdentification.findAll", CEMBusinessApplicationUserGroupIdentification.class);
		cemBusinessApplicationUserGroupIdentificationList = query.getResultList();
		
		for(int i=0;i<cemBusinessApplicationUserGroupIdentificationList.size();i++){
			cemBusinessApplicationUserGroupIdentification = cemBusinessApplicationUserGroupIdentificationList.get(i);
			log.info("cemBusinessApplicationUserGroupIdentificationList["+i+"] = "+cemBusinessApplicationUserGroupIdentification);
		}
		
	}

	@Test
	@Ignore
	public void findAllCEMBusinessApplicationUserIdentification(){	
	
		TypedQuery<CEMBusinessApplicationUserIdentification> query = null;
		List<CEMBusinessApplicationUserIdentification> cemBusinessApplicationUserIdentificationList = null;
		CEMBusinessApplicationUserIdentification cemBusinessApplicationUserIdentification = null;
		
		query = em.createNamedQuery("CEMBusinessApplicationUserIdentification.findAll", CEMBusinessApplicationUserIdentification.class);
		cemBusinessApplicationUserIdentificationList = query.getResultList();
		
		for(int i=0;i<cemBusinessApplicationUserIdentificationList.size();i++){
			cemBusinessApplicationUserIdentification = cemBusinessApplicationUserIdentificationList.get(i);
			log.info("cemBusinessApplicationUserIdentificationList["+i+"] = "+cemBusinessApplicationUserIdentification);
		}
		
	}
	
	
	
/*	@Test
	@Ignore
	public void findAllCEMBusinessService(){	
		
		TypedQuery<CEMBusinessService> query = null;
		List<CEMBusinessService> cemBusinessServiceList = null;
		CEMBusinessService cemBusinessService = null;
		
		query = em.createNamedQuery("CEMBusinessService.findAll", CEMBusinessService.class);
		cemBusinessServiceList = query.getResultList();
		
		for(int i=0;i<cemBusinessServiceList.size();i++){
			cemBusinessService = cemBusinessServiceList.get(i);
			log.info("cemBusinessServiceList["+i+"] = "+cemBusinessService);
		}
		
	}*/

	@Test
	@Ignore
	public void findAllCEMBusinessService(){	
		
		TypedQuery<CEMBusinessService> query = null;
		CEMBusinessService cemBusinessService = null;
		
		query = em.createNamedQuery("CEMBusinessService.findByName", CEMBusinessService.class);
		
		query.setParameter("baName", "TomcatSample");
		query.setParameter("bsName", "TomcatSampleService");
		cemBusinessService = query.getSingleResult();
		log.info("cemBusinessServiceList= "+cemBusinessService);
		
	}
	
	
	@Test
	@Ignore
	public void findAllTsTransetGroup(){	
		
		TypedQuery<TsTransetGroup> query = null;
		List<TsTransetGroup> tsTransetGroupList = null;
		TsTransetGroup tsTransetGroup = null;
		
		query = em.createNamedQuery("TsTransetGroup.findAll", TsTransetGroup.class);
		tsTransetGroupList = query.getResultList();
		
		for(int i=0;i<tsTransetGroupList.size();i++){
			tsTransetGroup = tsTransetGroupList.get(i);
			log.info("tsTransetGroupList["+i+"] = "+tsTransetGroup);
		}
		
	}
	
	
	
	@Test
	@Ignore
	public void findAllCEMBusinessTransaction(){	
		
		TypedQuery<CEMBusinessTransaction> query = null;
		List<CEMBusinessTransaction> cemBusinessTransactionList = null;
		CEMBusinessTransaction cemBusinessTransaction = null;
		
		query = em.createNamedQuery("CEMBusinessTransaction.findAll", CEMBusinessTransaction.class);
		cemBusinessTransactionList = query.getResultList();
		
		for(int i=0;i<cemBusinessTransactionList.size();i++){
			cemBusinessTransaction = cemBusinessTransactionList.get(i);
			log.info("cemBusinessTransactionList["+i+"] = "+cemBusinessTransaction);
		}
		
	}

	@Test
	@Ignore
	public void findAllCEMSpecification(){	
		
		TypedQuery<CEMSpecification> query = null;
		List<CEMSpecification> cemSpecificationList = null;
		CEMSpecification cemSpecification = null;
		
		query = em.createNamedQuery("CEMSpecification.findAll", CEMSpecification.class);
		cemSpecificationList = query.getResultList();
		
		for(int i=0;i<cemSpecificationList.size();i++){
			cemSpecification = cemSpecificationList.get(i);
			log.info("cemSpecificationList["+i+"] = "+cemSpecification);
		}
		
	}
	
	
	@Test
	@Ignore
	public void findAllCEMTransaction(){	
		
		TypedQuery<CEMTransaction> query = null;
		List<CEMTransaction> cemTransactionList = null;
		CEMTransaction cemTransaction = null;
		
		query = em.createNamedQuery("CEMTransaction.findAll", CEMTransaction.class);
		cemTransactionList = query.getResultList();
		
		for(int i=0;i<cemTransactionList.size();i++){
			cemTransaction = cemTransactionList.get(i);
			log.info("cemTransactionList["+i+"] = "+cemTransaction);
		}
		
		
	}

	
	@Test
	@Ignore
	public void findAllCEMTransactionComponent(){	

		TypedQuery<CEMTransactionComponent> query = null;
		List<CEMTransactionComponent> cemTransactionComponentList = null;
		CEMTransactionComponent cemTransactionComponent = null;
		
		query = em.createNamedQuery("CEMTransactionComponent.findAll", CEMTransactionComponent.class);
		cemTransactionComponentList = query.getResultList();
		
		for(int i=0;i<cemTransactionComponentList.size();i++){
			cemTransactionComponent = cemTransactionComponentList.get(i);
			log.info("cemTransactionComponentList["+i+"] = "+cemTransactionComponent);
		}
		
	}

	@Test
	@Ignore
	public void findAllCEMTransactionComponentIdentification(){	

		TypedQuery<CEMTransactionComponentIdentification> query = null;
		List<CEMTransactionComponentIdentification> cemTransactionComponentIdentificationList = null;
		CEMTransactionComponentIdentification cemTransactionComponentIdentification = null;
		
		query = em.createNamedQuery("CEMTransactionComponentIdentification.findAll", CEMTransactionComponentIdentification.class);
		cemTransactionComponentIdentificationList = query.getResultList();
		
		for(int i=0;i<cemTransactionComponentIdentificationList.size();i++){
			cemTransactionComponentIdentification = cemTransactionComponentIdentificationList.get(i);
			log.info("cemTransactionComponentIdentificationList["+i+"] = "+cemTransactionComponentIdentification);
		}
		
	}
	
	
	/**
	 * Test below are dependent on actual data in database (they contain hard coded IDs)
	 * normally @Ignore annotation should be set and it should be commented/disabled only when
	 * doing particular test on relevant underlying data.
	 */
	
	@Test
	@Ignore
	public void findCEMBusinessApplication(){
		
		CEMBusinessApplication cemBusinessApplication = null;
		Long businessAppId = new Long("1");
		cemBusinessApplication = em.find(CEMBusinessApplication.class, businessAppId);
		log.info("cemBusinessApplication = "+cemBusinessApplication);
		
	}

	@Test
	@Ignore
	public void findCEMBusinessApplicationInterimSessionIdentification(){
		CEMBusinessApplicationInterimSessionIdentification cemBusinessApplicationInterimSessionIdentification = null;
		CEMBusinessApplicationInterimSessionIdentificationPK cemBusinessApplicationInterimSessionIdentificationPK = new CEMBusinessApplicationInterimSessionIdentificationPK();
		
		cemBusinessApplicationInterimSessionIdentificationPK.setTsAppId(new Long("700000000000000000"));
		cemBusinessApplicationInterimSessionIdentificationPK.setTsIdType(new Integer("0"));
		cemBusinessApplicationInterimSessionIdentificationPK.setTsName("xixi123");
		cemBusinessApplicationInterimSessionIdentificationPK.setTsType("LOCATION_URL");
		
		cemBusinessApplicationInterimSessionIdentification = em.find(CEMBusinessApplicationInterimSessionIdentification.class, cemBusinessApplicationInterimSessionIdentificationPK);
		log.info("cemBusinessApplicationInterimSessionIdentification = "+cemBusinessApplicationInterimSessionIdentification);
	}

	@Test
	@Ignore
	public void findCEMBusinessApplicationSessionIdentification(){
		
		CEMBusinessApplicationSessionIdentification cemBusinessApplicationSessionIdentification = null;
		CEMBusinessApplicationSessionIdentificationPK cemBusinessApplicationSessionIdentificationPK = new CEMBusinessApplicationSessionIdentificationPK();
		
		cemBusinessApplicationSessionIdentificationPK.setTsAppId(new Long("700000000000000000"));
		cemBusinessApplicationSessionIdentificationPK.setTsGroup(new Integer("1"));
		cemBusinessApplicationSessionIdentificationPK.setTsIdType(new Integer("0"));
		cemBusinessApplicationSessionIdentificationPK.setTsName("/path/1");
		cemBusinessApplicationSessionIdentificationPK.setTsType("PATH");
		
		cemBusinessApplicationSessionIdentification = em.find(CEMBusinessApplicationSessionIdentification.class, cemBusinessApplicationSessionIdentificationPK);
		log.info("cemBusinessApplicationSessionIdentification = "+cemBusinessApplicationSessionIdentification);
		
	}

	@Test
	@Ignore
	public void findCEMBusinessApplicationUserGroupIdentification(){
		
		CEMBusinessApplicationUserGroupIdentification cemBusinessApplicationUserGroupIdentification = null;
		CEMBusinessApplicationUserGroupIdentificationPK cemBusinessApplicationUserGroupIdentificationPK = new CEMBusinessApplicationUserGroupIdentificationPK();
		
		cemBusinessApplicationUserGroupIdentificationPK.setTsAppId(new Long("700000000000000000"));
		cemBusinessApplicationUserGroupIdentificationPK.setTsName("Post1");
		cemBusinessApplicationUserGroupIdentificationPK.setTsType("POST");
		
		cemBusinessApplicationUserGroupIdentification = em.find(CEMBusinessApplicationUserGroupIdentification.class, cemBusinessApplicationUserGroupIdentificationPK);
		log.info("cemBusinessApplicationUserGroupIdentification = "+cemBusinessApplicationUserGroupIdentification);
		
	}

	@Test
	@Ignore
	public void findCEMBusinessApplicationUserIdentification(){
		
		CEMBusinessApplicationUserIdentification cemBusinessApplicationUserIdentification = null;
		CEMBusinessApplicationUserIdentificationPK cemBusinessApplicationUserIdentificationPK = new CEMBusinessApplicationUserIdentificationPK();
		
		cemBusinessApplicationUserIdentificationPK.setTsAppId(new Long("700000000000000000"));
		cemBusinessApplicationUserIdentificationPK.setTsGroup(new Integer("1"));
		cemBusinessApplicationUserIdentificationPK.setTsName("Path");
		cemBusinessApplicationUserIdentificationPK.setTsType("URL");
		
		cemBusinessApplicationUserIdentification = em.find(CEMBusinessApplicationUserIdentification.class, cemBusinessApplicationUserIdentificationPK);
		log.info("cemBusinessApplicationUserIdentification = "+cemBusinessApplicationUserIdentification);
		
	}

	@Test
	@Ignore
	public void findCEMBusinessService(){
		
		CEMBusinessService cemBusinessService = null;
		Long businessServiceId = new Long("700000000000000001");
		cemBusinessService = em.find(CEMBusinessService.class, businessServiceId);
		log.info("cemBusinessService = "+cemBusinessService);
		
	}

	@Test
	@Ignore
	public void findTsTransetGroup(){

		TsTransetGroup tsTransetGroup = null;
		Long tsTransetGroupId = new Long("700000000000000001");
		tsTransetGroup = em.find(TsTransetGroup.class, tsTransetGroupId);
		log.info("tsTransetGroup = "+tsTransetGroup);
		
	}
	
	
	@Test
	@Ignore
	public void findCEMBusinessTransaction(){
		
		CEMBusinessTransaction cemBusinessTransaction = null;
		Long cemBusinessTransactionId = new Long("700000000000000037");
		cemBusinessTransaction = em.find(CEMBusinessTransaction.class, cemBusinessTransactionId);
		log.info("cemBusinessTransaction = "+cemBusinessTransaction);
		
	}

	@Test
	@Ignore
	public void findCEMSpecification(){
		
		CEMSpecification cemSpecification = null;
		Long cemSpecificationId = new Long("700000000000000097");
		cemSpecification = em.find(CEMSpecification.class, cemSpecificationId);
		log.info("cemSpecification = "+cemSpecification);
		
	}

	@Test
	@Ignore
	public void findCEMTransaction(){
		
		CEMTransaction cemTransaction = null;
		Long cemTransactionId = new Long("700000000000000036");
		cemTransaction = em.find(CEMTransaction.class, cemTransactionId);
		log.info("cemTransaction = "+cemTransaction);
		
	}

	@Test
	@Ignore
	public void findCEMTransactionComponent(){

		CEMTransactionComponent cemTransactionComponent = null;
		Long cemTransactionComponentId = new Long("700000000000000065");
		cemTransactionComponent = em.find(CEMTransactionComponent.class, cemTransactionComponentId);
		log.info("cemTransactionComponent = "+cemTransactionComponent);
		
	}

	@Test
	@Ignore
	public void findCEMTransactionComponentIdentification(){
		
		CEMTransactionComponentIdentification cemTransactionComponentIdentification = null;
		
		CEMTransactionComponentIdentificationPK cemTransactionComponentIdentificationPK = new CEMTransactionComponentIdentificationPK();
		
		cemTransactionComponentIdentificationPK.setTsName("Connection");
		cemTransactionComponentIdentificationPK.setTsParamType("HTTP");
		cemTransactionComponentIdentificationPK.setTsTrancompId(new Long("700000000000000064"));
		
		
		cemTransactionComponentIdentification = em.find(CEMTransactionComponentIdentification.class, cemTransactionComponentIdentificationPK);
		log.info("cemTransactionComponentIdentification = "+cemTransactionComponentIdentification);
		
	}	
	
}
