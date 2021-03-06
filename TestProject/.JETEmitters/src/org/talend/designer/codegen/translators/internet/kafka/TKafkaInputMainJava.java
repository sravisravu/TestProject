package org.talend.designer.codegen.translators.internet.kafka;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TKafkaInputMainJava
{
  protected static String nl;
  public static synchronized TKafkaInputMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TKafkaInputMainJava result = new TKafkaInputMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL;
  protected final String TEXT_3 = NL + "\t";
  protected final String TEXT_4 = NL;
  protected final String TEXT_5 = NL + NL + "kafka.message.MessageAndMetadata<byte[], ";
  protected final String TEXT_6 = "> ";
  protected final String TEXT_7 = "_messageAndMetadata = ";
  protected final String TEXT_8 = "_consumerIterator.next();";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = " = ";
  protected final String TEXT_11 = "_messageAndMetadata.message();";
  protected final String TEXT_12 = NL + "    ";
  protected final String TEXT_13 = ".offset = ";
  protected final String TEXT_14 = "_messageAndMetadata.offset();";
  protected final String TEXT_15 = NL + "\t";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = " = ";
  protected final String TEXT_20 = "_consumerRecord.value();";
  protected final String TEXT_21 = NL + "    ";
  protected final String TEXT_22 = ".offset = ";
  protected final String TEXT_23 = "_consumerRecord.offset();";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
class TSetKeystoreUtil {

    private static final String USE_HTTPS = "__USE_HTTPS__"; 

    private static final String HTTPS_SETTING = "__HTTPS_SETTING__"; 

    private static final String TRUSTSTORE_TYPE = "__TRUSTSTORE_TYPE__"; 

    private static final String SSL_TRUSTSERVER_TRUSTSTORE = "__SSL_TRUSTSERVER_TRUSTSTORE__"; 

    private static final String VERIFY_NAME = "__VERIFY_NAME__"; 

    private static final String SSL_KEYSTORE_PASSWORD = "__SSL_KEYSTORE_PASSWORD__"; 

    private static final String SSL_KEYSTORE = "__SSL_KEYSTORE__"; 

    private static final String KEYSTORE_TYPE = "__KEYSTORE_TYPE__"; 

    private static final String NEED_CLIENT_AUTH = "__NEED_CLIENT_AUTH__"; 

    private static final String SSL_TRUSTSERVER_PASSWORD = "__SSL_TRUSTSERVER_PASSWORD__"; 

    private INode node;

    public TSetKeystoreUtil(INode node) {
        if (ElementParameterParser.getBooleanValue(node, USE_HTTPS)) {
            this.node = ElementParameterParser.getLinkedNodeValue(node, HTTPS_SETTING);
        }
    }

    public boolean useHTTPS() {
        return node != null;
    }

    public String getTrustStoreType() {
        return ElementParameterParser.getWrapDoubleQuoteValue(node, TRUSTSTORE_TYPE);
    }

    public String getTrustStorePath() {
        return ElementParameterParser.getValue(node, SSL_TRUSTSERVER_TRUSTSTORE);
    }

    public String getTrustStorePassword() {
        return ElementParameterParser.getPasswordValue(node, SSL_TRUSTSERVER_PASSWORD);
    }

    public boolean needClientAuth() {
        return ElementParameterParser.getBooleanValue(node, NEED_CLIENT_AUTH);
    }

    public String getKeyStoreType() {
        return ElementParameterParser.getWrapDoubleQuoteValue(node, KEYSTORE_TYPE);
    }

    public String getKeyStorePath() {
        return ElementParameterParser.getValue(node, SSL_KEYSTORE);
    }

    public String getKeyStorePassword() {
        return ElementParameterParser.getPasswordValue(node, SSL_KEYSTORE_PASSWORD);
    }

    public boolean needVerifyName() {
        return ElementParameterParser.getBooleanValue(node, VERIFY_NAME);
    }
}

    

class TKafkaInputUtil {

    private INode node;

    private INode connNode;
    
    private TSetKeystoreUtil tSetKeystoreUtil;

    private IConnection outgoingConnection;

    private Map<String, String> kafkaConsumerProperties;

    private boolean useExistingConn;

    public TKafkaInputUtil(INode node) {
        this.node = node;
        this.connNode = Boolean.valueOf(ElementParameterParser.getValue(node, "__USE_EXISTING_CONNECTION__"))
            ? ElementParameterParser.getLinkedNodeValue(node, "__CONNECTION__")
            : node;
        this.tSetKeystoreUtil = new TSetKeystoreUtil(connNode);
        this.outgoingConnection = findOutgoingConnection();
        this.kafkaConsumerProperties = findKafkaConsumerProperties();
    }

    public String getOutputType() {
        return ElementParameterParser.getValue(node, "__OUTPUT_TYPE__");
    }

    public String getZookeeperConnect() {
        return ElementParameterParser.getValue(connNode, "__ZOOKEEPER_CONNECT__");
    }
    
    public String getBrokerList() {
        return ElementParameterParser.getValue(connNode, "__BROKER_LIST__");
    }

    public String getTopic() {
        return ElementParameterParser.getValue(node, "__KAFKA_TOPIC__");
    }

    public String getGroupId() {
        return ElementParameterParser.getValue(node, "__GROUP_ID__");
    }

    public boolean isResetNewConsumerGroup() {
        return "true".equals(ElementParameterParser.getValue(node, "__RESET_OFFSET__"));
    }

    public String getAutoOffsetResetNew() {
        return ElementParameterParser.getValue(node, "__AUTO_OFFSET_RESET_NEW__").toLowerCase();
	 }
	 
	 public String getAutoOffsetReset() {
        return ElementParameterParser.getValue(node, "__AUTO_OFFSET_RESET__").toLowerCase();
	 }

    public String getOffsetStorage() {
        return ElementParameterParser.getValue(node, "__KAFKA_OFFSET_STORAGE__").toLowerCase();
    }

    public boolean isDualCommit() {
        return "kafka".equals(getOffsetStorage()) && "true".equals(ElementParameterParser.getValue(node, "__KAFKA_DUAL_COMMIT_CHECK__"));
    }

    public boolean isAutoCommitOffset() {
        return "true".equals(ElementParameterParser.getValue(node, "__AUTO_COMMIT_OFFSET__"));
    }

    public String getAutoCommitInterval() {
        return ElementParameterParser.getValue(node, "__KAFKA_COMMIT_INTERVAL__");
    }

    public boolean isStopOnMaxDuration() {
        return "true".equals(ElementParameterParser.getValue(node, "__USE_BATCH_MAX_DURATION__"));
    }

    public String getMaxDuration() {
        return ElementParameterParser.getValue(node, "__BATCH_MAX_DURATION__");
    }

    public boolean isStopOnMaxSize() {
        return "true".equals(ElementParameterParser.getValue(node, "__USE_BATCH_MAX_SIZE__"));
    }

    public String getMaxSize() {
        return ElementParameterParser.getValue(node, "__BATCH_MAX_SIZE__");
    }

    public boolean isStopOnMaxMsgWait() {
        return "true".equals(ElementParameterParser.getValue(node, "__USE_BATCH_MESSAGE_TIMEOUT__"));
    }

    public String getMaxMsgWait() {
        return ElementParameterParser.getValue(node, "__BATCH_MESSAGE_TIMEOUT__");
    }

    public String getConsumerTimeout() {
        if (isStopOnMaxDuration()) {
            return getPrecision();
        } else if (isStopOnMaxMsgWait()) {
            return getMaxMsgWait();
        } else if (!isKafkaVersion("KAFKA_0_8_2_0")) {
            return getPrecision();
        }
        // If we're not stopping, then don't ever wait for consumer timeout.
        return "-1";
    }

    public String getEncoding() {
        String encoding = ElementParameterParser.getValue(node, "__ENCODING__");
        if (encoding == null || "".equals(encoding)) {
            encoding = "\"UTF-8\"";
        }
        return encoding;
   }

    public Map<String, String> getKafkaConsumerProperties() {
        return this.kafkaConsumerProperties;
    }

    public String getOutStructName() {
        return this.outgoingConnection.getName() + "Struct";
    }

    public String getOutgoingConnectionName() {
        return this.outgoingConnection.getName();
    }

    public String getOutgoingColumnName() {
        return this.outgoingConnection.getMetadataTable().getListColumns().get(0).getLabel();
    }
    
    private String getPrecision() {
			String precision = ElementParameterParser.getValue(node, "__KAFKA_CONSUMER_TIMEOUT__");
			return "\"-1\"".equals(precision) || "-1".equals(precision) ? "1000" : precision;
    }

    private String getIntProperty(String inputMaybeNumeric) {
        if (inputMaybeNumeric.matches("-?\\d+"))
            return "\"" + inputMaybeNumeric + "\"";
        return inputMaybeNumeric;
    }
    
    public boolean isKafkaVersion(String version) {
			return (version != null) ? version.equals(ElementParameterParser.getValue(node, "__KAFKA_VERSION__")) : false;
    }

    public String getAsLong(String inputMaybeLong) {
        if (inputMaybeLong.matches("-?\\d+[lL]?"))
            return inputMaybeLong;
        if (inputMaybeLong.matches("\"-?\\d+[lL]?\""))
            return inputMaybeLong.substring(1, inputMaybeLong.length() - 1);
        return "Long.valueOf("+inputMaybeLong+")";
    }
    
    public TSetKeystoreUtil getTSetKeystoreUtil() {
        return tSetKeystoreUtil;
    }
    
    public String getSecurityProtocol() {
			if (tSetKeystoreUtil.useHTTPS()) {
				return useKrb() ? "SASL_SSL" : "SSL";
			} else {
				return useKrb() ? "SASL_PLAINTEXT" : "PLAINTEXT";
			}
    }
    
    public boolean useKrb() {
			return "true".equals(ElementParameterParser.getValue(connNode, "__USE_KRB__"));
    }
    
    public String getJAASConf() {
    		return ElementParameterParser.getValue(connNode, "__JAAS_CONF__");
    }
    
    public String getKrbServiceName() {
    		return ElementParameterParser.getValue(connNode, "__KRB_SERVICE_NAME__");
    }
    
    public boolean isSetKinitPath() {
			return "true".equals(ElementParameterParser.getValue(connNode, "__SET_KINIT_PATH__"));
    }
    
    public String getKinitPath() {
    		return ElementParameterParser.getValue(connNode, "__KINIT_PATH__");
    }
    
    public boolean isSetKrb5Conf() {
			return "true".equals(ElementParameterParser.getValue(connNode, "__SET_KRB5_CONF__"));
    }
    
    public String getKrb5Conf() {
    		return ElementParameterParser.getValue(connNode, "__KRB5_CONF__");
    }

    private Map<String, String> findKafkaConsumerProperties() {
        java.util.Map<String, String> result = new java.util.TreeMap<String, String>();
        if(isKafkaVersion("KAFKA_0_8_2_0")) {
				// Old consumer API configuration
	        result.put("\"auto.commit.enable\"", "\"" + isAutoCommitOffset() + "\"");
	        result.put("\"auto.commit.interval.ms\"", getIntProperty(getAutoCommitInterval()));
	        result.put("\"auto.offset.reset\"", "\"" + getAutoOffsetReset() + "\"");
	        result.put("\"consumer.timeout.ms\"", getIntProperty(getConsumerTimeout()));
	        result.put("\"dual.commit.enabled\"", "\"" + isDualCommit() + "\"");
	        result.put("\"group.id\"", getGroupId());
	        result.put("\"offsets.storage\"", "\"" + getOffsetStorage() + "\"");
	        result.put("\"serializer.encoding\"", getEncoding());
	        result.put("\"zookeeper.connect\"", getZookeeperConnect());
			  fillWithCustomProps(result);
        } else {
				// New consumer API configuration
				result.put("\"enable.auto.commit\"", "\"" + isAutoCommitOffset() + "\"");
	        	result.put("\"auto.commit.interval.ms\"", getIntProperty(getAutoCommitInterval()));
				result.put("\"auto.offset.reset\"", "\"" + getAutoOffsetResetNew() + "\"");
				result.put("\"group.id\"", getGroupId());
				result.put("\"bootstrap.servers\"", getBrokerList());
				result.put("\"security.protocol\"", "\"" + getSecurityProtocol() + "\"");
				fillWithCustomProps(result);
        }
        return result;
   }
   
   private void fillWithCustomProps(java.util.Map<String, String> result) {
   	java.util.List<java.util.Map<String, String>> customProps = (java.util.List<java.util.Map<String, String>>)
				        ElementParameterParser.getObjectValue(node, "__KAFKA_CONSUMER_PROPERTIES__");
		if(customProps != null) {
		    for (java.util.Map<String, String> prop : customProps) {
		        result.put(prop.get("PROPERTY"), prop.get("VALUE"));
		    }
		}
   }

    private IConnection findOutgoingConnection() {
        IConnection result = null;
        List<? extends IConnection> connections = node.getOutgoingConnections();
        for(IConnection connection : connections) {
            if(connection.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                result = connection;
                break;
            }
        }
        return result;
    }
}

    stringBuffer.append(TEXT_2);
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TKafkaInputUtil tKafkaInputUtil = new TKafkaInputUtil(node);

if(tKafkaInputUtil.isKafkaVersion("KAFKA_0_8_2_0")) {

    stringBuffer.append(TEXT_3);
    
	// This is the tKafkaInput_main javajet part for Kafka 0.8.2.x
	
	// Since the new Consumer API was introduced in Kafka 0.9.0.x, we must split the javajets
	// in order to generate code with the relevant Consumer API depending of the current Kafka version.

    stringBuffer.append(TEXT_4);
    
String outStructName = tKafkaInputUtil.getOutStructName();

    stringBuffer.append(TEXT_5);
    stringBuffer.append(outStructName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(tKafkaInputUtil.getOutgoingConnectionName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    
if (ElementParameterParser.getBooleanValue(node, "__SAVE_OFFSET__")) {
    
    stringBuffer.append(TEXT_12);
    stringBuffer.append(tKafkaInputUtil.getOutgoingConnectionName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    
}

    
} else if(tKafkaInputUtil.isKafkaVersion("KAFKA_0_9_0_1")) {

    stringBuffer.append(TEXT_15);
    
	// This is the tKafkaInput_main javajet part for Kafka 0.9.0.x
	
	// Since the new Consumer API was introduced in Kafka 0.9.0.x, we must split the javajets
	// in order to generate code with the relevant Consumer API depending of the current Kafka version.

    stringBuffer.append(TEXT_16);
    
String outStructName = tKafkaInputUtil.getOutStructName();

    stringBuffer.append(TEXT_17);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(tKafkaInputUtil.getOutgoingConnectionName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    
if (ElementParameterParser.getBooleanValue(node, "__SAVE_OFFSET__")) {
    
    stringBuffer.append(TEXT_21);
    stringBuffer.append(tKafkaInputUtil.getOutgoingConnectionName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
}

    
}

    return stringBuffer.toString();
  }
}
