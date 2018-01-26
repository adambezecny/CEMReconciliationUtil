package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessTransactionAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.TsTransetgroupTransetsMapAdapter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.TimestampAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.util.DbUtil;
//import javax.xml.bind.annotation.XmlTransient; 

/**
 * The persistent class for the ts_transets database table.
 * 
 */
@Entity
@Table(name="ts_transets")
@NamedQuery(name="CEMBusinessTransaction.findByName", query="SELECT c FROM CEMBusinessTransaction c WHERE c.tsSoftDelete=false AND c.tsName=:btName AND c.tsApp.tsName=:baName AND c.tsTranDefGroup.tsName=:bsName")
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.CEMBusinessTransactionCustomizer.class)
@XmlRootElement(name="businessTransaction")
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(CEMBusinessTransactionAdapter.class)
public class CEMBusinessTransaction extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMBusinessTransaction.class.getName());


	@Id
	@SequenceGenerator(name="TS_TRANSETS_TSID_GENERATOR", sequenceName="TS_TRANSETS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_TRANSETS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_app_id_inherited")
	@XmlAttribute(name="ts_app_id_inherited")
	private Boolean tsAppIdInherited;

	@Column(name="ts_autogen_request_count")
	@XmlAttribute(name="ts_autogen_request_count")
	private Long tsAutogenRequestCount;

	@Column(name="ts_autogen_template_id")
	@XmlAttribute(name="ts_autogen_template_id")
	private Long tsAutogenTemplateId;

	@Column(name="ts_business_value")
	@XmlAttribute(name="ts_business_value")
	private BigDecimal tsBusinessValue;

	@Column(name="ts_business_value_inherited")
	@XmlAttribute(name="ts_business_value_inherited")
	private Boolean tsBusinessValueInherited;

	@Column(name="ts_collect_tranunit_stats")
	@XmlAttribute(name="ts_collect_tranunit_stats")
	private Boolean tsCollectTranunitStats;

	@Column(name="ts_creation_date")
	@XmlAttribute(name="ts_creation_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp tsCreationDate;

	@Column(name="ts_creator_id")
	@XmlAttribute(name="ts_creator_id")
	private Long tsCreatorId;

	@Column(name="ts_creator_name")
	@XmlAttribute(name="ts_creator_name")
	private String tsCreatorName;

	@Column(name="ts_description")
	@XmlAttribute(name="ts_description")
	private String tsDescription;

	@Column(name="ts_enabled")
	@XmlAttribute(name="ts_enabled")
	private Boolean tsEnabled;

	@Column(name="ts_importance_id")
	@XmlAttribute(name="ts_importance_id")
	private Long tsImportanceId;

	@Column(name="ts_importance_inherited")
	@XmlAttribute(name="ts_importance_inherited")
	private Boolean tsImportanceInherited;

	@Column(name="ts_incarnation_id")
	@XmlAttribute(name="ts_incarnation_id")
	private Long tsIncarnationId;

	@Column(name="ts_iscp_agnt_mtr_all_comp")
	@XmlAttribute(name="ts_iscp_agnt_mtr_all_comp")
	private Integer tsIscpAgntMtrAllComp;

	@Column(name="ts_it_value")
	@XmlAttribute(name="ts_it_value")
	private BigDecimal tsItValue;

	@Column(name="ts_it_value_inherited")
	@XmlAttribute(name="ts_it_value_inherited")
	private Boolean tsItValueInherited;

	@Column(name="ts_last_capture_date")
	@XmlAttribute(name="ts_last_capture_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp tsLastCaptureDate;

	@Column(name="ts_last_modified_date")
	@XmlAttribute(name="ts_last_modified_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	private Timestamp tsLastModifiedDate;

	@Column(name="ts_last_modifier_id")
	@XmlAttribute(name="ts_last_modifier_id")
	private Long tsLastModifierId;

	@Column(name="ts_last_modifier_name")
	@XmlAttribute(name="ts_last_modifier_name")
	private String tsLastModifierName;

	@Column(name="ts_matching_transet_id")
	@XmlAttribute(name="ts_matching_transet_id")
	private Long tsMatchingTransetId;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

	@Column(name="ts_request_base_id")
	@XmlAttribute(name="ts_request_base_id")
	private Long tsRequestBaseId;

	@Column(name="ts_sigma_sla")
	@XmlAttribute(name="ts_sigma_sla")
	private float tsSigmaSla;

	@Column(name="ts_sigma_sla_inherited")
	@XmlAttribute(name="ts_sigma_sla_inherited")
	private Boolean tsSigmaSlaInherited;

	@Column(name="ts_soft_delete")
	@XmlAttribute(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="ts_success_rate_sla")
	@XmlAttribute(name="ts_success_rate_sla")
	private float tsSuccessRateSla;

	@Column(name="ts_success_sla_inherited")
	@XmlAttribute(name="ts_success_sla_inherited")
	private Boolean tsSuccessSlaInherited;

	@Column(name="ts_total_captures")
	@XmlAttribute(name="ts_total_captures")
	private Long tsTotalCaptures;

	@Column(name="ts_tran_time_sla")
	@XmlAttribute(name="ts_tran_time_sla")
	private Integer tsTranTimeSla;

	@Column(name="ts_tran_time_sla_inherited")
	@XmlAttribute(name="ts_tran_time_sla_inherited")
	private Boolean tsTranTimeSlaInherited;

	@Column(name="ts_transet_type")
	@XmlAttribute(name="ts_transet_type")
	private Integer tsTransetType;

	@Column(name="ts_user_minute_cost")
	@XmlAttribute(name="ts_user_minute_cost")
	private BigDecimal tsUserMinuteCost;

	@Column(name="ts_user_minute_cost_inherited")
	@XmlAttribute(name="ts_user_minute_cost_inherited")
	private Boolean tsUserMinuteCostInherited;

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMBusinessApplication
	@ManyToOne
	@JoinColumn(name="ts_app_id")
	@XmlInverseReference(mappedBy="tsTransets")
	private CEMBusinessApplication tsApp;

	//bi-directional many-to-one association to CEMBusinessService
	@ManyToOne
	@JoinColumn(name="ts_trandef_group_id")
	@XmlInverseReference(mappedBy="tsTransets")
	private CEMBusinessService tsTranDefGroup;

	//bi-directional many-to-one association to CEMTransaction
	@OneToMany(mappedBy="tsTranset", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="transactions")
	@XmlElement(name = "item")
	private List<CEMTransaction> tsTranunits;

	//bi-directional many-to-one association to CEMSpecification
	@OneToMany(mappedBy="tsTranset", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="cemSpecification")
	@XmlElement(name = "item")
	private List<CEMSpecification> tsDefectDefs;

	
	//bi-directional many-to-one association to TsTransetgroupTransetsMap
	@OneToMany(mappedBy="tsTranset", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlElementWrapper(name="tsTransetgroupTransetsMaps")
	@XmlElement(name = "item")
	@XmlJavaTypeAdapter(TsTransetgroupTransetsMapAdapter.class)
	//@XmlTransient //transetMaps are rendered on CEMBusinessService level
	private List<TsTransetgroupTransetsMap> tsTransetgroupTransetsMaps;
	
	@Transient
	protected boolean isClone = false;
	
	public void cutOffChildNodes(){
		this.tsTranunits = null;
		this.tsDefectDefs = null;
		this.tsTransetgroupTransetsMaps = null;
	}
	
	
	public CEMBusinessTransaction() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Boolean getTsAppIdInherited() {
		return this.tsAppIdInherited;
	}

	public void setTsAppIdInherited(Boolean tsAppIdInherited) {
		this.tsAppIdInherited = tsAppIdInherited;
	}

	public Long getTsAutogenRequestCount() {
		return this.tsAutogenRequestCount;
	}

	public void setTsAutogenRequestCount(Long tsAutogenRequestCount) {
		this.tsAutogenRequestCount = tsAutogenRequestCount;
	}

	public Long getTsAutogenTemplateId() {
		return this.tsAutogenTemplateId;
	}

	public void setTsAutogenTemplateId(Long tsAutogenTemplateId) {
		this.tsAutogenTemplateId = tsAutogenTemplateId;
	}

	public BigDecimal getTsBusinessValue() {
		return this.tsBusinessValue;
	}

	public void setTsBusinessValue(BigDecimal tsBusinessValue) {
		this.tsBusinessValue = tsBusinessValue;
	}

	public Boolean getTsBusinessValueInherited() {
		return this.tsBusinessValueInherited;
	}

	public void setTsBusinessValueInherited(Boolean tsBusinessValueInherited) {
		this.tsBusinessValueInherited = tsBusinessValueInherited;
	}

	public Boolean getTsCollectTranunitStats() {
		return this.tsCollectTranunitStats;
	}

	public void setTsCollectTranunitStats(Boolean tsCollectTranunitStats) {
		this.tsCollectTranunitStats = tsCollectTranunitStats;
	}

	public Timestamp getTsCreationDate() {
		return this.tsCreationDate;
	}

	public void setTsCreationDate(Timestamp tsCreationDate) {
		this.tsCreationDate = tsCreationDate;
	}

	public Long getTsCreatorId() {
		return this.tsCreatorId;
	}

	public void setTsCreatorId(Long tsCreatorId) {
		this.tsCreatorId = tsCreatorId;
	}

	public String getTsCreatorName() {
		return this.tsCreatorName;
	}

	public void setTsCreatorName(String tsCreatorName) {
		this.tsCreatorName = tsCreatorName;
	}

	public String getTsDescription() {
		return this.tsDescription;
	}

	public void setTsDescription(String tsDescription) {
		this.tsDescription = tsDescription;
	}

	public Boolean getTsEnabled() {
		return this.tsEnabled;
	}

	public void setTsEnabled(Boolean tsEnabled) {
		this.tsEnabled = tsEnabled;
	}

	public Long getTsImportanceId() {
		return this.tsImportanceId;
	}

	public void setTsImportanceId(Long tsImportanceId) {
		this.tsImportanceId = tsImportanceId;
	}

	public Boolean getTsImportanceInherited() {
		return this.tsImportanceInherited;
	}

	public void setTsImportanceInherited(Boolean tsImportanceInherited) {
		this.tsImportanceInherited = tsImportanceInherited;
	}

	public Long getTsIncarnationId() {
		return this.tsIncarnationId;
	}

	public void setTsIncarnationId(Long tsIncarnationId) {
		this.tsIncarnationId = tsIncarnationId;
	}

	public Integer getTsIscpAgntMtrAllComp() {
		return this.tsIscpAgntMtrAllComp;
	}

	public void setTsIscpAgntMtrAllComp(Integer tsIscpAgntMtrAllComp) {
		this.tsIscpAgntMtrAllComp = tsIscpAgntMtrAllComp;
	}

	public BigDecimal getTsItValue() {
		return this.tsItValue;
	}

	public void setTsItValue(BigDecimal tsItValue) {
		this.tsItValue = tsItValue;
	}

	public Boolean getTsItValueInherited() {
		return this.tsItValueInherited;
	}

	public void setTsItValueInherited(Boolean tsItValueInherited) {
		this.tsItValueInherited = tsItValueInherited;
	}

	public Timestamp getTsLastCaptureDate() {
		return this.tsLastCaptureDate;
	}

	public void setTsLastCaptureDate(Timestamp tsLastCaptureDate) {
		this.tsLastCaptureDate = tsLastCaptureDate;
	}

	public Timestamp getTsLastModifiedDate() {
		return this.tsLastModifiedDate;
	}

	public void setTsLastModifiedDate(Timestamp tsLastModifiedDate) {
		this.tsLastModifiedDate = tsLastModifiedDate;
	}

	public Long getTsLastModifierId() {
		return this.tsLastModifierId;
	}

	public void setTsLastModifierId(Long tsLastModifierId) {
		this.tsLastModifierId = tsLastModifierId;
	}

	public String getTsLastModifierName() {
		return this.tsLastModifierName;
	}

	public void setTsLastModifierName(String tsLastModifierName) {
		this.tsLastModifierName = tsLastModifierName;
	}

	public Long getTsMatchingTransetId() {
		return this.tsMatchingTransetId;
	}

	public void setTsMatchingTransetId(Long tsMatchingTransetId) {
		this.tsMatchingTransetId = tsMatchingTransetId;
	}

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Long getTsRequestBaseId() {
		return this.tsRequestBaseId;
	}

	public void setTsRequestBaseId(Long tsRequestBaseId) {
		this.tsRequestBaseId = tsRequestBaseId;
	}

	public float getTsSigmaSla() {
		return this.tsSigmaSla;
	}

	public void setTsSigmaSla(float tsSigmaSla) {
		this.tsSigmaSla = tsSigmaSla;
	}

	public Boolean getTsSigmaSlaInherited() {
		return this.tsSigmaSlaInherited;
	}

	public void setTsSigmaSlaInherited(Boolean tsSigmaSlaInherited) {
		this.tsSigmaSlaInherited = tsSigmaSlaInherited;
	}

	public Boolean getTsSoftDelete() {
		return this.tsSoftDelete;
	}

	@Override
	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public float getTsSuccessRateSla() {
		return this.tsSuccessRateSla;
	}

	public void setTsSuccessRateSla(float tsSuccessRateSla) {
		this.tsSuccessRateSla = tsSuccessRateSla;
	}

	public Boolean getTsSuccessSlaInherited() {
		return this.tsSuccessSlaInherited;
	}

	public void setTsSuccessSlaInherited(Boolean tsSuccessSlaInherited) {
		this.tsSuccessSlaInherited = tsSuccessSlaInherited;
	}

	public Long getTsTotalCaptures() {
		return this.tsTotalCaptures;
	}

	public void setTsTotalCaptures(Long tsTotalCaptures) {
		this.tsTotalCaptures = tsTotalCaptures;
	}

	public Integer getTsTranTimeSla() {
		return this.tsTranTimeSla;
	}

	public void setTsTranTimeSla(Integer tsTranTimeSla) {
		this.tsTranTimeSla = tsTranTimeSla;
	}

	public Boolean getTsTranTimeSlaInherited() {
		return this.tsTranTimeSlaInherited;
	}

	public void setTsTranTimeSlaInherited(Boolean tsTranTimeSlaInherited) {
		this.tsTranTimeSlaInherited = tsTranTimeSlaInherited;
	}

	public Integer getTsTransetType() {
		return this.tsTransetType;
	}

	public void setTsTransetType(Integer tsTransetType) {
		this.tsTransetType = tsTransetType;
	}

	public BigDecimal getTsUserMinuteCost() {
		return this.tsUserMinuteCost;
	}

	public void setTsUserMinuteCost(BigDecimal tsUserMinuteCost) {
		this.tsUserMinuteCost = tsUserMinuteCost;
	}

	public Boolean getTsUserMinuteCostInherited() {
		return this.tsUserMinuteCostInherited;
	}

	public void setTsUserMinuteCostInherited(Boolean tsUserMinuteCostInherited) {
		this.tsUserMinuteCostInherited = tsUserMinuteCostInherited;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public CEMBusinessApplication getTsApp() {
		return this.tsApp;
	}

	public void setTsApp(CEMBusinessApplication tsApp) {
		this.tsApp = tsApp;
	}

	public CEMBusinessService getTsTranDefGroup() {
		return this.tsTranDefGroup;
	}

	public void setTsTranDefGroup(CEMBusinessService tsTranDefGroup) {
		this.tsTranDefGroup = tsTranDefGroup;
	}

	public List<CEMTransaction> getTsTranunits() {
		return this.tsTranunits;
	}

	public void setTsTranunits(List<CEMTransaction> tsTranunits) {
		this.tsTranunits = tsTranunits;
	}

	public CEMTransaction addTsTranunit(CEMTransaction tsTranunit) {
		getTsTranunits().add(tsTranunit);
		tsTranunit.setTsTranset(this);

		return tsTranunit;
	}

	public CEMTransaction removeTsTranunit(CEMTransaction tsTranunit) {
		getTsTranunits().remove(tsTranunit);
		tsTranunit.setTsTranset(null);

		return tsTranunit;
	}

	public List<CEMSpecification> getTsDefectDefs() {
		return this.tsDefectDefs;
	}

	public void setTsDefectDefs(List<CEMSpecification> tsDefectDefs) {
		this.tsDefectDefs = tsDefectDefs;
	}

	
	public List<TsTransetgroupTransetsMap> getTsTransetgroupTransetsMaps() {
		return this.tsTransetgroupTransetsMaps;
	}

	public void setTsTransetgroupTransetsMaps(List<TsTransetgroupTransetsMap> tsTransetgroupTransetsMaps) {
		this.tsTransetgroupTransetsMaps = tsTransetgroupTransetsMaps;
	}
	
	
	public CEMSpecification addTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().add(tsDefectDef);
		tsDefectDef.setTsTranset(this);

		return tsDefectDef;
	}

	public CEMSpecification removeTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().remove(tsDefectDef);
		tsDefectDef.setTsTranset(null);

		return tsDefectDef;
	}

	
	public TsTransetgroupTransetsMap addTsTransetgroupTransetsMap(TsTransetgroupTransetsMap tsTransetgroupTransetsMap) {
		getTsTransetgroupTransetsMaps().add(tsTransetgroupTransetsMap);
		tsTransetgroupTransetsMap.setTsTranset(this);

		return tsTransetgroupTransetsMap;
	}
	
	public TsTransetgroupTransetsMap removeTsTransetgroupTransetsMap(TsTransetgroupTransetsMap tsTransetgroupTransetsMap) {
		getTsTransetgroupTransetsMaps().remove(tsTransetgroupTransetsMap);
		tsTransetgroupTransetsMap.setTsTranset(null);

		return tsTransetgroupTransetsMap;
	}
	
	
	@Override
	public String toString() {
		return "CEMBusinessTransaction [tsId=" + tsId + ", tsAppIdInherited="
				+ tsAppIdInherited + ", tsAutogenRequestCount="
				+ tsAutogenRequestCount + ", tsAutogenTemplateId="
				+ tsAutogenTemplateId + ", tsBusinessValue=" + tsBusinessValue
				+ ", tsBusinessValueInherited=" + tsBusinessValueInherited
				+ ", tsCollectTranunitStats=" + tsCollectTranunitStats
				+ ", tsCreationDate=" + tsCreationDate + ", tsCreatorId="
				+ tsCreatorId + ", tsCreatorName=" + tsCreatorName
				+ ", tsDescription=" + tsDescription + ", tsEnabled="
				+ tsEnabled + ", tsImportanceId=" + tsImportanceId
				+ ", tsImportanceInherited=" + tsImportanceInherited
				+ ", tsIncarnationId=" + tsIncarnationId
				+ ", tsIscpAgntMtrAllComp=" + tsIscpAgntMtrAllComp
				+ ", tsItValue=" + tsItValue + ", tsItValueInherited="
				+ tsItValueInherited + ", tsLastCaptureDate="
				+ tsLastCaptureDate + ", tsLastModifiedDate="
				+ tsLastModifiedDate + ", tsLastModifierId=" + tsLastModifierId
				+ ", tsLastModifierName=" + tsLastModifierName
				+ ", tsMatchingTransetId=" + tsMatchingTransetId + ", tsName="
				+ tsName + ", tsRequestBaseId=" + tsRequestBaseId
				+ ", tsSigmaSla=" + tsSigmaSla + ", tsSigmaSlaInherited="
				+ tsSigmaSlaInherited + ", tsSoftDelete=" + tsSoftDelete
				+ ", tsSuccessRateSla=" + tsSuccessRateSla
				+ ", tsSuccessSlaInherited=" + tsSuccessSlaInherited
				+ ", tsTotalCaptures=" + tsTotalCaptures + ", tsTranTimeSla="
				+ tsTranTimeSla + ", tsTranTimeSlaInherited="
				+ tsTranTimeSlaInherited + ", tsTransetType=" + tsTransetType
				+ ", tsUserMinuteCost=" + tsUserMinuteCost
				+ ", tsUserMinuteCostInherited=" + tsUserMinuteCostInherited
				//+ ", versionInfo=" + versionInfo + ", tsApp=" + tsApp //this would cause cyclic referencing and Stack Overflow
				//+ ", tsTranDefGroup=" + tsTranDefGroup + ", tsTranunits=" //this would cause cyclic referencing and Stack Overflow
				+ ", versionInfo=" + versionInfo
				//+ ", tsTransetgroupTransetsMaps=" + tsTransetgroupTransetsMaps
				+ ", tsTranunits="
				+ tsTranunits + ", tsDefectDefs=" + tsDefectDefs + "]";
	}

	/**
	 * returns fully qualified business transaction name
	 * @return
	 */
	public String getFQN(){
		return this.tsTranDefGroup.getFQN()+"_"+this.tsName;
	}
	

	@Override
	public void copyTo(CEMEntity target) throws Exception {
		
		CEMBusinessTransaction _target = (CEMBusinessTransaction)target;
		log.entry(this, target);
		
		_target.setTsAppIdInherited(this.getTsAppIdInherited());
		_target.setTsAutogenRequestCount(this.getTsAutogenRequestCount());
		_target.setTsAutogenTemplateId(this.getTsAutogenTemplateId());
		_target.setTsBusinessValue(this.getTsBusinessValue());
		_target.setTsBusinessValueInherited(this.getTsBusinessValueInherited());
		_target.setTsCollectTranunitStats(this.getTsCollectTranunitStats());
		_target.setTsCreationDate(this.getTsCreationDate());
		_target.setTsCreatorId(this.getTsCreatorId());
		_target.setTsCreatorName(this.getTsCreatorName());
		_target.setTsDescription(this.getTsDescription());
		_target.setTsEnabled(this.getTsEnabled());
		_target.setTsImportanceId(this.getTsImportanceId());
		_target.setTsImportanceInherited(this.getTsImportanceInherited());
		//_target.setTsIncarnationId(this.getTsIncarnationId()); //this MUST NOT BE MERGED as it would case unique constraint violation. 
		_target.setTsIscpAgntMtrAllComp(this.getTsIscpAgntMtrAllComp());
		_target.setTsItValue(this.getTsItValue());
		_target.setTsItValueInherited(this.getTsItValueInherited());
		_target.setTsLastCaptureDate(this.getTsLastCaptureDate());
		_target.setTsLastModifiedDate(this.getTsLastModifiedDate());
		_target.setTsLastModifierId(this.getTsLastModifierId());
		_target.setTsLastModifierName(this.getTsLastModifierName());
		_target.setTsMatchingTransetId(this.getTsMatchingTransetId());
		_target.setTsRequestBaseId(this.getTsRequestBaseId());
		_target.setTsSigmaSla(this.getTsSigmaSla());
		_target.setTsSigmaSlaInherited(this.getTsSigmaSlaInherited());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsSuccessRateSla(this.getTsSuccessRateSla());
		_target.setTsSuccessSlaInherited(this.getTsSuccessSlaInherited());
		_target.setTsTotalCaptures(this.getTsTotalCaptures());
		_target.setTsTranTimeSla(this.getTsTranTimeSla());
		_target.setTsTranTimeSlaInherited(this.getTsTranTimeSlaInherited());
		_target.setTsTransetType(this.getTsTransetType());
		_target.setTsUserMinuteCost(this.getTsUserMinuteCost());
		_target.setTsUserMinuteCostInherited(this.getTsUserMinuteCostInherited());
		_target.setVersionInfo(this.getVersionInfo());		
		
		
		new CEMEntityMergeHelper<CEMBusinessTransaction, CEMSpecification>().mergeChildEntityLists(_target, this.getTsDefectDefs(), _target.getTsDefectDefs());
		new CEMEntityMergeHelper<CEMBusinessTransaction, CEMTransaction>().mergeChildEntityLists(_target, this.getTsTranunits(), _target.getTsTranunits());
				
		
	    log.exit(target);
		
	}

	
	@PrePersist
	private void prePersistCustomAction(){
		
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		
		if(!isClone) return; //this is called only for cloned CEMBusinesTransaction
							//i.e. for business transaction that is transferred from source to target CEM DB
		
		this.tsIncarnationId = this.tsId;
		
		TsTransetgroupTransetsMap map = this.getTsTransetgroupTransetsMaps().get(0);//get default transet map
		
		map.getId().setTsTransetGroupId(this.tsTranDefGroup.getTsTransetGroups().get(0).getTsId());
		map.getId().setTsTransetId(this.tsId);
		map.getId().setTsTransetIncarnationId(this.tsIncarnationId);
		
		
		
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		
		CEMBusinessService _targetParent = (CEMBusinessService)targetParent;
		
		_targetParent.addTsTranset(this);
		_targetParent.getTsApp().addTsTranset(this);
		
		for(TsTransetgroupTransetsMap itemTsM: this.getTsTransetgroupTransetsMaps()){
			itemTsM.getId().setTsTransetGroupId(_targetParent.getTsTransetGroups().get(0).getTsId());
			_targetParent.getTsTransetGroups().get(0).addTsTransetgroupTransetsMap(itemTsM);
		}
		
	}


	@Override
	public CEMEntity deepCopy() throws Exception {

		CEMBusinessTransaction clone = (CEMBusinessTransaction)super.deepCopy();

		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		clone.setTsIncarnationId(null);
		clone.isClone = true;
		
		//create default TsTransetgroupTransetsMap for new business transaction
		DbUtil.populateDefaultTsTransetgroupTransetsMap(clone);
		
				for(CEMSpecification itemSpecBT : clone.getTsDefectDefs()){
					itemSpecBT.setTsId(null);
				}
				
				for(CEMTransaction itemTR : clone.getTsTranunits()){
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
		
		return clone;
		
	}
	
	
	
	
	
}