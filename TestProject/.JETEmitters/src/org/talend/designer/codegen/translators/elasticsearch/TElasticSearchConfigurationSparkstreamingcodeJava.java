package org.talend.designer.codegen.translators.elasticsearch;

import java.util.Map;
import java.util.Map.Entry;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.spark.generator.storage.ElasticSearchSparkStorage;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;

public class TElasticSearchConfigurationSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TElasticSearchConfigurationSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TElasticSearchConfigurationSparkstreamingcodeJava result = new TElasticSearchConfigurationSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "public static class ";
  protected final String TEXT_4 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_5 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_6 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_7 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_8 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_9 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_10 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_11 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_12 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_13 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_14 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_15 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_16 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_17 = ");";
  protected final String TEXT_18 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_19 = ">(new ";
  protected final String TEXT_20 = "(context), config);" + NL + "\t}" + NL + "}";
  protected final String TEXT_21 = NL + NL + "public static class ";
  protected final String TEXT_22 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_23 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_24 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_25 = " create() throws Exception {" + NL + "\t\torg.elasticsearch.common.settings.Settings settings = " + NL + "\t\t";
  protected final String TEXT_26 = "\t" + NL + "\t\t\torg.elasticsearch.common.settings.ImmutableSettings.settingsBuilder() //" + NL + "\t\t";
  protected final String TEXT_27 = NL + "\t\t\torg.elasticsearch.common.settings.Settings.settingsBuilder() //" + NL + "\t\t";
  protected final String TEXT_28 = NL + "\t\t.put(\"cluster.name\", ";
  protected final String TEXT_29 = ") //" + NL + "\t\t\t";
  protected final String TEXT_30 = NL + "      \t.put(";
  protected final String TEXT_31 = ", ";
  protected final String TEXT_32 = ") //";
  protected final String TEXT_33 = NL + "\t\t\t.put(\"shield.user\", ";
  protected final String TEXT_34 = " +\":\"+ ";
  protected final String TEXT_35 = ") //";
  protected final String TEXT_36 = NL + "\t\t\t.put(\"shield.transport.ssl\", \"true\") //" + NL + "\t\t\t.put(\"shield.ssl.truststore.path\", ";
  protected final String TEXT_37 = ") //" + NL + "\t\t\t.put(\"shield.ssl.truststore.password\", ";
  protected final String TEXT_38 = ") //";
  protected final String TEXT_39 = NL + "\t\t\t\t.put(\"shield.ssl.hostname_verification\", \"false\") //";
  protected final String TEXT_40 = NL + "\t\t\t\t.put(\"shield.ssl.keystore.path\", ";
  protected final String TEXT_41 = ") //" + NL + "\t\t\t\t.put(\"shield.ssl.keystore.password\", ";
  protected final String TEXT_42 = ") //";
  protected final String TEXT_43 = NL + "\t\t.build();" + NL + "" + NL + "\t\t";
  protected final String TEXT_44 = "\t" + NL + "\t\t\torg.elasticsearch.client.transport.TransportClient transportClient = new org.elasticsearch.client.transport.TransportClient(settings);" + NL + "\t\t\t";
  protected final String TEXT_45 = NL + "\t\t\t\ttransportClient.addTransportAddress(new org.elasticsearch.common.transport.InetSocketTransportAddress(\"";
  protected final String TEXT_46 = "\", ";
  protected final String TEXT_47 = "));" + NL + "\t\t\t";
  protected final String TEXT_48 = NL + "\t\t\torg.elasticsearch.client.transport.TransportClient transportClient = org.elasticsearch.client.transport.TransportClient.builder()" + NL + "\t\t\t";
  protected final String TEXT_49 = NL + "\t\t\t.addPlugin(org.elasticsearch.shield.ShieldPlugin.class)" + NL + "\t\t\t";
  protected final String TEXT_50 = NL + "\t\t\t.settings(settings).build()" + NL + "\t\t\t";
  protected final String TEXT_51 = NL + "\t\t\t\t.addTransportAddresses(new org.elasticsearch.common.transport.InetSocketTransportAddress(java.net.InetAddress.getByName(\"";
  protected final String TEXT_52 = "\"), ";
  protected final String TEXT_53 = "))" + NL + "\t\t\t";
  protected final String TEXT_54 = NL + "\t\t\t;\t" + NL + "\t\t";
  protected final String TEXT_55 = NL + "\t\treturn transportClient;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_56 = "> wrap(";
  protected final String TEXT_57 = " client) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_58 = ">(client);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_59 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "}";
  protected final String TEXT_60 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final ElasticSearchSparkStorage storage = new ElasticSearchSparkStorage(node);
final TSetKeystoreUtil tSetKeystoreUtil = storage.getTSetKeystoreUtil();

    stringBuffer.append(TEXT_2);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_3);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_13);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_17);
    
		}

    stringBuffer.append(TEXT_18);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_20);
    
	// Pooled object factory code. This factory creates Elasticsearch transport clients when requested by a connection pool. 
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create Elasticsearch transport clients without relying on a dedicated Spark connector.
	
	// If this file is included, then spark_pool.javajet must be included as well.

    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_25);
    if(ElasticSearchSparkStorage.VERSION.ES_1_7 == storage.getVersion()){
    stringBuffer.append(TEXT_26);
    }else if(ElasticSearchSparkStorage.VERSION.ES_2_3 == storage.getVersion()){
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getClusterName());
    stringBuffer.append(TEXT_29);
    
		for(java.util.Map<String, String> config : storage.getConfiguration()) {

    stringBuffer.append(TEXT_30);
    stringBuffer.append(config.get("KEY"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(config.get("VALUE"));
    stringBuffer.append(TEXT_32);
    
		}
		
		if(storage.isUserAuthEnabled()) {

    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_35);
    
		}	
		if(tSetKeystoreUtil.useHTTPS()) {

    stringBuffer.append(TEXT_36);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_38);
    
			if(!tSetKeystoreUtil.needVerifyName()) {

    stringBuffer.append(TEXT_39);
    
			}
			
			if(tSetKeystoreUtil.needClientAuth()) {

    stringBuffer.append(TEXT_40);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_42);
    
			}
		} // end if(tSetKeystoreUtil.useHTTPS())

    stringBuffer.append(TEXT_43);
    if(ElasticSearchSparkStorage.VERSION.ES_1_7 == storage.getVersion()){
    stringBuffer.append(TEXT_44);
    
			for(java.util.Map.Entry<String, Integer> transportAddress : storage.getTransportAddresses().entrySet()) {
			
    stringBuffer.append(TEXT_45);
    stringBuffer.append(transportAddress.getKey());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(transportAddress.getValue());
    stringBuffer.append(TEXT_47);
    
			}
		}else if(ElasticSearchSparkStorage.VERSION.ES_2_3 == storage.getVersion()){
    stringBuffer.append(TEXT_48);
    if(storage.isUserAuthEnabled() || tSetKeystoreUtil.useHTTPS()){
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    
			for(java.util.Map.Entry<String, Integer> transportAddress : storage.getTransportAddresses().entrySet()) {
			
    stringBuffer.append(TEXT_51);
    stringBuffer.append(transportAddress.getKey());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(transportAddress.getValue());
    stringBuffer.append(TEXT_53);
    
			}
			
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    return stringBuffer.toString();
  }
}
