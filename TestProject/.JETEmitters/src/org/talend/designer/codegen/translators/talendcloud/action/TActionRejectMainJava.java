package org.talend.designer.codegen.translators.talendcloud.action;

import java.util.List;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class TActionRejectMainJava
{
  protected static String nl;
  public static synchronized TActionRejectMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TActionRejectMainJava result = new TActionRejectMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\tif (null != iPaasObject) {" + NL + "\t\t\t\t\tjava.util.Map<String, Object> map_";
  protected final String TEXT_2 = " = new java.util.HashMap<String, Object>(";
  protected final String TEXT_3 = ");";
  protected final String TEXT_4 = NL + "\t\t\t\t\t\tmap_";
  protected final String TEXT_5 = ".put(\"";
  protected final String TEXT_6 = "\", ";
  protected final String TEXT_7 = ".";
  protected final String TEXT_8 = ");";
  protected final String TEXT_9 = NL + "\t\t\t\t\tiPaasObject.reject(map_";
  protected final String TEXT_10 = ");" + NL + "\t\t\t\t}";
  protected final String TEXT_11 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();

for (IConnection conn : node.getIncomingConnections()) {
	if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
		List<IMetadataTable> metadatas = node.getMetadataList();
		if (null != metadatas && 0 < metadatas.size()) {
			IMetadataTable metadata = metadatas.get(0);
			if (null != metadata) {
				List<IMetadataColumn> metadataColumns = metadata.getListColumns();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(metadataColumns.size());
    stringBuffer.append(TEXT_3);
    					for (IMetadataColumn column : metadataColumns) {
						String name = column.getLabel(); 
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(conn.getName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_8);
    					} 
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
				break;
			}
		}
	}
}

    stringBuffer.append(TEXT_11);
    return stringBuffer.toString();
  }
}
