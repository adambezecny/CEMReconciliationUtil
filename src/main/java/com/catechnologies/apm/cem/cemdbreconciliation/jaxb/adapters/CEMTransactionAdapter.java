package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMTransaction;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class CEMTransactionAdapter extends XmlAdapter<CEMTransaction, CEMTransaction> {

	static final Logger log = LogManager.getLogger(CEMTransactionAdapter.class.getName());

	@Override
	public CEMTransaction unmarshal(CEMTransaction v) throws Exception {
		
		if(v.getTsTrancomps()!=null){
			for(int i=0;i<v.getTsTrancomps().size();i++){
				v.getTsTrancomps().get(i).setTsTranunit(v);
			}
		}

		if(v.getTsDefectDefs()!=null){
			for(int i=0;i<v.getTsDefectDefs().size();i++){
				v.getTsDefectDefs().get(i).setTsTranunit(v);
			}
		}
		
		
		
		return v;
	}

	@Override
	public CEMTransaction marshal(CEMTransaction v) throws Exception {

		return v;
	}
	
	
}
