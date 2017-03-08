package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TActionInputLoopEndJava
{
  protected static String nl;
  public static synchronized TActionInputLoopEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TActionInputLoopEndJava result = new TActionInputLoopEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\tif (null != iPaasObject) {" + NL + "\t\t\tmap_";
  protected final String TEXT_2 = " = iPaasObject.take();";
  protected final String TEXT_3 = NL + "\t\t\t\ttry {" + NL + "\t\t\t\t\tdynamic_map_";
  protected final String TEXT_4 = " = iPaasObject.takeDynamic();" + NL + "\t\t\t\t} catch (java.lang.NoSuchMethodError nsme_";
  protected final String TEXT_5 = ") {" + NL + "\t\t\t\t\tthrow new java.lang.Exception(\"Dynamic types don't supported in this version\");" + NL + "\t\t\t\t}";
  protected final String TEXT_6 = NL + "\t\t}" + NL + "\t} // end of while from _begin";
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName().replaceAll("_Loop", "");

IMetadataTable metadata = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if (null != metadatas && !metadatas.isEmpty()) {
	metadata = metadatas.get(0);
}

     if (null != metadata) { 
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    			if (metadata.isDynamicSchema()) { 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    			} 
    stringBuffer.append(TEXT_6);
     } 
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
