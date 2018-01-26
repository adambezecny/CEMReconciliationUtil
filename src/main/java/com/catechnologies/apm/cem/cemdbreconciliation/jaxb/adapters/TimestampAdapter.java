package com.catechnologies.apm.cem.cemdbreconciliation.jaxb.adapters;

import java.sql.Timestamp;
import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * This class enables serialization/deserialization of java.sql.Timestamp which is not possible by default
 * since it does not contain public constructor with no args as required by JAXB implementation.
 * For sample implementations of java.sql.Timestamp adapter see links below:
 * http://blog.2partsmagic.com/2009/04/resolving-javasqltimestamp-does-not-have-a-no-arg-default-constructor/
 * http://stackoverflow.com/questions/24489242/creating-adapter-for-timestamp-for-jaxb-conversion
 * @author bezad01
 *
 */
public class TimestampAdapter extends XmlAdapter<String,Timestamp>  {

	@Override
	public Timestamp unmarshal(String v) throws Exception {
		return (v == null ? null : Timestamp.valueOf(v));
	}

	@Override
	public String marshal(Timestamp v) throws Exception {
		return (v == null ? null : v.toString());
	}

	public static void main(String[] args){
		
		Timestamp tsIn = new Timestamp(new java.util.Date().getTime());//2016-02-23 17:11:59.99
		String tsInStr = tsIn.toString();
		
		System.out.println("tsInStr="+tsInStr);
		
		Timestamp deserializedTimestamp = Timestamp.valueOf(tsInStr);
		System.out.println("deserializedTimestamp="+deserializedTimestamp);
		
	}
	
}
