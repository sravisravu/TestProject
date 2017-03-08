package org.talend.designer.codegen.translators.processing.fields;

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

public class TExtractParquetFieldsMrcodeJava
{
  protected static String nl;
  public static synchronized TExtractParquetFieldsMrcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TExtractParquetFieldsMrcodeJava result = new TExtractParquetFieldsMrcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t\t\t\t\t";
  protected final String TEXT_2 = NL + NL + "public static class NanoTimeUtils_";
  protected final String TEXT_3 = " {" + NL + "" + NL + "    static final long NANOS_PER_HOUR = java.util.concurrent.TimeUnit.HOURS.toNanos(1);" + NL + "    static final long NANOS_PER_MINUTE = java.util.concurrent.TimeUnit.MINUTES.toNanos(1);" + NL + "    static final long NANOS_PER_SECOND = java.util.concurrent.TimeUnit.SECONDS.toNanos(1);" + NL + "    static final long NANOS_PER_DAY = java.util.concurrent.TimeUnit.DAYS.toNanos(1);" + NL + "" + NL + "    private static final ThreadLocal<java.util.Calendar> parquetGMTCalendar = new ThreadLocal<java.util.Calendar>();" + NL + "    private static final ThreadLocal<java.util.Calendar> parquetLocalCalendar = new ThreadLocal<java.util.Calendar>();" + NL + "" + NL + "    private static java.util.Calendar getGMTCalendar() {" + NL + "        // Calendar.getInstance calculates the current-time needlessly, so cache" + NL + "        // an instance." + NL + "        if (parquetGMTCalendar.get() == null) {" + NL + "            parquetGMTCalendar.set(java.util.Calendar.getInstance(java.util.TimeZone" + NL + "                    .getTimeZone(\"GMT\")));" + NL + "        }" + NL + "        return parquetGMTCalendar.get();" + NL + "    }" + NL + "" + NL + "    private static java.util.Calendar getLocalCalendar() {" + NL + "        if (parquetLocalCalendar.get() == null) {" + NL + "            parquetLocalCalendar.set(java.util.Calendar.getInstance());" + NL + "        }" + NL + "        return parquetLocalCalendar.get();" + NL + "    }" + NL + "" + NL + "    private static java.util.Calendar getCalendar(boolean skipConversion) {" + NL + "        java.util.Calendar calendar = skipConversion ? getLocalCalendar() : getGMTCalendar();" + NL + "        calendar.clear();" + NL + "        return calendar;" + NL + "    }" + NL + "" + NL + "    public static java.sql.Timestamp getTimestamp(parquet.example.data.simple.NanoTime nt, boolean skipConversion) {" + NL + "        int julianDay = nt.getJulianDay();" + NL + "        long nanosOfDay = nt.getTimeOfDayNanos();" + NL + "" + NL + "        long remainder = nanosOfDay;" + NL + "        julianDay += remainder / NANOS_PER_DAY;" + NL + "        remainder %= NANOS_PER_DAY;" + NL + "        if (remainder < 0) {" + NL + "            remainder += NANOS_PER_DAY;" + NL + "            julianDay--;" + NL + "        }" + NL + "" + NL + "        jodd.datetime.JDateTime jDateTime = new jodd.datetime.JDateTime((double) julianDay);" + NL + "        java.util.Calendar calendar = getCalendar(skipConversion);" + NL + "        calendar.set(java.util.Calendar.YEAR, jDateTime.getYear());" + NL + "        calendar.set(java.util.Calendar.MONTH, jDateTime.getMonth() - 1);" + NL + "        calendar.set(java.util.Calendar.DAY_OF_MONTH, jDateTime.getDay());" + NL + "" + NL + "        int hour = (int) (remainder / (NANOS_PER_HOUR));" + NL + "        remainder = remainder % (NANOS_PER_HOUR);" + NL + "        int minutes = (int) (remainder / (NANOS_PER_MINUTE));" + NL + "        remainder = remainder % (NANOS_PER_MINUTE);" + NL + "        int seconds = (int) (remainder / (NANOS_PER_SECOND));" + NL + "        long nanos = remainder % NANOS_PER_SECOND;" + NL + "" + NL + "        calendar.set(java.util.Calendar.HOUR_OF_DAY, hour);" + NL + "        calendar.set(java.util.Calendar.MINUTE, minutes);" + NL + "        calendar.set(java.util.Calendar.SECOND, seconds);" + NL + "        java.sql.Timestamp ts = new java.sql.Timestamp(calendar.getTimeInMillis());" + NL + "        ts.setNanos((int) nanos);" + NL + "        return ts;" + NL + "    }" + NL + "}" + NL + "" + NL + "\t\t\t\t\tpublic static class TalendParquetInputMapper_";
  protected final String TEXT_4 = " extends MapReduceBase implements Mapper<LongWritable, Object, NullWritable, ";
  protected final String TEXT_5 = "Struct> {" + NL + "" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_6 = "Struct ";
  protected final String TEXT_7 = " = null;" + NL + "" + NL + "\t\t\t\t\t\tpublic void map(LongWritable key, Object objectContainer," + NL + "\t\t\t\t\t\t\t\torg.apache.hadoop.mapred.OutputCollector<NullWritable, ";
  protected final String TEXT_8 = "Struct> output, org.apache.hadoop.mapred.Reporter reporter)" + NL + "\t\t\t\t\t\t\t\tthrows IOException {" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t\t\tparquet.hadoop.mapred.Container<parquet.example.data.Group> container = (parquet.hadoop.mapred.Container<parquet.example.data.Group>) objectContainer;" + NL + "\t\t\t\t\t\t\tparquet.example.data.Group value = container.get();" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_9 = " = new ";
  protected final String TEXT_10 = "Struct();" + NL + "\t\t\t\t\t";
  protected final String TEXT_11 = NL + "                                if(value.getType().containsField(\"";
  protected final String TEXT_12 = "\") && value.getFieldRepetitionCount(\"";
  protected final String TEXT_13 = "\") > 0) {" + NL + "\t\t\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_15 = ".";
  protected final String TEXT_16 = " = value.getInteger(\"";
  protected final String TEXT_17 = "\", 0);" + NL + "\t\t\t\t\t";
  protected final String TEXT_18 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = " = BigDataParserUtils.parseTo_Byte(value.getInteger(\"";
  protected final String TEXT_21 = "\", 0));" + NL + "\t\t\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_23 = ".";
  protected final String TEXT_24 = " = value.getDouble(\"";
  protected final String TEXT_25 = "\", 0);" + NL + "\t\t\t\t\t";
  protected final String TEXT_26 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = " = value.getLong(\"";
  protected final String TEXT_29 = "\", 0);" + NL + "\t\t\t\t\t";
  protected final String TEXT_30 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_31 = ".";
  protected final String TEXT_32 = " = value.getFloat(\"";
  protected final String TEXT_33 = "\", 0);" + NL + "\t\t\t\t\t";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_35 = ".";
  protected final String TEXT_36 = " = new Integer(value.getInteger(\"";
  protected final String TEXT_37 = "\", 0)).shortValue();" + NL + "\t\t\t\t\t";
  protected final String TEXT_38 = NL + "\t\t\t\t\t\t\t\t\t\t if(value.getType().getType(\"";
  protected final String TEXT_39 = "\").isPrimitive() && value.getType().getType(\"";
  protected final String TEXT_40 = "\").asPrimitiveType().getPrimitiveTypeName() == parquet.schema.PrimitiveType.PrimitiveTypeName.BOOLEAN) {" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_41 = ".";
  protected final String TEXT_42 = " = value.getBoolean(\"";
  protected final String TEXT_43 = "\", 0);" + NL + "\t\t\t\t\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_44 = ".";
  protected final String TEXT_45 = " = new Boolean(value.getString(\"";
  protected final String TEXT_46 = "\", 0));" + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t";
  protected final String TEXT_47 = NL + "\t                                    if(value.getType().getType(\"";
  protected final String TEXT_48 = "\").getOriginalType() == null) {" + NL + "\t                                        ";
  protected final String TEXT_49 = ".";
  protected final String TEXT_50 = " = BigDataParserUtils.parseTo_BigDecimal(value.getString(\"";
  protected final String TEXT_51 = "\", 0));" + NL + "\t                                    } else if(parquet.schema.OriginalType.DECIMAL == value.getType().getType(\"";
  protected final String TEXT_52 = "\").getOriginalType()) {" + NL + "\t                                        ";
  protected final String TEXT_53 = ".";
  protected final String TEXT_54 = " = new java.math.BigDecimal(new java.math.BigInteger(value.getBinary(\"";
  protected final String TEXT_55 = "\", 0).getBytes()), value.getType().getType(\"";
  protected final String TEXT_56 = "\").asPrimitiveType().getDecimalMetadata().getScale());" + NL + "\t                                    }" + NL + "\t\t\t\t\t";
  protected final String TEXT_57 = NL + "                                    \t";
  protected final String TEXT_58 = ".";
  protected final String TEXT_59 = " = value.getBinary(\"";
  protected final String TEXT_60 = "\", 0).getBytes();" + NL + "\t\t\t\t\t";
  protected final String TEXT_61 = NL + "\t                                    if(parquet.schema.OriginalType.DATE == value.getType().getType(\"";
  protected final String TEXT_62 = "\").getOriginalType()) {" + NL + "\t                                        java.util.Calendar c = new java.util.GregorianCalendar();" + NL + "\t                                        c.setTime(new java.util.Date(0));" + NL + "\t                                        c.add(java.util.Calendar.DAY_OF_YEAR, value.getInteger(\"";
  protected final String TEXT_63 = "\", 0));" + NL + "\t                                        ";
  protected final String TEXT_64 = ".";
  protected final String TEXT_65 = " = c.getTime();" + NL + "\t                                    } else if(value.getType().getType(\"";
  protected final String TEXT_66 = "\").isPrimitive() && value.getType().getType(\"";
  protected final String TEXT_67 = "\").asPrimitiveType().getPrimitiveTypeName() == parquet.schema.PrimitiveType.PrimitiveTypeName.INT96) {" + NL + "\t                                        parquet.example.data.simple.NanoTime nt = parquet.example.data.simple.NanoTime.fromBinary(value.getInt96(value.getType().getFieldIndex(\"";
  protected final String TEXT_68 = "\"), 0));" + NL + "\t                                        ";
  protected final String TEXT_69 = ".";
  protected final String TEXT_70 = " = new java.util.Date(NanoTimeUtils_";
  protected final String TEXT_71 = ".getTimestamp(nt, false).getTime());" + NL + "\t                                    } else {" + NL + "\t                                        ";
  protected final String TEXT_72 = ".";
  protected final String TEXT_73 = " = BigDataParserUtils.parseTo_Date(value.getString(\"";
  protected final String TEXT_74 = "\", 0),";
  protected final String TEXT_75 = ");" + NL + "\t                                    }" + NL + "\t\t\t\t\t";
  protected final String TEXT_76 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_77 = ".";
  protected final String TEXT_78 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_79 = "(value.getString(\"";
  protected final String TEXT_80 = "\", 0));" + NL + "\t\t\t\t\t";
  protected final String TEXT_81 = NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t";
  protected final String TEXT_82 = NL + "\t\t\t\t\t\t\toutput.collect(NullWritable.get(), ";
  protected final String TEXT_83 = ");" + NL + "\t\t\t\t\t\t}" + NL + "" + NL + "\t\t\t\t\t\tpublic void configure(JobConf conf) {" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_84 = " = new ";
  protected final String TEXT_85 = "Struct();" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t\tpublic void close() throws IOException {\t\t\t\t\t" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

INode node = (INode)codeGenArgument.getArgument();

String cid = node.getUniqueName();
String shortCid = cid.replaceAll("_Out", "").replaceAll("_In", "");

List<IMetadataTable> metadatas = node.getMetadataList();

if ((metadatas!=null) && (metadatas.size() > 0)) {
    IMetadataTable metadata = metadatas.get(0);
    if (metadata != null) {
    	String folder = ElementParameterParser.getValue(node,"__FILENAME__");
		
		List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
		if (conns != null){
		
			if (conns.size()>0){
		
				IConnection conn =conns.get(0);
				String connName = conn.getName();
		
				if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
					List<IMetadataColumn> columns = metadata.getListColumns();
					int nbColumns = columns.size();
					
					
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(shortCid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_10);
    
							for(int i=0; i<nbColumns; i++) {
								IMetadataColumn column = columns.get(i); 
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
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    
									} else if(javaType == JavaTypesManager.BYTE) {
					
    stringBuffer.append(TEXT_18);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_21);
    
									} else if(javaType == JavaTypesManager.DOUBLE) {
					
    stringBuffer.append(TEXT_22);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    
									} else if(javaType == JavaTypesManager.LONG) {
					
    stringBuffer.append(TEXT_26);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_29);
    
									} else if(javaType == JavaTypesManager.FLOAT) {
					
    stringBuffer.append(TEXT_30);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_33);
    
									} else if(javaType == JavaTypesManager.SHORT) {
					
    stringBuffer.append(TEXT_34);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_37);
    
									} else if(javaType == JavaTypesManager.BOOLEAN) {
					
    stringBuffer.append(TEXT_38);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_46);
    
									} else if(javaType == JavaTypesManager.BIGDECIMAL) {
					
    stringBuffer.append(TEXT_47);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_56);
    
                                	} else if(javaType == JavaTypesManager.BYTE_ARRAY) {
					
    stringBuffer.append(TEXT_57);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_60);
    
                                	} else if(javaType == JavaTypesManager.DATE) {
					
    stringBuffer.append(TEXT_61);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_75);
    
                                	} else {
					
    stringBuffer.append(TEXT_76);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_78);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_80);
    
									}
					
    stringBuffer.append(TEXT_81);
    
							}
					
    stringBuffer.append(TEXT_82);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_85);
    
				}
			}
		}
	}		
}

    return stringBuffer.toString();
  }
}
