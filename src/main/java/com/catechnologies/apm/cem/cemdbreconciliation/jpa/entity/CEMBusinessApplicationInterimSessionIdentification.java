package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ts_interim_session_id_params database table.
 * 
 */
@Entity
@Table(name="ts_interim_session_id_params")
@NamedQuery(name="CEMBusinessApplicationInterimSessionIdentification.findAll", query="SELECT c FROM CEMBusinessApplicationInterimSessionIdentification c WHERE c.tsSoftDelete=false")
public class CEMBusinessApplicationInterimSessionIdentification extends CEMEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CEMBusinessApplicationInterimSessionIdentificationPK id;

	@Column(name="ts_length")
	private Integer tsLength;

	@Column(name="ts_offset")
	private Integer tsOffset;

	@Column(name="ts_soft_delete")
	private Boolean tsSoftDelete;

	@Column(name="version_info")
	private Long versionInfo;

	public CEMBusinessApplicationInterimSessionIdentification() {
	}

	public CEMBusinessApplicationInterimSessionIdentificationPK getId() {
		return this.id;
	}

	public void setId(CEMBusinessApplicationInterimSessionIdentificationPK id) {
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

	@Override
	public String toString() {
		return "CEMBusinessApplicationInterimSessionIdentification [id=" + id
				+ ", tsLength=" + tsLength + ", tsOffset=" + tsOffset
				+ ", tsSoftDelete=" + tsSoftDelete + ", versionInfo="
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