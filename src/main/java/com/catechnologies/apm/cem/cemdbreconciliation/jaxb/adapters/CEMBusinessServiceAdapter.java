package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessService;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessTransaction;


/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class CEMBusinessServiceAdapter extends XmlAdapter<CEMBusinessService, CEMBusinessService> {

	static final Logger log = LogManager.getLogger(CEMBusinessServiceAdapter.class.getName());
	
	@Override
	public CEMBusinessService unmarshal(CEMBusinessService v) throws Exception {
		
		if(v.getTsTransets()!=null){
			for(CEMBusinessTransaction itemBT : v.getTsTransets()){
				itemBT.setTsTranDefGroup(v);
			}	
		}
		
		return v;
	}

	@Override
	public CEMBusinessService marshal(CEMBusinessService v) throws Exception {
		return v;
	}

}
