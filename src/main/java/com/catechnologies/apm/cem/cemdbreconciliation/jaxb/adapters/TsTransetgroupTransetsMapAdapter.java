package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetgroupTransetsMap;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class TsTransetgroupTransetsMapAdapter extends XmlAdapter<TsTransetgroupTransetsMapAdapted, TsTransetgroupTransetsMap> {

	static final Logger log = LogManager.getLogger(TsTransetgroupTransetsMapAdapter.class.getName());

	@Override
	public TsTransetgroupTransetsMap unmarshal(TsTransetgroupTransetsMapAdapted v) throws Exception {
		return new TsTransetgroupTransetsMap(v);
	}

	@Override
	public TsTransetgroupTransetsMapAdapted marshal(TsTransetgroupTransetsMap v) throws Exception {
		return new TsTransetgroupTransetsMapAdapted(v);
	}
	
	
}
