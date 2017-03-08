package org.talend.designer.codegen.translators.elasticsearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;
import org.talend.designer.spark.generator.storage.ElasticSearchSparkStorage;

public class TElasticSearchOutputSparkcodeJava
{
  protected static String nl;
  public static synchronized TElasticSearchOutputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TElasticSearchOutputSparkcodeJava result = new TElasticSearchOutputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tpublic static class ";
  protected final String TEXT_2 = "_toMetadataFunction implements org.apache.spark.api.java.function.PairFunction<";
  protected final String TEXT_3 = ", Object, Object>{" + NL + "\t\tprivate ContextProperties context = null;" + NL + "" + NL + "\t    public ";
  protected final String TEXT_4 = "_toMetadataFunction(JobConf job){" + NL + "\t \t\tthis.context = new ContextProperties(job);" + NL + "\t    }" + NL + "" + NL + "\t\tpublic scala.Tuple2<Object, Object> call(";
  protected final String TEXT_5 = " row) throws Exception{" + NL + "\t    \tjava.util.Map<org.elasticsearch.spark.rdd.Metadata, Object> meta = new java.util.HashMap<org.elasticsearch.spark.rdd.Metadata, Object>();" + NL + "\t    \t";
  protected final String TEXT_6 = NL + "\t\t    \tmeta.put(org.elasticsearch.spark.rdd.Metadata.";
  protected final String TEXT_7 = ", row.";
  protected final String TEXT_8 = ");" + NL + "\t\t    ";
  protected final String TEXT_9 = NL + "\t    \t";
  protected final String TEXT_10 = NL + "\t    \t\treturn new scala.Tuple2<Object, Object>(meta, row.json_document);" + NL + "\t    \t";
  protected final String TEXT_11 = NL + "\t\t    \t";
  protected final String TEXT_12 = " docRow = new ";
  protected final String TEXT_13 = "();" + NL + "\t\t    \t";
  protected final String TEXT_14 = NL + "\t\t\t    \tdocRow.";
  protected final String TEXT_15 = " = row.";
  protected final String TEXT_16 = ";" + NL + "\t\t    \t";
  protected final String TEXT_17 = NL + "\t\t    \treturn new scala.Tuple2<Object, Object>(meta, docRow);" + NL + "\t    \t";
  protected final String TEXT_18 = NL + "\t    }" + NL + "\t}";
  protected final String TEXT_19 = NL + "\t\tpublic static class ";
  protected final String TEXT_20 = "_toJsonFunction implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_21 = ", String>{" + NL + "\t\t\tprivate ContextProperties context = null;" + NL + "" + NL + "\t\t    public ";
  protected final String TEXT_22 = "_toJsonFunction(JobConf job){" + NL + "\t\t \t\tthis.context = new ContextProperties(job);" + NL + "\t\t    }" + NL + "" + NL + "\t\t\tpublic String call(";
  protected final String TEXT_23 = " row) throws Exception{" + NL + "\t\t    \treturn row.json_document;" + NL + "\t\t    }" + NL + "\t\t}" + NL + "" + NL + "\t";
  protected final String TEXT_24 = NL + "    public static class ";
  protected final String TEXT_25 = "_ForeachRDDOutput implements ";
  protected final String TEXT_26 = "{" + NL + "\t\tprivate ContextProperties context = null;" + NL + "" + NL + "\t    public ";
  protected final String TEXT_27 = "_ForeachRDDOutput(JobConf job){" + NL + "\t \t\tthis.context = new ContextProperties(job);" + NL + "\t    }" + NL + "\t\tpublic ";
  protected final String TEXT_28 = " call(";
  protected final String TEXT_29 = " rdd) throws Exception{" + NL + "" + NL + "\t    \tjava.util.Map<String, String> config = new java.util.HashMap<String, String>();" + NL + "\t    \t";
  protected final String TEXT_30 = NL + "\t\t\t\tconfig.put(";
  protected final String TEXT_31 = ", ";
  protected final String TEXT_32 = ");" + NL + "\t\t\t";
  protected final String TEXT_33 = NL + "\t        ";
  protected final String TEXT_34 = NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_USE_SSL, \"true\");" + NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_CERT_ALLOW_SELF_SIGNED, \"true\");" + NL + "\t\t\t\tString es_truststorepath_";
  protected final String TEXT_35 = " = ";
  protected final String TEXT_36 = ";" + NL + "\t\t\t\tif(es_truststorepath_";
  protected final String TEXT_37 = " != null && !es_truststorepath_";
  protected final String TEXT_38 = ".startsWith(\"file:\")){" + NL + "\t\t\t\t\tes_truststorepath_";
  protected final String TEXT_39 = " = \"file://\" + es_truststorepath_";
  protected final String TEXT_40 = ";" + NL + "\t\t\t\t}" + NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_TRUST_STORE_LOCATION, es_truststorepath_";
  protected final String TEXT_41 = ");;" + NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_TRUST_STORE_PASS, ";
  protected final String TEXT_42 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_43 = NL + "\t\t\t\t\tString es_keystorepath_";
  protected final String TEXT_44 = " = ";
  protected final String TEXT_45 = ";" + NL + "\t\t\t\t\tif(es_keystorepath_";
  protected final String TEXT_46 = " != null && !es_keystorepath_";
  protected final String TEXT_47 = ".startsWith(\"file:\")){" + NL + "\t\t\t\t\t\tes_keystorepath_";
  protected final String TEXT_48 = " = \"file://\" + es_keystorepath_";
  protected final String TEXT_49 = ";" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_LOCATION, es_keystorepath_";
  protected final String TEXT_50 = ");" + NL + "\t\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_PASS, ";
  protected final String TEXT_51 = ");" + NL + "\t\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_TYPE, ";
  protected final String TEXT_52 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_53 = NL + "\t\t\t\t";
  protected final String TEXT_54 = NL + "\t\t\t\t    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(" + NL + "\t\t\t\t    \tnew javax.net.ssl.HostnameVerifier(){" + NL + "\t\t\t\t      \t\tpublic boolean verify(String hostName,javax.net.ssl.SSLSession session)" + NL + "\t\t\t\t       \t\t\t{" + NL + "\t\t\t\t       \t\t\t\treturn true;" + NL + "\t\t\t\t       \t\t\t}" + NL + "\t\t\t\t     \t}" + NL + "\t\t\t\t    );" + NL + "\t\t\t\t";
  protected final String TEXT_55 = NL + "\t\t\t";
  protected final String TEXT_56 = NL + "\t    \tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NODES, ";
  protected final String TEXT_57 = ");" + NL + "\t    \t";
  protected final String TEXT_58 = NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_INPUT_JSON, \"true\");" + NL + "\t\t\t";
  protected final String TEXT_59 = NL + "\t\t\t";
  protected final String TEXT_60 = NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_HTTP_AUTH_USER, ";
  protected final String TEXT_61 = ");" + NL + "\t\t\t\tconfig.put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_HTTP_AUTH_PASS, ";
  protected final String TEXT_62 = ");" + NL + "\t\t\t";
  protected final String TEXT_63 = NL + NL + "\t\t\torg.elasticsearch.spark.rdd.api.java.JavaEsSpark.";
  protected final String TEXT_64 = "saveToEsWithMeta";
  protected final String TEXT_65 = "saveToEs";
  protected final String TEXT_66 = "(rdd, ";
  protected final String TEXT_67 = " + \"/\" + ";
  protected final String TEXT_68 = ", config);" + NL + "" + NL + "\t        ";
  protected final String TEXT_69 = NL + "\t    }" + NL + "\t}" + NL + "\t";
  protected final String TEXT_70 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
ElasticSearchSparkStorage storage = new ElasticSearchSparkStorage(node);

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if((metadatas!=null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
		columns = metadata.getListColumns();
	}
}
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

if(columns == null || columns.isEmpty() || conn == null){
	return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String connName = conn.getName();

    
String nodes = storage.getNodes();
String index = ElementParameterParser.getValue(node, "__INDEX__");
String type = ElementParameterParser.getValue(node, "__TYPE__");
boolean jsonDoc = "JSON".equals(ElementParameterParser.getValue(node, "__DOC_TYPE__"));
List<Map<String, String>> configuration = storage.getConfiguration();
List<Map<String, String>> docMetadatas = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__DOC_METADATA__");

Map<String, String> docMetadataMapping = new HashMap<String, String>();

org.talend.core.model.metadata.MetadataTable docColumnTable = new org.talend.core.model.metadata.MetadataTable(); //for non-metadata column

List<IMetadataColumn> docColumnList = new ArrayList<IMetadataColumn>();
docColumnTable.setListColumns(docColumnList);

for(Map<String, String> docMetadata : docMetadatas){
	if("true".equals(docMetadata.get("AS_META"))){
		docMetadataMapping.put(docMetadata.get("SCHEMA_COLUMN"), docMetadata.get("TYPE"));
	}else{
	    for(IMetadataColumn col : columns){
	        if(col.getLabel().equals(docMetadata.get("SCHEMA_COLUMN"))){
	            docColumnList.add(col);
	        }
	    }
	}
}
final boolean definedDocMetadata = docMetadataMapping.size() > 0;
String docRowStruct = "doc_" + connName + "Struct";

if(definedDocMetadata){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_5);
    
	    	for(String columnName : docMetadataMapping.keySet()){
		    
    stringBuffer.append(TEXT_6);
    stringBuffer.append(docMetadataMapping.get(columnName));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_8);
    
	    	}
	    	
    stringBuffer.append(TEXT_9);
    if(jsonDoc){
    stringBuffer.append(TEXT_10);
    }else{
    stringBuffer.append(TEXT_11);
    stringBuffer.append(docRowStruct);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(docRowStruct);
    stringBuffer.append(TEXT_13);
    
		    	for(IMetadataColumn docColumn : docColumnList){
		    		String docColumName = docColumn.getLabel();
		    		
    stringBuffer.append(TEXT_14);
    stringBuffer.append(docColumName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(docColumName);
    stringBuffer.append(TEXT_16);
    
		    	}
		    	
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    
}else{
	if(jsonDoc){
	
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_23);
    
	}
}

String rowType = jsonDoc ? "String" : inRowStruct;
String rddType = definedDocMetadata ? "org.apache.spark.api.java.JavaPairRDD<Object, Object>" : "org.apache.spark.api.java.JavaRDD<" + rowType + ">" ;

{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunction(
                    codeGenArgument.getSparkVersion(), rddType);

    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_29);
    
	    	for(Map<String, String> config : configuration){
			
    stringBuffer.append(TEXT_30);
    stringBuffer.append(config.get("KEY"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(config.get("VALUE"));
    stringBuffer.append(TEXT_32);
    
	    	}
	    	
    stringBuffer.append(TEXT_33);
    
			TSetKeystoreUtil tSetKeystoreUtil = storage.getTSetKeystoreUtil();
			if(tSetKeystoreUtil.useHTTPS()){
			
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
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
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_42);
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
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
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    if(!tSetKeystoreUtil.needVerifyName()){
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    
			}
			
    stringBuffer.append(TEXT_56);
    stringBuffer.append(nodes);
    stringBuffer.append(TEXT_57);
    if(jsonDoc){
    stringBuffer.append(TEXT_58);
    }
    stringBuffer.append(TEXT_59);
    if(storage.isUserAuthEnabled()) { 
    stringBuffer.append(TEXT_60);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_62);
    }
    stringBuffer.append(TEXT_63);
    if(definedDocMetadata){
    stringBuffer.append(TEXT_64);
    }else{
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(index);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_69);
    
} // Start ForeachRDD helper function

    stringBuffer.append(TEXT_70);
    return stringBuffer.toString();
  }
}
