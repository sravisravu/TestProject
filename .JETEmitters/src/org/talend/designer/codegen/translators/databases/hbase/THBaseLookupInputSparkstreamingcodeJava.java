package org.talend.designer.codegen.translators.databases.hbase;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmap.TMapAdapter;

public class THBaseLookupInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized THBaseLookupInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseLookupInputSparkstreamingcodeJava result = new THBaseLookupInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL;
  protected final String TEXT_4 = NL + "\t\t\t\tbyte[] bytesResult = null;" + NL + "\t\t\t\tString stringResult = null;";
  protected final String TEXT_5 = NL + "\t\t\t\tbytesResult = scannerResult.getValue(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_6 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_7 = "\"));" + NL + "\t\t\t\tstringResult = org.apache.hadoop.hbase.util.Bytes.toString(bytesResult);" + NL + "\t\t\t\tif(stringResult != null && stringResult.length() > 0) {";
  protected final String TEXT_8 = NL + "\t\t\t\t\t";
  protected final String TEXT_9 = ".";
  protected final String TEXT_10 = " = stringResult;";
  protected final String TEXT_11 = " " + NL + "\t\t\t\t\t";
  protected final String TEXT_12 = ".";
  protected final String TEXT_13 = " = java.nio.ByteBuffer.wrap(bytesResult);";
  protected final String TEXT_14 = " " + NL + "\t\t\t\t\t";
  protected final String TEXT_15 = ".";
  protected final String TEXT_16 = " = BigDataParserUtils.parseTo_Date(stringResult, ";
  protected final String TEXT_17 = ");";
  protected final String TEXT_18 = NL + "\t\t\t\t\t";
  protected final String TEXT_19 = ".";
  protected final String TEXT_20 = " = org.apache.hadoop.hbase.util.Bytes.toInt(bytesResult);  ";
  protected final String TEXT_21 = NL + "\t\t\t\t\t";
  protected final String TEXT_22 = ".";
  protected final String TEXT_23 = " = (char) org.apache.hadoop.hbase.util.Bytes.toInt(bytesResult);  ";
  protected final String TEXT_24 = NL + "\t\t\t\t\t";
  protected final String TEXT_25 = ".";
  protected final String TEXT_26 = " = org.apache.hadoop.hbase.util.Bytes.to";
  protected final String TEXT_27 = "(bytesResult);  ";
  protected final String TEXT_28 = NL + "\t\t\t\t\t";
  protected final String TEXT_29 = ".";
  protected final String TEXT_30 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_31 = "(stringResult);";
  protected final String TEXT_32 = NL + "\t\t\t\t}else{";
  protected final String TEXT_33 = NL + "\t\t\t\t\t";
  protected final String TEXT_34 = ".";
  protected final String TEXT_35 = " = ";
  protected final String TEXT_36 = ";";
  protected final String TEXT_37 = NL + "\t\t\t\t\t";
  protected final String TEXT_38 = ".";
  protected final String TEXT_39 = " = null;";
  protected final String TEXT_40 = NL + "\t\t\t\t\tthrow new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_41 = "' in '";
  protected final String TEXT_42 = "' connection, value is invalid or this column should be nullable or have a default value.\");\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_43 = NL + "\t\t\t\t}";
  protected final String TEXT_44 = NL;
  protected final String TEXT_45 = NL + NL + "public static class ";
  protected final String TEXT_46 = "_FlatMapper extends org.talend.bigdata.dataflow.hmap.HMapContextConfigurableBase implements org.talend.bigdata.dataflow.functions.FlatMapper<org.apache.avro.generic.IndexedRecord, ";
  protected final String TEXT_47 = "> {" + NL + "" + NL + "\tprivate transient org.apache.hadoop.conf.Configuration conf;" + NL + "\tprivate transient org.apache.hadoop.hbase.client.HTable table;" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_48 = "_FlatMapper(JobConf job) {" + NL + "\t\tthis.context = new ContextProperties(job);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void prepare() throws Exception {" + NL + "\t\tconf = org.apache.hadoop.hbase.HBaseConfiguration.create();" + NL + "\t\tconf.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_49 = ");" + NL + "\t\tconf.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_50 = ");" + NL + "\t\tconf.set(\"hbase.cluster.distributed\", \"true\");";
  protected final String TEXT_51 = NL + "\t\tconf.set(\"zookeeper.znode.parent\", ";
  protected final String TEXT_52 = ");";
  protected final String TEXT_53 = NL + "\t\tconf.set(\"hbase.table.namespace.mappings\", ";
  protected final String TEXT_54 = ");";
  protected final String TEXT_55 = NL + "\t        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_56 = ");" + NL + "            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_57 = ");";
  protected final String TEXT_58 = NL + "\t\tconf.set(\"hbase.master.kerberos.principal\", ";
  protected final String TEXT_59 = ");" + NL + "\t\tconf.set(\"hbase.regionserver.kerberos.principal\", ";
  protected final String TEXT_60 = ");" + NL + "\t\tconf.set(\"hbase.security.authorization\", \"true\");" + NL + "\t\tconf.set(\"hbase.security.authentication\", \"kerberos\");";
  protected final String TEXT_61 = NL + "\t\torg.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_62 = ", ";
  protected final String TEXT_63 = ");";
  protected final String TEXT_64 = NL + "            com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_65 = " = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "            maprLogin_";
  protected final String TEXT_66 = ".getMapRCredentialsViaKerberos(";
  protected final String TEXT_67 = ",";
  protected final String TEXT_68 = NL + "                                                            ";
  protected final String TEXT_69 = ");";
  protected final String TEXT_70 = NL + "                final String decryptedPasswordhbase_";
  protected final String TEXT_71 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_72 = ");";
  protected final String TEXT_73 = NL + "                final String decryptedPasswordhbase_";
  protected final String TEXT_74 = " = ";
  protected final String TEXT_75 = ";";
  protected final String TEXT_76 = NL + "            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_77 = ");" + NL + "            com.mapr.login.client.MapRLoginHttpsClient maprLogin_";
  protected final String TEXT_78 = " = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_79 = NL + "                System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = NL + "                maprLogin_";
  protected final String TEXT_82 = ".setCheckUGI(false);";
  protected final String TEXT_83 = NL + "            maprLogin_";
  protected final String TEXT_84 = ".getMapRCredentialsViaPassword(";
  protected final String TEXT_85 = ",";
  protected final String TEXT_86 = NL + "                                                             ";
  protected final String TEXT_87 = "," + NL + "                                                             decryptedPasswordhbase_";
  protected final String TEXT_88 = ",";
  protected final String TEXT_89 = NL + "                                                             ";
  protected final String TEXT_90 = ");";
  protected final String TEXT_91 = NL + "\t\tconf.set(";
  protected final String TEXT_92 = ",";
  protected final String TEXT_93 = ");";
  protected final String TEXT_94 = NL + NL + "\t\ttable = new org.apache.hadoop.hbase.client.HTable(conf, ";
  protected final String TEXT_95 = ");" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic Iterable<";
  protected final String TEXT_96 = "> flatMap(org.apache.avro.generic.IndexedRecord joinKeys) throws Exception {" + NL + "\t\tList<";
  protected final String TEXT_97 = "> result = new ArrayList<>();" + NL + "\t\t";
  protected final String TEXT_98 = " ";
  protected final String TEXT_99 = " = getHMapContext().getInputRecord(\"";
  protected final String TEXT_100 = "\");" + NL + "" + NL + "\t\torg.apache.hadoop.hbase.client.ResultScanner resultScanner = table.getScanner(getScan(";
  protected final String TEXT_101 = "));" + NL + "\t\ttry {" + NL + "\t\t\tfor (org.apache.hadoop.hbase.client.Result scannerResult = resultScanner.next(); scannerResult != null; scannerResult = resultScanner.next()) {" + NL + "        \t\t";
  protected final String TEXT_102 = " ";
  protected final String TEXT_103 = " = new ";
  protected final String TEXT_104 = "();";
  protected final String TEXT_105 = NL + "\t\t\t\tresult.add(";
  protected final String TEXT_106 = ");" + NL + "    \t\t}" + NL + "\t\t} catch (Exception e) {" + NL + "    \t\tthrow new RuntimeException(\"Lookup from table \"+";
  protected final String TEXT_107 = "+\" has failed : \"+e.getMessage(), e);" + NL + "\t\t} finally {" + NL + "\t\t\tif(resultScanner != null) {" + NL + "\t\t\t\tresultScanner.close();" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void cleanup() throws Exception {" + NL + "\t\tif(table != null) {" + NL + "\t\t\ttable.close();" + NL + "\t\t}";
  protected final String TEXT_108 = NL + "        org.apache.hadoop.hbase.client.HConnection hConnection = org.apache.hadoop.hbase.client.HConnectionManager.getConnection(conf);" + NL + "        if ((hConnection != null) && (!hConnection.isClosed())) {" + NL + "            hConnection.close();" + NL + "        }";
  protected final String TEXT_109 = NL + "\t\torg.apache.hadoop.hbase.client.HConnectionManager.deleteConnection(conf, true);";
  protected final String TEXT_110 = NL + "\t}" + NL + "" + NL + "\tprivate org.apache.hadoop.hbase.client.Scan getScan(";
  protected final String TEXT_111 = " ";
  protected final String TEXT_112 = ") {";
  protected final String TEXT_113 = NL + "\t\torg.apache.hadoop.hbase.client.Scan scan =  new org.apache.hadoop.hbase.client.Scan(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_114 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_115 = "));";
  protected final String TEXT_116 = NL + "\t\torg.apache.hadoop.hbase.client.Scan scan = new org.apache.hadoop.hbase.client.Scan();";
  protected final String TEXT_117 = NL + "\t\tscan.addColumn(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_118 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_119 = "\"));";
  protected final String TEXT_120 = NL + "\t\tscan.setFilter(getFilterList(scan, ";
  protected final String TEXT_121 = "));";
  protected final String TEXT_122 = NL + "\t\treturn scan;" + NL + "\t}" + NL;
  protected final String TEXT_123 = NL + "\tprivate org.apache.hadoop.hbase.filter.FilterList getFilterList(org.apache.hadoop.hbase.client.Scan scan, ";
  protected final String TEXT_124 = " ";
  protected final String TEXT_125 = ") {" + NL + "\t\torg.apache.hadoop.hbase.filter.FilterList filterList = new org.apache.hadoop.hbase.filter.FilterList(org.apache.hadoop.hbase.filter.FilterList.Operator.";
  protected final String TEXT_126 = ");";
  protected final String TEXT_127 = NL + "\t\tString [] multipleValues = null;" + NL + "\t\tbyte [][] multipleBytesValues = null;";
  protected final String TEXT_128 = NL + "\t\torg.apache.hadoop.hbase.filter.Filter filter\t= null;";
  protected final String TEXT_129 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_130 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_131 = "), org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_132 = ", org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_133 = "));";
  protected final String TEXT_134 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_135 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_136 = "), org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_137 = ", new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_138 = "));";
  protected final String TEXT_139 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_140 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_141 = "), org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_142 = ", new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_143 = "));";
  protected final String TEXT_144 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.FamilyFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_145 = ",new org.apache.hadoop.hbase.filter.BinaryComparator(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_146 = ")));";
  protected final String TEXT_147 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.FamilyFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_148 = ",new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_149 = "));";
  protected final String TEXT_150 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.FamilyFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_151 = ",new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_152 = "));";
  protected final String TEXT_153 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.QualifierFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_154 = ",new org.apache.hadoop.hbase.filter.BinaryComparator(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_155 = ")));";
  protected final String TEXT_156 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.QualifierFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_157 = ",new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_158 = "));";
  protected final String TEXT_159 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.QualifierFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_160 = ",new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_161 = "));";
  protected final String TEXT_162 = NL + "\t\tscan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_163 = "));";
  protected final String TEXT_164 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.ColumnPrefixFilter(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_165 = "));";
  protected final String TEXT_166 = NL + "\t\tscan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_167 = "));";
  protected final String TEXT_168 = NL + "\t\tif(";
  protected final String TEXT_169 = "!=null && !\"\".equals(";
  protected final String TEXT_170 = ")){" + NL + "\t\t\tmultipleValues = ";
  protected final String TEXT_171 = ".split(\",\");" + NL + "\t\t\tmultipleBytesValues = new byte [multipleValues.length] [];" + NL + "\t\t\tfor(int i=0;i< multipleValues.length;i++){" + NL + "\t\t\t\tmultipleBytesValues[i] = org.apache.hadoop.hbase.util.Bytes.toBytes(multipleValues[i]);" + NL + "\t\t\t}" + NL + "\t\t\tfilter = new org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter(multipleBytesValues);" + NL + "\t\t}";
  protected final String TEXT_172 = NL + "\t\tscan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_173 = "));";
  protected final String TEXT_174 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.ColumnRangeFilter(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_175 = ".split(\",\")[0]),true,org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_176 = ".split(\",\")[1]),true);";
  protected final String TEXT_177 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.RowFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_178 = ",new org.apache.hadoop.hbase.filter.BinaryComparator(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_179 = ")));";
  protected final String TEXT_180 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.RowFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_181 = ",new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_182 = "));";
  protected final String TEXT_183 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.RowFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_184 = ",new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_185 = "));";
  protected final String TEXT_186 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.ValueFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_187 = ",new org.apache.hadoop.hbase.filter.BinaryComparator(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_188 = ")));";
  protected final String TEXT_189 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.ValueFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_190 = ",new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_191 = "));";
  protected final String TEXT_192 = NL + "\t\tfilter = new org.apache.hadoop.hbase.filter.ValueFilter(org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_193 = ",new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_194 = "));";
  protected final String TEXT_195 = NL + "\t\tfilterList.addFilter(filter);";
  protected final String TEXT_196 = NL + "\t\treturn filterList;" + NL + "\t}";
  protected final String TEXT_197 = NL + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    
final class HBaseConfigurationUtil {

	protected INode configurationNode;
	protected org.talend.hadoop.distribution.component.HBaseComponent hbaseDistrib = null;
	
	public HBaseConfigurationUtil(INode configurationNode) {
		this.configurationNode = configurationNode;
   	    String hbaseDistribution = ElementParameterParser.getValue(configurationNode, "__DISTRIBUTION__");
    	String hbaseVersion = getVersion();
	    try {
	        this.hbaseDistrib = (org.talend.hadoop.distribution.component.HBaseComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(hbaseDistribution, hbaseVersion);
	    } catch (java.lang.Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public String getZookeeperUrl() {
		return ElementParameterParser.getValue(configurationNode, "__ZOOKEEPER_QUORUM__");
	}
	
	public String getZookeeperPort() {
		return ElementParameterParser.getValue(configurationNode, "__ZOOKEEPER_CLIENT_PORT__");
	}
	
	public boolean isUsingKerberos() {
		return "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_KRB__"));
	}
	
	public boolean isUsingKeytab() {
		return "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_KEYTAB__"));
	}
	
	public String getUserPrincipal() {
		return ElementParameterParser.getValue(configurationNode, "__PRINCIPAL__");
	}
	
	public String getKeytabPath() {
		return ElementParameterParser.getValue(configurationNode, "__KEYTAB_PATH__");
	}
	
	public String getHBaseMasterPrincipal() {
		return ElementParameterParser.getValue(configurationNode, "__HBASE_MASTER_PRINCIPAL__");
	}
	
	public String getHBaseRegionServerPrincipal() {
		return ElementParameterParser.getValue(configurationNode, "__HBASE_REGIONSERVER_PRINCIPAL__");
	}
	
	public String getVersion() {
		return ElementParameterParser.getValue(configurationNode, "__HBASE_VERSION__");
	}
	
	public boolean isZNodeParentSet() {
		return "true".equals(ElementParameterParser.getValue(configurationNode, "__SET_ZNODE_PARENT__"));
	}
	
	public String getZNodeParent() {
		return ElementParameterParser.getValue(configurationNode, "__ZNODE_PARENT__");
	}
	
	public List<Map<String, String>> getHBaseParameters() {
		return (List<Map<String,String>>) ElementParameterParser.getObjectValue(configurationNode, "__HBASE_PARAMETERS__");
   }
   
   public boolean isCustomDistribution() {
   	return this.hbaseDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;
   }
   
   public boolean supportNewHBaseApi() {
   		return this.hbaseDistrib.doSupportNewHBaseAPI();
   }
   
   	public boolean useTableNsMapping(INode currentNode) {
   	    return this.hbaseDistrib.doSupportMapRDB() && ElementParameterParser.getBooleanValue(currentNode, "__SET_TABLE_NS_MAPPING__")
   	            && getTableNsMapping(currentNode) != null && !getTableNsMapping(currentNode).equals(""); 
	}
	
	public String getTableNsMapping(INode currentNode) {
		return ElementParameterParser.getValue(currentNode, "__TABLE_NS_MAPPING__");
	}

   public boolean supportMapRTicket() {
        return this.hbaseDistrib.doSupportMapRTicket();
   }

	public boolean isUsingMapRTicket() {
        return "true".equals(ElementParameterParser.getValue(configurationNode, "__USE_MAPRTICKET__"));
    }

    public String getUsername() {
        return ElementParameterParser.getValue(configurationNode, "__USERNAME__");
    }

    public String getMapRTicketCluster() {
        return ElementParameterParser.getValue(configurationNode, "__MAPRTICKET_CLUSTER__");
    }

    public String getMapRTicketDuration() {
        return ElementParameterParser.getValue(configurationNode, "__MAPRTICKET_DURATION__");
    }

    public String getMapRHomeDir() {
        if (ElementParameterParser.getBooleanValue(configurationNode, "__SET_MAPR_HOME_DIR__")) {
            return ElementParameterParser.getValue(configurationNode, "__MAPR_HOME_DIR__");
        } else {
            return "\"/opt/mapr\"";
        }
    }

    public boolean isUsingMapRHadoopLogin() {
        return ElementParameterParser.getBooleanValue(configurationNode, "__SET_HADOOP_LOGIN__");
    }

    public String getMapRHadoopLogin() {
        if (ElementParameterParser.getBooleanValue(configurationNode, "__SET_HADOOP_LOGIN__")) {
            return ElementParameterParser.getValue(configurationNode, "__HADOOP_LOGIN__");
        } else {
            return "\"kerberos\"";
        }
    }
    
    // Allow to retrieve password
    public INode getConfigurationNode() {
        return configurationNode;
    }
    
} // end class HBaseConfigurationUtil

    stringBuffer.append(TEXT_3);
    
final class THBaseLookupInputUtil {
	
	protected INode node;
	
	protected IConnection outgoingConnection;

	public THBaseLookupInputUtil(INode node) {
		this.node = node;
		this.outgoingConnection = findOutgoingConnection();
	}
	
	public INode getConfigurationNode() {
		INode configurationNode = null;
		String configurationNodeName = ElementParameterParser.getValue(node,"__STORAGE_CONFIGURATION__");
		for (INode pNode : node.getProcess().getNodesOfType("tHBaseConfiguration")) {
		    if(configurationNodeName!=null && configurationNodeName.equals(pNode.getUniqueName())) {
		        configurationNode = pNode;
		    }
		}
		return configurationNode;
	}
	
	public IConnection getOutgoingConnection() {
		return outgoingConnection;
	}
	
	public List<IMetadataColumn> getColumns() {
		return node.getMetadataList().get(0).getListColumns();
	}
	
	public String getTableName() {
		return ElementParameterParser.getValue(node, "__TABLE__");
	}
	
	public boolean isByFilter() {
		return "true".equals(ElementParameterParser.getValue(node, "__IS_BY_FILTER__"));
	}
	
	public boolean isRowSelectionDefined() {
		return "true".equals(ElementParameterParser.getValue(node, "__DEFINE_ROW_SELECTION__"));
	}
	
	public String getStartRow() {
		return ElementParameterParser.getValue(node, "__START_ROW__");
	}
	
	public String getEndRow() {
		return ElementParameterParser.getValue(node, "__END_ROW__");
	}
	
	public List<Map<String, String>> getMapping() {
		return (List<Map<String,String>>) ElementParameterParser.getObjectValue(node,"__MAPPING__");
	}
	
	public List<Map<String, String>> getFilterMapping() {
		return (List<Map<String,String>>) ElementParameterParser.getObjectValue(node,"__FILTER__");
	}
	
	public String getLogicalOperator() {
		return ElementParameterParser.getValue(node,"__LOGICAL_OP__");
	}
	
	public void generateRowStructCode(String outputName){

    stringBuffer.append(TEXT_4);
    
		for(int i=0; i < getMapping().size(); i++){
			Map<String, String> map = getMapping().get(i);
			String schemaColumn = map.get("SCHEMA_COLUMN");
			String familyColumn= map.get("FAMILY_COLUMN");
			IMetadataColumn column = getColumns().get(i);
			String columnName = column.getLabel();
			String defaultValue = column.getDefault();
			if(columnName.equals(schemaColumn)) {//
				String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
				JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
				String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
				boolean isPrimitiveType = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());

    stringBuffer.append(TEXT_5);
    stringBuffer.append(familyColumn);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_7);
    									
				if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {

    stringBuffer.append(TEXT_8);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_10);
    									
				}else if(javaType == JavaTypesManager.BYTE_ARRAY){

    stringBuffer.append(TEXT_11);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_13);
    									
				}else if(javaType == JavaTypesManager.DATE){

    stringBuffer.append(TEXT_14);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_17);
    									
				}else if(isPrimitiveType && javaType == JavaTypesManager.INTEGER){  

    stringBuffer.append(TEXT_18);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_20);
    									
				}else if(isPrimitiveType && javaType == JavaTypesManager.CHARACTER){  

    stringBuffer.append(TEXT_21);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_23);
    									
				}else if(isPrimitiveType && (javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.LONG || javaType == JavaTypesManager.FLOAT || javaType == JavaTypesManager.DOUBLE)) {  

    stringBuffer.append(TEXT_24);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_27);
    									
				}else{

    stringBuffer.append(TEXT_28);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_31);
    								
				}

    stringBuffer.append(TEXT_32);
    
				String default_Value = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, defaultValue);
				if(default_Value != null && !"null".equals(default_Value)) {

    stringBuffer.append(TEXT_33);
    stringBuffer.append(outputName );
    stringBuffer.append(TEXT_34);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_35);
    stringBuffer.append(default_Value);
    stringBuffer.append(TEXT_36);
    
				} else if(!JavaTypesManager.isJavaPrimitiveType(javaType,column.isNullable())) {

    stringBuffer.append(TEXT_37);
    stringBuffer.append(outputName );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(columnName );
    stringBuffer.append(TEXT_39);
    
				} else {

    stringBuffer.append(TEXT_40);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_42);
    
				}

    stringBuffer.append(TEXT_43);
    
			} //if(columnName.equals(schema_column))
		} //for(int i=0;i<mapping.size();i++)	
	}
	
	private IConnection findOutgoingConnection() {
		IConnection result = null;
		List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
		if (outgoingConnections.size() > 0) {
		    result = outgoingConnections.get(0);
		}
		return result;
	}
	
} // end class THBaseLookupInputUtil

    stringBuffer.append(TEXT_44);
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

String validateError = org.talend.designer.common.tmap.AbstractTMapAdapter.validatePullLookupConfig(node);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "{throw new JobConfigurationError(\"" + validateError +"\");}";
}

THBaseLookupInputUtil tHBaseLookupInputUtil = new THBaseLookupInputUtil(node);
HBaseConfigurationUtil hBaseConfigurationUtil = new HBaseConfigurationUtil(tHBaseLookupInputUtil.getConfigurationNode());

String outputName = tHBaseLookupInputUtil.getOutgoingConnection().getName();
String structName = codeGenArgument.getRecordStructName(tHBaseLookupInputUtil.getOutgoingConnection());

TMapAdapter tMapAdapter = org.talend.designer.common.tmap.AbstractTMapAdapter.createForTMap(
        tHBaseLookupInputUtil.getOutgoingConnection().getTarget());
String inputMainName = tMapAdapter.getInputMainName();
String structMainName = codeGenArgument.getRecordStructName(inputMainName);


    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(hBaseConfigurationUtil.getZookeeperUrl());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(hBaseConfigurationUtil.getZookeeperPort());
    stringBuffer.append(TEXT_50);
    
	if(hBaseConfigurationUtil.isZNodeParentSet()) {

    stringBuffer.append(TEXT_51);
    stringBuffer.append(hBaseConfigurationUtil.getZNodeParent());
    stringBuffer.append(TEXT_52);
    
	}

	if(hBaseConfigurationUtil.useTableNsMapping(node)){

    stringBuffer.append(TEXT_53);
    stringBuffer.append(hBaseConfigurationUtil.getTableNsMapping(node));
    stringBuffer.append(TEXT_54);
    
	}

	if(hBaseConfigurationUtil.isUsingKerberos()) {
        if ((hBaseConfigurationUtil.isCustomDistribution() || hBaseConfigurationUtil.supportMapRTicket()) && hBaseConfigurationUtil.isUsingMapRTicket()) {

    stringBuffer.append(TEXT_55);
    stringBuffer.append(hBaseConfigurationUtil.getMapRHomeDir());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(hBaseConfigurationUtil.getMapRHadoopLogin());
    stringBuffer.append(TEXT_57);
    
	    }

    stringBuffer.append(TEXT_58);
    stringBuffer.append(hBaseConfigurationUtil.getHBaseMasterPrincipal());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(hBaseConfigurationUtil.getHBaseRegionServerPrincipal());
    stringBuffer.append(TEXT_60);
    
		if(hBaseConfigurationUtil.isUsingKeytab()) {

    stringBuffer.append(TEXT_61);
    stringBuffer.append(hBaseConfigurationUtil.getUserPrincipal());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(hBaseConfigurationUtil.getKeytabPath());
    stringBuffer.append(TEXT_63);
    
		}
        if ((hBaseConfigurationUtil.isCustomDistribution() || hBaseConfigurationUtil.supportMapRTicket()) && hBaseConfigurationUtil.isUsingMapRTicket()) {

    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(hBaseConfigurationUtil.getMapRTicketCluster());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(hBaseConfigurationUtil.getMapRTicketDuration());
    stringBuffer.append(TEXT_69);
    
        }
	} else {
        // MapR ticket
        if ((hBaseConfigurationUtil.isCustomDistribution() || hBaseConfigurationUtil.supportMapRTicket()) && hBaseConfigurationUtil.isUsingMapRTicket()) {
            String passwordFieldName = "__MAPRTICKET_PASSWORD__";
            INode configurationNode = hBaseConfigurationUtil.getConfigurationNode();
            if (ElementParameterParser.canEncrypt(configurationNode, passwordFieldName)) {
    stringBuffer.append(TEXT_70);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_71);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_72);
    } else {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(ElementParameterParser.getValue(configurationNode, passwordFieldName));
    stringBuffer.append(TEXT_75);
    }
    stringBuffer.append(TEXT_76);
    stringBuffer.append(hBaseConfigurationUtil.getMapRHomeDir());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_78);
    
            if (hBaseConfigurationUtil.isUsingMapRHadoopLogin()) {

    stringBuffer.append(TEXT_79);
    stringBuffer.append(hBaseConfigurationUtil.getMapRHadoopLogin());
    stringBuffer.append(TEXT_80);
    
            } else {

    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    
            }

    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(hBaseConfigurationUtil.getMapRTicketCluster());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(hBaseConfigurationUtil.getUsername());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(hBaseConfigurationUtil.getMapRTicketDuration());
    stringBuffer.append(TEXT_90);
    
        }
    }
	for(int i=0; i < hBaseConfigurationUtil.getHBaseParameters().size(); i++){
		Map<String, String> map = hBaseConfigurationUtil.getHBaseParameters().get(i);
		String property = map.get("PROPERTY");
		String value= map.get("VALUE");

    stringBuffer.append(TEXT_91);
    stringBuffer.append(property);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_93);
    
	}

    stringBuffer.append(TEXT_94);
    stringBuffer.append(tHBaseLookupInputUtil.getTableName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_104);
    
				tHBaseLookupInputUtil.generateRowStructCode(outputName);

    stringBuffer.append(TEXT_105);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(tHBaseLookupInputUtil.getTableName());
    stringBuffer.append(TEXT_107);
    
	if((hBaseConfigurationUtil.isCustomDistribution()) || (!hBaseConfigurationUtil.isCustomDistribution() && hBaseConfigurationUtil.getVersion() !=null && hBaseConfigurationUtil.supportNewHBaseApi())) {

    stringBuffer.append(TEXT_108);
    
	} else {

    stringBuffer.append(TEXT_109);
    
	}

    stringBuffer.append(TEXT_110);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_112);
    
	if(tHBaseLookupInputUtil.isRowSelectionDefined()) {

    stringBuffer.append(TEXT_113);
    stringBuffer.append(tHBaseLookupInputUtil.getStartRow());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(tHBaseLookupInputUtil.getEndRow());
    stringBuffer.append(TEXT_115);
    
	} else {

    stringBuffer.append(TEXT_116);
    
	}

	List<IMetadataColumn> columns = tHBaseLookupInputUtil.getColumns();
	List<Map<String, String>> mapping = tHBaseLookupInputUtil.getMapping();
	for(int i=0; i < mapping.size(); i++){
		Map<String, String> map = mapping.get(i);
		IMetadataColumn column = columns.get(i);
		String familyColumn = map.get("FAMILY_COLUMN");

    stringBuffer.append(TEXT_117);
    stringBuffer.append(familyColumn);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_119);
    
	}

	if(tHBaseLookupInputUtil.isByFilter()) {

    stringBuffer.append(TEXT_120);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_121);
    
	}

    stringBuffer.append(TEXT_122);
    
	if(tHBaseLookupInputUtil.isByFilter()) {

    stringBuffer.append(TEXT_123);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(tHBaseLookupInputUtil.getLogicalOperator());
    stringBuffer.append(TEXT_126);
    
		List<Map<String, String>> filterMapping = tHBaseLookupInputUtil.getFilterMapping();
		boolean hasMultipleColumnPrefixFilterType = false;
		for(int i=0; i < filterMapping.size() ;i++){
			Map<String, String> filterMap = filterMapping.get(i);
			String filterType = filterMap.get("FILTER_TYPE");
			if("MultipleColumnPrefixFilter".equals(filterType)){
				hasMultipleColumnPrefixFilterType = true;
				break;
			}
		}

		if(hasMultipleColumnPrefixFilterType){

    stringBuffer.append(TEXT_127);
    
		}

    stringBuffer.append(TEXT_128);
    
		for(int j=0; j < filterMapping.size(); j++) {
			Map<String, String> filterMap = filterMapping.get(j);
			String filterType = filterMap.get("FILTER_TYPE");
			String filterColumn = filterMap.get("FILTER_COLUMN");
			String filterFamily = filterMap.get("FILTER_FAMILY");
			String filterOperation = filterMap.get("FILTER_OPERATOR");
			String filterValue = filterMap.get("FILTER_VALUE");
			String filterComparatorType = filterMap.get("FILTER_COMPARATOR_TYPE");
			if("SingleColumnValueFilter".equals(filterType)){
				if("BinaryComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_129);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_133);
    
				}else if ("RegexStringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_134);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_138);
    
				}else if("SubstringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_139);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_143);
    
				}
			}else if("FamilyFilter".equals(filterType)){
				if("BinaryComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_144);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_146);
    
				}else if ("RegexStringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_147);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_149);
    
				}else if("SubstringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_150);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_152);
    
				}
			}else if("QualifierFilter".equals(filterType)){
				if("BinaryComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_153);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_155);
    
				}else if ("RegexStringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_156);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_158);
    
				}else if("SubstringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_159);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_161);
    
				}
			}else if("ColumnPrefixFilter".equals(filterType)){
				if(filterFamily!=null && !"".equals(filterFamily)){

    stringBuffer.append(TEXT_162);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_163);
    
				}

    stringBuffer.append(TEXT_164);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_165);
    
			}else if("MultipleColumnPrefixFilter".equals(filterType)){
				if(filterFamily!=null && !"".equals(filterFamily)){

    stringBuffer.append(TEXT_166);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_167);
    
				}

    stringBuffer.append(TEXT_168);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_171);
    
			}else if("ColumnRangeFilter".equals(filterType)){
				if(filterFamily!=null && !"".equals(filterFamily)){

    stringBuffer.append(TEXT_172);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_173);
    
				}

    stringBuffer.append(TEXT_174);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_175);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_176);
    
			}else if("RowFilter".equals(filterType)){
				if("BinaryComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_177);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_178);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_179);
    
				}else if ("RegexStringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_180);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_182);
    
				}else if("SubstringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_183);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_185);
    
				}
			}else if("ValueFilter".equals(filterType)){
				if("BinaryComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_186);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_188);
    
				}else if ("RegexStringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_189);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_191);
    
				}else if("SubstringComparator".equals(filterComparatorType)){

    stringBuffer.append(TEXT_192);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_194);
    
				}
			}

    stringBuffer.append(TEXT_195);
    
		} // end for(int j=0; j < filterMapping.size(); j++)

    stringBuffer.append(TEXT_196);
    
	} // end if(tHBaseLookupInputUtil.isByFilter())

    stringBuffer.append(TEXT_197);
    return stringBuffer.toString();
  }
}
