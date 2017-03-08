package org.talend.designer.codegen.translators.misc;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TLogRowSparkcodeJava
{
  protected static String nl;
  public static synchronized TLogRowSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TLogRowSparkcodeJava result = new TLogRowSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "   ";
  protected final String TEXT_2 = "    ";
  protected final String TEXT_3 = NL + "    ";
  protected final String TEXT_4 = NL + "        public static class Util_";
  protected final String TEXT_5 = " {" + NL + "" + NL + "        String[] des_top = { \".\", \".\", \"-\", \"+\" };" + NL + "" + NL + "        String[] des_head = { \"|=\", \"=|\", \"-\", \"+\" };" + NL + "" + NL + "        String[] des_bottom = { \"'\", \"'\", \"-\", \"+\" };" + NL + "" + NL + "        String name=\"\";" + NL + "" + NL + "        java.util.List<String[]> list = new java.util.ArrayList<String[]>();" + NL + "" + NL + "        int[] colLengths = new int[";
  protected final String TEXT_6 = "];" + NL + "" + NL + "        public void addRow(String[] row) {" + NL + "" + NL + "            for (int i = 0; i < ";
  protected final String TEXT_7 = "; i++) {" + NL + "                if (row[i]!=null) {" + NL + "                  colLengths[i] = Math.max(colLengths[i], row[i].length());" + NL + "                }" + NL + "            }" + NL + "            list.add(row);" + NL + "        }" + NL + "" + NL + "        public void resetList(){" + NL + "            list.clear();" + NL + "        }" + NL + "" + NL + "        public void setTableName(String name) {" + NL + "" + NL + "            this.name = name;" + NL + "        }" + NL + "" + NL + "            public StringBuilder format() {" + NL + "            " + NL + "                StringBuilder sb = new StringBuilder();" + NL + " ";
  protected final String TEXT_8 = " " + NL + "            " + NL + "                    sb.append(print(des_top));" + NL + "    " + NL + "                    int totals = 0;" + NL + "                    for (int i = 0; i < colLengths.length; i++) {" + NL + "                        totals = totals + colLengths[i];" + NL + "                    }" + NL + "    " + NL + "                    // name" + NL + "                    sb.append(\"|\");" + NL + "                    int k = 0;" + NL + "                    for (k = 0; k < (totals + ";
  protected final String TEXT_9 = " - name.length()) / 2; k++) {" + NL + "                        sb.append(' ');" + NL + "                    }" + NL + "                    sb.append(name);" + NL + "                    for (int i = 0; i < totals + ";
  protected final String TEXT_10 = " - name.length() - k; i++) {" + NL + "                        sb.append(' ');" + NL + "                    }" + NL + "                    sb.append(\"|\\n\");" + NL + "" + NL + "                    // head and rows" + NL + "                    sb.append(print(des_head));" + NL + "                    for (int i = 0; i < list.size(); i++) {" + NL + "    " + NL + "                        String[] row = list.get(i);" + NL + "    " + NL + "                        java.util.Formatter formatter = new java.util.Formatter(new StringBuilder());" + NL + "                        " + NL + "                        StringBuilder sbformat = new StringBuilder();                                       ";
  protected final String TEXT_11 = "      " + NL + "                            sbformat.append(\"|%";
  protected final String TEXT_12 = "$-\");" + NL + "                            sbformat.append(colLengths[";
  protected final String TEXT_13 = "]);" + NL + "                            sbformat.append(\"s\");";
  protected final String TEXT_14 = "              " + NL + "                        sbformat.append(\"|\\n\");                    " + NL + "       " + NL + "                        formatter.format(sbformat.toString(), (Object[])row);   " + NL + "                                " + NL + "                        sb.append(formatter.toString());" + NL + "                        if (i == 0)" + NL + "                            sb.append(print(des_head)); // print the head" + NL + "                    }" + NL + "    " + NL + "                    // end" + NL + "                    sb.append(print(des_bottom));" + NL + "                    return sb;" + NL + "                }" + NL + "            " + NL + "" + NL + "            private StringBuilder print(String[] fillChars) {" + NL + "                StringBuilder sb = new StringBuilder();" + NL + "                //first column" + NL + "                sb.append(fillChars[0]);";
  protected final String TEXT_15 = "                " + NL + "                    for (int i = 0; i < colLengths[0] - fillChars[0].length() + 1; i++) {" + NL + "                        sb.append(fillChars[2]);" + NL + "                    }" + NL + "                    sb.append(fillChars[3]);";
  protected final String TEXT_16 = "                  " + NL;
  protected final String TEXT_17 = NL + "                    for (int i = 0; i < colLengths[";
  protected final String TEXT_18 = "] - fillChars[3].length() + 1; i++) {" + NL + "                        sb.append(fillChars[2]);" + NL + "                    }" + NL + "                    sb.append(fillChars[3]);";
  protected final String TEXT_19 = NL + "                ";
  protected final String TEXT_20 = "  " + NL + "                    //last column" + NL + "                    for (int i = 0; i < colLengths[";
  protected final String TEXT_21 = "] - fillChars[0].length() - fillChars[1].length()+2; i++) {" + NL + "                        sb.append(fillChars[2]);" + NL + "                    }";
  protected final String TEXT_22 = NL + "                    //last column" + NL + "                    for (int i = 0; i < colLengths[";
  protected final String TEXT_23 = "] - fillChars[1].length() + 1; i++) {" + NL + "                        sb.append(fillChars[2]);" + NL + "                    }";
  protected final String TEXT_24 = "         " + NL + "                sb.append(fillChars[1]);" + NL + "                sb.append(\"\\n\");";
  protected final String TEXT_25 = "               " + NL + "                return sb;" + NL + "            }" + NL + "        }";
  protected final String TEXT_26 = " " + NL + "        public static class Util_";
  protected final String TEXT_27 = " {" + NL + "        " + NL + "            String[] des_top = { \".\", \"-\" };" + NL + "    " + NL + "            String[] des_data = { \"-\", \"+\" };" + NL + "    " + NL + "            String[] des_frame = { \"|\" }; " + NL + "            " + NL + "            public void printLine(StringBuilder sb, int titleWidth, int dataWidth){" + NL + "            " + NL + "                sb.append(\"+\");" + NL + "                for(int i=0; i<titleWidth+2; i++)" + NL + "                    sb.append(\"-\");" + NL + "                sb.append(\"+\");" + NL + "                for(int i=0; i<dataWidth+2; i++)" + NL + "                    sb.append(\"-\");" + NL + "                sb.append(\"+\" + \"\\n\");" + NL + "            }      " + NL + "    " + NL + "            public String print(String[] row, int nbLine){" + NL + "                " + NL + "                StringBuilder sb = new StringBuilder();";
  protected final String TEXT_28 = NL + "                    String title = \"#\" + nbLine + \". \" + \"";
  protected final String TEXT_29 = "\";";
  protected final String TEXT_30 = NL + "                    String title = \"#\" + nbLine + \". \" + \"";
  protected final String TEXT_31 = "\";";
  protected final String TEXT_32 = NL + "                    String title = \"#\" + nbLine + \". \" + \"";
  protected final String TEXT_33 = "--";
  protected final String TEXT_34 = "\";";
  protected final String TEXT_35 = NL + "            " + NL + "                //step 1: get the max length of all the row[] member;" + NL + "                int dataWidth = 5;      //the length of the string \"value\"  " + NL + "                for(int i=0;i<row.length;i++) {" + NL + "                    if(row[i] == null && 4 > dataWidth) {" + NL + "                        dataWidth = 4;" + NL + "                    }" + NL + "                    else if(row[i] != null && row[i].length()>dataWidth) " + NL + "                        dataWidth = row[i].length();" + NL + "                }           ";
  protected final String TEXT_36 = "          " + NL + "                int titleWidth = ";
  protected final String TEXT_37 = ";" + NL + "                " + NL + "                int totalWidth = dataWidth + titleWidth + 5;" + NL + "                " + NL + "                //step 2: print the header with line number" + NL + "                sb.append(\".\");" + NL + "                for(int i=0 ; i<totalWidth ; i++)" + NL + "                    sb.append(\"-\");         " + NL + "                sb.append(\".\" + \"\\n\" + \"|\");" + NL + "                " + NL + "                int emptyCenterWidth = (totalWidth-title.length())/2;" + NL + "                for(int i=0 ; i<emptyCenterWidth; i++)" + NL + "                    sb.append(\" \"); " + NL + "                sb.append(title);" + NL + "                for(int i=0 ; i<totalWidth - emptyCenterWidth - title.length() ; i++)" + NL + "                    sb.append(\" \"); " + NL + "                sb.append(\"|\" + \"\\n\");" + NL + "                " + NL + "                //step 3: print \"key\" and \"value\"           " + NL + "                printLine(sb,titleWidth,dataWidth);" + NL + "                " + NL + "                sb.append(\"|\" + \" key\");" + NL + "                for(int i=0; i<titleWidth-2; i++)" + NL + "                    sb.append(\" \");" + NL + "                sb.append(\"|\" + \" value\");" + NL + "                for(int i=0; i<dataWidth-4; i++)" + NL + "                    sb.append(\" \");" + NL + "                sb.append(\"|\" + \"\\n\");" + NL + "                " + NL + "                printLine(sb,titleWidth,dataWidth);" + NL + "                " + NL + "                //step 4: print dataset";
  protected final String TEXT_38 = NL + "                //for(int i=0; i<row.length; i++){" + NL + "                    sb.append(\"| \" + \"";
  protected final String TEXT_39 = "\");" + NL + "                    for(int i=0; i<titleWidth -\"";
  protected final String TEXT_40 = "\".length()+ 1 ;i++)" + NL + "                        sb.append(\" \");" + NL + "                    sb.append(\"| \" + row[";
  protected final String TEXT_41 = "]);" + NL + "                    for(int i=0; row[";
  protected final String TEXT_42 = "] == null && i<dataWidth - 3 || row[";
  protected final String TEXT_43 = "] != null && i<dataWidth -row[";
  protected final String TEXT_44 = "].length()+ 1 ;i++)" + NL + "                        sb.append(\" \");" + NL + "                    sb.append(\"|\" + \"\\n\");" + NL + "                " + NL + "                //}" + NL + "    ";
  protected final String TEXT_45 = NL + "                //step 5: print a line gap" + NL + "                printLine(sb,titleWidth,dataWidth);" + NL + "                return sb.toString();" + NL + "            }" + NL + "        }";
  protected final String TEXT_46 = NL + NL + NL + "   public static class Displayer_";
  protected final String TEXT_47 = " {" + NL + "" + NL + "       private final ContextProperties context;" + NL + "       private final String OUTPUT_FIELD_SEPARATOR_";
  protected final String TEXT_48 = ";" + NL + "       private transient java.io.PrintStream consoleOut_";
  protected final String TEXT_49 = " = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));" + NL + "" + NL + "      public Displayer_";
  protected final String TEXT_50 = "(JobConf job){" + NL + "        this.context = new ContextProperties(job);" + NL + "        this.OUTPUT_FIELD_SEPARATOR_";
  protected final String TEXT_51 = " = ";
  protected final String TEXT_52 = ";" + NL + "        consoleOut_";
  protected final String TEXT_53 = " = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));";
  protected final String TEXT_54 = NL + "        ";
  protected final String TEXT_55 = "_createDisplayMode();" + NL + "      }" + NL + "" + NL + "      public void ";
  protected final String TEXT_56 = "_createDisplayMode(){";
  protected final String TEXT_57 = " " + NL + "             StringBuilder sbHeader_";
  protected final String TEXT_58 = " = new StringBuilder();";
  protected final String TEXT_59 = NL + "               sbHeader_";
  protected final String TEXT_60 = ".append(\"";
  protected final String TEXT_61 = "\");" + NL + "           ";
  protected final String TEXT_62 = NL + "                 sbHeader_";
  protected final String TEXT_63 = ".append(\"\\t\");";
  protected final String TEXT_64 = NL + "                       consoleOut_";
  protected final String TEXT_65 = ".println(sbHeader_";
  protected final String TEXT_66 = ".toString());" + NL + "                       consoleOut_";
  protected final String TEXT_67 = ".flush();";
  protected final String TEXT_68 = NL + NL + "      }" + NL + "" + NL + "      public void display(java.util.List<";
  protected final String TEXT_69 = "> collectedRddRecords) throws Exception {" + NL + "" + NL + "          if(consoleOut_";
  protected final String TEXT_70 = " == null){" + NL + "            consoleOut_";
  protected final String TEXT_71 = " = new java.io.PrintStream(new java.io.BufferedOutputStream(System.out));" + NL + "          }" + NL + "" + NL + "         java.util.Iterator<";
  protected final String TEXT_72 = "> recordsIterator = collectedRddRecords.iterator();" + NL + "" + NL + "          StringBuilder strBuffer_";
  protected final String TEXT_73 = " = null;";
  protected final String TEXT_74 = NL + "               Util_";
  protected final String TEXT_75 = " util_";
  protected final String TEXT_76 = " = new Util_";
  protected final String TEXT_77 = "();" + NL + "               util_";
  protected final String TEXT_78 = ".setTableName(\"";
  protected final String TEXT_79 = "\");" + NL + "               util_";
  protected final String TEXT_80 = ".addRow(new String[]{";
  protected final String TEXT_81 = "\"";
  protected final String TEXT_82 = "\",";
  protected final String TEXT_83 = "});";
  protected final String TEXT_84 = NL + "               Util_";
  protected final String TEXT_85 = " util_";
  protected final String TEXT_86 = " = new Util_";
  protected final String TEXT_87 = "();" + NL + "               int nb_line_";
  protected final String TEXT_88 = " = 0;";
  protected final String TEXT_89 = NL + "              while(recordsIterator.hasNext()){";
  protected final String TEXT_90 = NL + "                 ";
  protected final String TEXT_91 = " ";
  protected final String TEXT_92 = " = recordsIterator.next();";
  protected final String TEXT_93 = NL + "                   strBuffer_";
  protected final String TEXT_94 = " = new StringBuilder();";
  protected final String TEXT_95 = NL + "                     strBuffer_";
  protected final String TEXT_96 = ".append(\"[";
  protected final String TEXT_97 = "] \");";
  protected final String TEXT_98 = NL + "                       java.util.Formatter formatter_";
  protected final String TEXT_99 = "_";
  protected final String TEXT_100 = " = new java.util.Formatter(new StringBuilder());";
  protected final String TEXT_101 = NL + "                       strBuffer_";
  protected final String TEXT_102 = ".append(\"";
  protected final String TEXT_103 = ": \");";
  protected final String TEXT_104 = NL + "                       if(";
  protected final String TEXT_105 = ".";
  protected final String TEXT_106 = " != null) { //";
  protected final String TEXT_107 = NL + "                     strBuffer_";
  protected final String TEXT_108 = ".append(";
  protected final String TEXT_109 = NL + "                         formatter_";
  protected final String TEXT_110 = "_";
  protected final String TEXT_111 = ".format(\"%1$";
  protected final String TEXT_112 = "s\",";
  protected final String TEXT_113 = NL + "                           FormatterUtils.format_DateInUTC(";
  protected final String TEXT_114 = ".";
  protected final String TEXT_115 = ", ";
  protected final String TEXT_116 = ")";
  protected final String TEXT_117 = NL + "                                                   java.nio.charset.Charset.defaultCharset().decode(";
  protected final String TEXT_118 = ".";
  protected final String TEXT_119 = ").toString()";
  protected final String TEXT_120 = NL + "                                                   java.nio.charset.Charset.defaultCharset().decode(";
  protected final String TEXT_121 = ".";
  protected final String TEXT_122 = ").toString()";
  protected final String TEXT_123 = NL + "                           ";
  protected final String TEXT_124 = ".toPlainString()";
  protected final String TEXT_125 = NL + "                           FormatterUtils.formatUnwithE(";
  protected final String TEXT_126 = ".";
  protected final String TEXT_127 = ")";
  protected final String TEXT_128 = NL + "                               String.valueOf(";
  protected final String TEXT_129 = ".";
  protected final String TEXT_130 = ")      ";
  protected final String TEXT_131 = NL + "                         ).toString()";
  protected final String TEXT_132 = NL + "                     );";
  protected final String TEXT_133 = NL + "                       } //  ";
  protected final String TEXT_134 = NL + "                     strBuffer_";
  protected final String TEXT_135 = ".append(";
  protected final String TEXT_136 = ");";
  protected final String TEXT_137 = NL + "                             consoleOut_";
  protected final String TEXT_138 = ".println(strBuffer_";
  protected final String TEXT_139 = ".toString());" + NL + "                             consoleOut_";
  protected final String TEXT_140 = ".flush();";
  protected final String TEXT_141 = NL + "                           String[] row_";
  protected final String TEXT_142 = " = new String[";
  protected final String TEXT_143 = "];";
  protected final String TEXT_144 = NL + "                               if(";
  protected final String TEXT_145 = ".";
  protected final String TEXT_146 = " != null) { //";
  protected final String TEXT_147 = NL + "                             row_";
  protected final String TEXT_148 = "[";
  protected final String TEXT_149 = "]= ";
  protected final String TEXT_150 = NL + "                         FormatterUtils.format_DateInUTC(";
  protected final String TEXT_151 = ".";
  protected final String TEXT_152 = ", ";
  protected final String TEXT_153 = ")";
  protected final String TEXT_154 = NL + "                         java.nio.charset.Charset.defaultCharset().decode(";
  protected final String TEXT_155 = ".";
  protected final String TEXT_156 = ").toString()";
  protected final String TEXT_157 = NL + "                         ";
  protected final String TEXT_158 = ".toPlainString()";
  protected final String TEXT_159 = NL + "                         FormatterUtils.formatUnwithE(";
  protected final String TEXT_160 = ".";
  protected final String TEXT_161 = ")";
  protected final String TEXT_162 = NL + "                             String.valueOf(";
  protected final String TEXT_163 = ".";
  protected final String TEXT_164 = ")      ";
  protected final String TEXT_165 = NL + "                     ;";
  protected final String TEXT_166 = NL + "                               } //";
  protected final String TEXT_167 = NL + "                             util_";
  protected final String TEXT_168 = ".addRow(row_";
  protected final String TEXT_169 = ");";
  protected final String TEXT_170 = NL + "                             nb_line_";
  protected final String TEXT_171 = "++;" + NL + "                             consoleOut_";
  protected final String TEXT_172 = ".println(util_";
  protected final String TEXT_173 = ".print(row_";
  protected final String TEXT_174 = ",nb_line_";
  protected final String TEXT_175 = "));" + NL + "                             consoleOut_";
  protected final String TEXT_176 = ".flush();";
  protected final String TEXT_177 = NL + "               }";
  protected final String TEXT_178 = NL + "                  consoleOut_";
  protected final String TEXT_179 = ".println(util_";
  protected final String TEXT_180 = ".format().toString());" + NL + "                  consoleOut_";
  protected final String TEXT_181 = ".flush();";
  protected final String TEXT_182 = NL + "      }" + NL + "   }";
  protected final String TEXT_183 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    

      BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
      INode node = (INode)codeGenArgument.getArgument();  
      String cid = node.getUniqueName();

      List<IMetadataTable> metadatas = node.getMetadataList();
      IMetadataTable metadata = null;
      if(metadatas != null && metadatas.size() > 0){
          metadata = metadatas.get(0);
      }

      String inConnTypeName = null;
      String connName = null;
      List<IMetadataColumn> columns = null;

      if(metadata != null){
        boolean compress = "true".equals(ElementParameterParser.getValue(node, "__COMPRESS__"));
        String compression = ElementParameterParser.getValue(node, "__COMPRESSION__");
        List<? extends IConnection> inConns = node.getIncomingConnections(EConnectionType.FLOW_MAIN);
        if(inConns != null && inConns.size() > 0){
          IConnection inConn = inConns.get(0); 
          connName = inConn.getName();
          inConnTypeName = codeGenArgument.getRecordStructName(inConn);
        }
        columns = metadata.getListColumns();
      }

      String printUniqueName = ElementParameterParser.getValue(node,"__PRINT_UNIQUE_NAME__");
      String printColumnNames = ElementParameterParser.getValue(node,"__PRINT_COLNAMES__");
      String useFixedLength = ElementParameterParser.getValue(node,"__USE_FIXED_LENGTH__");
      List<Map<String, String>> lengths = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__LENGTHS__");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    
    String label = ElementParameterParser.getValue(node, "__LABEL__");
    if(("__UNIQUE_NAME__").equals(label))
        label=cid;
    boolean tablePrint = ("true").equals(ElementParameterParser.getValue(node,"__TABLE_PRINT__"));
    String printHeader = ElementParameterParser.getValue(node,"__PRINT_HEADER__");
    boolean vertical = ("true").equals(ElementParameterParser.getValue(node,"__VERTICAL__"));
    boolean uniquePrint = ("true").equals(ElementParameterParser.getValue(node,"__PRINT_UNIQUE__"));
    boolean titlePrint = ("true").equals(ElementParameterParser.getValue(node,"__PRINT_LABEL__"));
    boolean uniqueTitlePrint = ("true").equals(ElementParameterParser.getValue(node,"__PRINT_UNIQUE_LABEL__"));
    boolean basic = !(tablePrint||vertical);
    
    int sizeColumns = columns.size();

// Doesn't import the tLogRow_util from mrcode as in Spark Batch
// We need to instantiate Util_tLogRow object inside the ForEach object
// So the class was set to public static here.

    
    stringBuffer.append(TEXT_3);
    
    if(tablePrint) { // table display mode
        
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(sizeColumns );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(sizeColumns );
    stringBuffer.append(TEXT_7);
     
                if (sizeColumns > 0) { //more than one column
                
    stringBuffer.append(TEXT_8);
    stringBuffer.append(sizeColumns-1 );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(sizeColumns-1 );
    stringBuffer.append(TEXT_10);
    
                        for ( int i = 0; i < sizeColumns; i++) {
                            
    stringBuffer.append(TEXT_11);
    stringBuffer.append(i+1 );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_13);
    
                        }
                        
    stringBuffer.append(TEXT_14);
     
                if (sizeColumns > 1) { 
                    
    stringBuffer.append(TEXT_15);
    
                }
                
    stringBuffer.append(TEXT_16);
    
                for(int i = 0; i< sizeColumns-2;i++) {
                    
    stringBuffer.append(TEXT_17);
    stringBuffer.append(i+1 );
    stringBuffer.append(TEXT_18);
    
                }
                
    stringBuffer.append(TEXT_19);
     
                if (sizeColumns == 1) { 
                    
    stringBuffer.append(TEXT_20);
    stringBuffer.append(sizeColumns-1 );
    stringBuffer.append(TEXT_21);
     
                } else if (sizeColumns > 1){
                    
    stringBuffer.append(TEXT_22);
    stringBuffer.append(sizeColumns-1 );
    stringBuffer.append(TEXT_23);
    
                }
                
    stringBuffer.append(TEXT_24);
     
            } 
            
    stringBuffer.append(TEXT_25);
    
    }
    if(vertical) { 
    
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    
                if(uniquePrint) {
                    
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
                } else if(titlePrint) {
                    
    stringBuffer.append(TEXT_30);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_31);
    
                } else if(uniqueTitlePrint) {
                    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_34);
    
                }
                
    stringBuffer.append(TEXT_35);
    
                int titleWidth = 3;    //the length of the string 'key'
                for(IMetadataColumn column:columns)
                    if(column.getLabel().length()>titleWidth) titleWidth = column.getLabel().length();
                
    stringBuffer.append(TEXT_36);
    stringBuffer.append(titleWidth);
    stringBuffer.append(TEXT_37);
    
                int count = 0;
                for(IMetadataColumn column:columns){
                
    stringBuffer.append(TEXT_38);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_44);
    
                    count++;
                }
    stringBuffer.append(TEXT_45);
    
    }
    
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_51);
    stringBuffer.append(ElementParameterParser.getValue(node, "__FIELDSEPARATOR__") );
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    
         if(basic) {// basic display mode
           if (("true").equals(printHeader)) {
           
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
              
               for (int i = 0; i < sizeColumns; i++) {
               IMetadataColumn column = columns.get(i);
             
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_61);
    
               if(i == sizeColumns-1) break;               
               
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid);
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
         }
         
    stringBuffer.append(TEXT_68);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    
            // Table and Vertical object initialisation
           if(tablePrint) { // table display mode
           
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_80);
    for(int i=0;i< columns.size();i++){
    stringBuffer.append(TEXT_81);
    stringBuffer.append(columns.get(i).getLabel() );
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    
            }
            if(vertical) {
         
    stringBuffer.append(TEXT_84);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
     
             }

    stringBuffer.append(TEXT_89);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(inConnTypeName);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_92);
    
                   if (basic||vertical) {  // don't print the table form//***
                 
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
     
                     if (("true").equals(printUniqueName)) {//print the component name.
                   
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    
                     }
                     for (int i = 0; i < sizeColumns; i++) {//5
                       IMetadataColumn column = columns.get(i);
                     JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                     if (("true").equals(useFixedLength)) {//fix the column length
                     
    stringBuffer.append(TEXT_98);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    
                     }
                     if (("true").equals(printColumnNames)) {//print the schema name
                     
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_103);
    
                     }
                     boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType( javaType, column.isNullable());
                     if(!isPrimitive) { //begin
                     
    stringBuffer.append(TEXT_104);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_105);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_106);
    
                     }
                     
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    
                       if (("true").equals(useFixedLength)) {//fixed the column length
                       
    stringBuffer.append(TEXT_109);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(lengths.get(i).get("LENGTH") );
    stringBuffer.append(TEXT_112);
    
                       }
                       
    
                           String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                           if (javaType == JavaTypesManager.DATE && pattern != null && pattern.trim().length() != 0) {//Date
                         
    stringBuffer.append(TEXT_113);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_114);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_115);
    stringBuffer.append( pattern );
    stringBuffer.append(TEXT_116);
            
                         } else if (javaType == JavaTypesManager.BYTE_ARRAY) {//byte[]
                                            // Avro metadata table compatibility
                             if (codeGenArgument.getRecordStructGenerator().isByteArrayWrappedInBuffer()) {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_118);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_119);
    } else {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_122);
    }
    
                         } else if (javaType == JavaTypesManager.BIGDECIMAL) {
                         
    stringBuffer.append(TEXT_123);
    stringBuffer.append(column.getPrecision() == null? connName + "." + column.getLabel() : connName + "." + column.getLabel() + ".setScale(" + column.getPrecision() + ", java.math.RoundingMode.HALF_UP)" );
    stringBuffer.append(TEXT_124);
    
                         } else if (javaType == JavaTypesManager.DOUBLE || javaType == JavaTypesManager.FLOAT ) {
                         
    stringBuffer.append(TEXT_125);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_126);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_127);
    
                         } else {//others
                         
    stringBuffer.append(TEXT_128);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_130);
            
                         }
                         
    
                       if (("true").equals(useFixedLength)) {//fixed the column length
                       
    stringBuffer.append(TEXT_131);
    
                       }
                       
    stringBuffer.append(TEXT_132);
    
                     if(!isPrimitive) {//end
                     
    stringBuffer.append(TEXT_133);
    
                     }
                     if(i == sizeColumns-1) break;
                     
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(ElementParameterParser.getValue(node, "__FIELDSEPARATOR__") );
    stringBuffer.append(TEXT_136);
    
                   }
                 }
                 if (basic) { 
                 
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    
                         }
                         if(tablePrint || vertical) { //print the table and vertical model//***
                         
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(sizeColumns );
    stringBuffer.append(TEXT_143);
    
                           for (int i = 0; i < sizeColumns; i++) {//5
               
                       IMetadataColumn column = columns.get(i);
                       JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                       boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType( javaType, column.isNullable());
                     if(!isPrimitive) { //begin
                             
    stringBuffer.append(TEXT_144);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_145);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_146);
    
                             }
                             
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(i );
    stringBuffer.append(TEXT_149);
    
                         String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                         if (javaType == JavaTypesManager.DATE && pattern != null && pattern.trim().length() != 0) {//Date
                       
    stringBuffer.append(TEXT_150);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_151);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_152);
    stringBuffer.append( pattern );
    stringBuffer.append(TEXT_153);
            
                       } else if (javaType == JavaTypesManager.BYTE_ARRAY) {//byte[]
                       
    stringBuffer.append(TEXT_154);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_155);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_156);
    
                       } else if (javaType == JavaTypesManager.BIGDECIMAL) {
                       
    stringBuffer.append(TEXT_157);
    stringBuffer.append(column.getPrecision() == null? connName + "." + column.getLabel() : connName + "." + column.getLabel() + ".setScale(" + column.getPrecision() + ", java.math.RoundingMode.HALF_UP)" );
    stringBuffer.append(TEXT_158);
    
                       } else if (javaType == JavaTypesManager.DOUBLE || javaType == JavaTypesManager.FLOAT ) {
                       
    stringBuffer.append(TEXT_159);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_160);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_161);
    
                       } else {//others
                       
    stringBuffer.append(TEXT_162);
    stringBuffer.append(connName );
    stringBuffer.append(TEXT_163);
    stringBuffer.append(column.getLabel() );
    stringBuffer.append(TEXT_164);
            
                       }
                       
    stringBuffer.append(TEXT_165);
    
                     if(!isPrimitive) { //end
                             
    stringBuffer.append(TEXT_166);
    
                             }
                           }//5
                           if(tablePrint){
                           
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_169);
    
                           }else{
                           
    stringBuffer.append(TEXT_170);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_176);
    
                           }
                       }
                       
    stringBuffer.append(TEXT_177);
    
         if(tablePrint){
         
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_181);
    
         }
     
    stringBuffer.append(TEXT_182);
    

    stringBuffer.append(TEXT_183);
    return stringBuffer.toString();
  }
}
