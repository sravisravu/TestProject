package org.talend.designer.codegen.translators.machinelearning.classification;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IBigDataNode;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TPredictSparkcodeJava
{
  protected static String nl;
  public static synchronized TPredictSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TPredictSparkcodeJava result = new TPredictSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = "_FromRowTo";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_6 = NL + "        ";
  protected final String TEXT_7 = " result = new ";
  protected final String TEXT_8 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_9 = ".getClassSchema().getField(structFields[i].name());" + NL + "            if (avroField != null){" + NL + "                result.put(avroField.pos(), row.get(i));" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_10 = NL + NL + "\t\tpublic static class ";
  protected final String TEXT_11 = "_From";
  protected final String TEXT_12 = "To";
  protected final String TEXT_13 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_14 = ", ";
  protected final String TEXT_15 = "> {" + NL + "" + NL + "\t\t\tpublic ";
  protected final String TEXT_16 = " call(";
  protected final String TEXT_17 = " input) {" + NL + "\t\t\t\t";
  protected final String TEXT_18 = " result = new ";
  protected final String TEXT_19 = "();";
  protected final String TEXT_20 = NL + "\t\t\t\t\t\tif(input.";
  protected final String TEXT_21 = " != null) {" + NL + "\t\t\t\t\t\t\tresult.";
  protected final String TEXT_22 = " = new java.sql.Date(input.";
  protected final String TEXT_23 = ".getTime());" + NL + "\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\tresult.";
  protected final String TEXT_24 = " = null;" + NL + "\t\t\t\t\t\t}";
  protected final String TEXT_25 = NL + "\t\t\t\t\tresult.";
  protected final String TEXT_26 = " = input.";
  protected final String TEXT_27 = ";";
  protected final String TEXT_28 = NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_29 = NL + "public static class ";
  protected final String TEXT_30 = "_FromRowTo";
  protected final String TEXT_31 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_32 = "> {" + NL + "" + NL + "    public ";
  protected final String TEXT_33 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_34 = NL + "        ";
  protected final String TEXT_35 = " result = new ";
  protected final String TEXT_36 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_37 = ".getClassSchema().getField(structFields[i].name());" + NL + "            if (avroField != null){" + NL + "                result.put(avroField.pos(), row.get(i));" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "}";
  protected final String TEXT_38 = NL + NL + "\t\tpublic static class ";
  protected final String TEXT_39 = "_From";
  protected final String TEXT_40 = "To";
  protected final String TEXT_41 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_42 = ", ";
  protected final String TEXT_43 = "> {" + NL + "" + NL + "\t\t\tpublic ";
  protected final String TEXT_44 = " call(";
  protected final String TEXT_45 = " input) {" + NL + "\t\t\t\t";
  protected final String TEXT_46 = " result = new ";
  protected final String TEXT_47 = "();";
  protected final String TEXT_48 = NL + "\t\t\t\t\t\tif(input.";
  protected final String TEXT_49 = " != null) {" + NL + "\t\t\t\t\t\t\tresult.";
  protected final String TEXT_50 = " = new java.sql.Date(input.";
  protected final String TEXT_51 = ".getTime());" + NL + "\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\tresult.";
  protected final String TEXT_52 = " = null;" + NL + "\t\t\t\t\t\t}";
  protected final String TEXT_53 = NL + "\t\t\t\t\tresult.";
  protected final String TEXT_54 = " = input.";
  protected final String TEXT_55 = ";";
  protected final String TEXT_56 = NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_57 = NL + NL + "public static class StringIndexerInverseFunction_";
  protected final String TEXT_58 = NL + "        extends scala.runtime.AbstractFunction1<org.apache.spark.sql.Row, ";
  protected final String TEXT_59 = ">implements Serializable {" + NL + "    /** Default serial version UID. */" + NL + "    private static final long serialVersionUID = 1L;" + NL + "    private final java.util.Map<Object, String> i2s = new java.util.HashMap<>();" + NL + "" + NL + "    StringIndexerInverseFunction_";
  protected final String TEXT_60 = "(org.apache.spark.ml.feature.StringIndexerModel sim) {" + NL + "        for (scala.Tuple2<String, Object> label : scala.collection.JavaConversions.asJavaIterable(" + NL + "                sim.org$apache$spark$ml$feature$StringIndexerModel$$labelToIndex())) {" + NL + "            i2s.put(label._2(), label._1());" + NL + "        }" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public ";
  protected final String TEXT_61 = " apply(org.apache.spark.sql.Row in) {";
  protected final String TEXT_62 = NL + "        ";
  protected final String TEXT_63 = " out = new ";
  protected final String TEXT_64 = "();";
  protected final String TEXT_65 = NL + "                out.";
  protected final String TEXT_66 = " = (";
  protected final String TEXT_67 = ") in.get(in.fieldIndex(\"";
  protected final String TEXT_68 = "\"));";
  protected final String TEXT_69 = NL + "                out.";
  protected final String TEXT_70 = " = i2s.get(in.get(in.fieldIndex(\"";
  protected final String TEXT_71 = "\")));";
  protected final String TEXT_72 = NL + "        return out;" + NL + "    }" + NL + "}";
  protected final String TEXT_73 = NL + "public static class ";
  protected final String TEXT_74 = " extends ";
  protected final String TEXT_75 = " {" + NL + "    public ";
  protected final String TEXT_76 = " temporaryVector;" + NL + "}" + NL + "public static class GetPrediction_";
  protected final String TEXT_77 = NL + "        implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_78 = ", ";
  protected final String TEXT_79 = "> {" + NL + "" + NL + "    org.apache.spark.mllib.classification.SVMModel currentModel;" + NL + "" + NL + "    public GetPrediction_";
  protected final String TEXT_80 = "(" + NL + "            org.apache.spark.mllib.classification.SVMModel currentModel) {" + NL + "        this.currentModel = currentModel;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_81 = " call(";
  protected final String TEXT_82 = " input) {";
  protected final String TEXT_83 = NL + "        ";
  protected final String TEXT_84 = " output = new ";
  protected final String TEXT_85 = "();";
  protected final String TEXT_86 = NL + "                output.";
  protected final String TEXT_87 = " = input.";
  protected final String TEXT_88 = ";";
  protected final String TEXT_89 = NL + "        output.label = currentModel.predict(input.temporaryVector);" + NL + "        return output;" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class GetEncodedStruct_";
  protected final String TEXT_90 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_91 = "> {" + NL + "" + NL + "    String vectorName;" + NL + "" + NL + "    public GetEncodedStruct_";
  protected final String TEXT_92 = "(String vectorName) {" + NL + "        this.vectorName = vectorName;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_93 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_94 = NL + "        ";
  protected final String TEXT_95 = " output = new ";
  protected final String TEXT_96 = "();" + NL + "        try {" + NL + "            output.temporaryVector = (";
  protected final String TEXT_97 = ") input.get(input.fieldIndex(this.vectorName));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }";
  protected final String TEXT_98 = NL + "                output.";
  protected final String TEXT_99 = " = (";
  protected final String TEXT_100 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_101 = "\"));";
  protected final String TEXT_102 = NL + "        return output;" + NL + "    }" + NL + "}";
  protected final String TEXT_103 = NL + "public static class ";
  protected final String TEXT_104 = " extends ";
  protected final String TEXT_105 = " {" + NL + "    public ";
  protected final String TEXT_106 = " temporaryVector;" + NL + "}" + NL + "public static class GetPrediction_";
  protected final String TEXT_107 = NL + "        implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_108 = ", ";
  protected final String TEXT_109 = "> {" + NL + "" + NL + "    org.apache.spark.mllib.clustering.KMeansModel currentModel;" + NL + "" + NL + "    public GetPrediction_";
  protected final String TEXT_110 = "(" + NL + "            org.apache.spark.mllib.clustering.KMeansModel currentModel) {" + NL + "        this.currentModel = currentModel;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_111 = " call(";
  protected final String TEXT_112 = " input) {";
  protected final String TEXT_113 = NL + "        ";
  protected final String TEXT_114 = " output = new ";
  protected final String TEXT_115 = "();";
  protected final String TEXT_116 = NL + "                output.";
  protected final String TEXT_117 = " = input.";
  protected final String TEXT_118 = ";";
  protected final String TEXT_119 = NL + "        output.label = currentModel.predict(input.temporaryVector);" + NL + "        return output;" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class GetEncodedStruct_";
  protected final String TEXT_120 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_121 = "> {" + NL + "" + NL + "    String vectorName;" + NL + "" + NL + "    public GetEncodedStruct_";
  protected final String TEXT_122 = "(String vectorName) {" + NL + "        this.vectorName = vectorName;" + NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_123 = " call(org.apache.spark.sql.Row input) {";
  protected final String TEXT_124 = NL + "        ";
  protected final String TEXT_125 = " output = new ";
  protected final String TEXT_126 = "();" + NL + "        try {" + NL + "            output.temporaryVector = (";
  protected final String TEXT_127 = ") input.get(input.fieldIndex(this.vectorName));" + NL + "        } catch (java.lang.ClassCastException e) {" + NL + "            // nothing, return null" + NL + "        }";
  protected final String TEXT_128 = NL + "                output.";
  protected final String TEXT_129 = " = (";
  protected final String TEXT_130 = ") input.get(input.fieldIndex(\"";
  protected final String TEXT_131 = "\"));";
  protected final String TEXT_132 = NL + "        return output;" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final String modelType = ElementParameterParser.getValue((INode) ((BigDataCodeGeneratorArgument) argument).getArgument(), "__MODEL_TYPE__");

// NAIVEBAYES sparkcode
if("NAIVEBAYES".equals(modelType)){

    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
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

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_9);
    
// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.

org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

// Some of the incoming connections might share the same schema (and then the same rowXStruct). We must generate the below code only once by schema (if necessary).
java.util.Set<String> knownStructNames = new java.util.HashSet();

for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
	String originalStructName = codeGenArgument.getRecordStructName(incomingConnection);
	if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(originalStructName)) {
		java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
		String suggestedDfStructName = "DF_"+originalStructName;
		String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, originalStructName);
		knownStructNames.add(originalStructName);

    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_19);
    
				for(IMetadataColumn column : columns) {
					if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_24);
    
				} else {

    stringBuffer.append(TEXT_25);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_27);
    
				}
			} // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_28);
    
	} // end if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(originalStructName))
} // end for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections())

    

    
} // LINEAR_REGRESSION sparkcode 
else if("LINEAR_REGRESSION".equals(modelType)){

    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
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

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_37);
    
// If the incoming rowStruct contains a Date field (always typed as java.util.Date),
// we must generate a new structure which replaces these java.util.Date instances by
// java.sql.Date instances since DataFrames only support java.sql.Date.

org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

// Some of the incoming connections might share the same schema (and then the same rowXStruct). We must generate the below code only once by schema (if necessary).
java.util.Set<String> knownStructNames = new java.util.HashSet();

for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
	String originalStructName = codeGenArgument.getRecordStructName(incomingConnection);
	if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(originalStructName)) {
		java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
		String suggestedDfStructName = "DF_"+originalStructName;
		String dfStructName = avroRecordStructGenerator.generateRecordStructForDataFrame(suggestedDfStructName, originalStructName);
		knownStructNames.add(originalStructName);

    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_47);
    
				for(IMetadataColumn column : columns) {
					if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_48);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    
				} else {

    stringBuffer.append(TEXT_53);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_55);
    
				}
			} // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_56);
    
	} // end if(tSqlRowUtil.containsDateFields(incomingConnection) && !knownStructNames.contains(originalStructName))
} // end for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections())

    

    
} // ML_CLASSIFICATION (LOGISTIC_REGRESSION || RANDOM_FOREST || DECISION_TREE || GRADIENT_BOOSTED) sparkcode 
else if(("LOGISTIC_REGRESSION".equals(modelType)) || ("RANDOM_FOREST".equals(modelType)) || ("DECISION_TREE".equals(modelType)) || ("GRADIENT_BOOSTED".equals(modelType))){

    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas == null) || (metadatas.size() == 0))
    return "" ;
IMetadataTable metadata = metadatas.get(0);
if (metadata == null)
    return "";
List<IMetadataColumn> columns = metadata.getListColumns();
if (columns == null)
    return "";
List<? extends IConnection> conns = node.getIncomingConnections();
if (conns == null || conns.size() == 0 || !conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
    return "";
IConnection inConn = conns.get(0);
conns = node.getOutgoingConnections();
if (conns == null || conns.size() == 0 || !conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
    return "";
IConnection outConn = conns.get(0);

String inRowStruct = codeGenArgument.getRecordStructName(inConn);
String inConnName = inConn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();

// This is set to true if the output label is not double format.
// TODO: check if the labelColumn is double and provide a unique labelColumn.
boolean needsLabelIndexer = true;
String inputLabelColumn = "unique82464359435"; // TO READ FROM MODEL!
String labelColumn = "label";

    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_64);
    
        for (IMetadataColumn column: columns) {
            if (!labelColumn.equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_65);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_68);
    
            } else {
                
    stringBuffer.append(TEXT_69);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(inputLabelColumn);
    stringBuffer.append(TEXT_71);
    
            }
        }
        
    stringBuffer.append(TEXT_72);
    
}// SVM_CLASSIFICATION sparkcode 
else if("SVM_CLASSIFICATION".equals(modelType)){

    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if((metadatas!=null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
        columns = metadata.getListColumns();
    }
}
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(columns == null || columns.isEmpty() || conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inRowStructEncoded = conn.getName() + "Encoded";
String connName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


    stringBuffer.append(TEXT_73);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_85);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_86);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_88);
    
            }
        }
        
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_97);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_101);
    
            }
        }
        
    stringBuffer.append(TEXT_102);
    
}// KMEANS sparkcode 
else if("KMEANS".equals(modelType)){

    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";
final String vectorClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.mllib.linalg.Vector"
    : "org.apache.spark.ml.linalg.Vector";

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if((metadatas!=null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
        columns = metadata.getListColumns();
    }
}
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

List<? extends IConnection> outConns = node.getOutgoingConnections();
IConnection outConn = null;
if(outConns != null && outConns.size() > 0 && outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    outConn = outConns.get(0);
}

if(columns == null || columns.isEmpty() || conn == null || outConn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);
String inRowStructEncoded = conn.getName() + "Encoded";
String connName = conn.getName();

String outRowStruct = codeGenArgument.getRecordStructName(outConn);
String outConnName = outConn.getName();


    stringBuffer.append(TEXT_103);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_115);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_116);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_118);
    
            }
        }
        
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(inRowStructEncoded);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(vectorClass);
    stringBuffer.append(TEXT_127);
    
        for (IMetadataColumn column: columns) {
            if (!"label".equals(column.getLabel())) {
                
    stringBuffer.append(TEXT_128);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(),
                                column.isNullable()));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_131);
    
            }
        }
        
    stringBuffer.append(TEXT_132);
    
}

    return stringBuffer.toString();
  }
}
