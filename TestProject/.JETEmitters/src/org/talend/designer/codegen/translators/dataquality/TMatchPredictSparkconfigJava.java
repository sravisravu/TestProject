package org.talend.designer.codegen.translators.dataquality;

import java.util.List;
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

public class TMatchPredictSparkconfigJava
{
  protected static String nl;
  public static synchronized TMatchPredictSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchPredictSparkconfigJava result = new TMatchPredictSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_2 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_3 = " = new TalendPipelineModelSerializer();" + NL + "" + NL + "//Get the pairing pipeline model." + NL + "com.esotericsoftware.kryo.io.Input pairingModelInput_";
  protected final String TEXT_4 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_5 = " + \"/model/\")));" + NL + "TalendPipelineModel pairingModel_";
  protected final String TEXT_6 = " = kryo_";
  protected final String TEXT_7 = ".readObject(pairingModelInput_";
  protected final String TEXT_8 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_9 = ");" + NL + "org.apache.spark.ml.PipelineModel pairingPM_";
  protected final String TEXT_10 = " = pairingModel_";
  protected final String TEXT_11 = ".getPipelineModel();" + NL + "" + NL + "//Get the ml pipeline model,";
  protected final String TEXT_12 = NL + "    // from file system." + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_13 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_14 = " + \"/model/\")));" + NL + "    TalendPipelineModel mlModel_";
  protected final String TEXT_15 = " = kryo_";
  protected final String TEXT_16 = ".readObject(featuresInput_";
  protected final String TEXT_17 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_18 = ");" + NL + "    org.apache.spark.ml.PipelineModel mlPM_";
  protected final String TEXT_19 = " = mlModel_";
  protected final String TEXT_20 = ".getPipelineModel();";
  protected final String TEXT_21 = NL + "    // from current job." + NL + "    org.apache.spark.ml.PipelineModel mlPM_";
  protected final String TEXT_22 = ";" + NL + "    {" + NL + "        Object model = globalMap.get(\"";
  protected final String TEXT_23 = "_MODEL\");" + NL + "        if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "            mlPM_";
  protected final String TEXT_24 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "        } else {" + NL + "            throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "        }" + NL + "    }";
  protected final String TEXT_25 = NL + "//Convert incoming RDD to DataFrame" + NL + "org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_26 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_27 = NL;
  protected final String TEXT_28 = " df_";
  protected final String TEXT_29 = " = sqlContext_";
  protected final String TEXT_30 = ".createDataFrame(rdd_";
  protected final String TEXT_31 = ", ";
  protected final String TEXT_32 = ".class);" + NL + "" + NL + "org.talend.dataquality.reconciliation.api.MatchPairing.Results matchPairs_";
  protected final String TEXT_33 = " = org.talend.dataquality.reconciliation.api.MatchPairing.run(" + NL + "        df_";
  protected final String TEXT_34 = "," + NL + "        pairingPM_";
  protected final String TEXT_35 = "," + NL + "        new org.talend.dataquality.reconciliation.api.MatchPairing.Config(";
  protected final String TEXT_36 = ",\"";
  protected final String TEXT_37 = "\",";
  protected final String TEXT_38 = ")" + NL + ");" + NL;
  protected final String TEXT_39 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_40 = "> rdd_";
  protected final String TEXT_41 = ";" + NL + "{" + NL + "    org.apache.spark.sql.DataFrame dfPairHorizontal = " + NL + "            org.talend.dataquality.reconciliation.api.PairsReshaping.horizontalize(matchPairs_";
  protected final String TEXT_42 = ".suspectOut());" + NL + "    " + NL + "    org.apache.spark.sql.DataFrame results = " + NL + "            org.talend.dataquality.reconciliation.api.PairsReshaping.verticalize(mlPM_";
  protected final String TEXT_43 = ".transform(dfPairHorizontal));";
  protected final String TEXT_44 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) mlPM_";
  protected final String TEXT_45 = ".stages()[1];" + NL + "        // Convert suspect outgoing DataFrame to RDD" + NL + "        org.apache.spark.rdd.RDD<";
  protected final String TEXT_46 = "> rdd = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_47 = "(sim)," + NL + "                scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_48 = ".class));" + NL + "        rdd_";
  protected final String TEXT_49 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_50 = NL + "        // TODO";
  protected final String TEXT_51 = NL + "}";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "// Convert exact outgoing DataFrame to RDD" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_54 = "> rdd_";
  protected final String TEXT_55 = " =" + NL + "        matchPairs_";
  protected final String TEXT_56 = ".exactOut().get().toJavaRDD().map(new ";
  protected final String TEXT_57 = "_FromRowTo";
  protected final String TEXT_58 = "());";
  protected final String TEXT_59 = NL;
  protected final String TEXT_60 = NL + "// Convert unique outgoing DataFrame to RDD" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_61 = "> rdd_";
  protected final String TEXT_62 = " =" + NL + "        matchPairs_";
  protected final String TEXT_63 = ".uniqueOut().get().toJavaRDD().map(new ";
  protected final String TEXT_64 = "_FromRowTo";
  protected final String TEXT_65 = "());";

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


    
// This is set to true if the output label is not double format.
// TODO: check if the labelColumn is double and provide a unique labelColumn.
boolean needsLabelIndexer = true;
String labelColumn = "label";

String pairingModelFolder = ElementParameterParser.getValue(node,"__PAIRING_HDFS_FOLDER__");
Boolean savedOnDisk = ElementParameterParser.getValue(node, "__ML_MODEL_LOCATION__").equals("FILE_SYSTEM")? true : false;
String mlModelFolder = ElementParameterParser.getValue(node,"__ML_HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    pairingModelFolder = uriPrefix + " + " + pairingModelFolder;
    mlModelFolder = uriPrefix + " + " + mlModelFolder;
}

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(pairingModelFolder);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
if (savedOnDisk) {
    
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(mlModelFolder);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
} else {
    String mlModelCid = ElementParameterParser.getValue(node, "__ML_MODEL_NAME__");
    
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(mlModelCid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
}

    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(hasExactConnection);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cnt);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(hasUniqueConnection);
    stringBuffer.append(TEXT_38);
    
if(hasSuspectConnection){
    String suspectOutConnectionName = suspectConnection.getName();
    String suspectOutStructName = codeGenArgument.getRecordStructName(suspectConnection);

    stringBuffer.append(TEXT_39);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(suspectOutConnectionName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(suspectOutConnectionName);
    stringBuffer.append(TEXT_49);
    
    } else {
        
    stringBuffer.append(TEXT_50);
    
    }
    
    stringBuffer.append(TEXT_51);
    
}

    stringBuffer.append(TEXT_52);
    
if(hasExactConnection){
    String exactOutConnectionName = exactConnection.getName();
    String exactOutStructName = codeGenArgument.getRecordStructName(exactConnection);

    stringBuffer.append(TEXT_53);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(exactOutConnectionName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_58);
    
}

    stringBuffer.append(TEXT_59);
    
if(hasUniqueConnection){
    String uniqueOutConnectionName = uniqueConnection.getName();
    String uniqueOutStructName = codeGenArgument.getRecordStructName(uniqueConnection);

    stringBuffer.append(TEXT_60);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(uniqueOutConnectionName);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_65);
    
}

    return stringBuffer.toString();
  }
}
