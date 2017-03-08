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
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;

public class TRandomForestModelSparkconfigJava
{
  protected static String nl;
  public static synchronized TRandomForestModelSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRandomForestModelSparkconfigJava result = new TRandomForestModelSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "// 0.0 - Create a parameters hashmap, this map will contain any information that you want to use in the prediction phase." + NL + "java.util.Map<String, String> paramsMap_";
  protected final String TEXT_2 = " = new java.util.HashMap<String, String>();" + NL + "paramsMap_";
  protected final String TEXT_3 = ".put(\"MY_ORIGINAL_FEATURES_COLUMN\", \"";
  protected final String TEXT_4 = "\");" + NL + "" + NL + "// 1.0 - Create the features transformations pipeline." + NL + "org.apache.spark.ml.Pipeline featuresTransformationsPipeline_";
  protected final String TEXT_5 = " =" + NL + "\tnew org.apache.spark.ml.Pipeline()" + NL + "\t\t.setStages(";
  protected final String TEXT_6 = NL + "\t\t.toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_7 = ".size()]));" + NL + "" + NL + "// 2.0 - Create the label transformations pipeline." + NL + "\t\t// the StringIndexer is required here for this classification component" + NL + "\t\t// 1. if it's a string column, this func will convert it to double column," + NL + "\t\t// 2. if it's already a double column, this func will transform to a 0-based double column" + NL + "\t\t// Note: for binary classification, a label should be either 0 (negative) or 1 (positive)," + NL + "\t\t// and for multiclass classification, labels should be class indices starting from zero: 0, 1, 2, ...." + NL + "org.apache.spark.ml.feature.StringIndexer s2i_";
  protected final String TEXT_8 = " =" + NL + "\tnew org.apache.spark.ml.feature.StringIndexer()" + NL + "\t\t.setInputCol(\"";
  protected final String TEXT_9 = "\")" + NL + "\t\t.setOutputCol(\"indexedLabel\");" + NL + "" + NL + "// 3.0 - Create the RandomForestClassifier" + NL + "org.apache.spark.ml.classification.RandomForestClassifier rf_";
  protected final String TEXT_10 = " =" + NL + "\tnew org.apache.spark.ml.classification.RandomForestClassifier()" + NL + "\t\t.setLabelCol(\"indexedLabel\")// labelCol should be indexed by StringIndexer in previous steps, otherwise spark will throw a IllegalArgumentException: invalid label column label, without the number of classes specified." + NL + "\t\t.setFeaturesCol(\"";
  protected final String TEXT_11 = "\")" + NL + "\t\t.setPredictionCol(\"";
  protected final String TEXT_12 = "\")" + NL + "\t\t.setNumTrees(";
  protected final String TEXT_13 = ")" + NL + "\t\t.setMaxDepth(";
  protected final String TEXT_14 = ")" + NL + "\t\t.setSubsamplingRate(";
  protected final String TEXT_15 = ")" + NL + "\t\t.setFeatureSubsetStrategy(\"";
  protected final String TEXT_16 = "\")" + NL + "\t    .setMaxBins(";
  protected final String TEXT_17 = ")" + NL + "\t    .setMinInfoGain(";
  protected final String TEXT_18 = ")" + NL + "\t    .setMinInstancesPerNode(";
  protected final String TEXT_19 = ")" + NL + "\t    .setImpurity(\"";
  protected final String TEXT_20 = "\")";
  protected final String TEXT_21 = NL + "\t\t.setSeed(";
  protected final String TEXT_22 = ");";
  protected final String TEXT_23 = NL + "\t\t;";
  protected final String TEXT_24 = NL + NL + "// 4.0 - setStages to the pipeline" + NL + "org.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_25 = " =" + NL + "\tnew org.apache.spark.ml.Pipeline().setStages(" + NL + "\t\tnew org.apache.spark.ml.PipelineStage[] {" + NL + "\t\t\t\tfeaturesTransformationsPipeline_";
  protected final String TEXT_26 = ", // indexedFeatures" + NL + "\t\t\t\ts2i_";
  protected final String TEXT_27 = ", // indexedLabel" + NL + "\t\t\t\trf_";
  protected final String TEXT_28 = " // RandomForestClassifier" + NL + "\t\t}" + NL + "\t);" + NL + "" + NL + "org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_29 = " = pipeline_";
  protected final String TEXT_30 = ".fit(";
  protected final String TEXT_31 = ");" + NL + "" + NL + "// save on global map" + NL + "globalMap.put(\"";
  protected final String TEXT_32 = "_MODEL\", pipelineModel_";
  protected final String TEXT_33 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_34 = "_PIPELINE\", pipeline_";
  protected final String TEXT_35 = ");" + NL;
  protected final String TEXT_36 = NL + "    Path pathToDelete_";
  protected final String TEXT_37 = " = new Path(";
  protected final String TEXT_38 = ");" + NL + "    if (fs.exists(pathToDelete_";
  protected final String TEXT_39 = ")) {" + NL + "        fs.delete(pathToDelete_";
  protected final String TEXT_40 = ", true);" + NL + "    }" + NL + "" + NL + "    // Serialize the model" + NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_41 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Output modelOutput_";
  protected final String TEXT_42 = " = new com.esotericsoftware.kryo.io.Output(" + NL + "            fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_43 = " + \"/model\")));" + NL + "    kryo_";
  protected final String TEXT_44 = ".writeObject(modelOutput_";
  protected final String TEXT_45 = "," + NL + "    \t\tnew TalendPipelineModel(pipelineModel_";
  protected final String TEXT_46 = ", paramsMap_";
  protected final String TEXT_47 = ")," + NL + "    \t\tnew TalendPipelineModelSerializer());" + NL + "    modelOutput_";
  protected final String TEXT_48 = ".close();";
  protected final String TEXT_49 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);

// The name of the training DataFrame
String trainingDataFrame = "training_" + tModelEncoderUtil.getFirstModelEncoderName();

// The name of the List containing all of the ModelEncoder transformations (List<org.apache.spark.ml.PipelineStage>)
String modelEncoderTransformationsList = "stages_" + tModelEncoderUtil.getFirstModelEncoderName();

/********* Input columns info *******/
// The name of label column
String labelColumn = ElementParameterParser.getValue(node,"__LABEL_COLUMN__");
// The name of features column
String featuresColumn = ElementParameterParser.getValue(node,"__FEATURES_COLUMN__");

/******** Hyper-parameters for tuning**********/
// http://spark.apache.org/docs/latest/mllib-ensembles.html#usage-tips
// The number of trees
String numTrees = ElementParameterParser.getValue(node,"__NUM_TREES__");
// max depth
String maxDepth = ElementParameterParser.getValue(node,"__MAX_DEPTH__");

/*********** Model saving ************/
// True if save model on disk(file system), otherwise false
Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
// HDFS folder to save in
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}

/********* Advanced settings **********/
//Subsampling Rate
String subsamplingRate = ElementParameterParser.getValue(node,"__SUBSAMPLING_RATE__");
//Feature Subset Strategy
String featureSubsetStrategy = ElementParameterParser.getValue(node,"__FEATURE_SUBSET_STRATEGY__");
//max bins
String maxBins = ElementParameterParser.getValue(node,"__MAX_BINS__");
//minInfoGain
String minInfoGain = ElementParameterParser.getValue(node,"__MIN_INFO_GAIN__");
//minInstancesPerNode
String minInstancesPerNode = ElementParameterParser.getValue(node,"__MIN_INSTANCES_PER_NODE__");
//impurity
String impurity = ElementParameterParser.getValue(node,"__IMPURITY__");
//seed
Boolean hasSeed = ElementParameterParser.getBooleanValue(node, "__HASSEED__");
String seed = ElementParameterParser.getValue(node,"__SEED__");

// TODO currently, synchronize with tLogisticRegressionModel for using the same component(classify) in the prediction prase
String predictionColumn = "unique82464359435"; // ElementParameterParser.getValue(node,"__PREDICTION_COLUMN__");

/**** TODO: (expert-only) Parameters ****/


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(featuresColumn);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(labelColumn);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(featuresColumn);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(predictionColumn);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(numTrees);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(maxDepth);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(subsamplingRate);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(featureSubsetStrategy);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(maxBins);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(minInfoGain);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(minInstancesPerNode);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(impurity);
    stringBuffer.append(TEXT_20);
     if(hasSeed) { 
    stringBuffer.append(TEXT_21);
    stringBuffer.append(seed);
    stringBuffer.append(TEXT_22);
    
}else{
    stringBuffer.append(TEXT_23);
    
}

    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(trainingDataFrame);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
     if (saveOnDisk) {
    
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
}

    stringBuffer.append(TEXT_49);
    return stringBuffer.toString();
  }
}
