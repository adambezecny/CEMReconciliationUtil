package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.catechnologies.apm.cem.cemdbreconciliation.jpa.entity.CEMBusinessTransaction;

/**
 * XML adapters are used to adjust default JAXB serialization/deserialization process. Typically they are used to set up
 * back pointers of child classes towards their parents during deserialization. 
 * @author bezad01
 *
 */
public class CEMBusinessTransactionAdapter extends XmlAdapter<CEMBusinessTransaction, CEMBusinessTransaction> {

	static final Logger log = LogManager.getLogger(CEMBusinessTransactionAdapter.class.getName());
	
	
	@Override
	public CEMBusinessTransaction unmarshal(CEMBusinessTransaction v) throws Exception {
		
		if(v.getTsTranunits()!=null){
			for(int i=0;i<v.getTsTranunits().size();i++){
				v.getTsTranunits().get(i).setTsTranset(v);
				
				//we must also set reference to deserialized CEMBusinessTransaction to CEMSpecifications associated
				//with underlying transactions. This is not possible to do in CEMTransactionAdapter (it does not see above lying deserialized CEMBusinessTransaction)!
				for(int ii=0;ii < v.getTsTranunits().get(i).getTsDefectDefs().size();ii++){
					v.getTsTranunits().get(i).getTsDefectDefs().get(ii).setTsTranset(v);
				}
				
			}
		}

		if(v.getTsDefectDefs()!=null){
			for(int i=0;i<v.getTsDefectDefs().size();i++){
				v.getTsDefectDefs().get(i).setTsTranset(v);
			}
		}

		
		return v;
	}

	@Override
	public CEMBusinessTransaction marshal(CEMBusinessTransaction v) throws Exception {
		return v;
	}

}
