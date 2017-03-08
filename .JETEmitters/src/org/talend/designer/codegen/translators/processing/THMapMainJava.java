package org.talend.designer.codegen.translators.processing;

import java.util.Set;
import org.talend.transform.component.thmap.MapperComponent;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class THMapMainJava
{
  protected static String nl;
  public static synchronized THMapMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THMapMainJava result = new THMapMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\tlist_";
  protected final String TEXT_3 = " = new java.util.ArrayList();";
  protected final String TEXT_4 = NL + "\tjava.util.Map<String, Object> map_";
  protected final String TEXT_5 = " = new java.util.HashMap();";
  protected final String TEXT_6 = NL + " \tmap_";
  protected final String TEXT_7 = ".put(\"";
  protected final String TEXT_8 = "\", ";
  protected final String TEXT_9 = ".";
  protected final String TEXT_10 = ");";
  protected final String TEXT_11 = NL + "\tlist_";
  protected final String TEXT_12 = ".add(map_";
  protected final String TEXT_13 = ");";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL + "    javax.xml.transform.Result result_";
  protected final String TEXT_16 = " = null; " + NL + "   \torg.talend.transform.runtime.api.RuntimeEngine __tdmRuntime_";
  protected final String TEXT_17 = " = null;" + NL + "   \torg.talend.transform.runtime.api.MapRuntime mapRuntime_";
  protected final String TEXT_18 = " = null;";
  protected final String TEXT_19 = NL + "   \torg.talend.transform.runtime.api.MapPathHelper mapPathHelper_";
  protected final String TEXT_20 = " = new org.talend.transform.runtime.api.MapPathHelper(";
  protected final String TEXT_21 = ", ";
  protected final String TEXT_22 = ", null, ";
  protected final String TEXT_23 = ");";
  protected final String TEXT_24 = NL + "\torg.talend.transform.runtime.api.MapPathHelper mapPathHelper_";
  protected final String TEXT_25 = " = new org.talend.transform.runtime.api.MapPathHelper(\"";
  protected final String TEXT_26 = "\", ";
  protected final String TEXT_27 = ", null, ";
  protected final String TEXT_28 = ");";
  protected final String TEXT_29 = NL + "\torg.talend.transform.runtime.api.MapPathHelper mapPathHelper_";
  protected final String TEXT_30 = " = new org.talend.transform.runtime.api.MapPathHelper(null, ";
  protected final String TEXT_31 = ", null, ";
  protected final String TEXT_32 = ");";
  protected final String TEXT_33 = NL + NL + "    synchronized (routines.system.RuntimeMap.getInstance().getRuntimeMap()) {" + NL + "    " + NL + "    \tif (routines.system.RuntimeMap.getInstance().getRuntimeMap().containsKey(\"__tdmRuntime\")) {" + NL + "        \t__tdmRuntime_";
  protected final String TEXT_34 = " = (org.talend.transform.runtime.api.RuntimeEngine) routines.system.RuntimeMap.getInstance().getRuntimeMap().get(\"__tdmRuntime\");" + NL + "    \t} else {";
  protected final String TEXT_35 = NL + "        \t\tSystem.setProperty(org.talend.transform.runtime.api.RuntimeFactory.ODT_DEV_WORKSPACE_VAR, mapPathHelper_";
  protected final String TEXT_36 = ".getWorkspace());" + NL + "        \t\tSystem.setProperty(org.talend.transform.runtime.api.RuntimeFactory.ODT_DEV_INSTALL_VAR, ";
  protected final String TEXT_37 = ");";
  protected final String TEXT_38 = NL + "\t\t\tif (mapPathHelper_";
  protected final String TEXT_39 = ".isValidMapPath()) {" + NL + "\t\t\t\tSystem.setProperty(org.talend.transform.runtime.api.RuntimeFactory.ODT_DEV_WORKSPACE_VAR, mapPathHelper_";
  protected final String TEXT_40 = ".getWorkspace());" + NL + "\t\t\t}";
  protected final String TEXT_41 = NL + "        \t__tdmRuntime_";
  protected final String TEXT_42 = " = org.talend.transform.runtime.api.RuntimeFactory.createRuntime();" + NL + "        \t__tdmRuntime_";
  protected final String TEXT_43 = ".setLogging(\"";
  protected final String TEXT_44 = "\", org.talend.transform.runtime.api.RuntimeEngine.LOG_DEST_CONSOLE);" + NL + "            routines.system.RuntimeMap.getInstance().getRuntimeMap().put(\"__tdmRuntime\",__tdmRuntime_";
  protected final String TEXT_45 = ");" + NL + "\t\t}" + NL + "    " + NL + "    \tif (";
  protected final String TEXT_46 = ".this.globalMap.containsKey(\"mapRuntime_";
  protected final String TEXT_47 = "\")) {" + NL + "        \tmapRuntime_";
  protected final String TEXT_48 = " = (org.talend.transform.runtime.api.MapRuntime) ";
  protected final String TEXT_49 = ".this.globalMap.get(\"mapRuntime_";
  protected final String TEXT_50 = "\");" + NL + "    \t} else {";
  protected final String TEXT_51 = NL + "            \t__tdmRuntime_";
  protected final String TEXT_52 = ".addProjectDevLink(mapPathHelper_";
  protected final String TEXT_53 = ".isValidMapPath() ? mapPathHelper_";
  protected final String TEXT_54 = ".getProjectName() : \"";
  protected final String TEXT_55 = "\");";
  protected final String TEXT_56 = NL + "\t\t\t\tif (mapPathHelper_";
  protected final String TEXT_57 = ".isValidMapPath()) {" + NL + "\t\t\t\t\t__tdmRuntime_";
  protected final String TEXT_58 = ".addProjectDevLink(mapPathHelper_";
  protected final String TEXT_59 = ".getProjectName());" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\tjava.io.File userDir = new java.io.File(System.getProperty(\"user.dir\"));" + NL + "\t\t\t\t\tjava.net.URL location = null;" + NL + "\t\t\t\t\tlocation = userDir.toURI().toURL();" + NL + "\t\t\t\t\tString pas = null;" + NL + "\t\t\t\t\tboolean isExportedAsOSGI = org.talend.transform.runtime.api.RuntimeFactory.inOsgi();" + NL + "\t\t\t\t\tif (isExportedAsOSGI)" + NL + "\t\t\t\t\t\tpas = ";
  protected final String TEXT_60 = ".replace(\"file://\", \"classpath://\").replace(\" \", \"%20\");" + NL + "\t\t\t\t\telse" + NL + "\t\t\t\t\t\tpas = ";
  protected final String TEXT_61 = ".replace(\"file://\", location.toString()).replace(\" \", \"%20\");" + NL + "\t\t\t\t\tString projectArchiveArray[] = pas.split(\",\", 0);" + NL + "" + NL + "\t\t\t\t\tfor (int i = 0; i < projectArchiveArray.length; i++) {" + NL + "\t\t\t\t\t\tif (projectArchiveArray[i].length() == 0)" + NL + "\t\t\t\t\t\t\tcontinue;" + NL + "\t\t\t\t\t\tjava.net.URI uri = null;" + NL + "\t\t\t\t\t\turi = new java.net.URI(projectArchiveArray[i]);" + NL + "\t\t\t\t\t\t__tdmRuntime_";
  protected final String TEXT_62 = ".addProjectUri(uri);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}";
  protected final String TEXT_63 = NL + "\t\tmapRuntime_";
  protected final String TEXT_64 = " = __tdmRuntime_";
  protected final String TEXT_65 = ".getMapRuntime(mapPathHelper_";
  protected final String TEXT_66 = ".getMap());" + NL + "    \t";
  protected final String TEXT_67 = ".this.globalMap.put(\"mapRuntime_";
  protected final String TEXT_68 = "\",mapRuntime_";
  protected final String TEXT_69 = ");" + NL + "\t\t}" + NL + "" + NL + "\t} // Synchronized" + NL + "\t" + NL + "\tjava.util.Map<String, Object> ecProps_";
  protected final String TEXT_70 = " = new java.util.HashMap<String, Object>();";
  protected final String TEXT_71 = " " + NL + "\t";
  protected final String TEXT_72 = ".synchronizeContext();" + NL + "        " + NL + "\tjava.util.Enumeration<?> propertyNames_";
  protected final String TEXT_73 = " = ";
  protected final String TEXT_74 = ".propertyNames();" + NL + "\twhile (propertyNames_";
  protected final String TEXT_75 = ".hasMoreElements()) {" + NL + "\t\tString key_";
  protected final String TEXT_76 = " = (String) propertyNames_";
  protected final String TEXT_77 = ".nextElement();" + NL + "\t\tObject value_";
  protected final String TEXT_78 = " = (Object) ";
  protected final String TEXT_79 = ".get(key_";
  protected final String TEXT_80 = ");       " + NL + "\t\tecProps_";
  protected final String TEXT_81 = ".put(\"context.\"+key_";
  protected final String TEXT_82 = ", value_";
  protected final String TEXT_83 = ");" + NL + "\t}" + NL + "        " + NL + "\torg.talend.transform.runtime.api.MapExecutionContext ec_";
  protected final String TEXT_84 = " = __tdmRuntime_";
  protected final String TEXT_85 = ".getMapExecutionContext();" + NL + "\tec_";
  protected final String TEXT_86 = ".setExecutionProperties(ecProps_";
  protected final String TEXT_87 = ");" + NL + "\t";
  protected final String TEXT_88 = NL + "\t\torg.talend.transform.runtime.api.JavaObjectSource source_";
  protected final String TEXT_89 = ";" + NL + "\t\tsource_";
  protected final String TEXT_90 = " = new org.talend.transform.runtime.api.JavaObjectSource(list_";
  protected final String TEXT_91 = ");" + NL + "\t\tif (false)" + NL + "\t\t\tSystem.out.println(\"Source: \" + ((org.talend.transform.runtime.api.JavaObjectSource)source_";
  protected final String TEXT_92 = ").getObject());" + NL + "\t\tec_";
  protected final String TEXT_93 = ".setSource(source_";
  protected final String TEXT_94 = ");";
  protected final String TEXT_95 = NL + "\t\tjavax.xml.transform.stream.StreamSource ss_";
  protected final String TEXT_96 = " = null;";
  protected final String TEXT_97 = NL + "\t\t\tss_";
  protected final String TEXT_98 = " = new javax.xml.transform.stream.StreamSource();" + NL + "\t\t\tif (";
  protected final String TEXT_99 = "!=null&&";
  protected final String TEXT_100 = ".";
  protected final String TEXT_101 = "!=null) {" + NL + "\t\t\t\tss_";
  protected final String TEXT_102 = ".setReader(new java.io.StringReader(";
  protected final String TEXT_103 = ".";
  protected final String TEXT_104 = "));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tss_";
  protected final String TEXT_105 = ".setReader(new java.io.StringReader(\"\"));" + NL + "\t\t\t}";
  protected final String TEXT_106 = NL + "\t\t\tif (";
  protected final String TEXT_107 = "!=null&&";
  protected final String TEXT_108 = ".";
  protected final String TEXT_109 = "!=null){" + NL + "\t\t\t\tec_";
  protected final String TEXT_110 = ".setSource(new org.dom4j.io.DocumentSource(((routines.system.Document)";
  protected final String TEXT_111 = ".";
  protected final String TEXT_112 = ").getDocument()));" + NL + "\t\t\t} else {" + NL + "\t\t\t\tec_";
  protected final String TEXT_113 = ".setSource(new org.dom4j.io.DocumentSource(new routines.system.Document().getDocument()));" + NL + "\t\t\t}";
  protected final String TEXT_114 = NL + "\t\t\tss_";
  protected final String TEXT_115 = " = new javax.xml.transform.stream.StreamSource();" + NL + "\t\t\tss_";
  protected final String TEXT_116 = ".setInputStream(new java.io.ByteArrayInputStream((byte[])";
  protected final String TEXT_117 = ".";
  protected final String TEXT_118 = "));";
  protected final String TEXT_119 = NL + " \t\t\tif (";
  protected final String TEXT_120 = ".";
  protected final String TEXT_121 = " instanceof java.io.InputStream) {" + NL + "            \tss_";
  protected final String TEXT_122 = " = new javax.xml.transform.stream.StreamSource();" + NL + "            \tss_";
  protected final String TEXT_123 = ".setInputStream((java.io.InputStream)";
  protected final String TEXT_124 = ".";
  protected final String TEXT_125 = ");" + NL + "        \t} else if (";
  protected final String TEXT_126 = ".";
  protected final String TEXT_127 = " instanceof String) {" + NL + "            \tss_";
  protected final String TEXT_128 = " = new javax.xml.transform.stream.StreamSource();" + NL + "            \tss_";
  protected final String TEXT_129 = ".setReader(new java.io.StringReader((String)";
  protected final String TEXT_130 = ".";
  protected final String TEXT_131 = "));" + NL + "        \t} else if (";
  protected final String TEXT_132 = ".";
  protected final String TEXT_133 = " instanceof byte[]) {" + NL + "            \tss_";
  protected final String TEXT_134 = " = new javax.xml.transform.stream.StreamSource();" + NL + "            \tss_";
  protected final String TEXT_135 = ".setInputStream(new java.io.ByteArrayInputStream((byte[])";
  protected final String TEXT_136 = ".";
  protected final String TEXT_137 = "));" + NL + "        \t} else if (";
  protected final String TEXT_138 = ".";
  protected final String TEXT_139 = " instanceof routines.system.Document) {" + NL + "            \tec_";
  protected final String TEXT_140 = ".setSource(new org.dom4j.io.DocumentSource(((routines.system.Document)";
  protected final String TEXT_141 = ".";
  protected final String TEXT_142 = ").getDocument()));" + NL + "        \t}";
  protected final String TEXT_143 = NL + "\t\t\tif (ss_";
  protected final String TEXT_144 = " != null)" + NL + "\t\t\t\tec_";
  protected final String TEXT_145 = ".setSource(ss_";
  protected final String TEXT_146 = ");";
  protected final String TEXT_147 = NL + "\t" + NL + "\t";
  protected final String TEXT_148 = "\t\t\t\t\t" + NL + "\t\tresult_";
  protected final String TEXT_149 = " = new org.talend.transform.runtime.api.JavaObjectResult();" + NL + "    \t";
  protected final String TEXT_150 = ".this.globalMap.put(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_151 = "_\"+\"outputResult\", result_";
  protected final String TEXT_152 = ");";
  protected final String TEXT_153 = "\t\t\t\t\t" + NL + "\t\tresult_";
  protected final String TEXT_154 = " = new javax.xml.transform.stream.StreamResult();" + NL + "        java.io.StringWriter sw_";
  protected final String TEXT_155 = " = new java.io.StringWriter();      " + NL + "\t\t((javax.xml.transform.stream.StreamResult)result_";
  protected final String TEXT_156 = ").setWriter(sw_";
  protected final String TEXT_157 = ");" + NL + "    \t";
  protected final String TEXT_158 = ".this.globalMap.put(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_159 = "_\"+\"outputResult\", sw_";
  protected final String TEXT_160 = ");";
  protected final String TEXT_161 = "\t\t\t\t\t" + NL + "\t\tresult_";
  protected final String TEXT_162 = " = new javax.xml.transform.stream.StreamResult();" + NL + "        java.io.ByteArrayOutputStream bas_";
  protected final String TEXT_163 = " = new java.io.ByteArrayOutputStream();       " + NL + "\t\t((javax.xml.transform.stream.StreamResult)result_";
  protected final String TEXT_164 = ").setOutputStream(bas_";
  protected final String TEXT_165 = ");" + NL + "    \t";
  protected final String TEXT_166 = ".this.globalMap.put(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_167 = "_\"+\"outputResult\", bas_";
  protected final String TEXT_168 = ");";
  protected final String TEXT_169 = "\t\t\t\t\t" + NL + "\t\t// Don't store in globalMap - that's done later with the actual InputStream" + NL + "\t\tresult_";
  protected final String TEXT_170 = " = new javax.xml.transform.stream.StreamResult();";
  protected final String TEXT_171 = "\t\t\t\t\t" + NL + "\t\tresult_";
  protected final String TEXT_172 = " = new org.dom4j.io.DocumentResult();" + NL + "    \t";
  protected final String TEXT_173 = ".this.globalMap.put(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_174 = "_\"+\"outputResult\", result_";
  protected final String TEXT_175 = ");";
  protected final String TEXT_176 = NL + "\tec_";
  protected final String TEXT_177 = ".setResult(result_";
  protected final String TEXT_178 = ");" + NL + "\torg.talend.transform.runtime.api.ExecutionStatus results_";
  protected final String TEXT_179 = " = ec_";
  protected final String TEXT_180 = ".getExecutionStatus();" + NL + "\t";
  protected final String TEXT_181 = ".this.globalMap.put(\"";
  protected final String TEXT_182 = "_\"+\"EXECUTION_STATUS\",results_";
  protected final String TEXT_183 = ");\t";
  protected final String TEXT_184 = NL + "\t\t// Runs the map when the InputStream is accessed\t\t\t\t\t" + NL + "    \t";
  protected final String TEXT_185 = ".this.globalMap.put(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_186 = "_\"+\"outputResult\", mapRuntime_";
  protected final String TEXT_187 = ".getResultAsInputStream(ec_";
  protected final String TEXT_188 = "));";
  protected final String TEXT_189 = "\t\t\t\t\t" + NL + "\t\tmapRuntime_";
  protected final String TEXT_190 = ".run(ec_";
  protected final String TEXT_191 = ");" + NL + "    \t";
  protected final String TEXT_192 = ".this.globalMap.put(\"";
  protected final String TEXT_193 = "_\"+\"EXECUTION_SEVERITY\",results_";
  protected final String TEXT_194 = ".getOverallSeverity());" + NL + "    \tif (results_";
  protected final String TEXT_195 = ".getOverallSeverity()>=";
  protected final String TEXT_196 = ") {" + NL + "\t    \tthrow new TalendException(new java.lang.Exception(String.valueOf(results_";
  protected final String TEXT_197 = ")),currentComponent,globalMap);" + NL + "\t\t}";
  protected final String TEXT_198 = NL + NL + "    if (results_";
  protected final String TEXT_199 = ".getOverallSeverity() > org.talend.transform.runtime.api.ExecutionStatus.STATUS_INFO) {" + NL + "        System.err.println(results_";
  protected final String TEXT_200 = ");" + NL + "    }\t\t\t";
  protected final String TEXT_201 = NL;
  protected final String TEXT_202 = "\t\t\t\t\t" + NL + "  org.talend.transform.runtime.api.JavaObjectResult javaResult_";
  protected final String TEXT_203 = " = (org.talend.transform.runtime.api.JavaObjectResult)";
  protected final String TEXT_204 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_205 = "_\"+\"outputResult\");" + NL + "  if (javaResult_";
  protected final String TEXT_206 = " != null && javaResult_";
  protected final String TEXT_207 = ".getObject() != null && javaResult_";
  protected final String TEXT_208 = ".getObject() instanceof java.util.List && ((java.util.List)javaResult_";
  protected final String TEXT_209 = ".getObject()).size() > 0) {" + NL + "    java.util.List<java.util.Map<String, Object>> outList_";
  protected final String TEXT_210 = " = (java.util.List)javaResult_";
  protected final String TEXT_211 = ".getObject();" + NL + "    java.util.Map<String, Object> outMap = outList_";
  protected final String TEXT_212 = ".get(0);";
  protected final String TEXT_213 = "\t\t\t\t\t" + NL;
  protected final String TEXT_214 = NL + "    java.io.StringWriter swOut_";
  protected final String TEXT_215 = " = (java.io.StringWriter)";
  protected final String TEXT_216 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_217 = "_\"+\"outputResult\");" + NL + "    if (swOut_";
  protected final String TEXT_218 = " != null)" + NL + " \t\t";
  protected final String TEXT_219 = ".";
  protected final String TEXT_220 = " = swOut_";
  protected final String TEXT_221 = ".toString();";
  protected final String TEXT_222 = NL + "\tjava.io.ByteArrayOutputStream basOut_";
  protected final String TEXT_223 = " = (java.io.ByteArrayOutputStream)";
  protected final String TEXT_224 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_225 = "_\"+\"outputResult\");" + NL + "    if (basOut_";
  protected final String TEXT_226 = " != null)" + NL + " \t    ";
  protected final String TEXT_227 = ".";
  protected final String TEXT_228 = " = (byte[])basOut_";
  protected final String TEXT_229 = ".toByteArray();";
  protected final String TEXT_230 = NL + "\t";
  protected final String TEXT_231 = ".";
  protected final String TEXT_232 = " = (java.io.InputStream)";
  protected final String TEXT_233 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_234 = "_\"+\"outputResult\");";
  protected final String TEXT_235 = NL + "  \t";
  protected final String TEXT_236 = ".";
  protected final String TEXT_237 = " = new routines.system.Document();" + NL + "\torg.dom4j.io.DocumentResult drOut_";
  protected final String TEXT_238 = " = (org.dom4j.io.DocumentResult)";
  protected final String TEXT_239 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_240 = "_\"+\"outputResult\");" + NL + "    if (drOut_";
  protected final String TEXT_241 = " != null)" + NL + " \t    ((routines.system.Document)";
  protected final String TEXT_242 = ".";
  protected final String TEXT_243 = ").setDocument(drOut_";
  protected final String TEXT_244 = ".getDocument()); \t";
  protected final String TEXT_245 = NL + "  if(outMap.get(\"";
  protected final String TEXT_246 = "\")==null){";
  protected final String TEXT_247 = NL + "    ";
  protected final String TEXT_248 = ".";
  protected final String TEXT_249 = " = null;" + NL + "  }else if (outMap.get(\"";
  protected final String TEXT_250 = "\") instanceof String) { ";
  protected final String TEXT_251 = NL + "    ";
  protected final String TEXT_252 = ".";
  protected final String TEXT_253 = " = (String)outMap.get(\"";
  protected final String TEXT_254 = "\");" + NL + "  }";
  protected final String TEXT_255 = NL + "  if(outMap.get(\"";
  protected final String TEXT_256 = "\")==null){";
  protected final String TEXT_257 = NL + "    ";
  protected final String TEXT_258 = ".";
  protected final String TEXT_259 = " = false;" + NL + "  }else if (outMap.get(\"";
  protected final String TEXT_260 = "\") instanceof Boolean) { ";
  protected final String TEXT_261 = NL + "    ";
  protected final String TEXT_262 = ".";
  protected final String TEXT_263 = " = ((Boolean)outMap.get(\"";
  protected final String TEXT_264 = "\")).booleanValue();" + NL + "  }";
  protected final String TEXT_265 = NL + "  if (outMap.get(\"";
  protected final String TEXT_266 = "\")==null){";
  protected final String TEXT_267 = NL + "   ";
  protected final String TEXT_268 = ".";
  protected final String TEXT_269 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_270 = "\") instanceof Byte) { ";
  protected final String TEXT_271 = NL + "   ";
  protected final String TEXT_272 = ".";
  protected final String TEXT_273 = " = ((Byte)outMap.get(\"";
  protected final String TEXT_274 = "\")).byteValue();" + NL + "  }";
  protected final String TEXT_275 = NL + "  if (outMap.get(\"";
  protected final String TEXT_276 = "\")==null){";
  protected final String TEXT_277 = NL + "   ";
  protected final String TEXT_278 = ".";
  protected final String TEXT_279 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_280 = "\") instanceof Character) { ";
  protected final String TEXT_281 = NL + "   ";
  protected final String TEXT_282 = ".";
  protected final String TEXT_283 = " = ((Character)outMap.get(\"";
  protected final String TEXT_284 = "\")).charValue();" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_285 = "\") instanceof Short) { ";
  protected final String TEXT_286 = NL + "   ";
  protected final String TEXT_287 = ".";
  protected final String TEXT_288 = " = (char)((Short)outMap.get(\"";
  protected final String TEXT_289 = "\")).shortValue();" + NL + "  }";
  protected final String TEXT_290 = NL + "  if (outMap.get(\"";
  protected final String TEXT_291 = "\")==null){";
  protected final String TEXT_292 = NL + "   ";
  protected final String TEXT_293 = ".";
  protected final String TEXT_294 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_295 = "\") instanceof Short) { ";
  protected final String TEXT_296 = NL + "   ";
  protected final String TEXT_297 = ".";
  protected final String TEXT_298 = " = ((Short)outMap.get(\"";
  protected final String TEXT_299 = "\")).shortValue();" + NL + "  }";
  protected final String TEXT_300 = NL + "  if (outMap.get(\"";
  protected final String TEXT_301 = "\")==null){";
  protected final String TEXT_302 = NL + "   ";
  protected final String TEXT_303 = ".";
  protected final String TEXT_304 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_305 = "\") instanceof Integer) { ";
  protected final String TEXT_306 = NL + "   ";
  protected final String TEXT_307 = ".";
  protected final String TEXT_308 = " = ((Integer)outMap.get(\"";
  protected final String TEXT_309 = "\")).intValue();" + NL + "  }";
  protected final String TEXT_310 = NL + "  if (outMap.get(\"";
  protected final String TEXT_311 = "\")==null){";
  protected final String TEXT_312 = NL + "   ";
  protected final String TEXT_313 = ".";
  protected final String TEXT_314 = " = (long)0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_315 = "\") instanceof Long) { ";
  protected final String TEXT_316 = NL + "   ";
  protected final String TEXT_317 = ".";
  protected final String TEXT_318 = " = ((Long)outMap.get(\"";
  protected final String TEXT_319 = "\")).longValue();" + NL + "  }";
  protected final String TEXT_320 = NL + "  if (outMap.get(\"";
  protected final String TEXT_321 = "\")==null){";
  protected final String TEXT_322 = NL + "   ";
  protected final String TEXT_323 = ".";
  protected final String TEXT_324 = " = 0.0f;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_325 = "\") instanceof Float) { ";
  protected final String TEXT_326 = NL + "   ";
  protected final String TEXT_327 = ".";
  protected final String TEXT_328 = " = ((Float)outMap.get(\"";
  protected final String TEXT_329 = "\")).floatValue();" + NL + "  }";
  protected final String TEXT_330 = NL + "  if (outMap.get(\"";
  protected final String TEXT_331 = "\")==null){";
  protected final String TEXT_332 = NL + "   ";
  protected final String TEXT_333 = ".";
  protected final String TEXT_334 = " = 0.0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_335 = "\") instanceof Double) { ";
  protected final String TEXT_336 = NL + "   ";
  protected final String TEXT_337 = ".";
  protected final String TEXT_338 = " = ((Double)outMap.get(\"";
  protected final String TEXT_339 = "\")).doubleValue();" + NL + "  }";
  protected final String TEXT_340 = NL + "  if (outMap.get(\"";
  protected final String TEXT_341 = "\")==null){";
  protected final String TEXT_342 = NL + "    ";
  protected final String TEXT_343 = ".";
  protected final String TEXT_344 = " = null;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_345 = "\") instanceof java.math.BigDecimal) { ";
  protected final String TEXT_346 = NL + "    ";
  protected final String TEXT_347 = ".";
  protected final String TEXT_348 = " = (java.math.BigDecimal)outMap.get(\"";
  protected final String TEXT_349 = "\");" + NL + "  }";
  protected final String TEXT_350 = NL + "  if (outMap.get(\"";
  protected final String TEXT_351 = "\")==null){";
  protected final String TEXT_352 = NL + "    ";
  protected final String TEXT_353 = ".";
  protected final String TEXT_354 = " = null;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_355 = "\") instanceof java.util.Date) { ";
  protected final String TEXT_356 = NL + "    ";
  protected final String TEXT_357 = ".";
  protected final String TEXT_358 = " = (java.util.Date)outMap.get(\"";
  protected final String TEXT_359 = "\");" + NL + "  }";
  protected final String TEXT_360 = "\t" + NL;
  protected final String TEXT_361 = "\t\t\t\t\t" + NL + "  } // end if";
  protected final String TEXT_362 = "\t\t\t\t\t";
  protected final String TEXT_363 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String processName = org.talend.core.model.utils.JavaResourcesHelper.getSimpleClassName(node.getProcess());
	String this_cid = ElementParameterParser.getValue(node, "__UNIQUE_NAME__");
	String tHMap_id = this_cid;
	String cid = tHMap_id;
	INode tHMapNode = node;	

	boolean sourceAsMap = "true".equals(ElementParameterParser.getValue(node, "__SOURCE_AS_MAP__"));
	if (sourceAsMap) {

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    
		// Populate a TDM HashMap using a DI rowStruct
		if (node.getIncomingConnections()!=null) {
			for (IConnection incomingConn : node.getIncomingConnections()) {
				if (incomingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
					IMetadataTable inputMetadataTable = incomingConn.getMetadataTable();
					for (IMetadataColumn inputCol : inputMetadataTable.getListColumns()) {

    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_10);
    
					} // for (IMetadataColumn

    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
				break;
				} // if (incomingConn
			} // for (IConnection
		} // if (node

    
	} // if (sourceAsMap)

    stringBuffer.append(TEXT_14);
    
	// Fills the dependency property of the component
	Set<String> projects = ((MapperComponent)tHMapNode.getExternalNode()).populateDependencies(0);
	Set<String> projectArchiveSet = ((MapperComponent)tHMapNode.getExternalNode()).populateDependencies(MapperComponent.DEP_FOR_PROJECT_ARCHIVE);

    String mapPath = ElementParameterParser.getValue(node, "__HMAP_PATH__");
	mapPath = ((MapperComponent)tHMapNode.getExternalNode()).makeAbsoluteMapPath(mapPath);
	mapPath = MapperComponent.fixMapPath(mapPath);
	
	String mapVarPath = ElementParameterParser.getValue(node, "__HMAP_VAR_PATH__");
	mapVarPath = TalendTextUtils.removeQuotes(mapVarPath);
	mapVarPath = ((MapperComponent)tHMapNode.getExternalNode()).toPortableString(mapVarPath);
	
	String log = ElementParameterParser.getValue(node, "__LOG__");

	StringBuffer pa = new StringBuffer();
	for (String project : projectArchiveSet) {
	    if (pa.toString().length() > 0) {
	       pa.append(",");
	    }
		pa.append("file://__tdm/" + project.replace(" ", "%20") + ".zip");
	}
	String projectArchives = pa.toString();
	projectArchives = TalendTextUtils.addQuotes(projectArchives);    

	boolean asMap = "true".equals(ElementParameterParser.getValue(node, "__AS_MAP__"));
	boolean asString = "true".equals(ElementParameterParser.getValue(node, "__AS_STRING__"));
	boolean asBytearray = "true".equals(ElementParameterParser.getValue(node, "__AS_BYTEARRAY__"));
	boolean asInputstream = "true".equals(ElementParameterParser.getValue(node, "__AS_INPUTSTREAM__"));
	boolean asDocument = "true".equals(ElementParameterParser.getValue(node, "__AS_DOCUMENT__"));
	
    String devWorkspace = ElementParameterParser.getValue(node.getProcess(), "__COMP_DEFAULT_FILE_DIR__");
	devWorkspace = TalendTextUtils.addQuotes(devWorkspace);    
    String devInstall = ElementParameterParser.getValue(node.getProcess(), "__PRODUCT_ROOT_DIR__");
	devInstall = TalendTextUtils.addQuotes(devInstall);    
    
    Integer exceptionLevel = Integer.parseInt(ElementParameterParser.getValue(node, "__EXCEPTION__")); 

    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    	
	if (mapVarPath != null && !"".equals(mapVarPath.trim())) {
		if (mapVarPath.startsWith("context.")) {

    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(mapVarPath);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(devWorkspace);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(mapPath);
    stringBuffer.append(TEXT_23);
    
    } else {

    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(mapVarPath);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(devWorkspace);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(mapPath);
    stringBuffer.append(TEXT_28);
    
    }} else {

    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(devWorkspace);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(mapPath);
    stringBuffer.append(TEXT_32);
    
    }

    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    
			if (!org.talend.designer.runprocess.ProcessorUtilities.isExportConfig()) {

    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(devInstall);
    stringBuffer.append(TEXT_37);
    
        	} else {

    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    
        	}

    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(log);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
		if (!org.talend.designer.runprocess.ProcessorUtilities.isExportConfig()) {
			for (String project : projects) {
				String projectResolved = ((MapperComponent)tHMapNode.getExternalNode()).resolveLinkedProject(project);

    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(projectResolved);
    stringBuffer.append(TEXT_55);
    
			} 
		} else {

    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(projectArchives);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(projectArchives);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
		} // isExportConfig

    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
     
    String localContext = "context";

    stringBuffer.append(TEXT_71);
    stringBuffer.append(localContext);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(localContext);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(localContext);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    
	if (sourceAsMap) {

    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    
	} else {

    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    
		if (tHMapNode.getIncomingConnections()!=null) {
			for (IConnection incomingConn : tHMapNode.getIncomingConnections()) {
			if (incomingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {							
					IMetadataTable inputMetadataTable = incomingConn.getMetadataTable();
					for (IMetadataColumn inputCol : inputMetadataTable.getListColumns()) {
						if (JavaTypesManager.STRING.getId().equals(inputCol.getTalendType())) {

    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_100);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    
						} else if ("id_Document".equals(inputCol.getTalendType())) {

    stringBuffer.append(TEXT_106);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_108);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    
                        } else if ("id_byte[]".equals(inputCol.getTalendType())) {

    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_117);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_118);
    
						} else {

    stringBuffer.append(TEXT_119);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_124);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_126);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_130);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_131);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_132);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_136);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_137);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_138);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(incomingConn.getName() );
    stringBuffer.append(TEXT_141);
    stringBuffer.append(inputCol.getLabel() );
    stringBuffer.append(TEXT_142);
    
		              } // if
		       		break;
					} // for

    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    
				} // if 
			} // for			

					} // if
	} // if (sourceAsMap)

    stringBuffer.append(TEXT_147);
    
	if(asMap) {

    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_152);
    
	} else if (asString) {

    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_157);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_160);
    
	} else if (asBytearray) {

    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    
	} else if (asInputstream) {

    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_170);
    
	} else if (asDocument) {

    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    
	}

    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_181);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_183);
    
	if (asInputstream) {

    stringBuffer.append(TEXT_184);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_188);
    
	} else {

    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_191);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(exceptionLevel);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_197);
    
	}
		
if(!org.talend.designer.runprocess.ProcessorUtilities.isExportConfig()){

    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    
}

    stringBuffer.append(TEXT_201);
    
	if (asMap) {

    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append( processName );
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    
	} // if (asMap)

    stringBuffer.append(TEXT_213);
    
	if (node.getOutgoingConnections()!=null) {
		java.util.Set<IConnection> dataConns = new java.util.HashSet<IConnection>();	
		for (IConnection outgoingConn : node.getOutgoingConnections()) {
		if (outgoingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
		dataConns.add(outgoingConn);	
		}
		}
		for (IConnection outgoingConn : node.getOutgoingConnections()) {
			if (outgoingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
				String outputConnName = outgoingConn.getName();
				IMetadataTable outputMetadataTable = outgoingConn.getMetadataTable();
				for (IMetadataColumn outputCol : outputMetadataTable.getListColumns()) {
					if (asString) {

    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_218);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_219);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_221);
    
						break;
					} else if (asBytearray) {

    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_227);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
    
						break;
					} else if (asInputstream) {

    stringBuffer.append(TEXT_230);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_231);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_232);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_234);
    
						break;
					} else if (asDocument) {

    stringBuffer.append(TEXT_235);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_236);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_242);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    
						break;
					} else if (asMap) {

    
						// Populate a DI rowStruct using a TDM HashMap
						if (JavaTypesManager.STRING.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_245);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_246);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_248);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_249);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_250);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_252);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_253);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_254);
    
						} else if (JavaTypesManager.BOOLEAN.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_255);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_256);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_258);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_259);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_260);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_263);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_264);
    
						} else if (JavaTypesManager.BYTE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_265);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_266);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_268);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_269);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_270);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_272);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_273);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_274);
    
						} else if (JavaTypesManager.CHARACTER.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_275);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_276);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_278);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_279);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_280);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_282);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_283);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_284);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_285);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_287);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_288);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_289);
    
						} else if (JavaTypesManager.SHORT.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_290);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_291);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_293);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_294);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_295);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_297);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_298);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_299);
    
						} else if (JavaTypesManager.INTEGER.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_300);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_301);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_303);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_304);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_305);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_307);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_308);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_309);
    
						} else if (JavaTypesManager.LONG.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_310);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_311);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_313);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_314);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_315);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_317);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_318);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_319);
    
						} else if (JavaTypesManager.FLOAT.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_320);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_321);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_323);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_324);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_325);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_327);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_328);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_329);
    
						} else if (JavaTypesManager.DOUBLE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_330);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_331);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_333);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_334);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_335);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_337);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_338);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_339);
    
						} else if (JavaTypesManager.BIGDECIMAL.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_340);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_341);
    stringBuffer.append(TEXT_342);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_343);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_344);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_345);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_347);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_348);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_349);
    
						} else if (JavaTypesManager.DATE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_350);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_351);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_353);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_354);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_355);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_357);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_358);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_359);
    
						}

    
					}
				}
				break;
			}
		}		
	}

    stringBuffer.append(TEXT_360);
    
	if (asMap) {

    stringBuffer.append(TEXT_361);
    
	} // if (asMap)

    stringBuffer.append(TEXT_362);
    stringBuffer.append(TEXT_363);
    return stringBuffer.toString();
  }
}
