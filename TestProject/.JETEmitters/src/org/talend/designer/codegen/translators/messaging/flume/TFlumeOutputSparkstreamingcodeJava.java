package org.talend.designer.codegen.translators.messaging.flume;

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
import org.talend.designer.spark.generator.storage.FlumeSparkStorage;

public class TFlumeOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TFlumeOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFlumeOutputSparkstreamingcodeJava result = new TFlumeOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "public static class ";
  protected final String TEXT_3 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_4 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_5 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_6 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_7 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_8 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_9 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_10 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_11 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_12 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_13 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_14 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_15 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_16 = ");";
  protected final String TEXT_17 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_18 = ">(new ";
  protected final String TEXT_19 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_20 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_21 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_22 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_23 = " create() throws java.lang.Exception {" + NL + "\t\treturn org.apache.flume.api.RpcClientFactory.getDefaultInstance(";
  protected final String TEXT_24 = ", ";
  protected final String TEXT_25 = ");" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_26 = "> wrap(";
  protected final String TEXT_27 = " client) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_28 = ">(client);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_29 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_30 = "_OutputFunction" + NL + "    implements org.apache.spark.api.java.function.VoidFunction<java.util.Iterator<";
  protected final String TEXT_31 = ">>{" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public ";
  protected final String TEXT_32 = "_OutputFunction(ContextProperties context){" + NL + "    \tthis.context = context;" + NL + "    }" + NL + "" + NL + "    public void call(java.util.Iterator<";
  protected final String TEXT_33 = "> dataIterator) throws java.lang.Exception{" + NL + "\t\t";
  protected final String TEXT_34 = " client = ";
  protected final String TEXT_35 = ".get(context).borrowObject();" + NL + "" + NL + "\t\ttry {" + NL + "\t\t\twhile(dataIterator.hasNext()){" + NL + "\t\t\t\t";
  protected final String TEXT_36 = " ";
  protected final String TEXT_37 = " = dataIterator.next();" + NL + "\t\t\t\t";
  protected final String TEXT_38 = NL + "            \tjava.util.Map<String, String> headers = new java.util.HashMap<String, String>();" + NL + "            \t";
  protected final String TEXT_39 = NL + "            \t\theaders.put(\"";
  protected final String TEXT_40 = "\", String.valueOf(";
  protected final String TEXT_41 = ".";
  protected final String TEXT_42 = "));" + NL + "            \t";
  protected final String TEXT_43 = NL + "            try{" + NL + "            \tclient.append(org.apache.flume.event.EventBuilder.withBody(";
  protected final String TEXT_44 = ".line, java.nio.charset.Charset.forName(";
  protected final String TEXT_45 = ")";
  protected final String TEXT_46 = ", headers";
  protected final String TEXT_47 = "));" + NL + "            }catch(org.apache.flume.EventDeliveryException e){" + NL + "            \t// We want a fresh client instance to replace the current one inside the pool" + NL + "            \tclient.close();" + NL + "            \tclient = org.apache.flume.api.RpcClientFactory.getDefaultInstance(";
  protected final String TEXT_48 = ", ";
  protected final String TEXT_49 = ");" + NL + "            }" + NL + "    \t\t}" + NL + "    \t} finally {" + NL + "\t\t\tif(client != null) {" + NL + "\t\t\t\t";
  protected final String TEXT_50 = ".get(context).returnObject(client);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_51 = NL + NL + "    public static class ";
  protected final String TEXT_52 = "_ForeachRDDOutput implements ";
  protected final String TEXT_53 = " {" + NL + "" + NL + "        private ContextProperties context;" + NL + "" + NL + "        public ";
  protected final String TEXT_54 = "_ForeachRDDOutput(JobConf conf) {" + NL + "            this.context = new ContextProperties(conf);" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public ";
  protected final String TEXT_55 = " call(";
  protected final String TEXT_56 = " rdd) throws Exception {" + NL + "            rdd.foreachPartition(new ";
  protected final String TEXT_57 = "_OutputFunction(context));";
  protected final String TEXT_58 = NL + "            ";
  protected final String TEXT_59 = NL + "        }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final FlumeSparkStorage storage = new FlumeSparkStorage(node);

    
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
String host = ElementParameterParser.getValue(node, "__HOST__");
String port = ElementParameterParser.getValue(node, "__PORT__");
String encoding = ElementParameterParser.getValue(node, "__ENCODING__");

    stringBuffer.append(TEXT_1);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_2);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_12);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_13);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_16);
    
		}

    stringBuffer.append(TEXT_17);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(host);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(port);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_37);
    
        		List<IMetadataColumn> headerKeys = new ArrayList<IMetadataColumn>();
            for(IMetadataColumn column : columns){
            	String columnName = column.getLabel();
            	if("line".equals(columnName)){
            		continue;
            	}
            	headerKeys.add(column);
            }
            if(headerKeys.size() > 0){
            
    stringBuffer.append(TEXT_38);
    
            	for(IMetadataColumn column : headerKeys){
            		String columnName = column.getLabel();
            		
    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_42);
    
            	}
            	
    
            }
            
    stringBuffer.append(TEXT_43);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(encoding);
    stringBuffer.append(TEXT_45);
    if(headerKeys.size() > 0){
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(host);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(port);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_50);
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaRDD(
                    codeGenArgument.getSparkVersion(), inRowStruct);
    
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_59);
    
} // End ForeachRDD helper function

    return stringBuffer.toString();
  }
}
