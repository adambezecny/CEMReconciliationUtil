package com.catechnologies.apm.cem.cemdbreconciliation;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * http://www.cavdar.net/2008/07/21/junit-4-in-60-seconds/
 * https://wiki.eclipse.org/EclipseLink/Examples/JPA/JSF_Tutorial
 * http://www.objectdb.com/java/jpa/persistence/retrieve
 * http://www.java2s.com/Tutorials/Java/JPA/4710__JPA_Query_Join_ManyToOne.htm
 * 
 * https://docs.oracle.com/javaee/7/api/javax/persistence/package-summary.html
 * 
 * @author bezad01
 *
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
  JPATests.class,
  EntityMergeTests.class,
  JAXBTests.class,
  CLITests.class
})
public class AppTest {
/**
 * This class is placeholder only. It holds @RunWith &  @Suite 
 * annotations and defines all classes of test suite.	
 */
}