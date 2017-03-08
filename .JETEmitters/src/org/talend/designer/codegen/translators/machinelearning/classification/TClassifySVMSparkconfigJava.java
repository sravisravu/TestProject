package org.talend.designer.codegen.translators.machinelearning.classification;

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

public class TClassifySVMSparkconfigJava
{
  protected static String nl;
  public static synchronized TClassifySVMSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySVMSparkconfigJava result = new TClassifySVMSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_3 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_4 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_5 = " + \"/features\")));" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_6 = " = kryo_";
  protected final String TEXT_7 = ".readObject(featuresInput_";
  protected final String TEXT_8 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_9 = " = org.apache.spark.mllib.classification.SVMModel.load(ctx.sc(), ";
  protected final String TEXT_10 = " + \"/model\");";
  protected final String TEXT_11 = NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_12 = " = (TalendPipelineModel)globalMap.get(\"";
  protected final String TEXT_13 = "_PIPELINE\");" + NL + "" + NL + "    Object temporaryModel_";
  protected final String TEXT_14 = " = globalMap.get(\"";
  protected final String TEXT_15 = "_MODEL\");" + NL + "    if (temporaryModel_";
  protected final String TEXT_16 = " == null) {" + NL + "        throw new RuntimeException(\"The selected model does not exist\");" + NL + "    }else if (!(temporaryModel_";
  protected final String TEXT_17 = " instanceof org.apache.spark.mllib.classification.SVMModel)) {" + NL + "        throw new RuntimeException(\"The selected model is of type \" + temporaryModel_";
  protected final String TEXT_18 = ".getClass() + \" is should be of type org.apache.spark.mllib.classification.SVMModel\");" + NL + "    }" + NL + "    org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_19 = " = (org.apache.spark.mllib.classification.SVMModel) temporaryModel_";
  protected final String TEXT_20 = ";" + NL;
  protected final String TEXT_21 = NL + "java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_22 = " = featuresTalendPipelineModel_";
  protected final String TEXT_23 = ".getParams();" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_24 = " = featuresTalendPipelineModel_";
  protected final String TEXT_25 = ".getPipelineModel();" + NL + "String vectorName_";
  protected final String TEXT_26 = " = featuresParamsMap_";
  protected final String TEXT_27 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "// Pipeline" + NL + "org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_28 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_29 = NL;
  protected final String TEXT_30 = " inputDataFrame";
  protected final String TEXT_31 = " = sqlContext_";
  protected final String TEXT_32 = ".createDataFrame(rdd_";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ".class);";
  protected final String TEXT_35 = NL;
  protected final String TEXT_36 = " transformedInputDataFrame_";
  protected final String TEXT_37 = " = featuresTransformationsModel_";
  protected final String TEXT_38 = ".transform(inputDataFrame";
  protected final String TEXT_39 = ");" + NL + "" + NL + "// Model" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_40 = "> temporaryrdd_";
  protected final String TEXT_41 = " = transformedInputDataFrame_";
  protected final String TEXT_42 = ".toJavaRDD().map(new GetEncodedStruct_";
  protected final String TEXT_43 = "(vectorName_";
  protected final String TEXT_44 = "));" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_45 = "> rdd_";
  protected final String TEXT_46 = " = temporaryrdd_";
  protected final String TEXT_47 = ".map(new GetPrediction_";
  protected final String TEXT_48 = "(currentModel_";
  protected final String TEXT_49 = "));" + NL;
  protected final String TEXT_50 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
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
String inRowStructEncoded = conn.getName() + "Encoded";
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


Boolean modelOnFilesystem = ElementParameterParser.getBooleanValue(node, "__MODEL_ON_FILESYSTEM__");
Boolean modelOnJob = ElementParameterParser.getBooleanValue(node, "__MODEL_COMPUTED__");

if (modelOnFilesystem) {
    String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");

    
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
    
} else if (modelOnJob) {

    //retrieve name of the external component
    String modelLocation = ElementParameterParser.getValue(node, "__MODEL_LOCATION__");
    
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(modelLocation);
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
    
}

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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(TEXT_50);
    return stringBuffer.toString();
  }
}
