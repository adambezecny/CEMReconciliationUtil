package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMTransactionComponent;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class CEMTransactionComponentAdapter extends XmlAdapter<CEMTransactionComponent, CEMTransactionComponent> {

	static final Logger log = LogManager.getLogger(CEMTransactionComponentAdapter.class.getName());
	
	
	@Override
	public CEMTransactionComponent unmarshal(CEMTransactionComponent v)	throws Exception {
		
		if(v.getTsParams()!=null){
			for(int i=0;i<v.getTsParams().size();i++){
				v.getTsParams().get(i).setTsTrancomp(v);
			}
		}

		if(v.getTsDefectDefs()!=null){
			for(int i=0;i<v.getTsDefectDefs().size();i++){
				v.getTsDefectDefs().get(i).setTsTrancomp(v);
			}
		}
		
		
		return v;
	}

	@Override
	public CEMTransactionComponent marshal(CEMTransactionComponent v)	throws Exception {
		return v;
	}

}
