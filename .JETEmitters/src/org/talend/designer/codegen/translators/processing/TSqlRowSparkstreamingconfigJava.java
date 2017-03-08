package org.talend.designer.codegen.translators.processing;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.ElementParameterParser;

public class TSqlRowSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TSqlRowSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSqlRowSparkstreamingconfigJava result = new TSqlRowSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t";
  protected final String TEXT_2 = NL + "\torg.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_3 = " = new org.apache.spark.sql.SQLContext(ctx.sc());" + NL + "\t";
  protected final String TEXT_4 = NL + "\tif(System.getProperty(\"hive.metastore.uris\") == null){" + NL + "\t\t// There is no Hive component in the job. Metastore will be created locally but we don't want it to be within the current directory." + NL + "\t\tSystem.setProperty(\"javax.jdo.option.ConnectionURL\", \"jdbc:derby:;databaseName=\"+ System.getProperty(\"java.io.tmpdir\") + \"/\" + jobName + \"/metastore_db;create=true\");" + NL + "\t}" + NL + "\torg.apache.spark.sql.hive.HiveContext sqlContext_";
  protected final String TEXT_5 = " = new org.apache.spark.sql.hive.HiveContext(ctx.sc());" + NL + "\t";
  protected final String TEXT_6 = NL + "// Create dataframes from incoming schemas and register related tables";
  protected final String TEXT_7 = NL + "    ";
  protected final String TEXT_8 = " df_";
  protected final String TEXT_9 = "_";
  protected final String TEXT_10 = " = sqlContext_";
  protected final String TEXT_11 = ".createDataFrame(" + NL + "            rdd_";
  protected final String TEXT_12 = ", ";
  protected final String TEXT_13 = ".class);" + NL + "    df_";
  protected final String TEXT_14 = "_";
  protected final String TEXT_15 = ".registerTempTable(\"";
  protected final String TEXT_16 = "\");";
  protected final String TEXT_17 = NL + NL + "// Execute the sql query";
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = " df_";
  protected final String TEXT_20 = " = sqlContext_";
  protected final String TEXT_21 = ".sql(";
  protected final String TEXT_22 = ");" + NL + "" + NL + "// Retrieve the associated RDD" + NL + "org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_23 = "> rdd_";
  protected final String TEXT_24 = " = df_";
  protected final String TEXT_25 = ".toJavaRDD().map(" + NL + "    new ";
  protected final String TEXT_26 = "_FromRowTo";
  protected final String TEXT_27 = "());";
  protected final String TEXT_28 = NL + "\torg.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_29 = "> rdd_";
  protected final String TEXT_30 = " = rdd_";
  protected final String TEXT_31 = ".transform(new Transformer_";
  protected final String TEXT_32 = "(job));";
  protected final String TEXT_33 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode((INode) ((BigDataCodeGeneratorArgument) argument).getArgument())) {

    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, true);
if (validateError != null) {
// Cause the job compilation to explicitly fail if there is a problem.
return "throw new JobConfigurationError(\"" + validateError +"\");";
}

String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
final String sqlContext = ElementParameterParser.getValue(node, "__SQL_CONTEXT__");

if(("SQLContext").equals(sqlContext)){
	
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
} else if(("HiveContext").equals(sqlContext)){
	
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
}


    stringBuffer.append(TEXT_6);
    
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    String inStructName = codeGenArgument.getRecordStructName(incomingConnection);

    stringBuffer.append(TEXT_7);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(tSqlRowUtil.getTableName(incomingConnection));
    stringBuffer.append(TEXT_16);
    
} // end for

    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(tSqlRowUtil.getSqlQuery());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_27);
    
} else {
	BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
    String validateError = tSqlRowUtil.validate(true, true);
    if (validateError != null) {
        // Cause the job compilation to explicitly fail if there is a problem.
        return "throw new JobConfigurationError(\"" + validateError +"\");";
    }

	String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());
	IConnection incomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
	String inStructName = codeGenArgument.getRecordStructName(incomingConnection);

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(tSqlRowUtil.getOutgoingConnection().getName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    
} // end else

    stringBuffer.append(TEXT_33);
    return stringBuffer.toString();
  }
}
