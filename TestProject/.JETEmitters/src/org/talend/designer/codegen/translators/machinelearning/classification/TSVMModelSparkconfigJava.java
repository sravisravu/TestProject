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

public class TSVMModelSparkconfigJava
{
  protected static String nl;
  public static synchronized TSVMModelSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSVMModelSparkconfigJava result = new TSVMModelSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "Map<String, String> paramsMap_";
  protected final String TEXT_3 = " = new java.util.HashMap<String, String>();" + NL + "paramsMap_";
  protected final String TEXT_4 = ".put(\"VECTOR_NAME\", \"";
  protected final String TEXT_5 = "\");" + NL + "" + NL + "// Retrieve and use the transformation pipeline" + NL + "org.apache.spark.ml.Pipeline featuresTransformationsPipeline_";
  protected final String TEXT_6 = " = new org.apache.spark.ml.Pipeline()" + NL + "    .setStages(";
  protected final String TEXT_7 = NL + "    .toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_8 = ".size()]));" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_9 = " = featuresTransformationsPipeline_";
  protected final String TEXT_10 = ".fit(";
  protected final String TEXT_11 = ");";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = " transformedFeatures_";
  protected final String TEXT_14 = " = featuresTransformationsModel_";
  protected final String TEXT_15 = ".transform(";
  protected final String TEXT_16 = ");" + NL + "" + NL + "// retrieve the vector RDD" + NL + "org.apache.spark.api.java.JavaRDD<org.apache.spark.mllib.regression.LabeledPoint> temporaryrdd_";
  protected final String TEXT_17 = " = transformedFeatures_";
  protected final String TEXT_18 = ".toJavaRDD().map(new GetLabeledPoint_";
  protected final String TEXT_19 = "());" + NL + "temporaryrdd_";
  protected final String TEXT_20 = ".cache();" + NL + "" + NL + "// create the Kmeans object" + NL + "org.apache.spark.mllib.classification.SVMWithSGD svm_";
  protected final String TEXT_21 = " = new org.apache.spark.mllib.classification.SVMWithSGD();" + NL;
  protected final String TEXT_22 = NL + "svm_";
  protected final String TEXT_23 = ".optimizer()" + NL + "    .setStepSize(";
  protected final String TEXT_24 = ")" + NL + "    .setNumIterations(";
  protected final String TEXT_25 = ")" + NL + "    .setRegParam(";
  protected final String TEXT_26 = ")" + NL + "    .setMiniBatchFraction(";
  protected final String TEXT_27 = ");" + NL;
  protected final String TEXT_28 = NL + "        svm_";
  protected final String TEXT_29 = ".optimizer().setConvergenceTol(";
  protected final String TEXT_30 = ");";
  protected final String TEXT_31 = NL + NL + "svm_";
  protected final String TEXT_32 = NL + "    .setFeatureScaling(";
  protected final String TEXT_33 = ")" + NL + "    .setIntercept(";
  protected final String TEXT_34 = ")" + NL + "    .setValidateData(";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "svm_";
  protected final String TEXT_37 = ".optimizer().setUpdater(";
  protected final String TEXT_38 = ");";
  protected final String TEXT_39 = NL + NL + "// Launch the training and retreive the model" + NL + "org.apache.spark.mllib.classification.SVMModel svmModel_";
  protected final String TEXT_40 = " = svm_";
  protected final String TEXT_41 = ".run(temporaryrdd_";
  protected final String TEXT_42 = ".rdd());" + NL + "" + NL + "// Saving" + NL + "TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_43 = " = new TalendPipelineModel(featuresTransformationsModel_";
  protected final String TEXT_44 = ", paramsMap_";
  protected final String TEXT_45 = ");";
  protected final String TEXT_46 = NL + NL + "// save on global map" + NL + "globalMap.put(\"";
  protected final String TEXT_47 = "_MODEL\", svmModel_";
  protected final String TEXT_48 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_49 = "_PIPELINE\", featuresTalendPipelineModel_";
  protected final String TEXT_50 = ");" + NL;
  protected final String TEXT_51 = NL + "    Path pathToDelete_";
  protected final String TEXT_52 = " = new Path(";
  protected final String TEXT_53 = ");" + NL + "    if (fs.exists(pathToDelete_";
  protected final String TEXT_54 = ")) {" + NL + "        fs.delete(pathToDelete_";
  protected final String TEXT_55 = ", true);" + NL + "    }" + NL + "" + NL + "    // Serialize the model" + NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_56 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Output featuresOutput_";
  protected final String TEXT_57 = " = new com.esotericsoftware.kryo.io.Output(" + NL + "            fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_58 = " + \"/features\")));" + NL + "    kryo_";
  protected final String TEXT_59 = ".writeObject(featuresOutput_";
  protected final String TEXT_60 = ", featuresTalendPipelineModel_";
  protected final String TEXT_61 = ", new TalendPipelineModelSerializer());" + NL + "    featuresOutput_";
  protected final String TEXT_62 = ".close();" + NL + "    svmModel_";
  protected final String TEXT_63 = ".save(ctx.sc(), ";
  protected final String TEXT_64 = " + \"/model\");";
  protected final String TEXT_65 = NL;

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

    
TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);

IConnection conn = null;
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

if (conn == null) {
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String connName = conn.getName();

String trainingDataFrame = "training_" + tModelEncoderUtil.getFirstModelEncoderName();
String modelEncoderTransformationsList = "stages_" + tModelEncoderUtil.getFirstModelEncoderName();

String inputVectorColumn = ElementParameterParser.getValue(node, "__INPUT_COLUMN__");


    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(trainingDataFrame);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(trainingDataFrame);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
// Handle parameters
String stepSize = ElementParameterParser.getValue(node, "__STEP_SIZE__");
String numIterations = ElementParameterParser.getValue(node, "__NUM_ITERATION__");
String regParam = ElementParameterParser.getValue(node, "__REG_PARAM__");
String miniBatchFraction = ElementParameterParser.getValue(node, "__MINI_BATCH_FRACTION__");
String convergenceTol = ElementParameterParser.getValue(node, "__CONVERGENCE_TOL__");
String featureScaling = ElementParameterParser.getValue(node, "__FEATURE_SCALING__");
String intercept = ElementParameterParser.getValue(node, "__INTERCEPT__");
String validateData = ElementParameterParser.getValue(node, "__VALIDATE_DATA__");

String updater = ElementParameterParser.getValue(node, "__UPDATER__");
String gradient = ElementParameterParser.getValue(node, "__GRADIENT__");


    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(stepSize);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(numIterations);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(regParam);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(miniBatchFraction);
    stringBuffer.append(TEXT_27);
    
// ConvergenceTol is only available for spark 1.6+

List<? extends INode> sparkConfigList = node.getProcess().getNodesOfType("tSparkConfiguration"); //$NON-NLS-1$
if (sparkConfigList != null && sparkConfigList.size() > 0) {
    // The tSparkConfiguration is a singleton in a Spark job.
    INode sparkConfig = sparkConfigList.get(0);
    org.talend.hadoop.distribution.ESparkVersion sparkVersion =  org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(sparkConfig);

    if (!"SPARK_1_4".equals(sparkVersion.toString()) && !"SPARK_1_5".equals(sparkVersion.toString())) {
        
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(convergenceTol);
    stringBuffer.append(TEXT_30);
    
    }
}

    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(featureScaling);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(intercept);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(validateData);
    stringBuffer.append(TEXT_35);
    

// handle specifically Updater and Gradient
String updaterObject = "";
if ("SIMPLE_UPDATER".equals(updater)) {
    updaterObject = "new org.apache.spark.mllib.optimization.SimpleUpdater()";
} else if ("SQUARED_L2UPDATER".equals(updater)) {
    updaterObject = "new org.apache.spark.mllib.optimization.SquaredL2Updater()";
} else { // L1UPDATER
    updaterObject = "new org.apache.spark.mllib.optimization.L1Updater()";
}

    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(updaterObject);
    stringBuffer.append(TEXT_38);
    

String gradientObject = "";
if ("LEAST_SQUARES_GRADIENT".equals(gradient)) {
    gradientObject = "new org.apache.spark.mllib.optimization.LeastSquaresGradient()";
} else if ("LOGISTIC_GRADIENT".equals(gradient)) {
    gradientObject = "new org.apache.spark.mllib.optimization.LogisticGradient()";
} else { // HINGE_GRADIENT
    gradientObject = "new org.apache.spark.mllib.optimization.HingeGradient()";
}

    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    modelPath = uriPrefix + " + " + modelPath;
}


    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
     if (saveOnDisk) {
    
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_64);
    
}

    stringBuffer.append(TEXT_65);
    return stringBuffer.toString();
  }
}
