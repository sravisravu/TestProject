package org.talend.designer.codegen.translators.technical;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ProcessUtils;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TCollectAndCheckSparkconfigJava
{
  protected static String nl;
  public static synchronized TCollectAndCheckSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCollectAndCheckSparkconfigJava result = new TCollectAndCheckSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    java.util.List<String> reference_";
  protected final String TEXT_2 = " = new java.util.ArrayList<String>();" + NL + "    // replace \\ by \\\\\\\\ in order to parse a \\\\" + NL + "    String lineSeparator_";
  protected final String TEXT_3 = " = ";
  protected final String TEXT_4 = ";";
  protected final String TEXT_5 = NL + "        if ((";
  protected final String TEXT_6 = " != null) && (!\"\".equals(";
  protected final String TEXT_7 = "))) {" + NL + "            reference_";
  protected final String TEXT_8 = ".addAll(java.util.Arrays.asList(";
  protected final String TEXT_9 = ".split(lineSeparator_";
  protected final String TEXT_10 = ")));" + NL + "        }";
  protected final String TEXT_11 = NL + "        // Arrays.asList is create a immutable list, but we want to remove some element." + NL + "        if (";
  protected final String TEXT_12 = " != null) {" + NL + "            reference_";
  protected final String TEXT_13 = ".addAll(java.util.Arrays.asList(";
  protected final String TEXT_14 = ".split(lineSeparator_";
  protected final String TEXT_15 = ")));" + NL + "        }";
  protected final String TEXT_16 = NL + "    java.util.List<String> reference_";
  protected final String TEXT_17 = " = new java.util.ArrayList<String>();" + NL + "    String javajetLineSeparator_";
  protected final String TEXT_18 = " = ";
  protected final String TEXT_19 = ";" + NL + "" + NL + "    String referenceDecodedStr_";
  protected final String TEXT_20 = " = \"\";" + NL + "    try {" + NL + "        if (checkForBase64Encode_";
  protected final String TEXT_21 = "(";
  protected final String TEXT_22 = ")) {" + NL + "            // JUNIT lauch" + NL + "            referenceDecodedStr_";
  protected final String TEXT_23 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(";
  protected final String TEXT_24 = "), utf8Charset);" + NL + "        } else {" + NL + "            // Normal launch" + NL + "            // replace \\ by \\\\" + NL + "            javajetLineSeparator_";
  protected final String TEXT_25 = " = ";
  protected final String TEXT_26 = ";" + NL + "            referenceDecodedStr_";
  protected final String TEXT_27 = " = ";
  protected final String TEXT_28 = ";" + NL + "        }" + NL + "    } catch (java.io.UnsupportedEncodingException e) {" + NL + "        e.printStackTrace();" + NL + "    }" + NL;
  protected final String TEXT_29 = NL + "        if (!\"\".equals(referenceDecodedStr_";
  protected final String TEXT_30 = ")) {" + NL + "         // This will magically make a cross compatibility between window and linux line return" + NL + "            org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_31 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                    new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_32 = ".getBytes(utf8Charset))," + NL + "                    utf8Charset,";
  protected final String TEXT_33 = NL + "                    ";
  protected final String TEXT_34 = "," + NL + "                    javajetLineSeparator_";
  protected final String TEXT_35 = "," + NL + "                    true, 0, 0, -1, -1, false);" + NL + "" + NL + "            while (fid_";
  protected final String TEXT_36 = ".nextRecord()) {" + NL + "                String result_";
  protected final String TEXT_37 = " = \"\";";
  protected final String TEXT_38 = NL + "                    if (";
  protected final String TEXT_39 = " < fid_";
  protected final String TEXT_40 = ".getColumnsCountOfCurrentRow()) {" + NL + "                        result_";
  protected final String TEXT_41 = " += fid_";
  protected final String TEXT_42 = ".get(";
  protected final String TEXT_43 = ").toString() + ";
  protected final String TEXT_44 = ";" + NL + "                    }";
  protected final String TEXT_45 = NL + "                reference_";
  protected final String TEXT_46 = ".add(result_";
  protected final String TEXT_47 = ".substring(0, result_";
  protected final String TEXT_48 = ".length() - 1));" + NL + "            }" + NL + "        }";
  protected final String TEXT_49 = NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_50 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_51 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_52 = NL + "                ";
  protected final String TEXT_53 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_54 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_55 = ".nextRecord()) {" + NL + "            String result_";
  protected final String TEXT_56 = " = \"\";";
  protected final String TEXT_57 = NL + "                if (";
  protected final String TEXT_58 = " < fid_";
  protected final String TEXT_59 = ".getColumnsCountOfCurrentRow()) {" + NL + "                    result_";
  protected final String TEXT_60 = " += fid_";
  protected final String TEXT_61 = ".get(";
  protected final String TEXT_62 = ").toString() + ";
  protected final String TEXT_63 = ";" + NL + "                }";
  protected final String TEXT_64 = NL + "            reference_";
  protected final String TEXT_65 = ".add(result_";
  protected final String TEXT_66 = ".substring(0, result_";
  protected final String TEXT_67 = ".length() - 1));" + NL + "        }";
  protected final String TEXT_68 = NL + "    java.util.List<String> reference_";
  protected final String TEXT_69 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_70 = NL + NL + "        String javajetLineSeparator_";
  protected final String TEXT_71 = " = ";
  protected final String TEXT_72 = ";" + NL + "        String referenceDecodedStr_";
  protected final String TEXT_73 = " = \"\";" + NL + "" + NL + "        try {" + NL + "            referenceDecodedStr_";
  protected final String TEXT_74 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(\"";
  protected final String TEXT_75 = "\"), utf8Charset);" + NL + "        } catch (java.io.UnsupportedEncodingException e) {" + NL + "            e.printStackTrace();" + NL + "        }" + NL + "" + NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_76 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_77 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_78 = NL + "                ";
  protected final String TEXT_79 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_80 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_81 = ".nextRecord()) {" + NL + "            String result_";
  protected final String TEXT_82 = " = \"\";";
  protected final String TEXT_83 = NL + "                if (";
  protected final String TEXT_84 = " < fid_";
  protected final String TEXT_85 = ".getColumnsCountOfCurrentRow()) {" + NL + "                    result_";
  protected final String TEXT_86 = " += fid_";
  protected final String TEXT_87 = ".get(";
  protected final String TEXT_88 = ").toString() + ";
  protected final String TEXT_89 = ";" + NL + "                }";
  protected final String TEXT_90 = NL + "            reference_";
  protected final String TEXT_91 = ".add(result_";
  protected final String TEXT_92 = ".substring(0, result_";
  protected final String TEXT_93 = ".length() - 1));" + NL + "        }";
  protected final String TEXT_94 = NL + NL + "// the globalOutputRDD and staticReference_";
  protected final String TEXT_95 = " will be used to display the diff file if the rdd and the reference differ. " + NL + "StringBuilder globalOutputRDD_";
  protected final String TEXT_96 = " = new StringBuilder();" + NL + "final java.util.List<String> staticReference_";
  protected final String TEXT_97 = " = new java.util.ArrayList<String>(reference_";
  protected final String TEXT_98 = ");" + NL + "" + NL + "// if the output has an element which is not in the reference, it is considered as a new element." + NL + "boolean outputHasNewElement_";
  protected final String TEXT_99 = " = false;" + NL + "" + NL + "// collect the RDD" + NL + "for (";
  protected final String TEXT_100 = " rdd: rdd_";
  protected final String TEXT_101 = ".collect()) {" + NL + "    // dump each line into a string" + NL + "    StringBuilder currentOutputRDD_";
  protected final String TEXT_102 = " = new StringBuilder();";
  protected final String TEXT_103 = NL + "            currentOutputRDD_";
  protected final String TEXT_104 = ".append(BigDataParserUtils.parseTo_String(rdd.";
  protected final String TEXT_105 = ", ";
  protected final String TEXT_106 = ")).append(";
  protected final String TEXT_107 = ");";
  protected final String TEXT_108 = NL + "            currentOutputRDD_";
  protected final String TEXT_109 = ".append(BigDataParserUtils.parseTo_String(rdd.";
  protected final String TEXT_110 = ")).append(";
  protected final String TEXT_111 = ");";
  protected final String TEXT_112 = NL + "    String currentOutputString_";
  protected final String TEXT_113 = " = currentOutputRDD_";
  protected final String TEXT_114 = ".substring(0, currentOutputRDD_";
  protected final String TEXT_115 = ".lastIndexOf(";
  protected final String TEXT_116 = "));" + NL + "    // save the current RDD in the globalOutputRDD" + NL + "    globalOutputRDD_";
  protected final String TEXT_117 = ".append(currentOutputString_";
  protected final String TEXT_118 = ").append(";
  protected final String TEXT_119 = ");" + NL + "" + NL + "    // check this RDD with the ref.";
  protected final String TEXT_120 = NL + "        if ((reference_";
  protected final String TEXT_121 = ".size() > 0) && (reference_";
  protected final String TEXT_122 = ".get(0).equals((currentOutputString_";
  protected final String TEXT_123 = ")))) {" + NL + "            reference_";
  protected final String TEXT_124 = ".remove(currentOutputString_";
  protected final String TEXT_125 = ");" + NL + "        } else {" + NL + "            outputHasNewElement_";
  protected final String TEXT_126 = "= true;" + NL + "        }";
  protected final String TEXT_127 = NL + "        if (reference_";
  protected final String TEXT_128 = ".contains(currentOutputString_";
  protected final String TEXT_129 = ")) {" + NL + "            reference_";
  protected final String TEXT_130 = ".remove(currentOutputString_";
  protected final String TEXT_131 = ");" + NL + "        } else {" + NL + "            outputHasNewElement_";
  protected final String TEXT_132 = " = true;" + NL + "        }";
  protected final String TEXT_133 = NL + "}" + NL + "" + NL + "" + NL + "// display result" + NL + "if (outputHasNewElement_";
  protected final String TEXT_134 = " || reference_";
  protected final String TEXT_135 = ".size() > 0) {" + NL + "    // if the output has new element and if the output does miss at least one element, display the error message" + NL + "    " + NL + "    StringBuilder sb_";
  protected final String TEXT_136 = " = new StringBuilder();" + NL + "    String NL_";
  protected final String TEXT_137 = " = System.getProperty(\"line.separator\");" + NL + "    sb_";
  protected final String TEXT_138 = ".append(\"";
  protected final String TEXT_139 = ": the reference data does not match the computed results\" + NL_";
  protected final String TEXT_140 = ");" + NL + "    sb_";
  protected final String TEXT_141 = ".append(\"New elements: \"+ outputHasNewElement_";
  protected final String TEXT_142 = " + NL_";
  protected final String TEXT_143 = ");" + NL + "    sb_";
  protected final String TEXT_144 = ".append(\"Missing: \"+ (reference_";
  protected final String TEXT_145 = ".size() > 0) + NL_";
  protected final String TEXT_146 = ");" + NL + "    sb_";
  protected final String TEXT_147 = ".append(\"==================\" + NL_";
  protected final String TEXT_148 = ");" + NL + "    sb_";
  protected final String TEXT_149 = ".append(\"=== REFERENCE ====\" + NL_";
  protected final String TEXT_150 = ");" + NL + "    sb_";
  protected final String TEXT_151 = ".append(\"==================\" + NL_";
  protected final String TEXT_152 = ");" + NL + "    for (String element:staticReference_";
  protected final String TEXT_153 = ") {" + NL + "    \tsb_";
  protected final String TEXT_154 = ".append(element + NL_";
  protected final String TEXT_155 = ");" + NL + "    }" + NL + "    sb_";
  protected final String TEXT_156 = ".append(\"==================\" + NL_";
  protected final String TEXT_157 = ");" + NL + "    sb_";
  protected final String TEXT_158 = ".append(\"==== RESULTS =====\" + NL_";
  protected final String TEXT_159 = ");" + NL + "    sb_";
  protected final String TEXT_160 = ".append(\"==================\" + NL_";
  protected final String TEXT_161 = ");" + NL + "    sb_";
  protected final String TEXT_162 = ".append(globalOutputRDD_";
  protected final String TEXT_163 = " + NL_";
  protected final String TEXT_164 = ");" + NL + "    sb_";
  protected final String TEXT_165 = ".append(\"==================\" + NL_";
  protected final String TEXT_166 = ");" + NL + "    sb_";
  protected final String TEXT_167 = ".append(\"==== MISSING =====\" + NL_";
  protected final String TEXT_168 = ");" + NL + "    sb_";
  protected final String TEXT_169 = ".append(\"==================\" + NL_";
  protected final String TEXT_170 = ");" + NL + "    sb_";
  protected final String TEXT_171 = ".append(reference_";
  protected final String TEXT_172 = " + NL_";
  protected final String TEXT_173 = ");" + NL + "    sb_";
  protected final String TEXT_174 = ".append(\"==================\" + NL_";
  protected final String TEXT_175 = ");";
  protected final String TEXT_176 = NL + "            if (junitGlobalMap.get(\"tests.log\") == null) {" + NL + "                junitGlobalMap.put(\"tests.log\", new String());" + NL + "            }" + NL + "            if (junitGlobalMap.get(\"tests.nbFailure\") == null) {" + NL + "                junitGlobalMap.put(\"tests.nbFailure\", new Integer(0));" + NL + "            }" + NL + "            junitGlobalMap.put(\"tests.log\"," + NL + "                    ((String)junitGlobalMap.get(\"tests.log\"))" + NL + "                    + \"";
  protected final String TEXT_177 = ":\" + NL_";
  protected final String TEXT_178 = NL + "                    + sb_";
  protected final String TEXT_179 = ".toString());" + NL + "            junitGlobalMap.put(\"tests.nbFailure\",((Integer)junitGlobalMap.get(\"tests.nbFailure\")) + 1);";
  protected final String TEXT_180 = NL + "            LOG.error(sb_";
  protected final String TEXT_181 = ".toString());";
  protected final String TEXT_182 = NL + "            System.out.println(sb_";
  protected final String TEXT_183 = ".toString());";
  protected final String TEXT_184 = NL + "        // Then throw an error" + NL + "        throw new RuntimeException(\"Error on component ";
  protected final String TEXT_185 = ".\");";
  protected final String TEXT_186 = NL + "} else {";
  protected final String TEXT_187 = NL + "        LOG.info(\"";
  protected final String TEXT_188 = ": the reference data matches the computed results\");";
  protected final String TEXT_189 = NL + "        System.out.println(\"";
  protected final String TEXT_190 = ": the reference data matches the computed results\");";
  protected final String TEXT_191 = NL + "        assertTrue(true);";
  protected final String TEXT_192 = NL + "}";
  protected final String TEXT_193 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
if(inConns != null && inConns.size() > 0) {
} else {
	return "";
}

IConnection inConn = inConns.get(0);
String inConnName = inConn.getName();
String inConnTypeName = codeGenArgument.getRecordStructName(inConn);
String separator = ElementParameterParser.getValue(node, "__SEPARATOR__");
String lineSeparator = ElementParameterParser.getValue(node, "__LINE_SEPARATOR__");

boolean useContext = ("true").equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"));
String reference = ElementParameterParser.getValue(node, "__REFERENCE__");
String contextVariable = ElementParameterParser.getValue(node, "__CONTEXT_VARIABLE__");
boolean keepOrdering = ("true").equals(ElementParameterParser.getValue(node, "__ORDERING__"));
Boolean emptyValueMeanNoResult = ElementParameterParser.getBooleanValue(node, "__EMPTY_VALUE_MEAN_NO_RESULT__");

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

org.talend.core.model.process.IProcess process = node.getProcess();
if ((useContext) && !(org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // The context is a normal string for normal job,
    // but in test mode, it is an encoded string, so we need to uncoded it.
    
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(lineSeparator.replace("\\", "\\\\\\\\"));
    stringBuffer.append(TEXT_4);
    
    if (emptyValueMeanNoResult) {
        
    stringBuffer.append(TEXT_5);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
    } else {
        
    stringBuffer.append(TEXT_11);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    
    }
} else if ((useContext) && (org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // if we are on test job, we need to uncode the input string
    // since it is on a variable, we need to do taht on the java side.
    
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(lineSeparator.replace("\\", "\\\\"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_28);
    
    if (emptyValueMeanNoResult) {
        
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    
                int i = -1;
                for (IMetadataColumn column : inConn.getMetadataTable().getListColumns()) {
                    i++;
                    
    stringBuffer.append(TEXT_38);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_44);
    
                }
                
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
    } else {
        
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
            int i = -1;
            for (IMetadataColumn column : inConn.getMetadataTable().getListColumns()) {
                i++;
                
    stringBuffer.append(TEXT_57);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(separator);
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
} else { // use direct input
    
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    
    if ((!emptyValueMeanNoResult) || ((reference != null) && (!"".equals(reference)))) {
        // if the reference was directly written we need to uncode the input string
        // First encode on javajet side
        String referenceEnCodeStr = "";
        try {
            referenceEnCodeStr = (new sun.misc.BASE64Encoder()).encode(reference.getBytes("UTF-8"));
        } catch (java.io.UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // We use the system line separator here.
        referenceEnCodeStr = referenceEnCodeStr.replace((String) java.security.AccessController.doPrivileged(
                new sun.security.action.GetPropertyAction("line.separator")), "\"+\"");
        // then decode on generated code
        
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(referenceEnCodeStr);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    
            int i = -1;
            for (IMetadataColumn column : inConn.getMetadataTable().getListColumns()) {
                i++;
                
    stringBuffer.append(TEXT_83);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_89);
    
            }
            
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    
    }
}

    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(inConnName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    
    for(IMetadataColumn column: inConn.getMetadataTable().getListColumns()) {
        if (JavaTypesManager.getJavaTypeFromId(column.getTalendType()) == JavaTypesManager.DATE) {
            
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_107);
    
        } else {
            
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_111);
    
        }
    }
    
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_119);
    
    if (keepOrdering) {
        
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    
    } else {
        
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    
    }
    
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    
    if(ProcessUtils.isTestContainer(node.getProcess())) {
        
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    
        }
    else { // not a testCase
        if (isLog4jEnabled) {
            
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    
        } else {
            
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_183);
    
        }
        
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_185);
    
    }
    
    stringBuffer.append(TEXT_186);
    
    if (isLog4jEnabled) {
        
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    
    } else {
        
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    
    }

    if(ProcessUtils.isTestContainer(node.getProcess())) {
        
    stringBuffer.append(TEXT_191);
    
    }
    
    stringBuffer.append(TEXT_192);
    stringBuffer.append(TEXT_193);
    return stringBuffer.toString();
  }
}
