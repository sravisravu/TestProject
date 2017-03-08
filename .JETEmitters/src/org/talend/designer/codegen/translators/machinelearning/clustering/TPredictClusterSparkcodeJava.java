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

public class TPredictClusterSparkcodeJava
{
  protected static String nl;
  public static synchronized TPredictClusterSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictClusterSparkcodeJava result = new TPredictClusterSparkcodeJava();
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
  protected final String TEXT_30 = NL + "        return output;" + NL + "    }" + NL + "}";

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
    return stringBuffer.toString();
  }
}
