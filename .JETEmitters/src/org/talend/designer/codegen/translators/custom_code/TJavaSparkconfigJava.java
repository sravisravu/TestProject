package org.talend.designer.codegen.translators.custom_code;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TJavaSparkconfigJava
{
  protected static String nl;
  public static synchronized TJavaSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJavaSparkconfigJava result = new TJavaSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_2 = "> rdd_";
  protected final String TEXT_3 = " = rdd_";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL + "    org.apache.spark.api.java.JavaRDD<RecordOut_";
  protected final String TEXT_6 = "> outputrdd_";
  protected final String TEXT_7 = ";";
  protected final String TEXT_8 = NL + "    ";
  protected final String TEXT_9 = NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_10 = "> rdd_";
  protected final String TEXT_11 = " = outputrdd_";
  protected final String TEXT_12 = ".map(new ToRecordOut_";
  protected final String TEXT_13 = "(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final String code = ElementParameterParser.getValue(node, "__CODE__");
final java.util.List<? extends IConnection> incomingConnections = node.getIncomingConnections();
final java.util.List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
IConnection incomingConnection = null;
IConnection outgoingConnection = null;
String incomingConnectionName = null;
String outgoingConnectionName = null;
String incomingStructName = null;
String outGoingStructName = null;

// Incoming connection
if(incomingConnections != null){
    for (IConnection connection : incomingConnections) {
        if (connection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
            incomingConnection = connection;
            incomingConnectionName = incomingConnection.getName();
            incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);
            break;
        }
    }
} else {
    return "";
}

// Outgoing connection
if(outgoingConnections != null){
    for(IConnection connection : outgoingConnections){
        if (connection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
            outgoingConnection = connection;
            outgoingConnectionName = outgoingConnection.getName();
            outGoingStructName = codeGenArgument.getRecordStructName(outgoingConnection);
            break;
        }
    }
} else {
    return "";
}

if (incomingConnection != null) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_4);
    
}
if (outgoingConnection != null) {

    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
}

    stringBuffer.append(TEXT_8);
    stringBuffer.append(code);
    
if (outgoingConnection != null) {

    stringBuffer.append(TEXT_9);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(outgoingConnectionName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
}

    return stringBuffer.toString();
  }
}
