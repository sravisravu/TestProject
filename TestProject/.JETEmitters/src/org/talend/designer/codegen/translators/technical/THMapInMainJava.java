package org.talend.designer.codegen.translators.technical;

import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.utils.TalendTextUtils;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class THMapInMainJava
{
  protected static String nl;
  public static synchronized THMapInMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THMapInMainJava result = new THMapInMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "  if(outMap.get(\"";
  protected final String TEXT_5 = "\")==null){";
  protected final String TEXT_6 = NL + "    ";
  protected final String TEXT_7 = ".";
  protected final String TEXT_8 = " = null;" + NL + "  }else if (outMap.get(\"";
  protected final String TEXT_9 = "\") instanceof String) { ";
  protected final String TEXT_10 = NL + "    ";
  protected final String TEXT_11 = ".";
  protected final String TEXT_12 = " = (String)outMap.get(\"";
  protected final String TEXT_13 = "\");" + NL + "  }";
  protected final String TEXT_14 = NL + "  if(outMap.get(\"";
  protected final String TEXT_15 = "\")==null){";
  protected final String TEXT_16 = NL + "    ";
  protected final String TEXT_17 = ".";
  protected final String TEXT_18 = " = false;" + NL + "  }else if (outMap.get(\"";
  protected final String TEXT_19 = "\") instanceof Boolean) { ";
  protected final String TEXT_20 = NL + "    ";
  protected final String TEXT_21 = ".";
  protected final String TEXT_22 = " = ((Boolean)outMap.get(\"";
  protected final String TEXT_23 = "\")).booleanValue();" + NL + "  }";
  protected final String TEXT_24 = NL + "  if (outMap.get(\"";
  protected final String TEXT_25 = "\")==null){";
  protected final String TEXT_26 = NL + "   ";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_29 = "\") instanceof Byte) { ";
  protected final String TEXT_30 = NL + "   ";
  protected final String TEXT_31 = ".";
  protected final String TEXT_32 = " = ((Byte)outMap.get(\"";
  protected final String TEXT_33 = "\")).byteValue();" + NL + "  }";
  protected final String TEXT_34 = NL + "  if (outMap.get(\"";
  protected final String TEXT_35 = "\")==null){";
  protected final String TEXT_36 = NL + "   ";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_39 = "\") instanceof Character) { ";
  protected final String TEXT_40 = NL + "   ";
  protected final String TEXT_41 = ".";
  protected final String TEXT_42 = " = ((Character)outMap.get(\"";
  protected final String TEXT_43 = "\")).charValue();" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_44 = "\") instanceof Short) { ";
  protected final String TEXT_45 = NL + "   ";
  protected final String TEXT_46 = ".";
  protected final String TEXT_47 = " = (char)((Short)outMap.get(\"";
  protected final String TEXT_48 = "\")).shortValue();" + NL + "  }";
  protected final String TEXT_49 = NL + "  if (outMap.get(\"";
  protected final String TEXT_50 = "\")==null){";
  protected final String TEXT_51 = NL + "   ";
  protected final String TEXT_52 = ".";
  protected final String TEXT_53 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_54 = "\") instanceof Short) { ";
  protected final String TEXT_55 = NL + "   ";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = " = ((Short)outMap.get(\"";
  protected final String TEXT_58 = "\")).shortValue();" + NL + "  }";
  protected final String TEXT_59 = NL + "  if (outMap.get(\"";
  protected final String TEXT_60 = "\")==null){";
  protected final String TEXT_61 = NL + "   ";
  protected final String TEXT_62 = ".";
  protected final String TEXT_63 = " = 0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_64 = "\") instanceof Integer) { ";
  protected final String TEXT_65 = NL + "   ";
  protected final String TEXT_66 = ".";
  protected final String TEXT_67 = " = ((Integer)outMap.get(\"";
  protected final String TEXT_68 = "\")).intValue();" + NL + "  }";
  protected final String TEXT_69 = NL + "  if (outMap.get(\"";
  protected final String TEXT_70 = "\")==null){";
  protected final String TEXT_71 = NL + "   ";
  protected final String TEXT_72 = ".";
  protected final String TEXT_73 = " = (long)0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_74 = "\") instanceof Long) { ";
  protected final String TEXT_75 = NL + "   ";
  protected final String TEXT_76 = ".";
  protected final String TEXT_77 = " = ((Long)outMap.get(\"";
  protected final String TEXT_78 = "\")).longValue();" + NL + "  }";
  protected final String TEXT_79 = NL + "  if (outMap.get(\"";
  protected final String TEXT_80 = "\")==null){";
  protected final String TEXT_81 = NL + "   ";
  protected final String TEXT_82 = ".";
  protected final String TEXT_83 = " = 0.0f;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_84 = "\") instanceof Float) { ";
  protected final String TEXT_85 = NL + "   ";
  protected final String TEXT_86 = ".";
  protected final String TEXT_87 = " = ((Float)outMap.get(\"";
  protected final String TEXT_88 = "\")).floatValue();" + NL + "  }";
  protected final String TEXT_89 = NL + "  if (outMap.get(\"";
  protected final String TEXT_90 = "\")==null){";
  protected final String TEXT_91 = NL + "   ";
  protected final String TEXT_92 = ".";
  protected final String TEXT_93 = " = 0.0;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_94 = "\") instanceof Double) { ";
  protected final String TEXT_95 = NL + "   ";
  protected final String TEXT_96 = ".";
  protected final String TEXT_97 = " = ((Double)outMap.get(\"";
  protected final String TEXT_98 = "\")).doubleValue();" + NL + "  }";
  protected final String TEXT_99 = NL + "  if (outMap.get(\"";
  protected final String TEXT_100 = "\")==null){";
  protected final String TEXT_101 = NL + "    ";
  protected final String TEXT_102 = ".";
  protected final String TEXT_103 = " = null;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_104 = "\") instanceof java.math.BigDecimal) { ";
  protected final String TEXT_105 = NL + "    ";
  protected final String TEXT_106 = ".";
  protected final String TEXT_107 = " = (java.math.BigDecimal)outMap.get(\"";
  protected final String TEXT_108 = "\");" + NL + "  }";
  protected final String TEXT_109 = NL + "  if (outMap.get(\"";
  protected final String TEXT_110 = "\")==null){";
  protected final String TEXT_111 = NL + "    ";
  protected final String TEXT_112 = ".";
  protected final String TEXT_113 = " = null;" + NL + "  } else if (outMap.get(\"";
  protected final String TEXT_114 = "\") instanceof java.util.Date) { ";
  protected final String TEXT_115 = NL + "    ";
  protected final String TEXT_116 = ".";
  protected final String TEXT_117 = " = (java.util.Date)outMap.get(\"";
  protected final String TEXT_118 = "\");" + NL + "  }";
  protected final String TEXT_119 = NL + "    java.io.StringWriter swOut_";
  protected final String TEXT_120 = " = (java.io.StringWriter)";
  protected final String TEXT_121 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_122 = "_\"+\"outputResult\");" + NL + "    if (swOut_";
  protected final String TEXT_123 = " != null)" + NL + " \t    ";
  protected final String TEXT_124 = ".";
  protected final String TEXT_125 = " = swOut_";
  protected final String TEXT_126 = ".toString();";
  protected final String TEXT_127 = NL + "\tjava.io.ByteArrayOutputStream basOut_";
  protected final String TEXT_128 = " = (java.io.ByteArrayOutputStream)";
  protected final String TEXT_129 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_130 = "_\"+\"outputResult\");" + NL + "    if (basOut_";
  protected final String TEXT_131 = " != null)" + NL + " \t    ";
  protected final String TEXT_132 = ".";
  protected final String TEXT_133 = " = (byte[])basOut_";
  protected final String TEXT_134 = ".toByteArray();";
  protected final String TEXT_135 = NL + "\t";
  protected final String TEXT_136 = ".";
  protected final String TEXT_137 = " = (java.io.InputStream)";
  protected final String TEXT_138 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_139 = "_\"+\"outputResult\");";
  protected final String TEXT_140 = NL + "  \t";
  protected final String TEXT_141 = ".";
  protected final String TEXT_142 = " = new routines.system.Document();" + NL + "\torg.dom4j.io.DocumentResult drOut_";
  protected final String TEXT_143 = " = (org.dom4j.io.DocumentResult)";
  protected final String TEXT_144 = ".this.globalMap.get(Thread" + NL + "                .currentThread().getId()+\"_";
  protected final String TEXT_145 = "_\"+\"outputResult\");" + NL + "    if (drOut_";
  protected final String TEXT_146 = " != null)" + NL + " \t    ((routines.system.Document)";
  protected final String TEXT_147 = ".";
  protected final String TEXT_148 = ").setDocument(drOut_";
  protected final String TEXT_149 = ".getDocument()); \t";
  protected final String TEXT_150 = "\t" + NL + NL;
  protected final String TEXT_151 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String processName = org.talend.core.model.utils.JavaResourcesHelper.getSimpleClassName(node.getProcess());
	String this_cid = ElementParameterParser.getValue(node, "__UNIQUE_NAME__");
	String tHMap_id = this_cid.replace("_THMAP_IN", "");
	String cid = tHMap_id;

	boolean asMap = "true".equals(ElementParameterParser.getValue(node, "__AS_MAP__"));
	boolean asString = "true".equals(ElementParameterParser.getValue(node, "__AS_STRING__"));
	boolean asBytearray = "true".equals(ElementParameterParser.getValue(node, "__AS_BYTEARRAY__"));
	boolean asInputstream = "true".equals(ElementParameterParser.getValue(node, "__AS_INPUTSTREAM__"));
	boolean asDocument = "true".equals(ElementParameterParser.getValue(node, "__AS_DOCUMENT__"));

    stringBuffer.append(TEXT_2);
    
	if (node.getOutgoingConnections()!=null) {			
for (IConnection outgoingConn : node.getOutgoingConnections()) {
		if (outgoingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {						

    stringBuffer.append(TEXT_3);
    
				String outputConnName = outgoingConn.getName();
				IMetadataTable outputMetadataTable = outgoingConn.getMetadataTable();
				for (IMetadataColumn outputCol : outputMetadataTable.getListColumns()) {
					if (asMap) {

    
						// Populate a DI rowStruct using a TDM HashMap
						if (JavaTypesManager.STRING.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_4);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_8);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_12);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_13);
    
						} else if (JavaTypesManager.BOOLEAN.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_14);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_23);
    
						} else if (JavaTypesManager.BYTE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_24);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_25);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_27);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_28);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_32);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_33);
    
						} else if (JavaTypesManager.CHARACTER.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_34);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_48);
    
						} else if (JavaTypesManager.SHORT.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_49);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_52);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_56);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_57);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_58);
    
						} else if (JavaTypesManager.INTEGER.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_60);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_62);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_63);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_66);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_67);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_68);
    
						} else if (JavaTypesManager.LONG.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_69);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_70);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_72);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_74);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_76);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_78);
    
						} else if (JavaTypesManager.FLOAT.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_79);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_80);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_87);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_88);
    
						} else if (JavaTypesManager.DOUBLE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_89);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_90);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_96);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_98);
    
						} else if (JavaTypesManager.BIGDECIMAL.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_99);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_100);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_108);
    
						} else if (JavaTypesManager.DATE.getId().equals(outputCol.getTalendType())) {

    stringBuffer.append(TEXT_109);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_114);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_116);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_117);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_118);
    
						}

    
					} else if (asString) {

    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_124);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    
						break;
					} else if (asBytearray) {

    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_132);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    
						break;
					} else if (asInputstream) {

    stringBuffer.append(TEXT_135);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_136);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_137);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_139);
    
						break;
					} else if (asDocument) {

    stringBuffer.append(TEXT_140);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_141);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_142);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_143);
    stringBuffer.append( processName );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_146);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_147);
    stringBuffer.append(outputCol.getLabel() );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_149);
    
						break;
					}
				}
				break;
			}
		}		

	}

    stringBuffer.append(TEXT_150);
    stringBuffer.append(TEXT_151);
    return stringBuffer.toString();
  }
}
