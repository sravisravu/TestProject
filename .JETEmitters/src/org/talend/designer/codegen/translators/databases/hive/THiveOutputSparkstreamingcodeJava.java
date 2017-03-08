package org.talend.designer.codegen.translators.databases.hive;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class THiveOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized THiveOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THiveOutputSparkstreamingcodeJava result = new THiveOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = "_From";
  protected final String TEXT_3 = "To";
  protected final String TEXT_4 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_7 = " call(";
  protected final String TEXT_8 = " input) {";
  protected final String TEXT_9 = NL + "        ";
  protected final String TEXT_10 = " result = new ";
  protected final String TEXT_11 = "();";
  protected final String TEXT_12 = NL + "        result.";
  protected final String TEXT_13 = " = new java.sql.Date(input.";
  protected final String TEXT_14 = ".getTime());";
  protected final String TEXT_15 = NL + "        result.";
  protected final String TEXT_16 = " = input.";
  protected final String TEXT_17 = ";";
  protected final String TEXT_18 = NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_19 = NL + NL + "    static class ";
  protected final String TEXT_20 = "_JavaHiveContextSingleton {" + NL + "      static private transient org.apache.spark.sql.hive.HiveContext instance = null;" + NL + "      static public org.apache.spark.sql.hive.HiveContext getInstance(org.apache.spark.SparkContext sparkContext, ContextProperties context) {" + NL + "        if (instance == null) {" + NL + "            System.setProperty(\"hive.metastore.uris\",\"thrift://\" + ";
  protected final String TEXT_21 = " + \":\" + ";
  protected final String TEXT_22 = " + \"/\");";
  protected final String TEXT_23 = NL + "                System.setProperty(\"hive.metastore.sasl.enabled\", \"true\");" + NL + "                System.setProperty(\"hive.security.authorization.enabled\", \"false\");" + NL + "                System.setProperty(\"hive.metastore.kerberos.principal\", ";
  protected final String TEXT_24 = ");" + NL + "                System.setProperty(\"hive.metastore.execute.setugi\", \"true\");";
  protected final String TEXT_25 = NL + "              System.setProperty(\"fs.defaultFS\", ";
  protected final String TEXT_26 = ");";
  protected final String TEXT_27 = NL + NL + "          instance = new org.apache.spark.sql.hive.HiveContext(sparkContext);" + NL + "        }" + NL + "        return instance;" + NL + "      }" + NL + "    }" + NL + NL + NL;
  protected final String TEXT_28 = NL + "    public static class ";
  protected final String TEXT_29 = "_ForeachRDDOutput implements ";
  protected final String TEXT_30 = " {" + NL + "" + NL + "        private ContextProperties context;" + NL + "" + NL + "        public ";
  protected final String TEXT_31 = "_ForeachRDDOutput(JobConf job){" + NL + "            this.context = new ContextProperties(job);" + NL + "       }" + NL + "" + NL + "        @Override" + NL + "        public ";
  protected final String TEXT_32 = " call(";
  protected final String TEXT_33 = " temporaryRdd) throws Exception {" + NL + "" + NL + "            org.apache.spark.sql.hive.HiveContext hiveContext_";
  protected final String TEXT_34 = " = ";
  protected final String TEXT_35 = "_JavaHiveContextSingleton.getInstance(temporaryRdd.context(), this.context);";
  protected final String TEXT_36 = NL + "            ";
  protected final String TEXT_37 = " df_";
  protected final String TEXT_38 = " = hiveContext_";
  protected final String TEXT_39 = ".createDataFrame(temporaryRdd, ";
  protected final String TEXT_40 = ".class);" + NL;
  protected final String TEXT_41 = NL + "                hiveContext_";
  protected final String TEXT_42 = ".sql(\"USE \" + ";
  protected final String TEXT_43 = ");";
  protected final String TEXT_44 = NL + "                    df_";
  protected final String TEXT_45 = ".saveAsTable(";
  protected final String TEXT_46 = ", \"";
  protected final String TEXT_47 = "\", org.apache.spark.sql.SaveMode.";
  protected final String TEXT_48 = ");";
  protected final String TEXT_49 = NL + "                    df_";
  protected final String TEXT_50 = ".write()" + NL + "                            .mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_51 = ")" + NL + "                            .format(\"";
  protected final String TEXT_52 = "\")" + NL + "                            .saveAsTable(";
  protected final String TEXT_53 = ");" + NL;
  protected final String TEXT_54 = NL + "                df_";
  protected final String TEXT_55 = ".write().format(\"orc\").mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_56 = ").save(";
  protected final String TEXT_57 = " + ";
  protected final String TEXT_58 = ");";
  protected final String TEXT_59 = NL + "            ";
  protected final String TEXT_60 = NL + "        }" + NL + "" + NL + "    }";
  protected final String TEXT_61 = NL;

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
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

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

String saveMode = ElementParameterParser.getValue(node, "__SAVEMODE__");
String saveFormat = ElementParameterParser.getValue(node, "__TABLEFORMAT__");
String outputSource = ElementParameterParser.getValue(node, "__OUTPUT_SOURCE__");

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
    for (INode pNode2 : node.getProcess().getNodesOfType("tHDFSConfiguration")) {
        if(hdfsConfiguration!=null && hdfsConfiguration.equals(pNode2.getUniqueName())) {
            hdfsConfigurationNode = pNode2;
            hdfsNamenodeURI = ElementParameterParser.getValue(hdfsConfigurationNode, "__FS_DEFAULT_NAME__");
            break;
        }
    }
}

IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
String inStructName = codeGenArgument.getRecordStructName(componentIncomingConnection);
String structName = inStructName;

// Change the structName if there's a Date column
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        String newStructName = "DF_"+inStructName+"AvroRecord";
        structName = newStructName;
    }
}

// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.
org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
        String originalStructName = codeGenArgument.getRecordStructName(incomingConnection);
        String suggestedDfStructName = "DF_"+originalStructName;
        String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, originalStructName);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_11);
    
        for(IMetadataColumn column : columns) {
            if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
            } else {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_17);
    
            }
        } // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_18);
    
    } // end if(tSqlRowUtil.containsDateFields(incomingConnection))
} // end for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections())


    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(hiveThriftMetaStoreHost);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(hiveThriftMetaStorePort);
    stringBuffer.append(TEXT_22);
    
            if(useKrb){
                
    stringBuffer.append(TEXT_23);
    stringBuffer.append(hivePrincipal);
    stringBuffer.append(TEXT_24);
    
            }

     if(hdfsNamenodeURI != null) { 
    stringBuffer.append(TEXT_25);
    stringBuffer.append(hdfsNamenodeURI);
    stringBuffer.append(TEXT_26);
     } 
    stringBuffer.append(TEXT_27);
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaRDD(
                    codeGenArgument.getSparkVersion(), structName);
    
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_40);
    
            if(outputSource.equals("HIVE_TABLE")){
                String hiveDatabaseName = ElementParameterParser.getValue(node, "__HIVE_DATABASE_NAME__");
                String hiveTableName = ElementParameterParser.getValue(node, "__HIVE_TABLE_NAME__");
                
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(hiveDatabaseName);
    stringBuffer.append(TEXT_43);
    
                if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
                    
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(hiveTableName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(saveFormat);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_48);
    
                } else {
                    // The write() method is mandatory in Spark 2.0 (optional between 1.4-1.6).
                    
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(saveFormat);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(hiveTableName);
    stringBuffer.append(TEXT_53);
    
                }
            }else if (outputSource.equals("ORC_FILE")){
                String outputFolder = ElementParameterParser.getValue(node, "__OUTPUT_FOLDER__");
                
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(hdfsNamenodeURI);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(outputFolder);
    stringBuffer.append(TEXT_58);
    
            }
            
    stringBuffer.append(TEXT_59);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_60);
    
} // End ForeachRDD helper function

    stringBuffer.append(TEXT_61);
    return stringBuffer.toString();
  }
}
