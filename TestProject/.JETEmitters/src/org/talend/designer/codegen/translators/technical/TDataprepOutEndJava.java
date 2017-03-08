package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;

public class TDataprepOutEndJava
{
  protected static String nl;
  public static synchronized TDataprepOutEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepOutEndJava result = new TDataprepOutEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "os_";
  protected final String TEXT_3 = ".write(\"]}\".getBytes(charSet_";
  protected final String TEXT_4 = "));" + NL + "os_";
  protected final String TEXT_5 = ".flush();" + NL + "os_";
  protected final String TEXT_6 = ".close();" + NL + "" + NL + "java.io.InputStream is_";
  protected final String TEXT_7 = " = null;" + NL + "try {" + NL + "\tis_";
  protected final String TEXT_8 = " = urlConnection_";
  protected final String TEXT_9 = ".getInputStream();" + NL + "} catch (java.io.IOException ioe_";
  protected final String TEXT_10 = ") {" + NL + "\tjava.io.InputStream es_";
  protected final String TEXT_11 = " = new java.io.BufferedInputStream(urlConnection_";
  protected final String TEXT_12 = ".getErrorStream());" + NL + "" + NL + "\tjava.io.ByteArrayOutputStream baos_";
  protected final String TEXT_13 = " = new java.io.ByteArrayOutputStream();" + NL + "\t" + NL + "\tbyte[] buf_";
  protected final String TEXT_14 = " = new byte[1024];" + NL + "\tint read_";
  protected final String TEXT_15 = " = -1;" + NL + "\twhile ((read_";
  protected final String TEXT_16 = " = es_";
  protected final String TEXT_17 = ".read(buf_";
  protected final String TEXT_18 = ")) > 0) {" + NL + "\t\tbaos_";
  protected final String TEXT_19 = ".write(buf_";
  protected final String TEXT_20 = ", 0, read_";
  protected final String TEXT_21 = ");" + NL + "\t}" + NL + "" + NL + "\tes_";
  protected final String TEXT_22 = ".close();" + NL + "" + NL + "\tthrow new java.io.IOException(\"Error while reading from \" + urlConnection_";
  protected final String TEXT_23 = ".getRequestMethod() + \": [\" + urlConnection_";
  protected final String TEXT_24 = ".getResponseCode() + \"] \"" + NL + "\t\t\t+ urlConnection_";
  protected final String TEXT_25 = ".getResponseMessage() + \"\\n\" + new String(baos_";
  protected final String TEXT_26 = ".toByteArray(), \"UTF-8\"), ioe_";
  protected final String TEXT_27 = ");" + NL + "}" + NL + "" + NL + "resourceMap.put(\"is_";
  protected final String TEXT_28 = "\", is_";
  protected final String TEXT_29 = ");";

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

IMetadataTable metadata = metadatas.get(0);

if(metadata == null) {
	return stringBuffer.toString();
}

String uname = ElementParameterParser.getValue(node, "__UNAME__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(uname);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(uname);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(uname);
    stringBuffer.append(TEXT_29);
    return stringBuffer.toString();
  }
}
