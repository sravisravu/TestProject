package org.talend.designer.codegen.translators.machinelearning.clustering;

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

public class TPredictClusterSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TPredictClusterSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictClusterSparkstreamingconfigJava result = new TPredictClusterSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_2 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_3 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_4 = " + \"/features\")));" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_5 = " = kryo_";
  protected final String TEXT_6 = ".readObject(featuresInput_";
  protected final String TEXT_7 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel_";
  protected final String TEXT_8 = " = org.apache.spark.mllib.clustering.KMeansModel.load(ctx.sparkContext().sc(), ";
  protected final String TEXT_9 = " + \"/model\");" + NL + "" + NL + "    java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_10 = " = featuresTalendPipelineModel_";
  protected final String TEXT_11 = ".getParams();" + NL + "    org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_12 = " = featuresTalendPipelineModel_";
  protected final String TEXT_13 = ".getPipelineModel();" + NL + "    String vectorName_";
  protected final String TEXT_14 = " = featuresParamsMap_";
  protected final String TEXT_15 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "" + NL + "     // Create context" + NL + "     org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_16 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + "     // Go to RDD view in order to use dataframe" + NL + "     org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_17 = "> rdd_";
  protected final String TEXT_18 = " =" + NL + "         rdd_";
  protected final String TEXT_19 = ".transform(new GenerateVector_";
  protected final String TEXT_20 = "(vectorName_";
  protected final String TEXT_21 = ", featuresTransformationsModel_";
  protected final String TEXT_22 = ", sqlContext_";
  protected final String TEXT_23 = "))" + NL + "                 .map(new GetPrediction_";
  protected final String TEXT_24 = "(currentModel_";
  protected final String TEXT_25 = "));";
  protected final String TEXT_26 = NL + "    if ((globalMap.getLocal(\"";
  protected final String TEXT_27 = "_PIPELINE\") == null)" + NL + "            || (globalMap.getLocal(\"";
  protected final String TEXT_28 = "_MODEL\")  == null)) {" + NL + "       throw new IOException(\"Pipeline or model initialized before the component ";
  protected final String TEXT_29 = "\");" + NL + "    }" + NL;
  protected final String TEXT_30 = NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_31 = " = (TalendPipelineModel)globalMap.getLocal(\"";
  protected final String TEXT_32 = "_PIPELINE\");" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_33 = " = featuresTalendPipelineModel_";
  protected final String TEXT_34 = ".getParams();" + NL + "        String vectorName_";
  protected final String TEXT_35 = " = featuresParamsMap_";
  protected final String TEXT_36 = ".get(\"VECTOR_NAME\");" + NL + "        org.apache.spark.ml.PipelineModel featuresTransformations_";
  protected final String TEXT_37 = " = featuresTalendPipelineModel_";
  protected final String TEXT_38 = ".getPipelineModel();";
  protected final String TEXT_39 = NL + "        TalendPipeline featuresTalendPipeline_";
  protected final String TEXT_40 = " = (TalendPipeline)globalMap.getLocal(\"";
  protected final String TEXT_41 = "_PIPELINE\");" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_42 = " = featuresTalendPipeline_";
  protected final String TEXT_43 = ".getParams();" + NL + "        String vectorName_";
  protected final String TEXT_44 = " = featuresParamsMap_";
  protected final String TEXT_45 = ".get(\"VECTOR_NAME\");" + NL + "        org.apache.spark.ml.Pipeline featuresTransformations_";
  protected final String TEXT_46 = " = featuresTalendPipeline_";
  protected final String TEXT_47 = ".getPipeline();";
  protected final String TEXT_48 = NL + NL + "    Object temporaryModel_";
  protected final String TEXT_49 = " = globalMap.getLocal(\"";
  protected final String TEXT_50 = "_MODEL\");" + NL + "    if (!(temporaryModel_";
  protected final String TEXT_51 = " instanceof org.apache.spark.mllib.clustering.StreamingKMeans)) {" + NL + "        throw new RuntimeException(\"The selected model is of type \" + temporaryModel_";
  protected final String TEXT_52 = ".getClass() + \" is should be of type org.apache.spark.mllib.clustering.StreamingKMeans\");" + NL + "    }" + NL + "    org.apache.spark.mllib.clustering.StreamingKMeans strKMeans_";
  protected final String TEXT_53 = " = (org.apache.spark.mllib.clustering.StreamingKMeans) temporaryModel_";
  protected final String TEXT_54 = ";" + NL + "" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_55 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + "    org.apache.spark.streaming.api.java.JavaPairDStream<";
  protected final String TEXT_56 = ", ";
  protected final String TEXT_57 = "> transformedrdd_";
  protected final String TEXT_58 = " = rdd_";
  protected final String TEXT_59 = ".transformToPair(" + NL + "            new GenerateKeyVector_";
  protected final String TEXT_60 = "(vectorName_";
  protected final String TEXT_61 = ", featuresTransformations_";
  protected final String TEXT_62 = ", sqlContext_";
  protected final String TEXT_63 = "));" + NL + "" + NL + "    org.apache.spark.streaming.api.java.JavaPairDStream<";
  protected final String TEXT_64 = ", Integer> pairrdd_";
  protected final String TEXT_65 = " = strKMeans_";
  protected final String TEXT_66 = ".predictOnValues(transformedrdd_";
  protected final String TEXT_67 = ");" + NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_68 = "> rdd_";
  protected final String TEXT_69 = " = pairrdd_";
  protected final String TEXT_70 = ".map(new GetStreamingPrediction_";
  protected final String TEXT_71 = "());";
  protected final String TEXT_72 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
IConnection conn = null;
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();

Boolean modelOnFilesystem = ElementParameterParser.getBooleanValue(node, "__MODEL_ON_FILESYSTEM__");
Boolean modelComputed = ElementParameterParser.getBooleanValue(node, "__MODEL_COMPUTED__");

String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");

if (modelOnFilesystem) {
    // We are on Kmeans (or a Kmeans streaming dumped into a Kmeans format)
    
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    
} else if (modelComputed) {
    // We are on Kmeans streamimng
    String modelLocation = ElementParameterParser.getValue(node, "__MODEL_LOCATION__");

    Boolean inputAsPipelineModel = false;
    List<? extends INode> nodes = node.getProcess().getGeneratingNodes();
    for(INode targetNode : nodes){
        if (targetNode.getUniqueName().equals(modelLocation)) {
            // If the tKmeansStrModel load the pipeline from disk, it will save a PipelineModel.
            // Otherwise (the default case), it will be a Pipeline.
            // This is because this pipeline was read from HDFS and can be a complexe pipeline computed with a batch KMeans
            inputAsPipelineModel = ElementParameterParser.getBooleanValue(targetNode, "__REUSE_PIPELINE__")
                    && ElementParameterParser.getBooleanValue(targetNode, "__LOAD_FROM_DISK__");
            break;
        }
    }

    // retrieve name of the external component
    
    stringBuffer.append(TEXT_26);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
    if (inputAsPipelineModel) {
        
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
    } else {
        
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    
    }
    
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    
}

    stringBuffer.append(TEXT_72);
    return stringBuffer.toString();
  }
}
