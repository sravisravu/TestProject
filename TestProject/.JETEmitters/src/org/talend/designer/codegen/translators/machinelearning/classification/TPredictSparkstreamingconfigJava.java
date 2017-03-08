package org.talend.designer.codegen.translators.machinelearning.classification;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IBigDataNode;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TPredictSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TPredictSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictSparkstreamingconfigJava result = new TPredictSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "// 1. Model Loading" + NL + "// Common processing for both batch & streaming version of tPredict" + NL + "final org.apache.spark.mllib.classification.talend.NaiveBayesRegularRecordsModel model_";
  protected final String TEXT_2 = " =";
  protected final String TEXT_3 = NL + "        (org.apache.spark.mllib.classification.talend.NaiveBayesRegularRecordsModel) org.talend.datascience.mllib.pmml.imports.ModelImporter" + NL + "            .fromPMML(";
  protected final String TEXT_4 = ", ";
  protected final String TEXT_5 = ");";
  protected final String TEXT_6 = NL + "        (org.apache.spark.mllib.classification.talend.NaiveBayesRegularRecordsModel) org.talend.datascience.mllib.pmml.imports.ModelImporter" + NL + "                .fromPMML(";
  protected final String TEXT_7 = ");";
  protected final String TEXT_8 = NL;
  protected final String TEXT_9 = NL + NL + "\t//Streaming version of tPredict" + NL + "\torg.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_10 = ">" + NL + "\t\trdd_";
  protected final String TEXT_11 = " = rdd_";
  protected final String TEXT_12 = ".transform(" + NL + "" + NL + "\t\t    new org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_13 = ">, org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_14 = ">>() {" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_15 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_16 = "> rdd) {" + NL + "" + NL + "\t\t\t\t\t// 2. Create dataFrame" + NL + "\t\t\t\t\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_17 = " = org.talend.datascience.util.SQLUtil.getSQLContextSingleton(rdd.context());" + NL + "\t\t\t\t    org.apache.spark.sql.DataFrame df_";
  protected final String TEXT_18 = " =" + NL + "\t\t\t\t    \tsqlContext_";
  protected final String TEXT_19 = ".createDataFrame(rdd, ";
  protected final String TEXT_20 = ".class);" + NL + "" + NL + "\t\t\t\t    // 3. Call predictor" + NL + "\t\t\t\t    return (org.talend.datascience.mllib.classification.NaiveBayes.predictor(" + NL + "\t\t\t\t    \t\t\tdf_";
  protected final String TEXT_21 = ".rdd(), // rdd<Row>" + NL + "\t\t\t\t    \t\t\tdf_";
  protected final String TEXT_22 = ".schema(), // schema" + NL + "\t\t\t\t    \t\t\tmodel_";
  protected final String TEXT_23 = " // model" + NL + "\t\t\t\t    \t\t).toJavaRDD()).map(new ";
  protected final String TEXT_24 = "_FromRowTo";
  protected final String TEXT_25 = "());" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t);" + NL;
  protected final String TEXT_26 = NL + "\t// Batch version of tPredict" + NL + "\t// 2. Create dataFrame from incoming rdd & rowStruct" + NL + "\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_27 = " = new org.apache.spark.sql.SQLContext(";
  protected final String TEXT_28 = ");" + NL + "\torg.apache.spark.sql.DataFrame df_";
  protected final String TEXT_29 = "_";
  protected final String TEXT_30 = " =" + NL + "\t\tsqlContext_";
  protected final String TEXT_31 = ".createDataFrame(rdd_";
  protected final String TEXT_32 = ", ";
  protected final String TEXT_33 = ".class);" + NL + "" + NL + "\t// 3. Call predictor" + NL + "\torg.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_34 = "> rdd_";
  protected final String TEXT_35 = " =" + NL + "\t\t(org.talend.datascience.mllib.classification.NaiveBayes.predictor(" + NL + "\t\t\tdf_";
  protected final String TEXT_36 = "_";
  protected final String TEXT_37 = ".rdd(), // rdd<Row>" + NL + "\t\t\tdf_";
  protected final String TEXT_38 = "_";
  protected final String TEXT_39 = ".schema(), // schema" + NL + "\t\t\tmodel_";
  protected final String TEXT_40 = " // model" + NL + "\t\t).toJavaRDD()).map(new ";
  protected final String TEXT_41 = "_FromRowTo";
  protected final String TEXT_42 = "());";
  protected final String TEXT_43 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_44 = "> rdd_";
  protected final String TEXT_45 = ";" + NL + "{";
  protected final String TEXT_46 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_47 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_48 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_49 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_50 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_51 = " = kryo_";
  protected final String TEXT_52 = ".readObject(featuresInput_";
  protected final String TEXT_53 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_54 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_55 = " = featuresTalendPipelineModel_";
  protected final String TEXT_56 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_57 = " = featuresTalendPipelineModel_";
  protected final String TEXT_58 = ".getPipelineModel();";
  protected final String TEXT_59 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_60 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_61 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_62 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_63 = NL + NL + "    rdd_";
  protected final String TEXT_64 = " = rdd_";
  protected final String TEXT_65 = ".transform(new Predict_";
  protected final String TEXT_66 = "(pipelineModel_";
  protected final String TEXT_67 = "));" + NL + "}" + NL;
  protected final String TEXT_68 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_69 = "> rdd_";
  protected final String TEXT_70 = ";" + NL + "{";
  protected final String TEXT_71 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_72 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_73 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_74 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_75 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_76 = " = kryo_";
  protected final String TEXT_77 = ".readObject(featuresInput_";
  protected final String TEXT_78 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_79 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_80 = " = featuresTalendPipelineModel_";
  protected final String TEXT_81 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_82 = " = featuresTalendPipelineModel_";
  protected final String TEXT_83 = ".getPipelineModel();";
  protected final String TEXT_84 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_85 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_86 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_87 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_88 = NL + NL + "    rdd_";
  protected final String TEXT_89 = " = rdd_";
  protected final String TEXT_90 = ".transform(new Classify_";
  protected final String TEXT_91 = "(pipelineModel_";
  protected final String TEXT_92 = "));" + NL + "}" + NL;
  protected final String TEXT_93 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_94 = "> rdd_";
  protected final String TEXT_95 = ";" + NL + "{";
  protected final String TEXT_96 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_97 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_98 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_99 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_100 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_101 = " = kryo_";
  protected final String TEXT_102 = ".readObject(featuresInput_";
  protected final String TEXT_103 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_104 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_105 = " = featuresTalendPipelineModel_";
  protected final String TEXT_106 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_107 = " = featuresTalendPipelineModel_";
  protected final String TEXT_108 = ".getPipelineModel();";
  protected final String TEXT_109 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_110 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_111 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_112 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_113 = NL + NL + "    rdd_";
  protected final String TEXT_114 = " = rdd_";
  protected final String TEXT_115 = ".transform(new Classify_";
  protected final String TEXT_116 = "(pipelineModel_";
  protected final String TEXT_117 = "));" + NL + "}" + NL;
  protected final String TEXT_118 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_119 = "> rdd_";
  protected final String TEXT_120 = ";" + NL + "{";
  protected final String TEXT_121 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_122 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_123 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_124 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_125 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_126 = " = kryo_";
  protected final String TEXT_127 = ".readObject(featuresInput_";
  protected final String TEXT_128 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_129 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_130 = " = featuresTalendPipelineModel_";
  protected final String TEXT_131 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_132 = " = featuresTalendPipelineModel_";
  protected final String TEXT_133 = ".getPipelineModel();";
  protected final String TEXT_134 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_135 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_136 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_137 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_138 = NL + NL + "    rdd_";
  protected final String TEXT_139 = " = rdd_";
  protected final String TEXT_140 = ".transform(new Classify_";
  protected final String TEXT_141 = "(pipelineModel_";
  protected final String TEXT_142 = "));" + NL + "}" + NL;
  protected final String TEXT_143 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_144 = "> rdd_";
  protected final String TEXT_145 = ";" + NL + "{";
  protected final String TEXT_146 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_147 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_148 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_149 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_150 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_151 = " = kryo_";
  protected final String TEXT_152 = ".readObject(featuresInput_";
  protected final String TEXT_153 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_154 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_155 = " = featuresTalendPipelineModel_";
  protected final String TEXT_156 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_157 = " = featuresTalendPipelineModel_";
  protected final String TEXT_158 = ".getPipelineModel();";
  protected final String TEXT_159 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_160 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_161 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_162 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_163 = NL + NL + "    rdd_";
  protected final String TEXT_164 = " = rdd_";
  protected final String TEXT_165 = ".transform(new Classify_";
  protected final String TEXT_166 = "(pipelineModel_";
  protected final String TEXT_167 = "));" + NL + "}" + NL;
  protected final String TEXT_168 = NL + NL;
  protected final String TEXT_169 = NL + "com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_170 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_171 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_172 = " + \"/features\")));" + NL + "TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_173 = " = kryo_";
  protected final String TEXT_174 = ".readObject(featuresInput_";
  protected final String TEXT_175 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_176 = " = org.apache.spark.mllib.classification.SVMModel.load(ctx.sparkContext().sc(), ";
  protected final String TEXT_177 = " + \"/model\");" + NL + "" + NL + "java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_178 = " = featuresTalendPipelineModel_";
  protected final String TEXT_179 = ".getParams();" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_180 = " = featuresTalendPipelineModel_";
  protected final String TEXT_181 = ".getPipelineModel();" + NL + "String vectorName_";
  protected final String TEXT_182 = " = featuresParamsMap_";
  protected final String TEXT_183 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "" + NL + " // Create context" + NL + " org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_184 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + " // Go to RDD view in order to use dataframe" + NL + " org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_185 = "> rdd_";
  protected final String TEXT_186 = " =" + NL + "     rdd_";
  protected final String TEXT_187 = ".transform(new GenerateEncodedStruct_";
  protected final String TEXT_188 = "(vectorName_";
  protected final String TEXT_189 = ", featuresTransformationsModel_";
  protected final String TEXT_190 = ", sqlContext_";
  protected final String TEXT_191 = "))" + NL + "             .map(new GetPrediction_";
  protected final String TEXT_192 = "(currentModel_";
  protected final String TEXT_193 = "));";
  protected final String TEXT_194 = NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_195 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_196 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_197 = " + \"/features\")));" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_198 = " = kryo_";
  protected final String TEXT_199 = ".readObject(featuresInput_";
  protected final String TEXT_200 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel_";
  protected final String TEXT_201 = " = org.apache.spark.mllib.clustering.KMeansModel.load(ctx.sparkContext().sc(), ";
  protected final String TEXT_202 = " + \"/model\");" + NL + "" + NL + "    java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_203 = " = featuresTalendPipelineModel_";
  protected final String TEXT_204 = ".getParams();" + NL + "    org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_205 = " = featuresTalendPipelineModel_";
  protected final String TEXT_206 = ".getPipelineModel();" + NL + "    String vectorName_";
  protected final String TEXT_207 = " = featuresParamsMap_";
  protected final String TEXT_208 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "" + NL + "     // Create context" + NL + "     org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_209 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + "     // Go to RDD view in order to use dataframe" + NL + "     org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_210 = "> rdd_";
  protected final String TEXT_211 = " =" + NL + "         rdd_";
  protected final String TEXT_212 = ".transform(new GenerateVector_";
  protected final String TEXT_213 = "(vectorName_";
  protected final String TEXT_214 = ", featuresTransformationsModel_";
  protected final String TEXT_215 = ", sqlContext_";
  protected final String TEXT_216 = "))" + NL + "                 .map(new GetPrediction_";
  protected final String TEXT_217 = "(currentModel_";
  protected final String TEXT_218 = "));";
  protected final String TEXT_219 = NL + "    if ((globalMap.getLocal(\"";
  protected final String TEXT_220 = "_PIPELINE\") == null)" + NL + "            || (globalMap.getLocal(\"";
  protected final String TEXT_221 = "_MODEL\")  == null)) {" + NL + "       throw new IOException(\"Pipeline or model initialized before the component ";
  protected final String TEXT_222 = "\");" + NL + "    }" + NL;
  protected final String TEXT_223 = NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_224 = " = (TalendPipelineModel)globalMap.getLocal(\"";
  protected final String TEXT_225 = "_PIPELINE\");" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_226 = " = featuresTalendPipelineModel_";
  protected final String TEXT_227 = ".getParams();" + NL + "        String vectorName_";
  protected final String TEXT_228 = " = featuresParamsMap_";
  protected final String TEXT_229 = ".get(\"VECTOR_NAME\");" + NL + "        org.apache.spark.ml.PipelineModel featuresTransformations_";
  protected final String TEXT_230 = " = featuresTalendPipelineModel_";
  protected final String TEXT_231 = ".getPipelineModel();";
  protected final String TEXT_232 = NL + "        TalendPipeline featuresTalendPipeline_";
  protected final String TEXT_233 = " = (TalendPipeline)globalMap.getLocal(\"";
  protected final String TEXT_234 = "_PIPELINE\");" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_235 = " = featuresTalendPipeline_";
  protected final String TEXT_236 = ".getParams();" + NL + "        String vectorName_";
  protected final String TEXT_237 = " = featuresParamsMap_";
  protected final String TEXT_238 = ".get(\"VECTOR_NAME\");" + NL + "        org.apache.spark.ml.Pipeline featuresTransformations_";
  protected final String TEXT_239 = " = featuresTalendPipeline_";
  protected final String TEXT_240 = ".getPipeline();";
  protected final String TEXT_241 = NL + NL + "    Object temporaryModel_";
  protected final String TEXT_242 = " = globalMap.getLocal(\"";
  protected final String TEXT_243 = "_MODEL\");" + NL + "    if (!(temporaryModel_";
  protected final String TEXT_244 = " instanceof org.apache.spark.mllib.clustering.StreamingKMeans)) {" + NL + "        throw new RuntimeException(\"The selected model is of type \" + temporaryModel_";
  protected final String TEXT_245 = ".getClass() + \" is should be of type org.apache.spark.mllib.clustering.StreamingKMeans\");" + NL + "    }" + NL + "    org.apache.spark.mllib.clustering.StreamingKMeans strKMeans_";
  protected final String TEXT_246 = " = (org.apache.spark.mllib.clustering.StreamingKMeans) temporaryModel_";
  protected final String TEXT_247 = ";" + NL + "" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_248 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext());" + NL + "" + NL + "    org.apache.spark.streaming.api.java.JavaPairDStream<";
  protected final String TEXT_249 = ", ";
  protected final String TEXT_250 = "> transformedrdd_";
  protected final String TEXT_251 = " = rdd_";
  protected final String TEXT_252 = ".transformToPair(" + NL + "            new GenerateKeyVector_";
  protected final String TEXT_253 = "(vectorName_";
  protected final String TEXT_254 = ", featuresTransformations_";
  protected final String TEXT_255 = ", sqlContext_";
  protected final String TEXT_256 = "));" + NL + "" + NL + "    org.apache.spark.streaming.api.java.JavaPairDStream<";
  protected final String TEXT_257 = ", Integer> pairrdd_";
  protected final String TEXT_258 = " = strKMeans_";
  protected final String TEXT_259 = ".predictOnValues(transformedrdd_";
  protected final String TEXT_260 = ");" + NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_261 = "> rdd_";
  protected final String TEXT_262 = " = pairrdd_";
  protected final String TEXT_263 = ".map(new GetStreamingPrediction_";
  protected final String TEXT_264 = "());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
final String modelType = ElementParameterParser.getValue(node, "__MODEL_TYPE__");

// NAIVEBAYES sparkcode
if("NAIVEBAYES".equals(modelType)){

    
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, true);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

IConnection inConn = tSqlRowUtil.getIncomingConnections().get(0);
String pmmlModelPath = ElementParameterParser.getValue(node, "__PMML_MODEL_PATH__");

String inStructName = codeGenArgument.getRecordStructName(inConn);
String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
String ctx = ("SPARKSTREAMING".equals(node.getComponent().getType())) ? "ctx.sparkContext().sc()" : "ctx.sc()" ;


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
    if(useConfigurationComponent){//import from dfs


    stringBuffer.append(TEXT_3);
    stringBuffer.append(ctx);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(pmmlModelPath);
    stringBuffer.append(TEXT_5);
    
    }else{//import from local

    stringBuffer.append(TEXT_6);
    stringBuffer.append(pmmlModelPath);
    stringBuffer.append(TEXT_7);
    
    }

    stringBuffer.append(TEXT_8);
    
if("SPARKSTREAMING".equals(node.getComponent().getType())
	&& !org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node)) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_25);
    
}else{

    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(ctx);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_42);
    
}

    
} // REGRESSION sparkcode
 else if("LINEAR_REGRESSION".equals(modelType)){

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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


// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_43);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_45);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    
    } else {
        String modelCid = ElementParameterParser.getValue(node, "__MODEL_LOCATION__");
        
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
    }
    
    stringBuffer.append(TEXT_63);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
}// LOGISTIC_REGRESSION sparkconfig
 else if("LOGISTIC_REGRESSION".equals(modelType)){

    
String modelCid = ElementParameterParser.getValue(node, "__LOR_MODEL_LOCATION__");

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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

// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_68);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_70);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    
    } else {
        
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    
    }
    
    stringBuffer.append(TEXT_88);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    

    
}// RANDOM_FOREST sparkconfig
 else if("RANDOM_FOREST".equals(modelType)){

    
String modelCid = ElementParameterParser.getValue(node, "__RF_MODEL_LOCATION__");

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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

// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_93);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_95);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    
    } else {
        
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    
    }
    
    stringBuffer.append(TEXT_113);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    

    
}// DECISION_TREE sparkconfig
 else if("DECISION_TREE".equals(modelType)){

    
String modelCid = ElementParameterParser.getValue(node, "__DT_MODEL_LOCATION__");

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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

// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_118);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_120);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    
    } else {
        
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    
    }
    
    stringBuffer.append(TEXT_138);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    

    
}// GRADIENT_BOOSTED sparkconfig
 else if("GRADIENT_BOOSTED".equals(modelType)){

    
String modelCid = ElementParameterParser.getValue(node, "__GB_MODEL_LOCATION__");

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
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

// This is always set to true until there are model encoders that can exist in
// A spark streaming job.
Boolean savedOnDisk = true; // ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_143);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_145);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
    } else {
        
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    
    }
    
    stringBuffer.append(TEXT_163);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    

    
}
// SVM_CLASSIFICATION sparkconfig
 else if("SVM_CLASSIFICATION".equals(modelType)){

    stringBuffer.append(TEXT_168);
    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
IConnection conn = null;
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


String modelPath = ElementParameterParser.getValue(node, "__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
        String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
        modelPath = uriPrefix + " + " + modelPath;
}


// We are on SVM

    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    
}// KMEANS sparkconfig
 else if("KMEANS".equals(modelType)){

    
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    
IConnection conn = null;
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();

Boolean modelOnFilesystem = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
Boolean modelComputed = ElementParameterParser.getBooleanValue(node, "__KMEANS_MODEL_COMPUTED__");

String modelPath = ElementParameterParser.getValue(node, "__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
        String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
        modelPath = uriPrefix + " + " + modelPath;
}

if (modelOnFilesystem) {
    // We are on Kmeans (or a Kmeans streaming dumped into a Kmeans format)
    
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    
} else if (modelComputed) {
    // We are on Kmeans streamimng
    String modelLocation = ElementParameterParser.getValue(node, "__KMEANS_MODEL_LOCATION__");

    Boolean inputAsPipelineModel = false;
    List<? extends INode> nodes = node.getProcess().getGeneratingNodes();
    for(INode targetNode : nodes){
        if (targetNode.getUniqueName().equals(modelLocation)) {
            // If the tKmeansStrModel load the pipeline from disk, it will save a PipelineModel.
            // Otherwise (the default case), it will be a Pipeline.
            // This is because this pipeline was read from HDFS and can be a complexe pipeline computed with a batch KMeans
            inputAsPipelineModel = ElementParameterParser.getBooleanValue(targetNode, "__REUSE_PIPELINE__")
                    && ElementParameterParser.getBooleanValue(targetNode, "__LOAD_FROM_DISK__");
            break;
        }
    }

    // retrieve name of the external component
    
    stringBuffer.append(TEXT_219);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    
    if (inputAsPipelineModel) {
        
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_231);
    
    } else {
        
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    
    }
    
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_264);
    
}

    
}

    return stringBuffer.toString();
  }
}
