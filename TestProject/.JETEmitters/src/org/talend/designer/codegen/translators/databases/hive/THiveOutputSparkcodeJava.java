package org.talend.designer.codegen.translators.databases.hive;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class THiveOutputSparkcodeJava
{
  protected static String nl;
  public static synchronized THiveOutputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THiveOutputSparkcodeJava result = new THiveOutputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = "_From";
  protected final String TEXT_3 = "To";
  protected final String TEXT_4 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_5 = ", ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_7 = " call(";
  protected final String TEXT_8 = " input) {" + NL + "\t\t";
  protected final String TEXT_9 = " result = new ";
  protected final String TEXT_10 = "();";
  protected final String TEXT_11 = NL + "\t\tresult.";
  protected final String TEXT_12 = " = new java.sql.Date(input.";
  protected final String TEXT_13 = ".getTime());";
  protected final String TEXT_14 = NL + "\t\tresult.";
  protected final String TEXT_15 = " = input.";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL + "\t\treturn result;" + NL + "\t}" + NL + "}";
  protected final String TEXT_18 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}


// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.

org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
	if(tSqlRowUtil.containsDateFields(incomingConnection)) {
		java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
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
    
			} else {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    
			}
		} // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_17);
    
	} // end if(tSqlRowUtil.containsDateFields(incomingConnection))
} // end for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections())


    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
