package org.talend.designer.codegen.translators.databases.db_jdbc;

import java.util.List;
import java.util.Map;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TJDBCInputSparkcodeJava
{
  protected static String nl;
  public static synchronized TJDBCInputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJDBCInputSparkcodeJava result = new TJDBCInputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class ";
  protected final String TEXT_2 = "_FromRowTo";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.Function<org.apache.spark.sql.Row, ";
  protected final String TEXT_4 = "> {" + NL + "    java.util.Set<String> fieldsToTrim = new java.util.HashSet<String>();" + NL + "    public ";
  protected final String TEXT_5 = "_FromRowTo";
  protected final String TEXT_6 = "(){";
  protected final String TEXT_7 = NL + "                fieldsToTrim.add(\"";
  protected final String TEXT_8 = "\");";
  protected final String TEXT_9 = NL + "    }" + NL + "    public ";
  protected final String TEXT_10 = " call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = " result = new ";
  protected final String TEXT_13 = "();" + NL + "        org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "        for (int i = 0; i < structFields.length; i++) {" + NL + "            org.apache.avro.Schema.Field avroField = ";
  protected final String TEXT_14 = ".getClassSchema().getField(structFields[i].name());" + NL + "            if (avroField != null){" + NL + "                org.apache.avro.Schema fieldSchema = avroField.schema();" + NL + "            \tString talendType = getTalendType(fieldSchema);" + NL + "            \tboolean trim = ";
  protected final String TEXT_15 = " || fieldsToTrim.contains(structFields[i].name());" + NL + "            \ttry{" + NL + "                \tresult.put(avroField.pos(), handleNull(convertValue(row.get(i), structFields[i].dataType(), talendType, trim), talendType, isNullable(fieldSchema), null));" + NL + "                }catch(Exception e){" + NL + "                \tthrow new RuntimeException(\"col: \" + structFields[i].name() + \" - sparkSQL type: \" + structFields[i].dataType() + \" - talend type:\" + talendType + \" - \" + e.getMessage());" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "" + NL + "    private String getTalendType(org.apache.avro.Schema fieldSchema) {" + NL + "        String talendType = null;" + NL + "        if (org.apache.avro.Schema.Type.UNION.equals(fieldSchema.getType())) {" + NL + "            for (org.apache.avro.Schema childFieldSchema : fieldSchema.getTypes()) {" + NL + "                if (!org.apache.avro.Schema.Type.NULL.equals(childFieldSchema.getType())) {" + NL + "                    if (childFieldSchema.getProp(\"java-class\") != null) {" + NL + "                        talendType = childFieldSchema.getProp(\"java-class\");" + NL + "                    } else {" + NL + "                        talendType = childFieldSchema.getType().getName();" + NL + "                    }" + NL + "                }" + NL + "            }" + NL + "        } else {" + NL + "            if (fieldSchema.getProp(\"java-class\") != null) {" + NL + "                talendType = fieldSchema.getProp(\"java-class\");" + NL + "            } else {" + NL + "                talendType = fieldSchema.getType().getName();" + NL + "            }" + NL + "        }" + NL + "        return talendType;" + NL + "    }" + NL + "" + NL + "    private boolean isNullable(org.apache.avro.Schema fieldSchema) {" + NL + "        boolean nullable = false;" + NL + "        if (org.apache.avro.Schema.Type.UNION.equals(fieldSchema.getType())) {" + NL + "            for (org.apache.avro.Schema childFieldSchema : fieldSchema.getTypes()) {" + NL + "                if (org.apache.avro.Schema.Type.NULL.equals(childFieldSchema.getType())) {" + NL + "                    nullable = true;" + NL + "                    break;" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "        return nullable;" + NL + "    }" + NL + "" + NL + "    private Object convertValue(Object value, org.apache.spark.sql.types.DataType sparkSqlType, String destType, boolean trim) {" + NL + "        if (value != null) {" + NL + "            if (sparkSqlType.equals(org.apache.spark.sql.types.DataTypes.StringType)) {" + NL + "                //trim" + NL + "                value = trim ? ((String)value).trim() : (String)value;" + NL + "            } else if (sparkSqlType.equals(org.apache.spark.sql.types.DataTypes.TimestampType)) {" + NL + "                //convert java.sql.Timestamp to java.util.Date" + NL + "                value = new java.util.Date(((java.sql.Timestamp) value).getTime());" + NL + "            } else if (sparkSqlType.equals(org.apache.spark.sql.types.DataTypes.DateType)) {" + NL + "                //convert java.sql.Date to java.util.Date" + NL + "                value = new java.util.Date(((java.sql.Date) value).getTime());" + NL + "            } else if (sparkSqlType.equals(org.apache.spark.sql.types.DataTypes.BinaryType)) {" + NL + "            \tvalue = java.nio.ByteBuffer.wrap((byte[])value);" + NL + "            }" + NL + "        }" + NL + "        String sourceType = sparkSqlTypeToTalendType.get(sparkSqlType.getClass());" + NL + "        return convertValue(value, sourceType, destType);" + NL + "    }" + NL + "" + NL + "    private Object convertValue(Object value, String sourceType, String destType) {" + NL + "        if (value == null || sourceType.equals(destType) || OBJECT.equals(destType)) {" + NL + "            return value;" + NL + "        }" + NL + "        //TODO consider all the situation between sourceType and destType" + NL + "        if(BYTE.equals(destType)){" + NL + "        \tif(INT.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Byte((Integer)value);" + NL + "        \t}else if(LONG.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Byte((Long)value);" + NL + "        \t}else if(BOOLEAN.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Byte((Boolean)value);" + NL + "        \t}" + NL + "        }else if(CHAR.equals(destType)){" + NL + "        \tif(STRING.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Character((String)value);" + NL + "        \t}else if(BYTE.equals(sourceType) || BYTES.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Character(BigDataParserUtils.parseTo_String((java.nio.ByteBuffer)value));" + NL + "        \t}" + NL + "        }else if(INT.equals(destType)){" + NL + "        \tif(DECIMAL.equals(sourceType)){" + NL + "        \t\treturn ((java.math.BigDecimal)value).intValue();" + NL + "        \t}" + NL + "        }else if(SHORT.equals(destType)){" + NL + "        \tif(INT.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Short((Integer)value);" + NL + "        \t}else if(LONG.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Short((Long)value);" + NL + "        \t}else if(DECIMAL.equals(sourceType)){" + NL + "        \t\treturn ((java.math.BigDecimal)value).shortValueExact();" + NL + "        \t}" + NL + "        }else if(LONG.equals(destType)){" + NL + "        \tif(DECIMAL.equals(sourceType)){" + NL + "        \t\treturn ((java.math.BigDecimal)value).longValue();" + NL + "        \t}" + NL + "        }else if(FLOAT.equals(destType)){" + NL + "        \tif(DOUBLE.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Float((Double)value);" + NL + "        \t}else if(DECIMAL.equals(sourceType)){" + NL + "        \t\treturn ((java.math.BigDecimal)value).floatValue();" + NL + "        \t}" + NL + "        }else if(DOUBLE.equals(destType)){" + NL + "        \tif(FLOAT.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_Double((Float)value);" + NL + "        \t}else if(DECIMAL.equals(sourceType)){" + NL + "        \t\treturn ((java.math.BigDecimal)value).doubleValue();" + NL + "        \t}" + NL + "        }else if(DECIMAL.equals(destType)){" + NL + "        \tif(INT.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_BigDecimal((Integer)value);" + NL + "        \t}else if(LONG.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_BigDecimal((Long)value);" + NL + "        \t}else if(FLOAT.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_BigDecimal((Float)value);" + NL + "        \t}else if(DOUBLE.equals(sourceType)){" + NL + "        \t\treturn BigDataParserUtils.parseTo_BigDecimal((Double)value);" + NL + "        \t}" + NL + "        }" + NL + "        return value;" + NL + "    }" + NL + "" + NL + "    private Object handleNull(Object value, String destType, boolean nullable, Object defaultValue) {" + NL + "        if (nullable) {" + NL + "            return value;" + NL + "        } else {" + NL + "            if (value == null) {" + NL + "                if (defaultValue != null) {" + NL + "                    return defaultValue;" + NL + "                } else {" + NL + "                    switch (destType) {" + NL + "                        case BYTE:" + NL + "                        case SHORT:" + NL + "                        case INT:" + NL + "                        case LONG:" + NL + "                        case FLOAT:" + NL + "                        case DOUBLE:" + NL + "                            value = 0;" + NL + "                            break;" + NL + "                        case BOOLEAN:" + NL + "                            value = false;" + NL + "                            break;" + NL + "                        case DECIMAL:" + NL + "                        case STRING:" + NL + "                        case BYTES:" + NL + "                        case DATE:" + NL + "                        case LIST:" + NL + "                            break;" + NL + "                        default:" + NL + "                            break;" + NL + "                    }" + NL + "                    return value;" + NL + "                }" + NL + "            } else {" + NL + "                return value;" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private static final String BYTE = \"java.lang.Byte\";" + NL + "    private static final String SHORT = \"java.lang.Short\";" + NL + "    private static final String INT = \"int\";" + NL + "    private static final String LONG = \"long\";" + NL + "    private static final String FLOAT = \"float\";" + NL + "    private static final String DOUBLE = \"double\";" + NL + "    private static final String DECIMAL = \"java.math.BigDecimal\";" + NL + "    private static final String STRING = \"java.lang.String\";" + NL + "    private static final String BYTES = \"bytes\";" + NL + "    private static final String BOOLEAN = \"boolean\";" + NL + "    private static final String DATE = \"java.util.Date\";" + NL + "    private static final String LIST = \"java.util.List\";" + NL + "    private static final String OBJECT = \"java.lang.Object\";" + NL + "    private static final String CHAR = \"java.lang.Character\";" + NL + "" + NL + "    private static Map<Class<? extends org.apache.spark.sql.types.DataType>, String> sparkSqlTypeToTalendType = new java.util.HashMap<Class<? extends org.apache.spark.sql.types.DataType>, String>();" + NL + "" + NL + "    static {" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.ByteType.getClass(), BYTE);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.ShortType.getClass(), SHORT);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.IntegerType.getClass(), INT);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.LongType.getClass(), LONG);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.FloatType.getClass(), FLOAT);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.DoubleType.getClass(), DOUBLE);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DecimalType.class, DECIMAL);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.StringType.getClass(), STRING);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.BinaryType.getClass(), BYTES);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.BooleanType.getClass(), BOOLEAN);" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.TimestampType.getClass(), DATE);//there is conversation from java.sql.timestamp to java.util.date" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.DataTypes.DateType.getClass(), DATE);//there is conversation from java.sql.date to java.util.date" + NL + "        sparkSqlTypeToTalendType.put(org.apache.spark.sql.types.ArrayType.class, LIST);" + NL + "" + NL + "        //ignore MapType/StructType/StructField" + NL + "    }" + NL + "}";
  protected final String TEXT_16 = NL;

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

String outStructName = codeGenArgument.getRecordStructName(tSqlRowUtil.getOutgoingConnection());

boolean whetherTrimAllCol = ("true").equals(ElementParameterParser.getValue(node, "__TRIM_ALL_COLUMN__"));
List<Map<String, String>> trimColumnList = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__TRIM_COLUMN__");

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
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
