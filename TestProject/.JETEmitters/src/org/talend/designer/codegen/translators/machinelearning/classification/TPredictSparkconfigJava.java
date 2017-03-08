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

public class TPredictSparkconfigJava
{
  protected static String nl;
  public static synchronized TPredictSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictSparkconfigJava result = new TPredictSparkconfigJava();
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
  protected final String TEXT_43 = NL + "org.apache.spark.api.java.JavaRDD<";
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
  protected final String TEXT_63 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_64 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_65 = NL + "    ";
  protected final String TEXT_66 = " df_";
  protected final String TEXT_67 = " = sqlContext_";
  protected final String TEXT_68 = ".createDataFrame(rdd_";
  protected final String TEXT_69 = ", ";
  protected final String TEXT_70 = ".class);";
  protected final String TEXT_71 = NL + "    ";
  protected final String TEXT_72 = " results_";
  protected final String TEXT_73 = " = pipelineModel_";
  protected final String TEXT_74 = ".transform(df_";
  protected final String TEXT_75 = ");" + NL + "" + NL + "    // Convert DataFrame back to RDD" + NL + "    rdd_";
  protected final String TEXT_76 = " = results_";
  protected final String TEXT_77 = ".toJavaRDD().map(new ";
  protected final String TEXT_78 = "_FromRowTo";
  protected final String TEXT_79 = "());" + NL + "}";
  protected final String TEXT_80 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_81 = "> rdd_";
  protected final String TEXT_82 = ";" + NL + "{";
  protected final String TEXT_83 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_84 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_85 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_86 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_87 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_88 = " = kryo_";
  protected final String TEXT_89 = ".readObject(featuresInput_";
  protected final String TEXT_90 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_91 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_92 = " = featuresTalendPipelineModel_";
  protected final String TEXT_93 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_94 = " = featuresTalendPipelineModel_";
  protected final String TEXT_95 = ".getPipelineModel();";
  protected final String TEXT_96 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_97 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_98 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_99 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_100 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_101 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_102 = NL + "    ";
  protected final String TEXT_103 = " df_";
  protected final String TEXT_104 = " = sqlContext_";
  protected final String TEXT_105 = ".createDataFrame(rdd_";
  protected final String TEXT_106 = ", ";
  protected final String TEXT_107 = ".class);";
  protected final String TEXT_108 = NL + "    ";
  protected final String TEXT_109 = " results = pipelineModel_";
  protected final String TEXT_110 = ".transform(df_";
  protected final String TEXT_111 = ");" + NL;
  protected final String TEXT_112 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel_";
  protected final String TEXT_113 = ".stages()[1];";
  protected final String TEXT_114 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_115 = "> rdd = results.map(" + NL + "                    new StringIndexerInverseFunction_";
  protected final String TEXT_116 = "(sim)," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_117 = ".class));" + NL + "            rdd_";
  protected final String TEXT_118 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_119 = NL + "            rdd_";
  protected final String TEXT_120 = " = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_121 = "(sim)," + NL + "                    org.apache.spark.sql.Encoders.bean(";
  protected final String TEXT_122 = ".class)).toJavaRDD();";
  protected final String TEXT_123 = NL + "        // TODO";
  protected final String TEXT_124 = NL + "}";
  protected final String TEXT_125 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_126 = "> rdd_";
  protected final String TEXT_127 = ";" + NL + "{";
  protected final String TEXT_128 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_129 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_130 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_131 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_132 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_133 = " = kryo_";
  protected final String TEXT_134 = ".readObject(featuresInput_";
  protected final String TEXT_135 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_136 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_137 = " = featuresTalendPipelineModel_";
  protected final String TEXT_138 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_139 = " = featuresTalendPipelineModel_";
  protected final String TEXT_140 = ".getPipelineModel();";
  protected final String TEXT_141 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_142 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_143 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_144 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_145 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_146 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_147 = NL + "    ";
  protected final String TEXT_148 = " df_";
  protected final String TEXT_149 = " = sqlContext_";
  protected final String TEXT_150 = ".createDataFrame(rdd_";
  protected final String TEXT_151 = ", ";
  protected final String TEXT_152 = ".class);";
  protected final String TEXT_153 = NL + "    ";
  protected final String TEXT_154 = " results = pipelineModel_";
  protected final String TEXT_155 = ".transform(df_";
  protected final String TEXT_156 = ");" + NL;
  protected final String TEXT_157 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel_";
  protected final String TEXT_158 = ".stages()[1];";
  protected final String TEXT_159 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_160 = "> rdd = results.map(" + NL + "                    new StringIndexerInverseFunction_";
  protected final String TEXT_161 = "(sim)," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_162 = ".class));" + NL + "            rdd_";
  protected final String TEXT_163 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_164 = NL + "            rdd_";
  protected final String TEXT_165 = " = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_166 = "(sim)," + NL + "                    org.apache.spark.sql.Encoders.bean(";
  protected final String TEXT_167 = ".class)).toJavaRDD();";
  protected final String TEXT_168 = NL + "        // TODO";
  protected final String TEXT_169 = NL + "}";
  protected final String TEXT_170 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_171 = "> rdd_";
  protected final String TEXT_172 = ";" + NL + "{";
  protected final String TEXT_173 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_174 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_175 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_176 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_177 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_178 = " = kryo_";
  protected final String TEXT_179 = ".readObject(featuresInput_";
  protected final String TEXT_180 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_181 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_182 = " = featuresTalendPipelineModel_";
  protected final String TEXT_183 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_184 = " = featuresTalendPipelineModel_";
  protected final String TEXT_185 = ".getPipelineModel();";
  protected final String TEXT_186 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_187 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_188 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_189 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_190 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_191 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_192 = NL + "    ";
  protected final String TEXT_193 = " df_";
  protected final String TEXT_194 = " = sqlContext_";
  protected final String TEXT_195 = ".createDataFrame(rdd_";
  protected final String TEXT_196 = ", ";
  protected final String TEXT_197 = ".class);";
  protected final String TEXT_198 = NL + "    ";
  protected final String TEXT_199 = " results = pipelineModel_";
  protected final String TEXT_200 = ".transform(df_";
  protected final String TEXT_201 = ");" + NL;
  protected final String TEXT_202 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel_";
  protected final String TEXT_203 = ".stages()[1];";
  protected final String TEXT_204 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_205 = "> rdd = results.map(" + NL + "                    new StringIndexerInverseFunction_";
  protected final String TEXT_206 = "(sim)," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_207 = ".class));" + NL + "            rdd_";
  protected final String TEXT_208 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_209 = NL + "            rdd_";
  protected final String TEXT_210 = " = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_211 = "(sim)," + NL + "                    org.apache.spark.sql.Encoders.bean(";
  protected final String TEXT_212 = ".class)).toJavaRDD();";
  protected final String TEXT_213 = NL + "        // TODO";
  protected final String TEXT_214 = NL + "}";
  protected final String TEXT_215 = NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_216 = "> rdd_";
  protected final String TEXT_217 = ";" + NL + "{";
  protected final String TEXT_218 = NL + "        com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_219 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "        com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_220 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_221 = " + \"/model/\")));" + NL + "        TalendPipelineModelSerializer talendPipelineModelSerializer_";
  protected final String TEXT_222 = " = new TalendPipelineModelSerializer();" + NL + "        TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_223 = " = kryo_";
  protected final String TEXT_224 = ".readObject(featuresInput_";
  protected final String TEXT_225 = ", TalendPipelineModel.class, talendPipelineModelSerializer_";
  protected final String TEXT_226 = ");" + NL + "" + NL + "        java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_227 = " = featuresTalendPipelineModel_";
  protected final String TEXT_228 = ".getParams();" + NL + "" + NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_229 = " = featuresTalendPipelineModel_";
  protected final String TEXT_230 = ".getPipelineModel();";
  protected final String TEXT_231 = NL + "        org.apache.spark.ml.PipelineModel pipelineModel_";
  protected final String TEXT_232 = ";" + NL + "        {" + NL + "            Object model = globalMap.get(\"";
  protected final String TEXT_233 = "_MODEL\");" + NL + "            if (model instanceof org.apache.spark.ml.PipelineModel) {" + NL + "                pipelineModel_";
  protected final String TEXT_234 = " = (org.apache.spark.ml.PipelineModel) model;" + NL + "            } else {" + NL + "                throw new Exception(\"Unsupported model type: \" + model.getClass());" + NL + "            }" + NL + "        }";
  protected final String TEXT_235 = NL + NL + "    // Convert incoming RDD to DataFrame" + NL + "    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_236 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_237 = NL + "    ";
  protected final String TEXT_238 = " df_";
  protected final String TEXT_239 = " = sqlContext_";
  protected final String TEXT_240 = ".createDataFrame(rdd_";
  protected final String TEXT_241 = ", ";
  protected final String TEXT_242 = ".class);";
  protected final String TEXT_243 = NL + "    ";
  protected final String TEXT_244 = " results = pipelineModel_";
  protected final String TEXT_245 = ".transform(df_";
  protected final String TEXT_246 = ");" + NL;
  protected final String TEXT_247 = NL + "        org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel_";
  protected final String TEXT_248 = ".stages()[1];";
  protected final String TEXT_249 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_250 = "> rdd = results.map(" + NL + "                    new StringIndexerInverseFunction_";
  protected final String TEXT_251 = "(sim)," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_252 = ".class));" + NL + "            rdd_";
  protected final String TEXT_253 = " = org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_254 = NL + "            rdd_";
  protected final String TEXT_255 = " = results.map(new StringIndexerInverseFunction_";
  protected final String TEXT_256 = "(sim)," + NL + "                    org.apache.spark.sql.Encoders.bean(";
  protected final String TEXT_257 = ".class)).toJavaRDD();";
  protected final String TEXT_258 = NL + "        // TODO";
  protected final String TEXT_259 = NL + "}";
  protected final String TEXT_260 = NL;
  protected final String TEXT_261 = NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_262 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_263 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_264 = " + \"/features\")));" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_265 = " = kryo_";
  protected final String TEXT_266 = ".readObject(featuresInput_";
  protected final String TEXT_267 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_268 = " = org.apache.spark.mllib.classification.SVMModel.load(ctx.sc(), ";
  protected final String TEXT_269 = " + \"/model\");";
  protected final String TEXT_270 = NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_271 = " = (TalendPipelineModel)globalMap.get(\"";
  protected final String TEXT_272 = "_PIPELINE\");" + NL + "" + NL + "    Object temporaryModel_";
  protected final String TEXT_273 = " = globalMap.get(\"";
  protected final String TEXT_274 = "_MODEL\");" + NL + "    if (temporaryModel_";
  protected final String TEXT_275 = " == null) {" + NL + "        throw new RuntimeException(\"The selected model does not exist\");" + NL + "    }else if (!(temporaryModel_";
  protected final String TEXT_276 = " instanceof org.apache.spark.mllib.classification.SVMModel)) {" + NL + "        throw new RuntimeException(\"The selected model is of type \" + temporaryModel_";
  protected final String TEXT_277 = ".getClass() + \" is should be of type org.apache.spark.mllib.classification.SVMModel\");" + NL + "    }" + NL + "    org.apache.spark.mllib.classification.SVMModel currentModel_";
  protected final String TEXT_278 = " = (org.apache.spark.mllib.classification.SVMModel) temporaryModel_";
  protected final String TEXT_279 = ";" + NL;
  protected final String TEXT_280 = NL + "java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_281 = " = featuresTalendPipelineModel_";
  protected final String TEXT_282 = ".getParams();" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_283 = " = featuresTalendPipelineModel_";
  protected final String TEXT_284 = ".getPipelineModel();" + NL + "String vectorName_";
  protected final String TEXT_285 = " = featuresParamsMap_";
  protected final String TEXT_286 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "// Pipeline" + NL + "org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_287 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_288 = NL;
  protected final String TEXT_289 = " inputDataFrame";
  protected final String TEXT_290 = " = sqlContext_";
  protected final String TEXT_291 = ".createDataFrame(rdd_";
  protected final String TEXT_292 = ", ";
  protected final String TEXT_293 = ".class);";
  protected final String TEXT_294 = NL;
  protected final String TEXT_295 = " transformedInputDataFrame_";
  protected final String TEXT_296 = " = featuresTransformationsModel_";
  protected final String TEXT_297 = ".transform(inputDataFrame";
  protected final String TEXT_298 = ");" + NL + "" + NL + "// Model" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_299 = "> temporaryrdd_";
  protected final String TEXT_300 = " = transformedInputDataFrame_";
  protected final String TEXT_301 = ".toJavaRDD().map(new GetEncodedStruct_";
  protected final String TEXT_302 = "(vectorName_";
  protected final String TEXT_303 = "));" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_304 = "> rdd_";
  protected final String TEXT_305 = " = temporaryrdd_";
  protected final String TEXT_306 = ".map(new GetPrediction_";
  protected final String TEXT_307 = "(currentModel_";
  protected final String TEXT_308 = "));" + NL;
  protected final String TEXT_309 = NL;
  protected final String TEXT_310 = NL + "    com.esotericsoftware.kryo.Kryo kryo_";
  protected final String TEXT_311 = " = new com.esotericsoftware.kryo.Kryo();" + NL + "    com.esotericsoftware.kryo.io.Input featuresInput_";
  protected final String TEXT_312 = " = new com.esotericsoftware.kryo.io.Input(fs.open(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_313 = " + \"/features\")));" + NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_314 = " = kryo_";
  protected final String TEXT_315 = ".readObject(featuresInput_";
  protected final String TEXT_316 = ", TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel_";
  protected final String TEXT_317 = " = org.apache.spark.mllib.clustering.KMeansModel.load(ctx.sc(), ";
  protected final String TEXT_318 = " + \"/model\");";
  protected final String TEXT_319 = NL + "    TalendPipelineModel featuresTalendPipelineModel_";
  protected final String TEXT_320 = " = (TalendPipelineModel)globalMap.get(\"";
  protected final String TEXT_321 = "_PIPELINE\");" + NL + "" + NL + "    Object temporaryModel_";
  protected final String TEXT_322 = " = globalMap.get(\"";
  protected final String TEXT_323 = "_MODEL\");" + NL + "    if (temporaryModel_";
  protected final String TEXT_324 = " == null) {" + NL + "        throw new RuntimeException(\"The selected model does not exist\");" + NL + "    }else if (!(temporaryModel_";
  protected final String TEXT_325 = " instanceof org.apache.spark.mllib.clustering.KMeansModel)) {" + NL + "        throw new RuntimeException(\"The selected model is of type \" + temporaryModel_";
  protected final String TEXT_326 = ".getClass() + \" is should be of type org.apache.spark.mllib.clustering.KMeansModel\");" + NL + "    }" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel_";
  protected final String TEXT_327 = " = (org.apache.spark.mllib.clustering.KMeansModel) temporaryModel_";
  protected final String TEXT_328 = ";" + NL;
  protected final String TEXT_329 = NL + "java.util.Map<String, String> featuresParamsMap_";
  protected final String TEXT_330 = " = featuresTalendPipelineModel_";
  protected final String TEXT_331 = ".getParams();" + NL + "org.apache.spark.ml.PipelineModel featuresTransformationsModel_";
  protected final String TEXT_332 = " = featuresTalendPipelineModel_";
  protected final String TEXT_333 = ".getPipelineModel();" + NL + "String vectorName_";
  protected final String TEXT_334 = " = featuresParamsMap_";
  protected final String TEXT_335 = ".get(\"VECTOR_NAME\");" + NL + "" + NL + "// Pipeline" + NL + "org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_336 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_337 = NL;
  protected final String TEXT_338 = " inputDataFrame";
  protected final String TEXT_339 = " = sqlContext_";
  protected final String TEXT_340 = ".createDataFrame(rdd_";
  protected final String TEXT_341 = ", ";
  protected final String TEXT_342 = ".class);";
  protected final String TEXT_343 = NL;
  protected final String TEXT_344 = " transformedInputDataFrame_";
  protected final String TEXT_345 = " = featuresTransformationsModel_";
  protected final String TEXT_346 = ".transform(inputDataFrame";
  protected final String TEXT_347 = ");" + NL + "" + NL + "// Model" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_348 = "> temporaryrdd_";
  protected final String TEXT_349 = " = transformedInputDataFrame_";
  protected final String TEXT_350 = ".toJavaRDD().map(new GetEncodedStruct_";
  protected final String TEXT_351 = "(vectorName_";
  protected final String TEXT_352 = "));" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_353 = "> rdd_";
  protected final String TEXT_354 = " = temporaryrdd_";
  protected final String TEXT_355 = ".map(new GetPrediction_";
  protected final String TEXT_356 = "(currentModel_";
  protected final String TEXT_357 = "));" + NL;

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

    
final String modelType = ElementParameterParser.getValue(node, "__MODEL_TYPE__");

// NAIVEBAYES sparkconfig
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

    
} // LINEAR_REGRESSION sparkconfig
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
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
        String modelCid = ElementParameterParser.getValue(node, "__LR_MODEL_LOCATION__");
        
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
    }
    
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_79);
    

    
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_80);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_82);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    
    } else {
        
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    
    }
    
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_111);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    
        if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
            
    stringBuffer.append(TEXT_114);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_118);
    
        } else {
            
    stringBuffer.append(TEXT_119);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_122);
    
        }
    } else {
       
    stringBuffer.append(TEXT_123);
    
    }
    
    stringBuffer.append(TEXT_124);
    

    
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_125);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_127);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    
    } else {
        
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    
    }
    
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_156);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    
        if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
            
    stringBuffer.append(TEXT_159);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_163);
    
        } else {
            
    stringBuffer.append(TEXT_164);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_167);
    
        }
    } else {
       
    stringBuffer.append(TEXT_168);
    
    }
    
    stringBuffer.append(TEXT_169);
    

    
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_170);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_172);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_185);
    
    } else {
        
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    
    }
    
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_201);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    
        if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
            
    stringBuffer.append(TEXT_204);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_208);
    
        } else {
            
    stringBuffer.append(TEXT_209);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_212);
    
        }
    } else {
       
    stringBuffer.append(TEXT_213);
    
    }
    
    stringBuffer.append(TEXT_214);
    

    
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

Boolean savedOnDisk = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
String hdfsFolder = ElementParameterParser.getValue(node,"__HDFS_FOLDER__");
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    hdfsFolder = uriPrefix + " + " + hdfsFolder;
}


    stringBuffer.append(TEXT_215);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_217);
    
    // Get the pipeline model.
    if (savedOnDisk) {
        
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(hdfsFolder);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
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
    
    } else {
        
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(modelCid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    
    }
    
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_246);
    

    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
    
        if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
            
    stringBuffer.append(TEXT_249);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_253);
    
        } else {
            
    stringBuffer.append(TEXT_254);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_257);
    
        }
    } else {
       
    stringBuffer.append(TEXT_258);
    
    }
    
    stringBuffer.append(TEXT_259);
    

    
}
// SVM_CLASSIFICATION sparkconfig
 else if("SVM_CLASSIFICATION".equals(modelType)){

    stringBuffer.append(TEXT_260);
    
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
String inRowStructEncoded = conn.getName() + "Encoded";
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


Boolean modelOnFilesystem = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
Boolean modelOnJob = ElementParameterParser.getBooleanValue(node, "__SVM_MODEL_COMPUTED__");

if (modelOnFilesystem) {

    String modelPath = ElementParameterParser.getValue(node, "__HDFS_FOLDER__");
    boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
    if(useConfigurationComponent) {
        String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
        modelPath = uriPrefix + " + " + modelPath;
    }

    
    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_269);
    
} else if (modelOnJob) {

    //retrieve name of the external component
    String modelLocation = ElementParameterParser.getValue(node, "__SVM_MODEL_LOCATION__");
    
    stringBuffer.append(TEXT_270);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_279);
    
}

    stringBuffer.append(TEXT_280);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_283);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_287);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_308);
    
}// KMEANS sparkconfig
 else if("KMEANS".equals(modelType)){

    stringBuffer.append(TEXT_309);
    
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
String inRowStructEncoded = conn.getName() + "Encoded";
String inConnName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


Boolean modelOnFilesystem = ElementParameterParser.getBooleanValue(node, "__SAVE_ON_DISK__");
Boolean modelOnJob = ElementParameterParser.getBooleanValue(node, "__KMEANS_MODEL_COMPUTED__");

if (modelOnFilesystem) {

    String modelPath = ElementParameterParser.getValue(node, "__HDFS_FOLDER__");
    boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
    if(useConfigurationComponent) {
        String uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
        modelPath = uriPrefix + " + " + modelPath;
    }

    
    stringBuffer.append(TEXT_310);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(modelPath);
    stringBuffer.append(TEXT_318);
    
} else if (modelOnJob) {

    //retrieve name of the external component
    String modelLocation = ElementParameterParser.getValue(node, "__KMEANS_MODEL_LOCATION__");
    
    stringBuffer.append(TEXT_319);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(modelLocation);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_328);
    
}

    stringBuffer.append(TEXT_329);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_331);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_334);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_335);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_342);
    stringBuffer.append(TEXT_343);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_348);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_350);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_357);
    
}

    return stringBuffer.toString();
  }
}
