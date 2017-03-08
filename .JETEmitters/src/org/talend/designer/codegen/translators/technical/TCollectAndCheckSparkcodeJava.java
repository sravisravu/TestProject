package org.talend.designer.codegen.translators.technical;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TCollectAndCheckSparkcodeJava
{
  protected static String nl;
  public static synchronized TCollectAndCheckSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCollectAndCheckSparkcodeJava result = new TCollectAndCheckSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "/**" + NL + " * This method check if the context string is encoded or not" + NL + " */" + NL + "public boolean checkForBase64Encode_";
  protected final String TEXT_2 = "(String string) {" + NL + "    String pattern = \"^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$\";" + NL + "    java.util.regex.Pattern r = java.util.regex.Pattern.compile(pattern);" + NL + "    java.util.regex.Matcher m = r.matcher(string);" + NL + "    return m.find();" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
