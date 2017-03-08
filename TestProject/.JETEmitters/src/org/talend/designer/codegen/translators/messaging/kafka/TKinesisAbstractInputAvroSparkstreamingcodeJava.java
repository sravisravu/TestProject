package org.talend.designer.codegen.translators.messaging.kafka;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TKinesisAbstractInputAvroSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TKinesisAbstractInputAvroSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKinesisAbstractInputAvroSparkstreamingcodeJava result = new TKinesisAbstractInputAvroSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + NL + "public static class ";
  protected final String TEXT_2 = "_MapToOutputStruct implements org.apache.spark.api.java.function.PairFunction<byte[], NullWritable, ";
  protected final String TEXT_3 = "> {" + NL + "" + NL + "    private transient org.apache.avro.io.DatumReader<org.apache.avro.generic.GenericRecord> datumReader = null;" + NL + "" + NL + "    private transient org.apache.avro.generic.GenericRecord record = null;" + NL + "" + NL + "    private org.apache.avro.io.BinaryDecoder decoder;" + NL;
  protected final String TEXT_4 = NL + "        private String avroSchema;" + NL + "" + NL + "        public ";
  protected final String TEXT_5 = "_MapToOutputStruct(String avroSchema) {" + NL + "            this.avroSchema = avroSchema;" + NL + "        }";
  protected final String TEXT_6 = NL + NL + "    @Override" + NL + "    public scala.Tuple2<NullWritable, ";
  protected final String TEXT_7 = "> call(byte[] input) throws Exception {" + NL + "        // initialize here due to serialization issue" + NL + "        if (datumReader == null) {";
  protected final String TEXT_8 = NL + "                org.apache.avro.Schema schema = new org.apache.avro.Schema.Parser().parse(avroSchema);";
  protected final String TEXT_9 = NL + "                org.apache.avro.Schema schema = createSchema();";
  protected final String TEXT_10 = NL + "            datumReader = new org.apache.avro.generic.GenericDatumReader<org.apache.avro.generic.GenericRecord>(schema);" + NL + "            record = new org.apache.avro.generic.GenericData.Record(schema);" + NL + "        }";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = " output = new ";
  protected final String TEXT_13 = "();" + NL + "        decoder = org.apache.avro.io.DecoderFactory.get().binaryDecoder(input, decoder);" + NL + "" + NL + "        try {" + NL + "            output.set(datumReader.read(record, decoder));" + NL + "        } catch(java.io.IOException e) {";
  protected final String TEXT_14 = NL + "                LOG.error(e);";
  protected final String TEXT_15 = NL + "        }" + NL + "        return new scala.Tuple2<NullWritable, org.apache.hadoop.io.ObjectWritable>(NullWritable.get(), output) ;" + NL + "    }" + NL;
  protected final String TEXT_16 = NL + "        private org.apache.avro.Schema createSchema() {" + NL + "            //set schema" + NL + "            List<org.apache.avro.Schema.Field> fields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "            List<org.apache.avro.Schema> unionSchema = null;" + NL;
  protected final String TEXT_17 = NL + "            fields.add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_18 = "\",org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_19 = "),null,null));";
  protected final String TEXT_20 = NL + "            unionSchema = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "            unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_21 = "));" + NL + "            unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));" + NL + "            fields.add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_22 = "\",org.apache.avro.Schema.createUnion(unionSchema),null,null));" + NL;
  protected final String TEXT_23 = NL + "            return org.apache.avro.Schema.createRecord(fields);" + NL + "        }";
  protected final String TEXT_24 = NL + "}" + NL;
  protected final String TEXT_25 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

String connectionNameStruct = "Struct";
boolean useHierarchical = "true".equals(ElementParameterParser.getValue(node, "__USE_HIERARCHICAL__"));
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));


String avroRecordStruct = "org.apache.hadoop.io.ObjectWritable";
codeGenArgument.getRecordStructGenerator().reserveRecordStructName(node.getOutgoingConnections().get(0), avroRecordStruct);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(avroRecordStruct);
    stringBuffer.append(TEXT_3);
    
    if (useHierarchical) {
        
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
    }
    
    stringBuffer.append(TEXT_6);
    stringBuffer.append(avroRecordStruct);
    stringBuffer.append(TEXT_7);
    
            if (useHierarchical) {
                
    stringBuffer.append(TEXT_8);
    
            } else {
                
    stringBuffer.append(TEXT_9);
    
            }
            
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(avroRecordStruct);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(avroRecordStruct);
    stringBuffer.append(TEXT_13);
    
            if (isLog4jEnabled) {
                
    stringBuffer.append(TEXT_14);
    
            }
            
    stringBuffer.append(TEXT_15);
    
    if (!useHierarchical) {
        
    stringBuffer.append(TEXT_16);
    
        java.util.Map<JavaType, String> talendTypeToAvroType = new HashMap<JavaType, String>();
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

        List<IMetadataColumn> columns = node.getMetadataList().get(0).getListColumns();
        int nbColumns = columns.size();

        for (int i = 0; i < nbColumns; i++ ){
            IMetadataColumn column = columns.get(i);
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
            String columnName = column.getLabel();
            boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());

            if(isPrimitive) {
    
    stringBuffer.append(TEXT_17);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_19);
    
            } else {
    
    stringBuffer.append(TEXT_20);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_22);
    
         }
        }
    
    stringBuffer.append(TEXT_23);
    
    }
    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
