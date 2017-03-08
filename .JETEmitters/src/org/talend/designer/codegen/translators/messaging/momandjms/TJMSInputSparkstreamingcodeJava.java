package org.talend.designer.codegen.translators.messaging.momandjms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
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

public class TJMSInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TJMSInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJMSInputSparkstreamingcodeJava result = new TJMSInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class JMSReceiver_";
  protected final String TEXT_2 = " extends org.apache.spark.streaming.receiver.Receiver<";
  protected final String TEXT_3 = ">{" + NL + "\tprivate ContextProperties context = null;" + NL + "    //private GlobalVar globalMap = null;" + NL + "    " + NL + "    String contextProvider;" + NL + "    String url;" + NL + "    String connFacName;" + NL + "    boolean useAuth = false;" + NL + "    String user;" + NL + "    String password;" + NL + "    String from;" + NL + "    String messageSelector;" + NL + "    java.util.Hashtable props;" + NL + "    int timeout = -1;" + NL + "    int maxMsg = -1;" + NL + "    " + NL + "    String tsPath;" + NL + "    String tsType;" + NL + "    String tsPwd;" + NL + "    String ksPath;" + NL + "    String ksType;" + NL + "    String ksPwd;" + NL + "\t" + NL + "\tpublic JMSReceiver_";
  protected final String TEXT_4 = "(JobConf job, org.apache.spark.storage.StorageLevel storageLevel) {" + NL + "        super(storageLevel);" + NL + " \t\tthis.context = new ContextProperties(job);" + NL + " \t\t//this.globalMap = new GlobalVar(job);" + NL + " \t\t";
  protected final String TEXT_5 = NL + " \t\turl = ";
  protected final String TEXT_6 = ";" + NL + " \t\tcontextProvider = ";
  protected final String TEXT_7 = ";" + NL + " \t\tconnFacName = ";
  protected final String TEXT_8 = ";" + NL + " \t\t";
  protected final String TEXT_9 = NL + "\t \t\tuseAuth = ";
  protected final String TEXT_10 = ";" + NL + "\t \t\tuser = ";
  protected final String TEXT_11 = ";" + NL + "\t \t\tpassword = ";
  protected final String TEXT_12 = ";" + NL + " \t\t";
  protected final String TEXT_13 = NL + " \t\tfrom = ";
  protected final String TEXT_14 = ";" + NL + " \t\tmessageSelector = ";
  protected final String TEXT_15 = ";" + NL + " \t\t" + NL + " \t\tprops = new java.util.Hashtable();" + NL + "    \tprops.put(javax.naming.Context.INITIAL_CONTEXT_FACTORY, contextProvider);" + NL + "    \tprops.put(javax.naming.Context.PROVIDER_URL, url);" + NL + "    \t";
  protected final String TEXT_16 = NL + "        \t\tprops.put(";
  protected final String TEXT_17 = ", ";
  protected final String TEXT_18 = ");" + NL + "        \t";
  protected final String TEXT_19 = NL + "        " + NL + "        timeout = ";
  protected final String TEXT_20 = ";" + NL + "        maxMsg = ";
  protected final String TEXT_21 = ";" + NL + "        ";
  protected final String TEXT_22 = NL + "        \ttsPath = ";
  protected final String TEXT_23 = ";" + NL + "        \ttsType = ";
  protected final String TEXT_24 = ";" + NL + "        \ttsPwd = ";
  protected final String TEXT_25 = ";" + NL + "        \t";
  protected final String TEXT_26 = NL + "\t        \tksPath = ";
  protected final String TEXT_27 = ";" + NL + "\t        \tksType = ";
  protected final String TEXT_28 = ";" + NL + "\t        \tksPwd = ";
  protected final String TEXT_29 = ";" + NL + "        \t";
  protected final String TEXT_30 = NL + "    }" + NL + "    " + NL + "    public void onStart() {" + NL + "        new Thread() {" + NL + "            public void run() {" + NL + "                receive();" + NL + "            }" + NL + "        }.start();" + NL + "    }" + NL + "    public void onStop() {" + NL + "" + NL + "    }" + NL + "    private void receive(){";
  protected final String TEXT_31 = NL + "    \t\tSystem.setProperty(\"javax.net.ssl.trustStore\", tsPath);" + NL + "    \t\ttry{" + NL + "\t    \t\tnew java.io.FileInputStream(new java.io.File(System.getProperty(\"javax.net.ssl.trustStore\")));" + NL + "\t        }catch(Exception e){" + NL + "\t       \t\treportError(e.getMessage(), e);" + NL + "\t        }" + NL + "    \t\tSystem.setProperty(\"javax.net.ssl.trustStoreType\", tsType);" + NL + "\t\t    System.setProperty(\"javax.net.ssl.trustStorePassword\", tsPwd);" + NL + "\t\t    ";
  protected final String TEXT_32 = NL + "\t\t\t    System.setProperty(\"javax.net.ssl.keyStore\", ksPath);" + NL + "\t\t\t    try{" + NL + "\t\t    \t\tnew java.io.FileInputStream(new java.io.File(System.getProperty(\"javax.net.ssl.keyStore\")));" + NL + "\t\t        }catch(Exception e){" + NL + "\t\t       \t\treportError(e.getMessage(), e);" + NL + "\t\t        }" + NL + "\t    \t\tSystem.setProperty(\"javax.net.ssl.keyStoreType\", ksType);" + NL + "\t    \t\tSystem.setProperty(\"javax.net.ssl.keyStorePassword\", ksPwd);" + NL + "\t    \t";
  protected final String TEXT_33 = NL + "\t        ";
  protected final String TEXT_34 = NL + "\t\t\t    javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(" + NL + "\t\t\t    \tnew javax.net.ssl.HostnameVerifier(){" + NL + "\t\t\t      \t\tpublic boolean verify(String hostName,javax.net.ssl.SSLSession session)" + NL + "\t\t\t       \t\t\t{" + NL + "\t\t\t       \t\t\t\treturn true;" + NL + "\t\t\t       \t\t\t}" + NL + "\t\t\t     \t}" + NL + "\t\t\t    );" + NL + "\t\t\t";
  protected final String TEXT_35 = NL + "    \t";
  protected final String TEXT_36 = NL + "        " + NL + "    \tjavax.jms.Connection connection_";
  protected final String TEXT_37 = " = null;" + NL + "    \tjavax.jms.Session session_";
  protected final String TEXT_38 = " = null;" + NL + "    \tjavax.jms.MessageConsumer consumer_";
  protected final String TEXT_39 = " = null;" + NL + "    \ttry{" + NL + "        \tjavax.naming.Context context_";
  protected final String TEXT_40 = " = new javax.naming.InitialContext(props);" + NL + "        \tjavax.jms.ConnectionFactory factory_";
  protected final String TEXT_41 = " = (javax.jms.ConnectionFactory) context_";
  protected final String TEXT_42 = ".lookup(connFacName);" + NL + "        \tif(useAuth){" + NL + "        \tconnection_";
  protected final String TEXT_43 = " = factory_";
  protected final String TEXT_44 = ".createConnection(user, password);" + NL + "        \t}else{" + NL + "\t        \tconnection_";
  protected final String TEXT_45 = " = factory_";
  protected final String TEXT_46 = ".createConnection();" + NL + "        \t}" + NL + "        \tsession_";
  protected final String TEXT_47 = " = connection_";
  protected final String TEXT_48 = ".createSession(false, javax.jms.Session.CLIENT_ACKNOWLEDGE);" + NL + "        \tjavax.jms.Destination dest_";
  protected final String TEXT_49 = " = session_";
  protected final String TEXT_50 = ".create";
  protected final String TEXT_51 = "(from);" + NL + "        \tconsumer_";
  protected final String TEXT_52 = "\t= session_";
  protected final String TEXT_53 = ".createConsumer(dest_";
  protected final String TEXT_54 = ", messageSelector);" + NL + "        " + NL + "        \tconnection_";
  protected final String TEXT_55 = ".start();" + NL + "        " + NL + "        \tSystem.out.println(\"Ready to receive message\");" + NL + "        \tSystem.out.println(\"Waiting...\");" + NL + "        " + NL + "        \tjavax.jms.Message message_";
  protected final String TEXT_56 = ";" + NL + "        " + NL + "        \tint nbline_";
  protected final String TEXT_57 = " = 0;" + NL + "        " + NL + "        \twhile(!isStopped() && (message_";
  protected final String TEXT_58 = " = consumer_";
  protected final String TEXT_59 = ".receive(timeout*1000)) != null){" + NL + "        \t\t";
  protected final String TEXT_60 = " row = new ";
  protected final String TEXT_61 = "();" + NL + "         \t\t";
  protected final String TEXT_62 = NL + "        \t\t\trow.message=message_";
  protected final String TEXT_63 = ";\t" + NL + "    \t\t\t";
  protected final String TEXT_64 = NL + "        \t\t\t\trow.messageContent=BigDataParserUtils.parseTo_Document(((javax.jms.ObjectMessage) message_";
  protected final String TEXT_65 = ").getObject().toString());" + NL + "        \t\t\t";
  protected final String TEXT_66 = NL + "        \t\t\t\trow.messageContent=((javax.jms.TextMessage) message_";
  protected final String TEXT_67 = ").getText();" + NL + "        \t\t\t";
  protected final String TEXT_68 = NL + "    \t\t    store(row);" + NL + "        \t\tmessage_";
  protected final String TEXT_69 = ".acknowledge();" + NL + "            \tif(maxMsg > 0 && nbline_";
  protected final String TEXT_70 = " >= maxMsg){" + NL + "            \t\tbreak;" + NL + "            \t}" + NL + "    \t\t}" + NL + "    \t}catch(Throwable ex){" + NL + "    \t\trestart(ex.getMessage(), ex);" + NL + "    \t}finally{" + NL + "    \t\ttry{" + NL + "        \t\tif(consumer_";
  protected final String TEXT_71 = " != null){" + NL + "                \tconsumer_";
  protected final String TEXT_72 = ".close();" + NL + "                }" + NL + "                if(session_";
  protected final String TEXT_73 = " != null){" + NL + "                \tsession_";
  protected final String TEXT_74 = ".close();" + NL + "                }" + NL + "                if(connection_";
  protected final String TEXT_75 = " != null){" + NL + "                \tconnection_";
  protected final String TEXT_76 = ".close();" + NL + "                }" + NL + "            }catch(Exception ex){" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    
IMetadataTable metadata = null;
IConnection conn = null;
List<IMetadataColumn> columns = null;
List<IMetadataTable> metadatas = node.getMetadataList();    
if((metadatas != null) && (metadatas.size() > 0)){
    metadata = metadatas.get(0);
    if(metadata != null){
        columns = metadata.getListColumns();        
    }
}

List<? extends IConnection> conns = node.getOutgoingSortedConnections();
if(conns != null && conns.size() > 0 && conns.get(0).getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
    conn = conns.get(0);
}   

if(columns == null || columns.isEmpty() || conn == null){
	return "";
}
String outRowStruct = codeGenArgument.getRecordStructName(conn);
String connName = conn.getName();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    
 		String contextProvider = ElementParameterParser.getValue(node, "__CONTEXT_PROVIDER__");
 		String url = ElementParameterParser.getValue(node, "__SERVER_URL__");
 		String connFacName = ElementParameterParser.getValue(node, "__CONN_FACTORY_NAME__");
        boolean userIdentity = ElementParameterParser.getBooleanValue(node, "__USER_IDENTITY__");
        String user = ElementParameterParser.getValue(node, "__USER__");
        String from = ElementParameterParser.getValue(node, "__FROM__");
        String messageSelector = ElementParameterParser.getValue(node, "__MSG_SELECTOR__");
        List<Map<String, String>> advProps = ElementParameterParser.getTableValue(node, "__ADVANCED_PROPERTIES__");
        String timeout = ElementParameterParser.getValue(node, "__TIMEOUT__");
        String maxMsg = ElementParameterParser.getValue(node, "__MAX_MSG__");
 		
    stringBuffer.append(TEXT_5);
    stringBuffer.append(url);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(contextProvider);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(connFacName);
    stringBuffer.append(TEXT_8);
    if(userIdentity){
    stringBuffer.append(TEXT_9);
    stringBuffer.append(userIdentity);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(user);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(ElementParameterParser.getPasswordValue(node, "__PASS__"));
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(from);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(messageSelector);
    stringBuffer.append(TEXT_15);
    
        if(advProps.size() > 0){
        	for(Map<String, String> item : advProps){
        	
    stringBuffer.append(TEXT_16);
    stringBuffer.append(item.get("PROPERTY"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(item.get("VALUE"));
    stringBuffer.append(TEXT_18);
     
        	} 
        }
        
    stringBuffer.append(TEXT_19);
    stringBuffer.append("-1".equals(timeout)?0:timeout);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(maxMsg);
    stringBuffer.append(TEXT_21);
    
        TSetKeystoreUtil tSetKeystoreUtil = new TSetKeystoreUtil(node);
        if(tSetKeystoreUtil.useHTTPS()){
        
    stringBuffer.append(TEXT_22);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(tSetKeystoreUtil.getTrustStoreType());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_25);
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_26);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_29);
    
        	}
        }
        
    stringBuffer.append(TEXT_30);
    
        String processingMode = ElementParameterParser.getValue(node, "__PROCESSING_MODE__");
        String msgType = ElementParameterParser.getValue(node, "__MSGTYPE__");	
		if(tSetKeystoreUtil.useHTTPS()){
		
    stringBuffer.append(TEXT_31);
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    if(!tSetKeystoreUtil.needVerifyName()){
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    
    	}
    	
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
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
    stringBuffer.append(msgType);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(outRowStruct);
    stringBuffer.append(TEXT_61);
    
    			if("RAW".equals(processingMode)){
        		
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    
    			}else{
    				if("id_Document".equals(metadata.getColumn("messageContent").getTalendType())){
        			
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    
    				}else{
        			
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    
    				}
    			}
    		    
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    return stringBuffer.toString();
  }
}
