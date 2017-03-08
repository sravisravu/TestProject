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

public class THBaseInputSparkconfigJava
{
  protected static String nl;
  public static synchronized THBaseInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseInputSparkconfigJava result = new THBaseInputSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "                System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_2 = ");" + NL + "                System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_3 = ");";
  protected final String TEXT_4 = NL + "            job.set(\"hbase.master.kerberos.principal\",";
  protected final String TEXT_5 = ");" + NL + "            job.set(\"hbase.regionserver.kerberos.principal\",";
  protected final String TEXT_6 = ");" + NL + "            job.set(\"hbase.security.authorization\",\"true\");" + NL + "            job.set(\"hbase.security.authentication\",\"kerberos\");" + NL + "            job.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_7 = ");" + NL + "            job.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_8 = ");    ";
  protected final String TEXT_9 = NL + "                org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_10 = ", ";
  protected final String TEXT_11 = ");";
  protected final String TEXT_12 = NL + "                com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_13 = " = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "                maprLogin_";
  protected final String TEXT_14 = ".getMapRCredentialsViaKerberos(";
  protected final String TEXT_15 = ", ";
  protected final String TEXT_16 = ");";
  protected final String TEXT_17 = NL + "                // Get MapReduce job authentication token from Hbase" + NL + "                org.apache.hadoop.hbase.security.token.TokenUtil.obtainTokenForJob(job, org.apache.hadoop.security.UserGroupInformation.getCurrentUser());";
  protected final String TEXT_18 = " " + NL + "                    final String decryptedPasswordhbase_";
  protected final String TEXT_19 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_20 = ");";
  protected final String TEXT_21 = NL + "                    final String decryptedPasswordhbase_";
  protected final String TEXT_22 = " = ";
  protected final String TEXT_23 = "; ";
  protected final String TEXT_24 = NL + "                System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_25 = ");" + NL + "                com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_26 = " = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_27 = NL + "                    System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = NL + "                    maprLogin_";
  protected final String TEXT_30 = ".setCheckUGI(false);";
  protected final String TEXT_31 = NL + "                maprLogin_";
  protected final String TEXT_32 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_33 = ", ";
  protected final String TEXT_34 = ", decryptedPasswordhbase_";
  protected final String TEXT_35 = ", ";
  protected final String TEXT_36 = ");";
  protected final String TEXT_37 = NL + "            org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_38 = "> rdd_";
  protected final String TEXT_39 = " = ctx.hadoopRDD(job, ";
  protected final String TEXT_40 = "InputFormat_";
  protected final String TEXT_41 = ".class, NullWritable.class, ";
  protected final String TEXT_42 = ".class);" + NL + "            org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_43 = "> rdd_";
  protected final String TEXT_44 = " = rdd_";
  protected final String TEXT_45 = ".mapToPair(new IdentityMap_";
  protected final String TEXT_46 = "(job));";
  protected final String TEXT_47 = NL + "            // extract raw HBase cells" + NL + "            org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_48 = "TemporaryStruct> rawRdd_";
  protected final String TEXT_49 = " = ctx.hadoopRDD(job, ";
  protected final String TEXT_50 = "StructInputFormat.class, NullWritable.class, ";
  protected final String TEXT_51 = "TemporaryStruct.class);" + NL + "" + NL + "            // extract rejected records" + NL + "            org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_52 = "> rdd_";
  protected final String TEXT_53 = " = rawRdd_";
  protected final String TEXT_54 = ".flatMapToPair(new RejectMap_";
  protected final String TEXT_55 = "(job));";
  protected final String TEXT_56 = NL + "                    // extract raw HBase cells" + NL + "                    org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_57 = "TemporaryStruct> rawRdd_";
  protected final String TEXT_58 = " = ctx.hadoopRDD(job, ";
  protected final String TEXT_59 = "StructInputFormat.class, NullWritable.class, ";
  protected final String TEXT_60 = "TemporaryStruct.class);" + NL + "" + NL + "                    // cast to avro structs" + NL + "                    org.apache.spark.api.java.JavaPairRDD<Boolean, org.apache.avro.specific.SpecificRecordBase> castedRdd_";
  protected final String TEXT_61 = " = rawRdd_";
  protected final String TEXT_62 = ".mapToPair(new CastMap_";
  protected final String TEXT_63 = "(job));" + NL + "" + NL + "                    // Main flow" + NL + "                    org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_64 = "> rdd_";
  protected final String TEXT_65 = " = castedRdd_";
  protected final String TEXT_66 = ".filter(new ";
  protected final String TEXT_67 = "TrueFilter()).mapToPair(new ";
  protected final String TEXT_68 = "ToNullWritableMain(job));" + NL + "" + NL + "                    // Reject flow" + NL + "                    org.apache.spark.api.java.JavaPairRDD<NullWritable, ";
  protected final String TEXT_69 = "> rdd_";
  protected final String TEXT_70 = " = castedRdd_";
  protected final String TEXT_71 = ".filter(new ";
  protected final String TEXT_72 = "FalseFilter()).mapToPair(new ";
  protected final String TEXT_73 = "ToNullWritableReject(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();
INode configurationNode = null;
String configurationNodeName = ElementParameterParser.getValue(node,"__STORAGE_CONFIGURATION__");

for (INode pNode : node.getProcess().getNodesOfType("tHBaseConfiguration")) {
    if(configurationNodeName!=null && configurationNodeName.equals(pNode.getUniqueName())) {
        configurationNode = pNode;
    }else{
        return "";
    }
}

if(metadatas!=null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        String zookeeperUrl = ElementParameterParser.getValue(configurationNode,"__ZOOKEEPER_QUORUM__");
        String zookeeperPort = ElementParameterParser.getValue(configurationNode,"__ZOOKEEPER_CLIENT_PORT__");
        String table_name = ElementParameterParser.getValue(node, "__TABLE__");
        List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
        List< ? extends IConnection> mainConnections = node.getOutgoingConnections("FLOW");
        List< ? extends IConnection> rejectedConnections = node.getOutgoingConnections("REJECT");

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
                if (ElementParameterParser.canEncrypt(configurationNode, passwordFieldName)) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_20);
    } else {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append( ElementParameterParser.getValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    
                if (setMapRHadoopLogin) {
                    
    stringBuffer.append(TEXT_27);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_28);
    
                } else {
                    
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
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

        // Fail fast when no output connections exist.
        if (conns == null || conns.size() == 0)
            return "";

        // Only main flow
       if ((mainConnections.size() == 1) && (rejectedConnections.size() == 0)) {
            IConnection mainConnection = mainConnections.get(0);
            String connName = mainConnection.getName();
            String outStruct = codeGenArgument.getRecordStructName(mainConnection);
            //Note: Because Hadoop's RecordReader class re-uses the same Writable object for each record, directly caching the returned RDD or directly passing it to an aggregation or shuffle operation will create many references to the same object. If you plan to directly cache, sort, or aggregate Hadoop writable objects, you should first copy them using a map function.
            
    stringBuffer.append(TEXT_37);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    
        }
        // Only reject flow
        else if((mainConnections.size() == 0) && (rejectedConnections.size() == 1)){
            IConnection rejectedConnection = rejectedConnections.get(0);
            String rejectConnName = rejectedConnection.getName();
            String rejectOutStruct = codeGenArgument.getRecordStructName(rejectedConnection);
            
    stringBuffer.append(TEXT_47);
    stringBuffer.append(rejectOutStruct);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(rejectOutStruct);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(rejectOutStruct);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(rejectOutStruct);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    
        }
        else {
            // Main and reject flows
            if ((mainConnections != null) && (mainConnections.size() == 1)
                    && (rejectedConnections != null) && (rejectedConnections.size() == 1)) {
                IConnection mainConnection = mainConnections.get(0);
                String mainConnName = mainConnection.getName();
                String mainOutStruct = codeGenArgument.getRecordStructName(mainConnection);
                IConnection rejectedConnection = rejectedConnections.get(0);
                String rejectConnName = rejectedConnection.getName();
                String rejectOutStruct = codeGenArgument.getRecordStructName(rejectedConnection);

    stringBuffer.append(TEXT_56);
    stringBuffer.append(mainOutStruct);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(mainOutStruct);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(mainOutStruct);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(mainOutStruct);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(mainConnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(rejectOutStruct);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(rejectConnName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    
            }
        }
    }
}

    return stringBuffer.toString();
  }
}
