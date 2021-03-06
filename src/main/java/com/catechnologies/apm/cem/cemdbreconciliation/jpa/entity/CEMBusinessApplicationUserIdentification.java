package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ts_login_id_parameters database table.
 * 
 */
@Entity
@Table(name="ts_login_id_parameters")
@NamedQuery(name="CEMBusinessApplicationUserIdentification.findAll", query="SELECT c FROM CEMBusinessApplicationUserIdentification c WHERE c.tsSoftDelete=false")
public class CEMBusinessApplicationUserIdentification extends CEMEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CEMBusinessApplicationUserIdentificationPK id;

	@Column(name="ts_length")
	private Integer tsLength;

	@Column(name="ts_offset")
	private Integer tsOffset;

	@Column(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="version_info")
	private Long versionInfo;

	//bi-directional many-to-one association to CEMBusinessApplication
	@ManyToOne
	@JoinColumn(name="ts_app_id")
	private CEMBusinessApplication tsApp;

	public CEMBusinessApplicationUserIdentification() {
	}

	public CEMBusinessApplicationUserIdentificationPK getId() {
		return this.id;
	}

	public void setId(CEMBusinessApplicationUserIdentificationPK id) {
		this.id = id;
	}

	public Integer getTsLength() {
		return this.tsLength;
	}

	public void setTsLength(Integer tsLength) {
		this.tsLength = tsLength;
	}

	public Integer getTsOffset() {
		return this.tsOffset;
	}

	public void setTsOffset(Integer tsOffset) {
		this.tsOffset = tsOffset;
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

	public CEMBusinessApplication getTsApp() {
		return this.tsApp;
	}

	public void setTsApp(CEMBusinessApplication tsApp) {
		this.tsApp = tsApp;
	}

	@Override
	public String toString() {
		return "CEMBusinessApplicationUserIdentification [id=" + id
				+ ", tsLength=" + tsLength + ", tsOffset=" + tsOffset
				+ ", tsSoftDelete=" + tsSoftDelete + ", versionInfo="
				//+ versionInfo + ", tsApp=" + tsApp + "]";//this would cause cyclic referencing and Stack Overflow
				+ versionInfo + "]";
	}

	@Override
	public void copyTo(CEMEntity target) {
		throw new UnsupportedOperationException("Not supported in current release!");
	}

	@Override
	public void addToTargetParent(CEMEntity targetParent) {
		throw new UnsupportedOperationException("Not supported in current release!");		
	}

	@Override
	public String getFQN() {
		throw new UnsupportedOperationException("Not supported in current release!");
	}
	
}