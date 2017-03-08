package org.talend.designer.codegen.translators.misc;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TLogRowSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TLogRowSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TLogRowSparkstreamingconfigJava result = new TLogRowSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tjava.util.List<";
  protected final String TEXT_2 = "> collectedRddRecords_";
  protected final String TEXT_3 = " = rdd_";
  protected final String TEXT_4 = ".values().collect();" + NL + "\tDisplayer_";
  protected final String TEXT_5 = " displayer_";
  protected final String TEXT_6 = " = new Displayer_";
  protected final String TEXT_7 = "(job);" + NL + "\tdisplayer_";
  protected final String TEXT_8 = ".display(collectedRddRecords_";
  protected final String TEXT_9 = ");" + NL;
  protected final String TEXT_10 = NL + "    rdd_";
  protected final String TEXT_11 = ".foreachRDD(new ";
  protected final String TEXT_12 = "_ForeachRDDOutput(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode((INode) ((BigDataCodeGeneratorArgument) argument).getArgument())) {
    
    
	BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
	IConnection inConn = inConns.get(0);
	String inConnName = inConn.getName();
	String inConnTypeName = codeGenArgument.getRecordStructName(inConn);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
} else {
    // We do a foreachRDD on the DStream and collect values then print them
    BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();

    List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
    if(inConns != null && inConns.size() > 0) {
    } else {
        return "";
    }

    IConnection inConn = inConns.get(0);
    String inConnName = inConn.getName();
    String inConnTypeName = codeGenArgument.getRecordStructName(inConn);

    
    stringBuffer.append(TEXT_10);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    
} // end else

    return stringBuffer.toString();
  }
}
