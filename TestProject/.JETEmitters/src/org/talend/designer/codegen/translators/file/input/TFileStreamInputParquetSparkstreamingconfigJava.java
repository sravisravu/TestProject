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
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TFileStreamInputParquetSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TFileStreamInputParquetSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileStreamInputParquetSparkstreamingconfigJava result = new TFileStreamInputParquetSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            TalendParquetInputFormat_";
  protected final String TEXT_2 = ".setReadSupportClass(job, parquet.hadoop.example.GroupReadSupport.class);" + NL + "            org.apache.spark.streaming.api.java.JavaPairDStream<NullWritable, ";
  protected final String TEXT_3 = "> rdd_";
  protected final String TEXT_4 = " = ctx.fileStream(";
  protected final String TEXT_5 = ", Void.class, Object.class, TalendParquetInputFormat_";
  protected final String TEXT_6 = ".class, " + NL + "            new PathFilter_";
  protected final String TEXT_7 = "(), true, job).mapToPair(new toNullWritable_";
  protected final String TEXT_8 = "());";
  protected final String TEXT_9 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();

// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with InputFormat or native Dataframe API.
final List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
INode sparkConfig = null;
if(sparkConfigs != null && sparkConfigs.size() > 0) {
    sparkConfig = sparkConfigs.get(0);
}

if (metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        List< ? extends IConnection> connections = node.getOutgoingConnections();
        if ((connections != null) && (connections.size() > 0)) {
            IConnection connection = connections.get(0);
            String connName = connection.getName();
            String folder = ElementParameterParser.getValue(node, "__FILENAME__");
            String uriPrefix = "";
            // Used for Spark only for now.
            boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
            if(useConfigurationComponent) {
                uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
                folder = uriPrefix + " + " + folder;
            }
            String connTypeName = codeGenArgument.getRecordStructName(connection);
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
        }
    }
}

    stringBuffer.append(TEXT_9);
    return stringBuffer.toString();
  }
}
