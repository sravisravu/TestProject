package org.talend.designer.codegen.translators.databases.mysql;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.spark.generator.storage.jdbc.MysqlSparkStorage;
import org.talend.hadoop.distribution.ESparkVersion;

public class TMysqlInputSparkconfigJava
{
  protected static String nl;
  public static synchronized TMysqlInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMysqlInputSparkconfigJava result = new TMysqlInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "\t\t\t" + NL + "\t            org.apache.spark.sql.SQLContext sqlCtx_";
  protected final String TEXT_3 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL + "" + NL + "\t\t\t\t\t// Spark SQL JDBC reader configuration" + NL + "\t            Map<String, String> options_";
  protected final String TEXT_4 = " = new java.util.HashMap<String, String>();" + NL + "\t            options_";
  protected final String TEXT_5 = ".put(\"url\" , ";
  protected final String TEXT_6 = ");" + NL + "\t            options_";
  protected final String TEXT_7 = ".put(\"dbtable\", ";
  protected final String TEXT_8 = ");" + NL + "\t            options_";
  protected final String TEXT_9 = ".put(\"driver\", ";
  protected final String TEXT_10 = ");";
  protected final String TEXT_11 = NL + "\t\t\t\t\t\t// JDBC driver configuration" + NL + "\t\t\t\t\t\tjava.util.Properties jdbcDriverProperties_";
  protected final String TEXT_12 = " = new java.util.Properties();" + NL + "\t\t\t\t\t\tString jdbcAdditionalParameters_";
  protected final String TEXT_13 = " = ";
  protected final String TEXT_14 = ";" + NL + "\t\t\t\t\t\tif(jdbcAdditionalParameters_";
  protected final String TEXT_15 = " != null && !\"\\\"\\\"\".equals(jdbcAdditionalParameters_";
  protected final String TEXT_16 = ") && !\"\".equals(jdbcAdditionalParameters_";
  protected final String TEXT_17 = ")){" + NL + "\t\t\t\t\t\t\tjdbcDriverProperties_";
  protected final String TEXT_18 = ".load(new java.io.ByteArrayInputStream(jdbcAdditionalParameters_";
  protected final String TEXT_19 = ".replace(";
  protected final String TEXT_20 = ", \"\\n\").getBytes()));" + NL + "\t\t\t\t\t\t}\t\t" + NL + "\t\t            for (String jdbcDriverKey_";
  protected final String TEXT_21 = " : jdbcDriverProperties_";
  protected final String TEXT_22 = ".stringPropertyNames()) {" + NL + "\t\t\t\t\t\t\toptions_";
  protected final String TEXT_23 = ".put(jdbcDriverKey_";
  protected final String TEXT_24 = ", jdbcDriverProperties_";
  protected final String TEXT_25 = ".getProperty(jdbcDriverKey_";
  protected final String TEXT_26 = "));" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\tif(";
  protected final String TEXT_27 = " != null && !\"\\\"\\\"\".equals(";
  protected final String TEXT_28 = ") && !\"\".equals(";
  protected final String TEXT_29 = ")){" + NL + "\t\t\t\t\t\t\toptions_";
  protected final String TEXT_30 = ".put(\"user\", ";
  protected final String TEXT_31 = ");" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\tString password_";
  protected final String TEXT_32 = " = ";
  protected final String TEXT_33 = ";" + NL + "\t\t\t\t\t\tif(password_";
  protected final String TEXT_34 = " != null && !\"\\\"\\\"\".equals(password_";
  protected final String TEXT_35 = ") && !\"\".equals(password_";
  protected final String TEXT_36 = ")){" + NL + "\t\t\t\t\t\t\toptions_";
  protected final String TEXT_37 = ".put(\"password\", password_";
  protected final String TEXT_38 = ");" + NL + "\t\t\t\t\t\t}";
  protected final String TEXT_39 = NL + "            \t\toptions_";
  protected final String TEXT_40 = ".put(";
  protected final String TEXT_41 = ", ";
  protected final String TEXT_42 = ");";
  protected final String TEXT_43 = NL + "            \t\t";
  protected final String TEXT_44 = " df_";
  protected final String TEXT_45 = " = sqlCtx_";
  protected final String TEXT_46 = ".load(\"jdbc\", options_";
  protected final String TEXT_47 = ");";
  protected final String TEXT_48 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_49 = " df_";
  protected final String TEXT_50 = " = sqlCtx_";
  protected final String TEXT_51 = ".read().format(\"jdbc\").options(options_";
  protected final String TEXT_52 = ").load();";
  protected final String TEXT_53 = NL + "\t            df_";
  protected final String TEXT_54 = ".registerTempTable(";
  protected final String TEXT_55 = ");" + NL + "\t            df_";
  protected final String TEXT_56 = " =  sqlCtx_";
  protected final String TEXT_57 = ".sql(";
  protected final String TEXT_58 = ")";
  protected final String TEXT_59 = ";" + NL + "" + NL + "\t            // Retrieve the associated RDD" + NL + "\t            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_60 = "> rdd_";
  protected final String TEXT_61 = " = df_";
  protected final String TEXT_62 = ".toJavaRDD().map(new ";
  protected final String TEXT_63 = "_FromRowTo";
  protected final String TEXT_64 = "());";
  protected final String TEXT_65 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
List<IMetadataTable> metadatas = node.getMetadataList();
MysqlSparkStorage storage = new MysqlSparkStorage(node);

    
	// Common code generation for all Spark JDBC input components.
	// The actual SparkStorage instance must have been created elsewhere before including this file
	// and must be named "storage".

	// This file is intended to be included in every JDBC input component sparkconfig.javajet

    stringBuffer.append(TEXT_1);
    
	// Spark 1.3 do not support the "new" SQLContext API.
	final List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
	INode sparkConfig = null;
	if(sparkConfigs != null && sparkConfigs.size() > 0) {
	    sparkConfig = sparkConfigs.get(0);
	}
	boolean isSpark13 = (ESparkVersion.SPARK_1_3 == codeGenArgument.getSparkVersion());

	if (metadatas != null && metadatas.size() > 0) {
	    IMetadataTable metadata = metadatas.get(0);
	    if(metadata != null){
	        List< ? extends IConnection> connections = node.getOutgoingConnections();
	        if ((connections != null) && (connections.size() > 0)) {
	            IConnection connection = connections.get(0);
	            String connName = connection.getName();
	            String connectionTypeName = codeGenArgument.getRecordStructName(connection);
	            String table = ElementParameterParser.getValue(node,  "__TABLE__");
	            String dbquery = org.talend.core.model.utils.NodeUtil.replaceCRLFInMEMO_SQL(ElementParameterParser.getValue(node, "__QUERY__"));

	            String renamedColumn = "";
	            for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
	                if (!column.getOriginalDbColumnName().equals(column.getLabel())) {
	                    // the DB column is not the same as the label, with have to bind the name.
	                    renamedColumn += ".withColumnRenamed(\"" + column.getOriginalDbColumnName() + "\", \"" + column.getLabel() + "\")";
	                }
	            }

	            java.util.List<java.util.Map<String, String>> options = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node, "__SPARK_SQL_JDBC_OPTIONS__");

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getDriver());
    stringBuffer.append(TEXT_10);
    
					if(!isSpark13) {
						// Spark SQL 1.3 does not support to pass custom properties to the JDBC driver

    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(storage.getJDBCAdditionalParameters());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getJDBCAdditionalParametersSeparator());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
					}
					
					for(java.util.Map<String, String> option : options) {
						// Custom properties provided by the user (if any)

    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(option.get("PROPERTY"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(option.get("VALUE"));
    stringBuffer.append(TEXT_42);
    
        			}

					if(isSpark13) {
						// This API is deprecated from Spark 1.4.0

    stringBuffer.append(TEXT_43);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    
					} else {
						// This API was introduced in Spark 1.4.0 and is the current reference

    stringBuffer.append(TEXT_48);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    
					}

    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(dbquery);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_64);
    
	        }
	    }
	}

    stringBuffer.append(TEXT_65);
    return stringBuffer.toString();
  }
}
