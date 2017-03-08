package org.talend.designer.codegen.translators.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

public class TRowGeneratorSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TRowGeneratorSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRowGeneratorSparkstreamingcodeJava result = new TRowGeneratorSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "public static class CustomReceiver_";
  protected final String TEXT_2 = " extends org.apache.spark.streaming.receiver.Receiver<";
  protected final String TEXT_3 = "> {" + NL + "" + NL + "    private long repetitionInterval;" + NL + "    private int nbRows;" + NL + "    private ";
  protected final String TEXT_4 = "Randomizer randomizer;" + NL + "" + NL + "    public CustomReceiver_";
  protected final String TEXT_5 = "(long repetitionInterval, int nbRows, JobConf job) {" + NL + "        super(org.apache.spark.storage.StorageLevel.MEMORY_AND_DISK());" + NL + "        this.repetitionInterval = repetitionInterval < 1 ? 1 : repetitionInterval;" + NL + "        this.nbRows = nbRows;" + NL + "        this.randomizer = new ";
  protected final String TEXT_6 = "Randomizer(job);" + NL + "    }" + NL + "" + NL + "    private ";
  protected final String TEXT_7 = " generateRecord(){";
  protected final String TEXT_8 = NL + "        ";
  protected final String TEXT_9 = " record = new ";
  protected final String TEXT_10 = "();" + NL + "        Object value = null;";
  protected final String TEXT_11 = NL + "            value = randomizer.getRandom";
  protected final String TEXT_12 = "();";
  protected final String TEXT_13 = NL + "                if(value != null){";
  protected final String TEXT_14 = NL + "                        record.";
  protected final String TEXT_15 = " = java.nio.ByteBuffer.wrap((byte[]) value);";
  protected final String TEXT_16 = NL + "                        record.";
  protected final String TEXT_17 = " = (";
  protected final String TEXT_18 = ") value;";
  protected final String TEXT_19 = NL + "                }";
  protected final String TEXT_20 = NL + "    return record;" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void onStart() {" + NL + "        new Thread()  {" + NL + "              @Override " + NL + "              public void run() {" + NL + "                try {" + NL + "                    receive();" + NL + "                } catch (InterruptedException e) {" + NL + "                    e.printStackTrace();" + NL + "                }" + NL + "              }" + NL + "            }.start();" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public void onStop() {" + NL + "    }" + NL + "" + NL + "    private void receive() throws InterruptedException{" + NL + "    \t  randomizer.loadContext();" + NL + "        while(true){" + NL + "            Thread.sleep(repetitionInterval);" + NL + "            writeRecords();" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void writeRecords(){" + NL + "        for(int i=0; i<nbRows; i++){" + NL + "            store(generateRecord());" + NL + "        }" + NL + "    }" + NL + "}" + NL;
  protected final String TEXT_21 = NL + "public static class ";
  protected final String TEXT_22 = "Randomizer implements Serializable{" + NL + "" + NL + "\tprivate final ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_23 = "Randomizer(JobConf job) {" + NL + "\t\tthis.context = new ContextProperties(job);" + NL + "\t}" + NL + "\t" + NL + "\tpublic void loadContext() {" + NL + "\t\tif(context != null) {" + NL + "\t\t\tcontext.loadValue(null, null);" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_24 = NL + "        public ";
  protected final String TEXT_25 = " getRandom";
  protected final String TEXT_26 = "() {";
  protected final String TEXT_27 = NL + "                return ";
  protected final String TEXT_28 = ";";
  protected final String TEXT_29 = NL + "                ";
  protected final String TEXT_30 = "[] ";
  protected final String TEXT_31 = "Table = new ";
  protected final String TEXT_32 = "[] { ";
  protected final String TEXT_33 = " };" + NL + "                java.util.Random random";
  protected final String TEXT_34 = " = new java.util.Random();" + NL + "                return ";
  protected final String TEXT_35 = "Table[random";
  protected final String TEXT_36 = ".nextInt(";
  protected final String TEXT_37 = "Table.length)];";
  protected final String TEXT_38 = " " + NL + "                return ";
  protected final String TEXT_39 = ";";
  protected final String TEXT_40 = NL + "        }";
  protected final String TEXT_41 = NL + "}";
  protected final String TEXT_42 = NL + "public static class ";
  protected final String TEXT_43 = "_mapToPair implements org.apache.spark.api.java.function.PairFunction<";
  protected final String TEXT_44 = ", NullWritable, ";
  protected final String TEXT_45 = "> {" + NL + "" + NL + "    private ContextProperties context = null;" + NL + "" + NL + "    public ";
  protected final String TEXT_46 = "_mapToPair(JobConf job) {" + NL + "                this.context = new ContextProperties(job);" + NL + "            }" + NL + "    " + NL + "    public scala.Tuple2<NullWritable, ";
  protected final String TEXT_47 = "> call(";
  protected final String TEXT_48 = " data){" + NL + "        return new scala.Tuple2<NullWritable, ";
  protected final String TEXT_49 = ">(NullWritable.get(), data);" + NL + "    }" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final List<Map<String, String>> tableValues = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__VALUES__");

List< ? extends IConnection> connections = node.getOutgoingConnections();
if (connections == null || connections.size() == 0)
    return "";

IConnection connection = connections.get(0);
String outStruct = codeGenArgument.getRecordStructName(connection);

List<IMetadataTable> metadatas = node.getMetadataList();
IMetadataTable metadata = null;
if(metadatas != null && metadatas.size() > 0){
        metadata = metadatas.get(0);
}

List<IMetadataColumn> columns = metadata.getListColumns(); 
String connName = null;
List<? extends IConnection> conns = node.getOutgoingConnections();
IConnection outConn = null;
for(IConnection conn : conns){
    if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){
        outConn = conn;
        connName = conn.getName();
    }
}
if(metadata == null || connName == null || columns.size() == 0){
        return "";
    }


    
// Using a custom streaming receiver to simualte a configurable streaming source that generates rows according to the row generator parameters
// More info : http://spark.apache.org/docs/1.3.0/streaming-custom-receivers.html

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_10);
    
        for(IMetadataColumn column : columns){
            String columnName = column.getLabel();
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
            String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
            
    stringBuffer.append(TEXT_11);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_12);
    
            boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
            if(!isPrimitive){
            
    stringBuffer.append(TEXT_13);
    
            }
                    if(javaType == JavaTypesManager.BYTE_ARRAY) {
                    
    stringBuffer.append(TEXT_14);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_15);
    
                    } else {
                    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable()));
    stringBuffer.append(TEXT_18);
    
                    }
            if(!isPrimitive){
            
    stringBuffer.append(TEXT_19);
    
            }
        }

    stringBuffer.append(TEXT_20);
    
// Generate Random fields using the generator class

    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    
    for(int i = 0; i < tableValues.size(); i++){
        Map<String, String> lineValue = tableValues.get(i);
        //lineValue.get("ARRAY")
        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_26);
    
            /* if column parameter looks like abcd(efgh,...) )  */
            if(lineValue.get("ARRAY").indexOf("(") >0){
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_28);
     
            /* else if parameter is  separated by , */
            }else if (lineValue.get("ARRAY").indexOf(",") >0){
            
    stringBuffer.append(TEXT_29);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(columns.get(i).getTalendType(), columns.get(i).isNullable()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columns.get(i).getLabel());
    stringBuffer.append(TEXT_37);
    
            }else{
            
    stringBuffer.append(TEXT_38);
    stringBuffer.append(lineValue.get("ARRAY"));
    stringBuffer.append(TEXT_39);
    
            }
            
    stringBuffer.append(TEXT_40);
    
    }
    
    stringBuffer.append(TEXT_41);
    
// map Values to KeyValues

    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_49);
    

    return stringBuffer.toString();
  }
}
