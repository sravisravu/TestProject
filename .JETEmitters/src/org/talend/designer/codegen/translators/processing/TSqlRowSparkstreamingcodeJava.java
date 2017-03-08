package org.talend.designer.codegen.translators.processing;

import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataColumn;

public class TSqlRowSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TSqlRowSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSqlRowSparkstreamingcodeJava result = new TSqlRowSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = "_FromRowTo";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_6 = NL + "        ";
  protected final String TEXT_7 = " result = new ";
  protected final String TEXT_8 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_9 = ".getClassSchema().getField(structFields[i].name());" + NL + "            if (avroField != null){" + NL + "                result.put(avroField.pos(), row.get(i));" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_10 = NL + NL + "\tpublic static class ";
  protected final String TEXT_11 = "_From";
  protected final String TEXT_12 = "To";
  protected final String TEXT_13 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_14 = ", ";
  protected final String TEXT_15 = "> {" + NL + "" + NL + "\t\tpublic ";
  protected final String TEXT_16 = " call(";
  protected final String TEXT_17 = " input) {" + NL + "\t\t\t";
  protected final String TEXT_18 = " result = new ";
  protected final String TEXT_19 = "();";
  protected final String TEXT_20 = NL + "\t\t\t\tif(input.";
  protected final String TEXT_21 = " != null) {" + NL + "\t\t\t\t\tresult.";
  protected final String TEXT_22 = " = new java.sql.Date(input.";
  protected final String TEXT_23 = ".getTime());" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\tresult.";
  protected final String TEXT_24 = " = null;" + NL + "\t\t\t\t}";
  protected final String TEXT_25 = NL + "\t\t\t\tresult.";
  protected final String TEXT_26 = " = input.";
  protected final String TEXT_27 = ";";
  protected final String TEXT_28 = NL + "\t\t\treturn result;" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_29 = NL + NL + "static class ";
  protected final String TEXT_30 = "_JavaSQLContextSingleton {";
  protected final String TEXT_31 = NL + "\tstatic private transient org.apache.spark.sql.hive.HiveContext instance = null;" + NL + "\tstatic public org.apache.spark.sql.hive.HiveContext getInstance(org.apache.spark.SparkContext sparkContext) {" + NL + "\t\tif (instance == null) {" + NL + "\t  \t\tif(System.getProperty(\"hive.metastore.uris\") == null){" + NL + "\t\t\t\t// There is no Hive component in the job. Metastore will be created locally but we don't want it to be within the current directory." + NL + "\t\t\t\tSystem.setProperty(\"javax.jdo.option.ConnectionURL\", \"jdbc:derby:;databaseName=\"+ System.getProperty(\"java.io.tmpdir\") + \"/\" + jobName + \"/metastore_db;create=true\");" + NL + "\t\t\t}" + NL + "\t\t\tinstance = new org.apache.spark.sql.hive.HiveContext(sparkContext);" + NL + "\t\t\t";
  protected final String TEXT_32 = NL + "\t\t\t\t\tinstance.sql(\"CREATE TEMPORARY FUNCTION \" + ";
  protected final String TEXT_33 = " + \" AS '\" + ";
  protected final String TEXT_34 = " + \"'\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_35 = NL + "\t\t}" + NL + "\t\treturn instance;" + NL + "\t}";
  protected final String TEXT_36 = NL + "\tstatic private transient org.apache.spark.sql.SQLContext instance = null;" + NL + "\tstatic public org.apache.spark.sql.SQLContext getInstance(org.apache.spark.SparkContext sparkContext) {" + NL + "\t\tif (instance == null) {" + NL + "\t\t\tinstance = new org.apache.spark.sql.SQLContext(sparkContext);" + NL + "\t\t\t";
  protected final String TEXT_37 = NL + "\t\t\t\t\tinstance.udf().register(";
  protected final String TEXT_38 = ", new ";
  protected final String TEXT_39 = "(), org.apache.spark.sql.types.DataTypes.";
  protected final String TEXT_40 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_41 = NL + "\t\t}" + NL + "\t\treturn instance;" + NL + "\t}";
  protected final String TEXT_42 = NL + "}" + NL + "" + NL + "public static class Transformer_";
  protected final String TEXT_43 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_44 = ">, org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_45 = ">> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic Transformer_";
  protected final String TEXT_46 = "(JobConf job) {" + NL + "  \t\tthis.context = new ContextProperties(job);" + NL + "  \t}" + NL + "" + NL + " \t@Override" + NL + "  \tpublic org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_47 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_48 = "> ";
  protected final String TEXT_49 = ") throws Exception {" + NL + "  \t";
  protected final String TEXT_50 = NL + "  \t\torg.apache.spark.sql.hive.HiveContext sqlContext = ";
  protected final String TEXT_51 = "_JavaSQLContextSingleton.getInstance(temporaryRdd.context());" + NL + "  \t";
  protected final String TEXT_52 = NL + "  \t\torg.apache.spark.sql.SQLContext sqlContext = ";
  protected final String TEXT_53 = "_JavaSQLContextSingleton.getInstance(temporaryRdd.context());" + NL + "  \t";
  protected final String TEXT_54 = NL + "\t\t\t// SparkSQL will use the default TimeZone to handle dates (see org.apache.spark.sql.catalyst.util.DateTimeUtils)." + NL + "\t\t\t// To avoid inconsistencies with others components in the Studio, ensure that UTC is set as the default TimeZone." + NL + "\t\t\tjava.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone(\"UTC\"));" + NL + "" + NL + "\t\t\t// java.util.Date -> java.sql.Date conversions to be compliant with Spark SQL before creating our DataFrame" + NL + "\t\t\torg.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_55 = "> ";
  protected final String TEXT_56 = " = ";
  protected final String TEXT_57 = ".map(new ";
  protected final String TEXT_58 = "_From";
  protected final String TEXT_59 = "To";
  protected final String TEXT_60 = "());";
  protected final String TEXT_61 = NL + "\t  \t// Create dataframes from incoming schemas and register related tables" + NL + "\t  \t";
  protected final String TEXT_62 = " dataframeInput = sqlContext.createDataFrame(";
  protected final String TEXT_63 = ", ";
  protected final String TEXT_64 = ".class);" + NL + "\t  \tdataframeInput.registerTempTable(\"";
  protected final String TEXT_65 = "\");" + NL + "" + NL + "\t  \t// Execute the sql query" + NL + "\t \t ";
  protected final String TEXT_66 = " dataframeOutput = sqlContext.sql(";
  protected final String TEXT_67 = ");" + NL + "" + NL + "\t  \t// Retrieve the associated RDD" + NL + "\t  \treturn dataframeOutput.toJavaRDD().map(new ";
  protected final String TEXT_68 = "_FromRowTo";
  protected final String TEXT_69 = "());" + NL + "  \t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, true);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
IConnection incomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
final String sqlContext = ElementParameterParser.getValue(node, "__SQL_CONTEXT__");
java.util.List<java.util.Map<String, String>> tempUdfsFunctions = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__TEMP_UDF_FUNCTIONS__");
java.util.List<java.util.Map<String, String>> tempSqlUdfsFunctions = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__TEMP_SQL_UDF_FUNCTIONS__");
String inRddName = "temporaryRdd";

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_9);
    
// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.

org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

if(tSqlRowUtil.containsDateFields(incomingConnection)) {
	java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
	String originalStructName = inStructName;
	String suggestedDfStructName = "DF_"+originalStructName;
	String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, originalStructName);

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_19);
    
		for(IMetadataColumn column : columns) {
			if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_24);
    
			} else {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_27);
    
			}
		} // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_28);
    
	} // end if(tSqlRowUtil.containsDateFields(incomingConnection))

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    
if(("HiveContext").equals(sqlContext)){

    stringBuffer.append(TEXT_31);
    
			// Create temporary function aliases for Hive UDFs
			if(tempUdfsFunctions.size() > 0){
				for(java.util.Map<String, String> item : tempUdfsFunctions){
					
    stringBuffer.append(TEXT_32);
    stringBuffer.append(item.get("TEMPORARY_FUNCTION_ALIAS"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(item.get("UDF_FQCN"));
    stringBuffer.append(TEXT_34);
    
				}
			}
			
    stringBuffer.append(TEXT_35);
    
} else {

    stringBuffer.append(TEXT_36);
    
			// Create temporary function aliases for SQL UDFs
			if(tempSqlUdfsFunctions.size() > 0){
				for(java.util.Map<String, String> item : tempSqlUdfsFunctions){
					
    stringBuffer.append(TEXT_37);
    stringBuffer.append(item.get("TEMPORARY_FUNCTION_ALIAS"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(item.get("UDF_FQCN").replace("\"", ""));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(item.get("DATA_TYPE"));
    stringBuffer.append(TEXT_40);
    
				}
			}
			
    stringBuffer.append(TEXT_41);
    
}

    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_49);
    
  	if(("HiveContext").equals(sqlContext)){
  	
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
  	} else {
  	
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    
  	}
  	
    
    	String rddName, structName;

   	if(tSqlRowUtil.containsDateFields(incomingConnection)) {
	   	// Additional map to convert from java.util.Date to java.sql.Date
	    	String newRddName = "tmp_rdd_"+incomingConnection.getName();
	    	String newStructName = "DF_"+inStructName+"AvroRecord";

    stringBuffer.append(TEXT_54);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_60);
    
			rddName = newRddName;
			structName = newStructName;
		} else {
			// No need for additional map
			rddName = inRddName;
			structName = inStructName;
		}

    stringBuffer.append(TEXT_61);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(tSqlRowUtil.getTableName(incomingConnection));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(tSqlRowUtil.getSqlQuery());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_69);
    return stringBuffer.toString();
  }
}
