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

public class TSVMModelSparkcodeJava
{
  protected static String nl;
  public static synchronized TSVMModelSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSVMModelSparkcodeJava result = new TSVMModelSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "public static class GetLabeledPoint_";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, org.apache.spark.mllib.regression.LabeledPoint> {" + NL + "" + NL + "    public org.apache.spark.mllib.regression.LabeledPoint call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_4 = NL + "        ";
  protected final String TEXT_5 = " feature = null;" + NL + "        double label = 0.0d;" + NL + "        try {" + NL + "            label = (double) input.get(input.fieldIndex(\"";
  protected final String TEXT_6 = "\"));" + NL + "            feature = (";
  protected final String TEXT_7 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_8 = "\"));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }" + NL + "        return new org.apache.spark.mllib.regression.LabeledPoint(label, feature);" + NL + "    }" + NL + "}";

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
String inputLabelColumn = ElementParameterParser.getValue(node, "__LABEL_COLUMN__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inputLabelColumn);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inputVectorColumn);
    stringBuffer.append(TEXT_8);
    return stringBuffer.toString();
  }
}
