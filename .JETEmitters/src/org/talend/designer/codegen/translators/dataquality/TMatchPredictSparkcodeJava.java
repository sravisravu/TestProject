package org.talend.designer.codegen.translators.dataquality;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class TMatchPredictSparkcodeJava
{
  protected static String nl;
  public static synchronized TMatchPredictSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchPredictSparkcodeJava result = new TMatchPredictSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "public static class StringIndexerInverseFunction_";
  protected final String TEXT_3 = NL + "        extends scala.runtime.AbstractFunction1<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = ">implements Serializable {" + NL + "    /** Default serial version UID. */" + NL + "    private static final long serialVersionUID = 1L;" + NL + "    private final java.util.Map<Object, String> i2s = new java.util.HashMap<>();" + NL + "" + NL + "    StringIndexerInverseFunction_";
  protected final String TEXT_5 = "(org.apache.spark.ml.feature.StringIndexerModel sim) {" + NL + "        for (scala.Tuple2<String, Object> label : scala.collection.JavaConversions.asJavaIterable(" + NL + "                sim.org$apache$spark$ml$feature$StringIndexerModel$$labelToIndex())) {" + NL + "            i2s.put(label._2(), label._1());" + NL + "        }" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public ";
  protected final String TEXT_6 = " apply(org.apache.spark.sql.Row in) {";
  protected final String TEXT_7 = NL + "        ";
  protected final String TEXT_8 = " out = new ";
  protected final String TEXT_9 = "();";
  protected final String TEXT_10 = NL + "                out.";
  protected final String TEXT_11 = " = (";
  protected final String TEXT_12 = ") in.get(in.fieldIndex(\"";
  protected final String TEXT_13 = "\"));";
  protected final String TEXT_14 = NL + "        out.";
  protected final String TEXT_15 = " = i2s.get(in.get(in.fieldIndex(\"";
  protected final String TEXT_16 = "\")));";
  protected final String TEXT_17 = NL + "            out.";
  protected final String TEXT_18 = " = (String) in.get(in.fieldIndex(\"";
  protected final String TEXT_19 = "\"));";
  protected final String TEXT_20 = NL + NL + "        return out;" + NL + "    }" + NL + "}";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "        public static class ";
  protected final String TEXT_23 = "_FromRowTo";
  protected final String TEXT_24 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_25 = "> {" + NL + "    " + NL + "            public ";
  protected final String TEXT_26 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_27 = NL + "                ";
  protected final String TEXT_28 = " result = new ";
  protected final String TEXT_29 = "();" + NL + "                org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "                for (int i = 0; i < structFields.length; i++) {" + NL + "                    org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_30 = ".getClassSchema().getField(structFields[i].name());" + NL + "                    if (avroField != null){" + NL + "                        result.put(avroField.pos(), row.get(i));" + NL + "                    }" + NL + "                }" + NL + "                return result;" + NL + "            }" + NL + "        }";
  protected final String TEXT_31 = NL + "        public static class ";
  protected final String TEXT_32 = "_FromRowTo";
  protected final String TEXT_33 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_34 = "> {" + NL + "            " + NL + "            public ";
  protected final String TEXT_35 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_36 = NL + "                ";
  protected final String TEXT_37 = " result = new ";
  protected final String TEXT_38 = "();" + NL + "                org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "                for (int i = 0; i < structFields.length; i++) {" + NL + "                    org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_39 = ".getClassSchema().getField(structFields[i].name());" + NL + "                    if (avroField != null){" + NL + "                        result.put(avroField.pos(), row.get(i));" + NL + "                    }" + NL + "                }" + NL + "                return result;" + NL + "            }" + NL + "        }" + NL;
  protected final String TEXT_40 = NL + "            public static class ";
  protected final String TEXT_41 = "_From";
  protected final String TEXT_42 = "To";
  protected final String TEXT_43 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_44 = ", ";
  protected final String TEXT_45 = "> {" + NL + "" + NL + "                public ";
  protected final String TEXT_46 = " call(";
  protected final String TEXT_47 = " input) {";
  protected final String TEXT_48 = NL + "                    ";
  protected final String TEXT_49 = " result = new ";
  protected final String TEXT_50 = "();";
  protected final String TEXT_51 = NL + "                            if(input.";
  protected final String TEXT_52 = " != null) {" + NL + "                                result.";
  protected final String TEXT_53 = " = new java.sql.Date(input.";
  protected final String TEXT_54 = ".getTime());" + NL + "                            } else {" + NL + "                                result.";
  protected final String TEXT_55 = " = null;" + NL + "                            }";
  protected final String TEXT_56 = NL + "                            result.";
  protected final String TEXT_57 = " = input.";
  protected final String TEXT_58 = ";";
  protected final String TEXT_59 = NL + "                     return result;" + NL + "                }" + NL + "            }";

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

// This is set to true if the output label is not double format.
boolean needsLabelIndexer = true;
String inputLabelColumn = "unique82464359435"; // TO READ FROM MODEL!
String labelColumn = "matchingLabel";

    stringBuffer.append(TEXT_1);
    
if(hasSuspectConnection){
    String suspectOutConnectionName = suspectConnection.getName();
    String suspectOutStructName = codeGenArgument.getRecordStructName(suspectConnection);

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(suspectOutStructName);
    stringBuffer.append(TEXT_9);
    
        for (IMetadataColumn column: columns) {
        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_13);
    
        }
        
    stringBuffer.append(TEXT_14);
    stringBuffer.append(labelColumn);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inputLabelColumn);
    stringBuffer.append(TEXT_16);
    
        if(hasPairID){
        
    stringBuffer.append(TEXT_17);
    stringBuffer.append(pairID);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(pairID);
    stringBuffer.append(TEXT_19);
    
        }
        
    stringBuffer.append(TEXT_20);
    
}

    stringBuffer.append(TEXT_21);
    
try {
    TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
    String validateError = tSqlRowUtil.validate(true, false);
    if (validateError != null) {
        // Cause the job compilation to explicitly fail if there is a problem.
        return "throw new JobConfigurationError(\"" + validateError +"\");";
    }
    
    if(hasUniqueConnection){
        String uniqueOutConnectionName = uniqueConnection.getName();
        String uniqueOutStructName = codeGenArgument.getRecordStructName(uniqueConnection);

    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(uniqueOutStructName);
    stringBuffer.append(TEXT_30);
    
    }//end if(hasUniqueConnection)
    
    if(hasExactConnection){ 
        String exactOutConnectionName = exactConnection.getName();
        String exactOutStructName = codeGenArgument.getRecordStructName(exactConnection);

    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(exactOutStructName);
    stringBuffer.append(TEXT_39);
    
    }//end if(hasExactConnection)
    
        // If the incoming rowStruct contains a Date field (always typed as java.util.Date),
        // we must generate a new structure which replaces these java.util.Date instances by
        // java.sql.Date instances since DataFrames only support java.sql.Date.
       org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator =
           (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

       // Some of the incoming connections might share the same schema (and then the same rowXStruct). We must generate the below code only once by schema (if necessary).
       java.util.Set<String> knownStructNames = new java.util.HashSet();

       if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(incomingStructName)) {
            String suggestedDfStructName = "DF_"+incomingStructName;
            String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, incomingStructName);
            knownStructNames.add(incomingStructName);

    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_50);
    
                    for(IMetadataColumn column : columns) {
                        if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_55);
    
                        } else {

    stringBuffer.append(TEXT_56);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_58);
    
                        }
                     } // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_59);
    
        } // end if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(incomingStructName))
} catch (java.lang.Exception e) {
    // Do not generate sparkcode part : a clean exception has to be generated within sparkconfig part
}

    return stringBuffer.toString();
  }
}
