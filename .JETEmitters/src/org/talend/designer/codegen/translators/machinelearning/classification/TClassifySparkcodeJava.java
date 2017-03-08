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

public class TClassifySparkcodeJava
{
  protected static String nl;
  public static synchronized TClassifySparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TClassifySparkcodeJava result = new TClassifySparkcodeJava();
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
  protected final String TEXT_16 = NL + "        return out;" + NL + "    }" + NL + "}";
  protected final String TEXT_17 = NL;

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
    stringBuffer.append(TEXT_17);
    return stringBuffer.toString();
  }
}
