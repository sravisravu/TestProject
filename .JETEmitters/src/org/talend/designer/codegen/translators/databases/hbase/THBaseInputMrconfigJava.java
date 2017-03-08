package org.talend.designer.codegen.translators.databases.hbase;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class THBaseInputMrconfigJava
{
  protected static String nl;
  public static synchronized THBaseInputMrconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseInputMrconfigJava result = new THBaseInputMrconfigJava();
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
  protected final String TEXT_25 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_26 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_27 = ");";
  protected final String TEXT_28 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_29 = " = ";
  protected final String TEXT_30 = "; ";
  protected final String TEXT_31 = NL + NL + "        maprLogin_";
  protected final String TEXT_32 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ", decryptedPassword_";
  protected final String TEXT_35 = ", ";
  protected final String TEXT_36 = ");";
  protected final String TEXT_37 = NL + "    " + NL + "MultipleInputs.addInputPath(job, ";
  protected final String TEXT_38 = "InputFormat.class, ChainMapper.class, \"";
  protected final String TEXT_39 = "\");" + NL + "chainMapper.setCid(\"";
  protected final String TEXT_40 = "\");";
  protected final String TEXT_41 = NL + "    chainMapper.addMapper(job, ";
  protected final String TEXT_42 = "_InputMapper.class," + NL + "        NullWritable.class, ";
  protected final String TEXT_43 = "TemporaryStruct.class," + NL + "        NullWritable.class, ";
  protected final String TEXT_44 = "Struct.class," + NL + "        true, new JobConf(false));" + NL + "" + NL + "    MultipleOutputs.setWorkDir(job," + NL + "            genTempFolderForComponent(\"";
  protected final String TEXT_45 = "\"));" + NL + "    MultipleOutputs.setKeyValue(job, \"";
  protected final String TEXT_46 = "\"," + NL + "            NullWritable.class, ";
  protected final String TEXT_47 = "Struct.class);" + NL;
  protected final String TEXT_48 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();

// Fail fast when no output connections exist.
List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
if (conns == null || conns.size() == 0)
    return "";

// Parameters
boolean useKrb = "true".equals(ElementParameterParser.getValue(node, "__USE_KRB__"));
boolean useKeytab = "true".equals(ElementParameterParser.getValue(node, "__USE_KEYTAB__"));
String userPrincipal = ElementParameterParser.getValue(node, "__PRINCIPAL__");
String keytabPath = ElementParameterParser.getValue(node, "__KEYTAB_PATH__");
String hbaseMasterPrincipal = ElementParameterParser.getValue(node, "__HBASE_MASTER_PRINCIPAL__");
String hbaseRegionServerPrincipal = ElementParameterParser.getValue(node, "__HBASE_REGIONSERVER_PRINCIPAL__");
String distribution = ElementParameterParser.getValue(node,"__DISTRIBUTION__");
String version = ElementParameterParser.getValue(node,"__HBASE_VERSION__");
String zookeeperQuorum = ElementParameterParser.getValue(node, "__ZOOKEEPER_QUORUM__");
String zookeeperClientPort = ElementParameterParser.getValue(node, "__ZOOKEEPER_CLIENT_PORT__");

boolean useMapRTicket = ElementParameterParser.getBooleanValue(node, "__USE_MAPRTICKET__");
String username = ElementParameterParser.getValue(node, "__USERNAME__");
String mapRTicketCluster = ElementParameterParser.getValue(node, "__MAPRTICKET_CLUSTER__");
String mapRTicketDuration = ElementParameterParser.getValue(node, "__MAPRTICKET_DURATION__");

boolean setMapRHomeDir = ElementParameterParser.getBooleanValue(node, "__SET_MAPR_HOME_DIR__");
String mapRHomeDir = ElementParameterParser.getValue(node, "__MAPR_HOME_DIR__");

boolean setMapRHadoopLogin = ElementParameterParser.getBooleanValue(node, "__SET_HADOOP_LOGIN__");
String mapRHadoopLogin = ElementParameterParser.getValue(node, "__HADOOP_LOGIN__");

org.talend.hadoop.distribution.component.HBaseComponent hbaseDistrib = null;
try {
    hbaseDistrib = (org.talend.hadoop.distribution.component.HBaseComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
} catch (java.lang.Exception e) {
    e.printStackTrace();
    return "";
}

boolean isCustom = hbaseDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;


// Get the output connections.
List< ? extends IConnection> rejConns = node.getOutgoingConnections("REJECT");
List< ? extends IConnection> mainConns = node.getOutgoingConnections("FLOW");
IConnection mainConn = mainConns == null || mainConns.size() == 0
        ? null : mainConns.get(0);
IConnection rejConn = rejConns == null || rejConns.size() == 0
        ? null : rejConns.get(0);

// Fail fast on bad main connection style.
if (mainConn != null && !mainConn.getLineStyle().hasConnectionCategory(
        IConnectionCategory.DATA)) {
    return "";
}

// Fail fast on no connections.
if (mainConn == null && rejConn == null) {
    return "";
}        

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
    stringBuffer.append(zookeeperQuorum);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(zookeeperClientPort);
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
    
    } else { // the following line is NOT compatible with MapR Ticket

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
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_27);
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    

// If there is a main and a reject connection, the input format will wrap the
// rows into a generic object, and the mapper will separate them into multiple
// outputs.
if (mainConn != null && rejConn != null) {
    
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(mainConn.getName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(mainConn.getName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(rejConn.getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(rejConn.getName());
    stringBuffer.append(TEXT_47);
    
}

    stringBuffer.append(TEXT_48);
    return stringBuffer.toString();
  }
}
