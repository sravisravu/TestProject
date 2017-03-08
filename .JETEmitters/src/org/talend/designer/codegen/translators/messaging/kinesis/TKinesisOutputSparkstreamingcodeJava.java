package org.talend.designer.codegen.translators.messaging.kinesis;

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
import org.talend.designer.common.output.TKinesisOutputUtil;
import org.talend.designer.spark.generator.storage.KinesisSparkStorage;

public class TKinesisOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TKinesisOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKinesisOutputSparkstreamingcodeJava result = new TKinesisOutputSparkstreamingcodeJava();
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
  protected final String TEXT_20 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_21 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_22 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_23 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_24 = " create() throws java.lang.Exception {";
  protected final String TEXT_25 = NL + "\t\t//Get the password under the variable decryptedPassword" + NL + "\t\t";
  protected final String TEXT_26 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_27 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_30 = " = ";
  protected final String TEXT_31 = "; ";
  protected final String TEXT_32 = NL + "\t\t";
  protected final String TEXT_33 = " client = new ";
  protected final String TEXT_34 = "(" + NL + "                new com.amazonaws.auth.BasicAWSCredentials(";
  protected final String TEXT_35 = ", decryptedPassword_";
  protected final String TEXT_36 = "));" + NL + "\t\tclient.setEndpoint(";
  protected final String TEXT_37 = ");" + NL + "\t\treturn client;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_38 = "> wrap(";
  protected final String TEXT_39 = " client) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_40 = ">(client);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_41 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().shutdown();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_42 = "_ForeachPartition_KinesisMessageSender<KEY> implements org.apache.spark.api.java.function.VoidFunction<java.util.Iterator<scala.Tuple2<KEY,";
  protected final String TEXT_43 = ">>> {" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public ";
  protected final String TEXT_44 = "_ForeachPartition_KinesisMessageSender(ContextProperties context){" + NL + "        this.context = context;" + NL + "    }" + NL + "" + NL + "    public void call(java.util.Iterator<scala.Tuple2<KEY,";
  protected final String TEXT_45 = ">> iterator) throws java.lang.Exception {";
  protected final String TEXT_46 = NL + "        ";
  protected final String TEXT_47 = " client = ";
  protected final String TEXT_48 = ".get(context).borrowObject();" + NL + "        Integer shard = 0;" + NL + "" + NL + "        scala.Tuple2<KEY, ";
  protected final String TEXT_49 = "> tuple;" + NL + "        try {" + NL + "\t        while (iterator.hasNext()) {" + NL + "\t            if (";
  protected final String TEXT_50 = " > 1) {" + NL + "\t                shard = shard + 1 % ";
  protected final String TEXT_51 = ";" + NL + "\t            }" + NL + "\t            tuple = iterator.next();" + NL + "\t            if (tuple._2() != null) {" + NL + "\t                /* Create a PutRecordRequest with an Array[Byte] version of the data */" + NL + "\t                com.amazonaws.services.kinesis.model.PutRecordRequest putRecordRequest =" + NL + "\t                        new com.amazonaws.services.kinesis.model.PutRecordRequest()" + NL + "\t                            .withStreamName(";
  protected final String TEXT_52 = ")" + NL + "\t                            .withPartitionKey(\"partitionKey-\" + shard)" + NL + "\t                            .withData(tuple._2().";
  protected final String TEXT_53 = ");" + NL + "" + NL + "\t                /* Put the record onto the stream and capture the PutRecordResult */" + NL + "\t                client.putRecord(putRecordRequest);" + NL + "\t            }" + NL + "\t        }" + NL + "        } finally {" + NL + "\t\t\t\tif(client != null) {" + NL + "\t\t\t\t\t";
  protected final String TEXT_54 = ".get(context).returnObject(client);" + NL + "\t\t\t\t}" + NL + "        }" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_55 = NL + NL + "    public static class ";
  protected final String TEXT_56 = "_ForeachRDDOutput<KEY> implements ";
  protected final String TEXT_57 = " {" + NL + "" + NL + "        private ContextProperties context;" + NL + "" + NL + "        public ";
  protected final String TEXT_58 = "_ForeachRDDOutput(JobConf conf) {" + NL + "            this.context = new ContextProperties(conf);" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public ";
  protected final String TEXT_59 = " call(";
  protected final String TEXT_60 = " rdd) throws Exception {" + NL + "            rdd.foreachPartition(new ";
  protected final String TEXT_61 = "_ForeachPartition_KinesisMessageSender<KEY>(context));";
  protected final String TEXT_62 = NL + "            ";
  protected final String TEXT_63 = NL + "        }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final TKinesisOutputUtil tKinesisOutputUtil = new TKinesisOutputUtil(node);
final KinesisSparkStorage storage = new KinesisSparkStorage(node);

String inStructName = codeGenArgument.getRecordStructName(tKinesisOutputUtil.getIncomingConnection());

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
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_24);
    
        String accessKey = ElementParameterParser.getValue(node, "__ACCESS_KEY__");
        String passwordFieldName = "__SECRET_KEY__";

    stringBuffer.append(TEXT_25);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_28);
    } else {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(accessKey);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(tKinesisOutputUtil.getEndpointUrl());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(tKinesisOutputUtil.getPartitionNumber());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(tKinesisOutputUtil.getPartitionNumber());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(tKinesisOutputUtil.getStreamName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(tKinesisOutputUtil.getIncomingColumnName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_54);
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaPairRDD(
                    codeGenArgument.getSparkVersion(), "KEY", inStructName);
    
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_63);
    
} // End ForeachRDD helper function

    return stringBuffer.toString();
  }
}
