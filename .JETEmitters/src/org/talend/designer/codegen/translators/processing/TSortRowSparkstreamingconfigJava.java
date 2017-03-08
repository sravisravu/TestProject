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
import org.talend.designer.spark.generator.utils.SparkFunctionUtil;

public class TSortRowSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TSortRowSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TSortRowSparkstreamingconfigJava result = new TSortRowSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "            public static class ";
  protected final String TEXT_2 = " implements ";
  protected final String TEXT_3 = " {";
  protected final String TEXT_4 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_5 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "\t            public ";
  protected final String TEXT_6 = " ";
  protected final String TEXT_7 = "(";
  protected final String TEXT_8 = ") ";
  protected final String TEXT_9 = " {" + NL + "\t            \t";
  protected final String TEXT_10 = NL + "\t            \t";
  protected final String TEXT_11 = NL + "\t                ";
  protected final String TEXT_12 = NL + "\t                return ";
  protected final String TEXT_13 = ";" + NL + "\t            }" + NL + "\t        }" + NL + "\t\t";
  protected final String TEXT_14 = NL + "            public static class ";
  protected final String TEXT_15 = " implements ";
  protected final String TEXT_16 = " {";
  protected final String TEXT_17 = NL + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_18 = "(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "                public ";
  protected final String TEXT_19 = " ";
  protected final String TEXT_20 = "(";
  protected final String TEXT_21 = ") ";
  protected final String TEXT_22 = " {" + NL + "                \t";
  protected final String TEXT_23 = NL + "\t                 \treturn ";
  protected final String TEXT_24 = ";";
  protected final String TEXT_25 = NL + "                }" + NL + "            }";
  protected final String TEXT_26 = NL;
  protected final String TEXT_27 = NL + "            // No sparkcode generated for unnecessary ";
  protected final String TEXT_28 = NL;
  protected final String TEXT_29 = NL + "            public static class ";
  protected final String TEXT_30 = "TrueFilter implements org.apache.spark.api.java.function.Function<scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase>, Boolean> {" + NL + "" + NL + "                public Boolean call(scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase> arg0)" + NL + "                        throws Exception {" + NL + "                    return arg0._1;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            public static class ";
  protected final String TEXT_31 = "FalseFilter implements org.apache.spark.api.java.function.Function<scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase>, Boolean> {" + NL + "" + NL + "                public Boolean call(scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase> arg0)" + NL + "                        throws Exception {" + NL + "                    return !arg0._1;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            public static class ";
  protected final String TEXT_32 = "ToNullWritableMain implements ";
  protected final String TEXT_33 = " {" + NL + "" + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_34 = "ToNullWritableMain(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "                public ";
  protected final String TEXT_35 = " call(" + NL + "                        scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase> data){";
  protected final String TEXT_36 = NL + "                    ";
  protected final String TEXT_37 = " ";
  protected final String TEXT_38 = " = (";
  protected final String TEXT_39 = ")data._2;" + NL + "                    return ";
  protected final String TEXT_40 = ";" + NL + "                }" + NL + "            }" + NL + "" + NL + "            public static class ";
  protected final String TEXT_41 = "ToNullWritableReject implements ";
  protected final String TEXT_42 = " {" + NL + "" + NL + "                private ContextProperties context = null;" + NL + "" + NL + "                public ";
  protected final String TEXT_43 = "ToNullWritableReject(JobConf job) {" + NL + "                    this.context = new ContextProperties(job);" + NL + "                }" + NL + "" + NL + "                public ";
  protected final String TEXT_44 = " call(" + NL + "                        scala.Tuple2<Boolean, org.apache.avro.specific.SpecificRecordBase> data){";
  protected final String TEXT_45 = NL + "                        ";
  protected final String TEXT_46 = " ";
  protected final String TEXT_47 = " = (";
  protected final String TEXT_48 = ")data._2;" + NL + "                    return ";
  protected final String TEXT_49 = ";" + NL + "                }" + NL + "            }";
  protected final String TEXT_50 = NL + "            // No sparkconfig generated for unnecessary ";
  protected final String TEXT_51 = NL;
  protected final String TEXT_52 = NL + "            // Extract data." + NL;
  protected final String TEXT_53 = NL + "            ";
  protected final String TEXT_54 = "<Boolean, org.apache.avro.specific.SpecificRecordBase> temporary_rdd_";
  protected final String TEXT_55 = " = rdd_";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = "(new ";
  protected final String TEXT_58 = "(job));" + NL + "" + NL + "            // Main flow" + NL;
  protected final String TEXT_59 = NL + "            ";
  protected final String TEXT_60 = " rdd_";
  protected final String TEXT_61 = " = temporary_rdd_";
  protected final String TEXT_62 = NL + "                  .filter(new ";
  protected final String TEXT_63 = "TrueFilter())" + NL + "                    .";
  protected final String TEXT_64 = "(new ";
  protected final String TEXT_65 = "ToNullWritableMain(job));" + NL + "" + NL + "            // Reject flow";
  protected final String TEXT_66 = NL + "            ";
  protected final String TEXT_67 = " rdd_";
  protected final String TEXT_68 = " = temporary_rdd_";
  protected final String TEXT_69 = NL + "                    .filter(new ";
  protected final String TEXT_70 = "FalseFilter())" + NL + "                    .";
  protected final String TEXT_71 = "(new ";
  protected final String TEXT_72 = "ToNullWritableReject(job));";
  protected final String TEXT_73 = NL + "            ";
  protected final String TEXT_74 = " rdd_";
  protected final String TEXT_75 = " = rdd_";
  protected final String TEXT_76 = ".";
  protected final String TEXT_77 = "(new ";
  protected final String TEXT_78 = "(job));";
  protected final String TEXT_79 = NL + "            return 1;";
  protected final String TEXT_80 = NL + "            return -1;";
  protected final String TEXT_81 = NL + "            return -1;";
  protected final String TEXT_82 = NL + "            return 1;";
  protected final String TEXT_83 = NL + "            if (";
  protected final String TEXT_84 = " == null && ";
  protected final String TEXT_85 = " != null) {";
  protected final String TEXT_86 = NL + "            } else if (";
  protected final String TEXT_87 = " != null && ";
  protected final String TEXT_88 = " == null) {";
  protected final String TEXT_89 = NL + "            } else if (";
  protected final String TEXT_90 = " == null && ";
  protected final String TEXT_91 = " == null) {" + NL + "                //ignore" + NL + "            } else {";
  protected final String TEXT_92 = NL + "            if (";
  protected final String TEXT_93 = " != ";
  protected final String TEXT_94 = ") {" + NL + "                if (";
  protected final String TEXT_95 = ") {";
  protected final String TEXT_96 = NL + "                } else {";
  protected final String TEXT_97 = NL + "                }" + NL + "            }";
  protected final String TEXT_98 = NL + "            if (";
  protected final String TEXT_99 = " > ";
  protected final String TEXT_100 = ") {";
  protected final String TEXT_101 = NL + "            } else if (";
  protected final String TEXT_102 = " < ";
  protected final String TEXT_103 = ") {";
  protected final String TEXT_104 = NL + "            }";
  protected final String TEXT_105 = NL + "            int comp_";
  protected final String TEXT_106 = " = ";
  protected final String TEXT_107 = ".compareTo(";
  protected final String TEXT_108 = ");" + NL + "            if (comp_";
  protected final String TEXT_109 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_110 = " > 0) {";
  protected final String TEXT_111 = NL + "                } else {";
  protected final String TEXT_112 = NL + "                }" + NL + "            }";
  protected final String TEXT_113 = NL + "            if (";
  protected final String TEXT_114 = " - ";
  protected final String TEXT_115 = " != 0) {" + NL + "                if (";
  protected final String TEXT_116 = " - ";
  protected final String TEXT_117 = " > 0) {";
  protected final String TEXT_118 = NL + "                } else {";
  protected final String TEXT_119 = NL + "                }" + NL + "            }";
  protected final String TEXT_120 = NL + "                int cmp_";
  protected final String TEXT_121 = " = FormatterUtils.format_DateInUTC(";
  protected final String TEXT_122 = ", ";
  protected final String TEXT_123 = ").compareTo(FormatterUtils.format_DateInUTC(";
  protected final String TEXT_124 = ", ";
  protected final String TEXT_125 = "));" + NL + "                if (cmp_";
  protected final String TEXT_126 = " > 0) {";
  protected final String TEXT_127 = NL + "                } else if (cmp_";
  protected final String TEXT_128 = " < 0) {";
  protected final String TEXT_129 = NL + "                }";
  protected final String TEXT_130 = NL + "                if (!";
  protected final String TEXT_131 = ".equals(";
  protected final String TEXT_132 = ")) {" + NL + "                    if (";
  protected final String TEXT_133 = ".compareTo(";
  protected final String TEXT_134 = ") > 0) {";
  protected final String TEXT_135 = NL + "                    } else {";
  protected final String TEXT_136 = NL + "                    }" + NL + "                }";
  protected final String TEXT_137 = NL + "                int cmp_";
  protected final String TEXT_138 = " = String.valueOf(";
  protected final String TEXT_139 = ").compareTo(String.valueOf(";
  protected final String TEXT_140 = "));" + NL + "                if (cmp_";
  protected final String TEXT_141 = " > 0) {";
  protected final String TEXT_142 = NL + "                } else if (cmp_";
  protected final String TEXT_143 = " < 0) {";
  protected final String TEXT_144 = NL + "                }";
  protected final String TEXT_145 = NL + "                if (";
  protected final String TEXT_146 = " > ";
  protected final String TEXT_147 = ") {";
  protected final String TEXT_148 = NL + "                } else if (";
  protected final String TEXT_149 = " < ";
  protected final String TEXT_150 = ") {";
  protected final String TEXT_151 = NL + "                }";
  protected final String TEXT_152 = NL + "                int cmp_";
  protected final String TEXT_153 = " = String.valueOf(";
  protected final String TEXT_154 = ").compareTo(String.valueOf(";
  protected final String TEXT_155 = "));" + NL + "                if (cmp_";
  protected final String TEXT_156 = " > 0) {";
  protected final String TEXT_157 = NL + "                } else if (cmp_";
  protected final String TEXT_158 = " < 0) {";
  protected final String TEXT_159 = NL + "                }";
  protected final String TEXT_160 = NL + "                if (";
  protected final String TEXT_161 = " > ";
  protected final String TEXT_162 = ") {";
  protected final String TEXT_163 = NL + "                } else if (";
  protected final String TEXT_164 = " < ";
  protected final String TEXT_165 = ") {";
  protected final String TEXT_166 = NL + "                }";
  protected final String TEXT_167 = NL + "                int cmp_";
  protected final String TEXT_168 = " = String.valueOf(";
  protected final String TEXT_169 = ").compareTo(String.valueOf(";
  protected final String TEXT_170 = "));";
  protected final String TEXT_171 = NL + "                int cmp_";
  protected final String TEXT_172 = " = ";
  protected final String TEXT_173 = ".compareTo(";
  protected final String TEXT_174 = ");";
  protected final String TEXT_175 = NL + "                if (cmp_";
  protected final String TEXT_176 = " > 0) {";
  protected final String TEXT_177 = NL + "                } else if (cmp_";
  protected final String TEXT_178 = " < 0) {";
  protected final String TEXT_179 = NL + "                }";
  protected final String TEXT_180 = NL + "                int cmp_";
  protected final String TEXT_181 = " = String.valueOf(";
  protected final String TEXT_182 = ").compareTo(String.valueOf(";
  protected final String TEXT_183 = "));" + NL + "                if (cmp_";
  protected final String TEXT_184 = " > 0) {";
  protected final String TEXT_185 = NL + "                } else if (cmp_";
  protected final String TEXT_186 = " < 0) {";
  protected final String TEXT_187 = NL + "                }";
  protected final String TEXT_188 = NL + "                if (";
  protected final String TEXT_189 = " > ";
  protected final String TEXT_190 = ") {";
  protected final String TEXT_191 = NL + "                } else if (";
  protected final String TEXT_192 = " < ";
  protected final String TEXT_193 = ") {";
  protected final String TEXT_194 = NL + "                }";
  protected final String TEXT_195 = NL + "                int cmp_";
  protected final String TEXT_196 = " = String.valueOf(";
  protected final String TEXT_197 = ").compareTo(String.valueOf(";
  protected final String TEXT_198 = "));" + NL + "                if (cmp_";
  protected final String TEXT_199 = " > 0) {";
  protected final String TEXT_200 = NL + "                } else if (cmp_";
  protected final String TEXT_201 = " < 0) {";
  protected final String TEXT_202 = NL + "                }";
  protected final String TEXT_203 = NL + "                if (";
  protected final String TEXT_204 = " > ";
  protected final String TEXT_205 = ") {";
  protected final String TEXT_206 = NL + "                } else if (";
  protected final String TEXT_207 = " < ";
  protected final String TEXT_208 = ") {";
  protected final String TEXT_209 = NL + "                }";
  protected final String TEXT_210 = NL + "                int cmp_";
  protected final String TEXT_211 = " = String.valueOf(";
  protected final String TEXT_212 = ").compareTo(String.valueOf(";
  protected final String TEXT_213 = "));" + NL + "                if (cmp_";
  protected final String TEXT_214 = " > 0) {";
  protected final String TEXT_215 = NL + "                } else if (cmp_";
  protected final String TEXT_216 = " < 0) {";
  protected final String TEXT_217 = NL + "                }";
  protected final String TEXT_218 = NL + "                if (";
  protected final String TEXT_219 = " > ";
  protected final String TEXT_220 = ") {";
  protected final String TEXT_221 = NL + "                } else if (";
  protected final String TEXT_222 = " < ";
  protected final String TEXT_223 = ") {";
  protected final String TEXT_224 = NL + "                }";
  protected final String TEXT_225 = NL + "            int comp_";
  protected final String TEXT_226 = " = ";
  protected final String TEXT_227 = ".compareTo(";
  protected final String TEXT_228 = ");" + NL + "            if (comp_";
  protected final String TEXT_229 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_230 = " > 0) {";
  protected final String TEXT_231 = NL + "                } else {";
  protected final String TEXT_232 = NL + "                }" + NL + "" + NL + "            }";
  protected final String TEXT_233 = NL + "            throw new JobConfigurationError(\"The ";
  protected final String TEXT_234 = " type is not supported: column--";
  protected final String TEXT_235 = "\");";
  protected final String TEXT_236 = NL + "            }";
  protected final String TEXT_237 = NL + "org.apache.spark.streaming.api.java.JavaPairDStream<";
  protected final String TEXT_238 = ", ";
  protected final String TEXT_239 = "> rdd_";
  protected final String TEXT_240 = " = rdd_";
  protected final String TEXT_241 = ".transformToPair(new ";
  protected final String TEXT_242 = "_Function(job));";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

    
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
		
    stringBuffer.append(TEXT_1);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_2);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementation(getOutValueClass(), getInValueClass()));
    stringBuffer.append(TEXT_3);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_4);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_5);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedType(this.outValueClass.toString()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(this.sparkFunction.getCodeKeyMapping(getInValue()));
    stringBuffer.append(TEXT_11);
    
	                this.transformer.generateTransformContextInitialization();
	                this.transformer.generateTransform(true);
	                
    stringBuffer.append(TEXT_12);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn(this.getOutValue(), this.getOutValueClass()));
    stringBuffer.append(TEXT_13);
    
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
        
    stringBuffer.append(TEXT_14);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(this.sparkFunction.getCodeFunctionImplementationOutputFixedType(getInValueClass(), "Boolean", "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_16);
    
                this.transformer.generateHelperClasses(true);
                this.transformer.generateTransformContextDeclaration();
                
    stringBuffer.append(TEXT_17);
    stringBuffer.append(this.sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturnedTypeFixedType((String)this.outKeyClass, "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(this.sparkFunction.getCodeImplementedMethod());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(this.sparkFunction.getCodeFunctionArgument(getInValueClass()));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(this.sparkFunction.getCodeThrowException());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(this.sparkFunction.getMethodHeader(this.outValueClass, this.outValue, this.inValueClass, this.inValue));
    
                    this.transformer.generateTransformContextInitialization();
                    this.transformer.generateTransform(true);
	                if(this.sparkFunction.getCodeFunctionReturn()!=null) {
                
    stringBuffer.append(TEXT_23);
    stringBuffer.append(this.sparkFunction.getCodeFunctionReturn());
    stringBuffer.append(TEXT_24);
    
	            	}
                
    stringBuffer.append(TEXT_25);
    
        }
    }

    stringBuffer.append(TEXT_26);
    

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
        if(!isReject && this.sparkFunction!=null && !isMultiOutput) {
            return this.sparkFunction.getCodeToInitOut(functionGenerator.getOutValue("main"), functionGenerator.getOutValueClass("main"));
        } else {
            return "";
        }
    }

    // Method to avoid using getCodeToInitOut that calls sparkFunction.getCodeToInitOut which creates unnecessary objects
    // Check getCodeToAddToOutput in SparkFunction and its implementation in FlatMapToPairFunction
    public String getCodeToAddToOutput(boolean isReject, Iterable<IMetadataColumn> columns) {
        if(this.sparkFunction!=null && !isMultiOutput) {
            return this.sparkFunction.getCodeToAddToOutput(false, false, functionGenerator.getOutValue(isReject ? "reject" : "main"), functionGenerator.getOutValueClass(isReject ? "reject" : "main"));
        }else if(this.sparkFunction!=null && isMultiOutput){
            if(isReject){
                return this.sparkFunction.getCodeToAddToOutput(true, false, functionGenerator.getOutValue("reject"), functionGenerator.getOutValueClass("reject"));
            }else{
                return this.sparkFunction.getCodeToAddToOutput(true, true, functionGenerator.getOutValue("main"), functionGenerator.getOutValueClass("main"));
            }
        }else {
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
        if (this.sparkFunction != null && isMultiOutput) {
            if (isReject) {
                return this.sparkFunction.getCodeToEmit(false, functionGenerator.getOutValue("reject"), functionGenerator.getOutValueClass("reject"));
            } else {
                return this.sparkFunction.getCodeToEmit(true, functionGenerator.getOutValue("main"), functionGenerator.getOutValueClass("main"));
            }
        } else {
            if (isReject) {
                return this.sparkFunction.getCodeToInitOut(functionGenerator.getOutValue("reject"), functionGenerator.getOutValueClass("reject"));
            } else {
                return "";
            }
        }
    }

    public void generateSparkCode(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        if (transformer.isMultiOutput()) {
            setMultiOutput(true);
        }
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
            return;
        }

        if (transformer.isMultiOutput()) {
            org.talend.designer.spark.generator.SparkFunction localSparkFunction = null;
            if ((sparkFunction instanceof org.talend.designer.spark.generator.FlatMapFunction)
                    || (sparkFunction instanceof org.talend.designer.spark.generator.FlatMapToPairFunction)) {
                localSparkFunction = new org.talend.designer.spark.generator.FlatMapToPairFunction(
                        sparkFunction.isInputPair(),
                        codeGenArgument.getSparkVersion(),
                        sparkFunction.getKeyList());
            } else {
                localSparkFunction = new org.talend.designer.spark.generator.MapToPairFunction(
                        sparkFunction.isInputPair(),
                        sparkFunction.getKeyList());
            }

            org.talend.designer.spark.generator.SparkFunction extractSparkFunction = null;
            if ((sparkFunction instanceof org.talend.designer.spark.generator.FlatMapFunction)
                    || (sparkFunction instanceof org.talend.designer.spark.generator.MapFunction)) {
                extractSparkFunction = new org.talend.designer.spark.generator.MapFunction(
                        sparkFunction.isInputPair(),
                        sparkFunction.getKeyList());
            } else {
                extractSparkFunction = new org.talend.designer.spark.generator.MapToPairFunction(
                        sparkFunction.isInputPair(),
                        sparkFunction.getKeyList());
            }
            this.sparkFunction = localSparkFunction;

            // The multi-output condition is slightly different, and the
            // MapperHelper is configured with internal names for the
            // connections.
            java.util.HashMap<String, String> names = new java.util.HashMap<String, String>();
            names.put("main", transformer.getOutConnMainName());
            names.put("reject", transformer.getOutConnRejectName());

            // Refactoring FunctionGenerator to java so we have to instaniate a MO or SO here
            functionGenerator = new MOFunctionGenerator(transformer, localSparkFunction);
            functionGenerator.init(node, cid, null, transformer.getInConnName(), null, names);

            
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(extractSparkFunction.getCodeFunctionImplementationInputFixedType(transformer.getOutConnMainTypeName(), "Boolean", "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(extractSparkFunction.getCodeFunctionReturnedType(transformer.getOutConnMainTypeName()));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(transformer.getOutConnMainTypeName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(transformer.getOutConnMainTypeName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(extractSparkFunction.getCodeFunctionReturn(transformer.getOutConnMainName(), transformer.getOutConnMainTypeName()));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(extractSparkFunction.getCodeFunctionImplementationInputFixedType(transformer.getOutConnRejectTypeName(), "Boolean", "org.apache.avro.specific.SpecificRecordBase"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(extractSparkFunction.getCodeFunctionReturnedType(transformer.getOutConnRejectTypeName()));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(transformer.getOutConnRejectTypeName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(transformer.getOutConnRejectName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(transformer.getOutConnRejectTypeName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(extractSparkFunction.getCodeFunctionReturn(transformer.getOutConnRejectName(), transformer.getOutConnRejectTypeName()));
    stringBuffer.append(TEXT_49);
    
        } else {
            // Refactoring FunctionGenerator to java so we have to instaniate a MO or SO here
            functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

            functionGenerator.init(node, cid, null, transformer.getInConnName(), null,
                    transformer.getOutConnMainName() != null
                        ? transformer.getOutConnMainName()
                                : transformer.getOutConnRejectName());
        }
        functionGenerator.generate();
    }

    public void generateSparkConfig(final org.talend.designer.common.TransformerBase transformer, final org.talend.designer.spark.generator.SparkFunction sparkFunction) {
        if (transformer.isUnnecessary()) {
            
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    
            return;
        }

        if (transformer.isMultiOutput()) {
            String localFunctionType = "mapToPair";
            if ((sparkFunction instanceof org.talend.designer.spark.generator.FlatMapFunction)
                    || (sparkFunction instanceof org.talend.designer.spark.generator.FlatMapToPairFunction)) {
                localFunctionType = "flatMapToPair";
            }

            String extractFunctionType = "mapToPair";
            if ((sparkFunction instanceof org.talend.designer.spark.generator.FlatMapFunction)
                    || (sparkFunction instanceof org.talend.designer.spark.generator.MapFunction)) {
                extractFunctionType = "map";
            }
            
    stringBuffer.append(TEXT_52);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(sparkFunction.isStreaming() ?"org.apache.spark.streaming.api.java.JavaPairDStream":"org.apache.spark.api.java.JavaPairRDD");
    stringBuffer.append(TEXT_54);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(transformer.getInConnName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(localFunctionType);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(sparkFunction.getConfigReturnedType(transformer.getOutConnMainTypeName()));
    stringBuffer.append(TEXT_60);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(extractFunctionType);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(sparkFunction.getConfigReturnedType(transformer.getOutConnRejectTypeName()));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(transformer.getOutConnRejectName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(extractFunctionType);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    
        } else {
            functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

            functionGenerator.init(node, cid, null, transformer.getInConnName(), null,
                    transformer.getOutConnMainName() != null
                        ? transformer.getOutConnMainName()
                                : transformer.getOutConnRejectName());
            
    stringBuffer.append(TEXT_73);
    stringBuffer.append(sparkFunction.getConfigReturnedType(transformer.getOutConnMainName() != null ? transformer.getOutConnMainTypeName() : transformer.getOutConnRejectTypeName()));
    stringBuffer.append(TEXT_74);
    stringBuffer.append(transformer.getOutConnMainName() != null ? transformer.getOutConnMainName() : transformer.getOutConnRejectName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(transformer.getInConnName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(sparkFunction.getConfigTransformation());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_78);
    
        }
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
            
    stringBuffer.append(TEXT_79);
    
        } else {
            
    stringBuffer.append(TEXT_80);
    
        }
    }

    protected void lesser(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_81);
    
        } else {
            
    stringBuffer.append(TEXT_82);
    
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
            
    stringBuffer.append(TEXT_83);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_85);
    lesser(columnName);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_88);
    greater(columnName);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_91);
    
            // Note that the nullable else case is left open here and closed
            // at the end.
        }

        if (typeToGenerate.equalsIgnoreCase("Boolean")) {
            
    stringBuffer.append(TEXT_92);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_95);
    greater(columnName);
    stringBuffer.append(TEXT_96);
    lesser(columnName);
    stringBuffer.append(TEXT_97);
    
        } else if (typeToGenerate.equalsIgnoreCase("Byte")) {
            
    stringBuffer.append(TEXT_98);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_100);
    greater(columnName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_103);
    lesser(columnName);
    stringBuffer.append(TEXT_104);
    
        } else if (typeToGenerate.equals("byte[]")) {
            
    stringBuffer.append(TEXT_105);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_110);
    greater(columnName);
    stringBuffer.append(TEXT_111);
    lesser(columnName);
    stringBuffer.append(TEXT_112);
    
        } else if (typeToGenerate.equalsIgnoreCase("Char") || typeToGenerate.equalsIgnoreCase("Character")) {
            
    stringBuffer.append(TEXT_113);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_117);
    greater(columnName);
    stringBuffer.append(TEXT_118);
    lesser(columnName);
    stringBuffer.append(TEXT_119);
    
        } else if (typeToGenerate.equals("java.util.Date")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_120);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_126);
    greater(columnName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_128);
    lesser(columnName);
    stringBuffer.append(TEXT_129);
    
            } else {
                
    stringBuffer.append(TEXT_130);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_134);
    greater(columnName);
    stringBuffer.append(TEXT_135);
    lesser(columnName);
    stringBuffer.append(TEXT_136);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Double")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_137);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_141);
    greater(columnName);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_143);
    lesser(columnName);
    stringBuffer.append(TEXT_144);
    
            } else {
                
    stringBuffer.append(TEXT_145);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_147);
    greater(columnName);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_150);
    lesser(columnName);
    stringBuffer.append(TEXT_151);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Float")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_152);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_156);
    greater(columnName);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_158);
    lesser(columnName);
    stringBuffer.append(TEXT_159);
    
            } else {
                
    stringBuffer.append(TEXT_160);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_162);
    greater(columnName);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_165);
    lesser(columnName);
    stringBuffer.append(TEXT_166);
    
            }
        } else if (typeToGenerate.equals("BigDecimal")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_167);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_170);
    
            } else {
                
    stringBuffer.append(TEXT_171);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_176);
    greater(columnName);
    stringBuffer.append(TEXT_177);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_178);
    lesser(columnName);
    stringBuffer.append(TEXT_179);
    
        } else if (typeToGenerate.equalsIgnoreCase("Integer") || typeToGenerate.equalsIgnoreCase("int")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_180);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_184);
    greater(columnName);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_186);
    lesser(columnName);
    stringBuffer.append(TEXT_187);
    
            } else {
                
    stringBuffer.append(TEXT_188);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_190);
    greater(columnName);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_192);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_193);
    lesser(columnName);
    stringBuffer.append(TEXT_194);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Long")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_195);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_199);
    greater(columnName);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_201);
    lesser(columnName);
    stringBuffer.append(TEXT_202);
    
            } else {
                
    stringBuffer.append(TEXT_203);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_204);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_205);
    greater(columnName);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_208);
    lesser(columnName);
    stringBuffer.append(TEXT_209);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Short")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_210);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_214);
    greater(columnName);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_216);
    lesser(columnName);
    stringBuffer.append(TEXT_217);
    
            } else {
                
    stringBuffer.append(TEXT_218);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_219);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_220);
    greater(columnName);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_223);
    lesser(columnName);
    stringBuffer.append(TEXT_224);
    
            }
        } else if (typeToGenerate.equals("String")) {
            
    stringBuffer.append(TEXT_225);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_230);
    greater(columnName);
    stringBuffer.append(TEXT_231);
    lesser(columnName);
    stringBuffer.append(TEXT_232);
    
        } else if (typeToGenerate.equals("Object")
                || typeToGenerate.equals("List")
                || typeToGenerate.equals("Document")
                || typeToGenerate.equals("Dynamic")) {
            
    stringBuffer.append(TEXT_233);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_235);
    
        }

        // Close the open else statement for nullable columns.
        if (nullable) {
            
    stringBuffer.append(TEXT_236);
    
        }
    }
}

    
final SparkRowTransformUtil sparkTransformUtil = new SparkRowTransformUtil();
final TSortRowUtil tSortRowUtil = new TSortRowUtil(
        node, codeGenArgument, sparkTransformUtil);

java.util.Map<String, java.util.List<org.talend.core.model.metadata.IMetadataColumn>> keyList = bigDataNode.getKeyList();
org.talend.designer.spark.generator.SparkFunction sparkFunction = new org.talend.designer.spark.generator.SortByKeyFunction(false, keyList);

if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node)) {
	sparkTransformUtil.generateSparkConfig(tSortRowUtil, sparkFunction);
} else {
	String outputType = tSortRowUtil.getOutConnMainName() != null ? tSortRowUtil.getOutConnMainTypeName() : tSortRowUtil.getOutConnRejectTypeName();
	String outputName = tSortRowUtil.getOutConnMainName() != null ? tSortRowUtil.getOutConnMainName() : tSortRowUtil.getOutConnRejectName();
	String inputName = tSortRowUtil.getInConnName();

    stringBuffer.append(TEXT_237);
    stringBuffer.append(SparkFunctionUtil.getKeyType(keyList, "BOTH"));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(inputName);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    
}

    return stringBuffer.toString();
  }
}
