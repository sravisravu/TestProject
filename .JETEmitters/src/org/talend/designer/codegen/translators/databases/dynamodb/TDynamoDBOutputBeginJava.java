package org.talend.designer.codegen.translators.databases.dynamodb;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;
import java.util.Map;

public class TDynamoDBOutputBeginJava
{
  protected static String nl;
  public static synchronized TDynamoDBOutputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDynamoDBOutputBeginJava result = new TDynamoDBOutputBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\tlog.debug(\"";
  protected final String TEXT_2 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_5 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_6 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_7 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_8 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_9 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_10 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_11 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_12 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_13 = " - Written records count: \" + nb_line_";
  protected final String TEXT_14 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_16 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_18 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_19 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_20 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_21 = " - Writing the record \" + nb_line_";
  protected final String TEXT_22 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_23 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_24 = " - Processing the record \" + nb_line_";
  protected final String TEXT_25 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_26 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_27 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_28 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_29 = NL + "\tint nb_line_";
  protected final String TEXT_30 = " = 0;" + NL + "\t" + NL + "\t";
  protected final String TEXT_31 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.Item item_";
  protected final String TEXT_32 = " = new com.amazonaws.services.dynamodbv2.document.Item(); " + NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.spec.PutItemSpec putItemSpec_";
  protected final String TEXT_33 = ";" + NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.PutItemOutcome putItemOutcome_";
  protected final String TEXT_34 = ";" + NL + "\t\t";
  protected final String TEXT_35 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec updateItemSpec_";
  protected final String TEXT_36 = " = new com.amazonaws.services.dynamodbv2.document.spec.UpdateItemSpec();" + NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.UpdateItemOutcome updateItemOutcome_";
  protected final String TEXT_37 = ";" + NL + "\t\t";
  protected final String TEXT_38 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec deleteItemSpec_";
  protected final String TEXT_39 = " = new com.amazonaws.services.dynamodbv2.document.spec.DeleteItemSpec();" + NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.DeleteItemOutcome deleteItemOutcome_";
  protected final String TEXT_40 = ";" + NL + "\t\t";
  protected final String TEXT_41 = "\t" + NL + "\t\t\tcom.amazonaws.auth.AWSCredentials credentials_";
  protected final String TEXT_42 = " = new com.amazonaws.auth.BasicAWSCredentials(" + NL + "\t\t\t\t";
  protected final String TEXT_43 = "," + NL + "\t\t\t\t";
  protected final String TEXT_44 = ");\t\t" + NL + "\t\t\t" + NL + "\t\t\tcom.amazonaws.services.dynamodbv2.AmazonDynamoDBClient dynamoDBClient_";
  protected final String TEXT_45 = " = new com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient(credentials_";
  protected final String TEXT_46 = ");" + NL + "   " + NL + "\t\t\t";
  protected final String TEXT_47 = NL + "\t\t\t\tdynamoDBClient_";
  protected final String TEXT_48 = " = dynamoDBClient_";
  protected final String TEXT_49 = ".withEndpoint(";
  protected final String TEXT_50 = ");" + NL + "\t\t\t";
  protected final String TEXT_51 = NL + "\t\t\t\tdynamoDBClient_";
  protected final String TEXT_52 = ".setRegion(com.amazonaws.regions.RegionUtils.getRegion(";
  protected final String TEXT_53 = "));" + NL + "\t\t\t";
  protected final String TEXT_54 = NL + "\t\t\t\t\t\t\t" + NL + "\t\t\tcom.amazonaws.services.dynamodbv2.document.DynamoDB dynamoDB_";
  protected final String TEXT_55 = " = new com.amazonaws.services.dynamodbv2.document.DynamoDB(dynamoDBClient_";
  protected final String TEXT_56 = ");" + NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_57 = NL + "\t\t\t\tList<com.amazonaws.services.dynamodbv2.model.AttributeDefinition> attributeDefinitionList_";
  protected final String TEXT_58 = " = new java.util.ArrayList<com.amazonaws.services.dynamodbv2.model.AttributeDefinition>();" + NL + "\t\t\t\t" + NL + "\t\t\t\tList<com.amazonaws.services.dynamodbv2.model.KeySchemaElement> keyList_";
  protected final String TEXT_59 = " = new java.util.ArrayList<com.amazonaws.services.dynamodbv2.model.KeySchemaElement>();" + NL + "\t\t\t\t" + NL + "\t\t\t\tkeyList_";
  protected final String TEXT_60 = ".add(new com.amazonaws.services.dynamodbv2.model.KeySchemaElement(";
  protected final String TEXT_61 = ", com.amazonaws.services.dynamodbv2.model.KeyType.HASH));" + NL + "\t\t\t\t" + NL + "\t\t\t\tif(";
  protected final String TEXT_62 = " != null && !";
  protected final String TEXT_63 = ".isEmpty())" + NL + "\t\t\t\t{\t" + NL + "\t\t\t\t\tkeyList_";
  protected final String TEXT_64 = ".add(new com.amazonaws.services.dynamodbv2.model.KeySchemaElement(";
  protected final String TEXT_65 = ", com.amazonaws.services.dynamodbv2.model.KeyType.RANGE));" + NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_66 = "\t\t" + NL + "\t\t\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_67 = ".add(new com.amazonaws.services.dynamodbv2.model.AttributeDefinition(\"";
  protected final String TEXT_68 = "\", com.amazonaws.services.dynamodbv2.model.ScalarAttributeType.S));" + NL + "\t\t\t\t\t";
  protected final String TEXT_69 = "\t\t" + NL + "\t\t\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_70 = ".add(new com.amazonaws.services.dynamodbv2.model.AttributeDefinition(\"";
  protected final String TEXT_71 = "\", com.amazonaws.services.dynamodbv2.model.ScalarAttributeType.N));\t\t\t" + NL + "\t\t\t\t\t";
  protected final String TEXT_72 = NL + "\t\t" + NL + "\t\t\t";
  protected final String TEXT_73 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.Table tableTemp_";
  protected final String TEXT_74 = " = dynamoDB_";
  protected final String TEXT_75 = ".createTable(" + NL + "\t\t\t\t\t";
  protected final String TEXT_76 = "," + NL + "\t\t\t\t\tkeyList_";
  protected final String TEXT_77 = "," + NL + "\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_78 = "," + NL + "\t\t\t\t\tnew com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput((long)";
  protected final String TEXT_79 = ", (long)";
  protected final String TEXT_80 = "));" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_81 = ".waitForActive();" + NL + "\t\t\t\t";
  protected final String TEXT_82 = NL + "\t\t\t\t\tlog.info(";
  protected final String TEXT_83 = " + \" has been created.\");" + NL + "\t\t\t\t";
  protected final String TEXT_84 = NL + "\t\t\t\ttry{" + NL + "\t\t\t\t\tcom.amazonaws.services.dynamodbv2.document.Table tableTemp_";
  protected final String TEXT_85 = " = dynamoDB_";
  protected final String TEXT_86 = ".getTable(";
  protected final String TEXT_87 = ");" + NL + "\t\t\t\t" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\ttableTemp_";
  protected final String TEXT_88 = " = dynamoDB_";
  protected final String TEXT_89 = ".createTable(" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_90 = "," + NL + "\t\t\t\t\t\tkeyList_";
  protected final String TEXT_91 = "," + NL + "\t\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_92 = "," + NL + "\t\t\t\t\tnew com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput((long)";
  protected final String TEXT_93 = ", (long)";
  protected final String TEXT_94 = "));" + NL + "\t\t\t\t\ttableTemp_";
  protected final String TEXT_95 = ".waitForActive();" + NL + "\t\t\t\t\t";
  protected final String TEXT_96 = NL + "\t\t\t\t\t\tlog.info(";
  protected final String TEXT_97 = " + \" has been created.\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_98 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch(com.amazonaws.services.dynamodbv2.model.ResourceInUseException ex)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\t";
  protected final String TEXT_99 = NL + "\t\t\t\t\t\tlog.info(";
  protected final String TEXT_100 = " + \" already exist.\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_101 = NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t" + NL + "\t\t\t";
  protected final String TEXT_102 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.Table tableTemp_";
  protected final String TEXT_103 = " = dynamoDB_";
  protected final String TEXT_104 = ".getTable(";
  protected final String TEXT_105 = ");" + NL + "\t\t\t\t" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_106 = ".delete();" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_107 = ".waitForDelete();" + NL + "\t\t\t\t";
  protected final String TEXT_108 = NL + "\t\t\t\t\tlog.info(";
  protected final String TEXT_109 = " + \" has been deleted.\");" + NL + "\t\t\t\t";
  protected final String TEXT_110 = NL + "\t\t\t\t" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_111 = " = dynamoDB_";
  protected final String TEXT_112 = ".createTable(" + NL + "\t\t\t\t\t";
  protected final String TEXT_113 = "," + NL + "\t\t\t\t\tkeyList_";
  protected final String TEXT_114 = "," + NL + "\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_115 = "," + NL + "\t\t\t\t\tnew com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput((long)";
  protected final String TEXT_116 = ", (long)";
  protected final String TEXT_117 = "));" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_118 = ".waitForActive();\t\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_119 = "\t" + NL + "\t\t\t\t\tlog.info(";
  protected final String TEXT_120 = " + \" has been created.\");" + NL + "\t\t\t\t";
  protected final String TEXT_121 = NL + "\t\t\t\tcom.amazonaws.services.dynamodbv2.document.Table tableTemp_";
  protected final String TEXT_122 = " = dynamoDB_";
  protected final String TEXT_123 = ".getTable(";
  protected final String TEXT_124 = ");" + NL + "\t\t\t\t" + NL + "\t\t\t\ttry{\t\t\t\t" + NL + "\t\t\t\t\ttableTemp_";
  protected final String TEXT_125 = ".delete();" + NL + "\t\t\t\t\ttableTemp_";
  protected final String TEXT_126 = ".waitForDelete();" + NL + "\t\t\t\t\t";
  protected final String TEXT_127 = "\t" + NL + "\t\t\t\t\t\tlog.info(";
  protected final String TEXT_128 = " + \" has been deleted.\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_129 = "\t\t\t\t\t" + NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch(com.amazonaws.services.dynamodbv2.model.ResourceNotFoundException ex)" + NL + "\t\t\t\t{\t" + NL + "\t\t\t\t\t";
  protected final String TEXT_130 = "\t" + NL + "\t\t\t\t\t\tlog.info(";
  protected final String TEXT_131 = " + \" doesn't exist.\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_132 = "\t\t\t\t\t" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_133 = " = dynamoDB_";
  protected final String TEXT_134 = ".createTable(" + NL + "\t\t\t\t\t";
  protected final String TEXT_135 = "," + NL + "\t\t\t\t\tkeyList_";
  protected final String TEXT_136 = "," + NL + "\t\t\t\t\tattributeDefinitionList_";
  protected final String TEXT_137 = "," + NL + "\t\t\t\t\tnew com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput((long)";
  protected final String TEXT_138 = ", (long)";
  protected final String TEXT_139 = "));" + NL + "\t\t\t\ttableTemp_";
  protected final String TEXT_140 = ".waitForActive();\t\t\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_141 = "\t" + NL + "\t\t\t\t\tlog.info(";
  protected final String TEXT_142 = " + \" has been created.\");" + NL + "\t\t\t\t";
  protected final String TEXT_143 = "\t\t\t" + NL + "\t\t\tcom.amazonaws.services.dynamodbv2.document.Table table_";
  protected final String TEXT_144 = " = dynamoDB_";
  protected final String TEXT_145 = ".getTable(";
  protected final String TEXT_146 = ");" + NL + "" + NL + "\t\t\t\t" + NL + "\t";
  protected final String TEXT_147 = NL;
  protected final String TEXT_148 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_1);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_2);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_6);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_8);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_9);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_12);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_17);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_19);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_20);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_22);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_23);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_25);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_26);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_28);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    
  	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
  	INode node = (INode)codeGenArgument.getArgument();
  	String cid = node.getUniqueName();

	String accessKey   = ElementParameterParser.getValue(node, "__ACCESS_KEY__");
	String secretKey   = ElementParameterParser.getValue(node, "__SECRET_KEY__");
	
	String region = ElementParameterParser.getValue(node, "__REGION__");

	boolean useEndPoint = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__USE_ENDPOINT__"));
	
    String endPoint   = ElementParameterParser.getValue(node, "__ENDPOINT__");
    
	String dataAction = ElementParameterParser.getValue(node,"__DATA_ACTION__");
	
	String tableAction = ElementParameterParser.getValue(node,"__TABLE_ACTION__");
	
	String table   = ElementParameterParser.getValue(node, "__TABLE__");
	
	String hashKeyName = ElementParameterParser.getValue(node, "__PARTITION_KEY__");
	String rangeKeyName = ElementParameterParser.getValue(node, "__SORT_KEY__");
	
	long rcu = Long.parseLong(ElementParameterParser.getValue(node, "__RCU__"));
	long wcu = Long.parseLong(ElementParameterParser.getValue(node, "__WCU__"));
		
	boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
	
  	List<IMetadataTable> metadatas = node.getMetadataList();
  	List<IMetadataColumn> columnList = null;
  	List<? extends IConnection> outputs = node.getOutgoingSortedConnections();
  	String firstConnName = "";
  	if (outputs.size() > 0){
		IConnection out = outputs.get(0);
		if(out!=null && out.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
			firstConnName = out.getName();
		}
    }
	
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    
  	if(metadatas != null && metadatas.size() > 0){
      	IMetadataTable metadata = metadatas.get(0);
      	if(metadata != null){
			if ("INSERT".equalsIgnoreCase(dataAction)) {
		
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
			}
			else if ("UPDATE".equalsIgnoreCase(dataAction)) {			
		
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    
			}
			else if ("DELETE".equalsIgnoreCase(dataAction)) {				
		
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
			}
		
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(accessKey);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(secretKey);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    
			if(useEndPoint)
			{
			
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(endPoint);
    stringBuffer.append(TEXT_50);
    
			}		
		
			if(!useEndPoint && region!=null && !region.isEmpty() && !"DEFAULT".equalsIgnoreCase(region)){
			
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(region);
    stringBuffer.append(TEXT_53);
    
			}
		
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
			if(!"NONE".equalsIgnoreCase(tableAction))
			{
				
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(hashKeyName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(rangeKeyName);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(rangeKeyName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(rangeKeyName);
    stringBuffer.append(TEXT_65);
    
				columnList = metadata.getListColumns();
				int sizeColumns = columnList.size();
				
				for (int i = 0; i < sizeColumns; i++) {
					IMetadataColumn column = columnList.get(i);
					if(column.isKey()){				
						String type = column.getTalendType();
						if(type.toUpperCase().indexOf("STRING") >=0)
						{
					
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_68);
    	
						}
						else if(type.toUpperCase().indexOf("INTEGER") >=0)
						{
					
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_71);
    	
						}								
					}
				}
			}
			
			if ("NONE".equalsIgnoreCase(tableAction)) {
			
    stringBuffer.append(TEXT_72);
    
			} else if ("CREATE".equalsIgnoreCase(tableAction)) {
			
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(rcu);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(wcu);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    
				if(isLog4jEnabled){
				
    stringBuffer.append(TEXT_82);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_83);
    
				}

			} else if ("CREATE_IF_NOT_EXISTS".equalsIgnoreCase(tableAction)) {
			
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(rcu);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(wcu);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    
					if(isLog4jEnabled){
					
    stringBuffer.append(TEXT_96);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_97);
    
					}
					
    stringBuffer.append(TEXT_98);
    
					if(isLog4jEnabled){
					
    stringBuffer.append(TEXT_99);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_100);
    
					}
					
    stringBuffer.append(TEXT_101);
    
			} else if ("DROP_CREATE".equalsIgnoreCase(tableAction)) {
			
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    
				if(isLog4jEnabled){
				
    stringBuffer.append(TEXT_108);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_109);
    
				}
				
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(rcu);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(wcu);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    
				if(isLog4jEnabled){
				
    stringBuffer.append(TEXT_119);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_120);
    
				}				

			} else if ("DROP_IF_EXISTS_AND_CREATE".equalsIgnoreCase(tableAction)) {
			
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    
					if(isLog4jEnabled){
					
    stringBuffer.append(TEXT_127);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_128);
    
					}
					
    stringBuffer.append(TEXT_129);
    
					if(isLog4jEnabled){
					
    stringBuffer.append(TEXT_130);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_131);
    
					}
					
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(rcu);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(wcu);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    
				if(isLog4jEnabled){
				
    stringBuffer.append(TEXT_141);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_142);
    
				}
			}
			
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_146);
    						
		}
	} 
	
    stringBuffer.append(TEXT_147);
    stringBuffer.append(TEXT_148);
    return stringBuffer.toString();
  }
}
