package org.talend.designer.codegen.translators.file.hadoop;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;

public class THDFSPutBeginJava
{
  protected static String nl;
  public static synchronized THDFSPutBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THDFSPutBeginJava result = new THDFSPutBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_5 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_6 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_7 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_8 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_9 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_10 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_12 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_13 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_15 = " - Written records count: \" + nb_line_";
  protected final String TEXT_16 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_18 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_19 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_20 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_21 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_23 = " - Writing the record \" + nb_line_";
  protected final String TEXT_24 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_25 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_26 = " - Processing the record \" + nb_line_";
  protected final String TEXT_27 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_28 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_29 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_30 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_31 = NL;
  protected final String TEXT_32 = NL + "String username_";
  protected final String TEXT_33 = " = \"\";" + NL + "org.apache.hadoop.fs.FileSystem fs_";
  protected final String TEXT_34 = " = null;";
  protected final String TEXT_35 = NL + "\torg.apache.hadoop.conf.Configuration conf_";
  protected final String TEXT_36 = " = new org.apache.hadoop.conf.Configuration();" + NL + "\tconf_";
  protected final String TEXT_37 = ".set(\"";
  protected final String TEXT_38 = "\", ";
  protected final String TEXT_39 = ");" + NL + "" + NL + "\t";
  protected final String TEXT_40 = NL + "        conf_";
  protected final String TEXT_41 = ".set(\"dfs.client.use.datanode.hostname\", \"true\");";
  protected final String TEXT_42 = NL + "\t\t\tconf_";
  protected final String TEXT_43 = ".set(";
  protected final String TEXT_44 = " ,";
  protected final String TEXT_45 = ");" + NL + "\t\t";
  protected final String TEXT_46 = NL + "            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_47 = ");" + NL + "            com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_48 = " = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_49 = NL + "                System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_50 = ");";
  protected final String TEXT_51 = NL + "                maprLogin_";
  protected final String TEXT_52 = ".setCheckUGI(false);";
  protected final String TEXT_53 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_54 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_55 = ");";
  protected final String TEXT_56 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_57 = " = ";
  protected final String TEXT_58 = "; ";
  protected final String TEXT_59 = NL + NL + "            maprLogin_";
  protected final String TEXT_60 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_61 = ", ";
  protected final String TEXT_62 = ", decryptedPassword_";
  protected final String TEXT_63 = ", ";
  protected final String TEXT_64 = ");";
  protected final String TEXT_65 = NL + "            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_66 = ");" + NL + "            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_67 = ");";
  protected final String TEXT_68 = NL + "\t\tconf_";
  protected final String TEXT_69 = ".set(\"dfs.namenode.kerberos.principal\", ";
  protected final String TEXT_70 = ");";
  protected final String TEXT_71 = NL + "\t\t\torg.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_72 = ", ";
  protected final String TEXT_73 = ");";
  protected final String TEXT_74 = NL + "            com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_75 = " = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "            maprLogin_";
  protected final String TEXT_76 = ".getMapRCredentialsViaKerberos(";
  protected final String TEXT_77 = ", ";
  protected final String TEXT_78 = ");";
  protected final String TEXT_79 = NL + "       org.apache.hadoop.security.UserGroupInformation.setConfiguration(conf_";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = NL + "    \tconf_";
  protected final String TEXT_82 = ".set(\"hadoop.job.ugi\",";
  protected final String TEXT_83 = "+\",\"+";
  protected final String TEXT_84 = ");" + NL + "    \tfs_";
  protected final String TEXT_85 = " = org.apache.hadoop.fs.FileSystem.get(conf_";
  protected final String TEXT_86 = ");" + NL + "\t";
  protected final String TEXT_87 = NL + "\t\tusername_";
  protected final String TEXT_88 = " = ";
  protected final String TEXT_89 = ";" + NL + "\t\tif(username_";
  protected final String TEXT_90 = " == null || \"\".equals(username_";
  protected final String TEXT_91 = ")){" + NL + "\t\t\tfs_";
  protected final String TEXT_92 = " = org.apache.hadoop.fs.FileSystem.get(conf_";
  protected final String TEXT_93 = ");" + NL + "\t\t}else{" + NL + "\t\t\tfs_";
  protected final String TEXT_94 = " = org.apache.hadoop.fs.FileSystem.get(new java.net.URI(conf_";
  protected final String TEXT_95 = ".get(\"";
  protected final String TEXT_96 = "\")),conf_";
  protected final String TEXT_97 = ",username_";
  protected final String TEXT_98 = ");" + NL + "\t\t}\t" + NL + "\t";
  protected final String TEXT_99 = NL + "\torg.apache.hadoop.conf.Configuration conf_";
  protected final String TEXT_100 = " = (org.apache.hadoop.conf.Configuration)globalMap.get(\"conn_";
  protected final String TEXT_101 = "\");" + NL + "\t";
  protected final String TEXT_102 = NL + "\t\t    \tfs_";
  protected final String TEXT_103 = " = org.apache.hadoop.fs.FileSystem.get(conf_";
  protected final String TEXT_104 = ");" + NL + "\t\t\t";
  protected final String TEXT_105 = NL + "\t\t\t\t\t\tconf_";
  protected final String TEXT_106 = ".set(\"dfs.namenode.kerberos.principal\", ";
  protected final String TEXT_107 = ");";
  protected final String TEXT_108 = "\t\t\t\t\t" + NL + "\t\t\t\t\tusername_";
  protected final String TEXT_109 = " = ";
  protected final String TEXT_110 = ";";
  protected final String TEXT_111 = NL + "\t\t\t\t\tif(!org.apache.hadoop.security.UserGroupInformation.isSecurityEnabled()) {" + NL + "\t\t\t\t\t\tusername_";
  protected final String TEXT_112 = " = conf_";
  protected final String TEXT_113 = ".get(\"talend.hadoop.user.name\", \"\");" + NL + "\t\t\t\t\t}";
  protected final String TEXT_114 = NL + "\t\t\t\tif(username_";
  protected final String TEXT_115 = " == null || \"\".equals(username_";
  protected final String TEXT_116 = ")){" + NL + "\t\t\t\t\tfs_";
  protected final String TEXT_117 = " = org.apache.hadoop.fs.FileSystem.get(conf_";
  protected final String TEXT_118 = ");" + NL + "\t\t\t\t}else{" + NL + "\t\t\t\t\tfs_";
  protected final String TEXT_119 = " = org.apache.hadoop.fs.FileSystem.get(new java.net.URI(conf_";
  protected final String TEXT_120 = ".get(\"";
  protected final String TEXT_121 = "\")),conf_";
  protected final String TEXT_122 = ",username_";
  protected final String TEXT_123 = ");" + NL + "\t\t\t\t}\t\t\t  \t\t" + NL + "\t\t  \t";
  protected final String TEXT_124 = NL + "\t\t";
  protected final String TEXT_125 = NL + "\tint nb_file_";
  protected final String TEXT_126 = " = 0;" + NL + "\tint nb_success_";
  protected final String TEXT_127 = " = 0;" + NL + "\t" + NL + "\tfs_";
  protected final String TEXT_128 = ".mkdirs(new org.apache.hadoop.fs.Path(";
  protected final String TEXT_129 = "));" + NL + "\tjava.util.List<String> msg_";
  protected final String TEXT_130 = " = new java.util.ArrayList<String>();" + NL + "    java.util.List<java.util.Map<String,String>> list_";
  protected final String TEXT_131 = " = new java.util.ArrayList<java.util.Map<String,String>>();\t" + NL + "\t";
  protected final String TEXT_132 = "    " + NL + "\t\tjava.util.Map<String,String> map_";
  protected final String TEXT_133 = "_";
  protected final String TEXT_134 = " = new java.util.HashMap<String,String>();" + NL + "\t\tmap_";
  protected final String TEXT_135 = "_";
  protected final String TEXT_136 = ".put(";
  protected final String TEXT_137 = ",";
  protected final String TEXT_138 = ");\t\t" + NL + "\t \tlist_";
  protected final String TEXT_139 = ".add(map_";
  protected final String TEXT_140 = "_";
  protected final String TEXT_141 = ");       " + NL + "\t";
  protected final String TEXT_142 = "\t" + NL + "" + NL + "\tString localdir_";
  protected final String TEXT_143 = "  = ";
  protected final String TEXT_144 = ";\t" + NL + "\tfor (java.util.Map<String, String> map_";
  protected final String TEXT_145 = " : list_";
  protected final String TEXT_146 = ") " + NL + "\t{" + NL;
  protected final String TEXT_147 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    
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
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_5);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_8);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_11);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_14);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_16);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_19);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_21);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_22);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_24);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_25);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_28);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    stringBuffer.append(TEXT_31);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

String fsDefaultName = ElementParameterParser.getValue(node, "__FS_DEFAULT_NAME__");
boolean useExistingConnection = "true".equals(ElementParameterParser.getValue(node, "__USE_EXISTING_CONNECTION__"));
List<Map<String, String>> hadoopProps = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__HADOOP_ADVANCED_PROPERTIES__");
String user = null;

String fsDefalutName = "fs.default.name";

String distribution = null;
String hadoopVersion = null;
boolean isCustom = false;
org.talend.hadoop.distribution.component.HDFSComponent hdfsDistrib = null;

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

log4jFileUtil.componentStartInfo(node);


    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
if(!useExistingConnection) { // if we don't use an existing connection, we create a new hadoop configuration
	distribution = ElementParameterParser.getValue(node, "__DISTRIBUTION__");
	hadoopVersion = ElementParameterParser.getValue(node, "__DB_VERSION__");

	boolean useKrb = "true".equals(ElementParameterParser.getValue(node, "__USE_KRB__"));
	String kerberosPrincipal = ElementParameterParser.getValue(node, "__NAMENODE_PRINCIPAL__");
	boolean useKeytab = "true".equals(ElementParameterParser.getValue(node, "__USE_KEYTAB__"));
	String userPrincipal = ElementParameterParser.getValue(node, "__PRINCIPAL__");
	String keytabPath = ElementParameterParser.getValue(node, "__KEYTAB_PATH__");
    boolean mrUseDatanodeHostname = "true".equals(ElementParameterParser.getValue(node, "__USE_DATANODE_HOSTNAME__"));
    
    boolean useMapRTicket = ElementParameterParser.getBooleanValue(node, "__USE_MAPRTICKET__");
    String mapRTicketCluster = ElementParameterParser.getValue(node, "__MAPRTICKET_CLUSTER__");
    String mapRTicketDuration = ElementParameterParser.getValue(node, "__MAPRTICKET_DURATION__");

    boolean setMapRHomeDir = ElementParameterParser.getBooleanValue(node, "__SET_MAPR_HOME_DIR__");
    String mapRHomeDir = ElementParameterParser.getValue(node, "__MAPR_HOME_DIR__");

    boolean setMapRHadoopLogin = ElementParameterParser.getBooleanValue(node, "__SET_HADOOP_LOGIN__");
    String mapRHadoopLogin = ElementParameterParser.getValue(node, "__HADOOP_LOGIN__");
    
	try {
		hdfsDistrib = (org.talend.hadoop.distribution.component.HDFSComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, hadoopVersion);
	} catch (java.lang.Exception e) {
		e.printStackTrace();
		return "";
	}
    isCustom = hdfsDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;

	String auth = ElementParameterParser.getValue(node, "__AUTHENTICATION_MODE__");
	
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(fsDefalutName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(fsDefaultName);
    stringBuffer.append(TEXT_39);
    
	if (hdfsDistrib.doSupportUseDatanodeHostname() && mrUseDatanodeHostname) {
        
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
    }
	if(hadoopProps!=null && hadoopProps.size() > 0){
		for(Map<String, String> item : hadoopProps){
		
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(item.get("PROPERTY") );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(item.get("VALUE") );
    stringBuffer.append(TEXT_45);
     
		}
	}
	
	if(!((!isCustom && hdfsDistrib.doSupportKerberos() && useKrb) || (isCustom && "KRB".equals(auth)))) {
		user = ElementParameterParser.getValue(node, "__USERNAME__");
		if ((isCustom || hdfsDistrib.doSupportMapRTicket()) && useMapRTicket) {
            String passwordFieldName = "__MAPRTICKET_PASSWORD__";
            
    stringBuffer.append(TEXT_46);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
            if (setMapRHadoopLogin) {
                
    stringBuffer.append(TEXT_49);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_50);
    
            } else {
                
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    
            }
            
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_55);
    } else {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_58);
    }
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(user);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_64);
    
        }
	} else {
	    if ((isCustom || hdfsDistrib.doSupportMapRTicket()) && useMapRTicket) {
            
    stringBuffer.append(TEXT_65);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_66);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_67);
    
        }

    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(kerberosPrincipal);
    stringBuffer.append(TEXT_70);
    
		if(useKeytab) {

    stringBuffer.append(TEXT_71);
    stringBuffer.append(userPrincipal);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(keytabPath);
    stringBuffer.append(TEXT_73);
    
		}
        if ((isCustom || hdfsDistrib.doSupportMapRTicket()) && useMapRTicket) {
            
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_78);
    
        }
	}


    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    
	
	if((!isCustom && hdfsDistrib.doSupportGroup()) || (isCustom && "UGI".equals(auth))){
		String group = ElementParameterParser.getValue(node, "__GROUP__");
	
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(user);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(group);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    
	}else{
	
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(user);
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
    stringBuffer.append(fsDefalutName);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    
	}
	
} else { // We re use the existing connection, coming from the component learned.
	String connectionSid = ElementParameterParser.getValue(node, "__CONNECTION__");
	
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(connectionSid);
    stringBuffer.append(TEXT_101);
    
	List<? extends INode> nodes = node.getProcess().getGeneratingNodes();
    for(INode targetNode : nodes){
		if (targetNode.getUniqueName().equals(connectionSid)) {
        	distribution = ElementParameterParser.getValue(targetNode, "__DISTRIBUTION__");
        	hadoopVersion = ElementParameterParser.getValue(targetNode, "__DB_VERSION__"); 

    		boolean useKrb = "true".equals(ElementParameterParser.getValue(targetNode, "__USE_KRB__"));
    		String kerberosPrincipal = ElementParameterParser.getValue(targetNode, "__NAMENODE_PRINCIPAL__");

			try {
				hdfsDistrib = (org.talend.hadoop.distribution.component.HDFSComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, hadoopVersion);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
				return "";
			}
    		isCustom = hdfsDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;

    		String auth = ElementParameterParser.getValue(targetNode, "__AUTHENTICATION_MODE__");
		
	      	if((!isCustom && hdfsDistrib.doSupportGroup()) || (isCustom && "UGI".equals(auth))){
		    
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    
		  	}else{
		  		boolean configureFromClassPath = "true".equals(ElementParameterParser.getValue(targetNode, "__CONFIGURATIONS_FROM_CLASSPATH__"));
		  		if(!configureFromClassPath) {
					if(!((!isCustom && hdfsDistrib.doSupportKerberos() && useKrb) || (isCustom && "KRB".equals(auth)))) {
						user = ElementParameterParser.getValue(targetNode, "__USERNAME__");
					} else {

    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(kerberosPrincipal);
    stringBuffer.append(TEXT_107);
    
					}

    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(user);
    stringBuffer.append(TEXT_110);
    
				} else {
					// If the configuration is inspected from the classpath

    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    				
				}
			  	
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(fsDefalutName);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    
		  	}
	      	break;
	    }
    }
}

    stringBuffer.append(TEXT_124);
    
	String localdir = ElementParameterParser.getValue(node, "__LOCALDIR__");	
	String remotedir = ElementParameterParser.getValue(node, "__REMOTEDIR__");
	List<Map<String, String>> files = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__FILES__");	

    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(remotedir);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_131);
    
    for (int i=0; i<files.size(); i++) {
    	Map<String, String> line = files.get(i);
		
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_135);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_136);
    stringBuffer.append( line.get("FILEMASK") );
    stringBuffer.append(TEXT_137);
    stringBuffer.append( line.get("NEWNAME") );
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_140);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_141);
    
	}
	
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_143);
    stringBuffer.append(localdir);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_146);
    stringBuffer.append(TEXT_147);
    return stringBuffer.toString();
  }
}
