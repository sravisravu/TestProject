package org.talend.designer.codegen.translators.databases.hbase;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class THBaseOutputSparkcodeJava
{
  protected static String nl;
  public static synchronized THBaseOutputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseOutputSparkcodeJava result = new THBaseOutputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t";
  protected final String TEXT_2 = NL + "\t\t\tpublic static class ";
  protected final String TEXT_3 = "StructOutputFormat implements OutputFormat<NullWritable, ";
  protected final String TEXT_4 = "> {" + NL + "\t\t\t\tprotected static class HBaseTableRecordWriter implements" + NL + "\t\t\t\t\t\tRecordWriter<NullWritable, ";
  protected final String TEXT_5 = "> {" + NL + "\t\t\t\t\tprivate org.apache.hadoop.hbase.client.HTable m_table;" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tContextProperties context;" + NL + "\t\t\t\t\t" + NL + "\t\t\t\t\tpublic HBaseTableRecordWriter(org.apache.hadoop.hbase.client.HTable table, Configuration job) {" + NL + "\t\t\t\t\t\tthis.m_table = table;" + NL + "\t\t\t\t\t\tcontext = new ContextProperties(job);" + NL + "\t\t\t\t\t}" + NL + "\t\t" + NL + "\t\t\t\t\tprivate void writeObject(";
  protected final String TEXT_6 = " value) throws IOException {" + NL + "\t\t\t\t\t\tbyte[] rowKey = org.apache.hadoop.hbase.util.Bytes.toBytes(value.";
  protected final String TEXT_7 = ");" + NL + "\t\t\t\t\t\torg.apache.hadoop.hbase.client.Put put = new org.apache.hadoop.hbase.client.Put(rowKey);" + NL + "\t\t\t\t\t\tbyte[] temp = null;" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_8 = NL + "\t\t\t\t\t\t\t\ttemp = null;" + NL + "\t\t\t\t\t\t\t\tif(value.";
  protected final String TEXT_9 = "!=null){" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_10 = NL + "\t\t\t\t\t\t\t\ttemp = org.apache.hadoop.hbase.util.Bytes.toBytes(FormatterUtils.format_DateInUTC(value.";
  protected final String TEXT_11 = ", ";
  protected final String TEXT_12 = "));" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_13 = NL + "\t\t\t\t\t\t\t\ttemp = value.";
  protected final String TEXT_14 = ";" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_15 = NL + "\t\t\t\t\t\t\t\ttemp = org.apache.hadoop.hbase.util.Bytes.toBytes(value.";
  protected final String TEXT_16 = "+\"\");" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\t\t\t\t\ttemp = org.apache.hadoop.hbase.util.Bytes.toBytes(value.";
  protected final String TEXT_18 = ");" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_19 = NL + "\t\t\t\t\t\t\t\ttemp = org.apache.hadoop.hbase.util.Bytes.toBytes(value.";
  protected final String TEXT_20 = ".toString());" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_21 = NL + "\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\t\t\t\tput.add(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_23 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_24 = "\"), temp);" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_25 = NL + "\t\t\t\t\t\tthis.m_table.put(put);" + NL + "\t\t\t\t\t}" + NL + "\t\t" + NL + "\t\t\t\t\tpublic void write(NullWritable key, ";
  protected final String TEXT_26 = " value)" + NL + "\t\t\t\t\t\t\tthrows IOException {" + NL + "\t\t" + NL + "\t\t\t\t\t\tboolean nullValue = value == null;" + NL + "\t\t\t\t\t\tif (nullValue) {" + NL + "\t\t\t\t\t\t\treturn;" + NL + "\t\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t\twriteObject(value);" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t" + NL + "\t\t\t\t\tpublic synchronized void close(Reporter reporter) throws IOException {" + NL + "\t\t\t\t\t\tthis.m_table.close();" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t\t\t\tContextProperties context;" + NL + "\t\t\t\t" + NL + "\t\t\t\tpublic RecordWriter<NullWritable, ";
  protected final String TEXT_27 = "> getRecordWriter(" + NL + "\t\t\t\t\t\tFileSystem ignored, JobConf job, String name, Progressable progress) throws IOException{" + NL + "\t\t\t\t\tcontext = new ContextProperties(job);" + NL + "\t\t\t\t\tJobConf hbaseJob = new JobConf(job);" + NL + "" + NL + "" + NL + "\t\t\t\t\thbaseJob.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_28 = ");" + NL + "\t\t\t\t\thbaseJob.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_29 = ");" + NL + "" + NL + "\t\t\t\t\t";
  protected final String TEXT_30 = NL + "                        hbaseJob.set(\"hbase.master.kerberos.principal\",";
  protected final String TEXT_31 = ");" + NL + "                        hbaseJob.set(\"hbase.regionserver.kerberos.principal\",";
  protected final String TEXT_32 = ");" + NL + "                        hbaseJob.set(\"hbase.security.authorization\",\"true\");" + NL + "                        hbaseJob.set(\"hbase.security.authentication\",\"kerberos\");";
  protected final String TEXT_33 = NL + "                            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_34 = ");" + NL + "                            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "                            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_37 = ");";
  protected final String TEXT_38 = NL + "                                System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_39 = ");";
  protected final String TEXT_40 = NL + "\t\t\t\t\t    hbaseJob.set(\"zookeeper.znode.parent\",";
  protected final String TEXT_41 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\thbaseJob.set(";
  protected final String TEXT_43 = ",";
  protected final String TEXT_44 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_45 = NL + "\t\t\t\t\torg.apache.hadoop.hbase.client.HTable hTable = new org.apache.hadoop.hbase.client.HTable(hbaseJob, ";
  protected final String TEXT_46 = ");" + NL + "\t\t\t\t\thTable.setAutoFlush(false);" + NL + "\t\t\t\t\treturn new HBaseTableRecordWriter(hTable, job);" + NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t\t\t\tpublic void checkOutputSpecs(FileSystem ignored, JobConf job)" + NL + "\t\t\t\t  throws FileAlreadyExistsException, InvalidJobConfException, IOException {" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
    BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();  
    String cid = node.getUniqueName();
    INode configurationNode = null;
	String configurationNodeName = ElementParameterParser.getValue(node,"__STORAGE_CONFIGURATION__");

	for (INode pNode : node.getProcess().getNodesOfType("tHBaseConfiguration")) {
	    if(configurationNodeName!=null && configurationNodeName.equals(pNode.getUniqueName())) {
	        configurationNode = pNode;
	    }else{
	        return "";
	    }
	}
    
    List<IMetadataTable> metadatas = node.getMetadataList();
    if(metadatas != null && metadatas.size() > 0) {
        IMetadataTable metadata = metadatas.get(0);
        if(metadata != null){

            String hbaseDistribution = ElementParameterParser.getValue(configurationNode, "__DISTRIBUTION__");
            String hbaseVersion = ElementParameterParser.getValue(configurationNode, "__HBASE_VERSION__");
            
            String zookeeper_quorum = ElementParameterParser.getValue(configurationNode, "__ZOOKEEPER_QUORUM__");
			String zookeeper_client_port = ElementParameterParser.getValue(configurationNode, "__ZOOKEEPER_CLIENT_PORT__");
		    String hbaseMasterPrincipal = ElementParameterParser.getValue(configurationNode, "__HBASE_MASTER_PRINCIPAL__");
		    String userPrincipal = ElementParameterParser.getValue(configurationNode, "__PRINCIPAL__");
		    String keytabPath = ElementParameterParser.getValue(configurationNode, "__KEYTAB_PATH__");
		    String hbaseRegionServerPrincipal = ElementParameterParser.getValue(configurationNode, "__HBASE_REGIONSERVER_PRINCIPAL__");

			
			boolean setZNodeParent = "true".equals(ElementParameterParser.getValue(configurationNode, "__SET_ZNODE_PARENT__"));
			String zNodeParent = ElementParameterParser.getValue(configurationNode, "__ZNODE_PARENT__");
						
			String table_name = ElementParameterParser.getValue(node, "__TABLE__");
			String rowKey = ElementParameterParser.getValue(node, "__ROWKEY_COLUMN__");
			List<Map<String,String>> families = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__FAMILIES__");
			boolean contains_rowKey = "true".equals(ElementParameterParser.getValue(node, "__CONTAIN_ROWKEY__"));

	        boolean useKrb = "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_KRB__"));
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
		    
	  		String connName = "";
	        String inConnTypeName="";

		    List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
		    if(inConns != null && inConns.size() > 0){
		    	IConnection inConn = inConns.get(0); 
		        connName = inConn.getName();
		        inConnTypeName = codeGenArgument.getRecordStructName(inConn);
		    }else{
		        return "";
		    }
    
		    List<IMetadataColumn> columns = metadata.getListColumns();
		    int columnSize = columns.size();
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(rowKey);
    stringBuffer.append(TEXT_7);
    
						for(int familyNum = 0; familyNum < families.size(); familyNum++){
							IMetadataColumn column = columns.get(familyNum);
							JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
							String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
							Map<String,String> mapLine = families.get(familyNum);
							String schema_column = mapLine.get("SCHEMA_COLUMN");
							String family_column = mapLine.get("FAMILY_COLUMN");
							if(family_column==null || family_column.trim().length()==0){
								continue;
							}
							if(!contains_rowKey){
								if(schema_column.equals(rowKey)){
									continue;
								}
							}
							boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType( javaType, column.isNullable());
							if(!isPrimitive){
							
    stringBuffer.append(TEXT_8);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_9);
    
							}
							if (javaType == JavaTypesManager.DATE && pattern != null && pattern.trim().length() != 0) {
							
    stringBuffer.append(TEXT_10);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_11);
    stringBuffer.append( pattern);
    stringBuffer.append(TEXT_12);
    
							}else if (javaType == JavaTypesManager.BYTE_ARRAY) {
							
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
							}else if (javaType == JavaTypesManager.BYTE) {
							
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    
							}else if(JavaTypesManager.isJavaPrimitiveType(javaType,column.isNullable())) {
							
    stringBuffer.append(TEXT_17);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_18);
    
							}else{
							
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_20);
    
							}
							if(!isPrimitive){
							
    stringBuffer.append(TEXT_21);
    
							}
							
    stringBuffer.append(TEXT_22);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_24);
    
						}
						
    stringBuffer.append(TEXT_25);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(zookeeper_quorum);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(zookeeper_client_port);
    stringBuffer.append(TEXT_29);
    
                    if((isCustom || hbaseDistrib.doSupportKerberos()) && useKrb){
                        
    stringBuffer.append(TEXT_30);
    stringBuffer.append(hbaseMasterPrincipal);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(hbaseRegionServerPrincipal);
    stringBuffer.append(TEXT_32);
    
                        if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {
                            
    stringBuffer.append(TEXT_33);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_35);
    
                        }
                    } else {
                        if ((isCustom || hbaseDistrib.doSupportMapRTicket()) && useMapRTicket) {
                            
    stringBuffer.append(TEXT_36);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_37);
    
                            if (setMapRHadoopLogin) {
                                
    stringBuffer.append(TEXT_38);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_39);
    
                            }
                        }
                    }

					if(setZNodeParent) {
					
    stringBuffer.append(TEXT_40);
    stringBuffer.append(zNodeParent);
    stringBuffer.append(TEXT_41);
    
					}

					List<Map<String, String>> properties =
				        (List<Map<String,String>>)ElementParameterParser.getObjectValue(configurationNode,"__HBASE_PARAMETERS__");
				   	for(int i=0;i<properties.size();i++){
				   		Map<String, String> map = properties.get(i);
				   		String property = map.get("PROPERTY");
				   		String value= map.get("VALUE");
						
    stringBuffer.append(TEXT_42);
    stringBuffer.append(property);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_44);
    
				   	}
					
    stringBuffer.append(TEXT_45);
    stringBuffer.append(table_name);
    stringBuffer.append(TEXT_46);
    
    	}
	}   
	
    return stringBuffer.toString();
  }
}
