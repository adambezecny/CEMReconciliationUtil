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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.math.BigDecimal;
import java.util.List;
import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.CEMBusinessServiceAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.util.DbUtil;



/**
 * The persistent class for the ts_tran_def_groups database table.
 * 
 */
@Entity
@Table(name="ts_tran_def_groups")
@NamedQuery(name="CEMBusinessService.findByName", query="SELECT c FROM CEMBusinessService c WHERE c.tsSoftDelete=false AND c.tsName=:bsName AND c.tsApp.tsName=:baName")
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.CEMBusinessServiceCustomizer.class)
@XmlRootElement(name="businessService")
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(CEMBusinessServiceAdapter.class)
public class CEMBusinessService extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(CEMBusinessService.class.getName());


	@Id
	@SequenceGenerator(name="TS_TRAN_DEF_GROUPS_TSID_GENERATOR", sequenceName="TS_TRAN_DEF_GROUPS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_TRAN_DEF_GROUPS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_business_value")
	@XmlAttribute(name="ts_business_value")
	private BigDecimal tsBusinessValue;

	@Column(name="ts_business_value_inherited")
	@XmlAttribute(name="ts_business_value_inherited")
	private Boolean tsBusinessValueInherited;

	@Column(name="ts_description")
	@XmlAttribute(name="ts_description")
	private String tsDescription;

	@Column(name="ts_domain_id")
	@XmlAttribute(name="ts_domain_id")
	private Long tsDomainId;

	@Column(name="ts_importance_id")
	@XmlAttribute(name="ts_importance_id")
	private Long tsImportanceId;

	@Column(name="ts_importance_inherited")
	@XmlAttribute(name="ts_importance_inherited")
	private Boolean tsImportanceInherited;

	@Column(name="ts_it_value")
	@XmlAttribute(name="ts_it_value")
	private BigDecimal tsItValue;

	@Column(name="ts_it_value_inherited")
	@XmlAttribute(name="ts_it_value_inherited")
	private Boolean tsItValueInherited;

	@Column(name="ts_name")
	@XmlAttribute(name="ts_name")
	private String tsName;

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
	@XmlInverseReference(mappedBy="tsTranDefGroups")
	private CEMBusinessApplication tsApp;

	//bi-directional many-to-one association to CEMBusinessTransaction
	@OneToMany(mappedBy="tsTranDefGroup", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@OrderBy("tsName ASC")
	@XmlElementWrapper(name="businessTransactions")
	@XmlElement(name = "item")
	private List<CEMBusinessTransaction> tsTransets;

	//bi-directional many-to-one association to TsTransetGroup
	@OneToMany(mappedBy="tsTranDefGroup", fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	@XmlElementWrapper(name="tsTransetGroups")
	@XmlElement(name = "item")
	private List<TsTransetGroup> tsTransetGroups;
	
	//@Transient
	//private boolean isClone = false;
	
	
	public void cutOffChildNodes(){
		this.tsTransets = null;
		this.tsTransetGroups = null;
	}
	
	
	public CEMBusinessService() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
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

	public String getTsName() {
		return this.tsName;
	}

	public void setTsName(String tsName) {
		this.tsName = tsName;
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

	public List<CEMBusinessTransaction> getTsTransets() {
		return this.tsTransets;
	}

	public void setTsTransets(List<CEMBusinessTransaction> tsTransets) {
		this.tsTransets = tsTransets;
	}

	
	public List<TsTransetGroup> getTsTransetGroups() {
		return this.tsTransetGroups;
	}

	public void setTsTransetGroups(List<TsTransetGroup> tsTransetGroups) {
		this.tsTransetGroups = tsTransetGroups;
	}
	
	public CEMBusinessTransaction addTsTranset(CEMBusinessTransaction tsTranset) {
		getTsTransets().add(tsTranset);
		tsTranset.setTsTranDefGroup(this);

		return tsTranset;
	}

	public CEMBusinessTransaction removeTsTranset(CEMBusinessTransaction tsTranset) {
		getTsTransets().remove(tsTranset);
		tsTranset.setTsTranDefGroup(null);

		return tsTranset;
	}

	
	public TsTransetGroup addTsTransetGroup(TsTransetGroup tsTransetGroup) {
		getTsTransetGroups().add(tsTransetGroup);
		tsTransetGroup.setTsTranDefGroup(this);

		return tsTransetGroup;
	}

	public TsTransetGroup removeTsTransetGroup(TsTransetGroup tsTransetGroup) {
		getTsTransetGroups().remove(tsTransetGroup);
		tsTransetGroup.setTsTranDefGroup(null);

		return tsTransetGroup;
	}
	
	
	@Override
	public String toString() {
		return "CEMBusinessService [tsId=" + tsId + ", tsBusinessValue="
				+ tsBusinessValue + ", tsBusinessValueInherited="
				+ tsBusinessValueInherited + ", tsDescription=" + tsDescription
				+ ", tsDomainId=" + tsDomainId + ", tsImportanceId="
				+ tsImportanceId + ", tsImportanceInherited="
				+ tsImportanceInherited + ", tsItValue=" + tsItValue
				+ ", tsItValueInherited=" + tsItValueInherited + ", tsName="
				+ tsName + ", tsSigmaSla=" + tsSigmaSla
				+ ", tsSigmaSlaInherited=" + tsSigmaSlaInherited
				+ ", tsSoftDelete=" + tsSoftDelete + ", tsSuccessRateSla="
				+ tsSuccessRateSla + ", tsSuccessSlaInherited="
				+ tsSuccessSlaInherited + ", tsTranTimeSla=" + tsTranTimeSla
				+ ", tsTranTimeSlaInherited=" + tsTranTimeSlaInherited
				+ ", tsUserMinuteCost=" + tsUserMinuteCost
				+ ", tsUserMinuteCostInherited=" + tsUserMinuteCostInherited
				//+ ", versionInfo=" + versionInfo + ", tsApp=" + tsApp //this would cause cyclic referencing and Stack Overflow
				+ ", versionInfo=" + versionInfo
				//+ ", tsTransetGroups=" + tsTransetGroups
				+ ", tsTransets=" + tsTransets + "]";
	}

	
	/**
	 * returns fully qualified service name
	 * @return
	 */
	public String getFQN(){
		return this.tsApp.getFQN()+"_"+this.tsName;
	}
	
	
	@Override
	public void copyTo(CEMEntity target) throws Exception {
		
		CEMBusinessService _target = (CEMBusinessService)target;
		log.entry(this, target);
		
		_target.setTsBusinessValue(this.getTsBusinessValue());
		_target.setTsBusinessValueInherited(this.getTsBusinessValueInherited());
		_target.setTsDescription(this.getTsDescription());
		//_target.setTsDomainId(this.getTsDomainId());//this is set in addToTargetParent method
		_target.setTsImportanceId(this.getTsImportanceId());
		_target.setTsImportanceInherited(this.getTsImportanceInherited());
		_target.setTsItValue(this.getTsItValue());
		_target.setTsItValueInherited(this.getTsItValueInherited());
		_target.setTsSigmaSla(this.getTsSigmaSla());
		_target.setTsSigmaSlaInherited(this.getTsSigmaSlaInherited());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsSuccessRateSla(this.getTsSuccessRateSla());
		_target.setTsSuccessSlaInherited(this.getTsSuccessSlaInherited());
		_target.setTsTranTimeSla(this.getTsTranTimeSla());
		_target.setTsTranTimeSlaInherited(this.getTsTranTimeSlaInherited());
		_target.setTsUserMinuteCost(this.getTsUserMinuteCost());
		_target.setTsUserMinuteCostInherited(this.getTsUserMinuteCostInherited());
		_target.setVersionInfo(this.getVersionInfo());
		
		new CEMEntityMergeHelper<CEMBusinessService, CEMBusinessTransaction>().mergeChildEntityLists(_target, this.getTsTransets(), _target.getTsTransets());
		
	    log.exit(target);
		
	}

	
	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		
		CEMBusinessApplication _targetParent = (CEMBusinessApplication)targetParent;
		_targetParent.addTsTranDefGroup(this);
		
		this.setTsDomainId(_targetParent.getTsDomainId());
		
	}


	@Override
	public CEMEntity deepCopy() throws Exception {
		
		CEMBusinessService clone = (CEMBusinessService)super.deepCopy(); 
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		
		DbUtil.populateDefaultTsTransetGroup(this, clone);
		
		
			for(CEMBusinessTransaction itemBT : clone.getTsTransets()){
				itemBT.setTsId(null);
				itemBT.isClone = true;
				DbUtil.populateDefaultTsTransetgroupTransetsMap(itemBT);
				
				
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

		
		return clone;
	}
	
	
	
	
}