package org.talend.designer.codegen.translators.dataquality;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;
import org.talend.designer.spark.ml.features.*;
import org.talend.designer.spark.ml.features.impl.*;
import org.talend.designer.spark.ml.features.parameter.*;

public class TReservoirSamplingSparkconfigJava
{
  protected static String nl;
  public static synchronized TReservoirSamplingSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TReservoirSamplingSparkconfigJava result = new TReservoirSamplingSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "    org.talend.dataquality.sampling.parallel.SparkSamplingUtil<";
  protected final String TEXT_2 = "> sampler_";
  protected final String TEXT_3 = NL + "                = new org.talend.dataquality.sampling.parallel.SparkSamplingUtil<";
  protected final String TEXT_4 = ">(";
  protected final String TEXT_5 = NL + "                                Long.valueOf(";
  protected final String TEXT_6 = ")";
  protected final String TEXT_7 = NL + "                        );" + NL + "    " + NL + "    List<";
  protected final String TEXT_8 = "> topPairs_";
  protected final String TEXT_9 = " " + NL + "                = sampler_";
  protected final String TEXT_10 = ".getSampleList(rdd_";
  protected final String TEXT_11 = ", Integer.valueOf(";
  protected final String TEXT_12 = "));" + NL + "" + NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_13 = "> rdd_";
  protected final String TEXT_14 = " = ctx.parallelize(topPairs_";
  protected final String TEXT_15 = ");" + NL + "    ";
  protected final String TEXT_16 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    
IConnection incomingConnection = null, outgoingConnection = null;
if (node.getIncomingConnections() != null) {
    for (IConnection in : node.getIncomingConnections()) {
        if (in.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
            incomingConnection = in;
            break;
        }
    }
}
String incomingConnectionName = incomingConnection.getName();
String incomingStructName = codeGenArgument.getRecordStructName(incomingConnection);


List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
if (outgoingConnections != null && !outgoingConnections.isEmpty()) {    
    for (IConnection out : outgoingConnections) {
        if (out.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
            outgoingConnection = out;              
            break;
        }
    }
}  
String outgoingConnectionName = outgoingConnection.getName();
String outgoingStructName = codeGenArgument.getRecordStructName(outgoingConnection);

//Component params
String sampleSizeString = ElementParameterParser.getValue(node, "__SAMPLE_SIZE__");
String randomSeedString = ElementParameterParser.getValue(node, "__RANDOM_SEED__");


    stringBuffer.append(TEXT_1);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_4);
    if(randomSeedString.length() > 0 && !"\"\"".equals(randomSeedString)){ 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(randomSeedString);
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(incomingStructName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(sampleSizeString);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outgoingStructName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(outgoingConnectionName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    return stringBuffer.toString();
  }
}
