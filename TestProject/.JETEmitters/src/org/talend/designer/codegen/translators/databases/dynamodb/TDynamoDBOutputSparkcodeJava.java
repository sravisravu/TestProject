package org.talend.designer.codegen.translators.databases.dynamodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
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

public class TDynamoDBOutputSparkcodeJava
{
  protected static String nl;
  public static synchronized TDynamoDBOutputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDynamoDBOutputSparkcodeJava result = new TDynamoDBOutputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = "OutputFormat<KeyT> extends org.apache.hadoop.dynamodb.write.AbstractDynamoDBOutputFormat<KeyT, ";
  protected final String TEXT_3 = "> {" + NL + "    public RecordWriter<KeyT, ";
  protected final String TEXT_4 = "> getRecordWriter(FileSystem ignored, JobConf job, String name," + NL + "            Progressable progress) throws IOException {" + NL + "        return new ";
  protected final String TEXT_5 = "RecordWriter<KeyT>(job, progress);" + NL + "    }" + NL + "" + NL + "    public static class ";
  protected final String TEXT_6 = "RecordWriter<KeyT> extends org.apache.hadoop.dynamodb.write.AbstractDynamoDBRecordWriter<KeyT, ";
  protected final String TEXT_7 = "> {" + NL + "        public ";
  protected final String TEXT_8 = "RecordWriter(JobConf jobConf, Progressable progress) throws IOException {" + NL + "            super(jobConf, progress);" + NL + "        }" + NL + "" + NL + "        public org.apache.hadoop.dynamodb.DynamoDBItemWritable convertValueToDynamoDBItem(KeyT key, ";
  protected final String TEXT_9 = " value) {" + NL + "            org.apache.hadoop.dynamodb.DynamoDBItemWritable w = new org.apache.hadoop.dynamodb.DynamoDBItemWritable();" + NL + "            w.setItem(new java.util.HashMap<String, com.amazonaws.services.dynamodbv2.model.AttributeValue>());";
  protected final String TEXT_10 = NL + "                {" + NL + "                    com.amazonaws.services.dynamodbv2.model.AttributeValue av = null;";
  protected final String TEXT_11 = NL + "                        if (null != value.";
  protected final String TEXT_12 = ") {";
  protected final String TEXT_13 = NL + "                        av = new com.amazonaws.services.dynamodbv2.model.AttributeValue().withN(" + NL + "                                String.valueOf(value.";
  protected final String TEXT_14 = "));";
  protected final String TEXT_15 = NL + "                        av =  new com.amazonaws.services.dynamodbv2.model.AttributeValue().withBOOL(" + NL + "                                value.";
  protected final String TEXT_16 = ");";
  protected final String TEXT_17 = NL + "                        av =  new com.amazonaws.services.dynamodbv2.model.AttributeValue().withB(" + NL + "                                value.";
  protected final String TEXT_18 = ");";
  protected final String TEXT_19 = NL + "                        av =  new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS(" + NL + "                                BigDataParserUtils.parseTo_String(value.";
  protected final String TEXT_20 = ", ";
  protected final String TEXT_21 = "));";
  protected final String TEXT_22 = NL + "                        av =  new com.amazonaws.services.dynamodbv2.model.AttributeValue().withS(" + NL + "                                String.valueOf(value.";
  protected final String TEXT_23 = "));";
  protected final String TEXT_24 = NL + "                        }";
  protected final String TEXT_25 = NL + "                    if (av != null)" + NL + "                        w.getItem().put(\"";
  protected final String TEXT_26 = "\", av);" + NL + "                }";
  protected final String TEXT_27 = NL + "            return w;" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_28 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    
List<IMetadataTable> metadatas = node.getMetadataList();
List< ? extends IConnection> connections = node.getIncomingConnections();
if (connections == null || connections.size() == 0 || metadatas == null || metadatas.size() == 0)
    return "";

String rowName = connections.get(0).getName();
String rowStruct = codeGenArgument.getRecordStructName(connections.get(0));

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_9);
    
            for (IMetadataColumn column : metadatas.get(0).getListColumns()) {
                
    stringBuffer.append(TEXT_10);
    
                    // Get information about the column to send.
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

                    if (column.isNullable()) {
                        
    stringBuffer.append(TEXT_11);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_12);
    
                    }

                    // Finish the above statement differently depending on the type.
                    if (javaType == JavaTypesManager.BIGDECIMAL
                            || javaType == JavaTypesManager.LONG
                            || javaType == JavaTypesManager.INTEGER
                            || javaType == JavaTypesManager.SHORT
                            || javaType == JavaTypesManager.BYTE
                            || javaType == JavaTypesManager.DOUBLE
                            || javaType == JavaTypesManager.FLOAT) {
                        
    stringBuffer.append(TEXT_13);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_14);
    
                    } else if (javaType == JavaTypesManager.BOOLEAN) {
                        
    stringBuffer.append(TEXT_15);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_16);
    
                    } else if (javaType == JavaTypesManager.BYTE_ARRAY) {
                        
    stringBuffer.append(TEXT_17);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_18);
    
                    } else if (javaType == JavaTypesManager.DATE) {
                        
    stringBuffer.append(TEXT_19);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_21);
    
                    } else {
                        
    stringBuffer.append(TEXT_22);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_23);
    
                    }

                    if (column.isNullable()) {
                        
    stringBuffer.append(TEXT_24);
    
                    }
                    
    stringBuffer.append(TEXT_25);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_26);
    
            }
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
