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

public class TCollectAndCheckSparkstreamingpostprocessingJava
{
  protected static String nl;
  public static synchronized TCollectAndCheckSparkstreamingpostprocessingJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCollectAndCheckSparkstreamingpostprocessingJava result = new TCollectAndCheckSparkstreamingpostprocessingJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    java.util.List<java.util.List<String>> reference_";
  protected final String TEXT_2 = " = new java.util.ArrayList<java.util.List<String>>();" + NL + "    java.util.List<String> referenceCurrentBatch_";
  protected final String TEXT_3 = " = new java.util.ArrayList<String>();" + NL + "    String javajetLineSeparator_";
  protected final String TEXT_4 = " = ";
  protected final String TEXT_5 = ";" + NL + "" + NL + "    String referenceDecodedStr_";
  protected final String TEXT_6 = " = \"\";" + NL + "    try {" + NL + "        if (checkForBase64Encode_";
  protected final String TEXT_7 = "(";
  protected final String TEXT_8 = ")) {" + NL + "            // JUNIT lauch" + NL + "            referenceDecodedStr_";
  protected final String TEXT_9 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(";
  protected final String TEXT_10 = "), utf8Charset);" + NL + "        } else {" + NL + "            // Normal launch" + NL + "            // replace \\ by \\\\" + NL + "            javajetLineSeparator_";
  protected final String TEXT_11 = " = ";
  protected final String TEXT_12 = ";" + NL + "            referenceDecodedStr_";
  protected final String TEXT_13 = " = ";
  protected final String TEXT_14 = ";" + NL + "        }" + NL + "    } catch (java.io.UnsupportedEncodingException e) {" + NL + "        e.printStackTrace();" + NL + "    }" + NL;
  protected final String TEXT_15 = NL + "        if (!\"\".equals(referenceDecodedStr_";
  protected final String TEXT_16 = ")) {" + NL + "         // This will magically make a cross compatibility between window and linux line return" + NL + "            org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_17 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                    new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_18 = ".getBytes(utf8Charset))," + NL + "                    utf8Charset,";
  protected final String TEXT_19 = NL + "                    ";
  protected final String TEXT_20 = "," + NL + "                    javajetLineSeparator_";
  protected final String TEXT_21 = "," + NL + "                    true, 0, 0, -1, -1, false);" + NL + "" + NL + "            while (fid_";
  protected final String TEXT_22 = ".nextRecord()) {" + NL + "                if ((fid_";
  protected final String TEXT_23 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_24 = ".get(0)).equals(";
  protected final String TEXT_25 = ")) {" + NL + "                    reference_";
  protected final String TEXT_26 = ".add(referenceCurrentBatch_";
  protected final String TEXT_27 = ");" + NL + "                    referenceCurrentBatch_";
  protected final String TEXT_28 = " = new java.util.ArrayList();" + NL + "                } else {" + NL + "                    String result_";
  protected final String TEXT_29 = " = \"\";";
  protected final String TEXT_30 = NL + "                        if (";
  protected final String TEXT_31 = " < fid_";
  protected final String TEXT_32 = ".getColumnsCountOfCurrentRow()) {" + NL + "                            result_";
  protected final String TEXT_33 = " += fid_";
  protected final String TEXT_34 = ".get(";
  protected final String TEXT_35 = ").toString() + ";
  protected final String TEXT_36 = ";" + NL + "                        }";
  protected final String TEXT_37 = NL + "                    referenceCurrentBatch_";
  protected final String TEXT_38 = ".add(result_";
  protected final String TEXT_39 = ".substring(0, result_";
  protected final String TEXT_40 = ".length() - 1));" + NL + "                }" + NL + "            }" + NL + "            reference_";
  protected final String TEXT_41 = ".add(referenceCurrentBatch_";
  protected final String TEXT_42 = ");" + NL + "        }";
  protected final String TEXT_43 = NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_44 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_45 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_46 = NL + "                ";
  protected final String TEXT_47 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_48 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_49 = ".nextRecord()) {" + NL + "            if ((fid_";
  protected final String TEXT_50 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_51 = ".get(0)).equals(";
  protected final String TEXT_52 = ")) {" + NL + "                reference_";
  protected final String TEXT_53 = ".add(referenceCurrentBatch_";
  protected final String TEXT_54 = ");" + NL + "                referenceCurrentBatch_";
  protected final String TEXT_55 = " = new java.util.ArrayList();" + NL + "            } else {" + NL + "                String result_";
  protected final String TEXT_56 = " = \"\";";
  protected final String TEXT_57 = NL + "                    if (";
  protected final String TEXT_58 = " < fid_";
  protected final String TEXT_59 = ".getColumnsCountOfCurrentRow()) {" + NL + "                        result_";
  protected final String TEXT_60 = " += fid_";
  protected final String TEXT_61 = ".get(";
  protected final String TEXT_62 = ").toString() + ";
  protected final String TEXT_63 = ";" + NL + "                    }";
  protected final String TEXT_64 = NL + "                referenceCurrentBatch_";
  protected final String TEXT_65 = ".add(result_";
  protected final String TEXT_66 = ".substring(0, result_";
  protected final String TEXT_67 = ".length() - 1));" + NL + "            }" + NL + "        }" + NL + "        reference_";
  protected final String TEXT_68 = ".add(referenceCurrentBatch_";
  protected final String TEXT_69 = ");";
  protected final String TEXT_70 = NL + "    java.util.List<java.util.List<String>> reference_";
  protected final String TEXT_71 = " = new java.util.ArrayList<java.util.List<String>>();" + NL + "    java.util.List<String> referenceCurrentBatch_";
  protected final String TEXT_72 = " = new java.util.ArrayList<String>();";
  protected final String TEXT_73 = NL + NL + "        String javajetLineSeparator_";
  protected final String TEXT_74 = " = ";
  protected final String TEXT_75 = ";" + NL + "        String referenceDecodedStr_";
  protected final String TEXT_76 = " = \"\";" + NL + "" + NL + "        try {" + NL + "            referenceDecodedStr_";
  protected final String TEXT_77 = " = new String(new sun.misc.BASE64Decoder().decodeBuffer(\"";
  protected final String TEXT_78 = "\"), utf8Charset);" + NL + "        } catch (java.io.UnsupportedEncodingException e) {" + NL + "            e.printStackTrace();" + NL + "        }" + NL + "" + NL + "        // This will magically make a cross compatibility between window and linux line return" + NL + "        org.talend.fileprocess.FileInputDelimited fid_";
  protected final String TEXT_79 = " = new org.talend.fileprocess.FileInputDelimited(" + NL + "                new java.io.ByteArrayInputStream(referenceDecodedStr_";
  protected final String TEXT_80 = ".getBytes(utf8Charset))," + NL + "                utf8Charset,";
  protected final String TEXT_81 = NL + "                ";
  protected final String TEXT_82 = "," + NL + "                javajetLineSeparator_";
  protected final String TEXT_83 = "," + NL + "                true, 0, 0, -1, -1, false);" + NL + "" + NL + "        while (fid_";
  protected final String TEXT_84 = ".nextRecord()) {" + NL + "            if ((fid_";
  protected final String TEXT_85 = ".getColumnsCountOfCurrentRow() == 1L) && ((String) fid_";
  protected final String TEXT_86 = ".get(0)).equals(";
  protected final String TEXT_87 = ")) {" + NL + "                reference_";
  protected final String TEXT_88 = ".add(referenceCurrentBatch_";
  protected final String TEXT_89 = ");" + NL + "                referenceCurrentBatch_";
  protected final String TEXT_90 = " = new java.util.ArrayList();" + NL + "            } else {" + NL + "                String result_";
  protected final String TEXT_91 = " = \"\";";
  protected final String TEXT_92 = NL + "                    if (";
  protected final String TEXT_93 = " < fid_";
  protected final String TEXT_94 = ".getColumnsCountOfCurrentRow()) {" + NL + "                        result_";
  protected final String TEXT_95 = " += fid_";
  protected final String TEXT_96 = ".get(";
  protected final String TEXT_97 = ").toString() + ";
  protected final String TEXT_98 = ";" + NL + "                    }";
  protected final String TEXT_99 = NL + "                referenceCurrentBatch_";
  protected final String TEXT_100 = ".add(result_";
  protected final String TEXT_101 = ".substring(0, result_";
  protected final String TEXT_102 = ".length() - 1));" + NL + "            }" + NL + "        }" + NL + "        reference_";
  protected final String TEXT_103 = ".add(referenceCurrentBatch_";
  protected final String TEXT_104 = ");";
  protected final String TEXT_105 = NL + NL + NL + "//the globalOutputRDD and staticReference_";
  protected final String TEXT_106 = " will be used to display the diff file if the rdd and the reference differ. " + NL + "java.util.List<StringBuilder> globalOutputRDD_";
  protected final String TEXT_107 = " = new java.util.ArrayList<StringBuilder>();" + NL + "final java.util.List<java.util.List<String>> staticReference_";
  protected final String TEXT_108 = " = new java.util.ArrayList<java.util.List<String>>();" + NL + "for (java.util.List<String> currentBatch: reference_";
  protected final String TEXT_109 = ") {" + NL + "    staticReference_";
  protected final String TEXT_110 = ".add(new java.util.ArrayList<String>(currentBatch));" + NL + "}" + NL + "" + NL + "//if the output has an element which is not in the reference, it is considered as a new element." + NL + "boolean outputHasNewElement_";
  protected final String TEXT_111 = " = false;" + NL + "" + NL + "//collect the RDD" + NL + "for (int batchIterator_";
  protected final String TEXT_112 = " = 0; batchIterator_";
  protected final String TEXT_113 = " < staticReference_";
  protected final String TEXT_114 = ".size(); batchIterator_";
  protected final String TEXT_115 = "++) {" + NL + "    StringBuilder currentBatchOutputRDD_";
  protected final String TEXT_116 = " = new StringBuilder();" + NL + "" + NL + "    for (org.apache.avro.specific.SpecificRecordBase specificRdd: manualClock.getActual(\"";
  protected final String TEXT_117 = "\", batchIterator_";
  protected final String TEXT_118 = ")) {";
  protected final String TEXT_119 = NL + "        ";
  protected final String TEXT_120 = " rdd = (";
  protected final String TEXT_121 = ") specificRdd; " + NL + "        // dump each line into a string" + NL + "        StringBuilder currentOutputRDD_";
  protected final String TEXT_122 = " = new StringBuilder();";
  protected final String TEXT_123 = NL + "                currentOutputRDD_";
  protected final String TEXT_124 = ".append(BigDataParserUtils.parseTo_String(rdd.";
  protected final String TEXT_125 = ", ";
  protected final String TEXT_126 = ")).append(";
  protected final String TEXT_127 = ");";
  protected final String TEXT_128 = NL + "                currentOutputRDD_";
  protected final String TEXT_129 = ".append(BigDataParserUtils.parseTo_String(rdd.";
  protected final String TEXT_130 = ")).append(";
  protected final String TEXT_131 = ");";
  protected final String TEXT_132 = NL + "        String currentOutputString_";
  protected final String TEXT_133 = " = currentOutputRDD_";
  protected final String TEXT_134 = ".substring(0, currentOutputRDD_";
  protected final String TEXT_135 = ".lastIndexOf(";
  protected final String TEXT_136 = "));" + NL + "        // save the current RDD in the globalOutputRDD" + NL + "        currentBatchOutputRDD_";
  protected final String TEXT_137 = ".append(currentOutputString_";
  protected final String TEXT_138 = ").append(";
  protected final String TEXT_139 = ");" + NL + "    " + NL + "        // check this RDD with the ref.";
  protected final String TEXT_140 = NL + "            if ((reference_";
  protected final String TEXT_141 = ".get(batchIterator_";
  protected final String TEXT_142 = ").size() > 0)" + NL + "                    && (reference_";
  protected final String TEXT_143 = ".get(batchIterator_";
  protected final String TEXT_144 = ").get(0).equals((currentOutputString_";
  protected final String TEXT_145 = ")))) {" + NL + "                reference_";
  protected final String TEXT_146 = ".get(batchIterator_";
  protected final String TEXT_147 = ").remove(currentOutputString_";
  protected final String TEXT_148 = ");" + NL + "            } else {" + NL + "                outputHasNewElement_";
  protected final String TEXT_149 = "= true;" + NL + "            }";
  protected final String TEXT_150 = NL + "            if (reference_";
  protected final String TEXT_151 = ".get(batchIterator_";
  protected final String TEXT_152 = ").contains(currentOutputString_";
  protected final String TEXT_153 = ")) {" + NL + "                reference_";
  protected final String TEXT_154 = ".get(batchIterator_";
  protected final String TEXT_155 = ").remove(currentOutputString_";
  protected final String TEXT_156 = ");" + NL + "            } else {" + NL + "                outputHasNewElement_";
  protected final String TEXT_157 = " = true;" + NL + "            }";
  protected final String TEXT_158 = NL + "    }" + NL + "" + NL + "    globalOutputRDD_";
  protected final String TEXT_159 = ".add(currentBatchOutputRDD_";
  protected final String TEXT_160 = ");" + NL + "}" + NL + "" + NL + "" + NL + "//display result" + NL + "boolean missingElement_";
  protected final String TEXT_161 = " = false;" + NL + "for (java.util.List<String> batch: reference_";
  protected final String TEXT_162 = ") {" + NL + "    missingElement_";
  protected final String TEXT_163 = " = missingElement_";
  protected final String TEXT_164 = " || batch.size() > 0;" + NL + "}" + NL + "if (outputHasNewElement_";
  protected final String TEXT_165 = " || missingElement_";
  protected final String TEXT_166 = ") {" + NL + "    // if the output has new element and if the output does miss at least one element, display the error message" + NL + " " + NL + "    StringBuilder sb_";
  protected final String TEXT_167 = " = new StringBuilder();" + NL + "    String NL_";
  protected final String TEXT_168 = " = System.getProperty(\"line.separator\");" + NL + "    sb_";
  protected final String TEXT_169 = ".append(\"";
  protected final String TEXT_170 = ": the reference data does not match the computed results\" + NL_";
  protected final String TEXT_171 = ");" + NL + "    sb_";
  protected final String TEXT_172 = ".append(\"New elements: \" + outputHasNewElement_";
  protected final String TEXT_173 = " + NL_";
  protected final String TEXT_174 = ");" + NL + "    sb_";
  protected final String TEXT_175 = ".append(\"Missing: \"+ missingElement_";
  protected final String TEXT_176 = " + NL_";
  protected final String TEXT_177 = ");" + NL + "    sb_";
  protected final String TEXT_178 = ".append(\"====================\" + NL_";
  protected final String TEXT_179 = ");" + NL + "    sb_";
  protected final String TEXT_180 = ".append(\"==== REFERENCE =====\" + NL_";
  protected final String TEXT_181 = ");" + NL + "    sb_";
  protected final String TEXT_182 = ".append(\"====================\" + NL_";
  protected final String TEXT_183 = ");" + NL + "    for (int i = 0; i < staticReference_";
  protected final String TEXT_184 = ".size(); i++) {" + NL + "        if (i != 0) {" + NL + "            sb_";
  protected final String TEXT_185 = ".append(";
  protected final String TEXT_186 = " + NL_";
  protected final String TEXT_187 = ");" + NL + "        }" + NL + "        for (String element: staticReference_";
  protected final String TEXT_188 = ".get(i)) {" + NL + "            sb_";
  protected final String TEXT_189 = ".append(element + NL_";
  protected final String TEXT_190 = ");" + NL + "        }" + NL + "    }" + NL + "    sb_";
  protected final String TEXT_191 = ".append(\"====================\" + NL_";
  protected final String TEXT_192 = ");" + NL + "    sb_";
  protected final String TEXT_193 = ".append(\"===== RESULTS ======\" + NL_";
  protected final String TEXT_194 = ");" + NL + "    sb_";
  protected final String TEXT_195 = ".append(\"====================\" + NL_";
  protected final String TEXT_196 = ");" + NL + "    for (int i = 0; i < globalOutputRDD_";
  protected final String TEXT_197 = ".size(); i++) {" + NL + "        if (i != 0) {" + NL + "            sb_";
  protected final String TEXT_198 = ".append(";
  protected final String TEXT_199 = " + NL_";
  protected final String TEXT_200 = ");" + NL + "        }" + NL + "        sb_";
  protected final String TEXT_201 = ".append(globalOutputRDD_";
  protected final String TEXT_202 = ".get(i));" + NL + "    }" + NL + "    sb_";
  protected final String TEXT_203 = ".append(\"====================\" + NL_";
  protected final String TEXT_204 = ");" + NL + "    sb_";
  protected final String TEXT_205 = ".append(\"= MISSING BY BATCH =\" + NL_";
  protected final String TEXT_206 = ");" + NL + "    sb_";
  protected final String TEXT_207 = ".append(\"====================\" + NL_";
  protected final String TEXT_208 = ");" + NL + "    for (int i = 0; i < reference_";
  protected final String TEXT_209 = ".size(); i++) {" + NL + "        if (i != 0) {" + NL + "            sb_";
  protected final String TEXT_210 = ".append(";
  protected final String TEXT_211 = " + NL_";
  protected final String TEXT_212 = ");" + NL + "        }" + NL + "        for (String element: reference_";
  protected final String TEXT_213 = ".get(i)) {" + NL + "            sb_";
  protected final String TEXT_214 = ".append(element + NL_";
  protected final String TEXT_215 = ");" + NL + "        }" + NL + "    }" + NL + "    sb_";
  protected final String TEXT_216 = ".append(\"====================\" + NL_";
  protected final String TEXT_217 = ");";
  protected final String TEXT_218 = NL + "        if (junitGlobalMap.get(\"tests.log\") == null) {" + NL + "            junitGlobalMap.put(\"tests.log\", new String());" + NL + "        }" + NL + "        if (junitGlobalMap.get(\"tests.nbFailure\") == null) {" + NL + "            junitGlobalMap.put(\"tests.nbFailure\", new Integer(0));" + NL + "        }" + NL + "        junitGlobalMap.put(\"tests.log\"," + NL + "                ((String)junitGlobalMap.get(\"tests.log\"))" + NL + "                + \"";
  protected final String TEXT_219 = ":\" + NL_";
  protected final String TEXT_220 = NL + "                + sb_";
  protected final String TEXT_221 = ".toString());" + NL + "        junitGlobalMap.put(\"tests.nbFailure\",((Integer)junitGlobalMap.get(\"tests.nbFailure\")) + 1);";
  protected final String TEXT_222 = NL + "            LOG.error(sb_";
  protected final String TEXT_223 = ".toString());";
  protected final String TEXT_224 = NL + "            System.out.println(sb_";
  protected final String TEXT_225 = ".toString());";
  protected final String TEXT_226 = NL + "        // Then throw an error" + NL + "        throw new RuntimeException(\"Error on component ";
  protected final String TEXT_227 = ".\");";
  protected final String TEXT_228 = NL + "} else {";
  protected final String TEXT_229 = NL + "        LOG.info(\"";
  protected final String TEXT_230 = ": the reference data matches the computed results\");";
  protected final String TEXT_231 = NL + "        System.out.println(\"";
  protected final String TEXT_232 = ": the reference data matches the computed results\");";
  protected final String TEXT_233 = NL + "        assertTrue(true);";
  protected final String TEXT_234 = NL + "}";
  protected final String TEXT_235 = NL;

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
String batchSeparator = ElementParameterParser.getValue(node, "__BATCH_SEPARATOR__");

boolean useContext = ("true").equals(ElementParameterParser.getValue(node, "__USE_CONTEXT__"));
String reference = ElementParameterParser.getValue(node, "__REFERENCE__");
String contextVariable = ElementParameterParser.getValue(node, "__CONTEXT_VARIABLE__");
boolean keepOrdering = ("true").equals(ElementParameterParser.getValue(node, "__ORDERING__"));
//option desactivated: if you want empty microbatch batch, you have to write it anyway.
Boolean emptyValueMeanNoResult = false; 

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

org.talend.core.model.process.IProcess process = node.getProcess();
if ((useContext) && !(org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // Currently not handled
} else if ((useContext) && (org.talend.core.model.process.ProcessUtils.isTestContainer(process))) {
    // if we are on test job, we need to uncode the input string
    // since it is on a variable, we need to do taht on the java side.
    
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(lineSeparator.replace("\\", "\\\\"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(contextVariable);
    stringBuffer.append(TEXT_14);
    
    if (emptyValueMeanNoResult) {
        
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
                    int i = -1;
                    for (IMetadataColumn column : inConn.getMetadataTable().getListColumns()) {
                        i++;
                        
    stringBuffer.append(TEXT_30);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_36);
    
                    }
                    
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
    
    } else {
        
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    
    }
} else { // use direct input
    
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    
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
        
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(referenceEnCodeStr);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    
                int i = -1;
                for (IMetadataColumn column : inConn.getMetadataTable().getListColumns()) {
                    i++;
                    
    stringBuffer.append(TEXT_92);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_98);
    
                }
                
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    
    }
}

    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    
        for(IMetadataColumn column: inConn.getMetadataTable().getListColumns()) {
            if (JavaTypesManager.getJavaTypeFromId(column.getTalendType()) == JavaTypesManager.DATE) {
                
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_127);
    
            } else {
                
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_131);
    
            }
        }
        
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(separator);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(lineSeparator);
    stringBuffer.append(TEXT_139);
    
        if (keepOrdering) {
            
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
    
        } else {
            
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
    
        }
        
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(batchSeparator);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    
    if(ProcessUtils.isTestContainer(node.getProcess())) {
        
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    
    } else { // not a testCase
        if (isLog4jEnabled) {
            
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    
        } else {
            
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    
        }
        
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    
    }
    
    stringBuffer.append(TEXT_228);
    
    if (isLog4jEnabled) {
        
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    
    } else {
        
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_232);
    
    }

    if(ProcessUtils.isTestContainer(node.getProcess())) {
        
    stringBuffer.append(TEXT_233);
    
    }
    
    stringBuffer.append(TEXT_234);
    stringBuffer.append(TEXT_235);
    return stringBuffer.toString();
  }
}
