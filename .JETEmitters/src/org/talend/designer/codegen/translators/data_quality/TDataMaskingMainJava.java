package org.talend.designer.codegen.translators.data_quality;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import org.talend.core.model.utils.NodeUtil;

public class TDataMaskingMainJava
{
  protected static String nl;
  public static synchronized TDataMaskingMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataMaskingMainJava result = new TDataMaskingMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "List<";
  protected final String TEXT_3 = "Struct> ";
  protected final String TEXT_4 = "ReslutList = duplicator_";
  protected final String TEXT_5 = ".process(";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = ");" + NL + "" + NL + "for (";
  protected final String TEXT_8 = "Struct tmpStructMask_";
  protected final String TEXT_9 = " : ";
  protected final String TEXT_10 = "ReslutList)" + NL + "{";
  protected final String TEXT_11 = NL + "    ";
  protected final String TEXT_12 = " = tmpStructMask_";
  protected final String TEXT_13 = ";" + NL;
  protected final String TEXT_14 = NL + "                }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();

    List<Map<String, String>> modifTableList = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__MODIF_TABLE__");
    boolean keep = ("true").equals(ElementParameterParser.getValue(node, "__OUTPUT_ORIGINAL__"));

    Map<String, Map<String, String>> modifTableMap = new HashMap<String, Map<String, String>>();
    for (int i = 0; i < modifTableMap.size(); ++i)
    {
        Map<String, String> modif = modifTableList.get(i);
        String inputColumn = modif.get("INPUT_COLUMN");
        modifTableMap.put(inputColumn, modif);
    }

    String incomingConnName = null;
    IMetadataTable inputMetadataTable = null;
    java.util.List<IMetadataColumn> inputColumns = null;
    List< ? extends IConnection> incomingConnections = node.getIncomingConnections();

    String outgoingConnName = null;
    IMetadataTable outputMetadataTable = null;
    java.util.List<IMetadataColumn> outputColumns = null;
    List< ? extends IConnection> outgoingConnections = node.getOutgoingConnections();

if (incomingConnections != null && !incomingConnections.isEmpty()) {    
        for (IConnection conn : incomingConnections) {
            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                INode preNode = conn.getSource();
                while(NodeUtil.isDataAutoPropagated(preNode)&&!preNode.isSubProcessStart()) {     
                   List<? extends IConnection> preincomingConnections = preNode.getIncomingConnections();
                   if (preincomingConnections != null && !preincomingConnections.isEmpty()) {    
                      for (IConnection preNodeConn : preincomingConnections) {
                         conn=preNodeConn;
                         preNode=preNodeConn.getSource();
                         break;
                      }
                   } else {
                      break;
                   }
                }
                incomingConnName = conn.getName();              
                inputMetadataTable = conn.getMetadataTable();
                inputColumns = inputMetadataTable.getListColumns();
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

    stringBuffer.append(TEXT_2);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(keep);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
    if (node.getOutgoingConnections() == null || node.getOutgoingConnections().size() == 0)
        {
            
    stringBuffer.append(TEXT_14);
    
        }

    return stringBuffer.toString();
  }
}
