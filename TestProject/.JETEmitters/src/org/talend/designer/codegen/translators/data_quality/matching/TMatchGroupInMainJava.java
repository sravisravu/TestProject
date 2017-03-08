package org.talend.designer.codegen.translators.data_quality.matching;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.BlockCode;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import org.eclipse.emf.common.util.EMap;
import org.talend.designer.tdqmatching.MatchingData;
import org.talend.designer.tdqmatching.RuleMatcher;
import org.talend.designer.tdqmatching.JoinkeyRecord;

public class TMatchGroupInMainJava
{
  protected static String nl;
  public static synchronized TMatchGroupInMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMatchGroupInMainJava result = new TMatchGroupInMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "java.util.List<java.util.List<java.util.Map<String, String>>> matchingRulesAll_";
  protected final String TEXT_2 = " = new java.util.ArrayList<java.util.List<java.util.Map<String, String>>>();" + NL + "java.util.List<java.util.Map<String, String>> matcherList_";
  protected final String TEXT_3 = " = null;" + NL + "java.util.Map<String,String> tmpMap_";
  protected final String TEXT_4 = " =null;" + NL + "java.util.Map<String,String> paramMapTmp_";
  protected final String TEXT_5 = " =null;" + NL + "java.util.List<java.util.Map<String, String>> defaultSurvivorshipRules_";
  protected final String TEXT_6 = " = new java.util.ArrayList<java.util.Map<String, String>>();";
  protected final String TEXT_7 = NL + "        matcherList_";
  protected final String TEXT_8 = " = new java.util.ArrayList<java.util.Map<String, String>>();";
  protected final String TEXT_9 = NL + "            tmpMap_";
  protected final String TEXT_10 = "=new java.util.HashMap<String,String>();";
  protected final String TEXT_11 = NL + "                    tmpMap_";
  protected final String TEXT_12 = ".put(\"ATTRIBUTE_NAME\",\"";
  protected final String TEXT_13 = "\");";
  protected final String TEXT_14 = NL + "                    tmpMap_";
  protected final String TEXT_15 = ".put(\"";
  protected final String TEXT_16 = "\",";
  protected final String TEXT_17 = "+\"\");";
  protected final String TEXT_18 = NL + "                tmpMap_";
  protected final String TEXT_19 = ".put(\"";
  protected final String TEXT_20 = "\",\"";
  protected final String TEXT_21 = "\");";
  protected final String TEXT_22 = NL + "                    tmpMap_";
  protected final String TEXT_23 = ".put(\"";
  protected final String TEXT_24 = "\", ";
  protected final String TEXT_25 = "+\"\");";
  protected final String TEXT_26 = NL + "                    tmpMap_";
  protected final String TEXT_27 = ".put(\"";
  protected final String TEXT_28 = "\", \"";
  protected final String TEXT_29 = "\");";
  protected final String TEXT_30 = NL + "                                tmpMap_";
  protected final String TEXT_31 = ".put(\"";
  protected final String TEXT_32 = "\", String.valueOf(";
  protected final String TEXT_33 = "));";
  protected final String TEXT_34 = NL + "                matcherList_";
  protected final String TEXT_35 = ".add(tmpMap_";
  protected final String TEXT_36 = ");";
  protected final String TEXT_37 = NL + "        matcherList_";
  protected final String TEXT_38 = ".sort(new Comparator<java.util.Map<String, String>>() {" + NL + "" + NL + "            @Override" + NL + "            public int compare(java.util.Map<String, String> map1, java.util.Map<String, String> map2) {" + NL + "" + NL + "                return map1.get(\"COLUMN_IDX\").compareTo(map2.get(\"COLUMN_IDX\"));" + NL + "            }" + NL + "" + NL + "        });";
  protected final String TEXT_39 = NL + "        matchingRulesAll_";
  protected final String TEXT_40 = ".add(matcherList_";
  protected final String TEXT_41 = ");";
  protected final String TEXT_42 = NL + "    java.util.Map<String,String> realSurShipMap_";
  protected final String TEXT_43 = "=null;";
  protected final String TEXT_44 = NL + "        realSurShipMap_";
  protected final String TEXT_45 = "=new java.util.HashMap<String,String>();";
  protected final String TEXT_46 = NL + "                realSurShipMap_";
  protected final String TEXT_47 = ".put(\"";
  protected final String TEXT_48 = "\",\"";
  protected final String TEXT_49 = "\");";
  protected final String TEXT_50 = NL + "        defaultSurvivorshipRules_";
  protected final String TEXT_51 = ".add(realSurShipMap_";
  protected final String TEXT_52 = ");";
  protected final String TEXT_53 = NL + "if(matchingRulesAll_";
  protected final String TEXT_54 = ".size()==0){" + NL + "   //If no matching rules in external data, try to get to rule from JOIN_KEY table (which will be compatible to old version less than 5.3)   ";
  protected final String TEXT_55 = NL + "      matcherList_";
  protected final String TEXT_56 = " = new java.util.ArrayList<java.util.Map<String, String>>();" + NL + "      ";
  protected final String TEXT_57 = NL + "          tmpMap_";
  protected final String TEXT_58 = "=new java.util.HashMap<String,String>();";
  protected final String TEXT_59 = NL + "                  tmpMap_";
  protected final String TEXT_60 = ".put(\"ATTRIBUTE_NAME\",\"";
  protected final String TEXT_61 = "\");";
  protected final String TEXT_62 = NL + "                  tmpMap_";
  protected final String TEXT_63 = ".put(\"";
  protected final String TEXT_64 = "\",";
  protected final String TEXT_65 = "+\"\");";
  protected final String TEXT_66 = NL + "              tmpMap_";
  protected final String TEXT_67 = ".put(\"";
  protected final String TEXT_68 = "\",\"";
  protected final String TEXT_69 = "\");";
  protected final String TEXT_70 = NL + "          matcherList_";
  protected final String TEXT_71 = ".add(tmpMap_";
  protected final String TEXT_72 = ");";
  protected final String TEXT_73 = NL + "//      paramMapTmp_";
  protected final String TEXT_74 = " = new java.util.HashMap<String,String>();" + NL + "//      paramMapTmp_";
  protected final String TEXT_75 = ".put(\"INTERVAL_RULE\", \"";
  protected final String TEXT_76 = "\");" + NL + "//      paramMapTmp_";
  protected final String TEXT_77 = ".put(\"MATCHING_ALGORITHM\", \"Simple VSR Matcher\");" + NL + "//      matcherList_";
  protected final String TEXT_78 = ".add( paramMapTmp_";
  protected final String TEXT_79 = ");" + NL + "      matchingRulesAll_";
  protected final String TEXT_80 = ".add(matcherList_";
  protected final String TEXT_81 = ");";
  protected final String TEXT_82 = " " + NL + "}" + NL;
  protected final String TEXT_83 = NL + "  tHash_Lookup_";
  protected final String TEXT_84 = ".initGet();";
  protected final String TEXT_85 = NL;
  protected final String TEXT_86 = NL + "    ";
  protected final String TEXT_87 = "Struct ";
  protected final String TEXT_88 = " = null; // main output is used even in \"separate output\" mode";
  protected final String TEXT_89 = NL;
  protected final String TEXT_90 = "Struct masterRow_";
  protected final String TEXT_91 = " = null; // a master-row in a group";
  protected final String TEXT_92 = NL;
  protected final String TEXT_93 = "Struct subRow_";
  protected final String TEXT_94 = " = null; // a sub-row in a group." + NL + "// key row";
  protected final String TEXT_95 = NL;
  protected final String TEXT_96 = "Struct hashKey_";
  protected final String TEXT_97 = " = new ";
  protected final String TEXT_98 = "Struct();  " + NL + "// rows respond to key row ";
  protected final String TEXT_99 = NL;
  protected final String TEXT_100 = "Struct hashValue_";
  protected final String TEXT_101 = " = new ";
  protected final String TEXT_102 = "Struct();" + NL + "java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap_";
  protected final String TEXT_103 = " = (java.util.concurrent.ConcurrentHashMap) globalMap.get(\"concurrentHashMap\");" + NL + "concurrentHashMap_";
  protected final String TEXT_104 = ".putIfAbsent(\"gid_";
  protected final String TEXT_105 = "\", new java.util.concurrent.atomic.AtomicLong(0L));" + NL + "java.util.concurrent.atomic.AtomicLong gid_";
  protected final String TEXT_106 = " = (java.util.concurrent.atomic.AtomicLong) concurrentHashMap_";
  protected final String TEXT_107 = ".get(\"gid_";
  protected final String TEXT_108 = "\");" + NL + "// master rows in a group" + NL + "final java.util.List<";
  protected final String TEXT_109 = "Struct> masterRows_";
  protected final String TEXT_110 = " = new java.util.ArrayList<";
  protected final String TEXT_111 = "Struct>();" + NL + "// all rows in a group " + NL + "final java.util.List<";
  protected final String TEXT_112 = "Struct> groupRows_";
  protected final String TEXT_113 = " = new java.util.ArrayList<";
  protected final String TEXT_114 = "Struct>();" + NL + "// this Map key is MASTER GID,value is this MASTER index of all MASTERS.it will be used to get DUPLICATE GRP_QUALITY from MASTER and only in case of separate output." + NL + "final java.util.Map<String,Double> gID2GQMap_";
  protected final String TEXT_115 = " = new java.util.HashMap<String,Double>();" + NL + "final double CONFIDENCE_THRESHOLD_";
  protected final String TEXT_116 = " = Double.valueOf(";
  protected final String TEXT_117 = ");" + NL + "" + NL + "" + NL + "" + NL + "//Long gid_";
  protected final String TEXT_118 = " = 0L;" + NL + "boolean forceLoop_";
  protected final String TEXT_119 = " = (blockRows_";
  protected final String TEXT_120 = ".isEmpty());";
  protected final String TEXT_121 = "    " + NL + "        java.util.Iterator<Object[]> iter_";
  protected final String TEXT_122 = " = blockRows_";
  protected final String TEXT_123 = ".iterator();";
  protected final String TEXT_124 = NL + "        java.util.Iterator<";
  protected final String TEXT_125 = "_2Struct> iter_";
  protected final String TEXT_126 = " = blockRows_";
  protected final String TEXT_127 = ".keySet().iterator();";
  protected final String TEXT_128 = NL + NL + "//TDQ-9172 reuse JAVA API at here.";
  protected final String TEXT_129 = NL + "org.talend.dataquality.record.linkage.grouping.AbstractRecordGrouping<Object> recordGroupImp_";
  protected final String TEXT_130 = ";";
  protected final String TEXT_131 = NL + " recordGroupImp_";
  protected final String TEXT_132 = "=new org.talend.dataquality.record.linkage.grouping.AbstractRecordGrouping<Object>(){" + NL + "    @Override" + NL + "    protected void outputRow(Object[] row) {";
  protected final String TEXT_133 = NL + "        ";
  protected final String TEXT_134 = "Struct outStuct_";
  protected final String TEXT_135 = " = new ";
  protected final String TEXT_136 = "Struct ();" + NL + "        boolean isMaster=false; ";
  protected final String TEXT_137 = NL + "           " + NL + "              if(";
  protected final String TEXT_138 = " <row.length){" + NL + "                  outStuct_";
  protected final String TEXT_139 = ".";
  protected final String TEXT_140 = "=row[";
  protected final String TEXT_141 = "]==null?null:(";
  protected final String TEXT_142 = ")row[";
  protected final String TEXT_143 = "];" + NL + "              }" + NL + "              ";
  protected final String TEXT_144 = NL + "          if(outStuct_";
  protected final String TEXT_145 = ".MASTER==true){" + NL + "             masterRows_";
  protected final String TEXT_146 = ".add(outStuct_";
  protected final String TEXT_147 = ");";
  protected final String TEXT_148 = NL + "                 gID2GQMap_";
  protected final String TEXT_149 = ".put(String.valueOf(outStuct_";
  protected final String TEXT_150 = ".GID), outStuct_";
  protected final String TEXT_151 = ".GRP_QUALITY);";
  protected final String TEXT_152 = NL + "         }else{" + NL + "             groupRows_";
  protected final String TEXT_153 = ".add(outStuct_";
  protected final String TEXT_154 = ");" + NL + "         }" + NL + "    }" + NL + "    @Override" + NL + "    protected boolean isMaster(Object col) {" + NL + "        return String.valueOf(col).equals(\"true\");" + NL + "    }" + NL + "    @Override" + NL + "    protected Object incrementGroupSize(Object oldGroupSize) {" + NL + "        return Integer.parseInt(String.valueOf(oldGroupSize)) + 1;" + NL + "    }" + NL + "    @Override" + NL + "    protected Object[] createTYPEArray(int size) {" + NL + "        return  new Object[size];" + NL + "    }" + NL + "    @Override" + NL + "    protected Object castAsType(Object objectValue) {" + NL + "        return objectValue;" + NL + "    }" + NL + "   @Override" + NL + "   protected void outputRow(org.talend.dataquality.record.linkage.grouping.swoosh.RichRecord row) {" + NL + "       // No implementation temporarily." + NL + "" + NL + "   }      " + NL + "};" + NL + "recordGroupImp_";
  protected final String TEXT_155 = ".setOrginalInputColumnSize(";
  protected final String TEXT_156 = ");" + NL;
  protected final String TEXT_157 = NL + "    recordGroupImp_";
  protected final String TEXT_158 = "=new org.talend.dataquality.record.linkage.grouping.swoosh.ComponentSwooshMatchRecordGrouping(){" + NL + "    @Override" + NL + "    protected void outputRow(Object[] row) {";
  protected final String TEXT_159 = NL + "        ";
  protected final String TEXT_160 = "Struct outStuct_";
  protected final String TEXT_161 = " = new ";
  protected final String TEXT_162 = "Struct ();" + NL + "        boolean isMaster=false; ";
  protected final String TEXT_163 = NL + "           " + NL + "              if(";
  protected final String TEXT_164 = " <row.length){";
  protected final String TEXT_165 = NL + "                      java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(\"yyyy-mm-dd\");" + NL + "                      java.util.Date date =null;" + NL + "                      try {" + NL + "                          if(row[";
  protected final String TEXT_166 = "]==null){" + NL + "                              date =null;" + NL + "                          }else{" + NL + "                              date =sdf.parse((String)row[";
  protected final String TEXT_167 = "]);" + NL + "                          }" + NL + "                      } catch (ParseException e) {" + NL + "                          date =null;" + NL + "                      }" + NL + "                      outStuct_";
  protected final String TEXT_168 = ".";
  protected final String TEXT_169 = "=date;";
  protected final String TEXT_170 = NL + "                  " + NL + "                  try{" + NL + "                      outStuct_";
  protected final String TEXT_171 = ".";
  protected final String TEXT_172 = "=";
  protected final String TEXT_173 = ".valueOf((String)row[";
  protected final String TEXT_174 = "]);" + NL + "                      }catch(java.lang.NumberFormatException e){" + NL + "                          outStuct_";
  protected final String TEXT_175 = ".";
  protected final String TEXT_176 = "=";
  protected final String TEXT_177 = NL + "                          row[";
  protected final String TEXT_178 = "]==null?null:0;";
  protected final String TEXT_179 = NL + "                              0.0;";
  protected final String TEXT_180 = NL + "                              0;";
  protected final String TEXT_181 = NL + "                              0l;";
  protected final String TEXT_182 = NL + "                          row[";
  protected final String TEXT_183 = "]==null?null:0;";
  protected final String TEXT_184 = NL + "                      }";
  protected final String TEXT_185 = NL + "                      outStuct_";
  protected final String TEXT_186 = ".";
  protected final String TEXT_187 = "=row[";
  protected final String TEXT_188 = "]==null?null:";
  protected final String TEXT_189 = ".valueOf((String)row[";
  protected final String TEXT_190 = "]);";
  protected final String TEXT_191 = NL + "              }" + NL + "              ";
  protected final String TEXT_192 = NL + "          if(outStuct_";
  protected final String TEXT_193 = ".MASTER==true){" + NL + "             masterRows_";
  protected final String TEXT_194 = ".add(outStuct_";
  protected final String TEXT_195 = ");" + NL + "             gID2GQMap_";
  protected final String TEXT_196 = ".put(String.valueOf(outStuct_";
  protected final String TEXT_197 = ".GID), outStuct_";
  protected final String TEXT_198 = ".GRP_QUALITY);" + NL + "         }else{" + NL + "             groupRows_";
  protected final String TEXT_199 = ".add(outStuct_";
  protected final String TEXT_200 = ");" + NL + "         }" + NL + "    }" + NL + "    @Override" + NL + "    protected boolean isMaster(Object col) {" + NL + "        return String.valueOf(col).equals(\"true\");" + NL + "    }" + NL + "};";
  protected final String TEXT_201 = NL + "recordGroupImp_";
  protected final String TEXT_202 = ".setOrginalInputColumnSize(";
  protected final String TEXT_203 = ");";
  protected final String TEXT_204 = NL + "recordGroupImp_";
  protected final String TEXT_205 = ".setOrginalInputColumnSize(";
  protected final String TEXT_206 = ");";
  protected final String TEXT_207 = NL + "recordGroupImp_";
  protected final String TEXT_208 = ".setRecordLinkAlgorithm(org.talend.dataquality.record.linkage.constant.RecordMatcherType.T_SwooshAlgorithm);" + NL + "//add mutch rules" + NL + "for(java.util.List<java.util.Map<String, String>> matcherList:matchingRulesAll_";
  protected final String TEXT_209 = "){" + NL + "   recordGroupImp_";
  protected final String TEXT_210 = ".addMatchRule(matcherList);" + NL + "}" + NL + "recordGroupImp_";
  protected final String TEXT_211 = ".initialize();" + NL + "//init the parameters of the tswoosh algorithm" + NL + "java.util.Map<String, String> columnWithType_";
  protected final String TEXT_212 = " = new java.util.HashMap<String, String>();" + NL + "java.util.Map<String, String> columnWithIndex_";
  protected final String TEXT_213 = " = new java.util.HashMap<String, String>();" + NL + "java.util.List<java.util.List<java.util.Map<String, String>>> allRules_";
  protected final String TEXT_214 = " = new java.util.ArrayList<java.util.List<java.util.Map<String, String>>>();" + NL + NL;
  protected final String TEXT_215 = NL + "    java.util.Map<String,String> realSurShipMap=new java.util.HashMap<String,String>();";
  protected final String TEXT_216 = NL + "        realSurShipMap.put(\"";
  protected final String TEXT_217 = "\",\"";
  protected final String TEXT_218 = "\");";
  protected final String TEXT_219 = NL + "    defaultSurvivorshipRules_";
  protected final String TEXT_220 = ".add(realSurShipMap);";
  protected final String TEXT_221 = NL + "    java.util.List targetMatchRuleList=new java.util.ArrayList<java.util.Map<String,String>>();";
  protected final String TEXT_222 = NL + "        java.util.Map<String,String> realJoinKeyMap=new java.util.HashMap<String,String>();";
  protected final String TEXT_223 = NL + "            realJoinKeyMap.put(\"";
  protected final String TEXT_224 = "\",\"";
  protected final String TEXT_225 = "\");";
  protected final String TEXT_226 = NL + "        targetMatchRuleList.add(realJoinKeyMap);";
  protected final String TEXT_227 = NL + "    allRules_";
  protected final String TEXT_228 = ".add(targetMatchRuleList);";
  protected final String TEXT_229 = NL + "    columnWithType_";
  protected final String TEXT_230 = ".put(\"";
  protected final String TEXT_231 = "\",\"";
  protected final String TEXT_232 = "\");" + NL + "    columnWithIndex_";
  protected final String TEXT_233 = ".put(\"";
  protected final String TEXT_234 = "\",\"";
  protected final String TEXT_235 = "\");";
  protected final String TEXT_236 = NL + "org.talend.dataquality.record.linkage.grouping.swoosh.SurvivorShipAlgorithmParams survivorShipAlgorithmParams_";
  protected final String TEXT_237 = " = org.talend.dataquality.record.linkage.grouping.swoosh.SurvivorshipUtils." + NL + "    createSurvivorShipAlgorithmParams((org.talend.dataquality.record.linkage.grouping.swoosh.AnalysisSwooshMatchRecordGrouping)recordGroupImp_";
  protected final String TEXT_238 = ",matchingRulesAll_";
  protected final String TEXT_239 = ",defaultSurvivorshipRules_";
  protected final String TEXT_240 = ",columnWithType_";
  protected final String TEXT_241 = ",columnWithIndex_";
  protected final String TEXT_242 = ");" + NL + "((org.talend.dataquality.record.linkage.grouping.swoosh.AnalysisSwooshMatchRecordGrouping)recordGroupImp_";
  protected final String TEXT_243 = ").setSurvivorShipAlgorithmParams(survivorShipAlgorithmParams_";
  protected final String TEXT_244 = ");";
  protected final String TEXT_245 = NL + "recordGroupImp_";
  protected final String TEXT_246 = ".setColumnDelimiter(\";\");" + NL + "recordGroupImp_";
  protected final String TEXT_247 = ".setIsOutputDistDetails(";
  protected final String TEXT_248 = ");" + NL + "recordGroupImp_";
  protected final String TEXT_249 = ".setIsComputeGrpQuality(";
  protected final String TEXT_250 = ");" + NL + "recordGroupImp_";
  protected final String TEXT_251 = ".setAcceptableThreshold(Float.valueOf(";
  protected final String TEXT_252 = "+\"\"));" + NL + "recordGroupImp_";
  protected final String TEXT_253 = ".setIsLinkToPrevious(";
  protected final String TEXT_254 = ");" + NL + "recordGroupImp_";
  protected final String TEXT_255 = ".setIsDisplayAttLabels(";
  protected final String TEXT_256 = ");" + NL + "recordGroupImp_";
  protected final String TEXT_257 = ".setIsGIDStringType(\"true\".equals(\"";
  protected final String TEXT_258 = "\")?true :false);" + NL + "gID2GQMap_";
  protected final String TEXT_259 = ".clear();" + NL + "" + NL + "while (iter_";
  protected final String TEXT_260 = ".hasNext() || forceLoop_";
  protected final String TEXT_261 = "){ // C_01" + NL + "" + NL + "  if (forceLoop_";
  protected final String TEXT_262 = "){" + NL + "    forceLoop_";
  protected final String TEXT_263 = " = false;" + NL + "  } else {" + NL + "    // block existing" + NL;
  protected final String TEXT_264 = NL + "      ";
  protected final String TEXT_265 = "_2Struct row_";
  protected final String TEXT_266 = " =null;";
  protected final String TEXT_267 = NL + "          row_";
  protected final String TEXT_268 = " = new ";
  protected final String TEXT_269 = "_2Struct();" + NL + "          row_";
  protected final String TEXT_270 = ".restoreObjectByArrays(iter_";
  protected final String TEXT_271 = ".next());";
  protected final String TEXT_272 = NL + "          row_";
  protected final String TEXT_273 = " = iter_";
  protected final String TEXT_274 = ".next();";
  protected final String TEXT_275 = NL + "          hashKey_";
  protected final String TEXT_276 = ".";
  protected final String TEXT_277 = " = row_";
  protected final String TEXT_278 = ".";
  protected final String TEXT_279 = ";" + NL + "          hashKey_";
  protected final String TEXT_280 = ".hashCodeDirty = true;";
  protected final String TEXT_281 = NL + "  }" + NL + "  tHash_Lookup_";
  protected final String TEXT_282 = ".lookup(hashKey_";
  protected final String TEXT_283 = ");" + NL + "  masterRows_";
  protected final String TEXT_284 = ".clear();";
  protected final String TEXT_285 = NL + "  groupRows_";
  protected final String TEXT_286 = ".clear();";
  protected final String TEXT_287 = NL + "  " + NL + "  ";
  protected final String TEXT_288 = NL + "  //add mutch rules" + NL + "  for(java.util.List<java.util.Map<String, String>> matcherList:matchingRulesAll_";
  protected final String TEXT_289 = "){" + NL + "     recordGroupImp_";
  protected final String TEXT_290 = ".addMatchRule(matcherList);" + NL + "  }" + NL + "  recordGroupImp_";
  protected final String TEXT_291 = ".initialize();";
  protected final String TEXT_292 = NL + " " + NL + "  while (tHash_Lookup_";
  protected final String TEXT_293 = ".hasNext()){  // loop on each data in one block" + NL + "    hashValue_";
  protected final String TEXT_294 = " = tHash_Lookup_";
  protected final String TEXT_295 = ".next();" + NL + "    // set the a loop data into inputTexts one column by one column. " + NL + "    java.util.List<Object> inputTexts=new java.util.ArrayList<Object>();";
  protected final String TEXT_296 = NL + "          inputTexts.add(hashValue_";
  protected final String TEXT_297 = ".";
  protected final String TEXT_298 = ");" + NL + "          ";
  protected final String TEXT_299 = NL + "        inputTexts.add(hashValue_";
  protected final String TEXT_300 = ".MASTER);";
  protected final String TEXT_301 = NL + "    recordGroupImp_";
  protected final String TEXT_302 = ".doGroup((Object[]) inputTexts.toArray(new Object[inputTexts.size()]));" + NL + "    " + NL + "  } // while" + NL + "" + NL + "  recordGroupImp_";
  protected final String TEXT_303 = ".end();" + NL + "  groupRows_";
  protected final String TEXT_304 = ".addAll(masterRows_";
  protected final String TEXT_305 = ");" + NL + "    ";
  protected final String TEXT_306 = NL + "    java.util.Collections.sort(groupRows_";
  protected final String TEXT_307 = ", " + NL + "      new Comparator<";
  protected final String TEXT_308 = "Struct>(){" + NL + "        public int compare(";
  protected final String TEXT_309 = "Struct row1, ";
  protected final String TEXT_310 = "Struct row2){" + NL + "          if (!(row1.GID).equals(row2.GID)){" + NL + "            return (row1.GID).compareTo(row2.GID);" + NL + "          } else {" + NL + "            // false < true" + NL + "            return (row2.MASTER).compareTo(row1.MASTER); " + NL + "          }" + NL + "        }" + NL + "      }" + NL + "    );";
  protected final String TEXT_311 = NL;
  protected final String TEXT_312 = NL + "} // C_01 //TDQ-9655  if the second tMatchGroup select sort and mulyipass, make the C_01 while end here, and sort all rows " + NL + "    java.util.Collections.sort(groupRows_";
  protected final String TEXT_313 = ", " + NL + "      new Comparator<";
  protected final String TEXT_314 = "Struct>(){" + NL + "        public int compare(";
  protected final String TEXT_315 = "Struct row1, ";
  protected final String TEXT_316 = "Struct row2){" + NL + "          if (!(row1.GID).equals(row2.GID)){" + NL + "            return (row1.GID).compareTo(row2.GID);" + NL + "          } else {" + NL + "            // false < true" + NL + "            return (row2.MASTER).compareTo(row1.MASTER); " + NL + "          }" + NL + "        }" + NL + "      }" + NL + "    );" + NL;
  protected final String TEXT_317 = NL + "  // output data" + NL + "  for (";
  protected final String TEXT_318 = "Struct out_";
  protected final String TEXT_319 = " : groupRows_";
  protected final String TEXT_320 = "){ // C_02" + NL + "  " + NL + "    if (out_";
  protected final String TEXT_321 = " != null){ // C_03";
  protected final String TEXT_322 = NL + "          if(out_";
  protected final String TEXT_323 = ".GRP_SIZE == 1){ // unique rows";
  protected final String TEXT_324 = NL + "              ";
  protected final String TEXT_325 = " = new ";
  protected final String TEXT_326 = "Struct();";
  protected final String TEXT_327 = NL + "                  ";
  protected final String TEXT_328 = ".";
  protected final String TEXT_329 = " = out_";
  protected final String TEXT_330 = ".";
  protected final String TEXT_331 = ";";
  protected final String TEXT_332 = NL + "                  ";
  protected final String TEXT_333 = " = null;";
  protected final String TEXT_334 = NL + "                  ";
  protected final String TEXT_335 = " = null;";
  protected final String TEXT_336 = NL + "          }else{" + NL + "              double groupQuality;" + NL + "              if(out_";
  protected final String TEXT_337 = ".MASTER == true){ // master rows case we get group quality directly" + NL + "                  groupQuality = out_";
  protected final String TEXT_338 = ".GRP_QUALITY;" + NL + "              }else{ // sub rows case we get group quality from gID2GQMap" + NL + "                  groupQuality = gID2GQMap_";
  protected final String TEXT_339 = ".get(String.valueOf(out_";
  protected final String TEXT_340 = ".GID));" + NL + "              }" + NL + "              if(groupQuality >= CONFIDENCE_THRESHOLD_";
  protected final String TEXT_341 = "){";
  protected final String TEXT_342 = NL + "                      ";
  protected final String TEXT_343 = " = null;";
  protected final String TEXT_344 = NL + "                      ";
  protected final String TEXT_345 = " = new ";
  protected final String TEXT_346 = "Struct();";
  protected final String TEXT_347 = NL + "                          ";
  protected final String TEXT_348 = ".";
  protected final String TEXT_349 = " = out_";
  protected final String TEXT_350 = ".";
  protected final String TEXT_351 = ";";
  protected final String TEXT_352 = NL + "                      ";
  protected final String TEXT_353 = " = null;";
  protected final String TEXT_354 = NL + "              }else{";
  protected final String TEXT_355 = NL + "                      ";
  protected final String TEXT_356 = " = null;";
  protected final String TEXT_357 = NL + "                      ";
  protected final String TEXT_358 = " = null;";
  protected final String TEXT_359 = NL + "                  ";
  protected final String TEXT_360 = " = new ";
  protected final String TEXT_361 = "Struct();";
  protected final String TEXT_362 = NL + "                      ";
  protected final String TEXT_363 = ".";
  protected final String TEXT_364 = " = out_";
  protected final String TEXT_365 = ".";
  protected final String TEXT_366 = "; ";
  protected final String TEXT_367 = NL + "              }" + NL + "          }";
  protected final String TEXT_368 = NL;
  protected final String TEXT_369 = NL + "              ";
  protected final String TEXT_370 = " = null;";
  protected final String TEXT_371 = NL + "              ";
  protected final String TEXT_372 = " = null;";
  protected final String TEXT_373 = NL + "              ";
  protected final String TEXT_374 = " = null;";
  protected final String TEXT_375 = NL + NL + "      // all output";
  protected final String TEXT_376 = NL + "      ";
  protected final String TEXT_377 = " = new ";
  protected final String TEXT_378 = "Struct();";
  protected final String TEXT_379 = " ";
  protected final String TEXT_380 = NL + "          ";
  protected final String TEXT_381 = ".";
  protected final String TEXT_382 = " = out_";
  protected final String TEXT_383 = ".";
  protected final String TEXT_384 = ";";
  protected final String TEXT_385 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
     
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
org.talend.core.model.process.IProcess process = node.getProcess();
String cid = node.getUniqueName();
cid = cid.replace("_GroupIn", "");
boolean bSeparateOutput = "true".equals(ElementParameterParser.getValue(node, "__SEPARATE_OUTPUT__"));
boolean bOutputDetails = "true".equals(ElementParameterParser.getValue(node, "__OUTPUTDD__"));
boolean bCompGRPQuality = "true".equals(ElementParameterParser.getValue(node, "__COMPUTE_GRP_QUALITY__"));
final List<org.talend.core.model.process.IContextParameter> params = process.getContextManager().getDefaultContext().getContextParameterList();
boolean bSortOnDisk = "true".equals(ElementParameterParser.getValue(node, "__STORE_ON_DISK__")); 
// incoming connection
INode previousNode=node.getIncomingConnections().get(0).getSource();
List<? extends IConnection> inConns = previousNode.getIncomingConnections();
IConnection inConn = null; 
String connNameMain = null;

if (inConns == null || inConns.size() == 0){
  return "";
} else{
  inConn = inConns.get(0);
  connNameMain = inConn.getName();
}

// schema of tGroup
IMetadataTable table = node.getMetadataList().get(0);
List<IMetadataColumn> columns = null;

// 'originalInputColumnSize' except the exernal columns as follow.
int extSize=bCompGRPQuality ? 5 : 4;
extSize=bOutputDetails ? extSize+1 : extSize;
int originalInputColumnSize=0;
if (table!=null) {
    columns = table.getListColumns();
    originalInputColumnSize=columns.size()-extSize;
}
String [] externalColumnsName=new String []{"GID","GRP_SIZE","MASTER","SCORE","GRP_QUALITY","MATCHING_DISTANCES"};
java.util.List externalColumnList=java.util.Arrays.asList(externalColumnsName);

// input schema
IMetadataTable tableIn = inConn.getMetadataTable();
List<IMetadataColumn> inColumns = null;
if (tableIn!=null) {
    inColumns = tableIn.getListColumns();
}

// get previous node name
INode preNode= inConn.getSource();
String preNodeName=preNode.getComponent().getName();

// outing connection
String connNameOut = null, connNameMainOut = null;
String connNameUniqueRowsOut = null, connNameConfidentGroupsOut = null, connNameUncertainGroupsOut = null;
List<? extends IConnection> outConns = node.getOutgoingSortedConnections();

if (outConns != null && outConns.size() > 0){

    connNameOut = outConns.get(0).getName(); 

    if(connNameMainOut == null){
        connNameMainOut = "mainOutput_" + cid; 
    }    
        for (IConnection connection: outConns){    
            if (connection.isActivate()){
                String connOutCntorName = connection.getConnectorName();
                String connOutFlowName = connection.getName();        
                if ("UNIQUE_ROWS".equals(connOutCntorName)){
                    connNameUniqueRowsOut = connOutFlowName;
                } else if ("CONFIDENT_GROUPS".equals(connOutCntorName)){
                    connNameConfidentGroupsOut = connOutFlowName;
                } else if ("UNCERTAIN_GROUPS".equals(connOutCntorName)){
                    connNameUncertainGroupsOut = connOutFlowName;
                } else if ("FLOW".equals(connOutCntorName)){
                    connNameMainOut = connOutFlowName;
                }
            }
        }
} else {
    return "";
}

// blocks
List<Map<String, String>> listBlocking = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__BLOCKING_DEFINITION__");
List<String> blockColumns = new java.util.ArrayList<String>(); // to store unduplicated blocking columns
boolean hasAtLeastOneBlock = false;
String sSorted = "";

for (Map<String, String> blocking : listBlocking){
  String sColumnName = blocking.get("INPUT_COLUMN");
  
  if (!hasAtLeastOneBlock){
    hasAtLeastOneBlock = true;
    sSorted = "Sorted";
  }

  if (!blockColumns.contains(sColumnName)){
    blockColumns.add(sColumnName);
  }
}

String _ACCEPTABLE_THRESHOLD = ElementParameterParser.getValue(node, "__INTERVAL__");

//Get the join key parameter
//mzhao TDQ-5981 add multi rule set feature. Get the multi rules from matching component external data.

MatchingData matchData = (MatchingData)node.getExternalNode().getExternalEmfData();

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    
if (matchData != null && matchData.getRuleMatchers().size() > 0) {
    List<RuleMatcher> ruleMatchers = matchData.getRuleMatchers();
    for (RuleMatcher ruleMatcher : ruleMatchers) {
        List<JoinkeyRecord> jionkeys = ruleMatcher.getJoinkeys();
        
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    
        EMap<String, Object> paramMap = ruleMatcher.getMatchParamMap();
        for (JoinkeyRecord joinKey : jionkeys) {
            EMap<String, Object> columnMap = joinKey.getColumnMap();
            
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_10);
    
            int indexOfcurrentMatchKey = 0;
            for(String key: columnMap.keySet()){
                String value=columnMap.get(key)==null?"":columnMap.get(key).toString();
                String newKey=key;
                String newValue=value;
                //replace "INPUT_COLUMN" with "ATTRIBUTE_NAME" and "COLUMN_IDX" to JAVA API.
                if("INPUT_COLUMN".equals(key)){
                    
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_13);
    
                    newKey="COLUMN_IDX";
                    for(int index = 0; index < columns.size(); index++) {
                        IMetadataColumn column = columns.get(index);
                        if(value.equals(column.getLabel())) {
                            newValue=String.valueOf(index);
                            indexOfcurrentMatchKey=index;
                            break;
                        }
                      }
                }else if("CUSTOM_MATCHER".equals(key)){
                    //only add CUSTOMER_MATCH_CLASS for custom Mating Type.
                    if("custom".equals(columnMap.get("MATCHING_TYPE"))){
                        newKey="CUSTOMER_MATCH_CLASS";
                    }else{
                        continue;
                    }
                }
                // they allow expression, like as "globalMap.get("col1")"
                if ("CONFIDENCE_WEIGHT".equals(key)||"CUSTOM_MATCHER".equals(key)){
                    
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(newValue);
    stringBuffer.append(TEXT_17);
    
                    continue;
                }
                //avoid to appear this style """"
                newValue=newValue.replace("\"", "");
                
    stringBuffer.append(TEXT_18);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(newValue);
    stringBuffer.append(TEXT_21);
    
            }
            
            if(!"dummy".equals(columnMap.get("MATCHING_TYPE"))){
                for(String key: paramMap.keySet()){
                    String value=paramMap.get(key)==null ? "" :paramMap.get(key).toString();
                    String newKey=key;
                    //replace key "INTERVAL_RULE" with "RECORD_MATCH_THRESHOLD" .
                    if(key.equals("INTERVAL_RULE")){
                        newKey="RECORD_MATCH_THRESHOLD";
                    }
                    if (!"RECORD_MATCH_THRESHOLD".equals(newKey)){
                        value=value.replace("\"", "");
                    }
                    if ("RECORD_MATCH_THRESHOLD".equals(newKey)){
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_25);
    }else{
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_29);
    }
                    if(params.size()>0&&value.contains("context.")){
                        String contextName=value.split("context.")[1];
                        for (org.talend.core.model.process.IContextParameter ctxParam :params){
                            if(ctxParam.getName().equals(contextName)){
                                
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_33);
    
                                break;
                            }
                        }
                    }
                }
            }
            
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_36);
      
           
        }
        if(ruleMatchers.size()>1){
        
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    }
        
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    
    }
    
    List<org.talend.designer.tdqmatching.DefaultSurvivorshipDefinitions> defSurs = matchData.getDefaultSurvivorshipDefinitions();
    
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    
    for(org.talend.designer.tdqmatching.DefaultSurvivorshipDefinitions defSur :defSurs){
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    
        EMap<String, Object> columnMap= defSur.getColumnMap();
        for(String key:columnMap.keySet()){
            String value=columnMap.get(key)==null ? "" :columnMap.get(key).toString();
            value=value.replace("\"", "");
            
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_49);
    
        }
        
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    
    }
    
}


    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    
  List<List<Map<String, String>>> allRules = (List<List<Map<String, String>>>)ElementParameterParser.getMultiObjectValue(node, "__JOIN_KEY__");
  if(allRules!=null&&allRules.size()>0){
      List<Map<String, String>> firstMatcherList = allRules.get(0);
      
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
      for(Map<String,String> columnMap:firstMatcherList){
      
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    
          for(String key: columnMap.keySet()){
              String value=columnMap.get(key)==null?"":columnMap.get(key).toString();
              String newKey=key;
              String newValue=value;
              //replace "INPUT_COLUMN" with "ATTRIBUTE_NAME" and "COLUMN_IDX" to JAVA API.
              if("INPUT_COLUMN".equals(key)){
                  
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_61);
    
                  newKey="COLUMN_IDX";
                  for(int index = 0; index < columns.size(); index++) {
                      IMetadataColumn column = columns.get(index);
                      if(value.equals(column.getLabel())) {
                          newValue=String.valueOf(index);
                          break;
                      }
                    }
              }else if("CUSTOM_MATCHER".equals(key)){
                //only add CUSTOMER_MATCH_CLASS for custom Mating Type.
                  if("custom".equals(columnMap.get("MATCHING_TYPE"))){
                      newKey="CUSTOMER_MATCH_CLASS";
                  }else{
                      continue;
                  }
              }
              // it allows expression like as "globalMap.get("col1")"
              if ("CONFIDENCE_WEIGHT".equals(key)||"CUSTOM_MATCHER".equals(key)){
                  
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(newValue);
    stringBuffer.append(TEXT_65);
    
                  continue;
              }
              //avoid to appear this style """"
              newValue=newValue.replace("\"", "");
              
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(newKey);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(newValue);
    stringBuffer.append(TEXT_69);
    
          }
          
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    
      }
      //Add the parameter map of algorithm interval of each matching rule, and  algorithm name.
      
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(_ACCEPTABLE_THRESHOLD);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_81);
    
      
  }  
 
    stringBuffer.append(TEXT_82);
    
String _CONFIDENCE_THRESHOLD = ElementParameterParser.getValue(node, "__CONFIDENCE_THRESHOLD__");
boolean bStoreOnDisk = "true".equals(ElementParameterParser.getValue(node, "__STORE_ON_DISK__")); 
boolean bSortData = "true".equals(ElementParameterParser.getValue(node, "__SORT_DATA__")); 
boolean bGIDUseString = "true".equals(ElementParameterParser.getValue(node, "__GID_USE_STRING__"));
boolean bMultiPass="true".equals(ElementParameterParser.getValue(node, "__LINK_WITH_PREVIOUS__"))&&(preNodeName.startsWith("tMatchGroup")||preNodeName.equals("tJavaRow"));
boolean displayAttLabels = "true".equals(ElementParameterParser.getValue(node, "__DISPLAY_ATTR_LABELS__"));

if (bStoreOnDisk){

    stringBuffer.append(TEXT_83);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_84);
    
}

    stringBuffer.append(TEXT_85);
    if(connNameMainOut.startsWith("mainOutput_")){
    stringBuffer.append(TEXT_86);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(connNameMainOut);
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_89);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(_CONFIDENCE_THRESHOLD);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_120);
    
    if (bSortOnDisk && hasAtLeastOneBlock){

    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_123);
    }else{
    stringBuffer.append(TEXT_124);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_128);
     
String algorithm=ElementParameterParser.getValue(node, "__MATCHING_ALGORITHM__");
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    if("Simple VSR Matcher".equals(algorithm)){
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_136);
     
         
         for(int i=0;i< columns.size();i++){
              IMetadataColumn column=columns.get(i);
              String type=column.getTalendType();
              type=type.replace("id_", "");
        
    stringBuffer.append(TEXT_137);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    if (bSeparateOutput){
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(originalInputColumnSize);
    stringBuffer.append(TEXT_156);
    }else{
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_162);
     
         
         for(int i=0;i< columns.size();i++){
              IMetadataColumn column=columns.get(i);
              String type=column.getTalendType();
              type=type.replace("id_", "");
        
    stringBuffer.append(TEXT_163);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_164);
    
                  if("Date".equals(type)){
                  
    stringBuffer.append(TEXT_165);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_169);
    
                  }else if("Double".equals(type)||"Float".equals(type)||"Integer".equals(type)||"Long".equals(type)||"Short".equals(type)){
                  
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_176);
    
                          if("Integer".equals(type)){
                          
    stringBuffer.append(TEXT_177);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_178);
    
                          }else if("Double".equals(type)){
                              
    stringBuffer.append(TEXT_179);
    
                          }else if("Short".equals(type)){
                              
    stringBuffer.append(TEXT_180);
      
                          }else if("Long".equals(type)){
                              
    stringBuffer.append(TEXT_181);
      
                          }else{
                          
    stringBuffer.append(TEXT_182);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_183);
    
                          }
                          
    stringBuffer.append(TEXT_184);
    
                  }else{  
                   
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(type);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_190);
    
                  }
                  
    stringBuffer.append(TEXT_191);
    }
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    if(bMultiPass){
    stringBuffer.append(TEXT_201);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(originalInputColumnSize-1);
    stringBuffer.append(TEXT_203);
    }else{
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(originalInputColumnSize);
    stringBuffer.append(TEXT_206);
    }
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
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_214);
    
for(Map<String,String> surShipMap:(List<Map<String, String>>)ElementParameterParser.getMultiObjectValue(node, "__SURVIVORSHIP__")){
    
    stringBuffer.append(TEXT_215);
    
    for(String key:surShipMap.keySet()){
        
    stringBuffer.append(TEXT_216);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(surShipMap.get(key));
    stringBuffer.append(TEXT_218);
    
    }
    
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    
}
List<List<Map<String, String>>> allRules2 = (List<List<Map<String, String>>>)ElementParameterParser.getMultiObjectValue(node, "__JOIN_KEY__");

for(List<Map<String,String>> matchRuleList:allRules2){
    
    stringBuffer.append(TEXT_221);
    
    for(Map<String,String> joinKeyMap:matchRuleList){
        
    stringBuffer.append(TEXT_222);
    
        for(String key: joinKeyMap.keySet()){
            
    stringBuffer.append(TEXT_223);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(joinKeyMap.get(key));
    stringBuffer.append(TEXT_225);
    
        }
        
    stringBuffer.append(TEXT_226);
    
    }
    
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    
}
for(int i=0;i< columns.size();i++){
    IMetadataColumn column=columns.get(i);
    
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(column.getTalendType());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_235);
    
}

    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(TEXT_245);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(bOutputDetails);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(bCompGRPQuality);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(_ACCEPTABLE_THRESHOLD);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(bMultiPass);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(displayAttLabels);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(bGIDUseString);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_263);
    
  if (hasAtLeastOneBlock){
  
    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_266);
        
      if(bSortOnDisk){
      
    stringBuffer.append(TEXT_267);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    
      }else{
      
    stringBuffer.append(TEXT_272);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_274);
    
      }
      for (String block : blockColumns){
      
    stringBuffer.append(TEXT_275);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(block);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(block);
    stringBuffer.append(TEXT_279);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_280);
    
      }
  }
  
    stringBuffer.append(TEXT_281);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_283);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_284);
    if( !(bSortData && bMultiPass)){ //TDQ-9655 if the second tMatchGroup select sort and mulyipass, not clear 
    stringBuffer.append(TEXT_285);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    if("Simple VSR Matcher".equals(algorithm)){
    stringBuffer.append(TEXT_288);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_291);
    }
    stringBuffer.append(TEXT_292);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_295);
    
    for(int i=0;i<inColumns.size();i++){  
        IMetadataColumn column=inColumns.get(i);
          String label= column.getLabel();
          //filter the external columns if it is not multiple pass.
          if(!bMultiPass && externalColumnList.contains(label)&&i>originalInputColumnSize-1){
                  continue;
          }
    
    stringBuffer.append(TEXT_296);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_298);
    }
     if(!"Simple VSR Matcher".equals(algorithm) &&bMultiPass){  
    stringBuffer.append(TEXT_299);
    stringBuffer.append(connNameMain);
    stringBuffer.append(TEXT_300);
    }
    stringBuffer.append(TEXT_301);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_305);
    
  if (bSortData && !bMultiPass){ // order by gid, master
  
    stringBuffer.append(TEXT_306);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_309);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_310);
    }
    stringBuffer.append(TEXT_311);
    if( bSortData && bMultiPass){  
    stringBuffer.append(TEXT_312);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_316);
    } 
    stringBuffer.append(TEXT_317);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_319);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_321);
    if (bSeparateOutput){
    stringBuffer.append(TEXT_322);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_323);
    if (connNameUniqueRowsOut != null){
    stringBuffer.append(TEXT_324);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_326);
    for (IMetadataColumn column : table.getListColumns()){
    stringBuffer.append(TEXT_327);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_328);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_329);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_331);
    }
              }
              if (connNameConfidentGroupsOut != null){
    stringBuffer.append(TEXT_332);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_333);
    }
              if (connNameUncertainGroupsOut != null){
    stringBuffer.append(TEXT_334);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_339);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_341);
    if (connNameUniqueRowsOut != null){
    stringBuffer.append(TEXT_342);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_343);
    }
                  if (connNameConfidentGroupsOut != null){
    stringBuffer.append(TEXT_344);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_346);
    for (IMetadataColumn column : table.getListColumns()){
    stringBuffer.append(TEXT_347);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_348);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_350);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_351);
    }
                  }
                  if (connNameUncertainGroupsOut != null){
    stringBuffer.append(TEXT_352);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_354);
    if (connNameUniqueRowsOut != null){
    stringBuffer.append(TEXT_355);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_356);
    }
                  if (connNameConfidentGroupsOut != null){
    stringBuffer.append(TEXT_357);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_358);
    }
                  if (connNameUncertainGroupsOut != null){
    stringBuffer.append(TEXT_359);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_360);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_361);
    for (IMetadataColumn column : table.getListColumns()){
    stringBuffer.append(TEXT_362);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_363);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_364);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_366);
    }
                  }
    stringBuffer.append(TEXT_367);
    }else{
    stringBuffer.append(TEXT_368);
    if (connNameUniqueRowsOut != null){
    stringBuffer.append(TEXT_369);
    stringBuffer.append(connNameUniqueRowsOut);
    stringBuffer.append(TEXT_370);
    }
          if (connNameConfidentGroupsOut != null){
    stringBuffer.append(TEXT_371);
    stringBuffer.append(connNameConfidentGroupsOut);
    stringBuffer.append(TEXT_372);
    }
          if (connNameUncertainGroupsOut != null){
    stringBuffer.append(TEXT_373);
    stringBuffer.append(connNameUncertainGroupsOut);
    stringBuffer.append(TEXT_374);
    }
      }
    stringBuffer.append(TEXT_375);
    stringBuffer.append(TEXT_376);
    stringBuffer.append(connNameMainOut);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(connNameOut);
    stringBuffer.append(TEXT_378);
    for(IMetadataColumn column : table.getListColumns()){
    stringBuffer.append(TEXT_379);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(connNameMainOut);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(column);
    stringBuffer.append(TEXT_384);
    }
        
      List<BlockCode> blockCodes = new java.util.ArrayList<BlockCode>();
    blockCodes.add(new BlockCode("C_03"));
  blockCodes.add(new BlockCode("C_02"));
  if( !(bSortData && bMultiPass)){  // TDQ-9655 if the second tMatchGroup select sort and mulyipass, do not need C_01 here
    blockCodes.add(new BlockCode("C_01"));
  }
((org.talend.core.model.process.AbstractNode) node).setBlocksCodeToClose(blockCodes);

    stringBuffer.append(TEXT_385);
    return stringBuffer.toString();
  }
}
