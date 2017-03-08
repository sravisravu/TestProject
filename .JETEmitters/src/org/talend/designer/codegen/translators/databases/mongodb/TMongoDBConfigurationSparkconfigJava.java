package org.talend.designer.codegen.translators.databases.mongodb;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;;

public class TMongoDBConfigurationSparkconfigJava
{
  protected static String nl;
  public static synchronized TMongoDBConfigurationSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMongoDBConfigurationSparkconfigJava result = new TMongoDBConfigurationSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

// Component Parameters
String dbhost = ElementParameterParser.getValue(node, "__HOST__");
String dbname = ElementParameterParser.getValue(node, "__DATABASE__");
String dbuser = ElementParameterParser.getValue(node, "__USERNAME__");
String dbpass = ElementParameterParser.getValue(node, "__PASSWORD__");
String dbversion = ElementParameterParser.getValue(node, "__DB_VERSION__");
boolean authentication = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__REQUIRED_AUTHENTICATION__"));
String authenticationMechanism = ElementParameterParser.getValue(node, "__AUTHENTICATION_MECHANISM__");
String krbUserPrincipal = ElementParameterParser.getValue(node, "__KRB_USER_PRINCIPAL__");
String krbRealm = ElementParameterParser.getValue(node, "__KRB_REALM__");
String krbKdc = ElementParameterParser.getValue(node, "__KRB_KDC__");
boolean useReplicaSet = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__USE_REPLICA_SET__"));
boolean useSSL = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__USE_SSL__"));
String mongoPort = ElementParameterParser.getValue(node, "__PORT__");
String dbport = mongoPort.startsWith("context.") ? "Integer.valueOf(" + mongoPort + ").intValue()" : mongoPort.replace("\"", "");
java.util.List<java.util.Map<String,String>> replicaAddrs= (java.util.List<java.util.Map<String,String>>)ElementParameterParser.getObjectValue(node, "__REPLICA_SET__");


    

    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
