package org.talend.designer.codegen.translators.messaging.kafka;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tkafkaoutput.TKafkaOutputUtil;
import org.talend.designer.spark.generator.storage.KafkaSparkStorage;

public class TKafkaOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TKafkaOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKafkaOutputSparkstreamingcodeJava result = new TKafkaOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\tkafkaProperties = new java.util.Properties();";
  protected final String TEXT_3 = NL + "\t\t\tif(true){" + NL + "\t\t\t\tthrow new Exception(\"A broker list must be provided.\");" + NL + "\t\t\t}";
  protected final String TEXT_4 = NL + "\t\t\tkafkaProperties.setProperty(\"bootstrap.servers\", ";
  protected final String TEXT_5 = ");" + NL + "\t\t\tkafkaProperties.setProperty(\"compression.type\", \"";
  protected final String TEXT_6 = "\");";
  protected final String TEXT_7 = NL + "\t\t\t\tkafkaProperties.setProperty(";
  protected final String TEXT_8 = ", ";
  protected final String TEXT_9 = ");";
  protected final String TEXT_10 = NL + "\t\tkafkaTopics = new java.util.HashSet<String>();";
  protected final String TEXT_11 = NL + NL + "\t\t\tif(true){" + NL + "\t\t\t\tthrow new Exception(\"At least one Kafka topic must be provided.\");" + NL + "\t\t\t}";
  protected final String TEXT_12 = NL + "\t\t\t\tkafkaTopics.add(";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + NL + "public static class ";
  protected final String TEXT_17 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_18 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_19 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_20 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_21 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_22 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_23 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_24 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_25 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_26 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_27 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_28 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_29 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_30 = ");";
  protected final String TEXT_31 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_32 = ">(new ";
  protected final String TEXT_33 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_34 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_35 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tprivate java.util.Properties kafkaProperties;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_36 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;";
  protected final String TEXT_37 = NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_38 = " create() throws java.lang.Exception {" + NL + "\t\treturn new ";
  protected final String TEXT_39 = "(this.kafkaProperties, new org.apache.kafka.common.serialization.ByteArraySerializer(), new org.apache.kafka.common.serialization.ByteArraySerializer());" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_40 = "> wrap(";
  protected final String TEXT_41 = " producer) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_42 = ">(producer);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_43 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_44 = "_ForeachRDD<KEY> implements ";
  protected final String TEXT_45 = " {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_46 = "_ForeachRDD(JobConf job){" + NL + "\t\tthis.context = new ContextProperties(job);" + NL + "\t}" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_47 = " call(org.apache.spark.api.java.JavaPairRDD<KEY,";
  protected final String TEXT_48 = "> rdd) throws java.lang.Exception {" + NL + "\t\trdd.foreachPartition(new ";
  protected final String TEXT_49 = "_ForeachPartition<KEY>(context));" + NL + "\t\t";
  protected final String TEXT_50 = NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_51 = "_ForeachPartition<KEY> implements org.apache.spark.api.java.function.VoidFunction<java.util.Iterator<scala.Tuple2<KEY,";
  protected final String TEXT_52 = ">>> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tprivate java.util.Set<String> kafkaTopics;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_53 = "_ForeachPartition(ContextProperties context){" + NL + "\t\tthis.context = context;";
  protected final String TEXT_54 = NL + "\t}" + NL + "" + NL + "\tpublic void call(java.util.Iterator<scala.Tuple2<KEY,";
  protected final String TEXT_55 = ">> iterator) throws java.lang.Exception {" + NL + "\t\t";
  protected final String TEXT_56 = " kafkaProducer = ";
  protected final String TEXT_57 = ".get(context).borrowObject();" + NL + "\t\tscala.Tuple2<KEY,";
  protected final String TEXT_58 = "> tuple;" + NL + "\t\ttry {" + NL + "\t\t\twhile(iterator.hasNext()) {" + NL + "\t\t\t\ttuple = iterator.next();" + NL + "\t\t\t\tif(tuple._2() != null){" + NL + "\t\t\t\t\tfor(String topic : kafkaTopics){" + NL + "\t\t\t\t\t\tjava.nio.ByteBuffer byteBuffer = tuple._2().";
  protected final String TEXT_59 = ";" + NL + "\t\t\t\t\t\tif(byteBuffer != null){" + NL + "\t\t\t\t\t\t\tbyte[] value = new byte[byteBuffer.remaining()];" + NL + "\t\t\t\t\t\t\tbyteBuffer.get(value);" + NL + "\t\t\t\t\t\t\tkafkaProducer.send(new org.apache.kafka.clients.producer.ProducerRecord<byte[], byte[]>(topic, value));" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t} finally {" + NL + "\t\t\tif(kafkaProducer != null) {" + NL + "\t\t\t\t";
  protected final String TEXT_60 = ".get(context).returnObject(kafkaProducer);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "}" + NL + NL;
  protected final String TEXT_61 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
final class TKafkaOutputHelper {

	private TKafkaOutputUtil tKafkaOutputUtil;
	
	public TKafkaOutputHelper(TKafkaOutputUtil util){
		tKafkaOutputUtil = util;
	}

	public void generateKafkaProperties() {

    stringBuffer.append(TEXT_2);
    
		if(tKafkaOutputUtil.getBrokerList() == null || "".equals(tKafkaOutputUtil.getBrokerList())){

    stringBuffer.append(TEXT_3);
     
		} else {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(tKafkaOutputUtil.getBrokerList());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(tKafkaOutputUtil.getCompression());
    stringBuffer.append(TEXT_6);
    
			for(java.util.Map.Entry<String, String> kafkaProperty : tKafkaOutputUtil.getKafkaProducerProperties().entrySet()) {

    stringBuffer.append(TEXT_7);
    stringBuffer.append(kafkaProperty.getKey());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(kafkaProperty.getValue());
    stringBuffer.append(TEXT_9);
    
			} // end for
		} // end else
	} // end generateKafkaProperties
	
	public void generateKafkaTopics() {

    stringBuffer.append(TEXT_10);
    
		if(tKafkaOutputUtil.getKafkaTopics().isEmpty()){

    stringBuffer.append(TEXT_11);
    
		}else {
			for(String kafkaTopic : tKafkaOutputUtil.getKafkaTopics()) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(kafkaTopic);
    stringBuffer.append(TEXT_13);
    
			} // end for
		} // end else
	} // end generateKafkaTopics
	
} // end class TKafkaOutputHelper

    stringBuffer.append(TEXT_14);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final TKafkaOutputUtil tKafkaOutputUtil = new TKafkaOutputUtil(node);
final TKafkaOutputHelper tKafkaOutputHelper = new TKafkaOutputHelper(tKafkaOutputUtil);
final KafkaSparkStorage storage = new KafkaSparkStorage(node);

final String cid = node.getUniqueName();
String inStructName = codeGenArgument.getRecordStructName(tKafkaOutputUtil.getIncomingConnection());

final String foreachClass;
final String foreachReturnClass;
final String foreachReturn;
if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0) {
    foreachClass = "org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaPairRDD<KEY, " + inStructName + ">, Void>";
    foreachReturnClass = "Void";
    foreachReturn = "return null;";
} else {
    foreachClass = "org.apache.spark.api.java.function.VoidFunction<org.apache.spark.api.java.JavaPairRDD<KEY," + inStructName + ">>";
    foreachReturnClass = "void";
    foreachReturn = "";
}

    stringBuffer.append(TEXT_15);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_16);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_26);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_30);
    
		}

    stringBuffer.append(TEXT_31);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_36);
    
		tKafkaOutputHelper.generateKafkaProperties();

    stringBuffer.append(TEXT_37);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(foreachClass);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(foreachReturnClass);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(foreachReturn);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    
		tKafkaOutputHelper.generateKafkaTopics();

    stringBuffer.append(TEXT_54);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(tKafkaOutputUtil.getIncomingColumnName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(TEXT_61);
    return stringBuffer.toString();
  }
}
