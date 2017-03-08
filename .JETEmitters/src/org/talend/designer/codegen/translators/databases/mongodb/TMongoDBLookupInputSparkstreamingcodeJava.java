package org.talend.designer.codegen.translators.databases.mongodb;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmap.TMapAdapter;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.spark.generator.storage.MongoDBSparkStorage;

public class TMongoDBLookupInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TMongoDBLookupInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMongoDBLookupInputSparkstreamingcodeJava result = new TMongoDBLookupInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    \t\tpublic static class ";
  protected final String TEXT_2 = "_FlatMapper extends org.talend.bigdata.dataflow.hmap.HMapContextConfigurableBase implements org.talend.bigdata.dataflow.functions.FlatMapper<org.apache.avro.generic.IndexedRecord, ";
  protected final String TEXT_3 = "> {" + NL + "" + NL + "\t\t\t\tprivate ContextProperties context;" + NL + "\t\t\t\tprivate transient com.mongodb.MongoClient mongo_";
  protected final String TEXT_4 = ";" + NL + "\t\t\t\tprivate transient com.mongodb.client.MongoCollection<org.bson.Document> coll_";
  protected final String TEXT_5 = ";" + NL + "\t\t\t\tprivate transient java.util.Map<String, String> pathMap_";
  protected final String TEXT_6 = ";" + NL + "" + NL + "\t\t\t\tpublic ";
  protected final String TEXT_7 = "_FlatMapper(JobConf job) {" + NL + "\t\t\t\t\tthis.context = new ContextProperties(job);" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic void prepare() throws Exception {" + NL + "\t\t\t\t\t";
  protected final String TEXT_8 = NL + "\t\t\t        mongo_";
  protected final String TEXT_9 = " = ";
  protected final String TEXT_10 = "_MongoClient.getMongoClient(context);" + NL + "\t\t\t        ";
  protected final String TEXT_11 = NL + "\t\t\t        \tmongo_";
  protected final String TEXT_12 = ".addOption(com.mongodb.Bytes.QUERYOPTION_NOTIMEOUT);" + NL + "\t\t\t        \t";
  protected final String TEXT_13 = NL + "\t\t\t        com.mongodb.client.MongoDatabase db_";
  protected final String TEXT_14 = " = mongo_";
  protected final String TEXT_15 = ".getDatabase(";
  protected final String TEXT_16 = ");" + NL + "\t\t\t        coll_";
  protected final String TEXT_17 = " = db_";
  protected final String TEXT_18 = ".getCollection(";
  protected final String TEXT_19 = ");" + NL + "\t\t\t        ";
  protected final String TEXT_20 = NL + "\t\t\t        \tcoll_";
  protected final String TEXT_21 = " = coll_";
  protected final String TEXT_22 = ".withReadPreference(com.mongodb.ReadPreference.valueOf(\"";
  protected final String TEXT_23 = "\"));" + NL + "\t\t\t        \t";
  protected final String TEXT_24 = NL + "\t\t\t        pathMap_";
  protected final String TEXT_25 = " = new java.util.HashMap<String, String>();" + NL + "\t\t\t        ";
  protected final String TEXT_26 = NL + "\t\t\t\t\t\t\tpathMap_";
  protected final String TEXT_27 = ".put(\"";
  protected final String TEXT_28 = "\",";
  protected final String TEXT_29 = ");" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_30 = NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic Iterable<";
  protected final String TEXT_31 = "> flatMap(org.apache.avro.generic.IndexedRecord joinKeys) throws Exception {" + NL + "\t\t\t\t\tList<";
  protected final String TEXT_32 = "> output = new ArrayList<>();" + NL + "\t\t\t\t\t";
  protected final String TEXT_33 = " ";
  protected final String TEXT_34 = " = getHMapContext().getInputRecord(\"";
  protected final String TEXT_35 = "\");" + NL + "\t\t\t\t\torg.bson.Document myQuery_";
  protected final String TEXT_36 = " = org.bson.Document.parse(";
  protected final String TEXT_37 = ");" + NL + "\t\t\t\t\tcom.mongodb.client.FindIterable<org.bson.Document> findIterable_";
  protected final String TEXT_38 = " = coll_";
  protected final String TEXT_39 = ".find(myQuery_";
  protected final String TEXT_40 = ");" + NL + "\t\t\t\t\tcom.mongodb.client.MongoCursor<org.bson.Document> cursor_";
  protected final String TEXT_41 = " = findIterable_";
  protected final String TEXT_42 = ".iterator();" + NL + "\t\t\t\t\t";
  protected final String TEXT_43 = NL + "\t\t\t\t\twhile (cursor_";
  protected final String TEXT_44 = ".hasNext()){" + NL + "\t\t\t\t\t\tObject valueObj_";
  protected final String TEXT_45 = " = null;" + NL + "\t\t\t\t\t\torg.bson.Document document = cursor_";
  protected final String TEXT_46 = ".next();" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_47 = " result = new ";
  protected final String TEXT_48 = "();" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_49 = NL + "\t\t\t\t\t\t\tvalueObj_";
  protected final String TEXT_50 = " = getValue(pathMap_";
  protected final String TEXT_51 = ".get(\"";
  protected final String TEXT_52 = "\"),\"";
  protected final String TEXT_53 = "\", document);" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_54 = NL + "                        \t\tresult.";
  protected final String TEXT_55 = " = valueObj_";
  protected final String TEXT_56 = ";" + NL + "                        \t\t";
  protected final String TEXT_57 = NL + "                        \t\tif(valueObj_";
  protected final String TEXT_58 = " != null && valueObj_";
  protected final String TEXT_59 = ".toString().length() > 0) {" + NL + "                        \t\t";
  protected final String TEXT_60 = NL + "\t\t                                result.";
  protected final String TEXT_61 = " = BigDataParserUtils.parseTo_Date((java.util.Date) valueObj_";
  protected final String TEXT_62 = ", ";
  protected final String TEXT_63 = ");" + NL + "\t\t                                ";
  protected final String TEXT_64 = NL + "\t\t                                result.";
  protected final String TEXT_65 = " = BigDataParserUtils.parseTo_Date((java.util.Date) valueObj_";
  protected final String TEXT_66 = ", ";
  protected final String TEXT_67 = ");" + NL + "\t\t                                ";
  protected final String TEXT_68 = NL + "\t\t                                result.";
  protected final String TEXT_69 = " = java.nio.ByteBuffer.wrap(java.nio.charset.Charset.defaultCharset().decode(java.nio.ByteBuffer.wrap(((org.bson.types.Binary) valueObj_";
  protected final String TEXT_70 = ").getData())).toString().getBytes(utf8Charset));" + NL + "\t\t                                ";
  protected final String TEXT_71 = NL + "\t\t                                result.";
  protected final String TEXT_72 = " = valueObj_";
  protected final String TEXT_73 = ".toString();" + NL + "\t\t                                ";
  protected final String TEXT_74 = NL + "\t\t                                    if (valueObj_";
  protected final String TEXT_75 = ".getClass().equals(Double.class)) {" + NL + "\t\t                                        result.";
  protected final String TEXT_76 = " = BigDataParserUtils.parseTo_Double(valueObj_";
  protected final String TEXT_77 = ".toString()).intValue();" + NL + "\t\t                                    } else {" + NL + "\t\t                                        result.";
  protected final String TEXT_78 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_79 = "(valueObj_";
  protected final String TEXT_80 = ".toString());" + NL + "\t\t                                    }" + NL + "\t\t                                ";
  protected final String TEXT_81 = NL + "\t\t                                    if (valueObj_";
  protected final String TEXT_82 = ".getClass().equals(Double.class)) {" + NL + "\t\t                                        result.";
  protected final String TEXT_83 = " = BigDataParserUtils.parseTo_Double(valueObj_";
  protected final String TEXT_84 = ".toString()).longValue();" + NL + "\t\t                                    } else {" + NL + "\t\t                                        result.";
  protected final String TEXT_85 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_86 = "(valueObj_";
  protected final String TEXT_87 = ".toString());" + NL + "\t\t                                    }" + NL + "\t\t                                ";
  protected final String TEXT_88 = NL + "\t\t                                    if (valueObj_";
  protected final String TEXT_89 = ".getClass().equals(Double.class)) {" + NL + "\t\t                                        result.";
  protected final String TEXT_90 = " = BigDataParserUtils.parseTo_Double(valueObj_";
  protected final String TEXT_91 = ".toString()).shortValue();" + NL + "\t\t                                    } else {" + NL + "\t\t                                        result.";
  protected final String TEXT_92 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_93 = "(valueObj_";
  protected final String TEXT_94 = ".toString());" + NL + "\t\t                                    }" + NL + "\t\t                                ";
  protected final String TEXT_95 = NL + "\t\t                                result.";
  protected final String TEXT_96 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_97 = "(valueObj_";
  protected final String TEXT_98 = ".toString());" + NL + "\t\t                                ";
  protected final String TEXT_99 = NL + "                        \t\t} else {" + NL + "                            \t\tresult.";
  protected final String TEXT_100 = " = ";
  protected final String TEXT_101 = ";" + NL + "                        \t\t}" + NL + "                        \t\t";
  protected final String TEXT_102 = NL + "\t\t\t\t\t\toutput.add(result);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\treturn output;" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\t// Get Value from BSON document" + NL + "\t            public Object getValue(String parentNode, String dbColumnName, org.bson.Document document) {" + NL + "\t                Object value = null;" + NL + "\t                if(document == null){" + NL + "\t                    return null;" + NL + "\t                }" + NL + "\t                if (parentNode == null || \"\".equals(parentNode)) {" + NL + "\t                    if (\"*\".equals(dbColumnName)) {" + NL + "\t                        value = document;" + NL + "\t                    } else if (document.get(dbColumnName)!= null){" + NL + "\t                        value = document.get(dbColumnName);" + NL + "\t                    }" + NL + "\t                } else {" + NL + "\t                    String objNames[] = parentNode.split(\"\\\\.\");" + NL + "\t                    org.bson.Document currentDocument = document;" + NL + "\t                    for(int i=0; i<objNames.length; i++){" + NL + "\t                        currentDocument = (org.bson.Document) currentDocument.get(objNames[i]);" + NL + "\t                        if(currentDocument == null){" + NL + "\t                            break;" + NL + "\t                        }" + NL + "\t                    }" + NL + "\t                    if (\"*\".equals(dbColumnName)) {" + NL + "\t                        value = currentDocument;" + NL + "\t                    } else if(currentDocument != null){" + NL + "\t                        value=currentDocument.get(dbColumnName);" + NL + "\t                    }" + NL + "\t                }" + NL + "\t            \treturn value;" + NL + "\t            }" + NL + "" + NL + "\t\t\t\t@Override" + NL + "\t\t\t\tpublic void cleanup() throws Exception {" + NL + "\t\t\t\t\t//" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t}" + NL + "" + NL + "\t\t\tpublic static class ";
  protected final String TEXT_103 = "_MongoClient {" + NL + "" + NL + "\t\t\t\tprivate static volatile com.mongodb.MongoClient mongoClient;" + NL + "" + NL + "\t\t\t\tprivate ";
  protected final String TEXT_104 = "_MongoClient(){" + NL + "\t\t\t\t}" + NL + "" + NL + "\t\t\t\tpublic static com.mongodb.MongoClient getMongoClient(ContextProperties context){" + NL + "\t\t\t\t\tif(mongoClient == null){" + NL + "\t\t\t\t\t\tsynchronized (";
  protected final String TEXT_105 = "_MongoClient.class) {" + NL + "\t\t\t\t\t\t\tif(mongoClient == null){" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_106 = NL + "\t\t\t\t\t\t\t\t// Empty client options" + NL + "\t\t\t\t\t    \t\tcom.mongodb.MongoClientOptions clientOptions_";
  protected final String TEXT_107 = " = new com.mongodb.MongoClientOptions.Builder().build();" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_108 = NL + "                    \t\t\t\tclientOptions_";
  protected final String TEXT_109 = " = new com.mongodb.MongoClientOptions.Builder()" + NL + "                    \t\t\t\t.socketFactory(javax.net.ssl.SSLSocketFactory.getDefault())" + NL + "                    \t\t\t\t.build();" + NL + "                    \t\t\t\t";
  protected final String TEXT_110 = NL + "\t\t\t\t\t    \t\t// Empty client credentials list" + NL + "\t\t\t\t\t    \t\tjava.util.List<com.mongodb.MongoCredential> mongoCredentialList_";
  protected final String TEXT_111 = " = new java.util.ArrayList<com.mongodb.MongoCredential>();" + NL + "\t\t\t\t\t    \t\t";
  protected final String TEXT_112 = NL + "\t\t\t\t                    com.mongodb.MongoCredential mongoCredential_";
  protected final String TEXT_113 = ";" + NL + "\t\t\t\t                    ";
  protected final String TEXT_114 = NL + "\t\t\t\t                    \t\t\tmongoCredential_";
  protected final String TEXT_115 = " = com.mongodb.MongoCredential.createCredential(";
  protected final String TEXT_116 = ", ";
  protected final String TEXT_117 = ", new String(";
  protected final String TEXT_118 = ").toCharArray());" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_119 = NL + "\t\t\t\t                    \t\t\tmongoCredential_";
  protected final String TEXT_120 = " = com.mongodb.MongoCredential.createMongoCRCredential(";
  protected final String TEXT_121 = ", ";
  protected final String TEXT_122 = ", new String(";
  protected final String TEXT_123 = ").toCharArray());" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_124 = NL + "\t\t\t\t                            mongoCredential_";
  protected final String TEXT_125 = " = com.mongodb.MongoCredential.createPlainCredential(";
  protected final String TEXT_126 = ", \"$external\", new String(";
  protected final String TEXT_127 = ").toCharArray());" + NL + "\t\t\t\t                            ";
  protected final String TEXT_128 = NL + "\t\t\t\t                            mongoCredential_";
  protected final String TEXT_129 = " = com.mongodb.MongoCredential.createScramSha1Credential(";
  protected final String TEXT_130 = ", ";
  protected final String TEXT_131 = ", new String(";
  protected final String TEXT_132 = ").toCharArray());" + NL + "\t\t\t\t                            ";
  protected final String TEXT_133 = NL + "\t\t\t\t                        System.setProperty(\"java.security.krb5.realm\", ";
  protected final String TEXT_134 = ");" + NL + "\t\t\t\t                        System.setProperty(\"java.security.krb5.kdc\", ";
  protected final String TEXT_135 = ");" + NL + "\t\t\t\t                        System.setProperty(\"javax.security.auth.useSubjectCredsOnly\", \"false\");" + NL + "\t\t\t\t                        mongoCredential_";
  protected final String TEXT_136 = " = com.mongodb.MongoCredential.createGSSAPICredential(";
  protected final String TEXT_137 = ");" + NL + "\t\t\t\t                        ";
  protected final String TEXT_138 = NL + "\t\t\t\t                    mongoCredentialList_";
  protected final String TEXT_139 = ".add(mongoCredential_";
  protected final String TEXT_140 = ");" + NL + "\t\t\t\t                    ";
  protected final String TEXT_141 = NL + "\t\t\t\t\t\t\t\t\tList<com.mongodb.ServerAddress> addrs_";
  protected final String TEXT_142 = " = new java.util.ArrayList<com.mongodb.ServerAddress>();" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_143 = NL + "\t\t\t\t\t\t\t\t\t\taddrs_";
  protected final String TEXT_144 = ".add(new com.mongodb.ServerAddress(";
  protected final String TEXT_145 = ",";
  protected final String TEXT_146 = "));" + NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_147 = NL + "\t\t\t\t                    mongoClient = new com.mongodb.MongoClient(addrs_";
  protected final String TEXT_148 = ", mongoCredentialList_";
  protected final String TEXT_149 = ", clientOptions_";
  protected final String TEXT_150 = ");" + NL + "\t\t\t\t                    ";
  protected final String TEXT_151 = NL + "\t\t\t\t                    com.mongodb.ServerAddress serverAddress_";
  protected final String TEXT_152 = " = new com.mongodb.ServerAddress(";
  protected final String TEXT_153 = ", ";
  protected final String TEXT_154 = ");" + NL + "\t\t\t\t                    mongoClient = new com.mongodb.MongoClient(serverAddress_";
  protected final String TEXT_155 = ", mongoCredentialList_";
  protected final String TEXT_156 = ", clientOptions_";
  protected final String TEXT_157 = ");" + NL + "\t\t\t\t                    ";
  protected final String TEXT_158 = NL + "\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t}" + NL + "" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\treturn mongoClient;" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "    \t\t";
  protected final String TEXT_159 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
String query = ElementParameterParser.getValue(node,"__QUERY__");
query = query.replaceAll("\n","");
query = query.replaceAll("\r","");
List<Map<String, String>> mapping = (List<Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__MAPPING__");

TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(false, true);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
TMapAdapter tMapAdapter = org.talend.designer.common.tmap.AbstractTMapAdapter.createForTMap(tSqlRowUtil.getOutgoingConnection().getTarget());
String inputMainName = tMapAdapter.getInputMainName();
String structMainName = codeGenArgument.getRecordStructName(inputMainName);

java.util.List<IMetadataTable> metadatas = node.getMetadataList();
java.util.List<IMetadataColumn> columnList = null;

String mongoDBConfiguration = ElementParameterParser.getValue(node,"__MONGODB_CONFIGURATION__");
INode mongoDBConfigurationNode = null;

for (INode pNode1 : node.getProcess().getNodesOfType("tMongoDBConfiguration")) {
    if(mongoDBConfiguration!=null && mongoDBConfiguration.equals(pNode1.getUniqueName())) {
        mongoDBConfigurationNode = pNode1;
        break;
    }
}
MongoDBSparkStorage storage = new MongoDBSparkStorage(mongoDBConfigurationNode);

	// IF01
	if(metadatas != null && metadatas.size() > 0){
		IMetadataTable metadata = metadatas.get(0);
		// IF02
    	if(metadata != null){
    		
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
					boolean setReadPreference = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__SET_READ_PREFERENCE__"));
					String readPreference = ElementParameterParser.getValue(node, "__READ_PREFERENCE__");
					boolean queryOptionNoTimeOut = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__QUERYOPTION_NOTIMEOUT__"));
					String collection = ElementParameterParser.getValue(node,"__COLLECTION__");
					String dbName = storage.getDatabase();
					
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
			        if(queryOptionNoTimeOut){
			        	
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
			        }
			        
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dbName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(collection);
    stringBuffer.append(TEXT_19);
    
			        if(setReadPreference){
			        	
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(readPreference);
    stringBuffer.append(TEXT_23);
    
			        }
			        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    
			        for (int i=0;i<mapping.size();i++) {
						String path = mapping.get(i).get("PARENT_NODE_PATH");
						if(path==null || "".equals(path)){
							path="\"\"";
						}
						String schemaColumn=mapping.get(i).get("SCHEMA_COLUMN");
							
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(schemaColumn);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(path);
    stringBuffer.append(TEXT_29);
    
					}
			        
    stringBuffer.append(TEXT_30);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(query);
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
    
					columnList = metadata.getListColumns();
					int sizeColumns = columnList.size();
					
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_48);
    
						for (int i = 0; i < sizeColumns; i++) {
							IMetadataColumn column = columnList.get(i);
							String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
							JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
							String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
							
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_53);
    
							// Types conversion
                    		// If object then return the value
                    		if(javaType == JavaTypesManager.OBJECT) {
                        		
    stringBuffer.append(TEXT_54);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
                    		// Else other types conversion
                    		} else {
                    			
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    

                        			// DATE
		                            if(javaType == JavaTypesManager.DATE) {
		                                
    stringBuffer.append(TEXT_60);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append( patternValue);
    stringBuffer.append(TEXT_63);
    
		                            }

		                            // DATE
		                            if(javaType == JavaTypesManager.DATE) {
		                                
    stringBuffer.append(TEXT_64);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append( patternValue);
    stringBuffer.append(TEXT_67);
    
		                            }

		                            // BYTE ARRAY
		                            else if (javaType == JavaTypesManager.BYTE_ARRAY){
		                                
    stringBuffer.append(TEXT_68);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    
		                            }

		                            // STRING
		                            else if(javaType == JavaTypesManager.STRING){
		                                
    stringBuffer.append(TEXT_71);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    
		                            }

		                            // INTEGER with a returned DB type of double or Int
		                            else if(javaType == JavaTypesManager.INTEGER){
		                                
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    
		                            }

		                            // LONG with a returned DB type of double or long
		                            else if (javaType == JavaTypesManager.LONG){
		                                
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    
		                            }

		                            // SHORT with a returned DB type of double or short
		                            else if(javaType == JavaTypesManager.SHORT){
		                                
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    
		                            }

		                            // OTHER
		                            else {
		                                
    stringBuffer.append(TEXT_95);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    
		                            }

                        		
    stringBuffer.append(TEXT_99);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate));
    stringBuffer.append(TEXT_101);
    
                    		}
						}
						
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    
								List<Map<String,String>> replicaAddrs = storage.getReplicaAddrs();
								boolean useSSL = storage.isUseSSL();
								String dbversion = storage.getDbversion();
								boolean authentication = storage.isAuthentication();
								String authenticationMechanism = storage.getAuthenticationMechanism();
								String password = storage.getEncryptedPassword();
								String dbuser = storage.getDbuser();
								boolean useAuthDB = storage.isUseAuthDB();
    							String authDB = storage.getAuthenticationDatabase();
    							String dbname = storage.getDatabase();
    							String usedAuthenticationDB = useAuthDB ? authDB : dbname;
    							String krbRealm = storage.getKrbRealm();
    							String krbKdc = storage.getKrbKdc();
    							String krbUserPrincipal = storage.getKrbUserPrincipal();
    							boolean useReplicaSet = storage.isUseReplicaSet();
    							String dbhost = storage.getDbhost();
    							String dbport = storage.getDbport();
								
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    
					    		if (useSSL) {
									
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    
                				}
					    		
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    
					    		// Authentication
								if (authentication){
									
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    
				                    if((authenticationMechanism.equals("NEGOTIATE"))||(authenticationMechanism.equals("PLAIN"))||(authenticationMechanism.equals("SCRAM-SHA-1"))){
				                        if(authenticationMechanism.equals("NEGOTIATE")){
				                			if(dbversion.equals("MONGODB_3_0_X") || dbversion.equals("MONGODB_3_2_X")) {
												
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(usedAuthenticationDB);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(storage.getEncryptedPassword());
    stringBuffer.append(TEXT_118);
    
				                			} else {
												
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(usedAuthenticationDB);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(storage.getEncryptedPassword());
    stringBuffer.append(TEXT_123);
    
				                			}
				                        } else if(authenticationMechanism.equals("PLAIN")){
				                            
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(storage.getEncryptedPassword());
    stringBuffer.append(TEXT_127);
    
				                        } else if(authenticationMechanism.equals("SCRAM-SHA-1")){
				                            
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(usedAuthenticationDB);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(storage.getEncryptedPassword());
    stringBuffer.append(TEXT_132);
    
				                        }
				                    } else { // GSSAPI SASL (KERBEROS)
				                        
    stringBuffer.append(TEXT_133);
    stringBuffer.append(krbRealm);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(krbKdc);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(krbUserPrincipal);
    stringBuffer.append(TEXT_137);
    
				                    }
				                    
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    
								}
								// the client
								if(useReplicaSet){
								    
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    
									for(Map<String,String> replicaAddr:replicaAddrs){
									
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(replicaAddr.get("REPLICA_HOST"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(replicaAddr.get("REPLICA_PORT"));
    stringBuffer.append(TEXT_146);
    
									}
				                    
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    
								}else{
				                    
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(dbhost);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(dbport);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    
								}
					    		
    stringBuffer.append(TEXT_158);
    
    	// END IF02
    	}
    // END IF01
	}

    stringBuffer.append(TEXT_159);
    

    return stringBuffer.toString();
  }
}
