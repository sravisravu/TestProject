package org.talend.designer.codegen.translators.file.output;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tsqlrow.TSqlRowUtil;

public class TFileOutputParquetSparkcodeJava
{
  protected static String nl;
  public static synchronized TFileOutputParquetSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFileOutputParquetSparkcodeJava result = new TFileOutputParquetSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "                    public static class TalendParquetOutputFormat_";
  protected final String TEXT_2 = " extends parquet.hadoop.mapred.DeprecatedParquetOutputFormat<parquet.example.data.Group> {" + NL + "                    }" + NL + "" + NL + "                    public static class toVoid_";
  protected final String TEXT_3 = " implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<NullWritable, ";
  protected final String TEXT_4 = ">, Void, Object> {" + NL + "" + NL + "                        private transient parquet.example.data.simple.SimpleGroupFactory factory = null;" + NL + "" + NL + "                        public scala.Tuple2<Void, Object> call(" + NL + "                                scala.Tuple2<NullWritable, ";
  protected final String TEXT_5 = "> arg0)" + NL + "                                throws Exception {" + NL;
  protected final String TEXT_6 = NL + "                            ";
  protected final String TEXT_7 = " value = arg0._2;" + NL + "                            if(factory == null) {" + NL + "                                java.util.List<parquet.schema.Type> types = new java.util.ArrayList<parquet.schema.Type>();";
  protected final String TEXT_8 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT32, \"";
  protected final String TEXT_9 = "\"));";
  protected final String TEXT_10 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.INT64, \"";
  protected final String TEXT_11 = "\"));";
  protected final String TEXT_12 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.DOUBLE, \"";
  protected final String TEXT_13 = "\"));";
  protected final String TEXT_14 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.FLOAT, \"";
  protected final String TEXT_15 = "\"));";
  protected final String TEXT_16 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BOOLEAN, \"";
  protected final String TEXT_17 = "\"));";
  protected final String TEXT_18 = NL + "                                        types.add(new parquet.schema.PrimitiveType(parquet.schema.Type.Repetition.REQUIRED,    parquet.schema.PrimitiveType.PrimitiveTypeName.BINARY, \"";
  protected final String TEXT_19 = "\"));";
  protected final String TEXT_20 = NL + "                                parquet.schema.MessageType schema = new parquet.schema.MessageType(\"";
  protected final String TEXT_21 = "\", types);" + NL + "                                factory = new parquet.example.data.simple.SimpleGroupFactory(schema);" + NL + "                            }" + NL + "" + NL + "                            parquet.example.data.Group group = factory.newGroup()";
  protected final String TEXT_22 = NL + "                                    .append(\"";
  protected final String TEXT_23 = "\", ";
  protected final String TEXT_24 = "value.";
  protected final String TEXT_25 = " == null ? null : ";
  protected final String TEXT_26 = "new java.text.SimpleDateFormat(";
  protected final String TEXT_27 = ").format(value.";
  protected final String TEXT_28 = "))";
  protected final String TEXT_29 = NL + "                                    .append(\"";
  protected final String TEXT_30 = "\", ";
  protected final String TEXT_31 = "value.";
  protected final String TEXT_32 = " == null ? null : ";
  protected final String TEXT_33 = "parquet.io.api.Binary.fromByteArray(value.";
  protected final String TEXT_34 = ".array()))";
  protected final String TEXT_35 = NL + "                                    .append(\"";
  protected final String TEXT_36 = "\", ";
  protected final String TEXT_37 = "value.";
  protected final String TEXT_38 = " == null ? null : ";
  protected final String TEXT_39 = "parquet.io.api.Binary.fromByteArray(BigDataParserUtils.parseTo_String(value.";
  protected final String TEXT_40 = ").getBytes()))";
  protected final String TEXT_41 = NL + "                                        .append(\"";
  protected final String TEXT_42 = "\", value.";
  protected final String TEXT_43 = " == null ? null : value.";
  protected final String TEXT_44 = ".intValue())";
  protected final String TEXT_45 = NL + "                                        .append(\"";
  protected final String TEXT_46 = "\", BigDataParserUtils.parseTo_int(value.";
  protected final String TEXT_47 = "))";
  protected final String TEXT_48 = NL + "                                    .append(\"";
  protected final String TEXT_49 = "\", value.";
  protected final String TEXT_50 = ")";
  protected final String TEXT_51 = NL + "                            ;" + NL + "                            return new scala.Tuple2<Void, Object>(null, group);" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_52 = NL + NL + "                                public static class ";
  protected final String TEXT_53 = "_From";
  protected final String TEXT_54 = "To";
  protected final String TEXT_55 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_56 = ", ";
  protected final String TEXT_57 = "> {" + NL + "" + NL + "                                    public ";
  protected final String TEXT_58 = " call(";
  protected final String TEXT_59 = " input) {";
  protected final String TEXT_60 = NL + "                                        ";
  protected final String TEXT_61 = " result = new ";
  protected final String TEXT_62 = "();";
  protected final String TEXT_63 = NL + "                                        if(input.";
  protected final String TEXT_64 = " != null) {" + NL + "                                            result.";
  protected final String TEXT_65 = " = new java.sql.Date(input.";
  protected final String TEXT_66 = ".getTime());" + NL + "                                        } else {" + NL + "                                            result.";
  protected final String TEXT_67 = " = null;" + NL + "                                        }";
  protected final String TEXT_68 = NL + "                                        result.";
  protected final String TEXT_69 = " = input.";
  protected final String TEXT_70 = ".toString();";
  protected final String TEXT_71 = NL + "                                        result.";
  protected final String TEXT_72 = " = input.";
  protected final String TEXT_73 = ".array();";
  protected final String TEXT_74 = NL + "                                        result.";
  protected final String TEXT_75 = " = input.";
  protected final String TEXT_76 = ";";
  protected final String TEXT_77 = NL + "                                        return result;" + NL + "                                    }" + NL + "                                }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();
final org.talend.hadoop.distribution.ESparkVersion sparkVersion = codeGenArgument.getSparkVersion();

final String dataframeClass = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0
    ? "org.apache.spark.sql.DataFrame"
    : "org.apache.spark.sql.Dataset<org.apache.spark.sql.Row>";

    
// Find the tSparkConfiguration and define which Spark version is currently used. It will define the generation mode: RDD with OutputFormat or native Dataframe API.
final List<? extends INode> sparkConfigs = node.getProcess().getNodesOfType("tSparkConfiguration");
INode sparkConfig = null;
if(sparkConfigs != null && sparkConfigs.size() > 0) {
    sparkConfig = sparkConfigs.get(0);
}


List<IMetadataTable> metadatas = node.getMetadataList();

if ((metadatas!=null) && (metadatas.size() > 0)) {
    IMetadataTable metadata = metadatas.get(0);
    if (metadata != null) {
        List< ? extends IConnection> conns = node.getIncomingConnections();

        if ((conns != null) && (conns.size() > 0)) {
            IConnection conn = conns.get(0);
            String connName = conn.getName();
            String connTypeName = codeGenArgument.getRecordStructName(conn);

            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                // If the current Spark version is 1.3, 1.4 or 1.5, we use the RDD OutputFormat API.
                if(sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3
                        || sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_4
                        || sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_5) {
                    List<IMetadataColumn> columns = metadata.getListColumns();
                    int nbColumns = columns.size();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(connTypeName);
    stringBuffer.append(TEXT_7);
    
                                for(int i=0; i<nbColumns; i++) {
                                    IMetadataColumn column = columns.get(i);
                                    String columnName = column.getLabel();
                                    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

                                    if(javaType == JavaTypesManager.INTEGER || javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.BYTE) {
    
    stringBuffer.append(TEXT_8);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_9);
    
                                    } else if(javaType == JavaTypesManager.LONG) {
    
    stringBuffer.append(TEXT_10);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_11);
    
                                    } else if(javaType == JavaTypesManager.DOUBLE) {
    
    stringBuffer.append(TEXT_12);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_13);
    
                                    } else if(javaType == JavaTypesManager.FLOAT) {
    
    stringBuffer.append(TEXT_14);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_15);
    
                                    } else if(javaType == JavaTypesManager.BOOLEAN) {
    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    
                                    } else {
    
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_19);
    
                                    }
                                }
    
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    
                            for(int i=0; i<nbColumns; i++) {
                                IMetadataColumn column = columns.get(i);
                                String columnName = columns.get(i).getLabel();
                                String outputType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                                if(javaType == JavaTypesManager.DATE) {

    stringBuffer.append(TEXT_22);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_23);
     if(column.isNullable()) { 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
     } 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    
                                } else if(javaType == JavaTypesManager.BYTE_ARRAY) {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_30);
     if(column.isNullable()) { 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_32);
     } 
    stringBuffer.append(TEXT_33);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_34);
    
                                } else if(javaType == JavaTypesManager.CHARACTER || javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.BIGDECIMAL) {

    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_36);
     if(column.isNullable()) { 
    stringBuffer.append(TEXT_37);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_38);
     } 
    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    
                                } else if(javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.BYTE) {
                                    if(column.isNullable()) {

    stringBuffer.append(TEXT_41);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_44);
    
                                    } else {

    stringBuffer.append(TEXT_45);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_47);
    
                                    }
                                } else {

    stringBuffer.append(TEXT_48);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_50);
    
                                }
                            }

    stringBuffer.append(TEXT_51);
    
                } else {
                    // If the current Spark version greater than or equals to 1.6, we use the native dataframe API.
                    try {
                        TSqlRowUtil tSqlRowUtil = new TSqlRowUtil(node);
                        String validateError = tSqlRowUtil.validate(true, false);
                        if (validateError != null) {
                            // Cause the job compilation to explicitly fail if there is a problem.
                            return "throw new JobConfigurationError(\"" + validateError +"\");";
                        }

                            // If the incoming rowStruct contains a Date field (always typed as java.util.Date),
                        // we must generate a new structure which replaces these java.util.Date instances by
                        // java.sql.Date instances since DataFrames only support java.sql.Date.

                        org.talend.designer.bigdata.avro.AvroRecordStructGenerator avroRecordStructGenerator = (org.talend.designer.bigdata.avro.AvroRecordStructGenerator) codeGenArgument.getRecordStructGenerator();

                        for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections()) {
                            if(tSqlRowUtil.needsRecordStructChange(incomingConnection)) {
                                java.util.List<IMetadataColumn> columns = tSqlRowUtil.getColumns(incomingConnection);
                                String originalStructName = codeGenArgument.getRecordStructName(incomingConnection);
                                String suggestedDfStructName = "DF_"+originalStructName;
                                String dfStructName = avroRecordStructGenerator.generateRecordStructForSpark16DataFrame(suggestedDfStructName, originalStructName);

    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(originalStructName);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(dfStructName);
    stringBuffer.append(TEXT_62);
    
                                for(IMetadataColumn column : columns) {
                                    if(tSqlRowUtil.isDateField(column)) {

    stringBuffer.append(TEXT_63);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_67);
    
                                    } else if(tSqlRowUtil.isCharacterField(column)) {

    stringBuffer.append(TEXT_68);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_70);
    
                                    } else if(tSqlRowUtil.isByteArrayField(column)) {

    stringBuffer.append(TEXT_71);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_73);
    
                                    } else {

    stringBuffer.append(TEXT_74);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_76);
    
                                    }
                                } // end for(IMetadataColumn column : columns)

    stringBuffer.append(TEXT_77);
    
                            } // end if(tSqlRowUtil.containsDateFields(incomingConnection))
                        } // end for(IConnection incomingConnection : tSqlRowUtil.getIncomingConnections())
                    } catch (java.lang.Exception e) {
                        // Do not generate sparkcode part : a clean exception has to be generated within sparkconfig part
                    }
                }
            }
        }
    }
}

    return stringBuffer.toString();
  }
}
