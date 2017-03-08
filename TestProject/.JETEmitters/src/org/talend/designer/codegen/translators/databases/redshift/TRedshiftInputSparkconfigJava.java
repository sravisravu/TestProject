package org.talend.designer.codegen.translators.databases.redshift;

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
import org.talend.designer.spark.generator.storage.jdbc.RedshiftSparkStorage;

public class TRedshiftInputSparkconfigJava
{
  protected static String nl;
  public static synchronized TRedshiftInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRedshiftInputSparkconfigJava result = new TRedshiftInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t\t\tctx.hadoopConfiguration().set(\"fs.s3a.access.key\", ";
  protected final String TEXT_2 = ");" + NL + "\t\t\tctx.hadoopConfiguration().set(\"fs.s3a.secret.key\", ";
  protected final String TEXT_3 = ");" + NL + "            org.apache.spark.sql.SQLContext sqlCtx_";
  protected final String TEXT_4 = " = new org.apache.spark.sql.SQLContext(ctx);";
  protected final String TEXT_5 = NL + "            ";
  protected final String TEXT_6 = " df_";
  protected final String TEXT_7 = " = sqlCtx_";
  protected final String TEXT_8 = ".read()" + NL + "            \t.format(\"com.databricks.spark.redshift\")" + NL + "            \t.option(\"url\", ";
  protected final String TEXT_9 = ")" + NL + "            \t";
  protected final String TEXT_10 = NL + "            \t\t.option(\"query\", ";
  protected final String TEXT_11 = ")" + NL + "            \t";
  protected final String TEXT_12 = NL + "            \t\t.option(\"dbtable\", ";
  protected final String TEXT_13 = "+\".\"+";
  protected final String TEXT_14 = ")" + NL + "            \t";
  protected final String TEXT_15 = NL + "            \t.option(\"tempdir\", \"s3a://\" + ";
  protected final String TEXT_16 = " + ";
  protected final String TEXT_17 = ")" + NL + "            \t.load();" + NL + "" + NL + "\t\t\t";
  protected final String TEXT_18 = NL + "\t\t\t\tdf_";
  protected final String TEXT_19 = ".registerTempTable(";
  protected final String TEXT_20 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_21 = NL + "\t        \t\tdf_";
  protected final String TEXT_22 = " = sqlCtx_";
  protected final String TEXT_23 = ".sql(";
  protected final String TEXT_24 = ")";
  protected final String TEXT_25 = ";" + NL + "\t        \t";
  protected final String TEXT_26 = NL + "\t        \t\tdf_";
  protected final String TEXT_27 = " = sqlCtx_";
  protected final String TEXT_28 = ".sql(\"select * from \" + ";
  protected final String TEXT_29 = ")";
  protected final String TEXT_30 = ";" + NL + "        \t\t";
  protected final String TEXT_31 = NL + NL + NL + "            // Retrieve the associated RDD" + NL + "            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_32 = "> rdd_";
  protected final String TEXT_33 = " = df_";
  protected final String TEXT_34 = ".toJavaRDD().map(new ";
  protected final String TEXT_35 = "_FromRowTo";
  protected final String TEXT_36 = "());";
  protected final String TEXT_37 = NL;

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

            RedshiftSparkStorage storage = new RedshiftSparkStorage(node);
            String dbSchema = storage.getSchemaDb();
            String table = ElementParameterParser.getValue(node,  "__TABLE__");
            String dbquery = ElementParameterParser.getValue(node, "__QUERY__");
            dbquery = org.talend.core.model.utils.NodeUtil.replaceCRLFInMEMO_SQL(dbquery);
            INode s3ConfNode = storage.getS3ConfigurationNode();
            String s3AccessKey = ElementParameterParser.getValue(s3ConfNode, "__ACCESS_KEY__");
            String s3SecurityKey = ElementParameterParser.getPasswordValue(s3ConfNode, "__SECRET_KEY__");
            String s3Bucket = ElementParameterParser.getValue(s3ConfNode, "__BUCKET_NAME__");
            String readMode = ElementParameterParser.getValue(node, "__READ_MODE__");
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(s3AccessKey);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(s3SecurityKey);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_9);
    if("QUERY".equals(readMode)){
    stringBuffer.append(TEXT_10);
    stringBuffer.append(dbquery);
    stringBuffer.append(TEXT_11);
    }else if("TABLE".equals(readMode)){
    stringBuffer.append(TEXT_12);
    if(dbSchema!=null && !"".equals(dbSchema) && !"\"\"".equals(dbSchema)){
    stringBuffer.append(dbSchema);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(table);
    }else{
    stringBuffer.append(table);
    }
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(s3Bucket);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(storage.getS3TempPath());
    stringBuffer.append(TEXT_17);
    
			String renamedColumn = "";
            for (IMetadataColumn column : connection.getMetadataTable().getListColumns()) {
                if (!column.getOriginalDbColumnName().equals(column.getLabel())) {
                    // the DB column is not the same as the label, with have to bind the name.
                    renamedColumn += ".withColumnRenamed(\"" + column.getOriginalDbColumnName() + "\", \"" + column.getLabel() + "\")";
                }
            }
            if(!"".equals(renamedColumn)){
			
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_20);
    if("QUERY".equals(readMode)){
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(dbquery);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_25);
    }else if("TABLE".equals(readMode)){
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(table);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_30);
    
        		}
        	}
        	
    stringBuffer.append(TEXT_31);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_36);
    
        }
    }
}

    stringBuffer.append(TEXT_37);
    return stringBuffer.toString();
  }
}
