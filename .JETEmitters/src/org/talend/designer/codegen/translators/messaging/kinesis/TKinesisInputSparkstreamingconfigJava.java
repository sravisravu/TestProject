package org.talend.designer.codegen.translators.messaging.kinesis;

import java.util.Map.Entry;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TKinesisInputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TKinesisInputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKinesisInputSparkstreamingconfigJava result = new TKinesisInputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "System.setProperty(\"aws.accessKeyId\", ";
  protected final String TEXT_2 = ");" + NL + "//Get the password under the variable decryptedPassword";
  protected final String TEXT_3 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_4 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_5 = ");";
  protected final String TEXT_6 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_7 = " = ";
  protected final String TEXT_8 = "; ";
  protected final String TEXT_9 = NL + "System.setProperty(\"aws.secretKey\", decryptedPassword_";
  protected final String TEXT_10 = ");" + NL;
  protected final String TEXT_11 = NL + "    java.text.DateFormat outdfm_";
  protected final String TEXT_12 = " = new java.text.SimpleDateFormat(\"yyyy_MM_dd_HH_mm_ss\");" + NL + "    String kinesisName_";
  protected final String TEXT_13 = " = projectName + \"_\" + jobName + \"_\" + jobVersion + \"_\" + \"";
  protected final String TEXT_14 = "\" + \"_\"" + NL + "                + outdfm_";
  protected final String TEXT_15 = ".format(new java.util.Date());" + NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_16 = "> rdd_";
  protected final String TEXT_17 = " = " + NL + "            org.apache.spark.streaming.kinesis.KinesisUtils.createStream(" + NL + "                ctx," + NL + "                kinesisName_";
  protected final String TEXT_18 = ",";
  protected final String TEXT_19 = NL + "                ";
  protected final String TEXT_20 = ",";
  protected final String TEXT_21 = NL + "                ";
  protected final String TEXT_22 = ",";
  protected final String TEXT_23 = NL + "                ";
  protected final String TEXT_24 = "," + NL + "                com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream.";
  protected final String TEXT_25 = "," + NL + "                new org.apache.spark.streaming.Duration(";
  protected final String TEXT_26 = ")," + NL + "                org.apache.spark.storage.StorageLevel.";
  protected final String TEXT_27 = "(),";
  protected final String TEXT_28 = NL + "                ";
  protected final String TEXT_29 = "," + NL + "                decryptedPassword_";
  protected final String TEXT_30 = ")" + NL + "            .map(new ";
  protected final String TEXT_31 = "_MapToOutputStruct());";
  protected final String TEXT_32 = NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_33 = "> rdd_";
  protected final String TEXT_34 = " = " + NL + "        org.apache.spark.streaming.kinesis.KinesisUtils.createStream(" + NL + "                ctx,";
  protected final String TEXT_35 = NL + "                ";
  protected final String TEXT_36 = ",";
  protected final String TEXT_37 = NL + "                ";
  protected final String TEXT_38 = "," + NL + "                new org.apache.spark.streaming.Duration(";
  protected final String TEXT_39 = ")," + NL + "                com.amazonaws.services.kinesis.clientlibrary.lib.worker.InitialPositionInStream.";
  protected final String TEXT_40 = "," + NL + "                org.apache.spark.storage.StorageLevel.";
  protected final String TEXT_41 = "())" + NL + "        .map(new ";
  protected final String TEXT_42 = "_MapToOutputStruct());";
  protected final String TEXT_43 = NL;
  protected final String TEXT_44 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
IConnection conn = node.getOutgoingConnections().get(0);
String connectionTypeName = codeGenArgument.getRecordStructName(conn);
String connName = conn.getUniqueName();

String accessKey = ElementParameterParser.getValue(node, "__ACCESS_KEY__");
String passwordFieldName = "__SECRET_KEY__";

String streamName = ElementParameterParser.getValue(node, "__STREAM_NAME__");
String endpointUrl = ElementParameterParser.getValue(node, "__ENDPOINT_URL__");

Boolean explicitAuthentication = ElementParameterParser.getBooleanValue(node, "__EXPLICIT_AUTHENTICATION__");
String region = ElementParameterParser.getValue(node, "__REGION__");

String checkpointInterval = ElementParameterParser.getValue(node, "__CHECKPOINT_INTERVAL__");
String initialPositionStream = ElementParameterParser.getValue(node, "__INITIAL_POSITION_STREAM__");
String storageLevel = ElementParameterParser.getValue(node, "__STORAGELEVEL__");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(accessKey);
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
    
if (explicitAuthentication) {
    // new connection mode, need a region
    // but the access key and secret key will be shared
    
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(streamName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(endpointUrl);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(initialPositionStream);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(checkpointInterval);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(storageLevel);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(accessKey);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
} else {
    // old connection mode, does not require a region
    // but the connection may fail
    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(streamName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(endpointUrl);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(checkpointInterval);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(initialPositionStream);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(storageLevel);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    
}

    stringBuffer.append(TEXT_43);
    stringBuffer.append(TEXT_44);
    return stringBuffer.toString();
  }
}
