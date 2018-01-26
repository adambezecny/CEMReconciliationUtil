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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.TsTransetGroupAdapter;
import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.TsTransetgroupTransetsMapAdapter;

import java.util.List;


/**
 * The persistent class for the ts_transet_groups database table.
 * 
 */
@Entity
@Table(name="ts_transet_groups")
@NamedQuery(name="TsTransetGroup.findAll", query="SELECT t FROM TsTransetGroup t WHERE t.tsSoftDelete=false")
@org.eclipse.persistence.annotations.Customizer(com.catechnologies.apm.cem.cemdbreconciliation.jpa.ext.eclipselink.TsTransetGroupCustomizer.class)
@XmlRootElement(name="tsTransetGroup")
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(TsTransetGroupAdapter.class)
public class TsTransetGroup extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(TsTransetGroup.class.getName());


	@Id
	@SequenceGenerator(name="TS_TRANSET_GROUPS_TSID_GENERATOR", sequenceName="TS_TRANSET_GROUPS_TS_ID_SQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TS_TRANSET_GROUPS_TSID_GENERATOR")
	@Column(name="ts_id")
	@XmlAttribute(name="ts_id")
	private Long tsId;

	@Column(name="ts_description")
	@XmlAttribute(name="ts_description")
	private String tsDescription;

	@Column(name="ts_importance_id")
	@XmlAttribute(name="ts_importance_id")
	private Long tsImportanceId;

	@Column(name="ts_importance_inherited")
	@XmlAttribute(name="ts_importance_inherited")
	private Boolean tsImportanceInherited;

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

	@Column(name="version_info")
	@XmlAttribute(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMBusinessService
	@ManyToOne
	@JoinColumn(name="ts_trandef_group_id")
	@XmlInverseReference(mappedBy="tsTransetGroups")
	private CEMBusinessService tsTranDefGroup;

	//bi-directional many-to-one association to TsTransetGroup
	@ManyToOne
	@JoinColumn(name="ts_parent_id")
	@XmlInverseReference(mappedBy="tsTransetGroups")
	private TsTransetGroup tsTransetGroup;

	//bi-directional many-to-one association to TsTransetGroup
	@OneToMany(mappedBy="tsTransetGroup", fetch=FetchType.EAGER)
	@XmlElementWrapper(name="childTsTransetGroups")
	@XmlElement(name = "item")
	private List<TsTransetGroup> tsTransetGroups;

	//bi-directional many-to-one association to TsTransetgroupTransetsMap
	@OneToMany(mappedBy="tsTransetGroup", fetch=FetchType.EAGER)
	@XmlElementWrapper(name="tsTransetgroupTransetsMaps")
	@XmlElement(name = "item")
	@XmlJavaTypeAdapter(TsTransetgroupTransetsMapAdapter.class)
	private List<TsTransetgroupTransetsMap> tsTransetgroupTransetsMaps;

	public TsTransetGroup() {
	}

	public Long getTsId() {
		return this.tsId;
	}

	public void setTsId(Long tsId) {
		this.tsId = tsId;
	}

	public String getTsDescription() {
		return this.tsDescription;
	}

	public void setTsDescription(String tsDescription) {
		this.tsDescription = tsDescription;
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

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public CEMBusinessService getTsTranDefGroup() {
		return this.tsTranDefGroup;
	}

	public void setTsTranDefGroup(CEMBusinessService tsTranDefGroup) {
		this.tsTranDefGroup = tsTranDefGroup;
	}

	public TsTransetGroup getTsTransetGroup() {
		return this.tsTransetGroup;
	}

	public void setTsTransetGroup(TsTransetGroup tsTransetGroup) {
		this.tsTransetGroup = tsTransetGroup;
	}

	public List<TsTransetGroup> getTsTransetGroups() {
		return this.tsTransetGroups;
	}

	public void setTsTransetGroups(List<TsTransetGroup> tsTransetGroups) {
		this.tsTransetGroups = tsTransetGroups;
	}

	public TsTransetGroup addTsTransetGroup(TsTransetGroup tsTransetGroup) {
		getTsTransetGroups().add(tsTransetGroup);
		tsTransetGroup.setTsTransetGroup(this);

		return tsTransetGroup;
	}

	public TsTransetGroup removeTsTransetGroup(TsTransetGroup tsTransetGroup) {
		getTsTransetGroups().remove(tsTransetGroup);
		tsTransetGroup.setTsTransetGroup(null);

		return tsTransetGroup;
	}

	public List<TsTransetgroupTransetsMap> getTsTransetgroupTransetsMaps() {
		return this.tsTransetgroupTransetsMaps;
	}

	public void setTsTransetgroupTransetsMaps(List<TsTransetgroupTransetsMap> tsTransetgroupTransetsMaps) {
		this.tsTransetgroupTransetsMaps = tsTransetgroupTransetsMaps;
	}

	public TsTransetgroupTransetsMap addTsTransetgroupTransetsMap(TsTransetgroupTransetsMap tsTransetgroupTransetsMap) {
		getTsTransetgroupTransetsMaps().add(tsTransetgroupTransetsMap);
		tsTransetgroupTransetsMap.setTsTransetGroup(this);

		return tsTransetgroupTransetsMap;
	}

	public TsTransetgroupTransetsMap removeTsTransetgroupTransetsMap(TsTransetgroupTransetsMap tsTransetgroupTransetsMap) {
		getTsTransetgroupTransetsMaps().remove(tsTransetgroupTransetsMap);
		tsTransetgroupTransetsMap.setTsTransetGroup(null);

		return tsTransetgroupTransetsMap;
	}

	@Override
	public String toString() {
		return "TsTransetGroup [tsId=" + tsId + ", tsDescription="
				+ tsDescription + ", tsImportanceId=" + tsImportanceId
				+ ", tsImportanceInherited=" + tsImportanceInherited
				+ ", tsName=" + tsName + ", tsSigmaSla=" + tsSigmaSla
				+ ", tsSigmaSlaInherited=" + tsSigmaSlaInherited
				+ ", tsSoftDelete=" + tsSoftDelete + ", tsSuccessRateSla="
				+ tsSuccessRateSla + ", tsSuccessSlaInherited="
				+ tsSuccessSlaInherited + ", tsTranTimeSla=" + tsTranTimeSla
				+ ", tsTranTimeSlaInherited=" + tsTranTimeSlaInherited
				+ ", versionInfo=" + versionInfo + ", tsTranDefGroup.ts_name="
				+ (tsTranDefGroup !=null ?tsTranDefGroup.getTsName() : null) + ", tsTransetGroup.ts_parent_id=" + (tsTransetGroup != null ? tsTransetGroup.getTsId(): null)
				+ ", tsTransetGroups=" + (tsTransetGroups != null ? tsTransetGroups : null)
				+ ", tsTransetgroupTransetsMaps=" + (tsTransetgroupTransetsMaps != null ? tsTransetgroupTransetsMaps : null)
				+ "]";
	}

	
	/**
	 * returns fully qualified service name (because every TsTransetGroup entity is bound to particular CEMBusinessService)
	 * @return
	 */
	@Override
	public String getFQN(){
		return this.tsTranDefGroup.getFQN();
	}

	

	@Override
	public void copyTo(CEMEntity target) {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		TsTransetGroup _target = (TsTransetGroup)target;
		
		log.entry(this, target);
		log.debug("merging...");

		_target.setTsDescription(this.getTsDescription());
		_target.setTsImportanceId(this.getTsImportanceId());
		_target.setTsImportanceInherited(this.getTsImportanceInherited());
		_target.setTsSigmaSla(this.getTsSigmaSla());
		_target.setTsSigmaSlaInherited(this.getTsSigmaSlaInherited());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setTsSuccessRateSla(this.getTsSuccessRateSla());
		_target.setTsSuccessSlaInherited(this.getTsSuccessSlaInherited());
		_target.setTsTranTimeSla(this.getTsTranTimeSla());
		_target.setTsTranTimeSlaInherited(this.getTsTranTimeSlaInherited());
		_target.setVersionInfo(this.getVersionInfo());		
		
		//we are not merging tsTransetgroupTransetsMaps here! This is done in CEMBusinessTransaction entity!
		//we are not merging tsTransetGroups. I believe this attribute is never used in real DB, this is some legacy stuff. If not we can always implement it later
	    log.exit(target);
		
		*/
	}

	
	@PrePersist
	private void prePersistCustomAction(){
		//log.debug("prePersistCustomAction()>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	}
	
	
	/**
	 * code is ready and commented but not supported in this version
	 */
	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		log.entry(this, targetParent);
		CEMBusinessService _targetParent = (CEMBusinessService)targetParent;
		_targetParent.addTsTransetGroup(this);
		log.exit(this);
		*/
		
	}

	/**
	 * code is ready and commented but not supported in this version
	 */
	@Override
	public CEMEntity deepCopy() throws Exception {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		
		TsTransetGroup clone = (TsTransetGroup)super.deepCopy();
		
		//after cloning the entity we need to reset all ts_id attributes across the whole object tree so that
		//JPA will generate new IDs from respective DB sequences.!!!!
		clone.setTsId(null);
		
		for(TsTransetGroup itemTsG : clone.tsTransetGroups){
			itemTsG.setTsId(null);
			
			for(TsTransetgroupTransetsMap itemTsM: this.tsTransetgroupTransetsMaps){
				itemTsM.getId().setTsTransetGroupId(null);
				itemTsM.getId().setTsTransetId(null);
				itemTsM.getId().setTsTransetIncarnationId(null);
			}
			
		}
		
		return clone;
		*/
	}
	
	
	
}