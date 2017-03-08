package org.talend.designer.codegen.translators.processing;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IBigDataNode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.spark.aggregate.AggregateFunctionFactory;
import org.talend.designer.spark.aggregate.IAggregateFunction;
import org.talend.designer.spark.generator.utils.SparkFunctionUtil;

public class TAggregateRowSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TAggregateRowSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TAggregateRowSparkstreamingcodeJava result = new TAggregateRowSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "                public static class Extended";
  protected final String TEXT_2 = "_";
  protected final String TEXT_3 = " extends ";
  protected final String TEXT_4 = " {";
  protected final String TEXT_5 = NL + "                        ";
  protected final String TEXT_6 = NL + "                }";
  protected final String TEXT_7 = NL + NL + NL + "                // Map to the computation type and instantiate value." + NL + "                public static class PreMap";
  protected final String TEXT_8 = NL + "                implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<";
  protected final String TEXT_9 = ", ";
  protected final String TEXT_10 = ">, ";
  protected final String TEXT_11 = ", ";
  protected final String TEXT_12 = "> {" + NL + "                    public scala.Tuple2<";
  protected final String TEXT_13 = ", ";
  protected final String TEXT_14 = "> call(scala.Tuple2<";
  protected final String TEXT_15 = ", ";
  protected final String TEXT_16 = "> data) throws Exception {";
  protected final String TEXT_17 = NL + "                        ";
  protected final String TEXT_18 = " key = data._1;";
  protected final String TEXT_19 = NL + "                        ";
  protected final String TEXT_20 = " input = data._2;";
  protected final String TEXT_21 = NL + "                        ";
  protected final String TEXT_22 = " output = new ";
  protected final String TEXT_23 = "();";
  protected final String TEXT_24 = NL + "                            ";
  protected final String TEXT_25 = NL + "                        return new scala.Tuple2<";
  protected final String TEXT_26 = ", ";
  protected final String TEXT_27 = ">(key, output);" + NL + "                }" + NL + "" + NL + "            }" + NL + "" + NL + "            // Combiner function" + NL + "            public static class Comb";
  protected final String TEXT_28 = " implements org.apache.spark.api.java.function.Function2<";
  protected final String TEXT_29 = ", ";
  protected final String TEXT_30 = ", ";
  protected final String TEXT_31 = "> {//$NON-NLS-1$" + NL + "                public ";
  protected final String TEXT_32 = "  call(";
  protected final String TEXT_33 = " output, ";
  protected final String TEXT_34 = " input) throws Exception {";
  protected final String TEXT_35 = NL + "                        ";
  protected final String TEXT_36 = NL + "                    return output;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            // Final mapping to the output value" + NL + "" + NL + "            public static class Map";
  protected final String TEXT_37 = " implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<";
  protected final String TEXT_38 = ", ";
  protected final String TEXT_39 = ">, ";
  protected final String TEXT_40 = ", ";
  protected final String TEXT_41 = "> {" + NL + "                public scala.Tuple2<";
  protected final String TEXT_42 = ", ";
  protected final String TEXT_43 = "> call(scala.Tuple2<";
  protected final String TEXT_44 = ", ";
  protected final String TEXT_45 = "> data) throws Exception {";
  protected final String TEXT_46 = NL + "                    ";
  protected final String TEXT_47 = " key = data._1;";
  protected final String TEXT_48 = NL + "                    ";
  protected final String TEXT_49 = " input = data._2;";
  protected final String TEXT_50 = NL + "                        ";
  protected final String TEXT_51 = " output = new ";
  protected final String TEXT_52 = "();";
  protected final String TEXT_53 = NL + "                        ";
  protected final String TEXT_54 = " output = input;";
  protected final String TEXT_55 = NL + "                        ";
  protected final String TEXT_56 = NL + "                            output.";
  protected final String TEXT_57 = " = BigDataParserUtils.parseTo_Date(";
  protected final String TEXT_58 = NL + "                                ";
  protected final String TEXT_59 = ",";
  protected final String TEXT_60 = NL + "                                ";
  protected final String TEXT_61 = ");";
  protected final String TEXT_62 = NL + "                            output.";
  protected final String TEXT_63 = " = java.nio.ByteBuffer.wrap(" + NL + "                            BigDataParserUtils.parseTo_String(";
  protected final String TEXT_64 = NL + "                                ";
  protected final String TEXT_65 = NL + "                            ).getBytes());";
  protected final String TEXT_66 = NL + "                            output.";
  protected final String TEXT_67 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_68 = "(";
  protected final String TEXT_69 = NL + "                                ";
  protected final String TEXT_70 = ");";
  protected final String TEXT_71 = NL + "                    return new scala.Tuple2<";
  protected final String TEXT_72 = ", ";
  protected final String TEXT_73 = ">(key, output);" + NL + "                 };" + NL + "             }" + NL;
  protected final String TEXT_74 = NL + "                static class UtilClass_";
  protected final String TEXT_75 = " {" + NL + "" + NL + "                    public static void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        byte r = (byte) (a + b);" + NL + "                        if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'short/Short'\", \"'byte/Byte'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        short r = (short)(a + b);" + NL + "                        if (checkTypeOverFlow && (short)((a ^ r) & (b ^ r)) < (short)0) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'int/Integer'\", \"'short/Short'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        int r = a + b;" + NL + "                        if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'long/Long'\", \"'int/Integer'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        long r = a + b;" + NL + "                        if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'BigDecimal'\", \"'long/Long'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        if(checkUlp) {" + NL + "                            float minAddedValue = Math.ulp(a);" + NL + "                            if (minAddedValue > Math.abs(b)) {" + NL + "                                throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b), \"'double' or 'BigDecimal'\", \"'float/Float'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE) || ((double) a + (double) b < (double) -Float.MAX_VALUE)) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'double' or 'BigDecimal'\", \"'float/Float'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        if(checkUlp) {" + NL + "                            double minAddedValue = Math.ulp(a);" + NL + "                            if (minAddedValue > Math.abs(b)) {" + NL + "                                throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a), \"'BigDecimal'\", \"'double/Double'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE )) {" + NL + "                            throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'BigDecimal'\", \"'double/Double'\"));" + NL + "                        }" + NL + "                    }" + NL + "" + NL + "                    public static void checkedIADD(BigDecimal a, BigDecimal b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                        // Nothing, BigDecimal is infinite" + NL + "                    }" + NL + "" + NL + "                    private static String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {" + NL + "                        return \"Type overflow when adding \" + b + \" to \" + a" + NL + "                        + \", to resolve this problem, increase the precision by using \" + advicedTypes + \" type in place of \" + originalType + \".\";" + NL + "                    }" + NL + "" + NL + "                    private static String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {" + NL + "                        return \"The double precision is unsufficient to add the value \" + b + \" to \" + a" + NL + "                        + \", to resolve this problem, increase the precision by using \" + advicedTypes + \" type in place of \" + originalType + \".\";" + NL + "                    }" + NL + "                }" + NL;
  protected final String TEXT_76 = NL + "                    public static class Extended";
  protected final String TEXT_77 = "_";
  protected final String TEXT_78 = " extends ";
  protected final String TEXT_79 = " {";
  protected final String TEXT_80 = NL + "                            ";
  protected final String TEXT_81 = NL + "                    }";
  protected final String TEXT_82 = NL + NL + NL + "                // Map to the computation type and instantiate value." + NL + "                public static class PreMap";
  protected final String TEXT_83 = NL + "                implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<";
  protected final String TEXT_84 = ", ";
  protected final String TEXT_85 = ">, ";
  protected final String TEXT_86 = ", ";
  protected final String TEXT_87 = "> {" + NL + "                    public scala.Tuple2<";
  protected final String TEXT_88 = ", ";
  protected final String TEXT_89 = "> call(scala.Tuple2<";
  protected final String TEXT_90 = ", ";
  protected final String TEXT_91 = "> data) throws Exception {";
  protected final String TEXT_92 = NL + "                        ";
  protected final String TEXT_93 = " key = data._1;";
  protected final String TEXT_94 = NL + "                        ";
  protected final String TEXT_95 = " input = data._2;";
  protected final String TEXT_96 = NL + "                        ";
  protected final String TEXT_97 = " output = new ";
  protected final String TEXT_98 = "();";
  protected final String TEXT_99 = NL + "                            ";
  protected final String TEXT_100 = NL + "                        return new scala.Tuple2<";
  protected final String TEXT_101 = ", ";
  protected final String TEXT_102 = ">(key, output);" + NL + "                    }" + NL + "                }" + NL + "" + NL + "                // Combiner function" + NL + "                public static class Comb";
  protected final String TEXT_103 = " implements org.apache.spark.api.java.function.Function2<";
  protected final String TEXT_104 = ", ";
  protected final String TEXT_105 = ", ";
  protected final String TEXT_106 = "> {//$NON-NLS-1$" + NL + "                    public ";
  protected final String TEXT_107 = "  call(";
  protected final String TEXT_108 = " output, ";
  protected final String TEXT_109 = " input) throws Exception {";
  protected final String TEXT_110 = NL + "                            ";
  protected final String TEXT_111 = NL + "                        return output;" + NL + "                    }" + NL + "                }" + NL + "" + NL + "                // Final mapping to the output value" + NL + "" + NL + "                public static class Map";
  protected final String TEXT_112 = " implements org.apache.spark.api.java.function.PairFunction<scala.Tuple2<";
  protected final String TEXT_113 = ", ";
  protected final String TEXT_114 = ">, ";
  protected final String TEXT_115 = ", ";
  protected final String TEXT_116 = "> {" + NL + "                    public scala.Tuple2<";
  protected final String TEXT_117 = ", ";
  protected final String TEXT_118 = "> call(scala.Tuple2<";
  protected final String TEXT_119 = ", ";
  protected final String TEXT_120 = "> data) throws Exception {";
  protected final String TEXT_121 = NL + "                        ";
  protected final String TEXT_122 = " key = data._1;";
  protected final String TEXT_123 = NL + "                        ";
  protected final String TEXT_124 = " input = data._2;";
  protected final String TEXT_125 = NL + "                            ";
  protected final String TEXT_126 = " output = new ";
  protected final String TEXT_127 = "();";
  protected final String TEXT_128 = NL + "                            ";
  protected final String TEXT_129 = " output = input;";
  protected final String TEXT_130 = NL + "                            ";
  protected final String TEXT_131 = NL + "                                output.";
  protected final String TEXT_132 = " = BigDataParserUtils.parseTo_Date(";
  protected final String TEXT_133 = NL + "                                    ";
  protected final String TEXT_134 = ",";
  protected final String TEXT_135 = NL + "                                    ";
  protected final String TEXT_136 = ");";
  protected final String TEXT_137 = NL + "                                output.";
  protected final String TEXT_138 = " = java.nio.ByteBuffer.wrap(" + NL + "                                BigDataParserUtils.parseTo_String(";
  protected final String TEXT_139 = NL + "                                    ";
  protected final String TEXT_140 = NL + "                                ).getBytes());";
  protected final String TEXT_141 = NL + "                                output.";
  protected final String TEXT_142 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_143 = "(";
  protected final String TEXT_144 = NL + "                                    ";
  protected final String TEXT_145 = ");";
  protected final String TEXT_146 = NL + "                        return new scala.Tuple2<";
  protected final String TEXT_147 = ", ";
  protected final String TEXT_148 = ">(key, output);" + NL + "                     };" + NL + "                 }" + NL;
  protected final String TEXT_149 = NL + "                    static class UtilClass_";
  protected final String TEXT_150 = " {" + NL + "" + NL + "                        public static void checkedIADD(byte a, byte b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            byte r = (byte) (a + b);" + NL + "                            if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'short/Short'\", \"'byte/Byte'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(short a, short b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            short r = (short)(a + b);" + NL + "                            if (checkTypeOverFlow && (short)((a ^ r) & (b ^ r)) < (short)0) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'int/Integer'\", \"'short/Short'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(int a, int b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            int r = a + b;" + NL + "                            if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'long/Long'\", \"'int/Integer'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(long a, long b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            long r = a + b;" + NL + "                            if (checkTypeOverFlow && ((a ^ r) & (b ^ r)) < 0) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'BigDecimal'\", \"'long/Long'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(float a, float b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            if(checkUlp) {" + NL + "                                float minAddedValue = Math.ulp(a);" + NL + "                                if (minAddedValue > Math.abs(b)) {" + NL + "                                    throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(b), \"'double' or 'BigDecimal'\", \"'float/Float'\"));" + NL + "                                }" + NL + "                            }" + NL + "" + NL + "                            if (checkTypeOverFlow && ((double) a + (double) b > (double) Float.MAX_VALUE) || ((double) a + (double) b < (double) -Float.MAX_VALUE)) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'double' or 'BigDecimal'\", \"'float/Float'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(double a, double b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            if(checkUlp) {" + NL + "                                double minAddedValue = Math.ulp(a);" + NL + "                                if (minAddedValue > Math.abs(b)) {" + NL + "                                    throw new RuntimeException(buildPrecisionMessage(String.valueOf(a), String.valueOf(a), \"'BigDecimal'\", \"'double/Double'\"));" + NL + "                                }" + NL + "                            }" + NL + "" + NL + "                            if (checkTypeOverFlow && (a + b > (double) Double.MAX_VALUE) || (a + b < -Double.MAX_VALUE )) {" + NL + "                                throw new RuntimeException(buildOverflowMessage(String.valueOf(a), String.valueOf(b), \"'BigDecimal'\", \"'double/Double'\"));" + NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public static void checkedIADD(BigDecimal a, BigDecimal b, boolean checkTypeOverFlow, boolean checkUlp) {" + NL + "                            // Nothing, BigDecimal is infinite" + NL + "                        }" + NL + "" + NL + "                        private static String buildOverflowMessage(String a, String b, String advicedTypes, String originalType) {" + NL + "                            return \"Type overflow when adding \" + b + \" to \" + a" + NL + "                            + \", to resolve this problem, increase the precision by using \" + advicedTypes + \" type in place of \" + originalType + \".\";" + NL + "                        }" + NL + "" + NL + "                        private static String buildPrecisionMessage(String a, String b, String advicedTypes, String originalType) {" + NL + "                            return \"The double precision is unsufficient to add the value \" + b + \" to \" + a" + NL + "                            + \", to resolve this problem, increase the precision by using \" + advicedTypes + \" type in place of \" + originalType + \".\";" + NL + "                        }" + NL + "                    }" + NL;
  protected final String TEXT_151 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    


if(org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode((INode) ((BigDataCodeGeneratorArgument) argument).getArgument())) {
    
    
//Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();
List<IMetadataTable> metadatas = node.getMetadataList();

if (metadatas != null && metadatas.size() > 0) {
    IMetadataTable metadata = metadatas.get(0);
    if(metadata != null){
        List< ? extends IConnection> connections = node.getOutgoingConnections();
        List< ? extends IConnection> inConnections = node.getIncomingConnections();
        if ((connections != null) && (connections.size() > 0)
                && (inConnections != null) && (inConnections.size() > 0)) {
            IConnection connection = connections.get(0);
            IConnection inConnection = inConnections.get(0);
            String connName = connection.getName();
            String connectionTypeName = codeGenArgument.getRecordStructName(connection);
            String inConnectionTypeName = codeGenArgument.getRecordStructName(inConnection);


            List<Map<String, String>> groupBy = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__GROUPBYS__");
            List<String> groupByInput = new ArrayList<String>();
            List<String> groupByOutput = new ArrayList<String>();
            for (Map<String, String> element: groupBy) {
                groupByInput.add(element.get("INPUT_COLUMN"));
                groupByOutput.add(element.get("OUTPUT_COLUMN"));
            }

            Boolean useFinancialPrecision = "true".equals(ElementParameterParser.getValue(node, "__USE_FINANCIAL_PRECISION__"));
            boolean checkTypeOverflow = "true".equals(ElementParameterParser.getValue(node, "__CHECK_TYPE_OVERFLOW__"));
            boolean checkUlp = "true".equals(ElementParameterParser.getValue(node, "__CHECK_ULP__"));

            String computationType = connectionTypeName;
            Boolean needToExtendOutputType = useFinancialPrecision;
            List<Map<String, String>> operationTemp = (List<Map<String,String>>)
                    ElementParameterParser.getObjectValue(node, "__OPERATIONS__");
            List<IAggregateFunction> aggregateFunctions = new ArrayList<IAggregateFunction>();
            for (Map<String, String> operationRow: operationTemp) {
                if (operationRow.get("FUNCTION").equals("std_dev") || operationRow.get("FUNCTION").equals("avg")) {
                    needToExtendOutputType = true;
                }
                aggregateFunctions.add(AggregateFunctionFactory.getFunction(cid, operationRow,
                        inConnection.getMetadataTable().getListColumns(), connection.getMetadataTable().getListColumns(),
                        useFinancialPrecision, checkTypeOverflow, checkUlp));
            }

            if (needToExtendOutputType) {
                
    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_4);
    
                    for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                        
    stringBuffer.append(TEXT_5);
    stringBuffer.append(aggregateFunction.getExtendedType());
    
                    }
                    
    stringBuffer.append(TEXT_6);
    
                computationType = "Extended" + cid + "_" + computationType;
            }

                String keyType = SparkFunctionUtil.getKeyType(bigDataNode.getKeyList(), "BOTH");
            
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_23);
    
                    for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(aggregateFunction.getPreMapping());
    
                    }
                    
    stringBuffer.append(TEXT_25);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_34);
    
                    for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                        
    stringBuffer.append(TEXT_35);
    stringBuffer.append(aggregateFunction.getCombFunction());
    
                    }
                    
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_49);
    
                    if (needToExtendOutputType) {
                        
    stringBuffer.append(TEXT_50);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_52);
    
                    } else {
                        
    stringBuffer.append(TEXT_53);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_54);
    
                    }

                    for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                        
    stringBuffer.append(TEXT_55);
    stringBuffer.append(aggregateFunction.getFinalMapping(needToExtendOutputType));
    
                    }

                    int keyCount = 0;
                    for (String outputKey: groupByOutput) {
                        // get the type of each groupBy element
                        String outputType = "";
                        String datePattern = "";
                        for (IMetadataColumn column : connection.getMetadataTable().getListColumns()) {
                            if (outputKey.equals(column.getLabel())) {
                                outputType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                if (outputType.equals("java.util.Date")) {
                                    datePattern = column.getPattern();
                                }
                                break;
                            }
                        }

                        if (outputType.equals("java.util.Date")) {
                            
    stringBuffer.append(TEXT_56);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(datePattern);
    stringBuffer.append(TEXT_61);
    
                        } else if (outputType.equals("byte[]")) {
                            
    stringBuffer.append(TEXT_62);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_65);
    
                        } else {
                            
    stringBuffer.append(TEXT_66);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_70);
    
                        }
                        keyCount++;
                    }
                    
    stringBuffer.append(TEXT_71);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_73);
    
            if (checkTypeOverflow || checkUlp) {
            
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    
            } //checkTypeOverflow || checkUlp
        }
    }
}

    
} else {
    //Parse the inputs to this javajet generator.
    final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
    final INode node = (INode) codeGenArgument.getArgument();
    final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
    final String cid = node.getUniqueName();
    List<IMetadataTable> metadatas = node.getMetadataList();

    if (metadatas != null && metadatas.size() > 0) {
        IMetadataTable metadata = metadatas.get(0);
        if(metadata != null){
            List< ? extends IConnection> connections = node.getOutgoingConnections();
            List< ? extends IConnection> inConnections = node.getIncomingConnections();
            if ((connections != null) && (connections.size() > 0)
                    && (inConnections != null) && (inConnections.size() > 0)) {
                IConnection connection = connections.get(0);
                IConnection inConnection = inConnections.get(0);
                String connName = connection.getName();
                String connectionTypeName = codeGenArgument.getRecordStructName(connection);
                String inConnectionTypeName = codeGenArgument.getRecordStructName(inConnection);


                List<Map<String, String>> groupBy = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node, "__GROUPBYS__");
                List<String> groupByInput = new ArrayList<String>();
                List<String> groupByOutput = new ArrayList<String>();
                for (Map<String, String> element: groupBy) {
                    groupByInput.add(element.get("INPUT_COLUMN"));
                    groupByOutput.add(element.get("OUTPUT_COLUMN"));
                }
                Boolean useFinancialPrecision = "true".equals(ElementParameterParser.getValue(node, "__USE_FINANCIAL_PRECISION__"));
                boolean checkTypeOverflow = "true".equals(ElementParameterParser.getValue(node, "__CHECK_TYPE_OVERFLOW__"));
                boolean checkUlp = "true".equals(ElementParameterParser.getValue(node, "__CHECK_ULP__"));

                String computationType = connectionTypeName;
                Boolean needToExtendOutputType = useFinancialPrecision;
                List<Map<String, String>> operationTemp = (List<Map<String,String>>)
                        ElementParameterParser.getObjectValue(node, "__OPERATIONS__");
                List<IAggregateFunction> aggregateFunctions = new ArrayList<IAggregateFunction>();
                for (Map<String, String> operationRow: operationTemp) {
                    if (operationRow.get("FUNCTION").equals("std_dev") || operationRow.get("FUNCTION").equals("avg")) {
                        needToExtendOutputType = true;
                    }
                    aggregateFunctions.add(AggregateFunctionFactory.getFunction(cid, operationRow,
                                                                                                    inConnection.getMetadataTable().getListColumns(), connection.getMetadataTable().getListColumns(),
                            useFinancialPrecision, checkTypeOverflow, checkUlp));
                }

                if (needToExtendOutputType) {
                    
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_79);
    
                        for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                            
    stringBuffer.append(TEXT_80);
    stringBuffer.append(aggregateFunction.getExtendedType());
    
                        }
                        
    stringBuffer.append(TEXT_81);
    
                    computationType = "Extended" + cid + "_" + computationType;
                }

                String keyType = SparkFunctionUtil.getKeyType(bigDataNode.getKeyList(), "BOTH");
                
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(inConnectionTypeName);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_98);
    
                        for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                            
    stringBuffer.append(TEXT_99);
    stringBuffer.append(aggregateFunction.getPreMapping());
    
                        }
                        
    stringBuffer.append(TEXT_100);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_109);
    
                        for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                            
    stringBuffer.append(TEXT_110);
    stringBuffer.append(aggregateFunction.getCombFunction());
    
                        }
                        
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(computationType);
    stringBuffer.append(TEXT_124);
    
                        if (needToExtendOutputType) {
                            
    stringBuffer.append(TEXT_125);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_127);
    
                        } else {
                            
    stringBuffer.append(TEXT_128);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_129);
    
                        }

                        for (IAggregateFunction aggregateFunction: aggregateFunctions) {
                            
    stringBuffer.append(TEXT_130);
    stringBuffer.append(aggregateFunction.getFinalMapping(needToExtendOutputType));
    
                        }

                        int keyCount = 0;
                        for (String outputKey: groupByOutput) {
                        // get the type of each groupBy element
                            String outputType = "";
                        String datePattern = "";
                            for (IMetadataColumn column : connection.getMetadataTable().getListColumns()) {
                                if (outputKey.equals(column.getLabel())) {
                                    outputType = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                if (outputType.equals("java.util.Date")) {
                                    datePattern = column.getPattern();
                                }
                                    break;
                                }
                            }

                            if (outputType.equals("java.util.Date")) {
                                
    stringBuffer.append(TEXT_131);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(datePattern);
    stringBuffer.append(TEXT_136);
    
                            } else if (outputType.equals("byte[]")) {
                                
    stringBuffer.append(TEXT_137);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_140);
    
                            } else {
                                
    stringBuffer.append(TEXT_141);
    stringBuffer.append(outputKey);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(outputType);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(SparkFunctionUtil.getKeyValueAccessor(bigDataNode.getKeyList(), "BOTH", "key", keyCount));
    stringBuffer.append(TEXT_145);
    
                            }
                            keyCount++;
                        }
                        
    stringBuffer.append(TEXT_146);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(connectionTypeName);
    stringBuffer.append(TEXT_148);
    
                if (checkTypeOverflow || checkUlp) {
                
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_150);
    
                } //checkTypeOverflow || checkUlp
            }
        }
    }
}

    stringBuffer.append(TEXT_151);
    return stringBuffer.toString();
  }
}
