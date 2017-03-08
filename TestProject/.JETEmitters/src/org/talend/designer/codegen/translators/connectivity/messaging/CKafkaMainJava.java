package org.talend.designer.codegen.translators.connectivity.messaging;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IElementParameter;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.config.CamelEndpointBuilder;
import java.util.List;
import java.util.Map;

public class CKafkaMainJava
{
  protected static String nl;
  public static synchronized CKafkaMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    CKafkaMainJava result = new CKafkaMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            .to(";
  protected final String TEXT_2 = ")";
  protected final String TEXT_3 = NL + "        from(";
  protected final String TEXT_4 = ")";
  protected final String TEXT_5 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode) codeGenArgument.getArgument();
    String cid = node.getUniqueName();
    
    CamelEndpointBuilder builder = CamelEndpointBuilder.getBuilder();
    
    builder.setComponent("kafka");
    
    builder.useDoubleSlash(false);
    
    List< ? extends IConnection> conns = node.getIncomingConnections();
    
    String brokerList = ElementParameterParser.getValue(node, "__BROKER_LIST__");
    
    String topic = ElementParameterParser.getValue(node, "__TOPIC__").trim();
    
    builder.addParam("topic", topic);
    
    if(conns.size()>0){
        builder.setName(brokerList);
    }else{
        
        String zookeeperConnect = ElementParameterParser.getValue(node, "__ZOOKEEPER_CONNECT__");

        builder.addParam("zookeeperConnect", zookeeperConnect);
    
        String zookeeperSessionTimeoutMs = ElementParameterParser.getValue(node, "__ZOOKEEPER_SESSION_TIMEOUT_MS__");
        
        builder.addParam("zookeeperSessionTimeoutMs", zookeeperSessionTimeoutMs);
        
        String zookeeperConnectionTimeoutMs = ElementParameterParser.getValue(node, "__ZOOKEEPER_CONNECTION_TIMEOUT_MS__");
        
        builder.addParam("zookeeperConnectionTimeoutMs", zookeeperConnectionTimeoutMs);
        
        String zookeeperSynctimeMs = ElementParameterParser.getValue(node, "__ZOOKEEPER_SYNC_TIME_MS__");
        
        builder.addParam("zookeeperSynctimeMs", zookeeperSynctimeMs);
    }
    
    String clientId = ElementParameterParser.getValue(node, "__CLIENT_ID__");
    
    builder.addParam("clientId", clientId);
    
    if(conns.size()>0){
        
        String partitioner = ElementParameterParser.getValue(node, "__PARTITIONER__").trim();
        
        if (!"\"\"".equals(partitioner)) {
            builder.addParam("partitioner", partitioner);
        }
        
        String producerType = ElementParameterParser.getValue(node, "__PRODUCER_TYPE__").trim();
        if (!"\"\"".equals(producerType)) {
            builder.addParam("producerType", "\""+producerType+"\"");
        }
    
        boolean compressedTopicsOps = ElementParameterParser.getBooleanValue(node, "__COMPRESSED_TOPICS_OPS__");
        
        String compressedTopics = ElementParameterParser.getValue(node, "__COMPRESSED_TOPICS__").trim();
        if (compressedTopicsOps && !"\"\"".equals(compressedTopics)) {
            builder.addParam("compressedTopics", compressedTopics);
        }
    
        String compressionCodec = ElementParameterParser.getValue(node, "__COMPRESSION_CODEC__").trim();
        if (compressedTopicsOps && !"\"\"".equals(compressionCodec)) {
            builder.addParam("compressionCodec", "\""+compressionCodec+"\"");
        }
    
        String messageSendMaxRetries = ElementParameterParser.getValue(node, "__MESSAGE_SEND_MAX_RETRIES__").trim();
        if (!"\"\"".equals(messageSendMaxRetries)) {
            builder.addParam("messageSendMaxRetries", messageSendMaxRetries);
        }
    
        String retryBackoffMs = ElementParameterParser.getValue(node, "__RETRY_BACKOFF_MS__").trim();
        if (!"\"\"".equals(retryBackoffMs)) {
            builder.addParam("retryBackoffMs", retryBackoffMs);
        }
    
        String topicMetadataRefreshIntervalMs = ElementParameterParser.getValue(node, "__TOPIC_METADATA_REFRESH_INTERVAL_MS__").trim();
        if (!"\"\"".equals(topicMetadataRefreshIntervalMs)) {
            builder.addParam("topicMetadataRefreshIntervalMs", topicMetadataRefreshIntervalMs);
        }
    
        if("async".equals(producerType)){
        
            String queueBufferingMaxMs = ElementParameterParser.getValue(node, "__QUEUE_BUFFERING_MAX_MS__").trim();
            if (!"\"\"".equals(queueBufferingMaxMs)) {
                builder.addParam("queueBufferingMaxMs", queueBufferingMaxMs);
            }
        
            String queueBufferingMaxMessages = ElementParameterParser.getValue(node, "__QUEUE_BUFFERING_MAX_MESSAGES__").trim();
            if (!"\"\"".equals(queueBufferingMaxMessages)) {
                builder.addParam("queueBufferingMaxMessages", queueBufferingMaxMessages);
            }
        
            String queueEnqueueTimeoutMs = ElementParameterParser.getValue(node, "__QUEUE_ENQUEUE_TIMEOUT_MS__").trim();
            if (!"\"\"".equals(queueEnqueueTimeoutMs)) {
                builder.addParam("queueEnqueueTimeoutMs", queueEnqueueTimeoutMs);
            }
        
            String batchNumMessages = ElementParameterParser.getValue(node, "__BATCH_NUM_MESSAGES__").trim();
            if (!"\"\"".equals(batchNumMessages)) {
                builder.addParam("batchNumMessages", batchNumMessages);
            }
        
            String serializerClass = ElementParameterParser.getValue(node, "__SERIALIZER_CLASS__").trim();
            if (!"\"\"".equals(serializerClass)) {
                builder.addParam("serializerClass", serializerClass);
            }
        
            String keySerializerClass = ElementParameterParser.getValue(node, "__KEY_SERIALIZER_CLASS__").trim();
            if (!"\"\"".equals(keySerializerClass)) {
                builder.addParam("keySerializerClass", keySerializerClass);
            }
        }else{
            String sendBufferBytes = ElementParameterParser.getValue(node, "__SEND_BUFFER_BYTES__").trim();
            if (!"\"\"".equals(sendBufferBytes)) {
                builder.addParam("sendBufferBytes", sendBufferBytes);
            }
        
            String requestRequiredAcks = ElementParameterParser.getValue(node, "__REQUEST_REQUIRED_ACKS__").trim();
            if (!"\"\"".equals(requestRequiredAcks)) {
                builder.addParam("requestRequiredAcks", requestRequiredAcks);
            }
        
            String requestTimeoutMs = ElementParameterParser.getValue(node, "__REQUEST_TIMEOUT_MS__").trim();
            if (!"\"\"".equals(requestTimeoutMs)) {
                builder.addParam("requestTimeoutMs", requestTimeoutMs);
            }
        }
    
    }else{
    
            String groupId = ElementParameterParser.getValue(node, "__GROUP_ID_C__").trim();
    
            if (!"\"\"".equals(groupId)) {
                builder.addParam("groupId", groupId);
            }
            
            String consumerId = ElementParameterParser.getValue(node, "__CONSUMER_ID__").trim();
            if (!"\"\"".equals(consumerId)) {
                builder.addParam("consumerId", consumerId);
            }
            
            String consumerStreams = ElementParameterParser.getValue(node, "__CONSUMER_STREAMS__").trim();
            if (!"\"\"".equals(consumerStreams)) {
                builder.addParam("consumerStreams", consumerStreams);
            }
            
            String consumersCount = ElementParameterParser.getValue(node, "__CONSUMERS_COUNT__").trim();
            if (!"\"\"".equals(consumersCount)) {
                builder.addParam("consumersCount", consumersCount);
            }
            
            String batchSize = ElementParameterParser.getValue(node, "__BATCH_SIZE__").trim();
            if (!"\"\"".equals(batchSize)) {
                builder.addParam("batchSize", batchSize);
            }
            
            String barrierAwaitTimeoutMs = ElementParameterParser.getValue(node, "__BARRIER_AWAIT_TIMEOUT_MS__").trim();
            if (!"\"\"".equals(barrierAwaitTimeoutMs)) {
                builder.addParam("barrierAwaitTimeoutMs", barrierAwaitTimeoutMs);
            }
            
            String consumerTimeoutMs = ElementParameterParser.getValue(node, "__CONSUMER_TIMEOUT_MS__").trim();
            if (!"\"\"".equals(consumerTimeoutMs)) {
                builder.addParam("consumerTimeoutMs", consumerTimeoutMs);
            }
            
            String socketTimeoutMs = ElementParameterParser.getValue(node, "__SOCKET_TIMEOUT_MS__").trim();
            if (!"\"\"".equals(socketTimeoutMs)) {
                builder.addParam("socketTimeoutMs", socketTimeoutMs);
            }
        
            String socketReceiveBufferBytes = ElementParameterParser.getValue(node, "__SOCKET_RECEIVE_BUFFER_BYTES__").trim();
            if (!"\"\"".equals(socketReceiveBufferBytes)) {
                builder.addParam("socketReceiveBufferBytes", socketReceiveBufferBytes);
            }
        
            String fetchMessageMaxBytes = ElementParameterParser.getValue(node, "__FETCH_MESSAGE_MAX_BYTES__").trim();
            if (!"\"\"".equals(fetchMessageMaxBytes)) {
                builder.addParam("fetchMessageMaxBytes", fetchMessageMaxBytes);
            }
        
            String autoCommitEnable = ElementParameterParser.getValue(node, "__AUTO_COMMIT_ENABLE__").trim();
            if (!"\"\"".equals(autoCommitEnable)) {
                builder.addParam("autoCommitEnable", autoCommitEnable);
            }
        
            String autoCommitIntervalMs = ElementParameterParser.getValue(node, "__AUTO_COMMIT_INTERVAL_MS__").trim();
            if (!"\"\"".equals(autoCommitIntervalMs)) {
                builder.addParam("autoCommitIntervalMs", autoCommitIntervalMs);
            }

            String queuedMaxMessages = ElementParameterParser.getValue(node, "__QUEUED_MAX_MESSAGES__").trim();
            if (!"\"\"".equals(queuedMaxMessages)) {
                builder.addParam("queuedMaxMessages", queuedMaxMessages);
            }

            String rebalanceMaxRetries = ElementParameterParser.getValue(node, "__REBALANCE_MAX_RETRIES__").trim();
            if (!"\"\"".equals(rebalanceMaxRetries)) {
                builder.addParam("rebalanceMaxRetries", rebalanceMaxRetries);
            }

            String fetchMinBytes = ElementParameterParser.getValue(node, "__FETCH_MIN_BYTES__").trim();
            if (!"\"\"".equals(fetchMinBytes)) {
                builder.addParam("fetchMinBytes", fetchMinBytes);
            }

            String fetchWaitMaxMs = ElementParameterParser.getValue(node, "__FETCH_WAIT_MAX_MS__").trim();
            if (!"\"\"".equals(fetchWaitMaxMs)) {
                builder.addParam("fetchWaitMaxMs", fetchWaitMaxMs);
            }

            String rebalanceBackoffMs = ElementParameterParser.getValue(node, "__REBALANCE_BACKOFF_MS__").trim();
            if (!"\"\"".equals(rebalanceBackoffMs)) {
                builder.addParam("rebalanceBackoffMs", rebalanceBackoffMs);
            }

            String refreshLeaderBackoffMs = ElementParameterParser.getValue(node, "__REFRESH_LEADER_BACKOFF_MS__").trim();
            if (!"\"\"".equals(refreshLeaderBackoffMs)) {
                builder.addParam("refreshLeaderBackoffMs", refreshLeaderBackoffMs);
            }

            String autoOffsetReset = ElementParameterParser.getValue(node, "__AUTO_OFFSET_RESET__").trim();
            if (!"\"\"".equals(autoOffsetReset)) {
                builder.addParam("autoOffsetReset", "\""+autoOffsetReset+"\"");
            }
    }

    
    List<Map<String, String>> tableValues = (List<Map<String, String>>) ElementParameterParser.getObjectValue(node, "__URI_OPTIONS__");
    for (Map<String, String> map : tableValues) {
        String argName = map.get("NAME").trim();
        String argValue = map.get("VALUE").trim();
        if(argName.startsWith("\"") && argName.endsWith("\"") && argName.length() >= 2) {
            argName = argName.substring(1, argName.length() - 1);
        }
        builder.addParam(argName, argValue);
    }

    String uri = builder.build();

    
    
    if(conns.size()>0) {

    stringBuffer.append(TEXT_1);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_2);
    
    } else {

    stringBuffer.append(TEXT_3);
    stringBuffer.append(uri);
    stringBuffer.append(TEXT_4);
    
    }

    stringBuffer.append(TEXT_5);
    return stringBuffer.toString();
  }
}
