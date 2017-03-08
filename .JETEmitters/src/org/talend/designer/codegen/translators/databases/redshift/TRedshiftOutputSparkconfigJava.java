package org.talend.designer.codegen.translators.databases.redshift;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.spark.generator.storage.jdbc.RedshiftSparkStorage;
import java.util.Map;
import java.util.List;

public class TRedshiftOutputSparkconfigJava
{
  protected static String nl;
  public static synchronized TRedshiftOutputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRedshiftOutputSparkconfigJava result = new TRedshiftOutputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "ctx.hadoopConfiguration().set(\"fs.s3a.access.key\", ";
  protected final String TEXT_2 = ");" + NL + "ctx.hadoopConfiguration().set(\"fs.s3a.secret.key\", ";
  protected final String TEXT_3 = ");" + NL + "org.apache.spark.sql.SQLContext sqlCtx_";
  protected final String TEXT_4 = " = new org.apache.spark.sql.SQLContext(ctx);" + NL;
  protected final String TEXT_5 = NL + "        org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_6 = "> ";
  protected final String TEXT_7 = " = ";
  protected final String TEXT_8 = ".map(new ";
  protected final String TEXT_9 = "_From";
  protected final String TEXT_10 = "To";
  protected final String TEXT_11 = "());";
  protected final String TEXT_12 = NL + "    ";
  protected final String TEXT_13 = " df_";
  protected final String TEXT_14 = "_";
  protected final String TEXT_15 = " = sqlCtx_";
  protected final String TEXT_16 = ".createDataFrame(";
  protected final String TEXT_17 = ", ";
  protected final String TEXT_18 = ".class);" + NL + "" + NL + "" + NL + "" + NL + "\tdf_";
  protected final String TEXT_19 = "_";
  protected final String TEXT_20 = NL + "\t\t";
  protected final String TEXT_21 = NL + "\t\t\t";
  protected final String TEXT_22 = NL + "\t\t";
  protected final String TEXT_23 = NL + "\t\t.write()" + NL + "\t\t.format(\"com.databricks.spark.redshift\")" + NL + "\t\t.option(\"url\", ";
  protected final String TEXT_24 = ")" + NL + "\t\t.option(\"dbtable\", ";
  protected final String TEXT_25 = "+\".\"+";
  protected final String TEXT_26 = ")" + NL + "\t\t.option(\"tempdir\", \"s3a://\" + ";
  protected final String TEXT_27 = " + ";
  protected final String TEXT_28 = ")" + NL + "\t\t.option(\"diststyle\", \"";
  protected final String TEXT_29 = "\")" + NL + "\t\t";
  protected final String TEXT_30 = NL + "\t\t\t.option(\"distkey\", \"";
  protected final String TEXT_31 = "\")" + NL + "\t\t";
  protected final String TEXT_32 = NL + "\t\t\t.option(\"sortkeyspec\", \"";
  protected final String TEXT_33 = "\")" + NL + "\t\t";
  protected final String TEXT_34 = NL + "\t\t\t.option(\"usestagingtable\", \"";
  protected final String TEXT_35 = "\")" + NL + "\t\t";
  protected final String TEXT_36 = NL + "\t\t\t.option(\"preactions\", ";
  protected final String TEXT_37 = ")" + NL + "\t\t";
  protected final String TEXT_38 = NL + "\t\t\t.option(\"postactions\", ";
  protected final String TEXT_39 = ")" + NL + "\t\t";
  protected final String TEXT_40 = NL + "\t\t\t.option(\"extracopyoptions\", ";
  protected final String TEXT_41 = ")" + NL + "\t\t";
  protected final String TEXT_42 = NL + "\t\t.mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_43 = ")" + NL + "\t\t.save();";
  protected final String TEXT_44 = NL;

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

    
RedshiftSparkStorage storage = new RedshiftSparkStorage(node);
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
String dbSchema = storage.getSchemaDb();
String table = ElementParameterParser.getValue(node,  "__TABLE__");
INode s3ConfNode = storage.getS3ConfigurationNode();
String s3AccessKey = ElementParameterParser.getValue(s3ConfNode, "__ACCESS_KEY__");
String s3SecurityKey = ElementParameterParser.getPasswordValue(s3ConfNode, "__SECRET_KEY__");
String s3Bucket = ElementParameterParser.getValue(s3ConfNode, "__BUCKET_NAME__");
String saveMode = ElementParameterParser.getValue(node, "__SAVEMODE__");
String distStyle = ElementParameterParser.getValue(node, "__DIST_STYLE__");
String distKey = ElementParameterParser.getValue(node, "__DIST_KEY__");
boolean defineSortKey = ElementParameterParser.getBooleanValue(node, "__DEFINE_SORT_KEY__");
String sortKeyType = ElementParameterParser.getValue(node, "__SORT_KEY_TYPE__");
String sortKey = ElementParameterParser.getValue(node, "__SORT_KEY__");
List<Map<String, String>> multipleSortKeys = ElementParameterParser.getTableValue(node, "__MULTIPLE_SORT_KEYS__");
boolean useStagingTable = ElementParameterParser.getBooleanValue(node, "__USE_STAGING_TABLE__");
boolean definePreActions = ElementParameterParser.getBooleanValue(node, "__DEFINE_PRE_ACTIONS__");
String preActions = ElementParameterParser.getValue(node, "__PRE_ACTIONS__").replaceAll("[\r\n]", " ");
boolean definePostActions = ElementParameterParser.getBooleanValue(node, "__DEFINE_POST_ACTIONS__");
String postActions = ElementParameterParser.getValue(node, "__POST_ACTIONS__").replaceAll("[\r\n]", " ");
boolean defineExtraCopyOptions = ElementParameterParser.getBooleanValue(node, "__DEFINE_EXTRA_COPY_OPTIONS__");
String extraCopyOptions = ElementParameterParser.getValue(node, "__EXTRA_COPY_OPTIONS__").replaceAll("[\r\n]", " ");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(s3AccessKey);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(s3SecurityKey);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    
// DATAFRAME DATE CONVERSION
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
    String inRddName = "rdd_"+incomingConnection.getName();
    String rddName, structName;

    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        // Additional map to convert from java.util.Date to java.sql.Date
        String newRddName = "tmp_rdd_"+incomingConnection.getName();
        String newStructName = "DF_"+inStructName+"AvroRecord";
        
    stringBuffer.append(TEXT_5);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_11);
    
        rddName = newRddName;
        structName = newStructName;
    }else{
        // No need for additional map
        rddName = inRddName;
        structName = inStructName;
    }
    // Convert the RDD into a DF
    List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
    Map<String, String> colNameMapping = new java.util.HashMap<String, String>();
    for(IMetadataColumn col : columns){
    	colNameMapping.put(col.getLabel(), col.getOriginalDbColumnName());
    }
    
    stringBuffer.append(TEXT_12);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_20);
    
		String renamedColumn = "";
	    for (IMetadataColumn column : incomingConnection.getMetadataTable().getListColumns()) {
	        if (!column.getOriginalDbColumnName().equals(column.getLabel())) {
	            // the DB column is not the same as the label, with have to bind the name.
	            renamedColumn += ".withColumnRenamed(\"" + column.getLabel() + "\", \"" + column.getOriginalDbColumnName() + "\")";
	        }
	    }
	    if(!"".equals(renamedColumn)){
		
    stringBuffer.append(TEXT_21);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_22);
    
		}
		
    stringBuffer.append(TEXT_23);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_24);
    if(dbSchema!=null && !"".equals(dbSchema) && !"\"\"".equals(dbSchema)){
    stringBuffer.append(dbSchema);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(table);
    }else{
    stringBuffer.append(table);
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(s3Bucket);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(storage.getS3TempPath());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(distStyle);
    stringBuffer.append(TEXT_29);
    
		if("KEY".equals(distStyle)){
		
    stringBuffer.append(TEXT_30);
    stringBuffer.append(colNameMapping.get(distKey));
    stringBuffer.append(TEXT_31);
    
		}
		if(defineSortKey){
			String sortStmt = "";
			if("SINGLE".equals(sortKeyType)){
				sortStmt = "SORTKEY(" + colNameMapping.get(sortKey) + ")";
			}else{
				sortStmt += sortKeyType + " SORTKEY(";
				int i = 0;
				for(Map<String, String> sKey : multipleSortKeys){
					sortStmt += colNameMapping.get(sKey.get("COLUMN_NAME"));
					if(++i < multipleSortKeys.size()){
						sortStmt += ",";
					}
				}
				sortStmt += ")";
			}
			
    stringBuffer.append(TEXT_32);
    stringBuffer.append(sortStmt);
    stringBuffer.append(TEXT_33);
    
		}
		if("Overwrite".equals(saveMode)){
		
    stringBuffer.append(TEXT_34);
    stringBuffer.append(useStagingTable);
    stringBuffer.append(TEXT_35);
    
		}
		if(definePreActions){
		
    stringBuffer.append(TEXT_36);
    stringBuffer.append(preActions);
    stringBuffer.append(TEXT_37);
    
		}
		if(definePostActions){
		
    stringBuffer.append(TEXT_38);
    stringBuffer.append(postActions);
    stringBuffer.append(TEXT_39);
    
		}
		if(defineExtraCopyOptions){
		
    stringBuffer.append(TEXT_40);
    stringBuffer.append(extraCopyOptions);
    stringBuffer.append(TEXT_41);
    
		}
		
    stringBuffer.append(TEXT_42);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_43);
    
}

    stringBuffer.append(TEXT_44);
    return stringBuffer.toString();
  }
}
