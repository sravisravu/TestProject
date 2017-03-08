package org.talend.designer.codegen.translators.databases.mongodb;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.designer.spark.generator.storage.MongoDBSparkStorage;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;

public class TMongoDBOutputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TMongoDBOutputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMongoDBOutputSparkstreamingconfigJava result = new TMongoDBOutputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    if(";
  protected final String TEXT_2 = " == 0){" + NL + "        throw new RuntimeException(\"MongoDB update operations require at least one key to be defined in the schema\");" + NL + "    }";
  protected final String TEXT_3 = NL + "    org.apache.hadoop.conf.Configuration mongoConfig_";
  protected final String TEXT_4 = " = new org.apache.hadoop.conf.Configuration();" + NL + "    String connectionString_";
  protected final String TEXT_5 = " = ";
  protected final String TEXT_6 = ";" + NL;
  protected final String TEXT_7 = NL + "        connectionString_";
  protected final String TEXT_8 = " += \"&w=\" + \"";
  protected final String TEXT_9 = "\";";
  protected final String TEXT_10 = NL + "        String authString_";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ";" + NL + "        mongoConfig_";
  protected final String TEXT_13 = ".set(\"mongo.auth.uri\", authString_";
  protected final String TEXT_14 = ");";
  protected final String TEXT_15 = NL + "        mongoConfig_";
  protected final String TEXT_16 = ".set(";
  protected final String TEXT_17 = ", ";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "    mongoConfig_";
  protected final String TEXT_20 = ".set(\"mongo.output.uri\", connectionString_";
  protected final String TEXT_21 = ");";
  protected final String TEXT_22 = NL + "            org.apache.spark.streaming.api.java.JavaPairDStream<Object, org.bson.BSONObject> bsonDS_";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = ".mapToPair(new ";
  protected final String TEXT_25 = "_From";
  protected final String TEXT_26 = "ToBson(job));";
  protected final String TEXT_27 = NL + "            org.apache.spark.api.java.JavaPairRDD<Object, org.bson.BSONObject> bsonRDD_";
  protected final String TEXT_28 = " = ";
  protected final String TEXT_29 = ".mapToPair(new ";
  protected final String TEXT_30 = "_From";
  protected final String TEXT_31 = "ToBson(job));";
  protected final String TEXT_32 = NL + "            org.apache.spark.streaming.api.java.JavaPairDStream<Object, com.mongodb.hadoop.io.MongoUpdateWritable> bsonDS_";
  protected final String TEXT_33 = " = ";
  protected final String TEXT_34 = ".mapToPair(new ";
  protected final String TEXT_35 = "_From";
  protected final String TEXT_36 = "ToBson(job));";
  protected final String TEXT_37 = NL + "            org.apache.spark.api.java.JavaPairRDD<Object, com.mongodb.hadoop.io.MongoUpdateWritable> bsonRDD_";
  protected final String TEXT_38 = " = ";
  protected final String TEXT_39 = ".mapToPair(new ";
  protected final String TEXT_40 = "_From";
  protected final String TEXT_41 = "ToBson(job));";
  protected final String TEXT_42 = NL + "        bsonDS_";
  protected final String TEXT_43 = ".foreachRDD(new ";
  protected final String TEXT_44 = "_ForeachRDDOutput(mongoConfig_";
  protected final String TEXT_45 = "));";
  protected final String TEXT_46 = NL + "        bsonRDD_";
  protected final String TEXT_47 = ".saveAsNewAPIHadoopFile(" + NL + "        \"\"," + NL + "        Object.class," + NL + "        org.bson.BSONObject.class," + NL + "        com.mongodb.hadoop.MongoOutputFormat.class," + NL + "        mongoConfig_";
  protected final String TEXT_48 = NL + "        );";
  protected final String TEXT_49 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
String inStructName = codeGenArgument.getRecordStructName(componentIncomingConnection);
String inRddName = "rdd_"+componentIncomingConnection.getName();

String collection = ElementParameterParser.getValue(node,"__COLLECTION__");
boolean isInsert = "INSERT".equalsIgnoreCase(ElementParameterParser.getValue(node, "__DATA_ACTION__"));
boolean setWriteConcern = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__SET_WRITE_CONCERN__"));
String writeConcern = ElementParameterParser.getValue(node, "__WRITE_CONCERN__");
java.util.List<java.util.Map<String, String>> hadoopConfiguration = (java.util.List<java.util.Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__ADVANCED_HADOOP_MONGODB__");

String mongoDBConfiguration = ElementParameterParser.getValue(node,"__MONGODB_CONFIGURATION__");
INode mongoDBConfigurationNode = null;

for (INode pNode1 : node.getProcess().getNodesOfType("tMongoDBConfiguration")) {
    if(mongoDBConfiguration!=null && mongoDBConfiguration.equals(pNode1.getUniqueName())) {
        mongoDBConfigurationNode = pNode1;
        break;
    }
}

// Updates check
if(!isInsert){
    java.util.List<IMetadataTable> metadatas = node.getMetadataList();
    int schemaKeys = 0;
    if(metadatas != null && metadatas.size() > 0){
        IMetadataTable metadata = metadatas.get(0);
        if(metadata != null){
            java.util.List<IMetadataColumn> columnList = tSqlRowUtil.getColumns(componentIncomingConnection);
            int sizeColumns = columnList.size();
            for (int i = 0; i < sizeColumns; i++) {
                IMetadataColumn column = columnList.get(i);
                if(column.isKey()){
                    schemaKeys++;
                }
            }
        }
    }

    stringBuffer.append(TEXT_1);
    stringBuffer.append(schemaKeys);
    stringBuffer.append(TEXT_2);
    
}

MongoDBSparkStorage storage = new MongoDBSparkStorage(mongoDBConfigurationNode);


    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getIOConnectionString(collection));
    stringBuffer.append(TEXT_6);
    
    if(setWriteConcern){
        
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(writeConcern);
    stringBuffer.append(TEXT_9);
    
    }

    if(storage.isUseAuthDB()){
        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(storage.getAuthConnectionString());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    
    }

    for(java.util.Map<String,String> config: hadoopConfiguration){
        
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(config.get("PROPERTY"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(config.get("VALUE"));
    stringBuffer.append(TEXT_18);
    
    }

    
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
    // FOR INSERT action
    if(isInsert){
        if("SPARKSTREAMING".equals(node.getComponent().getType())) {
            
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_26);
    
        }else{
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_31);
    
        }
    // FOR UPDATE actions
    }else {
        if("SPARKSTREAMING".equals(node.getComponent().getType())) {
            
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_36);
    
        } else {
            
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_41);
    
        }
    }

    if("SPARKSTREAMING".equals(node.getComponent().getType())) {
        
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
    }else{
        
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
    }

    stringBuffer.append(TEXT_49);
    return stringBuffer.toString();
  }
}
