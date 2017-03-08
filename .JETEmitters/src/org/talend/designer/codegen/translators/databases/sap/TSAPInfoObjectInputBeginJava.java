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

public class TSAPInfoObjectInputBeginJava
{
  protected static String nl;
  public static synchronized TSAPInfoObjectInputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSAPInfoObjectInputBeginJava result = new TSAPInfoObjectInputBeginJava();
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
  protected final String TEXT_58 = ".getBWService();" + NL + "\t" + NL + "\ttry {";
  protected final String TEXT_59 = NL + "\t\torg.talend.sap.model.table.ISAPTableData data_";
  protected final String TEXT_60 = " = dataService_";
  protected final String TEXT_61 = NL + "\t\t";
  protected final String TEXT_62 = NL + "\t\t\t.createInfoObjectAttributeRequest(";
  protected final String TEXT_63 = ")" + NL + "\t\t";
  protected final String TEXT_64 = NL + "\t\t\t.createInfoObjectHierarchyRequest(";
  protected final String TEXT_65 = ")" + NL + "\t\t";
  protected final String TEXT_66 = NL + "\t\t\t.createInfoObjectTextRequest(";
  protected final String TEXT_67 = ")" + NL + "\t\t";
  protected final String TEXT_68 = NL + "\t\t\t.addField(\"";
  protected final String TEXT_69 = "\")";
  protected final String TEXT_70 = NL + "\t\t.readable();" + NL + "\t\t" + NL + "\t \tboolean empty_";
  protected final String TEXT_71 = " = data_";
  protected final String TEXT_72 = ".isEmpty();" + NL + "\t \t" + NL + "\t \tif(!empty_";
  protected final String TEXT_73 = ") {" + NL + "\t \t\tdata_";
  protected final String TEXT_74 = ".firstRow();" + NL + "\t \t}" + NL + "\t \t" + NL + "\t\tdo {" + NL + "\t\t\tif(empty_";
  protected final String TEXT_75 = ") {" + NL + "\t\t\t\tbreak;" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t";
  protected final String TEXT_76 = NL + "\t\t\t";
  protected final String TEXT_77 = ".";
  protected final String TEXT_78 = " = data_";
  protected final String TEXT_79 = ".getString(";
  protected final String TEXT_80 = "\"";
  protected final String TEXT_81 = "\"";
  protected final String TEXT_82 = ");";
  protected final String TEXT_83 = NL + "\t\t\t";
  protected final String TEXT_84 = ".";
  protected final String TEXT_85 = " = data_";
  protected final String TEXT_86 = ".getInteger(";
  protected final String TEXT_87 = "\"";
  protected final String TEXT_88 = "\"";
  protected final String TEXT_89 = ");";
  protected final String TEXT_90 = NL + "\t\t\t";
  protected final String TEXT_91 = ".";
  protected final String TEXT_92 = " = data_";
  protected final String TEXT_93 = ".getLong(";
  protected final String TEXT_94 = "\"";
  protected final String TEXT_95 = "\"";
  protected final String TEXT_96 = ");";
  protected final String TEXT_97 = NL + "\t\t\t";
  protected final String TEXT_98 = ".";
  protected final String TEXT_99 = " = data_";
  protected final String TEXT_100 = ".getShort(";
  protected final String TEXT_101 = "\"";
  protected final String TEXT_102 = "\"";
  protected final String TEXT_103 = ");";
  protected final String TEXT_104 = NL + "\t\t\t";
  protected final String TEXT_105 = ".";
  protected final String TEXT_106 = " = data_";
  protected final String TEXT_107 = ".getDate(";
  protected final String TEXT_108 = "\"";
  protected final String TEXT_109 = "\"";
  protected final String TEXT_110 = ");";
  protected final String TEXT_111 = NL + "\t\t\t";
  protected final String TEXT_112 = ".";
  protected final String TEXT_113 = " = data_";
  protected final String TEXT_114 = ".getByte(";
  protected final String TEXT_115 = "\"";
  protected final String TEXT_116 = "\"";
  protected final String TEXT_117 = ");";
  protected final String TEXT_118 = NL + "\t\t\t";
  protected final String TEXT_119 = ".";
  protected final String TEXT_120 = " = data_";
  protected final String TEXT_121 = ".getRaw(";
  protected final String TEXT_122 = "\"";
  protected final String TEXT_123 = "\"";
  protected final String TEXT_124 = ");";
  protected final String TEXT_125 = NL + "\t\t\t";
  protected final String TEXT_126 = ".";
  protected final String TEXT_127 = " = data_";
  protected final String TEXT_128 = ".getDouble(";
  protected final String TEXT_129 = "\"";
  protected final String TEXT_130 = "\"";
  protected final String TEXT_131 = ");";
  protected final String TEXT_132 = NL + "\t\t\t";
  protected final String TEXT_133 = ".";
  protected final String TEXT_134 = " = data_";
  protected final String TEXT_135 = ".getFloat(";
  protected final String TEXT_136 = "\"";
  protected final String TEXT_137 = "\"";
  protected final String TEXT_138 = ");";
  protected final String TEXT_139 = NL + "\t\t\t";
  protected final String TEXT_140 = ".";
  protected final String TEXT_141 = " = new java.math.BigDecimal(data_";
  protected final String TEXT_142 = ".getBigInteger(";
  protected final String TEXT_143 = "\"";
  protected final String TEXT_144 = "\"";
  protected final String TEXT_145 = "));";
  protected final String TEXT_146 = NL + "\t\t\t";
  protected final String TEXT_147 = ".";
  protected final String TEXT_148 = " = data_";
  protected final String TEXT_149 = ".getBigDecimal(";
  protected final String TEXT_150 = "\"";
  protected final String TEXT_151 = "\"";
  protected final String TEXT_152 = ");";
  protected final String TEXT_153 = NL + "\t\t\t";
  protected final String TEXT_154 = ".";
  protected final String TEXT_155 = " = data_";
  protected final String TEXT_156 = ".getBigInteger(";
  protected final String TEXT_157 = "\"";
  protected final String TEXT_158 = "\"";
  protected final String TEXT_159 = ");\t\t";
  protected final String TEXT_160 = NL + "\t\t\t";
  protected final String TEXT_161 = ".";
  protected final String TEXT_162 = " = data_";
  protected final String TEXT_163 = ".getString(";
  protected final String TEXT_164 = "\"";
  protected final String TEXT_165 = "\"";
  protected final String TEXT_166 = ");";
  protected final String TEXT_167 = NL + "\t\t\t";
  protected final String TEXT_168 = ".";
  protected final String TEXT_169 = " = ParserUtils.parseTo_";
  protected final String TEXT_170 = "(data_";
  protected final String TEXT_171 = ".getString(";
  protected final String TEXT_172 = "\"";
  protected final String TEXT_173 = "\"";
  protected final String TEXT_174 = "));";

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
    
		String info_object_type = ElementParameterParser.getValue(node, "__INFO_OBJECT_TYPE__");

    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    if("ATTRIBUTE".equals(info_object_type)) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_63);
    } else if("HIERARCHY".equals(info_object_type)) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_67);
    }
    
		for(int i=0;i<columnList.size();i++) {
			IMetadataColumn column = columnList.get(i);
			String tableField = column.getOriginalDbColumnName();

    stringBuffer.append(TEXT_68);
    stringBuffer.append(tableField);
    stringBuffer.append(TEXT_69);
    
		}

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
    
	boolean hierarchy = "HIERARCHY".equals(info_object_type);
	for(int i=0;i<columnList.size();i++) {
		IMetadataColumn column = columnList.get(i);
	    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(),column.isNullable());
	    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
	    String dbType = column.getType();
	    
	    String columnName = column.getOriginalDbColumnName();
	    
		if(javaType == JavaTypesManager.STRING) {

    stringBuffer.append(TEXT_76);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    if(hierarchy) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_81);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_82);
    
		} else if(javaType == JavaTypesManager.INTEGER) {

    stringBuffer.append(TEXT_83);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    if(hierarchy) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_88);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_89);
    
		} else if(javaType == JavaTypesManager.LONG) {

    stringBuffer.append(TEXT_90);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    if(hierarchy) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_95);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_96);
    
		} else if(javaType == JavaTypesManager.SHORT) {

    stringBuffer.append(TEXT_97);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    if(hierarchy) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_102);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_103);
    
		} else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_104);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    if(hierarchy) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_109);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_110);
    
		} else if(javaType == JavaTypesManager.BYTE) {

    stringBuffer.append(TEXT_111);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    if(hierarchy) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_116);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_117);
    
		} else if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_118);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    if(hierarchy) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_123);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_124);
    
		} else if(javaType == JavaTypesManager.DOUBLE) {

    stringBuffer.append(TEXT_125);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_126);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    if(hierarchy) {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_131);
    
		} else if(javaType == JavaTypesManager.FLOAT) {

    stringBuffer.append(TEXT_132);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    if(hierarchy) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_137);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_138);
    
		} else if(javaType == JavaTypesManager.BIGDECIMAL) {
			if("BIG_INTEGER".equals(dbType)) {

    stringBuffer.append(TEXT_139);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_140);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    if(hierarchy) {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_144);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_145);
    
			} else {

    stringBuffer.append(TEXT_146);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_147);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    if(hierarchy) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_152);
    
			}
		} else if(javaType == JavaTypesManager.OBJECT && "BIG_INTEGER".equals(dbType)) {

    stringBuffer.append(TEXT_153);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_154);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    if(hierarchy) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_159);
    
		} else if(javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_160);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_161);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    if(hierarchy) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_165);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_166);
    
		} else {

    stringBuffer.append(TEXT_167);
    stringBuffer.append(outputConnection.getName() );
    stringBuffer.append(TEXT_168);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_169);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    if(hierarchy) {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_173);
    } else {
    stringBuffer.append(i);
    }
    stringBuffer.append(TEXT_174);
    
		}
	}

    return stringBuffer.toString();
  }
}
