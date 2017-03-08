package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;

public class TDataprepOutMainJava
{
  protected static String nl;
  public static synchronized TDataprepOutMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepOutMainJava result = new TDataprepOutMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "if(firstRow_";
  protected final String TEXT_3 = ") {" + NL + "\tos_";
  protected final String TEXT_4 = ".write(\"{\\\"records\\\":[\".getBytes(charSet_";
  protected final String TEXT_5 = "));" + NL + "\tos_";
  protected final String TEXT_6 = ".flush();" + NL + "\t" + NL + "\tfirstRow_";
  protected final String TEXT_7 = " = false;" + NL + "} else {" + NL + "\tos_";
  protected final String TEXT_8 = ".write(separator_";
  protected final String TEXT_9 = ");" + NL + "\tos_";
  protected final String TEXT_10 = ".flush();" + NL + "}" + NL + "" + NL + "us.monoid.json.JSONObject record_";
  protected final String TEXT_11 = " = new us.monoid.json.JSONObject();" + NL;
  protected final String TEXT_12 = NL + "    record_";
  protected final String TEXT_13 = ".put(" + NL + "        \"";
  protected final String TEXT_14 = "\"," + NL + "    \troutines.system.FormatterUtils.format(";
  protected final String TEXT_15 = ".";
  protected final String TEXT_16 = ",";
  protected final String TEXT_17 = ")" + NL + "    );" + NL + "\t";
  protected final String TEXT_18 = NL + NL + "os_";
  protected final String TEXT_19 = ".write(record_";
  protected final String TEXT_20 = ".toString().getBytes(charSet_";
  protected final String TEXT_21 = "));" + NL + "os_";
  protected final String TEXT_22 = ".flush();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
List<? extends IConnection> incomingConnections = node.getIncomingConnections();

if((incomingConnections==null)&&(incomingConnections.isEmpty())) {
	return stringBuffer.toString();
}

IConnection inputConn = null;
for(IConnection incomingConnection : incomingConnections) {
	if(incomingConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
		inputConn = incomingConnection;
	}
}

if(inputConn==null) {
	return stringBuffer.toString();
}

List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas==null)||(metadatas.isEmpty())) {
	return stringBuffer.toString();
}

IMetadataTable metadataOfNode = metadatas.get(0);

if(metadataOfNode == null) {
	return stringBuffer.toString();
}

IMetadataTable metadataOfInput = inputConn.getMetadataTable();

if(metadataOfInput == null) {
	return stringBuffer.toString();
}

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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
int index = 0;
for(IMetadataColumn column : metadataOfInput.getListColumns()){
	String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
	
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_13);
    stringBuffer.append(String.format("%04d", index++));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(inputConn.getName() );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_17);
    
}

    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
