package com.catechnologies.apm.cem.cemdbreconciliation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.*;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.*;
import com.catechnologies.apm.cem.cemdbreconciliation.util.XMLSerializer;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.Vector;
import java.util.Date;
import java.math.BigDecimal;
import java.sql.Timestamp;


/**
 * http://blog.bdoughan.com/2010/07/xpath-based-mapping.html
 * http://blog.bdoughan.com/2010/07/xmladapter-jaxbs-secret-weapon.html
 * http://www.eclipse.org/eclipselink/documentation/2.4/solutions/jpatoxml002.htm
 * http://blog.bdoughan.com/2013/03/moxys-xmlinversereference-is-now-truly.html  !!!!
 * http://www.ibm.com/developerworks/rational/library/resolve-jaxb-cycle-errors/index.html !!!!
 * 
 * 
 * @author bezad01
 *
 */
public class JAXBTests {

	static final Logger log = LogManager.getLogger(JAXBTests.class.getName());
	
	
	static CEMBusinessApplication cemBusinessApp = new CEMBusinessApplication();
	static CEMBusinessService cemBusinessSvc = new CEMBusinessService();
	
	static TsTransetGroup cemTransetGroup1 = new TsTransetGroup();
	static TsTransetGroup cemTransetGroup2 = new TsTransetGroup();
	
	static CEMBusinessTransaction cemBusinessTrx1 = new CEMBusinessTransaction(); 
	static CEMTransaction cemTrx1 = new CEMTransaction();
	static CEMTransactionComponent cemTrxComp1 = new CEMTransactionComponent();

	static TsTransetgroupTransetsMapPK cemTranSetMapPK1 = new TsTransetgroupTransetsMapPK(); 
	static TsTransetgroupTransetsMapPK cemTranSetMapPK2 = new TsTransetgroupTransetsMapPK();
	static TsTransetgroupTransetsMap cemTranSetMap1 = new TsTransetgroupTransetsMap(); 
	static TsTransetgroupTransetsMap cemTranSetMap2 = new TsTransetgroupTransetsMap();
	
	static CEMSpecification cemSpec1 = new CEMSpecification(); //Business Transaction level specifications  
	static CEMSpecification cemSpec2 = new CEMSpecification();

	static CEMSpecification cemSpec3 = new CEMSpecification(); //Transaction level specifications 
	static CEMSpecification cemSpec4 = new CEMSpecification();

	static CEMSpecification cemSpec5 = new CEMSpecification(); //Component level specifications, should not be used in real environment 
	static CEMSpecification cemSpec6 = new CEMSpecification(); //this is for testing only
	
	
	

	static CEMTransactionComponentIdentification cemTrxCompIdent1 = new CEMTransactionComponentIdentification();
	static CEMTransactionComponentIdentificationPK cemTrxCompIdentPK1 = new CEMTransactionComponentIdentificationPK();
	
	static CEMTransactionComponentIdentification cemTrxCompIdent2 = new CEMTransactionComponentIdentification();
	static CEMTransactionComponentIdentificationPK cemTrxCompIdentPK2 = new CEMTransactionComponentIdentificationPK();
	
    @BeforeClass
    public static void setUp(){

    	cemBusinessApp.setTsId(new Long("123"));
    	cemBusinessApp.setTsApptypeId(new Long("456"));
    	cemBusinessApp.setTsAuthtypeId(new Long("789"));
    	cemBusinessApp.setTsCaseSenstvCookieName(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvCookieValue(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvLoginName(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvPostName(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvPostValue(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvQueryName(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvQueryValue(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvUrlHost(Boolean.FALSE);
    	cemBusinessApp.setTsCaseSenstvUrlPath(Boolean.TRUE);
    	cemBusinessApp.setTsCharset("UTF-8");
    	cemBusinessApp.setTsDescription("cemBusinessApp description");
    	cemBusinessApp.setTsDomainId(new Long("1"));
    	cemBusinessApp.setTsName("cemBusinessApp");
    	cemBusinessApp.setTsSessionTimeout(new Integer("5"));
    	cemBusinessApp.setTsSoftDelete(Boolean.FALSE);
    	cemBusinessApp.setTsUserProcessingType(new Integer("1"));
    	cemBusinessApp.setVersionInfo(new Long("666"));
    	cemBusinessApp.setTsTranDefGroups(new Vector<CEMBusinessService>());
    	cemBusinessApp.setTsTransets(new Vector<CEMBusinessTransaction>());
    	cemBusinessApp.addTsTranDefGroup(cemBusinessSvc);
    	cemBusinessApp.addTsTranset(cemBusinessTrx1);
    	

    	cemBusinessSvc.setTsId(new Long("123456"));
    	cemBusinessSvc.setTsBusinessValue(new BigDecimal("56"));
    	cemBusinessSvc.setTsBusinessValueInherited(Boolean.TRUE);
    	cemBusinessSvc.setTsDescription("cemBusinessSvc description");
    	cemBusinessSvc.setTsDomainId(new Long("1"));
    	cemBusinessSvc.setTsImportanceId(new Long("2"));
    	cemBusinessSvc.setTsImportanceInherited(Boolean.FALSE);
    	cemBusinessSvc.setTsItValue(new BigDecimal("65"));
    	cemBusinessSvc.setTsItValueInherited(Boolean.FALSE);
    	cemBusinessSvc.setTsName("cemBusinessSvc");
    	cemBusinessSvc.setTsSigmaSla(12.5f);
    	cemBusinessSvc.setTsSigmaSlaInherited(Boolean.FALSE);
    	cemBusinessSvc.setTsSoftDelete(Boolean.FALSE);
    	cemBusinessSvc.setTsSuccessRateSla(14.5f);
    	cemBusinessSvc.setTsSuccessSlaInherited(Boolean.FALSE);
    	cemBusinessSvc.setTsTranTimeSla(new Integer("5"));
    	cemBusinessSvc.setTsTranTimeSlaInherited(Boolean.FALSE);
    	cemBusinessSvc.setTsUserMinuteCost(new BigDecimal("600"));
    	cemBusinessSvc.setTsUserMinuteCostInherited(Boolean.FALSE);
    	cemBusinessSvc.setVersionInfo(new Long("777"));    	
    	cemBusinessSvc.setTsTransets(new Vector<CEMBusinessTransaction>());
    	cemBusinessSvc.setTsTransetGroups(new Vector<TsTransetGroup>());
    	cemBusinessSvc.addTsTranset(cemBusinessTrx1);
    	cemBusinessSvc.addTsTransetGroup(cemTransetGroup1);
    	cemBusinessSvc.addTsTransetGroup(cemTransetGroup2);
    	
    	cemTransetGroup1.setTsId(new Long("556"));
    	cemTransetGroup1.setTsDescription("cemTransetGroup1 description");
    	cemTransetGroup1.setTsImportanceId(new Long("6"));
    	cemTransetGroup1.setTsImportanceInherited(Boolean.FALSE);
    	cemTransetGroup1.setTsName("cemTransetGroup1");
    	cemTransetGroup1.setTsSigmaSla(6.6f);
    	cemTransetGroup1.setTsSigmaSlaInherited(Boolean.FALSE);
    	cemTransetGroup1.setTsSoftDelete(Boolean.FALSE);
    	cemTransetGroup1.setTsSuccessRateSla(3.2f);
    	cemTransetGroup1.setTsSuccessSlaInherited(Boolean.FALSE);
    	cemTransetGroup1.setTsTranTimeSla(new Integer("650"));
    	cemTransetGroup1.setTsTranTimeSlaInherited(Boolean.FALSE);
    	cemTransetGroup1.setVersionInfo(new Long("654"));
    	cemTransetGroup1.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
    	cemTransetGroup1.setTsTransetGroups(new Vector<TsTransetGroup>());
    	cemTransetGroup1.addTsTransetgroupTransetsMap(cemTranSetMap1);
    	cemTransetGroup1.addTsTransetgroupTransetsMap(cemTranSetMap2);
    	cemTransetGroup1.addTsTransetGroup(cemTransetGroup2);
    	
    	cemTransetGroup2.setTsId(new Long("556777"));
    	cemTransetGroup2.setTsDescription("cemTransetGroup2 description");
    	cemTransetGroup2.setTsName("cemTransetGroup2");
    	
    	
    	cemBusinessTrx1.setTsId(new Long("555666"));
    	cemBusinessTrx1.setTsAppIdInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsAutogenRequestCount(new Long("2"));
    	cemBusinessTrx1.setTsAutogenTemplateId(new Long("222333"));
    	cemBusinessTrx1.setTsBusinessValue(new BigDecimal("600"));
    	cemBusinessTrx1.setTsBusinessValueInherited(Boolean.TRUE);
    	cemBusinessTrx1.setTsCollectTranunitStats(Boolean.TRUE);
    	cemBusinessTrx1.setTsCreationDate(new Timestamp(new Date().getTime()));
    	cemBusinessTrx1.setTsCreatorId(new Long("77888"));
    	cemBusinessTrx1.setTsCreatorName("Adam");
    	cemBusinessTrx1.setTsDescription("cemBusinessTrx1 description");
    	cemBusinessTrx1.setTsEnabled(Boolean.TRUE);
    	cemBusinessTrx1.setTsImportanceId(new Long("5"));
    	cemBusinessTrx1.setTsImportanceInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsIncarnationId(new Long("67"));
    	cemBusinessTrx1.setTsIscpAgntMtrAllComp(new Integer("1"));
    	cemBusinessTrx1.setTsItValue(new BigDecimal("250.55"));
    	cemBusinessTrx1.setTsItValueInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsLastCaptureDate(new Timestamp(new Date().getTime()));
    	cemBusinessTrx1.setTsLastModifiedDate(new Timestamp(new Date().getTime()));
    	cemBusinessTrx1.setTsLastModifierId(new Long("77888"));
    	cemBusinessTrx1.setTsLastModifierName("Adam");
    	cemBusinessTrx1.setTsMatchingTransetId(new Long("123"));
    	cemBusinessTrx1.setTsName("cemBusinessTrx1");
    	cemBusinessTrx1.setTsRequestBaseId(new Long("123456789"));
    	cemBusinessTrx1.setTsSigmaSla(15.5f);
    	cemBusinessTrx1.setTsSigmaSlaInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsSoftDelete(Boolean.FALSE);
    	cemBusinessTrx1.setTsSuccessRateSla(3.5f);
    	cemBusinessTrx1.setTsSuccessSlaInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsTotalCaptures(new Long("25000"));
    	cemBusinessTrx1.setTsTranTimeSla(new Integer("500"));
    	cemBusinessTrx1.setTsTranTimeSlaInherited(Boolean.FALSE);
    	cemBusinessTrx1.setTsTransetType(new Integer("2"));
    	cemBusinessTrx1.setTsUserMinuteCost(new BigDecimal("12.55"));
    	cemBusinessTrx1.setTsUserMinuteCostInherited(Boolean.FALSE);
    	cemBusinessTrx1.setVersionInfo(new Long("5678"));
    	cemBusinessTrx1.setTsTranunits(new Vector<CEMTransaction>());
    	cemBusinessTrx1.setTsDefectDefs(new Vector<CEMSpecification>());
    	cemBusinessTrx1.setTsTransetgroupTransetsMaps(new Vector<TsTransetgroupTransetsMap>());
    	cemBusinessTrx1.addTsTranunit(cemTrx1);
    	cemBusinessTrx1.addTsDefectDef(cemSpec1);
    	cemBusinessTrx1.addTsDefectDef(cemSpec2);
    	cemBusinessTrx1.addTsTransetgroupTransetsMap(cemTranSetMap1);
    	cemBusinessTrx1.addTsTransetgroupTransetsMap(cemTranSetMap2);
    	
    	
    	cemTranSetMapPK1.setTsTransetGroupId(new Long("123456789"));
    	cemTranSetMapPK1.setTsTransetId(new Long("555666"));
    	cemTranSetMapPK1.setTsTransetIncarnationId(new Long("67"));
    	cemTranSetMap1.setId(cemTranSetMapPK1);
    	cemTranSetMap1.setTsCreateDate(new Timestamp(new Date().getTime()));
    	cemTranSetMap1.setTsDeleteDate(new Timestamp(new Date().getTime()));
    	cemTranSetMap1.setTsSoftDelete(Boolean.TRUE);
    	cemTranSetMap1.setVersionInfo(new Long("12"));    	
    	
    	cemTranSetMapPK2.setTsTransetGroupId(new Long("123456789"));
    	cemTranSetMapPK2.setTsTransetId(new Long("555666"));
    	cemTranSetMapPK2.setTsTransetIncarnationId(new Long("68"));
    	cemTranSetMap2.setId(cemTranSetMapPK2);
    	cemTranSetMap2.setTsCreateDate(new Timestamp(new Date().getTime()));
    	cemTranSetMap2.setTsDeleteDate(null);
    	cemTranSetMap2.setTsSoftDelete(Boolean.FALSE);
    	cemTranSetMap2.setVersionInfo(new Long("14"));    	
    	
    	
    	cemSpec1.setTsId(new Long("700000000000000097"));
    	cemSpec1.setTsAttributeId(new Long("1"));
    	cemSpec1.setTsCondition(null);
    	cemSpec1.setTsEnabled(Boolean.TRUE);
    	cemSpec1.setTsHeaderName("Name");
    	cemSpec1.setTsImportanceId(new Long("5"));
    	cemSpec1.setTsLocked(Boolean.FALSE);
    	cemSpec1.setTsName("High Throughput");
    	cemSpec1.setTsSoftDelete(Boolean.FALSE);
    	cemSpec1.setTsTranType(new Integer("3"));
    	cemSpec1.setTsType(new Integer("11"));
    	cemSpec1.setTsValue("10000");
    	cemSpec1.setVersionInfo(new Long("0"));

    	cemSpec2.setTsId(new Long("700000000000012397"));
    	cemSpec2.setTsAttributeId(new Long("12"));
    	cemSpec2.setTsCondition(new Integer("56"));
    	cemSpec2.setTsEnabled(Boolean.TRUE);
    	cemSpec2.setTsHeaderName(null);
    	cemSpec2.setTsImportanceId(new Long("50"));
    	cemSpec2.setTsLocked(Boolean.TRUE);
    	cemSpec2.setTsName("Slow Response");
    	cemSpec2.setTsSoftDelete(Boolean.FALSE);
    	cemSpec2.setTsTranType(new Integer("30"));
    	cemSpec2.setTsType(new Integer("111"));
    	cemSpec2.setTsValue("100000");
    	cemSpec2.setVersionInfo(new Long("123"));    	
    	
    	cemTrx1.setTsId(new Long("700000000000012666"));
    	cemTrx1.setTsCollectTrancompStats(Boolean.TRUE);
    	cemTrx1.setTsDescription("cemTrx1 description");
    	cemTrx1.setTsIdentifying(Boolean.TRUE);
    	cemTrx1.setTsIncluded(Boolean.TRUE);
    	cemTrx1.setTsName("cemTrx1");
    	cemTrx1.setTsOptional(Boolean.FALSE);
    	cemTrx1.setTsSequenceNumber(new Integer("0"));
    	cemTrx1.setTsSigmaSla(35.6f);
    	cemTrx1.setTsSigmaSlaInherited(Boolean.FALSE);
    	cemTrx1.setTsSoftDelete(Boolean.FALSE);
    	cemTrx1.setTsSuccessRateSla(22.2f);
    	cemTrx1.setTsSuccessSlaInherited(Boolean.FALSE);
    	cemTrx1.setTsTranTimeSla(new Integer("5"));
    	cemTrx1.setTsTranTimeSlaInherited(Boolean.FALSE);
    	cemTrx1.setVersionInfo(new Long("987"));
    	cemTrx1.setTsTrancomps(new Vector<CEMTransactionComponent>());
    	cemTrx1.setTsDefectDefs(new Vector<CEMSpecification>());
    	cemTrx1.addTsTrancomp(cemTrxComp1);
    	cemTrx1.addTsDefectDef(cemSpec3);
    	cemTrx1.addTsDefectDef(cemSpec4);
    	
    	cemSpec3.setTsId(new Long("700000000000001097"));
    	cemSpec3.setTsAttributeId(new Long("1"));
    	cemSpec3.setTsCondition(null);
    	cemSpec3.setTsEnabled(Boolean.TRUE);
    	cemSpec3.setTsHeaderName("Name");
    	cemSpec3.setTsImportanceId(new Long("5"));
    	cemSpec3.setTsLocked(Boolean.FALSE);
    	cemSpec3.setTsName("High Throughput");
    	cemSpec3.setTsSoftDelete(Boolean.FALSE);
    	cemSpec3.setTsTranType(new Integer("3"));
    	cemSpec3.setTsType(new Integer("11"));
    	cemSpec3.setTsValue("10000");
    	cemSpec3.setVersionInfo(new Long("0"));

    	cemSpec4.setTsId(new Long("700000002000012397"));
    	cemSpec4.setTsAttributeId(new Long("12"));
    	cemSpec4.setTsCondition(new Integer("56"));
    	cemSpec4.setTsEnabled(Boolean.TRUE);
    	cemSpec4.setTsHeaderName(null);
    	cemSpec4.setTsImportanceId(new Long("50"));
    	cemSpec4.setTsLocked(Boolean.TRUE);
    	cemSpec4.setTsName("Fast Response");
    	cemSpec4.setTsSoftDelete(Boolean.FALSE);
    	cemSpec4.setTsTranType(new Integer("30"));
    	cemSpec4.setTsType(new Integer("111"));
    	cemSpec4.setTsValue("100000");
    	cemSpec4.setVersionInfo(new Long("123"));    	
    	
		cemTrxComp1.setTsId(new Long("700000000000000033"));
		cemTrxComp1.setTsCount(new Integer("123"));
		cemTrxComp1.setTsCreationDate(new Timestamp(new Date().getTime()));
		cemTrxComp1.setTsDescription("description...");
		cemTrxComp1.setTsIdentifying(Boolean.TRUE);
		cemTrxComp1.setTsIncluded(Boolean.TRUE);
		cemTrxComp1.setTsName("/sample/hello.jsp");
		cemTrxComp1.setTsOptional(Boolean.TRUE);
		cemTrxComp1.setTsPending(Boolean.FALSE);
		cemTrxComp1.setTsRecordingComponentId(new Long("700000000000066633"));
		cemTrxComp1.setTsSequenceNumber(new Integer("123"));
		cemTrxComp1.setTsSigmaSla(12.5f);
		cemTrxComp1.setTsSigmaSlaInherited(Boolean.TRUE);
		cemTrxComp1.setTsSoftDelete(Boolean.FALSE);
		cemTrxComp1.setTsSuccessRateSla(5.5f);
		cemTrxComp1.setTsSuccessSlaInherited(Boolean.FALSE);
		cemTrxComp1.setTsTranTimeSla(6);
		cemTrxComp1.setTsTranTimeSlaInherited(Boolean.TRUE);
		cemTrxComp1.setTsUrl("/a/b/c/d");
		cemTrxComp1.setVersionInfo(new Long("666"));
		cemTrxComp1.setTsDefectDefs(new Vector<CEMSpecification>());
		cemTrxComp1.setTsParams(new Vector<CEMTransactionComponentIdentification>());
		cemTrxComp1.addTsDefectDef(cemSpec5);
		cemTrxComp1.addTsDefectDef(cemSpec6);
		cemTrxComp1.addTsParam(cemTrxCompIdent1);
		cemTrxComp1.addTsParam(cemTrxCompIdent2);
    	
		
    	cemSpec5.setTsId(new Long("700000000000551097"));
    	cemSpec5.setTsAttributeId(new Long("1"));
    	cemSpec5.setTsCondition(null);
    	cemSpec5.setTsEnabled(Boolean.TRUE);
    	cemSpec5.setTsHeaderName("Name");
    	cemSpec5.setTsImportanceId(new Long("5"));
    	cemSpec5.setTsLocked(Boolean.FALSE);
    	cemSpec5.setTsName("High Throughput");
    	cemSpec5.setTsSoftDelete(Boolean.FALSE);
    	cemSpec5.setTsTranType(new Integer("3"));
    	cemSpec5.setTsType(new Integer("11"));
    	cemSpec5.setTsValue("10050");
    	cemSpec5.setVersionInfo(new Long("550"));

    	cemSpec6.setTsId(new Long("700000002006612397"));
    	cemSpec6.setTsAttributeId(new Long("12"));
    	cemSpec6.setTsCondition(new Integer("56"));
    	cemSpec6.setTsEnabled(Boolean.FALSE);
    	cemSpec6.setTsHeaderName("Name");
    	cemSpec6.setTsImportanceId(new Long("50"));
    	cemSpec6.setTsLocked(Boolean.TRUE);
    	cemSpec6.setTsName("Fast Response");
    	cemSpec6.setTsSoftDelete(Boolean.FALSE);
    	cemSpec6.setTsTranType(new Integer("30"));
    	cemSpec6.setTsType(new Integer("111"));
    	cemSpec6.setTsValue("100000");
    	cemSpec6.setVersionInfo(new Long("123"));    	
		
		
    	
    	cemTrxCompIdentPK1.setTsName("ClientIP");
    	cemTrxCompIdentPK1.setTsParamType("URL");
    	cemTrxCompIdentPK1.setTsTrancompId(new Long("700000000000000033"));
    	
    	cemTrxCompIdent1.setId(cemTrxCompIdentPK1);
    	cemTrxCompIdent1.setTsAutogenSequence(new Integer("2"));
    	cemTrxCompIdent1.setTsNameType(new Integer("0"));
    	cemTrxCompIdent1.setTsOperator(new Integer("1"));
    	cemTrxCompIdent1.setTsOriginalValue("192.168.128.1");
    	cemTrxCompIdent1.setTsPattern("192.168.128.1");
    	cemTrxCompIdent1.setTsSoftDelete(Boolean.TRUE);
    	cemTrxCompIdent1.setVersionInfo(new Long("123"));
    	
    	cemTrxCompIdentPK2.setTsName("Host");
    	cemTrxCompIdentPK2.setTsParamType("URL");
    	cemTrxCompIdentPK2.setTsTrancompId(new Long("700000000000000033"));
    	
    	
    	cemTrxCompIdent2.setId(cemTrxCompIdentPK2);
    	cemTrxCompIdent2.setTsAutogenSequence(new Integer("3"));
    	cemTrxCompIdent2.setTsNameType(new Integer("20"));
    	cemTrxCompIdent2.setTsOperator(new Integer("10"));
    	cemTrxCompIdent2.setTsOriginalValue("adambe.com");
    	cemTrxCompIdent2.setTsPattern("adambe.com");
		cemTrxCompIdent2.setTsSoftDelete(Boolean.FALSE);
		cemTrxCompIdent2.setVersionInfo(new Long("12356"));
    	
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
	//@Ignore
	public void CemTransactionComponentIdentificationPkToXmlAndBack(){
		
		String xml = XMLSerializer.objectToXML(cemTrxCompIdentPK1, CEMTransactionComponentIdentificationPK.class);
		log.info("object1 = "+xml);
		
		
        String xpathExpression = "/componentIdentificationPK/@ts_name";
        String actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdentPK1.getTsName()));
        
        xpathExpression = "/componentIdentificationPK/@ts_param_type";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdentPK1.getTsParamType()));
        
        xpathExpression = "/componentIdentificationPK/@ts_trancomp_id";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdentPK1.getTsTrancompId().toString()));

        
        CEMTransactionComponentIdentificationPK cemTrxCompIdentPK1Deser = (CEMTransactionComponentIdentificationPK)XMLSerializer.xmlToObject(xml, CEMTransactionComponentIdentificationPK.class);
        log.info("cemTrxCompIdentPK1Deser = "+cemTrxCompIdentPK1Deser);
        assertTrue(cemTrxCompIdentPK1.equals(cemTrxCompIdentPK1Deser));
        
	}
	
	@Test
	//@Ignore
	public void CemTransactionComponentIdentificationToXmlAndBack(){
		
		
		CEMTransactionComponentIdentificationAdapted foo = new CEMTransactionComponentIdentificationAdapted(cemTrxCompIdent1);
		
		String xml = XMLSerializer.objectToXML(foo, CEMTransactionComponentIdentificationAdapted.class);
		log.info("foo = "+xml);
		
		
        String xpathExpression = "/componentIdentification/@ts_name";
        String actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getId().getTsName()));
        
        xpathExpression = "/componentIdentification/@ts_param_type";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getId().getTsParamType()));
        
        xpathExpression = "/componentIdentification/@ts_trancomp_id";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getId().getTsTrancompId().toString()));

        xpathExpression = "/componentIdentification/@ts_autogen_sequence";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsAutogenSequence().toString()));

        xpathExpression = "/componentIdentification/@ts_name_type";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsNameType().toString()));

        xpathExpression = "/componentIdentification/@ts_operator";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsOperator().toString()));

        xpathExpression = "/componentIdentification/@ts_original_value";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsOriginalValue()));

        xpathExpression = "/componentIdentification/@ts_pattern";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsPattern()));

        xpathExpression = "/componentIdentification/@ts_soft_delete";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getTsSoftDelete().toString()));

        xpathExpression = "/componentIdentification/@version_info";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxCompIdent1.getVersionInfo().toString()));
        
		

		//this assertion will not work since parent transaction component will not be deserialized in fooDeser/cemTrxCompIdent1Deser
		//it can be deserialized only by respective deserialization adapter on higher level (i.e. in CEMTransactionComponentAdapter)
		//CEMTransactionComponentIdentificationAdapted fooDeser = (CEMTransactionComponentIdentificationAdapted) XMLSerializer.xmlToObject(xml, CEMTransactionComponentIdentificationAdapted.class);
        //CEMTransactionComponentIdentification cemTrxCompIdent1Deser = new CEMTransactionComponentIdentification(fooDeser);
		//assertTrue(cemTrxCompIdent1.equals(cemTrxCompIdent1Deser));
        
		
	}
	
	@Test
	//@Ignore
	public void CemTransactionComponentToXmlAndBack(){
		
		String xml = XMLSerializer.objectToXML(cemTrxComp1, CEMTransactionComponent.class);
		log.info("cemTrxComp1 = " + xml);
		
        String xpathExpression = "/component/@ts_recording_component_id";
        String actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxComp1.getTsRecordingComponentId().toString()));

        xpathExpression = "/component/@ts_url";
        actual = XMLSerializer.extractValue(xml, xpathExpression);
        assertThat(actual, is(cemTrxComp1.getTsUrl()));
        
		
		CEMTransactionComponent cemTrxComp1Deser = (CEMTransactionComponent)XMLSerializer.xmlToObject(xml, CEMTransactionComponent.class);
		
		log.info("cemTrxComp1Deser = " + cemTrxComp1Deser);
		//will not work deserialized transaction component does not contain back pointer to transaction
		//assertTrue(cemTrxComp1.equals(cemTrxComp1Deser));
		
		//back pointer CEMTransactionComponentIdentification.tsTrancomp will not be set since
		//it is populated by CEMTransactionComponentAdapter which will be called only on higher
		//level, i.e. on level of CEMTransaction when deserializing underlying components!
		assertTrue(cemTrxComp1Deser.getTsParams().get(0).getTsTrancomp()==null);
		assertTrue(cemTrxComp1Deser.getTsParams().get(1).getTsTrancomp()==null);
		
		//..same as above is valid for defect definitions
		assertTrue(cemTrxComp1Deser.getTsDefectDefs().get(0).getTsTrancomp()==null);
		assertTrue(cemTrxComp1Deser.getTsDefectDefs().get(1).getTsTrancomp()==null);
		
	}
	
	
	@Test
	//@Ignore
	public void CemTransactionToXmlAndBack(){
		
		String xml = XMLSerializer.objectToXML(cemTrx1, CEMTransaction.class);
		log.info(xml);
		
		
		CEMTransaction cemTrx1Deser = (CEMTransaction)XMLSerializer.xmlToObject(xml, CEMTransaction.class);		
		
		assertTrue(cemTrx1Deser.getTsTrancomps().size()==1);
		CEMTransactionComponent comp = cemTrx1Deser.getTsTrancomps().get(0);
		CEMSpecification spec= cemTrx1Deser.getTsDefectDefs().get(0);
				
		assertTrue(comp.getTsTranunit()==null);//this will be set on higher level
		assertTrue(spec.getTsTranunit()==null);
		
		
		//these back pointers are already set properly by CEMTransactionComponentAdapter
		assertTrue(comp.getTsParams().get(0).getTsTrancomp()==comp);
		assertTrue(comp.getTsParams().get(1).getTsTrancomp()==comp);
		
		assertTrue(comp.getTsDefectDefs().get(0).getTsTrancomp()==comp);
		assertTrue(comp.getTsDefectDefs().get(1).getTsTrancomp()==comp);
		
		
	}
	
	@Test
	//@Ignore
	public void CemBusinessTransactionToXmlAndBack(){
		
		String xml = XMLSerializer.objectToXML(cemBusinessTrx1, CEMBusinessTransaction.class);
		log.info(xml);
		
		CEMBusinessTransaction cemBusinessTrx1Deser = (CEMBusinessTransaction)XMLSerializer.xmlToObject(xml, CEMBusinessTransaction.class);
		
		CEMTransactionComponent comp = cemBusinessTrx1Deser.getTsTranunits().get(0).getTsTrancomps().get(0);
		CEMSpecification spec = cemBusinessTrx1Deser.getTsTranunits().get(0).getTsDefectDefs().get(0);
		
		assertTrue(comp.getTsTranunit() == cemBusinessTrx1Deser.getTsTranunits().get(0));//back pointers are set properly during deserialization
		assertTrue(spec.getTsTranunit() == cemBusinessTrx1Deser.getTsTranunits().get(0));
		
		assertTrue(cemBusinessTrx1Deser.getTsTranunits().get(0).getTsTranset()==null);
		assertTrue(cemBusinessTrx1Deser.getTsDefectDefs().get(0).getTsTranset()==null);
		
		//we will render transet maps on business service level only
		//assertTrue(cemBusinessTrx1Deser.getTsTransetgroupTransetsMaps().get(0).getTsTranset()==null);
		
	}

	@Test
	//@Ignore
	public void CemBusinessServiceToXmlAndBack(){
		
		String xml = XMLSerializer.objectToXML(cemBusinessSvc, CEMBusinessService.class);
		log.info(xml);
		
		CEMBusinessService cemBusinessSvcDeser = (CEMBusinessService)XMLSerializer.xmlToObject(xml, CEMBusinessService.class);
		CEMBusinessTransaction btran = cemBusinessSvcDeser.getTsTransets().get(0);
		
		//check back pointers
		assertTrue(btran.getTsTranunits().get(0).getTsTranset() == btran);
		assertTrue(btran.getTsDefectDefs().get(0).getTsTranset() == btran);
		
		assertTrue(cemBusinessSvcDeser.getTsTransets().get(0).getTsTranDefGroup()==null);
		assertTrue(cemBusinessSvcDeser.getTsTransetGroups().get(0).getTsTranDefGroup()==null);
		
	}
	
	@Test
	//@Ignore
	public void CemBusinessApplicationToXmlAndBack(){
		String xml = XMLSerializer.objectToXML(cemBusinessApp, CEMBusinessApplication.class);
		log.info(xml);
		
		CEMBusinessApplication cemBusinessAppDeser = (CEMBusinessApplication)XMLSerializer.xmlToObject(xml, CEMBusinessApplication.class);
		log.info(cemBusinessAppDeser);
		
		CEMBusinessService cemBusinessSvcDeser =  cemBusinessAppDeser.getTsTranDefGroups().get(0);
		
		assertTrue(cemBusinessSvcDeser.getTsTransets().get(0).getTsTranDefGroup() == cemBusinessSvcDeser);
		//assertTrue(cemBusinessSvcDeser.getTsTransetGroups().get(0).getTsTranDefGroup() == cemBusinessSvcDeser);
		
	}
	
	
}
