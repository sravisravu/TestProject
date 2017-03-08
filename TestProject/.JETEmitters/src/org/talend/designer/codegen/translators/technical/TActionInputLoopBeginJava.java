package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TActionInputLoopBeginJava
{
  protected static String nl;
  public static synchronized TActionInputLoopBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TActionInputLoopBeginJava result = new TActionInputLoopBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\tjava.util.Map<String, Object> map_";
  protected final String TEXT_2 = " = null;" + NL + "\tjava.util.Map<IPaasField, Object> dynamic_map_";
  protected final String TEXT_3 = " = null;" + NL + "\tif (null != iPaasObject) {" + NL + "\t\tmap_";
  protected final String TEXT_4 = " = iPaasObject.take();";
  protected final String TEXT_5 = NL + "\t\t\ttry {" + NL + "\t\t\t\tdynamic_map_";
  protected final String TEXT_6 = " = iPaasObject.takeDynamic();" + NL + "\t\t\t} catch (java.lang.NoSuchMethodError nsme_";
  protected final String TEXT_7 = ") {" + NL + "\t\t\t\tthrow new java.lang.Exception(\"Dynamic types don't supported in this version\");" + NL + "\t\t\t}";
  protected final String TEXT_8 = NL + "\t}" + NL + "" + NL + "\twhile(null != map_";
  protected final String TEXT_9 = " || null != dynamic_map_";
  protected final String TEXT_10 = ") {";
  protected final String TEXT_11 = NL;

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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    		if (metadata.isDynamicSchema()) { 
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    		} 
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
     } 
    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
