package org.talend.designer.codegen.translators.transformation;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.NodeParamsHelper;
import org.talend.designer.codegen.config.CamelEndpointBuilder;

public class CFlatPackMainJava
{
  protected static String nl;
  public static synchronized CFlatPackMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CFlatPackMainJava result = new CFlatPackMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t.to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "\t\tfrom(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	INode consumerNode = node;
	NodeParamsHelper helper = new NodeParamsHelper(node);
	CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();

	boolean asProductor = !node.getIncomingConnections().isEmpty();
	if(asProductor){
		Object desNode= ElementParameterParser.getValue(node, "____EXIST_ENDPOINT____");
		if(desNode!=null){
			String desNodeName = desNode.toString();	
			for(INode aNode : node.getProcess().getGraphicalNodes()){
				if(aNode.getUniqueName().equals(desNodeName)){
					consumerNode=aNode;
					break;
				}
			}
		}
	}
	
	StringBuilder sb = new StringBuilder("\"flatpack:");
	boolean isfixed = helper.getBoolParam("__ISFIXED__");
	boolean isdelimited = helper.getBoolParam("__ISDELIMITED__");
	if (isfixed){
		sb.append("fixed:");
	} else if (isdelimited) {
		sb.append("delim:");
	}
	sb.append('"');
	
	final String filePath;
	String pzmap_filetype = ElementParameterParser.getValue(node, "__PZMAP_FILETYPE__");
	if ("file".equals(pzmap_filetype)) {
		filePath = "\"file://\"+" + ElementParameterParser.getValue(node, "__PZMAP_FILENAME__");
	} else {
		filePath = "\"classpath:"
		    + org.talend.core.model.utils.JavaResourcesHelper.getResouceClasspath(
                ElementParameterParser.getValue(node, "__PZMAP_FILE_REPO__"),
                ElementParameterParser.getValue(node, "__ROUTE_RESOURCE_TYPE_VERSION__"))
		    + '"';
	}
	sb.append("+").append(filePath.replaceAll("\\\\","/"));
	boolean splitRows = helper.getBoolParam("__SPLIT_ROWS__");
	boolean allowShortLines = helper.getBoolParam("__ALLOW_SHORT_LINES__");
	boolean ignoreExtraColumns = helper.getBoolParam("__IGNORE_EXTRA_COLUMNS__");

	if (!splitRows) {
		sb.append("+\"&").append("splitRows").append('=').append(splitRows).append('"');
	}
	if (allowShortLines) {
		sb.append("+\"&").append("allowShortLines").append('=').append(allowShortLines).append('"');
	}
	if (ignoreExtraColumns) {
		sb.append("+\"&").append("ignoreExtraColumns").append('=').append(ignoreExtraColumns).append('"');
	}

	if (isdelimited) {
		String textQualifier = ElementParameterParser.getValue(node, "__TEXT_QUALIFIER__");
		if (textQualifier != null && !textQualifier.trim().equals("") && !"\"".equals(textQualifier) && !"\"\"\"".equals(textQualifier)) {
            //the first '&' should replace to '?' later.
			if (!textQualifier.startsWith("\"")) {
            textQualifier = "\"" + textQualifier;
			}
			if (!textQualifier.endsWith("\"")) {
            textQualifier = textQualifier + "\"";
			}
            sb.append("+\"&").append("textQualifier").append("=\"+").append(textQualifier);
        }

		String delimiter = ElementParameterParser.getValue(node, "__TEXT_DELIMITER__");
		if (delimiter != null && !delimiter.trim().equals("") && !",".equals(delimiter) && !"\",\"".equals(delimiter)) {
            //the first '&' should replace to '?' later.
			if (!delimiter.startsWith("\"")) {
            delimiter = "\"" + delimiter;
			}
			if (!delimiter.endsWith("\"")) {
            delimiter = delimiter + "\"";
			}
			if ("\"".equals(delimiter) || "\"\"\"".equals(delimiter)) {
			delimiter = "\"\\\"\"";
			}
            sb.append("+\"&").append("delimiter").append("=\"+").append(delimiter);
        }

		boolean ignoreFirstRecord = helper.getBoolParam("__IGNORE_FIRST_RECORD__");
		if (!ignoreFirstRecord) {
			sb.append("+\"&").append("ignoreFirstRecord").append('=').append(ignoreFirstRecord).append('"');
		}
	}

	String uriRef = sb.toString().replaceFirst("&", "?");

	if(asProductor) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uriRef);
    stringBuffer.append(TEXT_2);
    
	} else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(uriRef);
    stringBuffer.append(TEXT_4);
    
	}

    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
