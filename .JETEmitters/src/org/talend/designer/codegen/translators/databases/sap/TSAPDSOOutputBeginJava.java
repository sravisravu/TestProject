package org.talend.designer.codegen.translators.databases.sap;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.utils.TalendTextUtils;

public class TSAPDSOOutputBeginJava
{
  protected static String nl;
  public static synchronized TSAPDSOOutputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSAPDSOOutputBeginJava result = new TSAPDSOOutputBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "\t";
  protected final String TEXT_3 = "\t    " + NL + "\t\torg.talend.sap.ISAPConnection connection_";
  protected final String TEXT_4 = " = (org.talend.sap.ISAPConnection)globalMap.get(\"conn_";
  protected final String TEXT_5 = "\");\t";
  protected final String TEXT_6 = NL + "\t\t\t\tif(connection_";
  protected final String TEXT_7 = " == null){" + NL + "\t\t\t\t\tconnection_";
  protected final String TEXT_8 = " = ((org.talend.sap.impl.SAPConnectionFactory)(org.talend.sap.impl.SAPConnectionFactory.getInstance())).createConnection(";
  protected final String TEXT_9 = ");" + NL + "\t\t\t\t}";
  protected final String TEXT_10 = NL + "\t";
  protected final String TEXT_11 = NL + "\t\torg.talend.sap.ISAPConnection connection_";
  protected final String TEXT_12 = " = null;";
  protected final String TEXT_13 = NL + "\t\t\t\tconnection_";
  protected final String TEXT_14 = " = ((org.talend.sap.impl.SAPConnectionFactory)(org.talend.sap.impl.SAPConnectionFactory.getInstance())).createConnection(";
  protected final String TEXT_15 = ");";
  protected final String TEXT_16 = NL + "\t\t\tif (connection_";
  protected final String TEXT_17 = " == null) {//}";
  protected final String TEXT_18 = NL + "\t\t";
  protected final String TEXT_19 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_20 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_21 = ");";
  protected final String TEXT_22 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_23 = " = ";
  protected final String TEXT_24 = "; ";
  protected final String TEXT_25 = NL + "    \tjava.util.Properties properties_";
  protected final String TEXT_26 = " = new java.util.Properties();" + NL + "        properties_";
  protected final String TEXT_27 = ".put(org.talend.sap.ISAPConnection.PROP_CLIENT, ";
  protected final String TEXT_28 = ");" + NL + "        properties_";
  protected final String TEXT_29 = ".put(org.talend.sap.ISAPConnection.PROP_USER, ";
  protected final String TEXT_30 = ");" + NL + "        properties_";
  protected final String TEXT_31 = ".put(org.talend.sap.ISAPConnection.PROP_PASSWORD, decryptedPassword_";
  protected final String TEXT_32 = ");" + NL + "        properties_";
  protected final String TEXT_33 = ".put(org.talend.sap.ISAPConnection.PROP_LANGUAGE, ";
  protected final String TEXT_34 = ");" + NL + "        ";
  protected final String TEXT_35 = NL + "        properties_";
  protected final String TEXT_36 = ".put(org.talend.sap.ISAPConnection.PROP_APPLICATION_SERVER_HOST, ";
  protected final String TEXT_37 = ");" + NL + "        properties_";
  protected final String TEXT_38 = ".put(org.talend.sap.ISAPConnection.PROP_SYSTEM_NUMBER, ";
  protected final String TEXT_39 = ");";
  protected final String TEXT_40 = NL + "        properties_";
  protected final String TEXT_41 = ".put(\"jco.client.mshost\", ";
  protected final String TEXT_42 = ");" + NL + "        properties_";
  protected final String TEXT_43 = ".put(\"jco.client.r3name\", ";
  protected final String TEXT_44 = ");" + NL + "        properties_";
  protected final String TEXT_45 = ".put(\"jco.client.group\", ";
  protected final String TEXT_46 = ");";
  protected final String TEXT_47 = NL + "        " + NL + "    \t";
  protected final String TEXT_48 = NL + "    \t\tproperties_";
  protected final String TEXT_49 = ".put(";
  protected final String TEXT_50 = " ,";
  protected final String TEXT_51 = ");" + NL + "    \t\t";
  protected final String TEXT_52 = NL + "        " + NL + "    \tconnection_";
  protected final String TEXT_53 = " = org.talend.sap.impl.SAPConnectionFactory.getInstance().createConnection(properties_";
  protected final String TEXT_54 = ");";
  protected final String TEXT_55 = NL + "\t\t\t//{" + NL + "\t\t\t}";
  protected final String TEXT_56 = NL + "\tresourceMap.put(\"conn_";
  protected final String TEXT_57 = "\", connection_";
  protected final String TEXT_58 = ");" + NL + "\t" + NL + "\torg.talend.sap.service.ISAPBWService dataService_";
  protected final String TEXT_59 = " = connection_";
  protected final String TEXT_60 = ".getBWService();" + NL + "\t" + NL + "\torg.talend.sap.bw.ISAPBWTableDataWritable data_";
  protected final String TEXT_61 = " = dataService_";
  protected final String TEXT_62 = ".createDataStoreObjectRequest(";
  protected final String TEXT_63 = ")";
  protected final String TEXT_64 = NL + "\t\t.addField(\"";
  protected final String TEXT_65 = "\")";
  protected final String TEXT_66 = NL + "\t\t.modeUpdate()";
  protected final String TEXT_67 = NL + "\t\t.modeUpsert()";
  protected final String TEXT_68 = "\t" + NL + "\t\t.writable();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<? extends IConnection> inputConnections = node.getIncomingConnections();
	if((inputConnections == null) || (inputConnections.size() == 0)) {
		return "";
	}
	
	IConnection inputConnection = null;
	for(IConnection inputConn : inputConnections) {
		if(inputConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			inputConnection = inputConn;
			break;
		}
	}
	
	if(inputConnection == null) {
		return "";
	}
	
	List<IMetadataTable> metadatas = node.getMetadataList();
	if ((metadatas == null) && (metadatas.size() == 0) || (metadatas.get(0) == null)) {
		return "";
	}
	IMetadataTable metadata = metadatas.get(0);
	
	List<IMetadataColumn> columnList = metadata.getListColumns();
	if((columnList == null) || (columnList.size() == 0)) {
		return "";
	}
	
	String client = ElementParameterParser.getValue(node, "__CLIENT__");
	String userid = ElementParameterParser.getValue(node, "__USERID__");
	String password = ElementParameterParser.getValue(node, "__PASSWORD__");
	String language = ElementParameterParser.getValue(node, "__LANGUAGE__");
	String hostname = ElementParameterParser.getValue(node, "__HOSTNAME__");
	String systemnumber = ElementParameterParser.getValue(node, "__SYSTEMNUMBER__");
	
	String systemId = ElementParameterParser.getValue(node,"__SYSTEMID__");
	String groupName = ElementParameterParser.getValue(node,"__GROUPNAME__");
	
	String serverType = ElementParameterParser.getValue(node,"__SERVERTYPE__");
	
	String tableName = ElementParameterParser.getValue(node, "__TABLE__");
	
	List<Map<String, String>> sapProps = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__SAP_PROPERTIES__");
	
	String passwordFieldName = "__PASSWORD__";
	
	String data_action = ElementParameterParser.getValue(node, "__DATA_ACTION__");
	
    boolean useExistingConn = ("true").equals(ElementParameterParser.getValue(node,"__USE_EXISTING_CONNECTION__"));
	String connection = ElementParameterParser.getValue(node,"__CONNECTION__");

    stringBuffer.append(TEXT_2);
    if(useExistingConn){
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_5);
    	
		INode connectionNode = null; 
		for (INode processNode : node.getProcess().getGeneratingNodes()) { 
			if(connection.equals(processNode.getUniqueName())) { 
				connectionNode = processNode; 
				break; 
			} 
		} 
		boolean specify_alias = "true".equals(ElementParameterParser.getValue(connectionNode, "__SPECIFY_DATASOURCE_ALIAS__"));
		if(specify_alias){
			String alias = ElementParameterParser.getValue(connectionNode, "__SAP_DATASOURCE_ALIAS__");
			if(null != alias && !("".equals(alias))){

    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(alias);
    stringBuffer.append(TEXT_9);
    
			}
		}

    stringBuffer.append(TEXT_10);
    }else{
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
		boolean specify_alias = "true".equals(ElementParameterParser.getValue(node, "__SPECIFY_DATASOURCE_ALIAS__"));
		if(specify_alias){
			String alias = ElementParameterParser.getValue(node, "__SAP_DATASOURCE_ALIAS__");
			if(null != alias && !("".equals(alias))){

    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(alias);
    stringBuffer.append(TEXT_15);
    
			}

    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
		}

    stringBuffer.append(TEXT_18);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_21);
    } else {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(client);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(userid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(language);
    stringBuffer.append(TEXT_34);
    if("ApplicationServer".equals(serverType)){
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(hostname);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(systemnumber);
    stringBuffer.append(TEXT_39);
    }else{
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(hostname);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(systemId);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(groupName);
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    
        if(sapProps!=null) {
    		for(Map<String, String> item : sapProps){
    		
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(item.get("PROPERTY") );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(item.get("VALUE") );
    stringBuffer.append(TEXT_51);
     
    		}
        }
		
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    
		if(specify_alias){

    stringBuffer.append(TEXT_55);
    
		}
	}

    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_63);
    
	for(int i=0;i<columnList.size();i++) {
		IMetadataColumn column = columnList.get(i);
		String tableField = column.getOriginalDbColumnName();

    stringBuffer.append(TEXT_64);
    stringBuffer.append(tableField);
    stringBuffer.append(TEXT_65);
    
	}
	
	if("INSERT".equals(data_action)) {
		//do nothing
	} else if("UPDATE".equals(data_action)) {

    stringBuffer.append(TEXT_66);
    
	} else if("UPSERT".equals(data_action)) {

    stringBuffer.append(TEXT_67);
    
	}

    stringBuffer.append(TEXT_68);
    return stringBuffer.toString();
  }
}
