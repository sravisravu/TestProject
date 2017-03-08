package org.talend.designer.codegen.translators.databases.hive;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.ElementParameterParser;;

public class THiveInputSparkconfigJava
{
  protected static String nl;
  public static synchronized THiveInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THiveInputSparkconfigJava result = new THiveInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "System.setProperty(\"hive.metastore.uris\", \"thrift://\" + ";
  protected final String TEXT_2 = " + \":\" + ";
  protected final String TEXT_3 = " + \"/\");" + NL;
  protected final String TEXT_4 = NL + "        System.setProperty(\"hive.metastore.sasl.enabled\", \"true\");" + NL + "        System.setProperty(\"hive.security.authorization.enabled\", \"false\");" + NL + "        System.setProperty(\"hive.metastore.kerberos.principal\", ";
  protected final String TEXT_5 = ");" + NL + "        System.setProperty(\"hive.metastore.execute.setugi\", \"true\");";
  protected final String TEXT_6 = NL + "        System.setProperty(\"fs.defaultFS\", ";
  protected final String TEXT_7 = ");";
  protected final String TEXT_8 = NL + "    org.apache.spark.sql.hive.HiveContext hiveContext_";
  protected final String TEXT_9 = " = new org.apache.spark.sql.hive.HiveContext(ctx.sparkContext().sc());" + NL;
  protected final String TEXT_10 = NL + "    org.apache.spark.sql.hive.HiveContext hiveContext_";
  protected final String TEXT_11 = " = new org.apache.spark.sql.hive.HiveContext(ctx.sc());" + NL;
  protected final String TEXT_12 = NL + "\t\thiveContext_";
  protected final String TEXT_13 = ".sql(\"CREATE TEMPORARY FUNCTION \" + ";
  protected final String TEXT_14 = " + \" AS '\" + ";
  protected final String TEXT_15 = " + \"'\");" + NL + "\t\t";
  protected final String TEXT_16 = NL + "    hiveContext_";
  protected final String TEXT_17 = ".sql(\"USE \" + ";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "    ";
  protected final String TEXT_20 = " dfQueryResult_";
  protected final String TEXT_21 = " = hiveContext_";
  protected final String TEXT_22 = ".sql(\"SELECT * FROM \" + ";
  protected final String TEXT_23 = ");";
  protected final String TEXT_24 = NL + "    hiveContext_";
  protected final String TEXT_25 = ".sql(\"USE \" + ";
  protected final String TEXT_26 = ");";
  protected final String TEXT_27 = NL + "    ";
  protected final String TEXT_28 = " dfQueryResult_";
  protected final String TEXT_29 = " = hiveContext_";
  protected final String TEXT_30 = ".sql(";
  protected final String TEXT_31 = ");";
  protected final String TEXT_32 = NL + "    ";
  protected final String TEXT_33 = " dfQueryResult_";
  protected final String TEXT_34 = " = hiveContext_";
  protected final String TEXT_35 = ".read().format(\"orc\").load(";
  protected final String TEXT_36 = " + ";
  protected final String TEXT_37 = ");";
  protected final String TEXT_38 = NL;
  protected final String TEXT_39 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_40 = "> rdd_";
  protected final String TEXT_41 = " = dfQueryResult_";
  protected final String TEXT_42 = ".toJavaRDD().map(" + NL + "        new ";
  protected final String TEXT_43 = "_FromRowTo";
  protected final String TEXT_44 = "());";

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

    
// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with InputFormat or native Dataframe API.
final java.util.List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
INode sparkConfig = null;
if(sparkConfigs != null && sparkConfigs.size() > 0) {
    sparkConfig = sparkConfigs.get(0);
}

boolean useLocalMode = false;
org.talend.hadoop.distribution.component.SparkBatchComponent sparkBatchDistrib = null;
if(sparkConfig != null) {
    String sparkDistribution = ElementParameterParser.getValue(sparkConfig, "__DISTRIBUTION__");
    String sparkVersion = ElementParameterParser.getValue(sparkConfig, "__SPARK_VERSION__");

    useLocalMode = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SPARK_LOCAL_MODE__"));
    if(!useLocalMode) {
        try {
            sparkBatchDistrib = (org.talend.hadoop.distribution.component.SparkBatchComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(sparkDistribution, sparkVersion);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(false, true);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
java.util.List<java.util.Map<String, String>> tempUdfsFunctions = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__TEMP_UDF_FUNCTIONS__");
String inputSource = ElementParameterParser.getValue(node, "__INPUT_SOURCE__");

// Get Hive & HDFS configurations
String hiveConfiguration = ElementParameterParser.getValue(node, "__HIVE_STORAGE_CONFIGURATION__");
String hdfsConfiguration = ElementParameterParser.getValue(node, "__HDFS_STORAGE_CONFIGURATION__");
INode hiveConfigurationNode = null;
INode hdfsConfigurationNode = null;
String hiveThriftMetaStoreHost = null;
String hiveThriftMetaStorePort = null;
String hdfsNamenodeURI = null;
boolean useKrb = false;
String hivePrincipal = null;

for (INode pNode1 : node.getProcess().getNodesOfType("tHiveConfiguration")) {
    if(hiveConfiguration!=null && hiveConfiguration.equals(pNode1.getUniqueName())) {
        hiveConfigurationNode = pNode1;
        hiveThriftMetaStoreHost = ElementParameterParser.getValue(hiveConfigurationNode, "__HOST__");
        hiveThriftMetaStorePort = ElementParameterParser.getValue(hiveConfigurationNode, "__PORT__");
        useKrb = "true".equals(ElementParameterParser.getValue(hiveConfigurationNode, "__USE_KRB__"));
        hivePrincipal = ElementParameterParser.getValue(hiveConfigurationNode, "__HIVE_PRINCIPAL__");
        break;
    }

}

if(useLocalMode || (sparkBatchDistrib != null && !sparkBatchDistrib.isExecutedThroughLivy())) {
    for (INode pNode3 : node.getProcess().getNodesOfType("tHDFSConfiguration")) {
        if(hdfsConfiguration!=null && hdfsConfiguration.equals(pNode3.getUniqueName())) {
            hdfsConfigurationNode = pNode3;
            hdfsNamenodeURI = ElementParameterParser.getValue(hdfsConfigurationNode, "__FS_DEFAULT_NAME__");
            break;
        }
    }
}


    stringBuffer.append(TEXT_1);
    stringBuffer.append(hiveThriftMetaStoreHost);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(hiveThriftMetaStorePort);
    stringBuffer.append(TEXT_3);
    
    if(useKrb){
        
    stringBuffer.append(TEXT_4);
    stringBuffer.append(hivePrincipal);
    stringBuffer.append(TEXT_5);
    
}


    
    if(hdfsNamenodeURI != null) {
    
    stringBuffer.append(TEXT_6);
    stringBuffer.append(hdfsNamenodeURI);
    stringBuffer.append(TEXT_7);
    
    }

    

// In streaming we need the Spark Context from the Batch context
if("SPARKSTREAMING".equals(node.getComponent().getType())){

    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
} else {

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
}

// Create temporary function aliases for Hive UDFs
if(tempUdfsFunctions.size() > 0){
	for(java.util.Map<String, String> item : tempUdfsFunctions){
		
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(item.get("TEMPORARY_FUNCTION_ALIAS"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(item.get("UDF_FQCN"));
    stringBuffer.append(TEXT_15);
    
	}
}

// Choose the correct Input source
if(inputSource.equals("HIVE_TABLE")){
    String hiveDatabaseName = ElementParameterParser.getValue(node, "__HIVE_DATABASE_NAME__");
    String hiveTableName = ElementParameterParser.getValue(node, "__HIVE_TABLE_NAME__");
    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(hiveDatabaseName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(hiveTableName);
    stringBuffer.append(TEXT_23);
    
} else if (inputSource.equals("HIVE_HQL")){
    String hiveDatabaseName = ElementParameterParser.getValue(node, "__HIVE_DATABASE_NAME__");
    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(hiveDatabaseName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(tSqlRowUtil.getSqlQuery());
    stringBuffer.append(TEXT_31);
    
} else if (inputSource.equals("ORC_FILE")){
    String orcFilePath = ElementParameterParser.getValue(node, "__FILENAME__");
    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(hdfsNamenodeURI);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(orcFilePath);
    stringBuffer.append(TEXT_37);
    
}

    stringBuffer.append(TEXT_38);
    
// Convert the DF into RDD

    stringBuffer.append(TEXT_39);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_44);
    

    return stringBuffer.toString();
  }
}
