package org.talend.designer.codegen.translators.data_quality;

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

public class TDataMaskingSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TDataMaskingSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataMaskingSparkstreamingcodeJava result = new TDataMaskingSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t\t" + NL + "" + NL + "public static class ";
  protected final String TEXT_2 = "maskFunction implements org.apache.spark.api.java.function." + NL + "   \t";
  protected final String TEXT_3 = "FlatMapFunction" + NL + "   \t";
  protected final String TEXT_4 = "Function" + NL + "   \t";
  protected final String TEXT_5 = "<";
  protected final String TEXT_6 = ", ";
  protected final String TEXT_7 = "> {" + NL + "" + NL + "        private ContextProperties context = null;" + NL + "        private Map<Integer, org.talend.dataquality.datamasking.Function> pairList_";
  protected final String TEXT_8 = " = null;" + NL + "" + NL + "        public ";
  protected final String TEXT_9 = "maskFunction(JobConf job, Map<Integer, org.talend.dataquality.datamasking.Function> pairList) {" + NL + "            this.context = new ContextProperties(job);" + NL + "            this.pairList_";
  protected final String TEXT_10 = " = pairList;" + NL + "        }" + NL;
  protected final String TEXT_11 = NL + "        @Override" + NL + "        public List<";
  protected final String TEXT_12 = "> call(";
  protected final String TEXT_13 = " originalStruct) throws java.lang.Exception {" + NL + "            List<";
  protected final String TEXT_14 = "> resList = new ArrayList<>();" + NL + "            ";
  protected final String TEXT_15 = NL + "            ";
  protected final String TEXT_16 = " original = new ";
  protected final String TEXT_17 = "();";
  protected final String TEXT_18 = NL + "            ";
  protected final String TEXT_19 = " modified = new ";
  protected final String TEXT_20 = "();" + NL + "            ";
  protected final String TEXT_21 = "      " + NL + "                original.put(";
  protected final String TEXT_22 = ", originalStruct.get(";
  protected final String TEXT_23 = "));" + NL + "                " + NL + "                if (this.pairList_";
  protected final String TEXT_24 = ".get(";
  protected final String TEXT_25 = ") != null) {" + NL + "                    modified.put(";
  protected final String TEXT_26 = ", this.pairList_";
  protected final String TEXT_27 = ".get(";
  protected final String TEXT_28 = ").generateMaskedRow(originalStruct.";
  protected final String TEXT_29 = "));" + NL + "                } else {" + NL + "                    modified.put(";
  protected final String TEXT_30 = ", originalStruct.get(";
  protected final String TEXT_31 = "));" + NL + "                }";
  protected final String TEXT_32 = NL + "            " + NL + "            original.setORIGINALMARK(true);" + NL + "            modified.setORIGINALMARK(false);" + NL + "            " + NL + "            resList.add(original);" + NL + "            resList.add(modified);" + NL + "            " + NL + "            return resList;" + NL + "        }";
  protected final String TEXT_33 = NL + NL + "\t    @Override" + NL + "\t    public  ";
  protected final String TEXT_34 = " call(";
  protected final String TEXT_35 = " originalStruct) throws java.lang.Exception {" + NL + "" + NL + "\t        ";
  protected final String TEXT_36 = " tmpStruct = new ";
  protected final String TEXT_37 = "();" + NL + "" + NL + "\t        ";
  protected final String TEXT_38 = NL + "\t\t        if (this.pairList_";
  protected final String TEXT_39 = ".get(";
  protected final String TEXT_40 = ") != null) {" + NL + "\t\t            tmpStruct.put(";
  protected final String TEXT_41 = ", this.pairList_";
  protected final String TEXT_42 = ".get(";
  protected final String TEXT_43 = ").generateMaskedRow(originalStruct.";
  protected final String TEXT_44 = "));" + NL + "\t\t        } else {" + NL + "\t\t            tmpStruct.put(";
  protected final String TEXT_45 = ", originalStruct.get(";
  protected final String TEXT_46 = "));" + NL + "\t\t        }" + NL + "\t\t    ";
  protected final String TEXT_47 = NL + "\t        " + NL + "\t        tmpStruct.setORIGINALMARK(false);" + NL + "\t        " + NL + "\t        return tmpStruct; \t\t" + NL + "\t    }" + NL + "\t    ";
  protected final String TEXT_48 = NL + "}";

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
    
    boolean keepOriginal = ("true").equals(ElementParameterParser.getValue(node, "__OUTPUT_ORIGINAL__"));

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
   	    if (keepOriginal) {
   	
    stringBuffer.append(TEXT_3);
    
   	    } else {
   	
    stringBuffer.append(TEXT_4);
    
   	    }
    
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
    if (keepOriginal) {

    stringBuffer.append(TEXT_11);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_20);
     
            for(int i = 0; i < inputColumns.size(); i++) {          
                IMetadataColumn column = inputColumns.get(i);
            
    stringBuffer.append(TEXT_21);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_31);
    
            }
            
    stringBuffer.append(TEXT_32);
    
    } else {

    stringBuffer.append(TEXT_33);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_37);
    
	        for(int i = 0; i < inputColumns.size(); i++) {
	            IMetadataColumn column = inputColumns.get(i);
	        
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_46);
    
	          }
	        
    stringBuffer.append(TEXT_47);
    
    }

    stringBuffer.append(TEXT_48);
    return stringBuffer.toString();
  }
}
