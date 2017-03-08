package org.talend.designer.codegen.translators.data_quality.standardization;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.BlockCode;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class TSynonymSearchMainJava
{
  protected static String nl;
  public static synchronized TSynonymSearchMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSynonymSearchMainJava result = new TSynonymSearchMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tinputs_";
  protected final String TEXT_2 = " = new String[uniqueIndexNum_";
  protected final String TEXT_3 = "];" + NL + "\t";
  protected final String TEXT_4 = NL + "\t\t\tinputs_";
  protected final String TEXT_5 = "[";
  protected final String TEXT_6 = "] = ";
  protected final String TEXT_7 = ".";
  protected final String TEXT_8 = ";" + NL + "\t\t\t";
  protected final String TEXT_9 = NL + "\tList<org.talend.dataquality.standardization.record.OutputRecord> output_";
  protected final String TEXT_10 = " = null;" + NL + "\t" + NL + "\ttry {" + NL + "\t\toutput_";
  protected final String TEXT_11 = " = recSearch_";
  protected final String TEXT_12 = ".search(";
  protected final String TEXT_13 = ", inputs_";
  protected final String TEXT_14 = ");" + NL + "\t} catch (NullPointerException e) {" + NL + "\t\terror_";
  protected final String TEXT_15 = ".set(false, \"Parse exception, field value is NULL\");" + NL + "\t} catch (java.io.IOException e) {" + NL + "\t\terror_";
  protected final String TEXT_16 = ".set(false, \"IO exception\");" + NL + "\t}" + NL + "\tboolean forceIn_";
  protected final String TEXT_17 = " = false;" + NL + "\t" + NL + "\tif (!error_";
  protected final String TEXT_18 = ".getStatus()) {" + NL + "\t\tforceIn_";
  protected final String TEXT_19 = " = true;" + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_20 = NL + "\t\t\tif (";
  protected final String TEXT_21 = " == null) {" + NL + "\t\t\t\t";
  protected final String TEXT_22 = " = new ";
  protected final String TEXT_23 = "Struct();" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_24 = NL + "\t\t\t\t\t";
  protected final String TEXT_25 = ".";
  protected final String TEXT_26 = " = ";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = ";" + NL + "\t\t\t\t";
  protected final String TEXT_29 = NL + "\t\t\t";
  protected final String TEXT_30 = ".error_message = error_";
  protected final String TEXT_31 = ".getMessage();" + NL + "\t\t";
  protected final String TEXT_32 = NL + "\t\t\t";
  protected final String TEXT_33 = " = null;" + NL + "\t\t";
  protected final String TEXT_34 = NL + "\t}" + NL + "\terror_";
  protected final String TEXT_35 = ".reset();" + NL + "\t" + NL + "\tif (output_";
  protected final String TEXT_36 = " != null && !output_";
  protected final String TEXT_37 = ".isEmpty()) gid_";
  protected final String TEXT_38 = "++;" + NL + "\t" + NL + "\tfor (int i_";
  protected final String TEXT_39 = " = 0; (output_";
  protected final String TEXT_40 = " != null && i_";
  protected final String TEXT_41 = " < output_";
  protected final String TEXT_42 = ".size()) || forceIn_";
  protected final String TEXT_43 = ";) {// C_01" + NL + "\t\torg.talend.dataquality.standardization.record.OutputRecord outputRecord_";
  protected final String TEXT_44 = ";" + NL + "\t\t" + NL + "\t\tif (forceIn_";
  protected final String TEXT_45 = ") {" + NL + "\t\t\toutputRecord_";
  protected final String TEXT_46 = " = null;" + NL + "\t\t\tforceIn_";
  protected final String TEXT_47 = " = false;" + NL + "\t\t} else {" + NL + "\t\t\toutputRecord_";
  protected final String TEXT_48 = " = output_";
  protected final String TEXT_49 = ".get(i_";
  protected final String TEXT_50 = ");" + NL + "\t\t\t";
  protected final String TEXT_51 = " ";
  protected final String TEXT_52 = " = null;";
  protected final String TEXT_53 = NL + "\t\t\ti_";
  protected final String TEXT_54 = "++;" + NL + "\t\t} " + NL + "\t\t" + NL + "\t\t";
  protected final String TEXT_55 = NL + "\t\t\tif (outputRecord_";
  protected final String TEXT_56 = " != null) {" + NL + "\t\t\t\tif (";
  protected final String TEXT_57 = " == null) ";
  protected final String TEXT_58 = " = new ";
  protected final String TEXT_59 = "Struct(); " + NL + "\t\t\t\t";
  protected final String TEXT_60 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_61 = ".";
  protected final String TEXT_62 = " = ";
  protected final String TEXT_63 = ".";
  protected final String TEXT_64 = ";" + NL + "\t\t\t\t\t";
  protected final String TEXT_65 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_66 = ".";
  protected final String TEXT_67 = " = outputRecord_";
  protected final String TEXT_68 = ".getRecord(";
  protected final String TEXT_69 = ");" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_70 = NL + "\t\t\t\t";
  protected final String TEXT_71 = ".GID = gid_";
  protected final String TEXT_72 = ";" + NL + "\t\t\t\t";
  protected final String TEXT_73 = ".GRP_SIZE = output_";
  protected final String TEXT_74 = ".size();" + NL + "\t\t\t\t";
  protected final String TEXT_75 = ".SCORE = outputRecord_";
  protected final String TEXT_76 = ".getScore();" + NL + "\t\t\t\t";
  protected final String TEXT_77 = ".SCORES = outputRecord_";
  protected final String TEXT_78 = ".getScores();";
  protected final String TEXT_79 = NL + "                ";
  protected final String TEXT_80 = ".NB_MATCHED_FIELDS = outputRecord_";
  protected final String TEXT_81 = ".getNbMatch();" + NL + "\t\t\t}" + NL + "\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
IConnection inConn = null; 
IMetadataTable preMetadata; 
List<String> inputFieldsName = new java.util.ArrayList<String>();

// get incoming connection
if (inConns == null || inConns.isEmpty()) {
	return "";
} else {
	inConn = inConns.get(0);
	// to fetch input fields name
	preMetadata = inConn.getMetadataTable();
	List<IMetadataColumn> preColumns = preMetadata.getListColumns();
	
	for (IMetadataColumn preColumn : preColumns) {
		inputFieldsName.add(preColumn.getLabel());
	}
}
String sInConnName = null;

// get incoming connection name
if (inConn == null || !inConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
	return "";
} else {
	sInConnName = inConn.getName();
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
String sOutMainConnName = null, sOutRejectConnName = null;

for (IConnection outConn : outConns) {
	if (outConn.isActivate() && outConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
		String connOutCntorName = outConn.getConnectorName();
		String connOutFlowName = outConn.getName();
		
		if ("REJECT".equals(connOutCntorName)) {
			sOutRejectConnName = connOutFlowName;
		} else {
			sOutMainConnName = connOutFlowName;
		}
	}
}

// if no output flow, without code generated.
if (sOutMainConnName == null && sOutRejectConnName == null) {
	return "";
}
List<IMetadataTable> metadatas = node.getMetadataList();

if ((metadatas != null) && (metadatas.size() > 0)) {
	IMetadataTable metadata = metadatas.get(0);
	List<IMetadataColumn> columns = metadata.getListColumns();
	
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
	List<Map<String, String>> listToSearch = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__SEARCH_INDEXS__");
	String resultLimit = ElementParameterParser.getValue(node, "__LIMIT__"); 
	List<String> uniqueInputName = new java.util.ArrayList<String>();
	List<String> uniqueOutputName = new java.util.ArrayList<String>();
	int i = 0;

	for (Map<String, String> toSearch : listToSearch) {
		String inputName = toSearch.get("PRE_COLUMN");
		String outputName = toSearch.get("COLUMN");
		
		if (!uniqueInputName.contains(inputName)){
			uniqueInputName.add(inputName);
			uniqueOutputName.add(outputName);
			
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(sInConnName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inputName);
    stringBuffer.append(TEXT_8);
    
			i++;
		}
	}
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(resultLimit);
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
    if (sOutRejectConnName != null) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_23);
    
			for (IMetadataColumn column : columns) {
				String columnName = column.getLabel();
				
				if (inputFieldsName.contains(columnName)){
    stringBuffer.append(TEXT_24);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(sInConnName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    
				}
			}
			
    stringBuffer.append(TEXT_29);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
		}
		
		if (sOutMainConnName != null) {
		
    stringBuffer.append(TEXT_32);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    if (sOutRejectConnName != null) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(sOutRejectConnName);
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    if (sOutMainConnName != null) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_59);
    
				for (IMetadataColumn column : columns) {
					String columnName = column.getLabel();
					
					// if the field exists in input schema, set the value with input.
					if (inputFieldsName.contains(columnName)){
    stringBuffer.append(TEXT_60);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(sInConnName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_64);
    } else if (uniqueOutputName.contains(columnName)) { 
						// if the field exists in reference list, set the value with search result.
						int index = uniqueOutputName.indexOf(columnName);
						// String outputFieldName = uniqueOutputName.get(index);
						
    stringBuffer.append(TEXT_65);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_69);
    
					}
				}
				
    stringBuffer.append(TEXT_70);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(sOutMainConnName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    
		}
	List<BlockCode> blockCodes = new java.util.ArrayList<BlockCode>();
	blockCodes.add(new BlockCode("C_01"));
	((org.talend.core.model.process.AbstractNode) node).setBlocksCodeToClose(blockCodes);
}

    return stringBuffer.toString();
  }
}
