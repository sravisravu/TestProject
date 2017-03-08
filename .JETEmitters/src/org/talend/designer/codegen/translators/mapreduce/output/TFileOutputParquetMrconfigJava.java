package org.talend.designer.codegen.translators.mapreduce.output;

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

public class TFileOutputParquetMrconfigJava
{
  protected static String nl;
  public static synchronized TFileOutputParquetMrconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileOutputParquetMrconfigJava result = new TFileOutputParquetMrconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "                String outputPath_";
  protected final String TEXT_2 = " = ";
  protected final String TEXT_3 = ";";
  protected final String TEXT_4 = NL + "                    job.set(parquet.hadoop.ParquetOutputFormat.WRITE_SUPPORT_CLASS, parquet.hadoop.example.GroupWriteSupport.class.getCanonicalName());" + NL + "                    java.util.List<parquet.schema.Type> types_";
  protected final String TEXT_5 = " = new java.util.ArrayList<parquet.schema.Type>();";
  protected final String TEXT_6 = NL + "                            types_";
  protected final String TEXT_7 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT32, \"";
  protected final String TEXT_8 = "\"));";
  protected final String TEXT_9 = NL + "                            types_";
  protected final String TEXT_10 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT64, \"";
  protected final String TEXT_11 = "\"));";
  protected final String TEXT_12 = NL + "                            types_";
  protected final String TEXT_13 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.DOUBLE, \"";
  protected final String TEXT_14 = "\"));";
  protected final String TEXT_15 = NL + "                            types_";
  protected final String TEXT_16 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.FLOAT, \"";
  protected final String TEXT_17 = "\"));";
  protected final String TEXT_18 = NL + "                            types_";
  protected final String TEXT_19 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BOOLEAN, \"";
  protected final String TEXT_20 = "\"));";
  protected final String TEXT_21 = NL + "                            types_";
  protected final String TEXT_22 = ".add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BINARY, \"";
  protected final String TEXT_23 = "\"));";
  protected final String TEXT_24 = NL + "                    parquet.schema.MessageType schema_";
  protected final String TEXT_25 = " = new parquet.schema.MessageType(\"";
  protected final String TEXT_26 = "\", types_";
  protected final String TEXT_27 = ");" + NL + "                    parquet.hadoop.example.GroupWriteSupport.setSchema(schema_";
  protected final String TEXT_28 = ", job);" + NL + "" + NL + "                    job.setOutputFormat(TalendParquetOutputFormat_";
  protected final String TEXT_29 = ".class);" + NL + "                    TalendParquetOutputFormat_";
  protected final String TEXT_30 = ".setOutputPath(job, new Path(outputPath_";
  protected final String TEXT_31 = "));" + NL + "                    TalendParquetOutputFormat_";
  protected final String TEXT_32 = ".setCompression(job, parquet.hadoop.metadata.CompressionCodecName.";
  protected final String TEXT_33 = ");";
  protected final String TEXT_34 = NL + "                        Path pathToDelete_";
  protected final String TEXT_35 = " = new Path(";
  protected final String TEXT_36 = ");" + NL + "                        if (fs.exists(pathToDelete_";
  protected final String TEXT_37 = ")) {" + NL + "                            fs.delete(pathToDelete_";
  protected final String TEXT_38 = ", true);" + NL + "                        }";
  protected final String TEXT_39 = NL;
  protected final String TEXT_40 = NL + "            final String clouderaManagerPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_41 = ");";
  protected final String TEXT_42 = NL + "            final String clouderaManagerPassword = ";
  protected final String TEXT_43 = ";";
  protected final String TEXT_44 = NL + "        this.lineageCreator = new org.talend.lineage.cloudera.LineageCreator(";
  protected final String TEXT_45 = NL + "                ";
  protected final String TEXT_46 = ",";
  protected final String TEXT_47 = NL + "                ";
  protected final String TEXT_48 = ",";
  protected final String TEXT_49 = NL + "                ";
  protected final String TEXT_50 = ",";
  protected final String TEXT_51 = NL + "                ";
  protected final String TEXT_52 = "," + NL + "                clouderaManagerPassword," + NL + "                jobName + \"_\" + jobVersion.replace(\".\", \"_\")," + NL + "                projectName,";
  protected final String TEXT_53 = NL + "                ";
  protected final String TEXT_54 = ",";
  protected final String TEXT_55 = NL + "                ";
  protected final String TEXT_56 = ",";
  protected final String TEXT_57 = NL + "                ";
  protected final String TEXT_58 = ");";
  protected final String TEXT_59 = NL + "        this.lineageCreator = new org.talend.lineage.atlas.AtlasLineageCreator(";
  protected final String TEXT_60 = ");" + NL + "" + NL + "        java.util.Map<String, Object> lineageCreatorJobMetadata = new java.util.HashMap<String, Object>();" + NL + "        lineageCreatorJobMetadata.put(\"name\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"description\", jobName);" + NL + "        lineageCreatorJobMetadata.put(\"purpose\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"author\", System.getProperty(\"user.name\"));" + NL + "        lineageCreatorJobMetadata.put(\"version\", jobVersion);" + NL + "        lineageCreatorJobMetadata.put(\"jobType\", \"Talend BigData Job\");" + NL + "        lineageCreatorJobMetadata.put(\"framework\", \"Talend BigData\");" + NL + "        lineageCreatorJobMetadata.put(\"status\", \"FINISHED\");" + NL + "        lineageCreatorJobMetadata.put(\"creationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"lastModificationDate\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"startTime\", System.currentTimeMillis());" + NL + "        lineageCreatorJobMetadata.put(\"endTime\", System.currentTimeMillis());" + NL + "" + NL + "        this.lineageCreator.addJobInfo(lineageCreatorJobMetadata);";
  protected final String TEXT_61 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_62 = " = new java.util.HashMap<>();";
  protected final String TEXT_63 = NL + "            columns_";
  protected final String TEXT_64 = ".put(\"";
  protected final String TEXT_65 = "\", \"";
  protected final String TEXT_66 = "\");";
  protected final String TEXT_67 = NL + "        java.util.Map<String, String> columns_";
  protected final String TEXT_68 = " = new java.util.HashMap<>();";
  protected final String TEXT_69 = NL + "                        columns_";
  protected final String TEXT_70 = ".put(\"";
  protected final String TEXT_71 = "\", \"";
  protected final String TEXT_72 = "\");";
  protected final String TEXT_73 = NL + "                    columns_";
  protected final String TEXT_74 = ".put(\"";
  protected final String TEXT_75 = "\", \"";
  protected final String TEXT_76 = "\");";
  protected final String TEXT_77 = NL + "        lineageCreator.addDataset(columns_";
  protected final String TEXT_78 = ", \"";
  protected final String TEXT_79 = "\", ";
  protected final String TEXT_80 = ", \"";
  protected final String TEXT_81 = "\");";
  protected final String TEXT_82 = NL + "        java.util.List<String> inputNodes_";
  protected final String TEXT_83 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_84 = NL + "                inputNodes_";
  protected final String TEXT_85 = ".add(\"";
  protected final String TEXT_86 = "\");";
  protected final String TEXT_87 = NL + "        java.util.List<String> outputNodes_";
  protected final String TEXT_88 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_89 = NL + "                outputNodes_";
  protected final String TEXT_90 = ".add(\"";
  protected final String TEXT_91 = "\");";
  protected final String TEXT_92 = NL + "        this.lineageCreator.addNodeToLineage(\"";
  protected final String TEXT_93 = "\", columns_";
  protected final String TEXT_94 = ", inputNodes_";
  protected final String TEXT_95 = ", outputNodes_";
  protected final String TEXT_96 = ", new java.util.HashMap<String, Object>());";
  protected final String TEXT_97 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

// Note: because tFileOutputParquet is always preceded by a Parquet mapper,
// it is never directly based on a direct REJECT flow from a component with
// multiple connections (i.e., its data is never coming from a MultipleOutputs
// object)

BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

INode node = (INode)codeGenArgument.getArgument();

String cid = node.getUniqueName();

List<IMetadataTable> metadatas = node.getMetadataList();

if(metadatas!=null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){

        String folder = ElementParameterParser.getValue(node,"__FILENAME__");
        String fileAction = ElementParameterParser.getValue(node, "__FILE_ACTION__");
        String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");

        List< ? extends IConnection> conns = node.getIncomingConnections();
        if(conns != null){

            if(conns.size()>0){

                IConnection conn =conns.get(0);
                String connName = conn.getName();


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_3);
    
                if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
                    List<IMetadataColumn> columns = metadata.getListColumns();
                    int nbColumns = columns.size();
                
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
                    for(int i=0; i<nbColumns; i++) {
                        IMetadataColumn column = columns.get(i);
                        String columnName = column.getLabel();
                        String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                        JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

                        if(javaType == JavaTypesManager.INTEGER || javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.BYTE) {
                
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_8);
    
                        } else if(javaType == JavaTypesManager.LONG) {
                
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_11);
    
                        } else if(javaType == JavaTypesManager.DOUBLE) {
                
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_14);
    
                        } else if(javaType == JavaTypesManager.FLOAT) {
                
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    
                        } else if(javaType == JavaTypesManager.BOOLEAN) {
                
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_20);
    
                        } else {
                
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_23);
    
                        }
                    }
                
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(compression);
    stringBuffer.append(TEXT_33);
    
                    if("OVERWRITE".equals(fileAction)){
                        
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    
                    }
                }
            }
        }
    }
}

    stringBuffer.append(TEXT_39);
    
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
            
    stringBuffer.append(TEXT_40);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_41);
    
        } else {
            
    stringBuffer.append(TEXT_42);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, "__CLOUDERA_NAVIGATOR_PASSWORD__"));
    stringBuffer.append(TEXT_43);
    
        }
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(clientUrlCN);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(urlCN);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(urlMetadataCN);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(usernameCN);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(useAutocommit);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(disableSslValidation);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(apiVersion);
    stringBuffer.append(TEXT_58);
    
    }

    /**
    *
    * generates an Atlas lineage creator
    *
    */
    public void generateAtlasLinageCreator(INode configurationNode) {
        String atlasURL = ElementParameterParser.getValue(configurationNode,"__ATLAS_URL__");
        
    stringBuffer.append(TEXT_59);
    stringBuffer.append(atlasURL);
    stringBuffer.append(TEXT_60);
    
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
        
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
        for (IMetadataColumn column: metadataTable.getListColumns()) {
            
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_66);
    
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
        
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    
        if ((node.getOutgoingConnections() != null)
            && (node.getOutgoingConnections().size() > 0)) {
            for (IConnection connection: node.getOutgoingConnections()) {
                if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                    for (IMetadataColumn column: connection.getMetadataTable().getListColumns()) {
                        
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_72);
    
                    }
                }
            }
        } else  {
            for (IMetadataTable metadataTable: node.getMetadataList()) {
                for (IMetadataColumn column: metadataTable.getListColumns()) {
                    
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(org.talend.core.model.metadata.types.JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_76);
    
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
        
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(componentName);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(folderPath);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(folderType);
    stringBuffer.append(TEXT_81);
    
    }

    /**
     * Generate list of input nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateInputNodeList(INode node) {
        
    stringBuffer.append(TEXT_82);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_83);
    
        java.util.List<IConnection> inputs = (java.util.List<IConnection>)node.getIncomingConnections();
        for (IConnection connection: inputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_84);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(connection.getSource().getUniqueName());
    stringBuffer.append(TEXT_86);
    
            }
        }
    }

    /**
     * Generate list of output nodes for a given component.
     * 
     * @param node the currentNode
     */
    public void generateOutputNodeList(INode node) {
        
    stringBuffer.append(TEXT_87);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_88);
    
        java.util.List<IConnection> outputs = (java.util.List<IConnection>)node.getOutgoingConnections();
        for (IConnection connection: outputs) {
            if (connection.getLineStyle().hasConnectionCategory(org.talend.core.model.process.IConnectionCategory.DATA)) {
                
    stringBuffer.append(TEXT_89);
    stringBuffer.append(node.getUniqueName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(connection.getTarget().getUniqueName());
    stringBuffer.append(TEXT_91);
    
            }
        }
    }

    /**
     * Generate the code to call the method addNodeToLineage of rg.talend.cloudera.navigator.api.LineageCreator.
     * 
     * @param cid the cid of the current component
     */
    public void addNodeToLineage(String cid) {
        
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    
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
        talendLineageAPI.addDataset(cid, originalName, "outputPath_" + cid, "PARQUET");
    }
}

    stringBuffer.append(TEXT_97);
    return stringBuffer.toString();
  }
}
