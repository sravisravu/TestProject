package org.talend.designer.codegen.translators.technical;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.utils.TalendTextUtils;

public class TSAPInfoObjectOutputInBeginJava
{
  protected static String nl;
  public static synchronized TSAPInfoObjectOutputInBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSAPInfoObjectOutputInBeginJava result = new TSAPInfoObjectOutputInBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\tjava.util.List<org.talend.sap.model.bw.ISAPBWProtocolMessage> messages_";
  protected final String TEXT_3 = " = (java.util.List<org.talend.sap.model.bw.ISAPBWProtocolMessage>)globalMap.get(\"protocol_";
  protected final String TEXT_4 = "\");" + NL + "\t" + NL + "\tif(messages_";
  protected final String TEXT_5 = " == null) {" + NL + "\t\tmessages_";
  protected final String TEXT_6 = " = new java.util.ArrayList<org.talend.sap.model.bw.ISAPBWProtocolMessage>();" + NL + "\t}" + NL + "\t" + NL + "\tfor(org.talend.sap.model.bw.ISAPBWProtocolMessage message_";
  protected final String TEXT_7 = " : messages_";
  protected final String TEXT_8 = ") {//}" + NL + "\t\t";
  protected final String TEXT_9 = ".protocol_message_id = message_";
  protected final String TEXT_10 = ".getId();" + NL + "\t\t";
  protected final String TEXT_11 = ".protocol_message_number = message_";
  protected final String TEXT_12 = ".getNumber();" + NL + "\t\t";
  protected final String TEXT_13 = ".protocol_message_text = message_";
  protected final String TEXT_14 = ".getText();" + NL + "\t\t";
  protected final String TEXT_15 = ".protocol_message_type = message_";
  protected final String TEXT_16 = ".getType();" + NL + "\t\t";
  protected final String TEXT_17 = ".protocol_message_variable1 = message_";
  protected final String TEXT_18 = ".getVariable1();" + NL + "\t\t";
  protected final String TEXT_19 = ".protocol_message_variable2 = message_";
  protected final String TEXT_20 = ".getVariable2();" + NL + "\t\t";
  protected final String TEXT_21 = ".protocol_message_variable3 = message_";
  protected final String TEXT_22 = ".getVariable3();" + NL + "\t\t";
  protected final String TEXT_23 = ".protocol_message_variable4 = message_";
  protected final String TEXT_24 = ".getVariable4();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<IMetadataTable> metadatas = node.getMetadataList();
	if ((metadatas == null) && (metadatas.size() == 0) || (metadatas.get(0) == null)) {
		return "";
	}
	IMetadataTable metadata = metadatas.get(0);
	
	List<IMetadataColumn> columnList = metadata.getListColumns();
	if((columnList == null) || (columnList.size() == 0)) {
		return "";
	}
	
	boolean on_submit_protocol_work = "true".equals(ElementParameterParser.getValue(node, "__ON_SUBMIT_PROTOCOL_WORK__"));
	if(!on_submit_protocol_work) {
		return "";
	}
	
	String graphicalUniqueName = cid.replace("_In","");
	
	IConnection outputConnection = null;
	List<? extends IConnection> connections = node.getOutgoingConnections();
	if(connections==null || connections.isEmpty()) {
		return "";
	}
	
	for(IConnection conn : connections) {
		if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			outputConnection = conn;
			break;
		}
	}
	
	if(outputConnection == null) {
		return "";
	}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(graphicalUniqueName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(outputConnection.getName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
