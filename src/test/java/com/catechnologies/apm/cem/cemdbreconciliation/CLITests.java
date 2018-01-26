package com.catechnologies.apm.cem.cemdbreconciliation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Ignore;

import com.catechnologies.apm.cem.cemdbreconciliation.exceptions.CLIValidationException;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.startsWith;
import static org.hamcrest.Matchers.is;

import org.junit.Rule;
import org.apache.commons.cli.*;
import org.junit.rules.ExpectedException;

import com.catechnologies.apm.cem.cemdbreconciliation.util.CLIFactory;
import com.catechnologies.apm.cem.cemdbreconciliation.util.CLIValidator;

import org.apache.commons.cli.MissingArgumentException;

/**
 * https://commons.apache.org/proper/commons-cli/
 * http://www.programcreek.com/java-api-examples/org.apache.commons.cli.CommandLineParser
 * @author bezad01
 *
 */
public class CLITests {

	static final Logger log = LogManager.getLogger(CLITests.class.getName());
	static Options options = null;
	static CommandLineParser parser = null;
	 @Rule
	 public ExpectedException thrown = ExpectedException.none();	
	
	
    @BeforeClass
    public static void setUp(){
    	options = CLIFactory.getCLIDefinition();
    	parser = new DefaultParser();
    	log.info("setUp()");
    }
    
    @AfterClass
    public static void tearDown() {
    	log.info("tearDown()");
    }
	
	@Test
	@Ignore("This is just template!")
    public void testTemplate() {
        assertTrue(true);
        assertFalse(false);
        assertThat(true,is(true));
    }    

	@Test
	public void testExport1() throws ParseException, CLIValidationException{

		String[] args = {"--export"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_NO_ENTITY_TYPE)));
		CLIValidator.validateExport(cmdLine);
		
	}
	
	@Test
	public void testExport2() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type"};

		thrown.expect(MissingArgumentException.class);
		thrown.expectMessage(startsWith("Missing argument for option: et"));
		@SuppressWarnings("unused")
		CommandLine cmdLine = parser.parse(options, args);
		
	}
	
	
	@Test
	public void testExport3() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","EE"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_WRONG_ENTITY_TYPE)));
		CLIValidator.validateExport(cmdLine);
		
	}

	@Test
	public void testExport4() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BA"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_NO_ENTITY_NAME)));
		CLIValidator.validateExport(cmdLine);
		
	}

	
	@Test
	public void testExport5() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BS", "--bs-name"};

		thrown.expect(MissingArgumentException.class);
		thrown.expectMessage(startsWith("Missing argument for option: bsn"));
		@SuppressWarnings("unused")
		CommandLine cmdLine = parser.parse(options, args);
		
	}
	
	@Test
	public void testExport6() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BS", "--bs-name","BusinessService1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BANAME)));
		CLIValidator.validateExport(cmdLine);
		
	}

	@Test
	public void testExport7() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BT", "--bt-name","BusinessTransaction1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateExport(cmdLine);
		
	}

	@Test
	public void testExport8() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BT", "--bt-name","BusinessTransaction1","--ba-name","BusinessAppplication1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateExport(cmdLine);
		
	}

	@Test
	public void testExport9() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BT", "--bt-name","BusinessTransaction1","--bs-name","BusinessService1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateExport(cmdLine);
		
	}
	
	@Test
	public void testExport10() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BT", "--bt-name","BusinessTransaction1","--bs-name","BusinessService1","--ba-name","BusinessApplication1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_NO_EXPORT_FILE)));
		CLIValidator.validateExport(cmdLine);
		
	}

	@Test
	public void testExport11() throws ParseException, CLIValidationException{

		String[] args = {"--export","--entity-type","BT", "--bt-name","BusinessTransaction1",
				"--bs-name","BusinessService1","--ba-name","BusinessApplication1","--export-file","\"c:/tmp/hello world.xml\""};
		CommandLine cmdLine = parser.parse(options, args);
		
		CLIValidator.validateExport(cmdLine);
		
	}
	
	
	@Test
	public void testDelta1() throws ParseException, CLIValidationException{

		String[] args = {"--delta"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateDelta(cmdLine);
		
	}

	@Test
	public void testDelta2() throws ParseException, CLIValidationException{

		String[] args = {"--delta","--ba-name","BA1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateDelta(cmdLine);
		
	}

	@Test
	public void testDelta3() throws ParseException, CLIValidationException{

		String[] args = {"--delta","--bs-name","BS1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_MISSING_BA_OR_BS_NAME)));
		CLIValidator.validateDelta(cmdLine);
		
	}
	
	@Test
	public void testDelta4() throws ParseException, CLIValidationException{

		String[] args = {"--delta","--ba-name"};

		thrown.expect(MissingArgumentException.class);
		thrown.expectMessage(startsWith("Missing argument for option: ban"));
		@SuppressWarnings("unused")
		CommandLine cmdLine = parser.parse(options, args);
		
	}
	
	@Test
	public void testDelta5() throws ParseException, CLIValidationException{

		String[] args = {"--delta","--bs-name"};

		thrown.expect(MissingArgumentException.class);
		thrown.expectMessage(startsWith("Missing argument for option: bsn"));
		@SuppressWarnings("unused")
		CommandLine cmdLine = parser.parse(options, args);
		
	}
	
	@Test
	public void testDelta6() throws ParseException, CLIValidationException{

		String[] args = {"--delta","--ba-name","BusinessApplication1", "--bs-name","BusinessService1"};
		CommandLine cmdLine = parser.parse(options, args);
		
		CLIValidator.validateDelta(cmdLine);
		
	}

	@Test
	public void testMerge1() throws ParseException, CLIValidationException{

		String[] args = {"--merge"};
		CommandLine cmdLine = parser.parse(options, args);
		
		thrown.expect(CLIValidationException.class);
		thrown.expect(hasProperty("invalidReason", is(CLIValidationException.INVALID_REASON_NO_EXPORT_FILE)));
		CLIValidator.validateMerge(cmdLine);
		
	}

	@Test
	public void testMerge2() throws ParseException, CLIValidationException{

		String[] args = {"--merge","/tmp/export.xml","--dry-run"};
		
		thrown.expect(MissingArgumentException.class);
		thrown.expectMessage(startsWith("Missing argument for option: dr"));
		@SuppressWarnings("unused")
		CommandLine cmdLine = parser.parse(options, args);
		
	}
	
	@Test
	public void testMerge3() throws ParseException, CLIValidationException{

		String[] args = {"--merge","--export-file","/tmp/export.xml","--dry-run","/tmp/dryrunoutput.xml"};
		
		CommandLine cmdLine = parser.parse(options, args);
		CLIValidator.validateMerge(cmdLine);
		
	}

	@Test
	public void testMerge4() throws ParseException, CLIValidationException{

		String[] args = {"--merge","--export-file","/tmp/export.xml"};
		
		CommandLine cmdLine = parser.parse(options, args);
		CLIValidator.validateMerge(cmdLine);
		
	}
	
	
	
	
}
