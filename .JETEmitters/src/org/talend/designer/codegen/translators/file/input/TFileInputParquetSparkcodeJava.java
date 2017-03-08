package org.talend.designer.codegen.translators.file.input;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TFileInputParquetSparkcodeJava
{
  protected static String nl;
  public static synchronized TFileInputParquetSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputParquetSparkcodeJava result = new TFileInputParquetSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "                    public static class TalendParquetInputFormat_";
  protected final String TEXT_2 = NL + "                        extends parquet.hadoop.ParquetInputFormat<Object> {" + NL + "                    }" + NL;
  protected final String TEXT_3 = NL + NL + "public static class NanoTimeUtils_";
  protected final String TEXT_4 = " {" + NL + "" + NL + "    static final long NANOS_PER_HOUR = java.util.concurrent.TimeUnit.HOURS.toNanos(1);" + NL + "    static final long NANOS_PER_MINUTE = java.util.concurrent.TimeUnit.MINUTES.toNanos(1);" + NL + "    static final long NANOS_PER_SECOND = java.util.concurrent.TimeUnit.SECONDS.toNanos(1);" + NL + "    static final long NANOS_PER_DAY = java.util.concurrent.TimeUnit.DAYS.toNanos(1);" + NL + "" + NL + "    private static final ThreadLocal<java.util.Calendar> parquetGMTCalendar = new ThreadLocal<java.util.Calendar>();" + NL + "    private static final ThreadLocal<java.util.Calendar> parquetLocalCalendar = new ThreadLocal<java.util.Calendar>();" + NL + "" + NL + "    private static java.util.Calendar getGMTCalendar() {" + NL + "        // Calendar.getInstance calculates the current-time needlessly, so cache" + NL + "        // an instance." + NL + "        if (parquetGMTCalendar.get() == null) {" + NL + "            parquetGMTCalendar.set(java.util.Calendar.getInstance(java.util.TimeZone" + NL + "                    .getTimeZone(\"GMT\")));" + NL + "        }" + NL + "        return parquetGMTCalendar.get();" + NL + "    }" + NL + "" + NL + "    private static java.util.Calendar getLocalCalendar() {" + NL + "        if (parquetLocalCalendar.get() == null) {" + NL + "            parquetLocalCalendar.set(java.util.Calendar.getInstance());" + NL + "        }" + NL + "        return parquetLocalCalendar.get();" + NL + "    }" + NL + "" + NL + "    private static java.util.Calendar getCalendar(boolean skipConversion) {" + NL + "        java.util.Calendar calendar = skipConversion ? getLocalCalendar() : getGMTCalendar();" + NL + "        calendar.clear();" + NL + "        return calendar;" + NL + "    }" + NL + "" + NL + "    public static java.sql.Timestamp getTimestamp(parquet.example.data.simple.NanoTime nt, boolean skipConversion) {" + NL + "        int julianDay = nt.getJulianDay();" + NL + "        long nanosOfDay = nt.getTimeOfDayNanos();" + NL + "" + NL + "        long remainder = nanosOfDay;" + NL + "        julianDay += remainder / NANOS_PER_DAY;" + NL + "        remainder %= NANOS_PER_DAY;" + NL + "        if (remainder < 0) {" + NL + "            remainder += NANOS_PER_DAY;" + NL + "            julianDay--;" + NL + "        }" + NL + "" + NL + "        jodd.datetime.JDateTime jDateTime = new jodd.datetime.JDateTime((double) julianDay);" + NL + "        java.util.Calendar calendar = getCalendar(skipConversion);" + NL + "        calendar.set(java.util.Calendar.YEAR, jDateTime.getYear());" + NL + "        calendar.set(java.util.Calendar.MONTH, jDateTime.getMonth() - 1);" + NL + "        calendar.set(java.util.Calendar.DAY_OF_MONTH, jDateTime.getDay());" + NL + "" + NL + "        int hour = (int) (remainder / (NANOS_PER_HOUR));" + NL + "        remainder = remainder % (NANOS_PER_HOUR);" + NL + "        int minutes = (int) (remainder / (NANOS_PER_MINUTE));" + NL + "        remainder = remainder % (NANOS_PER_MINUTE);" + NL + "        int seconds = (int) (remainder / (NANOS_PER_SECOND));" + NL + "        long nanos = remainder % NANOS_PER_SECOND;" + NL + "" + NL + "        calendar.set(java.util.Calendar.HOUR_OF_DAY, hour);" + NL + "        calendar.set(java.util.Calendar.MINUTE, minutes);" + NL + "        calendar.set(java.util.Calendar.SECOND, seconds);" + NL + "        java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTimeInMillis());" + NL + "        ts.setNanos((int) nanos);" + NL + "        return ts;" + NL + "    }" + NL + "}" + NL + "" + NL + "                public static class toNullWritable_";
  protected final String TEXT_5 = NL + "                    implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<Void, Object>," + NL + "                        NullWritable, ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "                        public scala.Tuple2<NullWritable, ";
  protected final String TEXT_7 = "> call(" + NL + "                                    scala.Tuple2<Void, Object> arg0) throws Exception {" + NL + "                            parquet.example.data.Group input = (parquet.example.data.Group)arg0._2;";
  protected final String TEXT_8 = NL + "                            ";
  protected final String TEXT_9 = " output = new ";
  protected final String TEXT_10 = "();" + NL;
  protected final String TEXT_11 = NL + "                            if(input.getType().containsField(\"";
  protected final String TEXT_12 = "\") && input.getFieldRepetitionCount(\"";
  protected final String TEXT_13 = "\") > 0) {";
  protected final String TEXT_14 = NL + "                                    output.";
  protected final String TEXT_15 = " = input.getInteger(\"";
  protected final String TEXT_16 = "\", 0);";
  protected final String TEXT_17 = NL + "                                    output.";
  protected final String TEXT_18 = " = BigDataParserUtils.parseTo_Byte(input.getInteger(\"";
  protected final String TEXT_19 = "\", 0));";
  protected final String TEXT_20 = NL + "                                    output.";
  protected final String TEXT_21 = " = input.getDouble(\"";
  protected final String TEXT_22 = "\", 0);";
  protected final String TEXT_23 = NL + "                                    output.";
  protected final String TEXT_24 = " = input.getLong(\"";
  protected final String TEXT_25 = "\", 0);";
  protected final String TEXT_26 = NL + "                                    output.";
  protected final String TEXT_27 = " = input.getFloat(\"";
  protected final String TEXT_28 = "\", 0);";
  protected final String TEXT_29 = NL + "                                    output.";
  protected final String TEXT_30 = " = new Integer(input.getInteger(\"";
  protected final String TEXT_31 = "\", 0)).shortValue();";
  protected final String TEXT_32 = NL + "                                     if(input.getType().getType(\"";
  protected final String TEXT_33 = "\").isPrimitive() && input.getType().getType(\"";
  protected final String TEXT_34 = "\").asPrimitiveType().getPrimitiveTypeName() == parquet.schema.PrimitiveType.PrimitiveTypeName.BOOLEAN) {" + NL + "                                        output.";
  protected final String TEXT_35 = " = input.getBoolean(\"";
  protected final String TEXT_36 = "\", 0);" + NL + "                                    } else {" + NL + "                                        output.";
  protected final String TEXT_37 = " = new Boolean(input.getString(\"";
  protected final String TEXT_38 = "\", 0));" + NL + "                                    }";
  protected final String TEXT_39 = NL + "                                    if(input.getType().getType(\"";
  protected final String TEXT_40 = "\").getOriginalType() == null) {" + NL + "                                        output.";
  protected final String TEXT_41 = " = BigDataParserUtils.parseTo_BigDecimal(input.getString(\"";
  protected final String TEXT_42 = "\", 0));" + NL + "                                    } else if(parquet.schema.OriginalType.DECIMAL == input.getType().getType(\"";
  protected final String TEXT_43 = "\").getOriginalType()) {" + NL + "                                        output.";
  protected final String TEXT_44 = " = new java.math.BigDecimal(new java.math.BigInteger(input.getBinary(\"";
  protected final String TEXT_45 = "\", 0).getBytes()), input.getType().getType(\"";
  protected final String TEXT_46 = "\").asPrimitiveType().getDecimalMetadata().getScale());" + NL + "                                    }";
  protected final String TEXT_47 = NL + "                                    output.";
  protected final String TEXT_48 = " = java.nio.ByteBuffer.wrap(input.getBinary(\"";
  protected final String TEXT_49 = "\", 0).getBytes());";
  protected final String TEXT_50 = NL + "                                    if(parquet.schema.OriginalType.DATE == input.getType().getType(\"";
  protected final String TEXT_51 = "\").getOriginalType()) {" + NL + "                                        java.util.Calendar c = new java.util.GregorianCalendar();" + NL + "                                        c.setTime(new java.util.Date(0));" + NL + "                                        c.add(java.util.Calendar.DAY_OF_YEAR, input.getInteger(\"";
  protected final String TEXT_52 = "\", 0));" + NL + "                                        output.";
  protected final String TEXT_53 = " = c.getTime();" + NL + "                                    } else if(input.getType().getType(\"";
  protected final String TEXT_54 = "\").isPrimitive() && input.getType().getType(\"";
  protected final String TEXT_55 = "\").asPrimitiveType().getPrimitiveTypeName() == parquet.schema.PrimitiveType.PrimitiveTypeName.INT96) {" + NL + "                                        parquet.example.data.simple.NanoTime nt = parquet.example.data.simple.NanoTime.fromBinary(input.getInt96(input.getType().getFieldIndex(\"";
  protected final String TEXT_56 = "\"), 0));" + NL + "                                        output.";
  protected final String TEXT_57 = " = new java.util.Date(NanoTimeUtils_";
  protected final String TEXT_58 = ".getTimestamp(nt, false).getTime());" + NL + "                                    } else {" + NL + "                                        output.";
  protected final String TEXT_59 = " = BigDataParserUtils.parseTo_Date(input.getString(\"";
  protected final String TEXT_60 = "\", 0),";
  protected final String TEXT_61 = ");" + NL + "                                    }";
  protected final String TEXT_62 = NL + "                                    output.";
  protected final String TEXT_63 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_64 = "(input.getString(\"";
  protected final String TEXT_65 = "\", 0));";
  protected final String TEXT_66 = NL + "                            }";
  protected final String TEXT_67 = NL + "                            return new scala.Tuple2<NullWritable, ";
  protected final String TEXT_68 = ">(" + NL + "                                NullWritable.get(), output);" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_69 = NL + "                    public static class ";
  protected final String TEXT_70 = "_FromRowTo";
  protected final String TEXT_71 = " implements org.apache.spark.api.java.function.PairFunction<org.apache.spark.sql.Row, NullWritable, ";
  protected final String TEXT_72 = "> {" + NL + "" + NL + "                        public scala.Tuple2<NullWritable, ";
  protected final String TEXT_73 = "> call(org.apache.spark.sql.Row row) {";
  protected final String TEXT_74 = NL + "                            ";
  protected final String TEXT_75 = " result = new ";
  protected final String TEXT_76 = "();" + NL + "                            org.apache.avro.Schema.Field avroField = null;" + NL + "                            org.apache.spark.sql.types.StructField[] structFields = row.schema().fields();" + NL + "                            java.util.Map<String, String> colNameToType = new java.util.HashMap<>();" + NL + "                            for(int i = 0; i < structFields.length; i++) {" + NL + "                                colNameToType.put(structFields[i].name(), structFields[i].dataType().simpleString());" + NL + "                            }" + NL + "                            String colType = \"\";";
  protected final String TEXT_77 = NL + "                                avroField = ";
  protected final String TEXT_78 = ".getClassSchema().getField(\"";
  protected final String TEXT_79 = "\");" + NL + "                                colType = colNameToType.get(\"";
  protected final String TEXT_80 = "\");" + NL + "                                if (avroField != null){";
  protected final String TEXT_81 = NL + "                                        result.put(avroField.pos(), java.nio.ByteBuffer.wrap((byte[])row.getAs(\"";
  protected final String TEXT_82 = "\")));";
  protected final String TEXT_83 = NL + "                                        if(\"binary\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), new String((byte[])row.getAs(\"";
  protected final String TEXT_84 = "\")));" + NL + "                                        } else {" + NL + "                                            result.put(avroField.pos(), (String)row.getAs(\"";
  protected final String TEXT_85 = "\"));" + NL + "                                        }";
  protected final String TEXT_86 = NL + "                                        if(\"int\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), ((Integer)row.getAs(\"";
  protected final String TEXT_87 = "\")).byteValue());" + NL + "                                        } else {" + NL + "                                            result.put(avroField.pos(), row.getAs(\"";
  protected final String TEXT_88 = "\"));" + NL + "                                        }";
  protected final String TEXT_89 = NL + "                                        if(\"int\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), ((Integer)row.getAs(\"";
  protected final String TEXT_90 = "\")).shortValue());" + NL + "                                        } else {" + NL + "                                            result.put(avroField.pos(), row.getAs(\"";
  protected final String TEXT_91 = "\"));" + NL + "                                        }";
  protected final String TEXT_92 = NL + "                                        if(\"binary\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), new String((byte[])row.getAs(\"";
  protected final String TEXT_93 = "\")).charAt(0));" + NL + "                                        } else if(\"string\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), ((String)row.getAs(\"";
  protected final String TEXT_94 = "\")).charAt(0));" + NL + "                                        }";
  protected final String TEXT_95 = NL + "                                        if(\"binary\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), BigDataParserUtils.parseTo_Date(new String((byte[])row.getAs(\"";
  protected final String TEXT_96 = "\")),";
  protected final String TEXT_97 = "));" + NL + "                                        } else {" + NL + "                                            result.put(avroField.pos(), row.getAs(\"";
  protected final String TEXT_98 = "\"));" + NL + "                                        }";
  protected final String TEXT_99 = NL + "                                        if(\"binary\".equalsIgnoreCase(colType)) {" + NL + "                                            result.put(avroField.pos(), BigDataParserUtils.parseTo_BigDecimal(new String((byte[])row.getAs(\"";
  protected final String TEXT_100 = "\"))));" + NL + "                                        } else {" + NL + "                                            result.put(avroField.pos(), row.getAs(\"";
  protected final String TEXT_101 = "\"));" + NL + "                                        }";
  protected final String TEXT_102 = NL + "                                        result.put(avroField.pos(), row.getAs(\"";
  protected final String TEXT_103 = "\"));";
  protected final String TEXT_104 = NL + "                                }";
  protected final String TEXT_105 = NL + "                            return new scala.Tuple2(NullWritable.get(), result);" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_106 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

boolean isNodeInBatchMode = org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node);
boolean isAnInputStreamComponent = false;
if("SPARKSTREAMING".equals(node.getComponent().getType()) && !isNodeInBatchMode) {
    isAnInputStreamComponent = true;
}


// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with InputFormat or native Dataframe API.
final List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
INode sparkConfig = null;
if(sparkConfigs != null && sparkConfigs.size() > 0) {
    sparkConfig = sparkConfigs.get(0);
}

org.talend.hadoop.distribution.ESparkVersion currentSparkVersion = org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3;
if(sparkConfig != null) {
    String sparkDistribution = ElementParameterParser.getValue(sparkConfig, "__DISTRIBUTION__");
    String sparkVersion = ElementParameterParser.getValue(sparkConfig, "__SPARK_VERSION__");
    String sparkApiVersion = ElementParameterParser.getValue(sparkConfig, "__SPARK_API_VERSION__");
    String sparkLocalVersion = ElementParameterParser.getValue(sparkConfig, "__SPARK_LOCAL_VERSION__");

    boolean useLocalMode = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SPARK_LOCAL_MODE__"));
    org.talend.hadoop.distribution.component.SparkBatchComponent sparkBatchDistrib = null;
    if(!useLocalMode) {
        try {
            sparkBatchDistrib = (org.talend.hadoop.distribution.component.SparkBatchComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(sparkDistribution, sparkVersion);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    currentSparkVersion = org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(useLocalMode, sparkLocalVersion, sparkDistribution, sparkApiVersion, sparkBatchDistrib);
}

List<IMetadataTable> metadatas = node.getMetadataList();

if ((metadatas!=null) && (metadatas.size() > 0)) {
    IMetadataTable metadata = metadatas.get(0);
    if (metadata != null) {
        List< ? extends IConnection> conns = node.getOutgoingConnections();

        if ((conns != null) && (conns.size() > 0)) {
            IConnection conn = conns.get(0);
            String connName = conn.getName();
            String connTypeName = codeGenArgument.getRecordStructName(conn);

            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                // If the current Spark version is 1.3, 1.4 or 1.5, we use the RDD InputFormat API.
                if(currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_4 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_5 || isAnInputStreamComponent) {
                
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_10);
    
                        List<IMetadataColumn> columns = metadata.getListColumns();
                        for(IMetadataColumn column: columns) {
                            String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                            String columnName = column.getLabel();

    stringBuffer.append(TEXT_11);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_13);
    
                                if(javaType == JavaTypesManager.INTEGER) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_16);
    
                                } else if(javaType == JavaTypesManager.BYTE) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_19);
    
                                } else if(javaType == JavaTypesManager.DOUBLE) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_22);
    
                                } else if(javaType == JavaTypesManager.LONG) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    
                                } else if(javaType == JavaTypesManager.FLOAT) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    
                                } else if(javaType == JavaTypesManager.SHORT) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_31);
    
                                } else if(javaType == JavaTypesManager.BOOLEAN) {

    stringBuffer.append(TEXT_32);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_38);
    
                                } else if(javaType == JavaTypesManager.BIGDECIMAL) {

    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_46);
    

                                } else if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_47);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_49);
    
                                } else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_50);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_61);
    
                                } else {

    stringBuffer.append(TEXT_62);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_65);
    
                                }

    stringBuffer.append(TEXT_66);
    
                        }

    stringBuffer.append(TEXT_67);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_68);
    
                } else {
                    // If the current Spark version greater than or equals to 1.6, we use the native dataframe API.
                    TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
                    String validateError = tSqlRowUtil.validate(false, true);
                    if (validateError != null) {
                        // Cause the job compilation to explicitly fail if there is a problem.
                        return "throw new JobConfigurationError(\"" + validateError +"\");";
                    }


    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_76);
    
                            List<IMetadataColumn> columns = metadata.getListColumns();
                            int indexCol = -1;
                            for(IMetadataColumn column: columns) {
                                indexCol++;
                                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                                String columnName = column.getLabel();

    stringBuffer.append(TEXT_77);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_80);
    
                                    if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_81);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_82);
    
                                    } else if(javaType == JavaTypesManager.STRING) {

    stringBuffer.append(TEXT_83);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_85);
    
                                    } else if(javaType == JavaTypesManager.BYTE) {

    stringBuffer.append(TEXT_86);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_88);
    
                                    } else if(javaType == JavaTypesManager.SHORT) {

    stringBuffer.append(TEXT_89);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_91);
    
                                    } else if(javaType == JavaTypesManager.CHARACTER) {

    stringBuffer.append(TEXT_92);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_94);
    
                                    } else if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_95);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_98);
    
                                    } else if(javaType == JavaTypesManager.BIGDECIMAL) {

    stringBuffer.append(TEXT_99);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_101);
    
                                    } else {

    stringBuffer.append(TEXT_102);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_103);
    
                                    }

    stringBuffer.append(TEXT_104);
    
                            }

    stringBuffer.append(TEXT_105);
    

                }
            }
        }
    }
}

    stringBuffer.append(TEXT_106);
    return stringBuffer.toString();
  }
}
