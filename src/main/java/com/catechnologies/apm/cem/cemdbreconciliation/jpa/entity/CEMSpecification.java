package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




/**
 * The persistent class for the ts_defect_defs database table.
 * 
 */
@Entity
@Table(name="ts_defect_defs")
@NamedQuery(name="CEMSpecification.findAll", query="SELECT c FROM CEMSpecification c WHERE c.tsSoftDelete=false")
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name="cemSpecification")
public class CEMSpecification extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMSpecification.class.getName());

	@Id
	@SequenceGenerator(name="TS_DEFECT_DEFS_TSID_GENERATOR", sequenceName="TS_DEFECT_DEFS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_DEFECT_DEFS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_attribute_id")
	@XmlAttribute(name="ts_attribute_id")
	private Long tsAttributeId;

	@Column(name="ts_condition")
	@XmlAttribute(name="ts_condition")
	private Integer tsCondition;

	@Column(name="ts_enabled")
	@XmlAttribute(name="ts_enabled")
	private Boolean tsEnabled;

	@Column(name="ts_header_name")
	@XmlAttribute(name="ts_header_name")
	private String tsHeaderName;

	@Column(name="ts_importance_id")
	@XmlAttribute(name="ts_importance_id")
	private Long tsImportanceId;

	@Column(name="ts_locked")
	@XmlAttribute(name="ts_locked")
	private Boolean tsLocked;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

	@Column(name="ts_soft_delete")
	@XmlAttribute(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="ts_tran_type")
	@XmlAttribute(name="ts_tran_type")
	private Integer tsTranType;

	@Column(name="ts_type")
	@XmlAttribute(name="ts_type")
	private Integer tsType;

	@Column(name="ts_value")
	@XmlAttribute(name="ts_value")
	private String tsValue;

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMTransactionComponent
	@ManyToOne
	@JoinColumn(name="ts_trancomp_id")
	private CEMTransactionComponent tsTrancomp;

	//bi-directional many-to-one association to CEMBusinessTransaction
	@ManyToOne
	@JoinColumn(name="ts_transet_id")
	private CEMBusinessTransaction tsTranset;

	//bi-directional many-to-one association to CEMTransaction
	@ManyToOne
	@JoinColumn(name="ts_tranunit_id")
	private CEMTransaction tsTranunit;

	public CEMSpecification() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Long getTsAttributeId() {
		return this.tsAttributeId;
	}

	public void setTsAttributeId(Long tsAttributeId) {
		this.tsAttributeId = tsAttributeId;
	}

	public Integer getTsCondition() {
		return this.tsCondition;
	}

	public void setTsCondition(Integer tsCondition) {
		this.tsCondition = tsCondition;
	}

	public Boolean getTsEnabled() {
		return this.tsEnabled;
	}

	public void setTsEnabled(Boolean tsEnabled) {
		this.tsEnabled = tsEnabled;
	}

	public String getTsHeaderName() {
		return this.tsHeaderName;
	}

	public void setTsHeaderName(String tsHeaderName) {
		this.tsHeaderName = tsHeaderName;
	}

	public Long getTsImportanceId() {
		return this.tsImportanceId;
	}

	public void setTsImportanceId(Long tsImportanceId) {
		this.tsImportanceId = tsImportanceId;
	}

	public Boolean getTsLocked() {
		return this.tsLocked;
	}

	public void setTsLocked(Boolean tsLocked) {
		this.tsLocked = tsLocked;
	}

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
	}

	public Boolean getTsSoftDelete() {
		return this.tsSoftDelete;
	}

	@Override
	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public Integer getTsTranType() {
		return this.tsTranType;
	}

	public void setTsTranType(Integer tsTranType) {
		this.tsTranType = tsTranType;
	}

	public Integer getTsType() {
		return this.tsType;
	}

	public void setTsType(Integer tsType) {
		this.tsType = tsType;
	}

	public String getTsValue() {
		return this.tsValue;
	}

	public void setTsValue(String tsValue) {
		this.tsValue = tsValue;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public CEMTransactionComponent getTsTrancomp() {
		return this.tsTrancomp;
	}

	public void setTsTrancomp(CEMTransactionComponent tsTrancomp) {
		this.tsTrancomp = tsTrancomp;
	}

	public CEMBusinessTransaction getTsTranset() {
		return this.tsTranset;
	}

	public void setTsTranset(CEMBusinessTransaction tsTranset) {
		this.tsTranset = tsTranset;
	}

	public CEMTransaction getTsTranunit() {
		return this.tsTranunit;
	}

	public void setTsTranunit(CEMTransaction tsTranunit) {
		this.tsTranunit = tsTranunit;
	}

	@Override
	public String toString() {
		return "CEMSpecification [tsId=" + tsId + ", tsAttributeId="
				+ tsAttributeId + ", tsCondition=" + tsCondition
				+ ", tsEnabled=" + tsEnabled + ", tsHeaderName=" + tsHeaderName
				+ ", tsImportanceId=" + tsImportanceId + ", tsLocked="
				+ tsLocked + ", tsName=" + tsName + ", tsSoftDelete="
				+ tsSoftDelete + ", tsTranType=" + tsTranType + ", tsType="
				+ tsType + ", tsValue=" + tsValue + ", versionInfo=" + versionInfo + "]";
				//+ versionInfo + ", tsTrancomp=" + tsTrancomp + ", tsTranset=" //this would cause cyclic referencing and Stack Overflow
				//+ tsTranset + ", tsTranunit=" + tsTranunit + "]"; //this would cause cyclic referencing and Stack Overflow
				
	}

	
	@Override
	public String getFQN() {
		String fqn = this.tsTranset.getFQN();
		
		if(this.tsTranunit!=null) fqn = fqn + "_" + this.tsTranunit.getTsName();
		if(this.tsTrancomp!=null) fqn = fqn + "_" + this.tsTrancomp.getTsName();
		fqn = fqn + "_" + this.tsName;//this is actual defect type, e.g. Slow Response
		
		return fqn;
	}

	@Override
	public void copyTo(CEMEntity target) {
		
		CEMSpecification _target = (CEMSpecification)target;
		
		log.entry(this, target);
		
		_target.setTsAttributeId(this.getTsAttributeId());
		_target.setTsCondition(this.getTsCondition());
		_target.setTsEnabled(this.getTsEnabled());
		_target.setTsHeaderName(this.getTsHeaderName());
		_target.setTsImportanceId(this.getTsImportanceId());
		_target.setTsLocked(this.getTsLocked());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsTranType(this.getTsTranType());
		_target.setTsType(this.getTsType());
		_target.setTsValue(this.getTsValue());
		_target.setVersionInfo(this.getVersionInfo());
		
		log.exit(target);
		
		
	}

	
	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+this.tsId);
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {

		
		log.entry(this, targetParent);
		
		if(targetParent instanceof CEMTransactionComponent){

			/**
			 * this should never happen since specification is always bound to (business)transaction level
			 * i have never seen specification bound to transaction component even though database design  allows this
			 */
			
			
			CEMTransactionComponent _targetParent = (CEMTransactionComponent)targetParent;
			
			_targetParent.addTsDefectDef(this);
			
			//for BT and TR just set respective back pointers of CEMSpecification object, DO NOT include in BT & TR Lists of defects/specifications!
			this.setTsTranunit(_targetParent.getTsTranunit());
			this.setTsTranset(_targetParent.getTsTranunit().getTsTranset());

			
		}else if(targetParent instanceof CEMTransaction){
			
			CEMTransaction _targetParent = (CEMTransaction)targetParent;
			
			_targetParent.addTsDefectDef(this);
			
			//for BT just set respective back pointer of CEMSpecification object, DO NOT include in BT List of defects/specifications!
			this.setTsTranset(_targetParent.getTsTranset());
			
		}else if(targetParent instanceof CEMBusinessTransaction){
			
			CEMBusinessTransaction _targetParent = (CEMBusinessTransaction)targetParent;
			
			_targetParent.addTsDefectDef(this);
			
		}else{
			throw new UnsupportedOperationException("Unsupported targetParent in CEMSpecification.addToParent()!");
		}
		
		
		log.exit(this);
		
	}

	@Override
	public CEMEntity deepCopy() throws Exception {
		
		CEMSpecification clone = (CEMSpecification)super.deepCopy();
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		
		return clone;
	}
	

	
	
	
}