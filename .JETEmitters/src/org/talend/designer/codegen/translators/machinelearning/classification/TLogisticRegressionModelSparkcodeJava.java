package org.talend.designer.codegen.translators.machinelearning.classification;

import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TLogisticRegressionModelSparkcodeJava
{
  protected static String nl;
  public static synchronized TLogisticRegressionModelSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TLogisticRegressionModelSparkcodeJava result = new TLogisticRegressionModelSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    return stringBuffer.toString();
  }
}
