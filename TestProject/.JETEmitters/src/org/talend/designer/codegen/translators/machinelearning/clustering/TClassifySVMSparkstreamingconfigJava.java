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

public class TClassifySVMSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TClassifySVMSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySVMSparkstreamingconfigJava result = new TClassifySVMSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL;
  protected final String TEXT_2 = NL + "com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_3 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_4 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_5 = " + \"/features\")));" + NL + "TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_6 = " = kryo_";
  protected final String TEXT_7 = ".readObject(featuresInput_";
  protected final String TEXT_8 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_9 = " = org.apache.spark.mllib.classification.SVMModel.load(ctx.sparkContext().sc(), ";
  protected final String TEXT_10 = " + \"/model\");" + NL + "" + NL + "java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_11 = " = featuresTalendPipelineModel_";
  protected final String TEXT_12 = ".getParams();" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_13 = " = featuresTalendPipelineModel_";
  protected final String TEXT_14 = ".getPipelineModel();" + NL + "String vectorName_";
  protected final String TEXT_15 = " = featuresParamsMap_";
  protected final String TEXT_16 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "" + NL + " // Create context" + NL + " org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_17 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + " // Go to RDD view in order to use dataframe" + NL + " org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_18 = "> rdd_";
  protected final String TEXT_19 = " =" + NL + "     rdd_";
  protected final String TEXT_20 = ".transform(new GenerateEncodedStruct_";
  protected final String TEXT_21 = "(vectorName_";
  protected final String TEXT_22 = ", featuresTransformationsModel_";
  protected final String TEXT_23 = ", sqlContext_";
  protected final String TEXT_24 = "))" + NL + "             .map(new GetPrediction_";
  protected final String TEXT_25 = "(currentModel_";
  protected final String TEXT_26 = "));" + NL;
  protected final String TEXT_27 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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


String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");


// We are on SVM

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(modelPath);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(inConnName);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
