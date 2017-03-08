package org.talend.designer.codegen.translators.mapreduce.output;

import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TFileOutputPositionalMrconfigJava
{
  protected static String nl;
  public static synchronized TFileOutputPositionalMrconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileOutputPositionalMrconfigJava result = new TFileOutputPositionalMrconfigJava();
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
  protected final String TEXT_23 = NL + "            final String clouderaManagerPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_24 = ");";
  protected final String TEXT_25 = NL + "            final String clouderaManagerPassword = ";
  protected final String TEXT_26 = ";";
  protected final String TEXT_27 = NL + "        this.lineageCreator = new org.talend.lineage.cloudera.LineageCreator(";
  protected final String TEXT_28 = NL + "                ";
  protected final String TEXT_29 = ",";
  protected final String TEXT_30 = NL + "                ";
  protected final String TEXT_31 = ",";
  protected final String TEXT_32 = NL + "                ";
  protected final String TEXT_33 = ",";
  protected final String TEXT_34 = NL + "                ";
  protected final String TEXT_35 = "," + NL + "                clouderaManagerPassword," + NL + "                jobName + \"_\" + jobVersion.replace(\".\", \"_\")," + NL + "                projectName,";
  protected final String TEXT_36 = NL + "                ";
  protected final String TEXT_37 = ",";
  protected final String TEXT_38 = NL + "                ";
  protected final String TEXT_39 = ",";
  protected final String TEXT_40 = NL + "                ";
  protected final String TEXT_41 = ");";
  protected final String TEXT_42 = NL + "        this.lineageCreator = new org.talend.lineage.atlas.AtlasLineageCreator(";
  protected final String TEXT_43 = ");" + NL + "" + NL + "        java.util.Map<String, Object> lineageCreatorJobMetadata = new java.util.HashMap<String, Object>();" + NL + "        lineageCreatorJobMetadata.put(\"name\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"description\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"purpose\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"author\", System.getProperty(\"user.name\"));" + NL + "        lineageCreatorJobMetadata.put(\"version\", jobVersion);" + NL + "        lineageCreatorJobMetadata.put(\"jobType\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"framework\", \"Talend BigData\");" + NL + "        lineageCreatorJobMetadata.put(\"status\", \"FINISHED\");" + NL + "        lineageCreatorJobMetadata.put(\"creationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"lastModificationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"startTime\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"endTime\", System.currentTimeMillis());" + NL + "" + NL + "        this.lineageCreator.addJobInfo(lineageCreatorJobMetadata);";
  protected final String TEXT_44 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_45 = " = new java.util.HashMap<>();";
  protected final String TEXT_46 = NL + "            columns_";
  protected final String TEXT_47 = ".put(\"";
  protected final String TEXT_48 = "\", \"";
  protected final String TEXT_49 = "\");";
  protected final String TEXT_50 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_51 = " = new java.util.HashMap<>();";
  protected final String TEXT_52 = NL + "                        columns_";
  protected final String TEXT_53 = ".put(\"";
  protected final String TEXT_54 = "\", \"";
  protected final String TEXT_55 = "\");";
  protected final String TEXT_56 = NL + "                    columns_";
  protected final String TEXT_57 = ".put(\"";
  protected final String TEXT_58 = "\", \"";
  protected final String TEXT_59 = "\");";
  protected final String TEXT_60 = NL + "        lineageCreator.addDataset(columns_";
  protected final String TEXT_61 = ", \"";
  protected final String TEXT_62 = "\", ";
  protected final String TEXT_63 = ", \"";
  protected final String TEXT_64 = "\");";
  protected final String TEXT_65 = NL + "        java.util.List<String> inputNodes_";
  protected final String TEXT_66 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_67 = NL + "                inputNodes_";
  protected final String TEXT_68 = ".add(\"";
  protected final String TEXT_69 = "\");";
  protected final String TEXT_70 = NL + "        java.util.List<String> outputNodes_";
  protected final String TEXT_71 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_72 = NL + "                outputNodes_";
  protected final String TEXT_73 = ".add(\"";
  protected final String TEXT_74 = "\");";
  protected final String TEXT_75 = NL + "        this.lineageCreator.addNodeToLineage(\"";
  protected final String TEXT_76 = "\", columns_";
  protected final String TEXT_77 = ", inputNodes_";
  protected final String TEXT_78 = ", outputNodes_";
  protected final String TEXT_79 = ", new java.util.HashMap<String, Object>());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    

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


    
new MrRowOutputUtil().generateOutputMrConfig(cid,
        ElementParameterParser.getValue(node,"__FOLDER__"),
        "OVERWRITE".equals(ElementParameterParser.getValue(node, "__FILE_ACTION__")),
        node.getIncomingConnections(EConnectionType.FLOW_MAIN));

    
class TalendLineageAPI{

    /**
    * Find the configuration node given the current node
    * 
    * @param node
    **/
    public INode findConfigurationNode(INode node){
        INode configurationNode = null;
        for (INode pNode : node.getProcess().getNodesOfType("tMRConfiguration")) {
            configurationNode = pNode;
            break;
        }
        for (INode pNode : node.getProcess().getNodesOfType("tSparkConfiguration")) {
            // spark compatibility, will not be run on Map Reduce
            configurationNode = pNode;
            break;
        }
        return configurationNode;
    }

    /**
     * Does the job require lineage generation
     */
    public boolean doRequireLineageSupport(INode node){
        INode configurationNode = findConfigurationNode(node);
        if (configurationNode != null) {
            Boolean useClouderaNavigator = ElementParameterParser.getBooleanValue(configurationNode,"__USE_CLOUDERA_NAVIGATOR__");
            Boolean useAtlas = ElementParameterParser.getBooleanValue(configurationNode,"__USE_ATLAS__");
            return (useClouderaNavigator && doRequireClouderaNavigatorSupport(configurationNode)) || (useAtlas && doRequireAtlasSupport(configurationNode));
        }
        return false;
    }

    /**
     * Does the job require Cloudera Navigator lineage generation
     */
    public boolean doRequireClouderaNavigatorSupport(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        Boolean useClouderaNavigator = ElementParameterParser.getBooleanValue(configurationNode,"__USE_CLOUDERA_NAVIGATOR__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
            version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return useClouderaNavigator && currentDistribution.doSupportClouderaNavigator();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Does the job require Atlas lineage generation
     */
    public boolean doRequireAtlasSupport(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        Boolean useAtlas = ElementParameterParser.getBooleanValue(configurationNode,"__USE_ATLAS__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
           version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return useAtlas && currentDistribution.doSupportAtlas();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Get Cloudera Navigator API version
     */
    public int getClouderaNavigatorAPIVersion(INode configurationNode){
        String distribution = ElementParameterParser.getValue(configurationNode,"__DISTRIBUTION__");
        String version = ElementParameterParser.getValue(configurationNode,"__MR_VERSION__");
        // spark compatibility
        if ((version == null) || ("".equals(version))) { 
           version = ElementParameterParser.getValue(configurationNode,"__SPARK_VERSION__");
        }
        try {
            org.talend.hadoop.distribution.component.MRComponent currentDistribution = (org.talend.hadoop.distribution.component.MRComponent)org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
            return currentDistribution.getClouderaNavigatorAPIVersion();
        } catch (Exception e) {
            return 8;
        }
    }

    /**
    *
    * generates a Cloudera Navigator lineage creator
    *
    */
    public void generateClouderaNavigatorLinageCreator(INode configurationNode) {
        String usernameCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_USERNAME__");
        String urlCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_URL__");
        String urlMetadataCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_METADATA_URL__");
        String clientUrlCN = ElementParameterParser.getValue(configurationNode,"__CLOUDERA_NAVIGATOR_CLIENT_URL__");
        Boolean useAutocommit = ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_AUTOCOMMIT__");
        Boolean disableSslValidation = ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_DISABLE_SSL_VALIDATION__");
        int apiVersion = getClouderaNavigatorAPIVersion(configurationNode);

        if (ElementParameterParser.canEncrypt(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__")) {
            
    stringBuffer.append(TEXT_23);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_24);
    
        } else {
            
    stringBuffer.append(TEXT_25);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_26);
    
        }
        
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(clientUrlCN);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(urlCN);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(urlMetadataCN);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(usernameCN);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(useAutocommit);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(disableSslValidation);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(apiVersion);
    stringBuffer.append(TEXT_41);
    
    }

    /**
    *
    * generates an Atlas lineage creator
    *
    */
    public void generateAtlasLinageCreator(INode configurationNode) {
        String atlasURL = ElementParameterParser.getValue(configurationNode,"__ATLAS_URL__");
        
    stringBuffer.append(TEXT_42);
    stringBuffer.append(atlasURL);
    stringBuffer.append(TEXT_43);
    
    }

    /**
    * returns the dieOnError value
    */
    public Boolean getDieOnError(INode configurationNode){
        if(doRequireClouderaNavigatorSupport(configurationNode)){
            return ElementParameterParser.getBooleanValue(configurationNode,"__CLOUDERA_NAVIGATOR_DIE_ON_ERROR__");
        } else if (doRequireAtlasSupport(configurationNode)){
            return ElementParameterParser.getBooleanValue(configurationNode,"__ATLAS_DIE_ON_ERROR__");
        }
        return false;
    }

    /**
     * Generate a Map containing the columns information.
     * The map contain the column name as the key, and the column type as the value.
     */
    public void generateColumnList(IMetadataTable metadataTable, String cid) {
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
        for (IMetadataColumn column: metadataTable.getListColumns()) {
            
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_49);
    
        }
    }

    /**
     * Generate a Map containing the columns information.
     * The map contain the column name as the key, and the column type as the value.
     * This function will generate the full output mapping of a component, in order to display any output field.
     * 
     * If the component contain output link, the map will be  generate from these links,
     * otherwise the metadata of the component will be used.
     */
    public void generateColumnListFromOutputLink(INode node, String cid) {
        
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
        if ((node.getOutgoingConnections() != null)
            && (node.getOutgoingConnections().size() > 0)) {
            for (IConnection connection: node.getOutgoingConnections()) {
                if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                    for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
                        
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_55);
    
                    }
                }
            }
        } else  {
            for (IMetadataTable metadataTable: node.getMetadataList()) {
                for (IMetadataColumn column: metadataTable.getListColumns()) {
                    
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_59);
    
                }
            }
        }
    }

    /**
     * Generate the code to call the method addDataset of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     * @param componentName name of the graphical component
     * @param folderPath The path to the folder containing data into HDFS
     * @param folderType The type of the folder, must be defined into com.cloudera.nav.sdk.model.entities.FileFormat
     */
    public void addDataset(String cid, String componentName, String folderPath, String folderType) {
        
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(componentName);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(folderPath);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(folderType);
    stringBuffer.append(TEXT_64);
    
    }

    /**
     * Generate list of input nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateInputNodeList(INode node) {
        
    stringBuffer.append(TEXT_65);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_66);
    
        java.util.List<IConnection> inputs = (java.util.List<IConnection>)node.getIncomingConnections();
        for (IConnection connection: inputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_67);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(connection.getSource().getUniqueName());
    stringBuffer.append(TEXT_69);
    
            }
        }
    }

    /**
     * Generate list of output nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateOutputNodeList(INode node) {
        
    stringBuffer.append(TEXT_70);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_71);
    
        java.util.List<IConnection> outputs = (java.util.List<IConnection>)node.getOutgoingConnections();
        for (IConnection connection: outputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_72);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(connection.getTarget().getUniqueName());
    stringBuffer.append(TEXT_74);
    
            }
        }
    }

    /**
     * Generate the code to call the method addNodeToLineage of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     */
    public void addNodeToLineage(String cid) {
        
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    
    }

}

    
TalendLineageAPI talendLineageAPI = new TalendLineageAPI();
if (talendLineageAPI.doRequireLineageSupport(node)) {
    if (node.getIncomingConnections() != null
            && node.getIncomingConnections().size() > 0) {
        IConnection inputConnection = node.getIncomingConnections().get(0);
        String structureName = inputConnection.getName() + "Struct";
        String originalName = org.talend.core.model.utils.NodeUtil.getVirtualNode(node).getUniqueName();

        talendLineageAPI.generateColumnList(inputConnection.getMetadataTable(), cid);
        talendLineageAPI.addDataset(cid, originalName, "outputPath_" + cid, "CSV");
    }
}

    return stringBuffer.toString();
  }
}
