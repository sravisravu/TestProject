package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.designer.codegen.config.NodeConnectionsHelper;
import org.talend.designer.codegen.config.NodeParamsHelper;
import org.talend.core.model.metadata.MetadataColumn;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import java.util.List;
import java.util.Iterator;

public class TDataprepInBeginJava
{
  protected static String nl;
  public static synchronized TDataprepInBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataprepInBeginJava result = new TDataprepInBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "com.fasterxml.jackson.databind.ObjectMapper objectMapper_";
  protected final String TEXT_3 = " = new com.fasterxml.jackson.databind.ObjectMapper();" + NL + "com.fasterxml.jackson.core.JsonParser jsonParser_";
  protected final String TEXT_4 = " = null;" + NL + "" + NL + "try {//}" + NL + "jsonParser_";
  protected final String TEXT_5 = " = new com.fasterxml.jackson.core.JsonFactory().createParser(is_";
  protected final String TEXT_6 = ");" + NL + "com.fasterxml.jackson.databind.MappingIterator<java.util.Map<String, String>> iterator_";
  protected final String TEXT_7 = " = null;" + NL + "" + NL + "com.fasterxml.jackson.core.JsonToken currentToken_";
  protected final String TEXT_8 = " = null;" + NL + "while ((currentToken_";
  protected final String TEXT_9 = " = jsonParser_";
  protected final String TEXT_10 = ".nextToken()) != com.fasterxml.jackson.core.JsonToken.END_OBJECT) {" + NL + "    if (currentToken_";
  protected final String TEXT_11 = " == com.fasterxml.jackson.core.JsonToken.START_ARRAY) {" + NL + "        jsonParser_";
  protected final String TEXT_12 = ".nextToken();" + NL + "        iterator_";
  protected final String TEXT_13 = " = objectMapper_";
  protected final String TEXT_14 = ".readValues(" + NL + "        \tjsonParser_";
  protected final String TEXT_15 = ", " + NL + "        \tnew com.fasterxml.jackson.core.type.TypeReference<java.util.Map<String, String>>() {" + NL + "       \t\t}" + NL + "       \t);" + NL + "        break;" + NL + "    }" + NL + "}" + NL + "        " + NL + "while(iterator_";
  protected final String TEXT_16 = "!=null && iterator_";
  protected final String TEXT_17 = ".hasNext()){//}" + NL + "java.util.Map<String, String> record_";
  protected final String TEXT_18 = " = iterator_";
  protected final String TEXT_19 = ".next();";
  protected final String TEXT_20 = NL + "String value_";
  protected final String TEXT_21 = " = null;";
  protected final String TEXT_22 = NL + "\tvalue_";
  protected final String TEXT_23 = " = record_";
  protected final String TEXT_24 = ".get(\"";
  protected final String TEXT_25 = "\");";
  protected final String TEXT_26 = NL + "\t\t";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = " = value_";
  protected final String TEXT_29 = " == null ? ";
  protected final String TEXT_30 = NL;
  protected final String TEXT_31 = NL + "\t\t";
  protected final String TEXT_32 = NL + "\t\t";
  protected final String TEXT_33 = NL + NL + " : value_";
  protected final String TEXT_34 = ";";
  protected final String TEXT_35 = NL + "\t\tif(value_";
  protected final String TEXT_36 = " != null && !value_";
  protected final String TEXT_37 = ".isEmpty()) {";
  protected final String TEXT_38 = NL + "\t\t\t";
  protected final String TEXT_39 = ".";
  protected final String TEXT_40 = " = value_";
  protected final String TEXT_41 = ";";
  protected final String TEXT_42 = NL + "\t\t\t";
  protected final String TEXT_43 = ".";
  protected final String TEXT_44 = " = ParserUtils.parseTo_Date(value_";
  protected final String TEXT_45 = ", ";
  protected final String TEXT_46 = ");";
  protected final String TEXT_47 = NL + "\t\t\t";
  protected final String TEXT_48 = ".";
  protected final String TEXT_49 = " = value_";
  protected final String TEXT_50 = ".getBytes();";
  protected final String TEXT_51 = NL + "\t\t\t";
  protected final String TEXT_52 = ".";
  protected final String TEXT_53 = " = ParserUtils.parseTo_";
  protected final String TEXT_54 = "(value_";
  protected final String TEXT_55 = ");";
  protected final String TEXT_56 = NL + "\t\t} else {" + NL + "\t\t\t";
  protected final String TEXT_57 = ".";
  protected final String TEXT_58 = " = ";
  protected final String TEXT_59 = NL;
  protected final String TEXT_60 = NL + "\t\t";
  protected final String TEXT_61 = NL + "\t\t";
  protected final String TEXT_62 = NL + NL + ";" + NL + "\t\t}";
  protected final String TEXT_63 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<? extends IConnection> outConnections = node.getOutgoingConnections();

if((outConnections==null)&&(outConnections.isEmpty())) {
	return stringBuffer.toString();
}

boolean isValid = false;
IConnection outFlowConn = null;
for(IConnection outConnection : outConnections) {
	if(outConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.MAIN)) {
		isValid = true;
		if(outConnection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			outFlowConn = outConnection;
		}
	}
}

if(!isValid) {
	return stringBuffer.toString();
}

String uname = ElementParameterParser.getValue(node, "__UNAME__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(uname);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
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
    
if(outFlowConn==null) {
	return stringBuffer.toString();
}

    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
for (IMetadataColumn column : outFlowConn.getMetadataTable().getListColumns()) {
	String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
    String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
    
    String defaultValue = column.getDefault();
    boolean isNotSetDefault = (defaultValue == null || defaultValue.trim().length()==0);
    
    String index = column.getOriginalDbColumnName();

    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_25);
    
	if(javaType == JavaTypesManager.STRING){

    stringBuffer.append(TEXT_26);
    stringBuffer.append(outFlowConn.getName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    
	if(column.isNullable()) {

    stringBuffer.append(TEXT_31);
    stringBuffer.append(isNotSetDefault? null: defaultValue);
    
	} else {

    stringBuffer.append(TEXT_32);
    stringBuffer.append(isNotSetDefault ? JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate) : defaultValue);
    
	}

    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
	} else {

    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    
		if(javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(outFlowConn.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
		} else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_42);
    stringBuffer.append(outFlowConn.getName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_46);
    
		} else if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_47);
    stringBuffer.append(outFlowConn.getName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
		} else {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(outFlowConn.getName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    
		}

    stringBuffer.append(TEXT_56);
    stringBuffer.append(outFlowConn.getName() );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(TEXT_59);
    
	if(column.isNullable()) {

    stringBuffer.append(TEXT_60);
    stringBuffer.append(isNotSetDefault? null: defaultValue);
    
	} else {

    stringBuffer.append(TEXT_61);
    stringBuffer.append(isNotSetDefault ? JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate) : defaultValue);
    
	}

    stringBuffer.append(TEXT_62);
    
	}
}

    stringBuffer.append(TEXT_63);
    return stringBuffer.toString();
  }
}
