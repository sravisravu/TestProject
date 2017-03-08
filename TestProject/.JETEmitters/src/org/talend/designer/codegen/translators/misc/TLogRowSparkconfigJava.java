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

public class TLogRowSparkconfigJava
{
  protected static String nl;
  public static synchronized TLogRowSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TLogRowSparkconfigJava result = new TLogRowSparkconfigJava();
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
  protected final String TEXT_10 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
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
    stringBuffer.append(TEXT_10);
    return stringBuffer.toString();
  }
}
