package org.talend.designer.codegen.translators.file.output;

import java.util.List;
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

public class TFileOutputParquetSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TFileOutputParquetSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileOutputParquetSparkstreamingconfigJava result = new TFileOutputParquetSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        String outputPath_";
  protected final String TEXT_2 = " = ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL + "            java.net.URI currentURI_";
  protected final String TEXT_5 = "_config = FileSystem.getDefaultUri(ctx.sparkContext().hadoopConfiguration());" + NL + "            FileSystem.setDefaultUri(ctx.sparkContext().hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_6 = "));" + NL + "            fs = FileSystem.get(ctx.sparkContext().hadoopConfiguration());";
  protected final String TEXT_7 = NL + "                Path pathToDelete_";
  protected final String TEXT_8 = " = new Path(";
  protected final String TEXT_9 = ");" + NL + "                if (fs.exists(pathToDelete_";
  protected final String TEXT_10 = ")) {" + NL + "                    fs.delete(pathToDelete_";
  protected final String TEXT_11 = ", true);" + NL + "                }";
  protected final String TEXT_12 = NL + NL + "                job.setWorkingDirectory(fs.getWorkingDirectory());" + NL + "                job.set(parquet.hadoop.ParquetOutputFormat.WRITE_SUPPORT_CLASS, parquet.hadoop.example.GroupWriteSupport.class.getCanonicalName());" + NL + "                java.util.List<parquet.schema.Type> types_";
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
  protected final String TEXT_42 = ");" + NL;
  protected final String TEXT_43 = NL + "                    rdd_";
  protected final String TEXT_44 = ".mapToPair(new toVoid_";
  protected final String TEXT_45 = "()).saveAsHadoopFiles(";
  protected final String TEXT_46 = NL + "                        ";
  protected final String TEXT_47 = ", \"\", NullWritable.class, Object.class," + NL + "                        TalendParquetOutputFormat_";
  protected final String TEXT_48 = ".class, job);";
  protected final String TEXT_49 = NL + "                    rdd_";
  protected final String TEXT_50 = ".mapToPair(new toVoid_";
  protected final String TEXT_51 = "()).foreachRDD(" + NL + "                        new WriteNonEmpty_";
  protected final String TEXT_52 = "_ForeachRDDOutput<Void, Object>(";
  protected final String TEXT_53 = NL + "                                ";
  protected final String TEXT_54 = ", \"\", Void.class," + NL + "                                Object.class, TalendParquetOutputFormat_";
  protected final String TEXT_55 = ".class, job));";
  protected final String TEXT_56 = NL + "                            org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_57 = "> ";
  protected final String TEXT_58 = " = ";
  protected final String TEXT_59 = ".transform(new ";
  protected final String TEXT_60 = "_TransformToValue_";
  protected final String TEXT_61 = "()).map(new ";
  protected final String TEXT_62 = "_From";
  protected final String TEXT_63 = "To";
  protected final String TEXT_64 = "());";
  protected final String TEXT_65 = NL + "                            org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_66 = "> ";
  protected final String TEXT_67 = " = ";
  protected final String TEXT_68 = ".transform(new ";
  protected final String TEXT_69 = "_TransformToValue_";
  protected final String TEXT_70 = "());";
  protected final String TEXT_71 = NL + "                        ";
  protected final String TEXT_72 = ".foreachRDD(new ";
  protected final String TEXT_73 = "_ForeachRDD(job));";
  protected final String TEXT_74 = NL + "                    if(true) {" + NL + "                        throw new java.lang.RuntimeException(\"";
  protected final String TEXT_75 = "\");" + NL + "                    }";
  protected final String TEXT_76 = NL + "            FileSystem.setDefaultUri(ctx.sparkContext().hadoopConfiguration(), currentURI_";
  protected final String TEXT_77 = "_config);" + NL + "            fs = FileSystem.get(ctx.sparkContext().hadoopConfiguration());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

List<IMetadataTable> metadatas = node.getMetadataList();

// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with OutputFormat or native Dataframe API.
final List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
INode sparkConfig = null;
if(sparkConfigs != null && sparkConfigs.size() > 0) {
    sparkConfig = sparkConfigs.get(0);
}

org.talend.hadoop.distribution.ESparkVersion sparkVersion
        = org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(sparkConfig);

if(metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        String rowSeparator = ElementParameterParser.getValue(node,"__ROWSEPARATOR__");
        String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");
        String folder = ElementParameterParser.getValue(node, "__FILENAME__");
        String fileAction = ElementParameterParser.getValue(node, "__FILE_ACTION__");
        boolean writeEmptyBatches = "true".equals(ElementParameterParser.getValue(node, "__WRITE_EMPTY_BATCHES__"));

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
            if (sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3
                    || sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_4
                    || sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_5) {
            
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
    
                if (writeEmptyBatches) {
                
    stringBuffer.append(TEXT_43);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
                } else {
                
    stringBuffer.append(TEXT_49);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    
                }
            } else {
                // If the current Spark version greater than or equals to 1.6, we use the native dataframe API.
                try {
                    TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
                    String validateError = tSqlRowUtil.validate(true, false);
                    if (validateError != null) {
                        // Cause the job compilation to explicitly fail if there is a problem.
                        return "throw new JobConfigurationError(\"" + validateError +"\");";
                    }

                    for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
                        String inStructName = codeGenArgument.getRecordStructName(incomingConnection);
                        String inRddName = "rdd_"+incomingConnection.getName();

                        String rddName, structName;
                        String newRddName = "tmp_rdd_"+incomingConnection.getName();
                        if(tSqlRowUtil.needsRecordStructChange(incomingConnection)) {
                            // Additional map to convert from java.util.Date to java.sql.Date
                            String newStructName = "DF_"+inStructName+"AvroRecord";
                            // As the tFileOutputParquet requires a KEYVALUE in input, we know that the incoming dstream is a JavaPairDStream. We first must transform it as a JavaDStream using the toJavaDStream() method.

    stringBuffer.append(TEXT_56);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(newStructName);
    stringBuffer.append(TEXT_64);
    
                            rddName = newRddName;
                            structName = newStructName;
                        } else {

    stringBuffer.append(TEXT_65);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(newRddName);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(inRddName);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_70);
    
                            rddName = newRddName;
                            structName = inStructName;
                        }

    stringBuffer.append(TEXT_71);
    stringBuffer.append(rddName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    
                    }
                } catch(java.lang.Exception e) {

    stringBuffer.append(TEXT_74);
    stringBuffer.append(e.getMessage());
    stringBuffer.append(TEXT_75);
    
                }
            }
        }

        if(!"\"\"".equals(uriPrefix)) {
        
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    
        }
    }
}

    return stringBuffer.toString();
  }
}
