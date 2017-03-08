package org.talend.designer.codegen.translators.file.output;

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
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TFileOutputParquetSparkconfigJava
{
  protected static String nl;
  public static synchronized TFileOutputParquetSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileOutputParquetSparkconfigJava result = new TFileOutputParquetSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        String outputPath_";
  protected final String TEXT_2 = " = ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL + "            java.net.URI currentURI_";
  protected final String TEXT_5 = "_config = FileSystem.getDefaultUri(ctx.hadoopConfiguration());" + NL + "            FileSystem.setDefaultUri(ctx.hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_6 = "));" + NL + "            fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_7 = NL + "                Path pathToDelete_";
  protected final String TEXT_8 = " = new Path(";
  protected final String TEXT_9 = ");" + NL + "                if (fs.exists(pathToDelete_";
  protected final String TEXT_10 = ")) {" + NL + "                    fs.delete(pathToDelete_";
  protected final String TEXT_11 = ", true);" + NL + "                }";
  protected final String TEXT_12 = NL + "                job.setWorkingDirectory(fs.getWorkingDirectory());" + NL + "                job.set(parquet.hadoop.ParquetOutputFormat.WRITE_SUPPORT_CLASS, parquet.hadoop.example.GroupWriteSupport.class.getCanonicalName());" + NL + "                java.util.List<parquet.schema.Type> types_";
  protected final String TEXT_13 = " = new java.util.ArrayList<parquet.schema.Type>();";
  protected final String TEXT_14 = NL + "                        types_";
  protected final String TEXT_15 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT32, \"";
  protected final String TEXT_16 = "\"));";
  protected final String TEXT_17 = NL + "                        types_";
  protected final String TEXT_18 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT64, \"";
  protected final String TEXT_19 = "\"));";
  protected final String TEXT_20 = NL + "                        types_";
  protected final String TEXT_21 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.DOUBLE, \"";
  protected final String TEXT_22 = "\"));";
  protected final String TEXT_23 = NL + "                        types_";
  protected final String TEXT_24 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.FLOAT, \"";
  protected final String TEXT_25 = "\"));";
  protected final String TEXT_26 = NL + "                        types_";
  protected final String TEXT_27 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BOOLEAN, \"";
  protected final String TEXT_28 = "\"));";
  protected final String TEXT_29 = NL + "                        types_";
  protected final String TEXT_30 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BINARY, \"";
  protected final String TEXT_31 = "\"));";
  protected final String TEXT_32 = NL + "                parquet.schema.MessageType schema_";
  protected final String TEXT_33 = " = new parquet.schema.MessageType(\"";
  protected final String TEXT_34 = "\", types_";
  protected final String TEXT_35 = ");" + NL + "                parquet.hadoop.example.GroupWriteSupport.setSchema(schema_";
  protected final String TEXT_36 = ", job);" + NL + "" + NL + "                TalendParquetOutputFormat_";
  protected final String TEXT_37 = ".setWorkOutputPath(job, new Path(outputPath_";
  protected final String TEXT_38 = " + \"/workoutput\"));" + NL + "                TalendParquetOutputFormat_";
  protected final String TEXT_39 = ".setOutputPath(job, new Path(outputPath_";
  protected final String TEXT_40 = "));" + NL + "                TalendParquetOutputFormat_";
  protected final String TEXT_41 = ".setCompression(job, parquet.hadoop.metadata.CompressionCodecName.";
  protected final String TEXT_42 = ");" + NL + "" + NL + "                rdd_";
  protected final String TEXT_43 = ".mapToPair(new toVoid_";
  protected final String TEXT_44 = "()).saveAsHadoopFile(";
  protected final String TEXT_45 = ", NullWritable.class, Object.class, TalendParquetOutputFormat_";
  protected final String TEXT_46 = ".class, job);";
  protected final String TEXT_47 = NL + "                    org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_48 = " = new org.apache.spark.sql.SQLContext(ctx.sc());" + NL + "                    sqlContext_";
  protected final String TEXT_49 = ".setConf(\"spark.sql.parquet.compression.codec\", \"";
  protected final String TEXT_50 = "\");";
  protected final String TEXT_51 = NL + "                            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_52 = "> ";
  protected final String TEXT_53 = " = ";
  protected final String TEXT_54 = ".values().map(new ";
  protected final String TEXT_55 = "_From";
  protected final String TEXT_56 = "To";
  protected final String TEXT_57 = "());";
  protected final String TEXT_58 = NL + "                            org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_59 = "> ";
  protected final String TEXT_60 = " = ";
  protected final String TEXT_61 = ".values();";
  protected final String TEXT_62 = NL + "                        ";
  protected final String TEXT_63 = " df_";
  protected final String TEXT_64 = "_";
  protected final String TEXT_65 = " = sqlContext_";
  protected final String TEXT_66 = ".createDataFrame(";
  protected final String TEXT_67 = ", ";
  protected final String TEXT_68 = ".class);" + NL + "                        df_";
  protected final String TEXT_69 = "_";
  protected final String TEXT_70 = ".write()";
  protected final String TEXT_71 = ".parquet(outputPath_";
  protected final String TEXT_72 = ");";
  protected final String TEXT_73 = NL + "                    if(true) {" + NL + "                        throw new java.lang.RuntimeException(\"";
  protected final String TEXT_74 = "\");" + NL + "                    }";
  protected final String TEXT_75 = NL + "            FileSystem.setDefaultUri(ctx.hadoopConfiguration(), currentURI_";
  protected final String TEXT_76 = "_config);" + NL + "            fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_77 = NL + "            final String clouderaManagerPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_78 = ");";
  protected final String TEXT_79 = NL + "            final String clouderaManagerPassword = ";
  protected final String TEXT_80 = ";";
  protected final String TEXT_81 = NL + "        this.lineageCreator = new org.talend.lineage.cloudera.LineageCreator(";
  protected final String TEXT_82 = NL + "                ";
  protected final String TEXT_83 = ",";
  protected final String TEXT_84 = NL + "                ";
  protected final String TEXT_85 = ",";
  protected final String TEXT_86 = NL + "                ";
  protected final String TEXT_87 = ",";
  protected final String TEXT_88 = NL + "                ";
  protected final String TEXT_89 = "," + NL + "                clouderaManagerPassword," + NL + "                jobName + \"_\" + jobVersion.replace(\".\", \"_\")," + NL + "                projectName,";
  protected final String TEXT_90 = NL + "                ";
  protected final String TEXT_91 = ",";
  protected final String TEXT_92 = NL + "                ";
  protected final String TEXT_93 = ",";
  protected final String TEXT_94 = NL + "                ";
  protected final String TEXT_95 = ");";
  protected final String TEXT_96 = NL + "        this.lineageCreator = new org.talend.lineage.atlas.AtlasLineageCreator(";
  protected final String TEXT_97 = ");" + NL + "" + NL + "        java.util.Map<String, Object> lineageCreatorJobMetadata = new java.util.HashMap<String, Object>();" + NL + "        lineageCreatorJobMetadata.put(\"name\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"description\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"purpose\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"author\", System.getProperty(\"user.name\"));" + NL + "        lineageCreatorJobMetadata.put(\"version\", jobVersion);" + NL + "        lineageCreatorJobMetadata.put(\"jobType\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"framework\", \"Talend BigData\");" + NL + "        lineageCreatorJobMetadata.put(\"status\", \"FINISHED\");" + NL + "        lineageCreatorJobMetadata.put(\"creationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"lastModificationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"startTime\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"endTime\", System.currentTimeMillis());" + NL + "" + NL + "        this.lineageCreator.addJobInfo(lineageCreatorJobMetadata);";
  protected final String TEXT_98 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_99 = " = new java.util.HashMap<>();";
  protected final String TEXT_100 = NL + "            columns_";
  protected final String TEXT_101 = ".put(\"";
  protected final String TEXT_102 = "\", \"";
  protected final String TEXT_103 = "\");";
  protected final String TEXT_104 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_105 = " = new java.util.HashMap<>();";
  protected final String TEXT_106 = NL + "                        columns_";
  protected final String TEXT_107 = ".put(\"";
  protected final String TEXT_108 = "\", \"";
  protected final String TEXT_109 = "\");";
  protected final String TEXT_110 = NL + "                    columns_";
  protected final String TEXT_111 = ".put(\"";
  protected final String TEXT_112 = "\", \"";
  protected final String TEXT_113 = "\");";
  protected final String TEXT_114 = NL + "        lineageCreator.addDataset(columns_";
  protected final String TEXT_115 = ", \"";
  protected final String TEXT_116 = "\", ";
  protected final String TEXT_117 = ", \"";
  protected final String TEXT_118 = "\");";
  protected final String TEXT_119 = NL + "        java.util.List<String> inputNodes_";
  protected final String TEXT_120 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_121 = NL + "                inputNodes_";
  protected final String TEXT_122 = ".add(\"";
  protected final String TEXT_123 = "\");";
  protected final String TEXT_124 = NL + "        java.util.List<String> outputNodes_";
  protected final String TEXT_125 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_126 = NL + "                outputNodes_";
  protected final String TEXT_127 = ".add(\"";
  protected final String TEXT_128 = "\");";
  protected final String TEXT_129 = NL + "        this.lineageCreator.addNodeToLineage(\"";
  protected final String TEXT_130 = "\", columns_";
  protected final String TEXT_131 = ", inputNodes_";
  protected final String TEXT_132 = ", outputNodes_";
  protected final String TEXT_133 = ", new java.util.HashMap<String, Object>());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
List<IMetadataTable> metadatas = node.getMetadataList();

// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with OutputFormat or native Dataframe API.
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

if(metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        String rowSeparator = ElementParameterParser.getValue(node,"__ROWSEPARATOR__");
        String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");
        String folder = ElementParameterParser.getValue(node, "__FILENAME__");
        String fileAction = ElementParameterParser.getValue(node, "__FILE_ACTION__");

        String uriPrefix = "\"\"";
        // Used for Spark only for now.
        boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
        if(useConfigurationComponent) {
            uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
            folder = uriPrefix + " + " + folder;
        }

        String inConnName = "";
        IConnection connection = null;
        String connTypeName = "";
        if ((node.getIncomingConnections() != null) && (node.getIncomingConnections().size() > 0)) {
            connection = node.getIncomingConnections().get(0);
            inConnName = connection.getName();
            connTypeName = codeGenArgument.getRecordStructName(connection);
        }
        
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_3);
    

        if(!"\"\"".equals(uriPrefix)) {
            
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_6);
    
        }

        if(connection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
            if("OVERWRITE".equals(fileAction)){
                
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
            }

            // If the current Spark version is 1.3, 1.4 or 1.5, we use the RDD OutputFormat API.
            if(currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_4 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_5) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
                List<IMetadataColumn> columns = metadata.getListColumns();
                int nbColumns = columns.size();
                for(int i=0; i<nbColumns; i++) {
                    IMetadataColumn column = columns.get(i);
                    String columnName = column.getLabel();
                    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

                    if(javaType == JavaTypesManager.INTEGER || javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.BYTE) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_16);
    
                    } else if(javaType == JavaTypesManager.LONG) {

    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_19);
    
                    } else if(javaType == JavaTypesManager.DOUBLE) {

    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_22);
    
                    } else if(javaType == JavaTypesManager.FLOAT) {

    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    
                    } else if(javaType == JavaTypesManager.BOOLEAN) {

    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    
                    } else {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(columnName);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(compression);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    
            } else {
                // If the current Spark version greater than or equals to 1.6, we use the native dataframe API.
                try {
                    TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
                    String validateError = tSqlRowUtil.validate(true, false);
                    if (validateError != null) {
                        // Cause the job compilation to explicitly fail if there is a problem.
                        return "throw new JobConfigurationError(\"" + validateError +"\");";
                    }


    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(compression.toLowerCase());
    stringBuffer.append(TEXT_50);
    
                    for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
                        String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
                        String inRddName = "rdd_"+incomingConnection.getName();

                        String rddName, structName;
                        String newRddName = "tmp_rdd_"+incomingConnection.getName();
                        if(tSqlRowUtil.needsRecordStructChange(incomingConnection)) {
                            // Additional map to convert from java.util.Date to java.sql.Date
                            String newStructName = "DF_"+inStructName+"AvroRecord";
                            // As the tFileOutputParquet requires a KEYVALUE in input, we know that the incoming RDD is a JavaPairRDD. We first must transform it as a JavaRDD using the values() method.

    stringBuffer.append(TEXT_51);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_57);
    
                            rddName = newRddName;
                            structName = newStructName;
                        } else {

    stringBuffer.append(TEXT_58);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_61);
    
                            rddName = newRddName;
                            structName = inStructName;
                        }

                        boolean definePartitions = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_PARTITIONS__"));
                        final List<Map<String, String>> partitionBy = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__PARTITION_BY__");
                        java.lang.StringBuilder columnsListBuilder = new java.lang.StringBuilder();
                        if(definePartitions) {
                            for (int i = 0 ; i < partitionBy.size() ; i++) {
                                if(i!=0) columnsListBuilder.append(",");
                                Map<String, String> line = partitionBy.get(i);
                                columnsListBuilder.append("\"" + line.get("COLUMN") + "\"");
                            }
                        }

    stringBuffer.append(TEXT_62);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(incomingConnection.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(definePartitions?".partitionBy("+ columnsListBuilder.toString() +")":"");
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    
                    }
                } catch(java.lang.Exception e) {

    stringBuffer.append(TEXT_73);
    stringBuffer.append(e.getMessage());
    stringBuffer.append(TEXT_74);
    
                }
            }
        }

        if(!"\"\"".equals(uriPrefix)) {
        
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    
        }
        
    
class TalendLineageAPI{

    /**
    * Find the configuration node given the current node
    * 
    * @param node
    **/
    public INode findConfigurationNode(INode node){
        INode configurationNode = null;
        for (INode pNode : node.getProcess().getNodesOfType("tMRConfiguration")) {
            configurationNode = pNode;
            break;
        }
        for (INode pNode : node.getProcess().getNodesOfType("tSparkConfiguration")) {
            // spark compatibility, will not be run on Map Reduce
            configurationNode = pNode;
            break;
        }
        return configurationNode;
    }

    /**
     * Does the job require lineage generation
     */
    public boolean doRequireLineageSupport(INode node){
        INode configurationNode = findConfigurationNode(node);
        if (configurationNode != null) {
            Boolean useClouderaNavigator = ElementParameterParser.getBooleanValue(configurationNode,"__USE_CLOUDERA_NAVIGATOR__");
            Boolean useAtlas = ElementParameterParser.getBooleanValue(configurationNode,"__USE_ATLAS__");
            return (useClouderaNavigator && doRequireClouderaNavigatorSupport(configurationNode)) || (useAtlas && doRequireAtlasSupport(configurationNode));
        }
        return false;
    }

    /**
     * Does the job require Cloudera Navigator lineage generation
     */
    public boolean doRequireClouderaNavigatorSupport(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        Boolean useClouderaNavigator = ElementParameterParser.getBooleanValue(configurationNode,"__USE_CLOUDERA_NAVIGATOR__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
            version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return useClouderaNavigator && currentDistribution.doSupportClouderaNavigator();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the job require Atlas lineage generation
     */
    public boolean doRequireAtlasSupport(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        Boolean useAtlas = ElementParameterParser.getBooleanValue(configurationNode,"__USE_ATLAS__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
           version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return useAtlas && currentDistribution.doSupportAtlas();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Cloudera Navigator API version
     */
    public int getClouderaNavigatorAPIVersion(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
           version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return currentDistribution.getClouderaNavigatorAPIVersion();
        } catch (Exception e) {
            return 8;
        }
    }

    /**
    *
    * generates a Cloudera Navigator lineage creator
    *
    */
    public void generateClouderaNavigatorLinageCreator(INode configurationNode) {
        String usernameCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_USERNAME__");
        String urlCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_URL__");
        String urlMetadataCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_METADATA_URL__");
        String clientUrlCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_CLIENT_URL__");
        Boolean useAutocommit = ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_AUTOCOMMIT__");
        Boolean disableSslValidation = ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_DISABLE_SSL_VALIDATION__");
        int apiVersion = getClouderaNavigatorAPIVersion(configurationNode);

        if (ElementParameterParser.canEncrypt(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__")) {
            
    stringBuffer.append(TEXT_77);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_78);
    
        } else {
            
    stringBuffer.append(TEXT_79);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_80);
    
        }
        
    stringBuffer.append(TEXT_81);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(clientUrlCN);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(urlCN);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(urlMetadataCN);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(usernameCN);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(useAutocommit);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(disableSslValidation);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(apiVersion);
    stringBuffer.append(TEXT_95);
    
    }

    /**
    *
    * generates an Atlas lineage creator
    *
    */
    public void generateAtlasLinageCreator(INode configurationNode) {
        String atlasURL = ElementParameterParser.getValue(configurationNode,"__ATLAS_URL__");
        
    stringBuffer.append(TEXT_96);
    stringBuffer.append(atlasURL);
    stringBuffer.append(TEXT_97);
    
    }

    /**
    * returns the dieOnError value
    */
    public Boolean getDieOnError(INode configurationNode){
        if(doRequireClouderaNavigatorSupport(configurationNode)){
            return ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_DIE_ON_ERROR__");
        } else if (doRequireAtlasSupport(configurationNode)){
            return ElementParameterParser.getBooleanValue(configurationNode,"__ATLAS_DIE_ON_ERROR__");
        }
        return false;
    }

    /**
     * Generate a Map containing the columns information.
     * The map contain the column name as the key, and the column type as the value.
     */
    public void generateColumnList(IMetadataTable metadataTable, String cid) {
        
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    
        for (IMetadataColumn column: metadataTable.getListColumns()) {
            
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_103);
    
        }
    }

    /**
     * Generate a Map containing the columns information.
     * The map contain the column name as the key, and the column type as the value.
     * This function will generate the full output mapping of a component, in order to display any output field.
     * 
     * If the component contain output link, the map will be  generate from these links,
     * otherwise the metadata of the component will be used.
     */
    public void generateColumnListFromOutputLink(INode node, String cid) {
        
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    
        if ((node.getOutgoingConnections() != null)
            && (node.getOutgoingConnections().size() > 0)) {
            for (IConnection connection: node.getOutgoingConnections()) {
                if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                    for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
                        
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_109);
    
                    }
                }
            }
        } else  {
            for (IMetadataTable metadataTable: node.getMetadataList()) {
                for (IMetadataColumn column: metadataTable.getListColumns()) {
                    
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_113);
    
                }
            }
        }
    }

    /**
     * Generate the code to call the method addDataset of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     * @param componentName name of the graphical component
     * @param folderPath The path to the folder containing data into HDFS
     * @param folderType The type of the folder, must be defined into com.cloudera.nav.sdk.model.entities.FileFormat
     */
    public void addDataset(String cid, String componentName, String folderPath, String folderType) {
        
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(componentName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(folderPath);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(folderType);
    stringBuffer.append(TEXT_118);
    
    }

    /**
     * Generate list of input nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateInputNodeList(INode node) {
        
    stringBuffer.append(TEXT_119);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_120);
    
        java.util.List<IConnection> inputs = (java.util.List<IConnection>)node.getIncomingConnections();
        for (IConnection connection: inputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_121);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(connection.getSource().getUniqueName());
    stringBuffer.append(TEXT_123);
    
            }
        }
    }

    /**
     * Generate list of output nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateOutputNodeList(INode node) {
        
    stringBuffer.append(TEXT_124);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_125);
    
        java.util.List<IConnection> outputs = (java.util.List<IConnection>)node.getOutgoingConnections();
        for (IConnection connection: outputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_126);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(connection.getTarget().getUniqueName());
    stringBuffer.append(TEXT_128);
    
            }
        }
    }

    /**
     * Generate the code to call the method addNodeToLineage of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     */
    public void addNodeToLineage(String cid) {
        
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    
    }

}

    
        TalendLineageAPI talendLineageAPI = new TalendLineageAPI();
        if (talendLineageAPI.doRequireLineageSupport(node)) {
            if (node.getIncomingConnections() != null
                    && node.getIncomingConnections().size() > 0) {
                IConnection inputConnection = node.getIncomingConnections().get(0);
                String structureName = inputConnection.getName() + "Struct";
                String originalName = org.talend.core.model.utils.NodeUtil.getVirtualNode(node).getUniqueName();

                talendLineageAPI.generateColumnList(inputConnection.getMetadataTable(), cid);
                talendLineageAPI.addDataset(cid, originalName, folder, "PARQUET");
            }
        }
    }
}

    return stringBuffer.toString();
  }
}
