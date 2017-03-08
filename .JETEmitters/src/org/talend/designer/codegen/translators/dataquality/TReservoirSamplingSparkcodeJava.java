package org.talend.designer.codegen.translators.dataquality;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.designer.common.tmodelencoder.TModelEncoderUtil;

public class TReservoirSamplingSparkcodeJava
{
  protected static String nl;
  public static synchronized TReservoirSamplingSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TReservoirSamplingSparkcodeJava result = new TReservoirSamplingSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
