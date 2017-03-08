package org.talend.designer.codegen.translators.databases.dynamodb;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmap.TMapAdapter;
import org.talend.designer.spark.generator.storage.DynamoDBSparkStorage;

public class TDynamoDBLookupInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TDynamoDBLookupInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDynamoDBLookupInputSparkstreamingcodeJava result = new TDynamoDBLookupInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_3 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_4 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_5 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_6 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_7 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_8 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_9 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_10 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_11 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_12 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_13 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_14 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_15 = ");";
  protected final String TEXT_16 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_17 = ">(new ";
  protected final String TEXT_18 = "(context), config);" + NL + "\t}" + NL + "}";
  protected final String TEXT_19 = NL + NL + "public static class ";
  protected final String TEXT_20 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_21 = "> {" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public ";
  protected final String TEXT_22 = "(ContextProperties context) {" + NL + "        this.context = context;" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public ";
  protected final String TEXT_23 = " create() throws Exception {" + NL + "        com.amazonaws.auth.AWSCredentials credentials = new com.amazonaws.auth.BasicAWSCredentials(";
  protected final String TEXT_24 = NL + "                ";
  protected final String TEXT_25 = ",";
  protected final String TEXT_26 = NL + "                ";
  protected final String TEXT_27 = ");" + NL + "        com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient client" + NL + "                = new com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient(credentials);" + NL;
  protected final String TEXT_28 = NL + "            client.withEndpoint(";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + "            client.setRegion(com.amazonaws.regions.RegionUtils.getRegion(";
  protected final String TEXT_31 = "));";
  protected final String TEXT_32 = NL + NL + "        return new com.amazonaws.services.dynamodbv2.document.DynamoDB(client);" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_33 = "> wrap(";
  protected final String TEXT_34 = " connection) {" + NL + "        return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_35 = ">(connection);" + NL + "    }" + NL + "}";
  protected final String TEXT_36 = NL + "            ";
  protected final String TEXT_37 = ".set(";
  protected final String TEXT_38 = ", ";
  protected final String TEXT_39 = ");";
  protected final String TEXT_40 = NL + "                ";
  protected final String TEXT_41 = ".";
  protected final String TEXT_42 = " = !item.isPresent(\"";
  protected final String TEXT_43 = "\") || item.isNull(\"";
  protected final String TEXT_44 = "\")" + NL + "                        ? ";
  protected final String TEXT_45 = " : item.getString(\"";
  protected final String TEXT_46 = "\");";
  protected final String TEXT_47 = NL + "                {" + NL + "                    String tmp = !item.isPresent(\"";
  protected final String TEXT_48 = "\") || item.isNull(\"";
  protected final String TEXT_49 = "\")" + NL + "                        ? \"\" : item.getString(\"";
  protected final String TEXT_50 = "\");";
  protected final String TEXT_51 = NL + "                    ";
  protected final String TEXT_52 = ".";
  protected final String TEXT_53 = " = tmp.length() > 0 ? tmp.charAt(0) : ";
  protected final String TEXT_54 = ";" + NL + "                }";
  protected final String TEXT_55 = NL + "                ";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = " = !item.isPresent(\"";
  protected final String TEXT_58 = "\") || item.isNull(\"";
  protected final String TEXT_59 = "\")" + NL + "                        ? ";
  protected final String TEXT_60 = " : item.getBOOL(\"";
  protected final String TEXT_61 = "\");";
  protected final String TEXT_62 = NL + "                ";
  protected final String TEXT_63 = ".";
  protected final String TEXT_64 = " = !item.isPresent(\"";
  protected final String TEXT_65 = "\") || item.isNull(\"";
  protected final String TEXT_66 = "\")" + NL + "                        ? ";
  protected final String TEXT_67 = " : item.getLong(\"";
  protected final String TEXT_68 = "\");";
  protected final String TEXT_69 = NL + "                ";
  protected final String TEXT_70 = ".";
  protected final String TEXT_71 = " = !item.isPresent(\"";
  protected final String TEXT_72 = "\") || item.isNull(\"";
  protected final String TEXT_73 = "\")" + NL + "                        ? ";
  protected final String TEXT_74 = " : item.getInt(\"";
  protected final String TEXT_75 = "\");";
  protected final String TEXT_76 = NL + "                ";
  protected final String TEXT_77 = ".";
  protected final String TEXT_78 = " = !item.isPresent(\"";
  protected final String TEXT_79 = "\") || item.isNull(\"";
  protected final String TEXT_80 = "\")" + NL + "                        ? ";
  protected final String TEXT_81 = " : item.getShort(\"";
  protected final String TEXT_82 = "\");";
  protected final String TEXT_83 = NL + "                ";
  protected final String TEXT_84 = ".";
  protected final String TEXT_85 = " = !item.isPresent(\"";
  protected final String TEXT_86 = "\") || item.isNull(\"";
  protected final String TEXT_87 = "\")" + NL + "                        ? ";
  protected final String TEXT_88 = " : (byte) item.getShort(\"";
  protected final String TEXT_89 = "\");";
  protected final String TEXT_90 = NL + "                ";
  protected final String TEXT_91 = ".";
  protected final String TEXT_92 = " = !item.isPresent(\"";
  protected final String TEXT_93 = "\") || item.isNull(\"";
  protected final String TEXT_94 = "\")" + NL + "                        ? ";
  protected final String TEXT_95 = " : item.getDouble(\"";
  protected final String TEXT_96 = "\");";
  protected final String TEXT_97 = NL + "                ";
  protected final String TEXT_98 = ".";
  protected final String TEXT_99 = " = !item.isPresent(\"";
  protected final String TEXT_100 = "\") || item.isNull(\"";
  protected final String TEXT_101 = "\")" + NL + "                        ? ";
  protected final String TEXT_102 = " : item.getFloat(\"";
  protected final String TEXT_103 = "\");";
  protected final String TEXT_104 = NL + "                ";
  protected final String TEXT_105 = ".";
  protected final String TEXT_106 = " = !item.isPresent(\"";
  protected final String TEXT_107 = "\") || item.isNull(\"";
  protected final String TEXT_108 = "\")" + NL + "                        ? ";
  protected final String TEXT_109 = " : item.getNumber(\"";
  protected final String TEXT_110 = "\");";
  protected final String TEXT_111 = NL + "                ";
  protected final String TEXT_112 = ".";
  protected final String TEXT_113 = " = !item.isPresent(\"";
  protected final String TEXT_114 = "\") || item.isNull(\"";
  protected final String TEXT_115 = "\")" + NL + "                        ? ";
  protected final String TEXT_116 = " : item.getByteBuffer(\"";
  protected final String TEXT_117 = "\");";
  protected final String TEXT_118 = NL + "                ";
  protected final String TEXT_119 = ".";
  protected final String TEXT_120 = " = !item.isPresent(\"";
  protected final String TEXT_121 = "\") || item.isNull(\"";
  protected final String TEXT_122 = "\")" + NL + "                        ? ";
  protected final String TEXT_123 = NL + "                        : BigDataParserUtils.parseTo_Date(" + NL + "                                item.getString(\"";
  protected final String TEXT_124 = "\"),";
  protected final String TEXT_125 = NL + "                                ";
  protected final String TEXT_126 = ");";
  protected final String TEXT_127 = NL + "                ";
  protected final String TEXT_128 = ".";
  protected final String TEXT_129 = " = !item.isPresent(\"";
  protected final String TEXT_130 = "\") || item.isNull(\"";
  protected final String TEXT_131 = "\")" + NL + "                        ? ";
  protected final String TEXT_132 = " : item.get(\"";
  protected final String TEXT_133 = "\");";
  protected final String TEXT_134 = NL + "                ";
  protected final String TEXT_135 = ".";
  protected final String TEXT_136 = " = !item.isPresent(\"";
  protected final String TEXT_137 = "\") || item.isNull(\"";
  protected final String TEXT_138 = "\")" + NL + "                        ? ";
  protected final String TEXT_139 = " : item.getList(\"";
  protected final String TEXT_140 = "\");";
  protected final String TEXT_141 = NL + "                throw new JobConfigurationError(\"Type not supported: ";
  protected final String TEXT_142 = "\");";
  protected final String TEXT_143 = NL + NL + "public static class ";
  protected final String TEXT_144 = "_FlatMapper extends org.talend.bigdata.dataflow.hmap.HMapContextConfigurableBase implements org.talend.bigdata.dataflow.functions.FlatMapper<org.apache.avro.generic.IndexedRecord, ";
  protected final String TEXT_145 = "> {" + NL + "" + NL + "    private transient ";
  protected final String TEXT_146 = " session;" + NL + "" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public ";
  protected final String TEXT_147 = "_FlatMapper(JobConf job) {" + NL + "        this.context = new ContextProperties(job);" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void prepare() throws Exception {" + NL + "        this.session = ";
  protected final String TEXT_148 = ".get(context).borrowObject();" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public Iterable<";
  protected final String TEXT_149 = "> flatMap(org.apache.avro.generic.IndexedRecord joinKeys) throws Exception {" + NL + "        List<";
  protected final String TEXT_150 = "> result = new ArrayList<>();";
  protected final String TEXT_151 = NL + "        ";
  protected final String TEXT_152 = " ";
  protected final String TEXT_153 = " = getHMapContext().getInputRecord(\"";
  protected final String TEXT_154 = "\");" + NL + "" + NL + "        try {" + NL + "            // Construct and send the query." + NL + "            com.amazonaws.services.dynamodbv2.document.spec.QuerySpec querySpec" + NL + "                    = new com.amazonaws.services.dynamodbv2.document.spec.QuerySpec()" + NL + "                            .withKeyConditionExpression(";
  protected final String TEXT_155 = ");" + NL;
  protected final String TEXT_156 = NL + "                java.util.HashMap<String, Object> values_";
  protected final String TEXT_157 = " = new java.util.HashMap<String, Object>();";
  protected final String TEXT_158 = NL + "                    values_";
  protected final String TEXT_159 = ".put(";
  protected final String TEXT_160 = ",";
  protected final String TEXT_161 = ");";
  protected final String TEXT_162 = NL + "                querySpec = querySpec.withValueMap(values_";
  protected final String TEXT_163 = ");";
  protected final String TEXT_164 = NL + NL + "            session.getTable(";
  protected final String TEXT_165 = ");" + NL + "            com.amazonaws.services.dynamodbv2.document.ItemCollection<com.amazonaws.services.dynamodbv2.document.QueryOutcome> items" + NL + "                    = session.getTable(";
  protected final String TEXT_166 = ").query(querySpec);" + NL + "" + NL + "            for (com.amazonaws.services.dynamodbv2.document.Item item : items) {";
  protected final String TEXT_167 = NL + "                ";
  protected final String TEXT_168 = " ";
  protected final String TEXT_169 = " = new ";
  protected final String TEXT_170 = "();";
  protected final String TEXT_171 = NL + "                result.add(";
  protected final String TEXT_172 = ");" + NL + "            }" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(" + NL + "                    \"Lookup from query \"" + NL + "                    + ";
  protected final String TEXT_173 = NL + "                    + \" has failed : \" + e.getMessage(), e);" + NL + "        }" + NL + "        return result;" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void cleanup() throws Exception {" + NL + "        if (session != null) {";
  protected final String TEXT_174 = NL + "            ";
  protected final String TEXT_175 = ".get(context).returnObject(session);" + NL + "        }" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final DynamoDBSparkStorage storage = new DynamoDBSparkStorage(node);

    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_1);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_11);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_15);
    
		}

    stringBuffer.append(TEXT_16);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_18);
    
// Pooled object factory code. This factory creates reusable DynamoDB connections.

// If this file is included, then spark_pool.javajet must be included as well.

    stringBuffer.append(TEXT_19);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(storage.getAwsAccessKey());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(storage.getAwsSecretKey());
    stringBuffer.append(TEXT_27);
     if (storage.getAwsEndpoint() != null) { 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(storage.getAwsEndpoint());
    stringBuffer.append(TEXT_29);
     } else if (storage.getAwsRegion() != null) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(storage.getAwsRegion());
    stringBuffer.append(TEXT_31);
     } 
    stringBuffer.append(TEXT_32);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_35);
    
/**
 * Helper class for adapting information for DynamicDB input to spark code and
 * config structures.  This provides code generation utility methods that are
 * completely spark-specific.
 */
class TDynamoDBInputUtil extends org.talend.designer.common.TransformerBase {

    /** There are two reject fields at the end of the reject schema. */
    private final static int REJECT_FIELDS_SIZE = 2;

    // All of the configuration is stored in this instance.
    protected final DynamoDBSparkStorage storage;

    public final Iterable<IMetadataColumn> mainColumnsCopy;

    TDynamoDBInputUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        super(node, argument, rowTransformUtil, "FLOW", "REJECT");
        storage = new DynamoDBSparkStorage(node);

        // All of the configurations are set in the hadoop properties.
        storage.addProperty(false,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.INPUT_TABLE_NAME",
                ElementParameterParser.getValue(node, "__TABLE__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.THROUGHPUT_READ_PERCENT",
                ElementParameterParser.getValue(node, "__THROUGHPUT_READ_PERCENT__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.SCAN_SEGMENTS",
                ElementParameterParser.getValue(node, "__SCAN_SEGMENTS__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.MAX_MAP_TASKS",
                ElementParameterParser.getValue(node, "__MAX_MAP_TASKS__"));

        // Add the advanced properties last to override any of the values.
        storage.addAdvancedProperties("__DYNAMODB_ADVANCED_PROPERTIES__");

        if (getOutColumnsMain() == null) {
            List<IMetadataColumn> columns = getOutConnReject().getMetadataTable().getListColumns();
            columns = columns.subList(0, columns.size() - REJECT_FIELDS_SIZE);
            mainColumnsCopy = columns;
        } else {
            mainColumnsCopy = getOutColumnsMain();
        }
    }

    /**
     * Generate the standard hadoop job configuration for the DynamoDB formats.
     */
    public void generateHadoopJobConfiguration(String codeVarJobConf) {
        for (java.util.Map.Entry<String, String> e : storage.getProperties().entrySet()) {
            
    stringBuffer.append(TEXT_36);
    stringBuffer.append(codeVarJobConf);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(e.getKey());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(e.getValue());
    stringBuffer.append(TEXT_39);
    
        }
    }

    /** Generates the main transformation from a DynamoDBItemWritable to the
     *  main rowStruct. */
    public void generateTransform(String codeVarRow) {
        for (IMetadataColumn column : mainColumnsCopy) {
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
            String javaFieldType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
            String defaultValue = JavaTypesManager.getDefaultValueFromJavaIdType(
                    column.getTalendType(), column.isNullable(), column.getDefault());

            // TODO transform the label with a map in Advanced?
            String attr = column.getLabel();
            if (javaType == JavaTypesManager.STRING) {
                
    stringBuffer.append(TEXT_40);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_46);
    
            } else if (javaType == JavaTypesManager.CHARACTER) {
                
    stringBuffer.append(TEXT_47);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_54);
    
            } else if (javaType == JavaTypesManager.BOOLEAN) {
                
    stringBuffer.append(TEXT_55);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_61);
    
            } else if (javaType == JavaTypesManager.LONG) {
                
    stringBuffer.append(TEXT_62);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_68);
    
            } else if (javaType == JavaTypesManager.INTEGER) {
                
    stringBuffer.append(TEXT_69);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_75);
    
            } else if (javaType == JavaTypesManager.SHORT) {
                
    stringBuffer.append(TEXT_76);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_82);
    
            } else if (javaType == JavaTypesManager.BYTE) {
                
    stringBuffer.append(TEXT_83);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_89);
    
            } else if (javaType == JavaTypesManager.DOUBLE) {
                
    stringBuffer.append(TEXT_90);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_96);
    
            } else if (javaType == JavaTypesManager.FLOAT) {
                
    stringBuffer.append(TEXT_97);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_103);
    
            } else if (javaType == JavaTypesManager.BIGDECIMAL) {
                
    stringBuffer.append(TEXT_104);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_110);
    
            } else if (javaType == JavaTypesManager.BYTE_ARRAY) {
                
    stringBuffer.append(TEXT_111);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_117);
    
            } else if (javaType == JavaTypesManager.DATE) {
                
    stringBuffer.append(TEXT_118);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_126);
    
            } else if (javaType == JavaTypesManager.OBJECT) {
                
    stringBuffer.append(TEXT_127);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_133);
    
            } else if (javaType == JavaTypesManager.LIST) {
                
    stringBuffer.append(TEXT_134);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_140);
    
            } else {
                
    stringBuffer.append(TEXT_141);
    stringBuffer.append(javaType);
    stringBuffer.append(TEXT_142);
    
            }
        }
    }
}

    

final class TDynamoDBLookupInputUtil extends TDynamoDBInputUtil {

    protected final INode node;

    public TDynamoDBLookupInputUtil(INode node) {
        super(node, codeGenArgument, null);
        this.node = node;
    }

    public String getTable() {
       return ElementParameterParser.getValue(node, "__TABLE__");
    }

    public String getKeyConditionExpression() {
        return ElementParameterParser.getValue(node, "__ADVANCED_KEY_CONDITION_EXPRESSION__").replaceAll("[\r\n]", " ");
    }

    public Map<String, String> getValuesMap() {
        java.util.HashMap<String, String> keyValue = new java.util.HashMap<String, String>();
        java.util.List<java.util.Map<String, String>> valueTable = ElementParameterParser.getTableValue(node, "__VALUE_MAPPING__");
        for (java.util.Map<String, String> valueTableRow : valueTable)
            keyValue.put(valueTableRow.get("PLACEHOLDER"), valueTableRow.get("VALUE"));
        return keyValue;
    }
}

final TDynamoDBLookupInputUtil util = new TDynamoDBLookupInputUtil(node);

String validateError = org.talend.designer.common.tmap.AbstractTMapAdapter.validatePullLookupConfig(node);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "{throw new JobConfigurationError(\"" + validateError +"\");}";
}

String outputName = util.getOutConnMain().getName();
String structName = codeGenArgument.getRecordStructName(util.getOutConnMain());

TMapAdapter tMapAdapter = org.talend.designer.common.tmap.AbstractTMapAdapter.createForTMap(
        util.getOutConnMain().getTarget());
String inputMainName = tMapAdapter.getInputMainName();
String structMainName = codeGenArgument.getRecordStructName(inputMainName);

    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(util.getKeyConditionExpression());
    stringBuffer.append(TEXT_155);
    
            java.util.Map<String, String> values = util.getValuesMap();
            if (values.size() > 0) {
                
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    
                for (java.util.Map.Entry<String, String> e : values.entrySet()) {
                    
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(e.getKey());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(e.getValue());
    stringBuffer.append(TEXT_161);
    
                }
                
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    
            }
            
    stringBuffer.append(TEXT_164);
    stringBuffer.append(util.getTable());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(util.getTable());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_170);
    
                util.generateTransform(outputName);
                
    stringBuffer.append(TEXT_171);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(util.getKeyConditionExpression());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_175);
    return stringBuffer.toString();
  }
}
