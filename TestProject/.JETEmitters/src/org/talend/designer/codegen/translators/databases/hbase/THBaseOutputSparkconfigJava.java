package org.talend.designer.codegen.translators.databases.hbase;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class THBaseOutputSparkconfigJava
{
  protected static String nl;
  public static synchronized THBaseOutputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseOutputSparkconfigJava result = new THBaseOutputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_2 = ");" + NL + "        System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_3 = ");";
  protected final String TEXT_4 = NL + "    job.set(\"hbase.master.kerberos.principal\",";
  protected final String TEXT_5 = ");" + NL + "    job.set(\"hbase.regionserver.kerberos.principal\",";
  protected final String TEXT_6 = ");" + NL + "    job.set(\"hbase.security.authorization\",\"true\");" + NL + "    job.set(\"hbase.security.authentication\",\"kerberos\");" + NL + "    job.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_7 = ");" + NL + "    job.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_8 = ");    ";
  protected final String TEXT_9 = NL + "        org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_10 = ", ";
  protected final String TEXT_11 = ");";
  protected final String TEXT_12 = NL + "        com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_13 = " = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "        maprLogin_";
  protected final String TEXT_14 = ".getMapRCredentialsViaKerberos(";
  protected final String TEXT_15 = ", ";
  protected final String TEXT_16 = ");";
  protected final String TEXT_17 = NL + "        // Get MapReduce job authentication token from Hbase" + NL + "        org.apache.hadoop.hbase.security.token.TokenUtil.obtainTokenForJob(job, org.apache.hadoop.security.UserGroupInformation.getCurrentUser());";
  protected final String TEXT_18 = NL + "        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_19 = ");" + NL + "        com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_20 = " = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_21 = NL + "            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_22 = ");";
  protected final String TEXT_23 = NL + "            maprLogin_";
  protected final String TEXT_24 = ".setCheckUGI(false);";
  protected final String TEXT_25 = " " + NL + "            final String decryptedPassword_";
  protected final String TEXT_26 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_27 = ");";
  protected final String TEXT_28 = NL + "            final String decryptedPassword_";
  protected final String TEXT_29 = " = ";
  protected final String TEXT_30 = "; ";
  protected final String TEXT_31 = NL + NL + "        maprLogin_";
  protected final String TEXT_32 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ", decryptedPassword_";
  protected final String TEXT_35 = ", ";
  protected final String TEXT_36 = ");";
  protected final String TEXT_37 = NL + "    job.setOutputFormat(";
  protected final String TEXT_38 = "StructOutputFormat.class);" + NL + "    rdd_";
  protected final String TEXT_39 = ".saveAsHadoopDataset(job);";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
    INode configurationNode = null;
    String configurationNodeName = ElementParameterParser.getValue(node,"__STORAGE_CONFIGURATION__");

    for (INode pNode : node.getProcess().getNodesOfType("tHBaseConfiguration")) {
        if(configurationNodeName!=null && configurationNodeName.equals(pNode.getUniqueName())) {
            configurationNode = pNode;
        }else{
            return "";
        }
    }

// Input Connections
IConnection inMainCon = null;
java.util.List<? extends IConnection> connsIn = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
if (connsIn == null || connsIn.size() == 0 ){
    return "";
} else{
    inMainCon = connsIn.get(0);
}
final String incomingConnectionName = inMainCon.getName();
final String inConnTypeName = codeGenArgument.getRecordStructName(inMainCon);

String zookeeperUrl = ElementParameterParser.getValue(configurationNode,"__ZOOKEEPER_QUORUM__");
String zookeeperPort = ElementParameterParser.getValue(configurationNode,"__ZOOKEEPER_CLIENT_PORT__");
	boolean useKrb = "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_KRB__"));
    boolean useKeytab = "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_KEYTAB__"));
    String userPrincipal = ElementParameterParser.getValue(configurationNode, "__PRINCIPAL__");
    String keytabPath = ElementParameterParser.getValue(configurationNode, "__KEYTAB_PATH__");
    String hbaseMasterPrincipal = ElementParameterParser.getValue(configurationNode, "__HBASE_MASTER_PRINCIPAL__");
    String hbaseRegionServerPrincipal = ElementParameterParser.getValue(configurationNode, "__HBASE_REGIONSERVER_PRINCIPAL__");

    String hbaseDistribution = ElementParameterParser.getValue(configurationNode, "__DISTRIBUTION__");
    String hbaseVersion = ElementParameterParser.getValue(configurationNode, "__HBASE_VERSION__");
    
    boolean useMapRTicket = ElementParameterParser.getBooleanValue(configurationNode, "__USE_MAPRTICKET__");
    String username = ElementParameterParser.getValue(configurationNode, "__USERNAME__");
    String mapRTicketCluster = ElementParameterParser.getValue(configurationNode, "__MAPRTICKET_CLUSTER__");
    String mapRTicketDuration = ElementParameterParser.getValue(configurationNode, "__MAPRTICKET_DURATION__");
    
    boolean setMapRHomeDir = ElementParameterParser.getBooleanValue(configurationNode, "__SET_MAPR_HOME_DIR__");
    String mapRHomeDir = ElementParameterParser.getValue(configurationNode, "__MAPR_HOME_DIR__");
    
    boolean setMapRHadoopLogin = ElementParameterParser.getBooleanValue(configurationNode, "__SET_HADOOP_LOGIN__");
    String mapRHadoopLogin = ElementParameterParser.getValue(configurationNode, "__HADOOP_LOGIN__");

    org.talend.hadoop.distribution.component.HBaseComponent hbaseDistrib = null;
    try {
        hbaseDistrib = (org.talend.hadoop.distribution.component.HBaseComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(hbaseDistribution, hbaseVersion);
    } catch (java.lang.Exception e) {
        e.printStackTrace();
        return "";
    }

    boolean isCustom = hbaseDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;

    
if((isCustom || hbaseDistrib.doSupportKerberos()) && useKrb){
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_2);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_3);
    
    }

    stringBuffer.append(TEXT_4);
    stringBuffer.append(hbaseMasterPrincipal);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(hbaseRegionServerPrincipal);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(zookeeperUrl);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(zookeeperPort);
    stringBuffer.append(TEXT_8);
    
    if(useKeytab){

    stringBuffer.append(TEXT_9);
    stringBuffer.append(userPrincipal);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(keytabPath);
    stringBuffer.append(TEXT_11);
    
    }
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {

    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_16);
    
    } else {

    stringBuffer.append(TEXT_17);
    
    }
} else {
    // MapR ticket
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {
        String passwordFieldName = "__MAPRTICKET_PASSWORD__";
        
    stringBuffer.append(TEXT_18);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
        if (setMapRHadoopLogin) {
            
    stringBuffer.append(TEXT_21);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_22);
    
        } else {
            
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    
        }

        if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_27);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_36);
    
    }
}

    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(incomingConnectionName);
    stringBuffer.append(TEXT_39);
    

    return stringBuffer.toString();
  }
}
