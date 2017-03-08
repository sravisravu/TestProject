package org.talend.designer.codegen.translators.messaging.kafka;

import java.util.Map.Entry;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tkafkaoutput.TKafkaOutputUtil;

public class TKafkaOutputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TKafkaOutputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKafkaOutputSparkstreamingconfigJava result = new TKafkaOutputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "rdd_";
  protected final String TEXT_3 = ".foreachRDD(new ";
  protected final String TEXT_4 = "_ForeachRDD(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final TKafkaOutputUtil tKafkaOutputUtil = new TKafkaOutputUtil(node);

    stringBuffer.append(TEXT_2);
    stringBuffer.append(tKafkaOutputUtil.getIncomingConnection().getName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    return stringBuffer.toString();
  }
}
