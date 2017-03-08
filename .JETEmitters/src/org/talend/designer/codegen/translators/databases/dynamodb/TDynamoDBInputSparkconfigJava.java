package org.talend.designer.codegen.translators.databases.dynamodb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import org.talend.designer.spark.generator.storage.DynamoDBSparkStorage;

public class TDynamoDBInputSparkconfigJava
{
  protected static String nl;
  public static synchronized TDynamoDBInputSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDynamoDBInputSparkconfigJava result = new TDynamoDBInputSparkconfigJava();
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
  protected final String TEXT_79 = NL + "            ";
  protected final String TEXT_80 = ".set(";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = ");";
  protected final String TEXT_83 = NL + "                ";
  protected final String TEXT_84 = ".";
  protected final String TEXT_85 = " = !item.isPresent(\"";
  protected final String TEXT_86 = "\") || item.isNull(\"";
  protected final String TEXT_87 = "\")" + NL + "                        ? ";
  protected final String TEXT_88 = " : item.getString(\"";
  protected final String TEXT_89 = "\");";
  protected final String TEXT_90 = NL + "                {" + NL + "                    String tmp = !item.isPresent(\"";
  protected final String TEXT_91 = "\") || item.isNull(\"";
  protected final String TEXT_92 = "\")" + NL + "                        ? \"\" : item.getString(\"";
  protected final String TEXT_93 = "\");";
  protected final String TEXT_94 = NL + "                    ";
  protected final String TEXT_95 = ".";
  protected final String TEXT_96 = " = tmp.length() > 0 ? tmp.charAt(0) : ";
  protected final String TEXT_97 = ";" + NL + "                }";
  protected final String TEXT_98 = NL + "                ";
  protected final String TEXT_99 = ".";
  protected final String TEXT_100 = " = !item.isPresent(\"";
  protected final String TEXT_101 = "\") || item.isNull(\"";
  protected final String TEXT_102 = "\")" + NL + "                        ? ";
  protected final String TEXT_103 = " : item.getBOOL(\"";
  protected final String TEXT_104 = "\");";
  protected final String TEXT_105 = NL + "                ";
  protected final String TEXT_106 = ".";
  protected final String TEXT_107 = " = !item.isPresent(\"";
  protected final String TEXT_108 = "\") || item.isNull(\"";
  protected final String TEXT_109 = "\")" + NL + "                        ? ";
  protected final String TEXT_110 = " : item.getLong(\"";
  protected final String TEXT_111 = "\");";
  protected final String TEXT_112 = NL + "                ";
  protected final String TEXT_113 = ".";
  protected final String TEXT_114 = " = !item.isPresent(\"";
  protected final String TEXT_115 = "\") || item.isNull(\"";
  protected final String TEXT_116 = "\")" + NL + "                        ? ";
  protected final String TEXT_117 = " : item.getInt(\"";
  protected final String TEXT_118 = "\");";
  protected final String TEXT_119 = NL + "                ";
  protected final String TEXT_120 = ".";
  protected final String TEXT_121 = " = !item.isPresent(\"";
  protected final String TEXT_122 = "\") || item.isNull(\"";
  protected final String TEXT_123 = "\")" + NL + "                        ? ";
  protected final String TEXT_124 = " : item.getShort(\"";
  protected final String TEXT_125 = "\");";
  protected final String TEXT_126 = NL + "                ";
  protected final String TEXT_127 = ".";
  protected final String TEXT_128 = " = !item.isPresent(\"";
  protected final String TEXT_129 = "\") || item.isNull(\"";
  protected final String TEXT_130 = "\")" + NL + "                        ? ";
  protected final String TEXT_131 = " : (byte) item.getShort(\"";
  protected final String TEXT_132 = "\");";
  protected final String TEXT_133 = NL + "                ";
  protected final String TEXT_134 = ".";
  protected final String TEXT_135 = " = !item.isPresent(\"";
  protected final String TEXT_136 = "\") || item.isNull(\"";
  protected final String TEXT_137 = "\")" + NL + "                        ? ";
  protected final String TEXT_138 = " : item.getDouble(\"";
  protected final String TEXT_139 = "\");";
  protected final String TEXT_140 = NL + "                ";
  protected final String TEXT_141 = ".";
  protected final String TEXT_142 = " = !item.isPresent(\"";
  protected final String TEXT_143 = "\") || item.isNull(\"";
  protected final String TEXT_144 = "\")" + NL + "                        ? ";
  protected final String TEXT_145 = " : item.getFloat(\"";
  protected final String TEXT_146 = "\");";
  protected final String TEXT_147 = NL + "                ";
  protected final String TEXT_148 = ".";
  protected final String TEXT_149 = " = !item.isPresent(\"";
  protected final String TEXT_150 = "\") || item.isNull(\"";
  protected final String TEXT_151 = "\")" + NL + "                        ? ";
  protected final String TEXT_152 = " : item.getNumber(\"";
  protected final String TEXT_153 = "\");";
  protected final String TEXT_154 = NL + "                ";
  protected final String TEXT_155 = ".";
  protected final String TEXT_156 = " = !item.isPresent(\"";
  protected final String TEXT_157 = "\") || item.isNull(\"";
  protected final String TEXT_158 = "\")" + NL + "                        ? ";
  protected final String TEXT_159 = " : item.getByteBuffer(\"";
  protected final String TEXT_160 = "\");";
  protected final String TEXT_161 = NL + "                ";
  protected final String TEXT_162 = ".";
  protected final String TEXT_163 = " = !item.isPresent(\"";
  protected final String TEXT_164 = "\") || item.isNull(\"";
  protected final String TEXT_165 = "\")" + NL + "                        ? ";
  protected final String TEXT_166 = NL + "                        : BigDataParserUtils.parseTo_Date(" + NL + "                                item.getString(\"";
  protected final String TEXT_167 = "\"),";
  protected final String TEXT_168 = NL + "                                ";
  protected final String TEXT_169 = ");";
  protected final String TEXT_170 = NL + "                ";
  protected final String TEXT_171 = ".";
  protected final String TEXT_172 = " = !item.isPresent(\"";
  protected final String TEXT_173 = "\") || item.isNull(\"";
  protected final String TEXT_174 = "\")" + NL + "                        ? ";
  protected final String TEXT_175 = " : item.get(\"";
  protected final String TEXT_176 = "\");";
  protected final String TEXT_177 = NL + "                ";
  protected final String TEXT_178 = ".";
  protected final String TEXT_179 = " = !item.isPresent(\"";
  protected final String TEXT_180 = "\") || item.isNull(\"";
  protected final String TEXT_181 = "\")" + NL + "                        ? ";
  protected final String TEXT_182 = " : item.getList(\"";
  protected final String TEXT_183 = "\");";
  protected final String TEXT_184 = NL + "                throw new JobConfigurationError(\"Type not supported: ";
  protected final String TEXT_185 = "\");";
  protected final String TEXT_186 = NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_187 = "> rdd_";
  protected final String TEXT_188 = ";";
  protected final String TEXT_189 = NL + "    org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_190 = "> rdd_";
  protected final String TEXT_191 = ";";
  protected final String TEXT_192 = NL + "{" + NL + "    // Get the data from DynamoDB." + NL + "    JobConf jobConf_";
  protected final String TEXT_193 = " = new JobConf(job);";
  protected final String TEXT_194 = NL + "    org.apache.spark.api.java.JavaRDD<org.apache.hadoop.dynamodb.DynamoDBItemWritable> rddFromHadoop_";
  protected final String TEXT_195 = " =" + NL + "            ctx.hadoopRDD(" + NL + "                    jobConf_";
  protected final String TEXT_196 = "," + NL + "                    org.apache.hadoop.dynamodb.read.DynamoDBInputFormat.class," + NL + "                    org.apache.hadoop.io.Text.class," + NL + "                    org.apache.hadoop.dynamodb.DynamoDBItemWritable.class).values();" + NL + "" + NL + "    // Convert each DynamoDBItemWritable to the correct row structure.";
  protected final String TEXT_197 = NL + "        rdd_";
  protected final String TEXT_198 = " = rddFromHadoop_";
  protected final String TEXT_199 = ".flatMap(new ";
  protected final String TEXT_200 = "Function());";
  protected final String TEXT_201 = NL + "        rdd_";
  protected final String TEXT_202 = " = rddFromHadoop_";
  protected final String TEXT_203 = ".flatMap(new ";
  protected final String TEXT_204 = "RejectFunction());";
  protected final String TEXT_205 = NL + "        org.apache.spark.api.java.JavaRDD<scala.Tuple2<";
  protected final String TEXT_206 = ",";
  protected final String TEXT_207 = ">> rddMainReject_";
  protected final String TEXT_208 = NL + "                = rddFromHadoop_";
  protected final String TEXT_209 = ".map(new ";
  protected final String TEXT_210 = "MainAndRejectFunction());" + NL + "        rdd_";
  protected final String TEXT_211 = " = rddMainReject_";
  protected final String TEXT_212 = ".flatMap(new ";
  protected final String TEXT_213 = "MainFilterFunction());" + NL + "        rdd_";
  protected final String TEXT_214 = " = rddMainReject_";
  protected final String TEXT_215 = ".flatMap(new ";
  protected final String TEXT_216 = "RejectFilterFunction());";
  protected final String TEXT_217 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode)codeGenArgument.getArgument();
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
 * Helper class for adapting information for DynamicDB input to spark code and
 * config structures.  This provides code generation utility methods that are
 * completely spark-specific.
 */
class TDynamoDBInputUtil extends org.talend.designer.common.TransformerBase {

    /** There are two reject fields at the end of the reject schema. */
    private final static int REJECT_FIELDS_SIZE = 2;

    // All of the configuration is stored in this instance.
    protected final DynamoDBSparkStorage storage;

    public final Iterable<IMetadataColumn> mainColumnsCopy;

    TDynamoDBInputUtil(INode node,
            org.talend.designer.common.BigDataCodeGeneratorArgument argument,
            org.talend.designer.common.CommonRowTransformUtil rowTransformUtil) {
        super(node, argument, rowTransformUtil, "FLOW", "REJECT");
        storage = new DynamoDBSparkStorage(node);

        // All of the configurations are set in the hadoop properties.
        storage.addProperty(false,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.INPUT_TABLE_NAME",
                ElementParameterParser.getValue(node, "__TABLE__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.THROUGHPUT_READ_PERCENT",
                ElementParameterParser.getValue(node, "__THROUGHPUT_READ_PERCENT__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.SCAN_SEGMENTS",
                ElementParameterParser.getValue(node, "__SCAN_SEGMENTS__"));
        storage.addProperty(true,
                "org.apache.hadoop.dynamodb.DynamoDBConstants.MAX_MAP_TASKS",
                ElementParameterParser.getValue(node, "__MAX_MAP_TASKS__"));

        // Add the advanced properties last to override any of the values.
        storage.addAdvancedProperties("__DYNAMODB_ADVANCED_PROPERTIES__");

        if (getOutColumnsMain() == null) {
            List<IMetadataColumn> columns = getOutConnReject().getMetadataTable().getListColumns();
            columns = columns.subList(0, columns.size() - REJECT_FIELDS_SIZE);
            mainColumnsCopy = columns;
        } else {
            mainColumnsCopy = getOutColumnsMain();
        }
    }

    /**
     * Generate the standard hadoop job configuration for the DynamoDB formats.
     */
    public void generateHadoopJobConfiguration(String codeVarJobConf) {
        for (java.util.Map.Entry<String, String> e : storage.getProperties().entrySet()) {
            
    stringBuffer.append(TEXT_79);
    stringBuffer.append(codeVarJobConf);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(e.getKey());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(e.getValue());
    stringBuffer.append(TEXT_82);
    
        }
    }

    /** Generates the main transformation from a DynamoDBItemWritable to the
     *  main rowStruct. */
    public void generateTransform(String codeVarRow) {
        for (IMetadataColumn column : mainColumnsCopy) {
            JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
            String javaFieldType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
            String defaultValue = JavaTypesManager.getDefaultValueFromJavaIdType(
                    column.getTalendType(), column.isNullable(), column.getDefault());

            // TODO transform the label with a map in Advanced?
            String attr = column.getLabel();
            if (javaType == JavaTypesManager.STRING) {
                
    stringBuffer.append(TEXT_83);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_89);
    
            } else if (javaType == JavaTypesManager.CHARACTER) {
                
    stringBuffer.append(TEXT_90);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_97);
    
            } else if (javaType == JavaTypesManager.BOOLEAN) {
                
    stringBuffer.append(TEXT_98);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_104);
    
            } else if (javaType == JavaTypesManager.LONG) {
                
    stringBuffer.append(TEXT_105);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_111);
    
            } else if (javaType == JavaTypesManager.INTEGER) {
                
    stringBuffer.append(TEXT_112);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_118);
    
            } else if (javaType == JavaTypesManager.SHORT) {
                
    stringBuffer.append(TEXT_119);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_125);
    
            } else if (javaType == JavaTypesManager.BYTE) {
                
    stringBuffer.append(TEXT_126);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_132);
    
            } else if (javaType == JavaTypesManager.DOUBLE) {
                
    stringBuffer.append(TEXT_133);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_139);
    
            } else if (javaType == JavaTypesManager.FLOAT) {
                
    stringBuffer.append(TEXT_140);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_146);
    
            } else if (javaType == JavaTypesManager.BIGDECIMAL) {
                
    stringBuffer.append(TEXT_147);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_153);
    
            } else if (javaType == JavaTypesManager.BYTE_ARRAY) {
                
    stringBuffer.append(TEXT_154);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_160);
    
            } else if (javaType == JavaTypesManager.DATE) {
                
    stringBuffer.append(TEXT_161);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(column.getPattern());
    stringBuffer.append(TEXT_169);
    
            } else if (javaType == JavaTypesManager.OBJECT) {
                
    stringBuffer.append(TEXT_170);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_176);
    
            } else if (javaType == JavaTypesManager.LIST) {
                
    stringBuffer.append(TEXT_177);
    stringBuffer.append(codeVarRow);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_182);
    stringBuffer.append(attr);
    stringBuffer.append(TEXT_183);
    
            } else {
                
    stringBuffer.append(TEXT_184);
    stringBuffer.append(javaType);
    stringBuffer.append(TEXT_185);
    
            }
        }
    }
}

    
final SparkRowTransformUtil transform = new SparkRowTransformUtil();
final TDynamoDBInputUtil util = new TDynamoDBInputUtil(node, codeGenArgument, transform);

// If there aren't any output columns, then this is an unnecessary component.
if (util.getOutConnMain() == null && util.getOutConnReject() == null)
    return "";

// Declare the output structures.
String mainRowName = null;
String mainRowStruct = null;
String rejectRowName = null;
String rejectRowStruct = null;
if (util.getOutConnMain() != null) {
    mainRowName = util.getOutConnMain().getName();
    mainRowStruct = codeGenArgument.getRecordStructName(util.getOutConnMain());
    
    stringBuffer.append(TEXT_186);
    stringBuffer.append(mainRowStruct);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(mainRowName);
    stringBuffer.append(TEXT_188);
    
}
if (util.getOutConnReject() != null) {
    rejectRowName = util.getOutConnReject().getName();
    rejectRowStruct = codeGenArgument.getRecordStructName(util.getOutConnReject());
    
    stringBuffer.append(TEXT_189);
    stringBuffer.append(rejectRowStruct);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(rejectRowName);
    stringBuffer.append(TEXT_191);
    
}

// Define the behaviour for the component.

    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_193);
     util.generateHadoopJobConfiguration("jobConf_" + cid); 
    stringBuffer.append(TEXT_194);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_196);
    
    if (util.getOutConnMain() != null && util.getOutConnReject() == null) {
        
    stringBuffer.append(TEXT_197);
    stringBuffer.append(mainRowName);
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    
    } else if (util.getOutConnMain() == null && util.getOutConnReject() != null) {
        
    stringBuffer.append(TEXT_201);
    stringBuffer.append(rejectRowName);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_204);
    
    } else {
        
    stringBuffer.append(TEXT_205);
    stringBuffer.append(mainRowStruct);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(rejectRowStruct);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_209);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(mainRowName);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_213);
    stringBuffer.append(rejectRowName);
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    
    }
    
    stringBuffer.append(TEXT_217);
    return stringBuffer.toString();
  }
}
