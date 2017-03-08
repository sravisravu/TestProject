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

public class TDecisionTreeModelSparkconfigJava
{
  protected static String nl;
  public static synchronized TDecisionTreeModelSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDecisionTreeModelSparkconfigJava result = new TDecisionTreeModelSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "org.apache.spark.ml.classification.DecisionTreeClassifier classifier_";
  protected final String TEXT_2 = " = new org.apache.spark.ml.classification.DecisionTreeClassifier();";
  protected final String TEXT_3 = NL + "            ";
  protected final String TEXT_4 = NL + "            classifier_";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = "(\"";
  protected final String TEXT_7 = "\");";
  protected final String TEXT_8 = NL + "            ";
  protected final String TEXT_9 = NL + "            classifier_";
  protected final String TEXT_10 = ".";
  protected final String TEXT_11 = "(";
  protected final String TEXT_12 = ");";
  protected final String TEXT_13 = NL + "            // Force the random seed" + NL + "            classifier_";
  protected final String TEXT_14 = ".setSeed(";
  protected final String TEXT_15 = ");";
  protected final String TEXT_16 = NL + "// Create a parameters hashmap, this map will contain any information that you want to use in the prediction phase. The map will be serialized and made available for the prediction job." + NL + "java.util.Map<String, String> paramsMap_";
  protected final String TEXT_17 = " = new java.util.HashMap<String, String>();" + NL + "paramsMap_";
  protected final String TEXT_18 = ".put(\"MY_ORIGINAL_FEATURES_COLUMN\", \"";
  protected final String TEXT_19 = "\");" + NL + "" + NL + "// Create the features transformations pipeline (To be used in the training and prediction phases to apply the same transformations on the input) :" + NL + "org.apache.spark.ml.Pipeline featuresTransformationsPipeline_";
  protected final String TEXT_20 = " = new org.apache.spark.ml.Pipeline().setStages(";
  protected final String TEXT_21 = ".toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_22 = ".size()]));" + NL;
  protected final String TEXT_23 = NL + "    org.apache.spark.ml.feature.StringIndexer s2i_";
  protected final String TEXT_24 = " = new org.apache.spark.ml.feature.StringIndexer();" + NL + "    s2i_";
  protected final String TEXT_25 = ".setInputCol(\"";
  protected final String TEXT_26 = "\");" + NL + "    s2i_";
  protected final String TEXT_27 = ".setOutputCol(\"indexedLabel\");" + NL + "    org.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_28 = " = new org.apache.spark.ml.Pipeline().setStages(" + NL + "            new org.apache.spark.ml.PipelineStage[] {" + NL + "            featuresTransformationsPipeline_";
  protected final String TEXT_29 = "," + NL + "            s2i_";
  protected final String TEXT_30 = "," + NL + "            classifier_";
  protected final String TEXT_31 = "});";
  protected final String TEXT_32 = NL + "    org.apache.spark.ml.Pipeline pipeline_";
  protected final String TEXT_33 = " = new org.apache.spark.ml.Pipeline().setStages(" + NL + "            new org.apache.spark.ml.PipelineStage[] {" + NL + "            featuresTransformationsPipeline_";
  protected final String TEXT_34 = "," + NL + "            classifier_";
  protected final String TEXT_35 = "});";
  protected final String TEXT_36 = NL + "org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_37 = " = pipeline_";
  protected final String TEXT_38 = ".fit(";
  protected final String TEXT_39 = ");" + NL + "" + NL + "// save on global map" + NL + "globalMap.put(\"";
  protected final String TEXT_40 = "_MODEL\", pipelineModel_";
  protected final String TEXT_41 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_42 = "_PIPELINE\", pipeline_";
  protected final String TEXT_43 = ");" + NL;
  protected final String TEXT_44 = NL + "    Path pathToDelete_";
  protected final String TEXT_45 = " = new Path(";
  protected final String TEXT_46 = ");" + NL + "    if (fs.exists(pathToDelete_";
  protected final String TEXT_47 = ")) {" + NL + "        fs.delete(pathToDelete_";
  protected final String TEXT_48 = ", true);" + NL + "    }" + NL + "" + NL + "    // Serialize the model" + NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_49 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Output modelOutput_";
  protected final String TEXT_50 = " = new com.esotericsoftware.kryo.io.Output(" + NL + "            fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_51 = " + \"/model\")));" + NL + "    kryo_";
  protected final String TEXT_52 = ".writeObject(modelOutput_";
  protected final String TEXT_53 = ", new TalendPipelineModel(pipelineModel_";
  protected final String TEXT_54 = ", paramsMap_";
  protected final String TEXT_55 = "), new TalendPipelineModelSerializer());" + NL + "    modelOutput_";
  protected final String TEXT_56 = ".close();";
  protected final String TEXT_57 = NL;

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

// The features column
String featuresColumn = ElementParameterParser.getValue(node,"__FEATURES_COLUMN__");

// The label column
String labelInputColumn = ElementParameterParser.getValue(node,"__LABEL_COLUMN__");

// HDFS folder to save in
Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}

// This is set to true if the label is not already in double format.
// TODO: check if the labelColumn is double and provide a unique labelColumn.
boolean needsLabelIndexer = true;
String labelColumn = "indexedLabel";

// Logistic regression parameters
String predictionColumn = "unique82464359435";
String rawPredictionColumn = null; // useless, we never use this parameter, so set to null

String impurity = ElementParameterParser.getValue(node,"__IMPURITY__");
String maxBins = ElementParameterParser.getValue(node,"__MAX_BINS__");
String maxDepth = ElementParameterParser.getValue(node,"__MAX_DEPTH__");
String minInfoGain = ElementParameterParser.getValue(node,"__MIN_INFO_GAIN__");
String minInstancesPerNode = ElementParameterParser.getValue(node,"__MIN_INSTANCES_PER_NODE__");
String checkpointInterval = ElementParameterParser.getValue(node,"__MAX_MEMORY_IN_MB__");
String maxMemoryInMB = ElementParameterParser.getValue(node,"__CHECKPOINT_INTERVAL__");

// Generate and configure the linear regression model.  The rows in this table:
// - The method name on the model to call (if the value is not empty),
// - Whether the value to set should be quoted.
// - The value to set (the entire row is ignored if null or empty), and
// - A comment to append.
Object[][] modelParameters = {
    {"setFeaturesCol", true, featuresColumn , ""},
    {"setLabelCol", true, needsLabelIndexer ? labelColumn : labelInputColumn,"// the transformed label column"},
    {"setRawPredictionCol", true, rawPredictionColumn, "// predicted label (double)"},
    {"setPredictionCol", true, predictionColumn, "// predicted label (double)"},

    // Decision Tree Parameters
    {"setImpurity", true, impurity, "// impurity method (Gini or Entropy)"},
    {"setMaxBins", false, maxBins, "// Maximum number of bins used for discretizing continuous feature (int)"},
    {"setMaxDepth", false, maxDepth, "// Maximum depth of the tree (int)"},
    {"setMinInfoGain", false, minInfoGain, "// Minimum information gain for a split to be considered at a tree node (double)"},
    {"setMinInstancesPerNode", false, minInstancesPerNode, "// Minimum number of instances each child must have after split (int)"},

    // advanced parameters
    {"setCheckpointInterval", false, checkpointInterval, "// Specifies how often to checkpoint the cached node IDs (>= 1) or disable checkpoint (-1) (int)"},
    {"setMaxMemoryInMB", false, maxMemoryInMB, "// Maximum memory in MB allocated to histogram aggregation (int)"},
    };

// Use the model parameters array to configure the model.

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
for (Object[] modelParameter: modelParameters) {
    if (null != modelParameter[2] && !"".equals(modelParameter[2])) {
        if ((Boolean) modelParameter[1]) {
            
    stringBuffer.append(TEXT_3);
    stringBuffer.append(modelParameter[3]);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(modelParameter[0]);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelParameter[2]);
    stringBuffer.append(TEXT_7);
    
        } else {
            
    stringBuffer.append(TEXT_8);
    stringBuffer.append(modelParameter[3]);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(modelParameter[0]);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(modelParameter[2]);
    stringBuffer.append(TEXT_12);
    
        }
    }
}

// specific case of the seed.
// Compatible only for spark 1.6+

List<? extends INode> sparkConfigList = node.getProcess().getNodesOfType("tSparkConfiguration"); //$NON-NLS-1$
if (sparkConfigList != null && sparkConfigList.size() > 0) {
    // The tSparkConfiguration is a singleton in a Spark job.
    INode sparkConfig = sparkConfigList.get(0);
    org.talend.hadoop.distribution.ESparkVersion sparkVersion =  org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(sparkConfig);

    if (!"SPARK_1_4".equals(sparkVersion.toString()) && !"SPARK_1_5".equals(sparkVersion.toString())) {
        Boolean useSeed = ElementParameterParser.getBooleanValue(node,"__USE_SEED__");
        if (useSeed) {
            String seed = ElementParameterParser.getValue(node,"__SEED__");
            
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(seed);
    stringBuffer.append(TEXT_15);
    
        }
    }
}


    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(featuresColumn);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_22);
    
if (needsLabelIndexer) {
    
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(labelInputColumn);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
} else {
    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    
}

    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(trainingDataFrame);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
     if (saveOnDisk) {
    
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
}

    stringBuffer.append(TEXT_57);
    return stringBuffer.toString();
  }
}
