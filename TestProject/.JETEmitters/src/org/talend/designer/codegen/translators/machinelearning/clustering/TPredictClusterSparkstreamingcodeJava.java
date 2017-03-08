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

public class TPredictClusterSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TPredictClusterSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictClusterSparkstreamingcodeJava result = new TPredictClusterSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = " extends ";
  protected final String TEXT_3 = " {" + NL + "    public ";
  protected final String TEXT_4 = " temporaryVector;" + NL + "}" + NL + "public static class GetPrediction_";
  protected final String TEXT_5 = NL + "        implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = "> {" + NL + "" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel;" + NL + "" + NL + "    public GetPrediction_";
  protected final String TEXT_8 = "(" + NL + "            org.apache.spark.mllib.clustering.KMeansModel currentModel) {" + NL + "        this.currentModel = currentModel;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_9 = " call(";
  protected final String TEXT_10 = " input) {";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = " output = new ";
  protected final String TEXT_13 = "();";
  protected final String TEXT_14 = NL + "                output.";
  protected final String TEXT_15 = " = input.";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL + "        output.label = currentModel.predict(input.temporaryVector);" + NL + "        return output;" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class GetEncodedStruct_";
  protected final String TEXT_18 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_19 = "> {" + NL + "" + NL + "    String vectorName;" + NL + "" + NL + "    public GetEncodedStruct_";
  protected final String TEXT_20 = "(String vectorName) {" + NL + "        this.vectorName = vectorName;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_21 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_22 = NL + "        ";
  protected final String TEXT_23 = " output = new ";
  protected final String TEXT_24 = "();" + NL + "        try {" + NL + "            output.temporaryVector = (";
  protected final String TEXT_25 = ") input.get(input.fieldIndex(this.vectorName));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }";
  protected final String TEXT_26 = NL + "                output.";
  protected final String TEXT_27 = " = (";
  protected final String TEXT_28 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_29 = "\"));";
  protected final String TEXT_30 = NL + "        return output;" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class GenerateVector_";
  protected final String TEXT_31 = NL + "    implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_32 = ">," + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_33 = ">> {" + NL + "" + NL + "    private String vectorName = \"\";" + NL + "    private org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "    private org.apache.spark.sql.SQLContext context;" + NL + "" + NL + "    public GenerateVector_";
  protected final String TEXT_34 = " (String vectorName, org.apache.spark.ml.PipelineModel pipelineModel, org.apache.spark.sql.SQLContext context) {" + NL + "        this.vectorName = vectorName;" + NL + "        this.pipelineModel = pipelineModel;" + NL + "        this.context = context;" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_35 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_36 = "> inputRDD) throws Exception {";
  protected final String TEXT_37 = NL + "        ";
  protected final String TEXT_38 = " inputDataFrame = context" + NL + "              .createDataFrame(inputRDD, ";
  protected final String TEXT_39 = ".class);";
  protected final String TEXT_40 = NL + "        ";
  protected final String TEXT_41 = " outputDataFrame = pipelineModel" + NL + "              .transform(inputDataFrame);" + NL + "" + NL + "        return outputDataFrame.toJavaRDD().map(new GetEncodedStruct_";
  protected final String TEXT_42 = "(vectorName));" + NL + "    }" + NL + "}" + NL + "" + NL + "" + NL + "" + NL + "public static class GetKeyVectorStruct_";
  protected final String TEXT_43 = NL + "        implements" + NL + "        org.apache.spark.api.java.function.PairFunction<org.apache.spark.sql.Row, ";
  protected final String TEXT_44 = ", ";
  protected final String TEXT_45 = "> {" + NL + "" + NL + "    String vectorName;" + NL + "" + NL + "    public GetKeyVectorStruct_";
  protected final String TEXT_46 = "(String vectorName) {" + NL + "        this.vectorName = vectorName;" + NL + "    }" + NL + "" + NL + "    public scala.Tuple2<";
  protected final String TEXT_47 = ", ";
  protected final String TEXT_48 = "> call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_49 = NL + "        ";
  protected final String TEXT_50 = " output = new ";
  protected final String TEXT_51 = "();";
  protected final String TEXT_52 = NL + "        ";
  protected final String TEXT_53 = " outputVector  = null;" + NL + "        try {" + NL + "            outputVector = (";
  protected final String TEXT_54 = ") input" + NL + "                    .get(input.fieldIndex(this.vectorName));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }";
  protected final String TEXT_55 = NL + "                output.";
  protected final String TEXT_56 = " = (";
  protected final String TEXT_57 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_58 = "\"));";
  protected final String TEXT_59 = NL + "        return new scala.Tuple2(output, outputVector);" + NL + "    }" + NL + "}" + NL + NL;
  protected final String TEXT_60 = NL + "    public static class GenerateKeyVector_";
  protected final String TEXT_61 = NL + "        implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_62 = ">," + NL + "                    org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_63 = ", ";
  protected final String TEXT_64 = ">> {" + NL + "" + NL + "        private String vectorName = \"\";" + NL + "        private org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "        private org.apache.spark.sql.SQLContext context;" + NL + "" + NL + "        public GenerateKeyVector_";
  protected final String TEXT_65 = " (String vectorName, org.apache.spark.ml.PipelineModel pipelineModel, org.apache.spark.sql.SQLContext context) {" + NL + "            this.vectorName = vectorName;" + NL + "            this.pipelineModel = pipelineModel;" + NL + "            this.context = context;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_66 = ", ";
  protected final String TEXT_67 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_68 = "> inputRDD) throws Exception {";
  protected final String TEXT_69 = NL + "            ";
  protected final String TEXT_70 = " inputDataFrame = context" + NL + "                  .createDataFrame(inputRDD, ";
  protected final String TEXT_71 = ".class);";
  protected final String TEXT_72 = NL + "            ";
  protected final String TEXT_73 = " outputDataFrame = pipelineModel" + NL + "                .transform(inputDataFrame);" + NL + "            return outputDataFrame.toJavaRDD().mapToPair(new GetKeyVectorStruct_";
  protected final String TEXT_74 = "(vectorName));" + NL + "        }" + NL + "    }";
  protected final String TEXT_75 = NL + "    public static class GenerateKeyVector_";
  protected final String TEXT_76 = NL + "        implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_77 = ">," + NL + "                    org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_78 = ", ";
  protected final String TEXT_79 = ">> {" + NL + "" + NL + "        private String vectorName = \"\";" + NL + "        private org.apache.spark.ml.Pipeline pipeline;" + NL + "        private org.apache.spark.sql.SQLContext context;" + NL + "" + NL + "        public GenerateKeyVector_";
  protected final String TEXT_80 = " (String vectorName, org.apache.spark.ml.Pipeline pipeline, org.apache.spark.sql.SQLContext context) {" + NL + "            this.vectorName = vectorName;" + NL + "            this.pipeline = pipeline;" + NL + "            this.context = context;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_83 = "> inputRDD) throws Exception {";
  protected final String TEXT_84 = NL + "            ";
  protected final String TEXT_85 = " inputDataFrame = context" + NL + "                  .createDataFrame(inputRDD, ";
  protected final String TEXT_86 = ".class);";
  protected final String TEXT_87 = NL + "            ";
  protected final String TEXT_88 = " outputDataFrame = pipeline" + NL + "                .fit(inputDataFrame)" + NL + "                .transform(inputDataFrame);" + NL + "            return outputDataFrame.toJavaRDD().mapToPair(new GetKeyVectorStruct_";
  protected final String TEXT_89 = "(vectorName));" + NL + "        }" + NL + "    }";
  protected final String TEXT_90 = NL + NL + "public static class GetStreamingPrediction_";
  protected final String TEXT_91 = NL + "        implements org.apache.spark.api.java.function.Function<scala.Tuple2<";
  protected final String TEXT_92 = ", Integer>, ";
  protected final String TEXT_93 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_94 = " call(scala.Tuple2<";
  protected final String TEXT_95 = ", Integer> inputTuple) {";
  protected final String TEXT_96 = NL + "        ";
  protected final String TEXT_97 = " input = inputTuple._1();" + NL + "        Integer label = inputTuple._2();";
  protected final String TEXT_98 = NL + "        ";
  protected final String TEXT_99 = " output = new ";
  protected final String TEXT_100 = "();";
  protected final String TEXT_101 = NL + "                output.";
  protected final String TEXT_102 = " = input.";
  protected final String TEXT_103 = ";";
  protected final String TEXT_104 = NL + "        output.label = label;" + NL + "        return output;" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
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

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if((metadatas!=null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
        columns = metadata.getListColumns();
    }
}
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(columns == null || columns.isEmpty() || conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inRowStructEncoded = conn.getName() + "Encoded";
String connName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


    stringBuffer.append(TEXT_1);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_13);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_14);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    
            }
        }
        
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_25);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_26);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_29);
    
            }
        }
        
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_54);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_55);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_58);
    
            }
        }
        
    stringBuffer.append(TEXT_59);
    

String modelLocation = ElementParameterParser.getValue(node, "__MODEL_LOCATION__");
Boolean inputAsPipelineModel = false;
for(INode targetNode : node.getProcess().getGeneratingNodes()){
    if (targetNode.getUniqueName().equals(modelLocation)) {
        // If the tKmeansStrModel load the pipeline from disk, it will save a PipelineModel.
        // Otherwise (the default case), it will be a Pipeline.
        // This is because this pipeline was read from HDFS and can be a complexe pipeline computed with a batch KMeans
        inputAsPipelineModel = ElementParameterParser.getBooleanValue(targetNode, "__REUSE_PIPELINE__")
                && ElementParameterParser.getBooleanValue(targetNode, "__LOAD_FROM_DISK__");
        break;
    }
}

if (inputAsPipelineModel) {
    
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    
} else {
    
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    
}

    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_100);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_101);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_103);
    
            }
        }
        
    stringBuffer.append(TEXT_104);
    return stringBuffer.toString();
  }
}
