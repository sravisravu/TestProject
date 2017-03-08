package org.talend.designer.codegen.translators.file.output;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TAvroOutputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TAvroOutputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TAvroOutputSparkstreamingconfigJava result = new TAvroOutputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "            java.net.URI currentURI_";
  protected final String TEXT_3 = "_config = FileSystem.getDefaultUri(ctx.sparkContext().hadoopConfiguration());" + NL + "            FileSystem.setDefaultUri(ctx.sparkContext().hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_4 = "));" + NL + "            fs = FileSystem.get(ctx.sparkContext().hadoopConfiguration());";
  protected final String TEXT_5 = NL + "            Path pathToDelete_";
  protected final String TEXT_6 = " = new Path(";
  protected final String TEXT_7 = ");" + NL + "            if (fs.exists(pathToDelete_";
  protected final String TEXT_8 = ")) {" + NL + "                fs.delete(pathToDelete_";
  protected final String TEXT_9 = ", true);" + NL + "            }";
  protected final String TEXT_10 = NL + "            rdd_";
  protected final String TEXT_11 = ".saveAsHadoopFiles(";
  protected final String TEXT_12 = ", \"\", NullWritable.class, ";
  protected final String TEXT_13 = ".class, ";
  protected final String TEXT_14 = "StructOutputFormat.class);";
  protected final String TEXT_15 = NL + "            rdd_";
  protected final String TEXT_16 = ".foreachRDD(" + NL + "                    new WriteNonEmpty_";
  protected final String TEXT_17 = "_ForeachRDDOutput<NullWritable, ";
  protected final String TEXT_18 = ">(";
  protected final String TEXT_19 = NL + "                            ";
  protected final String TEXT_20 = ", \"\", NullWritable.class,";
  protected final String TEXT_21 = NL + "                            ";
  protected final String TEXT_22 = ".class, ";
  protected final String TEXT_23 = "StructOutputFormat.class));";
  protected final String TEXT_24 = NL + "            FileSystem.setDefaultUri(ctx.sparkContext().hadoopConfiguration(), currentURI_";
  protected final String TEXT_25 = "_config);" + NL + "            fs = FileSystem.get(ctx.sparkContext().hadoopConfiguration());";
  protected final String TEXT_26 = NL;
  protected final String TEXT_27 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();

if(metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        String rowSeparator = ElementParameterParser.getValue(node,"__ROWSEPARATOR__");
        boolean compress = "true".equals(ElementParameterParser.getValue(node, "__COMPRESS__"));
        String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");
        String folder = ElementParameterParser.getValue(node, "__FOLDER__");
        boolean deleteExisting = "OVERWRITE".equals(ElementParameterParser.getValue(node, "__FILE_ACTION__"));
        boolean writeEmptyBatches = "true".equals(ElementParameterParser.getValue(node, "__WRITE_EMPTY_BATCHES__"));

        String uriPrefix = "\"\"";
        // Used for Spark only for now.
        boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
        if(useConfigurationComponent) {
            uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
            folder = uriPrefix + " + " + folder;
        }

        String inConnName = "";
        String connTypeName = "";
        if ((node.getIncomingConnections() != null) && (node.getIncomingConnections().size() > 0)) {
            IConnection connection = node.getIncomingConnections().get(0);
            inConnName = connection.getName();
            connTypeName = codeGenArgument.getRecordStructName(connection);
        }

        if(!"\"\"".equals(uriPrefix)) {
            
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_4);
    
        }

        if (deleteExisting) {
            
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
        }

        if (writeEmptyBatches) {
            
    stringBuffer.append(TEXT_10);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    
        } else {
            
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
        }

        if(!"\"\"".equals(uriPrefix)) {
        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    
        }
    }
}

    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
