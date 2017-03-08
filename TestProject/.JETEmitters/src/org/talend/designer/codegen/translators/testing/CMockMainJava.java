package org.talend.designer.codegen.translators.testing;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class CMockMainJava
{
  protected static String nl;
  public static synchronized CMockMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CMockMainJava result = new CMockMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "            .to(\"mock:";
  protected final String TEXT_3 = "\")";
  protected final String TEXT_4 = NL + "        from(\"mock:";
  protected final String TEXT_5 = "\")";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	List< ? extends IConnection> conns = node.getIncomingConnections();
	
    if(conns.size()>0) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
    } else {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
    }

    return stringBuffer.toString();
  }
}
