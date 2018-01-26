package com.catechnologies.apm.cem.cemdbreconciliation;

import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.ArrayList;
import java.util.Vector;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.*;
import com.catechnologies.apm.cem.cemdbreconciliation.util.*;

/**
 * 
 * @author bezad01
 *
 */
public class EntityMergeTests {

	static final Logger log = LogManager.getLogger(EntityMergeTests.class.getName());
	
    @BeforeClass
    public static void setUp(){
    	log.info("setUp()");
    }
    
    @AfterClass
    public static void tearDown() {
    	log.info("tearDown()");
    }
    
    
	@Test
	@Ignore("This is just template!")
    public void testTemplate() {
        assertTrue(true);
    }    
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeCEMTransactionComponentIdentification(){
		
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		CEMBusinessService sourceSV = new CEMBusinessService();
		CEMBusinessService targetSV = new CEMBusinessService();

		CEMBusinessTransaction sourceBT = new CEMBusinessTransaction();
		CEMBusinessTransaction targetBT = new CEMBusinessTransaction();
		
		CEMTransaction sourceTR = new CEMTransaction();
		CEMTransaction targetTR = new CEMTransaction();

		CEMTransactionComponent sourceTC = new CEMTransactionComponent();
		CEMTransactionComponent targetTC = new CEMTransactionComponent();
		
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");
		
		sourceSV.setTsName("Service1");
		targetSV.setTsName("Service1");

		sourceBT.setTsName("/sample/hello");
		targetBT.setTsName("/sample/hello");

		sourceTR.setTsName("/sample/hello");
		targetTR.setTsName("/sample/hello");
		
		sourceTC.setTsName("/sample/hello");
		targetTC.setTsName("/sample/hello");
		
		
		sourceSV.setTsApp(sourceBA);
		targetSV.setTsApp(targetBA);

		sourceBT.setTsTranDefGroup(sourceSV);
		targetBT.setTsTranDefGroup(targetSV);

		sourceTR.setTsTranset(sourceBT);
		targetTR.setTsTranset(targetBT);
		
		sourceTC.setTsTranunit(sourceTR);
		targetTC.setTsTranunit(targetTR);		
		
		CEMTransactionComponentIdentification source = new CEMTransactionComponentIdentification();
		CEMTransactionComponentIdentification target = new CEMTransactionComponentIdentification();
		
		CEMTransactionComponentIdentificationPK sourcePK = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK = new CEMTransactionComponentIdentificationPK();
		
		source.setTsTrancomp(sourceTC);
		target.setTsTrancomp(targetTC);
		
		sourcePK.setTsName("ClientIP");
		sourcePK.setTsParamType("URL");
		sourcePK.setTsTrancompId(new Long("700000000000000064"));
		
		targetPK.setTsName("ClientIP");
		targetPK.setTsParamType("URL");
		targetPK.setTsTrancompId(new Long("700000000000000064"));
		
		source.setId(sourcePK);
		target.setId(targetPK);
		
		source.setTsAutogenSequence(new Integer("9"));
		source.setTsNameType(new Integer("0"));
		source.setTsOperator(new Integer("0"));
		source.setTsOriginalValue("127.0.0.1");
		source.setTsPattern("127.0.0.1");
		source.setTsSoftDelete(Boolean.FALSE);
		source.setVersionInfo(new Long("0"));

		//target is same, the only difference is in IP addresses 127.0.0.1 -> 192.168.128.135
		target.setTsAutogenSequence(new Integer("9"));
		target.setTsNameType(new Integer("0"));
		target.setTsOperator(new Integer("0"));
		target.setTsOriginalValue("192.168.128.135");
		target.setTsPattern("192.168.128.135");
		target.setTsSoftDelete(Boolean.FALSE);
		target.setVersionInfo(new Long("0"));
		
		assertTrue(source.equals(target)); //equals must return true even though some attributes are different 
										   //since for equals only Component Identification Type & Name matters (contained in PK)
		
		targetPK.setTsTrancompId(new Long("700000000000000664"));
		assertTrue(source.equals(target));//even FK ID to component table might be different and they are still considered to be same entity
											
		
		
		targetPK.setTsParamType("URL123");
		assertFalse(source.equals(target));//different type => different entity
		
		targetPK.setTsParamType("URL");
		assertTrue(source.equals(target));
		
		source.copyTo(target);
		
		assertTrue(source.equals(target));
		
		
		assertTrue(target.getTsOriginalValue().equals("127.0.0.1"));
		assertTrue(target.getTsPattern().equals("127.0.0.1"));
		
		assertTrue(target.getId().getTsTrancompId().equals(new Long("700000000000000664")));//component_id must be not merged
		
	}
	
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeCEMSpecification(){
		
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		CEMBusinessService sourceSV = new CEMBusinessService();
		CEMBusinessService targetSV = new CEMBusinessService();
		
		CEMBusinessTransaction sourceBT = new CEMBusinessTransaction();
		CEMBusinessTransaction targetBT = new CEMBusinessTransaction();
		
		CEMTransaction sourceTR = new CEMTransaction();
		CEMTransaction targetTR = new CEMTransaction();
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");
		
		sourceSV.setTsApp(sourceBA);
		targetSV.setTsApp(targetBA);
		
		sourceSV.setTsName("Service1");
		targetSV.setTsName("Service1");
		
		sourceBT.setTsName("BusinessTransaction1");
		targetBT.setTsName("BusinessTransaction1");
		
		sourceTR.setTsName("Transaction1");
		targetTR.setTsName("Transaction1");
		
		sourceSV.setTsTransets(new Vector<CEMBusinessTransaction>());
		targetSV.setTsTransets(new Vector<CEMBusinessTransaction>());
		sourceSV.addTsTranset(sourceBT);
		targetSV.addTsTranset(targetBT);
		
		
		sourceBT.setTsTranunits(new Vector<CEMTransaction>());
		targetBT.setTsTranunits(new Vector<CEMTransaction>());
		sourceBT.addTsTranunit(sourceTR);
		targetBT.addTsTranunit(targetTR);
		
		
		sourceTR.setTsTranset(sourceBT);
		targetTR.setTsTranset(targetBT);
		
		sourceBT.setTsTranDefGroup(sourceSV);
		targetBT.setTsTranDefGroup(targetSV);
		
		CEMSpecification source = new CEMSpecification();
		CEMSpecification target = new CEMSpecification();
		
		source.setTsTranset(sourceBT);
		target.setTsTranset(targetBT);
		
		source.setTsTranunit(sourceTR);
		target.setTsTranunit(targetTR);
		
		source.setTsTrancomp(null);//even though database structure allows to bind defect specification on component level in reality this never happens
		target.setTsTrancomp(null);//CEM GUI does not allow to bind defect specification to component, it is bound always to transaction to which component belongs
		
		source.setTsId(new Long("700000000000000097"));
		target.setTsId(new Long("700000000000000097"));
												        
		source.setTsAttributeId(new Long("1"));
		source.setTsCondition(null);
		source.setTsEnabled(Boolean.TRUE);
		source.setTsHeaderName("Name");
		source.setTsImportanceId(new Long("5"));
		source.setTsLocked(Boolean.FALSE);
		source.setTsName("High Throughput");
		source.setTsSoftDelete(Boolean.FALSE);
		source.setTsTranType(new Integer("3"));
		source.setTsType(new Integer("11"));
		source.setTsValue("10000");
		source.setVersionInfo(new Long("0"));

		//the only difference is initially in version attribute (0 vs 666)
		target.setTsAttributeId(new Long("1"));
		target.setTsCondition(null);
		target.setTsEnabled(Boolean.TRUE);
		target.setTsHeaderName("Name");
		target.setTsImportanceId(new Long("5"));
		target.setTsLocked(Boolean.FALSE);
		target.setTsName("High Throughput");
		target.setTsSoftDelete(Boolean.FALSE);
		target.setTsTranType(new Integer("3"));
		target.setTsType(new Integer("11"));
		target.setTsValue("10000");
		target.setVersionInfo(new Long("666"));
		
		assertTrue(source.equals(target)); //names of Business Service, Business Transaction, Transaction and Defect name are same => identical entities
		
		target.setTsId(new Long("700000000000066697"));
		assertTrue(source.equals(target));//different PK ID does not matter, still same entities
		
		
		target.setTsTranType(new Integer("3666"));
		assertTrue(source.equals(target));//different ts_tran_type does not matter as well, still same entities
		
		
		source.copyTo(target);
		
		assertTrue(source.equals(target));
		
		
		assertTrue(target.getTsTranType().equals(3));//ts_tran_type was merged
		
	}
	

	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
    public void mergeCEMTransactionComponentIdentificationLists() throws Exception {
        
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		CEMBusinessService sourceSV = new CEMBusinessService();
		CEMBusinessService targetSV = new CEMBusinessService();

		CEMBusinessTransaction sourceBT = new CEMBusinessTransaction();
		CEMBusinessTransaction targetBT = new CEMBusinessTransaction();
		
		CEMTransaction sourceTR = new CEMTransaction();
		CEMTransaction targetTR = new CEMTransaction();
		
		CEMTransactionComponent sourceTC=new CEMTransactionComponent();
		CEMTransactionComponent targetTC=new CEMTransactionComponent();
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");
		
		sourceSV.setTsName("Service1");
		targetSV.setTsName("Service1");

		sourceBT.setTsName("/sample/hello");
		targetBT.setTsName("/sample/hello");

		sourceTR.setTsName("/sample/hello");
		targetTR.setTsName("/sample/hello");
		
		sourceTC.setTsName("/sample/hello");
		targetTC.setTsName("/sample/hello");
		
		
		sourceSV.setTsApp(sourceBA);
		targetSV.setTsApp(targetBA);

		sourceBT.setTsTranDefGroup(sourceSV);
		targetBT.setTsTranDefGroup(targetSV);

		sourceTR.setTsTranset(sourceBT);
		targetTR.setTsTranset(targetBT);
		
		sourceTC.setTsTranunit(sourceTR);
		targetTC.setTsTranunit(targetTR);
		
		
		CEMTransactionComponentIdentification source1 = new CEMTransactionComponentIdentification();//this one will be updated (CI1)
		CEMTransactionComponentIdentification source2 = new CEMTransactionComponentIdentification();//this one will be updated (CI2)
		CEMTransactionComponentIdentification source3 = new CEMTransactionComponentIdentification();//this one will be inserted
		
		CEMTransactionComponentIdentification target1 = new CEMTransactionComponentIdentification();//this one will be deleted since it does not exist in source any more
		CEMTransactionComponentIdentification target2 = new CEMTransactionComponentIdentification();//this one will be updated(CI2)
		CEMTransactionComponentIdentification target3 = new CEMTransactionComponentIdentification();//this one will be updated(CI1)
		CEMTransactionComponentIdentification target4 = new CEMTransactionComponentIdentification();//this one will be deleted since it does not exist in source any more
		
		source1.setTsTrancomp(sourceTC);
		source2.setTsTrancomp(sourceTC);
		source3.setTsTrancomp(sourceTC);
		
		target1.setTsTrancomp(targetTC);
		target2.setTsTrancomp(targetTC);
		target3.setTsTrancomp(targetTC);
		target4.setTsTrancomp(targetTC);
		
		CEMTransactionComponentIdentificationPK sourcePK1 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK sourcePK2 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK sourcePK3 = new CEMTransactionComponentIdentificationPK();
		
		CEMTransactionComponentIdentificationPK targetPK1 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK2 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK3 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK4 = new CEMTransactionComponentIdentificationPK();
		
		sourceTC.setTsId(new Long("700000000000000064"));
		targetTC.setTsId(new Long("700000000000006664"));
		
		
		sourcePK1.setTsName("ClientIP");
		sourcePK1.setTsParamType("URL");
		sourcePK1.setTsTrancompId(new Long("700000000000000064"));
		source1.setId(sourcePK1);
		source1.setTsAutogenSequence(new Integer("0"));
		source1.setTsNameType(new Integer("0"));
		source1.setTsOperator(new Integer("0"));
		source1.setTsOriginalValue("127.0.0.1");
		source1.setTsPattern("127.0.0.1");
		source1.setTsSoftDelete(Boolean.FALSE);
		source1.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source1.setVersionInfo(new Long("0"));
		source1.setTsTrancomp(sourceTC);
		
		sourcePK2.setTsName("Host");
		sourcePK2.setTsParamType("URL");
		sourcePK2.setTsTrancompId(new Long("700000000000000064"));
		source2.setId(sourcePK2);
		source2.setTsAutogenSequence(new Integer("1"));
		source2.setTsNameType(new Integer("0"));
		source2.setTsOperator(new Integer("1"));
		source2.setTsOriginalValue("127.0.0.1");
		source2.setTsPattern("127.0.0.1");
		source2.setTsSoftDelete(Boolean.FALSE);
		source2.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source2.setVersionInfo(new Long("0"));
		source2.setTsTrancomp(sourceTC);
		
		sourcePK3.setTsName("Port");
		sourcePK3.setTsParamType("URL");
		sourcePK3.setTsTrancompId(new Long("700000000000000064"));
		source3.setId(sourcePK3);
		source3.setTsAutogenSequence(new Integer("20"));
		source3.setTsNameType(new Integer("0"));
		source3.setTsOperator(new Integer("0"));
		source3.setTsOriginalValue("8080");
		source3.setTsPattern("8080");
		source3.setTsSoftDelete(Boolean.FALSE);
		source3.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source3.setVersionInfo(new Long("0"));
		source3.setTsTrancomp(sourceTC);
		
		targetPK1.setTsName("Server");
		targetPK1.setTsParamType("HTTP_RESPONSE_HEADER");
		targetPK1.setTsTrancompId(new Long("700000000000006664"));
		target1.setId(targetPK1);
		target1.setTsAutogenSequence(new Integer("0"));
		target1.setTsNameType(new Integer("0"));
		target1.setTsOperator(new Integer("0"));
		target1.setTsOriginalValue("Apache-Coyote/1.1");
		target1.setTsPattern("Apache-Coyote/1.1");
		target1.setTsSoftDelete(Boolean.FALSE);
		target1.setTsTrancomp(null); 
		target1.setVersionInfo(new Long("0"));
		target1.setTsTrancomp(targetTC);
		
		targetPK2.setTsName("Host");
		targetPK2.setTsParamType("URL");
		targetPK2.setTsTrancompId(new Long("700000000000006664"));//FK of component is different, but still if component name is same and tsName and tsParamType match this is considered same entity as source2
		target2.setId(targetPK2);
		//target2.setTsAutogenSequence(new Integer("1"));
		target2.setTsAutogenSequence(null);
		target2.setTsNameType(new Integer("0"));
		target2.setTsOperator(new Integer("1"));
		target2.setTsOriginalValue("192.168.128.135");
		target2.setTsPattern("192.168.128.135");
		target2.setTsSoftDelete(Boolean.FALSE);
		target2.setTsTrancomp(null); 
		target2.setVersionInfo(new Long("456"));
		target2.setTsTrancomp(targetTC);
		
		targetPK3.setTsName("ClientIP");
		targetPK3.setTsParamType("URL");
		targetPK3.setTsTrancompId(new Long("700000000000006664"));
		target3.setId(targetPK3);
		target3.setTsAutogenSequence(new Integer("2"));
		target3.setTsNameType(new Integer("0"));
		target3.setTsOperator(new Integer("0"));
		target3.setTsOriginalValue("192.168.128.136");
		target3.setTsPattern("192.168.128.136");
		target3.setTsSoftDelete(Boolean.FALSE);
		target3.setTsTrancomp(null); 
		target3.setVersionInfo(new Long("123"));
		target3.setTsTrancomp(targetTC);
		
		targetPK4.setTsName("Status");
		targetPK4.setTsParamType("HTTP_RESPONSE");
		targetPK4.setTsTrancompId(new Long("700000000000006664"));
		target4.setId(targetPK4);
		target4.setTsAutogenSequence(new Integer("3"));
		target4.setTsNameType(new Integer("0"));
		target4.setTsOperator(new Integer("1"));
		target4.setTsOriginalValue("200");
		target4.setTsPattern("200");
		target4.setTsSoftDelete(Boolean.FALSE);
		target4.setTsTrancomp(null); 
		target4.setVersionInfo(new Long("0"));
		target4.setTsTrancomp(targetTC);
		
		Vector<CEMTransactionComponentIdentification> source = new Vector<CEMTransactionComponentIdentification>();
		Vector<CEMTransactionComponentIdentification> target = new Vector<CEMTransactionComponentIdentification>();
		
		sourceTC.setTsParams(source);
		targetTC.setTsParams(target);
		
		
		source.add(source1);
		source.add(source2);
		source.add(source3);
		
		target.add(target1);
		target.add(target2);
		target.add(target3);
		target.add(target4);
		
		new CEMEntityMergeHelper<CEMTransactionComponent, CEMTransactionComponentIdentification>().mergeChildEntityLists(targetTC, source, target);
		
		assertTrue(target.size()==5);
		//this worked before when we deleted items physically, now we are doing soft deletes
		//assertFalse(target.contains(target1));
		//assertFalse(target.contains(target4));
		
		assertTrue(target.contains(target1));
		assertTrue(target.contains(target4));
		assertTrue(target1.getTsSoftDelete().booleanValue()==true);
		assertTrue(target4.getTsSoftDelete().booleanValue()==true);
		
		
		assertTrue(target.contains(target2));
		assertTrue(target.contains(target3));
		assertTrue(target.contains(source1));
		
		assertTrue(target2.getVersionInfo()==0);
		assertTrue(target3.getVersionInfo()==0);
		
		assertTrue(target2.getTsOriginalValue().equals("127.0.0.1"));
		assertTrue(target2.getTsPattern().equals("127.0.0.1"));
		assertTrue(target3.getTsOriginalValue().equals("127.0.0.1"));
		assertTrue(target3.getTsPattern().equals("127.0.0.1"));
		
		
		assertTrue(source3.getTsTrancomp() == targetTC);//since source3 was added to target list we must make sure its parent component was changed as well!!!
		
		log.info("source3 = "+source3);
		
		assertTrue(source3.getTsAutogenSequence().intValue() ==  4);
		
    }    
	
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
    public void mergeCEMSpecificationListsOnTransactionLevel() throws Exception {
		
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		CEMBusinessService sourceService = new CEMBusinessService();
		CEMBusinessService targetService = new CEMBusinessService();
		
		CEMBusinessTransaction sourceCEMBusinessTransaction = new CEMBusinessTransaction();
		CEMBusinessTransaction targetCEMBusinessTransaction = new CEMBusinessTransaction();
		CEMBusinessTransaction targetCEMBusinessTransaction2 = new CEMBusinessTransaction();
		
		CEMTransaction sourceCEMTransaction = new CEMTransaction();
		CEMTransaction targetCEMTransaction = new CEMTransaction();
		CEMTransaction targetCEMTransaction2 = new CEMTransaction();
		
		CEMTransactionComponent sourceTC = new CEMTransactionComponent();
		CEMTransactionComponent targetTC = new CEMTransactionComponent();
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");
		
		sourceService.setTsApp(sourceBA);
		targetService.setTsApp(targetBA);
		
		sourceService.setTsName("Service1");
		sourceCEMBusinessTransaction.setTsName("BT1");
		targetCEMBusinessTransaction.setTsName("BT1");
		targetCEMBusinessTransaction2.setTsName("BT2");
		
		targetService.setTsName("Service1");
		sourceCEMTransaction.setTsName("TR1");
		targetCEMTransaction.setTsName("TR1");
		targetCEMTransaction2.setTsName("TR2");
		
		sourceTC.setTsId(new Long("700000000000000064"));
		targetTC.setTsId(new Long("700000000000006664"));

		sourceTC.setTsName("component1");
		targetTC.setTsName("component1");
		
		sourceTC.setTsTranunit(sourceCEMTransaction);
		targetTC.setTsTranunit(targetCEMTransaction);
		
		sourceCEMTransaction.setTsTranset(sourceCEMBusinessTransaction);
		targetCEMTransaction.setTsTranset(targetCEMBusinessTransaction);
		targetCEMTransaction2.setTsTranset(targetCEMBusinessTransaction2);
		
		sourceCEMBusinessTransaction.setTsTranDefGroup(sourceService);
		targetCEMBusinessTransaction.setTsTranDefGroup(targetService);
		targetCEMBusinessTransaction2.setTsTranDefGroup(targetService);
		
		CEMSpecification sourceSpec1 = new CEMSpecification();//to be updated (SP1)
		CEMSpecification sourceSpec2 = new CEMSpecification();//to be inserted
		CEMSpecification sourceSpec3 = new CEMSpecification();//to be updated (SP2)

		CEMSpecification targetSpec1 = new CEMSpecification();//to be updated (SP1)
		CEMSpecification targetSpec2 = new CEMSpecification();//to be deleted
		CEMSpecification targetSpec3 = new CEMSpecification();//to be deleted
		CEMSpecification targetSpec4 = new CEMSpecification();//to be updated (SP2)
		
		
		Vector<CEMSpecification> source = new Vector<CEMSpecification>();
		Vector<CEMSpecification> target = new Vector<CEMSpecification>();
		
		source.add(sourceSpec1);
		source.add(sourceSpec2);
		source.add(sourceSpec3);
		
		target.add(targetSpec1);
		target.add(targetSpec2);
		target.add(targetSpec3);
		target.add(targetSpec4);
		
		sourceSpec1.setTsId(new Long("1"));
		sourceSpec1.setTsAttributeId(new Long("1"));
		sourceSpec1.setTsCondition(null);
		sourceSpec1.setTsEnabled(Boolean.TRUE);
		sourceSpec1.setTsHeaderName(null);//null or Name
		sourceSpec1.setTsImportanceId(new Long("5"));
		sourceSpec1.setTsLocked(Boolean.FALSE);
		sourceSpec1.setTsName("Slow Time");
		sourceSpec1.setTsSoftDelete(Boolean.FALSE);
		sourceSpec1.setTsTranType(new Integer("3"));
		sourceSpec1.setTsType(new Integer("1"));
		sourceSpec1.setTsValue("1500");
		sourceSpec1.setVersionInfo(new Long("6"));
		sourceSpec1.setTsTrancomp(null);//always null
		sourceSpec1.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec1.setTsTranunit(sourceCEMTransaction);//can be null as well
		
		sourceSpec2.setTsId(new Long("2"));
		sourceSpec2.setTsAttributeId(new Long("122"));
		sourceSpec2.setTsCondition(null);
		sourceSpec2.setTsEnabled(Boolean.FALSE);
		sourceSpec2.setTsHeaderName("Name");//null or Name
		sourceSpec2.setTsImportanceId(new Long("5"));
		sourceSpec2.setTsLocked(Boolean.FALSE);
		sourceSpec2.setTsName("Fast Time");
		sourceSpec2.setTsSoftDelete(Boolean.FALSE);
		sourceSpec2.setTsTranType(new Integer("3"));
		sourceSpec2.setTsType(new Integer("1"));
		sourceSpec2.setTsValue("100");
		sourceSpec2.setVersionInfo(new Long("7"));
		sourceSpec2.setTsTrancomp(null);//always null
		sourceSpec2.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec2.setTsTranunit(sourceCEMTransaction);//can be null as well

		sourceSpec3.setTsId(new Long("3"));
		sourceSpec3.setTsAttributeId(new Long("122333"));
		sourceSpec3.setTsCondition(null);
		sourceSpec3.setTsEnabled(Boolean.FALSE);
		sourceSpec3.setTsHeaderName("Name");//null or Name
		sourceSpec3.setTsImportanceId(new Long("5"));
		sourceSpec3.setTsLocked(Boolean.TRUE);
		sourceSpec3.setTsName("Large Size");
		sourceSpec3.setTsSoftDelete(Boolean.FALSE);
		sourceSpec3.setTsTranType(new Integer("3"));
		sourceSpec3.setTsType(new Integer("1"));
		sourceSpec3.setTsValue("650000");
		sourceSpec3.setVersionInfo(new Long("9"));
		sourceSpec3.setTsTrancomp(null);//always null
		sourceSpec3.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec3.setTsTranunit(sourceCEMTransaction);//can be null as well
		
		//attributes different from sourceSpec1 marked by comment  //***********
		targetSpec1.setTsId(new Long("10"));
		targetSpec1.setTsAttributeId(new Long("1"));
		targetSpec1.setTsCondition(null);
		targetSpec1.setTsEnabled(Boolean.TRUE);
		targetSpec1.setTsHeaderName(null);//null or Name
		targetSpec1.setTsImportanceId(new Long("5"));
		targetSpec1.setTsLocked(Boolean.TRUE);//***********
		targetSpec1.setTsName("Slow Time");
		targetSpec1.setTsSoftDelete(Boolean.FALSE);
		targetSpec1.setTsTranType(new Integer("30"));//***********
		targetSpec1.setTsType(new Integer("1"));
		targetSpec1.setTsValue("1600");//***********
		targetSpec1.setVersionInfo(new Long("6"));
		targetSpec1.setTsTrancomp(null);//always null
		targetSpec1.setTsTranset(targetCEMBusinessTransaction);
		targetSpec1.setTsTranunit(targetCEMTransaction);//can be null as well

		//belongs to targetCEMBusinessTransaction2, targetCEMTransaction2 => No counter-match in source data, will be deleted.
		targetSpec2.setTsId(new Long("20"));
		targetSpec2.setTsAttributeId(new Long("1"));
		targetSpec2.setTsCondition(null);
		targetSpec2.setTsEnabled(Boolean.TRUE);
		targetSpec2.setTsHeaderName(null);//null or Name
		targetSpec2.setTsImportanceId(new Long("5"));
		targetSpec2.setTsLocked(Boolean.TRUE);
		targetSpec2.setTsName("Slow Time");
		targetSpec2.setTsSoftDelete(Boolean.FALSE);
		targetSpec2.setTsTranType(new Integer("30"));
		targetSpec2.setTsType(new Integer("1"));
		targetSpec2.setTsValue("1600");
		targetSpec2.setVersionInfo(new Long("6"));
		targetSpec2.setTsTrancomp(null);//always null
		targetSpec2.setTsTranset(targetCEMBusinessTransaction2);
		targetSpec2.setTsTranunit(targetCEMTransaction2);//can be null as well

		//belongs to targetCEMBusinessTransaction2, targetCEMTransaction2 => No counter-match in source data, will be deleted.
		targetSpec3.setTsId(new Long("30"));
		targetSpec3.setTsAttributeId(new Long("1"));
		targetSpec3.setTsCondition(null);
		targetSpec3.setTsEnabled(Boolean.TRUE);
		targetSpec3.setTsHeaderName(null);//null or Name
		targetSpec3.setTsImportanceId(new Long("5"));
		targetSpec3.setTsLocked(Boolean.TRUE);
		targetSpec3.setTsName("Slow Time");
		targetSpec3.setTsSoftDelete(Boolean.FALSE);
		targetSpec3.setTsTranType(new Integer("30"));
		targetSpec3.setTsType(new Integer("1"));
		targetSpec3.setTsValue("1600");
		targetSpec3.setVersionInfo(new Long("6"));
		targetSpec3.setTsTrancomp(null);//always null
		targetSpec3.setTsTranset(targetCEMBusinessTransaction2);
		targetSpec3.setTsTranunit(targetCEMTransaction2);//can be null as well
		
		//attributes different from sourceSpec3 marked by comment  //***********
		targetSpec4.setTsId(new Long("31"));
		targetSpec4.setTsAttributeId(new Long("666"));//***********
		targetSpec4.setTsCondition(null);
		targetSpec4.setTsEnabled(Boolean.FALSE);
		targetSpec4.setTsHeaderName(null);//null or Name //***********
		targetSpec4.setTsImportanceId(new Long("5"));
		targetSpec4.setTsLocked(Boolean.FALSE);//***********
		targetSpec4.setTsName("Large Size");
		targetSpec4.setTsSoftDelete(Boolean.FALSE);
		targetSpec4.setTsTranType(new Integer("3"));
		targetSpec4.setTsType(new Integer("10"));//***********
		targetSpec4.setTsValue("200000");//***********
		targetSpec4.setVersionInfo(new Long("9"));
		targetSpec4.setTsTrancomp(null);//always null
		targetSpec4.setTsTranset(targetCEMBusinessTransaction);
		targetSpec4.setTsTranunit(targetCEMTransaction);//can be null as well
		
		
		assertTrue(sourceSpec2.getTsTranset() == sourceCEMBusinessTransaction);
		assertTrue(sourceSpec2.getTsTranunit() == sourceCEMTransaction);
		
		//CEM specifications are never defined on component level, testing this stuff does not make sense
		//new CEMEntityMergeHelper<CEMTransactionComponent, CEMSpecification>().mergeChildEntityLists(targetTC, source, target);
		
		new CEMEntityMergeHelper<CEMTransaction, CEMSpecification>().mergeChildEntityLists(targetCEMTransaction, source, target);
		

		//cloning now
		//assertFalse(sourceSpec2.getTsTranset() == sourceCEMBusinessTransaction);
		assertTrue(sourceSpec2.getTsTranset() == targetCEMBusinessTransaction);
		
		assertFalse(sourceSpec2.getTsTranunit() == sourceCEMTransaction);
		assertTrue(sourceSpec2.getTsTranunit() == targetCEMTransaction);
		
		
		assertTrue(target.size()==5);
		assertTrue(target.contains(sourceSpec2));
		assertTrue(target.contains(targetSpec1));
		assertTrue(target.contains(targetSpec4));
		
		
		assertTrue(targetSpec4.getTsLocked() == Boolean.TRUE);
		assertFalse(targetSpec4.getTsAttributeId()==new Long("666"));
		
	}

	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
    public void mergeCEMSpecificationListsOnBusinessTransactionLevel() throws Exception {
		
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		
		CEMBusinessService sourceService = new CEMBusinessService();
		CEMBusinessService targetService = new CEMBusinessService();
		
		CEMBusinessTransaction sourceCEMBusinessTransaction = new CEMBusinessTransaction();
		CEMBusinessTransaction targetCEMBusinessTransaction = new CEMBusinessTransaction();
		CEMBusinessTransaction targetCEMBusinessTransaction2 = new CEMBusinessTransaction();
		
		CEMTransaction sourceCEMTransaction = new CEMTransaction();
		CEMTransaction targetCEMTransaction = new CEMTransaction();
		CEMTransaction targetCEMTransaction2 = new CEMTransaction();
		
		CEMTransactionComponent sourceTC = new CEMTransactionComponent();
		CEMTransactionComponent targetTC = new CEMTransactionComponent();
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");

		sourceService.setTsApp(sourceBA);
		targetService.setTsApp(targetBA);
		
		
		sourceService.setTsName("Service1");
		sourceCEMBusinessTransaction.setTsName("BT1");
		targetCEMBusinessTransaction.setTsName("BT1");
		targetCEMBusinessTransaction2.setTsName("BT2");
		
		targetService.setTsName("Service1");
		sourceCEMTransaction.setTsName("TR1");
		targetCEMTransaction.setTsName("TR1");
		targetCEMTransaction2.setTsName("TR2");
		
		sourceTC.setTsId(new Long("700000000000000064"));
		targetTC.setTsId(new Long("700000000000006664"));

		sourceTC.setTsName("component1");
		targetTC.setTsName("component1");
		
		sourceTC.setTsTranunit(sourceCEMTransaction);
		targetTC.setTsTranunit(targetCEMTransaction);
		
		sourceCEMTransaction.setTsTranset(sourceCEMBusinessTransaction);
		targetCEMTransaction.setTsTranset(targetCEMBusinessTransaction);
		targetCEMTransaction2.setTsTranset(targetCEMBusinessTransaction2);
		
		sourceCEMBusinessTransaction.setTsTranDefGroup(sourceService);
		targetCEMBusinessTransaction.setTsTranDefGroup(targetService);
		targetCEMBusinessTransaction2.setTsTranDefGroup(targetService);
		
		CEMSpecification sourceSpec1 = new CEMSpecification();//to be updated (SP1)
		CEMSpecification sourceSpec2 = new CEMSpecification();//to be inserted
		CEMSpecification sourceSpec3 = new CEMSpecification();//to be updated (SP2)

		CEMSpecification targetSpec1 = new CEMSpecification();//to be updated (SP1)
		CEMSpecification targetSpec2 = new CEMSpecification();//to be deleted
		CEMSpecification targetSpec3 = new CEMSpecification();//to be deleted
		CEMSpecification targetSpec4 = new CEMSpecification();//to be updated (SP2)
		
		
		Vector<CEMSpecification> source = new Vector<CEMSpecification>();
		Vector<CEMSpecification> target = new Vector<CEMSpecification>();
		
		source.add(sourceSpec1);
		source.add(sourceSpec2);
		source.add(sourceSpec3);
		
		target.add(targetSpec1);
		target.add(targetSpec2);
		target.add(targetSpec3);
		target.add(targetSpec4);
		
		sourceSpec1.setTsId(new Long("1"));
		sourceSpec1.setTsAttributeId(new Long("1"));
		sourceSpec1.setTsCondition(null);
		sourceSpec1.setTsEnabled(Boolean.TRUE);
		sourceSpec1.setTsHeaderName(null);//null or Name
		sourceSpec1.setTsImportanceId(new Long("5"));
		sourceSpec1.setTsLocked(Boolean.FALSE);
		sourceSpec1.setTsName("Slow Time");
		sourceSpec1.setTsSoftDelete(Boolean.FALSE);
		sourceSpec1.setTsTranType(new Integer("3"));
		sourceSpec1.setTsType(new Integer("1"));
		sourceSpec1.setTsValue("1500");
		sourceSpec1.setVersionInfo(new Long("6"));
		sourceSpec1.setTsTrancomp(null);//always null
		sourceSpec1.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec1.setTsTranunit(null);//can be null as well
		
		sourceSpec2.setTsId(new Long("2"));
		sourceSpec2.setTsAttributeId(new Long("122"));
		sourceSpec2.setTsCondition(null);
		sourceSpec2.setTsEnabled(Boolean.FALSE);
		sourceSpec2.setTsHeaderName("Name");//null or Name
		sourceSpec2.setTsImportanceId(new Long("5"));
		sourceSpec2.setTsLocked(Boolean.FALSE);
		sourceSpec2.setTsName("Fast Time");
		sourceSpec2.setTsSoftDelete(Boolean.FALSE);
		sourceSpec2.setTsTranType(new Integer("3"));
		sourceSpec2.setTsType(new Integer("1"));
		sourceSpec2.setTsValue("100");
		sourceSpec2.setVersionInfo(new Long("7"));
		sourceSpec2.setTsTrancomp(null);//always null
		sourceSpec2.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec2.setTsTranunit(null);//can be null as well

		sourceSpec3.setTsId(new Long("3"));
		sourceSpec3.setTsAttributeId(new Long("122333"));
		sourceSpec3.setTsCondition(null);
		sourceSpec3.setTsEnabled(Boolean.FALSE);
		sourceSpec3.setTsHeaderName("Name");//null or Name
		sourceSpec3.setTsImportanceId(new Long("5"));
		sourceSpec3.setTsLocked(Boolean.TRUE);
		sourceSpec3.setTsName("Large Size");
		sourceSpec3.setTsSoftDelete(Boolean.FALSE);
		sourceSpec3.setTsTranType(new Integer("3"));
		sourceSpec3.setTsType(new Integer("1"));
		sourceSpec3.setTsValue("650000");
		sourceSpec3.setVersionInfo(new Long("9"));
		sourceSpec3.setTsTrancomp(null);//always null
		sourceSpec3.setTsTranset(sourceCEMBusinessTransaction);
		sourceSpec3.setTsTranunit(null);//can be null as well
		
		//attributes different from sourceSpec1 marked by comment  //***********
		targetSpec1.setTsId(new Long("10"));
		targetSpec1.setTsAttributeId(new Long("1"));
		targetSpec1.setTsCondition(null);
		targetSpec1.setTsEnabled(Boolean.TRUE);
		targetSpec1.setTsHeaderName(null);//null or Name
		targetSpec1.setTsImportanceId(new Long("5"));
		targetSpec1.setTsLocked(Boolean.TRUE);//***********
		targetSpec1.setTsName("Slow Time");
		targetSpec1.setTsSoftDelete(Boolean.FALSE);
		targetSpec1.setTsTranType(new Integer("30"));//***********
		targetSpec1.setTsType(new Integer("1"));
		targetSpec1.setTsValue("1600");//***********
		targetSpec1.setVersionInfo(new Long("6"));
		targetSpec1.setTsTrancomp(null);//always null
		targetSpec1.setTsTranset(targetCEMBusinessTransaction);
		targetSpec1.setTsTranunit(null);//can be null as well

		//belongs to targetCEMBusinessTransaction2, targetCEMTransaction2 => No counter-match in source data, will be deleted.
		targetSpec2.setTsId(new Long("20"));
		targetSpec2.setTsAttributeId(new Long("1"));
		targetSpec2.setTsCondition(null);
		targetSpec2.setTsEnabled(Boolean.TRUE);
		targetSpec2.setTsHeaderName(null);//null or Name
		targetSpec2.setTsImportanceId(new Long("5"));
		targetSpec2.setTsLocked(Boolean.TRUE);
		targetSpec2.setTsName("Slow Time");
		targetSpec2.setTsSoftDelete(Boolean.FALSE);
		targetSpec2.setTsTranType(new Integer("30"));
		targetSpec2.setTsType(new Integer("1"));
		targetSpec2.setTsValue("1600");
		targetSpec2.setVersionInfo(new Long("6"));
		targetSpec2.setTsTrancomp(null);//always null
		targetSpec2.setTsTranset(targetCEMBusinessTransaction2);
		targetSpec2.setTsTranunit(null);//can be null as well

		//belongs to targetCEMBusinessTransaction2, targetCEMTransaction2 => No counter-match in source data, will be deleted.
		targetSpec3.setTsId(new Long("30"));
		targetSpec3.setTsAttributeId(new Long("1"));
		targetSpec3.setTsCondition(null);
		targetSpec3.setTsEnabled(Boolean.TRUE);
		targetSpec3.setTsHeaderName(null);//null or Name
		targetSpec3.setTsImportanceId(new Long("5"));
		targetSpec3.setTsLocked(Boolean.TRUE);
		targetSpec3.setTsName("Slow Time");
		targetSpec3.setTsSoftDelete(Boolean.FALSE);
		targetSpec3.setTsTranType(new Integer("30"));
		targetSpec3.setTsType(new Integer("1"));
		targetSpec3.setTsValue("1600");
		targetSpec3.setVersionInfo(new Long("6"));
		targetSpec3.setTsTrancomp(null);//always null
		targetSpec3.setTsTranset(targetCEMBusinessTransaction2);
		targetSpec3.setTsTranunit(null);//can be null as well
		
		//attributes different from sourceSpec3 marked by comment  //***********
		targetSpec4.setTsId(new Long("31"));
		targetSpec4.setTsAttributeId(new Long("666"));//***********
		targetSpec4.setTsCondition(null);
		targetSpec4.setTsEnabled(Boolean.FALSE);
		targetSpec4.setTsHeaderName(null);//null or Name //***********
		targetSpec4.setTsImportanceId(new Long("5"));
		targetSpec4.setTsLocked(Boolean.FALSE);//***********
		targetSpec4.setTsName("Large Size");
		targetSpec4.setTsSoftDelete(Boolean.FALSE);
		targetSpec4.setTsTranType(new Integer("3"));
		targetSpec4.setTsType(new Integer("10"));//***********
		targetSpec4.setTsValue("200000");//***********
		targetSpec4.setVersionInfo(new Long("9"));
		targetSpec4.setTsTrancomp(null);//always null
		targetSpec4.setTsTranset(targetCEMBusinessTransaction);
		targetSpec4.setTsTranunit(null);//can be null as well
		
		
		assertTrue(sourceSpec2.getTsTranset() == sourceCEMBusinessTransaction);
		
		//CEM specifications are never defined on component level, testing this stuff does not make sense
		//new CEMEntityMergeHelper<CEMTransactionComponent, CEMSpecification>().mergeChildEntityLists(targetTC, source, target);
		
		new CEMEntityMergeHelper<CEMBusinessTransaction, CEMSpecification>().mergeChildEntityLists(targetCEMBusinessTransaction, source, target);
		
		
		assertFalse(sourceSpec2.getTsTranset() == sourceCEMBusinessTransaction);
		assertTrue(sourceSpec2.getTsTranset() == targetCEMBusinessTransaction);
		
		assertTrue(target.size()==5);
		assertTrue(target.contains(sourceSpec2));
		assertTrue(target.contains(targetSpec1));
		assertTrue(target.contains(targetSpec4));
		
		
	}
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeCEMTransactionComponent() throws Exception {

		CEMTransactionComponentIdentification source1 = new CEMTransactionComponentIdentification();//this one will be updated (CI1)
		CEMTransactionComponentIdentification source2 = new CEMTransactionComponentIdentification();//this one will be updated (CI2)
		CEMTransactionComponentIdentification source3 = new CEMTransactionComponentIdentification();//this one will be inserted
		
		CEMTransactionComponentIdentification target1 = new CEMTransactionComponentIdentification();//this one will be deleted since it does not exist in source any more
		CEMTransactionComponentIdentification target2 = new CEMTransactionComponentIdentification();//this one will be updated(CI2)
		CEMTransactionComponentIdentification target3 = new CEMTransactionComponentIdentification();//this one will be updated(CI1)
		CEMTransactionComponentIdentification target4 = new CEMTransactionComponentIdentification();//this one will be deleted since it does not exist in source any more
		
		CEMTransactionComponentIdentificationPK sourcePK1 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK sourcePK2 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK sourcePK3 = new CEMTransactionComponentIdentificationPK();
		
		CEMTransactionComponentIdentificationPK targetPK1 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK2 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK3 = new CEMTransactionComponentIdentificationPK();
		CEMTransactionComponentIdentificationPK targetPK4 = new CEMTransactionComponentIdentificationPK();
		
		
		CEMTransactionComponent sourceTC = new CEMTransactionComponent();
		CEMTransactionComponent targetTC = new CEMTransactionComponent();
		
		

		
		CEMBusinessApplication sourceBA = new CEMBusinessApplication();
		CEMBusinessApplication targetBA = new CEMBusinessApplication();
		
		CEMBusinessService sourceSV = new CEMBusinessService();
		CEMBusinessService targetSV = new CEMBusinessService();

		CEMBusinessTransaction sourceBT = new CEMBusinessTransaction();
		CEMBusinessTransaction targetBT = new CEMBusinessTransaction();
		
		CEMTransaction sourceTR = new CEMTransaction();
		CEMTransaction targetTR = new CEMTransaction();
		
		
		sourceBA.setTsName("BA1");
		targetBA.setTsName("BA1");
		
		sourceSV.setTsName("Service1");
		targetSV.setTsName("Service1");

		sourceBT.setTsName("/sample/hello");
		targetBT.setTsName("/sample/hello");

		sourceTR.setTsName("/sample/hello");
		targetTR.setTsName("/sample/hello");
		
		sourceSV.setTsApp(sourceBA);
		targetSV.setTsApp(targetBA);

		sourceBT.setTsTranDefGroup(sourceSV);
		targetBT.setTsTranDefGroup(targetSV);

		sourceTR.setTsTranset(sourceBT);
		targetTR.setTsTranset(targetBT);
		
		sourceTC.setTsTranunit(sourceTR);
		targetTC.setTsTranunit(targetTR);
		
		
		sourceTC.setTsId(new Long("700000000000000064"));
		targetTC.setTsId(new Long("700000000000006664"));
		
		sourceTC.setTsName("/sample/hello");
		targetTC.setTsName("/sample/hello");
		
		sourceTC.setTsPending(Boolean.TRUE);
		targetTC.setTsPending(Boolean.FALSE);
		
		sourcePK1.setTsName("ClientIP");
		sourcePK1.setTsParamType("URL");
		sourcePK1.setTsTrancompId(new Long("700000000000000064"));
		source1.setId(sourcePK1);
		source1.setTsAutogenSequence(new Integer("0"));
		source1.setTsNameType(new Integer("0"));
		source1.setTsOperator(new Integer("0"));
		source1.setTsOriginalValue("127.0.0.1");
		source1.setTsPattern("127.0.0.1");
		source1.setTsSoftDelete(Boolean.FALSE);
		source1.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source1.setVersionInfo(new Long("0"));
		source1.setTsTrancomp(sourceTC);
		
		sourcePK2.setTsName("Host");
		sourcePK2.setTsParamType("URL");
		sourcePK2.setTsTrancompId(new Long("700000000000000064"));
		source2.setId(sourcePK2);
		source2.setTsAutogenSequence(new Integer("1"));
		source2.setTsNameType(new Integer("0"));
		source2.setTsOperator(new Integer("1"));
		source2.setTsOriginalValue("127.0.0.1");
		source2.setTsPattern("127.0.0.1");
		source2.setTsSoftDelete(Boolean.FALSE);
		source2.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source2.setVersionInfo(new Long("0"));
		source2.setTsTrancomp(sourceTC);
		
		sourcePK3.setTsName("Port");
		sourcePK3.setTsParamType("URL");
		sourcePK3.setTsTrancompId(new Long("700000000000000064"));
		source3.setId(sourcePK3);
		source3.setTsAutogenSequence(new Integer("20"));
		source3.setTsNameType(new Integer("0"));
		source3.setTsOperator(new Integer("0"));
		source3.setTsOriginalValue("8080");
		source3.setTsPattern("8080");
		source3.setTsSoftDelete(Boolean.FALSE);
		source3.setTsTrancomp(null);//this is not taken into consideration when comparing or merging CEMTransactionComponentIdentification, we don't need to set this up 
		source3.setVersionInfo(new Long("0"));
		source3.setTsTrancomp(sourceTC);
		
		targetPK1.setTsName("Server");
		targetPK1.setTsParamType("HTTP_RESPONSE_HEADER");
		targetPK1.setTsTrancompId(new Long("700000000000006664"));
		target1.setId(targetPK1);
		target1.setTsAutogenSequence(new Integer("0"));
		target1.setTsNameType(new Integer("0"));
		target1.setTsOperator(new Integer("0"));
		target1.setTsOriginalValue("Apache-Coyote/1.1");
		target1.setTsPattern("Apache-Coyote/1.1");
		target1.setTsSoftDelete(Boolean.FALSE);
		target1.setTsTrancomp(null); 
		target1.setVersionInfo(new Long("0"));
		target1.setTsTrancomp(targetTC);
		
		targetPK2.setTsName("Host");
		targetPK2.setTsParamType("URL");
		targetPK2.setTsTrancompId(new Long("700000000000006664"));//FK of component is different, but still if component name is same and tsName and tsParamType match this is considered same entity as source2
		target2.setId(targetPK2);
		//target2.setTsAutogenSequence(new Integer("1"));
		target2.setTsAutogenSequence(null);
		target2.setTsNameType(new Integer("0"));
		target2.setTsOperator(new Integer("1"));
		target2.setTsOriginalValue("192.168.128.135");
		target2.setTsPattern("192.168.128.135");
		target2.setTsSoftDelete(Boolean.FALSE);
		target2.setTsTrancomp(null); 
		target2.setVersionInfo(new Long("456"));
		target2.setTsTrancomp(targetTC);
		
		targetPK3.setTsName("ClientIP");
		targetPK3.setTsParamType("URL");
		targetPK3.setTsTrancompId(new Long("700000000000006664"));
		target3.setId(targetPK3);
		target3.setTsAutogenSequence(new Integer("2"));
		target3.setTsNameType(new Integer("0"));
		target3.setTsOperator(new Integer("0"));
		target3.setTsOriginalValue("192.168.128.136");
		target3.setTsPattern("192.168.128.136");
		target3.setTsSoftDelete(Boolean.FALSE);
		target3.setTsTrancomp(null); 
		target3.setVersionInfo(new Long("123"));
		target3.setTsTrancomp(targetTC);
		
		targetPK4.setTsName("Status");
		targetPK4.setTsParamType("HTTP_RESPONSE");
		targetPK4.setTsTrancompId(new Long("700000000000006664"));
		target4.setId(targetPK4);
		target4.setTsAutogenSequence(new Integer("3"));
		target4.setTsNameType(new Integer("0"));
		target4.setTsOperator(new Integer("1"));
		target4.setTsOriginalValue("200");
		target4.setTsPattern("200");
		target4.setTsSoftDelete(Boolean.FALSE);
		target4.setTsTrancomp(null); 
		target4.setVersionInfo(new Long("0"));
		target4.setTsTrancomp(targetTC);
		
		Vector<CEMTransactionComponentIdentification> source = new Vector<CEMTransactionComponentIdentification>();
		Vector<CEMTransactionComponentIdentification> target = new Vector<CEMTransactionComponentIdentification>();
		
		sourceTC.setTsParams(source);
		targetTC.setTsParams(target);
		
		
		source.add(source1);
		source.add(source2);
		source.add(source3);
		
		target.add(target1);
		target.add(target2);
		target.add(target3);
		target.add(target4);
		
		
		sourceTC.copyTo(targetTC);//merge source & target component
		
		assertTrue(targetTC.getTsId().equals(new Long("700000000000006664")));
		assertTrue(targetTC.getTsPending().equals(Boolean.TRUE));
		
		assertTrue(targetTC.getTsParams().size() == 5);
		
		assertTrue(targetTC.getTsParams().contains(source3));
		assertTrue(targetTC.getTsParams().contains(target2));
		assertTrue(targetTC.getTsParams().contains(target3));
		
		assertTrue(target2.getTsTrancomp() == targetTC);
		assertTrue(target3.getTsTrancomp() == targetTC);
		assertTrue(source3.getTsTrancomp() == targetTC);
		
	}
	
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeCEMTransactionSimple() throws Exception {	
		
		CEMBusinessTransaction sourceBT = new CEMBusinessTransaction();
		CEMBusinessTransaction targetBT = new CEMBusinessTransaction();

		CEMTransaction sourceTR = new CEMTransaction();
		CEMTransaction targetTR = new CEMTransaction();
		
		
		sourceBT.setTsId(new Long("555"));
		targetBT.setTsId(new Long("666"));
		
		sourceBT.setTsName("BT /sample/hello");
		targetBT.setTsName("BT /sample/hello");
		
		
		sourceTR.setTsTranset(sourceBT);
		targetTR.setTsTranset(targetBT);
		
		sourceTR.setTsId(new Long("1"));
		sourceTR.setTsCollectTrancompStats(Boolean.FALSE);
		sourceTR.setTsDescription("/sample/hello transaction");
		sourceTR.setTsIdentifying(Boolean.TRUE);
		sourceTR.setTsIncluded(Boolean.TRUE);
		sourceTR.setTsName("/sample/hello");
		sourceTR.setTsOptional(Boolean.FALSE);
		sourceTR.setTsSequenceNumber(new Integer("0"));//************
		sourceTR.setTsSigmaSla(12);//************
		sourceTR.setTsSigmaSlaInherited(Boolean.FALSE);
		sourceTR.setTsSoftDelete(Boolean.FALSE);
		sourceTR.setTsSuccessRateSla(15);
		sourceTR.setTsSuccessSlaInherited(Boolean.FALSE);
		sourceTR.setTsTranTimeSla(16);//************
		sourceTR.setTsTranTimeSlaInherited(Boolean.TRUE);
		sourceTR.setVersionInfo(new Long("2"));
		
		targetTR.setTsId(new Long("112313"));
		targetTR.setTsCollectTrancompStats(Boolean.FALSE);
		targetTR.setTsDescription("/sample/hello transaction 123");
		targetTR.setTsIdentifying(Boolean.TRUE);
		targetTR.setTsIncluded(Boolean.TRUE);
		targetTR.setTsName("/sample/hello");
		targetTR.setTsOptional(Boolean.FALSE);
		targetTR.setTsSequenceNumber(new Integer("2"));
		targetTR.setTsSigmaSla(15);
		targetTR.setTsSigmaSlaInherited(Boolean.FALSE);
		targetTR.setTsSoftDelete(Boolean.FALSE);
		targetTR.setTsSuccessRateSla(15);
		targetTR.setTsSuccessSlaInherited(Boolean.FALSE);
		targetTR.setTsTranTimeSla(160);
		targetTR.setTsTranTimeSlaInherited(Boolean.TRUE);
		targetTR.setVersionInfo(new Long("2"));
		
		sourceTR.copyTo(targetTR);

		assertTrue(targetTR.getTsId().equals(new Long("112313")));
		assertTrue(targetTR.getTsTranTimeSla().equals(new Integer("16")));
		assertTrue(targetTR.getTsSigmaSla() == 12);
		
		
		
	}	

	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeJumboJet() throws Exception {	
		
		
		
		/**
		 * SOURCE TESTING DATA DEFINITION			
		 */
		
		
		CEMBusinessApplication sourceBA1 = new CEMBusinessApplication();
		CEMBusinessService sourceBA1SV1 = new CEMBusinessService();

		//BT1 BEGIN**********************************************************************************
		CEMBusinessTransaction sourceBT1 = new CEMBusinessTransaction();
			CEMSpecification sourceSpec1 = new CEMSpecification();
			CEMSpecification sourceSpec2 = new CEMSpecification();
			
			CEMTransaction sourceTR1 = new CEMTransaction();
				CEMSpecification sourceSpec5 = new CEMSpecification();
				CEMSpecification sourceSpec6 = new CEMSpecification();
				
				CEMTransactionComponent sourceTC1 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI1 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI2 = new CEMTransactionComponentIdentification();
				
				CEMTransactionComponent sourceTC2 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI3 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI4 = new CEMTransactionComponentIdentification();
				

			CEMTransaction sourceTR2 = new CEMTransaction();
				CEMSpecification sourceSpec7 = new CEMSpecification();
				CEMSpecification sourceSpec8 = new CEMSpecification();
			
				CEMTransactionComponent sourceTC3 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI5 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI6 = new CEMTransactionComponentIdentification();
				
				CEMTransactionComponent sourceTC4 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI7 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI8 = new CEMTransactionComponentIdentification();
		
		
		//BT2 BEGIN**********************************************************************************
		CEMBusinessTransaction sourceBT2 = new CEMBusinessTransaction();
			CEMSpecification sourceSpec3 = new CEMSpecification();
			CEMSpecification sourceSpec4 = new CEMSpecification();

		
			CEMTransaction sourceTR3 = new CEMTransaction();
				CEMSpecification sourceSpec9 = new CEMSpecification();
				CEMSpecification sourceSpec10 = new CEMSpecification();
				
				CEMTransactionComponent sourceTC5 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI9 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI10 = new CEMTransactionComponentIdentification();
				
				CEMTransactionComponent sourceTC6 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI11 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI12 = new CEMTransactionComponentIdentification();
				
			
			CEMTransaction sourceTR4 = new CEMTransaction();
				CEMSpecification sourceSpec11 = new CEMSpecification();
				CEMSpecification sourceSpec12 = new CEMSpecification();
			
				CEMTransactionComponent sourceTC7 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI13 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI14 = new CEMTransactionComponentIdentification();
				
				CEMTransactionComponent sourceTC8 = new CEMTransactionComponent();
					CEMTransactionComponentIdentification sourceCI15 = new CEMTransactionComponentIdentification();
					CEMTransactionComponentIdentification sourceCI16 = new CEMTransactionComponentIdentification();
				
		
					
		
		//BUSINESS APPLICATION - BEGIN
		sourceBA1.setTsName("Tomcat Sample Application");
		sourceBA1.setTsId(new Long("1"));
		
		sourceBA1.setTsTranDefGroups(new Vector<CEMBusinessService>());
		sourceBA1.setTsTransets(new Vector<CEMBusinessTransaction>());
		
		
		sourceBA1.addTsTranDefGroup(sourceBA1SV1);
		sourceBA1.addTsTranset(sourceBT1);
		sourceBA1.addTsTranset(sourceBT2);
		//BUSINESS APPLICATION - END
		
		//BUSINESS SERVICE - BEGIN
		sourceBA1SV1.setTsName("Tomcat Service 1");
		sourceBA1SV1.setTsDescription("Tomcat Service 1 description");
		sourceBA1SV1.setTsId(new Long("10"));
		sourceBA1SV1.setTsTransets(new Vector<CEMBusinessTransaction>());
		sourceBA1SV1.setTsTransetGroups(new Vector<TsTransetGroup>());
		sourceBA1SV1.setTsApp(sourceBA1);
		sourceBA1SV1.addTsTranset(sourceBT1);
		sourceBA1SV1.addTsTranset(sourceBT2);
		//BUSINESS SERVICE - END
		
		sourceBT1.setTsDefectDefs(new Vector<CEMSpecification>());
		sourceBT2.setTsDefectDefs(new Vector<CEMSpecification>());
		sourceBT1.setTsTranunits(new Vector<CEMTransaction>());
		sourceBT2.setTsTranunits(new Vector<CEMTransaction>());
		sourceBT1.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
		sourceBT2.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
		
		sourceTR1.setTsDefectDefs(new Vector<CEMSpecification>());
		sourceTR2.setTsDefectDefs(new Vector<CEMSpecification>());
		sourceTR3.setTsDefectDefs(new Vector<CEMSpecification>());
		sourceTR4.setTsDefectDefs(new Vector<CEMSpecification>());
		
		sourceTR1.setTsTrancomps(new Vector<CEMTransactionComponent>());
		sourceTR2.setTsTrancomps(new Vector<CEMTransactionComponent>());
		sourceTR3.setTsTrancomps(new Vector<CEMTransactionComponent>());
		sourceTR4.setTsTrancomps(new Vector<CEMTransactionComponent>());
		
		sourceTC1.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC2.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC3.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC4.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC5.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC6.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC7.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		sourceTC8.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		
		//BUSINESS TRANSACTION - BEGIN
			
			//BT1
			sourceBT1.setTsName("BT /service1/xx1");
			sourceBT1.setTsDescription("BT /service1/xx1 description");
			sourceBT1.setTsId(new Long("100"));
			sourceBT1.addTsTranunit(sourceTR1);
			sourceBT1.addTsTranunit(sourceTR2);
			sourceBT1.addTsDefectDef(sourceSpec1);
			sourceBT1.addTsDefectDef(sourceSpec2);

				sourceSpec1.setTsId(new Long("1000"));
				sourceSpec1.setTsName("Slow Time");
				sourceSpec1.setTsValue("3000");
			
				sourceSpec2.setTsId(new Long("2000"));
				sourceSpec2.setTsName("Fast Time");
				sourceSpec2.setTsValue("1500");
				
				sourceTR1.setTsName("TR /service1/xx1");
				sourceTR1.setTsDescription("TR /service1/xx1 description");
				sourceTR1.setTsSequenceNumber(new Integer("0"));
				sourceTR1.setTsId(new Long("10000"));
				sourceTR1.addTsDefectDef(sourceSpec5);
				sourceTR1.addTsDefectDef(sourceSpec6);
				sourceTR1.addTsTrancomp(sourceTC1);
				sourceTR1.addTsTrancomp(sourceTC2);
				
					sourceSpec5.setTsId(new Long("5000"));
					sourceSpec5.setTsName("Slow Time");
					sourceSpec5.setTsValue("3200");
					sourceSpec5.setTsTranset(sourceBT1);
					
					sourceSpec6.setTsId(new Long("6000"));
					sourceSpec6.setTsName("Fast Time");
					sourceSpec6.setTsValue("1700");
					sourceSpec6.setTsTranset(sourceBT1);
				
					sourceTC1.setTsName("TC /service1/xx1");
					sourceTC1.setTsDescription("source TC description");
					sourceTC1.setTsId(new Long("100000"));
					sourceTC1.addTsParam(sourceCI1);
					sourceTC1.addTsParam(sourceCI2);
						CEMTransactionComponentIdentificationPK sourceCI1pk = new CEMTransactionComponentIdentificationPK();
						sourceCI1pk.setTsName("ClientIP");
						sourceCI1pk.setTsParamType("URL");
						sourceCI1pk.setTsTrancompId(new Long("100000"));
						sourceCI1.setId(sourceCI1pk);
						sourceCI1.setTsPattern("192.168.128.135");
						sourceCI1.setTsAutogenSequence(new Integer("0"));

						CEMTransactionComponentIdentificationPK sourceCI2pk = new CEMTransactionComponentIdentificationPK();
						sourceCI2pk.setTsName("Port");
						sourceCI2pk.setTsParamType("URL");
						sourceCI2pk.setTsTrancompId(new Long("100000"));
						sourceCI2.setId(sourceCI2pk);
						sourceCI2.setTsPattern("8080");
						sourceCI2.setTsAutogenSequence(new Integer("1"));
						
					
					sourceTC2.setTsName("TC /service1/xx2");
					sourceTC2.setTsId(new Long("200000"));
					sourceTC2.addTsParam(sourceCI3);
					sourceTC2.addTsParam(sourceCI4);
						CEMTransactionComponentIdentificationPK sourceCI3pk = new CEMTransactionComponentIdentificationPK();
						sourceCI3pk.setTsName("ClientIP");
						sourceCI3pk.setTsParamType("URL");
						sourceCI3pk.setTsTrancompId(new Long("200000"));
						sourceCI3.setId(sourceCI3pk);
						sourceCI3.setTsPattern("192.168.128.136");
						sourceCI3.setTsAutogenSequence(new Integer("0"));
	
						CEMTransactionComponentIdentificationPK sourceCI4pk = new CEMTransactionComponentIdentificationPK();
						sourceCI4pk.setTsName("Port");
						sourceCI4pk.setTsParamType("URL");
						sourceCI4pk.setTsTrancompId(new Long("200000"));
						sourceCI4.setId(sourceCI4pk);
						sourceCI4.setTsPattern("8081");
						sourceCI4.setTsAutogenSequence(new Integer("1"));
				
				
				sourceTR2.setTsName("TR /service1/xx2");
				sourceTR2.setTsDescription("TR /service1/xx2 description");
				sourceTR2.setTsSequenceNumber(new Integer("1"));
				sourceTR2.setTsId(new Long("20000"));
				sourceTR2.addTsDefectDef(sourceSpec7);
				sourceTR2.addTsDefectDef(sourceSpec8);
				sourceTR2.addTsTrancomp(sourceTC3);
				sourceTR2.addTsTrancomp(sourceTC4);
				
				
				sourceSpec7.setTsId(new Long("7000"));
				sourceSpec7.setTsName("Slow Time");
				sourceSpec7.setTsValue("3200");
				sourceSpec7.setTsTranset(sourceBT1);
				
				sourceSpec8.setTsId(new Long("8000"));
				sourceSpec8.setTsName("Fast Time");
				sourceSpec8.setTsValue("1700");
				sourceSpec8.setTsTranset(sourceBT1);
			
				sourceTC3.setTsName("TC /service1/xx1");
				sourceTC3.setTsId(new Long("300000"));
				sourceTC3.addTsParam(sourceCI5);
				sourceTC3.addTsParam(sourceCI6);
				
					CEMTransactionComponentIdentificationPK sourceCI5pk = new CEMTransactionComponentIdentificationPK();
					sourceCI5pk.setTsName("ClientIP");
					sourceCI5pk.setTsParamType("URL");
					sourceCI5pk.setTsTrancompId(new Long("300000"));
					sourceCI5.setId(sourceCI5pk);
					sourceCI5.setTsPattern("192.168.128.135");
					sourceCI5.setTsAutogenSequence(new Integer("0"));

					CEMTransactionComponentIdentificationPK sourceCI6pk = new CEMTransactionComponentIdentificationPK();
					sourceCI6pk.setTsName("Port");
					sourceCI6pk.setTsParamType("URL");
					sourceCI6pk.setTsTrancompId(new Long("300000"));
					sourceCI6.setId(sourceCI6pk);
					sourceCI6.setTsPattern("8080");
					sourceCI6.setTsAutogenSequence(new Integer("1"));
					
				
				sourceTC4.setTsName("TC /service1/xx2");
				sourceTC4.setTsId(new Long("400000"));
				sourceTC4.addTsParam(sourceCI7);
				sourceTC4.addTsParam(sourceCI8);
				
					CEMTransactionComponentIdentificationPK sourceCI7pk = new CEMTransactionComponentIdentificationPK();
					sourceCI7pk.setTsName("ClientIP");
					sourceCI7pk.setTsParamType("URL");
					sourceCI7pk.setTsTrancompId(new Long("400000"));
					sourceCI7.setId(sourceCI7pk);
					sourceCI7.setTsPattern("192.168.128.136");
					sourceCI7.setTsAutogenSequence(new Integer("0"));

					CEMTransactionComponentIdentificationPK sourceCI8pk = new CEMTransactionComponentIdentificationPK();
					sourceCI8pk.setTsName("Port");
					sourceCI8pk.setTsParamType("URL");
					sourceCI8pk.setTsTrancompId(new Long("400000"));
					sourceCI8.setId(sourceCI8pk);
					sourceCI8.setTsPattern("8081");
					sourceCI8.setTsAutogenSequence(new Integer("1"));
				
			
			//BT2
			sourceBT2.setTsName("BT /service1/xx2 to insert");
			sourceBT2.setTsDescription("BT /service1/xx2 description");
			sourceBT2.setTsId(new Long("200"));
			sourceBT2.addTsTranunit(sourceTR3);
			sourceBT2.addTsTranunit(sourceTR4);
			sourceBT2.addTsDefectDef(sourceSpec3);
			sourceBT2.addTsDefectDef(sourceSpec4);
			
			
				sourceSpec3.setTsId(new Long("3000"));
				sourceSpec3.setTsName("Slow Time");
				sourceSpec3.setTsValue("3100");
			
				sourceSpec4.setTsId(new Long("4000"));
				sourceSpec4.setTsName("Fast Time to insert");
				sourceSpec4.setTsValue("1510");
			
				sourceTR3.setTsName("TR /service1/xx1");
				sourceTR3.setTsDescription("TR /service1/xx1 description");
				sourceTR3.setTsSequenceNumber(new Integer("0"));
				sourceTR3.setTsId(new Long("30000"));
				sourceTR3.addTsDefectDef(sourceSpec9);
				sourceTR3.addTsDefectDef(sourceSpec10);
				sourceTR3.addTsTrancomp(sourceTC5);
				sourceTR3.addTsTrancomp(sourceTC6);
				
				
					sourceSpec9.setTsId(new Long("9000"));
					sourceSpec9.setTsName("Slow Time");
					sourceSpec9.setTsValue("3200");
					sourceSpec9.setTsTranset(sourceBT2);
					
					sourceSpec10.setTsId(new Long("10000"));
					sourceSpec10.setTsName("Fast Time");
					sourceSpec10.setTsValue("1700");
					sourceSpec10.setTsTranset(sourceBT2);
				
					sourceTC5.setTsName("TC /service1/xx1");
					sourceTC5.setTsId(new Long("500000"));
					sourceTC5.addTsParam(sourceCI9);
					sourceTC5.addTsParam(sourceCI10);
						CEMTransactionComponentIdentificationPK sourceCI9pk = new CEMTransactionComponentIdentificationPK();
						sourceCI9pk.setTsName("ClientIP");
						sourceCI9pk.setTsParamType("URL");
						sourceCI9pk.setTsTrancompId(new Long("500000"));
						sourceCI9.setId(sourceCI9pk);
						sourceCI9.setTsPattern("192.168.128.135");
						sourceCI9.setTsAutogenSequence(new Integer("0"));

						CEMTransactionComponentIdentificationPK sourceCI10pk = new CEMTransactionComponentIdentificationPK();
						sourceCI10pk.setTsName("Port");
						sourceCI10pk.setTsParamType("URL");
						sourceCI10pk.setTsTrancompId(new Long("500000"));
						sourceCI10.setId(sourceCI10pk);
						sourceCI10.setTsPattern("8080");
						sourceCI10.setTsAutogenSequence(new Integer("1"));
						
					
					sourceTC6.setTsName("TC /service1/xx2");
					sourceTC6.setTsId(new Long("600000"));
					sourceTC6.addTsParam(sourceCI11);
					sourceTC6.addTsParam(sourceCI12);
						CEMTransactionComponentIdentificationPK sourceCI11pk = new CEMTransactionComponentIdentificationPK();
						sourceCI11pk.setTsName("ClientIP");
						sourceCI11pk.setTsParamType("URL");
						sourceCI11pk.setTsTrancompId(new Long("600000"));
						sourceCI11.setId(sourceCI11pk);
						sourceCI11.setTsPattern("192.168.128.136");
						sourceCI11.setTsAutogenSequence(new Integer("0"));
	
						CEMTransactionComponentIdentificationPK sourceCI12pk = new CEMTransactionComponentIdentificationPK();
						sourceCI12pk.setTsName("Port");
						sourceCI12pk.setTsParamType("URL");
						sourceCI12pk.setTsTrancompId(new Long("600000"));
						sourceCI12.setId(sourceCI12pk);
						sourceCI12.setTsPattern("8081");
						sourceCI12.setTsAutogenSequence(new Integer("1"));
				
				sourceTR4.setTsName("TR /service1/xx2 to insert");
				sourceTR4.setTsDescription("TR /service1/xx2 description");
				sourceTR4.setTsSequenceNumber(new Integer("1"));
				sourceTR4.setTsId(new Long("40000"));
				sourceTR4.addTsDefectDef(sourceSpec11);
				sourceTR4.addTsDefectDef(sourceSpec12);
				sourceTR4.addTsTrancomp(sourceTC7);
				sourceTR4.addTsTrancomp(sourceTC8);
				
				
				sourceSpec11.setTsId(new Long("11000"));
				sourceSpec11.setTsName("Slow Time");
				sourceSpec11.setTsValue("3200");
				sourceSpec11.setTsTranset(sourceBT2);
				
				sourceSpec12.setTsId(new Long("12000"));
				sourceSpec12.setTsName("Fast Time to insert");
				sourceSpec12.setTsValue("1700");
				sourceSpec12.setTsTranset(sourceBT2);
			
				sourceTC7.setTsName("TC /service1/xx1");
				sourceTC7.setTsId(new Long("700000"));
				sourceTC7.addTsParam(sourceCI13);
				sourceTC7.addTsParam(sourceCI14);
					CEMTransactionComponentIdentificationPK sourceCI13pk = new CEMTransactionComponentIdentificationPK();
					sourceCI13pk.setTsName("ClientIP");
					sourceCI13pk.setTsParamType("URL");
					sourceCI13pk.setTsTrancompId(new Long("700000"));
					sourceCI13.setId(sourceCI13pk);
					sourceCI13.setTsPattern("192.168.128.135");
					sourceCI13.setTsAutogenSequence(new Integer("0"));

					CEMTransactionComponentIdentificationPK sourceCI14pk = new CEMTransactionComponentIdentificationPK();
					sourceCI14pk.setTsName("Port");
					sourceCI14pk.setTsParamType("URL");
					sourceCI14pk.setTsTrancompId(new Long("700000"));
					sourceCI14.setId(sourceCI14pk);
					sourceCI14.setTsPattern("8080");
					sourceCI14.setTsAutogenSequence(new Integer("1"));
					
				
				sourceTC8.setTsName("TC /service1/xx2 to insert");
				sourceTC8.setTsId(new Long("800000"));
				sourceTC8.addTsParam(sourceCI15);
				sourceTC8.addTsParam(sourceCI16);
					CEMTransactionComponentIdentificationPK sourceCI15pk = new CEMTransactionComponentIdentificationPK();
					sourceCI15pk.setTsName("ClientIP");
					sourceCI15pk.setTsParamType("URL");
					sourceCI15pk.setTsTrancompId(new Long("800000"));
					sourceCI15.setId(sourceCI15pk);
					sourceCI15.setTsPattern("192.168.128.136");
					sourceCI15.setTsAutogenSequence(new Integer("0"));

					CEMTransactionComponentIdentificationPK sourceCI16pk = new CEMTransactionComponentIdentificationPK();
					sourceCI16pk.setTsName("Port to insert");
					sourceCI16pk.setTsParamType("URL");
					sourceCI16pk.setTsTrancompId(new Long("800000"));
					sourceCI16.setId(sourceCI16pk);
					sourceCI16.setTsPattern("8081");
					sourceCI16.setTsAutogenSequence(new Integer("1"));

		//BUSINESS TRANSACTION - END
		
		
					//TsTranseGroup & mapping definition - BEGIN
					TsTransetGroup sourcetsTransetGroup = new TsTransetGroup();
					
					TsTransetgroupTransetsMapPK sourceMap1pk = new TsTransetgroupTransetsMapPK();
					TsTransetgroupTransetsMapPK sourceMap2pk = new TsTransetgroupTransetsMapPK();
					TsTransetgroupTransetsMap sourceMap1 = new TsTransetgroupTransetsMap();
					TsTransetgroupTransetsMap sourceMap2 = new TsTransetgroupTransetsMap();
					
					TsTransetgroupTransetsMapPK sourceMap3pk = new TsTransetgroupTransetsMapPK();
					TsTransetgroupTransetsMapPK sourceMap4pk = new TsTransetgroupTransetsMapPK();
					TsTransetgroupTransetsMap sourceMap3 = new TsTransetgroupTransetsMap();
					TsTransetgroupTransetsMap sourceMap4 = new TsTransetgroupTransetsMap();
					
					sourceBA1SV1.addTsTransetGroup(sourcetsTransetGroup);
					sourcetsTransetGroup.setTsName("Tomcat Service 1");
					sourcetsTransetGroup.setTsId(new Long("1010"));
					sourcetsTransetGroup.setTsDescription("Tomcat Service 1 description");
					
					sourcetsTransetGroup.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
					sourcetsTransetGroup.addTsTransetgroupTransetsMap(sourceMap1);
					sourcetsTransetGroup.addTsTransetgroupTransetsMap(sourceMap2);
					sourcetsTransetGroup.addTsTransetgroupTransetsMap(sourceMap3);
					sourcetsTransetGroup.addTsTransetgroupTransetsMap(sourceMap4);
					
					sourceMap1pk.setTsTransetGroupId(sourcetsTransetGroup.getTsId());
					sourceMap1pk.setTsTransetId(sourceBT1.getTsId());
					sourceMap1pk.setTsTransetIncarnationId(sourceBT1.getTsId()+1);

					sourceMap2pk.setTsTransetGroupId(sourcetsTransetGroup.getTsId());
					sourceMap2pk.setTsTransetId(sourceBT1.getTsId());
					sourceMap2pk.setTsTransetIncarnationId(sourceBT1.getTsId()+2);

					sourceMap3pk.setTsTransetGroupId(sourcetsTransetGroup.getTsId());
					sourceMap3pk.setTsTransetId(sourceBT2.getTsId());
					sourceMap3pk.setTsTransetIncarnationId(sourceBT2.getTsId()+1);

					sourceMap4pk.setTsTransetGroupId(sourcetsTransetGroup.getTsId());
					sourceMap4pk.setTsTransetId(sourceBT2.getTsId());
					sourceMap4pk.setTsTransetIncarnationId(sourceBT2.getTsId()+2);
					
					sourceMap1.setId(sourceMap1pk);
					sourceMap2.setId(sourceMap2pk);
					sourceMap3.setId(sourceMap3pk);
					sourceMap4.setId(sourceMap4pk);
					
					sourceMap1.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					sourceMap2.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					sourceMap3.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					sourceMap4.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));

					sourceMap1.setTsSoftDelete(Boolean.FALSE);
					sourceMap2.setTsSoftDelete(Boolean.TRUE);
					sourceMap3.setTsSoftDelete(Boolean.FALSE);
					sourceMap4.setTsSoftDelete(Boolean.TRUE);
					
					sourceMap1.setTsDeleteDate(null);
					sourceMap2.setTsDeleteDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					sourceMap3.setTsDeleteDate(null);
					sourceMap4.setTsDeleteDate(new java.sql.Timestamp(new java.util.Date().getTime()));
					
					sourceMap1.setVersionInfo(new Long(123));
					
					sourceBT1.addTsTransetgroupTransetsMap(sourceMap1);
					//sourceBT1.addTsTransetgroupTransetsMap(sourceMap2);

					sourceBT2.addTsTransetgroupTransetsMap(sourceMap3);
					//sourceBT2.addTsTransetgroupTransetsMap(sourceMap4);
					
					
					//TsTranseGroup & mapping definition - END
					

					
		/**
		 * TARGET TESTING DATA DEFINITION			
		 */
		
		
					CEMBusinessApplication targetBA1 = new CEMBusinessApplication();
					CEMBusinessService targetBA1SV1 = new CEMBusinessService();

					//BT1 BEGIN**********************************************************************************
					CEMBusinessTransaction targetBT1 = new CEMBusinessTransaction();
						CEMSpecification targetSpec1 = new CEMSpecification();
						CEMSpecification targetSpec2 = new CEMSpecification();
						
						CEMTransaction targetTR1 = new CEMTransaction();
							CEMSpecification targetSpec5 = new CEMSpecification();
							CEMSpecification targetSpec6 = new CEMSpecification();
							
							CEMTransactionComponent targetTC1 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI1 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI2 = new CEMTransactionComponentIdentification();
							
							CEMTransactionComponent targetTC2 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI3 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI4 = new CEMTransactionComponentIdentification();
							

						CEMTransaction targetTR2 = new CEMTransaction();
							CEMSpecification targetSpec7 = new CEMSpecification();
							CEMSpecification targetSpec8 = new CEMSpecification();
						
							CEMTransactionComponent targetTC3 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI5 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI6 = new CEMTransactionComponentIdentification();
							
							CEMTransactionComponent targetTC4 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI7 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI8 = new CEMTransactionComponentIdentification();
					
					
					//BT2 BEGIN**********************************************************************************
					CEMBusinessTransaction targetBT2 = new CEMBusinessTransaction();
						CEMSpecification targetSpec3 = new CEMSpecification();
						CEMSpecification targetSpec4 = new CEMSpecification();

					
						CEMTransaction targetTR3 = new CEMTransaction();
							CEMSpecification targetSpec9 = new CEMSpecification();
							CEMSpecification targetSpec10 = new CEMSpecification();
							
							CEMTransactionComponent targetTC5 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI9 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI10 = new CEMTransactionComponentIdentification();
							
							CEMTransactionComponent targetTC6 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI11 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI12 = new CEMTransactionComponentIdentification();
							
						
						CEMTransaction targetTR4 = new CEMTransaction();
							CEMSpecification targetSpec11 = new CEMSpecification();
							CEMSpecification targetSpec12 = new CEMSpecification();
						
							CEMTransactionComponent targetTC7 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI13 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI14 = new CEMTransactionComponentIdentification();
							
							CEMTransactionComponent targetTC8 = new CEMTransactionComponent();
								CEMTransactionComponentIdentification targetCI15 = new CEMTransactionComponentIdentification();
								CEMTransactionComponentIdentification targetCI16 = new CEMTransactionComponentIdentification();
							
					
								
					
					//BUSINESS APPLICATION - BEGIN
					targetBA1.setTsName("Tomcat Sample Application");
					targetBA1.setTsId(new Long("1666"));
					
					targetBA1.setTsTranDefGroups(new Vector<CEMBusinessService>());
					targetBA1.setTsTransets(new Vector<CEMBusinessTransaction>());
					
					
					targetBA1.addTsTranDefGroup(targetBA1SV1);
					targetBA1.addTsTranset(targetBT1);
					targetBA1.addTsTranset(targetBT2);
					//BUSINESS APPLICATION - END
					
					//BUSINESS SERVICE - BEGIN
					targetBA1SV1.setTsName("Tomcat Service 1");
					targetBA1SV1.setTsDescription("Tomcat Service 1 description");
					targetBA1SV1.setTsId(new Long("10666"));
					targetBA1SV1.setTsTransets(new Vector<CEMBusinessTransaction>());
					targetBA1SV1.setTsTransetGroups(new Vector<TsTransetGroup>());
					targetBA1SV1.setTsApp(targetBA1);
					targetBA1SV1.addTsTranset(targetBT1);
					targetBA1SV1.addTsTranset(targetBT2);
					//BUSINESS SERVICE - END
					
					targetBT1.setTsDefectDefs(new Vector<CEMSpecification>());
					targetBT2.setTsDefectDefs(new Vector<CEMSpecification>());
					targetBT1.setTsTranunits(new Vector<CEMTransaction>());
					targetBT2.setTsTranunits(new Vector<CEMTransaction>());
					targetBT1.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
					targetBT2.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
					
					
					targetTR1.setTsDefectDefs(new Vector<CEMSpecification>());
					targetTR2.setTsDefectDefs(new Vector<CEMSpecification>());
					targetTR3.setTsDefectDefs(new Vector<CEMSpecification>());
					targetTR4.setTsDefectDefs(new Vector<CEMSpecification>());
					
					targetTR1.setTsTrancomps(new Vector<CEMTransactionComponent>());
					targetTR2.setTsTrancomps(new Vector<CEMTransactionComponent>());
					targetTR3.setTsTrancomps(new Vector<CEMTransactionComponent>());
					targetTR4.setTsTrancomps(new Vector<CEMTransactionComponent>());
					
					targetTC1.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC2.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC3.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC4.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC5.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC6.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC7.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					targetTC8.setTsParams(new Vector<CEMTransactionComponentIdentification>());
					
					//BUSINESS TRANSACTION - BEGIN
						
						//BT1
						targetBT1.setTsName("BT /service1/xx1 to delete");
						targetBT1.setTsDescription("BT /service1/xx1 description");
						targetBT1.setTsId(new Long("100666"));
						targetBT1.addTsTranunit(targetTR1);
						targetBT1.addTsTranunit(targetTR2);
						targetBT1.addTsDefectDef(targetSpec1);
						targetBT1.addTsDefectDef(targetSpec2);

							targetSpec1.setTsId(new Long("1000666"));
							targetSpec1.setTsName("Slow Time to delete");
							targetSpec1.setTsValue("3000");
						
							targetSpec2.setTsId(new Long("2000666"));
							targetSpec2.setTsName("Fast Time to delete");
							targetSpec2.setTsValue("1500");
							
							targetTR1.setTsName("TR /service1/xx1");
							targetTR1.setTsDescription("TR /service1/xx1 description");
							targetTR1.setTsSequenceNumber(new Integer("0"));
							targetTR1.setTsId(new Long("10000666"));
							targetTR1.addTsDefectDef(targetSpec5);
							targetTR1.addTsDefectDef(targetSpec6);
							targetTR1.addTsTrancomp(targetTC1);
							targetTR1.addTsTrancomp(targetTC2);
							
								targetSpec5.setTsId(new Long("5000666"));
								targetSpec5.setTsName("Slow Time");
								targetSpec5.setTsValue("3200");
								targetSpec5.setTsTranset(targetBT1);
								
								targetSpec6.setTsId(new Long("6000666"));
								targetSpec6.setTsName("Fast Time");
								targetSpec6.setTsValue("1700");
								targetSpec6.setTsTranset(targetBT1);
							
								targetTC1.setTsName("TC /service1/xx1");
								targetTC1.setTsId(new Long("100000666"));
								targetTC1.addTsParam(targetCI1);
								targetTC1.addTsParam(targetCI2);
									CEMTransactionComponentIdentificationPK targetCI1pk = new CEMTransactionComponentIdentificationPK();
									targetCI1pk.setTsName("ClientIP");
									targetCI1pk.setTsParamType("URL");
									targetCI1pk.setTsTrancompId(new Long("100000666"));
									targetCI1.setId(targetCI1pk);
									targetCI1.setTsPattern("192.168.128.135");
									targetCI1.setTsAutogenSequence(new Integer("0"));

									CEMTransactionComponentIdentificationPK targetCI2pk = new CEMTransactionComponentIdentificationPK();
									targetCI2pk.setTsName("Port");
									targetCI2pk.setTsParamType("URL");
									targetCI2pk.setTsTrancompId(new Long("100000666"));
									targetCI2.setId(targetCI2pk);
									targetCI2.setTsPattern("8080");
									targetCI2.setTsAutogenSequence(new Integer("1"));
									
								
								targetTC2.setTsName("TC /service1/xx2");
								targetTC2.setTsId(new Long("200000666"));
								targetTC2.addTsParam(targetCI3);
								targetTC2.addTsParam(targetCI4);
									CEMTransactionComponentIdentificationPK targetCI3pk = new CEMTransactionComponentIdentificationPK();
									targetCI3pk.setTsName("ClientIP");
									targetCI3pk.setTsParamType("URL");
									targetCI3pk.setTsTrancompId(new Long("200000666"));
									targetCI3.setId(targetCI3pk);
									targetCI3.setTsPattern("192.168.128.136");
									targetCI3.setTsAutogenSequence(new Integer("0"));
				
									CEMTransactionComponentIdentificationPK targetCI4pk = new CEMTransactionComponentIdentificationPK();
									targetCI4pk.setTsName("Port");
									targetCI4pk.setTsParamType("URL");
									targetCI4pk.setTsTrancompId(new Long("200000666"));
									targetCI4.setId(targetCI4pk);
									targetCI4.setTsPattern("8081");
									targetCI4.setTsAutogenSequence(new Integer("1"));
							
							
							targetTR2.setTsName("TR /service1/xx2");
							targetTR2.setTsDescription("TR /service1/xx2 description");
							targetTR2.setTsSequenceNumber(new Integer("1"));
							targetTR2.setTsId(new Long("20000666"));
							targetTR2.addTsDefectDef(targetSpec7);
							targetTR2.addTsDefectDef(targetSpec8);
							targetTR2.addTsTrancomp(targetTC3);
							targetTR2.addTsTrancomp(targetTC4);
							
							
							targetSpec7.setTsId(new Long("7000666"));
							targetSpec7.setTsName("Slow Time");
							targetSpec7.setTsValue("3200");
							targetSpec7.setTsTranset(targetBT1);
							
							targetSpec8.setTsId(new Long("8000666"));
							targetSpec8.setTsName("Fast Time");
							targetSpec8.setTsValue("1700");
							targetSpec8.setTsTranset(targetBT1);
						
							targetTC3.setTsName("TC /service1/xx1");
							targetTC3.setTsId(new Long("300000666"));
							targetTC3.addTsParam(targetCI5);
							targetTC3.addTsParam(targetCI6);
							
								CEMTransactionComponentIdentificationPK targetCI5pk = new CEMTransactionComponentIdentificationPK();
								targetCI5pk.setTsName("ClientIP");
								targetCI5pk.setTsParamType("URL");
								targetCI5pk.setTsTrancompId(new Long("300000666"));
								targetCI5.setId(targetCI5pk);
								targetCI5.setTsPattern("192.168.128.135");
								targetCI5.setTsAutogenSequence(new Integer("0"));

								CEMTransactionComponentIdentificationPK targetCI6pk = new CEMTransactionComponentIdentificationPK();
								targetCI6pk.setTsName("Port");
								targetCI6pk.setTsParamType("URL");
								targetCI6pk.setTsTrancompId(new Long("300000666"));
								targetCI6.setId(targetCI6pk);
								targetCI6.setTsPattern("8080");
								targetCI6.setTsAutogenSequence(new Integer("1"));
								
							
							targetTC4.setTsName("TC /service1/xx2");
							targetTC4.setTsId(new Long("400000666"));
							targetTC4.addTsParam(targetCI7);
							targetTC4.addTsParam(targetCI8);
							
								CEMTransactionComponentIdentificationPK targetCI7pk = new CEMTransactionComponentIdentificationPK();
								targetCI7pk.setTsName("ClientIP");
								targetCI7pk.setTsParamType("URL");
								targetCI7pk.setTsTrancompId(new Long("400000666"));
								targetCI7.setId(targetCI7pk);
								targetCI7.setTsPattern("192.168.128.136");
								targetCI7.setTsAutogenSequence(new Integer("0"));

								CEMTransactionComponentIdentificationPK targetCI8pk = new CEMTransactionComponentIdentificationPK();
								targetCI8pk.setTsName("Port");
								targetCI8pk.setTsParamType("URL");
								targetCI8pk.setTsTrancompId(new Long("400000666"));
								targetCI8.setId(targetCI8pk);
								targetCI8.setTsPattern("8081");
								targetCI8.setTsAutogenSequence(new Integer("1"));
							
						
						//BT2
						targetBT2.setTsName("BT /service1/xx1");
						targetBT2.setTsDescription("BT /service1/xx2 description");
						targetBT2.setTsId(new Long("200666"));
						targetBT2.addTsTranunit(targetTR3);
						targetBT2.addTsTranunit(targetTR4);
						targetBT2.addTsDefectDef(targetSpec3);
						targetBT2.addTsDefectDef(targetSpec4);
						
						
							targetSpec3.setTsId(new Long("3000666"));
							targetSpec3.setTsName("Slow Time to delete");
							targetSpec3.setTsValue("3100");
						
							targetSpec4.setTsId(new Long("4000666"));
							targetSpec4.setTsName("Slow Time");
							targetSpec4.setTsValue("1510");
						
							targetTR3.setTsName("TR /service1/xx1 to delete");
							targetTR3.setTsDescription("TR /service1/xx1 description");
							targetTR3.setTsSequenceNumber(new Integer("0"));
							targetTR3.setTsId(new Long("30000666"));
							targetTR3.addTsDefectDef(targetSpec9);
							targetTR3.addTsDefectDef(targetSpec10);
							targetTR3.addTsTrancomp(targetTC5);
							targetTR3.addTsTrancomp(targetTC6);
							
							
								targetSpec9.setTsId(new Long("9000666"));
								targetSpec9.setTsName("Slow Time to delete");
								targetSpec9.setTsValue("3200");
								targetSpec9.setTsTranset(targetBT2);
								
								targetSpec10.setTsId(new Long("10000666"));
								targetSpec10.setTsName("Fast Time to delete");
								targetSpec10.setTsValue("1700");
								targetSpec10.setTsTranset(targetBT2);
							
								targetTC5.setTsName("TC /service1/xx1");
								targetTC5.setTsId(new Long("500000666"));
								targetTC5.addTsParam(targetCI9);
								targetTC5.addTsParam(targetCI10);
									CEMTransactionComponentIdentificationPK targetCI9pk = new CEMTransactionComponentIdentificationPK();
									targetCI9pk.setTsName("ClientIP");
									targetCI9pk.setTsParamType("URL");
									targetCI9pk.setTsTrancompId(new Long("500000666"));
									targetCI9.setId(targetCI9pk);
									targetCI9.setTsPattern("192.168.128.135");
									targetCI9.setTsAutogenSequence(new Integer("0"));

									CEMTransactionComponentIdentificationPK targetCI10pk = new CEMTransactionComponentIdentificationPK();
									targetCI10pk.setTsName("Port");
									targetCI10pk.setTsParamType("URL");
									targetCI10pk.setTsTrancompId(new Long("500000666"));
									targetCI10.setId(targetCI10pk);
									targetCI10.setTsPattern("8080");
									targetCI10.setTsAutogenSequence(new Integer("1"));
									
								
								targetTC6.setTsName("TC /service1/xx2");
								targetTC6.setTsId(new Long("600000666"));
								targetTC6.addTsParam(targetCI11);
								targetTC6.addTsParam(targetCI12);
									CEMTransactionComponentIdentificationPK targetCI11pk = new CEMTransactionComponentIdentificationPK();
									targetCI11pk.setTsName("ClientIP");
									targetCI11pk.setTsParamType("URL");
									targetCI11pk.setTsTrancompId(new Long("600000666"));
									targetCI11.setId(targetCI11pk);
									targetCI11.setTsPattern("192.168.128.136");
									targetCI11.setTsAutogenSequence(new Integer("0"));
				
									CEMTransactionComponentIdentificationPK targetCI12pk = new CEMTransactionComponentIdentificationPK();
									targetCI12pk.setTsName("Port");
									targetCI12pk.setTsParamType("URL");
									targetCI12pk.setTsTrancompId(new Long("600000666"));
									targetCI12.setId(targetCI12pk);
									targetCI12.setTsPattern("8081");
									targetCI12.setTsAutogenSequence(new Integer("1"));
							
							targetTR4.setTsName("TR /service1/xx1");
							targetTR4.setTsDescription("TR /service1/xx2 description");
							targetTR4.setTsSequenceNumber(new Integer("1"));
							targetTR4.setTsId(new Long("40000666"));
							targetTR4.addTsDefectDef(targetSpec11);
							targetTR4.addTsDefectDef(targetSpec12);
							targetTR4.addTsTrancomp(targetTC7);
							targetTR4.addTsTrancomp(targetTC8);
							
							
							targetSpec11.setTsId(new Long("11000666"));
							targetSpec11.setTsName("Slow Time to delete");
							targetSpec11.setTsValue("3200");
							targetSpec11.setTsTranset(targetBT2);
							
							targetSpec12.setTsId(new Long("12000666"));
							targetSpec12.setTsName("Slow Time");
							targetSpec12.setTsValue("1700");
							targetSpec12.setTsTranset(targetBT2);
						
							targetTC7.setTsName("TC /service1/xx2 to delete");
							targetTC7.setTsId(new Long("700000666"));
							targetTC7.addTsParam(targetCI13);
							targetTC7.addTsParam(targetCI14);
								CEMTransactionComponentIdentificationPK targetCI13pk = new CEMTransactionComponentIdentificationPK();
								targetCI13pk.setTsName("ClientIP");
								targetCI13pk.setTsParamType("URL");
								targetCI13pk.setTsTrancompId(new Long("700000666"));
								targetCI13.setId(targetCI13pk);
								targetCI13.setTsPattern("192.168.128.135");
								targetCI13.setTsAutogenSequence(new Integer("0"));

								CEMTransactionComponentIdentificationPK targetCI14pk = new CEMTransactionComponentIdentificationPK();
								targetCI14pk.setTsName("Port");
								targetCI14pk.setTsParamType("URL");
								targetCI14pk.setTsTrancompId(new Long("700000666"));
								targetCI14.setId(targetCI14pk);
								targetCI14.setTsPattern("8080");
								targetCI14.setTsAutogenSequence(new Integer("1"));
								
							
							targetTC8.setTsName("TC /service1/xx1");
							targetTC8.setTsDescription("target TC description");
							targetTC8.setTsId(new Long("800000666"));
							targetTC8.addTsParam(targetCI15);
							targetTC8.addTsParam(targetCI16);
								CEMTransactionComponentIdentificationPK targetCI15pk = new CEMTransactionComponentIdentificationPK();
								targetCI15pk.setTsName("ClientIP to delete");
								targetCI15pk.setTsParamType("URL");
								targetCI15pk.setTsTrancompId(new Long("800000666"));
								targetCI15.setId(targetCI15pk);
								targetCI15.setTsPattern("192.168.128.136");
								targetCI15.setTsAutogenSequence(new Integer("0"));

								CEMTransactionComponentIdentificationPK targetCI16pk = new CEMTransactionComponentIdentificationPK();
								targetCI16pk.setTsName("ClientIP");
								targetCI16pk.setTsParamType("URL");
								targetCI16pk.setTsTrancompId(new Long("800000666"));
								targetCI16.setId(targetCI16pk);
								targetCI16.setTsPattern("8081");
								targetCI16.setTsAutogenSequence(new Integer("1"));

					//BUSINESS TRANSACTION - END
					
					
								//TsTranseGroup & mapping definition - BEGIN
								TsTransetGroup targettsTransetGroup = new TsTransetGroup();
								
								TsTransetgroupTransetsMapPK targetMap1pk = new TsTransetgroupTransetsMapPK();
								TsTransetgroupTransetsMapPK targetMap2pk = new TsTransetgroupTransetsMapPK();
								TsTransetgroupTransetsMap targetMap1 = new TsTransetgroupTransetsMap();
								TsTransetgroupTransetsMap targetMap2 = new TsTransetgroupTransetsMap();
								
								TsTransetgroupTransetsMapPK targetMap3pk = new TsTransetgroupTransetsMapPK();
								TsTransetgroupTransetsMapPK targetMap4pk = new TsTransetgroupTransetsMapPK();
								TsTransetgroupTransetsMap targetMap3 = new TsTransetgroupTransetsMap();
								TsTransetgroupTransetsMap targetMap4 = new TsTransetgroupTransetsMap();
								
								targetBA1SV1.addTsTransetGroup(targettsTransetGroup);
								targettsTransetGroup.setTsName("Tomcat Service 1");
								targettsTransetGroup.setTsId(new Long("1066610666"));
								targettsTransetGroup.setTsDescription("Tomcat Service 1 description");
								
								targettsTransetGroup.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
								targettsTransetGroup.addTsTransetgroupTransetsMap(targetMap1);
								targettsTransetGroup.addTsTransetgroupTransetsMap(targetMap2);
								targettsTransetGroup.addTsTransetgroupTransetsMap(targetMap3);
								targettsTransetGroup.addTsTransetgroupTransetsMap(targetMap4);
								
								targetMap1pk.setTsTransetGroupId(targettsTransetGroup.getTsId());
								targetMap1pk.setTsTransetId(targetBT1.getTsId());
								targetMap1pk.setTsTransetIncarnationId(targetBT1.getTsId()+1);

								targetMap2pk.setTsTransetGroupId(targettsTransetGroup.getTsId());
								targetMap2pk.setTsTransetId(targetBT1.getTsId());
								targetMap2pk.setTsTransetIncarnationId(targetBT1.getTsId()+2);

								targetMap3pk.setTsTransetGroupId(targettsTransetGroup.getTsId());
								targetMap3pk.setTsTransetId(targetBT2.getTsId());
								targetMap3pk.setTsTransetIncarnationId(targetBT2.getTsId()+1);

								targetMap4pk.setTsTransetGroupId(targettsTransetGroup.getTsId());
								targetMap4pk.setTsTransetId(targetBT2.getTsId());
								targetMap4pk.setTsTransetIncarnationId(targetBT2.getTsId()+2);
								
								targetMap1.setId(targetMap1pk);
								targetMap2.setId(targetMap2pk);
								targetMap3.setId(targetMap3pk);
								targetMap4.setId(targetMap4pk);
								
								targetMap1.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
								targetMap2.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
								targetMap3.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));
								targetMap4.setTsCreateDate(new java.sql.Timestamp(new java.util.Date().getTime()));

								targetMap1.setTsSoftDelete(Boolean.FALSE);
								targetMap2.setTsSoftDelete(Boolean.TRUE);
								targetMap3.setTsSoftDelete(Boolean.FALSE);
								targetMap4.setTsSoftDelete(Boolean.TRUE);
								
								
								targetMap1.setTsDeleteDate(null);
								targetMap2.setTsDeleteDate(new java.sql.Timestamp(new java.util.Date().getTime()));
								targetMap3.setTsDeleteDate(null);
								targetMap4.setTsDeleteDate(new java.sql.Timestamp(new java.util.Date().getTime()));
								
								targetMap3.setVersionInfo(new Long(456));
								
								targetBT1.addTsTransetgroupTransetsMap(targetMap1);
								//targetBT1.addTsTransetgroupTransetsMap(targetMap2);
								targetBT2.addTsTransetgroupTransetsMap(targetMap3);
								//targetBT2.addTsTransetgroupTransetsMap(targetMap4);
								
								
								//TsTranseGroup & mapping definition - END
					
/*
 * this code was used to serialize object to XML
String xml=null;
String xml2=null;
xml = com.catechnologies.apm.cem.cemdbreconciliation.util.XMLSerializer.objectToXML(sourceBA1, CEMBusinessApplication.class);
xml2 = com.catechnologies.apm.cem.cemdbreconciliation.util.XMLSerializer.objectToXML(targetBA1, CEMBusinessApplication.class);

try {
	java.io.PrintWriter out1 = new java.io.PrintWriter("c:/tmp/sourceBA1.xml");
	java.io.PrintWriter out2 =  new java.io.PrintWriter("c:/tmp/targetBA1.xml");
	out1.print(xml);
	out2.print(xml2);out1.close();out2.close();
} catch (Exception e) {	e.printStackTrace();}
*/

		
		/**
		 * General setup of source and target data. This will be done on all levels (from business
		 * transaction to component identification) effectively meaning we are testing same merging
		 * scenario across all levels.
		 * 
		 * 
		 * Source										Target
		 * -----------------------------------------------------------------------------------------------------
		 * item1 (Will update target item2)				item1 (will be deleted, no counter match in source data)
		 * item2 (Will be inserted as target item3)		item2 (will be updated by source item1)
		 * 												item3 (inserted source item1)
		 * 
		 * When applying for particular entities following will be valid (and testing data layout must support it):
		 * 
		 * 
		 * Prefix		Entity
		 * ---------------------------
		 * t*			target entity
		 * s*			source entity
		 * BT			CEMBusinessTransaction
		 * SP			CEMSpecification
		 * MP			TsTransetgroupTransetsMap
		 * TR			CEMTransaction
		 * TC			CEMTransactionComponent
		 * CI			CEMTransactionComponentIdentification
		 * 
		 * 
		 * Deleted entities				Updated/Merged Entities		Inserted Entities (into target)
		 * 
		 * (add 'to delete' literal		(name match)				(no identical name in target, add
		 * to target name)											'to insert' literal to source name)
		 * -----------------------------------------------------------------------------------------------------
		 *  tBT1  (and below)			sBT1->tBT2					sBT2
		 *  tSP1,2,3					sSP1->tSP4 					sSP2
		 *  tMP1,2,3					sMP3->tMP4 					sMP4
		 *  tTR3  (and below)			sTR1->tTR4					sTR2
		 *  tSP9,10,11					sSP11->tSP12				sSP12						
		 * 	tTC7  (and below)			sTC1->tTC8					sTC2
		 *  tCI15 (and below)			sCI15->tCI16				sCI16
		 * 
		 * 
		 * 
		 */

					//check insertions
					assertTrue(sourceBT2.getTsTranDefGroup().getTsApp() == sourceBA1);
					assertTrue(sourceBT2.getTsTranDefGroup() == sourceBA1SV1);
					assertTrue(sourceBT2.getTsTranDefGroup().getTsTransetGroups().get(0) == sourcetsTransetGroup);
					assertFalse(targetBA1.getTsTransets().contains(sourceBT2));
					assertTrue(sourceBA1.getTsTransets().contains(sourceBT2));

					assertTrue(sourceMap3.getTsTranset()==sourceBT2);
					assertTrue(sourceMap3.getTsTransetGroup()==sourcetsTransetGroup);
					assertTrue(sourceMap3.getTsTransetGroup().getTsTranDefGroup()==sourceBA1SV1);
					
					
					//check deleted entities
					assertTrue(targetBA1.getTsTransets().get(targetBA1.getTsTransets().indexOf(targetBT1)).getTsSoftDelete()==null);
					
					
					//check merges
					assertFalse(sourceBT1.getTsDescription().equals(targetBT2.getTsDescription()));
					assertTrue(targetBT2.getTsTransetgroupTransetsMaps().size()==1);
					assertTrue(targetBT2.getTsTransetgroupTransetsMaps().get(0).getVersionInfo().equals(new Long(456)));
					
					//CEMSpecification assertions
					assertTrue(targetBT2.getTsDefectDefs().size() == 2);
					assertTrue(targetBT2.getTsDefectDefs().contains(targetSpec3));
					assertTrue(targetBT2.getTsDefectDefs().contains(targetSpec4));
					assertTrue(targetSpec4.getTsValue().equals("1510"));
					assertTrue(sourceSpec2.getTsTranset() == sourceBT1);
					assertTrue(sourceSpec2.getTsTranunit() == null);
					
					//CEMTransaction assertions
					assertTrue(targetBA1.getTsTransets().get(1)==targetBT2);//just check whether we have two BTs, out of which second is targetBT2
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().size()==2);
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().contains(targetTR3));
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().contains(targetTR4));
					assertTrue(targetTR3.getTsSoftDelete()==null);
					assertFalse(sourceTR1.getTsDescription().equals(targetTR4.getTsDescription()));
					assertTrue(sourceTR2.getTsTranset()==sourceBT1);
					assertFalse(targetBT2.getTsTranunits().contains(sourceTR2));
					
					assertTrue(sourceTR2.getTsSequenceNumber().intValue()==1);
					
					//CEMTransactionComponent assertions
					assertTrue(targetTR4.getTsTrancomps().size()==2);
					assertTrue(targetTR4.getTsTrancomps().contains(targetTC7));
					assertTrue(targetTR4.getTsTrancomps().contains(targetTC8));
					
					assertTrue(sourceTC2.getTsSequenceNumber()==null);
					assertFalse(sourceTC1.getTsDescription().equals(targetTC8.getTsDescription()));
					
					/************************************
					 ************************************
					 * MERGING***************************
					 * **********************************
					 * **********************************
					 */
					
					log.warn("starting to merge...");
					sourceBA1.copyTo(targetBA1);
					log.info("mering done!");

					/************************************
					 ************************************
					 * MERGING***************************
					 * **********************************
					 * **********************************
					 */
					
					//
					//check insertions
					//
					//not valid any more since we are cloning source objects now and not reattaching them to target tree 
					//assertTrue(sourceBT2.getTsTranDefGroup().getTsApp() == targetBA1);
					//assertTrue(sourceBT2.getTsTranDefGroup() == targetBA1SV1);
					//assertTrue(sourceBT2.getTsTranDefGroup().getTsTransetGroups().get(0) == targettsTransetGroup);//added BT is pointing to appropriate TsTransetGroup (i.e. entity representing business service) 
					
					//assertTrue(targetBA1.getTsTransets().contains(sourceBT2));
					//assertTrue(sourceBA1.getTsTransets().contains(sourceBT2));//after merge we still keep BT object in old/source list as well. it probably does no matter
					
					//assertTrue(sourceMap3.getTsTranset()==sourceBT2);//this is still valid, map was transfered to target together with its business transaction
					//assertTrue(sourceBT2.getTsTransetgroupTransetsMaps().get(0) == sourceMap3);
					
					//not working with transet groups for now
					//assertFalse(sourceMap3.getTsTransetGroup()==sourcetsTransetGroup);//but transet group object is different
					//assertFalse(sourceBT2.getTsTransetgroupTransetsMaps().get(0).getTsTransetGroup()==sourcetsTransetGroup);
					
					//assertFalse(sourceMap3.getTsTransetGroup().getTsTranDefGroup()==sourceBA1SV1);//same as business service to which transet group object is bound
					//assertFalse(sourceBT2.getTsTransetgroupTransetsMaps().get(0).getTsTransetGroup().getTsTranDefGroup()==sourceBA1SV1);
					
					//assertTrue(sourceMap3.getTsTransetGroup()==targettsTransetGroup);
					//assertTrue(sourceBT2.getTsTransetgroupTransetsMaps().get(0).getTsTransetGroup()==targettsTransetGroup);
					
					//assertTrue(sourceMap3.getTsTransetGroup().getTsTranDefGroup()==targetBA1SV1);
					//assertTrue(sourceBT2.getTsTransetgroupTransetsMaps().get(0).getTsTransetGroup().getTsTranDefGroup()==targetBA1SV1);

					
					//check deleted entities
					assertFalse(targetBA1.getTsTransets().get(targetBA1.getTsTransets().indexOf(targetBT1)).getTsSoftDelete()==null);
					assertTrue(targetBA1.getTsTransets().get(targetBA1.getTsTransets().indexOf(targetBT1)).getTsSoftDelete().booleanValue()==true);
					assertTrue(targetSpec3.getTsSoftDelete().booleanValue()==true);
					
					//check merges
					assertTrue(sourceBT1.getTsDescription().equals(targetBT2.getTsDescription()));					
					assertTrue(targetBT2.getTsTransetgroupTransetsMaps().size()==1);
					
					//
					//not merging transet groups and maps in this iterations, uncomment this assertion once this kind of merge will be implemented
					//assertTrue(targetBT2.getTsTransetgroupTransetsMaps().get(0).getVersionInfo().equals(new Long(123)));

					
					
					//CEMSpecification assertions
					assertTrue(targetBT2.getTsDefectDefs().size() == 3);
					assertTrue(targetBT2.getTsDefectDefs().contains(targetSpec3));
					assertTrue(targetBT2.getTsDefectDefs().contains(targetSpec4));
					assertTrue(targetBT2.getTsDefectDefs().contains(sourceSpec2));
					assertTrue(targetSpec4.getTsValue().equals("3000"));
					
					assertTrue(sourceSpec2.getTsTranset() == targetBT2);
					assertTrue(sourceSpec2.getTsTranunit() == null);
					
					//CEMTransaction assertions
					assertTrue(targetBA1.getTsTransets().get(1)==targetBT2);//just check whether we have two BTs, out of which second is targetBT2
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().size()==3);//sourceTR2 was inserted
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().contains(targetTR3));
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().contains(targetTR4));
					assertTrue(targetBA1.getTsTransets().get(1).getTsTranunits().contains(sourceTR2));
					
					assertTrue(sourceTR2.getTsTranset()==targetBT2);
					
					assertTrue(targetBT2.getTsTranunits().contains(sourceTR2));
					
					assertTrue(targetTR3.getTsSoftDelete()!=null);
					assertTrue(targetTR3.getTsSoftDelete().booleanValue()==true);
					
					assertTrue(sourceTR1.getTsDescription().equals(targetTR4.getTsDescription()));//merge was successful    
					assertTrue(sourceTR2.getTsSequenceNumber().intValue()==2);
					
					//CEMTransactionComponent assertions
					
					assertTrue(targetTR4.getTsTrancomps().size()==3);
					assertTrue(targetTR4.getTsTrancomps().contains(targetTC7));
					assertTrue(targetTR4.getTsTrancomps().contains(targetTC8));
					assertTrue(targetTR4.getTsTrancomps().contains(sourceTC2));
					
					assertTrue(sourceTC2.getTsTranunit() == targetTR4);
					assertTrue(sourceTC2.getTsSequenceNumber().intValue()==1);
					
					assertTrue(sourceTC1.getTsDescription().equals(targetTC8.getTsDescription()));
					assertTrue(targetTC7.getTsSoftDelete().booleanValue()==true);
	}	
	
	@Test
	@Ignore("Merge tests needs to be reviewed and rewritten after implementing cloning in merge algorythm.")
	public void mergeJumboJetNG() throws Exception {
		
		String sourceXML = ResourceUtil.getFileResource("/sourceBA1.xml");
		String targetXML = ResourceUtil.getFileResource("/targetBA1.xml");
		
		
		CEMBusinessApplication sourceBA1 = (CEMBusinessApplication)XMLSerializer.xmlToObject(sourceXML, CEMBusinessApplication.class);
		CEMBusinessApplication targetBA1 = (CEMBusinessApplication)XMLSerializer.xmlToObject(targetXML, CEMBusinessApplication.class);
		
		//when BA is deserialized we always need to set respective back pointers for its services manually!
		
		for(int i=0;i<sourceBA1.getTsTranDefGroups().size();i++)
			sourceBA1.getTsTranDefGroups().get(i).setTsApp(sourceBA1);
		
		for(int i=0;i<targetBA1.getTsTranDefGroups().size();i++)
			targetBA1.getTsTranDefGroups().get(i).setTsApp(targetBA1);
		
		
		//we must also initialize list of business application's transets (business transactions). This is not done during deserialization since business transactions
		//are contained only within business services elements.
		sourceBA1.setTsTransets(new ArrayList<CEMBusinessTransaction>());
		targetBA1.setTsTransets(new ArrayList<CEMBusinessTransaction>());
		
		for(int i=0;i<sourceBA1.getTsTranDefGroups().size();i++){
			for(int ii=0;ii<sourceBA1.getTsTranDefGroups().get(i).getTsTransets().size();ii++){
				sourceBA1.addTsTranset(sourceBA1.getTsTranDefGroups().get(i).getTsTransets().get(ii));
			}
		}
		
		for(int i=0;i<targetBA1.getTsTranDefGroups().size();i++){
			for(int ii=0;ii<targetBA1.getTsTranDefGroups().get(i).getTsTransets().size();ii++){
				targetBA1.addTsTranset(targetBA1.getTsTranDefGroups().get(i).getTsTransets().get(ii));
			}
		}
		
		
		sourceBA1.copyTo(targetBA1);
		
		String xml = XMLSerializer.objectToXML(targetBA1, CEMBusinessApplication.class);
		log.info(xml);
	}	
	
	
	
}
