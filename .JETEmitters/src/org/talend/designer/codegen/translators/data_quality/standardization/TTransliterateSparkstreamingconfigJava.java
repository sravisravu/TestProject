package org.talend.designer.codegen.translators.data_quality.standardization;

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
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TTransliterateSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TTransliterateSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TTransliterateSparkstreamingconfigJava result = new TTransliterateSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "Map<String, Boolean> pairList_";
  protected final String TEXT_4 = " = new java.util.HashMap<String, Boolean>();" + NL;
  protected final String TEXT_5 = NL + "\t            //need to judge true or false by the code auto" + NL + "\t            pairList_";
  protected final String TEXT_6 = ".put(\"";
  protected final String TEXT_7 = "\", true);";
  protected final String TEXT_8 = NL + "\t            pairList_";
  protected final String TEXT_9 = ".put(\"";
  protected final String TEXT_10 = "\", false);";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + "    org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_13 = "> rdd_";
  protected final String TEXT_14 = " = " + NL + "        rdd_";
  protected final String TEXT_15 = ".map(new ";
  protected final String TEXT_16 = "transliterateFunction(job, pairList_";
  protected final String TEXT_17 = "));" + NL;
  protected final String TEXT_18 = NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_19 = "> rdd_";
  protected final String TEXT_20 = " = " + NL + "        rdd_";
  protected final String TEXT_21 = ".map(new ";
  protected final String TEXT_22 = "transliterateFunction(job, pairList_";
  protected final String TEXT_23 = "));" + NL;
  protected final String TEXT_24 = NL + "        ";
  protected final String TEXT_25 = NL + "        ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
List<Map<String, String>> transliterate = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__TABLE_CONFIG__");


    stringBuffer.append(TEXT_2);
    

IConnection outConn = node.getOutgoingConnections().get(0);
String outConnTypeName = codeGenArgument.getRecordStructName(outConn);

String requiredInputType = bigDataNode.getRequiredInputType();
String requiredOutputType = bigDataNode.getRequiredOutputType();
String incomingType = bigDataNode.getIncomingType();
String outgoingType = bigDataNode.getOutgoingType();

String incomingConnName = null;
IMetadataTable inputMetadateTable = null;
java.util.List<IMetadataColumn> inputColumns = null;
List< ? extends IConnection> incomingConnections = node.getIncomingConnections();

String outgoingConnName = null;
IMetadataTable outputMetadataTable = null;
java.util.List<IMetadataColumn> outputColumns = null;
List< ? extends IConnection> outgoingConnections = node.getOutgoingConnections();

if (incomingConnections != null && !incomingConnections.isEmpty())
{
    for (IConnection conn : incomingConnections)
    {
        if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
        {
            incomingConnName = conn.getName();
            inputMetadateTable = conn.getMetadataTable();
            inputColumns = inputMetadateTable.getListColumns();
            break;
        }
    }
}
if (outgoingConnections != null && !outgoingConnections.isEmpty())
{
    for (IConnection conn : outgoingConnections)
    {
        if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
        {
            outgoingConnName = conn.getName();
            outputMetadataTable = conn.getMetadataTable();
            outputColumns = outputMetadataTable.getListColumns();
            break;
        }
    }
}

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    
        Map<String, Boolean> mapColTrans = new HashMap<String, Boolean>();
        for (Map<String, String> mapCol : transliterate){
            String columnName = mapCol.get("SCHEMA_COLUMN");
            boolean isTransliterate = "true".equals(mapCol.get("COLUMNS_TRANS"));
            if(isTransliterate){

    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_7);
    
            }else{

    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_10);
    
            }
        }

    stringBuffer.append(TEXT_11);
    
if("SPARKSTREAMING".equals(node.getComponent().getType())
    && !org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node)){

    stringBuffer.append(TEXT_12);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
}else{

    stringBuffer.append(TEXT_18);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
}

    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
