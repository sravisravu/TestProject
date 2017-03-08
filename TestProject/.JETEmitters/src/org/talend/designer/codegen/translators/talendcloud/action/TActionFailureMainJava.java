package org.talend.designer.codegen.translators.talendcloud.action;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TActionFailureMainJava
{
  protected static String nl;
  public static synchronized TActionFailureMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TActionFailureMainJava result = new TActionFailureMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t";
  protected final String TEXT_2 = ".addMessage(\"tActionFailure\", \"";
  protected final String TEXT_3 = "\", 4, ";
  protected final String TEXT_4 = ", 5);" + NL + "\t\t\t\t";
  protected final String TEXT_5 = "Process(globalMap);";
  protected final String TEXT_6 = NL + "\tglobalMap.put(\"";
  protected final String TEXT_7 = "_ACTIONFAILURE_MESSAGE\", ";
  protected final String TEXT_8 = ");" + NL + "" + NL + "\texception = new TActionFailureException(";
  protected final String TEXT_9 = ",";
  protected final String TEXT_10 = ");" + NL + "" + NL + "\tcurrentComponent = \"";
  protected final String TEXT_11 = "\";" + NL + "" + NL + "\tstatus = \"failure\";" + NL + "" + NL + "\tif (true) {" + NL + "\t\tthrow exception;" + NL + "\t}" + NL;
  protected final String TEXT_12 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode) codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	String errorType = ElementParameterParser.getValue(node, "__ERROR_TYPE__");
	String errorMessage = ElementParameterParser.getValue(node, "__ERROR_MESSAGE__");

	if (node.getProcess().getNodesOfType("tLogCatcher").size() > 0) {
		List<INode> logCatchers = (List<INode>) node.getProcess().getNodesOfType("tLogCatcher");
		for (INode logCatcher : logCatchers) {
			if (ElementParameterParser.getBooleanValue(logCatcher, "__CATCH_TACTIONFAILURE__")) { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(logCatcher.getUniqueName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(errorMessage);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(logCatcher.getDesignSubjobStartNode().getUniqueName());
    stringBuffer.append(TEXT_5);
    
			}
		}
	}

    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(errorMessage);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(errorType);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(errorMessage);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    return stringBuffer.toString();
  }
}
