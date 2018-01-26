package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Timestamp;

import com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters.TsTransetgroupTransetsMapAdapted;


/**
 * The persistent class for the ts_transetgroup_transets_map database table.
 * 
 */
@Entity
@Table(name="ts_transetgroup_transets_map")
@NamedQuery(name="TsTransetgroupTransetsMap.findAll", query="SELECT t FROM TsTransetgroupTransetsMap t")
public class TsTransetgroupTransetsMap extends CEMEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	static final Logger log = LogManager.getLogger(TsTransetgroupTransetsMap.class.getName());


	@EmbeddedId
	private TsTransetgroupTransetsMapPK id;

	@Column(name="ts_create_date")
	private Timestamp tsCreateDate;

	@Column(name="ts_delete_date")
	private Timestamp tsDeleteDate;

	@Column(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to TsTransetGroup
	@ManyToOne
	@JoinColumn(name="ts_transet_group_id")
	private TsTransetGroup tsTransetGroup;

	//bi-directional many-to-one association to CEMBusinessTransaction
	@ManyToOne
	@JoinColumn(name="ts_transet_id")
	private CEMBusinessTransaction tsTranset;

	public TsTransetgroupTransetsMap() {
	}

	public TsTransetgroupTransetsMap(TsTransetgroupTransetsMapAdapted obj) {
		
		if(this.id==null)
			this.id = new TsTransetgroupTransetsMapPK();
		
		this.id.setTsTransetId(obj.getTsTransetId());
		this.id.setTsTransetIncarnationId(obj.getTsTransetIncarnationId());
		this.id.setTsTransetGroupId(obj.getTsTransetGroupId());
		
		this.tsCreateDate = obj.getTsCreateDate();
		this.tsDeleteDate = obj.getTsDeleteDate();
		this.tsSoftDelete = obj.getTsSoftDelete();
		this.versionInfo = obj.getVersionInfo();
		this.tsTransetGroup = obj.getTsTransetGroup();
		this.tsTranset = obj.getTsTranset();
		
	}
	
	
	public TsTransetgroupTransetsMapPK getId() {
		return this.id;
	}

	public void setId(TsTransetgroupTransetsMapPK id) {
		this.id = id;
	}

	public Timestamp getTsCreateDate() {
		return this.tsCreateDate;
	}

	public void setTsCreateDate(Timestamp tsCreateDate) {
		this.tsCreateDate = tsCreateDate;
	}

	public Timestamp getTsDeleteDate() {
		return this.tsDeleteDate;
	}

	public void setTsDeleteDate(Timestamp tsDeleteDate) {
		this.tsDeleteDate = tsDeleteDate;
	}

	public Boolean getTsSoftDelete() {
		return this.tsSoftDelete;
	}

	@Override
	public void setTsSoftDelete(Boolean tsSoftDelete) {
		this.tsSoftDelete = tsSoftDelete;
	}

	public Long getVersionInfo() {
		return this.versionInfo;
	}

	public void setVersionInfo(Long versionInfo) {
		this.versionInfo = versionInfo;
	}

	public TsTransetGroup getTsTransetGroup() {
		return this.tsTransetGroup;
	}

	public void setTsTransetGroup(TsTransetGroup tsTransetGroup) {
		this.tsTransetGroup = tsTransetGroup;
	}

	public CEMBusinessTransaction getTsTranset() {
		return this.tsTranset;
	}

	public void setTsTranset(CEMBusinessTransaction tsTranset) {
		this.tsTranset = tsTranset;
	}

	
	@Override
	public String getFQN() {
		
		/**
		 * 
		 * 
		 * 
		 * 					CEMBusinessService
		 * 							|	
		 * 		 -------------------------
		 * 		|						  |
		 * 		|						  |				
		 * 		v						  v	
		 * TransetGroup				CEMBusinessTransaction
		 * 		|						  |------------------------------
		 *      |						  |								|	
		 * 		|						  v							    v 
		 * 		|--------------->	TsTransetgroupTransetsMap		CEMTransaction
		 * 																| 	
		 * 																v	
		 * 															CEMTransactionComponent
		 * 																|
		 * 																v 
		 * 															CEMTransactionComponentIdentification
		 */
		
		return this.tsTransetGroup.getFQN() + "_" + this.tsTranset.getFQN();
		
	}
	

	@Override
	public String toString() {
		return "TsTransetgroupTransetsMap [id=" + id + ", tsCreateDate="
				+ tsCreateDate + ", tsDeleteDate=" + tsDeleteDate
				+ ", tsSoftDelete=" + tsSoftDelete + ", versionInfo="
				+ versionInfo + "]";
	}

	@Override
	public void copyTo(CEMEntity target) {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		TsTransetgroupTransetsMap _target = (TsTransetgroupTransetsMap)target;
		
		log.entry(this, target);
		log.debug("merging...");

		_target.setTsCreateDate(this.getTsCreateDate());
		_target.setTsDeleteDate(this.getTsDeleteDate());
		_target.setTsSoftDelete(this.getTsSoftDelete());
		_target.setVersionInfo(this.getVersionInfo());
		
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
		CEMBusinessTransaction _targetParent = (CEMBusinessTransaction)targetParent;
		
		
		_targetParent.addTsTransetgroupTransetsMap(this);

		//we are mapping only top level ts_transet_groups (the one where ts_parent_id=null) to CEMBusinessService entity
		//because of this we can call ...get(0) below
		_targetParent.getTsTranDefGroup().getTsTransetGroups().get(0).addTsTransetgroupTransetsMap(this);
		*/
		
	}

	/**
	 * code is ready and commented but not supported in this version
	 */
	@Override
	public CEMEntity deepCopy() throws Exception {
		
		throw new UnsupportedOperationException("Not supported in current release!");
		/*
		TsTransetgroupTransetsMap clone = (TsTransetgroupTransetsMap)super.deepCopy();
		
		clone.id.setTsTransetGroupId(null);
		clone.id.setTsTransetId(null);
		clone.id.setTsTransetIncarnationId(null);

		return clone;
		*/
	}

	
	
	
}