package org.talend.designer.codegen.translators.databases.mysql;

import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.spark.generator.storage.jdbc.MysqlSparkStorage;

public class TMysqlConfigurationSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TMysqlConfigurationSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMysqlConfigurationSparkstreamingcodeJava result = new TMysqlConfigurationSparkstreamingcodeJava();
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
  protected final String TEXT_21 = "(context), config);" + NL + "\t}" + NL + "}";
  protected final String TEXT_22 = NL + NL + "public static class ";
  protected final String TEXT_23 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_24 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_25 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_26 = " create() throws Exception {" + NL + "\t   Class.forName(";
  protected final String TEXT_27 = ");" + NL + "\t\tjava.util.Properties jdbcDriverProperties = new java.util.Properties();" + NL + "\t\tString jdbcAdditionalParameters = ";
  protected final String TEXT_28 = ";" + NL + "\t\tif(jdbcAdditionalParameters != null && !\"\\\"\\\"\".equals(jdbcAdditionalParameters) && !\"\".equals(jdbcAdditionalParameters)){" + NL + "\t\t\tjdbcDriverProperties.load(new java.io.ByteArrayInputStream(jdbcAdditionalParameters.replace(";
  protected final String TEXT_29 = ", \"\\n\").getBytes()));" + NL + "\t\t}" + NL + "\t\tif(";
  protected final String TEXT_30 = " != null && !\"\\\"\\\"\".equals(";
  protected final String TEXT_31 = ") && !\"\".equals(";
  protected final String TEXT_32 = ")){" + NL + "\t\t\tjdbcDriverProperties.setProperty(\"user\", ";
  protected final String TEXT_33 = ");" + NL + "\t\t}" + NL + "\t\tString password = ";
  protected final String TEXT_34 = " ;" + NL + "\t\tif(password != null && !\"\\\"\\\"\".equals(password) && !\"\".equals(password)){" + NL + "\t\t\tjdbcDriverProperties.setProperty(\"password\", password);" + NL + "\t\t}" + NL + "\t   return java.sql.DriverManager.getConnection(";
  protected final String TEXT_35 = ", jdbcDriverProperties);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_36 = "> wrap(";
  protected final String TEXT_37 = " connection) {" + NL + "\t   return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_38 = ">(connection);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_39 = "> pooledObject) throws Exception {" + NL + "\t\tif (pooledObject != null) {" + NL + "\t\t    pooledObject.getObject().close();" + NL + "\t\t}" + NL + "\t\tsuper.destroyObject(pooledObject);" + NL + "\t}" + NL + "}";
  protected final String TEXT_40 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

MysqlSparkStorage storage = new MysqlSparkStorage(node);

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
    
	// Pooled object factory code. This factory creates JDBC connections when requested by a connection pool. 
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create JDBC connections without relying on a dedicated Spark connector.
	
	// If this file is included, then spark_pool.javajet must be included as well.

    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(storage.getDriver());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(storage.getJDBCAdditionalParameters());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getJDBCAdditionalParametersSeparator());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    return stringBuffer.toString();
  }
}
