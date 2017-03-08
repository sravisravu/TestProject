package org.talend.designer.codegen.translators.machinelearning;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;
import org.talend.core.model.process.IBigDataNode;

public class TModelEncoderSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TModelEncoderSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TModelEncoderSparkstreamingcodeJava result = new TModelEncoderSparkstreamingcodeJava();
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
  protected final String TEXT_29 = NL + NL + "    static class ";
  protected final String TEXT_30 = "_JavaSQLContextSingleton {" + NL + "      static private transient org.apache.spark.sql.SQLContext instance = null;" + NL + "      static public org.apache.spark.sql.SQLContext getInstance(org.apache.spark.SparkContext sparkContext) {" + NL + "        if (instance == null) {" + NL + "          instance = new org.apache.spark.sql.SQLContext(sparkContext);" + NL + "        }" + NL + "        return instance;" + NL + "      }" + NL + "    }" + NL + "" + NL + "\tpublic static class Function_";
  protected final String TEXT_31 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_32 = ">, org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_33 = ">> {" + NL + "" + NL + "        private java.util.List<org.apache.spark.ml.Transformer> transformations;" + NL + "" + NL + "        public Function_";
  protected final String TEXT_34 = "(java.util.List<org.apache.spark.ml.Transformer> transformations){" + NL + "            this.transformations = transformations;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_35 = "> call(org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_36 = "> inputrdd){" + NL + "            // Create dataframe from first tModelEncoder's input rdd" + NL + "            org.apache.spark.sql.SQLContext sqlContext = ";
  protected final String TEXT_37 = "_JavaSQLContextSingleton.getInstance(inputrdd.context());" + NL;
  protected final String TEXT_38 = NL + "            ";
  protected final String TEXT_39 = " inputDataframe = sqlContext.createDataFrame(inputrdd, ";
  protected final String TEXT_40 = ".class);" + NL + "" + NL + "            // apply transformations to input RDD";
  protected final String TEXT_41 = NL + "            ";
  protected final String TEXT_42 = " transformedInputDataframe = applyTransformations(inputDataframe, transformations);" + NL + "" + NL + "            // Creat output JavaRDD" + NL + "            return transformedInputDataframe.toJavaRDD().map(new ";
  protected final String TEXT_43 = "_FromRowTo";
  protected final String TEXT_44 = "());" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_45 = " applyTransformations(";
  protected final String TEXT_46 = " df, java.util.List<org.apache.spark.ml.Transformer> transformations){" + NL + "        \t\t";
  protected final String TEXT_47 = " currentDf = df;" + NL + "        \t\tfor(org.apache.spark.ml.Transformer transformation : transformations) {" + NL + "        \t\t\tcurrentDf = transformation.transform(currentDf);" + NL + "        \t\t}" + NL + "        \t\treturn currentDf;" + NL + "        }" + NL + "\t}";
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = NL;

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

    
TModelEncoderUtil tModelEncoderUtil = new TModelEncoderUtil(node);
String inputDStreamStructName = tModelEncoderUtil.getFirstModelEncoderStructName(codeGenArgument);
outStructName = codeGenArgument.getRecordStructName(tModelEncoderUtil.getOutgoingConnection());

if(tModelEncoderUtil.isLastModelEncoder()) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(inputDStreamStructName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(inputDStreamStructName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(inputDStreamStructName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_47);
    
}

    stringBuffer.append(TEXT_48);
    stringBuffer.append(TEXT_49);
    return stringBuffer.toString();
  }
}
