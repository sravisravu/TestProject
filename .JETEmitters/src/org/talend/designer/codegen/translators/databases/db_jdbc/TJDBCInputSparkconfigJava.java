package org.talend.designer.codegen.translators.databases.db_jdbc;

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
import org.talend.designer.spark.generator.storage.jdbc.JDBCSparkStorage;

public class TJDBCInputSparkconfigJava
{
  protected static String nl;
  public static synchronized TJDBCInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJDBCInputSparkconfigJava result = new TJDBCInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\t// JDBC driver configuration" + NL + "\t\t\t\tjava.util.Properties jdbcDriverProperties_";
  protected final String TEXT_2 = " = new java.util.Properties();" + NL + "\t\t\t\tString jdbcAdditionalParameters_";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ";" + NL + "\t\t\t\tif(jdbcAdditionalParameters_";
  protected final String TEXT_5 = " != null && !\"\\\"\\\"\".equals(jdbcAdditionalParameters_";
  protected final String TEXT_6 = ") && !\"\".equals(jdbcAdditionalParameters_";
  protected final String TEXT_7 = ")){" + NL + "\t\t\t\t\tjdbcDriverProperties_";
  protected final String TEXT_8 = ".load(new java.io.ByteArrayInputStream(jdbcAdditionalParameters_";
  protected final String TEXT_9 = ".replace(";
  protected final String TEXT_10 = ", \"\\n\").getBytes()));" + NL + "\t\t\t\t}" + NL + "" + NL + "            org.apache.spark.sql.SQLContext sqlCtx_";
  protected final String TEXT_11 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL + "" + NL + "            Map<String, String> options_";
  protected final String TEXT_12 = " = new java.util.HashMap<String, String>();" + NL + "            options_";
  protected final String TEXT_13 = ".put(\"url\" , ";
  protected final String TEXT_14 = ");" + NL + "            options_";
  protected final String TEXT_15 = ".put(\"dbtable\", ";
  protected final String TEXT_16 = ");" + NL + "            options_";
  protected final String TEXT_17 = ".put(\"driver\", ";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "            \toptions_";
  protected final String TEXT_20 = ".put(\"fetchSize\", String.valueOf(";
  protected final String TEXT_21 = "));" + NL + "\t\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\tfor (String jdbcDriverKey_";
  protected final String TEXT_23 = " : jdbcDriverProperties_";
  protected final String TEXT_24 = ".stringPropertyNames()) {" + NL + "\t\t\t\t\toptions_";
  protected final String TEXT_25 = ".put(jdbcDriverKey_";
  protected final String TEXT_26 = ", jdbcDriverProperties_";
  protected final String TEXT_27 = ".getProperty(jdbcDriverKey_";
  protected final String TEXT_28 = "));" + NL + "\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_29 = NL + "    \t\t\toptions_";
  protected final String TEXT_30 = ".put(";
  protected final String TEXT_31 = ", ";
  protected final String TEXT_32 = ");" + NL + "\t\t\t";
  protected final String TEXT_33 = NL;
  protected final String TEXT_34 = NL + "            \t";
  protected final String TEXT_35 = " df_";
  protected final String TEXT_36 = " = sqlCtx_";
  protected final String TEXT_37 = ".load(\"jdbc\", options_";
  protected final String TEXT_38 = ");";
  protected final String TEXT_39 = NL + "\t            \toptions_";
  protected final String TEXT_40 = ".put(\"user\", ";
  protected final String TEXT_41 = ");" + NL + "\t            \toptions_";
  protected final String TEXT_42 = ".put(\"password\", ";
  protected final String TEXT_43 = ");" + NL + "            \t";
  protected final String TEXT_44 = NL + "            \t";
  protected final String TEXT_45 = " df_";
  protected final String TEXT_46 = " = sqlCtx_";
  protected final String TEXT_47 = ".read().format(\"jdbc\").options(options_";
  protected final String TEXT_48 = ").load();";
  protected final String TEXT_49 = NL + NL + "            df_";
  protected final String TEXT_50 = ".registerTempTable(";
  protected final String TEXT_51 = ");" + NL + "            df_";
  protected final String TEXT_52 = " = sqlCtx_";
  protected final String TEXT_53 = ".sql(";
  protected final String TEXT_54 = ")";
  protected final String TEXT_55 = ";" + NL + "" + NL + "            // Retrieve the associated RDD" + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_56 = "> rdd_";
  protected final String TEXT_57 = " = df_";
  protected final String TEXT_58 = ".toJavaRDD().map(new ";
  protected final String TEXT_59 = "_FromRowTo";
  protected final String TEXT_60 = "());";
  protected final String TEXT_61 = NL;

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
if (metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        List< ? extends IConnection> connections = node.getOutgoingConnections();
        if ((connections != null) && (connections.size() > 0)) {
            IConnection connection = connections.get(0);
            String connName = connection.getName();
            String connectionTypeName = codeGenArgument.getRecordStructName(connection);

            JDBCSparkStorage storage = new JDBCSparkStorage(node);

            String table = ElementParameterParser.getValue(node,  "__TABLE__");
            String dbquery= ElementParameterParser.getValue(node, "__QUERY__");
            dbquery = org.talend.core.model.utils.NodeUtil.replaceCRLFInMEMO_SQL(dbquery);
            boolean useCursor = ElementParameterParser.getBooleanValue(node, "__USE_CURSOR__");
			String fetchSize = ElementParameterParser.getValue(node, "__CURSOR_SIZE__");

            String renamedColumn = "";
            for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
                if (!column.getOriginalDbColumnName().equals(column.getLabel())) {
                    // the DB column is not the same as the label, with have to bind the name.
                    renamedColumn += ".withColumnRenamed(\"" + column.getOriginalDbColumnName() + "\", \"" + column.getLabel() + "\")";
                }
            }

            java.util.List<java.util.Map<String, String>> options = (java.util.List<java.util.Map<String, String>>) ElementParameterParser.getObjectValue(node, "__SPARK_SQL_JDBC_OPTIONS__");

            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(storage.getJDBCAdditionalParameters());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getJDBCAdditionalParametersSeparator());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(storage.getDriver());
    stringBuffer.append(TEXT_18);
    if(useCursor){
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(fetchSize);
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
			for(java.util.Map<String, String> option : options) {
			
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(option.get("PROPERTY"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(option.get("VALUE"));
    stringBuffer.append(TEXT_32);
    
			}
			
    stringBuffer.append(TEXT_33);
    
            if(org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3 == codeGenArgument.getSparkVersion()){
            
    stringBuffer.append(TEXT_34);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
            }else{
            	String username = storage.getUsername();
            	if(username != null && !"".equals(username) && !"\"\"".equals(username)){
            	
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_43);
    
            	}
            	
    stringBuffer.append(TEXT_44);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
            }
            
    stringBuffer.append(TEXT_49);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(dbquery);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_60);
    
        }
    }
}

    stringBuffer.append(TEXT_61);
    return stringBuffer.toString();
  }
}
