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

public class TTransliterateSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TTransliterateSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TTransliterateSparkstreamingcodeJava result = new TTransliterateSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t\t" + NL + "" + NL + "public static class ";
  protected final String TEXT_2 = "transliterateFunction implements org.apache.spark.api.java.function." + NL + "   Function<";
  protected final String TEXT_3 = ", ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "        private ContextProperties context = null;" + NL + "        private Map<String, Boolean> pairList_";
  protected final String TEXT_5 = " = null;" + NL + "" + NL + "        public ";
  protected final String TEXT_6 = "transliterateFunction(JobConf job, Map<String, Boolean> pairList) {" + NL + "            this.context = new ContextProperties(job);" + NL + "            this.pairList_";
  protected final String TEXT_7 = " = pairList;" + NL + "        }" + NL + "" + NL + "" + NL + "" + NL + "\t    @Override" + NL + "\t    public  ";
  protected final String TEXT_8 = " call(";
  protected final String TEXT_9 = " originalStruct) throws java.lang.Exception {" + NL + "" + NL + "\t        ";
  protected final String TEXT_10 = " tmpStruct = new ";
  protected final String TEXT_11 = "();" + NL + "" + NL + "\t        ";
  protected final String TEXT_12 = NL + "\t\t        if (this.pairList_";
  protected final String TEXT_13 = ".get(\"";
  protected final String TEXT_14 = "\")) {" + NL + "\t\t            " + NL + "\t\t            tmpStruct.put(";
  protected final String TEXT_15 = ", gcardone.junidecode.Junidecode.unidecode(originalStruct.";
  protected final String TEXT_16 = "));" + NL + "\t\t        } else {" + NL + "\t\t            tmpStruct.put(";
  protected final String TEXT_17 = ", originalStruct.get(";
  protected final String TEXT_18 = "));" + NL + "\t\t        }" + NL + "\t\t    ";
  protected final String TEXT_19 = NL + "\t        " + NL + "\t        //tmpStruct.setORIGINALMARK(false);" + NL + "\t        " + NL + "\t        return tmpStruct; \t\t" + NL + "\t    }" + NL + "\t    " + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    // Parse the inputs to this javajet generator.
    final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

    final INode node = (INode) codeGenArgument.getArgument();
    final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
    final String cid = node.getUniqueName();
    
    IConnection inConn = node.getIncomingConnections().get(0);
    String inConnTypeName = codeGenArgument.getRecordStructName(inConn);
    IConnection outConn = node.getOutgoingConnections().get(0);
    String outConnTypeName = codeGenArgument.getRecordStructName(outConn);

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
    

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_11);
    
	        for(int i = 0; i < inputColumns.size(); i++) {
	            IMetadataColumn column = inputColumns.get(i);
	            String colLabel=column.getLabel();
	        
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(colLabel);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(colLabel);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_18);
    
	          }
	        
    stringBuffer.append(TEXT_19);
    return stringBuffer.toString();
  }
}
