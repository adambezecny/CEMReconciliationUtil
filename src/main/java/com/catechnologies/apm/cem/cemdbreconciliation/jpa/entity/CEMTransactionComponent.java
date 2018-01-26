package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.*;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import java.sql.Timestamp;
import java.util.List;

/**
 * The persistent class for the ts_trancomps database table.
 * 
 */
@Entity
@Table(name="ts_trancomps")
@NamedQuery(name="CEMTransactionComponent.findAll", query="SELECT c FROM CEMTransactionComponent c WHERE c.tsSoftDelete=false")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="component")
@XmlJavaTypeAdapter(CEMTransactionComponentAdapter.class)
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.CEMTransactionComponentCustomizer.class)
public class CEMTransactionComponent extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMTransactionComponent.class.getName());
	

	@Id
	@SequenceGenerator(name="TS_TRANCOMPS_TSID_GENERATOR", sequenceName="TS_TRANCOMPS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_TRANCOMPS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_count")
	@XmlAttribute(name="ts_count")
	private Integer tsCount;

	@Column(name="ts_creation_date")
	@XmlJavaTypeAdapter(TimestampAdapter.class)
	@XmlAttribute(name="ts_creation_date")
	private Timestamp tsCreationDate;

	@Column(name="ts_description")
	@XmlAttribute(name="ts_description")
	private String tsDescription;

	@Column(name="ts_identifying")
	@XmlAttribute(name="ts_identifying")
	private Boolean tsIdentifying;

	@Column(name="ts_included")
	@XmlAttribute(name="ts_included")
	private Boolean tsIncluded;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

	@Column(name="ts_optional")
	@XmlAttribute(name="ts_optional")
	private Boolean tsOptional;

	@Column(name="ts_pending")
	@XmlAttribute(name="ts_pending")
	private Boolean tsPending;

	@Column(name="ts_recording_component_id")
	@XmlAttribute(name="ts_recording_component_id")
	private Long tsRecordingComponentId;

	@Column(name="ts_sequence_number")
	@XmlAttribute(name="ts_sequence_number")
	private Integer tsSequenceNumber;

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

	@Column(name="ts_tran_time_sla")
	@XmlAttribute(name="ts_tran_time_sla")
	private Integer tsTranTimeSla;

	@Column(name="ts_tran_time_sla_inherited")
	@XmlAttribute(name="ts_tran_time_sla_inherited")
	private Boolean tsTranTimeSlaInherited;

	@Column(name="ts_url")
	@XmlAttribute(name="ts_url")
	private String tsUrl;

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMTransactionComponentIdentification
	@OneToMany(mappedBy="tsTrancomp", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("id.tsName ASC")
	@org.eclipse.persistence.annotations.PrivateOwned //this will cause ts_params record to be deleted from DB once removed from tsParams collection
	@XmlElementWrapper(name="componentIdentification")
	@XmlElement(name = "item")
	@XmlJavaTypeAdapter(CEMTransactionComponentIdentificationAdapter.class)
	private List<CEMTransactionComponentIdentification> tsParams;

	//bi-directional many-to-one association to CEMTransaction
	@ManyToOne
	@JoinColumn(name="ts_tranunit_id")
	@XmlInverseReference(mappedBy="tsTrancomps")
	private CEMTransaction tsTranunit;

	//bi-directional many-to-one association to CEMSpecification
	@OneToMany(mappedBy="tsTrancomp", fetch=FetchType.EAGER)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="cemSpecification")
	@XmlElement(name = "item")
	private List<CEMSpecification> tsDefectDefs;

	public CEMTransactionComponent() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Integer getTsCount() {
		return this.tsCount;
	}

	public void setTsCount(Integer tsCount) {
		this.tsCount = tsCount;
	}

	public Timestamp getTsCreationDate() {
		return this.tsCreationDate;
	}

	public void setTsCreationDate(Timestamp tsCreationDate) {
		this.tsCreationDate = tsCreationDate;
	}

	public String getTsDescription() {
		return this.tsDescription;
	}

	public void setTsDescription(String tsDescription) {
		this.tsDescription = tsDescription;
	}

	public Boolean getTsIdentifying() {
		return this.tsIdentifying;
	}

	public void setTsIdentifying(Boolean tsIdentifying) {
		this.tsIdentifying = tsIdentifying;
	}

	public Boolean getTsIncluded() {
		return this.tsIncluded;
	}

	public void setTsIncluded(Boolean tsIncluded) {
		this.tsIncluded = tsIncluded;
	}

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Boolean getTsOptional() {
		return this.tsOptional;
	}

	public void setTsOptional(Boolean tsOptional) {
		this.tsOptional = tsOptional;
	}

	public Boolean getTsPending() {
		return this.tsPending;
	}

	public void setTsPending(Boolean tsPending) {
		this.tsPending = tsPending;
	}

	public Long getTsRecordingComponentId() {
		return this.tsRecordingComponentId;
	}

	public void setTsRecordingComponentId(Long tsRecordingComponentId) {
		this.tsRecordingComponentId = tsRecordingComponentId;
	}

	public Integer getTsSequenceNumber() {
		return this.tsSequenceNumber;
	}

	public void setTsSequenceNumber(Integer tsSequenceNumber) {
		this.tsSequenceNumber = tsSequenceNumber;
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

	public String getTsUrl() {
		return this.tsUrl;
	}

	public void setTsUrl(String tsUrl) {
		this.tsUrl = tsUrl;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public List<CEMTransactionComponentIdentification> getTsParams() {
		return this.tsParams;
	}

	public void setTsParams(List<CEMTransactionComponentIdentification> tsParams) {
		this.tsParams = tsParams;
	}

	public CEMTransactionComponentIdentification addTsParam(CEMTransactionComponentIdentification tsParam) {
		getTsParams().add(tsParam);
		tsParam.setTsTrancomp(this);

		return tsParam;
	}

	public CEMTransactionComponentIdentification removeTsParam(CEMTransactionComponentIdentification tsParam) {
		getTsParams().remove(tsParam);
		tsParam.setTsTrancomp(null);

		return tsParam;
	}

	public CEMTransaction getTsTranunit() {
		return this.tsTranunit;
	}

	public void setTsTranunit(CEMTransaction tsTranunit) {
		this.tsTranunit = tsTranunit;
	}

	public List<CEMSpecification> getTsDefectDefs() {
		return this.tsDefectDefs;
	}

	public void setTsDefectDefs(List<CEMSpecification> tsDefectDefs) {
		this.tsDefectDefs = tsDefectDefs;
	}

	public CEMSpecification addTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().add(tsDefectDef);
		tsDefectDef.setTsTrancomp(this);

		return tsDefectDef;
	}

	public CEMSpecification removeTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().remove(tsDefectDef);
		tsDefectDef.setTsTrancomp(null);

		return tsDefectDef;
	}

	@Override
	public String toString() {
		return "CEMTransactionComponent [tsId=" + tsId + ", tsCount=" + tsCount
				+ ", tsCreationDate=" + tsCreationDate + ", tsDescription="
				+ tsDescription + ", tsIdentifying=" + tsIdentifying
				+ ", tsIncluded=" + tsIncluded + ", tsName=" + tsName
				+ ", tsOptional=" + tsOptional + ", tsPending=" + tsPending
				+ ", tsRecordingComponentId=" + tsRecordingComponentId
				+ ", tsSequenceNumber=" + tsSequenceNumber + ", tsSigmaSla="
				+ tsSigmaSla + ", tsSigmaSlaInherited=" + tsSigmaSlaInherited
				+ ", tsSoftDelete=" + tsSoftDelete + ", tsSuccessRateSla="
				+ tsSuccessRateSla + ", tsSuccessSlaInherited="
				+ tsSuccessSlaInherited + ", tsTranTimeSla=" + tsTranTimeSla
				+ ", tsTranTimeSlaInherited=" + tsTranTimeSlaInherited
				+ ", tsUrl=" + tsUrl + ", versionInfo=" + versionInfo
				//+ ", tsParams=" + tsParams + ", tsTranunit=" + tsTranunit//this would cause cyclic referencing and Stack Overflow
				+ ", tsParams=" + tsParams 
				+ ", tsDefectDefs=" + tsDefectDefs + "]";
	}

	/**
	 * returns fully qualified transaction component name
	 * @return
	 */
	public String getFQN(){
		return this.tsTranunit.getFQN() + "_" + this.tsName;
	}
	

	@Override
	public void copyTo(CEMEntity target) throws Exception {

		CEMTransactionComponent _target = (CEMTransactionComponent)target;
		
		log.entry(this, target);
		
		_target.setTsCount(this.getTsCount());
		_target.setTsCreationDate(this.getTsCreationDate());
		_target.setTsDescription(this.getTsDescription());
		_target.setTsIdentifying(this.getTsIdentifying());
		_target.setTsIncluded(this.getTsIncluded());
		_target.setTsOptional(this.getTsOptional());
		_target.setTsPending(this.getTsPending());
		//_target.setTsSequenceNumber(this.getTsSequenceNumber());//calculated in addToParentPostprocessing
		_target.setTsSigmaSla(this.getTsSigmaSla());
		_target.setTsSigmaSlaInherited(this.getTsSigmaSlaInherited());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsSuccessRateSla(this.getTsSuccessRateSla());
		_target.setTsSuccessSlaInherited(this.getTsSuccessSlaInherited());
		_target.setTsTranTimeSla(this.getTsTranTimeSla());
		_target.setTsTranTimeSlaInherited(this.getTsTranTimeSlaInherited());
		_target.setTsUrl(this.getTsUrl());
		_target.setVersionInfo(this.getVersionInfo());

		new CEMEntityMergeHelper<CEMTransactionComponent, CEMSpecification>().mergeChildEntityLists(_target, this.getTsDefectDefs(), _target.getTsDefectDefs());
	    
		
		new CEMEntityMergeHelper<CEMTransactionComponent, CEMTransactionComponentIdentification>().mergeChildEntityLists(_target, this.getTsParams(), _target.getTsParams());

	    log.exit(target);
		
		
	}

	
	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		
		log.entry(this, targetParent);
		
		CEMTransaction _targetParent = (CEMTransaction)targetParent;
		_targetParent.addTsTrancomp(this);
		
		for(CEMSpecification itemSpecTC : this.getTsDefectDefs()){
			itemSpecTC.setTsTranunit(_targetParent);
			itemSpecTC.setTsTranset(_targetParent.getTsTranset());
		}
		
		List<CEMTransactionComponent> targetList = _targetParent.getTsTrancomps();
		
		Integer max = 0;
		
		for(int i=0;i<targetList.size();i++){
			
			CEMTransactionComponent iteratedItem = targetList.get(i);
			
			if(iteratedItem.getTsSequenceNumber() == null) continue;//ts_autogen_sequence might be zero which would case NullPointerException
			if(iteratedItem.equals(this)) continue;//we are not interested in comparing with same item, i.e. item that was just added
			
			if(iteratedItem.getTsSequenceNumber().intValue() > max)	max = iteratedItem.getTsSequenceNumber().intValue();
		}
		
		
		this.setTsSequenceNumber(new Integer(++max));
		log.trace("setTsSequenceNumber(): " + this.getTsSequenceNumber() );
		
		log.exit(this);
		
	}

	@Override
	public CEMEntity deepCopy() throws Exception {

		CEMTransactionComponent clone = (CEMTransactionComponent)super.deepCopy();
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		clone.setTsRecordingComponentId(null);//there will be no same recording component in target DB, this must be deleted during cloning!
		
		for(CEMSpecification itemSpecTC : clone.getTsDefectDefs()){
			itemSpecTC.setTsId(null);
			itemSpecTC.setTsTranunit(null);
			itemSpecTC.setTsTranset(null);
		}
		
		for(CEMTransactionComponentIdentification itemTCI : clone.getTsParams()){
			itemTCI.getId().setTsTrancompId(null);
		}
		
		return clone;
	}

	
	
}