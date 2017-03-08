package org.talend.designer.codegen.translators.processing.datamapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.talend.transform.components.spark.TDMExternalNodeProvider;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.transform.dataflow.common.THConvertFileParms;
import org.talend.transform.components.spark.TDMExternalNodeProvider;
import org.talend.transform.components.spark.utils.TDMUtils;
import org.talend.transform.components.spark.thconvertfile.THConvertFileComponent;

public class THConvertFileSparkconfigJava
{
  protected static String nl;
  public static synchronized THConvertFileSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THConvertFileSparkconfigJava result = new THConvertFileSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    java.net.URI currentURI_";
  protected final String TEXT_2 = "_config = FileSystem.getDefaultUri(ctx.hadoopConfiguration());" + NL + "    FileSystem.setDefaultUri(ctx.hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_3 = "));" + NL + "    fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_4 = NL + "    Path pathToDelete_";
  protected final String TEXT_5 = " = new Path(";
  protected final String TEXT_6 = ");" + NL + "    if (fs.exists(pathToDelete_";
  protected final String TEXT_7 = ")) {" + NL + "    \tfs.delete(pathToDelete_";
  protected final String TEXT_8 = ", true);" + NL + "    }";
  protected final String TEXT_9 = NL + "{" + NL + "    // Set up a Spark DataFlow." + NL + "    org.talend.bigdata.dataflow.spark.batch.SparkBatchDataFlowContext ";
  protected final String TEXT_10 = "_sdfContext = new org.talend.bigdata.dataflow.spark.batch.SparkBatchDataFlowContext.Builder()" + NL + "        .withSparkContext(ctx).build();" + NL + "    org.talend.bigdata.dataflow.spark.batch.SparkBatchDataFlow sdf = new org.talend.bigdata.dataflow.spark.batch.SparkBatchDataFlow(";
  protected final String TEXT_11 = NL + "        ";
  protected final String TEXT_12 = "_sdfContext);" + NL + "\t" + NL + "    // Set up and run the component." + NL + "    org.talend.transform.dataflow.hcio.HConvertIO hcio = new org.talend.transform.dataflow.hcio.HConvertIO();" + NL + "    org.talend.transform.dataflow.hcio.HConvertIOSpecBuilder hsb = hcio" + NL + "            .createSpecBuilder();" + NL + "    hsb.setId(\"";
  protected final String TEXT_13 = "\");" + NL + "    hsb.setInput(";
  protected final String TEXT_14 = ");" + NL + "    hsb.setOutput(";
  protected final String TEXT_15 = ");" + NL + "    hsb.setTdmStructure(\"";
  protected final String TEXT_16 = "\");" + NL + "    hsb.setParams(\"";
  protected final String TEXT_17 = "\");" + NL + "    hsb.setProjectArchives(\"";
  protected final String TEXT_18 = "\");" + NL + "    hcio.createDataFlowBuilder(hsb.build()).build(sdf);" + NL + "    ";
  protected final String TEXT_19 = NL + "    \tFileSystem.setDefaultUri(ctx.hadoopConfiguration(), currentURI_";
  protected final String TEXT_20 = "_config);" + NL + "        fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_21 = NL + "}";
  protected final String TEXT_22 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

String paramsStr = ElementParameterParser.getValue(node,"__PARAMS__");;
THConvertFileParms params = ((THConvertFileComponent) ((TDMExternalNodeProvider) node.getExternalNode()).getCurrentTDMNode()).getAndValidate(paramsStr);
String input = ElementParameterParser.getValue(node,"__INPUT__");;
String output = ElementParameterParser.getValue(node,"__OUTPUT__");;
String tdmStructure = TDMUtils.getStructureProjectPath(ElementParameterParser.getValue(node,"__TDM_STRUCTURE__"));
String projectArchives = ((TDMExternalNodeProvider) node.getExternalNode()).createTDMArchives(tdmStructure);
tdmStructure = ((TDMExternalNodeProvider) node.getExternalNode()).fixStructurePath(tdmStructure);


String uriPrefix = "";
// Used for Spark only for now.
boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
if(useConfigurationComponent) {
    uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
    input = uriPrefix + " + " + input;
    output = uriPrefix + " + " + output;
}
boolean deleteExisting = "OVERWRITE".equals(ElementParameterParser.getValue(node, "__FILE_ACTION__"));
if(uriPrefix.length() > 0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_3);
    
}
if (deleteExisting) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(output);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
}


    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(input);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(output);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(tdmStructure);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(((TDMExternalNodeProvider) node.getExternalNode()).escapeString(params.toString()));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(projectArchives);
    stringBuffer.append(TEXT_18);
    
    if(uriPrefix.length() > 0) {
    
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
    }
    
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
