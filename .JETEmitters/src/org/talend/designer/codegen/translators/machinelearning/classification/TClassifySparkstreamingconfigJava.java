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

public class TClassifySparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TClassifySparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySparkstreamingconfigJava result = new TClassifySparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_2 = "> rdd_";
  protected final String TEXT_3 = ";" + NL + "{";
  protected final String TEXT_4 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_5 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_6 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_7 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_8 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_9 = " = kryo_";
  protected final String TEXT_10 = ".readObject(featuresInput_";
  protected final String TEXT_11 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_12 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_13 = " = featuresTalendPipelineModel_";
  protected final String TEXT_14 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_15 = " = featuresTalendPipelineModel_";
  protected final String TEXT_16 = ".getPipelineModel();";
  protected final String TEXT_17 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_18 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_19 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_20 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_21 = NL + NL + "    rdd_";
  protected final String TEXT_22 = " = rdd_";
  protected final String TEXT_23 = ".transform(new Classify_";
  protected final String TEXT_24 = "(pipelineModel_";
  protected final String TEXT_25 = "));" + NL + "}" + NL;
  protected final String TEXT_26 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas == null) || (metadatas.size() == 0))
    return "" ;
IMetadataTable metadata = metadatas.get(0);
if (metadata == null)
    return "";
List<IMetadataColumn> columns = metadata.getListColumns();
if (columns == null)
    return "";
List<? extends IConnection> conns = node.getIncomingConnections();
if (conns == null || conns.size() == 0 || !conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
    return "";
IConnection inConn = conns.get(0);
conns = node.getOutgoingConnections();
if (conns == null || conns.size() == 0 || !conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
    return "";
IConnection outConn = conns.get(0);

String inRowStruct = codeGenArgument.getRecordStructName(inConn);
String inConnName = inConn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


// This is set to true if the output label is not double format.
// TODO: check if the labelColumn is double and provide a unique labelColumn.
boolean needsLabelIndexer = true;
String labelColumn = "label";

// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");


    stringBuffer.append(TEXT_1);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_3);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
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
    
    } else {
        String modelCid = ElementParameterParser.getValue(node, "__MODEL_LOCATION__");
        
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
    }
    
    stringBuffer.append(TEXT_21);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
