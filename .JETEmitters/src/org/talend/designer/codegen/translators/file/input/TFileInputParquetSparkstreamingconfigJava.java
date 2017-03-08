package org.talend.designer.codegen.translators.file.input;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TFileInputParquetSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TFileInputParquetSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileInputParquetSparkstreamingconfigJava result = new TFileInputParquetSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "                TalendParquetInputFormat_";
  protected final String TEXT_2 = ".setReadSupportClass(job, parquet.hadoop.example.GroupReadSupport.class);" + NL + "                org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_3 = "> rdd_";
  protected final String TEXT_4 = " =" + NL + "                        ctx.sparkContext().newAPIHadoopFile(";
  protected final String TEXT_5 = ", TalendParquetInputFormat_";
  protected final String TEXT_6 = ".class," + NL + "                                Void.class, Object.class, job)" + NL + "                            .mapToPair(new toNullWritable_";
  protected final String TEXT_7 = "());";
  protected final String TEXT_8 = NL + "                org.apache.spark.sql.SQLContext sqlContext_";
  protected final String TEXT_9 = " = new org.apache.spark.sql.SQLContext(ctx.sparkContext().sc());";
  protected final String TEXT_10 = NL + "                ";
  protected final String TEXT_11 = " parquetFile_";
  protected final String TEXT_12 = " = sqlContext_";
  protected final String TEXT_13 = ".read().parquet(";
  protected final String TEXT_14 = ");" + NL + "                org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_15 = "> rdd_";
  protected final String TEXT_16 = " = parquetFile_";
  protected final String TEXT_17 = ".toJavaRDD().mapToPair(new ";
  protected final String TEXT_18 = "_FromRowTo";
  protected final String TEXT_19 = "());";
  protected final String TEXT_20 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(codeGenArgument.getSparkVersion()) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
List<IMetadataTable> metadatas = node.getMetadataList();

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

if (metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        List< ? extends IConnection> connections = node.getOutgoingConnections();
        if ((connections != null) && (connections.size() > 0)) {
            IConnection connection = connections.get(0);
            String connName = connection.getName();
            String fileName = ElementParameterParser.getValue(node,"__FILENAME__");
            String uriPrefix = "";
            // Used for Spark only for now.
            boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
            if(useConfigurationComponent) {
                uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
                fileName = uriPrefix + " + " + fileName;
            }
            String connTypeName = codeGenArgument.getRecordStructName(connection);
            // If the current Spark version is 1.3, 1.4 or 1.5, we use the RDD InputFormat API.
            if(currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_4 || currentSparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_5) {
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(fileName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
            } else {
                // If the current Spark version greater than or equals to 1.6, we use the native dataframe API.
                TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
                String validateError = tSqlRowUtil.validate(false, true);
                if (validateError != null) {
                    // Cause the job compilation to explicitly fail if there is a problem.
                    return "throw new JobConfigurationError(\"" + validateError +"\");";
                }


    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(dataframeClass);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(fileName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_19);
    
            }
        }
    }
}

    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
