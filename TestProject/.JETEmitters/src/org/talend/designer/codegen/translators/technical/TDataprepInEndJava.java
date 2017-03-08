package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;

public class TDataprepInEndJava
{
  protected static String nl;
  public static synchronized TDataprepInEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepInEndJava result = new TDataprepInEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "\t}" + NL + "} finally {" + NL + "\tif(jsonParser_";
  protected final String TEXT_3 = "!=null) {" + NL + "    \ttry {" + NL + "    \t\tjsonParser_";
  protected final String TEXT_4 = ".close();" + NL + "    \t} catch(java.io.IOException exc_";
  protected final String TEXT_5 = ") {" + NL + "    \t\t//close quietly" + NL + "    \t}" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<? extends IConnection> outConnections = node.getOutgoingConnections();

if((outConnections==null)&&(outConnections.isEmpty())) {
	return stringBuffer.toString();
}

boolean isValid = false;
for(IConnection outConnection : outConnections) {
	if(outConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.MAIN)) {
		isValid = true;
	}
}

if(!isValid) {
	return stringBuffer.toString();
}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
