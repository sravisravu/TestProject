package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.process.IConnection;

public class TDataprepOutFinallyJava
{
  protected static String nl;
  public static synchronized TDataprepOutFinallyJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepOutFinallyJava result = new TDataprepOutFinallyJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "final Object os_";
  protected final String TEXT_3 = " = resourceMap.get(\"os_";
  protected final String TEXT_4 = "\");" + NL + "if(os_";
  protected final String TEXT_5 = "!=null){" + NL + "\ttry {" + NL + "\t\t((java.io.OutputStream)os_";
  protected final String TEXT_6 = ").close();" + NL + "\t} catch(java.lang.Exception e_";
  protected final String TEXT_7 = ") {" + NL + "\t\t//close quietly" + NL + "\t}" + NL + "}" + NL + "" + NL + "final Object is_";
  protected final String TEXT_8 = " = resourceMap.get(\"is_";
  protected final String TEXT_9 = "\");" + NL + "if(is_";
  protected final String TEXT_10 = "!=null){" + NL + "\ttry {" + NL + "\t\t((java.io.InputStream)is_";
  protected final String TEXT_11 = ").close();" + NL + "\t} catch(java.lang.Exception e_";
  protected final String TEXT_12 = ") {" + NL + "\t\t//close quietly" + NL + "\t}" + NL + "}" + NL + "" + NL + "final Object authorisationHeader_";
  protected final String TEXT_13 = " = resourceMap.get(\"authorisationHeader_";
  protected final String TEXT_14 = "\");" + NL + "if(authorisationHeader_";
  protected final String TEXT_15 = "!=null){//have login, need to logout" + NL + "    org.apache.http.client.fluent.Request logout_";
  protected final String TEXT_16 = " = org.apache.http.client.fluent.Request.Post((String)resourceMap.get(\"apiurl_";
  protected final String TEXT_17 = "\")+\"/logout?client-app=studio\").addHeader((org.apache.http.Header)authorisationHeader_";
  protected final String TEXT_18 = ");" + NL + "    " + NL + "    org.apache.http.HttpResponse response_";
  protected final String TEXT_19 = " = logout_";
  protected final String TEXT_20 = ".execute().returnResponse();" + NL + "    " + NL + "    org.talend.http.HttpUtil.handHttpResponse(response_";
  protected final String TEXT_21 = ");" + NL + "}" + NL;
  protected final String TEXT_22 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

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
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
