package org.talend.designer.codegen.translators.processing;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TTopSparkcodeJava
{
  protected static String nl;
  public static synchronized TTopSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TTopSparkcodeJava result = new TTopSparkcodeJava();
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
  protected final String TEXT_29 = NL + "            // No sparkconfig generated for unnecessary ";
  protected final String TEXT_30 = NL;
  protected final String TEXT_31 = NL + "        ";
  protected final String TEXT_32 = " rdd_";
  protected final String TEXT_33 = " =" + NL + "            ctx.parallelizePairs(" + NL + "                    rdd_";
  protected final String TEXT_34 = ".";
  protected final String TEXT_35 = "(";
  protected final String TEXT_36 = "," + NL + "                        new ";
  protected final String TEXT_37 = "(job))," + NL + "                1); // One partition to keep the ordering.";
  protected final String TEXT_38 = NL + "            return 1;";
  protected final String TEXT_39 = NL + "            return -1;";
  protected final String TEXT_40 = NL + "            return -1;";
  protected final String TEXT_41 = NL + "            return 1;";
  protected final String TEXT_42 = NL + "            if (";
  protected final String TEXT_43 = " == null && ";
  protected final String TEXT_44 = " != null) {";
  protected final String TEXT_45 = NL + "            } else if (";
  protected final String TEXT_46 = " != null && ";
  protected final String TEXT_47 = " == null) {";
  protected final String TEXT_48 = NL + "            } else if (";
  protected final String TEXT_49 = " == null && ";
  protected final String TEXT_50 = " == null) {" + NL + "                //ignore" + NL + "            } else {";
  protected final String TEXT_51 = NL + "            if (";
  protected final String TEXT_52 = " != ";
  protected final String TEXT_53 = ") {" + NL + "                if (";
  protected final String TEXT_54 = ") {";
  protected final String TEXT_55 = NL + "                } else {";
  protected final String TEXT_56 = NL + "                }" + NL + "            }";
  protected final String TEXT_57 = NL + "            if (";
  protected final String TEXT_58 = " > ";
  protected final String TEXT_59 = ") {";
  protected final String TEXT_60 = NL + "            } else if (";
  protected final String TEXT_61 = " < ";
  protected final String TEXT_62 = ") {";
  protected final String TEXT_63 = NL + "            }";
  protected final String TEXT_64 = NL + "            int comp_";
  protected final String TEXT_65 = " = ";
  protected final String TEXT_66 = ".compareTo(";
  protected final String TEXT_67 = ");" + NL + "            if (comp_";
  protected final String TEXT_68 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_69 = " > 0) {";
  protected final String TEXT_70 = NL + "                } else {";
  protected final String TEXT_71 = NL + "                }" + NL + "            }";
  protected final String TEXT_72 = NL + "            if (";
  protected final String TEXT_73 = " - ";
  protected final String TEXT_74 = " != 0) {" + NL + "                if (";
  protected final String TEXT_75 = " - ";
  protected final String TEXT_76 = " > 0) {";
  protected final String TEXT_77 = NL + "                } else {";
  protected final String TEXT_78 = NL + "                }" + NL + "            }";
  protected final String TEXT_79 = NL + "                int cmp_";
  protected final String TEXT_80 = " = FormatterUtils.format_DateInUTC(";
  protected final String TEXT_81 = ", ";
  protected final String TEXT_82 = ").compareTo(FormatterUtils.format_DateInUTC(";
  protected final String TEXT_83 = ", ";
  protected final String TEXT_84 = "));" + NL + "                if (cmp_";
  protected final String TEXT_85 = " > 0) {";
  protected final String TEXT_86 = NL + "                } else if (cmp_";
  protected final String TEXT_87 = " < 0) {";
  protected final String TEXT_88 = NL + "                }";
  protected final String TEXT_89 = NL + "                if (!";
  protected final String TEXT_90 = ".equals(";
  protected final String TEXT_91 = ")) {" + NL + "                    if (";
  protected final String TEXT_92 = ".compareTo(";
  protected final String TEXT_93 = ") > 0) {";
  protected final String TEXT_94 = NL + "                    } else {";
  protected final String TEXT_95 = NL + "                    }" + NL + "                }";
  protected final String TEXT_96 = NL + "                int cmp_";
  protected final String TEXT_97 = " = String.valueOf(";
  protected final String TEXT_98 = ").compareTo(String.valueOf(";
  protected final String TEXT_99 = "));" + NL + "                if (cmp_";
  protected final String TEXT_100 = " > 0) {";
  protected final String TEXT_101 = NL + "                } else if (cmp_";
  protected final String TEXT_102 = " < 0) {";
  protected final String TEXT_103 = NL + "                }";
  protected final String TEXT_104 = NL + "                if (";
  protected final String TEXT_105 = " > ";
  protected final String TEXT_106 = ") {";
  protected final String TEXT_107 = NL + "                } else if (";
  protected final String TEXT_108 = " < ";
  protected final String TEXT_109 = ") {";
  protected final String TEXT_110 = NL + "                }";
  protected final String TEXT_111 = NL + "                int cmp_";
  protected final String TEXT_112 = " = String.valueOf(";
  protected final String TEXT_113 = ").compareTo(String.valueOf(";
  protected final String TEXT_114 = "));" + NL + "                if (cmp_";
  protected final String TEXT_115 = " > 0) {";
  protected final String TEXT_116 = NL + "                } else if (cmp_";
  protected final String TEXT_117 = " < 0) {";
  protected final String TEXT_118 = NL + "                }";
  protected final String TEXT_119 = NL + "                if (";
  protected final String TEXT_120 = " > ";
  protected final String TEXT_121 = ") {";
  protected final String TEXT_122 = NL + "                } else if (";
  protected final String TEXT_123 = " < ";
  protected final String TEXT_124 = ") {";
  protected final String TEXT_125 = NL + "                }";
  protected final String TEXT_126 = NL + "                int cmp_";
  protected final String TEXT_127 = " = String.valueOf(";
  protected final String TEXT_128 = ").compareTo(String.valueOf(";
  protected final String TEXT_129 = "));";
  protected final String TEXT_130 = NL + "                int cmp_";
  protected final String TEXT_131 = " = ";
  protected final String TEXT_132 = ".compareTo(";
  protected final String TEXT_133 = ");";
  protected final String TEXT_134 = NL + "                if (cmp_";
  protected final String TEXT_135 = " > 0) {";
  protected final String TEXT_136 = NL + "                } else if (cmp_";
  protected final String TEXT_137 = " < 0) {";
  protected final String TEXT_138 = NL + "                }";
  protected final String TEXT_139 = NL + "                int cmp_";
  protected final String TEXT_140 = " = String.valueOf(";
  protected final String TEXT_141 = ").compareTo(String.valueOf(";
  protected final String TEXT_142 = "));" + NL + "                if (cmp_";
  protected final String TEXT_143 = " > 0) {";
  protected final String TEXT_144 = NL + "                } else if (cmp_";
  protected final String TEXT_145 = " < 0) {";
  protected final String TEXT_146 = NL + "                }";
  protected final String TEXT_147 = NL + "                if (";
  protected final String TEXT_148 = " > ";
  protected final String TEXT_149 = ") {";
  protected final String TEXT_150 = NL + "                } else if (";
  protected final String TEXT_151 = " < ";
  protected final String TEXT_152 = ") {";
  protected final String TEXT_153 = NL + "                }";
  protected final String TEXT_154 = NL + "                int cmp_";
  protected final String TEXT_155 = " = String.valueOf(";
  protected final String TEXT_156 = ").compareTo(String.valueOf(";
  protected final String TEXT_157 = "));" + NL + "                if (cmp_";
  protected final String TEXT_158 = " > 0) {";
  protected final String TEXT_159 = NL + "                } else if (cmp_";
  protected final String TEXT_160 = " < 0) {";
  protected final String TEXT_161 = NL + "                }";
  protected final String TEXT_162 = NL + "                if (";
  protected final String TEXT_163 = " > ";
  protected final String TEXT_164 = ") {";
  protected final String TEXT_165 = NL + "                } else if (";
  protected final String TEXT_166 = " < ";
  protected final String TEXT_167 = ") {";
  protected final String TEXT_168 = NL + "                }";
  protected final String TEXT_169 = NL + "                int cmp_";
  protected final String TEXT_170 = " = String.valueOf(";
  protected final String TEXT_171 = ").compareTo(String.valueOf(";
  protected final String TEXT_172 = "));" + NL + "                if (cmp_";
  protected final String TEXT_173 = " > 0) {";
  protected final String TEXT_174 = NL + "                } else if (cmp_";
  protected final String TEXT_175 = " < 0) {";
  protected final String TEXT_176 = NL + "                }";
  protected final String TEXT_177 = NL + "                if (";
  protected final String TEXT_178 = " > ";
  protected final String TEXT_179 = ") {";
  protected final String TEXT_180 = NL + "                } else if (";
  protected final String TEXT_181 = " < ";
  protected final String TEXT_182 = ") {";
  protected final String TEXT_183 = NL + "                }";
  protected final String TEXT_184 = NL + "            int comp_";
  protected final String TEXT_185 = " = ";
  protected final String TEXT_186 = ".compareTo(";
  protected final String TEXT_187 = ");" + NL + "            if (comp_";
  protected final String TEXT_188 = " != 0) {" + NL + "                if (comp_";
  protected final String TEXT_189 = " > 0) {";
  protected final String TEXT_190 = NL + "                } else {";
  protected final String TEXT_191 = NL + "                }" + NL + "" + NL + "            }";
  protected final String TEXT_192 = NL + "            throw new JobConfigurationError(\"The ";
  protected final String TEXT_193 = " type is not supported: column--";
  protected final String TEXT_194 = "\");";
  protected final String TEXT_195 = NL + "            }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

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
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    
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
            
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_30);
    
            return;
        }

        functionGenerator = new SOFunctionGenerator(transformer, sparkFunction);

        functionGenerator.init(node, cid, null, transformer.getInConnName(), null, 
                transformer.getOutConnMainName() != null
                    ? transformer.getOutConnMainName()
                            : transformer.getOutConnRejectName());
        
    stringBuffer.append(TEXT_31);
    stringBuffer.append(sparkFunction.getConfigReturnedType(functionGenerator.getOutValueClass()));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(transformer.getOutConnMainName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(transformer.getInConnName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(sparkFunction.getConfigTransformation());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(topX);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(sparkFunction.getClassName(cid));
    stringBuffer.append(TEXT_37);
    
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
            
    stringBuffer.append(TEXT_38);
    
        } else {
            
    stringBuffer.append(TEXT_39);
    
        }
    }

    protected void lesser(String columnName) {
        if (criteriasAscendingColumns.contains(columnName)) {
            
    stringBuffer.append(TEXT_40);
    
        } else {
            
    stringBuffer.append(TEXT_41);
    
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
            
    stringBuffer.append(TEXT_42);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_44);
    lesser(columnName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_47);
    greater(columnName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_50);
    
            // Note that the nullable else case is left open here and closed
            // at the end.
        }

        if (typeToGenerate.equalsIgnoreCase("Boolean")) {
            
    stringBuffer.append(TEXT_51);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_54);
    greater(columnName);
    stringBuffer.append(TEXT_55);
    lesser(columnName);
    stringBuffer.append(TEXT_56);
    
        } else if (typeToGenerate.equalsIgnoreCase("Byte")) {
            
    stringBuffer.append(TEXT_57);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_59);
    greater(columnName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_62);
    lesser(columnName);
    stringBuffer.append(TEXT_63);
    
        } else if (typeToGenerate.equals("byte[]")) {
            
    stringBuffer.append(TEXT_64);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_69);
    greater(columnName);
    stringBuffer.append(TEXT_70);
    lesser(columnName);
    stringBuffer.append(TEXT_71);
    
        } else if (typeToGenerate.equalsIgnoreCase("Char") || typeToGenerate.equalsIgnoreCase("Character")) {
            
    stringBuffer.append(TEXT_72);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_76);
    greater(columnName);
    stringBuffer.append(TEXT_77);
    lesser(columnName);
    stringBuffer.append(TEXT_78);
    
        } else if (typeToGenerate.equals("java.util.Date")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_79);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_85);
    greater(columnName);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_87);
    lesser(columnName);
    stringBuffer.append(TEXT_88);
    
            } else {
                
    stringBuffer.append(TEXT_89);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_93);
    greater(columnName);
    stringBuffer.append(TEXT_94);
    lesser(columnName);
    stringBuffer.append(TEXT_95);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Double")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_96);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_100);
    greater(columnName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_102);
    lesser(columnName);
    stringBuffer.append(TEXT_103);
    
            } else {
                
    stringBuffer.append(TEXT_104);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_106);
    greater(columnName);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_109);
    lesser(columnName);
    stringBuffer.append(TEXT_110);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Float")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_111);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_115);
    greater(columnName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_117);
    lesser(columnName);
    stringBuffer.append(TEXT_118);
    
            } else {
                
    stringBuffer.append(TEXT_119);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_121);
    greater(columnName);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_124);
    lesser(columnName);
    stringBuffer.append(TEXT_125);
    
            }
        } else if (typeToGenerate.equals("BigDecimal")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_126);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_129);
    
            } else {
                
    stringBuffer.append(TEXT_130);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_133);
    }
    stringBuffer.append(TEXT_134);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_135);
    greater(columnName);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_137);
    lesser(columnName);
    stringBuffer.append(TEXT_138);
    
        } else if (typeToGenerate.equalsIgnoreCase("Integer") || typeToGenerate.equalsIgnoreCase("int")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_139);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_143);
    greater(columnName);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_145);
    lesser(columnName);
    stringBuffer.append(TEXT_146);
    
            } else {
                
    stringBuffer.append(TEXT_147);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_149);
    greater(columnName);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_152);
    lesser(columnName);
    stringBuffer.append(TEXT_153);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Long")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_154);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_158);
    greater(columnName);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_160);
    lesser(columnName);
    stringBuffer.append(TEXT_161);
    
            } else {
                
    stringBuffer.append(TEXT_162);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_164);
    greater(columnName);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_167);
    lesser(columnName);
    stringBuffer.append(TEXT_168);
    
            }
        } else if (typeToGenerate.equalsIgnoreCase("Short")) {
            if (!sortTypes.get(columnName)) {
                
    stringBuffer.append(TEXT_169);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_173);
    greater(columnName);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_175);
    lesser(columnName);
    stringBuffer.append(TEXT_176);
    
            } else {
                
    stringBuffer.append(TEXT_177);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_179);
    greater(columnName);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_182);
    lesser(columnName);
    stringBuffer.append(TEXT_183);
    
            }
        } else if (typeToGenerate.equals("String")) {
            
    stringBuffer.append(TEXT_184);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(codeData1);
    stringBuffer.append(TEXT_186);
    stringBuffer.append(codeData2);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_189);
    greater(columnName);
    stringBuffer.append(TEXT_190);
    lesser(columnName);
    stringBuffer.append(TEXT_191);
    
        } else if (typeToGenerate.equals("Object")
                || typeToGenerate.equals("List")
                || typeToGenerate.equals("Document")
                || typeToGenerate.equals("Dynamic")) {
            
    stringBuffer.append(TEXT_192);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_194);
    
        }

        // Close the open else statement for nullable columns.
        if (nullable) {
            
    stringBuffer.append(TEXT_195);
    
        }
    }
}

    

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

sparkTransformUtil.generateSparkCode(tTopUtil, sparkFunction);

    return stringBuffer.toString();
  }
}
