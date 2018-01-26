package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessApplicationAdapter;

import java.util.List;


/**
 * The persistent class for the ts_apps database table.
 * 
 */
@Entity
@Table(name="ts_apps")
@NamedQuery(name="CEMBusinessApplication.findByName", query="SELECT c FROM CEMBusinessApplication c WHERE c.tsSoftDelete=false AND c.tsName=:baName")
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.CEMBusinessApplicationCustomizer.class)
@XmlRootElement(name="businessApplication")
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(CEMBusinessApplicationAdapter.class)
public class CEMBusinessApplication extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMBusinessApplication.class.getName());
	

	@Id
	@SequenceGenerator(name="TS_APPS_TSID_GENERATOR", sequenceName="TS_APPS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_APPS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_apptype_id")
	@XmlAttribute(name="ts_apptype_id")
	private Long tsApptypeId;

	@Column(name="ts_authtype_id")
	@XmlAttribute(name="ts_authtype_id")
	private Long tsAuthtypeId;

	@Column(name="ts_case_senstv_cookie_name")
	@XmlAttribute(name="ts_case_senstv_cookie_name")
	private Boolean tsCaseSenstvCookieName;

	@Column(name="ts_case_senstv_cookie_value")
	@XmlAttribute(name="ts_case_senstv_cookie_value")
	private Boolean tsCaseSenstvCookieValue;

	@Column(name="ts_case_senstv_login_name")
	@XmlAttribute(name="ts_case_senstv_login_name")
	private Boolean tsCaseSenstvLoginName;

	@Column(name="ts_case_senstv_post_name")
	@XmlAttribute(name="ts_case_senstv_post_name")
	private Boolean tsCaseSenstvPostName;

	@Column(name="ts_case_senstv_post_value")
	@XmlAttribute(name="ts_case_senstv_post_value")
	private Boolean tsCaseSenstvPostValue;

	@Column(name="ts_case_senstv_query_name")
	@XmlAttribute(name="ts_case_senstv_query_name")
	private Boolean tsCaseSenstvQueryName;

	@Column(name="ts_case_senstv_query_value")
	@XmlAttribute(name="ts_case_senstv_query_value")
	private Boolean tsCaseSenstvQueryValue;

	@Column(name="ts_case_senstv_url_host")
	@XmlAttribute(name="ts_case_senstv_url_host")
	private Boolean tsCaseSenstvUrlHost;

	@Column(name="ts_case_senstv_url_path")
	@XmlAttribute(name="ts_case_senstv_url_path")
	private Boolean tsCaseSenstvUrlPath;

	@Column(name="ts_charset")
	@XmlAttribute(name="ts_charset")
	private String tsCharset;

	@Column(name="ts_description")
	@XmlAttribute(name="ts_description")
	private String tsDescription;

	@Column(name="ts_domain_id")
	@XmlAttribute(name="ts_domain_id")
	private Long tsDomainId;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

	@Column(name="ts_session_timeout")
	@XmlAttribute(name="ts_session_timeout")
	private Integer tsSessionTimeout;

	@Column(name="ts_soft_delete")
	@XmlAttribute(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="ts_user_processing_type")
	@XmlAttribute(name="ts_user_processing_type")
	private Integer tsUserProcessingType;

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMBusinessService
	@OneToMany(mappedBy="tsApp", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="businessServices")
	@XmlElement(name = "item")
	private List<CEMBusinessService> tsTranDefGroups;

	//bi-directional many-to-one association to CEMBusinessTransaction
	//no cascading defined here. business transactions are cascaded from respective business services
	@OneToMany(mappedBy="tsApp", fetch=FetchType.EAGER)
	@XmlTransient//business transactions are rendered within business services
	private List<CEMBusinessTransaction> tsTransets;

	//bi-directional many-to-one association to CEMBusinessApplicationUserIdentification
	@OneToMany(mappedBy="tsApp", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlTransient
	private List<CEMBusinessApplicationUserIdentification> tsLoginIdParameters;

	//bi-directional many-to-one association to CEMBusinessApplicationSessionIdentification
	@OneToMany(mappedBy="tsApp", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlTransient
	private List<CEMBusinessApplicationSessionIdentification> tsSessionIdParameters;

	//bi-directional many-to-one association to CEMBusinessApplicationUserGroupIdentification
	@OneToMany(mappedBy="tsApp", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlTransient
	private List<CEMBusinessApplicationUserGroupIdentification> tsUsergroupIdParameters;

	
	public void cutOffChildNodes(){
		this.tsTranDefGroups = null;
		this.tsTransets = null;
		this.tsLoginIdParameters = null;
		this.tsSessionIdParameters = null;
		this.tsUsergroupIdParameters = null;	
	}
	
	
	public CEMBusinessApplication() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Long getTsApptypeId() {
		return this.tsApptypeId;
	}

	public void setTsApptypeId(Long tsApptypeId) {
		this.tsApptypeId = tsApptypeId;
	}

	public Long getTsAuthtypeId() {
		return this.tsAuthtypeId;
	}

	public void setTsAuthtypeId(Long tsAuthtypeId) {
		this.tsAuthtypeId = tsAuthtypeId;
	}

	public Boolean getTsCaseSenstvCookieName() {
		return this.tsCaseSenstvCookieName;
	}

	public void setTsCaseSenstvCookieName(Boolean tsCaseSenstvCookieName) {
		this.tsCaseSenstvCookieName = tsCaseSenstvCookieName;
	}

	public Boolean getTsCaseSenstvCookieValue() {
		return this.tsCaseSenstvCookieValue;
	}

	public void setTsCaseSenstvCookieValue(Boolean tsCaseSenstvCookieValue) {
		this.tsCaseSenstvCookieValue = tsCaseSenstvCookieValue;
	}

	public Boolean getTsCaseSenstvLoginName() {
		return this.tsCaseSenstvLoginName;
	}

	public void setTsCaseSenstvLoginName(Boolean tsCaseSenstvLoginName) {
		this.tsCaseSenstvLoginName = tsCaseSenstvLoginName;
	}

	public Boolean getTsCaseSenstvPostName() {
		return this.tsCaseSenstvPostName;
	}

	public void setTsCaseSenstvPostName(Boolean tsCaseSenstvPostName) {
		this.tsCaseSenstvPostName = tsCaseSenstvPostName;
	}

	public Boolean getTsCaseSenstvPostValue() {
		return this.tsCaseSenstvPostValue;
	}

	public void setTsCaseSenstvPostValue(Boolean tsCaseSenstvPostValue) {
		this.tsCaseSenstvPostValue = tsCaseSenstvPostValue;
	}

	public Boolean getTsCaseSenstvQueryName() {
		return this.tsCaseSenstvQueryName;
	}

	public void setTsCaseSenstvQueryName(Boolean tsCaseSenstvQueryName) {
		this.tsCaseSenstvQueryName = tsCaseSenstvQueryName;
	}

	public Boolean getTsCaseSenstvQueryValue() {
		return this.tsCaseSenstvQueryValue;
	}

	public void setTsCaseSenstvQueryValue(Boolean tsCaseSenstvQueryValue) {
		this.tsCaseSenstvQueryValue = tsCaseSenstvQueryValue;
	}

	public Boolean getTsCaseSenstvUrlHost() {
		return this.tsCaseSenstvUrlHost;
	}

	public void setTsCaseSenstvUrlHost(Boolean tsCaseSenstvUrlHost) {
		this.tsCaseSenstvUrlHost = tsCaseSenstvUrlHost;
	}

	public Boolean getTsCaseSenstvUrlPath() {
		return this.tsCaseSenstvUrlPath;
	}

	public void setTsCaseSenstvUrlPath(Boolean tsCaseSenstvUrlPath) {
		this.tsCaseSenstvUrlPath = tsCaseSenstvUrlPath;
	}

	public String getTsCharset() {
		return this.tsCharset;
	}

	public void setTsCharset(String tsCharset) {
		this.tsCharset = tsCharset;
	}

	public String getTsDescription() {
		return this.tsDescription;
	}

	public void setTsDescription(String tsDescription) {
		this.tsDescription = tsDescription;
	}

	public Long getTsDomainId() {
		return this.tsDomainId;
	}

	public void setTsDomainId(Long tsDomainId) {
		this.tsDomainId = tsDomainId;
	}

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Integer getTsSessionTimeout() {
		return this.tsSessionTimeout;
	}

	public void setTsSessionTimeout(Integer tsSessionTimeout) {
		this.tsSessionTimeout = tsSessionTimeout;
	}

	public Boolean getTsSoftDelete() {
		return this.tsSoftDelete;
	}

	@Override
	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public Integer getTsUserProcessingType() {
		return this.tsUserProcessingType;
	}

	public void setTsUserProcessingType(Integer tsUserProcessingType) {
		this.tsUserProcessingType = tsUserProcessingType;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public List<CEMBusinessService> getTsTranDefGroups() {
		return this.tsTranDefGroups;
	}

	public void setTsTranDefGroups(List<CEMBusinessService> tsTranDefGroups) {
		this.tsTranDefGroups = tsTranDefGroups;
	}

	public CEMBusinessService addTsTranDefGroup(CEMBusinessService tsTranDefGroup) {
		getTsTranDefGroups().add(tsTranDefGroup);
		tsTranDefGroup.setTsApp(this);

		return tsTranDefGroup;
	}

	public CEMBusinessService removeTsTranDefGroup(CEMBusinessService tsTranDefGroup) {
		getTsTranDefGroups().remove(tsTranDefGroup);
		tsTranDefGroup.setTsApp(null);

		return tsTranDefGroup;
	}

	public List<CEMBusinessTransaction> getTsTransets() {
		return this.tsTransets;
	}

	public void setTsTransets(List<CEMBusinessTransaction> tsTransets) {
		this.tsTransets = tsTransets;
	}

	public CEMBusinessTransaction addTsTranset(CEMBusinessTransaction tsTranset) {
		getTsTransets().add(tsTranset);
		tsTranset.setTsApp(this);

		return tsTranset;
	}

	public CEMBusinessTransaction removeTsTranset(CEMBusinessTransaction tsTranset) {
		getTsTransets().remove(tsTranset);
		tsTranset.setTsApp(null);

		return tsTranset;
	}

	public List<CEMBusinessApplicationUserIdentification> getTsLoginIdParameters() {
		return this.tsLoginIdParameters;
	}

	public void setTsLoginIdParameters(List<CEMBusinessApplicationUserIdentification> tsLoginIdParameters) {
		this.tsLoginIdParameters = tsLoginIdParameters;
	}

	public CEMBusinessApplicationUserIdentification addTsLoginIdParameter(CEMBusinessApplicationUserIdentification tsLoginIdParameter) {
		getTsLoginIdParameters().add(tsLoginIdParameter);
		tsLoginIdParameter.setTsApp(this);

		return tsLoginIdParameter;
	}

	public CEMBusinessApplicationUserIdentification removeTsLoginIdParameter(CEMBusinessApplicationUserIdentification tsLoginIdParameter) {
		getTsLoginIdParameters().remove(tsLoginIdParameter);
		tsLoginIdParameter.setTsApp(null);

		return tsLoginIdParameter;
	}

	public List<CEMBusinessApplicationSessionIdentification> getTsSessionIdParameters() {
		return this.tsSessionIdParameters;
	}

	public void setTsSessionIdParameters(List<CEMBusinessApplicationSessionIdentification> tsSessionIdParameters) {
		this.tsSessionIdParameters = tsSessionIdParameters;
	}

	public CEMBusinessApplicationSessionIdentification addTsSessionIdParameter(CEMBusinessApplicationSessionIdentification tsSessionIdParameter) {
		getTsSessionIdParameters().add(tsSessionIdParameter);
		tsSessionIdParameter.setTsApp(this);

		return tsSessionIdParameter;
	}

	public CEMBusinessApplicationSessionIdentification removeTsSessionIdParameter(CEMBusinessApplicationSessionIdentification tsSessionIdParameter) {
		getTsSessionIdParameters().remove(tsSessionIdParameter);
		tsSessionIdParameter.setTsApp(null);

		return tsSessionIdParameter;
	}

	public List<CEMBusinessApplicationUserGroupIdentification> getTsUsergroupIdParameters() {
		return this.tsUsergroupIdParameters;
	}

	public void setTsUsergroupIdParameters(List<CEMBusinessApplicationUserGroupIdentification> tsUsergroupIdParameters) {
		this.tsUsergroupIdParameters = tsUsergroupIdParameters;
	}

	public CEMBusinessApplicationUserGroupIdentification addTsUsergroupIdParameter(CEMBusinessApplicationUserGroupIdentification tsUsergroupIdParameter) {
		getTsUsergroupIdParameters().add(tsUsergroupIdParameter);
		tsUsergroupIdParameter.setTsApp(this);

		return tsUsergroupIdParameter;
	}

	public CEMBusinessApplicationUserGroupIdentification removeTsUsergroupIdParameter(CEMBusinessApplicationUserGroupIdentification tsUsergroupIdParameter) {
		getTsUsergroupIdParameters().remove(tsUsergroupIdParameter);
		tsUsergroupIdParameter.setTsApp(null);

		return tsUsergroupIdParameter;
	}

	@Override
	public String toString() {
		return "CEMBusinessApplication [tsId=" + tsId + ", tsApptypeId="
				+ tsApptypeId + ", tsAuthtypeId=" + tsAuthtypeId
				+ ", tsCaseSenstvCookieName=" + tsCaseSenstvCookieName
				+ ", tsCaseSenstvCookieValue=" + tsCaseSenstvCookieValue
				+ ", tsCaseSenstvLoginName=" + tsCaseSenstvLoginName
				+ ", tsCaseSenstvPostName=" + tsCaseSenstvPostName
				+ ", tsCaseSenstvPostValue=" + tsCaseSenstvPostValue
				+ ", tsCaseSenstvQueryName=" + tsCaseSenstvQueryName
				+ ", tsCaseSenstvQueryValue=" + tsCaseSenstvQueryValue
				+ ", tsCaseSenstvUrlHost=" + tsCaseSenstvUrlHost
				+ ", tsCaseSenstvUrlPath=" + tsCaseSenstvUrlPath
				+ ", tsCharset=" + tsCharset + ", tsDescription="
				+ tsDescription + ", tsDomainId=" + tsDomainId + ", tsName="
				+ tsName + ", tsSessionTimeout=" + tsSessionTimeout
				+ ", tsSoftDelete=" + tsSoftDelete + ", tsUserProcessingType="
				+ tsUserProcessingType + ", versionInfo=" + versionInfo
				+ ", tsTranDefGroups=" + tsTranDefGroups + ", tsTransets="
				+ tsTransets + ", tsLoginIdParameters=" + tsLoginIdParameters
				+ ", tsSessionIdParameters=" + tsSessionIdParameters
				+ ", tsUsergroupIdParameters=" + tsUsergroupIdParameters + "]";
	}

	@Override
	public String getFQN() {
		return this.tsName;
	}
	
	
	@Override
	public void copyTo(CEMEntity target) throws Exception {
		
		CEMBusinessApplication _target = (CEMBusinessApplication)target;
		
		log.entry(this, target);
		
		_target.setTsApptypeId(this.getTsApptypeId());
		_target.setTsAuthtypeId(this.getTsAuthtypeId());
		_target.setTsCaseSenstvCookieName(this.getTsCaseSenstvCookieName());
		_target.setTsCaseSenstvCookieValue(this.getTsCaseSenstvCookieValue());
		_target.setTsCaseSenstvLoginName(this.getTsCaseSenstvLoginName());
		_target.setTsCaseSenstvPostName(this.getTsCaseSenstvPostName());
		_target.setTsCaseSenstvPostValue(this.getTsCaseSenstvPostValue());
		_target.setTsCaseSenstvQueryName(this.getTsCaseSenstvQueryName());
		_target.setTsCaseSenstvQueryValue(this.getTsCaseSenstvQueryValue());
		_target.setTsCaseSenstvUrlHost(this.getTsCaseSenstvUrlHost());
		_target.setTsCaseSenstvUrlPath(this.getTsCaseSenstvUrlPath());
		_target.setTsCharset(this.getTsCharset());
		_target.setTsDescription(this.getTsDescription());
		_target.setTsDomainId(this.getTsDomainId());
		_target.setTsName(this.getTsName());
		_target.setTsSessionTimeout(this.getTsSessionTimeout());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsUserProcessingType(this.getTsUserProcessingType());
		_target.setVersionInfo(this.getVersionInfo());		
		
		new CEMEntityMergeHelper<CEMBusinessApplication, CEMBusinessService>().mergeChildEntityLists(_target, this.getTsTranDefGroups(), _target.getTsTranDefGroups());
		
	    log.exit(target);
		
	}

	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		throw new UnsupportedOperationException("This method must be not called for CEMBusinessApplication since this is top level entity with no parent!");
	}


	@Override
	public CEMEntity deepCopy() throws Exception {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		CEMBusinessApplication clone = (CEMBusinessApplication)super.deepCopy(); 

		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		
		for(CEMBusinessService itemBS : clone.tsTranDefGroups){
			itemBS.setTsId(null);
			itemBS.getTsTransetGroups().clear();

			
			for(CEMBusinessTransaction itemBT : itemBS.getTsTransets()){
				itemBT.setTsId(null);
				itemBT.isClone = true;
				itemBT.getTsTransetgroupTransetsMaps().clear();
				
				for(CEMSpecification itemSpecBT : itemBT.getTsDefectDefs()){
					itemSpecBT.setTsId(null);
				}
				
				
				for(CEMTransaction itemTR : itemBT.getTsTranunits()){
					itemTR.setTsId(null);
					
					for(CEMSpecification itemSpecTR : itemTR.getTsDefectDefs()){
						itemSpecTR.setTsId(null);
					}
					
					for(CEMTransactionComponent itemTC : itemTR.getTsTrancomps()){
						itemTC.setTsId(null);
						itemTC.setTsRecordingComponentId(null);
						
						for(CEMSpecification itemSpecTC : itemTC.getTsDefectDefs()){
							itemSpecTC.setTsId(null);
						}
						
						for(CEMTransactionComponentIdentification itemTCI : itemTC.getTsParams()){
							itemTCI.getId().setTsTrancompId(null);
						}
						
					}
					
				}
				
			}

		}
		
		
		return clone;
		*/
	}
	
}