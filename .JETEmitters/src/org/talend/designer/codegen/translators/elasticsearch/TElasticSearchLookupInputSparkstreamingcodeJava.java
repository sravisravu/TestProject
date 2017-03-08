package org.talend.designer.codegen.translators.elasticsearch;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tmap.TMapAdapter;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;
import org.talend.designer.spark.generator.storage.ElasticSearchSparkStorage;

public class TElasticSearchLookupInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TElasticSearchLookupInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TElasticSearchLookupInputSparkstreamingcodeJava result = new TElasticSearchLookupInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t\tif(source.get(\"";
  protected final String TEXT_3 = "\") != null) {";
  protected final String TEXT_4 = NL + "    \t\t\t\t";
  protected final String TEXT_5 = ".";
  protected final String TEXT_6 = " = source.get(\"";
  protected final String TEXT_7 = "\").toString();";
  protected final String TEXT_8 = NL + "    \t\t\t\t";
  protected final String TEXT_9 = ".";
  protected final String TEXT_10 = " = BigDataParserUtils.parseTo_Character(source.get(\"";
  protected final String TEXT_11 = "\").toString());";
  protected final String TEXT_12 = NL + "    \t\t\t\t";
  protected final String TEXT_13 = ".";
  protected final String TEXT_14 = " = source.get(\"";
  protected final String TEXT_15 = "\");";
  protected final String TEXT_16 = NL + "\t        \t\tif(true) {" + NL + "\t        \t\t\tthrow new Exception(\"tElasticSearchLookupInput does not support List\");" + NL + "\t        \t\t}";
  protected final String TEXT_17 = NL + "        \t\t\t";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = " = BigDataParserUtils.parseTo_Boolean(source.get(\"";
  protected final String TEXT_20 = "\").toString());";
  protected final String TEXT_21 = NL + "        \t\t\t";
  protected final String TEXT_22 = ".";
  protected final String TEXT_23 = " = java.nio.ByteBuffer.wrap(source.get(\"";
  protected final String TEXT_24 = "\").toString().getBytes());";
  protected final String TEXT_25 = NL + "        \t\t\t";
  protected final String TEXT_26 = ".";
  protected final String TEXT_27 = " = BigDataParserUtils.parseTo_Date(source.get(\"";
  protected final String TEXT_28 = "\").toString(), ";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + "        \t\t\t";
  protected final String TEXT_31 = ".";
  protected final String TEXT_32 = " = BigDataParserUtils.parseTo_BigDecimal(Double.valueOf(source.get(\"";
  protected final String TEXT_33 = "\").toString()));";
  protected final String TEXT_34 = NL + "        \t\t\t";
  protected final String TEXT_35 = ".";
  protected final String TEXT_36 = " = BigDataParserUtils.parseTo_Double(source.get(\"";
  protected final String TEXT_37 = "\").toString());";
  protected final String TEXT_38 = NL + "        \t\t\t";
  protected final String TEXT_39 = ".";
  protected final String TEXT_40 = " = BigDataParserUtils.parseTo_Float(source.get(\"";
  protected final String TEXT_41 = "\").toString());";
  protected final String TEXT_42 = NL + "        \t\t\t";
  protected final String TEXT_43 = ".";
  protected final String TEXT_44 = " = BigDataParserUtils.parseTo_Integer(source.get(\"";
  protected final String TEXT_45 = "\").toString());";
  protected final String TEXT_46 = NL + "        \t\t\t";
  protected final String TEXT_47 = ".";
  protected final String TEXT_48 = " = BigDataParserUtils.parseTo_Long(source.get(\"";
  protected final String TEXT_49 = "\").toString());";
  protected final String TEXT_50 = NL + "        \t\t\t";
  protected final String TEXT_51 = ".";
  protected final String TEXT_52 = " = BigDataParserUtils.parseTo_Short(source.get(\"";
  protected final String TEXT_53 = "\").toString());";
  protected final String TEXT_54 = NL + "\t\t\t} else {";
  protected final String TEXT_55 = NL + "\t\t\t}";
  protected final String TEXT_56 = NL + "\t\t\t\t   ";
  protected final String TEXT_57 = ".";
  protected final String TEXT_58 = " = null;";
  protected final String TEXT_59 = NL + "\t\t\t\t\tthrow new RuntimeException(\"Null value in non-Nullable column ";
  protected final String TEXT_60 = "\");";
  protected final String TEXT_61 = NL;
  protected final String TEXT_62 = NL;
  protected final String TEXT_63 = NL + "\t";
  protected final String TEXT_64 = NL + NL + "public static class ";
  protected final String TEXT_65 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_66 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_67 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_68 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_69 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_70 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_71 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_72 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_73 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_74 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_75 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_76 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_77 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_78 = ");";
  protected final String TEXT_79 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_80 = ">(new ";
  protected final String TEXT_81 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "\t";
  protected final String TEXT_82 = NL + NL + "public static class ";
  protected final String TEXT_83 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_84 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "\t" + NL + "\tpublic ";
  protected final String TEXT_85 = "(ContextProperties context) {" + NL + "\t\tthis.context = context;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_86 = " create() throws Exception {" + NL + "\t\torg.elasticsearch.common.settings.Settings settings = " + NL + "\t\t";
  protected final String TEXT_87 = "\t" + NL + "\t\t\torg.elasticsearch.common.settings.ImmutableSettings.settingsBuilder() //" + NL + "\t\t";
  protected final String TEXT_88 = NL + "\t\t\torg.elasticsearch.common.settings.Settings.settingsBuilder() //" + NL + "\t\t";
  protected final String TEXT_89 = NL + "\t\t.put(\"cluster.name\", ";
  protected final String TEXT_90 = ") //" + NL + "\t\t\t";
  protected final String TEXT_91 = NL + "      \t.put(";
  protected final String TEXT_92 = ", ";
  protected final String TEXT_93 = ") //";
  protected final String TEXT_94 = NL + "\t\t\t.put(\"shield.user\", ";
  protected final String TEXT_95 = " +\":\"+ ";
  protected final String TEXT_96 = ") //";
  protected final String TEXT_97 = NL + "\t\t\t.put(\"shield.transport.ssl\", \"true\") //" + NL + "\t\t\t.put(\"shield.ssl.truststore.path\", ";
  protected final String TEXT_98 = ") //" + NL + "\t\t\t.put(\"shield.ssl.truststore.password\", ";
  protected final String TEXT_99 = ") //";
  protected final String TEXT_100 = NL + "\t\t\t\t.put(\"shield.ssl.hostname_verification\", \"false\") //";
  protected final String TEXT_101 = NL + "\t\t\t\t.put(\"shield.ssl.keystore.path\", ";
  protected final String TEXT_102 = ") //" + NL + "\t\t\t\t.put(\"shield.ssl.keystore.password\", ";
  protected final String TEXT_103 = ") //";
  protected final String TEXT_104 = NL + "\t\t.build();" + NL + "" + NL + "\t\t";
  protected final String TEXT_105 = "\t" + NL + "\t\t\torg.elasticsearch.client.transport.TransportClient transportClient = new org.elasticsearch.client.transport.TransportClient(settings);" + NL + "\t\t\t";
  protected final String TEXT_106 = NL + "\t\t\t\ttransportClient.addTransportAddress(new org.elasticsearch.common.transport.InetSocketTransportAddress(\"";
  protected final String TEXT_107 = "\", ";
  protected final String TEXT_108 = "));" + NL + "\t\t\t";
  protected final String TEXT_109 = NL + "\t\t\torg.elasticsearch.client.transport.TransportClient transportClient = org.elasticsearch.client.transport.TransportClient.builder()" + NL + "\t\t\t";
  protected final String TEXT_110 = NL + "\t\t\t.addPlugin(org.elasticsearch.shield.ShieldPlugin.class)" + NL + "\t\t\t";
  protected final String TEXT_111 = NL + "\t\t\t.settings(settings).build()" + NL + "\t\t\t";
  protected final String TEXT_112 = NL + "\t\t\t\t.addTransportAddresses(new org.elasticsearch.common.transport.InetSocketTransportAddress(java.net.InetAddress.getByName(\"";
  protected final String TEXT_113 = "\"), ";
  protected final String TEXT_114 = "))" + NL + "\t\t\t";
  protected final String TEXT_115 = NL + "\t\t\t;\t" + NL + "\t\t";
  protected final String TEXT_116 = NL + "\t\treturn transportClient;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_117 = "> wrap(";
  protected final String TEXT_118 = " client) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_119 = ">(client);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_120 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "}";
  protected final String TEXT_121 = NL + NL + "public static class ";
  protected final String TEXT_122 = "_FlatMapper extends org.talend.bigdata.dataflow.hmap.HMapContextConfigurableBase implements org.talend.bigdata.dataflow.functions.FlatMapper<org.apache.avro.generic.IndexedRecord, ";
  protected final String TEXT_123 = "> {" + NL + "" + NL + "\tprivate transient org.elasticsearch.client.Client client;" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_124 = "_FlatMapper(JobConf job) {" + NL + "\t\tthis.context = new ContextProperties(job);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void prepare() throws Exception {" + NL + "\t\tthis.client = ";
  protected final String TEXT_125 = ".get(context).borrowObject(); " + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic Iterable<";
  protected final String TEXT_126 = "> flatMap(org.apache.avro.generic.IndexedRecord joinKeys) throws Exception {" + NL + "\t\tList<";
  protected final String TEXT_127 = "> result = new ArrayList<>();" + NL + "\t\t";
  protected final String TEXT_128 = " ";
  protected final String TEXT_129 = " = getHMapContext().getInputRecord(\"";
  protected final String TEXT_130 = "\");" + NL + "" + NL + "\t\ttry {" + NL + "\t\t\torg.elasticsearch.action.search.SearchRequestBuilder searchRequest = client.prepareSearch(";
  protected final String TEXT_131 = ") //" + NL + "\t\t\t\t.setTypes(";
  protected final String TEXT_132 = ") //" + NL + "\t\t\t\t";
  protected final String TEXT_133 = "\t" + NL + "\t\t\t\t\t.setSearchType(org.elasticsearch.action.search.SearchType.SCAN) //" + NL + "\t\t\t\t";
  protected final String TEXT_134 = NL + "\t\t\t\t\t.addSort(org.elasticsearch.search.sort.SortParseElement.DOC_FIELD_NAME, org.elasticsearch.search.sort.SortOrder.ASC)" + NL + "\t\t\t\t";
  protected final String TEXT_135 = NL + "\t\t\t\t.setScroll(new org.elasticsearch.common.unit.TimeValue(";
  protected final String TEXT_136 = ")) //" + NL + "\t\t\t\t.setQuery(";
  protected final String TEXT_137 = ");" + NL + "\t\t\t  " + NL + "\t\t\torg.elasticsearch.action.search.SearchResponse searchResponse = searchRequest.execute().actionGet();" + NL + "" + NL + "\t\t\twhile (true) {" + NL + "\t\t\t\tfor (org.elasticsearch.search.SearchHit searchHit : searchResponse.getHits().getHits()) {" + NL + "\t\t\t\t\tjava.util.Map<String, Object> source = searchHit.getSource();" + NL + "\t\t\t\t\t";
  protected final String TEXT_138 = " ";
  protected final String TEXT_139 = " = new ";
  protected final String TEXT_140 = "();";
  protected final String TEXT_141 = NL + "\t\t\t\t\tresult.add(";
  protected final String TEXT_142 = ");" + NL + "\t\t\t\t}" + NL + "\t\t\t\t" + NL + "\t\t\t\tsearchResponse = client.prepareSearchScroll(searchResponse.getScrollId()) //" + NL + "\t\t\t\t\t.setScroll(new org.elasticsearch.common.unit.TimeValue(";
  protected final String TEXT_143 = ")) //" + NL + "\t\t\t\t\t.execute() //" + NL + "\t\t\t\t\t.actionGet();" + NL + "\t\t\t\t\t" + NL + "\t\t\t\tif (searchResponse.getHits().getHits().length == 0) {" + NL + "\t\t\t\t    break;" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t} catch (Exception e) {" + NL + "    \t\tthrow new RuntimeException(\"Lookup from ";
  protected final String TEXT_144 = " component has failed : \"+e.getMessage(), e);" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void cleanup() throws Exception {" + NL + "\t\tif (client != null) {" + NL + "\t\t\t";
  protected final String TEXT_145 = ".get(context).returnObject(client);" + NL + "\t\t}" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
final class TElasticSearchLookupInputUtil {

	protected INode node;

	protected IConnection outgoingConnection;
	
	protected ElasticSearchSparkStorage storage;

	public TElasticSearchLookupInputUtil(INode node) {
		this.node = node;
		this.outgoingConnection = findOutgoingConnection();
		this.storage = new ElasticSearchSparkStorage(node);
	}
	
	public TSetKeystoreUtil getTSetKeystoreUtil() {
		return storage.getTSetKeystoreUtil();
	}
	
	public ElasticSearchSparkStorage getStorage() {
		return storage;
	}

	public IConnection getOutgoingConnection() {
		return outgoingConnection;
	}

	public String getNodes() {
		return storage.getNodes();
	}
	
	public String getClusterName() {
		return storage.getClusterName();
	}
	
	public boolean isUserAuthEnabled() {
		return storage.isUserAuthEnabled();
	}
	
	public String getUsername() {
		return storage.getUsername();
	}
	
	public String getPassword() {
		return storage.getPassword();
	}
	
	public Map<String, Integer> getTransportAddresses() {
		return storage.getTransportAddresses();
	}
	
	public String getIndex() {
		return ElementParameterParser.getValue(node, "__INDEX__");
	}

	public String getType() {
	   return ElementParameterParser.getValue(node, "__TYPE__");
	}
	
	public String getQuery() {
	   return NodeUtil.replaceCRLFInMEMO_SQL(ElementParameterParser.getValue(node, "__QUERY__"));
	}
	
	public long getScrollTime() {
	   return Long.valueOf(ElementParameterParser.getValue(node, "__SCROLL_TIME__").replace("\"", ""));
	}
	
	public List<Map<String, String>> getConfiguration() {
		return storage.getConfiguration();
   }

	public List<IMetadataColumn> getColumns() {
		return node.getMetadataList().get(0).getListColumns();
	}

	public void generateRowStructCode(String outputName){
		for(IMetadataColumn column : getColumns()) {
    		JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
    		String columnName = column.getLabel();

    stringBuffer.append(TEXT_2);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_3);
    
    			if(javaType == JavaTypesManager.STRING){

    stringBuffer.append(TEXT_4);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_7);
    
        		}else if(javaType == JavaTypesManager.CHARACTER){

    stringBuffer.append(TEXT_8);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_11);
    
				}else if(javaType == JavaTypesManager.OBJECT){

    stringBuffer.append(TEXT_12);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_15);
    
				}else if(javaType == JavaTypesManager.LIST){

    stringBuffer.append(TEXT_16);
    
        		}else if(javaType == JavaTypesManager.BOOLEAN){

    stringBuffer.append(TEXT_17);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_18);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_20);
    
    			}else if(javaType == JavaTypesManager.BYTE_ARRAY){

    stringBuffer.append(TEXT_21);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_24);
    
    			}else if(javaType == JavaTypesManager.DATE){
					String pattern = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();

    stringBuffer.append(TEXT_25);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_29);
    
    			}else if(javaType == JavaTypesManager.BIGDECIMAL){

    stringBuffer.append(TEXT_30);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_33);
    
    			}else if(javaType == JavaTypesManager.DOUBLE){

    stringBuffer.append(TEXT_34);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_37);
    
    			}else if(javaType == JavaTypesManager.FLOAT){

    stringBuffer.append(TEXT_38);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_41);
    
    			}else if(javaType == JavaTypesManager.INTEGER){

    stringBuffer.append(TEXT_42);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_45);
    
    			}else if(javaType == JavaTypesManager.LONG){

    stringBuffer.append(TEXT_46);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_49);
    
    			}else if(javaType == JavaTypesManager.SHORT){

    stringBuffer.append(TEXT_50);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_53);
    
    			}

    stringBuffer.append(TEXT_54);
    
				generateIsNullableCode(outputName, column);

    stringBuffer.append(TEXT_55);
    
    	} // end for
	}

	protected void generateIsNullableCode(String outputName, IMetadataColumn column) {
		if(column.isNullable()) {

    stringBuffer.append(TEXT_56);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_58);
    
		} else {

    stringBuffer.append(TEXT_59);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_60);
    
		}
	}

	private IConnection findOutgoingConnection() {
		IConnection result = null;
		List<? extends IConnection> outgoingConnections = node.getOutgoingConnections();
		if (outgoingConnections.size() > 0) {
		    result = outgoingConnections.get(0);
		}
		return result;
	}

} // end class TElasticSearchLookupInputUtil

    stringBuffer.append(TEXT_61);
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TElasticSearchLookupInputUtil tElasticSearchLookupInputUtil = new TElasticSearchLookupInputUtil(node);
ElasticSearchSparkStorage storage = tElasticSearchLookupInputUtil.getStorage();
TSetKeystoreUtil tSetKeystoreUtil = tElasticSearchLookupInputUtil.getTSetKeystoreUtil();

String validateError = org.talend.designer.common.tmap.AbstractTMapAdapter.validatePullLookupConfig(node);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "{throw new JobConfigurationError(\"" + validateError +"\");}";
}

String outputName = tElasticSearchLookupInputUtil.getOutgoingConnection().getName();
String structName = codeGenArgument.getRecordStructName(tElasticSearchLookupInputUtil.getOutgoingConnection());

TMapAdapter tMapAdapter = org.talend.designer.common.tmap.AbstractTMapAdapter.createForTMap(
        tElasticSearchLookupInputUtil.getOutgoingConnection().getTarget());
String inputMainName = tMapAdapter.getInputMainName();
String structMainName = codeGenArgument.getRecordStructName(inputMainName);

    stringBuffer.append(TEXT_62);
    
if(!storage.isUseExistingConnection()) {
	// Component have to generate its own connection pool code

    stringBuffer.append(TEXT_63);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_64);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_74);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_75);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_78);
    
		}

    stringBuffer.append(TEXT_79);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_81);
    
	// Pooled object factory code. This factory creates Elasticsearch transport clients when requested by a connection pool. 
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create Elasticsearch transport clients without relying on a dedicated Spark connector.
	
	// If this file is included, then spark_pool.javajet must be included as well.

    stringBuffer.append(TEXT_82);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_86);
    if(ElasticSearchSparkStorage.VERSION.ES_1_7 == storage.getVersion()){
    stringBuffer.append(TEXT_87);
    }else if(ElasticSearchSparkStorage.VERSION.ES_2_3 == storage.getVersion()){
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_89);
    stringBuffer.append(storage.getClusterName());
    stringBuffer.append(TEXT_90);
    
		for(java.util.Map<String, String> config : storage.getConfiguration()) {

    stringBuffer.append(TEXT_91);
    stringBuffer.append(config.get("KEY"));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(config.get("VALUE"));
    stringBuffer.append(TEXT_93);
    
		}
		
		if(storage.isUserAuthEnabled()) {

    stringBuffer.append(TEXT_94);
    stringBuffer.append(storage.getUsername());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(storage.getPassword());
    stringBuffer.append(TEXT_96);
    
		}	
		if(tSetKeystoreUtil.useHTTPS()) {

    stringBuffer.append(TEXT_97);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_99);
    
			if(!tSetKeystoreUtil.needVerifyName()) {

    stringBuffer.append(TEXT_100);
    
			}
			
			if(tSetKeystoreUtil.needClientAuth()) {

    stringBuffer.append(TEXT_101);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_103);
    
			}
		} // end if(tSetKeystoreUtil.useHTTPS())

    stringBuffer.append(TEXT_104);
    if(ElasticSearchSparkStorage.VERSION.ES_1_7 == storage.getVersion()){
    stringBuffer.append(TEXT_105);
    
			for(java.util.Map.Entry<String, Integer> transportAddress : storage.getTransportAddresses().entrySet()) {
			
    stringBuffer.append(TEXT_106);
    stringBuffer.append(transportAddress.getKey());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(transportAddress.getValue());
    stringBuffer.append(TEXT_108);
    
			}
		}else if(ElasticSearchSparkStorage.VERSION.ES_2_3 == storage.getVersion()){
    stringBuffer.append(TEXT_109);
    if(storage.isUserAuthEnabled() || tSetKeystoreUtil.useHTTPS()){
    stringBuffer.append(TEXT_110);
    }
    stringBuffer.append(TEXT_111);
    
			for(java.util.Map.Entry<String, Integer> transportAddress : storage.getTransportAddresses().entrySet()) {
			
    stringBuffer.append(TEXT_112);
    stringBuffer.append(transportAddress.getKey());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(transportAddress.getValue());
    stringBuffer.append(TEXT_114);
    
			}
			
    stringBuffer.append(TEXT_115);
    }
    stringBuffer.append(TEXT_116);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_120);
    
}

    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_126);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(tElasticSearchLookupInputUtil.getIndex());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(tElasticSearchLookupInputUtil.getType());
    stringBuffer.append(TEXT_132);
    if(ElasticSearchSparkStorage.VERSION.ES_1_7 == storage.getVersion()){
    stringBuffer.append(TEXT_133);
    }else if(ElasticSearchSparkStorage.VERSION.ES_2_3 == storage.getVersion()){
    stringBuffer.append(TEXT_134);
    }
    stringBuffer.append(TEXT_135);
    stringBuffer.append(tElasticSearchLookupInputUtil.getScrollTime());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(tElasticSearchLookupInputUtil.getQuery());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_140);
    
					tElasticSearchLookupInputUtil.generateRowStructCode(outputName);

    stringBuffer.append(TEXT_141);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_142);
    stringBuffer.append(tElasticSearchLookupInputUtil.getScrollTime());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_145);
    return stringBuffer.toString();
  }
}
