package org.talend.designer.codegen.translators.machinelearning;

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
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;
import org.talend.designer.spark.ml.features.*;
import org.talend.designer.spark.ml.features.impl.*;
import org.talend.designer.spark.ml.features.parameter.*;

public class TModelEncoderSparkconfigJava
{
  protected static String nl;
  public static synchronized TModelEncoderSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TModelEncoderSparkconfigJava result = new TModelEncoderSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t// Convert incoming RDD to DataFrame" + NL + "\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_2 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL + "\t";
  protected final String TEXT_3 = " ";
  protected final String TEXT_4 = " = sqlContext_";
  protected final String TEXT_5 = ".createDataFrame(rdd_";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = ".class);" + NL + "" + NL + "\t// Create holder for all transformations to perform" + NL + "\tjava.util.List<org.apache.spark.ml.PipelineStage> ";
  protected final String TEXT_8 = " = new java.util.ArrayList<org.apache.spark.ml.PipelineStage>();" + NL;
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = NL + "\tif(true) {" + NL + "\t\tthrow new Exception(\"";
  protected final String TEXT_12 = "\");" + NL + "\t}";
  protected final String TEXT_13 = NL + "\t";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = " = new ";
  protected final String TEXT_16 = "();";
  protected final String TEXT_17 = NL + "\t\t";
  protected final String TEXT_18 = ".setInputCol(\"";
  protected final String TEXT_19 = "\");";
  protected final String TEXT_20 = NL + "\t\t // ";
  protected final String TEXT_21 = " = ";
  protected final String TEXT_22 = ".withColumn(\"";
  protected final String TEXT_23 = "\", ";
  protected final String TEXT_24 = ".col(\"";
  protected final String TEXT_25 = "\").cast(new org.apache.spark.sql.types.ArrayType(org.apache.spark.sql.types.DataTypes.StringType, true)));";
  protected final String TEXT_26 = NL + "\t\t";
  protected final String TEXT_27 = ".setOutputCol(\"";
  protected final String TEXT_28 = "\");";
  protected final String TEXT_29 = NL + "\t\t";
  protected final String TEXT_30 = ".";
  protected final String TEXT_31 = ";";
  protected final String TEXT_32 = NL + "\t";
  protected final String TEXT_33 = ".add(";
  protected final String TEXT_34 = ");";
  protected final String TEXT_35 = NL + "\t";
  protected final String TEXT_36 = NL;
  protected final String TEXT_37 = NL + "\t\t// Create ML Pipeline from transformations created earlier and call the fit to generate pipelineModel" + NL + "\t\torg.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_38 = " = new org.apache.spark.ml.Pipeline().setStages(";
  protected final String TEXT_39 = ".toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_40 = ".size()]));" + NL + "\t\torg.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_41 = " = pipeline_";
  protected final String TEXT_42 = ".fit(";
  protected final String TEXT_43 = ");" + NL + "" + NL + "\t\t// Apply transformations to the input DF" + NL + "\t\t";
  protected final String TEXT_44 = " output_";
  protected final String TEXT_45 = " = pipelineModel_";
  protected final String TEXT_46 = ".transform(";
  protected final String TEXT_47 = ");" + NL + "" + NL + "\t\t// Convert outgoing DataFrame to RDD" + NL + "\t\torg.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_48 = "> rdd_";
  protected final String TEXT_49 = " = output_";
  protected final String TEXT_50 = ".toJavaRDD().map(" + NL + "        new ";
  protected final String TEXT_51 = "_FromRowTo";
  protected final String TEXT_52 = "());";

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
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
IConnection incomingConnection = tModelEncoderUtil.getIncomingConnection();
String incomingConnectionName = incomingConnection.getName();
String incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);
String stagesName = "stages_" + tModelEncoderUtil.getFirstModelEncoderName();
String trainingName = "training_" + tModelEncoderUtil.getFirstModelEncoderName();
String outStructName = codeGenArgument.getRecordStructName(tModelEncoderUtil.getOutgoingConnection());

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
    
java.util.List<java.util.Map<String, String>> transformationsTable = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node,"TRANSFORMATIONS_TABLE");
for (java.util.Map<String, String> transformationMap : transformationsTable){
	String function = transformationMap.get("FUNCTION");
	String inputColumn = transformationMap.get("INPUT_COLUMN");
	String outputColumn = transformationMap.get("OUTPUT_COLUMN");
	String params = transformationMap.get("PARAMS");
	
    stringBuffer.append(TEXT_10);
    
AbstractTransformation transformation = null;
try{
	transformation = TransformationHelper.getTransformation(function, params);
} catch(Exception e) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(e.getMessage());
    stringBuffer.append(TEXT_12);
    
}

if(transformation != null) {
	String variableName = transformation.getVariablePrefix()+"_"+inputColumn+"_"+outputColumn;
	String sparkMlClassName = transformation.getSparkMlClassName();

    stringBuffer.append(TEXT_13);
    stringBuffer.append(sparkMlClassName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(sparkMlClassName);
    stringBuffer.append(TEXT_16);
    	
	// Some transformations can support several input columns and don't have the "inputCol" property 
	if(transformation.hasSingleInputCol()) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_19);
    
	} 
	if(transformation.needsCastToArrayTypeOfStringType()) {
		// Some transformations like Word2Vector or CountVectorizer are expecting an ArrayType(StringType,true) as input column : 
		// its Java counterpart is List<String> (and not List<Object>) which we do not support in the Studio.
		// There is a trick to cast the input column type to be compliant with such transformations :

    stringBuffer.append(TEXT_20);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_25);
    
	}
	
	// Almost every transformation has the "outputCol" property, but some of them don't
	if(transformation.hasOutputCol()) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(outputColumn);
    stringBuffer.append(TEXT_28);
    
	}
	
	for(Parameter parameter : transformation.getParameters()) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(parameter.generateSetter());
    stringBuffer.append(TEXT_31);
    
	}

    stringBuffer.append(TEXT_32);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_34);
    
} // end if(transformation != null)

    stringBuffer.append(TEXT_35);
    
}

    stringBuffer.append(TEXT_36);
    
if(tModelEncoderUtil.isLastModelEncoder()) {
	if(!tModelEncoderUtil.isFollowedByMLComponent()) {

    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(tModelEncoderUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_52);
    
	}
} // end if(tModelEncoderUtil.isLastModelEncoder())

    return stringBuffer.toString();
  }
}
