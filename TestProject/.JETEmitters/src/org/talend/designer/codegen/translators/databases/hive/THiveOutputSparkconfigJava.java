package org.talend.designer.codegen.translators.databases.hive;

import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class THiveOutputSparkconfigJava
{
  protected static String nl;
  public static synchronized THiveOutputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THiveOutputSparkconfigJava result = new THiveOutputSparkconfigJava();
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
  protected final String TEXT_8 = NL + NL + "org.apache.spark.sql.hive.HiveContext hiveContext_";
  protected final String TEXT_9 = " = new org.apache.spark.sql.hive.HiveContext(ctx.sc());" + NL;
  protected final String TEXT_10 = NL + "        org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_11 = "> ";
  protected final String TEXT_12 = " = ";
  protected final String TEXT_13 = ".map(new ";
  protected final String TEXT_14 = "_From";
  protected final String TEXT_15 = "To";
  protected final String TEXT_16 = "());";
  protected final String TEXT_17 = NL + "    ";
  protected final String TEXT_18 = " df_";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = " = hiveContext_";
  protected final String TEXT_21 = ".createDataFrame(";
  protected final String TEXT_22 = ", ";
  protected final String TEXT_23 = ".class);";
  protected final String TEXT_24 = NL + "    hiveContext_";
  protected final String TEXT_25 = ".sql(\"USE \" + ";
  protected final String TEXT_26 = ");";
  protected final String TEXT_27 = NL + "        df_";
  protected final String TEXT_28 = "_";
  protected final String TEXT_29 = ".saveAsTable(";
  protected final String TEXT_30 = ", \"";
  protected final String TEXT_31 = "\", org.apache.spark.sql.SaveMode.";
  protected final String TEXT_32 = ");";
  protected final String TEXT_33 = NL + "        df_";
  protected final String TEXT_34 = "_";
  protected final String TEXT_35 = ".write()" + NL + "                .mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_36 = ")" + NL + "                .format(\"";
  protected final String TEXT_37 = "\")" + NL + "                .saveAsTable(";
  protected final String TEXT_38 = ");";
  protected final String TEXT_39 = NL + "    df_";
  protected final String TEXT_40 = "_";
  protected final String TEXT_41 = ".write().format(\"orc\").mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_42 = ").save(";
  protected final String TEXT_43 = " + ";
  protected final String TEXT_44 = ");";

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
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
String saveMode = ElementParameterParser.getValue(node, "__SAVEMODE__");
String saveFormat = ElementParameterParser.getValue(node, "__TABLEFORMAT__");
String outputSource = ElementParameterParser.getValue(node, "__OUTPUT_SOURCE__");

// Get Hive & HDFS configurations
String hiveConfiguration = ElementParameterParser.getValue(node, "__HIVE_STORAGE_CONFIGURATION__");
String hdfsConfiguration = ElementParameterParser.getValue(node, "__HDFS_STORAGE_CONFIGURATION__");
INode hiveConfigurationNode = null;
String hiveThriftMetaStoreHost = null;
String hiveThriftMetaStorePort = null;
INode hdfsConfigurationNode = null;
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
    for (INode pNode2 : node.getProcess().getNodesOfType("tHDFSConfiguration")) {
        if(hdfsConfiguration!=null && hdfsConfiguration.equals(pNode2.getUniqueName())) {
            hdfsConfigurationNode = pNode2;
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

    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
// DATAFRAME DATE CONVERSION
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
    String inRddName = "rdd_"+incomingConnection.getName();
    String rddName, structName;

    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        // Additional map to convert from java.util.Date to java.sql.Date
        String newRddName = "tmp_rdd_"+incomingConnection.getName();
        String newStructName = "DF_"+inStructName+"AvroRecord";
        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_16);
    
        rddName = newRddName;
        structName = newStructName;
    }else{
        // No need for additional map
        rddName = inRddName;
        structName = inStructName;
    }
    // Convert the RDD into a DF
    
    stringBuffer.append(TEXT_17);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_23);
    
}

// Choose the correct output source
if(outputSource.equals("HIVE_TABLE")){
    String hiveDatabaseName = ElementParameterParser.getValue(node, "__HIVE_DATABASE_NAME__");
    String hiveTableName = ElementParameterParser.getValue(node, "__HIVE_TABLE_NAME__");
    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(hiveDatabaseName);
    stringBuffer.append(TEXT_26);
    
    if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
        
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(componentIncomingConnection.getName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(hiveTableName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(saveFormat);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_32);
    
    } else {
        // The write() method is mandatory in Spark 2.0 (optional between 1.4-1.6).
        
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(componentIncomingConnection.getName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(saveFormat);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(hiveTableName);
    stringBuffer.append(TEXT_38);
    
    }
} else if (outputSource.equals("ORC_FILE")){
    String outputFolder = ElementParameterParser.getValue(node, "__OUTPUT_FOLDER__");
    
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(componentIncomingConnection.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(hdfsNamenodeURI);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outputFolder);
    stringBuffer.append(TEXT_44);
    
}

    return stringBuffer.toString();
  }
}
