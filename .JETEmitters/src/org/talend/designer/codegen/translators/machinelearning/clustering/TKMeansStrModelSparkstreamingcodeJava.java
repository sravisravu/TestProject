package org.talend.designer.codegen.translators.machinelearning.clustering;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;

public class TKMeansStrModelSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TKMeansStrModelSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKMeansStrModelSparkstreamingcodeJava result = new TKMeansStrModelSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    public static class GenerateVector_";
  protected final String TEXT_2 = NL + "        implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_3 = ">," + NL + "                org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_4 = ">> {" + NL + "" + NL + "        private org.apache.spark.sql.SQLContext context;" + NL + "        private org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "        private String vectorColumn;" + NL + "" + NL + "        public GenerateVector_";
  protected final String TEXT_5 = " (org.apache.spark.ml.PipelineModel pipelineModel, String vectorColumn, org.apache.spark.sql.SQLContext context) {" + NL + "            this.pipelineModel = pipelineModel;" + NL + "            this.vectorColumn = vectorColumn;" + NL + "            this.context = context;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_6 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_7 = "> inputRDD) throws Exception {";
  protected final String TEXT_8 = NL + "            ";
  protected final String TEXT_9 = " inputDataFrame = context.createDataFrame(inputRDD, ";
  protected final String TEXT_10 = ".class);";
  protected final String TEXT_11 = NL + "            ";
  protected final String TEXT_12 = " outputDataFrame = pipelineModel.transform(inputDataFrame);" + NL + "            return outputDataFrame.toJavaRDD().map(new GetVector_";
  protected final String TEXT_13 = "(vectorColumn));" + NL + "        }" + NL + "" + NL + "        public org.apache.spark.ml.PipelineModel getPipelineModel() {" + NL + "            return pipelineModel;" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public static class GetVector_";
  protected final String TEXT_14 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_15 = "> {" + NL + "" + NL + "        private String vectorColumn;" + NL + "" + NL + "        public GetVector_";
  protected final String TEXT_16 = " (String vectorColumn) {" + NL + "            this.vectorColumn = vectorColumn;" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_17 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_18 = NL + "            ";
  protected final String TEXT_19 = " result = null;" + NL + "            try {" + NL + "                result = (";
  protected final String TEXT_20 = ") input.get(input.fieldIndex(vectorColumn));" + NL + "            } catch (java.lang.ClassCastException e) {" + NL + "                // nothing, return null" + NL + "            }" + NL + "            return result;" + NL + "        }" + NL + "    }";
  protected final String TEXT_21 = NL + NL + "    public static class GenerateVector_";
  protected final String TEXT_22 = NL + "        implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_23 = ">," + NL + "                org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_24 = ">> {" + NL + "" + NL + "        private org.apache.spark.ml.Pipeline pipeline;" + NL + "        private org.apache.spark.sql.SQLContext context;" + NL + "        private org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "" + NL + "        public GenerateVector_";
  protected final String TEXT_25 = " (org.apache.spark.ml.Pipeline pipeline, org.apache.spark.sql.SQLContext context) {" + NL + "            this.pipeline = pipeline;" + NL + "            this.context = context;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_26 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_27 = "> inputRDD) throws Exception {";
  protected final String TEXT_28 = NL + "            ";
  protected final String TEXT_29 = " inputDataFrame = context.createDataFrame(inputRDD, ";
  protected final String TEXT_30 = ".class);" + NL + "            pipelineModel = pipeline.fit(inputDataFrame);";
  protected final String TEXT_31 = NL + "            ";
  protected final String TEXT_32 = " outputDataFrame = pipelineModel.transform(inputDataFrame);" + NL + "            return outputDataFrame.toJavaRDD().map(new GetVector_";
  protected final String TEXT_33 = "());" + NL + "        }" + NL + "" + NL + "        public org.apache.spark.ml.PipelineModel getPipelineModel() {" + NL + "            return pipelineModel;" + NL + "        }" + NL + "    }" + NL + "" + NL + "" + NL + "    public static class GetVector_";
  protected final String TEXT_34 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_35 = "> {" + NL + "" + NL + "        public ";
  protected final String TEXT_36 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_37 = NL + "            ";
  protected final String TEXT_38 = " result = null;" + NL + "            try {" + NL + "                result = (";
  protected final String TEXT_39 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_40 = "\"));" + NL + "            } catch (java.lang.ClassCastException e) {" + NL + "                // nothing, return null" + NL + "            }" + NL + "            return result;" + NL + "        }" + NL + "    }" + NL;
  protected final String TEXT_41 = NL + "        public static class SavingThread_";
  protected final String TEXT_42 = " extends Thread {" + NL + "            private FileSystem fs;" + NL + "            private org.apache.spark.mllib.clustering.StreamingKMeans model;" + NL + "            private org.apache.spark.SparkContext sc;" + NL + "            private org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "            private Map<String, String> paramsMap = new java.util.HashMap<String, String>();" + NL + "" + NL + "            public SavingThread_";
  protected final String TEXT_43 = "(org.apache.spark.mllib.clustering.StreamingKMeans model," + NL + "                    org.apache.spark.ml.PipelineModel pipelineModel," + NL + "                    String vectorColumn," + NL + "                    FileSystem fs, org.apache.spark.SparkContext sc) {" + NL + "                this.fs = fs;" + NL + "                this.model = model;" + NL + "                this.sc = sc;" + NL + "                this.pipelineModel = pipelineModel;" + NL + "                paramsMap.put(\"VECTOR_NAME\", vectorColumn);" + NL + "            }" + NL + "" + NL + "            @Override" + NL + "            public void run() {";
  protected final String TEXT_44 = NL + "                    for (";
  protected final String TEXT_45 = " center : model.model().clusterCenters()) {";
  protected final String TEXT_46 = NL + "                        ";
  protected final String TEXT_47 = "(center);" + NL + "                    }";
  protected final String TEXT_48 = NL + "                while (true) {" + NL + "                    try {" + NL + "                        Thread.sleep(";
  protected final String TEXT_49 = " * 60000);";
  protected final String TEXT_50 = NL + "                        ";
  protected final String TEXT_51 = "(\"Saving the model\");" + NL + "                        Path pathToDelete_";
  protected final String TEXT_52 = " = new Path(";
  protected final String TEXT_53 = ");" + NL + "                        if (fs.exists(pathToDelete_";
  protected final String TEXT_54 = ")) {" + NL + "                            fs.delete(pathToDelete_";
  protected final String TEXT_55 = ", true);" + NL + "                        }" + NL + "                        model.model().save(sc, ";
  protected final String TEXT_56 = " + \"/model\");" + NL + "" + NL + "                        // Serialize the model" + NL + "                        TalendPipelineModel featuresTalendPipelineModel = new TalendPipelineModel(pipelineModel, paramsMap);" + NL + "                        com.esotericsoftware.kryo.Kryo kryo= new com.esotericsoftware.kryo.Kryo();" + NL + "                        com.esotericsoftware.kryo.io.Output featuresOutput = new com.esotericsoftware.kryo.io.Output(" + NL + "                                fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_57 = " + \"/features\")));" + NL + "                        kryo.writeObject(featuresOutput, featuresTalendPipelineModel, new TalendPipelineModelSerializer());" + NL + "                        featuresOutput.close();" + NL;
  protected final String TEXT_58 = NL + "                        ";
  protected final String TEXT_59 = "(\"Saving done.\");";
  protected final String TEXT_60 = NL + "                            ";
  protected final String TEXT_61 = "(\"Model updated: \" + new java.util.Date());" + NL + "                            for (";
  protected final String TEXT_62 = " center : model.model().clusterCenters()) {";
  protected final String TEXT_63 = NL + "                                ";
  protected final String TEXT_64 = "(center);" + NL + "                            }";
  protected final String TEXT_65 = NL + "                    } catch (Exception e) {" + NL + "                        e.printStackTrace();" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "        }";
  protected final String TEXT_66 = NL + "        public static class SavingThread_";
  protected final String TEXT_67 = " extends Thread {" + NL + "            private FileSystem fs;" + NL + "            private org.apache.spark.mllib.clustering.StreamingKMeans model;" + NL + "            private org.apache.spark.SparkContext sc;" + NL + "            private GenerateVector_";
  protected final String TEXT_68 = " pipelineEncoder;" + NL + "            private Map<String, String> paramsMap = new java.util.HashMap<String, String>();" + NL + "" + NL + "            public SavingThread_";
  protected final String TEXT_69 = "(org.apache.spark.mllib.clustering.StreamingKMeans model," + NL + "                    GenerateVector_";
  protected final String TEXT_70 = " pipelineEncoder," + NL + "                    FileSystem fs, org.apache.spark.SparkContext sc) {" + NL + "                this.fs = fs;" + NL + "                this.model = model;" + NL + "                this.sc = sc;" + NL + "                this.pipelineEncoder = pipelineEncoder;" + NL + "" + NL + "                paramsMap.put(\"VECTOR_NAME\", \"";
  protected final String TEXT_71 = "\");" + NL + "            }" + NL + "" + NL + "            @Override" + NL + "            public void run() {";
  protected final String TEXT_72 = NL + "                    for (";
  protected final String TEXT_73 = " center : model.model().clusterCenters()) {";
  protected final String TEXT_74 = NL + "                        ";
  protected final String TEXT_75 = "(center);" + NL + "                    }";
  protected final String TEXT_76 = NL + "                while (true) {" + NL + "                    try {" + NL + "                        Thread.sleep(";
  protected final String TEXT_77 = " * 60000);";
  protected final String TEXT_78 = NL + "                        ";
  protected final String TEXT_79 = "(\"Saving the model\");" + NL + "                        Path pathToDelete_";
  protected final String TEXT_80 = " = new Path(";
  protected final String TEXT_81 = ");" + NL + "                        if (fs.exists(pathToDelete_";
  protected final String TEXT_82 = ")) {" + NL + "                            fs.delete(pathToDelete_";
  protected final String TEXT_83 = ", true);" + NL + "                        }" + NL + "                        model.model().save(sc, ";
  protected final String TEXT_84 = " + \"/model\");" + NL + "" + NL + "                        // Serialize the model" + NL + "" + NL + "                        TalendPipelineModel featuresTalendPipelineModel = new TalendPipelineModel(pipelineEncoder.getPipelineModel(), paramsMap);" + NL + "                        com.esotericsoftware.kryo.Kryo kryo= new com.esotericsoftware.kryo.Kryo();" + NL + "                        com.esotericsoftware.kryo.io.Output featuresOutput = new com.esotericsoftware.kryo.io.Output(" + NL + "                                fs.create(new org.apache.hadoop.fs.Path( ";
  protected final String TEXT_85 = " + \"/features\")));" + NL + "                        kryo.writeObject(featuresOutput, featuresTalendPipelineModel, new TalendPipelineModelSerializer());" + NL + "                        featuresOutput.close();" + NL;
  protected final String TEXT_86 = NL + "                        ";
  protected final String TEXT_87 = "(\"Saving done.\");";
  protected final String TEXT_88 = NL + "                            ";
  protected final String TEXT_89 = "(\"Model updated: \" + new java.util.Date());" + NL + "                            for (";
  protected final String TEXT_90 = " center : model.model().clusterCenters()) {";
  protected final String TEXT_91 = NL + "                                ";
  protected final String TEXT_92 = "(center);" + NL + "                            }";
  protected final String TEXT_93 = NL + "                    } catch (Exception e) {" + NL + "                        e.printStackTrace();" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "        }";
  protected final String TEXT_94 = NL + "    public static class DisplayVectorSize_";
  protected final String TEXT_95 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_96 = ">, Void> {" + NL + "        @Override" + NL + "        public Void call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_97 = "> inputrdd) throws Exception {" + NL + "            List<";
  protected final String TEXT_98 = "> vectors = inputrdd.take(1);" + NL + "            if (vectors.size() < 1) {" + NL + "                System.out.println(\"DStream not started yet\");" + NL + "            } else {" + NL + "                System.out.println(\"Vector size: \" + vectors.get(0).size());" + NL + "            }" + NL + "            return null;" + NL + "        }" + NL + "    }";
  protected final String TEXT_99 = NL + NL + NL;
  protected final String TEXT_100 = NL;

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

TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
String inStructName = tModelEncoderUtil.getFirstModelEncoderStructName(codeGenArgument);

String inputVectorColumn = ElementParameterParser.getValue(node, "__INPUT_COLUMN__");

Boolean reusePipeline = ElementParameterParser.getBooleanValue(node, "__REUSE_PIPELINE__");
Boolean loadFromDisk = ElementParameterParser.getBooleanValue(node, "__LOAD_FROM_DISK__");

if (reusePipeline && loadFromDisk) {
    
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_20);
    
} else {

    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_40);
    
}

Boolean saveOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String saveDelay = ElementParameterParser.getValue(node, "__SAVE_DELAY__");
Boolean printCenters = ElementParameterParser.getBooleanValue(node, "__PRINT_CENTERS__");
String modelPath = ElementParameterParser.getValue(node, "__MODEL_PATH__");
if (saveOnDisk) {
    if (reusePipeline && loadFromDisk) {
        
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    
                if (printCenters) {
                    
    stringBuffer.append(TEXT_44);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_47);
    
                }
                
    stringBuffer.append(TEXT_48);
    stringBuffer.append(saveDelay);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_59);
    
                        if (printCenters) {
                            
    stringBuffer.append(TEXT_60);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_64);
    
                        }
                        
    stringBuffer.append(TEXT_65);
    
    } else {
        
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_71);
    
                if (printCenters) {
                    
    stringBuffer.append(TEXT_72);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_75);
    
                }
                
    stringBuffer.append(TEXT_76);
    stringBuffer.append(saveDelay);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_87);
    
                        if (printCenters) {
                            
    stringBuffer.append(TEXT_88);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(outputFramework);
    stringBuffer.append(TEXT_92);
    
                        }
                        
    stringBuffer.append(TEXT_93);
    
    }
}

Boolean displayVectorSize = ElementParameterParser.getBooleanValue(node, "__DISPLAY_VECTOR_SIZE__");
if (displayVectorSize) {
    
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_98);
    
}

    stringBuffer.append(TEXT_99);
    stringBuffer.append(TEXT_100);
    return stringBuffer.toString();
  }
}
