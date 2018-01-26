package com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity;

import java.io.Serializable;

import javax.persistence.*;

/**
 * The primary key class for the ts_transetgroup_transets_map database table.
 * 
 */
@Embeddable
public class TsTransetgroupTransetsMapPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="ts_transet_id", insertable=false, updatable=false)
	private Long tsTransetId;

	@Column(name="ts_transet_incarnation_id")
	private Long tsTransetIncarnationId;

	@Column(name="ts_transet_group_id", insertable=false, updatable=false)
	private Long tsTransetGroupId;

	public TsTransetgroupTransetsMapPK() {
	}
	
	public Long getTsTransetId() {
		return this.tsTransetId;
	}
	public void setTsTransetId(Long tsTransetId) {
		this.tsTransetId = tsTransetId;
	}
	public Long getTsTransetIncarnationId() {
		return this.tsTransetIncarnationId;
	}
	public void setTsTransetIncarnationId(Long tsTransetIncarnationId) {
		this.tsTransetIncarnationId = tsTransetIncarnationId;
	}
	public Long getTsTransetGroupId() {
		return this.tsTransetGroupId;
	}
	public void setTsTransetGroupId(Long tsTransetGroupId) {
		this.tsTransetGroupId = tsTransetGroupId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TsTransetgroupTransetsMapPK)) {
			return false;
		}
		TsTransetgroupTransetsMapPK castOther = (TsTransetgroupTransetsMapPK)other;
		return 
			this.tsTransetId.equals(castOther.tsTransetId)
			&& this.tsTransetIncarnationId.equals(castOther.tsTransetIncarnationId)
			&& this.tsTransetGroupId.equals(castOther.tsTransetGroupId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tsTransetId.hashCode();
		hash = hash * prime + this.tsTransetIncarnationId.hashCode();
		hash = hash * prime + this.tsTransetGroupId.hashCode();
		
		return hash;
	}
	
	@Override
	public String toString() {
		return "TsTransetgroupTransetsMapPK [tsTransetId=" + tsTransetId
				+ ", tsTransetIncarnationId=" + tsTransetIncarnationId
				+ ", tsTransetGroupId=" + tsTransetGroupId + "]";
	}
	
}