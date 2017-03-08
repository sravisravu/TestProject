package org.talend.designer.codegen.translators.databases.mongodb;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;

public class TMongoDBOutputSparkcodeJava
{
  protected static String nl;
  public static synchronized TMongoDBOutputSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMongoDBOutputSparkcodeJava result = new TMongoDBOutputSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            public static class ";
  protected final String TEXT_2 = "_From";
  protected final String TEXT_3 = "ToBson implements org.apache.spark.api.java.function.PairFunction<";
  protected final String TEXT_4 = ", Object, org.bson.BSONObject>{";
  protected final String TEXT_5 = NL + "            public static class ";
  protected final String TEXT_6 = "_From";
  protected final String TEXT_7 = "ToBson implements org.apache.spark.api.java.function.PairFunction<";
  protected final String TEXT_8 = ", Object, com.mongodb.hadoop.io.MongoUpdateWritable>{";
  protected final String TEXT_9 = NL + "                private java.util.Map<String, String> pathMap_";
  protected final String TEXT_10 = ";" + NL + "                private BsonBuilder_";
  protected final String TEXT_11 = " modifiersBsonBuilder;" + NL + "                private BsonBuilder_";
  protected final String TEXT_12 = " queryBsonBuilder;" + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_13 = "_From";
  protected final String TEXT_14 = "ToBson(JobConf job){" + NL + "                    this.context = new ContextProperties(job);" + NL + "                    modifiersBsonBuilder = new BsonBuilder_";
  protected final String TEXT_15 = "();" + NL + "                    queryBsonBuilder = new BsonBuilder_";
  protected final String TEXT_16 = "();" + NL + "                    pathMap_";
  protected final String TEXT_17 = " = new java.util.HashMap<String, String>();";
  protected final String TEXT_18 = NL + "                        pathMap_";
  protected final String TEXT_19 = ".put(\"";
  protected final String TEXT_20 = "\", ";
  protected final String TEXT_21 = ");";
  protected final String TEXT_22 = NL + "                }" + NL;
  protected final String TEXT_23 = NL + "                    public scala.Tuple2<Object, org.bson.BSONObject> call(";
  protected final String TEXT_24 = " row) {";
  protected final String TEXT_25 = NL + "                    public scala.Tuple2<Object, com.mongodb.hadoop.io.MongoUpdateWritable> call(";
  protected final String TEXT_26 = " row) {" + NL + "                        queryBsonBuilder.setObject(new org.bson.BasicBSONObject());";
  protected final String TEXT_27 = NL + "                        modifiersBsonBuilder.setObject(new org.bson.BasicBSONObject());";
  protected final String TEXT_28 = NL + "                                        if (row.";
  protected final String TEXT_29 = " != null) {";
  protected final String TEXT_30 = NL + "                                                    queryBsonBuilder.putkeyNode(pathMap_";
  protected final String TEXT_31 = ".get(\"";
  protected final String TEXT_32 = "\"),\"";
  protected final String TEXT_33 = "\",((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_34 = "\")).array());";
  protected final String TEXT_35 = NL + "                                                    queryBsonBuilder.putkeyNode(pathMap_";
  protected final String TEXT_36 = ".get(\"";
  protected final String TEXT_37 = "\"),\"";
  protected final String TEXT_38 = "\", row.get(\"";
  protected final String TEXT_39 = "\"));";
  protected final String TEXT_40 = NL + "                                                    modifiersBsonBuilder.";
  protected final String TEXT_41 = "(pathMap_";
  protected final String TEXT_42 = ".get(\"";
  protected final String TEXT_43 = "\"),\"";
  protected final String TEXT_44 = "\", ((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_45 = "\")).array());";
  protected final String TEXT_46 = NL + "                                                    modifiersBsonBuilder.";
  protected final String TEXT_47 = "(pathMap_";
  protected final String TEXT_48 = ".get(\"";
  protected final String TEXT_49 = "\"),\"";
  protected final String TEXT_50 = "\", row.get(\"";
  protected final String TEXT_51 = "\"));";
  protected final String TEXT_52 = NL + "                                                    modifiersBsonBuilder.put(pathMap_";
  protected final String TEXT_53 = ".get(\"";
  protected final String TEXT_54 = "\"),\"";
  protected final String TEXT_55 = "\", ((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_56 = "\")).array());";
  protected final String TEXT_57 = NL + "                                                    modifiersBsonBuilder.put(pathMap_";
  protected final String TEXT_58 = ".get(\"";
  protected final String TEXT_59 = "\"),\"";
  protected final String TEXT_60 = "\", row.get(\"";
  protected final String TEXT_61 = "\"));";
  protected final String TEXT_62 = NL + "                                        }";
  protected final String TEXT_63 = NL + "                                                if(row.get(\"";
  protected final String TEXT_64 = "\") == null){" + NL + "                                                    queryBsonBuilder.putkeyNode(pathMap_";
  protected final String TEXT_65 = ".get(\"";
  protected final String TEXT_66 = "\"),\"";
  protected final String TEXT_67 = "\", null);" + NL + "                                                }else{" + NL + "                                                    queryBsonBuilder.putkeyNode(pathMap_";
  protected final String TEXT_68 = ".get(\"";
  protected final String TEXT_69 = "\"),\"";
  protected final String TEXT_70 = "\",((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_71 = "\")).array());" + NL + "                                                }";
  protected final String TEXT_72 = NL + "                                                queryBsonBuilder.putkeyNode(pathMap_";
  protected final String TEXT_73 = ".get(\"";
  protected final String TEXT_74 = "\"),\"";
  protected final String TEXT_75 = "\", row.get(\"";
  protected final String TEXT_76 = "\"));";
  protected final String TEXT_77 = NL + "                                                if(row.get(\"";
  protected final String TEXT_78 = "\") == null){" + NL + "                                                    modifiersBsonBuilder.";
  protected final String TEXT_79 = "(pathMap_";
  protected final String TEXT_80 = ".get(\"";
  protected final String TEXT_81 = "\"),\"";
  protected final String TEXT_82 = "\",null);" + NL + "                                                }else{" + NL + "                                                    modifiersBsonBuilder.";
  protected final String TEXT_83 = "(pathMap_";
  protected final String TEXT_84 = ".get(\"";
  protected final String TEXT_85 = "\"),\"";
  protected final String TEXT_86 = "\",((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_87 = "\")).array());" + NL + "                                                }";
  protected final String TEXT_88 = NL + "                                                modifiersBsonBuilder.";
  protected final String TEXT_89 = "(pathMap_";
  protected final String TEXT_90 = ".get(\"";
  protected final String TEXT_91 = "\"),\"";
  protected final String TEXT_92 = "\", row.get(\"";
  protected final String TEXT_93 = "\"));";
  protected final String TEXT_94 = NL + "                                                if(row.get(\"";
  protected final String TEXT_95 = "\") == null){" + NL + "                                                    modifiersBsonBuilder.put(pathMap_";
  protected final String TEXT_96 = ".get(\"";
  protected final String TEXT_97 = "\"),\"";
  protected final String TEXT_98 = "\", null);" + NL + "                                                } else {" + NL + "                                                    modifiersBsonBuilder.put(pathMap_";
  protected final String TEXT_99 = ".get(\"";
  protected final String TEXT_100 = "\"),\"";
  protected final String TEXT_101 = "\",((java.nio.ByteBuffer) row.get(\"";
  protected final String TEXT_102 = "\")).array());" + NL + "                                                }";
  protected final String TEXT_103 = NL + "                                                modifiersBsonBuilder.put(pathMap_";
  protected final String TEXT_104 = ".get(\"";
  protected final String TEXT_105 = "\"),\"";
  protected final String TEXT_106 = "\", row.get(\"";
  protected final String TEXT_107 = "\"));";
  protected final String TEXT_108 = NL + "                            return new scala.Tuple2<Object, org.bson.BSONObject>(null, modifiersBsonBuilder.getObject());";
  protected final String TEXT_109 = NL + "                                return new scala.Tuple2<Object, com.mongodb.hadoop.io.MongoUpdateWritable>(null, new com.mongodb.hadoop.io.MongoUpdateWritable(queryBsonBuilder.getObject(), modifiersBsonBuilder.getObject(), false, ";
  protected final String TEXT_110 = "));";
  protected final String TEXT_111 = NL + "                                return new scala.Tuple2<Object, com.mongodb.hadoop.io.MongoUpdateWritable>(null, new com.mongodb.hadoop.io.MongoUpdateWritable(queryBsonBuilder.getObject(), new org.bson.BasicBSONObject(\"$set\", modifiersBsonBuilder.getObject()), false, ";
  protected final String TEXT_112 = "));";
  protected final String TEXT_113 = NL + "                                return new scala.Tuple2<Object, com.mongodb.hadoop.io.MongoUpdateWritable>(null, new com.mongodb.hadoop.io.MongoUpdateWritable(queryBsonBuilder.getObject(), modifiersBsonBuilder.getObject(), true, ";
  protected final String TEXT_114 = "));";
  protected final String TEXT_115 = NL + "                                return new scala.Tuple2<Object, com.mongodb.hadoop.io.MongoUpdateWritable>(null, new com.mongodb.hadoop.io.MongoUpdateWritable(queryBsonBuilder.getObject(), new org.bson.BasicBSONObject(\"$set\", modifiersBsonBuilder.getObject()), true, ";
  protected final String TEXT_116 = "));";
  protected final String TEXT_117 = NL + "                    }" + NL + "            }" + NL + "" + NL + "            public static class BsonBuilder_";
  protected final String TEXT_118 = " implements Serializable {" + NL + "                private org.bson.BasicBSONObject object = null;" + NL + "                // Put value under parent node" + NL + "                public void put(String parentNode, String currentName, Object value) {" + NL + "                    if (parentNode == null || \"\".equals(parentNode)) {" + NL + "                        object.put(currentName, value);" + NL + "                    } else {" + NL + "                        String objNames[]= parentNode.split(\"\\\\.\");" + NL + "                        org.bson.BasicBSONObject lastNode = getParentNode(parentNode, objNames.length-1);" + NL + "                        lastNode.put(currentName, value);" + NL + "                        org.bson.BasicBSONObject parenttNode = null;" + NL + "                        for (int i = objNames.length - 1; i >=0; i--) {" + NL + "                            parenttNode = getParentNode(parentNode, i-1);" + NL + "                            parenttNode.put(objNames[i], lastNode);" + NL + "                            lastNode = (org.bson.BasicBSONObject) parenttNode.clone();" + NL + "                        }" + NL + "                        object=lastNode;" + NL + "                    }" + NL + "                }" + NL + "                // Get node(embedded document) by path configuration" + NL + "                public org.bson.BasicBSONObject getParentNode(String parentNode, int index) {" + NL + "                    org.bson.BasicBSONObject basicBSONObject = object;" + NL + "                    if (parentNode == null || \"\".equals(parentNode)) {" + NL + "                        return object;" + NL + "                    } else {" + NL + "                        String objNames[] = parentNode.split(\"\\\\.\");" + NL + "                        for (int i = 0; i <= index; i++) {" + NL + "                            basicBSONObject = (org.bson.BasicBSONObject) basicBSONObject" + NL + "                                    .get(objNames[i]);" + NL + "                            if (basicBSONObject == null) {" + NL + "                                basicBSONObject = new org.bson.BasicBSONObject();" + NL + "                                return basicBSONObject;" + NL + "                            }" + NL + "                            if (i == index) {" + NL + "                                break;" + NL + "                            }" + NL + "                        }" + NL + "                        return basicBSONObject;" + NL + "                    }" + NL + "                }" + NL + "                public void putkeyNode(String parentNode, String currentName, Object value){" + NL + "                    if (parentNode == null || \"\".equals(parentNode) || \".\".equals(parentNode)) {" + NL + "                        put(parentNode, currentName, value);" + NL + "                    }else{" + NL + "                        put(\"\", parentNode+\".\"+currentName, value);" + NL + "                    }" + NL + "                }" + NL + "                public org.bson.BasicBSONObject getObject() {" + NL + "                    return this.object;" + NL + "                }" + NL + "                public void setObject(org.bson.BasicBSONObject object){" + NL + "                    this.object=object;" + NL + "                }" + NL + "                public void append(String parentNode, String currentName, Object value){" + NL + "                    if (parentNode == null || \"\".equals(parentNode)) {" + NL + "                        object.put(currentName, value);" + NL + "                    }else{" + NL + "                        object.append(parentNode + \".\" + currentName, value);" + NL + "                    }" + NL + "                }" + NL + "            }" + NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();
TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
String validateError = tSqlRowUtil.validate(true, false);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "throw new JobConfigurationError(\"" + validateError +"\");";
}


java.util.List<IMetadataTable> metadatas = node.getMetadataList();
java.util.List<java.util.Map<String, String>> mappings = (java.util.List<java.util.Map<String,String>>)ElementParameterParser.getObjectValueXML(node, "__MAPPING__");
boolean isInsert = "INSERT".equalsIgnoreCase(ElementParameterParser.getValue(node, "__DATA_ACTION__"));
boolean updateALL = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__UPDATE_ALL__"));
String dataAction = ElementParameterParser.getValue(node, "__DATA_ACTION__");
boolean appendToParent = "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__APPEND_TO_PARENT__"));
String updateMethod = appendToParent ? "append" : "put";

if(metadatas != null && metadatas.size() > 0){
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        IConnection componentIncomingConnection = tSqlRowUtil.getIncomingConnections().get(0);
        String inStructName = codeGenArgument.getRecordStructName(componentIncomingConnection);
        String inRddName = "rdd_"+componentIncomingConnection.getName();
        java.util.List<IMetadataColumn> columnList = tSqlRowUtil.getColumns(componentIncomingConnection);
        int sizeColumns = columnList.size();

        // FOR INSERT action
        if(isInsert){
            
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_4);
    
        }
        // FOR UPDATE actions
        else{
            
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_8);
    
        }
        
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    
                    // Parent mapping
                    for (int i=0;i<mappings.size();i++) {
                        String path = mappings.get(i).get("PARENT_NODE_PATH");
                        if(path==null || "".equals(path)){
                            path="\"\"";
                        }
                        String schemaColumn=mappings.get(i).get("SCHEMA_COLUMN");
                        
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(schemaColumn);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(path);
    stringBuffer.append(TEXT_21);
    
                    }
                    
    stringBuffer.append(TEXT_22);
    
                // FOR INSERT action
                if(isInsert){
                    
    stringBuffer.append(TEXT_23);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_24);
    
                // FOR UPDATE actions
                } else {
                    
    stringBuffer.append(TEXT_25);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_26);
    
                }
                        
    stringBuffer.append(TEXT_27);
    
                        // Build the BSON document
                        for (int i = 0; i < sizeColumns; i++) {
                            IMetadataColumn column = columnList.get(i);
                            for (java.util.Map<String, String> mapping: mappings) {
                                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                                if (column.getLabel().equals(mapping.get("SCHEMA_COLUMN"))) {
                                    if ("true".equals(mapping.get("REMOVE_NULL_FIELD"))) {
                                        
    stringBuffer.append(TEXT_28);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_29);
    
                                            // FOR UPDATE actions
                                            if(!isInsert && column.isKey()){
                                                if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                    
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_33);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_34);
    
                                                } else {
                                                    
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_39);
    
                                                }
                                            }
                                            // FOR UPDATE actions
                                            if(!isInsert){
                                                if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                    
    stringBuffer.append(TEXT_40);
    stringBuffer.append(updateMethod);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_45);
    
                                                } else {
                                                    
    stringBuffer.append(TEXT_46);
    stringBuffer.append(updateMethod);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_51);
    
                                                }
                                            }else{
                                                if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                    
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_55);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_56);
    
                                                } else {
                                                    
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_60);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_61);
    
                                                }
                                            }
                                            
    stringBuffer.append(TEXT_62);
    
                                    } else {
                                        // FOR UPDATE actions
                                        if(!isInsert && column.isKey()){
                                            if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                
    stringBuffer.append(TEXT_63);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_71);
    
                                            } else {
                                                
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_76);
    
                                            }
                                        }
                                        if(!isInsert){
                                            if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                
    stringBuffer.append(TEXT_77);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(updateMethod);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(updateMethod);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_87);
    
                                            } else {
                                                
    stringBuffer.append(TEXT_88);
    stringBuffer.append(updateMethod);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_93);
    
                                            }
                                        }else{
                                            if (javaType == JavaTypesManager.BYTE_ARRAY){
                                                
    stringBuffer.append(TEXT_94);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_101);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_102);
    
                                            } else {
                                                
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getOriginalDbColumnName() );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_107);
    
                                            }
                                        }
                                    }
                                    break;
                                }
                            }
                        }
                        if(isInsert){
                            
    stringBuffer.append(TEXT_108);
    
                        } else {
                            if("UPDATE".equalsIgnoreCase(dataAction)){
                                
    stringBuffer.append(TEXT_109);
    stringBuffer.append(updateALL);
    stringBuffer.append(TEXT_110);
    
                            } else if ("SET".equalsIgnoreCase(dataAction)){
                                
    stringBuffer.append(TEXT_111);
    stringBuffer.append(updateALL);
    stringBuffer.append(TEXT_112);
    
                            } else if ("UPSERT".equalsIgnoreCase(dataAction)){
                                
    stringBuffer.append(TEXT_113);
    stringBuffer.append(updateALL);
    stringBuffer.append(TEXT_114);
    
                            } else if ("UPSERT_WITH_SET".equalsIgnoreCase(dataAction)){
                                
    stringBuffer.append(TEXT_115);
    stringBuffer.append(updateALL);
    stringBuffer.append(TEXT_116);
    
                            }
                        }
                        
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    
    }
}
// No schema
else{

}

    return stringBuffer.toString();
  }
}
