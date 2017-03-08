package org.talend.designer.codegen.translators.misc;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TRowGeneratorMrconfigJava
{
  protected static String nl;
  public static synchronized TRowGeneratorMrconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRowGeneratorMrconfigJava result = new TRowGeneratorMrconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t";
  protected final String TEXT_2 = NL + "\t";
  protected final String TEXT_3 = NL + NL + "int nb_max_row_";
  protected final String TEXT_4 = " = ";
  protected final String TEXT_5 = ";" + NL + "String tempFolder_";
  protected final String TEXT_6 = " = genTempFolderForComponent(\"";
  protected final String TEXT_7 = "\");" + NL;
  protected final String TEXT_8 = NL + "    tempFolder_";
  protected final String TEXT_9 = " = ";
  protected final String TEXT_10 = " + ";
  protected final String TEXT_11 = " + \"/\" + pid + \"/";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "    log.info(\"using \" + tempFolder_";
  protected final String TEXT_15 = " + \" as temp folder for generating the rows\");";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "List<org.apache.avro.Schema.Field> fields_";
  protected final String TEXT_18 = " = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "List<org.apache.avro.Schema> unionSchema_";
  protected final String TEXT_19 = " = null;";
  protected final String TEXT_20 = NL + "        fields_";
  protected final String TEXT_21 = ".add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_22 = "\",org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_23 = "),null,null));";
  protected final String TEXT_24 = NL + "        unionSchema_";
  protected final String TEXT_25 = " = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "        unionSchema_";
  protected final String TEXT_26 = ".add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_27 = "));" + NL + "        unionSchema_";
  protected final String TEXT_28 = ".add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));" + NL + "        fields_";
  protected final String TEXT_29 = ".add(new org.apache.avro.Schema.Field(\"";
  protected final String TEXT_30 = "\",org.apache.avro.Schema.createUnion(unionSchema_";
  protected final String TEXT_31 = "),null,null));";
  protected final String TEXT_32 = NL + "org.apache.avro.Schema schema_";
  protected final String TEXT_33 = " = org.apache.avro.Schema.createRecord(\"record\", null, null, false);" + NL + "schema_";
  protected final String TEXT_34 = ".setFields(fields_";
  protected final String TEXT_35 = ");" + NL + "org.apache.avro.io.DatumWriter<org.apache.avro.generic.GenericRecord> datumWriter_";
  protected final String TEXT_36 = " = new org.apache.avro.generic.GenericDatumWriter<org.apache.avro.generic.GenericRecord>(schema_";
  protected final String TEXT_37 = ");" + NL + "org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord> writer_";
  protected final String TEXT_38 = " = new org.apache.avro.file.DataFileWriter<org.apache.avro.generic.GenericRecord>(datumWriter_";
  protected final String TEXT_39 = ");" + NL + "writer_";
  protected final String TEXT_40 = ".setSyncInterval(job.getInt(org.apache.avro.mapred.AvroOutputFormat.SYNC_INTERVAL_KEY, org.apache.avro.file.DataFileConstants.DEFAULT_SYNC_INTERVAL));" + NL;
  protected final String TEXT_41 = NL + "java.net.URI currentURI_";
  protected final String TEXT_42 = "_config = FileSystem.getDefaultUri(ctx.hadoopConfiguration());" + NL + "FileSystem.setDefaultUri(ctx.hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_43 = "));" + NL + "fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_44 = NL + NL + "writer_";
  protected final String TEXT_45 = ".create(schema_";
  protected final String TEXT_46 = ", fs.create(new Path(tempFolder_";
  protected final String TEXT_47 = ", \"records.avro\")));" + NL;
  protected final String TEXT_48 = NL + "\tFileSystem.setDefaultUri(ctx.hadoopConfiguration(), currentURI_";
  protected final String TEXT_49 = "_config);" + NL + "\tfs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_50 = NL + NL + "class ";
  protected final String TEXT_51 = "Randomizer{";
  protected final String TEXT_52 = NL + "        public ";
  protected final String TEXT_53 = " getRandom";
  protected final String TEXT_54 = "() {";
  protected final String TEXT_55 = NL + "                return ";
  protected final String TEXT_56 = ";";
  protected final String TEXT_57 = NL + "                ";
  protected final String TEXT_58 = "[] ";
  protected final String TEXT_59 = "Table = new ";
  protected final String TEXT_60 = "[] { ";
  protected final String TEXT_61 = " };" + NL + "                java.util.Random random";
  protected final String TEXT_62 = " = new java.util.Random();" + NL + "                return ";
  protected final String TEXT_63 = "Table[random";
  protected final String TEXT_64 = ".nextInt(";
  protected final String TEXT_65 = "Table.length)];";
  protected final String TEXT_66 = " " + NL + "                return ";
  protected final String TEXT_67 = ";";
  protected final String TEXT_68 = NL + "        }";
  protected final String TEXT_69 = NL + "}";
  protected final String TEXT_70 = NL;
  protected final String TEXT_71 = "Randomizer rand";
  protected final String TEXT_72 = " = new ";
  protected final String TEXT_73 = "Randomizer();" + NL + "Object value_";
  protected final String TEXT_74 = " = null;" + NL + "for(int i";
  protected final String TEXT_75 = " = 0; i";
  protected final String TEXT_76 = " < nb_max_row_";
  protected final String TEXT_77 = "; i";
  protected final String TEXT_78 = "++){" + NL + "    org.apache.avro.generic.GenericRecord record_";
  protected final String TEXT_79 = " = new org.apache.avro.generic.GenericData.Record(schema_";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = NL + "        value_";
  protected final String TEXT_82 = " = rand";
  protected final String TEXT_83 = ".getRandom";
  protected final String TEXT_84 = "();";
  protected final String TEXT_85 = NL + "            if(value_";
  protected final String TEXT_86 = " != null){";
  protected final String TEXT_87 = NL + "                    record_";
  protected final String TEXT_88 = ".put(\"";
  protected final String TEXT_89 = "\", ((Byte)(value_";
  protected final String TEXT_90 = ")).intValue());";
  protected final String TEXT_91 = NL + "                    record_";
  protected final String TEXT_92 = ".put(\"";
  protected final String TEXT_93 = "\", ((Short)(value_";
  protected final String TEXT_94 = ")).intValue());";
  protected final String TEXT_95 = NL + "                    record_";
  protected final String TEXT_96 = ".put(\"";
  protected final String TEXT_97 = "\", (int)(char)(Character)(value_";
  protected final String TEXT_98 = "));";
  protected final String TEXT_99 = NL + "                    record_";
  protected final String TEXT_100 = ".put(\"";
  protected final String TEXT_101 = "\", java.nio.ByteBuffer.wrap((byte[])(value_";
  protected final String TEXT_102 = ")));";
  protected final String TEXT_103 = NL + "                    record_";
  protected final String TEXT_104 = ".put(\"";
  protected final String TEXT_105 = "\", ";
  protected final String TEXT_106 = ".toPlainString());";
  protected final String TEXT_107 = NL + "                    record_";
  protected final String TEXT_108 = ".put(\"";
  protected final String TEXT_109 = "\", ((java.util.Date)value_";
  protected final String TEXT_110 = ").getTime());";
  protected final String TEXT_111 = NL + "                    record_";
  protected final String TEXT_112 = ".put(\"";
  protected final String TEXT_113 = "\", value_";
  protected final String TEXT_114 = ");";
  protected final String TEXT_115 = NL + "            }";
  protected final String TEXT_116 = NL + "    writer_";
  protected final String TEXT_117 = ".append(record_";
  protected final String TEXT_118 = ");    " + NL + "}" + NL + "writer_";
  protected final String TEXT_119 = ".close();" + NL + "\t" + NL + "\t" + NL + "\t" + NL + "\tMultipleInputs.addInputPath(job, ";
  protected final String TEXT_120 = "StructInputFormat.class, ChainMapper.class, \"";
  protected final String TEXT_121 = "\");" + NL + "\t";
  protected final String TEXT_122 = "StructInputFormat.addInputPath(job, tempFolder_";
  protected final String TEXT_123 = ");" + NL + "\tchainMapper.setCid(\"";
  protected final String TEXT_124 = "\");" + NL;
  protected final String TEXT_125 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
	
	List<IMetadataTable> metadatas = node.getMetadataList();
	IMetadataTable metadata = null;
	if(metadatas != null && metadatas.size() > 0){
		metadata = metadatas.get(0);
	}
	List<IMetadataColumn> columns = metadata.getListColumns(); 
	String connName = null;
	List<? extends IConnection> conns = node.getOutgoingConnections();
	IConnection outConn = null;
	for(IConnection conn : conns){
		if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
			outConn = conn;
			connName = conn.getName();
		}
	}
	if(metadata == null || connName == null || columns.size() == 0){
		return "";
	}

	
    stringBuffer.append(TEXT_2);
    
List<Map<String, String>> tableValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__VALUES__");

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append((("").equals(ElementParameterParser.getValue(node, "__NB_ROWS__")) ? 100 : ElementParameterParser.getValue(node, "__NB_ROWS__")));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
String uriPrefix = "\"\"";
String tempFolderFromStorage = "\"\"";
// Used for Spark only for now.
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    tempFolderFromStorage = org.talend.designer.spark.generator.storage.SparkStorageUtils.getTempFolder(node);

    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tempFolderFromStorage);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
}

    stringBuffer.append(TEXT_13);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
     } 
    stringBuffer.append(TEXT_16);
    //init avro writer
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    
java.util.Map<JavaType,String> talendTypeToAvroType = new java.util.HashMap<JavaType,String>();
talendTypeToAvroType.put(JavaTypesManager.STRING,"STRING");
talendTypeToAvroType.put(JavaTypesManager.CHARACTER,"INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE_ARRAY,"BYTES");
talendTypeToAvroType.put(JavaTypesManager.INTEGER,"INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE,"INT");
talendTypeToAvroType.put(JavaTypesManager.SHORT,"INT");
talendTypeToAvroType.put(JavaTypesManager.LONG,"LONG");
talendTypeToAvroType.put(JavaTypesManager.FLOAT,"FLOAT");
talendTypeToAvroType.put(JavaTypesManager.DOUBLE,"DOUBLE");
talendTypeToAvroType.put(JavaTypesManager.BOOLEAN,"BOOLEAN");
talendTypeToAvroType.put(JavaTypesManager.BIGDECIMAL,"STRING");
talendTypeToAvroType.put(JavaTypesManager.DATE,"LONG");

for(int i = 0; i < columns.size(); i++){
    IMetadataColumn column = columns.get(i);
    String columnName = column.getLabel();
    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
    boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
    
    if(isPrimitive){
    
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_23);
    
    }else{
    
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(talendTypeToAvroType.get(javaType));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
    }
}

    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
	if(!"\"\"".equals(uriPrefix)) {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_43);
    
	}

    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    
	if(!"\"\"".equals(uriPrefix)) {

    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    
	}

    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
    for(int i = 0; i < tableValues.size(); i++){
        Map<String, String> lineValue = tableValues.get(i);
        //lineValue.get("ARRAY")
        
    stringBuffer.append(TEXT_52);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_54);
    
            /* if column parameter looks like abcd(efgh,...) )  */
            if(lineValue.get("ARRAY").indexOf("(") >0){
            
    stringBuffer.append(TEXT_55);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_56);
     
            /* else if parameter is  separated by , */
            }else if (lineValue.get("ARRAY").indexOf(",") >0){
            
    stringBuffer.append(TEXT_57);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_60);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_65);
    
            }else{
            
    stringBuffer.append(TEXT_66);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_67);
    
            }
            
    stringBuffer.append(TEXT_68);
    
    }
    
    stringBuffer.append(TEXT_69);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    
    for(IMetadataColumn column : columns){
        String columnName = column.getLabel();
        JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
        String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
        
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_84);
    
        boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
        if(!isPrimitive){
        
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    
        }
                if(javaType == JavaTypesManager.BYTE) {
                
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    
                } else if(javaType == JavaTypesManager.SHORT) {
                
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    
                } else if(javaType == JavaTypesManager.CHARACTER) {
                
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    
                } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
                
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    
                } else if(javaType == JavaTypesManager.BIGDECIMAL) {
                
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getPrecision() == null? "((java.math.BigDecimal)value_"+cid+")" : "((java.math.BigDecimal)value_"+cid+").setScale(" + column.getPrecision() + ", java.math.RoundingMode.HALF_UP)");
    stringBuffer.append(TEXT_106);
    
                } else if(javaType == JavaTypesManager.DATE) {
                    
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    
                } else {
                
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    
                }
        if(!isPrimitive){
        
    stringBuffer.append(TEXT_115);
    
        }
    }
    
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(TEXT_125);
    return stringBuffer.toString();
  }
}
