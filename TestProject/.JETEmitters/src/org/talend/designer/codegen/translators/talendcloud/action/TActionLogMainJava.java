package org.talend.designer.codegen.translators.talendcloud.action;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.NodeParamsHelper;

public class TActionLogMainJava
{
  protected static String nl;
  public static synchronized TActionLogMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TActionLogMainJava result = new TActionLogMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t\t\t\t";
  protected final String TEXT_2 = ".";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL + "\torg.apache.log4j.Level logPriority_";
  protected final String TEXT_7 = " = org.apache.log4j.Level.toLevel(";
  protected final String TEXT_8 = ");" + NL + "\tString message_";
  protected final String TEXT_9 = " = String.valueOf(";
  protected final String TEXT_10 = ");" + NL + "" + NL + "\torg.apache.log4j.MDC.put(\"ticLogLevel\", \"";
  protected final String TEXT_11 = "\");" + NL + "\tlog.log(logPriority_";
  protected final String TEXT_12 = ", message_";
  protected final String TEXT_13 = ");" + NL + "\torg.apache.log4j.MDC.remove(\"ticLogLevel\");" + NL;
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode) codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	NodeParamsHelper paramsHelper = new NodeParamsHelper(node);

	List<IMetadataTable> metadatas = node.getMetadataList();
	if (null != metadatas && !metadatas.isEmpty()) { //A
		IMetadataTable metadata = metadatas.get(0);
		if (null != metadata) { //2
			IConnection inConn = null;
			IConnection outConn = null;
			List<? extends IConnection> inConns = node.getIncomingConnections();

			//in order to support the "Iterate/Main" at the same time.
			List<? extends IConnection> outConns = node.getOutgoingSortedConnections();
			if (null != inConns && !inConns.isEmpty()) {
				inConn = inConns.get(0);
			}
			if (null != outConns && !outConns.isEmpty()) {
				outConn = outConns.get(0);
			}
			if (null != inConn && null != outConn
					&& inConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)
					&& outConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { //3
				IMetadataTable ref_metadata = inConn.getMetadataTable();
				if (null != ref_metadata) {
					List<IMetadataColumn> columns = metadata.getListColumns();
					for (IMetadataColumn column : columns) {
						//add a name mapping for issue:11712
						if (ref_metadata.getColumn(column.getLabel()) != null) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(outConn.getName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(inConn.getName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_5);
    
						}
					}
				}
			} //3
		} //2
	} //A

	//boolean businessLog = paramsHelper.getBoolParam("__BUSINESS_LOG__");
	int logLevel = Integer.parseInt(paramsHelper.getStringParam("__PRIORITY__"));

    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(logLevel);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(paramsHelper.getStringParam("__MESSAGE__"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(paramsHelper.getBoolParam("__DEV_LOG__") ? "2" : "1");
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
