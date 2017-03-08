package org.talend.designer.codegen.translators.dataquality;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TMatchPairingSparkcodeJava
{
  protected static String nl;
  public static synchronized TMatchPairingSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchPairingSparkcodeJava result = new TMatchPairingSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "public static class ";
  protected final String TEXT_3 = "_FromRowTo";
  protected final String TEXT_4 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_5 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_6 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_7 = NL + "        ";
  protected final String TEXT_8 = " result = new ";
  protected final String TEXT_9 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_10 = ".getClassSchema().getField(structFields[i].name());" + NL + "            if (avroField != null){" + NL + "                result.put(avroField.pos(), row.get(i));" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_11 = NL + NL + "        public static class ";
  protected final String TEXT_12 = "_From";
  protected final String TEXT_13 = "To";
  protected final String TEXT_14 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_15 = ", ";
  protected final String TEXT_16 = "> {" + NL + "" + NL + "            public ";
  protected final String TEXT_17 = " call(";
  protected final String TEXT_18 = " input) {";
  protected final String TEXT_19 = NL + "                ";
  protected final String TEXT_20 = " result = new ";
  protected final String TEXT_21 = "();";
  protected final String TEXT_22 = NL + "                        if(input.";
  protected final String TEXT_23 = " != null) {" + NL + "                            result.";
  protected final String TEXT_24 = " = new java.sql.Date(input.";
  protected final String TEXT_25 = ".getTime());" + NL + "                        } else {" + NL + "                            result.";
  protected final String TEXT_26 = " = null;" + NL + "                        }";
  protected final String TEXT_27 = NL + "                    result.";
  protected final String TEXT_28 = " = input.";
  protected final String TEXT_29 = ";";
  protected final String TEXT_30 = NL + "                return result;" + NL + "            }" + NL + "        }";
  protected final String TEXT_31 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.DataFrame";//keep using dataFrame even on spark 2.0

    
//IO Connections
IConnection incomingConnection = null;
if (node.getIncomingConnections() != null) {
 for (IConnection in : node.getIncomingConnections()) {
     if (in.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
         incomingConnection = in;
         break;
     }
 }
}
String incomingConnectionName = incomingConnection.getName();
String incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);

IConnection suspectConnection = null;
boolean hasSuspectConnection = false;
List<? extends IConnection> outgoingSuspectConnections = node.getOutgoingConnections("SUSPECT");
if (outgoingSuspectConnections.size() > 0) {
    suspectConnection = outgoingSuspectConnections.get(0);
}
if(suspectConnection != null){
    hasSuspectConnection = true;
}

IConnection exactConnection = null;
boolean hasExactConnection = false;
List<? extends IConnection> outgoingExactConnections = node.getOutgoingConnections("EXACT");
if (outgoingExactConnections.size() > 0) {
    exactConnection = outgoingExactConnections.get(0);
}
if(exactConnection != null){
    hasExactConnection = true;
}

IConnection uniqueConnection = null;
boolean hasUniqueConnection = false;
List<? extends IConnection> outgoingUniqueConnections = node.getOutgoingConnections("UNIQUE");
if (outgoingUniqueConnections.size() > 0) {
    uniqueConnection = outgoingUniqueConnections.get(0);
}
if(uniqueConnection != null){
    hasUniqueConnection = true;
}

//Component params
Boolean hasPairID = ElementParameterParser.getBooleanValue(node,"__PAIR_ID__");
//SuffixArrayBlocking output col name
String pairID = hasPairID ? "pairID" : "pairID_" + cid;
//count col name in the exact output schema
String cnt = hasExactConnection ? "count" : "cnt_" + cid;


    
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas == null) || (metadatas.size() == 0))
return "" ;
IMetadataTable metadata = metadatas.get(0);
if (metadata == null)
return "";
List<IMetadataColumn> columns = metadata.getListColumns();
List<String> outStructNames = new ArrayList<String>();
if(hasSuspectConnection){
    outStructNames.add(codeGenArgument.getRecordStructName(suspectConnection));
}
if(hasExactConnection){
    outStructNames.add(codeGenArgument.getRecordStructName(exactConnection));
}
if(hasUniqueConnection){
    outStructNames.add(codeGenArgument.getRecordStructName(uniqueConnection));
}

    stringBuffer.append(TEXT_1);
    
if(outStructNames.size() > 0){

try {
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

for(String outStructName: outStructNames){

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_10);
    
}
// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.

org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

// Some of the incoming connections might share the same schema (and then the same rowXStruct). We must generate the below code only once by schema (if necessary).
java.util.Set<String> knownStructNames = new java.util.HashSet();

    if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(incomingStructName)) {
        String suggestedDfStructName = "DF_"+incomingStructName;
        String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, incomingStructName);
        knownStructNames.add(incomingStructName);

    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_21);
    
                for(IMetadataColumn column : columns) {
                    if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_22);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_26);
    
                } else {

    stringBuffer.append(TEXT_27);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_29);
    
                }
            } // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_30);
    
    }
} catch (java.lang.Exception e) {
}

    stringBuffer.append(TEXT_31);
    
}

    return stringBuffer.toString();
  }
}
