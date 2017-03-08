package org.talend.designer.codegen.translators.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import org.talend.core.CorePlugin;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.ProcessUtils;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.common.tsetkeystore.TSetKeystoreUtil;
import org.talend.designer.runprocess.ProcessorException;
import org.talend.designer.runprocess.ProcessorUtilities;

public class Sparkstreaming_footerJava
{
  protected static String nl;
  public static synchronized Sparkstreaming_footerJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Sparkstreaming_footerJava result = new Sparkstreaming_footerJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        throw new java.lang.RuntimeException(\"A Spark job can't have more than 1 tS3Configuration defined in the designer.\");";
  protected final String TEXT_2 = NL + "        throw new java.lang.RuntimeException(\"A Spark job can't have more than 1 tTachyonConfiguration defined in the designer.\");";
  protected final String TEXT_3 = NL + "        throw new java.lang.RuntimeException(\"A Spark job can't have more than 1 tHDFSConfiguration defined in the designer.\");";
  protected final String TEXT_4 = NL + "        throw new java.lang.RuntimeException(\"A Spark job can't have more than 1 tCassandraConfiguration defined in the designer.\");";
  protected final String TEXT_5 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_6 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_7 = ");";
  protected final String TEXT_8 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_9 = " = ";
  protected final String TEXT_10 = "; ";
  protected final String TEXT_11 = NL + "                throw new java.lang.RuntimeException(\"A Spark job cannot support multiple amazon account. Please be sure to use the  same accessKey everywhere\");";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "public static class TalendPipelineModel implements java.io.Serializable {" + NL + "" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL + "\t" + NL + "\tprivate org.apache.spark.ml.PipelineModel pipelineModel;" + NL + "\tprivate java.util.Map<String, String> params;" + NL + "\t" + NL + "\tpublic TalendPipelineModel(org.apache.spark.ml.PipelineModel pipelineModel) {" + NL + "\t\tthis.pipelineModel = pipelineModel;" + NL + "\t\tthis.params = new java.util.HashMap<String, String>();" + NL + "\t}" + NL + "\t" + NL + "\tpublic TalendPipelineModel(org.apache.spark.ml.PipelineModel pipelineModel, java.util.Map<String, String> params) {" + NL + "\t\tthis.pipelineModel = pipelineModel;" + NL + "\t\tthis.params = params;" + NL + "\t}" + NL + "\t" + NL + "\tpublic org.apache.spark.ml.PipelineModel getPipelineModel() {" + NL + "\t\treturn this.pipelineModel;" + NL + "\t}" + NL + "\t" + NL + "\tpublic java.util.Map<String, String> getParams() {" + NL + "\t\treturn this.params;" + NL + "\t}" + NL + "}  ";
  protected final String TEXT_14 = NL + "public static class TalendPipeline implements java.io.Serializable {" + NL + "" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL + "\t" + NL + "\tprivate org.apache.spark.ml.Pipeline pipeline;" + NL + "\tprivate java.util.Map<String, String> params;" + NL + "\t" + NL + "\tpublic TalendPipeline(org.apache.spark.ml.Pipeline pipeline) {" + NL + "\t\tthis.pipeline = pipeline;" + NL + "\t\tthis.params = new java.util.HashMap<String, String>();" + NL + "\t}" + NL + "\t" + NL + "\tpublic TalendPipeline(org.apache.spark.ml.Pipeline pipeline, java.util.Map<String, String> params) {" + NL + "\t\tthis.pipeline = pipeline;" + NL + "\t\tthis.params = params;" + NL + "\t}" + NL + "\t" + NL + "\tpublic org.apache.spark.ml.Pipeline getPipeline() {" + NL + "\t\treturn this.pipeline;" + NL + "\t}" + NL + "\t" + NL + "\tpublic java.util.Map<String, String> getParams() {" + NL + "\t\treturn this.params;" + NL + "\t}" + NL + "}  ";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + "public static class TalendKryoRegistrator implements org.apache.spark.serializer.KryoRegistrator {" + NL + "\t\t" + NL + "\t@Override" + NL + "\tpublic void registerClasses(com.esotericsoftware.kryo.Kryo kryo) {" + NL + "\t\ttry {" + NL + "\t\t\tkryo.register(Class.forName(\"org.talend.bigdata.dataflow.keys.JoinKeyRecord\"));" + NL + "\t\t} catch (java.lang.ClassNotFoundException e) {" + NL + "\t\t\t// Ignore" + NL + "\t\t}" + NL + "\t\t\t\t\t";
  protected final String TEXT_17 = "\t\t" + NL + "\t\ttry {" + NL + "\t\t\tkryo.register(" + NL + "\t\t\t\tClass.forName(\"org.apache.avro.generic.GenericData$Record\"), " + NL + "\t\t\t\tnew org.talend.bigdata.dataflow.serializer.KryoAvroRecordSerializer());" + NL + "\t\t} catch (java.lang.ClassNotFoundException e) {" + NL + "\t\t\t// Ignore" + NL + "\t\t}";
  protected final String TEXT_18 = NL + NL + "\t\tkryo.register(java.net.InetAddress.class, new InetAddressSerializer());" + NL + "\t\tkryo.addDefaultSerializer(java.net.InetAddress.class, new InetAddressSerializer());" + NL + "\t";
  protected final String TEXT_19 = NL + "\t\t\tkryo.register(TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "\t\t\tkryo.addDefaultSerializer(TalendPipelineModel.class, new TalendPipelineModelSerializer());" + NL + "\t\t\tkryo.register(TalendPipeline.class, new TalendPipelineSerializer());" + NL + "\t\t\tkryo.addDefaultSerializer(TalendPipeline.class, new TalendPipelineSerializer());" + NL + "\t";
  protected final String TEXT_20 = "\t\t\t" + NL + "\t\tkryo.register(";
  protected final String TEXT_21 = ".class);" + NL + "\t";
  protected final String TEXT_22 = NL + "\t}" + NL + "}" + NL + "\t" + NL + "public static class InetAddressSerializer extends com.esotericsoftware.kryo.Serializer<java.net.InetAddress> {" + NL + "" + NL + "\t@Override" + NL + "\tpublic void write(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Output output, java.net.InetAddress value) {" + NL + "\t\toutput.writeInt(value.getAddress().length);" + NL + "\t\toutput.writeBytes(value.getAddress());" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic java.net.InetAddress read(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Input input, Class<java.net.InetAddress> paramClass) {" + NL + "\t\tjava.net.InetAddress inetAddress = null;" + NL + "\t\ttry {" + NL + "\t\t\tint length = input.readInt();" + NL + "\t\t\tbyte[] address = input.readBytes(length);" + NL + "\t\t\tinetAddress = java.net.InetAddress.getByAddress(address);" + NL + "\t\t} catch (java.net.UnknownHostException e) {" + NL + "\t\t\t// Cannot recreate InetAddress instance : return null" + NL + "\t\t} catch (com.esotericsoftware.kryo.KryoException e) {" + NL + "\t\t\t// Should not happen since write() and read() methods are consistent, but if it does happen, it is an unrecoverable error." + NL + "            throw new RuntimeException(e);" + NL + "\t\t}" + NL + "\t\treturn inetAddress;" + NL + "\t}" + NL + "}" + NL;
  protected final String TEXT_23 = NL + "public static class TalendPipelineModelSerializer extends com.esotericsoftware.kryo.Serializer<TalendPipelineModel> {" + NL + "" + NL + "\t@Override" + NL + "\tpublic void write(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Output output, TalendPipelineModel value) {" + NL + "\t\tkryo.writeObject(output, value.getParams(), new com.esotericsoftware.kryo.serializers.MapSerializer());" + NL + "\t\tkryo.writeObject(output, value.getPipelineModel(), new com.esotericsoftware.kryo.serializers.JavaSerializer());" + NL + "\t}" + NL + "" + NL + "\t@Override" + NL + "\tpublic TalendPipelineModel read(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Input input, Class<TalendPipelineModel> paramClass) {" + NL + "\t\tTalendPipelineModel talendPipelineModel = null;" + NL + "\t\ttry {" + NL + "\t\t\t@SuppressWarnings(\"unchecked\")" + NL + "\t\t\tjava.util.Map<String, String> params = kryo.readObject(input, java.util.HashMap.class, new com.esotericsoftware.kryo.serializers.MapSerializer());" + NL + "\t\t\torg.apache.spark.ml.PipelineModel pipelineModel = kryo.readObject(input, org.apache.spark.ml.PipelineModel.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());" + NL + "\t\t\ttalendPipelineModel = new TalendPipelineModel(pipelineModel, params);" + NL + "\t\t} catch (com.esotericsoftware.kryo.KryoException e) {" + NL + "\t\t\t// Should not happen since write() and read() methods are consistent, but if it does happen, it is an unrecoverable error." + NL + "            throw new RuntimeException(e);" + NL + "\t\t}" + NL + "\t\treturn talendPipelineModel;" + NL + "\t}" + NL + "}" + NL + "" + NL + "" + NL + "public static class TalendPipelineSerializer extends com.esotericsoftware.kryo.Serializer<TalendPipeline> {" + NL + "" + NL + "    @Override" + NL + "    public void write(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Output output, TalendPipeline value) {" + NL + "        kryo.writeObject(output, value.getParams(), new com.esotericsoftware.kryo.serializers.MapSerializer());" + NL + "        kryo.writeObject(output, value.getPipeline(), new com.esotericsoftware.kryo.serializers.JavaSerializer());" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    public TalendPipeline read(com.esotericsoftware.kryo.Kryo kryo, com.esotericsoftware.kryo.io.Input input, Class<TalendPipeline> paramClass) {" + NL + "        TalendPipeline talendPipeline = null;" + NL + "        try {" + NL + "            @SuppressWarnings(\"unchecked\")" + NL + "            java.util.Map<String, String> params = kryo.readObject(input, java.util.HashMap.class, new com.esotericsoftware.kryo.serializers.MapSerializer());" + NL + "            org.apache.spark.ml.Pipeline pipeline = kryo.readObject(input, org.apache.spark.ml.Pipeline.class, new com.esotericsoftware.kryo.serializers.JavaSerializer());" + NL + "            talendPipeline = new TalendPipeline(pipeline, params);" + NL + "        } catch (com.esotericsoftware.kryo.KryoException e) {" + NL + "\t\t\t// Should not happen since write() and read() methods are consistent, but if it does happen, it is an unrecoverable error." + NL + "            throw new RuntimeException(e);" + NL + "        }" + NL + "        return talendPipeline;" + NL + "    }" + NL + "}";
  protected final String TEXT_24 = NL + NL + "    public String resuming_logs_dir_path = null;" + NL + "    public String resuming_checkpoint_path = null;" + NL + "    public String parent_part_launcher = null;" + NL + "    public String pid = \"0\";" + NL + "    public String rootPid = null;" + NL + "    public String fatherPid = null;" + NL + "    public Integer portStats = null;" + NL + "    public String clientHost;" + NL + "    public String defaultClientHost = \"localhost\";" + NL + "    public String libjars = null;" + NL + "    private boolean execStat = true;" + NL + "    public boolean isChildJob = false;" + NL + "    public String fatherNode = null;" + NL + "    public String log4jLevel = \"\";" + NL + "    public boolean doInspect = false;";
  protected final String TEXT_25 = NL + "        private java.util.List<String> livyArgs = new java.util.ArrayList<>();";
  protected final String TEXT_26 = NL + NL + "    public String contextStr = \"";
  protected final String TEXT_27 = "\";" + NL + "    public boolean isDefaultContext = true;" + NL + "" + NL + "    private java.util.Properties context_param = new java.util.Properties();" + NL + "    public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();" + NL + "" + NL + "    public String status= \"\";" + NL + "" + NL + "    public static void main(String[] args) throws java.lang.RuntimeException {";
  protected final String TEXT_28 = NL + "                new java.lang.Exception(\"In Talend, Spark Streaming jobs only support one job. Please deactivate the extra jobs.\").printStackTrace();" + NL + "                int exitCode = 1;";
  protected final String TEXT_29 = NL + "            int exitCode = new ";
  protected final String TEXT_30 = "().runJobInTOS(args);";
  protected final String TEXT_31 = NL;
  protected final String TEXT_32 = NL + "            if(exitCode == 0) {" + NL + "                log.info(\"TalendJob: '";
  protected final String TEXT_33 = "' - Done.\");" + NL + "            } else {" + NL + "                log.error(\"TalendJob: '";
  protected final String TEXT_34 = "' - Failed with exit code: \" + exitCode + \".\");" + NL + "            }";
  protected final String TEXT_35 = NL + "        if(exitCode == 0) {" + NL + "            System.exit(exitCode);" + NL + "        } else {" + NL + "            throw new java.lang.RuntimeException(\"TalendJob: '";
  protected final String TEXT_36 = "' - Failed with exit code: \" + exitCode + \".\");" + NL + "        }" + NL + "    }" + NL;
  protected final String TEXT_37 = NL + "            @Test" + NL + "            public void test";
  protected final String TEXT_38 = "() throws java.lang.Exception{";
  protected final String TEXT_39 = NL + "                if(";
  protected final String TEXT_40 = "<=0){" + NL + "                    throw new java.lang.Exception(\"There is no tCollectAndCheck in your test case!\");" + NL + "                }" + NL + "                junitGlobalMap.put(\"tests.log\",new String());" + NL + "                junitGlobalMap.put(\"tests.nbFailure\",new Integer(0));" + NL + "                final ";
  protected final String TEXT_41 = " ";
  protected final String TEXT_42 = "Class = new ";
  protected final String TEXT_43 = "();" + NL + "                java.util.List<String> paraList_";
  protected final String TEXT_44 = " = new java.util.ArrayList<String>();" + NL + "                paraList_";
  protected final String TEXT_45 = ".add(\"--context=";
  protected final String TEXT_46 = "\");";
  protected final String TEXT_47 = NL + "                        paraList_";
  protected final String TEXT_48 = ".add(\"--context_param\");" + NL + "                        paraList_";
  protected final String TEXT_49 = ".add(\"";
  protected final String TEXT_50 = "=\" + ";
  protected final String TEXT_51 = ");";
  protected final String TEXT_52 = NL + "                String[] arrays = new String[paraList_";
  protected final String TEXT_53 = ".size()];" + NL + "                for(int i=0;i<paraList_";
  protected final String TEXT_54 = ".size();i++){" + NL + "                    arrays[i] = (String)paraList_";
  protected final String TEXT_55 = ".get(i);" + NL + "                }";
  protected final String TEXT_56 = NL + "            ";
  protected final String TEXT_57 = "Class.runJobInTOS(arrays);" + NL + "" + NL + "            String errors = (String)junitGlobalMap.get(\"tests.log\");" + NL + "            Integer nbFailure = (Integer)junitGlobalMap.get(\"tests.nbFailure\");" + NL + "            assertTrue(\"Failure=\"+nbFailure+java.lang.System.getProperty(\"line.separator\")+errors, errors.isEmpty());" + NL + "" + NL + "            }";
  protected final String TEXT_58 = NL + NL + "    public String[][] runJob(String[] args){";
  protected final String TEXT_59 = NL + "                new java.lang.Exception(\"In Talend, Spark Streaming jobs only support one job. Please deactivate the extra jobs.\").printStackTrace();" + NL + "                 String[][] bufferValue = new String[][] { { Integer.toString(1) } };";
  protected final String TEXT_60 = NL + "            int exitCode = runJobInTOS(args);" + NL + "            String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };";
  protected final String TEXT_61 = NL + "        return bufferValue;" + NL + "    }" + NL + "" + NL + "    public int runJobInTOS (String[] args) {" + NL + "        normalizeArgs(args);" + NL + "" + NL + "        String lastStr = \"\";" + NL + "        for (String arg : args) {" + NL + "            if (arg.equalsIgnoreCase(\"--context_param\")) {" + NL + "                lastStr = arg;" + NL + "            } else if (lastStr.equals(\"\")) {" + NL + "                evalParam(arg);" + NL + "            } else {" + NL + "                evalParam(lastStr + \" \" + arg);" + NL + "                lastStr = \"\";" + NL + "            }" + NL + "        }" + NL;
  protected final String TEXT_62 = NL + "            if(!\"\".equals(log4jLevel)){" + NL + "                if(\"trace\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.TRACE);" + NL + "                }else if(\"debug\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.DEBUG);" + NL + "                }else if(\"info\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.INFO);" + NL + "                }else if(\"warn\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.WARN);" + NL + "                }else if(\"error\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.ERROR);" + NL + "                }else if(\"fatal\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.FATAL);" + NL + "                }else if (\"off\".equalsIgnoreCase(log4jLevel)){" + NL + "                    log.setLevel(org.apache.log4j.Level.OFF);" + NL + "                }" + NL + "                org.apache.log4j.Logger.getRootLogger().setLevel(log.getLevel());" + NL + "            }" + NL + "            log.info(\"TalendJob: '";
  protected final String TEXT_63 = "' - Start.\");";
  protected final String TEXT_64 = NL + NL + "        initContext();" + NL + "" + NL + "        if (clientHost == null) {" + NL + "            clientHost = defaultClientHost;" + NL + "        }" + NL + "" + NL + "        if (pid == null || \"0\".equals(pid)) {" + NL + "            pid = TalendString.getAsciiRandomString(6);" + NL + "        }" + NL + "" + NL + "        if (rootPid == null) {" + NL + "            rootPid = pid;" + NL + "        }" + NL + "        if (fatherPid == null) {" + NL + "            fatherPid = pid;" + NL + "        } else {" + NL + "            isChildJob = true;" + NL + "        }" + NL;
  protected final String TEXT_65 = NL + NL + "            if (portStats != null) {" + NL + "                // portStats = -1; //for testing" + NL + "                if (portStats < 0 || portStats > 65535) {" + NL + "                    // issue:10869, the portStats is invalid, so this client socket" + NL + "                    // can't open" + NL + "                    System.err.println(\"The statistics socket port \" + portStats" + NL + "                            + \" is invalid.\");" + NL + "                    execStat = false;" + NL + "                }" + NL + "            } else {" + NL + "                execStat = false;" + NL + "            }" + NL + "" + NL + "            if (execStat) {" + NL + "                try {" + NL + "                    runStat.startThreadStat(clientHost, portStats);" + NL + "                    runStat.setAllPID(rootPid, fatherPid, pid);" + NL + "                } catch (java.io.IOException ioException) {" + NL + "                    ioException.printStackTrace();" + NL + "                }" + NL + "            }";
  protected final String TEXT_66 = NL + "            if(!\"\".equals(";
  protected final String TEXT_67 = ")) {" + NL + "                System.setProperty(\"HADOOP_USER_NAME\", ";
  protected final String TEXT_68 = ");" + NL + "            }";
  protected final String TEXT_69 = NL + "        String osName = System.getProperty(\"os.name\");" + NL + "        String snappyLibName = \"libsnappyjava.so\";" + NL + "        if(osName.startsWith(\"Windows\")) {" + NL + "            snappyLibName = \"snappyjava.dll\";" + NL + "        } else if(osName.startsWith(\"Mac\")) {" + NL + "            snappyLibName = \"libsnappyjava.jnilib\";" + NL + "        }" + NL + "        System.setProperty(\"org.xerial.snappy.lib.name\", snappyLibName);";
  protected final String TEXT_70 = NL + "            // Define ctx variable outside of the try, in order to close it on the catch part." + NL + "            org.apache.spark.streaming.api.java.JavaStreamingContext ctx = null;" + NL + "            try {" + NL + "                java.util.Map<String, String> tuningConf = new java.util.HashMap<>();" + NL + "                org.apache.spark.SparkConf sparkConfiguration = getConf(tuningConf);";
  protected final String TEXT_71 = NL + "                    return runSparkJobServerJob(sparkConfiguration, tuningConf);";
  protected final String TEXT_72 = NL + "                            ctx = manualClock.ssc();";
  protected final String TEXT_73 = NL + "                                if(!isLivyCall(args)) {" + NL + "                                    return runLivyJob(sparkConfiguration, livyArgs);" + NL + "                                } else {";
  protected final String TEXT_74 = NL + "                        try {" + NL + "                            org.apache.hadoop.conf.Configuration hadoopConfiguration = org.apache.spark.deploy.SparkHadoopUtil.get().newConfiguration(sparkConfiguration);" + NL + "                            org.apache.hadoop.security.UserGroupInformation.setConfiguration(hadoopConfiguration);" + NL + "                            org.apache.hadoop.security.UserGroupInformation.getLoginUser();" + NL + "                        } catch (IOException ioe) {";
  protected final String TEXT_75 = NL + "                                log.warn(ioe.getMessage());";
  protected final String TEXT_76 = NL + "                        }";
  protected final String TEXT_77 = NL + "                            ctx = new org.apache.spark.streaming.api.java.JavaStreamingContext(sparkConfiguration, new org.apache.spark.streaming.Duration(";
  protected final String TEXT_78 = "));";
  protected final String TEXT_79 = NL + NL + "                        return run(ctx);";
  protected final String TEXT_80 = NL + "                            }";
  protected final String TEXT_81 = NL + "                        return run(sparkConfiguration);";
  protected final String TEXT_82 = NL + "            } catch(Exception ex) {" + NL + "                ex.printStackTrace();" + NL + "                ex.printStackTrace(errorMessagePS);" + NL + "                if (ctx != null) {" + NL + "                    ctx.stop();";
  protected final String TEXT_83 = NL + "                        if (execStat) {" + NL + "                            runStat.stopThreadStat();" + NL + "                        }";
  protected final String TEXT_84 = NL + "                }" + NL + "                this.status = \"failure\";" + NL + "                return 1;" + NL + "            }";
  protected final String TEXT_85 = NL + "    }" + NL;
  protected final String TEXT_86 = NL + "        /**" + NL + "        *" + NL + "        * This method runs the Spark job using the SparkContext in argument." + NL + "        * @param ctx, the SparkContext." + NL + "        * @return the Spark job exit code." + NL + "        *" + NL + "        */" + NL + "        private int run(org.apache.spark.streaming.api.java.JavaStreamingContext ctx) throws java.lang.Exception {";
  protected final String TEXT_87 = NL + "\t\t            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "\t\t            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "\t\t            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_88 = ");" + NL + "\t\t            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_89 = ");" + NL + "\t\t            ";
  protected final String TEXT_90 = NL + "                    org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_91 = ", ";
  protected final String TEXT_92 = ");";
  protected final String TEXT_93 = NL + "\t\t            com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "\t\t            maprLogin.getMapRCredentialsViaKerberos(";
  protected final String TEXT_94 = ", ";
  protected final String TEXT_95 = ");" + NL + "\t\t            ";
  protected final String TEXT_96 = NL + "\t\t            System.setProperty(\"pname\", \"MapRLogin\");" + NL + "\t\t            System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "\t\t            System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_97 = ");" + NL + "\t\t            com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "\t\t            ";
  protected final String TEXT_98 = NL + "\t\t                System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_99 = ");" + NL + "\t\t                ";
  protected final String TEXT_100 = NL + "\t\t                maprLogin.setCheckUGI(false);" + NL + "\t\t                ";
  protected final String TEXT_101 = NL + "\t\t            ";
  protected final String TEXT_102 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_103 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_104 = ");";
  protected final String TEXT_105 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_106 = " = ";
  protected final String TEXT_107 = "; ";
  protected final String TEXT_108 = NL + "\t\t            maprLogin.getMapRCredentialsViaPassword(";
  protected final String TEXT_109 = ", ";
  protected final String TEXT_110 = ", decryptedPassword_";
  protected final String TEXT_111 = ", ";
  protected final String TEXT_112 = ");" + NL + "\t\t            ";
  protected final String TEXT_113 = NL + "                ctx.addStreamingListener(new TalendSparkStreamingListener(ctx.ssc()));";
  protected final String TEXT_114 = NL + NL + "        initContext();" + NL + "" + NL + "        setContext(ctx.sparkContext().hadoopConfiguration(), ctx.sparkContext());" + NL + "" + NL + "        if (doInspect) {" + NL + "            System.out.println(\"== inspect start ==\");" + NL + "            System.out.println(\"{\");" + NL + "                System.out.println(\"  \\\"SPARK_MASTER\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.master\") + \"\\\",\");" + NL + "                System.out.println(\"  \\\"SPARK_UI_PORT\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.ui.port\", \"4040\") + \"\\\",\");" + NL + "                System.out.println(\"  \\\"JOB_NAME\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.app.name\", jobName) + \"\\\"\");" + NL + "                System.out.println(\"}\"); //$NON-NLS-1$" + NL + "                System.out.println(\"== inspect end ==\");" + NL + "            }" + NL + "            globalMap = new GlobalVar(ctx.sparkContext().hadoopConfiguration());";
  protected final String TEXT_115 = NL + "                        ";
  protected final String TEXT_116 = "Process(ctx, globalMap);";
  protected final String TEXT_117 = NL + "        /**" + NL + "         *" + NL + "         * This method runs the Spark job." + NL + "         * @param sparkConfiguration, the SparkConf." + NL + "         * @return the Spark job exit code." + NL + "         *" + NL + "         */" + NL + "        private int run(org.apache.spark.SparkConf sparkConfiguration) throws java.lang.Exception {";
  protected final String TEXT_118 = NL + "                    System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                    System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                    System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_119 = ");" + NL + "                    System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_120 = ");";
  protected final String TEXT_121 = NL + "                    org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_122 = ", ";
  protected final String TEXT_123 = ");";
  protected final String TEXT_124 = NL + "                    com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "                    maprLogin.getMapRCredentialsViaKerberos(";
  protected final String TEXT_125 = ", ";
  protected final String TEXT_126 = ");";
  protected final String TEXT_127 = NL + "                    System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                    System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                    System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_128 = ");" + NL + "                    com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_129 = NL + "                        System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_130 = ");";
  protected final String TEXT_131 = NL + "                        maprLogin.setCheckUGI(false);";
  protected final String TEXT_132 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_133 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_134 = ");";
  protected final String TEXT_135 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_136 = " = ";
  protected final String TEXT_137 = "; ";
  protected final String TEXT_138 = NL + "                    maprLogin.getMapRCredentialsViaPassword(";
  protected final String TEXT_139 = ", ";
  protected final String TEXT_140 = ", decryptedPassword_";
  protected final String TEXT_141 = ", ";
  protected final String TEXT_142 = ");";
  protected final String TEXT_143 = NL + "            final org.apache.spark.SparkConf finalSparkConfiguration = sparkConfiguration;" + NL + "            class TalendJavaStreamingContextFactory implements org.apache.spark.streaming.api.java.JavaStreamingContextFactory {" + NL + "" + NL + "                private java.lang.Exception e = null;" + NL + "                private String status = null;" + NL + "                private int returnCode = 0;" + NL + "" + NL + "                @Override" + NL + "                public org.apache.spark.streaming.api.java.JavaStreamingContext create() {" + NL + "                    try {" + NL + "                        org.apache.spark.streaming.api.java.JavaStreamingContext ctx = null;";
  protected final String TEXT_144 = NL + "                                    ctx = ";
  protected final String TEXT_145 = "Process(finalSparkConfiguration, ctx);";
  protected final String TEXT_146 = NL + "                        return ctx;" + NL + "                    } catch(Exception e) {" + NL + "                            this.e = e;" + NL + "                            this.status = \"failure\";" + NL + "                            this.returnCode = 1;" + NL + "                            return null;" + NL + "                        }" + NL + "                }" + NL + "" + NL + "                public java.lang.Exception getException() { return this.e; }" + NL + "                public String getStatus() { return this.status; }" + NL + "                public int getReturnCode() { return this.returnCode; }" + NL + "            }" + NL;
  protected final String TEXT_147 = NL + "                try {" + NL + "                    org.apache.hadoop.conf.Configuration hadoopConfiguration = org.apache.spark.deploy.SparkHadoopUtil.get().newConfiguration(sparkConfiguration);" + NL + "                    org.apache.hadoop.security.UserGroupInformation.setConfiguration(hadoopConfiguration);" + NL + "                    org.apache.hadoop.security.UserGroupInformation.getLoginUser();" + NL + "                } catch (IOException ioe) {";
  protected final String TEXT_148 = NL + "                        log.warn(ioe.getMessage());";
  protected final String TEXT_149 = NL + "                }";
  protected final String TEXT_150 = NL + "            TalendJavaStreamingContextFactory factory = new TalendJavaStreamingContextFactory();";
  protected final String TEXT_151 = NL + "                org.apache.spark.streaming.api.java.JavaStreamingContext ctx = manualClock.ssc();";
  protected final String TEXT_152 = NL + "                org.apache.spark.streaming.api.java.JavaStreamingContext ctx = org.apache.spark.streaming.api.java.JavaStreamingContext.getOrCreate(";
  protected final String TEXT_153 = ", factory);";
  protected final String TEXT_154 = NL;
  protected final String TEXT_155 = NL + "                ctx.addStreamingListener(new TalendSparkStreamingListener(ctx.ssc()));";
  protected final String TEXT_156 = NL + "            initContext();" + NL + "" + NL + "            setContext(ctx.sparkContext().hadoopConfiguration(), ctx.sparkContext());" + NL + "" + NL + "            if (doInspect) {" + NL + "                System.out.println(\"== inspect start ==\");" + NL + "                System.out.println(\"{\");" + NL + "                System.out.println(\"  \\\"SPARK_MASTER\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.master\") + \"\\\",\");" + NL + "                System.out.println(\"  \\\"SPARK_UI_PORT\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.ui.port\", \"4040\") + \"\\\",\");" + NL + "                System.out.println(\"  \\\"JOB_NAME\\\": \\\"\" + ctx.sparkContext().getConf().get(\"spark.app.name\", jobName) + \"\\\"\");" + NL + "                System.out.println(\"}\"); //$NON-NLS-1$" + NL + "                System.out.println(\"== inspect end ==\");" + NL + "            }";
  protected final String TEXT_157 = NL + "            ctx.start();" + NL + "            ctx.awaitTermination(";
  protected final String TEXT_158 = ");";
  protected final String TEXT_159 = NL + "            manualClock.start();" + NL + "            while (manualClock.tick()) {" + NL + "                try {" + NL + "                    Thread.sleep(";
  protected final String TEXT_160 = "); // give time to correctly process any batch." + NL + "                    continue;" + NL + "                } catch(InterruptedException ex) {" + NL + "                    Thread.currentThread().interrupt();" + NL + "                    break;" + NL + "                }" + NL + "            }";
  protected final String TEXT_161 = NL + "                    ";
  protected final String TEXT_162 = "PostProcess(ctx, globalMap);";
  protected final String TEXT_163 = NL + "                if ((junitGlobalMap.get(\"tests.nbFailure\") != null)" + NL + "                        && (((Integer)junitGlobalMap.get(\"tests.nbFailure\")) > 0)) {" + NL + "                    manualClock.stop();" + NL + "                    Integer failedTest = (Integer) junitGlobalMap.get(\"tests.nbFailure\");" + NL + "                    String logs = (String) junitGlobalMap.get(\"tests.log\");" + NL + "                    if (failedTest == 1) {" + NL + "                        throw new RuntimeException(\"1 test failed:\" + logs);" + NL + "                    } else {" + NL + "                        throw new RuntimeException(failedTest + \" tests failed:\" + logs);" + NL + "                    }" + NL + "                }";
  protected final String TEXT_164 = NL + "        return 0;" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     *" + NL + "     * This method has the responsibility to return a Spark configuration for the Spark job to run." + NL + "     * @return a Spark configuration." + NL + "     *" + NL + "     */" + NL + "    private org.apache.spark.SparkConf getConf(java.util.Map<String, String> tuningConf) throws java.lang.Exception {" + NL + "        org.apache.spark.SparkConf sparkConfiguration = new org.apache.spark.SparkConf();" + NL + "        sparkConfiguration.set(\"spark.serializer\", \"org.apache.spark.serializer.KryoSerializer\");" + NL + "        sparkConfiguration.set(\"spark.kryo.registrator\", TalendKryoRegistrator.class.getName());" + NL + "" + NL + "        java.text.DateFormat outdfm = new java.text.SimpleDateFormat(\"yyyy_MM_dd_HH_mm_ss\");" + NL + "        String appName = projectName + \"_\" + jobName + \"_\" + jobVersion + \"_\" + outdfm.format(new java.util.Date());" + NL + "        sparkConfiguration.setAppName(appName);";
  protected final String TEXT_165 = NL + "            log.info(\"Created App:\" + appName);";
  protected final String TEXT_166 = NL + "            sparkConfiguration.setMaster(";
  protected final String TEXT_167 = ");";
  protected final String TEXT_168 = NL + "                sparkConfiguration.set(\"spark.submit.deployMode\", ";
  protected final String TEXT_169 = ");" + NL + "                if (libjars != null) {" + NL + "                    sparkConfiguration.set(\"spark.yarn.jars\", libjars);" + NL + "                }";
  protected final String TEXT_170 = NL + "            routines.system.GetJarsToRegister getJarsToRegister = new routines.system.GetJarsToRegister();" + NL + "            java.util.List<String> listJar = new java.util.ArrayList<String>();" + NL + "            if(libjars != null) {" + NL + "                String libJarUriPrefix = System.getProperty(\"os.name\").startsWith(\"Windows\")" + NL + "                        ? \"file:///\" : \"file://\";" + NL + "                for(String jar:libjars.split(\",\")) {";
  protected final String TEXT_171 = NL + "                        if(!jar.contains(\"";
  protected final String TEXT_172 = "\")) {";
  protected final String TEXT_173 = NL + "                            listJar.add(getJarsToRegister.replaceJarPaths(jar, libJarUriPrefix));";
  protected final String TEXT_174 = NL + "                        }";
  protected final String TEXT_175 = NL + "                }" + NL + "            }";
  protected final String TEXT_176 = NL + "                        routines.system.BigDataUtil.installWinutils(";
  protected final String TEXT_177 = ", getJarsToRegister.replaceJarPaths(\"";
  protected final String TEXT_178 = "\" + \"winutils-hadoop-2.6.0.exe\"));";
  protected final String TEXT_179 = NL + "            sparkConfiguration.setJars(listJar.toArray(new String[listJar.size()]));";
  protected final String TEXT_180 = NL + "                   sparkConfiguration.set(\"spark.driver.host\", ";
  protected final String TEXT_181 = ");" + NL + "                   org.apache.spark.util.Utils.setCustomHostname(";
  protected final String TEXT_182 = ");";
  protected final String TEXT_183 = NL + "                sparkConfiguration.setSparkHome(";
  protected final String TEXT_184 = ");";
  protected final String TEXT_185 = NL + "                sparkConfiguration.set(\"spark.hadoop.yarn.application.classpath\", \"";
  protected final String TEXT_186 = "\");" + NL + "                sparkConfiguration.set(\"spark.hadoop.yarn.resourcemanager.address\", ";
  protected final String TEXT_187 = ");";
  protected final String TEXT_188 = "sparkConfiguration.set(\"spark.hadoop.yarn.resourcemanager.scheduler.address\", ";
  protected final String TEXT_189 = ");";
  protected final String TEXT_190 = "sparkConfiguration.set(\"spark.hadoop.mapreduce.jobhistory.address\", ";
  protected final String TEXT_191 = ");";
  protected final String TEXT_192 = "sparkConfiguration.set(\"spark.hadoop.yarn.app.mapreduce.am.staging-dir\", ";
  protected final String TEXT_193 = ");";
  protected final String TEXT_194 = NL + "                    sparkConfiguration.set(\"spark.hadoop.mapreduce.map.memory.mb\", ";
  protected final String TEXT_195 = ");" + NL + "                    sparkConfiguration.set(\"spark.hadoop.mapreduce.reduce.memory.mb\", ";
  protected final String TEXT_196 = ");" + NL + "                    sparkConfiguration.set(\"spark.hadoop.yarn.app.mapreduce.am.resource.mb\", ";
  protected final String TEXT_197 = ");";
  protected final String TEXT_198 = NL + "                    sparkConfiguration.set(\"spark.hadoop.yarn.resourcemanager.principal\", ";
  protected final String TEXT_199 = ");" + NL + "                    sparkConfiguration.set(\"spark.hadoop.mapreduce.jobhistory.principal\", ";
  protected final String TEXT_200 = ");";
  protected final String TEXT_201 = NL + "                        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_202 = ");" + NL + "                        System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_203 = ");";
  protected final String TEXT_204 = NL + "                        org.apache.hadoop.security.UserGroupInformation.loginUserFromKeytab(";
  protected final String TEXT_205 = ", ";
  protected final String TEXT_206 = ");";
  protected final String TEXT_207 = NL + "                        com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();" + NL + "                        maprLogin.getMapRCredentialsViaKerberos(";
  protected final String TEXT_208 = ", ";
  protected final String TEXT_209 = ");";
  protected final String TEXT_210 = NL + "                        System.setProperty(\"pname\", \"MapRLogin\");" + NL + "                        System.setProperty(\"https.protocols\", \"TLSv1.2\");" + NL + "                        System.setProperty(\"mapr.home.dir\", ";
  protected final String TEXT_211 = ");" + NL + "                        com.mapr.login.client.MapRLoginHttpsClient maprLogin = new com.mapr.login.client.MapRLoginHttpsClient();";
  protected final String TEXT_212 = NL + "                            System.setProperty(\"hadoop.login\", ";
  protected final String TEXT_213 = ");";
  protected final String TEXT_214 = NL + "                            maprLogin.setCheckUGI(false);";
  protected final String TEXT_215 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_216 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_217 = ");";
  protected final String TEXT_218 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_219 = " = ";
  protected final String TEXT_220 = "; ";
  protected final String TEXT_221 = NL + "                        maprLogin.getMapRCredentialsViaPassword(";
  protected final String TEXT_222 = ", ";
  protected final String TEXT_223 = ", decryptedPassword_";
  protected final String TEXT_224 = ", ";
  protected final String TEXT_225 = ");";
  protected final String TEXT_226 = NL + "                            if(!";
  protected final String TEXT_227 = ".equals(";
  protected final String TEXT_228 = ")) {" + NL + "                                throw new RuntimeException(\"The HDFS and the Spark configurations must have the same user name.\");" + NL + "                            }";
  protected final String TEXT_229 = NL + "                            if(!\"\".equals(";
  protected final String TEXT_230 = ")) {" + NL + "                                System.setProperty(\"HADOOP_USER_NAME\", ";
  protected final String TEXT_231 = ");" + NL + "                            }";
  protected final String TEXT_232 = NL + "            System.setProperty(\"hadoop.home.dir\", ";
  protected final String TEXT_233 = ");";
  protected final String TEXT_234 = NL + "\t\t\t\t\t\t\tif(true) {" + NL + "\t\t\t\t\t\t\t\tthrow new java.lang.RuntimeException(\"The number of records per second to read from each Kafka partition must be the same between all Kafka input components.\");" + NL + "\t\t\t\t\t\t\t}";
  protected final String TEXT_235 = NL + "                tuningConf.put(\"spark.ui.port\", ";
  protected final String TEXT_236 = ");";
  protected final String TEXT_237 = NL + "            tuningConf.put(\"spark.executor.memory\", ";
  protected final String TEXT_238 = ");";
  protected final String TEXT_239 = NL + "                tuningConf.put(\"spark.yarn.executor.memoryOverhead\", ";
  protected final String TEXT_240 = ");";
  protected final String TEXT_241 = NL + "                tuningConf.put(\"spark.driver.cores\", ";
  protected final String TEXT_242 = ");" + NL + "                tuningConf.put(\"spark.driver.memory\", ";
  protected final String TEXT_243 = ");";
  protected final String TEXT_244 = NL + "                tuningConf.put(\"spark.executor.cores\", ";
  protected final String TEXT_245 = ");";
  protected final String TEXT_246 = NL + "                    tuningConf.put(\"spark.executor.instances\", ";
  protected final String TEXT_247 = ");";
  protected final String TEXT_248 = NL + "                    tuningConf.put(\"spark.dynamicAllocation.enabled\", \"true\");" + NL + "                    tuningConf.put(\"spark.shuffle.service.enabled\", \"true\");" + NL + "                    String dynInitialValue = ";
  protected final String TEXT_249 = ";" + NL + "                    tuningConf.put(\"spark.dynamicAllocation.initialExecutors\", dynInitialValue);" + NL + "                    String dynMinValue = ";
  protected final String TEXT_250 = ";" + NL + "                    tuningConf.put(\"spark.dynamicAllocation.minExecutors\", dynMinValue);";
  protected final String TEXT_251 = NL + "                        Integer iDynMaxValue = Integer.MAX_VALUE;" + NL + "                        tuningConf.put(\"spark.dynamicAllocation.maxExecutors\", new Integer(Integer.MAX_VALUE).toString());";
  protected final String TEXT_252 = NL + "                        Integer iDynMaxValue = Integer.parseInt(";
  protected final String TEXT_253 = ");" + NL + "                        tuningConf.put(\"spark.dynamicAllocation.maxExecutors\", ";
  protected final String TEXT_254 = ");";
  protected final String TEXT_255 = NL + "                    Integer iDynInitialValue = Integer.parseInt(dynInitialValue);" + NL + "                    Integer iDynMinValue = Integer.parseInt(dynMinValue);" + NL + "                    if(iDynInitialValue < iDynMinValue|| iDynInitialValue > iDynMaxValue || iDynMinValue > iDynMaxValue) {" + NL + "                        throw new RuntimeException(\"Please check your dynamicAllocation bounds, you should have min <= initial <= max\");" + NL + "                    }";
  protected final String TEXT_256 = NL + "                    tuningConf.put(\"spark.broadcast.factory\", \"org.apache.spark.broadcast.TorrentBroadcastFactory\");";
  protected final String TEXT_257 = NL + "                    tuningConf.put(\"spark.broadcast.factory\", \"org.apache.spark.broadcast.HttpBroadcastFactory\");";
  protected final String TEXT_258 = NL + "                tuningConf.put(\"spark.serializer\", ";
  protected final String TEXT_259 = ");";
  protected final String TEXT_260 = NL + "\t\t\t\t\ttuningConf.put(\"spark.streaming.backpressure.enabled\", \"true\");";
  protected final String TEXT_261 = NL + "            tuningConf.put(\"spark.eventLog.enabled\",\"true\");";
  protected final String TEXT_262 = NL + "                tuningConf.put(\"spark.eventLog.compress\",\"true\");";
  protected final String TEXT_263 = NL + "            tuningConf.put(\"spark.eventLog.dir\",";
  protected final String TEXT_264 = ");" + NL + "            tuningConf.put(\"spark.yarn.historyServer.address\",";
  protected final String TEXT_265 = ");";
  protected final String TEXT_266 = NL + "            tuningConf.put(";
  protected final String TEXT_267 = ", ";
  protected final String TEXT_268 = ");";
  protected final String TEXT_269 = NL + "        sparkConfiguration.setAll(scala.collection.JavaConversions.asScalaMap(tuningConf));";
  protected final String TEXT_270 = NL + "        sparkConfiguration.set(\"spark.local.dir\", ";
  protected final String TEXT_271 = ");";
  protected final String TEXT_272 = NL;
  protected final String TEXT_273 = NL + "            // Initialize Manual clock for test cases" + NL + "            manualClock = new TalendManualClockResource(sparkConfiguration, ";
  protected final String TEXT_274 = ");";
  protected final String TEXT_275 = NL + NL + "        return sparkConfiguration;" + NL + "    }";
  protected final String TEXT_276 = NL + "        /**" + NL + "         *" + NL + "         * This method uses the Spark JobServer REST API to submit a Spark job." + NL + "         * @param conf the Spark configuration of the job to execute." + NL + "         * @return the result of the job." + NL + "         *" + NL + "         */" + NL + "        public int runSparkJobServerJob(org.apache.spark.SparkConf conf, java.util.Map<String, String> tuningConf) throws Exception {";
  protected final String TEXT_277 = NL + "                final String hdInsightPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_278 = ");";
  protected final String TEXT_279 = NL + "                final String hdInsightPassword = ";
  protected final String TEXT_280 = ";";
  protected final String TEXT_281 = NL + "            String jobJar = \"\";" + NL + "            String[] jars = libjars.toString().split(\",\");" + NL + "            for(int i=0; i<jars.length; i++) {" + NL + "                String jar = jars[i];" + NL + "                if(jar.contains(jobName.toLowerCase())) {" + NL + "                    jobJar = jar;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            java.util.Map<String, String> confMap = new java.util.HashMap<>();" + NL + "            for(scala.Tuple2 element: java.util.Arrays.asList(conf.getAll())) {" + NL + "                confMap.put((String) element._1, (String) element._2);" + NL + "            }" + NL + "            routines.system.GetJarsToRegister getJarsToRegister = new routines.system.GetJarsToRegister();" + NL + "            org.talend.bigdata.launcher.jobserver.JobServerJob instance = new org.talend.bigdata.launcher.jobserver.SparkBatchJob.Builder()" + NL + "                .withEndpoint(";
  protected final String TEXT_282 = ")" + NL + "                .withCredentials(new org.talend.bigdata.launcher.security.HDInsightCredentials(";
  protected final String TEXT_283 = ", hdInsightPassword))" + NL + "                .withJarToExecute(jobJar)" + NL + "                .withClassToExecute(\"";
  protected final String TEXT_284 = ".";
  protected final String TEXT_285 = ".";
  protected final String TEXT_286 = "\")" + NL + "                .withAppName(projectName + \"_\" + jobName + \"_\" + jobVersion + \"_\" + pid)" + NL + "                .withConf(confMap)" + NL + "                .withTuningConf(tuningConf)" + NL + "                .build();" + NL + "" + NL + "            String jobResult = instance.executeJob();" + NL + "            if(\"ERROR\".equals(jobResult)) {" + NL + "                throw instance.getException();" + NL + "            } else if(\"OK\".equals(jobResult)) {" + NL + "                return instance.getExitCode();" + NL + "            }" + NL + "            return 1;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public Object runJob(Object sparkContext, com.typesafe.config.Config conf) {";
  protected final String TEXT_287 = NL + "                org.apache.spark.streaming.api.java.JavaStreamingContext ctx = manualClock.ssc();";
  protected final String TEXT_288 = NL + "                org.apache.spark.streaming.api.java.JavaStreamingContext ctx = new org.apache.spark.streaming.api.java.JavaStreamingContext(new org.apache.spark.api.java.JavaSparkContext((org.apache.spark.SparkContext) sparkContext), new org.apache.spark.streaming.Duration(10000));";
  protected final String TEXT_289 = NL + NL + "            int returnCode = 1;" + NL + "            try {" + NL + "                returnCode = run(ctx);" + NL + "            } catch (java.lang.Exception e) {" + NL + "                Thread.currentThread().stop(e);" + NL + "            }" + NL + "            return returnCode;" + NL + "        }";
  protected final String TEXT_290 = NL + "        public int runLivyJob(org.apache.spark.SparkConf sparkConfiguration, java.util.List livyJobArgs) throws Exception {" + NL + "            initContext();" + NL + "            routines.system.GetJarsToRegister getJarsToRegister = new routines.system.GetJarsToRegister();";
  protected final String TEXT_291 = NL + "                final String hdInsightPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_292 = ");";
  protected final String TEXT_293 = NL + "                final String hdInsightPassword = ";
  protected final String TEXT_294 = ";";
  protected final String TEXT_295 = NL + "                final String wasbPassword = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_296 = ");";
  protected final String TEXT_297 = NL + "                final String wasbPassword = ";
  protected final String TEXT_298 = ";";
  protected final String TEXT_299 = NL + NL + "            String jobJar = \"\";" + NL + "            String[] jars = libjars.toString().split(\",\");" + NL + "            for(int i=0; i<jars.length; i++) {" + NL + "                String jar = jars[i];" + NL + "                if(jar.contains(jobName.toLowerCase())) {" + NL + "                    jobJar = jar;" + NL + "                }" + NL + "            }" + NL + "" + NL + "            java.util.Map<String, String> conf = new java.util.HashMap<>();" + NL + "            for(scala.Tuple2 element: java.util.Arrays.asList(sparkConfiguration.getAll())) {" + NL + "                conf.put((String) element._1, (String) element._2);" + NL + "            }" + NL + "" + NL + "            org.talend.bigdata.launcher.fs.FileSystem azureFs = new org.talend.bigdata.launcher.fs.AzureFileSystem(\"DefaultEndpointsProtocol=https;\"" + NL + "                + \"AccountName=\"" + NL + "                + ";
  protected final String TEXT_300 = NL + "                + \";\"" + NL + "                + \"AccountKey=\" + wasbPassword, ";
  protected final String TEXT_301 = ");" + NL + "" + NL + "            org.talend.bigdata.launcher.livy.LivyJob instance = new org.talend.bigdata.launcher.livy.SparkJob.Builder()" + NL + "                .withFileSystem(azureFs)" + NL + "                .withCredentials(new org.talend.bigdata.launcher.security.HDInsightCredentials(";
  protected final String TEXT_302 = ", hdInsightPassword))" + NL + "                .withJarToExecute(jobJar)" + NL + "                .withClassToExecute(\"";
  protected final String TEXT_303 = ".";
  protected final String TEXT_304 = ".";
  protected final String TEXT_305 = "\")" + NL + "                .withLivyEndpoint(\"https://\" + ";
  protected final String TEXT_306 = " + \":\" + ";
  protected final String TEXT_307 = ")" + NL + "                .withRemoteFolder(org.talend.bigdata.launcher.utils.Utils.removeFirstSlash(";
  protected final String TEXT_308 = "))" + NL + "                .withUsername(";
  protected final String TEXT_309 = ")" + NL + "                .withLibJars(libjars.toString())" + NL + "                .withConf(conf)" + NL + "                .withArgs(livyJobArgs)" + NL + "                .withAppName(projectName + \"_\" + jobName + \"_\" + jobVersion)";
  protected final String TEXT_310 = NL + "                    .withExecutorMemory(conf.get(\"spark.executor.memory\"))" + NL + "                    .withDriverMemory(conf.get(\"spark.driver.memory\"))";
  protected final String TEXT_311 = NL + "                        .withExecutorCore(conf.get(\"spark.executor.cores\"))";
  protected final String TEXT_312 = NL + "                    .withDriverCore(conf.get(\"spark.driver.cores\"))";
  protected final String TEXT_313 = NL + "                .build();" + NL + "" + NL + "            int returnCode = instance.executeJob();" + NL + "            System.out.println(instance.getJobLog());" + NL + "            return returnCode;" + NL + "        }";
  protected final String TEXT_314 = NL + NL + "    private String genTempFolderForComponent(String name) {" + NL + "        java.io.File tempDir = new java.io.File(\"/tmp/\" + pid, name);" + NL + "        String tempDirPath = tempDir.getPath();" + NL + "        if (java.io.File.separatorChar != '/')" + NL + "            tempDirPath = tempDirPath.replace(java.io.File.separatorChar, '/');" + NL + "        return tempDirPath;" + NL + "    }" + NL + "" + NL + "    private void initContext(){" + NL + "        //get context" + NL + "        try{" + NL + "            //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead." + NL + "            java.io.InputStream inContext = ";
  protected final String TEXT_315 = ".class.getClassLoader().getResourceAsStream(\"";
  protected final String TEXT_316 = "/contexts/\"+contextStr+\".properties\");" + NL + "            if(isDefaultContext && inContext == null){" + NL + "" + NL + "            }else{" + NL + "                if(inContext!=null){" + NL + "                    //defaultProps is in order to keep the original context value" + NL + "                    defaultProps.load(inContext);" + NL + "                    inContext.close();" + NL + "                    context = new ContextProperties(defaultProps);" + NL + "                }else{" + NL + "                    //print info and job continue to run, for case: context_param is not empty." + NL + "                    System.err.println(\"Could not find the context \" + contextStr);" + NL + "                }" + NL + "            }" + NL + "" + NL + "            if(!context_param.isEmpty()){" + NL + "                context.putAll(context_param);" + NL + "            }" + NL + "            context.loadValue(context_param,null);" + NL + "            if(parentContextMap != null && !parentContextMap.isEmpty()){";
  protected final String TEXT_317 = NL + "                    if(parentContextMap.containsKey(\"";
  protected final String TEXT_318 = "\")){" + NL + "                        context.";
  protected final String TEXT_319 = " = (";
  protected final String TEXT_320 = ") parentContextMap.get(\"";
  protected final String TEXT_321 = "\");" + NL + "                    }";
  protected final String TEXT_322 = NL + "            }" + NL + "        }catch (java.io.IOException ie){" + NL + "            System.err.println(\"Could not load context \"+contextStr);" + NL + "            ie.printStackTrace();" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void setContext(Configuration conf, org.apache.spark.api.java.JavaSparkContext ctx){" + NL + "        //get context" + NL + "        //call job/subjob with an existing context, like: --context=production. if without this parameter, there will use the default context instead." + NL + "        java.net.URL inContextUrl = ";
  protected final String TEXT_323 = ".class.getClassLoader().getResource(\"";
  protected final String TEXT_324 = "/contexts/\"+contextStr+\".properties\");" + NL + "        if(isDefaultContext && inContextUrl == null){" + NL + "" + NL + "        }else{" + NL + "            if(inContextUrl!=null){" + NL + "                conf.set(ContextProperties.CONTEXT_FILE_NAME, contextStr+\".properties\");";
  protected final String TEXT_325 = NL + "                    java.io.File contextFile = new java.io.File(inContextUrl.getPath());" + NL + "                    if(contextFile.exists()) {" + NL + "                        ctx.addFile(inContextUrl.getPath());" + NL + "                    } else {" + NL + "                        java.io.InputStream contextIn = ";
  protected final String TEXT_326 = ".class.getClassLoader().getResourceAsStream(\"";
  protected final String TEXT_327 = "/contexts/\"+contextStr+\".properties\");" + NL + "                        if(contextIn != null){" + NL + "                            java.io.File tmpFile = new java.io.File(System.getProperty(\"java.io.tmpdir\") + \"/\" + jobName,  contextStr+\".properties\");" + NL + "                            java.io.OutputStream contextOut = null;" + NL + "                            try {" + NL + "                                tmpFile.getParentFile().mkdir();" + NL + "                                if(tmpFile.exists()) { tmpFile.delete(); }" + NL + "                                tmpFile.createNewFile();" + NL + "                                contextOut = new java.io.FileOutputStream(tmpFile);" + NL + "" + NL + "                                int len = -1;" + NL + "                                byte[] b = new byte[4096];" + NL + "                                while ((len = contextIn.read(b)) != -1) {" + NL + "                                    contextOut.write(b, 0, len);" + NL + "                                }" + NL + "                            } catch(java.io.IOException ioe) {" + NL + "                                ioe.printStackTrace();" + NL + "                            } finally {" + NL + "                                try {" + NL + "                                    contextIn.close();" + NL + "                                    if(contextOut != null) {" + NL + "                                        contextOut.close();" + NL + "                                    }" + NL + "                                } catch (java.io.IOException ioe) {" + NL + "                                    ioe.printStackTrace();" + NL + "                                }" + NL + "                            }" + NL + "" + NL + "                            ctx.addFile(tmpFile.getPath());" + NL + "                            tmpFile.delete();" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_328 = NL + NL + "            }" + NL + "        }" + NL + "" + NL + "        if(!context_param.isEmpty()){" + NL + "            for(Object contextKey : context_param.keySet()){" + NL + "                conf.set(ContextProperties.CONTEXT_PARAMS_PREFIX + contextKey, context.getProperty(contextKey.toString()));" + NL + "                conf.set(ContextProperties.CONTEXT_KEYS, conf.get(ContextProperties.CONTEXT_KEYS, \"\") + \" \" + contextKey);" + NL + "            }" + NL + "        }" + NL + "" + NL + "        if(parentContextMap != null && !parentContextMap.isEmpty()){";
  protected final String TEXT_329 = NL + "                if(parentContextMap.containsKey(\"";
  protected final String TEXT_330 = "\")){" + NL + "                    conf.set(ContextProperties.CONTEXT_PARENT_PARAMS_PREFIX + \"";
  protected final String TEXT_331 = "\", parentContextMap.get(\"";
  protected final String TEXT_332 = "\").toString());" + NL + "                    conf.set(ContextProperties.CONTEXT_PARENT_KEYS, conf.get(ContextProperties.CONTEXT_KEYS, \"\") + \" \" + \"";
  protected final String TEXT_333 = "\");" + NL + "                }";
  protected final String TEXT_334 = NL + "        }" + NL + "    }" + NL;
  protected final String TEXT_335 = NL + "        private boolean isLivyCall(String[] args) {" + NL + "            List<String> argsList = java.util.Arrays.asList(args);" + NL + "            int indexlibjars = argsList.indexOf(\"-calledByLivy\");" + NL + "            return (indexlibjars!=-1);" + NL + "        }";
  protected final String TEXT_336 = NL + NL + "    private void evalParam(String arg) {" + NL + "        if (arg.startsWith(\"--resuming_logs_dir_path\")) {" + NL + "            resuming_logs_dir_path = arg.substring(25);" + NL + "        } else if (arg.startsWith(\"--resuming_checkpoint_path\")) {" + NL + "            resuming_checkpoint_path = arg.substring(27);" + NL + "        } else if (arg.startsWith(\"--parent_part_launcher\")) {" + NL + "            parent_part_launcher = arg.substring(23);" + NL + "        } else if (arg.startsWith(\"--father_pid=\")) {" + NL + "            fatherPid = arg.substring(13);" + NL + "        } else if (arg.startsWith(\"--root_pid=\")) {" + NL + "            rootPid = arg.substring(11);" + NL + "        } else if (arg.startsWith(\"--pid=\")) {" + NL + "            pid = arg.substring(6);" + NL + "        } else if (arg.startsWith(\"--context=\")) {";
  protected final String TEXT_337 = NL + "                livyArgs.add(arg);";
  protected final String TEXT_338 = NL + "            contextStr = arg.substring(\"--context=\".length());" + NL + "            isDefaultContext = false;" + NL + "        } else if (arg.startsWith(\"--context_param\")) {" + NL + "            String keyValue = arg.substring(\"--context_param\".length() + 1);" + NL + "            int index = -1;" + NL + "            if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {" + NL + "                context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));" + NL + "            }";
  protected final String TEXT_339 = NL + "                livyArgs.add(arg);";
  protected final String TEXT_340 = NL + "        } else if (arg.startsWith(\"--stat_port=\")) {" + NL + "            String portStatsStr = arg.substring(12);" + NL + "            if (portStatsStr != null && !portStatsStr.equals(\"null\")) {" + NL + "                portStats = Integer.parseInt(portStatsStr);" + NL + "            }" + NL + "        } else if (arg.startsWith(\"--client_host=\")) {" + NL + "            clientHost = arg.substring(14);" + NL + "        } else if (arg.startsWith(\"--log4jLevel=\")) {" + NL + "            log4jLevel = arg.substring(13);" + NL + "        } else if (arg.startsWith(\"--inspect\")) {" + NL + "            doInspect = Boolean.valueOf(arg.substring(\"--inspect=\".length()));" + NL + "        }" + NL + "    }" + NL + "" + NL + "    private void normalizeArgs(String[] args){" + NL + "        java.util.List<String> argsList = java.util.Arrays.asList(args);" + NL + "        int indexlibjars = argsList.indexOf(\"-libjars\") + 1;" + NL + "        libjars = indexlibjars == 0 ? null : argsList.get(indexlibjars);";
  protected final String TEXT_341 = NL + "                if(!isLivyCall(args)) {";
  protected final String TEXT_342 = NL + "                    try {" + NL + "                        StringBuilder sb = new StringBuilder();" + NL + "                        routines.system.GetJarsToRegister getJarsToRegister = new routines.system.GetJarsToRegister();" + NL + "                        boolean isFirst = true;" + NL + "                        for(String libjar:libjars.split(\",\")) {" + NL + "                            if(!isFirst) {" + NL + "                                sb.append(\",\");" + NL + "                            }" + NL + "                            isFirst = false;" + NL + "                            sb.append(getJarsToRegister.replaceJarPaths(libjar));" + NL + "                        }" + NL + "                        libjars = sb.toString();" + NL + "                    } catch (java.lang.Exception e) {";
  protected final String TEXT_343 = " log.error(e.getMessage()); ";
  protected final String TEXT_344 = NL + "                    }";
  protected final String TEXT_345 = NL + "                }";
  protected final String TEXT_346 = NL + "    }" + NL + "" + NL + "    private final String[][] escapeChars = {" + NL + "        {\"\\\\\\\\\",\"\\\\\"},{\"\\\\n\",\"\\n\"},{\"\\\\'\",\"\\'\"},{\"\\\\r\",\"\\r\"}," + NL + "        {\"\\\\f\",\"\\f\"},{\"\\\\b\",\"\\b\"},{\"\\\\t\",\"\\t\"}" + NL + "        };" + NL + "    private String replaceEscapeChars (String keyValue) {" + NL + "" + NL + "        if (keyValue == null || (\"\").equals(keyValue.trim())) {" + NL + "            return keyValue;" + NL + "        }" + NL + "" + NL + "        StringBuilder result = new StringBuilder();" + NL + "        int currIndex = 0;" + NL + "        while (currIndex < keyValue.length()) {" + NL + "            int index = -1;" + NL + "            // judege if the left string includes escape chars" + NL + "            for (String[] strArray : escapeChars) {" + NL + "                index = keyValue.indexOf(strArray[0],currIndex);" + NL + "                if (index>=0) {" + NL + "" + NL + "                    result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0], strArray[1]));" + NL + "                    currIndex = index + strArray[0].length();" + NL + "                    break;" + NL + "                }" + NL + "            }" + NL + "            // if the left string doesn't include escape chars, append the left into the result" + NL + "            if (index < 0) {" + NL + "                result.append(keyValue.substring(currIndex));" + NL + "                currIndex = currIndex + keyValue.length();" + NL + "            }" + NL + "        }" + NL + "" + NL + "        return result.toString();" + NL + "    }" + NL + "" + NL + "    public String getStatus() {" + NL + "        return status;" + NL + "    }" + NL;
  protected final String TEXT_347 = NL + NL + NL + "/**" + NL + " * The class TickHandler exist to determine the timestamp oof the next tick" + NL + " * give the current timestamp" + NL + " *" + NL + " */" + NL + "public class TickHandler {" + NL + "" + NL + "    /** Time between microbatches. (in ms) */" + NL + "    private final long outDelta;" + NL + "" + NL + "    /** Expected number of microbatches. */" + NL + "    private final long expectedTickNumber;" + NL + "" + NL + "    /** Current microbatches. */" + NL + "    private long currentTickNumber;" + NL + "" + NL + "    /**" + NL + "     * Initialize the Tick Handler" + NL + "     * " + NL + "     * @param outDelta" + NL + "     *            Number of milisecond between two microbatches." + NL + "     * @param expectedTickNumber" + NL + "     *            Maximum number of microbatches." + NL + "     */" + NL + "    public TickHandler(long outDelta, long expectedTickNumber) {" + NL + "        this.expectedTickNumber = expectedTickNumber;" + NL + "        this.outDelta = outDelta;" + NL + "        this.currentTickNumber = 1l;" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Advance if necessary the internal timer to a give time." + NL + "     */" + NL + "    public void advanceTimer(long currentTimer) {" + NL + "        while ((currentTickNumber <= expectedTickNumber)" + NL + "                && (outDelta * currentTickNumber <= currentTimer)) {" + NL + "            // this tick has been processed, let move to the next one." + NL + "            currentTickNumber++;" + NL + "        }" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * " + NL + "     * Return the Timestamp of the nex tick." + NL + "     * " + NL + "     * @return the current time of the next tick of Long.MAX_VALUE if there" + NL + "     *         is no more expected microbatches" + NL + "     */" + NL + "    public long getNextTickTime() {" + NL + "        // if there is still some tick to process" + NL + "        if (currentTickNumber <= expectedTickNumber) {" + NL + "            return outDelta * currentTickNumber;" + NL + "        } else {" + NL + "            // nothing more to process => return max long" + NL + "            return Long.MAX_VALUE;" + NL + "        }" + NL + "" + NL + "    }" + NL + "" + NL + "    public long getOutDelta() {" + NL + "        return this.outDelta;" + NL + "    }" + NL + "}" + NL + "" + NL + "public class TalendManualClockResource extends" + NL + "        org.junit.rules.ExternalResource {" + NL + "" + NL + "    private org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(TalendManualClockResource.class);" + NL + "" + NL + "    /** Spark context, set up by the external resource. */" + NL + "    private org.apache.spark.streaming.api.java.JavaStreamingContext ssc;" + NL + "" + NL + "    private long currentTimestamp;" + NL + "" + NL + "    /** Time between microbatches. */" + NL + "    private final java.util.HashMap<String, TickHandler> outDeltas = new java.util.HashMap<>();" + NL + "" + NL + "    /** The result corresponding to the microbatch time. */" + NL + "    final java.util.HashMap<String, java.util.HashMap<Long, java.util.List<org.apache.avro.specific.SpecificRecordBase>>> results = new java.util.HashMap<>();" + NL + "" + NL + "    public TalendManualClockResource(org.apache.spark.SparkConf conf, long inDelta) {" + NL + "        this.currentTimestamp = 0l;" + NL + "        conf.set(\"spark.streaming.clock\", \"org.apache.spark.util.ManualClock\");" + NL + "        ssc = new org.apache.spark.streaming.api.java.JavaStreamingContext(conf, new org.apache.spark.streaming.Duration(inDelta));" + NL + "    }" + NL + "" + NL + "    @Override" + NL + "    protected void after() {" + NL + "        ssc.stop();" + NL + "        ssc.close();" + NL + "        ssc = null;" + NL + "    }" + NL + "" + NL + "    public org.apache.spark.streaming.api.java.JavaStreamingContext ssc() {" + NL + "        return ssc;" + NL + "    }" + NL + "" + NL + "    public org.apache.spark.api.java.JavaSparkContext sc() {" + NL + "        return ssc.sparkContext();" + NL + "    }" + NL + "" + NL + "    public void start() {" + NL + "        setTime(currentTimestamp);" + NL + "        ssc().start();" + NL + "    }" + NL + "" + NL + "    public boolean tick() {" + NL + "        long nextTimestamp = Long.MAX_VALUE;" + NL + "        for (TickHandler tickHandler : outDeltas.values()) {" + NL + "            LOG.debug(tickHandler.getNextTickTime());" + NL + "            nextTimestamp = Math.min(nextTimestamp, tickHandler.getNextTickTime());" + NL + "        }" + NL + "        if (nextTimestamp == Long.MAX_VALUE) {" + NL + "            LOG.debug(\"Every manual clock is completed.\");" + NL + "            return false;" + NL + "        }" + NL + "        LOG.debug(\"Next timer:\" + nextTimestamp);" + NL + "        advance(nextTimestamp - currentTimestamp);" + NL + "        waitTillTime(nextTimestamp + 1);" + NL + "        for (TickHandler tickHandler : outDeltas.values()) {" + NL + "            tickHandler.advanceTimer(nextTimestamp);" + NL + "        }" + NL + "        currentTimestamp = nextTimestamp + 1;" + NL + "        return true;" + NL + "    }" + NL + "" + NL + "    public void stop() {" + NL + "        ssc().stop();" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Prepares a fixed set of microbatches as a streaming input." + NL + "     */" + NL + "    public <T> org.apache.spark.streaming.api.java.JavaDStream<T> prepareInput(T[]... input) {" + NL + "        java.util.LinkedList<org.apache.spark.api.java.JavaRDD<T>> rdds = com.google.common.collect.Lists.newLinkedList();" + NL + "        for (T[] t : input) {" + NL + "            rdds.add(ssc.sparkContext().parallelize(java.util.Arrays.asList(t)));" + NL + "        }" + NL + "        return ssc().queueStream(rdds);" + NL + "    }" + NL + "" + NL + "    public <T> org.apache.spark.streaming.api.java.JavaDStream<T> prepareInput(java.util.List<java.util.List<T>> inputs) {" + NL + "        java.util.LinkedList<org.apache.spark.api.java.JavaRDD<T>> rdds = com.google.common.collect.Lists.newLinkedList();" + NL + "        for (java.util.List<T> t : inputs) {" + NL + "            rdds.add(ssc.sparkContext().parallelize(t));" + NL + "        }" + NL + "        return ssc().queueStream(rdds);" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Uses the given output to test for results." + NL + "     */" + NL + "    public <T extends org.apache.avro.specific.SpecificRecordBase> void prepareOutput(" + NL + "            org.apache.spark.streaming.api.java.JavaDStream<T> toTest, String component, long outDelta, long expectedTickNumber) {" + NL + "        results.put(component, new java.util.HashMap<Long, java.util.List<org.apache.avro.specific.SpecificRecordBase>>());" + NL + "        outDeltas.put(component, new TickHandler(outDelta, expectedTickNumber));" + NL + "        toTest.foreachRDD(new AdvanceTimer<T>(toTest.context().scheduler().clock(), component));" + NL + "    }" + NL + "" + NL + "    /**" + NL + "     * Get the results for the given microbatch." + NL + "     */" + NL + "    public java.util.List<org.apache.avro.specific.SpecificRecordBase> getActual(String component, int microbatch) {" + NL + "        LOG.info(\"Getting \" + component + \" at \" + outDeltas.get(component).getOutDelta() * (microbatch + 1));" + NL + "        LOG.debug(results.get(component));" + NL + "        return results.get(component).get(outDeltas.get(component).getOutDelta() * (microbatch + 1));" + NL + "    }" + NL + "" + NL + "    private void setTime(long timeToSet) {" + NL + "        ((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).setTime(timeToSet);" + NL + "    }" + NL + "" + NL + "    public long getTime() {" + NL + "        return ssc.ssc().scheduler().clock().getTimeMillis();" + NL + "    }" + NL + "" + NL + "    private void advance(long timeToAdd) {" + NL + "        ((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).advance(timeToAdd);" + NL + "    }" + NL + "" + NL + "    private void waitTillTime(long time) {" + NL + "        LOG.debug(\"Moving internal timestamp from:\" + ((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).getTimeMillis());" + NL + "        LOG.debug(\"To:\" + time);" + NL + "        long end = System.currentTimeMillis() + 10000;" + NL + "        while (System.currentTimeMillis() < end" + NL + "                && ((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).getTimeMillis() < time) {" + NL + "            try {" + NL + "                Thread.sleep(100l);" + NL + "            } catch (InterruptedException e) {" + NL + "                e.printStackTrace();" + NL + "            }" + NL + "        }" + NL + "        if (((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).getTimeMillis() < time) {" + NL + "            ((org.apache.spark.util.ManualClock) ssc.ssc().scheduler().clock()).advance(1);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public class AdvanceTimer<T extends org.apache.avro.specific.SpecificRecordBase> implements" + NL + "            org.apache.spark.api.java.function.Function<org.apache.spark.api.java.JavaRDD<T>, Void> {" + NL + "" + NL + "        private final org.apache.spark.util.Clock clock;" + NL + "" + NL + "        private final String component;" + NL + "" + NL + "        public AdvanceTimer(org.apache.spark.util.Clock clock, String component) {" + NL + "            this.clock = clock;" + NL + "            this.component = component;" + NL + "        }" + NL + "" + NL + "        @Override" + NL + "        public Void call(org.apache.spark.api.java.JavaRDD<T> rdd) throws Exception {" + NL + "            LOG.debug(component + \" proccessing batch at:\" + clock.getTimeMillis());" + NL + "            long time = clock.getTimeMillis();" + NL + "            if (time % outDeltas.get(component).getOutDelta() == 0) {" + NL + "                results.get(component).put(time, (java.util.List<org.apache.avro.specific.SpecificRecordBase>) rdd.collect());" + NL + "                ((org.apache.spark.util.ManualClock) clock).advance(1);" + NL + "                LOG.debug(component + \" advancing clock to:\" + clock.getTimeMillis());" + NL + "            } else {" + NL + "                // Allign the current timestamp with the outDelta" + NL + "                results.get(component).put(time - time % outDeltas.get(component).getOutDelta()," + NL + "                        (java.util.List<org.apache.avro.specific.SpecificRecordBase>) rdd.collect());" + NL + "            }" + NL + "            return null;" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_348 = NL + NL + "}";
  protected final String TEXT_349 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    Vector v = (Vector) codeGenArgument.getArgument();
    IProcess process = (IProcess)v.get(0);
    List<INode> rootNodes = (List<INode>)v.get(1);
    INode sparkConfig = (INode)v.get(2);
    org.talend.hadoop.distribution.ESparkVersion sparkVersion
            = org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(sparkConfig);

    Boolean isThereAtLeastOneMLComponent = (Boolean) v.get(4);

    String processId = process.getId();

    String cid = "Spark";
    String className = process.getName();
    boolean isTestContainer=ProcessUtils.isTestContainer(process);
    if (isTestContainer) {
        className = className + "Test";
    }
    String jobFolderName = JavaResourcesHelper.getJobFolderName(className, process.getVersion());

    String testCaseFolderName = "";
    IProcess baseProcess = ProcessUtils.getTestContainerBaseProcess(process);
    if (baseProcess != null) {
        testCaseFolderName = JavaResourcesHelper.getJobFolderName(baseProcess.getName(), baseProcess.getVersion()) + '/';
    }
    testCaseFolderName = testCaseFolderName + JavaResourcesHelper.getJobFolderName(process.getName(), process.getVersion());
    String jobClassPackage = codeGenArgument.getCurrentProjectName().toLowerCase() + '/' + testCaseFolderName;

    List<IContextParameter> params = new ArrayList<IContextParameter>();
    params=process.getContextManager().getDefaultContext().getContextParameterList();
    boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(process, "__LOG4J_ACTIVATE__"));
    boolean tuningProperties = "true".equals(ElementParameterParser.getValue(sparkConfig, "__ADVANCED_SETTINGS_CHECK__"));
    boolean setWebuiPort = "true".equals(ElementParameterParser.getValue(sparkConfig, "__WEB_UI_PORT_CHECK__"));
    boolean setExecutorCores = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SPARK_EXECUTOR_CORES_CHECK__"));

    String sparkMode = ElementParameterParser.getValue(sparkConfig, "__SPARK_MODE__");
    String sparkDistribution = ElementParameterParser.getValue(sparkConfig, "__DISTRIBUTION__");
    String sparkDistribVersion = ElementParameterParser.getValue(sparkConfig, "__SPARK_VERSION__");

    boolean useLocalMode = "true".equals(ElementParameterParser.getValue(process, "__SPARK_LOCAL_MODE__"));
    boolean useStandaloneMode = !useLocalMode && "CLUSTER".equals(sparkMode);
    final boolean useYarnClientMode = !useLocalMode && "YARN_CLIENT".equals(sparkMode);
    boolean useYarnClusterMode = !useLocalMode && "YARN_CLUSTER".equals(sparkMode);
    boolean useYarnMode = useYarnClusterMode || useYarnClientMode;

    boolean isExecutedThroughSparkJobServer = false;
    boolean isExecutedThroughLivy = false;

    boolean useMapRTicket = ElementParameterParser.getBooleanValue(sparkConfig, "__USE_MAPRTICKET__");
    String mapRTicketUsername = ElementParameterParser.getValue(sparkConfig, "__MAPRTICKET_USERNAME__");
    String mapRTicketCluster = ElementParameterParser.getValue(sparkConfig, "__MAPRTICKET_CLUSTER__");
    String mapRTicketDuration = ElementParameterParser.getValue(sparkConfig, "__MAPRTICKET_DURATION__");

    boolean setMapRHomeDir = ElementParameterParser.getBooleanValue(sparkConfig, "__SET_MAPR_HOME_DIR__");
    String mapRHomeDir = ElementParameterParser.getValue(sparkConfig, "__MAPR_HOME_DIR__");

    boolean setMapRHadoopLogin = ElementParameterParser.getBooleanValue(sparkConfig, "__SET_HADOOP_LOGIN__");
    String mapRHadoopLogin = ElementParameterParser.getValue(sparkConfig, "__HADOOP_LOGIN__");

    String passwordFieldName = "";

    boolean isCustom = false;
    boolean sparkUseKrb = "true".equals(ElementParameterParser.getValue(sparkConfig, "__USE_KRB__"));
    org.talend.hadoop.distribution.component.SparkStreamingComponent sparkStreamingDistrib = null;

    if(!useLocalMode) {
        try {
            sparkStreamingDistrib = (org.talend.hadoop.distribution.component.SparkStreamingComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(sparkDistribution, sparkDistribVersion);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "";
        }

        isCustom = sparkStreamingDistrib != null && sparkStreamingDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;
        isExecutedThroughSparkJobServer = !isCustom && sparkStreamingDistrib != null && sparkStreamingDistrib.isExecutedThroughSparkJobServer();
        isExecutedThroughLivy = !isCustom && sparkStreamingDistrib != null && sparkStreamingDistrib.isExecutedThroughLivy();
    }

    // Use to define if the spark version currently used is 1.3.
    boolean isSpark13 = sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3;

    boolean useCheckpoint = "true".equals(ElementParameterParser.getValue(sparkConfig, "__USE_CHECKPOINT__")) && ((sparkStreamingDistrib != null && sparkStreamingDistrib.doSupportCheckpointing()) || useLocalMode);
    String checkpointDir = ElementParameterParser.getValue(sparkConfig, "__CHECKPOINT_DIR__");

    String batchSize = ElementParameterParser.getValue(sparkConfig, "__STREAMING_BATCH_SIZE__");
    List<Map<String, String>> sparkAdvancedProperties = (List<Map<String,String>>)ElementParameterParser.getObjectValue(sparkConfig, "__SPARK_ADVANCED_PROPERTIES__");

    java.util.List<String> jarsToRegister = null;

    boolean stats = codeGenArgument.isStatistics();

    // Kerberos variables
    boolean useKrb = false;
    boolean useKeytab = false;
    String keytabPrincipal = null;
    String keytabPath = null;

    String username = null;

    // Count how many subjobs there are in the job. Currently, the Spark Streaming only supports one.
    int subjobCount = 0;
    for (INode rootNode : rootNodes) {
        if(rootNode.getOutgoingConnections().size() > 0) {
            subjobCount++;
        }
    }

    // Spark configurations for caching
    // In case we have multiple components that set different configurations, the last component in the list imposes its configurations
    List<INode> cacheConfNodes = new ArrayList<INode>();

    // Compressing RDDs is a global Spark config
    // So when at least one tCache use compression we use compression globally
    // tCacheOut nodes
    boolean cacheCompressRdd = false; // Compress the cached RDD by the tCache Component
    for (INode pNode : process.getNodesOfType("tCacheOut")) {
        cacheConfNodes.add(pNode);
        cacheCompressRdd = ("true").equals(ElementParameterParser.getValue(pNode, "__COMPRESSRDD__"));
        if(cacheCompressRdd) break;
    }

    // Compressing the RDDs is a global Spark config
    // So when at least one tReplicate use compression we use compression globally
    // tReplicate nodes
    boolean replicateCompressRdd = false; // Compress the chached RDD by the tReplicate Component
    for (INode pNode : process.getNodesOfType("tReplicate")) {
        cacheConfNodes.add(pNode);
        replicateCompressRdd = ("true").equals(ElementParameterParser.getValue(pNode, "__CACHEOUTPUT__")) && ("true").equals(ElementParameterParser.getValue(pNode, "__COMPRESSRDD__"));
        if(replicateCompressRdd) break;
    }

    boolean generatedCompressionConfig = false; // Only generate compression config once
    boolean generatedTachyonConfig = false; // Only generate tachyon config once

    // Parse nodes and setup configuration map
    for (INode pNode : cacheConfNodes) {
        boolean compressRdd = cacheCompressRdd || replicateCompressRdd;
        String compressCodec = ElementParameterParser.getValue(pNode, "__COMPRESSCODEC__");
        String storageLevel = ElementParameterParser.getValue(pNode, "__STORAGELEVEL__");
        String tachyonStoreUrl = ElementParameterParser.getValue(pNode, "__TACHYON_STORE_URL__");
        String tachyonStoreBaseDir = ElementParameterParser.getValue(pNode, "__TACHYON_STORE_BASEDIR__");

        if(!generatedTachyonConfig && storageLevel.equals("OFF_HEAP")){
            generatedTachyonConfig = true;
            Map<String, String> tachyonStoreUrlMap = new HashMap<String, String>();
            tachyonStoreUrlMap.put("PROPERTY","\"spark.tachyonStore.url\"");
            tachyonStoreUrlMap.put("VALUE",tachyonStoreUrl);
            sparkAdvancedProperties.add(tachyonStoreUrlMap);
            Map<String, String> tachyonStoreBaseDirMap = new HashMap<String, String>();
            tachyonStoreBaseDirMap.put("PROPERTY","\"spark.tachyonStore.baseDir\"");
            tachyonStoreBaseDirMap.put("VALUE",tachyonStoreBaseDir);
            sparkAdvancedProperties.add(tachyonStoreBaseDirMap);
        }

        if(!generatedCompressionConfig && compressRdd && (storageLevel.equals("MEMORY_ONLY_SER") || storageLevel.equals("MEMORY_AND_DISK_SER") || storageLevel.equals("MEMORY_AND_DISK_SER") || storageLevel.equals("MEMORY_ONLY_SER_2") || storageLevel.equals("MEMORY_AND_DISK_SER_2"))){
            generatedCompressionConfig = true;
            Map<String, String> rddCompression = new HashMap<String, String>();
            rddCompression.put("PROPERTY", "\"spark.rdd.compress\"");
            rddCompression.put("VALUE", "\"true\"");
            sparkAdvancedProperties.add(rddCompression);
            Map<String, String> rddCompressionCodec = new HashMap<String, String>();
            rddCompressionCodec.put("PROPERTY", "\"spark.io.compression.codec\"");
            rddCompressionCodec.put("VALUE", '"'+compressCodec+'"');
            sparkAdvancedProperties.add(rddCompressionCodec);
        }
    }

    // tS3Configuration component generation.
    List<INode> s3Nodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tS3Configuration")) {
        s3Nodes.add(pNode);
    }

    if(s3Nodes.size() > 1) {
        
    stringBuffer.append(TEXT_1);
    
    }
    if(s3Nodes.size() == 1) {
        INode pNode = s3Nodes.get(0);
        // the configuration components must not be considered as a root node.
        rootNodes.remove(pNode);

        Map<String, String> s3Properties = null;
        String s3FileSystem = "\"org.apache.hadoop.fs.s3native.NativeS3FileSystem\"";

        Boolean useS3a = ElementParameterParser.getBooleanValue(pNode, "__USE_S3A__");
        if (useS3a) {
            s3FileSystem = "\"org.apache.hadoop.fs.s3a.S3AFileSystem\"";
            s3Properties = new HashMap<String, String>();
            s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3n.impl\"");
            s3Properties.put("VALUE", s3FileSystem);
            sparkAdvancedProperties.add(s3Properties);
        }

        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3.impl\"");
        s3Properties.put("VALUE", s3FileSystem);
        sparkAdvancedProperties.add(s3Properties);
        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3n.impl\"");
        s3Properties.put("VALUE", s3FileSystem);
        sparkAdvancedProperties.add(s3Properties);
        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3.awsAccessKeyId\"");
        s3Properties.put("VALUE", ElementParameterParser.getValue(pNode, "__ACCESS_KEY__"));
        sparkAdvancedProperties.add(s3Properties);
        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3n.awsAccessKeyId\"");
        s3Properties.put("VALUE", ElementParameterParser.getValue(pNode, "__ACCESS_KEY__"));
        sparkAdvancedProperties.add(s3Properties);
        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3.awsSecretAccessKey\"");
        passwordFieldName = "__SECRET_KEY__";
        String password = "";
        if (ElementParameterParser.canEncrypt(pNode, passwordFieldName)) {
            password = ElementParameterParser.getEncryptedValue(pNode, passwordFieldName);
            password = "routines.system.PasswordEncryptUtil.decryptPassword(" + password + ")";
        } else {
            password = ElementParameterParser.getValue(pNode, passwordFieldName);
        }
        s3Properties.put("VALUE", password);
        sparkAdvancedProperties.add(s3Properties);
        s3Properties = new HashMap<String, String>();
        s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3n.awsSecretAccessKey\"");
        s3Properties.put("VALUE", password);
        sparkAdvancedProperties.add(s3Properties);

        if (useS3a) {
            s3Properties = new HashMap<String, String>();
            s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3a.awsAccessKeyId\"");
            s3Properties.put("VALUE", ElementParameterParser.getValue(pNode, "__ACCESS_KEY__"));
            sparkAdvancedProperties.add(s3Properties);
            s3Properties = new HashMap<String, String>();
            s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3a.awsSecretAccessKey\"");
            s3Properties.put("VALUE", password);
            sparkAdvancedProperties.add(s3Properties);
        }

        // Set endpoint
        Boolean setEndpoint = ElementParameterParser.getBooleanValue(pNode, "__SET_ENDPOINT__");
        if (setEndpoint) {
            org.talend.hadoop.distribution.component.HDFSComponent s3Distrib = null;
            try {
                s3Distrib = (org.talend.hadoop.distribution.component.HDFSComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(
                        sparkDistribution, sparkDistribVersion);
            } catch (java.lang.Exception e) {
                e.printStackTrace();
                return "";
            }
            if (s3Distrib.doSupportS3V4()) {
                String endpoint = ElementParameterParser.getValue(pNode, "__ENDPOINT__");
                s3Properties = new HashMap<String, String>();
                s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3.endpoint\"");
                s3Properties.put("VALUE", endpoint);
                sparkAdvancedProperties.add(s3Properties);

                s3Properties = new HashMap<String, String>();
                s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3n.endpoint\"");
                s3Properties.put("VALUE", endpoint);
                sparkAdvancedProperties.add(s3Properties);

                s3Properties = new HashMap<String, String>();
                s3Properties.put("PROPERTY", "\"hadoop.fs.s3.endpoint\"");
                s3Properties.put("VALUE", endpoint);
                sparkAdvancedProperties.add(s3Properties);

                s3Properties = new HashMap<String, String>();
                s3Properties.put("PROPERTY", "\"hadoop.fs.s3n.endpoint\"");
                s3Properties.put("VALUE", endpoint);
                sparkAdvancedProperties.add(s3Properties);

                if (useS3a) {
                    s3Properties = new HashMap<String, String>();
                    s3Properties.put("PROPERTY", "\"spark.hadoop.fs.s3a.endpoint\"");
                    s3Properties.put("VALUE", endpoint);
                    sparkAdvancedProperties.add(s3Properties);

                    s3Properties = new HashMap<String, String>();
                    s3Properties.put("PROPERTY", "\"hadoop.fs.s3a.endpoint\"");
                    s3Properties.put("VALUE", endpoint);
                    sparkAdvancedProperties.add(s3Properties);
                }
            }
        }
    }
    //End of tS3Configuration component generation.

    // tHiveConfiguration component generation.
    List<INode> hiveNodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tHiveConfiguration")) {
        hiveNodes.add(pNode);
    }
    if ((hiveNodes.size() > 0) && !useLocalMode && ("MAPR".equals(sparkDistribution))) {
        Map<String, String> hiveProperties = null;
        hiveProperties = new HashMap<String, String>();
        hiveProperties.put("PROPERTY", "\"spark.sql.hive.metastore.sharedPrefixes\"");
        hiveProperties.put("VALUE", "\"com.mysql.jdbc,org.postgresql,com.microsoft.sqlserver,oracle.jdbc,com.mapr.fs.shim.LibraryLoader,com.mapr.security.JNISecurity,com.mapr.fs.jni\"");
        sparkAdvancedProperties.add(hiveProperties);
    }
    //End of tHiveConfiguration component generation.

    // tTachyonConfiguration component generation.
    List<INode> tTachyonNodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tTachyonConfiguration")) {
        tTachyonNodes.add(pNode);
    }

    if(tTachyonNodes.size() > 1) {

    stringBuffer.append(TEXT_2);
    
    }
    if(tTachyonNodes.size() == 1) {
        INode pNode = tTachyonNodes.get(0);
        // the configuration components must not be considered as a root node.
        rootNodes.remove(pNode);

        Map<String, String> tachyonProperties = new HashMap<String, String>();
        tachyonProperties.put("PROPERTY", "\"spark.hadoop.fs.tachyon.impl\"");
        tachyonProperties.put("VALUE", "\"tachyon.hadoop.TFS\"");
        sparkAdvancedProperties.add(tachyonProperties);

        //Set underFS username
        //May conflict with the username set HDFSConfiguration
        //The username is used when the Spark worker is not on the same node as the tachyon worker, in this case the the write goes directly
        // to the UnderFS which requires a authentication (in the case of HDFS).
        username = ElementParameterParser.getValue(pNode, "__UNDERFS_USERNAME__");

    }
    //End of tTachyonConfiguration component generation.

    // tHDFSConfiguration component generation.
    List<INode> hdfsNodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tHDFSConfiguration")) {
        hdfsNodes.add(pNode);
    }

    if(hdfsNodes.size() > 1) {
    
    stringBuffer.append(TEXT_3);
    
    }
    if(hdfsNodes.size() == 1) {
        INode pNode = hdfsNodes.get(0);
        // the configuration components must not be considered as a root node.
        rootNodes.remove(pNode);

        Map<String, String> hdfsProperties = new HashMap<String, String>();
        hdfsProperties.put("PROPERTY", "\"spark.hadoop.fs.defaultFS\"");
        hdfsProperties.put("VALUE", ElementParameterParser.getValue(pNode, "__FS_DEFAULT_NAME__"));
        sparkAdvancedProperties.add(hdfsProperties);

        String hadoopDistribution = ElementParameterParser.getValue(pNode, "__DISTRIBUTION__");
        String hadoopVersion = ElementParameterParser.getValue(pNode, "__DB_VERSION__");

        org.talend.hadoop.distribution.component.HDFSComponent hdfsDistrib = null;
        try {
            hdfsDistrib = (org.talend.hadoop.distribution.component.HDFSComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(hadoopDistribution, hadoopVersion);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "";
        }

        boolean mrUseDatanodeHostname = "true".equals(ElementParameterParser.getValue(pNode, "__USE_DATANODE_HOSTNAME__")) && hdfsDistrib.doSupportUseDatanodeHostname();
        if(mrUseDatanodeHostname) {
            hdfsProperties = new HashMap<String, String>();
            hdfsProperties.put("PROPERTY", "\"spark.hadoop.dfs.client.use.datanode.hostname\"");
            hdfsProperties.put("VALUE", "\"true\"");
            sparkAdvancedProperties.add(hdfsProperties);
        }

        useKrb = "true".equals(ElementParameterParser.getValue(pNode, "__USE_KRB__")) && (hdfsDistrib.doSupportKerberos());
        if(useKrb) {
            hdfsProperties = new HashMap<String, String>();
            hdfsProperties.put("PROPERTY", "\"spark.hadoop.dfs.namenode.kerberos.principal\"");
            hdfsProperties.put("VALUE", ElementParameterParser.getValue(pNode, "__NAMENODE_PRINCIPAL__"));
            sparkAdvancedProperties.add(hdfsProperties);
            hdfsProperties = new HashMap<String, String>();
            hdfsProperties.put("PROPERTY", "\"spark.hadoop.hadoop.security.authorization\"");
            hdfsProperties.put("VALUE", "\"true\"");
            sparkAdvancedProperties.add(hdfsProperties);
            hdfsProperties = new HashMap<String, String>();
            hdfsProperties.put("PROPERTY", "\"spark.hadoop.hadoop.security.authentication\"");
            hdfsProperties.put("VALUE", "\"kerberos\"");
            sparkAdvancedProperties.add(hdfsProperties);
            useKeytab = "true".equals(ElementParameterParser.getValue(pNode, "__USE_KEYTAB__"));
            if(useKeytab) {
                keytabPrincipal = ElementParameterParser.getValue(pNode, "__PRINCIPAL__");
                keytabPath = ElementParameterParser.getValue(pNode, "__KEYTAB_PATH__");
            }
        } else {
            username = ElementParameterParser.getValue(pNode, "__USERNAME__");
        }

        List<Map<String, String>> hadoopProps = (List<Map<String,String>>)ElementParameterParser.getObjectValue(pNode, "__HADOOP_ADVANCED_PROPERTIES__");
        for(Map<String, String> item : hadoopProps){
            hdfsProperties = new HashMap<String, String>();
            hdfsProperties.put("PROPERTY", "\"spark.hadoop.\" + " + item.get("PROPERTY"));
            hdfsProperties.put("VALUE", item.get("VALUE"));
            sparkAdvancedProperties.add(hdfsProperties);
        }
    }
    //End of tHDFSConfiguration component generation.

    // tCassandraConfiguration component generation.
    List<INode> cassandraNodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tCassandraConfiguration")) {
        cassandraNodes.add(pNode);
    }

    if(cassandraNodes.size() > 1) {
    
    stringBuffer.append(TEXT_4);
    
    }
    if(cassandraNodes.size() == 1) {
        INode pNode = cassandraNodes.get(0);
        // the configuration components must not be considered as a root node.
        rootNodes.remove(pNode);
        
    
class CassandraConfiguration_Helper{
	public Map<String, String> getProperties(INode node){
		java.util.Map<String, String> properties = new java.util.HashMap<String, String>();
		
        java.util.List<java.util.Map<String, String>> configurations = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__CASSANDRA_CONFIGURATION__");
        //remove some key from the configuration table, but can remove it from migration task, so ignore them on code generate stage
        java.util.List<String> ignoreConfList = new java.util.ArrayList<String>();
        ignoreConfList.add("connection_rpc_port");//"spark.cassandra.connection.rpc.port"
		ignoreConfList.add("connection_native_port");//"spark.cassandra.connection.native.port"
        java.util.Map<String, String> confMapping = new java.util.HashMap<String, String>();
        confMapping.put("connection_conf_factory","spark.cassandra.connection.conf.factory");
        confMapping.put("connection_keep_alive_ms","spark.cassandra.connection.keep_alive_ms");
        confMapping.put("connection_timeout_ms","spark.cassandra.connection.timeout_ms");
        confMapping.put("reconnection_delay_ms_min","spark.cassandra.connection.reconnection_delay_ms.min");
        confMapping.put("connection_reconnection_delay_ms_max","spark.cassandra.connection.reconnection_delay_ms.max");
        confMapping.put("connection_local_dc","spark.cassandra.connection.local_dc");
        confMapping.put("auth_conf_factory","spark.cassandra.auth.conf.factory");
        confMapping.put("query_retry_count","spark.cassandra.query.retry.count");
        confMapping.put("read_timeout_ms","spark.cassandra.read.timeout_ms");   
        confMapping.put("input_split_size","spark.cassandra.input.split.size");
        confMapping.put("input_page_row_size","spark.cassandra.input.page.row.size");
        confMapping.put("input_consistency_level","spark.cassandra.input.consistency.level");
        for(java.util.Map<String, String> conf : configurations){
            String confKey = conf.get("KEY");
            if(ignoreConfList.contains(confKey)){
            	continue;
            }
            String propertyKey = confMapping.containsKey(confKey) ? "\"" + confMapping.get(confKey) + "\"" : confKey;
            properties.put(propertyKey, conf.get("VALUE"));
        }
        String host = ElementParameterParser.getValue(node,"__HOST__");
        if(!"".equals(host)){
        	properties.put("\"spark.cassandra.connection.host\"", host);
        }
        String port = ElementParameterParser.getValue(node,"__PORT__");
        if(!"".equals(port)){
        	properties.put("\"spark.cassandra.connection.port\"", port);
        }
        boolean authentication="true".equalsIgnoreCase(ElementParameterParser.getValue(node, "__REQUIRED_AUTHENTICATION__"));
        String userName = ElementParameterParser.getValue(node, "__USERNAME__");
        String passWord = ElementParameterParser.getPasswordValue(node, "__PASSWORD__");
        if(authentication){
            properties.put("\"spark.cassandra.auth.username\"", userName);
            properties.put("\"spark.cassandra.auth.password\"", passWord);
        }  
        TSetKeystoreUtil tSetKeystoreUtil = new TSetKeystoreUtil(node);
        if(tSetKeystoreUtil.useHTTPS()){
        	properties.put("\"spark.cassandra.connection.ssl.enabled\"", "\"true\"");
        	properties.put("\"spark.cassandra.connection.ssl.trustStore.type\"", tSetKeystoreUtil.getTrustStoreType());
        	properties.put("\"spark.cassandra.connection.ssl.trustStore.path\"", tSetKeystoreUtil.getTrustStorePath());
        	properties.put("\"spark.cassandra.connection.ssl.trustStore.password\"", tSetKeystoreUtil.getTrustStorePassword());
        	if(tSetKeystoreUtil.needClientAuth()){
        		properties.put("\"spark.cassandra.connection.ssl.client.auth.enabled\"", "\"true\"");
	        	properties.put("\"spark.cassandra.connection.ssl.keyStore.type\"", tSetKeystoreUtil.getKeyStoreType());
	        	properties.put("\"spark.cassandra.connection.ssl.keyStore.path\"", tSetKeystoreUtil.getKeyStorePath());
	        	properties.put("\"spark.cassandra.connection.ssl.keyStore.password\"", tSetKeystoreUtil.getKeyStorePassword());
        	}
        }
        return properties; 
	}
	
	public Map<String, String> getPropertiesForOutput(INode node){
		java.util.Map<String, String> properties = new java.util.HashMap<String, String>();
		
        java.util.List<java.util.Map<String, String>> configurations = (java.util.List<java.util.Map<String, String>>)ElementParameterParser.getObjectValue(node, "__CASSANDRA_CONFIGURATION__");
        java.util.Map<String, String> confMapping = new java.util.HashMap<String, String>();
        confMapping.put("output_batch_size_rows","spark.cassandra.output.batch.size.rows");
        confMapping.put("output_batch_size_bytes","spark.cassandra.output.batch.size.bytes");
        confMapping.put("output_batch_grouping_key","spark.cassandra.output.batch.grouping.key");
        confMapping.put("output_batch_buffer_size","spark.cassandra.output.batch.buffer.size");
        confMapping.put("output_concurrent_writes","spark.cassandra.output.concurrent.writes");
        confMapping.put("output_consistency_level","spark.cassandra.output.consistency.level");
        confMapping.put("output_throughput_mb_per_sec","spark.cassandra.output.throughput_mb_per_sec");
        for(java.util.Map<String, String> conf : configurations){
            String confKey = conf.get("KEY");
            String propertyKey = confMapping.containsKey(confKey) ? "\"" + confMapping.get(confKey) + "\"" : confKey;
            properties.put(propertyKey, conf.get("VALUE"));
        }
        
        return properties; 
	}
}	

    
        Map<String, String> cassandraProperties = (new CassandraConfiguration_Helper()).getProperties(pNode);
        for(String cPropKey : cassandraProperties.keySet()){
            Map<String, String> cProperty = new HashMap<String, String>();
            cProperty.put("PROPERTY", cPropKey);
            cProperty.put("VALUE", cassandraProperties.get(cPropKey));
            sparkAdvancedProperties.add(cProperty);
        }
    }
    //End of tCassandraConfiguration component generation.


    // tKinesisInput component generation.
    List<INode> kinesisNodes = new ArrayList<INode>();
    for (INode pNode : process.getNodesOfType("tKinesisInput")) {
        kinesisNodes.add(pNode);
    }
    for (INode pNode : process.getNodesOfType("tKinesisInputAvro")) {
        kinesisNodes.add(pNode);
    }

    String previousAccessKey = null;
    for (INode pNode: kinesisNodes) {
        String accessKey = ElementParameterParser.getValue(pNode, "__ACCESS_KEY__");
        if (previousAccessKey == null) {
            passwordFieldName = "__SECRET_KEY__";
            INode node = pNode;
            
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_7);
    } else {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_9);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_10);
    }
    
            Map<String, String> cProperty = new HashMap<String, String>();
            cProperty.put("PROPERTY", "\"spark.executor.extraJavaOptions\"");
            cProperty.put("VALUE", "\"-Daws.accessKeyId=\" + " + accessKey + "+ \" -Daws.secretKey=\" + decryptedPassword_" + cid);
            sparkAdvancedProperties.add(cProperty);
        } else {
            if (!previousAccessKey.equals(accessKey)) {
                
    stringBuffer.append(TEXT_11);
    
            }
        }
        previousAccessKey = accessKey;
    }
    //End of tKinesisInput component generation.

    stringBuffer.append(TEXT_12);
    
    if(isThereAtLeastOneMLComponent) {

    
	// Everything related to our TalendPipelineModel used in machine learning jobs 
	// should be written there to avoid code duplication.
	
	// This file is intended to be included in both spark_footer.javajet and sparkstreaming_footer.javajet

    stringBuffer.append(TEXT_13);
    
	// Everything related to our TalendPipeline used in machine learning jobs 
	// should be written there to avoid code duplication.
	
	// This file is intended to be included in both spark_footer.javajet and sparkstreaming_footer.javajet

    stringBuffer.append(TEXT_14);
    
    }

    stringBuffer.append(TEXT_15);
    
	// Kryo registrator and all custom kryo serializers should be written there to avoid code duplication
	
	// This file is intended to be included in both spark_footer.javajet and sparkstreaming_footer.javajet

    stringBuffer.append(TEXT_16);
     
	boolean hasAvroGenericRecord = false;
	for (INode rootNode : rootNodes) {
		if(rootNode.getComponent().getName() != null && (rootNode.getComponent().getName().startsWith("tHMap") || rootNode.getComponent().getName().startsWith("tHConvert"))) {
			hasAvroGenericRecord = true;
			break;
		}
	}
	if(hasAvroGenericRecord) {

    stringBuffer.append(TEXT_17);
    
	} // end if(hasAvroGenericRecord)

    stringBuffer.append(TEXT_18);
     
		if(isThereAtLeastOneMLComponent) {
	
    stringBuffer.append(TEXT_19);
    
		}

		Iterable<String> structNameList = (Iterable<String>) v.get(3);		
		for(String structName : structNameList) {
	
    stringBuffer.append(TEXT_20);
    stringBuffer.append(structName);
    stringBuffer.append(TEXT_21);
    
		} 
	
    stringBuffer.append(TEXT_22);
     
	if(isThereAtLeastOneMLComponent) {

    stringBuffer.append(TEXT_23);
     
	} // end if(isThereAtLeastOneMLComponent)

    stringBuffer.append(TEXT_24);
     if(isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_25);
     } 
    stringBuffer.append(TEXT_26);
    stringBuffer.append(codeGenArgument.getContextName());
    stringBuffer.append(TEXT_27);
     if(subjobCount > 1) { 
    stringBuffer.append(TEXT_28);
     } else { 
    stringBuffer.append(TEXT_29);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_30);
     } 
    stringBuffer.append(TEXT_31);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_32);
    stringBuffer.append(codeGenArgument.getJobName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(codeGenArgument.getJobName());
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    stringBuffer.append(codeGenArgument.getJobName());
    stringBuffer.append(TEXT_36);
    
    if (isTestContainer) {
        List<String> instanceList =  ProcessUtils.getTestInstances(process);
        for(String instance : instanceList) {
            String context = ProcessUtils.getInstanceContext(process,instance);
            
    stringBuffer.append(TEXT_37);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_38);
    
                int assertNum = ProcessUtils.getAssertAmount(process);
                
    stringBuffer.append(TEXT_39);
    stringBuffer.append(assertNum);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_46);
    
                String lineSeparator = (String) java.security.AccessController.doPrivileged(
                        new sun.security.action.GetPropertyAction("line.separator"));
                for(String testDataName : ProcessUtils.getTestData(process,instance)){
                    String testData =  ProcessUtils.getTestDataValue(process, instance, testDataName);
                    if(testData==null||testData.length()<=0){
                        continue;
                    }
                    String testDataEnCodeStr = "";
                    try {
                        if (testData != null) {
                            testDataEnCodeStr = (new sun.misc.BASE64Encoder()).encode(testData.getBytes("UTF-8"));
                        }
                    } catch (java.io.UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }

                    StringBuilder testDataBase64 = new StringBuilder();
                    for(String item : testDataEnCodeStr.split(lineSeparator))
                        testDataBase64.append('"').append(item).append("\"+");
                    // Remove trailing commas.
                    if (testDataBase64.length() > 0)
                        testDataBase64.setLength(testDataBase64.length() - 1);

                    if(testDataBase64 != null && testDataBase64.length() > 0){
                        
    stringBuffer.append(TEXT_47);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(testDataName);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(testDataBase64);
    stringBuffer.append(TEXT_51);
    
                    }
                }
                
    stringBuffer.append(TEXT_52);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(instance);
    stringBuffer.append(TEXT_55);
    stringBuffer.append(TEXT_56);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_57);
    
        }
    } // isTestContainer
    
    stringBuffer.append(TEXT_58);
     if(subjobCount > 1) { 
    stringBuffer.append(TEXT_59);
     } else { 
    stringBuffer.append(TEXT_60);
     } 
    stringBuffer.append(TEXT_61);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_62);
    stringBuffer.append(codeGenArgument.getJobName());
    stringBuffer.append(TEXT_63);
    }
    stringBuffer.append(TEXT_64);
    
        if(stats && !isExecutedThroughSparkJobServer && !isExecutedThroughLivy) {

    stringBuffer.append(TEXT_65);
    
        }

        if(username != null && !"".equals(username)) {

    stringBuffer.append(TEXT_66);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_68);
    
        }
        // this snippet to solve an issue in Spark Driver on MacOSX with snappy
        // you have to run with -Dorg.xerial.snappy.lib.name=libsnappyjava.jnilib

    stringBuffer.append(TEXT_69);
    
        if(sparkConfig!=null) {

    stringBuffer.append(TEXT_70);
    
                if(isExecutedThroughSparkJobServer) {

    stringBuffer.append(TEXT_71);
    
                } else {
                    if(!useCheckpoint) {

     if (isTestContainer) { 
    stringBuffer.append(TEXT_72);
     } else {
                            if(isExecutedThroughLivy) {

    stringBuffer.append(TEXT_73);
    
                    }

                            if(!useLocalMode && sparkUseKrb && (isCustom || sparkStreamingDistrib.doSupportKerberos()) && !isSpark13) {
                        // The following part of code is needed to spawn the thread for the ticket renewal in a secured environment.
                        // Fail silently or log a warning if log4j is activated.

    stringBuffer.append(TEXT_74);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_75);
     } 
    stringBuffer.append(TEXT_76);
    
                            }

    stringBuffer.append(TEXT_77);
    stringBuffer.append(batchSize);
    stringBuffer.append(TEXT_78);
     } 
    stringBuffer.append(TEXT_79);
    
                    if(isExecutedThroughLivy) {

    stringBuffer.append(TEXT_80);
    
                        }
                    } else {

    stringBuffer.append(TEXT_81);
    
                    }
                }

    stringBuffer.append(TEXT_82);
    
                    if(stats && !isExecutedThroughSparkJobServer && !isExecutedThroughLivy) {
                        
    stringBuffer.append(TEXT_83);
    
                    }
                    
    stringBuffer.append(TEXT_84);
    
        }

    stringBuffer.append(TEXT_85);
    
    if(!useCheckpoint) {

    stringBuffer.append(TEXT_86);
    
            if(useKrb) {

                if(((isCustom || (!useLocalMode && sparkStreamingDistrib.doSupportMapRTicket())) && useMapRTicket)) {
		            
    stringBuffer.append(TEXT_87);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_88);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_89);
    
		        }
                if(useKeytab) {

    stringBuffer.append(TEXT_90);
    stringBuffer.append(keytabPrincipal);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(keytabPath);
    stringBuffer.append(TEXT_92);
    
                }
                if(((isCustom || (!useLocalMode && sparkStreamingDistrib.doSupportMapRTicket())) && useMapRTicket)) {
		            
    stringBuffer.append(TEXT_93);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_95);
    
		        }
		    } else {
		        // Mapr ticket
                if(((isCustom || (!useLocalMode && sparkStreamingDistrib.doSupportMapRTicket())) && useMapRTicket)) {
		            passwordFieldName = "__MAPRTICKET_PASSWORD__";
		            
    stringBuffer.append(TEXT_96);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_97);
    
		            if (setMapRHadoopLogin) {
		                
    stringBuffer.append(TEXT_98);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_99);
    
		            } else {
		                
    stringBuffer.append(TEXT_100);
    
		            }
		            INode node = sparkConfig;
		            
    stringBuffer.append(TEXT_101);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_107);
    }
    stringBuffer.append(TEXT_108);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(mapRTicketUsername);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_112);
    
		        }
		    }
            if(stats && !isExecutedThroughSparkJobServer && !isExecutedThroughLivy) {

    stringBuffer.append(TEXT_113);
    
            }


    stringBuffer.append(TEXT_114);
    
            for (INode rootNode : rootNodes) {
                // Retrieve each subtree root linked with an ON_SUBJOB_OK link
                // the ON_SUBJOB_OK is displayed as a "IN PARALLEL" link for the user
                // We want to create the whole spark graph before the ctx.start()
                INode currentSubJobRootNode = rootNode;
                while (currentSubJobRootNode != null) {
                    String componentName = currentSubJobRootNode.getComponent().getName();
                    String uniqueName = currentSubJobRootNode.getUniqueName();
                    if ((currentSubJobRootNode.getOutgoingConnections().size() > 0) || ("tJava".equals(componentName))) {
                        
    stringBuffer.append(TEXT_115);
    stringBuffer.append(currentSubJobRootNode.getUniqueName());
    stringBuffer.append(TEXT_116);
    
                    } // end if
                    if (currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK) != null
                            && currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).size() > 0) {
                        currentSubJobRootNode = currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).get(0).getTarget();
                    } else {
                        currentSubJobRootNode = null;
                    }
                }
            } // end for
    } else { // Use checkpoint

    stringBuffer.append(TEXT_117);
    
            if(useKrb) {
                if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                    
    stringBuffer.append(TEXT_118);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_120);
    
                }
                if(useKeytab) {

    stringBuffer.append(TEXT_121);
    stringBuffer.append(keytabPrincipal);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(keytabPath);
    stringBuffer.append(TEXT_123);
    
                }
                if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                    
    stringBuffer.append(TEXT_124);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_126);
    
                }
            } else {
                // Mapr ticket
                if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                    passwordFieldName = "__MAPRTICKET_PASSWORD__";
                    
    stringBuffer.append(TEXT_127);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_128);
    
                    if (setMapRHadoopLogin) {
                        
    stringBuffer.append(TEXT_129);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_130);
    
                    } else {
                        
    stringBuffer.append(TEXT_131);
    
                    }
                    INode node = sparkConfig;
                    
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_136);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(mapRTicketUsername);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_142);
    
                }
            }

    stringBuffer.append(TEXT_143);
    
                        for (INode rootNode : rootNodes) {
                            // Retrieve each subtree root linked with an ON_SUBJOB_OK link
                            // the ON_SUBJOB_OK is displayed as a "IN PARALLEL" link for the user
                            // We want to create the whole spark graph before the ctx.start()
                            INode currentSubJobRootNode = rootNode;
                            while (currentSubJobRootNode != null) {
                                String componentName = currentSubJobRootNode.getComponent().getName();
                                String uniqueName = currentSubJobRootNode.getUniqueName();
                                if ((currentSubJobRootNode.getOutgoingConnections().size() > 0) || ("tJava".equals(componentName))) {
                                    
    stringBuffer.append(TEXT_144);
    stringBuffer.append(currentSubJobRootNode.getUniqueName());
    stringBuffer.append(TEXT_145);
    
                                } // end if
                                if (currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK) != null
                                        && currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).size() > 0) {
                                    currentSubJobRootNode = currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).get(0).getTarget();
                                } else {
                                    currentSubJobRootNode = null;
                                }
                            }
                        } // end for
                        
    stringBuffer.append(TEXT_146);
    
            if(!useLocalMode && sparkUseKrb && (isCustom || sparkStreamingDistrib.doSupportKerberos()) && !isSpark13) {
                // The following part of code is needed to spawn the thread for the ticket renewal in a secured environment.
                // Fail silently or log a warning if log4j is activated.

    stringBuffer.append(TEXT_147);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_148);
     } 
    stringBuffer.append(TEXT_149);
    
            }

    stringBuffer.append(TEXT_150);
     if (isTestContainer) { 
    stringBuffer.append(TEXT_151);
     } else { 
    stringBuffer.append(TEXT_152);
    stringBuffer.append(checkpointDir);
    stringBuffer.append(TEXT_153);
     } 
    stringBuffer.append(TEXT_154);
    
            if(stats && !isExecutedThroughSparkJobServer) {

    stringBuffer.append(TEXT_155);
    
            }


    stringBuffer.append(TEXT_156);
    
        } // Handle checkpoint ending
        boolean defineDuration = "true".equals(ElementParameterParser.getValue(sparkConfig, "__DEFINE_DURATION__"));
        String duration = ElementParameterParser.getValue(sparkConfig, "__STREAMING_DURATION__");

        if (!isTestContainer) {
        
    stringBuffer.append(TEXT_157);
    stringBuffer.append(defineDuration?duration:"");
    stringBuffer.append(TEXT_158);
    
        } else { // Test Case
        
    stringBuffer.append(TEXT_159);
    stringBuffer.append(batchSize);
    stringBuffer.append(TEXT_160);
    
        }

        // Handle post processing stuff
        for (INode rootNode : rootNodes) {
            // Retrieve each subtree root linked with an ON_SUBJOB_OK link
            // the ON_SUBJOB_OK is displayed as a "IN PARALLEL" link for the user
            // We want to create the whole spark graph before the ctx.start()
            INode currentSubJobRootNode = rootNode;
            while (currentSubJobRootNode != null) {
                String componentName = currentSubJobRootNode.getComponent().getName();
                String uniqueName = currentSubJobRootNode.getUniqueName();
                if ((currentSubJobRootNode.getOutgoingConnections().size() > 0) || ("tJava".equals(componentName))) {
                    
    stringBuffer.append(TEXT_161);
    stringBuffer.append(currentSubJobRootNode.getUniqueName());
    stringBuffer.append(TEXT_162);
    
                } // end if
                if (currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK) != null
                        && currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).size() > 0) {
                    currentSubJobRootNode = currentSubJobRootNode.getOutgoingConnections(EConnectionType.ON_SUBJOB_OK).get(0).getTarget();
                } else {
                    currentSubJobRootNode = null;
                }
            }

            // Validate result of test cases
            if (isTestContainer) {
                
    stringBuffer.append(TEXT_163);
    
            }
        } // end for
        
    stringBuffer.append(TEXT_164);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_165);
     } 
    
        String master = "\"local[*]\"";
        String deployMode = null;

        if(useStandaloneMode) {
            master = ElementParameterParser.getValue(sparkConfig, "__SPARK_HOST__");
        }

        if(useYarnClientMode) {
            master = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0 ? "\"yarn-client\"" : "\"yarn\"";
            deployMode = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0 ? null : "\"client\"";
        }

        if(useYarnClusterMode) {
            master = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0 ? "\"yarn-cluster\"" : "\"yarn\"";
            deployMode = org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0 ? null : "\"cluster\"";
        }

        if(!isExecutedThroughLivy) {

    stringBuffer.append(TEXT_166);
    stringBuffer.append(master);
    stringBuffer.append(TEXT_167);
    
            if (deployMode != null) {
                
    stringBuffer.append(TEXT_168);
    stringBuffer.append(deployMode);
    stringBuffer.append(TEXT_169);
    
            }
            
    stringBuffer.append(TEXT_170);
     if(isTestContainer){ 
    stringBuffer.append(TEXT_171);
    stringBuffer.append(jobFolderName);
    stringBuffer.append(TEXT_172);
     } 
    stringBuffer.append(TEXT_173);
     if(isTestContainer){ 
    stringBuffer.append(TEXT_174);
     } 
    stringBuffer.append(TEXT_175);
    
            // We need to look into the job command line to get the real path of the winutils.exe binary.
            String[] commandLine = new String[] {"<command>"};
            try {
                commandLine = ProcessorUtilities.getCommandLine("win32",true, processId, "",org.talend.designer.runprocess.IProcessor.NO_STATISTICS,org.talend.designer.runprocess.IProcessor.NO_TRACES, new String[]{});
            } catch (ProcessorException e) {
                e.printStackTrace();
            }
            java.util.List<String> jars = null;
            for (int j = 0; j < commandLine.length; j++) {
                if(commandLine[j].contains("jar")) {
                    jars = java.util.Arrays.asList(commandLine[j].split(";"));
                    break;
                }
            }
            if(jars != null) {
                for(int j=0; j<jars.size(); j++) {
                    if(jars.get(j).endsWith(".jar")) {

    stringBuffer.append(TEXT_176);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_SCRATCH_DIR__"));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(jars.get(j).substring(0, jars.get(j).lastIndexOf("/") + 1));
    stringBuffer.append(TEXT_178);
    
                        break;
                    }
                }
            }

    stringBuffer.append(TEXT_179);
    
        }
        if(!useLocalMode && !isExecutedThroughLivy) { // If the spark mode is not local and not executed through livy.
            boolean defineDriverHost = "true".equals(ElementParameterParser.getValue(sparkConfig, "__DEFINE_SPARK_DRIVER_HOST__"));
            if(defineDriverHost) {
                String driverHost = ElementParameterParser.getValue(sparkConfig, "__SPARK_DRIVER_HOST__");
                if(driverHost != null && !"".equals(driverHost)) {

    stringBuffer.append(TEXT_180);
    stringBuffer.append(driverHost);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(driverHost);
    stringBuffer.append(TEXT_182);
    
                }
            }
            if(useStandaloneMode) {
                String sparkHome = ElementParameterParser.getValue(sparkConfig, "__SPARK_HOME__");

    stringBuffer.append(TEXT_183);
    stringBuffer.append(sparkHome);
    stringBuffer.append(TEXT_184);
    
            } else if(useYarnMode) {
                // Set the YARN parameters in the SparkConf
                String resourceManager = ElementParameterParser.getValue(sparkConfig, "__RESOURCE_MANAGER__");
                boolean setResourceManagerSchedulerAddress = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SET_SCHEDULER_ADDRESS__"));
                boolean setJobHistoryAddress = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SET_JOBHISTORY_ADDRESS__"));
                boolean setStagingDirectory = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SET_STAGING_DIRECTORY__"));
                boolean setMemory = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SET_MEMORY__"));
                
    stringBuffer.append(TEXT_185);
    stringBuffer.append(sparkStreamingDistrib.getYarnApplicationClasspath());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(resourceManager);
    stringBuffer.append(TEXT_187);
    if(setResourceManagerSchedulerAddress) { 
    stringBuffer.append(TEXT_188);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__RESOURCEMANAGER_SCHEDULER_ADDRESS__"));
    stringBuffer.append(TEXT_189);
     } 
    if(setJobHistoryAddress) { 
    stringBuffer.append(TEXT_190);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__JOBHISTORY_ADDRESS__"));
    stringBuffer.append(TEXT_191);
     } 
    if(setStagingDirectory) { 
    stringBuffer.append(TEXT_192);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__STAGING_DIRECTORY__"));
    stringBuffer.append(TEXT_193);
     } 
    if(setMemory) { 
    stringBuffer.append(TEXT_194);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__MAPREDUCE_MAP_MEMORY_MB__"));
    stringBuffer.append(TEXT_195);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__MAPREDUCE_REDUCE_MEMORY_MB__"));
    stringBuffer.append(TEXT_196);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__YARN_APP_MAPREDUCE_AM_RESOURCE_MB__"));
    stringBuffer.append(TEXT_197);
     } 
    
                if(sparkUseKrb && (isCustom || sparkStreamingDistrib.doSupportKerberos())) {

    stringBuffer.append(TEXT_198);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__RESOURCEMANAGER_PRINCIPAL__"));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__JOBHISTORY_PRINCIPAL__"));
    stringBuffer.append(TEXT_200);
    
                    if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                        
    stringBuffer.append(TEXT_201);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_202);
    stringBuffer.append(setMapRHadoopLogin ? mapRHadoopLogin : "\"kerberos\"");
    stringBuffer.append(TEXT_203);
    
                    }
                    boolean sparkUseKeytab = "true".equals(ElementParameterParser.getValue(sparkConfig, "__USE_KEYTAB__"));
                    if(sparkUseKeytab) {

    stringBuffer.append(TEXT_204);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__PRINCIPAL__"));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__KEYTAB_PATH__"));
    stringBuffer.append(TEXT_206);
    
                    }
                    if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                        
    stringBuffer.append(TEXT_207);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_209);
    
                    }
                } else {
                    // Mapr ticket
                    if(((isCustom || sparkStreamingDistrib.doSupportMapRTicket()) && useMapRTicket)) {
                        passwordFieldName = "__MAPRTICKET_PASSWORD__";
                        
    stringBuffer.append(TEXT_210);
    stringBuffer.append(setMapRHomeDir ? mapRHomeDir : "\"/opt/mapr\"" );
    stringBuffer.append(TEXT_211);
    
                        if (setMapRHadoopLogin) {
                            
    stringBuffer.append(TEXT_212);
    stringBuffer.append(mapRHadoopLogin);
    stringBuffer.append(TEXT_213);
    
                        } else {
                            
    stringBuffer.append(TEXT_214);
    
                        }
                        INode node = sparkConfig;
                        
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_217);
    } else {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_219);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_220);
    }
    stringBuffer.append(TEXT_221);
    stringBuffer.append(mapRTicketCluster);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(mapRTicketUsername);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_224);
    stringBuffer.append(mapRTicketDuration);
    stringBuffer.append(TEXT_225);
    
                    }
                    String sparkUsername = ElementParameterParser.getValue(sparkConfig, "__USERNAME__");
                    if(sparkUsername != null) {
                        if(username != null) {

    stringBuffer.append(TEXT_226);
    stringBuffer.append(sparkUsername);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(username);
    stringBuffer.append(TEXT_228);
    
                        }
                        if(!"".equals(sparkUsername)) {

    stringBuffer.append(TEXT_229);
    stringBuffer.append(sparkUsername);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(sparkUsername);
    stringBuffer.append(TEXT_231);
    
                        }
                    }
                }
            }
        } // End of: If the spark mode is not local.

        boolean defineHadoopHomeDir = "true".equals(ElementParameterParser.getValue(sparkConfig, "__DEFINE_HADOOP_HOME_DIR__"));
        if(defineHadoopHomeDir && !isExecutedThroughLivy) {
        
    stringBuffer.append(TEXT_232);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__HADOOP_HOME_DIR__"));
    stringBuffer.append(TEXT_233);
    
        }

		// tKafkaInput and tKafkaInputAvro component generation.
		List<INode> kafkaNodes = new ArrayList<INode>();
		for (INode pNode : process.getNodesOfType("tKafkaInput")) {
			kafkaNodes.add(pNode);
		}
		for (INode pNode : process.getNodesOfType("tKafkaInputAvro")) {
			kafkaNodes.add(pNode);
		}
		if(!kafkaNodes.isEmpty()) {
			String maxRatePerPartition = null;
				for(INode kafkaNode : kafkaNodes) {
					if("true".equals(ElementParameterParser.getValue(kafkaNode, "__KAFKA_MAX_RATE_PER_PARTITION_CHECK__"))) {
						String currentMaxRatePerPartition = ElementParameterParser.getValue(kafkaNode, "__KAFKA_MAX_RATE_PER_PARTITION__");
						if(maxRatePerPartition == null || "".equals(maxRatePerPartition)){
							maxRatePerPartition = currentMaxRatePerPartition;
						}
						if(maxRatePerPartition != null && !maxRatePerPartition.equals(currentMaxRatePerPartition)) {

    stringBuffer.append(TEXT_234);
    
						}
					}
				}
			if(maxRatePerPartition != null && !"".equals(maxRatePerPartition)) {
				Map<String, String> property = new HashMap<String, String>();
				property.put("PROPERTY", "\"spark.streaming.kafka.maxRatePerPartition\"");
				property.put("VALUE", maxRatePerPartition);
				sparkAdvancedProperties.add(property);
			}
		}
		//End of tKafkaInput and tKafkaInputAvro component generation.

        if(tuningProperties) {
            // Set Web UI port
            if(setWebuiPort) {

    stringBuffer.append(TEXT_235);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__WEB_UI_PORT__"));
    stringBuffer.append(TEXT_236);
    
            }

    stringBuffer.append(TEXT_237);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_EXECUTOR_MEM__"));
    stringBuffer.append(TEXT_238);
    
            // executor memory overhead is a YARN only parameter
            boolean setExecutorMemoryOverhead = "true".equals(ElementParameterParser.getValue(sparkConfig, "__SET_SPARK_EXECUTOR_MEM_OVERHEAD__"));
            if(setExecutorMemoryOverhead && (useYarnClientMode || useYarnClusterMode)) {

    stringBuffer.append(TEXT_239);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_EXECUTOR_MEM_OVERHEAD__"));
    stringBuffer.append(TEXT_240);
    
            }

            if(useStandaloneMode || useYarnClusterMode) {

    stringBuffer.append(TEXT_241);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_DRIVER_CORES__"));
    stringBuffer.append(TEXT_242);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_DRIVER_MEM__"));
    stringBuffer.append(TEXT_243);
    
            }
            if(setExecutorCores && !useLocalMode) {

    stringBuffer.append(TEXT_244);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_EXECUTOR_CORES__"));
    stringBuffer.append(TEXT_245);
    
            }

            if(useYarnMode) {
                String allocationMode = ElementParameterParser.getValue(sparkConfig, "__SPARK_YARN_ALLOC_TYPE__");
                // we do nothing in AUTO mode
                if("FIXED".equalsIgnoreCase(allocationMode)) {

    stringBuffer.append(TEXT_246);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_EXECUTOR_INSTANCES__"));
    stringBuffer.append(TEXT_247);
    
                } else if("DYNAMIC".equalsIgnoreCase(allocationMode) && (isCustom || sparkStreamingDistrib.doSupportDynamicMemoryAllocation())) {

    stringBuffer.append(TEXT_248);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_YARN_DYN_INIT__"));
    stringBuffer.append(TEXT_249);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_YARN_DYN_MIN__"));
    stringBuffer.append(TEXT_250);
    
                    String dynMaxValue = ElementParameterParser.getValue(sparkConfig, "__SPARK_YARN_DYN_MAX__");
                    if(dynMaxValue.contains("MAX")) {

    stringBuffer.append(TEXT_251);
    
                    }
                    else {

    stringBuffer.append(TEXT_252);
    stringBuffer.append(dynMaxValue);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(dynMaxValue);
    stringBuffer.append(TEXT_254);
    
                    }

    stringBuffer.append(TEXT_255);
    
                }
            }

            // The broadcast factory is unused in Spark 2.
            if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0) {
                String broadcastFactory = ElementParameterParser.getValue(sparkConfig, "__SPARK_BROADCAST_FACTORY__");
                // we do nothing in auto mode
                if("TORRENT".equalsIgnoreCase(broadcastFactory)) {

    stringBuffer.append(TEXT_256);
    
                } else if ("HTTP".equalsIgnoreCase(broadcastFactory)) {

    stringBuffer.append(TEXT_257);
    
                }
            }

            boolean customizeSparkSerializer = "true".equals(ElementParameterParser.getValue(sparkConfig, "__CUSTOMIZE_SPARK_SERIALIZER__"));
            if(customizeSparkSerializer) {

    stringBuffer.append(TEXT_258);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_SERIALIZER__"));
    stringBuffer.append(TEXT_259);
    
            }
				boolean backpressureEnabled = "true".equals(ElementParameterParser.getValue(sparkConfig, "__ENABLE_BACKPRESSURE__"));
            if(backpressureEnabled) {

    stringBuffer.append(TEXT_260);
    
            }

        } // end set of tuning properties

        // SPARK LOGGING / HISTORY parameters
        // These parameters are valied for YARN and mesos
        boolean enableSparkEventLogging = "true".equals(ElementParameterParser.getValue(sparkConfig, "__ENABLE_SPARK_EVENT_LOGGING__"));
        if(enableSparkEventLogging && (useYarnClientMode || useYarnClusterMode)){

    stringBuffer.append(TEXT_261);
    
            boolean compressSparkEventLogs = "true".equals(ElementParameterParser.getValue(sparkConfig, "__COMPRESS_SPARK_EVENT_LOGS__"));
            if(compressSparkEventLogs){

    stringBuffer.append(TEXT_262);
    
            }

    stringBuffer.append(TEXT_263);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_EVENT_LOG_DIR__"));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARKHISTORY_ADDRESS__"));
    stringBuffer.append(TEXT_265);
    
        }

        // Advanced properties
        for(Map<String, String> property : sparkAdvancedProperties){

    stringBuffer.append(TEXT_266);
    stringBuffer.append(property.get("PROPERTY"));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(property.get("VALUE"));
    stringBuffer.append(TEXT_268);
    
        }

    stringBuffer.append(TEXT_269);
     if(!isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_270);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__SPARK_SCRATCH_DIR__"));
    stringBuffer.append(TEXT_271);
     } 
    stringBuffer.append(TEXT_272);
     if (isTestContainer) { 
    stringBuffer.append(TEXT_273);
    stringBuffer.append(batchSize);
    stringBuffer.append(TEXT_274);
     } 
    stringBuffer.append(TEXT_275);
    



    // This part of code is generated only if the used distribution execute Spark jobs through the Spark Job Server. ONly used by HD Insight currently.
    if(isExecutedThroughSparkJobServer) {

    stringBuffer.append(TEXT_276);
    
            passwordFieldName = "__HDINSIGHT_PASSWORD__";
            if (ElementParameterParser.canEncrypt(sparkConfig, passwordFieldName)) {

    stringBuffer.append(TEXT_277);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_278);
    
            } else {

    stringBuffer.append(TEXT_279);
    stringBuffer.append( ElementParameterParser.getValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_280);
    
            }

    stringBuffer.append(TEXT_281);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__HDINSIGHT_ENDPOINT__"));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__HDINSIGHT_USERNAME__"));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(codeGenArgument.getCurrentProjectName().toLowerCase());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(JavaResourcesHelper.getJobFolderName(className, process.getVersion()));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_286);
     if (isTestContainer) { 
    stringBuffer.append(TEXT_287);
     } else { 
    stringBuffer.append(TEXT_288);
     } 
    stringBuffer.append(TEXT_289);
    
    }

    if(isExecutedThroughLivy) {

    stringBuffer.append(TEXT_290);
    
            passwordFieldName = "__HDINSIGHT_PASSWORD__";
            if (ElementParameterParser.canEncrypt(sparkConfig, passwordFieldName)) {

    stringBuffer.append(TEXT_291);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_292);
    
            } else {

    stringBuffer.append(TEXT_293);
    stringBuffer.append( ElementParameterParser.getValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_294);
    
            }

            passwordFieldName = "__WASB_PASSWORD__";
            if (ElementParameterParser.canEncrypt(sparkConfig, passwordFieldName)) {

    stringBuffer.append(TEXT_295);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_296);
    
            } else {

    stringBuffer.append(TEXT_297);
    stringBuffer.append( ElementParameterParser.getValue(sparkConfig, passwordFieldName));
    stringBuffer.append(TEXT_298);
    
            }

    stringBuffer.append(TEXT_299);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__WASB_USERNAME__"));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__WASB_CONTAINER__"));
    stringBuffer.append(TEXT_301);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__HDINSIGHT_USERNAME__"));
    stringBuffer.append(TEXT_302);
    stringBuffer.append(codeGenArgument.getCurrentProjectName().toLowerCase());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(JavaResourcesHelper.getJobFolderName(className, process.getVersion()));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__LIVY_HOST__"));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__LIVY_PORT__"));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__REMOTE_FOLDER__"));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(ElementParameterParser.getValue(sparkConfig, "__LIVY_USERNAME__"));
    stringBuffer.append(TEXT_309);
    
                if(tuningProperties) {

    stringBuffer.append(TEXT_310);
    
                    if(setExecutorCores && !useLocalMode) {

    stringBuffer.append(TEXT_311);
    
                    }

    stringBuffer.append(TEXT_312);
    
                }

    stringBuffer.append(TEXT_313);
    
    }

    stringBuffer.append(TEXT_314);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(jobClassPackage);
    stringBuffer.append(TEXT_316);
    
                for(IContextParameter ctxParam :params){
                    String typeToGenerate = "String";
                    if(ctxParam.getType().equals("id_List Of Value") || ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory")){
                        typeToGenerate = "String";
                    }else{
                        typeToGenerate = JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true);
                    }
                    
    stringBuffer.append(TEXT_317);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_320);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_321);
    
                }
                
    stringBuffer.append(TEXT_322);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_323);
    stringBuffer.append(jobClassPackage);
    stringBuffer.append(TEXT_324);
    
                if(!useLocalMode) {

    stringBuffer.append(TEXT_325);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(jobClassPackage);
    stringBuffer.append(TEXT_327);
    
                }

    stringBuffer.append(TEXT_328);
    
            for(IContextParameter ctxParam : params){
            
    stringBuffer.append(TEXT_329);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_333);
    
            }
            
    stringBuffer.append(TEXT_334);
    
    if(isExecutedThroughLivy) {

    stringBuffer.append(TEXT_335);
    
    }

    stringBuffer.append(TEXT_336);
     if(isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_337);
     } 
    stringBuffer.append(TEXT_338);
     if(isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_339);
     } 
    stringBuffer.append(TEXT_340);
     if(isExecutedThroughLivy || isExecutedThroughSparkJobServer) { 
     if(isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_341);
     } 
    stringBuffer.append(TEXT_342);
    if(isLog4jEnabled) {
    stringBuffer.append(TEXT_343);
    }
    stringBuffer.append(TEXT_344);
     if(isExecutedThroughLivy) { 
    stringBuffer.append(TEXT_345);
     } 
     } 
    stringBuffer.append(TEXT_346);
    
    if (isTestContainer) {
        
    
// I am so sorry to put this java class on javajet, but we need to have JUNIT and Spark dependency according to the current distribution.
// Also we cannot include an external package only on Test Cases.


    stringBuffer.append(TEXT_347);
    
    }
    
    stringBuffer.append(TEXT_348);
    stringBuffer.append(TEXT_349);
    return stringBuffer.toString();
  }
}
