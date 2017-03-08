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

public class TFixedFlowInputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TFixedFlowInputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFixedFlowInputSparkstreamingconfigJava result = new TFixedFlowInputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "        // Nothing to do";
  protected final String TEXT_3 = NL + "            ";
  protected final String TEXT_4 = " = init(new Object[][] { new Object[] {";
  protected final String TEXT_5 = "}});";
  protected final String TEXT_6 = NL + "            // replace \\ by \\\\\\\\";
  protected final String TEXT_7 = NL + "            ";
  protected final String TEXT_8 = " = init(";
  protected final String TEXT_9 = NL + "                        .split(";
  protected final String TEXT_10 = "));";
  protected final String TEXT_11 = NL + "            ";
  protected final String TEXT_12 = " = init(";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL + "            private java.util.List<java.util.List<Object>> init(Object[][] fixedValuesAsArray) {" + NL + "                List<List<Object>> fixedValues = new java.util.ArrayList<java.util.List<Object>>();" + NL + "                for (Object[] tuple : fixedValuesAsArray)" + NL + "                    fixedValues.add(java.util.Arrays.asList(tuple));" + NL + "                return fixedValues;" + NL + "            }";
  protected final String TEXT_15 = NL + "            private java.util.List<java.util.List<Object>> init(String[] fixedValuesAsArray) {" + NL + "                List<List<Object>> fixedValues = new java.util.ArrayList<java.util.List<Object>>();" + NL + "                for (String tuple : fixedValuesAsArray)" + NL + "                    fixedValues.add(java.util.Arrays.asList((Object[])tuple.split(";
  protected final String TEXT_16 = ")));" + NL + "                return fixedValues;" + NL + "            }";
  protected final String TEXT_17 = NL + "            private java.util.List<java.util.List<Object>> init(String... base64Rows) {" + NL + "                java.util.List<java.util.List<Object>> fixedValues = new java.util.ArrayList<java.util.List<Object>>();" + NL + "                StringBuilder base64 = new StringBuilder();" + NL + "                for (String row : base64Rows) {" + NL + "                    base64.append(row);";
  protected final String TEXT_18 = NL + "                        base64.append(";
  protected final String TEXT_19 = ");";
  protected final String TEXT_20 = NL + "                }" + NL + "" + NL + "                String originalFileContent = \"\";" + NL + "                String javajetLineSeparator_";
  protected final String TEXT_21 = "  = ";
  protected final String TEXT_22 = ";" + NL + "                try {" + NL + "                    if (checkForBase64Encode_";
  protected final String TEXT_23 = "(base64.toString())) {" + NL + "                        originalFileContent = new String((new sun.misc.BASE64Decoder()).decodeBuffer(base64.toString()), utf8Charset);" + NL + "                    } else {" + NL + "                        originalFileContent = base64.toString();" + NL + "                    }" + NL + "                    org.talend.fileprocess.FileInputDelimited fid = new org.talend.fileprocess.FileInputDelimited(" + NL + "                            new java.io.ByteArrayInputStream(originalFileContent.getBytes(utf8Charset))," + NL + "                            utf8Charset,";
  protected final String TEXT_24 = NL + "                            ";
  protected final String TEXT_25 = "," + NL + "                            javajetLineSeparator_";
  protected final String TEXT_26 = "," + NL + "                            true, 0, 0, -1, -1, false);" + NL + "" + NL + "                    while (fid.nextRecord()) {" + NL + "                        java.util.List<Object> tuple = new java.util.ArrayList<Object>();" + NL;
  protected final String TEXT_27 = NL + NL + "                            if (";
  protected final String TEXT_28 = " < fid.getColumnsCountOfCurrentRow()) {" + NL + "                                String colContent = fid.get(";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + "                                    tuple.add(colContent == null || colContent.length() == 0" + NL + "                                            ? ";
  protected final String TEXT_31 = ": colContent);";
  protected final String TEXT_32 = NL + "                                    if ( colContent == null || colContent.length() == 0) {" + NL + "                                        tuple.add(BigDataParserUtils.parseTo_Date(";
  protected final String TEXT_33 = NL + "                                                (String)";
  protected final String TEXT_34 = " ";
  protected final String TEXT_35 = ",";
  protected final String TEXT_36 = NL + "                                            ";
  protected final String TEXT_37 = "));" + NL + "                                    } else {" + NL + "                                        tuple.add(BigDataParserUtils.parseTo_Date(colContent, ";
  protected final String TEXT_38 = "));" + NL + "                                    }";
  protected final String TEXT_39 = NL + "                                    tuple.add(colContent == null || colContent.length() == 0" + NL + "                                             ? ";
  protected final String TEXT_40 = " : colContent.getBytes());";
  protected final String TEXT_41 = NL + "                                    tuple.add(colContent == null || colContent.trim().length() == 0" + NL + "                                            ? ";
  protected final String TEXT_42 = " : BigDataParserUtils.parseTo_";
  protected final String TEXT_43 = "(colContent));";
  protected final String TEXT_44 = NL + "                            } else {";
  protected final String TEXT_45 = NL + "                                    tuple.add(";
  protected final String TEXT_46 = ");";
  protected final String TEXT_47 = NL + "                                    tuple.add(BigDataParserUtils.parseTo_Date((String)";
  protected final String TEXT_48 = ", ";
  protected final String TEXT_49 = "));";
  protected final String TEXT_50 = NL + "                                    tuple.add(BigDataParserUtils.parseTo_Date(";
  protected final String TEXT_51 = ", ";
  protected final String TEXT_52 = "));";
  protected final String TEXT_53 = NL + "                            }";
  protected final String TEXT_54 = NL + NL + "                        fixedValues.add(tuple);" + NL + "                    }" + NL + "" + NL + "                } catch (java.lang.Exception e) {" + NL + "                    e.printStackTrace();" + NL + "                }" + NL + "" + NL + "                return fixedValues;" + NL + "            }";
  protected final String TEXT_55 = NL + "\t\t           Object o_";
  protected final String TEXT_56 = " = ";
  protected final String TEXT_57 = ".get(";
  protected final String TEXT_58 = ").get(";
  protected final String TEXT_59 = ");" + NL + "\t\t           if(o_";
  protected final String TEXT_60 = " != null) {" + NL + "\t\t               ";
  protected final String TEXT_61 = ".";
  protected final String TEXT_62 = " = java.nio.ByteBuffer.wrap(o_";
  protected final String TEXT_63 = ".toString().getBytes());" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t\t}";
  protected final String TEXT_64 = NL + "                    ";
  protected final String TEXT_65 = ".";
  protected final String TEXT_66 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_67 = NL + "                            (";
  protected final String TEXT_68 = ".get(";
  protected final String TEXT_69 = ").get(";
  protected final String TEXT_70 = ").toString(), ";
  protected final String TEXT_71 = ");";
  protected final String TEXT_72 = NL + "                    // All inputs came from the context, there are string we want to cast";
  protected final String TEXT_73 = NL + "                    ";
  protected final String TEXT_74 = ".";
  protected final String TEXT_75 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_76 = NL + "                            (";
  protected final String TEXT_77 = ".get(";
  protected final String TEXT_78 = ").get(";
  protected final String TEXT_79 = ").toString());";
  protected final String TEXT_80 = NL + "\t\t\t           Object o_";
  protected final String TEXT_81 = " = ";
  protected final String TEXT_82 = ".get(";
  protected final String TEXT_83 = ").get(";
  protected final String TEXT_84 = ");" + NL + "\t\t\t           if(o_";
  protected final String TEXT_85 = " != null) {" + NL + "\t\t\t               ";
  protected final String TEXT_86 = ".";
  protected final String TEXT_87 = " = java.nio.ByteBuffer.wrap((";
  protected final String TEXT_88 = ") o_";
  protected final String TEXT_89 = ");" + NL + "\t\t\t           }" + NL + "\t\t            ";
  protected final String TEXT_90 = NL + "\t\t            ";
  protected final String TEXT_91 = ".";
  protected final String TEXT_92 = " =" + NL + "\t\t                    (";
  protected final String TEXT_93 = ") ";
  protected final String TEXT_94 = ".get(";
  protected final String TEXT_95 = ").get(";
  protected final String TEXT_96 = ");" + NL + "\t\t            ";
  protected final String TEXT_97 = NL + "                   Object o_";
  protected final String TEXT_98 = " = ";
  protected final String TEXT_99 = ".get(";
  protected final String TEXT_100 = ");" + NL + "                   if(o_";
  protected final String TEXT_101 = " != null) {";
  protected final String TEXT_102 = NL + "                       ";
  protected final String TEXT_103 = ".";
  protected final String TEXT_104 = " = java.nio.ByteBuffer.wrap(o_";
  protected final String TEXT_105 = ".toString().getBytes());" + NL + "                                                    }";
  protected final String TEXT_106 = NL + "                    ";
  protected final String TEXT_107 = ".";
  protected final String TEXT_108 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_109 = NL + "                            (";
  protected final String TEXT_110 = ".get(";
  protected final String TEXT_111 = ").toString(), ";
  protected final String TEXT_112 = ");";
  protected final String TEXT_113 = NL + "                    // All inputs came from the context, there are string we want to cast";
  protected final String TEXT_114 = NL + "                    ";
  protected final String TEXT_115 = ".";
  protected final String TEXT_116 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_117 = NL + "                            (";
  protected final String TEXT_118 = ".get(";
  protected final String TEXT_119 = ").toString());";
  protected final String TEXT_120 = NL + "                       Object o_";
  protected final String TEXT_121 = " = ";
  protected final String TEXT_122 = ".get(";
  protected final String TEXT_123 = ");" + NL + "                       if(o_";
  protected final String TEXT_124 = " != null) {";
  protected final String TEXT_125 = NL + "                           ";
  protected final String TEXT_126 = ".";
  protected final String TEXT_127 = " = java.nio.ByteBuffer.wrap((";
  protected final String TEXT_128 = ") o_";
  protected final String TEXT_129 = ");" + NL + "                       }";
  protected final String TEXT_130 = NL + "                    ";
  protected final String TEXT_131 = ".";
  protected final String TEXT_132 = " =" + NL + "                            (";
  protected final String TEXT_133 = ") ";
  protected final String TEXT_134 = ".get(";
  protected final String TEXT_135 = ");";
  protected final String TEXT_136 = NL + "org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_137 = "> ";
  protected final String TEXT_138 = "_sourceDStream = ctx.receiverStream(new CustomReceiver_";
  protected final String TEXT_139 = "(new Data_";
  protected final String TEXT_140 = "().getList(),Long.valueOf(";
  protected final String TEXT_141 = ").longValue()));" + NL + "" + NL + "org.apache.spark.streaming.api.java.JavaPairDStream<NullWritable, ";
  protected final String TEXT_142 = "> rdd_";
  protected final String TEXT_143 = " = ";
  protected final String TEXT_144 = "_sourceDStream.mapToPair(new ";
  protected final String TEXT_145 = "_mapToPair(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final String repetitionInterval = ElementParameterParser.getValue(node, "__INPUT_REPETITION__");


    stringBuffer.append(TEXT_1);
    

/**
 * Contains common processing for tFixedFlowInput code generation.  This is
 * used in MapReduce.
 *
 * The following imports must occur before importing this file:
 * @ include file="@{org.talend.designer.components.mrprovider}/resources/utils/common_codegen_util.javajet"
 */
class TFixedFlowInputUtil extends org.talend.designer.common.TransformerBase {

    /** If initialized, contains code that can be assigned to a list of lists.
     *  Exactly one of codeValues or codeValuesBase64 will be non-null. */
    private StringBuilder codeValues = null;

    /** If initialized, contains code that can be assigned to a list of lists.
     *  Exactly one of codeValues or codeValuesBase64 will be non-null. */
    private StringBuilder codeValuesBase64 = null;

    /** The number of times the specified values should be repeated. */
    public final String nbRows;

    public TFixedFlowInputUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        super(node, argument, rowTransformUtil, "FLOW", "REJECT");

        nbRows = ElementParameterParser.getValue(node, "__NB_ROWS__");

        // Exactly one of these will be true.
        if ("true".equals(ElementParameterParser.getValue(node, "__USE_SINGLEMODE__")))
            initCodeValuesSingleMode((List<Map<String,String>>)
                    ElementParameterParser.getObjectValue(node, "__VALUES__"));
        else if ("true".equals(ElementParameterParser.getValue(node, "__USE_INTABLE__")))
            initCodeValuesInTable((List<Map<String,String>>)
                    ElementParameterParser.getObjectValue(node, "__INTABLE__"));
        else if ("true".equals(ElementParameterParser.getValue(node, "__USE_INLINECONTENT__")))
            initCodeValuesInlineContent(ElementParameterParser.getValue(node,"__INLINECONTENT__"));
        else if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__")))
            initCodeValuesContext(ElementParameterParser.getValue(node,"__CONTEXT_VARIABLE__"));
    }

    /**
     * Used from the constructor to initialize the fixed values when only a
     * single value is specified.
     */
    private void initCodeValuesSingleMode(List<Map<String,String>> tableValues) {
        codeValues = new StringBuilder();
        if (tableValues == null || tableValues.size() == 0)
            return;

        for(Map<String, String> tableValue : tableValues) {
            String label = tableValue.get("SCHEMA_COLUMN");
            String value = tableValue.get("VALUE");
            if (value == null || value.length() == 0) { //use the default value
                IMetadataColumn column = getOutConnMain().getMetadataTable().getColumn(label);
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                String defaultValue = "null";
                if (column.getDefault() != null && column.getDefault().length() > 0) {
                    defaultValue = column.getDefault();
                } else {
                    if (typeToGenerate == null) {
                        throw new IllegalArgumentException();
                    }
                    if (JavaTypesManager.isJavaPrimitiveType(typeToGenerate)) {
                        if ("char".equals(typeToGenerate)) {
                            defaultValue = "' '";
                        } else if ("boolean".equals(typeToGenerate)) {
                            defaultValue = "false";
                        } else if ("byte".equals(typeToGenerate)) {
                            defaultValue = "(byte)0";
                        } else if ("double".equals(typeToGenerate)) {
                            defaultValue = "0.0d";
                        } else if ("float".equals(typeToGenerate)) {
                            defaultValue = "0.0f";
                        } else if ("long".equals(typeToGenerate)) {
                            defaultValue = "0l";
                        } else if ("short".equals(typeToGenerate)) {
                            defaultValue = "(short) 0";
                        } else {
                            defaultValue = "0";
                        }
                    }
                }
                codeValues.append(defaultValue).append(',');
            } else {
                codeValues.append(value).append(',');
            }
        }
        // Remove trailing commas.
        if (codeValues.length() > 0)
            codeValues.setLength(codeValues.length() - 1);
    }

    /**
     * Used from the constructor to initialize the fixed values when the list of
     * values is specified in the parameter table.
     */
    private void initCodeValuesInTable(List<Map<String,String>> tableValues) {
        codeValues = new StringBuilder();
        if (tableValues == null || tableValues.size() == 0)
            return;

        for (Map<String, String> tableValue : tableValues) {
            if (0 != codeValues.length())
                codeValues.append("}, new Object[] {");
            for (IMetadataColumn column: getOutColumnsMain()) {
                String label = column.getLabel();
                String value = tableValue.get(label);
                if (value == null || value.length() == 0) { //use the default value
                    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                    if (column.getDefault() != null && column.getDefault().length() > 0) {
                        codeValues.append(column.getDefault()).append(',');
                    } else {
                        if (typeToGenerate == null) {
                            throw new IllegalArgumentException();
                        }
                        if (JavaTypesManager.isJavaPrimitiveType(typeToGenerate)) {
                            if ("char".equals(typeToGenerate)) {
                                codeValues.append("' '").append(',');
                            } else if ("boolean".equals(typeToGenerate)) {
                                codeValues.append("false").append(',');
                            } else if ("byte".equals(typeToGenerate)) {
                                codeValues.append("(byte)0").append(',');
                            } else if ("double".equals(typeToGenerate)) {
                                codeValues.append("0.0d").append(',');
                            } else if ("float".equals(typeToGenerate)) {
                                codeValues.append("0.0f").append(',');
                            } else if ("long".equals(typeToGenerate)) {
                                codeValues.append("0l").append(',');
                            } else if ("short".equals(typeToGenerate)) {
                                codeValues.append("(short) 0").append(',');
                            } else {
                                codeValues.append("0").append(',');
                            }
                        } else {
                            codeValues.append("null").append(',');
                        }
                    }
                } else {
                    codeValues.append(value).append(',');
                }
            }
            // Remove trailing commas.
            if (codeValues.length() > 0)
                codeValues.setLength(codeValues.length() - 1);
        }
    }

    /**
     * Used from the constructor to initialize the fixed values when the list of
     * values is specified inline.
     */
    private void initCodeValuesInlineContent(String fileContent) {
        String lineSeparator = (String) java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator"));

        String fileContentEnCodeStr = "";
        try {
            fileContentEnCodeStr = (new sun.misc.BASE64Encoder()).encode(fileContent.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        codeValuesBase64 = new StringBuilder();
        for(String item : fileContentEnCodeStr.split(lineSeparator))
            codeValuesBase64.append('"').append(item).append("\",");
        // Remove trailing commas.
        if (codeValuesBase64.length() > 0)
            codeValuesBase64.setLength(codeValuesBase64.length() - 1);
    }

    /**
     * Used from the constructor to initialize the fixed values when the list of
     * values is in the context.
     */
    private void initCodeValuesContext(String contextVariable) {
        
    stringBuffer.append(TEXT_2);
    
    }

    /**
     * Generates the initialized class member that will contain the fixed data.
     */
    public void generateFixedDataMember(String codeFixedVar) {
        // The fixed data member is composed of a simple declaration, but also
        // a method to parse the data passed in.
        if (codeValues != null) {
            
    stringBuffer.append(TEXT_3);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_4);
    stringBuffer.append( codeValues );
    stringBuffer.append(TEXT_5);
    
        } else if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"))) {
            
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CONTEXT_VARIABLE__"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(ElementParameterParser.getValue(node, "__CONTEXTROWSEPARATOR__").replace("\\", "\\\\\\\\"));
    stringBuffer.append(TEXT_10);
    
        } else {
            
    stringBuffer.append(TEXT_11);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_12);
    stringBuffer.append( codeValuesBase64 );
    stringBuffer.append(TEXT_13);
    
        }
    }

    /**
     * Generates a private static init method that can be used to initialize a
     * static List of List of objects from the values specified by the user.
     */
    public void generateFixedDataInit() {
        org.talend.core.model.process.IProcess process = node.getProcess();
        if (codeValues != null) {
            
    stringBuffer.append(TEXT_14);
    
        } else if ((!org.talend.core.model.process.ProcessUtils.isTestContainer(process))
                && ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__")))) {
            // NOT test mode, handle context variable as string
            
    stringBuffer.append(TEXT_15);
    stringBuffer.append( ElementParameterParser.getValue(node, "__CONTEXTFIELDSEPARATOR__") );
    stringBuffer.append(TEXT_16);
    
        } else {
            String fieldSeparator = "";
            String rowSeparator = "";
            if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"))) {
                // test mode, handle context variable as encoded string
                fieldSeparator= ElementParameterParser.getValue(node, "__CONTEXTFIELDSEPARATOR__");
                rowSeparator = ElementParameterParser.getValue(node, "__CONTEXTROWSEPARATOR__");
            } else {// inline content
                fieldSeparator= ElementParameterParser.getValue(node, "__FIELDSEPARATOR__");
                rowSeparator = ElementParameterParser.getValue(node, "__ROWSEPARATOR__");
            }
            
    stringBuffer.append(TEXT_17);
     if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"))) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_19);
     } 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(rowSeparator);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(TEXT_24);
    stringBuffer.append( fieldSeparator );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    
                        int i = -1;
                        for (IMetadataColumn column : getOutColumnsMain()) {
                            i++;
                            String label = column.getLabel();
                            String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                            String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();

                            String defaultValue = "null";
                            if (column.getDefault() != null && column.getDefault().length() > 0) {
                                defaultValue = column.getDefault();
                            } else {
                                if (typeToGenerate == null) {
                                    throw new IllegalArgumentException();
                                }
                                if (JavaTypesManager.isJavaPrimitiveType(typeToGenerate)) {
                                    if ("char".equals(typeToGenerate)) {
                                        defaultValue = "' '";
                                    } else if ("boolean".equals(typeToGenerate)) {
                                        defaultValue = "false";
                                    } else if ("byte".equals(typeToGenerate)) {
                                        defaultValue = "(byte)0";
                                    } else if ("double".equals(typeToGenerate)) {
                                        defaultValue = "0.0d";
                                    } else if ("float".equals(typeToGenerate)) {
                                        defaultValue = "0.0f";
                                    } else if ("long".equals(typeToGenerate)) {
                                        defaultValue = "0l";
                                    } else if ("short".equals(typeToGenerate)) {
                                        defaultValue = "(short) 0";
                                    } else {
                                        defaultValue = "0";
                                    }
                                }
                            }
                            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_29);
    
                                if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {
                                    
    stringBuffer.append(TEXT_30);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_31);
    
                                } else if (javaType == JavaTypesManager.DATE) {
                                    
    stringBuffer.append(TEXT_32);
     if ((defaultValue==null) || "".equals(defaultValue) || "null".equals(defaultValue)){ 
    stringBuffer.append(TEXT_33);
     } 
    stringBuffer.append(TEXT_34);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_37);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_38);
    
                                } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
                                    
    stringBuffer.append(TEXT_39);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_40);
    
                                } else {
                                    
    stringBuffer.append(TEXT_41);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_42);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_43);
    
                                }
                                
    stringBuffer.append(TEXT_44);
    
                                if (javaType != JavaTypesManager.DATE) {
                                    
    stringBuffer.append(TEXT_45);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_46);
    
                                } else if (defaultValue == null || "".equals(defaultValue) || "null".equals(defaultValue)) {
                                    
    stringBuffer.append(TEXT_47);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_48);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_49);
    
                                } else {
                                    
    stringBuffer.append(TEXT_50);
    stringBuffer.append(defaultValue );
    stringBuffer.append(TEXT_51);
    stringBuffer.append( patternValue );
    stringBuffer.append(TEXT_52);
    
                                }
                                
    stringBuffer.append(TEXT_53);
    
                        }
                        
    stringBuffer.append(TEXT_54);
    
        }
    }

    /**
     * Generates code to copy from the fixed buffer to the named output struct.
     */
    public void generateCopyFromFixedToOut(String codeFixedVar, String codeOutStruct, String codeRowCount) {
        int i = 0;
        for (IMetadataColumn column : getOutColumnsMain()) {
            // TODO: this assignment should be handled by a new RowTransformUtil
            // that doesn't rely on an underlying Mapper.
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

            if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"))) {
                if (codeGenArgument.getRecordStructGenerator().isByteArrayWrappedInBuffer() && (javaType == JavaTypesManager.BYTE_ARRAY)) {
                    
    stringBuffer.append(TEXT_55);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(codeRowCount);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_63);
    
                } else if (javaType == JavaTypesManager.DATE) {
                    
    stringBuffer.append(TEXT_64);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_66);
    stringBuffer.append( javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(codeRowCount);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_71);
    
                } else {
                    
    stringBuffer.append(TEXT_72);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_75);
    stringBuffer.append( javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(codeRowCount);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_79);
    
                }
            } else {
		        // Avro metadata table compatibility
		        if (codeGenArgument.getRecordStructGenerator().isByteArrayWrappedInBuffer()&& (javaType == JavaTypesManager.BYTE_ARRAY)) {
		            
    stringBuffer.append(TEXT_80);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(codeRowCount);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_87);
    stringBuffer.append( javaType.getNullableClass().getSimpleName() );
    stringBuffer.append(TEXT_88);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_89);
    
					i++;
		        } else {
		            
    stringBuffer.append(TEXT_90);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_92);
    stringBuffer.append( javaType.getNullableClass().getSimpleName() );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(codeRowCount);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_96);
    
                }
            }
        }
    }
    

    /**
     * Generates code to copy from the fixed buffer to the named output struct.
     */
    public void generateCopyFromFixedToOut(String codeFixedVar, String codeOutStruct) {
        int i = 0;
        for (IMetadataColumn column : getOutColumnsMain()) {
            // TODO: this assignment should be handled by a new RowTransformUtil
            // that doesn't rely on an underlying Mapper.
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());

            if ("true".equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"))) {
                if (codeGenArgument.getRecordStructGenerator().isByteArrayWrappedInBuffer() && (javaType == JavaTypesManager.BYTE_ARRAY)) {
                    
    stringBuffer.append(TEXT_97);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_105);
    
                } else if (javaType == JavaTypesManager.DATE) {
                    
    stringBuffer.append(TEXT_106);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_108);
    stringBuffer.append( javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_112);
    
                } else {
                    
    stringBuffer.append(TEXT_113);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_116);
    stringBuffer.append( javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_119);
    
                }
            } else {
                // Avro metadata table compatibility
                if (codeGenArgument.getRecordStructGenerator().isByteArrayWrappedInBuffer()&& (javaType == JavaTypesManager.BYTE_ARRAY)) {
                    
    stringBuffer.append(TEXT_120);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_127);
    stringBuffer.append( javaType.getNullableClass().getSimpleName() );
    stringBuffer.append(TEXT_128);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_129);
    
                    i++;
                } else {
                    
    stringBuffer.append(TEXT_130);
    stringBuffer.append(codeOutStruct);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_132);
    stringBuffer.append( javaType.getNullableClass().getSimpleName() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(codeFixedVar);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(i++);
    stringBuffer.append(TEXT_135);
    
                }
            }
        }
    }
}

    
final TFixedFlowInputUtil tFixedFlowInputUtil = new TFixedFlowInputUtil(node, codeGenArgument, null);

List< ? extends IConnection> connections = node.getOutgoingConnections();
if (connections == null || connections.size() == 0)
    return "";

IConnection connection = connections.get(0);
String connName = connection.getName();
String outStruct = codeGenArgument.getRecordStructName(connection);

    stringBuffer.append(TEXT_136);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(repetitionInterval);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(outStruct);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    return stringBuffer.toString();
  }
}
