package org.talend.designer.codegen.translators.processing;

import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TSqlRowSparkconfigJava
{
  protected static String nl;
  public static synchronized TSqlRowSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSqlRowSparkconfigJava result = new TSqlRowSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_2 = " = null;";
  protected final String TEXT_3 = NL + "\tif(globalMap.getLocal(\"tsqlRow_sqlContext_29022016\") == null){" + NL + "\t\tsqlContext_";
  protected final String TEXT_4 = " = new org.apache.spark.sql.SQLContext(ctx.sc());" + NL + "\t\tglobalMap.putLocal(\"tsqlRow_sqlContext_29022016\", sqlContext_";
  protected final String TEXT_5 = ");" + NL + "\t}else{" + NL + "\t\tsqlContext_";
  protected final String TEXT_6 = " = (org.apache.spark.sql.SQLContext) globalMap.getLocal(\"tsqlRow_sqlContext_29022016\");" + NL + "\t}" + NL + "\t";
  protected final String TEXT_7 = NL + "\t\t\tsqlContext_";
  protected final String TEXT_8 = ".udf().register(";
  protected final String TEXT_9 = ", new ";
  protected final String TEXT_10 = "(), org.apache.spark.sql.types.DataTypes.";
  protected final String TEXT_11 = ");" + NL + "\t\t\t";
  protected final String TEXT_12 = NL + "\tif(System.getProperty(\"hive.metastore.uris\") == null){" + NL + "\t\t// There is no Hive component in the job. Metastore will be created locally but we don't want it to be within the current directory." + NL + "\t\tSystem.setProperty(\"javax.jdo.option.ConnectionURL\", \"jdbc:derby:;databaseName=\"+ System.getProperty(\"java.io.tmpdir\") + \"/\" + jobName + \"/metastore_db;create=true\");" + NL + "\t}" + NL + "\tif(globalMap.getLocal(\"tsqlRow_hiveContext_29022016\") == null){" + NL + "\t\tsqlContext_";
  protected final String TEXT_13 = " = new org.apache.spark.sql.hive.HiveContext(ctx.sc());" + NL + "\t\tglobalMap.putLocal(\"tsqlRow_hiveContext_29022016\", sqlContext_";
  protected final String TEXT_14 = ");" + NL + "\t}else{" + NL + "\t\tsqlContext_";
  protected final String TEXT_15 = " = (org.apache.spark.sql.hive.HiveContext) globalMap.getLocal(\"tsqlRow_hiveContext_29022016\");" + NL + "\t}" + NL + "\t";
  protected final String TEXT_16 = NL + "\t\t\tsqlContext_";
  protected final String TEXT_17 = ".sql(\"CREATE TEMPORARY FUNCTION \" + ";
  protected final String TEXT_18 = " + \" AS '\" + ";
  protected final String TEXT_19 = " + \"'\");" + NL + "\t\t\t";
  protected final String TEXT_20 = NL + "\t\t// SparkSQL will use the default TimeZone to handle dates (see org.apache.spark.sql.catalyst.util.DateTimeUtils)." + NL + "\t\t// To avoid inconsistencies with others components in the Studio, ensure that UTC is set as the default TimeZone." + NL + "\t\tjava.util.TimeZone.setDefault(java.util.TimeZone.getTimeZone(\"UTC\"));" + NL + "" + NL + "\t\t// java.util.Date -> java.sql.Date conversions to be compliant with Spark SQL before creating our DataFrame" + NL + "\t\torg.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_21 = "> ";
  protected final String TEXT_22 = " = ";
  protected final String TEXT_23 = ".map(new ";
  protected final String TEXT_24 = "_From";
  protected final String TEXT_25 = "To";
  protected final String TEXT_26 = "());";
  protected final String TEXT_27 = NL + "\t// Create dataframes from incoming schemas and register related tables";
  protected final String TEXT_28 = NL + "    ";
  protected final String TEXT_29 = " df_";
  protected final String TEXT_30 = "_";
  protected final String TEXT_31 = " = sqlContext_";
  protected final String TEXT_32 = ".createDataFrame(";
  protected final String TEXT_33 = NL + "            ";
  protected final String TEXT_34 = ", ";
  protected final String TEXT_35 = ".class);" + NL + "    df_";
  protected final String TEXT_36 = "_";
  protected final String TEXT_37 = ".registerTempTable(\"";
  protected final String TEXT_38 = "\");";
  protected final String TEXT_39 = NL + NL + "// Execute the sql query";
  protected final String TEXT_40 = NL;
  protected final String TEXT_41 = " df_";
  protected final String TEXT_42 = " = sqlContext_";
  protected final String TEXT_43 = ".sql(";
  protected final String TEXT_44 = ");" + NL + "" + NL + "// Retrieve the associated RDD" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_45 = "> rdd_";
  protected final String TEXT_46 = " = df_";
  protected final String TEXT_47 = ".toJavaRDD().map(" + NL + "    new ";
  protected final String TEXT_48 = "_FromRowTo";
  protected final String TEXT_49 = "());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
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
final String sqlContext = ElementParameterParser.getValue(node, "__SQL_CONTEXT__");
java.util.List<java.util.Map<String, String>> tempUdfsFunctions = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__TEMP_UDF_FUNCTIONS__");

java.util.List<java.util.Map<String, String>> tempSqlUdfsFunctions = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__TEMP_SQL_UDF_FUNCTIONS__");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
if(("SQLContext").equals(sqlContext)){
	
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    
	// Create temporary function aliases for SQL UDFs
	if(tempSqlUdfsFunctions.size() > 0){
		for(java.util.Map<String, String> item : tempSqlUdfsFunctions){
			
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(item.get("TEMPORARY_FUNCTION_ALIAS"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(item.get("UDF_FQCN").replace("\"", ""));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(item.get("DATA_TYPE"));
    stringBuffer.append(TEXT_11);
    
		}
	}
} else if(("HiveContext").equals(sqlContext)){
	
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
	// Create temporary function aliases for Hive UDFs
	if(tempUdfsFunctions.size() > 0){
		for(java.util.Map<String, String> item : tempUdfsFunctions){
			
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(item.get("TEMPORARY_FUNCTION_ALIAS"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(item.get("UDF_FQCN"));
    stringBuffer.append(TEXT_19);
    
		}
	}
}

for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
    String inRddName = "rdd_"+incomingConnection.getName();

    String rddName, structName;

   if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        // Additional map to convert from java.util.Date to java.sql.Date
    	String newRddName = "tmp_rdd_"+incomingConnection.getName();
    	String newStructName = "DF_"+inStructName+"AvroRecord";

    stringBuffer.append(TEXT_20);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_26);
    
		rddName = newRddName;
		structName = newStructName;
	} else {
		// No need for additional map
		rddName = inRddName;
		structName = inStructName;
	}

    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(tSqlRowUtil.getTableName(incomingConnection));
    stringBuffer.append(TEXT_38);
    
} // end for

    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(tSqlRowUtil.getSqlQuery());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_49);
    return stringBuffer.toString();
  }
}
