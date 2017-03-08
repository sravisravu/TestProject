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

public class TKMeansModelSparkcodeJava
{
  protected static String nl;
  public static synchronized TKMeansModelSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKMeansModelSparkcodeJava result = new TKMeansModelSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "public static class GetVector_";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_6 = NL + "        ";
  protected final String TEXT_7 = " result = null;" + NL + "        try {" + NL + "            result = (";
  protected final String TEXT_8 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_9 = "\"));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

String inputVectorColumn = ElementParameterParser.getValue(node, "__INPUT_COLUMN__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
