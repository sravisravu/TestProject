package org.talend.designer.codegen.translators.file.output;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TAvroOutputSparkconfigJava
{
  protected static String nl;
  public static synchronized TAvroOutputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TAvroOutputSparkconfigJava result = new TAvroOutputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "            java.net.URI currentURI_";
  protected final String TEXT_3 = "_config = FileSystem.getDefaultUri(ctx.hadoopConfiguration());" + NL + "            FileSystem.setDefaultUri(ctx.hadoopConfiguration(), new java.net.URI(";
  protected final String TEXT_4 = "));" + NL + "            fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_5 = NL + "            Path pathToDelete_";
  protected final String TEXT_6 = " = new Path(";
  protected final String TEXT_7 = ");" + NL + "            if (fs.exists(pathToDelete_";
  protected final String TEXT_8 = ")) {" + NL + "                fs.delete(pathToDelete_";
  protected final String TEXT_9 = ", true);" + NL + "            }";
  protected final String TEXT_10 = NL + "        rdd_";
  protected final String TEXT_11 = ".saveAsHadoopFile(";
  protected final String TEXT_12 = ", NullWritable.class, ";
  protected final String TEXT_13 = ".class, ";
  protected final String TEXT_14 = "StructOutputFormat.class);";
  protected final String TEXT_15 = NL + "            FileSystem.setDefaultUri(ctx.hadoopConfiguration(), currentURI_";
  protected final String TEXT_16 = "_config);" + NL + "            fs = FileSystem.get(ctx.hadoopConfiguration());";
  protected final String TEXT_17 = NL + "            final String clouderaManagerPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "            final String clouderaManagerPassword = ";
  protected final String TEXT_20 = ";";
  protected final String TEXT_21 = NL + "        this.lineageCreator = new org.talend.lineage.cloudera.LineageCreator(";
  protected final String TEXT_22 = NL + "                ";
  protected final String TEXT_23 = ",";
  protected final String TEXT_24 = NL + "                ";
  protected final String TEXT_25 = ",";
  protected final String TEXT_26 = NL + "                ";
  protected final String TEXT_27 = ",";
  protected final String TEXT_28 = NL + "                ";
  protected final String TEXT_29 = "," + NL + "                clouderaManagerPassword," + NL + "                jobName + \"_\" + jobVersion.replace(\".\", \"_\")," + NL + "                projectName,";
  protected final String TEXT_30 = NL + "                ";
  protected final String TEXT_31 = ",";
  protected final String TEXT_32 = NL + "                ";
  protected final String TEXT_33 = ",";
  protected final String TEXT_34 = NL + "                ";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "        this.lineageCreator = new org.talend.lineage.atlas.AtlasLineageCreator(";
  protected final String TEXT_37 = ");" + NL + "" + NL + "        java.util.Map<String, Object> lineageCreatorJobMetadata = new java.util.HashMap<String, Object>();" + NL + "        lineageCreatorJobMetadata.put(\"name\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"description\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"purpose\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"author\", System.getProperty(\"user.name\"));" + NL + "        lineageCreatorJobMetadata.put(\"version\", jobVersion);" + NL + "        lineageCreatorJobMetadata.put(\"jobType\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"framework\", \"Talend BigData\");" + NL + "        lineageCreatorJobMetadata.put(\"status\", \"FINISHED\");" + NL + "        lineageCreatorJobMetadata.put(\"creationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"lastModificationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"startTime\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"endTime\", System.currentTimeMillis());" + NL + "" + NL + "        this.lineageCreator.addJobInfo(lineageCreatorJobMetadata);";
  protected final String TEXT_38 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_39 = " = new java.util.HashMap<>();";
  protected final String TEXT_40 = NL + "            columns_";
  protected final String TEXT_41 = ".put(\"";
  protected final String TEXT_42 = "\", \"";
  protected final String TEXT_43 = "\");";
  protected final String TEXT_44 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_45 = " = new java.util.HashMap<>();";
  protected final String TEXT_46 = NL + "                        columns_";
  protected final String TEXT_47 = ".put(\"";
  protected final String TEXT_48 = "\", \"";
  protected final String TEXT_49 = "\");";
  protected final String TEXT_50 = NL + "                    columns_";
  protected final String TEXT_51 = ".put(\"";
  protected final String TEXT_52 = "\", \"";
  protected final String TEXT_53 = "\");";
  protected final String TEXT_54 = NL + "        lineageCreator.addDataset(columns_";
  protected final String TEXT_55 = ", \"";
  protected final String TEXT_56 = "\", ";
  protected final String TEXT_57 = ", \"";
  protected final String TEXT_58 = "\");";
  protected final String TEXT_59 = NL + "        java.util.List<String> inputNodes_";
  protected final String TEXT_60 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_61 = NL + "                inputNodes_";
  protected final String TEXT_62 = ".add(\"";
  protected final String TEXT_63 = "\");";
  protected final String TEXT_64 = NL + "        java.util.List<String> outputNodes_";
  protected final String TEXT_65 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_66 = NL + "                outputNodes_";
  protected final String TEXT_67 = ".add(\"";
  protected final String TEXT_68 = "\");";
  protected final String TEXT_69 = NL + "        this.lineageCreator.addNodeToLineage(\"";
  protected final String TEXT_70 = "\", columns_";
  protected final String TEXT_71 = ", inputNodes_";
  protected final String TEXT_72 = ", outputNodes_";
  protected final String TEXT_73 = ", new java.util.HashMap<String, Object>());";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();

if(metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        String folder = ElementParameterParser.getValue(node, "__FOLDER__");
        boolean deleteExisting = "OVERWRITE".equals(ElementParameterParser.getValue(node, "__FILE_ACTION__"));

        String uriPrefix = "\"\"";
        // Used for Spark only for now.
        boolean useConfigurationComponent = "true".equals(ElementParameterParser.getValue(node, "__DEFINE_STORAGE_CONFIGURATION__"));
        if(useConfigurationComponent) {
            uriPrefix = org.talend.designer.spark.generator.storage.SparkStorageUtils.getURIPrefix(node);
            folder = uriPrefix + " + " + folder;
        }

        String inConnName = "";
        String inConnTypeName = "";
        if ((node.getIncomingConnections() != null) && (node.getIncomingConnections().size() > 0)) {
            IConnection connection = node.getIncomingConnections().get(0);
            inConnName = connection.getName();
            inConnTypeName = codeGenArgument.getRecordStructName(connection);
        }

        if(!"\"\"".equals(uriPrefix)) {
            
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(uriPrefix);
    stringBuffer.append(TEXT_4);
    
        }

        if (deleteExisting) {
            
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    
        }
        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    

        if(!"\"\"".equals(uriPrefix)) {
        
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    
        }
        
    
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
            
    stringBuffer.append(TEXT_17);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_18);
    
        } else {
            
    stringBuffer.append(TEXT_19);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_20);
    
        }
        
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(clientUrlCN);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(urlCN);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(urlMetadataCN);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(usernameCN);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(useAutocommit);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(disableSslValidation);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(apiVersion);
    stringBuffer.append(TEXT_35);
    
    }

    /**
    *
    * generates an Atlas lineage creator
    *
    */
    public void generateAtlasLinageCreator(INode configurationNode) {
        String atlasURL = ElementParameterParser.getValue(configurationNode,"__ATLAS_URL__");
        
    stringBuffer.append(TEXT_36);
    stringBuffer.append(atlasURL);
    stringBuffer.append(TEXT_37);
    
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
        
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    
        for (IMetadataColumn column: metadataTable.getListColumns()) {
            
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_43);
    
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
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
        if ((node.getOutgoingConnections() != null)
            && (node.getOutgoingConnections().size() > 0)) {
            for (IConnection connection: node.getOutgoingConnections()) {
                if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                    for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
                        
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_49);
    
                    }
                }
            }
        } else  {
            for (IMetadataTable metadataTable: node.getMetadataList()) {
                for (IMetadataColumn column: metadataTable.getListColumns()) {
                    
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_53);
    
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
        
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(componentName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(folderPath);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(folderType);
    stringBuffer.append(TEXT_58);
    
    }

    /**
     * Generate list of input nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateInputNodeList(INode node) {
        
    stringBuffer.append(TEXT_59);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_60);
    
        java.util.List<IConnection> inputs = (java.util.List<IConnection>)node.getIncomingConnections();
        for (IConnection connection: inputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_61);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(connection.getSource().getUniqueName());
    stringBuffer.append(TEXT_63);
    
            }
        }
    }

    /**
     * Generate list of output nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateOutputNodeList(INode node) {
        
    stringBuffer.append(TEXT_64);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_65);
    
        java.util.List<IConnection> outputs = (java.util.List<IConnection>)node.getOutgoingConnections();
        for (IConnection connection: outputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_66);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(connection.getTarget().getUniqueName());
    stringBuffer.append(TEXT_68);
    
            }
        }
    }

    /**
     * Generate the code to call the method addNodeToLineage of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     */
    public void addNodeToLineage(String cid) {
        
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    
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
                talendLineageAPI.addDataset(cid, originalName, folder, "AVRO");
            }
        }
    }
}

    return stringBuffer.toString();
  }
}
