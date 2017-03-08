package org.talend.designer.codegen.translators.data_quality;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import org.talend.core.model.utils.NodeUtil;

public class TDataMaskingBeginJava
{
  protected static String nl;
  public static synchronized TDataMaskingBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataMaskingBeginJava result = new TDataMaskingBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "final org.talend.dataquality.datamasking.FunctionFactory fun_fact_";
  protected final String TEXT_3 = " = new org.talend.dataquality.datamasking.FunctionFactory();" + NL + "final org.talend.dataquality.datamasking.TypeTester typeTester_";
  protected final String TEXT_4 = " = new org.talend.dataquality.datamasking.TypeTester();" + NL + "final org.talend.dataquality.duplicating.RandomWrapper rnd_";
  protected final String TEXT_5 = " = new org.talend.dataquality.duplicating.RandomWrapper" + NL + "(";
  protected final String TEXT_6 = NL + "                Long.valueOf(";
  protected final String TEXT_7 = ")";
  protected final String TEXT_8 = NL + ");" + NL;
  protected final String TEXT_9 = NL + "           \t\t";
  protected final String TEXT_10 = " value_";
  protected final String TEXT_11 = " = null;" + NL + "           \t\tint type_";
  protected final String TEXT_12 = " = typeTester_";
  protected final String TEXT_13 = ".getType(value_";
  protected final String TEXT_14 = ");" + NL + "" + NL + "            \t@SuppressWarnings(\"unchecked\")" + NL + "\t\tfinal org.talend.dataquality.datamasking.functions.Function<";
  protected final String TEXT_15 = "> fun_";
  protected final String TEXT_16 = " = (org.talend.dataquality.datamasking.functions.Function<";
  protected final String TEXT_17 = ">)fun_fact_";
  protected final String TEXT_18 = ".getFunction(org.talend.dataquality.datamasking.FunctionType.";
  protected final String TEXT_19 = ", type_";
  protected final String TEXT_20 = ");";
  protected final String TEXT_21 = NL + "                    fun_";
  protected final String TEXT_22 = ".parse(";
  protected final String TEXT_23 = ", ";
  protected final String TEXT_24 = ", rnd_";
  protected final String TEXT_25 = ");";
  protected final String TEXT_26 = NL + "                    fun_";
  protected final String TEXT_27 = ".parse(null, ";
  protected final String TEXT_28 = ", rnd_";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + NL + NL + "org.talend.dataquality.datamasking.DataMasker<";
  protected final String TEXT_31 = "Struct, ";
  protected final String TEXT_32 = "Struct> duplicator_";
  protected final String TEXT_33 = NL + "= new org.talend.dataquality.datamasking.DataMasker<";
  protected final String TEXT_34 = "Struct, ";
  protected final String TEXT_35 = "Struct>()" + NL + "{" + NL + "    @Override" + NL + "    protected ";
  protected final String TEXT_36 = "Struct generateOutput(";
  protected final String TEXT_37 = "Struct originalStruct, boolean isOriginal)" + NL + "    {";
  protected final String TEXT_38 = NL + "        ";
  protected final String TEXT_39 = "Struct tmpStruct = new ";
  protected final String TEXT_40 = "Struct();" + NL;
  protected final String TEXT_41 = "      " + NL + "                tmpStruct.";
  protected final String TEXT_42 = " = originalStruct.";
  protected final String TEXT_43 = ";";
  protected final String TEXT_44 = NL + NL + "        if (isOriginal)" + NL + "        {" + NL + "            tmpStruct.ORIGINAL_MARK = true;" + NL + "        }" + NL + "        else" + NL + "        {" + NL + "            modifyOutput(tmpStruct);            " + NL + "            tmpStruct.ORIGINAL_MARK = false;" + NL + "        }" + NL + "        " + NL + "        return tmpStruct;" + NL + "    }" + NL + "" + NL + "    private void modifyOutput(";
  protected final String TEXT_45 = "Struct ";
  protected final String TEXT_46 = ")" + NL + "    {    " + NL + "        Object tmpValue_";
  protected final String TEXT_47 = " = null;";
  protected final String TEXT_48 = NL + NL + "                        tmpValue_";
  protected final String TEXT_49 = " = fun_";
  protected final String TEXT_50 = ".generateMaskedRow(";
  protected final String TEXT_51 = ".";
  protected final String TEXT_52 = ");" + NL + "                        ";
  protected final String TEXT_53 = NL + "                        if(tmpValue_";
  protected final String TEXT_54 = " == null)" + NL + "                        {";
  protected final String TEXT_55 = NL + "                            ";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = " = null;" + NL + "                        }" + NL + "                        else" + NL + "                        {";
  protected final String TEXT_58 = "    " + NL + "                        ";
  protected final String TEXT_59 = NL + "                                    ";
  protected final String TEXT_60 = ".";
  protected final String TEXT_61 = " = tmpValue_";
  protected final String TEXT_62 = ".toString();";
  protected final String TEXT_63 = NL + "                                   if(tmpValue_";
  protected final String TEXT_64 = " instanceof java.util.Date)" + NL + "                                    {";
  protected final String TEXT_65 = NL + "                                        ";
  protected final String TEXT_66 = ".";
  protected final String TEXT_67 = " = (java.util.Date)tmpValue_";
  protected final String TEXT_68 = ";" + NL + "                                    }" + NL + "                                    else" + NL + "                                    {";
  protected final String TEXT_69 = NL + "                                        ";
  protected final String TEXT_70 = ".";
  protected final String TEXT_71 = " = ParserUtils.parseTo_Date(tmpValue_";
  protected final String TEXT_72 = ".toString(), ";
  protected final String TEXT_73 = ");" + NL + "                                    }";
  protected final String TEXT_74 = NL + "                                    ";
  protected final String TEXT_75 = ".";
  protected final String TEXT_76 = " = tmpValue_";
  protected final String TEXT_77 = ".toString().getBytes();";
  protected final String TEXT_78 = "                                  ";
  protected final String TEXT_79 = NL + "                                ";
  protected final String TEXT_80 = ".";
  protected final String TEXT_81 = " = ParserUtils.parseTo_";
  protected final String TEXT_82 = "(tmpValue_";
  protected final String TEXT_83 = ".toString());";
  protected final String TEXT_84 = " ";
  protected final String TEXT_85 = NL + "                        }";
  protected final String TEXT_86 = NL + "    }" + NL + "};";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    INode node = (INode)codeGenArgument.getArgument();
    String cid = node.getUniqueName();

    List<Map<String, String>> modifTableList = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__MODIF_TABLE__");
    String randomSeedString = ElementParameterParser.getValue(node, "__RANDOM_SEED__");
    boolean keepNull = ("true").equals(ElementParameterParser.getValue(node, "__KEEP_NULL__"));

    String incomingConnName = null;
    IMetadataTable inputMetadataTable = null;
    java.util.List<IMetadataColumn> inputColumns = null;
    List< ? extends IConnection> incomingConnections = node.getIncomingConnections();

    String outgoingConnName = null;
    IMetadataTable outputMetadataTable = null;
    java.util.List<IMetadataColumn> outputColumns = null;
    List< ? extends IConnection> outgoingConnections = node.getOutgoingConnections();

    if (incomingConnections != null && !incomingConnections.isEmpty()) {    
        for (IConnection conn : incomingConnections) {
            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
                INode preNode = conn.getSource();
                while(NodeUtil.isDataAutoPropagated(preNode)&&!preNode.isSubProcessStart()) {     
                   List<? extends IConnection> preincomingConnections = preNode.getIncomingConnections();
                   if (preincomingConnections != null && !preincomingConnections.isEmpty()) {    
                      for (IConnection preNodeConn : preincomingConnections) {
                          conn=preNodeConn;
                          preNode=preNodeConn.getSource();
                          break;
                      }
                   } else {
                      break;
                   }
                }
                incomingConnName = conn.getName();              
                inputMetadataTable = conn.getMetadataTable();
                inputColumns = inputMetadataTable.getListColumns();
                break;
            }
        }
    }  
    if (outgoingConnections != null && !outgoingConnections.isEmpty())
    {    
        for (IConnection conn : outgoingConnections)
        {
            if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
            {
                outgoingConnName = conn.getName();              
                outputMetadataTable = conn.getMetadataTable();
                outputColumns = outputMetadataTable.getListColumns();
                break;
            }
        }
    }  

    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    
             if(randomSeedString.length() > 0 && !"\"\"".equals(randomSeedString)) {
        
    stringBuffer.append(TEXT_6);
    stringBuffer.append(randomSeedString);
    stringBuffer.append(TEXT_7);
    
             }
        
    stringBuffer.append(TEXT_8);
    
    int count = 0;
    for(int i = 0; i < inputColumns.size(); i++) {//for inputColumns

        IMetadataColumn column = inputColumns.get(i);

        for(Map<String, String> columnModifMap : modifTableList) {//for Map

            if(column.getLabel().equalsIgnoreCase(columnModifMap.get("INPUT_COLUMN"))) {//if column.getLabel()

                String function = columnModifMap.get("FUNCTION");
                String extraParam = columnModifMap.get("EXTRA_PARAMETER");
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);//TDQ-11328: fix compil err for type "int"(not nullable)
                
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                count++;

    stringBuffer.append(TEXT_9);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(function);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_20);
    
                if (extraParam.length() > 0) {
            
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(extraParam);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(keepNull);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_25);
    
                } else {
            
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(keepNull);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_29);
    
                }
           }//if column.getLabel()
        }//for Map
    }//for inputColumns

    stringBuffer.append(TEXT_30);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_40);
     
            for(int i = 0; i < inputColumns.size(); i++)
            {
                IMetadataColumn column = inputColumns.get(i);

        
    stringBuffer.append(TEXT_41);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_43);
    
            }
        
    stringBuffer.append(TEXT_44);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
     
        count = 0;
            for(int i = 0; i < inputColumns.size(); i++)
            {
                IMetadataColumn column = inputColumns.get(i);
            
                for(Map<String, String> columnModifMap : modifTableList)
                {
                    if(column.getLabel().equalsIgnoreCase(columnModifMap.get("INPUT_COLUMN")))
                    { 
                        String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                        JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                        count++;
        
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_52);
    if (column.isNullable()) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_57);
    }
    stringBuffer.append(TEXT_58);
                              
                                if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT)
                                {
                            
    stringBuffer.append(TEXT_59);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    
                                }
                                else if(javaType == JavaTypesManager.DATE)
                                {
                                    String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                            
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_73);
    
                                }
                                else if(javaType == JavaTypesManager.BYTE_ARRAY)
                                {
                            
    stringBuffer.append(TEXT_74);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    
                                }
                                else
                                {
                            
    stringBuffer.append(TEXT_78);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_81);
    stringBuffer.append( typeToGenerate );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_83);
    
                                } 
                            
    stringBuffer.append(TEXT_84);
    if(column.isNullable()){
    stringBuffer.append(TEXT_85);
    }
    
                } 
            }
        }
    
    stringBuffer.append(TEXT_86);
    return stringBuffer.toString();
  }
}
