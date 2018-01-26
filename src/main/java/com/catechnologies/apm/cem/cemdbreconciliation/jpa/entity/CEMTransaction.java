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

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMTransactionAdapter;

import java.util.List;


/**
 * The persistent class for the ts_tranunits database table.
 * 
 */
@Entity
@Table(name="ts_tranunits")
@NamedQuery(name="CEMTransaction.findAll", query="SELECT c FROM CEMTransaction c WHERE c.tsSoftDelete=false")
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.CEMTransactionCustomizer.class)
@XmlRootElement(name="transaction")
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(CEMTransactionAdapter.class)
public class CEMTransaction extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMTransaction.class.getName());

	@Id
	@SequenceGenerator(name="TS_TRANUNITS_TSID_GENERATOR", sequenceName="TS_TRANUNITS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_TRANUNITS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_collect_trancomp_stats")
	@XmlAttribute(name="ts_collect_trancomp_stats")
	private Boolean tsCollectTrancompStats;

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

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMTransactionComponent
	@OneToMany(mappedBy="tsTranunit", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="components")
	@XmlElement(name = "item")
	private List<CEMTransactionComponent> tsTrancomps;

	//bi-directional many-to-one association to CEMBusinessTransaction
	@ManyToOne
	@JoinColumn(name="ts_transet_id")
	@XmlInverseReference(mappedBy="tsTranunits")
	private CEMBusinessTransaction tsTranset;

	//bi-directional many-to-one association to CEMSpecification
	@OneToMany(mappedBy="tsTranunit", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="cemSpecification")
	@XmlElement(name = "item")
	private List<CEMSpecification> tsDefectDefs;

	public CEMTransaction() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public Boolean getTsCollectTrancompStats() {
		return this.tsCollectTrancompStats;
	}

	public void setTsCollectTrancompStats(Boolean tsCollectTrancompStats) {
		this.tsCollectTrancompStats = tsCollectTrancompStats;
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

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public List<CEMTransactionComponent> getTsTrancomps() {
		return this.tsTrancomps;
	}

	public void setTsTrancomps(List<CEMTransactionComponent> tsTrancomps) {
		this.tsTrancomps = tsTrancomps;
	}

	public CEMTransactionComponent addTsTrancomp(CEMTransactionComponent tsTrancomp) {
		getTsTrancomps().add(tsTrancomp);
		tsTrancomp.setTsTranunit(this);

		return tsTrancomp;
	}

	public CEMTransactionComponent removeTsTrancomp(CEMTransactionComponent tsTrancomp) {
		getTsTrancomps().remove(tsTrancomp);
		tsTrancomp.setTsTranunit(null);

		return tsTrancomp;
	}

	public CEMBusinessTransaction getTsTranset() {
		return this.tsTranset;
	}

	public void setTsTranset(CEMBusinessTransaction tsTranset) {
		this.tsTranset = tsTranset;
	}

	public List<CEMSpecification> getTsDefectDefs() {
		return this.tsDefectDefs;
	}

	public void setTsDefectDefs(List<CEMSpecification> tsDefectDefs) {
		this.tsDefectDefs = tsDefectDefs;
	}

	public CEMSpecification addTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().add(tsDefectDef);
		tsDefectDef.setTsTranunit(this);

		return tsDefectDef;
	}

	public CEMSpecification removeTsDefectDef(CEMSpecification tsDefectDef) {
		getTsDefectDefs().remove(tsDefectDef);
		tsDefectDef.setTsTranunit(null);

		return tsDefectDef;
	}

	@Override
	public String toString() {
		return "CEMTransaction [tsId=" + tsId + ", tsCollectTrancompStats="
				+ tsCollectTrancompStats + ", tsDescription=" + tsDescription
				+ ", tsIdentifying=" + tsIdentifying + ", tsIncluded="
				+ tsIncluded + ", tsName=" + tsName + ", tsOptional="
				+ tsOptional + ", tsSequenceNumber=" + tsSequenceNumber
				+ ", tsSigmaSla=" + tsSigmaSla + ", tsSigmaSlaInherited="
				+ tsSigmaSlaInherited + ", tsSoftDelete=" + tsSoftDelete
				+ ", tsSuccessRateSla=" + tsSuccessRateSla
				+ ", tsSuccessSlaInherited=" + tsSuccessSlaInherited
				+ ", tsTranTimeSla=" + tsTranTimeSla
				+ ", tsTranTimeSlaInherited=" + tsTranTimeSlaInherited
				+ ", versionInfo=" + versionInfo + ", tsTrancomps="
				//+ tsTrancomps + ", tsTranset=" + tsTranset + ", tsDefectDefs=" //this would cause cyclic referencing and Stack Overflow
				+ tsTrancomps + ", tsDefectDefs=" + tsDefectDefs + "]";
	}

	
	/**
	 * returns fully qualified transaction name
	 * @return
	 */
	public String getFQN(){
		return this.tsTranset.getFQN() + "_" + this.tsName;
	}
	

	@Override
	public void copyTo(CEMEntity target) throws Exception {
		
		CEMTransaction _target = (CEMTransaction)target;
		
		log.entry(this, target);
		
		_target.setTsCollectTrancompStats(this.getTsCollectTrancompStats());
		_target.setTsDescription(this.getTsDescription());
		_target.setTsIdentifying(this.getTsIdentifying());
		_target.setTsIncluded(this.getTsIncluded());
		_target.setTsOptional(this.getTsOptional());
		//_target.setTsSequenceNumber(this.getTsSequenceNumber());//calculated in addToParentPostprocessing
		_target.setTsSigmaSla(this.getTsSigmaSla());
		_target.setTsSigmaSlaInherited(this.getTsSigmaSlaInherited());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsSuccessRateSla(this.getTsSuccessRateSla());
		_target.setTsSuccessSlaInherited(this.getTsSuccessSlaInherited());
		_target.setTsTranTimeSla(this.getTsTranTimeSla());
		_target.setTsTranTimeSlaInherited(this.getTsTranTimeSlaInherited());
		_target.setVersionInfo(this.getVersionInfo());
		
		
		new CEMEntityMergeHelper<CEMTransaction, CEMTransactionComponent>().mergeChildEntityLists(_target, this.getTsTrancomps(), _target.getTsTrancomps());
		new CEMEntityMergeHelper<CEMTransaction, CEMSpecification>().mergeChildEntityLists(_target, this.getTsDefectDefs(), _target.getTsDefectDefs());		
		
	    log.exit(target);
		
	}

	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {

		log.entry(this, targetParent);
		
		CEMBusinessTransaction _targetParent = (CEMBusinessTransaction)targetParent;
		
		_targetParent.addTsTranunit(this);
		
		for(CEMSpecification itemSpecTR : this.getTsDefectDefs())
			itemSpecTR.setTsTranset(_targetParent);
		
		for(CEMTransactionComponent itemTC : this.getTsTrancomps())
			for(CEMSpecification itemSpecTC : itemTC.getTsDefectDefs())
				itemSpecTC.setTsTranset(_targetParent);
		
		/*
		List<CEMTransaction> targetList = _targetParent.getTsTranunits();
		
		Integer max = 0;
		
		for(int i=0;i<targetList.size();i++){
			
			CEMTransaction iteratedItem = targetList.get(i);
			
			if(iteratedItem.getTsSequenceNumber() == null) continue;//ts_autogen_sequence might be zero which would case NullPointerException
			if(iteratedItem.equals(this)) continue;//we are not interested in comparing with same item, i.e. item that was just added
			
			if(iteratedItem.getTsSequenceNumber().intValue() > max)	max = iteratedItem.getTsSequenceNumber().intValue();
		}
		
		
		this.setTsSequenceNumber(new Integer(++max));
		log.debug("setTsSequenceNumber(): " + this.getTsSequenceNumber() );
		*/
		
		log.exit(this);
		
	}

	@Override
	public CEMEntity deepCopy() throws Exception {

		CEMTransaction clone = (CEMTransaction)super.deepCopy();
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
				
					
					for(CEMSpecification itemSpecTR : clone.getTsDefectDefs()){
						itemSpecTR.setTsId(null);
						itemSpecTR.setTsTranset(null);
					}
					
					for(CEMTransactionComponent itemTC : clone.getTsTrancomps()){
						itemTC.setTsId(null);
						itemTC.setTsRecordingComponentId(null);
						
						for(CEMSpecification itemSpecTC : itemTC.getTsDefectDefs()){
							itemSpecTC.setTsId(null);
							itemSpecTC.setTsTranset(null);
						}
						
						for(CEMTransactionComponentIdentification itemTCI : itemTC.getTsParams()){
							itemTCI.getId().setTsTrancompId(null);
						}
						
					}
		
		return clone;
	}

	
	
}