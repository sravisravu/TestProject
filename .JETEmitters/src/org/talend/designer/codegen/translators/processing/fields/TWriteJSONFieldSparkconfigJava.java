package org.talend.designer.codegen.translators.processing.fields;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IBigDataNode;
import java.util.Map;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TWriteJSONFieldSparkconfigJava
{
  protected static String nl;
  public static synchronized TWriteJSONFieldSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TWriteJSONFieldSparkconfigJava result = new TWriteJSONFieldSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "    org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_3 = ",";
  protected final String TEXT_4 = NL + "        ";
  protected final String TEXT_5 = "> rdd_";
  protected final String TEXT_6 = "_temp" + NL + "        = rdd_";
  protected final String TEXT_7 = ".groupByKey().mapToPair(new ";
  protected final String TEXT_8 = "_convertFunction(job));";
  protected final String TEXT_9 = NL + "    org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_10 = ",";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = "> rdd_";
  protected final String TEXT_13 = "_temp" + NL + "        = rdd_";
  protected final String TEXT_14 = ".mapToPair(new ";
  protected final String TEXT_15 = "_convertFunction(job));";
  protected final String TEXT_16 = NL + "        // the UI of this component force us to generate node instead of BigDataNode" + NL + "        // So we have to force the output type to be a key-value with NullWrittable as the key" + NL + "        org.apache.spark.api.java.JavaPairRDD<NullWritable,";
  protected final String TEXT_17 = NL + "            ";
  protected final String TEXT_18 = "> rdd_";
  protected final String TEXT_19 = " =" + NL + "            rdd_";
  protected final String TEXT_20 = "_temp.mapToPair(new ";
  protected final String TEXT_21 = "_SetKeyAsNullWritable(job));";
  protected final String TEXT_22 = NL + "        org.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_23 = ", ";
  protected final String TEXT_24 = ">" + NL + "            rdd_";
  protected final String TEXT_25 = " =" + NL + "            rdd_";
  protected final String TEXT_26 = "_temp;";
  protected final String TEXT_27 = NL + "\t\torg.apache.spark.api.java.JavaPairRDD<";
  protected final String TEXT_28 = ", ";
  protected final String TEXT_29 = ">" + NL + "\t\t        rdd_";
  protected final String TEXT_30 = " =" + NL + "\t\t        rdd_";
  protected final String TEXT_31 = "_temp;";
  protected final String TEXT_32 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

// Do not use tWriteXXXHelper due to key handling.
// Do the key handling by hand because tWriteJSON is a INode and not a BigDataNode => No KeyList.


IConnection incomingConnection = null;
if (node.getIncomingConnections() != null) {
    for (IConnection in : node.getIncomingConnections()) {
        if (in.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
            incomingConnection = in;
            break;
        }
    }
}

IConnection outgoingConnection = null;
if (node.getOutgoingConnections() != null) {
    for (IConnection out : node.getOutgoingConnections()) {
        if (out.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
            outgoingConnection = out;
            break;
        }
    }
}

// get the KeyList from the previous component
String outputKey = "Object";
INode previousNode = incomingConnection.getSource();
if (previousNode instanceof IBigDataNode) {
    final IBigDataNode previousBDNode = (IBigDataNode) previousNode;
    List<IMetadataColumn> keyList = previousBDNode.getKeyList().get("OUTPUT");
    if (keyList == null) {
        keyList = previousBDNode.getKeyList().get("BOTH");
    }
    if (keyList.size() > 0) {
        outputKey = "scala.Tuple" + keyList.size() + "<";
        for (IMetadataColumn keyListElement: keyList) {
            outputKey += JavaTypesManager.getTypeToGenerate(keyListElement.getTalendType(), true) + ",";
        }
        outputKey = outputKey.substring(0, outputKey.length() - 1) +  ">";
    }
}

List<Map<String, String>> loopTable = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__LOOP__");
List<Map<String, String>> groupBys = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "GROUPBYS");
if ((loopTable.size() > 0) && (groupBys.size() > 0)) {
    // loop element exist =>  we group by
    
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(codeGenArgument.getRecordStructName(outgoingConnection));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
} else {
    // Flat structure
    
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(codeGenArgument.getRecordStructName(outgoingConnection));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
}

INode nextNode = outgoingConnection.getTarget();
if (nextNode instanceof IBigDataNode) {
    final IBigDataNode nextBDNode = (IBigDataNode) nextNode;

    // getRequiredOutputType is null for DummyMap
    if (nextBDNode.getRequiredOutputType() == null) {
        
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(codeGenArgument.getRecordStructName(outgoingConnection));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
    } else {
        
    stringBuffer.append(TEXT_22);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(codeGenArgument.getRecordStructName(outgoingConnection));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_26);
    
    }
} else {
	// tMap is not a BigDataNode and the output rdd still has to be declared properly

    stringBuffer.append(TEXT_27);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(codeGenArgument.getRecordStructName(outgoingConnection));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(outgoingConnection.getName());
    stringBuffer.append(TEXT_31);
    
}

    stringBuffer.append(TEXT_32);
    return stringBuffer.toString();
  }
}
