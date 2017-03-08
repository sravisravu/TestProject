package org.talend.designer.codegen.translators.file.output;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TAvroOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TAvroOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TAvroOutputSparkstreamingcodeJava result = new TAvroOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "public static class ";
  protected final String TEXT_3 = "StructOutputFormat extends FileOutputFormat<NullWritable, ";
  protected final String TEXT_4 = "> {" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    protected static class AvroRecordWriter implements RecordWriter<NullWritable, ";
  protected final String TEXT_5 = "> {" + NL + "        org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord> writer = null;" + NL + "        private ContextProperties context;" + NL + "" + NL + "        private org.apache.avro.Schema schema = null;" + NL + "" + NL + "        public AvroRecordWriter(org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord> writer, JobConf job" + NL + "            , org.apache.avro.Schema schema) {" + NL + "            this.context = new ContextProperties(job);" + NL + "            this.writer = writer;" + NL + "            this.schema = schema;" + NL + "        }" + NL + "" + NL + "        public void write(NullWritable key, ";
  protected final String TEXT_6 = " value)" + NL + "                throws IOException {" + NL + "            org.apache.avro.generic.GenericRecord record = new org.apache.avro.generic.GenericData.Record(schema);";
  protected final String TEXT_7 = NL + "                org.apache.avro.generic.GenericRecord keyRecord = new org.apache.avro.generic.GenericData.Record(schema.getFields().get(0).schema());" + NL + "                org.apache.avro.generic.GenericRecord valueRecord = new org.apache.avro.generic.GenericData.Record(schema.getFields().get(1).schema());" + NL + "                record.put(0, keyRecord);" + NL + "                record.put(1, valueRecord);";
  protected final String TEXT_8 = NL + "                    if (value.";
  protected final String TEXT_9 = " != null) {";
  protected final String TEXT_10 = NL + "                    ";
  protected final String TEXT_11 = ".put(\"";
  protected final String TEXT_12 = "\", ((Byte)(value.";
  protected final String TEXT_13 = ")).intValue());";
  protected final String TEXT_14 = NL + "                    ";
  protected final String TEXT_15 = ".put(\"";
  protected final String TEXT_16 = "\", ((Short)(value.";
  protected final String TEXT_17 = ")).intValue());";
  protected final String TEXT_18 = NL + "                    ";
  protected final String TEXT_19 = ".put(\"";
  protected final String TEXT_20 = "\", (int)(char)(Character)(value.";
  protected final String TEXT_21 = "));";
  protected final String TEXT_22 = NL + "                        ";
  protected final String TEXT_23 = ".put(\"";
  protected final String TEXT_24 = "\", value.";
  protected final String TEXT_25 = ");";
  protected final String TEXT_26 = NL + "                        ";
  protected final String TEXT_27 = ".put(\"";
  protected final String TEXT_28 = "\", java.nio.ByteBuffer.wrap(value.";
  protected final String TEXT_29 = "));";
  protected final String TEXT_30 = NL + "                    ";
  protected final String TEXT_31 = ".put(\"";
  protected final String TEXT_32 = "\", value.";
  protected final String TEXT_33 = ".toString());";
  protected final String TEXT_34 = NL + "                    ";
  protected final String TEXT_35 = ".put(\"";
  protected final String TEXT_36 = "\", value.";
  protected final String TEXT_37 = ".getTime());";
  protected final String TEXT_38 = NL + "                    ";
  protected final String TEXT_39 = ".put(\"";
  protected final String TEXT_40 = "\", value.";
  protected final String TEXT_41 = ");";
  protected final String TEXT_42 = NL + "                    }";
  protected final String TEXT_43 = NL + "            writer.append(record);" + NL + "        }" + NL + "" + NL + "        public void close(Reporter reporter) throws IOException {" + NL + "            writer.close();" + NL + "        }" + NL + "    }" + NL + "" + NL + "      void configureDataFileWriter(org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord> writer, JobConf job) throws java.io.UnsupportedEncodingException {";
  protected final String TEXT_44 = NL + "        int level = job.getInt(org.apache.avro.mapred.AvroOutputFormat.DEFLATE_LEVEL_KEY, ";
  protected final String TEXT_45 = ");" + NL + "        org.apache.avro.file.CodecFactory factory = org.apache.avro.file.CodecFactory.deflateCodec(level);" + NL + "        writer.setCodec(factory);";
  protected final String TEXT_46 = NL + NL + "        writer.setSyncInterval(job.getInt(org.apache.avro.mapred.AvroOutputFormat.SYNC_INTERVAL_KEY, org.apache.avro.file.DataFileConstants.DEFAULT_SYNC_INTERVAL));" + NL + "" + NL + "        //copy metadata from job" + NL + "        for (java.util.Map.Entry<String,String> e : job) {" + NL + "              if (e.getKey().startsWith(org.apache.avro.mapred.AvroJob.TEXT_PREFIX)) {" + NL + "                writer.setMeta(e.getKey().substring(org.apache.avro.mapred.AvroJob.TEXT_PREFIX.length()),e.getValue());" + NL + "            }" + NL + "              if (e.getKey().startsWith(org.apache.avro.mapred.AvroJob.BINARY_PREFIX)) {" + NL + "                writer.setMeta(e.getKey().substring(org.apache.avro.mapred.AvroJob.BINARY_PREFIX.length()),java.net.URLDecoder.decode(e.getValue(), \"ISO-8859-1\").getBytes(\"ISO-8859-1\"));" + NL + "           }" + NL + "        }" + NL + "      }" + NL + "" + NL + "    public final static String EXT = \".avro\";" + NL + "" + NL + "    public RecordWriter<NullWritable, ";
  protected final String TEXT_47 = "> getRecordWriter(" + NL + "            FileSystem ignored, JobConf job, String name, Progressable progress) throws IOException{" + NL + "        this.context = new ContextProperties(job);" + NL + "        //set schema";
  protected final String TEXT_48 = NL + "                List<org.apache.avro.Schema.Field> fields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "                List<org.apache.avro.Schema> unionSchema = null;";
  protected final String TEXT_49 = NL + "                        fields.add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_50 = "\"," + NL + "                            org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_51 = "),null,null));";
  protected final String TEXT_52 = NL + "                        unionSchema = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_53 = "));" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));" + NL + "                        fields.add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_54 = "\",org.apache.avro.Schema.createUnion(unionSchema),null,null));";
  protected final String TEXT_55 = NL + "                org.apache.avro.Schema schema = org.apache.avro.Schema.createRecord(\"record\", null, null, false);" + NL + "                schema.setFields(fields);";
  protected final String TEXT_56 = NL + "                List<org.apache.avro.Schema.Field> keyFields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "                List<org.apache.avro.Schema.Field> valueFields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "                List<org.apache.avro.Schema> unionSchema = null;";
  protected final String TEXT_57 = NL + "                        ";
  protected final String TEXT_58 = ".add(new org.apache.avro.Schema.Field(" + NL + "                            \"";
  protected final String TEXT_59 = "\"," + NL + "                            org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_60 = "), null, null));";
  protected final String TEXT_61 = NL + "                        unionSchema = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_62 = "));" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));";
  protected final String TEXT_63 = NL + "                        ";
  protected final String TEXT_64 = ".add(" + NL + "                            new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_65 = "\", org.apache.avro.Schema.createUnion(unionSchema), null, null));";
  protected final String TEXT_66 = NL + NL + "                org.apache.avro.Schema keySchema = org.apache.avro.Schema.createRecord(\"keyRecord\", null, null, false);" + NL + "                keySchema.setFields(keyFields);" + NL + "                org.apache.avro.Schema valueSchema = org.apache.avro.Schema.createRecord(\"valueRecord\", null, null, false);" + NL + "                valueSchema.setFields(valueFields);" + NL + "" + NL + "                // org.apache.avro.Schema schema = org.apache.avro.hadoop.io.AvroKeyValue.getKeyValue(keySchema, valueSchema);" + NL + "                 org.apache.avro.Schema schema = org.apache.avro.Schema.createRecord(" + NL + "                    \"KeyValuePair\", \"A key/value pair\", \"org.apache.avro.mapreduce\", false);" + NL + "                schema.setFields(java.util.Arrays.asList(" + NL + "                    new org.apache.avro.Schema.Field(\"key\", keySchema, \"The key\", null)," + NL + "                    new org.apache.avro.Schema.Field(\"value\", valueSchema, \"The value\", null)));";
  protected final String TEXT_67 = NL + "            org.apache.avro.Schema schema = ";
  protected final String TEXT_68 = ".getClassSchema();";
  protected final String TEXT_69 = NL + NL + "        //create file writer" + NL + "        org.apache.avro.io.DatumWriter<org.apache.avro.generic.GenericRecord> datumWriter = new org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericRecord>(schema);" + NL + "        org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord> writer = new org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord>(datumWriter);" + NL + "" + NL + "        //init writer" + NL + "        Path path = FileOutputFormat.getTaskOutputPath(job, name+EXT);" + NL + "        configureDataFileWriter(writer,job);" + NL + "             writer.create(schema, path.getFileSystem(job).create(path));" + NL + "" + NL + "             return new AvroRecordWriter(writer,job,schema);" + NL + "    }" + NL + "}";
  protected final String TEXT_70 = NL + NL + "    public static class WriteNonEmpty_";
  protected final String TEXT_71 = "_ForeachRDDOutput<KEY, VALUE> implements ";
  protected final String TEXT_72 = " {" + NL + "" + NL + "        /** default serial version UID */" + NL + "        private static final long serialVersionUID = 1L;" + NL + "" + NL + "        private final String prefix;" + NL + "        private final String suffix;" + NL + "        private final Class<KEY> keyClass;" + NL + "        private final Class<VALUE> valueClass;" + NL + "        private final Class<? extends org.apache.hadoop.mapred.OutputFormat<?,?>> outputFormatClass;" + NL + "        private final org.apache.hadoop.mapred.JobConf jobConf;" + NL + "" + NL + "        public WriteNonEmpty_";
  protected final String TEXT_73 = "_ForeachRDDOutput(String prefix, String suffix," + NL + "                Class<KEY> keyClass," + NL + "                Class<VALUE> valueClass," + NL + "                Class<? extends org.apache.hadoop.mapred.OutputFormat<?,?>> outputFormatClass," + NL + "                org.apache.hadoop.mapred.JobConf jobConf) {" + NL + "            this.prefix = prefix;" + NL + "            this.suffix = suffix;" + NL + "            this.keyClass = keyClass;" + NL + "            this.valueClass = valueClass;" + NL + "            this.outputFormatClass = outputFormatClass;" + NL + "            this.jobConf = jobConf;" + NL + "        }" + NL + "" + NL + "        public WriteNonEmpty_";
  protected final String TEXT_74 = "_ForeachRDDOutput(String prefix, String suffix," + NL + "                Class<KEY> keyClass," + NL + "                Class<VALUE> valueClass," + NL + "                Class<? extends org.apache.hadoop.mapred.OutputFormat<?,?>> outputFormatClass) {" + NL + "            this(prefix, suffix, keyClass, valueClass, outputFormatClass, null);" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public ";
  protected final String TEXT_75 = " call(";
  protected final String TEXT_76 = " rdd," + NL + "                org.apache.spark.streaming.Time time) throws Exception {" + NL + "            if (!rdd.isEmpty()) {" + NL + "                if (jobConf != null) {" + NL + "                    rdd.saveAsHadoopFile(prefix + \"-\" + time.milliseconds() + suffix," + NL + "                            keyClass, valueClass, outputFormatClass, jobConf);" + NL + "                } else {" + NL + "                    rdd.saveAsHadoopFile(prefix + \"-\" + time.milliseconds() + suffix," + NL + "                            keyClass, valueClass, outputFormatClass);" + NL + "                }" + NL + "            }";
  protected final String TEXT_77 = NL + "            ";
  protected final String TEXT_78 = NL + "        }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

// Ignore code generation when this node is unnecessary.
List<IMetadataTable> metadatas = node.getMetadataList();
if (metadatas == null || metadatas.size() == 0)
    return "";
IMetadataTable metadata = metadatas.get(0);
if (metadata == null)
    return "";
List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
if (inConns == null || inConns.size() == 0)
    return "";


boolean isSpark = "SPARKSTREAMING".equals(node.getComponent().getType()) || "SPARK".equals(node.getComponent().getType());

// Only used for the DEFLATE mode. Temporary fix in order to avoid code duplication.
boolean compress = "true".equals(ElementParameterParser.getValue(node, "__COMPRESS__"));
String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");
boolean useKeyValue = "true".equals(ElementParameterParser.getValue(node, "__USE_KEYVALUE__"));

// Get the key columns, if they exist.
java.util.HashSet<String> useKeyValueKeys = new java.util.HashSet<String>();
if (useKeyValue) {
    List<Map<String, String>> keyValuesList = (List<Map<String,String>>) ElementParameterParser.getObjectValueXML(node, "__USE_KEYVALUE_KEYS__");
    for (Map<String, String> keyValues: keyValuesList) {
        String outputColumnName = keyValues.get("SCHEMA_COLUMN");
        if ("true".equals(keyValues.get("SELECTED")))
            useKeyValueKeys.add(outputColumnName);
    }
}

IConnection inConn = inConns.get(0);
String connName = inConn.getName();
String connTypeName = codeGenArgument.getRecordStructName(inConn);
boolean isHierarchical = codeGenArgument.getRecordStructGenerator().isHierarchicalRecordStruct(inConn.getName());

List<IMetadataColumn> columns = metadata.getListColumns();
int columnSize = columns.size();


    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_6);
    
            // Generate a semi-flat keyvalue record from the output.
            if (useKeyValue) {
                
    stringBuffer.append(TEXT_7);
    
            }

            // Generate a flat record from the input line.
            for (IMetadataColumn column : columns) {
                String columnName = column.getLabel();
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());

                String recordName = "record";
                if (useKeyValue)
                    recordName = useKeyValueKeys.contains(columnName) ? "keyRecord" : "valueRecord";

                if(!isPrimitive){
                
    stringBuffer.append(TEXT_8);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_9);
    
                }

                if(javaType == JavaTypesManager.BYTE) {
                
    stringBuffer.append(TEXT_10);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_13);
    
                } else if(javaType == JavaTypesManager.SHORT) {
                
    stringBuffer.append(TEXT_14);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    
                } else if(javaType == JavaTypesManager.CHARACTER) {
                
    stringBuffer.append(TEXT_18);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_21);
    
                } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
                    if (isSpark) {
                        
    stringBuffer.append(TEXT_22);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    
                    } else {
                        
    stringBuffer.append(TEXT_26);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_29);
    
                    }
                } else if(javaType == JavaTypesManager.BIGDECIMAL) {
                
    stringBuffer.append(TEXT_30);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_33);
    
                } else if(javaType == JavaTypesManager.DATE) {
                
    stringBuffer.append(TEXT_34);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_37);
    
                } else {
                
    stringBuffer.append(TEXT_38);
    stringBuffer.append(recordName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_41);
    
                }

                if(!isPrimitive){
                
    stringBuffer.append(TEXT_42);
    
                }
            }
            
    stringBuffer.append(TEXT_43);
    if(compress && "DEFLATE".equals(compression)) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(isSpark?"org.apache.avro.file.CodecFactory.DEFAULT_DEFLATE_LEVEL":"org.apache.avro.mapred.AvroOutputFormat.DEFAULT_DEFLATE_LEVEL");
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_47);
    
        if (!isHierarchical) {
            java.util.Map<JavaType,String> talendTypeToAvroType = new java.util.HashMap<JavaType,String>();
            talendTypeToAvroType.put(JavaTypesManager.STRING, "STRING");
            talendTypeToAvroType.put(JavaTypesManager.CHARACTER, "INT");
            talendTypeToAvroType.put(JavaTypesManager.BYTE_ARRAY, "BYTES");
            talendTypeToAvroType.put(JavaTypesManager.INTEGER, "INT");
            talendTypeToAvroType.put(JavaTypesManager.BYTE, "INT");
            talendTypeToAvroType.put(JavaTypesManager.SHORT, "INT");
            talendTypeToAvroType.put(JavaTypesManager.LONG, "LONG");
            talendTypeToAvroType.put(JavaTypesManager.FLOAT, "FLOAT");
            talendTypeToAvroType.put(JavaTypesManager.DOUBLE, "DOUBLE");
            talendTypeToAvroType.put(JavaTypesManager.BOOLEAN, "BOOLEAN");
            talendTypeToAvroType.put(JavaTypesManager.BIGDECIMAL, "STRING");
            talendTypeToAvroType.put(JavaTypesManager.DATE, "LONG");

            if (!useKeyValue) {
                // Generate a flat schema from the metadata table.
                
    stringBuffer.append(TEXT_48);
    

                for (IMetadataColumn column : columns) {
                    String columnName = column.getLabel();
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                    boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());

                    if(isPrimitive) {
                    
    stringBuffer.append(TEXT_49);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_51);
    
                    } else {
                    
    stringBuffer.append(TEXT_52);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_54);
    
                    }
                }
                
    stringBuffer.append(TEXT_55);
    
            } else {
                // Generate a flat-ish schema key/value from the metadata table
                // (two embedded records of cardinality 1).  The format is
                // compatible with https://github.com/apache/avro/blob/trunk/lang/java/mapred/src/main/java/org/apache/avro/hadoop/io/AvroKeyValue.java

                
    stringBuffer.append(TEXT_56);
    

                for (IMetadataColumn column : columns) {
                    String columnName = column.getLabel();
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                    boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());

                    if (isPrimitive) {
                    
    stringBuffer.append(TEXT_57);
    stringBuffer.append( useKeyValueKeys.contains(columnName) ? "keyFields" : "valueFields" );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_60);
    
                    } else {
                    
    stringBuffer.append(TEXT_61);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(TEXT_63);
    stringBuffer.append( useKeyValueKeys.contains(columnName) ? "keyFields" : "valueFields" );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_65);
    
                    }
                }
                
    stringBuffer.append(TEXT_66);
    
            }
        } else {
            // Use the hierarchical schema directly.
            
    stringBuffer.append(TEXT_67);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_68);
    
        }
        
    stringBuffer.append(TEXT_69);
    
boolean writeEmptyBatches = "true".equals(ElementParameterParser.getValue(node, "__WRITE_EMPTY_BATCHES__"));
if (!writeEmptyBatches) {
    
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionWithTimeJavaPairRDD(
                    codeGenArgument.getSparkVersion(), "KEY", "VALUE");
    
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_78);
    
} // End ForeachRDD helper function

    
}

    return stringBuffer.toString();
  }
}
