package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.TsTransetGroup;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class TsTransetGroupAdapter extends XmlAdapter<TsTransetGroup, TsTransetGroup> {

	static final Logger log = LogManager.getLogger(TsTransetGroupAdapter.class.getName());
	
	@Override
	public TsTransetGroup unmarshal(TsTransetGroup v) throws Exception {

		if(v.getTsTransetGroups()!=null){
			for(int i=0;i<v.getTsTransetGroups().size();i++){
				v.getTsTransetGroups().get(i).setTsTranDefGroup(v.getTsTranDefGroup());
			}
		}

		if(v.getTsTransetgroupTransetsMaps()!=null){
			for(int i=0;i<v.getTsTransetgroupTransetsMaps().size();i++){
				v.getTsTransetgroupTransetsMaps().get(i).setTsTransetGroup(v);
			}
		}
		
		return v;
	}

	@Override
	public TsTransetGroup marshal(TsTransetGroup v) throws Exception {
		return v;
	}

}
