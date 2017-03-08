package org.talend.designer.codegen.translators.messaging.momandjms;

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
import org.talend.designer.spark.generator.storage.JMSSparkStorage;

public class TJMSOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TJMSOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJMSOutputSparkstreamingcodeJava result = new TJMSOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + NL + "public static class ";
  protected final String TEXT_5 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_6 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_7 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_8 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_9 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_10 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_11 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_12 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_13 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_14 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_15 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_16 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_17 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_20 = ">(new ";
  protected final String TEXT_21 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_22 = " {" + NL + "" + NL + "\tprivate javax.jms.Session session;" + NL + "" + NL + "\tprivate javax.jms.MessageProducer producer;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_23 = "(javax.jms.Session session, javax.jms.MessageProducer producer) {" + NL + "    \tthis.session = session;" + NL + "    \tthis.producer = producer;" + NL + "\t}" + NL + "" + NL + "\tpublic javax.jms.Session getSession() {" + NL + "\t\treturn this.session;" + NL + "\t}" + NL + "" + NL + "\tpublic javax.jms.MessageProducer getProducer() {" + NL + "\t\treturn this.producer;" + NL + "\t}" + NL + "" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_24 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_25 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\t// Hold references to JMS objects in order to avoid unwanted garbage collection and make them reusable during the run" + NL + "\tprivate javax.jms.Connection connection;" + NL + "" + NL + "\tprivate javax.jms.Session session;" + NL + "" + NL + "\tprivate javax.jms.Destination destination;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_26 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;";
  protected final String TEXT_27 = NL + "    \t\tinitSSL();";
  protected final String TEXT_28 = NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_29 = " create() throws java.lang.Exception {" + NL + "\t\tcreateJMSConnectionIfNecessary();" + NL + "    \tjavax.jms.MessageProducer producer = session.createProducer(destination);" + NL + "    \tproducer.setDeliveryMode(javax.jms.DeliveryMode.";
  protected final String TEXT_30 = ");" + NL + "    \treturn new ";
  protected final String TEXT_31 = "(this.session, producer);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_32 = "> wrap(";
  protected final String TEXT_33 = " producer) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_34 = ">(producer);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_35 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t    \t  // Only close the given producer, not the session which is shared between producers" + NL + "\t        pooledObject.getObject().getProducer().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "" + NL + "\tprivate void createJMSConnectionIfNecessary() throws java.lang.Exception {" + NL + "\t\tif(this.connection == null) {" + NL + "\t\t\tthis.connection = createConnection();" + NL + "\t    \tthis.session = connection.createSession(false, javax.jms.Session.AUTO_ACKNOWLEDGE);" + NL + "\t    \tthis.destination = session.create";
  protected final String TEXT_36 = "(";
  protected final String TEXT_37 = ");" + NL + "    \t}" + NL + "\t}" + NL + "" + NL + "\tprivate javax.jms.Connection createConnection() throws java.lang.Exception {" + NL + "\t\tjava.util.Hashtable props_";
  protected final String TEXT_38 = " = new java.util.Hashtable();" + NL + "    \tprops_";
  protected final String TEXT_39 = ".put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, ";
  protected final String TEXT_40 = ");" + NL + "    \tprops_";
  protected final String TEXT_41 = ".put(javax.naming.Context.PROVIDER_URL, ";
  protected final String TEXT_42 = ");" + NL + "    \t";
  protected final String TEXT_43 = NL + "    \t\t\tprops_";
  protected final String TEXT_44 = ".put(";
  protected final String TEXT_45 = ", ";
  protected final String TEXT_46 = ");" + NL + "\t\t\t";
  protected final String TEXT_47 = NL + NL + "    \tjavax.naming.Context context_";
  protected final String TEXT_48 = " = new javax.naming.InitialContext(props_";
  protected final String TEXT_49 = ");" + NL + "    \tjavax.jms.ConnectionFactory factory_";
  protected final String TEXT_50 = " = (javax.jms.ConnectionFactory) context_";
  protected final String TEXT_51 = ".lookup(";
  protected final String TEXT_52 = ");" + NL + "" + NL + "    \tjavax.jms.Connection connection_";
  protected final String TEXT_53 = " = factory_";
  protected final String TEXT_54 = ".createConnection(";
  protected final String TEXT_55 = ");" + NL + "    \tconnection_";
  protected final String TEXT_56 = ".start();" + NL + "    \treturn connection_";
  protected final String TEXT_57 = ";" + NL + "\t}" + NL;
  protected final String TEXT_58 = NL + "\t\tprivate void initSSL() {" + NL + "\t\t\tSystem.setProperty(\"javax.net.ssl.trustStore\", ";
  protected final String TEXT_59 = ");" + NL + "\t\t\ttry{" + NL + "\t    \t\tnew java.io.FileInputStream(new java.io.File(System.getProperty(\"javax.net.ssl.trustStore\")));" + NL + "\t\t\t}catch(Exception e){" + NL + "\t   \t\te.printStackTrace();" + NL + "\t\t\t}" + NL + "\t\t\tSystem.setProperty(\"javax.net.ssl.trustStoreType\", ";
  protected final String TEXT_60 = ");" + NL + "\t\t\tSystem.setProperty(\"javax.net.ssl.trustStorePassword\", ";
  protected final String TEXT_61 = ");" + NL + "\t\t\t";
  protected final String TEXT_62 = NL + "\t\t\t\tSystem.setProperty(\"javax.net.ssl.keyStore\", ";
  protected final String TEXT_63 = ");" + NL + "\t\t\t    try{" + NL + "\t\t\t\t\tnew java.io.FileInputStream(new java.io.File(System.getProperty(\"javax.net.ssl.keyStore\")));" + NL + "\t\t\t    }catch(Exception e){" + NL + "\t\t\t   \t\te.printStackTrace();" + NL + "\t\t\t    }" + NL + "\t\t\t\tSystem.setProperty(\"javax.net.ssl.keyStoreType\", ";
  protected final String TEXT_64 = ");" + NL + "\t\t\t\tSystem.setProperty(\"javax.net.ssl.keyStorePassword\", ";
  protected final String TEXT_65 = ");" + NL + "\t    \t";
  protected final String TEXT_66 = NL + "\t\t\t";
  protected final String TEXT_67 = NL + "\t\t\t\tjavax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(" + NL + "\t\t\t\t\tnew javax.net.ssl.HostnameVerifier(){" + NL + "\t\t\t\t  \t\tpublic boolean verify(String hostName,javax.net.ssl.SSLSession session)" + NL + "\t\t\t\t   \t\t\t{" + NL + "\t\t\t\t   \t\t\t\treturn true;" + NL + "\t\t\t\t   \t\t\t}" + NL + "\t\t\t\t \t}" + NL + "\t\t\t\t);" + NL + "\t\t\t";
  protected final String TEXT_68 = NL + "\t\t}";
  protected final String TEXT_69 = NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_70 = "_OutputFunction" + NL + "    implements org.apache.spark.api.java.function.VoidFunction<java.util.Iterator<";
  protected final String TEXT_71 = ">>{" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public ";
  protected final String TEXT_72 = "_OutputFunction(ContextProperties context){" + NL + "    \tthis.context = context;" + NL + "    }" + NL + "" + NL + "    public void call(java.util.Iterator<";
  protected final String TEXT_73 = "> dataIterator) throws java.lang.Exception {" + NL + "    \t";
  protected final String TEXT_74 = " talendJMSWrapper_";
  protected final String TEXT_75 = " = ";
  protected final String TEXT_76 = ".get(context).borrowObject();" + NL + "      javax.jms.MessageProducer producer_";
  protected final String TEXT_77 = " = talendJMSWrapper_";
  protected final String TEXT_78 = ".getProducer();" + NL + "      javax.jms.Session session_";
  protected final String TEXT_79 = " = talendJMSWrapper_";
  protected final String TEXT_80 = ".getSession();" + NL + "" + NL + "      try {" + NL + "\t\t\twhile(dataIterator.hasNext()){" + NL + "\t\t\t\t";
  protected final String TEXT_81 = " ";
  protected final String TEXT_82 = " = dataIterator.next();" + NL + "\t\t\t\t";
  protected final String TEXT_83 = NL + "\t\t\t\t\tproducer_";
  protected final String TEXT_84 = ".send((javax.jms.Message)";
  protected final String TEXT_85 = ".message);" + NL + "\t         ";
  protected final String TEXT_86 = NL + "\t                    javax.jms.ObjectMessage message_";
  protected final String TEXT_87 = " = session_";
  protected final String TEXT_88 = ".createObjectMessage();" + NL + "\t                    message_";
  protected final String TEXT_89 = ".setObject(";
  protected final String TEXT_90 = ".messageContent);" + NL + "\t            \t";
  protected final String TEXT_91 = NL + "\t                    javax.jms.TextMessage message_";
  protected final String TEXT_92 = " = session_";
  protected final String TEXT_93 = ".createTextMessage();" + NL + "\t                    message_";
  protected final String TEXT_94 = ".setText(";
  protected final String TEXT_95 = ".messageContent);" + NL + "\t            \t";
  protected final String TEXT_96 = NL + "\t            \tproducer_";
  protected final String TEXT_97 = ".send(message_";
  protected final String TEXT_98 = ");" + NL + "\t         ";
  protected final String TEXT_99 = NL + "\t    \t}" + NL + "\t\t} finally {" + NL + "\t\t\tif(talendJMSWrapper_";
  protected final String TEXT_100 = " != null) {" + NL + "\t\t\t\t";
  protected final String TEXT_101 = ".get(context).returnObject(talendJMSWrapper_";
  protected final String TEXT_102 = ");" + NL + "\t\t\t}" + NL + "\t   }" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_103 = NL + NL + "    public static class ";
  protected final String TEXT_104 = "_ForeachRDDOutput implements ";
  protected final String TEXT_105 = " {" + NL + "" + NL + "        private ContextProperties context;" + NL + "" + NL + "        public ";
  protected final String TEXT_106 = "_ForeachRDDOutput(JobConf conf) {" + NL + "            this.context = new ContextProperties(conf);" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public ";
  protected final String TEXT_107 = " call(";
  protected final String TEXT_108 = " rdd) throws Exception {" + NL + "            rdd.foreachPartition(new ";
  protected final String TEXT_109 = "_OutputFunction(context));";
  protected final String TEXT_110 = NL + "            ";
  protected final String TEXT_111 = NL + "        }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final JMSSparkStorage storage = new JMSSparkStorage(node);
final	TSetKeystoreUtil tSetKeystoreUtil = new TSetKeystoreUtil(node);

    stringBuffer.append(TEXT_2);
    
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

String contextProvider=ElementParameterParser.getValue(node, "__CONTEXT_PROVIDER__");
String connFacName=ElementParameterParser.getValue(node, "__CONN_FACTORY_NAME__");
String url=ElementParameterParser.getValue(node, "__SERVER_URL__");
boolean userIdentity=ElementParameterParser.getBooleanValue(node, "__USER_IDENTITY__");
String user=ElementParameterParser.getValue(node, "__USER__");

String to=ElementParameterParser.getValue(node, "__TO__");
String deliverMode = ElementParameterParser.getValue(node, "__DELIVERY_MODE__");
String msgType = ElementParameterParser.getValue(node, "__MSGTYPE__");

List<Map<String, String>> advProps = ElementParameterParser.getTableValue(node, "__ADVANCED_PROPERTIES__");
String processingMode = ElementParameterParser.getValue(node, "__PROCESSING_MODE__");

    stringBuffer.append(TEXT_3);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_4);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_14);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_15);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_18);
    
		}

    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_26);
    
		if(tSetKeystoreUtil.useHTTPS()){

    stringBuffer.append(TEXT_27);
    
    	}

    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(deliverMode);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(msgType);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(to);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(contextProvider);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_42);
    
    	if(advProps.size() > 0){
    		for(Map<String, String> item : advProps){
    		
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(item.get("PROPERTY"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(item.get("VALUE"));
    stringBuffer.append(TEXT_46);
    
    		}
    	}
    	
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(connFacName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(userIdentity ? user + "," + ElementParameterParser.getPasswordValue(node, "__PASS__") : "" );
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    
	if(tSetKeystoreUtil.useHTTPS()){

    stringBuffer.append(TEXT_58);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(tSetKeystoreUtil.getTrustStoreType());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_61);
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_62);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    if(!tSetKeystoreUtil.needVerifyName()){
    stringBuffer.append(TEXT_67);
    }
    stringBuffer.append(TEXT_68);
    
}

    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_82);
    
				if("RAW".equals(processingMode)){
				
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_85);
    
				}else{
					if("id_Document".equals(metadata.getColumn("messageContent").getTalendType())){
	            	
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_90);
    
	            	}else{
	            	
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_95);
    
	            	}
	            	
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    
				}
	         
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaRDD(
                    codeGenArgument.getSparkVersion(), inRowStruct);
    
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_111);
    
} // End ForeachRDD helper function

    return stringBuffer.toString();
  }
}
