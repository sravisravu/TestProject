package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;

public class TDataprepOutBeginJava
{
  protected static String nl;
  public static synchronized TDataprepOutBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepOutBeginJava result = new TDataprepOutBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_4 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_5 = ");";
  protected final String TEXT_6 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_7 = " = ";
  protected final String TEXT_8 = "; ";
  protected final String TEXT_9 = NL + NL + "String apiurl_";
  protected final String TEXT_10 = " = (String)(new us.monoid.web.Resty().json(\"\"+";
  protected final String TEXT_11 = "+\"/assets/config/config.json\").get(\"serverUrl\"));" + NL + "resourceMap.put(\"apiurl_";
  protected final String TEXT_12 = "\", apiurl_";
  protected final String TEXT_13 = ");" + NL + "" + NL + "org.apache.http.client.fluent.Request request_";
  protected final String TEXT_14 = " = org.apache.http.client.fluent.Request.Post(apiurl_";
  protected final String TEXT_15 = "+\"/login?username=\"+";
  protected final String TEXT_16 = "+\"&password=\"+decryptedPassword_";
  protected final String TEXT_17 = "+\"&client-app=studio\");" + NL + "org.apache.http.HttpResponse response_";
  protected final String TEXT_18 = " = request_";
  protected final String TEXT_19 = ".execute().returnResponse();" + NL + "" + NL + "org.talend.http.HttpUtil.handHttpResponse(response_";
  protected final String TEXT_20 = ");" + NL + "" + NL + "org.apache.http.Header authorisationHeader_";
  protected final String TEXT_21 = " = response_";
  protected final String TEXT_22 = ".getFirstHeader(\"Authorization\");" + NL + "resourceMap.put(\"authorisationHeader_";
  protected final String TEXT_23 = "\", authorisationHeader_";
  protected final String TEXT_24 = ");" + NL + "" + NL + "final java.net.URL connectionUrl_";
  protected final String TEXT_25 = " = new java.net.URL(apiurl_";
  protected final String TEXT_26 = "+\"/api/preparations/\"+";
  protected final String TEXT_27 = "+\"/content\");" + NL + "final java.net.HttpURLConnection urlConnection_";
  protected final String TEXT_28 = " = (java.net.HttpURLConnection) connectionUrl_";
  protected final String TEXT_29 = ".openConnection();" + NL + "urlConnection_";
  protected final String TEXT_30 = ".setRequestMethod(\"POST\");" + NL + "//if login success, auth header exists" + NL + "urlConnection_";
  protected final String TEXT_31 = ".setRequestProperty(authorisationHeader_";
  protected final String TEXT_32 = ".getName(), authorisationHeader_";
  protected final String TEXT_33 = ".getValue());" + NL + "urlConnection_";
  protected final String TEXT_34 = ".setRequestProperty(\"Content-Type\", \"application/json\");" + NL + "urlConnection_";
  protected final String TEXT_35 = ".setRequestProperty(\"Accept\", \"application/json, text/plain\");" + NL + "urlConnection_";
  protected final String TEXT_36 = ".setDoOutput(true);" + NL + "" + NL + "final java.io.OutputStream os_";
  protected final String TEXT_37 = " = urlConnection_";
  protected final String TEXT_38 = ".getOutputStream();" + NL + "resourceMap.put(\"os_";
  protected final String TEXT_39 = "\", os_";
  protected final String TEXT_40 = ");" + NL + "" + NL + "boolean firstRow_";
  protected final String TEXT_41 = " = true;" + NL + "final String charSet_";
  protected final String TEXT_42 = " = \"UTF-8\";" + NL + "final byte[] separator_";
  protected final String TEXT_43 = " = \",\".getBytes(charSet_";
  protected final String TEXT_44 = "); ";
  protected final String TEXT_45 = NL;

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

String url = ElementParameterParser.getValue(node, "__URL__");
String preparationId = ElementParameterParser.getValue(node, "__PREPARATION_ID__");

String uname = ElementParameterParser.getValue(node, "__UNAME__");

String username = ElementParameterParser.getValue(node, "__USERNAME__");
String passwordFieldName = "__PASSWORD__";

    stringBuffer.append(TEXT_2);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_5);
    } else {
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(url );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(username );
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
    stringBuffer.append(preparationId );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(TEXT_45);
    return stringBuffer.toString();
  }
}
