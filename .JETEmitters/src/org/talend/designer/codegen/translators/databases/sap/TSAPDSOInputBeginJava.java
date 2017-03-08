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

public class TSAPDSOInputBeginJava
{
  protected static String nl;
  public static synchronized TSAPDSOInputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSAPDSOInputBeginJava result = new TSAPDSOInputBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t" + NL + "" + NL + "\t";
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
  protected final String TEXT_56 = NL + "\torg.talend.sap.service.ISAPBWService dataService_";
  protected final String TEXT_57 = " = connection_";
  protected final String TEXT_58 = ".getBWService();" + NL + "\t" + NL + "\ttry {" + NL + "\t\t" + NL + "\t\torg.talend.sap.model.bw.request.ISAPDataStoreObjectRequest request_";
  protected final String TEXT_59 = " = dataService_";
  protected final String TEXT_60 = ".createDataStoreObjectRequest(";
  protected final String TEXT_61 = ")";
  protected final String TEXT_62 = NL + "\t\t\t.addField(\"";
  protected final String TEXT_63 = "\")";
  protected final String TEXT_64 = NL + "\t\t;" + NL + "\t\t" + NL + "\t\tint fetchSize_";
  protected final String TEXT_65 = " = ";
  protected final String TEXT_66 = ";" + NL + "\t\tint maxRowCount_";
  protected final String TEXT_67 = " = ";
  protected final String TEXT_68 = ";" + NL + "\t\t" + NL + "\t\tif(fetchSize_";
  protected final String TEXT_69 = " > 0) {" + NL + "\t\t\trequest_";
  protected final String TEXT_70 = ".fetchSize(fetchSize_";
  protected final String TEXT_71 = ");" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tif(maxRowCount_";
  protected final String TEXT_72 = " > 0) {" + NL + "\t\t\trequest_";
  protected final String TEXT_73 = ".maxRows(maxRowCount_";
  protected final String TEXT_74 = ");" + NL + "\t\t}" + NL + " \t\t" + NL + " \t\torg.talend.sap.model.table.ISAPTableData data_";
  protected final String TEXT_75 = " = request_";
  protected final String TEXT_76 = ".readable();" + NL + " \t" + NL + "\t \tboolean empty_";
  protected final String TEXT_77 = " = data_";
  protected final String TEXT_78 = ".isEmpty();" + NL + "\t \t" + NL + "\t \tif(!empty_";
  protected final String TEXT_79 = ") {" + NL + "\t \t\tdata_";
  protected final String TEXT_80 = ".nextRow();" + NL + "\t \t}" + NL + "\t \t" + NL + "\t\tdo {" + NL + "\t\t\tif(empty_";
  protected final String TEXT_81 = ") {" + NL + "\t\t\t\tbreak;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t";
  protected final String TEXT_82 = NL + "\t\t\t";
  protected final String TEXT_83 = ".";
  protected final String TEXT_84 = " = data_";
  protected final String TEXT_85 = ".getString(";
  protected final String TEXT_86 = ");";
  protected final String TEXT_87 = NL + "\t\t\t";
  protected final String TEXT_88 = ".";
  protected final String TEXT_89 = " = data_";
  protected final String TEXT_90 = ".getInteger(";
  protected final String TEXT_91 = ");";
  protected final String TEXT_92 = NL + "\t\t\t";
  protected final String TEXT_93 = ".";
  protected final String TEXT_94 = " = data_";
  protected final String TEXT_95 = ".getLong(";
  protected final String TEXT_96 = ");";
  protected final String TEXT_97 = NL + "\t\t\t";
  protected final String TEXT_98 = ".";
  protected final String TEXT_99 = " = data_";
  protected final String TEXT_100 = ".getShort(";
  protected final String TEXT_101 = ");";
  protected final String TEXT_102 = NL + "\t\t\t";
  protected final String TEXT_103 = ".";
  protected final String TEXT_104 = " = data_";
  protected final String TEXT_105 = ".getDate(";
  protected final String TEXT_106 = ");";
  protected final String TEXT_107 = NL + "\t\t\t";
  protected final String TEXT_108 = ".";
  protected final String TEXT_109 = " = data_";
  protected final String TEXT_110 = ".getByte(";
  protected final String TEXT_111 = ");";
  protected final String TEXT_112 = NL + "\t\t\t";
  protected final String TEXT_113 = ".";
  protected final String TEXT_114 = " = data_";
  protected final String TEXT_115 = ".getRaw(";
  protected final String TEXT_116 = ");";
  protected final String TEXT_117 = NL + "\t\t\t";
  protected final String TEXT_118 = ".";
  protected final String TEXT_119 = " = data_";
  protected final String TEXT_120 = ".getDouble(";
  protected final String TEXT_121 = ");";
  protected final String TEXT_122 = NL + "\t\t\t";
  protected final String TEXT_123 = ".";
  protected final String TEXT_124 = " = data_";
  protected final String TEXT_125 = ".getFloat(";
  protected final String TEXT_126 = ");";
  protected final String TEXT_127 = NL + "\t\t\t";
  protected final String TEXT_128 = ".";
  protected final String TEXT_129 = " = new java.math.BigDecimal(data_";
  protected final String TEXT_130 = ".getBigInteger(";
  protected final String TEXT_131 = "));";
  protected final String TEXT_132 = NL + "\t\t\t";
  protected final String TEXT_133 = ".";
  protected final String TEXT_134 = " = data_";
  protected final String TEXT_135 = ".getBigDecimal(";
  protected final String TEXT_136 = ");";
  protected final String TEXT_137 = NL + "\t\t\t";
  protected final String TEXT_138 = ".";
  protected final String TEXT_139 = " = data_";
  protected final String TEXT_140 = ".getBigInteger(";
  protected final String TEXT_141 = ");\t\t";
  protected final String TEXT_142 = NL + "\t\t\t";
  protected final String TEXT_143 = ".";
  protected final String TEXT_144 = " = data_";
  protected final String TEXT_145 = ".getString(";
  protected final String TEXT_146 = ");";
  protected final String TEXT_147 = NL + "\t\t\t";
  protected final String TEXT_148 = ".";
  protected final String TEXT_149 = " = ParserUtils.parseTo_";
  protected final String TEXT_150 = "(data_";
  protected final String TEXT_151 = ".getString(";
  protected final String TEXT_152 = "));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<? extends IConnection> outputConnections = node.getOutgoingSortedConnections();
	if((outputConnections == null) || (outputConnections.size() == 0)) {
		return "";
	}
	IConnection outputConnection = outputConnections.get(0);
	
	if(!outputConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
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
	String filter = ElementParameterParser.getValue(node, "__FILTER__");
	
	String fetchSize = ElementParameterParser.getValue(node, "__FETCH_SIZE__");
	String maxRowCount = ElementParameterParser.getValue(node, "__MAX_ROW_COUNT__");
	
	List<Map<String, String>> sapProps = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__SAP_PROPERTIES__");
	
	String passwordFieldName = "__PASSWORD__";
	
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
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_61);
    
		for(int i=0;i<columnList.size();i++) {
			IMetadataColumn column = columnList.get(i);
			String tableField = column.getOriginalDbColumnName();

    stringBuffer.append(TEXT_62);
    stringBuffer.append(tableField);
    stringBuffer.append(TEXT_63);
    
		}

    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(fetchSize);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(maxRowCount);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    
	for(int i=0;i<columnList.size();i++) {
		IMetadataColumn column = columnList.get(i);
	    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(),column.isNullable());
	    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
	    String dbType = column.getType();
	    
		if(javaType == JavaTypesManager.STRING) {

    stringBuffer.append(TEXT_82);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_86);
    
		} else if(javaType == JavaTypesManager.INTEGER) {

    stringBuffer.append(TEXT_87);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_88);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_91);
    
		} else if(javaType == JavaTypesManager.LONG) {

    stringBuffer.append(TEXT_92);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_96);
    
		} else if(javaType == JavaTypesManager.SHORT) {

    stringBuffer.append(TEXT_97);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_101);
    
		} else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_102);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_106);
    
		} else if(javaType == JavaTypesManager.BYTE) {

    stringBuffer.append(TEXT_107);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_108);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_111);
    
		} else if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_112);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_116);
    
		} else if(javaType == JavaTypesManager.DOUBLE) {

    stringBuffer.append(TEXT_117);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_118);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_121);
    
		} else if(javaType == JavaTypesManager.FLOAT) {

    stringBuffer.append(TEXT_122);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_123);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_126);
    
		} else if(javaType == JavaTypesManager.BIGDECIMAL) {
			if("BIG_INTEGER".equals(dbType)) {

    stringBuffer.append(TEXT_127);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_128);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_131);
    
			} else {

    stringBuffer.append(TEXT_132);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_136);
    
			}
		} else if(javaType == JavaTypesManager.OBJECT && "BIG_INTEGER".equals(dbType)) {

    stringBuffer.append(TEXT_137);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_138);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_141);
    
		} else if(javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_142);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_143);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_146);
    
		} else {

    stringBuffer.append(TEXT_147);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_149);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_152);
    
		}
	}

    return stringBuffer.toString();
  }
}
