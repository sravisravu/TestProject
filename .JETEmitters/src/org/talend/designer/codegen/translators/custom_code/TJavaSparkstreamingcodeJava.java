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

public class TJavaSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TJavaSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJavaSparkstreamingcodeJava result = new TJavaSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        public static class RecordOut_";
  protected final String TEXT_2 = " extends ";
  protected final String TEXT_3 = " implements Serializable {}" + NL + "" + NL + "        public static class ToRecordOut_";
  protected final String TEXT_4 = " implements org.apache.spark.api.java.function.Function<RecordOut_";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "            private ContextProperties context = null;" + NL + "            private java.util.List<org.apache.avro.Schema.Field> fieldsList;" + NL + "" + NL + "            public ToRecordOut_";
  protected final String TEXT_7 = "(JobConf job) {" + NL + "                this.context = new ContextProperties(job);" + NL + "            }" + NL + "            " + NL + "            @Override" + NL + "            public ";
  protected final String TEXT_8 = " call(RecordOut_";
  protected final String TEXT_9 = " origStruct){" + NL + "" + NL + "                if (fieldsList == null) {" + NL + "                        this.fieldsList = (new RecordOut_";
  protected final String TEXT_10 = "()).getSchema().getFields();" + NL + "                }" + NL;
  protected final String TEXT_11 = NL + "                ";
  protected final String TEXT_12 = " value = new ";
  protected final String TEXT_13 = "();" + NL + "" + NL + "                for (org.apache.avro.Schema.Field field : fieldsList) {" + NL + "                        value.put(field.pos(), origStruct.get(field.pos()));" + NL + "                }" + NL + "" + NL + "                return value;" + NL + "            }" + NL + "        }";
  protected final String TEXT_14 = NL + "    ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final String classes = ElementParameterParser.getValue(node, "__CLASS_CODE__");
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

if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node)) {
} else {
    // An intermediate class (RecordOut) as the output class so that the user does not have to deal with rowXStruct classes as his output class. A map to transform RecordOut rdds to rowXStruct rdds
    if (outgoingConnection != null) {
    
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outGoingStructName);
    stringBuffer.append(TEXT_13);
    
    }
    // Include user defined classes (from advanced settings) in the code part to avoid any serialization issues.
    
    stringBuffer.append(TEXT_14);
    stringBuffer.append(classes);
    
}

    return stringBuffer.toString();
  }
}
