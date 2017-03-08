package org.talend.designer.codegen.translators.data_quality;

import java.util.List;
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
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class TDataMaskingSparkconfigJava
{
  protected static String nl;
  public static synchronized TDataMaskingSparkconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TDataMaskingSparkconfigJava result = new TDataMaskingSparkconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    ";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + NL + "Map<Integer, org.talend.dataquality.datamasking.Function> pairList_";
  protected final String TEXT_4 = " = new java.util.HashMap<Integer, org.talend.dataquality.datamasking.Function>();" + NL + "final org.talend.dataquality.datamasking.FunctionFactory fact_";
  protected final String TEXT_5 = " = new org.talend.dataquality.datamasking.FunctionFactory();" + NL + "final org.talend.dataquality.datamasking.TypeTester t_";
  protected final String TEXT_6 = " = new org.talend.dataquality.datamasking.TypeTester();" + NL + "final org.talend.dataquality.duplicating.RandomWrapper rnd_";
  protected final String TEXT_7 = " = new org.talend.dataquality.duplicating.RandomWrapper" + NL + "      (";
  protected final String TEXT_8 = NL + "\t     Long.valueOf(";
  protected final String TEXT_9 = ")";
  protected final String TEXT_10 = NL + "      );" + NL;
  protected final String TEXT_11 = NL + NL + "\t    ";
  protected final String TEXT_12 = " value_";
  protected final String TEXT_13 = " = null;" + NL + "\t    int type_";
  protected final String TEXT_14 = " = t_";
  protected final String TEXT_15 = ".getType(value_";
  protected final String TEXT_16 = ");" + NL + "" + NL + "\t    @SuppressWarnings(\"unchecked\")" + NL + "\t    final org.talend.dataquality.datamasking.Function<";
  protected final String TEXT_17 = "> fun_";
  protected final String TEXT_18 = " = (org.talend.dataquality.datamasking.Function<";
  protected final String TEXT_19 = ">) fact_";
  protected final String TEXT_20 = ".getFunction(org.talend.dataquality.datamasking.FunctionType.";
  protected final String TEXT_21 = ", type_";
  protected final String TEXT_22 = ");" + NL + "" + NL + "" + NL + "\t    ";
  protected final String TEXT_23 = NL + "\t\t    fun_";
  protected final String TEXT_24 = ".parse(";
  protected final String TEXT_25 = ", ";
  protected final String TEXT_26 = ", rnd_";
  protected final String TEXT_27 = ");" + NL + "\t    ";
  protected final String TEXT_28 = NL + "\t\t    fun_";
  protected final String TEXT_29 = ".parse(null, ";
  protected final String TEXT_30 = ", rnd_";
  protected final String TEXT_31 = ");" + NL + "\t    ";
  protected final String TEXT_32 = NL + NL + "\t    pairList_";
  protected final String TEXT_33 = ".put(";
  protected final String TEXT_34 = ", fun_";
  protected final String TEXT_35 = ");";
  protected final String TEXT_36 = NL + "        org.apache.spark.streaming.api.java.JavaDStream<";
  protected final String TEXT_37 = "> rdd_";
  protected final String TEXT_38 = " = rdd_";
  protected final String TEXT_39 = ".";
  protected final String TEXT_40 = NL + "        org.apache.spark.api.java.JavaRDD<";
  protected final String TEXT_41 = "> rdd_";
  protected final String TEXT_42 = " = rdd_";
  protected final String TEXT_43 = ".";
  protected final String TEXT_44 = "flatMap";
  protected final String TEXT_45 = "map";
  protected final String TEXT_46 = "(new ";
  protected final String TEXT_47 = "maskFunction(job, pairList_";
  protected final String TEXT_48 = "));" + NL + "        ";
  protected final String TEXT_49 = NL + "        ";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
// Parse the inputs to this javajet generator.
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;

final INode node = (INode) codeGenArgument.getArgument();
final IBigDataNode bigDataNode = (IBigDataNode) codeGenArgument.getArgument();
final String cid = node.getUniqueName();

List<Map<String, String>> modifTableList = (List<Map<String, String>>)ElementParameterParser.getObjectValue(node, "__MODIF_TABLE__");
String randomSeedString = ElementParameterParser.getValue(node, "__RANDOM_SEED__");
boolean keepNull = ("true").equals(ElementParameterParser.getValue(node, "__KEEP_NULL__"));
boolean keepOriginal = ("true").equals(ElementParameterParser.getValue(node, "__OUTPUT_ORIGINAL__"));

    stringBuffer.append(TEXT_2);
    

IConnection outConn = node.getOutgoingConnections().get(0);
String outConnTypeName = codeGenArgument.getRecordStructName(outConn);

String incomingConnName = null;
IMetadataTable inputMetadateTable = null;
java.util.List<IMetadataColumn> inputColumns = null;
List< ? extends IConnection> incomingConnections = node.getIncomingConnections();

String outgoingConnName = null;
IMetadataTable outputMetadataTable = null;
java.util.List<IMetadataColumn> outputColumns = null;
List< ? extends IConnection> outgoingConnections = node.getOutgoingConnections();

if (incomingConnections != null && !incomingConnections.isEmpty())
    {
	for (IConnection conn : incomingConnections)
	    {
		if (conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))
		    {
			incomingConnName = conn.getName();
			inputMetadateTable = conn.getMetadataTable();
			inputColumns = inputMetadateTable.getListColumns();
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

    stringBuffer.append(TEXT_3);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
         if(randomSeedString.length() > 0 && !"\"\"".equals(randomSeedString)) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(randomSeedString);
    stringBuffer.append(TEXT_9);
    
         }

    stringBuffer.append(TEXT_10);
    
int count = 0;
for(int i = 0; i < inputColumns.size(); i++) {

    IMetadataColumn column = inputColumns.get(i);

    for(Map<String, String> columnModifMap : modifTableList) {

	if(column.getLabel().equalsIgnoreCase(columnModifMap.get("INPUT_COLUMN"))) {
		    String function = columnModifMap.get("FUNCTION");
		    String extraParam = columnModifMap.get("EXTRA_PARAMETER");
	    	    String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);//TDQ-11328: fix compil err for type "int"(not nullable)
	    	    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
	    	    count++;

    stringBuffer.append(TEXT_11);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(function);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_22);
    
		if (extraParam.length() > 0) {
	    
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(extraParam);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(keepNull);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_27);
    
		} else {
	    
    stringBuffer.append(TEXT_28);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(keepNull);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_31);
    
		}
	    
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_35);
    
	    }
	}
    }
    if("SPARKSTREAMING".equals(node.getComponent().getType())
            && !org.talend.designer.common.tmap.LookupUtil.isNodeInBatchMode(node)) {

    stringBuffer.append(TEXT_36);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_39);
    
     }else{

    stringBuffer.append(TEXT_40);
    stringBuffer.append(outConnTypeName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outgoingConnName);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(incomingConnName);
    stringBuffer.append(TEXT_43);
    
     }
    if (keepOriginal) {
        
    stringBuffer.append(TEXT_44);
     
    } else {
        
    stringBuffer.append(TEXT_45);
    
    }

    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(TEXT_49);
    return stringBuffer.toString();
  }
}
