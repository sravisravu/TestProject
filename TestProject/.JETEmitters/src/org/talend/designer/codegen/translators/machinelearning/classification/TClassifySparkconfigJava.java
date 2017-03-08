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

public class TClassifySparkconfigJava
{
  protected static String nl;
  public static synchronized TClassifySparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySparkconfigJava result = new TClassifySparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "org.apache.spark.api.java.JavaRDD<";
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
  protected final String TEXT_21 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_22 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_23 = NL + "    ";
  protected final String TEXT_24 = " df_";
  protected final String TEXT_25 = " = sqlContext_";
  protected final String TEXT_26 = ".createDataFrame(rdd_";
  protected final String TEXT_27 = ", ";
  protected final String TEXT_28 = ".class);";
  protected final String TEXT_29 = NL + "    ";
  protected final String TEXT_30 = " results = pipelineModel_";
  protected final String TEXT_31 = ".transform(df_";
  protected final String TEXT_32 = ");" + NL;
  protected final String TEXT_33 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel_";
  protected final String TEXT_34 = ".stages()[1];" + NL;
  protected final String TEXT_35 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_36 = "> rdd = results.map(" + NL + "                    new StringIndexerInverseFunction_";
  protected final String TEXT_37 = "(sim)," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_38 = ".class));" + NL + "            rdd_";
  protected final String TEXT_39 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_40 = NL + "            rdd_";
  protected final String TEXT_41 = " = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_42 = "(sim)," + NL + "                    org.apache.spark.sql.Encoders.bean(";
  protected final String TEXT_43 = ".class)).toJavaRDD();";
  protected final String TEXT_44 = NL + "        // TODO";
  protected final String TEXT_45 = NL + "}" + NL;
  protected final String TEXT_46 = NL;

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
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_32);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
        if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
            
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_39);
    
        } else {
            
    stringBuffer.append(TEXT_40);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_43);
    
        }
    } else {
        
    stringBuffer.append(TEXT_44);
    
    }
    
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    return stringBuffer.toString();
  }
}
