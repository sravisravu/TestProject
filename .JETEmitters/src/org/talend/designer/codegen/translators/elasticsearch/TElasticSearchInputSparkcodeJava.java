package org.talend.designer.codegen.translators.elasticsearch;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.telasticsearchinput.TElasticSearchInputUtil;

public class TElasticSearchInputSparkcodeJava
{
  protected static String nl;
  public static synchronized TElasticSearchInputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TElasticSearchInputSparkcodeJava result = new TElasticSearchInputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = "_FromEsTo";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<scala.Tuple2<String, String>, ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = " call(scala.Tuple2<String, String> record) {";
  protected final String TEXT_6 = NL + "        ";
  protected final String TEXT_7 = " result = new ";
  protected final String TEXT_8 = "();" + NL + "        result.";
  protected final String TEXT_9 = " = record._1();" + NL + "        result.";
  protected final String TEXT_10 = " = record._2();" + NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TElasticSearchInputUtil tElasticSearchInputUtil = new TElasticSearchInputUtil(node);
String outStructName = codeGenArgument.getRecordStructName(tElasticSearchInputUtil.getOutgoingConnection());

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(tElasticSearchInputUtil.getIdDocumentColumnName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(tElasticSearchInputUtil.getJsonDocumentColumnName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
