package org.talend.designer.codegen.translators.processing;

import java.util.List;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.utils.NodeUtil;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TTopSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TTopSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TTopSparkstreamingconfigJava result = new TTopSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "\t";
  protected final String TEXT_2 = NL + "            public static class ";
  protected final String TEXT_3 = " implements ";
  protected final String TEXT_4 = " {";
  protected final String TEXT_5 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_6 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "\t            public ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = "(";
  protected final String TEXT_9 = ") ";
  protected final String TEXT_10 = " {" + NL + "\t            \t";
  protected final String TEXT_11 = NL + "\t            \t";
  protected final String TEXT_12 = NL + "\t                ";
  protected final String TEXT_13 = NL + "\t                return ";
  protected final String TEXT_14 = ";" + NL + "\t            }" + NL + "\t        }" + NL + "\t\t";
  protected final String TEXT_15 = NL + "            public static class ";
  protected final String TEXT_16 = " implements ";
  protected final String TEXT_17 = " {";
  protected final String TEXT_18 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_19 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "                public ";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = "(";
  protected final String TEXT_22 = ") ";
  protected final String TEXT_23 = " {" + NL + "                \t";
  protected final String TEXT_24 = NL + "\t                 \treturn ";
  protected final String TEXT_25 = ";";
  protected final String TEXT_26 = NL + "                }" + NL + "            }";
  protected final String TEXT_27 = NL;
  protected final String TEXT_28 = NL + "            // No sparkcode generated for unnecessary ";
  protected final String TEXT_29 = NL;
  protected final String TEXT_30 = NL + "            // No sparkconfig generated for unnecessary ";
  protected final String TEXT_31 = NL;
  protected final String TEXT_32 = NL + "        ";
  protected final String TEXT_33 = " rdd_";
  protected final String TEXT_34 = " =" + NL + "            ctx.sparkContext().parallelizePairs(" + NL + "                    rdd_";
  protected final String TEXT_35 = ".";
  protected final String TEXT_36 = "(";
  protected final String TEXT_37 = "," + NL + "                        new ";
  protected final String TEXT_38 = "(job))," + NL + "                1); // One partition to keep the ordering.";
  protected final String TEXT_39 = NL + "            return 1;";
  protected final String TEXT_40 = NL + "            return -1;";
  protected final String TEXT_41 = NL + "            return -1;";
  protected final String TEXT_42 = NL + "            return 1;";
  protected final String TEXT_43 = NL + "            if (";
  protected final String TEXT_44 = " == null && ";
  protected final String TEXT_45 = " != null) {";
  protected final String TEXT_46 = NL + "            } else if (";
  protected final String TEXT_47 = " != null && ";
  protected final String TEXT_48 = " == null) {";
  protected final String TEXT_49 = NL + "            } else if (";
  protected final String TEXT_50 = " == null && ";
  protected final String TEXT_51 = " == null) {" + NL + "                //ignore" + NL + "            } else {";
  protected final String TEXT_52 = NL + "            if (";
  protected final String TEXT_53 = " != ";
  protected final String TEXT_54 = ") {" + NL + "                if (";
  protected final String TEXT_55 = ") {";
  protected final String TEXT_56 = NL + "                } else {";
  protected final String TEXT_57 = NL + "                }" + NL + "            }";
  protected final String TEXT_58 = NL + "            if (";
  protected final String TEXT_59 = " > ";
  protected final String TEXT_60 = ") {";
  protected final String TEXT_61 = NL + "            } else if (";
  protected final String TEXT_62 = " < ";
  protected final String TEXT_63 = ") {";
  protected final String TEXT_64 = NL + "            }";
  protected final String TEXT_65 = NL + "            int comp_";
  protected final String TEXT_66 = " = ";
  protected final String TEXT_67 = ".compareTo(";
  protected final String TEXT_68 = ");" + NL + "            if (comp_";
  protected final String TEXT_69 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_70 = " > 0) {";
  protected final String TEXT_71 = NL + "                } else {";
  protected final String TEXT_72 = NL + "                }" + NL + "            }";
  protected final String TEXT_73 = NL + "            if (";
  protected final String TEXT_74 = " - ";
  protected final String TEXT_75 = " != 0) {" + NL + "                if (";
  protected final String TEXT_76 = " - ";
  protected final String TEXT_77 = " > 0) {";
  protected final String TEXT_78 = NL + "                } else {";
  protected final String TEXT_79 = NL + "                }" + NL + "            }";
  protected final String TEXT_80 = NL + "                int cmp_";
  protected final String TEXT_81 = " = FormatterUtils.format_DateInUTC(";
  protected final String TEXT_82 = ", ";
  protected final String TEXT_83 = ").compareTo(FormatterUtils.format_DateInUTC(";
  protected final String TEXT_84 = ", ";
  protected final String TEXT_85 = "));" + NL + "                if (cmp_";
  protected final String TEXT_86 = " > 0) {";
  protected final String TEXT_87 = NL + "                } else if (cmp_";
  protected final String TEXT_88 = " < 0) {";
  protected final String TEXT_89 = NL + "                }";
  protected final String TEXT_90 = NL + "                if (!";
  protected final String TEXT_91 = ".equals(";
  protected final String TEXT_92 = ")) {" + NL + "                    if (";
  protected final String TEXT_93 = ".compareTo(";
  protected final String TEXT_94 = ") > 0) {";
  protected final String TEXT_95 = NL + "                    } else {";
  protected final String TEXT_96 = NL + "                    }" + NL + "                }";
  protected final String TEXT_97 = NL + "                int cmp_";
  protected final String TEXT_98 = " = String.valueOf(";
  protected final String TEXT_99 = ").compareTo(String.valueOf(";
  protected final String TEXT_100 = "));" + NL + "                if (cmp_";
  protected final String TEXT_101 = " > 0) {";
  protected final String TEXT_102 = NL + "                } else if (cmp_";
  protected final String TEXT_103 = " < 0) {";
  protected final String TEXT_104 = NL + "                }";
  protected final String TEXT_105 = NL + "                if (";
  protected final String TEXT_106 = " > ";
  protected final String TEXT_107 = ") {";
  protected final String TEXT_108 = NL + "                } else if (";
  protected final String TEXT_109 = " < ";
  protected final String TEXT_110 = ") {";
  protected final String TEXT_111 = NL + "                }";
  protected final String TEXT_112 = NL + "                int cmp_";
  protected final String TEXT_113 = " = String.valueOf(";
  protected final String TEXT_114 = ").compareTo(String.valueOf(";
  protected final String TEXT_115 = "));" + NL + "                if (cmp_";
  protected final String TEXT_116 = " > 0) {";
  protected final String TEXT_117 = NL + "                } else if (cmp_";
  protected final String TEXT_118 = " < 0) {";
  protected final String TEXT_119 = NL + "                }";
  protected final String TEXT_120 = NL + "                if (";
  protected final String TEXT_121 = " > ";
  protected final String TEXT_122 = ") {";
  protected final String TEXT_123 = NL + "                } else if (";
  protected final String TEXT_124 = " < ";
  protected final String TEXT_125 = ") {";
  protected final String TEXT_126 = NL + "                }";
  protected final String TEXT_127 = NL + "                int cmp_";
  protected final String TEXT_128 = " = String.valueOf(";
  protected final String TEXT_129 = ").compareTo(String.valueOf(";
  protected final String TEXT_130 = "));";
  protected final String TEXT_131 = NL + "                int cmp_";
  protected final String TEXT_132 = " = ";
  protected final String TEXT_133 = ".compareTo(";
  protected final String TEXT_134 = ");";
  protected final String TEXT_135 = NL + "                if (cmp_";
  protected final String TEXT_136 = " > 0) {";
  protected final String TEXT_137 = NL + "                } else if (cmp_";
  protected final String TEXT_138 = " < 0) {";
  protected final String TEXT_139 = NL + "                }";
  protected final String TEXT_140 = NL + "                int cmp_";
  protected final String TEXT_141 = " = String.valueOf(";
  protected final String TEXT_142 = ").compareTo(String.valueOf(";
  protected final String TEXT_143 = "));" + NL + "                if (cmp_";
  protected final String TEXT_144 = " > 0) {";
  protected final String TEXT_145 = NL + "                } else if (cmp_";
  protected final String TEXT_146 = " < 0) {";
  protected final String TEXT_147 = NL + "                }";
  protected final String TEXT_148 = NL + "                if (";
  protected final String TEXT_149 = " > ";
  protected final String TEXT_150 = ") {";
  protected final String TEXT_151 = NL + "                } else if (";
  protected final String TEXT_152 = " < ";
  protected final String TEXT_153 = ") {";
  protected final String TEXT_154 = NL + "                }";
  protected final String TEXT_155 = NL + "                int cmp_";
  protected final String TEXT_156 = " = String.valueOf(";
  protected final String TEXT_157 = ").compareTo(String.valueOf(";
  protected final String TEXT_158 = "));" + NL + "                if (cmp_";
  protected final String TEXT_159 = " > 0) {";
  protected final String TEXT_160 = NL + "                } else if (cmp_";
  protected final String TEXT_161 = " < 0) {";
  protected final String TEXT_162 = NL + "                }";
  protected final String TEXT_163 = NL + "                if (";
  protected final String TEXT_164 = " > ";
  protected final String TEXT_165 = ") {";
  protected final String TEXT_166 = NL + "                } else if (";
  protected final String TEXT_167 = " < ";
  protected final String TEXT_168 = ") {";
  protected final String TEXT_169 = NL + "                }";
  protected final String TEXT_170 = NL + "                int cmp_";
  protected final String TEXT_171 = " = String.valueOf(";
  protected final String TEXT_172 = ").compareTo(String.valueOf(";
  protected final String TEXT_173 = "));" + NL + "                if (cmp_";
  protected final String TEXT_174 = " > 0) {";
  protected final String TEXT_175 = NL + "                } else if (cmp_";
  protected final String TEXT_176 = " < 0) {";
  protected final String TEXT_177 = NL + "                }";
  protected final String TEXT_178 = NL + "                if (";
  protected final String TEXT_179 = " > ";
  protected final String TEXT_180 = ") {";
  protected final String TEXT_181 = NL + "                } else if (";
  protected final String TEXT_182 = " < ";
  protected final String TEXT_183 = ") {";
  protected final String TEXT_184 = NL + "                }";
  protected final String TEXT_185 = NL + "            int comp_";
  protected final String TEXT_186 = " = ";
  protected final String TEXT_187 = ".compareTo(";
  protected final String TEXT_188 = ");" + NL + "            if (comp_";
  protected final String TEXT_189 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_190 = " > 0) {";
  protected final String TEXT_191 = NL + "                } else {";
  protected final String TEXT_192 = NL + "                }" + NL + "" + NL + "            }";
  protected final String TEXT_193 = NL + "            throw new JobConfigurationError(\"The ";
  protected final String TEXT_194 = " type is not supported: column--";
  protected final String TEXT_195 = "\");";
  protected final String TEXT_196 = NL + "            }";
  protected final String TEXT_197 = NL + "\t";
  protected final String TEXT_198 = NL + "\t";
  protected final String TEXT_199 = NL + "            public static class ";
  protected final String TEXT_200 = " implements ";
  protected final String TEXT_201 = " {";
  protected final String TEXT_202 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_203 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "\t            public ";
  protected final String TEXT_204 = " ";
  protected final String TEXT_205 = "(";
  protected final String TEXT_206 = ") ";
  protected final String TEXT_207 = " {" + NL + "\t            \t";
  protected final String TEXT_208 = NL + "\t            \t";
  protected final String TEXT_209 = NL + "\t                ";
  protected final String TEXT_210 = NL + "\t                return ";
  protected final String TEXT_211 = ";" + NL + "\t            }" + NL + "\t        }" + NL + "\t\t";
  protected final String TEXT_212 = NL + "            public static class ";
  protected final String TEXT_213 = " implements ";
  protected final String TEXT_214 = " {";
  protected final String TEXT_215 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_216 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "                public ";
  protected final String TEXT_217 = " ";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = ") ";
  protected final String TEXT_220 = " {" + NL + "                \t";
  protected final String TEXT_221 = NL + "\t                 \treturn ";
  protected final String TEXT_222 = ";";
  protected final String TEXT_223 = NL + "                }" + NL + "            }";
  protected final String TEXT_224 = NL;
  protected final String TEXT_225 = NL + "            // No sparkcode generated for unnecessary ";
  protected final String TEXT_226 = NL + "        public static class StreamingTop_";
  protected final String TEXT_227 = " implements org.apache.spark.api.java.function.Function<";
  protected final String TEXT_228 = ", ";
  protected final String TEXT_229 = "> {" + NL + "            transient private org.apache.spark.api.java.JavaSparkContext ctx = null;" + NL + "            private Integer top = 5;" + NL + "" + NL + "            public StreamingTop_";
  protected final String TEXT_230 = "(Integer top) {" + NL + "                this.top = top;" + NL + "            }" + NL + "" + NL + "            public ";
  protected final String TEXT_231 = " call(";
  protected final String TEXT_232 = " temporaryRdd)" + NL + "                    throws Exception {" + NL + "                if (ctx == null) {" + NL + "                    ctx = new org.apache.spark.api.java.JavaSparkContext(temporaryRdd.context());" + NL + "                }" + NL + "                return ctx.parallelizePairs(" + NL + "                        temporaryRdd.";
  protected final String TEXT_233 = "(top," + NL + "                                new ";
  protected final String TEXT_234 = "(new JobConf())));" + NL + "            }" + NL + "        }";
  protected final String TEXT_235 = NL + "            // No sparkconfig generated for unnecessary ";
  protected final String TEXT_236 = NL + "            ";
  protected final String TEXT_237 = " rdd_";
  protected final String TEXT_238 = " =" + NL + "                    rdd_";
  protected final String TEXT_239 = ".transformToPair(new StreamingTop_";
  protected final String TEXT_240 = "(";
  protected final String TEXT_241 = "));";
  protected final String TEXT_242 = NL + "            return 1;";
  protected final String TEXT_243 = NL + "            return -1;";
  protected final String TEXT_244 = NL + "            return -1;";
  protected final String TEXT_245 = NL + "            return 1;";
  protected final String TEXT_246 = NL + "            if (";
  protected final String TEXT_247 = " == null && ";
  protected final String TEXT_248 = " != null) {";
  protected final String TEXT_249 = NL + "            } else if (";
  protected final String TEXT_250 = " != null && ";
  protected final String TEXT_251 = " == null) {";
  protected final String TEXT_252 = NL + "            } else if (";
  protected final String TEXT_253 = " == null && ";
  protected final String TEXT_254 = " == null) {" + NL + "                //ignore" + NL + "            } else {";
  protected final String TEXT_255 = NL + "            if (";
  protected final String TEXT_256 = " != ";
  protected final String TEXT_257 = ") {" + NL + "                if (";
  protected final String TEXT_258 = ") {";
  protected final String TEXT_259 = NL + "                } else {";
  protected final String TEXT_260 = NL + "                }" + NL + "            }";
  protected final String TEXT_261 = NL + "            if (";
  protected final String TEXT_262 = " > ";
  protected final String TEXT_263 = ") {";
  protected final String TEXT_264 = NL + "            } else if (";
  protected final String TEXT_265 = " < ";
  protected final String TEXT_266 = ") {";
  protected final String TEXT_267 = NL + "            }";
  protected final String TEXT_268 = NL + "            int comp_";
  protected final String TEXT_269 = " = ";
  protected final String TEXT_270 = ".compareTo(";
  protected final String TEXT_271 = ");" + NL + "            if (comp_";
  protected final String TEXT_272 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_273 = " > 0) {";
  protected final String TEXT_274 = NL + "                } else {";
  protected final String TEXT_275 = NL + "                }" + NL + "            }";
  protected final String TEXT_276 = NL + "            if (";
  protected final String TEXT_277 = " - ";
  protected final String TEXT_278 = " != 0) {" + NL + "                if (";
  protected final String TEXT_279 = " - ";
  protected final String TEXT_280 = " > 0) {";
  protected final String TEXT_281 = NL + "                } else {";
  protected final String TEXT_282 = NL + "                }" + NL + "            }";
  protected final String TEXT_283 = NL + "                int cmp_";
  protected final String TEXT_284 = " = FormatterUtils.format_DateInUTC(";
  protected final String TEXT_285 = ", ";
  protected final String TEXT_286 = ").compareTo(FormatterUtils.format_DateInUTC(";
  protected final String TEXT_287 = ", ";
  protected final String TEXT_288 = "));" + NL + "                if (cmp_";
  protected final String TEXT_289 = " > 0) {";
  protected final String TEXT_290 = NL + "                } else if (cmp_";
  protected final String TEXT_291 = " < 0) {";
  protected final String TEXT_292 = NL + "                }";
  protected final String TEXT_293 = NL + "                if (!";
  protected final String TEXT_294 = ".equals(";
  protected final String TEXT_295 = ")) {" + NL + "                    if (";
  protected final String TEXT_296 = ".compareTo(";
  protected final String TEXT_297 = ") > 0) {";
  protected final String TEXT_298 = NL + "                    } else {";
  protected final String TEXT_299 = NL + "                    }" + NL + "                }";
  protected final String TEXT_300 = NL + "                int cmp_";
  protected final String TEXT_301 = " = String.valueOf(";
  protected final String TEXT_302 = ").compareTo(String.valueOf(";
  protected final String TEXT_303 = "));" + NL + "                if (cmp_";
  protected final String TEXT_304 = " > 0) {";
  protected final String TEXT_305 = NL + "                } else if (cmp_";
  protected final String TEXT_306 = " < 0) {";
  protected final String TEXT_307 = NL + "                }";
  protected final String TEXT_308 = NL + "                if (";
  protected final String TEXT_309 = " > ";
  protected final String TEXT_310 = ") {";
  protected final String TEXT_311 = NL + "                } else if (";
  protected final String TEXT_312 = " < ";
  protected final String TEXT_313 = ") {";
  protected final String TEXT_314 = NL + "                }";
  protected final String TEXT_315 = NL + "                int cmp_";
  protected final String TEXT_316 = " = String.valueOf(";
  protected final String TEXT_317 = ").compareTo(String.valueOf(";
  protected final String TEXT_318 = "));" + NL + "                if (cmp_";
  protected final String TEXT_319 = " > 0) {";
  protected final String TEXT_320 = NL + "                } else if (cmp_";
  protected final String TEXT_321 = " < 0) {";
  protected final String TEXT_322 = NL + "                }";
  protected final String TEXT_323 = NL + "                if (";
  protected final String TEXT_324 = " > ";
  protected final String TEXT_325 = ") {";
  protected final String TEXT_326 = NL + "                } else if (";
  protected final String TEXT_327 = " < ";
  protected final String TEXT_328 = ") {";
  protected final String TEXT_329 = NL + "                }";
  protected final String TEXT_330 = NL + "                int cmp_";
  protected final String TEXT_331 = " = String.valueOf(";
  protected final String TEXT_332 = ").compareTo(String.valueOf(";
  protected final String TEXT_333 = "));";
  protected final String TEXT_334 = NL + "                int cmp_";
  protected final String TEXT_335 = " = ";
  protected final String TEXT_336 = ".compareTo(";
  protected final String TEXT_337 = ");";
  protected final String TEXT_338 = NL + "                if (cmp_";
  protected final String TEXT_339 = " > 0) {";
  protected final String TEXT_340 = NL + "                } else if (cmp_";
  protected final String TEXT_341 = " < 0) {";
  protected final String TEXT_342 = NL + "                }";
  protected final String TEXT_343 = NL + "                int cmp_";
  protected final String TEXT_344 = " = String.valueOf(";
  protected final String TEXT_345 = ").compareTo(String.valueOf(";
  protected final String TEXT_346 = "));" + NL + "                if (cmp_";
  protected final String TEXT_347 = " > 0) {";
  protected final String TEXT_348 = NL + "                } else if (cmp_";
  protected final String TEXT_349 = " < 0) {";
  protected final String TEXT_350 = NL + "                }";
  protected final String TEXT_351 = NL + "                if (";
  protected final String TEXT_352 = " > ";
  protected final String TEXT_353 = ") {";
  protected final String TEXT_354 = NL + "                } else if (";
  protected final String TEXT_355 = " < ";
  protected final String TEXT_356 = ") {";
  protected final String TEXT_357 = NL + "                }";
  protected final String TEXT_358 = NL + "                int cmp_";
  protected final String TEXT_359 = " = String.valueOf(";
  protected final String TEXT_360 = ").compareTo(String.valueOf(";
  protected final String TEXT_361 = "));" + NL + "                if (cmp_";
  protected final String TEXT_362 = " > 0) {";
  protected final String TEXT_363 = NL + "                } else if (cmp_";
  protected final String TEXT_364 = " < 0) {";
  protected final String TEXT_365 = NL + "                }";
  protected final String TEXT_366 = NL + "                if (";
  protected final String TEXT_367 = " > ";
  protected final String TEXT_368 = ") {";
  protected final String TEXT_369 = NL + "                } else if (";
  protected final String TEXT_370 = " < ";
  protected final String TEXT_371 = ") {";
  protected final String TEXT_372 = NL + "                }";
  protected final String TEXT_373 = NL + "                int cmp_";
  protected final String TEXT_374 = " = String.valueOf(";
  protected final String TEXT_375 = ").compareTo(String.valueOf(";
  protected final String TEXT_376 = "));" + NL + "                if (cmp_";
  protected final String TEXT_377 = " > 0) {";
  protected final String TEXT_378 = NL + "                } else if (cmp_";
  protected final String TEXT_379 = " < 0) {";
  protected final String TEXT_380 = NL + "                }";
  protected final String TEXT_381 = NL + "                if (";
  protected final String TEXT_382 = " > ";
  protected final String TEXT_383 = ") {";
  protected final String TEXT_384 = NL + "                } else if (";
  protected final String TEXT_385 = " < ";
  protected final String TEXT_386 = ") {";
  protected final String TEXT_387 = NL + "                }";
  protected final String TEXT_388 = NL + "            int comp_";
  protected final String TEXT_389 = " = ";
  protected final String TEXT_390 = ".compareTo(";
  protected final String TEXT_391 = ");" + NL + "            if (comp_";
  protected final String TEXT_392 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_393 = " > 0) {";
  protected final String TEXT_394 = NL + "                } else {";
  protected final String TEXT_395 = NL + "                }" + NL + "" + NL + "            }";
  protected final String TEXT_396 = NL + "            throw new JobConfigurationError(\"The ";
  protected final String TEXT_397 = " type is not supported: column--";
  protected final String TEXT_398 = "\");";
  protected final String TEXT_399 = NL + "            }";
  protected final String TEXT_400 = NL + "\t";
  protected final String TEXT_401 = NL;
  protected final String TEXT_402 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode((INode) ((BigDataCodeGeneratorArgument) argument).getArgument())) {

    stringBuffer.append(TEXT_1);
    
	/**
	 * Code generated for component with single output
 	 */
    class SOFunctionGenerator extends org.talend.designer.spark.generator.FunctionGenerator{

    	SOFunctionGenerator(org.talend.designer.common.TransformerBase transformer) {
            super(transformer);
        }

    	SOFunctionGenerator(org.talend.designer.common.TransformerBase transformer, org.talend.designer.spark.generator.SparkFunction sparkFunction) {
    		super(transformer, sparkFunction);
    	}

    	@Override
        public void generate(){
		
    stringBuffer.append(TEXT_2);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementation(getOutValueClass(), getInValueClass()));
    stringBuffer.append(TEXT_4);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_5);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedType(this.outValueClass.toString()));
    stringBuffer.append(TEXT_7);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(this.sparkFunction.getCodeKeyMapping(getInValue()));
    stringBuffer.append(TEXT_12);
    
	                this.transformer.generateTransformContextInitialization();
	                this.transformer.generateTransform(true);
	                
    stringBuffer.append(TEXT_13);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn(this.getOutValue(), this.getOutValueClass()));
    stringBuffer.append(TEXT_14);
    
        }
    }

	/**
	 * Code generated for component with multiple outputs
 	 */
    class MOFunctionGenerator extends org.talend.designer.spark.generator.FunctionGenerator{

        /** The single connection to pass along the output chain of Mappers
         *  instead of sending to a dedicated collector via MultipleOutputs. */
        String connectionToChain = null;

        MOFunctionGenerator(org.talend.designer.common.TransformerBase transformer) {
            super(transformer);
            defaultOutKeyClass = "Boolean";
        }

    	MOFunctionGenerator(org.talend.designer.common.TransformerBase transformer, org.talend.designer.spark.generator.SparkFunction sparkFunction) {
    		super(transformer, sparkFunction);
            defaultOutKeyClass = "Boolean";
    	}

    	@Override
        public void generate(){
        
    stringBuffer.append(TEXT_15);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementationOutputFixedType(getInValueClass(), "Boolean", "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_17);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_18);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedTypeFixedType((String)this.outKeyClass, "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    
                    this.transformer.generateTransformContextInitialization();
                    this.transformer.generateTransform(true);
	                if(this.sparkFunction.getCodeFunctionReturn()!=null) {
                
    stringBuffer.append(TEXT_24);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn());
    stringBuffer.append(TEXT_25);
    
	            	}
                
    stringBuffer.append(TEXT_26);
    
        }
    }

    stringBuffer.append(TEXT_27);
    

/**
 * A common, reusable utility that generates code in the context of a spark
 * component, for reading and writing to a spark RDD.
 */
class SparkRowTransformUtil extends org.talend.designer.common.CommonRowTransformUtil {

    private boolean isMultiOutput = false;

    private org.talend.designer.spark.generator.SparkFunction sparkFunction = null;
    

    private org.talend.designer.spark.generator.FunctionGenerator functionGenerator = null;

    public SparkRowTransformUtil() {

    }

    public SparkRowTransformUtil(org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        this.sparkFunction = sparkFunction;
    }

    public void setMultiOutput(boolean multiOutput) {
        isMultiOutput = multiOutput;
    }

    public String getCodeToGetInField(String columnName) {
        return functionGenerator.getInValue() + "." + columnName;
    }

    public String getInValue() {
        return functionGenerator.getInValue();
    }

    public String getOutValue() {
        return functionGenerator.getOutValue();
    }

    public String getInValueClass() {
        return functionGenerator.getInValueClass();
    }

    public String getOutValueClass() {
        return functionGenerator.getOutValueClass();
    }

    public String getCodeToGetOutField(boolean isReject, String columnName) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName;
    }

    public String getCodeToInitOut(boolean isReject, Iterable<IMetadataColumn> columns) {
        if(this.sparkFunction!=null && !isMultiOutput) {
            return this.sparkFunction.getCodeToInitOut(functionGenerator.getOutValue(isReject ? "reject" : "main"), functionGenerator.getOutValueClass(isReject ? "reject" : "main"));
        } else {
            return "";
        }
    }

    public String getCodeToSetOutField(boolean isReject, String columnName, String codeValue) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName + " = " + codeValue + ";";
    }

    public String getCodeToSetOutField(boolean isReject, String columnName, String codeValue, String operator) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName + " " + operator + " " + codeValue + ";";
    }

    public String getCodeToEmit(boolean isReject) {
        if (this.sparkFunction!=null && isMultiOutput) {
            if (isReject) {
                return this.sparkFunction.getCodeToEmit(false, functionGenerator.getOutValue("reject"), functionGenerator.getOutValueClass("reject"));
            } else {
                return this.sparkFunction.getCodeToEmit(true, functionGenerator.getOutValue("main"), functionGenerator.getOutValueClass("main"));
            }
        } else {
            return "";
        }
    }

    public void generateSparkCode(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        if (transformer.isMultiOutput()) {
            setMultiOutput(true);
        }
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
            return;
        }

        // Refactoring FunctionGenerator to java so we have to instaniate a MO or SO here
        functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

        functionGenerator.init(node, cid, null, transformer.getInConnName(), null, 
                transformer.getOutConnMainName() != null
                    ? transformer.getOutConnMainName()
                            : transformer.getOutConnRejectName());

        functionGenerator.generate();
    }

    public void generateSparkConfig(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction, String topX) {
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
            return;
        }

        functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

        functionGenerator.init(node, cid, null, transformer.getInConnName(), null, 
                transformer.getOutConnMainName() != null
                    ? transformer.getOutConnMainName()
                            : transformer.getOutConnRejectName());
        
    stringBuffer.append(TEXT_32);
    stringBuffer.append(sparkFunction.getConfigReturnedType(functionGenerator.getOutValueClass()));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(transformer.getInConnName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(sparkFunction.getConfigTransformation());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(topX);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_38);
    
    }
}


    

/**
 * Contains common processing for tSortRow code generation.
 */
class TSortRowUtil extends org.talend.designer.common.TransformerBase {

    /** The list of columns that should be copied directly from the input to
     *  the output schema (where they have the same column names). */
    final private Iterable<IMetadataColumn> copiedInColumns;

    /** When generating a comparator, the code accessor for the left side. */
    private final String codeVarData1;

    /** When generating a comparator, the code accessor for the right side. */
    private final String codeVarData2;

    java.util.List<java.util.Map<String, String>> criterias = (java.util.List<java.util.Map<String,String>>)ElementParameterParser.getObjectValue(node, "__CRITERIA__");
    java.util.List<String> listCols = new java.util.ArrayList<String>();

    /** Contains ONLY those columns that are ascending. */
    java.util.Set<String> criteriasAscendingColumns = new java.util.HashSet<String>();

    java.util.Map<String, Integer> criteriasSortType = new java.util.HashMap<String, Integer>();
    java.util.Map<String, Boolean> sortTypes = new java.util.HashMap<String, Boolean>();

    final java.util.Map<String, java.util.List<org.talend.core.model.metadata.IMetadataColumn>> keyList;

    final Integer SORT_NUM = 0;
    final Integer SORT_ALPHA = 1;
    final Integer SORT_DATE = 2;

    public TSortRowUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        this(node, argument, rowTransformUtil, "data1", "data2", false);
    }

    /**
     * @param invert if true, changes all ascending columns to descending
     *        columns and vice versa.
     */
    protected TSortRowUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil,
            String codeVarData1, String codeVarData2, boolean invert) {
        super(node, argument, rowTransformUtil, "FLOW", "REJECT");

        keyList = ((IBigDataNode) node).getKeyList();
        this.codeVarData1 = codeVarData1;
        this.codeVarData2 = codeVarData2;

        if (null != getInConn() && null != getOutConnMain()) {
            copiedInColumns = org.talend.designer.common.TransformerBaseUtil.getColumnsUnion(getInColumns(), getOutColumnsMain());
        } else {
            copiedInColumns = null;
        }

        for(int i = 0; i < criterias.size(); i++) {
            java.util.Map<String, String> line = criterias.get(i);
            String colname = line.get("COLNAME");
            if (listCols.contains(colname)) {
                continue;//skip dipulicate
            }
            listCols.add(colname);

            if ("asc".equals(line.get("ORDER")) == !invert) {
                criteriasAscendingColumns.add(colname);
            }

            if (("num").equals(line.get("SORT"))) {
                criteriasSortType.put(colname, SORT_NUM);
                sortTypes.put(colname, true);
            } else if (("alpha").equals(line.get("SORT"))) {
                sortTypes.put(colname, false);
            } else {
                criteriasSortType.put(colname, SORT_DATE);
                sortTypes.put(colname, true);
            }
        }
    }

    public void generateTransformContextDeclaration() {
        // Nothing here
    }

    /**
     * Generates code that performs the tranformation from one input string
     * to many output strings, or to a reject flow.
     */
    public void generateTransform() {
        generateTransform(true);
    }

    /**
     * Generates code that performs the tranformation from one input string
     * to many output strings, or to a reject flow. The boolean parameter is
     * used to define whether the transform method return type is void or something
     * else.
     */
    public void generateTransform(boolean hasAReturnedType) {
        int index = 0;
        for(String col:listCols) {
            for(IMetadataColumn column : copiedInColumns) {
                if (col.equals(column.getLabel())) {
                    generateComparatorSnippetForColumn(column, index,
                        org.talend.designer.spark.generator.utils.SparkFunctionUtil.getKeyValueAccessor(
                            keyList, "BOTH", codeVarData1, index),
                        org.talend.designer.spark.generator.utils.SparkFunctionUtil.getKeyValueAccessor(
                            keyList, "BOTH", codeVarData2, index));
                    index++;
                    break;
                }
            }
        }
    }

    /**
     * Generates code in the transform context to create reject output.
     *
     * @param die if this reject output should kill the job.  Normally, this is
     *    tied to a dieOnError parameter for the component, but can be
     *    explicitly set to false for non-fatal reject output.
     * @param codeException a variable in the transform scope that contains the
     *    variable with an exception.  If null, this will be constructed from
     *    the codeRejectMsg.
     * @param codeRejectMsg the error message to output with the reject output.
     */
    private void generateTransformReject(boolean die, String codeException, String codeRejectMsg) {

    }

    protected void greater(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_39);
    
        } else {
            
    stringBuffer.append(TEXT_40);
    
        }
    }

    protected void lesser(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_41);
    
        } else {
            
    stringBuffer.append(TEXT_42);
    
        }
    }

    private void generateComparatorSnippetForColumn(IMetadataColumn column,
                int i, String codeData1, String codeData2) {
        boolean nullable = !JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable());
        String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
        String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
        String columnName = column.getLabel();

        // For nullable columns, we always generate a comparison that sorts
        // nulls before non-null.
        if (nullable) {
            
    stringBuffer.append(TEXT_43);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_45);
    lesser(columnName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_48);
    greater(columnName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_51);
    
            // Note that the nullable else case is left open here and closed
            // at the end.
        }

        if (typeToGenerate.equalsIgnoreCase("Boolean")) {
            
    stringBuffer.append(TEXT_52);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_55);
    greater(columnName);
    stringBuffer.append(TEXT_56);
    lesser(columnName);
    stringBuffer.append(TEXT_57);
    
        } else if (typeToGenerate.equalsIgnoreCase("Byte")) {
            
    stringBuffer.append(TEXT_58);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_60);
    greater(columnName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_63);
    lesser(columnName);
    stringBuffer.append(TEXT_64);
    
        } else if (typeToGenerate.equals("byte[]")) {
            
    stringBuffer.append(TEXT_65);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_70);
    greater(columnName);
    stringBuffer.append(TEXT_71);
    lesser(columnName);
    stringBuffer.append(TEXT_72);
    
        } else if (typeToGenerate.equalsIgnoreCase("Char") || typeToGenerate.equalsIgnoreCase("Character")) {
            
    stringBuffer.append(TEXT_73);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_77);
    greater(columnName);
    stringBuffer.append(TEXT_78);
    lesser(columnName);
    stringBuffer.append(TEXT_79);
    
        } else if (typeToGenerate.equals("java.util.Date")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_80);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_86);
    greater(columnName);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_88);
    lesser(columnName);
    stringBuffer.append(TEXT_89);
    
            } else {
                
    stringBuffer.append(TEXT_90);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_94);
    greater(columnName);
    stringBuffer.append(TEXT_95);
    lesser(columnName);
    stringBuffer.append(TEXT_96);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Double")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_97);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_101);
    greater(columnName);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_103);
    lesser(columnName);
    stringBuffer.append(TEXT_104);
    
            } else {
                
    stringBuffer.append(TEXT_105);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_107);
    greater(columnName);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_110);
    lesser(columnName);
    stringBuffer.append(TEXT_111);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Float")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_112);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_116);
    greater(columnName);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_118);
    lesser(columnName);
    stringBuffer.append(TEXT_119);
    
            } else {
                
    stringBuffer.append(TEXT_120);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_122);
    greater(columnName);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_125);
    lesser(columnName);
    stringBuffer.append(TEXT_126);
    
            }
        } else if (typeToGenerate.equals("BigDecimal")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_127);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_130);
    
            } else {
                
    stringBuffer.append(TEXT_131);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_134);
    }
    stringBuffer.append(TEXT_135);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_136);
    greater(columnName);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_138);
    lesser(columnName);
    stringBuffer.append(TEXT_139);
    
        } else if (typeToGenerate.equalsIgnoreCase("Integer") || typeToGenerate.equalsIgnoreCase("int")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_140);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_144);
    greater(columnName);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_146);
    lesser(columnName);
    stringBuffer.append(TEXT_147);
    
            } else {
                
    stringBuffer.append(TEXT_148);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_150);
    greater(columnName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_153);
    lesser(columnName);
    stringBuffer.append(TEXT_154);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Long")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_155);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_159);
    greater(columnName);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_161);
    lesser(columnName);
    stringBuffer.append(TEXT_162);
    
            } else {
                
    stringBuffer.append(TEXT_163);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_165);
    greater(columnName);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_168);
    lesser(columnName);
    stringBuffer.append(TEXT_169);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Short")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_170);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_174);
    greater(columnName);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_176);
    lesser(columnName);
    stringBuffer.append(TEXT_177);
    
            } else {
                
    stringBuffer.append(TEXT_178);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_180);
    greater(columnName);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_183);
    lesser(columnName);
    stringBuffer.append(TEXT_184);
    
            }
        } else if (typeToGenerate.equals("String")) {
            
    stringBuffer.append(TEXT_185);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_190);
    greater(columnName);
    stringBuffer.append(TEXT_191);
    lesser(columnName);
    stringBuffer.append(TEXT_192);
    
        } else if (typeToGenerate.equals("Object")
                || typeToGenerate.equals("List")
                || typeToGenerate.equals("Document")
                || typeToGenerate.equals("Dynamic")) {
            
    stringBuffer.append(TEXT_193);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_194);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_195);
    
        }

        // Close the open else statement for nullable columns.
        if (nullable) {
            
    stringBuffer.append(TEXT_196);
    
        }
    }
}

    stringBuffer.append(TEXT_197);
    

/**
 * Contains common processing for tTop code generation.
 */
class TTopUtil extends TSortRowUtil {

    public TTopUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        super(node, argument, rowTransformUtil, "data1._1", "data2._1", true);
    }
}

    
	final SparkRowTransformUtil sparkTransformUtil = new SparkRowTransformUtil();
	final TTopUtil tTopUtil = new TTopUtil(node, codeGenArgument, sparkTransformUtil);

	java.util.Map<String, java.util.List<org.talend.core.model.metadata.IMetadataColumn>> keyList = bigDataNode.getKeyList();
	org.talend.designer.spark.generator.SparkFunction sparkFunction = new org.talend.designer.spark.generator.TopFunction(false, keyList);

	String topX = ElementParameterParser.getValue(node, "__TOP__");

	sparkTransformUtil.generateSparkConfig(tTopUtil, sparkFunction, topX);
} else {

    stringBuffer.append(TEXT_198);
    
	/**
	 * Code generated for component with single output
 	 */
    class SOFunctionGenerator extends org.talend.designer.spark.generator.FunctionGenerator{

    	SOFunctionGenerator(org.talend.designer.common.TransformerBase transformer) {
            super(transformer);
        }

    	SOFunctionGenerator(org.talend.designer.common.TransformerBase transformer, org.talend.designer.spark.generator.SparkFunction sparkFunction) {
    		super(transformer, sparkFunction);
    	}

    	@Override
        public void generate(){
		
    stringBuffer.append(TEXT_199);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_200);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementation(getOutValueClass(), getInValueClass()));
    stringBuffer.append(TEXT_201);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_202);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_203);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedType(this.outValueClass.toString()));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_206);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    stringBuffer.append(TEXT_208);
    stringBuffer.append(this.sparkFunction.getCodeKeyMapping(getInValue()));
    stringBuffer.append(TEXT_209);
    
	                this.transformer.generateTransformContextInitialization();
	                this.transformer.generateTransform(true);
	                
    stringBuffer.append(TEXT_210);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn(this.getOutValue(), this.getOutValueClass()));
    stringBuffer.append(TEXT_211);
    
        }
    }

	/**
	 * Code generated for component with multiple outputs
 	 */
    class MOFunctionGenerator extends org.talend.designer.spark.generator.FunctionGenerator{

        /** The single connection to pass along the output chain of Mappers
         *  instead of sending to a dedicated collector via MultipleOutputs. */
        String connectionToChain = null;

        MOFunctionGenerator(org.talend.designer.common.TransformerBase transformer) {
            super(transformer);
            defaultOutKeyClass = "Boolean";
        }

    	MOFunctionGenerator(org.talend.designer.common.TransformerBase transformer, org.talend.designer.spark.generator.SparkFunction sparkFunction) {
    		super(transformer, sparkFunction);
            defaultOutKeyClass = "Boolean";
    	}

    	@Override
        public void generate(){
        
    stringBuffer.append(TEXT_212);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_213);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementationOutputFixedType(getInValueClass(), "Boolean", "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_214);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_215);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_216);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedTypeFixedType((String)this.outKeyClass, "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_217);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_219);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    
                    this.transformer.generateTransformContextInitialization();
                    this.transformer.generateTransform(true);
	                if(this.sparkFunction.getCodeFunctionReturn()!=null) {
                
    stringBuffer.append(TEXT_221);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn());
    stringBuffer.append(TEXT_222);
    
	            	}
                
    stringBuffer.append(TEXT_223);
    
        }
    }

    stringBuffer.append(TEXT_224);
    

/**
 * A common, reusable utility that generates code in the context of a spark
 * component, for reading and writing to a spark RDD.
 */
class SparkRowTransformUtil extends org.talend.designer.common.CommonRowTransformUtil {

    private boolean isMultiOutput = false;

    private org.talend.designer.spark.generator.SparkFunction sparkFunction = null;
    

    private org.talend.designer.spark.generator.FunctionGenerator functionGenerator = null;

    public SparkRowTransformUtil() {

    }

    public SparkRowTransformUtil(org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        this.sparkFunction = sparkFunction;
    }

    public void setMultiOutput(boolean multiOutput) {
        isMultiOutput = multiOutput;
    }

    public String getCodeToGetInField(String columnName) {
        return functionGenerator.getInValue() + "." + columnName;
    }

    public String getInValue() {
        return functionGenerator.getInValue();
    }

    public String getOutValue() {
        return functionGenerator.getOutValue();
    }

    public String getInValueClass() {
        return functionGenerator.getInValueClass();
    }

    public String getOutValueClass() {
        return functionGenerator.getOutValueClass();
    }

    public String getCodeToGetOutField(boolean isReject, String columnName) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName;
    }

    public String getCodeToInitOut(boolean isReject, Iterable<IMetadataColumn> columns) {
        if(this.sparkFunction!=null && !isMultiOutput) {
            return this.sparkFunction.getCodeToInitOut(functionGenerator.getOutValue(isReject ? "reject" : "main"), functionGenerator.getOutValueClass(isReject ? "reject" : "main"));
        } else {
            return "";
        }
    }

    public String getCodeToSetOutField(boolean isReject, String columnName, String codeValue) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName + " = " + codeValue + ";";
    }

    public String getCodeToSetOutField(boolean isReject, String columnName, String codeValue, String operator) {
        return functionGenerator.getOutValue(isReject ? "reject" : "main") + "." + columnName + " " + operator + " " + codeValue + ";";
    }

    public String getCodeToEmit(boolean isReject) {
        if (this.sparkFunction!=null && isMultiOutput) {
            if (isReject) {
                return this.sparkFunction.getCodeToEmit(false, functionGenerator.getOutValue("reject"), functionGenerator.getOutValueClass("reject"));
            } else {
                return this.sparkFunction.getCodeToEmit(true, functionGenerator.getOutValue("main"), functionGenerator.getOutValueClass("main"));
            }
        } else {
            return "";
        }
    }

    public void generateSparkCode(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        if (transformer.isMultiOutput()) {
            setMultiOutput(true);
        }
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    
            return;
        }


        // Refactoring FunctionGenerator to java so we have to instaniate a MO or SO here
        functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

        functionGenerator.init(node, cid, null, transformer.getInConnName(), null, 
                transformer.getOutConnMainName() != null
                    ? transformer.getOutConnMainName()
                            : transformer.getOutConnRejectName());

        functionGenerator.generate();


        String internalType = ((org.talend.designer.spark.streaming.generator.TopStreamingFunction)sparkFunction).getConfigInternalType(functionGenerator.getOutValueClass());
        
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(internalType);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(internalType);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(internalType);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(internalType);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(sparkFunction.getConfigTransformation());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_234);
    
    }

    public void generateSparkConfig(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction, String topX) {
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    
            return;
        }

        functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

        functionGenerator.init(node, cid, null, transformer.getInConnName(), null, 
                transformer.getOutConnMainName() != null
                    ? transformer.getOutConnMainName()
                            : transformer.getOutConnRejectName());
            String returnedType = sparkFunction.getConfigReturnedType(functionGenerator.getOutValueClass());
            
    stringBuffer.append(TEXT_236);
    stringBuffer.append(returnedType);
    stringBuffer.append(TEXT_237);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(transformer.getInConnName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(topX);
    stringBuffer.append(TEXT_241);
    
    }
}


    

/**
 * Contains common processing for tSortRow code generation.
 */
class TSortRowUtil extends org.talend.designer.common.TransformerBase {

    /** The list of columns that should be copied directly from the input to
     *  the output schema (where they have the same column names). */
    final private Iterable<IMetadataColumn> copiedInColumns;

    /** When generating a comparator, the code accessor for the left side. */
    private final String codeVarData1;

    /** When generating a comparator, the code accessor for the right side. */
    private final String codeVarData2;

    java.util.List<java.util.Map<String, String>> criterias = (java.util.List<java.util.Map<String,String>>)ElementParameterParser.getObjectValue(node, "__CRITERIA__");
    java.util.List<String> listCols = new java.util.ArrayList<String>();

    /** Contains ONLY those columns that are ascending. */
    java.util.Set<String> criteriasAscendingColumns = new java.util.HashSet<String>();

    java.util.Map<String, Integer> criteriasSortType = new java.util.HashMap<String, Integer>();
    java.util.Map<String, Boolean> sortTypes = new java.util.HashMap<String, Boolean>();

    final java.util.Map<String, java.util.List<org.talend.core.model.metadata.IMetadataColumn>> keyList;

    final Integer SORT_NUM = 0;
    final Integer SORT_ALPHA = 1;
    final Integer SORT_DATE = 2;

    public TSortRowUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        this(node, argument, rowTransformUtil, "data1", "data2", false);
    }

    /**
     * @param invert if true, changes all ascending columns to descending
     *        columns and vice versa.
     */
    protected TSortRowUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil,
            String codeVarData1, String codeVarData2, boolean invert) {
        super(node, argument, rowTransformUtil, "FLOW", "REJECT");

        keyList = ((IBigDataNode) node).getKeyList();
        this.codeVarData1 = codeVarData1;
        this.codeVarData2 = codeVarData2;

        if (null != getInConn() && null != getOutConnMain()) {
            copiedInColumns = org.talend.designer.common.TransformerBaseUtil.getColumnsUnion(getInColumns(), getOutColumnsMain());
        } else {
            copiedInColumns = null;
        }

        for(int i = 0; i < criterias.size(); i++) {
            java.util.Map<String, String> line = criterias.get(i);
            String colname = line.get("COLNAME");
            if (listCols.contains(colname)) {
                continue;//skip dipulicate
            }
            listCols.add(colname);

            if ("asc".equals(line.get("ORDER")) == !invert) {
                criteriasAscendingColumns.add(colname);
            }

            if (("num").equals(line.get("SORT"))) {
                criteriasSortType.put(colname, SORT_NUM);
                sortTypes.put(colname, true);
            } else if (("alpha").equals(line.get("SORT"))) {
                sortTypes.put(colname, false);
            } else {
                criteriasSortType.put(colname, SORT_DATE);
                sortTypes.put(colname, true);
            }
        }
    }

    public void generateTransformContextDeclaration() {
        // Nothing here
    }

    /**
     * Generates code that performs the tranformation from one input string
     * to many output strings, or to a reject flow.
     */
    public void generateTransform() {
        generateTransform(true);
    }

    /**
     * Generates code that performs the tranformation from one input string
     * to many output strings, or to a reject flow. The boolean parameter is
     * used to define whether the transform method return type is void or something
     * else.
     */
    public void generateTransform(boolean hasAReturnedType) {
        int index = 0;
        for(String col:listCols) {
            for(IMetadataColumn column : copiedInColumns) {
                if (col.equals(column.getLabel())) {
                    generateComparatorSnippetForColumn(column, index,
                        org.talend.designer.spark.generator.utils.SparkFunctionUtil.getKeyValueAccessor(
                            keyList, "BOTH", codeVarData1, index),
                        org.talend.designer.spark.generator.utils.SparkFunctionUtil.getKeyValueAccessor(
                            keyList, "BOTH", codeVarData2, index));
                    index++;
                    break;
                }
            }
        }
    }

    /**
     * Generates code in the transform context to create reject output.
     *
     * @param die if this reject output should kill the job.  Normally, this is
     *    tied to a dieOnError parameter for the component, but can be
     *    explicitly set to false for non-fatal reject output.
     * @param codeException a variable in the transform scope that contains the
     *    variable with an exception.  If null, this will be constructed from
     *    the codeRejectMsg.
     * @param codeRejectMsg the error message to output with the reject output.
     */
    private void generateTransformReject(boolean die, String codeException, String codeRejectMsg) {

    }

    protected void greater(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_242);
    
        } else {
            
    stringBuffer.append(TEXT_243);
    
        }
    }

    protected void lesser(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_244);
    
        } else {
            
    stringBuffer.append(TEXT_245);
    
        }
    }

    private void generateComparatorSnippetForColumn(IMetadataColumn column,
                int i, String codeData1, String codeData2) {
        boolean nullable = !JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable());
        String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
        String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
        String columnName = column.getLabel();

        // For nullable columns, we always generate a comparison that sorts
        // nulls before non-null.
        if (nullable) {
            
    stringBuffer.append(TEXT_246);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_248);
    lesser(columnName);
    stringBuffer.append(TEXT_249);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_251);
    greater(columnName);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_254);
    
            // Note that the nullable else case is left open here and closed
            // at the end.
        }

        if (typeToGenerate.equalsIgnoreCase("Boolean")) {
            
    stringBuffer.append(TEXT_255);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_258);
    greater(columnName);
    stringBuffer.append(TEXT_259);
    lesser(columnName);
    stringBuffer.append(TEXT_260);
    
        } else if (typeToGenerate.equalsIgnoreCase("Byte")) {
            
    stringBuffer.append(TEXT_261);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_263);
    greater(columnName);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_266);
    lesser(columnName);
    stringBuffer.append(TEXT_267);
    
        } else if (typeToGenerate.equals("byte[]")) {
            
    stringBuffer.append(TEXT_268);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_273);
    greater(columnName);
    stringBuffer.append(TEXT_274);
    lesser(columnName);
    stringBuffer.append(TEXT_275);
    
        } else if (typeToGenerate.equalsIgnoreCase("Char") || typeToGenerate.equalsIgnoreCase("Character")) {
            
    stringBuffer.append(TEXT_276);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_279);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_280);
    greater(columnName);
    stringBuffer.append(TEXT_281);
    lesser(columnName);
    stringBuffer.append(TEXT_282);
    
        } else if (typeToGenerate.equals("java.util.Date")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_283);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_287);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_289);
    greater(columnName);
    stringBuffer.append(TEXT_290);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_291);
    lesser(columnName);
    stringBuffer.append(TEXT_292);
    
            } else {
                
    stringBuffer.append(TEXT_293);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_294);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_297);
    greater(columnName);
    stringBuffer.append(TEXT_298);
    lesser(columnName);
    stringBuffer.append(TEXT_299);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Double")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_300);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_304);
    greater(columnName);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_306);
    lesser(columnName);
    stringBuffer.append(TEXT_307);
    
            } else {
                
    stringBuffer.append(TEXT_308);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_309);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_310);
    greater(columnName);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_313);
    lesser(columnName);
    stringBuffer.append(TEXT_314);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Float")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_315);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_318);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_319);
    greater(columnName);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_321);
    lesser(columnName);
    stringBuffer.append(TEXT_322);
    
            } else {
                
    stringBuffer.append(TEXT_323);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_325);
    greater(columnName);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_327);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_328);
    lesser(columnName);
    stringBuffer.append(TEXT_329);
    
            }
        } else if (typeToGenerate.equals("BigDecimal")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_330);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_331);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_333);
    
            } else {
                
    stringBuffer.append(TEXT_334);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_335);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_336);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_337);
    }
    stringBuffer.append(TEXT_338);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_339);
    greater(columnName);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_341);
    lesser(columnName);
    stringBuffer.append(TEXT_342);
    
        } else if (typeToGenerate.equalsIgnoreCase("Integer") || typeToGenerate.equalsIgnoreCase("int")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_343);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_347);
    greater(columnName);
    stringBuffer.append(TEXT_348);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_349);
    lesser(columnName);
    stringBuffer.append(TEXT_350);
    
            } else {
                
    stringBuffer.append(TEXT_351);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_353);
    greater(columnName);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_356);
    lesser(columnName);
    stringBuffer.append(TEXT_357);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Long")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_358);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_360);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_361);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_362);
    greater(columnName);
    stringBuffer.append(TEXT_363);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_364);
    lesser(columnName);
    stringBuffer.append(TEXT_365);
    
            } else {
                
    stringBuffer.append(TEXT_366);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_368);
    greater(columnName);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_371);
    lesser(columnName);
    stringBuffer.append(TEXT_372);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Short")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_373);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_375);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_376);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_377);
    greater(columnName);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_379);
    lesser(columnName);
    stringBuffer.append(TEXT_380);
    
            } else {
                
    stringBuffer.append(TEXT_381);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_383);
    greater(columnName);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_386);
    lesser(columnName);
    stringBuffer.append(TEXT_387);
    
            }
        } else if (typeToGenerate.equals("String")) {
            
    stringBuffer.append(TEXT_388);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_393);
    greater(columnName);
    stringBuffer.append(TEXT_394);
    lesser(columnName);
    stringBuffer.append(TEXT_395);
    
        } else if (typeToGenerate.equals("Object")
                || typeToGenerate.equals("List")
                || typeToGenerate.equals("Document")
                || typeToGenerate.equals("Dynamic")) {
            
    stringBuffer.append(TEXT_396);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_398);
    
        }

        // Close the open else statement for nullable columns.
        if (nullable) {
            
    stringBuffer.append(TEXT_399);
    
        }
    }
}

    stringBuffer.append(TEXT_400);
    

/**
 * Contains common processing for tTop code generation.
 */
class TTopUtil extends TSortRowUtil {

    public TTopUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        super(node, argument, rowTransformUtil, "data1._1", "data2._1", true);
    }
}

    
	final SparkRowTransformUtil sparkTransformUtil = new SparkRowTransformUtil();
	final TTopUtil tTopUtil = new TTopUtil(node, codeGenArgument, sparkTransformUtil);

	java.util.Map<String, java.util.List<org.talend.core.model.metadata.IMetadataColumn>> keyList = bigDataNode.getKeyList();
	org.talend.designer.spark.generator.SparkFunction sparkFunction = new org.talend.designer.spark.streaming.generator.TopStreamingFunction(false, keyList);

	String topX = ElementParameterParser.getValue(node, "__TOP__");

	sparkTransformUtil.generateSparkConfig(tTopUtil, sparkFunction, topX);
}

    stringBuffer.append(TEXT_401);
    stringBuffer.append(TEXT_402);
    return stringBuffer.toString();
  }
}
