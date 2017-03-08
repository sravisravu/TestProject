package org.talend.designer.codegen.translators.databases.mysql;

import java.util.List;
import java.util.Map;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TMysqlInputSparkcodeJava
{
  protected static String nl;
  public static synchronized TMysqlInputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMysqlInputSparkcodeJava result = new TMysqlInputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = "_FromRowTo";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = "> {" + NL + "    java.util.Set<String> fieldsToTrim = new java.util.HashSet<String>();" + NL + "" + NL + "    public ";
  protected final String TEXT_5 = "_FromRowTo";
  protected final String TEXT_6 = "(){";
  protected final String TEXT_7 = NL + "                fieldsToTrim.add(\"";
  protected final String TEXT_8 = "\");";
  protected final String TEXT_9 = NL + "    }" + NL + "" + NL + "    public ";
  protected final String TEXT_10 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = " result = new ";
  protected final String TEXT_13 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "        \t\torg.apache.spark.sql.types.StructField sparkSqlField = structFields[i];" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_14 = ".getClassSchema().getField(sparkSqlField.name());" + NL + "            if (avroField != null && row.get(i) != null){" + NL + "                if (sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.StringType)" + NL + "                        && (";
  protected final String TEXT_15 = " || fieldsToTrim.contains(sparkSqlField.name()))) {" + NL + "                    result.put(avroField.pos(), ((String) row.get(i)).trim());" + NL + "                } else if(isFloatAvroType(avroField)) {" + NL + "                \t\tfillFloatAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else if(isDoubleAvroType(avroField)) {" + NL + "\t\t\t\t\t\t\tfillDoubleAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else if(isShortAvroType(avroField)) {" + NL + "\t\t\t\t\t\t\tfillShortAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else if(isCharAvroType(avroField)) {" + NL + "\t\t\t\t\t\t\tfillCharAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else if(isByteAvroType(avroField)) {" + NL + "\t\t\t\t\t\t\tfillByteAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else if(isBigDecimalAvroType(avroField)) {" + NL + "\t\t\t\t\t\t\tfillBigDecimalAvroField(result, avroField.pos(), row.get(i), sparkSqlField);" + NL + " \t             } else {" + NL + "                    result.put(avroField.pos(), row.get(i));" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "" + NL + "\t\tprivate boolean isCharConversionNeeded(org.apache.avro.Schema.Field avroField, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\treturn isCharAvroType(avroField) &&" + NL + "\t\t\t\tsparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.StringType);" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillFloatAvroField(";
  protected final String TEXT_16 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.FloatType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.DoubleType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Float((Double) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType() instanceof org.apache.spark.sql.types.DecimalType) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Float((java.math.BigDecimal) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillDoubleAvroField(";
  protected final String TEXT_17 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.DoubleType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.FloatType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Double((Float) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType() instanceof org.apache.spark.sql.types.DecimalType) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Double((java.math.BigDecimal) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillShortAvroField(";
  protected final String TEXT_18 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.ShortType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.IntegerType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Short((Integer) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.LongType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Short((Long) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillByteAvroField(";
  protected final String TEXT_19 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.ByteType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.IntegerType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Byte((Integer) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.LongType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Byte((Long) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.BooleanType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Byte((Boolean) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillBigDecimalAvroField(";
  protected final String TEXT_20 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType() instanceof org.apache.spark.sql.types.DecimalType) {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.IntegerType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_BigDecimal((Integer) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.LongType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_BigDecimal((Long) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.FloatType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_BigDecimal((Float) value));" + NL + "\t\t\t} else if(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.DoubleType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_BigDecimal((Double) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate void fillCharAvroField(";
  protected final String TEXT_21 = " result, int fieldIndex, Object value, org.apache.spark.sql.types.StructField sparkSqlField) {" + NL + "\t\t\tif(sparkSqlField.dataType().equals(org.apache.spark.sql.types.DataTypes.StringType)) {" + NL + "\t\t\t\tresult.put(fieldIndex, BigDataParserUtils.parseTo_Character((String) value));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tresult.put(fieldIndex, value);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isIntAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\treturn isAvroType(avroField, org.apache.avro.Schema.Type.INT);" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isLongAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\treturn isAvroType(avroField, org.apache.avro.Schema.Type.LONG);" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isFloatAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\treturn isAvroType(avroField, org.apache.avro.Schema.Type.FLOAT);" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isDoubleAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\treturn isAvroType(avroField, org.apache.avro.Schema.Type.DOUBLE);" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isBigDecimalAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\t// BigDecimal type does not exist in Avro schemas. It is actually mapped as a String with the 'java-class' attribute setted to 'java.math.BigDecimal'." + NL + "\t\t\treturn isAvroType(avroField, \"java.math.BigDecimal\");" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isShortAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\t// Short type does not exist in Avro schemas. It is actually mapped as a String with the 'java-class' attribute setted to 'java.lang.Short'." + NL + "\t\t\treturn isAvroType(avroField, \"java.lang.Short\");" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isCharAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\t// Character type does not exist in Avro schemas. It is actually mapped as a String with the 'java-class' attribute setted to 'java.lang.Character'." + NL + "\t\t\treturn isAvroType(avroField, \"java.lang.Character\");" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isByteAvroType(org.apache.avro.Schema.Field avroField) {" + NL + "\t\t\t// Byte type does not exist in Avro schemas. It is actually mapped as a String with the 'java-class' attribute setted to 'java.lang.Byte'." + NL + "\t\t\treturn isAvroType(avroField, \"java.lang.Byte\");" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isAvroType(org.apache.avro.Schema.Field avroField, org.apache.avro.Schema.Type avroType) {" + NL + "\t\t\tboolean result = false;" + NL + "\t\t\tif (avroType.equals(avroField.schema().getType())) {" + NL + "\t\t\t\tresult = true;" + NL + "\t\t\t} else if(org.apache.avro.Schema.Type.UNION.equals(avroField.schema().getType())) {" + NL + "\t\t\t\tfor (org.apache.avro.Schema currentAvroType : avroField.schema().getTypes()) {" + NL + "\t\t\t\t\tif(avroType.equals(currentAvroType.getType())) {" + NL + "\t\t\t\t\t\tresult = true;" + NL + "\t\t\t\t\t\tbreak;" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\treturn result;" + NL + "\t\t}" + NL + "" + NL + "\t\tprivate boolean isAvroType(org.apache.avro.Schema.Field avroField, String javaClass) {" + NL + "\t\t\tboolean result = false;" + NL + "\t\t\tif(javaClass != null){" + NL + "\t\t\t\tif(javaClass.equals(avroField.schema().getProp(\"java-class\"))) {" + NL + "\t\t\t\t\tresult = true;" + NL + "\t\t\t\t} else if(org.apache.avro.Schema.Type.UNION.equals(avroField.schema().getType())) {" + NL + "\t\t\t\t\tfor (org.apache.avro.Schema avroType : avroField.schema().getTypes()) {" + NL + "\t\t\t\t\t\tif(javaClass.equals(avroType.getProp(\"java-class\"))) {" + NL + "\t\t\t\t\t\t\tresult = true;" + NL + "\t\t\t\t\t\t\tbreak;" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t\treturn result;" + NL + "\t\t}" + NL + "}";
  protected final String TEXT_22 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
// We use the tSQLRowUtilFUnction to get the generator of Row->Struct function
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(false, true);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}

boolean whetherTrimAllCol = ("true").equals(ElementParameterParser.getValue(node, "__TRIM_ALL_COLUMN__"));
List<Map<String, String>> trimColumnList = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__TRIM_COLUMN__");
String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_6);
    
        for(Map<String, String> trimColumn : trimColumnList) {
            if(("true").equals(trimColumn.get("TRIM"))) {
                
    stringBuffer.append(TEXT_7);
    stringBuffer.append(trimColumn.get("SCHEMA_COLUMN"));
    stringBuffer.append(TEXT_8);
    
            }
        }
        
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(whetherTrimAllCol);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
