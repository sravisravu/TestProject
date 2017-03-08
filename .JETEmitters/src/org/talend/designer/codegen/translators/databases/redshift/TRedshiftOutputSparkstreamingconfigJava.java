package org.talend.designer.codegen.translators.databases.redshift;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;

public class TRedshiftOutputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TRedshiftOutputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRedshiftOutputSparkstreamingconfigJava result = new TRedshiftOutputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_2 = "> ";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ".map(new ";
  protected final String TEXT_5 = "_From";
  protected final String TEXT_6 = "To";
  protected final String TEXT_7 = "());";
  protected final String TEXT_8 = NL + "    ";
  protected final String TEXT_9 = ".foreachRDD(new ";
  protected final String TEXT_10 = "_ForeachRDDOutput(job));";
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);

// Convert util Date to sql Date
for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
    String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
    String inRddName = "rdd_"+incomingConnection.getName();
    String rddName, structName;

    if(tSqlRowUtil.containsDateFields(incomingConnection)) {
        // Additional map to convert from java.util.Date to java.sql.Date
        String newRddName = "tmp_rdd_"+incomingConnection.getName();
        String newStructName = "DF_"+inStructName+"AvroRecord";
        
    stringBuffer.append(TEXT_1);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_7);
    
        rddName = newRddName;
        structName = newStructName;
    }else{
        // No need for additional map
        rddName = inRddName;
        structName = inStructName;
    }

    
    stringBuffer.append(TEXT_8);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
}


    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
