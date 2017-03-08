package org.talend.designer.codegen.translators.databases.redshift;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.spark.generator.storage.jdbc.RedshiftSparkStorage;

public class TRedshiftOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TRedshiftOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRedshiftOutputSparkstreamingcodeJava result = new TRedshiftOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t\tpublic static class ";
  protected final String TEXT_2 = "_From";
  protected final String TEXT_3 = "To";
  protected final String TEXT_4 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "\t\t\tpublic ";
  protected final String TEXT_7 = " call(";
  protected final String TEXT_8 = " input) {" + NL + "\t\t\t\t";
  protected final String TEXT_9 = " result = new ";
  protected final String TEXT_10 = "();" + NL + "\t\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\t\t\tresult.";
  protected final String TEXT_12 = " = input.";
  protected final String TEXT_13 = " == null ? null : new java.sql.Date(input.";
  protected final String TEXT_14 = ".getTime());" + NL + "\t\t\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\t\t\t\tresult.";
  protected final String TEXT_16 = " = input.";
  protected final String TEXT_17 = ";" + NL + "\t\t\t\t\t";
  protected final String TEXT_18 = NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t    ";
  protected final String TEXT_19 = NL + NL + "        public static class ";
  protected final String TEXT_20 = "_ForeachRDDOutput implements ";
  protected final String TEXT_21 = " {" + NL + "" + NL + "            private ContextProperties context;" + NL + "" + NL + "            public ";
  protected final String TEXT_22 = "_ForeachRDDOutput(JobConf conf) {" + NL + "                this.context = new ContextProperties(conf);" + NL + "            }" + NL + "" + NL + "            @Override" + NL + "            public ";
  protected final String TEXT_23 = " call(";
  protected final String TEXT_24 = " temporaryRdd) throws Exception {" + NL + "\t\t\t\ttemporaryRdd.context().hadoopConfiguration().set(\"fs.s3a.access.key\", ";
  protected final String TEXT_25 = ");" + NL + "\t\t\t\ttemporaryRdd.context().hadoopConfiguration().set(\"fs.s3a.secret.key\", ";
  protected final String TEXT_26 = ");" + NL + "\t\t\t\torg.apache.spark.sql.SQLContext sqlCtx_";
  protected final String TEXT_27 = " = new org.apache.spark.sql.SQLContext(temporaryRdd.context());" + NL + "\t\t    \t";
  protected final String TEXT_28 = " df_";
  protected final String TEXT_29 = " = sqlCtx_";
  protected final String TEXT_30 = ".createDataFrame(temporaryRdd, ";
  protected final String TEXT_31 = ".class);" + NL + "" + NL + "\t\t    \tdf_";
  protected final String TEXT_32 = NL + "\t\t    \t\t";
  protected final String TEXT_33 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_34 = NL + "\t\t\t\t\t";
  protected final String TEXT_35 = NL + "\t\t    \t\t.write()" + NL + "\t\t\t\t\t.format(\"com.databricks.spark.redshift\")" + NL + "\t\t\t\t\t.option(\"url\", ";
  protected final String TEXT_36 = ")" + NL + "\t\t\t\t\t.option(\"dbtable\", ";
  protected final String TEXT_37 = "+\".\"+";
  protected final String TEXT_38 = ")" + NL + "\t\t\t\t\t.option(\"tempdir\", \"s3a://\" + ";
  protected final String TEXT_39 = " + ";
  protected final String TEXT_40 = ")" + NL + "\t\t\t\t\t.option(\"diststyle\", \"";
  protected final String TEXT_41 = "\")" + NL + "\t\t\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\t.option(\"distkey\", \"";
  protected final String TEXT_43 = "\")" + NL + "\t\t\t\t\t";
  protected final String TEXT_44 = NL + "\t\t\t\t\t\t.option(\"sortkeyspec\", \"";
  protected final String TEXT_45 = "\")" + NL + "\t\t\t\t\t";
  protected final String TEXT_46 = NL + "\t\t\t\t\t\t.option(\"usestagingtable\", \"";
  protected final String TEXT_47 = "\")" + NL + "\t\t\t\t\t";
  protected final String TEXT_48 = NL + "\t\t\t\t\t\t.option(\"preactions\", ";
  protected final String TEXT_49 = ")" + NL + "\t\t\t\t\t";
  protected final String TEXT_50 = NL + "\t\t\t\t\t\t.option(\"postactions\", ";
  protected final String TEXT_51 = ")" + NL + "\t\t\t\t\t";
  protected final String TEXT_52 = NL + "\t\t\t\t\t\t.option(\"extracopyoptions\", ";
  protected final String TEXT_53 = ")" + NL + "\t\t\t\t\t";
  protected final String TEXT_54 = NL + "\t\t\t\t\t.mode(org.apache.spark.sql.SaveMode.";
  protected final String TEXT_55 = ")" + NL + "\t\t\t\t\t.save();";
  protected final String TEXT_56 = NL + "                ";
  protected final String TEXT_57 = NL + "            }" + NL + "        }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
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
String inStructName = codeGenArgument.getRecordStructName(componentIncomingConnection);
String structName = inStructName;

// Change the structName if there's a Date column
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        String newStructName = "DF_"+inStructName+"AvroRecord";
        structName = newStructName;
	}
}
// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.
org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
	java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
	if(tSqlRowUtil.containsDateFields(incomingConnection)) {
		String originalStructName = codeGenArgument.getRecordStructName(incomingConnection);
		String suggestedDfStructName = "DF_"+originalStructName;
		String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, originalStructName);
		
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_10);
    
				for(IMetadataColumn column : columns) {
					if(tSqlRowUtil.isDateField(column)) {
					
    stringBuffer.append(TEXT_11);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
					} else {
					
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_17);
    
					}
				} // end for(IMetadataColumn column : columns)
				
    stringBuffer.append(TEXT_18);
    
	}

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
	Map<String, String> colNameMapping = new java.util.HashMap<String, String>();
	for(IMetadataColumn col : columns){
		colNameMapping.put(col.getLabel(), col.getOriginalDbColumnName());
	}

    { // Start ForeachRDD helper function
        // The signature of foreachRDD has changed in Spark 2.0
        org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
                org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaRDD(
                        codeGenArgument.getSparkVersion(), structName);
        
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(s3AccessKey);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(s3SecurityKey);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    
					String renamedColumn = "";
				    for (IMetadataColumn column : incomingConnection.getMetadataTable().getListColumns()) {
				        if (!column.getOriginalDbColumnName().equals(column.getLabel())) {
				            // the DB column is not the same as the label, with have to bind the name.
				            renamedColumn += ".withColumnRenamed(\"" + column.getLabel() + "\", \"" + column.getOriginalDbColumnName() + "\")";
				        }
				    }
				    if(!"".equals(renamedColumn)){
					
    stringBuffer.append(TEXT_33);
    stringBuffer.append(renamedColumn);
    stringBuffer.append(TEXT_34);
    
					}
					
    stringBuffer.append(TEXT_35);
    stringBuffer.append(storage.getURI());
    stringBuffer.append(TEXT_36);
    if(dbSchema!=null && !"".equals(dbSchema) && !"\"\"".equals(dbSchema)){
    stringBuffer.append(dbSchema);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(table);
    }else{
    stringBuffer.append(table);
    }
    stringBuffer.append(TEXT_38);
    stringBuffer.append(s3Bucket);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(storage.getS3TempPath());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(distStyle);
    stringBuffer.append(TEXT_41);
    
					if("KEY".equals(distStyle)){
					
    stringBuffer.append(TEXT_42);
    stringBuffer.append(colNameMapping.get(distKey));
    stringBuffer.append(TEXT_43);
    
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
						
    stringBuffer.append(TEXT_44);
    stringBuffer.append(sortStmt);
    stringBuffer.append(TEXT_45);
    
					}
					if("Overwrite".equals(saveMode)){
					
    stringBuffer.append(TEXT_46);
    stringBuffer.append(useStagingTable);
    stringBuffer.append(TEXT_47);
    
					}
					if(definePreActions){
					
    stringBuffer.append(TEXT_48);
    stringBuffer.append(preActions);
    stringBuffer.append(TEXT_49);
    
					}
					if(definePostActions){
					
    stringBuffer.append(TEXT_50);
    stringBuffer.append(postActions);
    stringBuffer.append(TEXT_51);
    
					}
					if(defineExtraCopyOptions){
					
    stringBuffer.append(TEXT_52);
    stringBuffer.append(extraCopyOptions);
    stringBuffer.append(TEXT_53);
    
					}
					
    stringBuffer.append(TEXT_54);
    stringBuffer.append(saveMode);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_57);
    
    } // End ForeachRDD helper function
}

    return stringBuffer.toString();
  }
}
