package org.talend.designer.codegen.translators.machinelearning.classification;

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

public class TClassifySparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TClassifySparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySparkstreamingcodeJava result = new TClassifySparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class StringIndexerInverseFunction_";
  protected final String TEXT_2 = NL + "        extends scala.runtime.AbstractFunction1<org.apache.spark.sql.Row, ";
  protected final String TEXT_3 = ">implements Serializable {" + NL + "    /** Default serial version UID. */" + NL + "    private static final long serialVersionUID = 1L;" + NL + "    private final java.util.Map<Object, String> i2s = new java.util.HashMap<>();" + NL + "" + NL + "    StringIndexerInverseFunction_";
  protected final String TEXT_4 = "(org.apache.spark.ml.feature.StringIndexerModel sim) {" + NL + "        for (scala.Tuple2<String, Object> label : scala.collection.JavaConversions.asJavaIterable(" + NL + "                sim.org$apache$spark$ml$feature$StringIndexerModel$$labelToIndex())) {" + NL + "            i2s.put(label._2(), label._1());" + NL + "        }" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public ";
  protected final String TEXT_5 = " apply(org.apache.spark.sql.Row in) {";
  protected final String TEXT_6 = NL + "        ";
  protected final String TEXT_7 = " out = new ";
  protected final String TEXT_8 = "();";
  protected final String TEXT_9 = NL + "                out.";
  protected final String TEXT_10 = " = (";
  protected final String TEXT_11 = ") in.get(in.fieldIndex(\"";
  protected final String TEXT_12 = "\"));";
  protected final String TEXT_13 = NL + "                out.";
  protected final String TEXT_14 = " = i2s.get(in.get(in.fieldIndex(\"";
  protected final String TEXT_15 = "\")));";
  protected final String TEXT_16 = NL + "        return out;" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class Classify_";
  protected final String TEXT_17 = NL + "    implements org.apache.spark.api.java.function.Function<" + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_18 = ">," + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_19 = ">> {" + NL + "" + NL + "    /** Default serial version UID. */" + NL + "    private static final long serialVersionUID = 1L;" + NL + "" + NL + "    /** Lazy instantiation of SQLContext */" + NL + "    private static org.apache.spark.sql.SQLContext sqlContext = null;" + NL + "" + NL + "    private final org.apache.spark.ml.PipelineModel pipelineModel;" + NL;
  protected final String TEXT_20 = NL + "        private final StringIndexerInverseFunction_";
  protected final String TEXT_21 = " inverseIndexer;";
  protected final String TEXT_22 = NL + NL + "    public Classify_";
  protected final String TEXT_23 = "(org.apache.spark.ml.PipelineModel pipelineModel) {" + NL + "        this.pipelineModel = pipelineModel;";
  protected final String TEXT_24 = NL + "            org.apache.spark.ml.feature.StringIndexerModel sim = (org.apache.spark.ml.feature.StringIndexerModel) pipelineModel.stages()[1];" + NL + "            inverseIndexer = new StringIndexerInverseFunction_";
  protected final String TEXT_25 = "(sim);";
  protected final String TEXT_26 = NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_27 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_28 = "> rddIn)" + NL + "            throws Exception {" + NL + "        if (sqlContext == null) {" + NL + "            sqlContext = new org.apache.spark.sql.SQLContext(rddIn.context());" + NL + "        }" + NL + "" + NL + "        // Convert incoming RDD to DataFrame";
  protected final String TEXT_29 = NL + "        ";
  protected final String TEXT_30 = " df = sqlContext.createDataFrame(rddIn, ";
  protected final String TEXT_31 = ".class);";
  protected final String TEXT_32 = NL + "        ";
  protected final String TEXT_33 = " results = pipelineModel.transform(df);" + NL;
  protected final String TEXT_34 = NL + "            org.apache.spark.rdd.RDD<";
  protected final String TEXT_35 = "> rdd = results.map(inverseIndexer," + NL + "                    scala.reflect.ClassManifestFactory.fromClass(";
  protected final String TEXT_36 = ".class));" + NL + "            return org.apache.spark.api.java.JavaRDD.fromRDD(rdd, rdd.org$apache$spark$rdd$RDD$$evidence$1);";
  protected final String TEXT_37 = NL + "            // TODO";
  protected final String TEXT_38 = NL + "    }" + NL + "}";
  protected final String TEXT_39 = NL;

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
String inputLabelColumn = "unique82464359435"; // TO READ FROM MODEL!
String labelColumn = "label";

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_8);
    
        for (IMetadataColumn column: columns) {
            if (!labelColumn.equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_9);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_12);
    
            } else {
                
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(inputLabelColumn);
    stringBuffer.append(TEXT_15);
    
            }
        }
        
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_19);
    
    if (needsLabelIndexer) {
        
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
    }
    
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
        if (needsLabelIndexer) {
            
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    
        }
        
    stringBuffer.append(TEXT_26);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_33);
    
        if (needsLabelIndexer) {
            
    stringBuffer.append(TEXT_34);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_36);
    
        } else {
            
    stringBuffer.append(TEXT_37);
    
        }
        
    stringBuffer.append(TEXT_38);
    stringBuffer.append(TEXT_39);
    return stringBuffer.toString();
  }
}
