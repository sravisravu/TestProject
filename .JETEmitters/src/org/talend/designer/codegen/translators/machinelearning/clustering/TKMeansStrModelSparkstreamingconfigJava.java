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

public class TKMeansStrModelSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TKMeansStrModelSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKMeansStrModelSparkstreamingconfigJava result = new TKMeansStrModelSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "// Create the model";
  protected final String TEXT_2 = NL + "// The number of cluster is useless when loading an existing model, but we have to set one." + NL + "org.apache.spark.mllib.clustering.StreamingKMeans strKMeans_";
  protected final String TEXT_3 = " = new org.apache.spark.mllib.clustering.StreamingKMeans(";
  protected final String TEXT_4 = NL + "        ";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = ", org.apache.spark.mllib.clustering.StreamingKMeans.";
  protected final String TEXT_7 = "());" + NL + "" + NL + "" + NL + "// Initialize it";
  protected final String TEXT_8 = NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_9 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_10 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_11 = " + \"/features\")));" + NL + "" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_12 = " = kryo_";
  protected final String TEXT_13 = ".readObject(featuresInput_";
  protected final String TEXT_14 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    java.util.Map<String, String> paramsMap_";
  protected final String TEXT_15 = " = featuresTalendPipelineModel_";
  protected final String TEXT_16 = ".getParams();" + NL + "    org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_17 = " = featuresTalendPipelineModel_";
  protected final String TEXT_18 = ".getPipelineModel();" + NL + "    String vectorName_";
  protected final String TEXT_19 = " = paramsMap_";
  protected final String TEXT_20 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_21 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "    GenerateVector_";
  protected final String TEXT_22 = " pipelineEncoder_";
  protected final String TEXT_23 = " = new GenerateVector_";
  protected final String TEXT_24 = "(featuresTransformationsModel_";
  protected final String TEXT_25 = ", vectorName_";
  protected final String TEXT_26 = ", sqlContext_";
  protected final String TEXT_27 = ");" + NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_28 = "> temporaryrdd_";
  protected final String TEXT_29 = " = rdd_";
  protected final String TEXT_30 = NL + "            .transform(pipelineEncoder_";
  protected final String TEXT_31 = ");" + NL + "    temporaryrdd_";
  protected final String TEXT_32 = ".cache();";
  protected final String TEXT_33 = NL + "    Map<String, String> paramsMap_";
  protected final String TEXT_34 = " = new java.util.HashMap<String, String>();" + NL + "    paramsMap_";
  protected final String TEXT_35 = ".put(\"VECTOR_NAME\", \"";
  protected final String TEXT_36 = "\");" + NL + "    // Retrieve and use the transformation pipeline" + NL + "    org.apache.spark.ml.Pipeline featuresTransformationsPipeline_";
  protected final String TEXT_37 = " = new org.apache.spark.ml.Pipeline()" + NL + "        .setStages(";
  protected final String TEXT_38 = NL + "        .toArray(new org.apache.spark.ml.PipelineStage[";
  protected final String TEXT_39 = ".size()]));" + NL + "" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_40 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "    GenerateVector_";
  protected final String TEXT_41 = " pipelineEncoder_";
  protected final String TEXT_42 = " = new GenerateVector_";
  protected final String TEXT_43 = "(featuresTransformationsPipeline_";
  protected final String TEXT_44 = ", sqlContext_";
  protected final String TEXT_45 = ");" + NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_46 = "> temporaryrdd_";
  protected final String TEXT_47 = " = ";
  protected final String TEXT_48 = NL + "            .transform(pipelineEncoder_";
  protected final String TEXT_49 = ");" + NL + "    temporaryrdd_";
  protected final String TEXT_50 = ".cache();";
  protected final String TEXT_51 = NL + "    temporaryrdd_";
  protected final String TEXT_52 = ".foreachRDD(new DisplayVectorSize_";
  protected final String TEXT_53 = "());";
  protected final String TEXT_54 = NL + "    // retrieve model" + NL + "    try {" + NL + "        org.apache.spark.mllib.clustering.KMeansModel kMeans_";
  protected final String TEXT_55 = " = org.apache.spark.mllib.clustering.KMeansModel.load(ctx.sparkContext().sc(), ";
  protected final String TEXT_56 = " + \"/model\");" + NL + "        double[] weights_";
  protected final String TEXT_57 = " = new double[kMeans_";
  protected final String TEXT_58 = ".clusterCenters().length];" + NL + "        for (int i = 0; i < weights_";
  protected final String TEXT_59 = ".length; i++) {" + NL + "            weights_";
  protected final String TEXT_60 = "[i] = 1.0;" + NL + "        }" + NL + "        strKMeans_";
  protected final String TEXT_61 = ".setInitialCenters(kMeans_";
  protected final String TEXT_62 = ".clusterCenters(), weights_";
  protected final String TEXT_63 = ");" + NL + "    } catch (Exception noModelFoundException) {" + NL + "        // if the model does not exist, use a random initialization" + NL;
  protected final String TEXT_64 = NL + "        ";
  protected final String TEXT_65 = "(\"The model path is invalid: \" +  ";
  protected final String TEXT_66 = " + \".\");";
  protected final String TEXT_67 = NL + "        ";
  protected final String TEXT_68 = "(\"It will be initialized as random.\");" + NL + "        strKMeans_";
  protected final String TEXT_69 = ".setRandomCenters(";
  protected final String TEXT_70 = ", 1.0d, (new java.util.Random()).nextLong());" + NL + "    }";
  protected final String TEXT_71 = NL + "    strKMeans_";
  protected final String TEXT_72 = ".setRandomCenters(";
  protected final String TEXT_73 = ", 1.0d, (new java.util.Random()).nextLong());";
  protected final String TEXT_74 = NL + NL + "strKMeans_";
  protected final String TEXT_75 = ".trainOn(temporaryrdd_";
  protected final String TEXT_76 = ".dstream());" + NL;
  protected final String TEXT_77 = NL + "    for (";
  protected final String TEXT_78 = " center_";
  protected final String TEXT_79 = " : strKMeans_";
  protected final String TEXT_80 = ".model().clusterCenters()) {";
  protected final String TEXT_81 = NL + "        ";
  protected final String TEXT_82 = "(center_";
  protected final String TEXT_83 = ");" + NL + "    }";
  protected final String TEXT_84 = NL + "    TalendPipelineModel featuresTalendPipeline_";
  protected final String TEXT_85 = " = new TalendPipelineModel(featuresTransformationsModel_";
  protected final String TEXT_86 = ", paramsMap_";
  protected final String TEXT_87 = ");";
  protected final String TEXT_88 = NL + "    TalendPipeline featuresTalendPipeline_";
  protected final String TEXT_89 = " = new TalendPipeline(featuresTransformationsPipeline_";
  protected final String TEXT_90 = ", paramsMap_";
  protected final String TEXT_91 = ");";
  protected final String TEXT_92 = NL + NL + "globalMap.putLocal(\"";
  protected final String TEXT_93 = "_MODEL\", strKMeans_";
  protected final String TEXT_94 = ");" + NL + "globalMap.putLocal(\"";
  protected final String TEXT_95 = "_PIPELINE\", featuresTalendPipeline_";
  protected final String TEXT_96 = ");" + NL;
  protected final String TEXT_97 = NL + "        //Saving the model" + NL + "        (new SavingThread_";
  protected final String TEXT_98 = "(strKMeans_";
  protected final String TEXT_99 = ", featuresTransformationsModel_";
  protected final String TEXT_100 = ", vectorName_";
  protected final String TEXT_101 = ", fs, ctx.sparkContext().sc())).start();";
  protected final String TEXT_102 = NL + "        //Saving the model" + NL + "        (new SavingThread_";
  protected final String TEXT_103 = "(strKMeans_";
  protected final String TEXT_104 = ", pipelineEncoder_";
  protected final String TEXT_105 = ", fs, ctx.sparkContext().sc())).start();";
  protected final String TEXT_106 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
String outputFramework = isLog4jEnabled ? "log.info" : "System.out.println";
String outputFrameworkWarning = isLog4jEnabled ? "log.warn" : "System.out.println";

TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
String inRowStruct = tModelEncoderUtil.getFirstModelEncoderStructName(codeGenArgument);
String trainingDataFrame = "training_" + tModelEncoderUtil.getFirstModelEncoderName();
String modelEncoderTransformationsList = "stages_" + tModelEncoderUtil.getFirstModelEncoderName();

IConnection conn = null;
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}


if(conn == null){
    return "";
}

String inConnName = codeGenArgument.getRecordStructName(conn);

    stringBuffer.append(TEXT_1);
    
String k = ElementParameterParser.getValue(node, "__K_NUMBER__");
String decayFactor = ElementParameterParser.getValue(node, "__DECAY__");
String timeUnit = ElementParameterParser.getValue(node, "__TIME_UNIT__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(k);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(decayFactor);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(timeUnit);
    stringBuffer.append(TEXT_7);
    
Boolean loadFromDisk = ElementParameterParser.getBooleanValue(node, "__LOAD_FROM_DISK__");
String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");
String inputVectorColumn = ElementParameterParser.getValue(node, "__INPUT_COLUMN__");
Boolean reusePipeline = ElementParameterParser.getBooleanValue(node, "__REUSE_PIPELINE__");

// retrieve Pipeline
if (loadFromDisk && reusePipeline) {
    
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(modelPath);
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
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    
} else { // pipeline from a modelEncoder
    
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(modelEncoderTransformationsList);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(modelEncoderTransformationsList);
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
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(trainingDataFrame);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
}

Boolean displayVectorSize = ElementParameterParser.getBooleanValue(node, "__DISPLAY_VECTOR_SIZE__");
if (displayVectorSize) {
    
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    
}

String vectorSize = ElementParameterParser.getValue(node, "__VECTOR_SIZE__");
if (loadFromDisk) {
    
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
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
    stringBuffer.append(TEXT_64);
    stringBuffer.append(outputFrameworkWarning);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(outputFrameworkWarning);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(vectorSize);
    stringBuffer.append(TEXT_70);
    
} else { // random initialization
    
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(vectorSize);
    stringBuffer.append(TEXT_73);
    
}

    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    
Boolean printCenters = ElementParameterParser.getBooleanValue(node, "__PRINT_CENTERS__");
if (printCenters) {
    
    stringBuffer.append(TEXT_77);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    
}


// Save on global map

if (reusePipeline && loadFromDisk) {
    
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    
} else {
    
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    
}

    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    
Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
if (saveOnDisk) {
    if (reusePipeline && loadFromDisk) {
        
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    
    } else {
        
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    
    }
}

    stringBuffer.append(TEXT_106);
    return stringBuffer.toString();
  }
}
