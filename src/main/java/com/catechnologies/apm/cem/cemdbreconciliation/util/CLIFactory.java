package com.catechnologies.apm.cem.cemdbreconciliation.util;


import org.apache.commons.cli.*;

/**
 * Factory class holding the definition of command line interface.
 * @author bezad01
 *
 */
public class CLIFactory {

	public static final String CLI_OPT_HELP = "help";
	public static final String CLI_OPT_EXPORT = "export";
	public static final String CLI_OPT_MERGE  = "merge";
	public static final String CLI_OPT_DELTA  = "delta";
	public static final String CLI_OPT_ENTITY_TYPE = "entity-type";
	public static final String CLI_OPT_BA_NAME = "ba-name";
	public static final String CLI_OPT_BS_NAME = "bs-name";
	public static final String CLI_OPT_BT_NAME = "bt-name";
	
	public static final String CLI_OPT_EXPORT_FILE = "export-file";
	public static final String CLI_OPT_EXPORT_INVERSE = "export-from-targetdb";
	
	public static final String CLI_OPT_IMPORT_DRY_RUN = "dry-run";
	
	
	public static final String CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BA = "BA";//business application
	public static final String CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BS = "BS";//business service
	public static final String CLI_OPT_EXPORT_ENTITY_ALLOWED_TYPE_BT = "BT";//business transaction
	
	/**
	 * returns definition of command line interface of the application
	 * @return
	 */
	public static Options getCLIDefinition(){
		
		Option helpOption = Option.builder("h")
                .longOpt(CLI_OPT_HELP)
                .required(false)
                .desc("Displays help.")
                .build();		

		Option exportOption = Option.builder("e")
                .longOpt(CLI_OPT_EXPORT)
                .required(false)
                .desc("Export data from source DB.")
                .build();	
		
		Option mergeOption = Option.builder("m")
                .longOpt(CLI_OPT_MERGE)
                .required(false)
                .desc("Merges XML export data to target DB.")
                .build();		

		Option deltaOption = Option.builder("d")
                .longOpt(CLI_OPT_DELTA)
                .required(false)
                .desc("Compares source and target data. List all target transactions not defined in source DB any more.")
                .build();		
		
		
		Option entityTypeOption = Option.builder("et")
                .longOpt(CLI_OPT_ENTITY_TYPE)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("Entity type which will be exported. Possible argument values: \nBA - business application \nBS - business service \nBT business transaction.\nThis switch is used together with -e switch.")
                .build();		

		Option baNameOption = Option.builder("ban")
                .longOpt(CLI_OPT_BA_NAME)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("The name of business application. Example -ban /sample/hello.jsp/ \nIf entity name contains spaces wrap the whole name in double quatation marks, e.g. -ban \"Business Service 1\"  ")
                .build();		

		Option bsNameOption = Option.builder("bsn")
                .longOpt(CLI_OPT_BS_NAME)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("The name of business service. Example -bsn /sample/hello.jsp/ \nIf entity name contains spaces wrap the whole name in double quatation marks, e.g. -bsn \"Business Service 1\"  ")
                .build();		

		Option btNameOption = Option.builder("btn")
                .longOpt(CLI_OPT_BT_NAME)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("The name of business transaction. Example -btn /sample/hello.jsp/ \nIf entity name contains spaces wrap the whole name in double quatation marks, e.g. -btn \"Business Service 1\"  ")
                .build();		
		
		
		
		Option exportFileOption = Option.builder("ef")
                .longOpt(CLI_OPT_EXPORT_FILE)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("Path to file where export data will be created or where export file already exist. E.g. c:/tmp/app1.xml. This switch is used together with either -e (when exporting source DB data) or -m (when merging existing export file with target DB data).")
                .build();		

		Option exportInverseOption = Option.builder("eftdb")
                .longOpt(CLI_OPT_EXPORT_INVERSE)
                .numberOfArgs(0)
                .required(false)
                .desc("Exports given business transaction from target db as opposed to source db(default).")
                .build();		
		
		
		Option dryRunImportOption = Option.builder("dr")
                .longOpt(CLI_OPT_IMPORT_DRY_RUN)
                .numberOfArgs(1)
                .required(false)
                .type(String.class)
                .desc("Merged source and target data will be not persisted to database but to XML file instead. This file can be examined then before running real merge where merged data will be stored to target database. Provide file name as an argument, e.g. /tmp/dryRunOutput.xml")
                .build();		
		
		
		
		Options options = new Options();
		
		OptionGroup optGroup = new OptionGroup();
		
		optGroup.addOption(helpOption)
			    .addOption(exportOption)
			    .addOption(mergeOption)
			    .addOption(deltaOption);

		options.addOptionGroup(optGroup);
		options.addOption(entityTypeOption)
			   .addOption(baNameOption)
			   .addOption(bsNameOption)
			   .addOption(btNameOption)
			   .addOption(exportFileOption)
			   .addOption(dryRunImportOption)
			   .addOption(exportInverseOption);
		
		
		return options;
	}
	
	public static void printHelp(){

		String header = "CA CEM DB Reconciliation Utility\n(C) CA Technologies, 2016\n\n";
		String footer = "\nUsage examples:\n"+
				"\n\nrunme.bat --delta --ba-name \"Business App Name 1\" --bs-name \"Business Service Name 1\""+
				"\n\nrunme.bat --export --entity-type BA --export-file \"c:/tmp/BA.xml\" --ba-name \"Business App Name 1\""+
				"\n\nrunme.bat --export --entity-type BA --export-file \"c:/tmp/BA.xml\" --ba-name \"Business App Name 1\" --export-from-targetdb"+
				"\n\nrunme.bat --export --entity-type BS --export-file \"c:/tmp/BS.xml\" --ba-name \"Business App Name 1\" --bs-name \"Business Service Name 1\""+
				"\n\nrunme.bat --export --entity-type BS --export-file \"c:/tmp/BS.xml\" --ba-name \"Business App Name 1\" --bs-name \"Business Service Name 1\"  --export-from-targetdb"+
				"\n\nrunme.bat --export --entity-type BT --export-file \"c:/tmp/BT.xml\" --ba-name \"Business App Name 1\" --bs-name \"Business Service Name 1\" --bt-name \"Business Transaction Name 1\""+
				"\n\nrunme.bat --export --entity-type BT --export-file \"c:/tmp/BT.xml\" --ba-name \"Business App Name 1\" --bs-name \"Business Service Name 1\" --bt-name \"Business Transaction Name 1\" --export-from-targetdb"+
				"\n\nrunme.bat --help"+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BA.xml\" --dry-run \"c:/tmp/BAout.xml\""+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BA.xml\""+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BS.xml\" --dry-run \"c:/tmp/BSout.xml\""+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BS.xml\""+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BT.xml\" --dry-run \"c:/tmp/BTout.xml\""+
				"\n\nrunme.bat --merge --export-file \"c:/tmp/BT.xml\""+
				""+/*extension placeholder*/
				"\n\nFor help with this tool contact CA Services please.";
		
		
		HelpFormatter formatter = new HelpFormatter();
		
		formatter.printHelp(200, "runme.bat", header, getCLIDefinition(), footer, true);
		
		
	}
}

