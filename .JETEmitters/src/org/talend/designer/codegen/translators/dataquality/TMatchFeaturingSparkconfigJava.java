package org.talend.designer.codegen.translators.dataquality;

import java.util.ArrayList;
import java.util.List;
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

public class TMatchFeaturingSparkconfigJava
{
  protected static String nl;
  public static synchronized TMatchFeaturingSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchFeaturingSparkconfigJava result = new TMatchFeaturingSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "    //convert the incoming RDD to DataFrame" + NL + "\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_2 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL + "\t";
  protected final String TEXT_3 = NL + "    ";
  protected final String TEXT_4 = " ";
  protected final String TEXT_5 = " = org.talend.dataquality.reconciliation.api.PairsReshaping.horizontalize(" + NL + "\t        sqlContext_";
  protected final String TEXT_6 = ".createDataFrame(rdd_";
  protected final String TEXT_7 = ", ";
  protected final String TEXT_8 = ".class)," + NL + "\t        new org.talend.dataquality.reconciliation.api.PairsReshaping.LabelColName(\"";
  protected final String TEXT_9 = "\", \"";
  protected final String TEXT_10 = "\")" + NL + "\t);" + NL + "" + NL + "    // Create holder for all transformations to perform" + NL + "    java.util.List<org.apache.spark.ml.PipelineStage> ";
  protected final String TEXT_11 = " =" + NL + "            new java.util.ArrayList<org.apache.spark.ml.PipelineStage>();" + NL + "" + NL + "    // List of columnNames for generating the features vector" + NL + "    java.util.List<String> ";
  protected final String TEXT_12 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_13 = NL + "              ";
  protected final String TEXT_14 = ".add(\"";
  protected final String TEXT_15 = "\");";
  protected final String TEXT_16 = NL + NL + "    // Add suffixes to the original input columns" + NL + "    String [] inputColsWithSuffix_";
  protected final String TEXT_17 = " = org.talend.dataquality.reconciliation.api.PairsReshaping.appendSuffix2ColNames(";
  protected final String TEXT_18 = NL + "            ";
  protected final String TEXT_19 = ".toArray(new String[";
  protected final String TEXT_20 = ".size()]));" + NL + "" + NL + "    // Add stage: SimilarityVectorGenerator into Pipeline";
  protected final String TEXT_21 = NL + "    ";
  protected final String TEXT_22 = ".add(new org.talend.dataquality.reconciliation.ml.transformer.SimilarityVectorGenerator()" + NL + "                .setInputCols(inputColsWithSuffix_";
  protected final String TEXT_23 = ")" + NL + "                .setOutputCol(\"";
  protected final String TEXT_24 = "\"));" + NL;
  protected final String TEXT_25 = NL + "            // Create ML Pipeline from transformations created earlier and call the fit to generate pipelineModel" + NL + "            org.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_26 = " = new org.apache.spark.ml.Pipeline().setStages(";
  protected final String TEXT_27 = NL + "                    ";
  protected final String TEXT_28 = ".toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_29 = ".size()]));" + NL + "            org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_30 = " = pipeline_";
  protected final String TEXT_31 = ".fit(";
  protected final String TEXT_32 = ");" + NL + "" + NL + "            // Apply transformations to the input DF";
  protected final String TEXT_33 = NL + "            ";
  protected final String TEXT_34 = " output_";
  protected final String TEXT_35 = " = pipelineModel_";
  protected final String TEXT_36 = ".transform(";
  protected final String TEXT_37 = ");" + NL + "" + NL + "            // Convert outgoing DataFrame to RDD" + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_38 = "> rdd_";
  protected final String TEXT_39 = " =" + NL + "                    output_";
  protected final String TEXT_40 = ".toJavaRDD().map(new ";
  protected final String TEXT_41 = "_FromRowTo";
  protected final String TEXT_42 = "());";
  protected final String TEXT_43 = NL;
  protected final String TEXT_44 = NL + "    //TODO";
  protected final String TEXT_45 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.DataFrame";//keep using dataFrame even on spark 2.0

    
//IO Connections
TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
IConnection incomingConnection = tModelEncoderUtil.getIncomingConnection();
String incomingConnectionName = incomingConnection.getName();
String incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);
String stagesName = "stages_" + cid;
String trainingName = "training_" + cid;
String outStructName = codeGenArgument.getRecordStructName(tModelEncoderUtil.getOutgoingConnection());

//Component params
List<Map<String, String>> inputCols = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__COLUMN_SELECTOR_TABLE__");
String selectedColNames = "inputCols_" + cid;
String inputLabelColumn = ElementParameterParser.getValue(node,"__LABEL_COLUMN__");
String outputLabelColumn = "matchingLabel";
String outputFeaturesColumn = "matchingFeatures";

//Advanced params
String thresholdText = ElementParameterParser.getValue(node, "__THRESHOLD_TEXT__");

if(tModelEncoderUtil.isFirstModelEncoder()) {
// make sure that tMatchFeaturing at the head of MLPipeline, no other tModelEncoder before

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(inputLabelColumn);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outputLabelColumn);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(selectedColNames);
    stringBuffer.append(TEXT_12);
    
       if((inputCols!=null)&&(inputCols.size() > 0)){
          for(Map<String,String> colMap: inputCols){
    
    stringBuffer.append(TEXT_13);
    stringBuffer.append(selectedColNames);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(colMap.get("FEATURE_COLUMN"));
    stringBuffer.append(TEXT_15);
    
          }
       }
    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(selectedColNames);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(selectedColNames);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(outputFeaturesColumn);
    stringBuffer.append(TEXT_24);
    
    if(tModelEncoderUtil.isLastModelEncoder()) {
        if(!tModelEncoderUtil.isFollowedByMLComponent()) {
    
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(tModelEncoderUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_42);
    
        }
    } // end if(tModelEncoderUtil.isLastModelEncoder())
    
    stringBuffer.append(TEXT_43);
    
}else{

    stringBuffer.append(TEXT_44);
    
}

    stringBuffer.append(TEXT_45);
    return stringBuffer.toString();
  }
}
