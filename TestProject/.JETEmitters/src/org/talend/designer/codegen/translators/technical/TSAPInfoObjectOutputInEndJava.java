package org.talend.designer.codegen.translators.technical;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.utils.TalendTextUtils;

public class TSAPInfoObjectOutputInEndJava
{
  protected static String nl;
  public static synchronized TSAPInfoObjectOutputInEndJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSAPInfoObjectOutputInEndJava result = new TSAPInfoObjectOutputInEndJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t}" + NL + "\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	
	List<IMetadataTable> metadatas = node.getMetadataList();
	if ((metadatas == null) && (metadatas.size() == 0) || (metadatas.get(0) == null)) {
		return "";
	}
	IMetadataTable metadata = metadatas.get(0);
	
	List<IMetadataColumn> columnList = metadata.getListColumns();
	if((columnList == null) || (columnList.size() == 0)) {
		return "";
	}
	
	boolean on_submit_protocol_work = "true".equals(ElementParameterParser.getValue(node, "__ON_SUBMIT_PROTOCOL_WORK__"));
	if(!on_submit_protocol_work) {
		return "";
	}
	
	IConnection outputConnection = null;
	List<? extends IConnection> connections = node.getOutgoingConnections();
	if(connections==null || connections.isEmpty()) {
		return "";
	}
	
	for(IConnection conn : connections) {
		if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
			outputConnection = conn;
			break;
		}
	}
	
	if(outputConnection == null) {
		return "";
	}

    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
