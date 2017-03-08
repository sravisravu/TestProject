package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ProcessUtils;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TCollectAndCheckSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TCollectAndCheckSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCollectAndCheckSparkstreamingconfigJava result = new TCollectAndCheckSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "Long batchNumber_";
  protected final String TEXT_2 = " = 1l;" + NL + "" + NL + "// Mandatory parsing of the expected result in order to retrieve the number of microbatch expected.";
  protected final String TEXT_3 = NL + "    java.util.List<String> reference_";
  protected final String TEXT_4 = " = new java.util.ArrayList<String>();" + NL + "    String javajetLineSeparator_";
  protected final String TEXT_5 = " = ";
  protected final String TEXT_6 = ";" + NL + "" + NL + "    String referenceDecodedStr_";
  protected final String TEXT_7 = " = \"\";" + NL + "    try {" + NL + "        if (checkForBase64Encode_";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = ")) {" + NL + "            // JUNIT lauch" + NL + "            referenceDecodedStr_";
  protected final String TEXT_10 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(";
  protected final String TEXT_11 = "), utf8Charset);" + NL + "        } else {" + NL + "            // Normal launch" + NL + "            // replace \\ by \\\\" + NL + "            javajetLineSeparator_";
  protected final String TEXT_12 = " = ";
  protected final String TEXT_13 = ";" + NL + "            referenceDecodedStr_";
  protected final String TEXT_14 = " = ";
  protected final String TEXT_15 = ";" + NL + "        }" + NL + "    } catch (java.io.UnsupportedEncodingException e) {" + NL + "        e.printStackTrace();" + NL + "    }" + NL;
  protected final String TEXT_16 = NL + "        if (!\"\".equals(referenceDecodedStr_";
  protected final String TEXT_17 = ")) {" + NL + "         // This will magically make a cross compatibility between window and linux line return" + NL + "            org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_18 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                    new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_19 = ".getBytes(utf8Charset))," + NL + "                    utf8Charset,";
  protected final String TEXT_20 = NL + "                    ";
  protected final String TEXT_21 = "," + NL + "                    javajetLineSeparator_";
  protected final String TEXT_22 = "," + NL + "                    true, 0, 0, -1, -1, false);" + NL + "" + NL + "            while (fid_";
  protected final String TEXT_23 = ".nextRecord()) {" + NL + "                if ((fid_";
  protected final String TEXT_24 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_25 = ".get(0)).equals(";
  protected final String TEXT_26 = ")) {" + NL + "                    batchNumber_";
  protected final String TEXT_27 = "++;" + NL + "                }" + NL + "            }" + NL + "        }";
  protected final String TEXT_28 = NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_29 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_30 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_31 = NL + "                ";
  protected final String TEXT_32 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_33 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_34 = ".nextRecord()) {" + NL + "            if ((fid_";
  protected final String TEXT_35 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_36 = ".get(0)).equals(";
  protected final String TEXT_37 = ")) {" + NL + "                batchNumber_";
  protected final String TEXT_38 = "++;" + NL + "            }" + NL + "        }";
  protected final String TEXT_39 = NL + "    java.util.List<String> reference_";
  protected final String TEXT_40 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_41 = NL + NL + "        String javajetLineSeparator_";
  protected final String TEXT_42 = " = ";
  protected final String TEXT_43 = ";" + NL + "        String referenceDecodedStr_";
  protected final String TEXT_44 = " = \"\";" + NL + "" + NL + "        try {" + NL + "            referenceDecodedStr_";
  protected final String TEXT_45 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(\"";
  protected final String TEXT_46 = "\"), utf8Charset);" + NL + "        } catch (java.io.UnsupportedEncodingException e) {" + NL + "            e.printStackTrace();" + NL + "        }" + NL + "" + NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_47 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_48 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_49 = NL + "                ";
  protected final String TEXT_50 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_51 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_52 = ".nextRecord()) {" + NL + "            if ((fid_";
  protected final String TEXT_53 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_54 = ".get(0)).equals(";
  protected final String TEXT_55 = ")) {" + NL + "                batchNumber_";
  protected final String TEXT_56 = "++;" + NL + "            }" + NL + "        }";
  protected final String TEXT_57 = NL + "manualClock.prepareOutput(rdd_";
  protected final String TEXT_58 = ", \"";
  protected final String TEXT_59 = "\", ";
  protected final String TEXT_60 = ", batchNumber_";
  protected final String TEXT_61 = ");";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
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
String separator = ElementParameterParser.getValue(node, "__SEPARATOR__");
String lineSeparator = ElementParameterParser.getValue(node, "__LINE_SEPARATOR__");
String batchSeparator = ElementParameterParser.getValue(node, "__BATCH_SEPARATOR__");

boolean useContext = ("true").equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"));
String reference = ElementParameterParser.getValue(node, "__REFERENCE__");
String contextVariable = ElementParameterParser.getValue(node, "__CONTEXT_VARIABLE__");
//option desactivated: if you want empty microbatch batch, you have to write it anyway.
Boolean emptyValueMeanNoResult = false; 

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    

org.talend.core.model.process.IProcess process = node.getProcess();
if ((useContext) && !(org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // Currently not handled
} else if ((useContext) && (org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // if we are on test job, we need to uncode the input string
    // since it is on a variable, we need to do taht on the java side.
    
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(lineSeparator.replace("\\", "\\\\"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_15);
    
    if (emptyValueMeanNoResult) {
        
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    
    } else {
        
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
    }
} else { // use direct input
    
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
    if ((!emptyValueMeanNoResult) || ((reference != null) && (!"".equals(reference)))) {
        // if the reference was directly written we need to uncode the input string
        // First encode on javajet side
        String referenceEnCodeStr = "";
        try {
            referenceEnCodeStr = (new sun.misc.BASE64Encoder()).encode(reference.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // We use the system line separator here.
        referenceEnCodeStr = referenceEnCodeStr.replace((String) java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator")), "\"+\"");
        // then decode on generated code
        
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(referenceEnCodeStr);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
    }
}

// set default batch duration from Spark Configuration
String batchDuration = "1000";
List<? extends INode> sparkConfigList = node.getProcess().getNodesOfType("tSparkConfiguration"); //$NON-NLS-1$
if (sparkConfigList != null && sparkConfigList.size() > 0) {
    // The tSparkConfiguration is a singleton in a Spark job.
    INode sparkConfig = sparkConfigList.get(0);
    batchDuration = ElementParameterParser.getValue(sparkConfig, "__STREAMING_BATCH_SIZE__");
}

// A tWindow can modify this value, check for a the component on the current job.
INode currentNode = node;
while (currentNode.getIncomingConnections() != null && currentNode.getIncomingConnections().size() > 0) {
    currentNode = currentNode.getIncomingConnections().get(0).getSource();
    if ("tWindow".equals(currentNode.getComponent().getName())) {
        if (ElementParameterParser.getBooleanValue(currentNode, "__DEFINE_SLIDE_DURATION__")) {
            batchDuration = ElementParameterParser.getValue(currentNode, "__SLIDE_DURATION__");
            break;
        }
    }
}

    stringBuffer.append(TEXT_57);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(batchDuration);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    return stringBuffer.toString();
  }
}
