package org.talend.designer.codegen.translators.webservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.MappingTypeRetriever;
import org.talend.core.model.metadata.MetadataTalendType;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;

public class TRestWebServiceOutputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TRestWebServiceOutputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRestWebServiceOutputSparkstreamingcodeJava result = new TRestWebServiceOutputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = "_OutputFunction" + NL + "    implements org.apache.spark.api.java.function.VoidFunction<java.util.Iterator<";
  protected final String TEXT_3 = ">>{" + NL + "" + NL + "    private ContextProperties context = null;" + NL + "" + NL + "    public ";
  protected final String TEXT_4 = "_OutputFunction(JobConf job){" + NL + "         this.context = new ContextProperties(job);" + NL + "    }" + NL + "" + NL + "    public void call(java.util.Iterator<";
  protected final String TEXT_5 = "> dataIterator) throws java.lang.Exception{" + NL + "        com.sun.jersey.api.client.config.ClientConfig config_";
  protected final String TEXT_6 = " = new com.sun.jersey.api.client.config.DefaultClientConfig();";
  protected final String TEXT_7 = NL + "            javax.net.ssl.SSLContext ctx_";
  protected final String TEXT_8 = " = javax.net.ssl.SSLContext.getInstance(\"SSL\");" + NL + "" + NL + "            javax.net.ssl.TrustManager[] tms_";
  protected final String TEXT_9 = " = null;" + NL + "" + NL + "            String trustStoreFile_";
  protected final String TEXT_10 = " = ";
  protected final String TEXT_11 = ";" + NL + "            String trustStoreType_";
  protected final String TEXT_12 = " = ";
  protected final String TEXT_13 = ";" + NL + "            String trustStorePWD_";
  protected final String TEXT_14 = " = ";
  protected final String TEXT_15 = ";" + NL + "" + NL + "            if(trustStoreFile_";
  protected final String TEXT_16 = "!=null && trustStoreType_";
  protected final String TEXT_17 = "!=null){" + NL + "                char[] password_";
  protected final String TEXT_18 = " = null;" + NL + "                if(trustStorePWD_";
  protected final String TEXT_19 = "!=null)" + NL + "                    password_";
  protected final String TEXT_20 = " = trustStorePWD_";
  protected final String TEXT_21 = ".toCharArray();" + NL + "                java.security.KeyStore trustStore_";
  protected final String TEXT_22 = " = java.security.KeyStore.getInstance(trustStoreType_";
  protected final String TEXT_23 = ");" + NL + "                trustStore_";
  protected final String TEXT_24 = ".load(new java.io.FileInputStream(trustStoreFile_";
  protected final String TEXT_25 = "), password_";
  protected final String TEXT_26 = ");" + NL + "" + NL + "                javax.net.ssl.TrustManagerFactory tmf_";
  protected final String TEXT_27 = " = javax.net.ssl.TrustManagerFactory.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());" + NL + "                tmf_";
  protected final String TEXT_28 = ".init(trustStore_";
  protected final String TEXT_29 = ");" + NL + "                tms_";
  protected final String TEXT_30 = " = tmf_";
  protected final String TEXT_31 = ".getTrustManagers();" + NL + "            }" + NL + "" + NL + "            javax.net.ssl.KeyManager[] kms_";
  protected final String TEXT_32 = " = null;";
  protected final String TEXT_33 = NL + "                String keyStoreFile_";
  protected final String TEXT_34 = " = ";
  protected final String TEXT_35 = ";" + NL + "                String keyStoreType_";
  protected final String TEXT_36 = " = ";
  protected final String TEXT_37 = ";" + NL + "                String keyStorePWD_";
  protected final String TEXT_38 = " = ";
  protected final String TEXT_39 = ";" + NL + "" + NL + "                if(keyStoreFile_";
  protected final String TEXT_40 = "!=null && keyStoreType_";
  protected final String TEXT_41 = "!=null){" + NL + "                    char[] password_";
  protected final String TEXT_42 = " = null;" + NL + "                    if(keyStorePWD_";
  protected final String TEXT_43 = "!=null)" + NL + "                        password_";
  protected final String TEXT_44 = " = keyStorePWD_";
  protected final String TEXT_45 = ".toCharArray();" + NL + "                    java.security.KeyStore keyStore_";
  protected final String TEXT_46 = " = java.security.KeyStore.getInstance(keyStoreType_";
  protected final String TEXT_47 = ");" + NL + "                    keyStore_";
  protected final String TEXT_48 = ".load(new java.io.FileInputStream(keyStoreFile_";
  protected final String TEXT_49 = "), password_";
  protected final String TEXT_50 = ");" + NL + "" + NL + "                    javax.net.ssl.KeyManagerFactory kmf_";
  protected final String TEXT_51 = " = javax.net.ssl.KeyManagerFactory.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());" + NL + "                    kmf_";
  protected final String TEXT_52 = ".init(keyStore_";
  protected final String TEXT_53 = ",password_";
  protected final String TEXT_54 = ");" + NL + "                    kms_";
  protected final String TEXT_55 = " = kmf_";
  protected final String TEXT_56 = ".getKeyManagers();" + NL + "                }";
  protected final String TEXT_57 = NL + "            ctx_";
  protected final String TEXT_58 = ".init(kms_";
  protected final String TEXT_59 = ", tms_";
  protected final String TEXT_60 = " , null);" + NL + "            javax.net.ssl.HostnameVerifier nameVerifier_";
  protected final String TEXT_61 = " = null;";
  protected final String TEXT_62 = NL + "                nameVerifier_";
  protected final String TEXT_63 = " = new javax.net.ssl.HostnameVerifier(){" + NL + "                                              public boolean verify(String hostName,javax.net.ssl.SSLSession session)" + NL + "                                                   {" + NL + "                                                       return true;" + NL + "                                                   }" + NL + "                                         };";
  protected final String TEXT_64 = NL + "            config_";
  protected final String TEXT_65 = ".getProperties().put(com.sun.jersey.client.urlconnection.HTTPSProperties.PROPERTY_HTTPS_PROPERTIES," + NL + "                    new com.sun.jersey.client.urlconnection.HTTPSProperties(nameVerifier_";
  protected final String TEXT_66 = ", ctx_";
  protected final String TEXT_67 = "));";
  protected final String TEXT_68 = NL + "        com.sun.jersey.api.client.Client restClient_";
  protected final String TEXT_69 = " = com.sun.jersey.api.client.Client.create(config_";
  protected final String TEXT_70 = ");" + NL + "        while(dataIterator.hasNext()){";
  protected final String TEXT_71 = NL + "            ";
  protected final String TEXT_72 = " ";
  protected final String TEXT_73 = " = dataIterator.next();";
  protected final String TEXT_74 = NL + "            try{" + NL + "                restClient_";
  protected final String TEXT_75 = ".asyncResource(";
  protected final String TEXT_76 = ")";
  protected final String TEXT_77 = NL + "                            .header(";
  protected final String TEXT_78 = ",";
  protected final String TEXT_79 = ")";
  protected final String TEXT_80 = NL + "                            .post(String.class, ";
  protected final String TEXT_81 = ".payload);";
  protected final String TEXT_82 = NL + "                            .put(String.class, ";
  protected final String TEXT_83 = ".payload);";
  protected final String TEXT_84 = NL + "                            .delete(String.class);";
  protected final String TEXT_85 = NL + "            }catch(Exception e){" + NL + "                e.printStackTrace();" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_86 = NL + "    public static class ";
  protected final String TEXT_87 = "_ForeachRDDOutput implements ";
  protected final String TEXT_88 = " {" + NL + "" + NL + "        private JobConf conf;" + NL + "" + NL + "        public ";
  protected final String TEXT_89 = "_ForeachRDDOutput(JobConf conf) {" + NL + "            this.conf = conf;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "           public ";
  protected final String TEXT_90 = " call(";
  protected final String TEXT_91 = " rdd) throws Exception {" + NL + "            rdd.foreachPartition(new ";
  protected final String TEXT_92 = "_OutputFunction(conf));";
  protected final String TEXT_93 = NL + "              ";
  protected final String TEXT_94 = NL + "        }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();
if((metadatas!=null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
        columns = metadata.getListColumns();
    }
}
List<? extends IConnection> conns = node.getIncomingConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}

if(columns == null || columns.isEmpty() || conn == null){
    return "";
}

String inRowStruct = codeGenArgument.getRecordStructName(conn);

String connName = conn.getName();

for(INode gNode : node.getProcess().getGraphicalNodes()){
    if(gNode.getUniqueName().equals(cid)){
        List<? extends IConnection> gConns = gNode.getIncomingConnections();
        if(gConns != null && gConns.size() > 0 && gConns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
            connName = gConns.get(0).getName();
        }
    }
}

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    
        TSetKeystoreUtil tSetKeystoreUtil = new TSetKeystoreUtil(node);
        if(tSetKeystoreUtil.useHTTPS()){
        
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(tSetKeystoreUtil.getTrustStoreType());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
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
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    if(!tSetKeystoreUtil.needVerifyName()){
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    }
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
        }
        
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(inRowStruct);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_73);
    
            String url= ElementParameterParser.getValue(node,"__URL__");
            String method = ElementParameterParser.getValue(node,"__METHOD__");
            List<Map<String, String>> headers = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__HEADERS__");
            
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_76);
    
                        for(int i = 0; i < headers.size(); i++){
                            Map<String, String> line = headers.get(i);
                            
    stringBuffer.append(TEXT_77);
    stringBuffer.append(line.get("NAME"));
    stringBuffer.append(TEXT_78);
    stringBuffer.append(line.get("VALUE"));
    stringBuffer.append(TEXT_79);
    
                        }
                        
    
                        if("POST".equals(method)){
                        
    stringBuffer.append(TEXT_80);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_81);
    
                        }else if("PUT".equals(method)){
                        
    stringBuffer.append(TEXT_82);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_83);
    
                        }else if("DELETE".equals(method)){
                        
    stringBuffer.append(TEXT_84);
    
                        }
                        
    stringBuffer.append(TEXT_85);
    
{ // Start ForeachRDD helper function
    // The signature of foreachRDD has changed in Spark 2.0
    org.talend.designer.spark.generator.utils.ForeachRDDUtil foreachUtil =
            org.talend.designer.spark.generator.utils.ForeachRDDUtil.createFunctionJavaRDD(
                    codeGenArgument.getSparkVersion(), inRowStruct);
    
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(foreachUtil.getFunctionInterface());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(foreachUtil.getCallReturnType());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(foreachUtil.getCallArgumentType());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(foreachUtil.getCallReturnCode());
    stringBuffer.append(TEXT_94);
    
} // End ForeachRDD helper function

    return stringBuffer.toString();
  }
}
