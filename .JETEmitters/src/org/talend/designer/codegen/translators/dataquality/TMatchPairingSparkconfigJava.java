package org.talend.designer.codegen.translators.dataquality;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;

public class TMatchPairingSparkconfigJava
{
  protected static String nl;
  public static synchronized TMatchPairingSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchPairingSparkconfigJava result = new TMatchPairingSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t// Convert incoming RDD to DataFrame" + NL + "\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_2 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL + "\t";
  protected final String TEXT_3 = " ";
  protected final String TEXT_4 = " =" + NL + "\t        sqlContext_";
  protected final String TEXT_5 = ".createDataFrame(rdd_";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = ".class);" + NL + "" + NL + "\t// Create holder for all transformations to perform" + NL + "\tjava.util.List<org.apache.spark.ml.PipelineStage> ";
  protected final String TEXT_8 = " = new java.util.ArrayList<org.apache.spark.ml.PipelineStage>();";
  protected final String TEXT_9 = NL + NL + NL + "\t// List of columnNames for constructing bkv" + NL + "\tjava.util.List<String> ";
  protected final String TEXT_10 = " = new java.util.ArrayList<String>();" + NL + "\t";
  protected final String TEXT_11 = NL + "\t          ";
  protected final String TEXT_12 = ".add(\"";
  protected final String TEXT_13 = "\");" + NL + "\t";
  protected final String TEXT_14 = NL + NL + "\t";
  protected final String TEXT_15 = ".add(//BKVConstructor" + NL + "            new org.talend.dataquality.reconciliation.ml.transformer.BKVConstructor()" + NL + "            .setInputCols(";
  protected final String TEXT_16 = ".toArray(new String[";
  protected final String TEXT_17 = ".size()]))//bkv cols" + NL + "            .setOutputCol(\"";
  protected final String TEXT_18 = "\"));" + NL + "" + NL + "\t";
  protected final String TEXT_19 = ".add(//SuffixArrayBlocking" + NL + "\t        new org.talend.dataquality.reconciliation.ml.transformer.SuffixArrayBlocking()" + NL + "            .setInputCol(\"";
  protected final String TEXT_20 = "\")" + NL + "            .setOutputCol(\"";
  protected final String TEXT_21 = "\")" + NL + "            .setMinSuffixLength(";
  protected final String TEXT_22 = ")" + NL + "            .setMaxBlockSize(";
  protected final String TEXT_23 = "));" + NL + "" + NL + "\t// Create ML Pipeline from transformations created earlier and call the fit to generate pipelineModel" + NL + "\torg.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_24 = " = new org.apache.spark.ml.Pipeline().setStages(" + NL + "\t        ";
  protected final String TEXT_25 = ".toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_26 = ".size()]));" + NL + "\torg.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_27 = " =" + NL + "\t        pipeline_";
  protected final String TEXT_28 = ".fit(";
  protected final String TEXT_29 = ");" + NL + "" + NL + "\t// Apply transformations to the input DF" + NL + "\torg.talend.dataquality.reconciliation.api.MatchPairing.Results matchPairs_";
  protected final String TEXT_30 = " = " + NL + "\t        org.talend.dataquality.reconciliation.api.MatchPairing.run(" + NL + "\t                ";
  protected final String TEXT_31 = "," + NL + "\t                pipelineModel_";
  protected final String TEXT_32 = "," + NL + "\t                new org.talend.dataquality.reconciliation.api.MatchPairing.Config(";
  protected final String TEXT_33 = ",\"";
  protected final String TEXT_34 = "\",";
  protected final String TEXT_35 = "));" + NL + "" + NL + "\t";
  protected final String TEXT_36 = NL + "\t// Convert suspect outgoing DataFrame to RDD" + NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_37 = "> rdd_";
  protected final String TEXT_38 = " =" + NL + "            matchPairs_";
  protected final String TEXT_39 = ".suspectOut().toJavaRDD().map(new ";
  protected final String TEXT_40 = "_FromRowTo";
  protected final String TEXT_41 = "());" + NL + "\t";
  protected final String TEXT_42 = NL;
  protected final String TEXT_43 = NL + "    // Convert exact outgoing DataFrame to RDD" + NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_44 = "> rdd_";
  protected final String TEXT_45 = " =" + NL + "            matchPairs_";
  protected final String TEXT_46 = ".exactOut().get().toJavaRDD().map(new ";
  protected final String TEXT_47 = "_FromRowTo";
  protected final String TEXT_48 = "());";
  protected final String TEXT_49 = NL + NL + "\t";
  protected final String TEXT_50 = NL + "\t// Convert unique outgoing DataFrame to RDD" + NL + "\torg.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_51 = "> rdd_";
  protected final String TEXT_52 = " =" + NL + "            matchPairs_";
  protected final String TEXT_53 = ".uniqueOut().get().toJavaRDD().map(new ";
  protected final String TEXT_54 = "_FromRowTo";
  protected final String TEXT_55 = "());" + NL + "\t";
  protected final String TEXT_56 = NL + NL + "\tPath pathToDelete_";
  protected final String TEXT_57 = " = new Path(";
  protected final String TEXT_58 = ");" + NL + "\tif (fs.exists(pathToDelete_";
  protected final String TEXT_59 = ")) {" + NL + "\t      fs.delete(pathToDelete_";
  protected final String TEXT_60 = ", true);" + NL + "\t}" + NL + "" + NL + "\t// Serialize the model" + NL + "\tcom.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_61 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "\tcom.esotericsoftware.kryo.io.Output modelOutput_";
  protected final String TEXT_62 = " = new com.esotericsoftware.kryo.io.Output(" + NL + "         fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_63 = " + \"/model\")));" + NL + "\tkryo_";
  protected final String TEXT_64 = ".writeObject(modelOutput_";
  protected final String TEXT_65 = "," + NL + "\t        new TalendPipelineModel(pipelineModel_";
  protected final String TEXT_66 = ")," + NL + "\t        new TalendPipelineModelSerializer());" + NL + "\tmodelOutput_";
  protected final String TEXT_67 = ".close();" + NL;
  protected final String TEXT_68 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.DataFrame";//keep using dataFrame even on spark 2.0

    
//IO Connections
IConnection incomingConnection = null;
if (node.getIncomingConnections() != null) {
 for (IConnection in : node.getIncomingConnections()) {
     if (in.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
         incomingConnection = in;
         break;
     }
 }
}
String incomingConnectionName = incomingConnection.getName();
String incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);

IConnection suspectConnection = null;
boolean hasSuspectConnection = false;
List<? extends IConnection> outgoingSuspectConnections = node.getOutgoingConnections("SUSPECT");
if (outgoingSuspectConnections.size() > 0) {
    suspectConnection = outgoingSuspectConnections.get(0);
}
if(suspectConnection != null){
    hasSuspectConnection = true;
}

IConnection exactConnection = null;
boolean hasExactConnection = false;
List<? extends IConnection> outgoingExactConnections = node.getOutgoingConnections("EXACT");
if (outgoingExactConnections.size() > 0) {
    exactConnection = outgoingExactConnections.get(0);
}
if(exactConnection != null){
    hasExactConnection = true;
}

IConnection uniqueConnection = null;
boolean hasUniqueConnection = false;
List<? extends IConnection> outgoingUniqueConnections = node.getOutgoingConnections("UNIQUE");
if (outgoingUniqueConnections.size() > 0) {
    uniqueConnection = outgoingUniqueConnections.get(0);
}
if(uniqueConnection != null){
    hasUniqueConnection = true;
}

//Component params
Boolean hasPairID = ElementParameterParser.getBooleanValue(node,"__PAIR_ID__");
//SuffixArrayBlocking output col name
String pairID = hasPairID ? "pairID" : "pairID_" + cid;
//count col name in the exact output schema
String cnt = hasExactConnection ? "count" : "cnt_" + cid;


    
//Component params
String lms = ElementParameterParser.getValue(node, "__MIN_SUFFIX_LENGTH__");
String lmbs = ElementParameterParser.getValue(node, "__MAX_BLOCK_SIZE__");

List<Map<String, String>> bkvCols = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__BKV_TABLE__");
String bkvColNames = "bkvCols_" + cid;

//BKVConstructor output col name
String bkvCol = "bkv_" + cid;

//HDFS folder to save in
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}

TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
String stagesName = "stages_" + tModelEncoderUtil.getFirstModelEncoderName();
String trainingName = "training_" + tModelEncoderUtil.getFirstModelEncoderName();

if(tModelEncoderUtil.isFirstModelEncoder()) {
    // We're on the first tModelEncoder, so we need to convert the incoming RDD to DataFrame

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_8);
    
} // end if(tModelEncoderUtil.isFirstModelEncoder())

    stringBuffer.append(TEXT_9);
    stringBuffer.append(bkvColNames);
    stringBuffer.append(TEXT_10);
    
	   if((bkvCols!=null)&&(bkvCols.size() > 0)){
	      for(Map<String,String> bkvColMap: bkvCols){
	
    stringBuffer.append(TEXT_11);
    stringBuffer.append(bkvColNames);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(bkvColMap.get("INPUT_COLUMN"));
    stringBuffer.append(TEXT_13);
    
	      }
	   }
	
    stringBuffer.append(TEXT_14);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(bkvColNames);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(bkvColNames);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(bkvCol);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(bkvCol);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(pairID);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(lms);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(lmbs);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(hasExactConnection);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cnt);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(hasUniqueConnection);
    stringBuffer.append(TEXT_35);
    
	if(hasSuspectConnection){
	    String suspectOutConnectionName = suspectConnection.getName();
	    String suspectOutStructName = codeGenArgument.getRecordStructName(suspectConnection);
	
    stringBuffer.append(TEXT_36);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(suspectOutConnectionName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_41);
    
	}
	
    stringBuffer.append(TEXT_42);
    
    if(hasExactConnection){
        String exactOutConnectionName = exactConnection.getName();
        String exactOutStructName = codeGenArgument.getRecordStructName(exactConnection);
    
    stringBuffer.append(TEXT_43);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(exactOutConnectionName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_48);
    
    }
    
    stringBuffer.append(TEXT_49);
    
	if(hasUniqueConnection){
	    String uniqueOutConnectionName = uniqueConnection.getName();
	    String uniqueOutStructName = codeGenArgument.getRecordStructName(uniqueConnection);
	
    stringBuffer.append(TEXT_50);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(uniqueOutConnectionName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_55);
    
	}
	
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(TEXT_68);
    return stringBuffer.toString();
  }
}
