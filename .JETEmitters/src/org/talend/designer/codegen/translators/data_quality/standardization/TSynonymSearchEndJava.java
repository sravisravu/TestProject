package org.talend.designer.codegen.translators.data_quality.standardization;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TSynonymSearchEndJava
{
  protected static String nl;
  public static synchronized TSynonymSearchEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSynonymSearchEndJava result = new TSynonymSearchEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "for (int index_";
  protected final String TEXT_2 = " = 0 ; index_";
  protected final String TEXT_3 = " < uniqueIndexNum_";
  protected final String TEXT_4 = "; index_";
  protected final String TEXT_5 = "++) {" + NL + "\trecSearch_";
  protected final String TEXT_6 = ".getSearcher(index_";
  protected final String TEXT_7 = ").close();" + NL + "}" + NL + "recSearch_";
  protected final String TEXT_8 = " = null;";
  protected final String TEXT_9 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();	

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
