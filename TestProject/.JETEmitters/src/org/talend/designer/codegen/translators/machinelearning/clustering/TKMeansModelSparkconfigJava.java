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
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;

public class TKMeansModelSparkconfigJava
{
  protected static String nl;
  public static synchronized TKMeansModelSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKMeansModelSparkconfigJava result = new TKMeansModelSparkconfigJava();
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
  protected final String TEXT_16 = ");" + NL + "" + NL + "// retrieve the vector RDD" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_17 = "> temporaryrdd_";
  protected final String TEXT_18 = " = transformedFeatures_";
  protected final String TEXT_19 = ".toJavaRDD().map(new GetVector_";
  protected final String TEXT_20 = "());" + NL + "temporaryrdd_";
  protected final String TEXT_21 = ".cache();" + NL + "" + NL + "// create the Kmeans object" + NL + "org.apache.spark.mllib.clustering.KMeans kMeans_";
  protected final String TEXT_22 = " = new org.apache.spark.mllib.clustering.KMeans();" + NL + "" + NL + "// Handle parameters";
  protected final String TEXT_23 = NL + "// set the number of cluster every time" + NL + "kMeans_";
  protected final String TEXT_24 = ".setK(";
  protected final String TEXT_25 = ");" + NL;
  protected final String TEXT_26 = NL + "    kMeans_";
  protected final String TEXT_27 = ".setEpsilon(";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = NL + "    kMeans_";
  protected final String TEXT_30 = ".setMaxIterations(";
  protected final String TEXT_31 = ");";
  protected final String TEXT_32 = NL + "    kMeans_";
  protected final String TEXT_33 = ".setRuns(";
  protected final String TEXT_34 = ");";
  protected final String TEXT_35 = NL + "    kMeans_";
  protected final String TEXT_36 = ".setInitializationMode(org.apache.spark.mllib.clustering.KMeans.K_MEANS_PARALLEL());" + NL;
  protected final String TEXT_37 = NL + "        kMeans_";
  protected final String TEXT_38 = ".setInitializationSteps(";
  protected final String TEXT_39 = ");";
  protected final String TEXT_40 = NL + "    kMeans_";
  protected final String TEXT_41 = ".setInitializationMode(org.apache.spark.mllib.clustering.KMeans.RANDOM());";
  protected final String TEXT_42 = NL + "    kMeans_";
  protected final String TEXT_43 = ".setSeed(";
  protected final String TEXT_44 = ");";
  protected final String TEXT_45 = NL + NL + "// Launch the training and retreive the model" + NL + "org.apache.spark.mllib.clustering.KMeansModel kMeansModel_";
  protected final String TEXT_46 = " = kMeans_";
  protected final String TEXT_47 = ".run(temporaryrdd_";
  protected final String TEXT_48 = ".rdd());" + NL;
  protected final String TEXT_49 = NL + "        for (";
  protected final String TEXT_50 = " center_";
  protected final String TEXT_51 = " : kMeansModel_";
  protected final String TEXT_52 = ".clusterCenters()) {" + NL + "            log.info(center_";
  protected final String TEXT_53 = ");" + NL + "        }";
  protected final String TEXT_54 = NL + "        for (";
  protected final String TEXT_55 = " center_";
  protected final String TEXT_56 = " : kMeansModel_";
  protected final String TEXT_57 = ".clusterCenters()) {" + NL + "            System.out.println(center_";
  protected final String TEXT_58 = ");" + NL + "        }";
  protected final String TEXT_59 = NL + NL + "// Saving" + NL + "TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_60 = " = new TalendPipelineModel(featuresTransformationsModel_";
  protected final String TEXT_61 = ", paramsMap_";
  protected final String TEXT_62 = ");";
  protected final String TEXT_63 = NL + NL + "// save on global map" + NL + "globalMap.put(\"";
  protected final String TEXT_64 = "_MODEL\", kMeansModel_";
  protected final String TEXT_65 = ");" + NL + "globalMap.put(\"";
  protected final String TEXT_66 = "_PIPELINE\", featuresTalendPipelineModel_";
  protected final String TEXT_67 = ");" + NL;
  protected final String TEXT_68 = NL + "    Path pathToDelete_";
  protected final String TEXT_69 = " = new Path(";
  protected final String TEXT_70 = ");" + NL + "    if (fs.exists(pathToDelete_";
  protected final String TEXT_71 = ")) {" + NL + "        fs.delete(pathToDelete_";
  protected final String TEXT_72 = ", true);" + NL + "    }" + NL + "" + NL + "    // Serialize the model" + NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_73 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Output featuresOutput_";
  protected final String TEXT_74 = " = new com.esotericsoftware.kryo.io.Output(" + NL + "            fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_75 = " + \"/features\")));" + NL + "    kryo_";
  protected final String TEXT_76 = ".writeObject(featuresOutput_";
  protected final String TEXT_77 = ", featuresTalendPipelineModel_";
  protected final String TEXT_78 = ", new TalendPipelineModelSerializer());" + NL + "    featuresOutput_";
  protected final String TEXT_79 = ".close();" + NL + "    kMeansModel_";
  protected final String TEXT_80 = ".save(ctx.sc(), ";
  protected final String TEXT_81 = " + \"/model\");";
  protected final String TEXT_82 = NL;

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
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
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
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    
String k = ElementParameterParser.getValue(node, "__K_NUMBER__");
Boolean setEpsilonLimit = ElementParameterParser.getBooleanValue(node, "__SET_EPSILON_LIMIT__");
String epsilonLimit = ElementParameterParser.getValue(node, "__EPSILON_LIMIT__");
Boolean setMaxIteration = ElementParameterParser.getBooleanValue(node, "__SET_MAX_ITERATION__");
String maxIteration = ElementParameterParser.getValue(node, "__MAX_ITERATION__");
Boolean setRuns = ElementParameterParser.getBooleanValue(node, "__SET_RUNS__");
String runs = ElementParameterParser.getValue(node, "__RUNS__");

// can be K_MEANS_PARALLEL or RANDOM
String initializationFunction = ElementParameterParser.getValue(node, "__INITIALIZATION_FUNCTION__");
Boolean setInitializationSteps = ElementParameterParser.getBooleanValue(node, "__SET_INITIALIZATION_STEPS__");
String initializationSteps = ElementParameterParser.getValue(node, "__INITIALIZATION_STEPS__");
Boolean setSeed = ElementParameterParser.getBooleanValue(node, "__SET_SEED__");
String seed = ElementParameterParser.getValue(node, "__SEED__");

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(k);
    stringBuffer.append(TEXT_25);
    
if (setEpsilonLimit) {
    
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(epsilonLimit);
    stringBuffer.append(TEXT_28);
    
}

if (setMaxIteration) {
    
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(maxIteration);
    stringBuffer.append(TEXT_31);
    
}

if (setRuns) {
    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(runs);
    stringBuffer.append(TEXT_34);
    
}

if ("K_MEANS_PARALLEL".equals(initializationFunction)) {
    
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    
    if (setInitializationSteps) {
        
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(initializationSteps);
    stringBuffer.append(TEXT_39);
    
    }
} else { // RANDOM
    
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
}

if (setSeed) {
    
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(seed);
    stringBuffer.append(TEXT_44);
    
}

    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
Boolean printCenters = ElementParameterParser.getBooleanValue(node, "__PRINT_CENTERS__");
if (printCenters) {
    if(isLog4jEnabled){
        
    stringBuffer.append(TEXT_49);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    
    } else {
        
    stringBuffer.append(TEXT_54);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    
    }
}

    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    modelPath = uriPrefix + " + " + modelPath;
}

    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
     if (saveOnDisk) {
    
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_81);
    
}

    stringBuffer.append(TEXT_82);
    return stringBuffer.toString();
  }
}
