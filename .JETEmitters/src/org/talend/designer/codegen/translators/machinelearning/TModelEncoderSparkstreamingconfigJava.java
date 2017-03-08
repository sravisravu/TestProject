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

public class TModelEncoderSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TModelEncoderSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TModelEncoderSparkstreamingconfigJava result = new TModelEncoderSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\torg.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_2 = "> ";
  protected final String TEXT_3 = " = rdd_";
  protected final String TEXT_4 = ";" + NL + "" + NL + "\t// Create holder for all transformations to perform" + NL + "\tjava.util.List<org.apache.spark.ml.Transformer> ";
  protected final String TEXT_5 = " = new java.util.ArrayList<org.apache.spark.ml.Transformer>();";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL + "\t";
  protected final String TEXT_8 = NL + "\tif(true) {" + NL + "\t\tthrow new Exception(\"";
  protected final String TEXT_9 = "\");" + NL + "\t}";
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = " = new ";
  protected final String TEXT_13 = "();";
  protected final String TEXT_14 = NL + "\t\t";
  protected final String TEXT_15 = ".setInputCol(\"";
  protected final String TEXT_16 = "\");";
  protected final String TEXT_17 = NL + "\t\t // ";
  protected final String TEXT_18 = " = ";
  protected final String TEXT_19 = ".withColumn(\"";
  protected final String TEXT_20 = "\", ";
  protected final String TEXT_21 = ".col(\"";
  protected final String TEXT_22 = "\").cast(new org.apache.spark.sql.types.ArrayType(org.apache.spark.sql.types.DataTypes.StringType, true)));";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = ".setOutputCol(\"";
  protected final String TEXT_25 = "\");";
  protected final String TEXT_26 = NL + "\t\t";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = ";";
  protected final String TEXT_29 = NL + "\t";
  protected final String TEXT_30 = ".add(";
  protected final String TEXT_31 = ");";
  protected final String TEXT_32 = NL + "\t";
  protected final String TEXT_33 = NL;
  protected final String TEXT_34 = NL + "\t\t// Create output DStream" + NL + "\t\torg.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_35 = "> rdd_";
  protected final String TEXT_36 = " = ";
  protected final String TEXT_37 = ".transform(new Function_";
  protected final String TEXT_38 = "(";
  protected final String TEXT_39 = "));";

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
	// We're on the first tModelEncoder, so we need to set the input DStream name

    stringBuffer.append(TEXT_1);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_5);
    
} // end if(tModelEncoderUtil.isFirstModelEncoder())

    stringBuffer.append(TEXT_6);
    
java.util.List<java.util.Map<String, String>> transformationsTable = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node,"TRANSFORMATIONS_TABLE");
for (java.util.Map<String, String> transformationMap : transformationsTable){
	String function = transformationMap.get("FUNCTION");
	String inputColumn = transformationMap.get("INPUT_COLUMN");
	String outputColumn = transformationMap.get("OUTPUT_COLUMN");
	String params = transformationMap.get("PARAMS");
	
    stringBuffer.append(TEXT_7);
    
AbstractTransformation transformation = null;
try{
	transformation = TransformationHelper.getTransformation(function, params);
} catch(Exception e) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(e.getMessage());
    stringBuffer.append(TEXT_9);
    
}

if(transformation != null) {
	String variableName = transformation.getVariablePrefix()+"_"+inputColumn+"_"+outputColumn;
	String sparkMlClassName = transformation.getSparkMlClassName();

    stringBuffer.append(TEXT_10);
    stringBuffer.append(sparkMlClassName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(sparkMlClassName);
    stringBuffer.append(TEXT_13);
    	
	// Some transformations can support several input columns and don't have the "inputCol" property 
	if(transformation.hasSingleInputCol()) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_16);
    
	} 
	if(transformation.needsCastToArrayTypeOfStringType()) {
		// Some transformations like Word2Vector or CountVectorizer are expecting an ArrayType(StringType,true) as input column : 
		// its Java counterpart is List<String> (and not List<Object>) which we do not support in the Studio.
		// There is a trick to cast the input column type to be compliant with such transformations :

    stringBuffer.append(TEXT_17);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(inputColumn);
    stringBuffer.append(TEXT_22);
    
	}
	
	// Almost every transformation has the "outputCol" property, but some of them don't
	if(transformation.hasOutputCol()) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(outputColumn);
    stringBuffer.append(TEXT_25);
    
	}
	
	for(Parameter parameter : transformation.getParameters()) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(parameter.generateSetter());
    stringBuffer.append(TEXT_28);
    
	}

    stringBuffer.append(TEXT_29);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(variableName);
    stringBuffer.append(TEXT_31);
    
} // end if(transformation != null)

    stringBuffer.append(TEXT_32);
    
}

    stringBuffer.append(TEXT_33);
    
if(tModelEncoderUtil.isLastModelEncoder()) {
	if(!tModelEncoderUtil.isFollowedByMLComponent()) {

    stringBuffer.append(TEXT_34);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(tModelEncoderUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(trainingName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(stagesName);
    stringBuffer.append(TEXT_39);
    
	}
} // end if(tModelEncoderUtil.isLastModelEncoder())

    return stringBuffer.toString();
  }
}
