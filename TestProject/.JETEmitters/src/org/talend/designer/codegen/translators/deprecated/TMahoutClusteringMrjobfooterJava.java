package org.talend.designer.codegen.translators.deprecated;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.ElementParameterParser;
import java.util.List;
import java.util.Map;

public class TMahoutClusteringMrjobfooterJava
{
  protected static String nl;
  public static synchronized TMahoutClusteringMrjobfooterJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMahoutClusteringMrjobfooterJava result = new TMahoutClusteringMrjobfooterJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "\t//fileTmpCluser : a temp file for resaving the res of tMahoutClustering, create in mrconfig, then be read in mrcode, delete in mrjobfooter in the end." + NL + "       Path pathToDelete_";
  protected final String TEXT_3 = " = new Path(";
  protected final String TEXT_4 = "_tmp_output_path);" + NL + "       if (fs.exists(pathToDelete_";
  protected final String TEXT_5 = ")) {" + NL + "           fs.delete(pathToDelete_";
  protected final String TEXT_6 = ", true);" + NL + "       }";
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;

	INode node = (INode)codeGenArgument.getArgument();

	String cid = node.getUniqueName();

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
