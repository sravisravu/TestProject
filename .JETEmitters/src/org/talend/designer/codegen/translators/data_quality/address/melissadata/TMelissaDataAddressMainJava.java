package org.talend.designer.codegen.translators.data_quality.address.melissadata;

import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import java.util.List;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.process.IConnectionCategory;

public class TMelissaDataAddressMainJava
{
  protected static String nl;
  public static synchronized TMelissaDataAddressMainJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMelissaDataAddressMainJava result = new TMelissaDataAddressMainJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t\t\t\t\tao_";
  protected final String TEXT_2 = ".ClearProperties();            " + NL + "" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_3 = ".SetCompany((";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = "!=null?";
  protected final String TEXT_6 = ".";
  protected final String TEXT_7 = ":\"\"));" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_8 = ".SetAddress((";
  protected final String TEXT_9 = ".";
  protected final String TEXT_10 = "!=null?";
  protected final String TEXT_11 = ".";
  protected final String TEXT_12 = ":\"\"));" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_13 = ".SetAddress2((";
  protected final String TEXT_14 = ".";
  protected final String TEXT_15 = "!=null?";
  protected final String TEXT_16 = ".";
  protected final String TEXT_17 = ":\"\"));" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_18 = ".SetCity((";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = "!=null?";
  protected final String TEXT_21 = ".";
  protected final String TEXT_22 = ":\"\"));" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_23 = ".SetState((";
  protected final String TEXT_24 = ".";
  protected final String TEXT_25 = "!=null?";
  protected final String TEXT_26 = ".";
  protected final String TEXT_27 = ":\"\"));" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_28 = ".SetZip((";
  protected final String TEXT_29 = ".";
  protected final String TEXT_30 = "!=null?";
  protected final String TEXT_31 = ".";
  protected final String TEXT_32 = ":\"\"));" + NL + "" + NL + "\t\t\t\t\tao_";
  protected final String TEXT_33 = ".VerifyAddress();";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_35 = ".";
  protected final String TEXT_36 = "=";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = ";";
  protected final String TEXT_39 = NL + "\t\t                                ";
  protected final String TEXT_40 = ".COMPANY_STANDARDIZED = ao_";
  protected final String TEXT_41 = ".GetCompany();" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_42 = ".ADDRESSLINE1_STANDARDIZED = ao_";
  protected final String TEXT_43 = ".GetParsedAddressRange() + \" \" +ao_";
  protected final String TEXT_44 = ".GetParsedPreDirection() + \" \" +ao_";
  protected final String TEXT_45 = ".GetParsedStreetName() + \" \" +ao_";
  protected final String TEXT_46 = ".GetParsedSuffix() + \" \" +ao_";
  protected final String TEXT_47 = ".GetParsedPostDirection() + \" \" +ao_";
  protected final String TEXT_48 = ".GetSuite();" + NL + "\t\t                                ";
  protected final String TEXT_49 = ".ADDRESSLINE2_STANDARDIZED = ao_";
  protected final String TEXT_50 = ".GetAddress2()  + \"  \" + ao_";
  protected final String TEXT_51 = ".GetParsedGarbage();" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_52 = ".CITY_STANDARDIZED = ao_";
  protected final String TEXT_53 = ".GetCity();" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_54 = ".STATE_STANDARDIZED = ao_";
  protected final String TEXT_55 = ".GetState();" + NL + "\t\t\t\t\t\t\t\t\t\tif(ao_";
  protected final String TEXT_56 = ".GetPlus4()!=null && ao_";
  protected final String TEXT_57 = ".GetPlus4().trim().length() >0) {\t\t\t" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_58 = ".POSTAL_STANDARDIZED = ao_";
  protected final String TEXT_59 = ".GetZip() + \"-\"  + ao_";
  protected final String TEXT_60 = ".GetPlus4();" + NL + "\t\t\t\t\t\t\t\t\t\t}else {\t\t\t" + NL + "\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_61 = ".POSTAL_STANDARDIZED = ao_";
  protected final String TEXT_62 = ".GetZip();" + NL + "\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_63 = ".COUNTRY_STANDARDIZED = ao_";
  protected final String TEXT_64 = ".GetCountryCode();" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_65 = ".RESULTS_CODE = ao_";
  protected final String TEXT_66 = ".GetResults(); " + NL + "" + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_67 = ".GetAddressKey = ao_";
  protected final String TEXT_68 = ".GetAddressKey(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_69 = ".GetSuite = ao_";
  protected final String TEXT_70 = ".GetSuite(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_71 = ".GetCountyName = ao_";
  protected final String TEXT_72 = ".GetCountyName(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_73 = ".GetAreaCode = \"\";\t\t\t\t\t\t\t\t\t//ao_";
  protected final String TEXT_74 = ".GetAreaCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_75 = ".GetRBDI = ao_";
  protected final String TEXT_76 = ".GetRBDI(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_77 = ".GetTimeZone = ao_";
  protected final String TEXT_78 = ".GetTimeZone(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_79 = ".GetTimeZoneCode = ao_";
  protected final String TEXT_80 = ".GetTimeZoneCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_81 = ".GetLongitude = \"\";\t\t\t\t\t\t\t\t\t//ao_";
  protected final String TEXT_82 = ".GetLongitude(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_83 = ".GetLatitude = \"\";\t\t\t\t\t\t\t\t\t//ao_";
  protected final String TEXT_84 = ".GetLatitude(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_85 = ".GetPmsa = ao_";
  protected final String TEXT_86 = ".GetPmsa(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_87 = ".GetMsa = ao_";
  protected final String TEXT_88 = ".GetMsa(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_89 = ".GetCarrierRoute = ao_";
  protected final String TEXT_90 = ".GetCarrierRoute(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_91 = ".GetAddressTypeCode = ao_";
  protected final String TEXT_92 = ".GetAddressTypeCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_93 = ".GetAddressTypeString = ao_";
  protected final String TEXT_94 = ".GetAddressTypeString(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_95 = ".GetCongressionalDistrict = ao_";
  protected final String TEXT_96 = ".GetCongressionalDistrict(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_97 = ".GetCountyFips = ao_";
  protected final String TEXT_98 = ".GetCountyFips(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_99 = ".GetZipType = ao_";
  protected final String TEXT_100 = ".GetZipType(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_101 = ".GetLacs = ao_";
  protected final String TEXT_102 = ".GetLACS(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_103 = ".GetLACSLinkIndicator = ao_";
  protected final String TEXT_104 = ".GetLACSLinkIndicator(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_105 = ".GetLACSLinkReturnCode = ao_";
  protected final String TEXT_106 = ".GetLACSLinkReturnCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_107 = ".GetSuiteLinkReturnCode = ao_";
  protected final String TEXT_108 = ".GetSuiteLinkReturnCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_109 = ".GetSuiteStatus = ao_";
  protected final String TEXT_110 = ".GetSuiteStatus(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_111 = ".GetUrbanization = ao_";
  protected final String TEXT_112 = ".GetUrbanization(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_113 = ".GetDPVFootnotes = ao_";
  protected final String TEXT_114 = ".GetDPVFootnotes(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_115 = ".GetDeliveryPointCode = ao_";
  protected final String TEXT_116 = ".GetDeliveryPointCode(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_117 = ".GetDeliveryPointCheckDigit = ao_";
  protected final String TEXT_118 = ".GetDeliveryPointCheckDigit(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_119 = ".GetParsedAddressRange = ao_";
  protected final String TEXT_120 = ".GetParsedAddressRange(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_121 = ".GetParsedPreDirection = ao_";
  protected final String TEXT_122 = ".GetParsedPreDirection(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_123 = ".GetParsedStreetName = ao_";
  protected final String TEXT_124 = ".GetParsedStreetName(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_125 = ".GetParsedPostDirection = ao_";
  protected final String TEXT_126 = ".GetParsedPostDirection(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_127 = ".GetParsedSuffix = ao_";
  protected final String TEXT_128 = ".GetParsedSuffix(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_129 = ".GetParsedSuiteName = ao_";
  protected final String TEXT_130 = ".GetParsedSuiteName(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_131 = ".GetParsedSuiteRange = ao_";
  protected final String TEXT_132 = ".GetParsedSuiteRange(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_133 = ".GetParsedPrivateMailboxName = ao_";
  protected final String TEXT_134 = ".GetParsedPrivateMailboxName(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_135 = ".GetParsedPrivateMailboxNumber = ao_";
  protected final String TEXT_136 = ".GetParsedPrivateMailboxNumber(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_137 = ".GetParsedRouteService = ao_";
  protected final String TEXT_138 = ".GetParsedRouteService(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_139 = ".GetParsedLockBox = ao_";
  protected final String TEXT_140 = ".GetParsedLockBox(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_141 = ".GetParsedDeliveryInstallation = ao_";
  protected final String TEXT_142 = ".GetParsedDeliveryInstallation(); " + NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_143 = ".GetParsedGarbage = ao_";
  protected final String TEXT_144 = ".GetParsedGarbage(); ";
  protected final String TEXT_145 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();

	String companyToVerify = ElementParameterParser.getValue(node, "__COMPANY_COLUMN__");
	String address1ToVerify = ElementParameterParser.getValue(node, "__ADDRESS1_COLUMN__");
	String address2ToVerify = ElementParameterParser.getValue(node, "__ADDRESS2_COLUMN__");
	String cityToVerify = ElementParameterParser.getValue(node, "__CITY_COLUMN__");
	String stateToVerify = ElementParameterParser.getValue(node, "__STATE_COLUMN__");
	String postalToVerify = ElementParameterParser.getValue(node, "__POSTAL_COLUMN__");

    	
	List<IMetadataTable> metadatas = node.getMetadataList();
	if ((metadatas!=null)&&(metadatas.size()>0)) {
		IMetadataTable metadata = metadatas.get(0);
		if (metadata!=null) {
			List< ? extends IConnection> conns = node.getIncomingConnections();
			for (IConnection conn : node.getIncomingConnections()) {
				if(conn!=null && conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_4);
    stringBuffer.append(companyToVerify);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_6);
    stringBuffer.append(companyToVerify);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_9);
    stringBuffer.append(address1ToVerify);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(address1ToVerify);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(address2ToVerify);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_16);
    stringBuffer.append(address2ToVerify);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(cityToVerify);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(cityToVerify);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_24);
    stringBuffer.append(stateToVerify);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_26);
    stringBuffer.append(stateToVerify);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_29);
    stringBuffer.append(postalToVerify);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_31);
    stringBuffer.append(postalToVerify);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    
					List< ? extends IConnection> connsout = node.getOutgoingConnections(); 
	                if (connsout!=null) {
    	            	List<IMetadataColumn> columnsout = metadata.getListColumns();
            	        for (int i=0;i<connsout.size();i++) {
            	        	IConnection connout = connsout.get(i);
                    	    if(connout.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
	                            int columnSize=columnsout.size()-47;
    	                        for (int j = 0; j < columnSize; j++) {
									IMetadataColumn columnout=columnsout.get(j);

    stringBuffer.append(TEXT_34);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnout.getLabel() );
    stringBuffer.append(TEXT_36);
    stringBuffer.append(conn.getName() );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(columnout.getLabel() );
    stringBuffer.append(TEXT_38);
    
								}

    stringBuffer.append(TEXT_39);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_58);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_62);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_137);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(connout.getName() );
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    							}
						}
					}
				}
			}
		}
	}

    stringBuffer.append(TEXT_145);
    return stringBuffer.toString();
  }
}
