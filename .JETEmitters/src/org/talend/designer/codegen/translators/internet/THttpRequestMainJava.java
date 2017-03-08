package org.talend.designer.codegen.translators.internet;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.EConnectionType;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnectionCategory;
import java.util.List;
import java.util.Map;

public class THttpRequestMainJava
{
  protected static String nl;
  public static synchronized THttpRequestMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THttpRequestMainJava result = new THttpRequestMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t\t\t\tlog.debug(\"";
  protected final String TEXT_2 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_5 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_6 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_7 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_8 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_9 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_10 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_11 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_12 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_13 = " - Written records count: \" + nb_line_";
  protected final String TEXT_14 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_16 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_18 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_19 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_20 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_21 = " - Writing the record \" + nb_line_";
  protected final String TEXT_22 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_23 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_24 = " - Processing the record \" + nb_line_";
  protected final String TEXT_25 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_26 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_27 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_28 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_29 = NL + NL + "int connectTimeOut_";
  protected final String TEXT_30 = " = Integer.valueOf(";
  protected final String TEXT_31 = ")*1000;" + NL + "int readTimeOut_";
  protected final String TEXT_32 = " = Integer.valueOf(";
  protected final String TEXT_33 = ")*1000;" + NL + "" + NL + "java.net.URL url_";
  protected final String TEXT_34 = " = new java.net.URL(";
  protected final String TEXT_35 = ");" + NL;
  protected final String TEXT_36 = NL + "  java.net.Authenticator.setDefault(new java.net.Authenticator() {" + NL + "      protected java.net.PasswordAuthentication getPasswordAuthentication() {" + NL;
  protected final String TEXT_37 = NL;
  protected final String TEXT_38 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_39 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_40 = ");";
  protected final String TEXT_41 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_42 = " = ";
  protected final String TEXT_43 = "; ";
  protected final String TEXT_44 = NL + NL + "        return new java.net.PasswordAuthentication(";
  protected final String TEXT_45 = ", decryptedPassword_";
  protected final String TEXT_46 = ".toCharArray());" + NL + "  }});";
  protected final String TEXT_47 = NL + "        log.info(\"";
  protected final String TEXT_48 = " - Connection attempt to '\" + ";
  protected final String TEXT_49 = ");";
  protected final String TEXT_50 = NL + "java.net.HttpURLConnection urlConn_";
  protected final String TEXT_51 = " = (java.net.HttpURLConnection) url_";
  protected final String TEXT_52 = ".openConnection();";
  protected final String TEXT_53 = NL + "        log.info(\"";
  protected final String TEXT_54 = " - Connection to '\" +  ";
  protected final String TEXT_55 = " + \"' has succeeded.\");";
  protected final String TEXT_56 = NL + "urlConn_";
  protected final String TEXT_57 = ".setRequestMethod(\"";
  protected final String TEXT_58 = "\");" + NL + "urlConn_";
  protected final String TEXT_59 = ".setDoOutput(true);" + NL + "urlConn_";
  protected final String TEXT_60 = ".setDoInput(true);" + NL + "urlConn_";
  protected final String TEXT_61 = ".setUseCaches(false);";
  protected final String TEXT_62 = NL + "\turlConn_";
  protected final String TEXT_63 = ".setConnectTimeout(connectTimeOut_";
  protected final String TEXT_64 = ");" + NL + "\turlConn_";
  protected final String TEXT_65 = ".setReadTimeout(readTimeOut_";
  protected final String TEXT_66 = ");";
  protected final String TEXT_67 = NL;
  protected final String TEXT_68 = NL + "  urlConn_";
  protected final String TEXT_69 = ".setRequestProperty(";
  protected final String TEXT_70 = ", ";
  protected final String TEXT_71 = ");";
  protected final String TEXT_72 = NL + "boolean connected_";
  protected final String TEXT_73 = " = false;" + NL + "int responseCode_";
  protected final String TEXT_74 = " = 0;" + NL + "String responseMessage_";
  protected final String TEXT_75 = " = null;" + NL + "globalMap.put(\"";
  protected final String TEXT_76 = "_CONNECTED\", false);" + NL + "try{" + NL + "    urlConn_";
  protected final String TEXT_77 = ".connect();" + NL + "    connected_";
  protected final String TEXT_78 = " = true;" + NL + "    globalMap.put(\"";
  protected final String TEXT_79 = "_CONNECTED\", true);" + NL + "" + NL + "    byte[] buffer_";
  protected final String TEXT_80 = " = new byte[1024];" + NL + "    int bos_buffer_";
  protected final String TEXT_81 = " = 0;";
  protected final String TEXT_82 = "StringBuilder sb_";
  protected final String TEXT_83 = "=new StringBuilder(); ";
  protected final String TEXT_84 = NL + "            java.io.InputStream bisParam_";
  protected final String TEXT_85 = " = new java.io.BufferedInputStream(new java.io.FileInputStream(";
  protected final String TEXT_86 = "));" + NL + "            java.io.OutputStream bosParam_";
  protected final String TEXT_87 = " = new java.io.BufferedOutputStream(urlConn_";
  protected final String TEXT_88 = ".getOutputStream());" + NL + "" + NL + "            while ((bos_buffer_";
  protected final String TEXT_89 = " = bisParam_";
  protected final String TEXT_90 = ".read(buffer_";
  protected final String TEXT_91 = ")) != -1) {" + NL + "                bosParam_";
  protected final String TEXT_92 = ".write(buffer_";
  protected final String TEXT_93 = ",0,bos_buffer_";
  protected final String TEXT_94 = ");" + NL + "            }" + NL + "            bosParam_";
  protected final String TEXT_95 = ".flush();" + NL + "            bosParam_";
  protected final String TEXT_96 = ".close();" + NL + "            bisParam_";
  protected final String TEXT_97 = ".close();";
  protected final String TEXT_98 = NL + NL + "    responseCode_";
  protected final String TEXT_99 = " = urlConn_";
  protected final String TEXT_100 = ".getResponseCode();" + NL + "    globalMap.put(\"";
  protected final String TEXT_101 = "_RESPONSE_CODE\", responseCode_";
  protected final String TEXT_102 = ");" + NL + "    responseMessage_";
  protected final String TEXT_103 = " = urlConn_";
  protected final String TEXT_104 = ".getResponseMessage();" + NL + "" + NL + "    if (java.net.HttpURLConnection.HTTP_OK == responseCode_";
  protected final String TEXT_105 = ") {" + NL + "        java.io.InputStream bis_";
  protected final String TEXT_106 = " = new java.io.BufferedInputStream(urlConn_";
  protected final String TEXT_107 = ".getInputStream());";
  protected final String TEXT_108 = NL + "                java.io.File bosParent_";
  protected final String TEXT_109 = " = new java.io.File(";
  protected final String TEXT_110 = ").getParentFile();" + NL + "                if (bosParent_";
  protected final String TEXT_111 = " != null && bosParent_";
  protected final String TEXT_112 = ".exists() == false){" + NL + "                    bosParent_";
  protected final String TEXT_113 = ".mkdirs();" + NL + "                }";
  protected final String TEXT_114 = NL + "            java.io.OutputStream bosContent_";
  protected final String TEXT_115 = " = new java.io.BufferedOutputStream(new java.io.FileOutputStream(";
  protected final String TEXT_116 = "));";
  protected final String TEXT_117 = NL + "        while ((bos_buffer_";
  protected final String TEXT_118 = " = bis_";
  protected final String TEXT_119 = ".read(buffer_";
  protected final String TEXT_120 = ")) != -1) {";
  protected final String TEXT_121 = "bosContent_";
  protected final String TEXT_122 = ".write(buffer_";
  protected final String TEXT_123 = ",0,bos_buffer_";
  protected final String TEXT_124 = "); ";
  protected final String TEXT_125 = "sb_";
  protected final String TEXT_126 = ".append(new String(buffer_";
  protected final String TEXT_127 = ",0,bos_buffer_";
  protected final String TEXT_128 = ")); ";
  protected final String TEXT_129 = NL + "        }";
  protected final String TEXT_130 = NL + "            bosContent_";
  protected final String TEXT_131 = ".flush();" + NL + "            bosContent_";
  protected final String TEXT_132 = ".close();";
  protected final String TEXT_133 = NL + "        bis_";
  protected final String TEXT_134 = ".close();" + NL + "    } else {";
  protected final String TEXT_135 = NL + "                log.error(\"";
  protected final String TEXT_136 = " - \" + responseCode_";
  protected final String TEXT_137 = " + \" \" + responseMessage_";
  protected final String TEXT_138 = ");";
  protected final String TEXT_139 = NL + "            System.err.println(responseCode_";
  protected final String TEXT_140 = " + \" \" + responseMessage_";
  protected final String TEXT_141 = ");";
  protected final String TEXT_142 = NL + "            throw new RuntimeException(\"Request failed:\" + responseCode_";
  protected final String TEXT_143 = " + \" \" + responseMessage_";
  protected final String TEXT_144 = ");";
  protected final String TEXT_145 = NL + "    }" + NL;
  protected final String TEXT_146 = NL + "                    ";
  protected final String TEXT_147 = ".ResponseContent = sb_";
  protected final String TEXT_148 = ".toString();";
  protected final String TEXT_149 = NL + "                    ";
  protected final String TEXT_150 = ".";
  protected final String TEXT_151 = " = ";
  protected final String TEXT_152 = ".";
  protected final String TEXT_153 = ";";
  protected final String TEXT_154 = NL + "    log.info(\"";
  protected final String TEXT_155 = " - Closing the connection to the server.\");";
  protected final String TEXT_156 = NL + "    urlConn_";
  protected final String TEXT_157 = ".disconnect();";
  protected final String TEXT_158 = NL + "    log.info(\"";
  protected final String TEXT_159 = " - Connection to the server closed.\");";
  protected final String TEXT_160 = NL + "}catch(Exception e){";
  protected final String TEXT_161 = NL + "            log.error(\"";
  protected final String TEXT_162 = " - \" + e.getMessage());";
  protected final String TEXT_163 = NL + "        System.err.println(e.getMessage());";
  protected final String TEXT_164 = NL + "        throw e;";
  protected final String TEXT_165 = NL + "}";
  protected final String TEXT_166 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_1);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_2);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_6);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_8);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_9);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_12);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_14);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_17);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_19);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_20);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_22);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_23);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_25);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_26);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_28);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
String cid = node.getUniqueName();

List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
List<? extends IConnection> outConns = node.getOutgoingSortedConnections();
String inConnName = null, outConnName = null;

boolean hasOutputSchema = false;

if (outConns != null && outConns.size() > 0){
    if (outConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
        outConnName = outConns.get(0).getName();
        hasOutputSchema = true;
    }
    if (inConns != null && inConns.size() > 0){
        inConnName = inConns.get(0).getName();
    }
}

String sURI = ElementParameterParser.getValue(node, "__URI__");
String sMethod = ElementParameterParser.getValue(node, "__METHOD_CHOSED__");
String sParamFile = ElementParameterParser.getValue(node, "__POST_PARAMS_FROM_FILE__");
boolean bOutToFile = "true".equals(ElementParameterParser.getValue(node, "__OUT_TO_FILE__"));
String sOutFilePath = ElementParameterParser.getValue(node, "__OUT_FILE_NAME__");
List<Map<String, String>> headerParams = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__HEADERS__");
boolean bNeedAuthentication = "true".equals(ElementParameterParser.getValue(node, "__NEED_AUTHENTICATION__"));
String sUser = ElementParameterParser.getValue(node, "__USR__");
boolean isTimeOutSetting = "true".equals(ElementParameterParser.getValue(node, "__TIMEOUT_SETTING__"));
String connectTimeOut = ElementParameterParser.getValue(node, "__CONNECT_TIMEOUT__");
String readTimeOut = ElementParameterParser.getValue(node, "__READ_TIMEOUT__");

boolean createDirs = "true".equals(ElementParameterParser.getValue(node, "__CREATE_DIRS__"));
boolean dieOnError = "true".equals(ElementParameterParser.getValue(node, "__DIE_ON_ERROR__"));

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(connectTimeOut);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(readTimeOut);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(sURI);
    stringBuffer.append(TEXT_35);
    if (bNeedAuthentication){
    stringBuffer.append(TEXT_36);
    
        String passwordFieldName = "__PWD__";
        
    stringBuffer.append(TEXT_37);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_40);
    } else {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(sUser);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    }
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(sURI);
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(sURI);
    stringBuffer.append(TEXT_55);
    }
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(sMethod);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    if(isTimeOutSetting){
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    for (Map<String, String> header : headerParams){ // set request properties
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(header.get("HEADER_NAME"));
    stringBuffer.append(TEXT_70);
    stringBuffer.append(header.get("HEADER_VALUE"));
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    if(hasOutputSchema){
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    }
    
// add parameters from file
    if ("POST".equals(sMethod)){
        if (sParamFile != null && !"".equals(sParamFile)){
  
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(sParamFile);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    
        }
    }

    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    
  // output response content to file
        if (bOutToFile && sOutFilePath != null){

              if(createDirs){
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(sOutFilePath);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
              }  
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(sOutFilePath);
    stringBuffer.append(TEXT_116);
          }
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
              if (bOutToFile && sOutFilePath != null){
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    }
              if(hasOutputSchema){
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    }
    stringBuffer.append(TEXT_129);
          if (bOutToFile && sOutFilePath != null){
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
          }
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    if(!dieOnError){
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    }else{
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    }
    stringBuffer.append(TEXT_145);
    
// output data
    if (outConnName != null){
        List<IMetadataTable> metaTables = node.getMetadataList();

        if (metaTables != null && metaTables.size() > 0){
            IMetadataTable metaTable = metaTables.get(0);
            List<IMetadataColumn> columns = metaTable.getListColumns();

            for (IMetadataColumn column : columns){
                String colName = column.getLabel();

                if ("ResponseContent".equals(colName)){
      
    stringBuffer.append(TEXT_146);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    
                } else {
      
    stringBuffer.append(TEXT_149);
    stringBuffer.append(outConnName);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(colName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(colName);
    stringBuffer.append(TEXT_153);
    
                }
            }
        }
    }

    if(isLog4jEnabled){
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    }
    stringBuffer.append(TEXT_160);
    if(!dieOnError){
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(TEXT_163);
    }else{
    stringBuffer.append(TEXT_164);
    }
    stringBuffer.append(TEXT_165);
    stringBuffer.append(TEXT_166);
    return stringBuffer.toString();
  }
}
