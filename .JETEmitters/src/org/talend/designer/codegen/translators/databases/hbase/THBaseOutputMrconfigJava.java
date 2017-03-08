package org.talend.designer.codegen.translators.databases.hbase;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class THBaseOutputMrconfigJava
{
  protected static String nl;
  public static synchronized THBaseOutputMrconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseOutputMrconfigJava result = new THBaseOutputMrconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            String ";
  protected final String TEXT_2 = " = ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL + "                Path pathToDelete_";
  protected final String TEXT_5 = " = new Path(";
  protected final String TEXT_6 = ");" + NL + "                if (fs.exists(pathToDelete_";
  protected final String TEXT_7 = ")) {" + NL + "                    fs.delete(pathToDelete_";
  protected final String TEXT_8 = ", true);" + NL + "                }";
  protected final String TEXT_9 = NL + "            MultipleOutputs.setOutputFormat(job, \"";
  protected final String TEXT_10 = "\",";
  protected final String TEXT_11 = NL + "                    ";
  protected final String TEXT_12 = "StructOutputFormat.class, ";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL + "            MultipleOutputs.setOutputFormat(job, \"";
  protected final String TEXT_15 = "\",";
  protected final String TEXT_16 = NL + "                    ";
  protected final String TEXT_17 = "StructOutputFormat.class);";
  protected final String TEXT_18 = NL + "        job.setOutputFormat(";
  protected final String TEXT_19 = "StructOutputFormat.class);";
  protected final String TEXT_20 = NL + "            ";
  protected final String TEXT_21 = "StructOutputFormat.setOutputPath(job," + NL + "                    new Path(";
  protected final String TEXT_22 = "));";
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = NL + "        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_25 = ");" + NL + "        System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_26 = ");";
  protected final String TEXT_27 = NL + "    job.set(\"hbase.master.kerberos.principal\", ";
  protected final String TEXT_28 = ");" + NL + "    job.set(\"hbase.regionserver.kerberos.principal\",";
  protected final String TEXT_29 = ");" + NL + "    job.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_30 = ");" + NL + "    job.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_31 = ");" + NL + "    job.set(\"hbase.security.authorization\",\"true\");" + NL + "    job.set(\"hbase.security.authentication\",\"kerberos\");";
  protected final String TEXT_32 = NL + "        org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ");";
  protected final String TEXT_35 = NL + "        com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_36 = " = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "        maprLogin_";
  protected final String TEXT_37 = ".getMapRCredentialsViaKerberos(";
  protected final String TEXT_38 = ", ";
  protected final String TEXT_39 = ");";
  protected final String TEXT_40 = NL + NL + "        // Get MapReduce job authentication token from Hbase" + NL + "        org.apache.hadoop.hbase.security.token.TokenUtil.obtainTokenForJob(job, org.apache.hadoop.security.UserGroupInformation.getCurrentUser());";
  protected final String TEXT_41 = NL + "        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_42 = ");" + NL + "        com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_43 = " = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_44 = NL + "            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_45 = ");";
  protected final String TEXT_46 = NL + "            maprLogin_";
  protected final String TEXT_47 = ".setCheckUGI(false);";
  protected final String TEXT_48 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_49 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_50 = ");";
  protected final String TEXT_51 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_52 = " = ";
  protected final String TEXT_53 = "; ";
  protected final String TEXT_54 = NL + NL + "        maprLogin_";
  protected final String TEXT_55 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_56 = ", ";
  protected final String TEXT_57 = ", decryptedPassword_";
  protected final String TEXT_58 = ", ";
  protected final String TEXT_59 = ");";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

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


    

/**
 * This helper is used to generate code for output components (that is,
 * components that sit at the end of a Mapper or Reducer chain and persist rows
 * to the data store).
 */
class MrRowOutputUtil {

    
    
/**
 * This helper class provides utilities for generating code when there are multiple outgoing connections, specifically
 * those that provide a main connection and a reject connection. When both are present, this class helps determine where
 * to send or retrieve the data for a given connection.
 *
 * Normally, for efficiency reasons, the main output continues along a chain of Mapper/Reducer classes and the reject
 * output is sent to a MultipleOutputs to start a new chain.
 *
 * TODO: the file mr_multioutput_utils.javajet and MultipleOutputsComponentUtils.java need to be kept synchronized.
 */
class MultiOutputUtils {

    private static final String MAIN_FLOW_NAME = "FLOW"; //$NON-NLS-1$

    private static final String REJECT_FLOW_NAME = "REJECT"; //$NON-NLS-1$

    private static final String TAVROINPUT_COMPONENT_NAME = "tAvroInput"; //$NON-NLS-1$

    private static final String TEXTRACTABSTRACTJSONFIELDS_COMPONENT_NAME = "tExtractAbstractJSONFields"; //$NON-NLS-1$

    private static final String TEXTRACTDELIMITEDFIELDS_COMPONENT_NAME = "tExtractDelimitedFields"; //$NON-NLS-1$

    private static final String TEXTRACTFULLROW_COMPONENT_NAME = "tExtractFullRow"; //$NON-NLS-1$

    private static final String TEXTRACTJSONFIELDS_COMPONENT_NAME = "tExtractJSONFields"; //$NON-NLS-1$

    private static final String TEXTRACTPOSITIONALFIELDS_COMPONENT_NAME = "tExtractPositionalFields"; //$NON-NLS-1$

    private static final String TEXTRACTREGEXFIELDS_COMPONENT_NAME = "tExtractRegexFields"; //$NON-NLS-1$

    private static final String TEXTRACTXMLFIELD_COMPONENT_NAME = "tExtractXMLField"; //$NON-NLS-1$

    private static final String TFILEINPUTDELIMITED_COMPONENT_NAME = "tFileInputDelimited"; //$NON-NLS-1$

    private static final String TFILEINPUTFULLROW_COMPONENT_NAME = "tFileInputFullRow"; //$NON-NLS-1$

    private static final String TFILEINPUTJSON_COMPONENT_NAME = "tFileInputJSON"; //$NON-NLS-1$

    private static final String TFILEINPUTPOSITIONAL_COMPONENT_NAME = "tFileInputPositional"; //$NON-NLS-1$

    private static final String TFILEINPUTREGEX_COMPONENT_NAME = "tFileInputRegex"; //$NON-NLS-1$

    private static final String TFILEINPUTXML_COMPONENT_NAME = "tFileInputXML"; //$NON-NLS-1$

    private static final String THBASEINPUT_COMPONENT_NAME = "tHBaseInput"; //$NON-NLS-1$

    private static final String THDFSINPUT_COMPONENT_NAME = "tHDFSInput"; //$NON-NLS-1$

    private static final String TJDBCINPUT_COMPONENT_NAME = "tJDBCInput"; //$NON-NLS-1$

    private static final String TVIRTUALINPUTCOMPONENT_NAME = "tMRInput"; //$NON-NLS-1$

    /**
     * Check if the source of a connection may be a multipleOutput.
     *
     * @param connection current connection
     * @return true if the component may have a reject flow.
     */
    public boolean canBeAMultipleOutput(IConnection connection) {
        if ((connection == null) || (connection.getSource() == null) || (connection.getSource().getComponent() == null)) {
            return false;
        } else {
            return isAMultipleOutputComponent(connection.getSource().getComponent().getName());
        }
    }

    /**
     * Check if a the name of a component is in the list of the multipleoutputs component.
     *
     * These component may have a reject flow for processing error. But we need to handle them just like they don't have
     * one.
     *
     * @param name of the component
     * @return true if the component may have a reject flow.
     */
    public boolean isAMultipleOutputComponent(String name) {
        return name.equals(TAVROINPUT_COMPONENT_NAME) || name.equals(TEXTRACTABSTRACTJSONFIELDS_COMPONENT_NAME)
                || name.equals(TEXTRACTDELIMITEDFIELDS_COMPONENT_NAME) || name.equals(TEXTRACTFULLROW_COMPONENT_NAME)
                || name.equals(TEXTRACTJSONFIELDS_COMPONENT_NAME) || name.equals(TEXTRACTPOSITIONALFIELDS_COMPONENT_NAME)
                || name.equals(TEXTRACTREGEXFIELDS_COMPONENT_NAME) || name.equals(TEXTRACTXMLFIELD_COMPONENT_NAME)
                || name.equals(TFILEINPUTDELIMITED_COMPONENT_NAME) || name.equals(TFILEINPUTFULLROW_COMPONENT_NAME)
                || name.equals(TFILEINPUTJSON_COMPONENT_NAME) || name.equals(TFILEINPUTPOSITIONAL_COMPONENT_NAME)
                || name.equals(TFILEINPUTREGEX_COMPONENT_NAME) || name.equals(TFILEINPUTXML_COMPONENT_NAME)
                || name.equals(THBASEINPUT_COMPONENT_NAME) || name.equals(THDFSINPUT_COMPONENT_NAME)
                || name.equals(TJDBCINPUT_COMPONENT_NAME) || name.equals(TVIRTUALINPUTCOMPONENT_NAME);
    }

    /**
     * Check if the current connection is a reject flow of a multiple output component.
     *
     * @param connection the current connection
     * @return true if the current connection is a reject flow
     */
    public boolean isRejectFlowOnMultipleOutput(IConnection connection) {
        if ((connection == null) || (connection.getSource() == null) || (connection.getSource().getComponent() == null)) {
            return false;
        } else {
            return isRejectFlowOnMultipleOutput(connection.getSource().getComponent().getName(), connection.getConnectorName());
        }
    }

    /**
     * Check if the current connection is a reject flow of a multiple output component.
     *
     * @param name of the component
     * @param connectorName name of the link
     * @return true if the current connection is a reject flow
     */
    public boolean isRejectFlowOnMultipleOutput(String name, String connectorName) {
        return isAMultipleOutputComponent(name) && connectorName.equals(REJECT_FLOW_NAME);
    }

    /**
     * Check if the current connection is a main flow of a multiple output component.
     *
     * @param connection the current connection
     * @return true if the current connection is a main flow
     */
    public boolean isMainFlowOnMultipleOutput(IConnection connection) {
        if ((connection == null) || (connection.getSource() == null) || (connection.getSource().getComponent() == null)) {
            return false;
        } else {
            return isMainFlowOnMultipleOutput(connection.getSource().getComponent().getName(), connection.getConnectorName());
        }
    }

    /**
     * Check if the current connection is a main flow of a multiple output component.
     *
     * @param name of the component
     * @param connectorName name of the link
     * @return true id the current connection is a main flow
     */
    public boolean isMainFlowOnMultipleOutput(String name, String connectorName) {
        return isAMultipleOutputComponent(name) && connectorName.equals(MAIN_FLOW_NAME);
    }

}


    
    private final MultiOutputUtils moUtil = new MultiOutputUtils();

    /**
     *
     */
    public void generateOutputMrConfig(String cid, String folder,
            boolean deleteExisting, java.util.List<? extends IConnection> inConns) {

        if (inConns == null || inConns.size() == 0)
            return;
        IConnection inConn = inConns.get(0);

        // Generate a variable for the folder name if present.
        String codeVarOutputPath = null;
        if (null != folder) {
            codeVarOutputPath = "outputPath_" + cid;
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(codeVarOutputPath);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_3);
    
            if (deleteExisting) {
                
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(codeVarOutputPath);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
            }
        }

        // This is guaranteed to contain at least inConn.
        java.util.List<? extends IConnection> srcOutConns = inConn.getSource().getOutgoingConnections(EConnectionType.FLOW_MAIN);

        // The incoming data is on the normal mapper chain when it is the sole
        // output of the preceding component, OR when it is the main output of
        // a standard main/reject component.
        if (srcOutConns.size() == 1 || (moUtil.isMainFlowOnMultipleOutput(inConn))) {
            generateOutputOnChain(cid, codeVarOutputPath);
        } else {
            // In all other conditions, the incoming data comes through a
            // MultipleOutput object.
            generateOutputFromMultiple(inConn.getName(), cid, codeVarOutputPath);
        }
    }

    /**
     * Generates code that uses data from a MultipleOutputs object.
     */
    public void generateOutputFromMultiple(String moTag, String cid,
            String codeVarOutputPath) {
        if (codeVarOutputPath != null) {
            
    stringBuffer.append(TEXT_9);
    stringBuffer.append(moTag);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(codeVarOutputPath);
    stringBuffer.append(TEXT_13);
    
        } else {
            
    stringBuffer.append(TEXT_14);
    stringBuffer.append(moTag);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
        }
    }

    /**
     * Generates code that uses data from the mapper chain.
     */
    public void generateOutputOnChain(String cid, String codeVarOutputPath) {
        
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    
        if (codeVarOutputPath != null) {
            
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(codeVarOutputPath);
    stringBuffer.append(TEXT_22);
    
        }
    }
}


    stringBuffer.append(TEXT_23);
    
if((isCustom || hbaseDistrib.doSupportKerberos()) && useKrb){
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {
        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_26);
    
    }
    
    stringBuffer.append(TEXT_27);
    stringBuffer.append(hbaseMasterPrincipal);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(hbaseRegionServerPrincipal);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(zookeeperQuorum);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(zookeeperClientPort);
    stringBuffer.append(TEXT_31);
    
    if(useKeytab){

    stringBuffer.append(TEXT_32);
    stringBuffer.append(userPrincipal);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(keytabPath);
    stringBuffer.append(TEXT_34);
    
    }
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {

    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_39);
    
    } else { // the following line is NOT compatible with MapR Ticket

    stringBuffer.append(TEXT_40);
    
    }
} else {
    // MapR ticket
    if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {
        String passwordFieldName = "__MAPRTICKET_PASSWORD__";
        
    stringBuffer.append(TEXT_41);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    
        if (setMapRHadoopLogin) {
            
    stringBuffer.append(TEXT_44);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_45);
    
        } else {
            
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    
        }
        
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_50);
    } else {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_59);
    
    }
}

new MrRowOutputUtil().generateOutputMrConfig(cid, null, false,
        node.getIncomingConnections(EConnectionType.FLOW_MAIN));

    return stringBuffer.toString();
  }
}
