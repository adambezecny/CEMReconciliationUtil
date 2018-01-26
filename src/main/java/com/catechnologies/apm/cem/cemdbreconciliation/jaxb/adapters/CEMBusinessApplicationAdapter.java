package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessApplication;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessService;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessTransaction;
import java.util.ArrayList;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class CEMBusinessApplicationAdapter extends XmlAdapter<CEMBusinessApplication, CEMBusinessApplication> {

	static final Logger log = LogManager.getLogger(CEMBusinessApplicationAdapter.class.getName());
	
	
	@Override
	public CEMBusinessApplication unmarshal(CEMBusinessApplication v) throws Exception {
		
		
		v.setTsTransets(new ArrayList<CEMBusinessTransaction>());
		
		for(CEMBusinessService itemBS : v.getTsTranDefGroups()){
			itemBS.setTsApp(v);

			for(CEMBusinessTransaction itemBT : itemBS.getTsTransets())
				v.addTsTranset(itemBT);
			
		}	
		
		return v;
	}

	@Override
	public CEMBusinessApplication marshal(CEMBusinessApplication v)	throws Exception {
		return v;
	}

}
