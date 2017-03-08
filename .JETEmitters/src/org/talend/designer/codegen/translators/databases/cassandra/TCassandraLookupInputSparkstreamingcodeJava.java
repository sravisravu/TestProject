package org.talend.designer.codegen.translators.databases.cassandra;

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
import org.talend.designer.spark.generator.storage.CassandraSparkStorage;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;

public class TCassandraLookupInputSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TCassandraLookupInputSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCassandraLookupInputSparkstreamingcodeJava result = new TCassandraLookupInputSparkstreamingcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "\t\t\tif(row.isNull(\"";
  protected final String TEXT_3 = "\")){" + NL + "\t\t\t\t";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = " = ";
  protected final String TEXT_6 = ";" + NL + "\t\t\t}else{" + NL + "\t\t\t\t";
  protected final String TEXT_7 = NL + "\t    \t\t\t\t";
  protected final String TEXT_8 = ".";
  protected final String TEXT_9 = " = row.getString(\"";
  protected final String TEXT_10 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_11 = NL + "\t    \t\t\t\t";
  protected final String TEXT_12 = ".";
  protected final String TEXT_13 = " = row.getUUID(\"";
  protected final String TEXT_14 = "\").toString();" + NL + "\t\t\t\t\t";
  protected final String TEXT_15 = NL + "\t    \t\t\t\t";
  protected final String TEXT_16 = ".";
  protected final String TEXT_17 = " = row.getVarint(\"";
  protected final String TEXT_18 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_19 = NL + "\t    \t\t\t\t";
  protected final String TEXT_20 = ".";
  protected final String TEXT_21 = " = row.getInet(\"";
  protected final String TEXT_22 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_23 = NL + "\t    \t\t\t\t";
  protected final String TEXT_24 = ".";
  protected final String TEXT_25 = " = row.getObject(\"";
  protected final String TEXT_26 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_27 = NL + "\t    \t\t\t\t";
  protected final String TEXT_28 = ".";
  protected final String TEXT_29 = " = row.getObject(\"";
  protected final String TEXT_30 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_31 = NL + "\t        \t\t";
  protected final String TEXT_32 = ".";
  protected final String TEXT_33 = " = (java.util.List)row.getObject(\"";
  protected final String TEXT_34 = "\");\t" + NL + "\t\t\t\t";
  protected final String TEXT_35 = NL + "\t        \t\t";
  protected final String TEXT_36 = ".";
  protected final String TEXT_37 = " = row.getBool(\"";
  protected final String TEXT_38 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_39 = NL + "\t        \t\t";
  protected final String TEXT_40 = ".";
  protected final String TEXT_41 = " = row.getBytes(\"";
  protected final String TEXT_42 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_43 = NL + "\t\t\t\t\t";
  protected final String TEXT_44 = ".";
  protected final String TEXT_45 = " = row.getByte(\"";
  protected final String TEXT_46 = "\");\t" + NL + "\t\t\t\t";
  protected final String TEXT_47 = NL + "\t        \t\t\t";
  protected final String TEXT_48 = ".";
  protected final String TEXT_49 = " = row.getTimestamp(\"";
  protected final String TEXT_50 = "\");\t" + NL + "\t        \t\t";
  protected final String TEXT_51 = NL + "\t        \t\t\t";
  protected final String TEXT_52 = ".";
  protected final String TEXT_53 = " = new java.util.Date(row.getDate(\"";
  protected final String TEXT_54 = "\").getMillisSinceEpoch());\t" + NL + "\t        \t\t";
  protected final String TEXT_55 = NL + "\t        \t\t";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = " = row.getDecimal(\"";
  protected final String TEXT_58 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_59 = NL + "\t        \t\t";
  protected final String TEXT_60 = ".";
  protected final String TEXT_61 = " = row.getDouble(\"";
  protected final String TEXT_62 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_63 = NL + "\t        \t\t";
  protected final String TEXT_64 = ".";
  protected final String TEXT_65 = " = row.getFloat(\"";
  protected final String TEXT_66 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_67 = NL + "\t        \t\t";
  protected final String TEXT_68 = ".";
  protected final String TEXT_69 = " = row.getInt(\"";
  protected final String TEXT_70 = "\");" + NL + "\t\t\t\t";
  protected final String TEXT_71 = NL + "\t        \t\t\t";
  protected final String TEXT_72 = ".";
  protected final String TEXT_73 = " = row.getTime(\"";
  protected final String TEXT_74 = "\");\t" + NL + "\t        \t\t";
  protected final String TEXT_75 = NL + "\t        \t\t\t";
  protected final String TEXT_76 = ".";
  protected final String TEXT_77 = " = row.getLong(\"";
  protected final String TEXT_78 = "\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_79 = NL + "\t    \t\t\t";
  protected final String TEXT_80 = ".";
  protected final String TEXT_81 = " = row.getShort(\"";
  protected final String TEXT_82 = "\");\t" + NL + "\t    \t\t";
  protected final String TEXT_83 = NL + "\t    \t}\t\t" + NL + "\t    ";
  protected final String TEXT_84 = NL + "\t\t\t\t   ";
  protected final String TEXT_85 = ".";
  protected final String TEXT_86 = " = null;";
  protected final String TEXT_87 = NL + "\t\t\t\t\tthrow new RuntimeException(\"Null value in non-Nullable column ";
  protected final String TEXT_88 = "\");";
  protected final String TEXT_89 = NL;
  protected final String TEXT_90 = NL;
  protected final String TEXT_91 = NL + NL + "public static class ";
  protected final String TEXT_92 = " {" + NL + "\tprivate static volatile org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_93 = "> pool;" + NL + "\t" + NL + "\tprivate ";
  protected final String TEXT_94 = "() {" + NL + "\t    // empty" + NL + "\t}" + NL + "" + NL + "\tpublic static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_95 = "> get(ContextProperties context) {" + NL + "\t\tif(pool == null) {" + NL + "\t\t\tsynchronized(";
  protected final String TEXT_96 = ".class) {" + NL + "\t\t\t\tif(pool == null) {" + NL + "\t\t\t\t\tpool = createPool(context);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t   return pool;" + NL + "\t}" + NL + "" + NL + "\tprivate static org.apache.commons.pool2.ObjectPool<";
  protected final String TEXT_97 = "> createPool(ContextProperties context) {" + NL + "\t    org.apache.commons.pool2.impl.GenericObjectPoolConfig config = new org.apache.commons.pool2.impl.GenericObjectPoolConfig();" + NL + "\t    config.setMaxTotal(";
  protected final String TEXT_98 = ");" + NL + "\t    config.setMaxWaitMillis(";
  protected final String TEXT_99 = ");" + NL + "\t    config.setMinIdle(";
  protected final String TEXT_100 = ");" + NL + "\t    config.setMaxIdle(";
  protected final String TEXT_101 = ");" + NL + "\t    config.setBlockWhenExhausted(true);";
  protected final String TEXT_102 = NL + "\t\t\tconfig.setTimeBetweenEvictionRunsMillis(";
  protected final String TEXT_103 = ");" + NL + "\t\t\tconfig.setMinEvictableIdleTimeMillis(";
  protected final String TEXT_104 = ");" + NL + "\t\t\tconfig.setSoftMinEvictableIdleTimeMillis(";
  protected final String TEXT_105 = ");";
  protected final String TEXT_106 = NL + "\t    return new org.apache.commons.pool2.impl.GenericObjectPool<";
  protected final String TEXT_107 = ">(new ";
  protected final String TEXT_108 = "(context), config);" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_109 = " extends org.apache.commons.pool2.BasePooledObjectFactory<";
  protected final String TEXT_110 = "> {" + NL + "" + NL + "\tprivate ContextProperties context;" + NL + "\t" + NL + "\tprivate shade.com.datastax.spark.connector.driver.core.Cluster cluster;" + NL + "\t\t" + NL + "\tpublic ";
  protected final String TEXT_111 = "(ContextProperties context){" + NL + "\t\tthis.context = context;" + NL + "\t\ttry{" + NL + "\t\t\tthis.cluster = createCluster();" + NL + "\t\t}catch(Exception ex){" + NL + "\t\t\tthrow new RuntimeException(ex.getMessage());" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic ";
  protected final String TEXT_112 = " create() throws java.lang.Exception {";
  protected final String TEXT_113 = NL + "\t\t\treturn cluster.connect(StringHandling.DQUOTE(";
  protected final String TEXT_114 = "));";
  protected final String TEXT_115 = NL + "\t\t\treturn cluster.connect();";
  protected final String TEXT_116 = NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_117 = "> wrap(";
  protected final String TEXT_118 = " session) {" + NL + "\t    return new org.apache.commons.pool2.impl.DefaultPooledObject<";
  protected final String TEXT_119 = ">(session);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void destroyObject(org.apache.commons.pool2.PooledObject<";
  protected final String TEXT_120 = "> pooledObject) throws Exception {" + NL + "\t    if (pooledObject != null) {" + NL + "\t        pooledObject.getObject().close();" + NL + "\t    }" + NL + "\t    super.destroyObject(pooledObject);" + NL + "\t}" + NL + "\t" + NL + "\tprivate shade.com.datastax.spark.connector.driver.core.Cluster createCluster() throws Exception{" + NL + "\t\t";
  protected final String TEXT_121 = NL + "\t\t\tjavax.net.ssl.KeyManagerFactory kmf = null;" + NL + "\t\t\t";
  protected final String TEXT_122 = NL + "\t\t\t\tjava.security.KeyStore ks = java.security.KeyStore.getInstance(";
  protected final String TEXT_123 = ");" + NL + "\t\t\t\tks.load(new java.io.FileInputStream(";
  protected final String TEXT_124 = "), ";
  protected final String TEXT_125 = ".toCharArray());" + NL + "\t\t\t\tkmf = javax.net.ssl.KeyManagerFactory.getInstance(javax.net.ssl.KeyManagerFactory.getDefaultAlgorithm());" + NL + "\t\t\t\tkmf.init(ks, ";
  protected final String TEXT_126 = ".toCharArray());" + NL + "\t\t\t";
  protected final String TEXT_127 = NL + "\t\t\t" + NL + "\t\t\tjava.security.KeyStore ts = java.security.KeyStore.getInstance(";
  protected final String TEXT_128 = ");" + NL + "\t\t\tts.load(new java.io.FileInputStream(";
  protected final String TEXT_129 = "), ";
  protected final String TEXT_130 = ".toCharArray());" + NL + "\t\t\tjavax.net.ssl.TrustManagerFactory tmf = javax.net.ssl.TrustManagerFactory.getInstance(javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm());" + NL + "\t    \ttmf.init(ts);" + NL + "\t\t\t" + NL + "\t\t\tjavax.net.ssl.SSLContext sslContext = javax.net.ssl.SSLContext.getInstance(\"TLS\");" + NL + "\t\t\tsslContext.init(kmf == null ? null : kmf.getKeyManagers(), tmf.getTrustManagers(), new java.security.SecureRandom());" + NL + "\t\t";
  protected final String TEXT_131 = NL + "\t";
  protected final String TEXT_132 = NL + "\t\t\tString password = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_133 = ");";
  protected final String TEXT_134 = NL + "\t\t\tString password = ";
  protected final String TEXT_135 = ";";
  protected final String TEXT_136 = NL + "   \treturn shade.com.datastax.spark.connector.driver.core.Cluster.builder() //" + NL + "    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.addContactPoints(";
  protected final String TEXT_137 = ".split(\",\")) //" + NL + "    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.withPort(Integer.valueOf(";
  protected final String TEXT_138 = ")) //";
  protected final String TEXT_139 = NL + "                                                .withCredentials(";
  protected final String TEXT_140 = ", password) //";
  protected final String TEXT_141 = NL + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_142 = NL + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.withSSL(shade.com.datastax.spark.connector.driver.core.JdkSSLOptions.builder()" + NL + "                                                            \t\t\t\t\t.withSSLContext(sslContext).build())" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_143 = NL + "    \t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.build();" + NL + "\t}" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_144 = "_FlatMapper extends org.talend.bigdata.dataflow.hmap.HMapContextConfigurableBase implements org.talend.bigdata.dataflow.functions.FlatMapper<org.apache.avro.generic.IndexedRecord, ";
  protected final String TEXT_145 = "> {" + NL + "" + NL + "\tprivate transient ";
  protected final String TEXT_146 = " session;" + NL + "\t" + NL + "\tprivate ContextProperties context;" + NL + "" + NL + "\tpublic ";
  protected final String TEXT_147 = "_FlatMapper(JobConf job) {" + NL + "\t\tthis.context = new ContextProperties(job);" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void prepare() throws Exception {" + NL + "\t\tthis.session = ";
  protected final String TEXT_148 = ".get(context).borrowObject();" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic Iterable<";
  protected final String TEXT_149 = "> flatMap(org.apache.avro.generic.IndexedRecord joinKeys) throws Exception {" + NL + "\t\tList<";
  protected final String TEXT_150 = "> result = new ArrayList<>();" + NL + "\t\t";
  protected final String TEXT_151 = " ";
  protected final String TEXT_152 = " = getHMapContext().getInputRecord(\"";
  protected final String TEXT_153 = "\");" + NL + "" + NL + "\t\ttry {" + NL + "\t\t\tshade.com.datastax.spark.connector.driver.core.ResultSet resultSet = session.execute(";
  protected final String TEXT_154 = ");" + NL + "\t\t\tfor(shade.com.datastax.spark.connector.driver.core.Row row : resultSet) {" + NL + "        \t\t";
  protected final String TEXT_155 = " ";
  protected final String TEXT_156 = " = new ";
  protected final String TEXT_157 = "();";
  protected final String TEXT_158 = NL + "\t\t\t\tresult.add(";
  protected final String TEXT_159 = ");" + NL + "    \t\t}" + NL + "\t\t} catch (Exception e) {" + NL + "    \t\tthrow new RuntimeException(\"Lookup from query \"+";
  protected final String TEXT_160 = "+\" has failed : \"+e.getMessage(), e);" + NL + "\t\t}" + NL + "\t\treturn result;" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic void cleanup() throws Exception {" + NL + "\t\tif (session != null) {" + NL + "\t\t\t";
  protected final String TEXT_161 = ".get(context).returnObject(session);" + NL + "\t\t}" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
final class TCassandraLookupInputUtil {

	protected INode node;

	protected IConnection outgoingConnection;
	
	private TSetKeystoreUtil tSetKeystoreUtil;

	public TCassandraLookupInputUtil(INode node) {
		this.node = node;
		this.outgoingConnection = findOutgoingConnection();
		this.tSetKeystoreUtil = new TSetKeystoreUtil(this.node);
	}
	
	public TSetKeystoreUtil getTSetKeystoreUtil() {
        return this.tSetKeystoreUtil;
    }

	public IConnection getOutgoingConnection() {
		return outgoingConnection;
	}

	public String getPort() {
		return ElementParameterParser.getValue(node, "__PORT__");
	}

	public String getHost() {
		return ElementParameterParser.getValue(node, "__HOST__");
	}

	public String getKeySpace() {
	   return ElementParameterParser.getValue(node, "__KEY_SPACE__");
	}

	public boolean isAuthenticationRequired() {
		return "true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__REQUIRED_AUTHENTICATION__"));
	}

	public String getUsername() {
		return ElementParameterParser.getValue(node, "__USERNAME__");
	}

	public String getPassword() {
		return canEncryptPassword() ? ElementParameterParser.getEncryptedValue(node, "__PASSWORD__") : ElementParameterParser.getValue(node, "__PASSWORD__");
	}

	public boolean canEncryptPassword() {
		return isAuthenticationRequired() && ElementParameterParser.canEncrypt(node, "__PASSWORD__");
	}

	public String getQuery() {
		return ElementParameterParser.getValue(node, "__QUERY__").replaceAll("[\r\n]", " ");
	}

	public List<IMetadataColumn> getColumns() {
		return node.getMetadataList().get(0).getListColumns();
	}

	public void generateRowStructCode(String outputName){
		for(IMetadataColumn column : getColumns()) {
			String dbType = column.getType();
    		JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
    		String columnName = column.getLabel();
    		String dbColumnName = column.getOriginalDbColumnName();
			dbColumnName = "\\\"" + dbColumnName + "\\\"";
			String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
			
    stringBuffer.append(TEXT_2);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate));
    stringBuffer.append(TEXT_6);
    
	    		if(javaType == JavaTypesManager.STRING){
	    			if("ascii".equals(dbType) || "text".equals(dbType) || "varchar".equals(dbType)){
					
    stringBuffer.append(TEXT_7);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_10);
    
	    			}else if("timeuuid".equals(dbType) || "uuid".equals(dbType)){
					
    stringBuffer.append(TEXT_11);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_14);
    
	    			}
	        	}else if(javaType == JavaTypesManager.OBJECT){
	        		if("varint".equals(dbType)){
					
    stringBuffer.append(TEXT_15);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_18);
    
	    			}else if("inet".equals(dbType)){
					
    stringBuffer.append(TEXT_19);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_22);
    
	    			}else if("map".equals(dbType)){
					
    stringBuffer.append(TEXT_23);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_24);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_25);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_26);
    
	    			}else if("set".equals(dbType)){
					
    stringBuffer.append(TEXT_27);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_30);
    
	    			}
	    		}else if(javaType == JavaTypesManager.LIST){
				
    stringBuffer.append(TEXT_31);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_34);
    
	        	}else if(javaType == JavaTypesManager.BOOLEAN){
				
    stringBuffer.append(TEXT_35);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_38);
    
	    		}else if(javaType == JavaTypesManager.BYTE_ARRAY){
				
    stringBuffer.append(TEXT_39);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_42);
    
				}else if(javaType == JavaTypesManager.BYTE){
				
    stringBuffer.append(TEXT_43);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_46);
            
	    		}else if(javaType == JavaTypesManager.DATE){
	        		if("timestamp".equals(dbType)){
	        		
    stringBuffer.append(TEXT_47);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_50);
    
	        		}else if("date".equals(dbType)){
	        		
    stringBuffer.append(TEXT_51);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_54);
    
	        		} 
	    		}else if(javaType == JavaTypesManager.BIGDECIMAL){
				
    stringBuffer.append(TEXT_55);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_58);
    
	    		}else if(javaType == JavaTypesManager.DOUBLE){
				
    stringBuffer.append(TEXT_59);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_62);
    
	    		}else if(javaType == JavaTypesManager.FLOAT){
				
    stringBuffer.append(TEXT_63);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_65);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_66);
    
	    		}else if(javaType == JavaTypesManager.INTEGER){
				
    stringBuffer.append(TEXT_67);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_70);
    
	    		}else if(javaType == JavaTypesManager.LONG){
	    			if("time".equals(dbType)){
	        		
    stringBuffer.append(TEXT_71);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_74);
    
	        		}else{
					
    stringBuffer.append(TEXT_75);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_78);
    
					}
	    		}else if(javaType == JavaTypesManager.SHORT){
	    		
    stringBuffer.append(TEXT_79);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_81);
    stringBuffer.append(dbColumnName);
    stringBuffer.append(TEXT_82);
    
	    		}
	    		
    stringBuffer.append(TEXT_83);
    
    	} // end for
	}

	protected void generateIsNullableCode(String outputName, IMetadataColumn column) {
		if(column.isNullable()) {

    stringBuffer.append(TEXT_84);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_86);
    
		} else {

    stringBuffer.append(TEXT_87);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_88);
    
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

} // end class TCassandraLookupInputUtil

    stringBuffer.append(TEXT_89);
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
final INode node = (INode)codeGenArgument.getArgument();
final String cid = node.getUniqueName();

final TCassandraLookupInputUtil tCassandraLookupInputUtil = new TCassandraLookupInputUtil(node);
final CassandraSparkStorage storage = new CassandraSparkStorage(node);

String validateError = org.talend.designer.common.tmap.AbstractTMapAdapter.validatePullLookupConfig(node);
if (validateError != null) {
    // Cause the job compilation to explicitly fail if there is a problem.
    return "{throw new JobConfigurationError(\"" + validateError +"\");}";
}

String outputName = tCassandraLookupInputUtil.getOutgoingConnection().getName();
String structName = codeGenArgument.getRecordStructName(tCassandraLookupInputUtil.getOutgoingConnection());

TMapAdapter tMapAdapter = org.talend.designer.common.tmap.AbstractTMapAdapter.createForTMap(
        tCassandraLookupInputUtil.getOutgoingConnection().getTarget());
String inputMainName = tMapAdapter.getInputMainName();
String structMainName = codeGenArgument.getRecordStructName(inputMainName);

    stringBuffer.append(TEXT_90);
    
	// Connection pool code generation.
	
	// Warning : the object factory class has to be generated elsewhere, since the factory implementation depends of
	// the actual pooling object we want to store in this pool.
	
	// This file should be included in every single Spark and Spark Streaming component sparkcode.javajet
	// which requires to create connections to an external system without relying on a dedicated Spark connector. 

    stringBuffer.append(TEXT_91);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(storage.getPoolMaxTotal());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(storage.getPoolMaxWait());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(storage.getPoolMinIdle());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(storage.getPoolMaxIdle());
    stringBuffer.append(TEXT_101);
    
		if(storage.isPoolUseEviction()) {

    stringBuffer.append(TEXT_102);
    stringBuffer.append(storage.getPoolTimeBetweenEviction());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(storage.getPoolEvictionMinIdleTime());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(storage.getPoolEvictionSoftMinIdleTime());
    stringBuffer.append(TEXT_105);
    
		}

    stringBuffer.append(TEXT_106);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(storage.getFactoryClassName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_112);
    
		if(tCassandraLookupInputUtil.getKeySpace() != null && !"".equals(tCassandraLookupInputUtil.getKeySpace())) {

    stringBuffer.append(TEXT_113);
    stringBuffer.append(tCassandraLookupInputUtil.getKeySpace());
    stringBuffer.append(TEXT_114);
    
		} else {

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
    
		TSetKeystoreUtil tSetKeystoreUtil = tCassandraLookupInputUtil.getTSetKeystoreUtil();
		if(tSetKeystoreUtil.useHTTPS()){
		
    stringBuffer.append(TEXT_121);
    if(tSetKeystoreUtil.needClientAuth()){
    stringBuffer.append(TEXT_122);
    stringBuffer.append(tSetKeystoreUtil.getKeyStoreType());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePath());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(tSetKeystoreUtil.getKeyStorePassword());
    stringBuffer.append(TEXT_126);
    }
    stringBuffer.append(TEXT_127);
    stringBuffer.append(tSetKeystoreUtil.getTrustStoreType());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePath());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(tSetKeystoreUtil.getTrustStorePassword());
    stringBuffer.append(TEXT_130);
    
		}
		
    stringBuffer.append(TEXT_131);
    
		if(tCassandraLookupInputUtil.canEncryptPassword()) {

    stringBuffer.append(TEXT_132);
    stringBuffer.append(tCassandraLookupInputUtil.getPassword());
    stringBuffer.append(TEXT_133);
    
		} else {

    stringBuffer.append(TEXT_134);
    stringBuffer.append(tCassandraLookupInputUtil.getPassword());
    stringBuffer.append(TEXT_135);
    
		}

    stringBuffer.append(TEXT_136);
    stringBuffer.append(tCassandraLookupInputUtil.getHost());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(tCassandraLookupInputUtil.getPort());
    stringBuffer.append(TEXT_138);
    
		if(tCassandraLookupInputUtil.isAuthenticationRequired()) {

    stringBuffer.append(TEXT_139);
    stringBuffer.append(tCassandraLookupInputUtil.getUsername());
    stringBuffer.append(TEXT_140);
    
		}

    stringBuffer.append(TEXT_141);
    if(tSetKeystoreUtil.useHTTPS()){
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(storage.getPooledObjectClassName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_150);
    stringBuffer.append(structMainName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(inputMainName);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(tCassandraLookupInputUtil.getQuery());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_156);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_157);
    
				tCassandraLookupInputUtil.generateRowStructCode(outputName);

    stringBuffer.append(TEXT_158);
    stringBuffer.append(outputName);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(tCassandraLookupInputUtil.getQuery());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(storage.getPoolClassName());
    stringBuffer.append(TEXT_161);
    return stringBuffer.toString();
  }
}
