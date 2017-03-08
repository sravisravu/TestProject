package org.talend.designer.codegen.translators.elasticsearch;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.telasticsearchinput.TElasticSearchInputUtil;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;
import org.talend.designer.spark.generator.storage.ElasticSearchSparkStorage;

public class TElasticSearchInputSparkconfigJava
{
  protected static String nl;
  public static synchronized TElasticSearchInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TElasticSearchInputSparkconfigJava result = new TElasticSearchInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "// Build ES configuration" + NL + "java.util.Map<String, String> config_";
  protected final String TEXT_2 = " = new java.util.HashMap<String, String>();";
  protected final String TEXT_3 = NL + "\tconfig_";
  protected final String TEXT_4 = ".put(";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = ");";
  protected final String TEXT_7 = NL + "\tconfig_";
  protected final String TEXT_8 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_HTTP_AUTH_USER, ";
  protected final String TEXT_9 = ");" + NL + "\tconfig_";
  protected final String TEXT_10 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_HTTP_AUTH_PASS, ";
  protected final String TEXT_11 = ");";
  protected final String TEXT_12 = NL + "\tconfig_";
  protected final String TEXT_13 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_USE_SSL, \"true\");" + NL + "\tconfig_";
  protected final String TEXT_14 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_CERT_ALLOW_SELF_SIGNED, \"true\");" + NL + "\tString es_truststorepath_";
  protected final String TEXT_15 = " = ";
  protected final String TEXT_16 = ";" + NL + "\tif(es_truststorepath_";
  protected final String TEXT_17 = " != null && !es_truststorepath_";
  protected final String TEXT_18 = ".startsWith(\"file:\")){" + NL + "\t\tes_truststorepath_";
  protected final String TEXT_19 = " = \"file://\" + es_truststorepath_";
  protected final String TEXT_20 = ";" + NL + "\t}" + NL + "\tconfig_";
  protected final String TEXT_21 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_TRUST_STORE_LOCATION, es_truststorepath_";
  protected final String TEXT_22 = ");" + NL + "\tconfig_";
  protected final String TEXT_23 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_TRUST_STORE_PASS, ";
  protected final String TEXT_24 = ");";
  protected final String TEXT_25 = NL + "\t\tString es_keystorepath_";
  protected final String TEXT_26 = " = ";
  protected final String TEXT_27 = ";" + NL + "\t\tif(es_keystorepath_";
  protected final String TEXT_28 = " != null && !es_keystorepath_";
  protected final String TEXT_29 = ".startsWith(\"file:\")){" + NL + "\t\t\tes_keystorepath_";
  protected final String TEXT_30 = " = \"file://\" + es_keystorepath_";
  protected final String TEXT_31 = ";" + NL + "\t\t}" + NL + "\t\tconfig_";
  protected final String TEXT_32 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_LOCATION, es_keystorepath_";
  protected final String TEXT_33 = ");" + NL + "\t\tconfig_";
  protected final String TEXT_34 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_PASS, ";
  protected final String TEXT_35 = ");" + NL + "\t\tconfig_";
  protected final String TEXT_36 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NET_SSL_KEYSTORE_TYPE, ";
  protected final String TEXT_37 = ");";
  protected final String TEXT_38 = NL + "\tjavax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(" + NL + "\t\tnew javax.net.ssl.HostnameVerifier(){" + NL + "\t  \t\tpublic boolean verify(String hostName,javax.net.ssl.SSLSession session)" + NL + "\t   \t\t\t{" + NL + "\t   \t\t\t\treturn true;" + NL + "\t   \t\t\t}" + NL + "\t \t}" + NL + "\t);";
  protected final String TEXT_39 = NL + "\tconfig_";
  protected final String TEXT_40 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_QUERY, ";
  protected final String TEXT_41 = ");";
  protected final String TEXT_42 = NL + "config_";
  protected final String TEXT_43 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_NODES, ";
  protected final String TEXT_44 = ");" + NL + "config_";
  protected final String TEXT_45 = ".put(org.elasticsearch.hadoop.cfg.ConfigurationOptions.ES_RESOURCE, ";
  protected final String TEXT_46 = " + \"/\" + ";
  protected final String TEXT_47 = ");" + NL + "" + NL + "// Query ES and build RDD" + NL + "org.apache.spark.api.java.JavaPairRDD<String, String> esRdd_";
  protected final String TEXT_48 = " = org.elasticsearch.spark.rdd.api.java.JavaEsSpark.esJsonRDD(ctx, config_";
  protected final String TEXT_49 = ");" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_50 = "> rdd_";
  protected final String TEXT_51 = " = esRdd_";
  protected final String TEXT_52 = ".map(new ";
  protected final String TEXT_53 = "_FromEsTo";
  protected final String TEXT_54 = "());" + NL;
  protected final String TEXT_55 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

TElasticSearchInputUtil tElasticSearchInputUtil = new TElasticSearchInputUtil(node);
ElasticSearchSparkStorage storage = new ElasticSearchSparkStorage(node);
TSetKeystoreUtil tSetKeystoreUtil = storage.getTSetKeystoreUtil();

String outStructName = codeGenArgument.getRecordStructName(tElasticSearchInputUtil.getOutgoingConnection());

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    
for(Map<String, String> config : storage.getConfiguration()) {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(config.get("KEY"));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(config.get("VALUE"));
    stringBuffer.append(TEXT_6);
    
}

if(storage.isUserAuthEnabled()) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_11);
    
}

if(tSetKeystoreUtil.useHTTPS()){

    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_24);
    
	if(tSetKeystoreUtil.needClientAuth()){

    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_37);
    
	}
	if(!tSetKeystoreUtil.needVerifyName()){

    stringBuffer.append(TEXT_38);
    
	}
} // end if(tSetKeystoreUtil.useHTTPS())
if(tElasticSearchInputUtil.getQuery() != null && !"".equals(tElasticSearchInputUtil.getQuery())) {

    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(tElasticSearchInputUtil.getQuery());
    stringBuffer.append(TEXT_41);
    
}

    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(storage.getNodes());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(tElasticSearchInputUtil.getIndex());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(tElasticSearchInputUtil.getType());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(tElasticSearchInputUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(TEXT_55);
    return stringBuffer.toString();
  }
}
