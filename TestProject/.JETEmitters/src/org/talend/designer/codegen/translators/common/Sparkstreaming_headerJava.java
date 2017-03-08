package org.talend.designer.codegen.translators.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.talend.core.GlobalServiceRegister;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IContextParameter;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.ProcessUtils;
import org.talend.core.model.utils.JavaResourcesHelper;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.ui.branding.AbstractBrandingService;
import org.talend.core.ui.branding.IBrandingService;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.designer.codegen.i18n.Messages;
import org.talend.designer.codegen.ITalendSynchronizer;
import org.talend.designer.runprocess.CodeGeneratorRoutine;

public class Sparkstreaming_headerJava
{
  protected static String nl;
  public static synchronized Sparkstreaming_headerJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Sparkstreaming_headerJava result = new Sparkstreaming_headerJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "package ";
  protected final String TEXT_3 = ";" + NL + "" + NL + "import routines.system.*;" + NL + "import routines.system.SparkStreamingRunStat.*;" + NL + "import routines.system.api.*;";
  protected final String TEXT_4 = NL + "import routines.";
  protected final String TEXT_5 = ";";
  protected final String TEXT_6 = NL + NL + NL + "import java.io.IOException;" + NL + "import java.io.Serializable;" + NL + "import java.util.ArrayList;" + NL + "import java.util.List;" + NL + "import java.util.Map;" + NL + "import java.math.BigDecimal;" + NL + "import java.io.ObjectInputStream;" + NL + "" + NL + "import org.apache.hadoop.conf.Configuration;" + NL + "import org.apache.hadoop.conf.Configured;" + NL + "import org.apache.hadoop.fs.FileSystem;" + NL + "import org.apache.hadoop.fs.FSDataInputStream;" + NL + "import org.apache.hadoop.fs.FSDataOutputStream;" + NL + "import org.apache.hadoop.fs.Path;" + NL + "import org.apache.hadoop.io.compress.CompressionCodec;" + NL + "import org.apache.hadoop.io.compress.CompressionCodecFactory;" + NL + "import org.apache.hadoop.io.compress.GzipCodec;" + NL + "import org.apache.hadoop.io.LongWritable;" + NL + "import org.apache.hadoop.io.NullWritable;" + NL + "import org.apache.hadoop.io.Text;" + NL + "import org.apache.hadoop.io.Writable;" + NL + "import org.apache.hadoop.io.WritableComparable;" + NL + "import org.apache.hadoop.io.WritableComparator;" + NL + "import org.apache.hadoop.io.WritableUtils;" + NL + "import org.apache.hadoop.io.SequenceFile;" + NL + "import org.apache.hadoop.mapred.FileAlreadyExistsException;" + NL + "import org.apache.hadoop.mapred.FileInputFormat;" + NL + "import org.apache.hadoop.mapred.SequenceFileInputFormat;" + NL + "import org.apache.hadoop.mapred.FileOutputFormat;" + NL + "import org.apache.hadoop.mapred.FileSplit;" + NL + "import org.apache.hadoop.mapred.InputSplit;" + NL + "import org.apache.hadoop.mapred.InvalidJobConfException;" + NL + "import org.apache.hadoop.mapred.JobClient;" + NL + "import org.apache.hadoop.mapred.JobConf;" + NL + "import org.apache.hadoop.mapred.JobConfigurable;" + NL + "import org.apache.hadoop.mapred.Mapper;" + NL + "import org.apache.hadoop.mapred.MapReduceBase;" + NL + "import org.apache.hadoop.mapred.OutputCollector;" + NL + "import org.apache.hadoop.mapred.RecordReader;" + NL + "import org.apache.hadoop.mapred.RecordWriter;" + NL + "import org.apache.hadoop.mapred.Reducer;" + NL + "import org.apache.hadoop.mapred.Reporter;" + NL + "import org.apache.hadoop.mapred.InputFormat;" + NL + "import org.apache.hadoop.mapred.OutputFormat;" + NL + "import org.apache.hadoop.security.UserGroupInformation;" + NL + "import org.apache.hadoop.util.Tool;" + NL + "import org.apache.hadoop.util.ToolRunner;" + NL + "import org.apache.hadoop.util.Progressable;" + NL + "import org.apache.hadoop.util.ReflectionUtils;" + NL + "import org.apache.commons.lang.SerializationUtils;" + NL + "import org.apache.commons.codec.binary.Base64;" + NL + "import org.apache.commons.codec.binary.StringUtils;" + NL + "import java.io.DataInput;" + NL + "import java.io.DataOutput;" + NL + "import java.io.DataOutputStream;" + NL + "import java.util.Date;" + NL;
  protected final String TEXT_7 = NL + "    import org.apache.spark.mllib.linalg.Vector;";
  protected final String TEXT_8 = NL + "    import org.apache.spark.ml.linalg.Vector;";
  protected final String TEXT_9 = NL + "\t\t\t\timport org.junit.Test;" + NL + "\t\t\t\timport static org.junit.Assert.assertTrue;" + NL + "\t\t\t\t";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL + NL;
  protected final String TEXT_13 = NL + "    //the import part of ";
  protected final String TEXT_14 = NL + "    ";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + NL + "@SuppressWarnings(\"unused\")" + NL + "" + NL + "/**" + NL + " * Job: ";
  protected final String TEXT_17 = " Purpose: ";
  protected final String TEXT_18 = "<br>" + NL + " * Description: ";
  protected final String TEXT_19 = " <br>" + NL + " * Spark: ";
  protected final String TEXT_20 = " <br>" + NL + " * @author ";
  protected final String TEXT_21 = NL + " * @version ";
  protected final String TEXT_22 = NL + " * @status ";
  protected final String TEXT_23 = NL + " */" + NL + "public class ";
  protected final String TEXT_24 = " implements TalendJob {" + NL + "    static {" + NL + "        System.setProperty(\"TalendJob.log\", \"";
  protected final String TEXT_25 = ".log\");" + NL + "    }" + NL + "    private final static String utf8Charset = \"UTF-8\";" + NL + "    private GlobalVar globalMap = null;" + NL;
  protected final String TEXT_26 = NL + "        @org.junit.Rule" + NL + "        private TalendManualClockResource manualClock;" + NL + "" + NL + "\t\t\t\t    private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();";
  protected final String TEXT_27 = NL;
  protected final String TEXT_28 = NL + "        private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger" + NL + "            .getLogger(";
  protected final String TEXT_29 = ".class);" + NL + "        // DI compatibility" + NL + "        private static org.apache.log4j.Logger log = LOG;";
  protected final String TEXT_30 = NL + NL + "    private static class GlobalVar {" + NL + "        public static final String GLOBALVAR_PARAMS_PREFIX = \"talend.globalvar.params.\";" + NL + "        private Configuration job;" + NL + "        private java.util.Map<String, Object> map;" + NL + "" + NL + "        public GlobalVar(Configuration job) {" + NL + "            this.job = job;" + NL + "            this.map = new java.util.HashMap<String, Object>();" + NL + "        }" + NL + "" + NL + "        public Object get(String key) {" + NL + "            String tempValue = job.get(GLOBALVAR_PARAMS_PREFIX + key);" + NL + "            if (tempValue != null) {" + NL + "                return SerializationUtils.deserialize(Base64" + NL + "                        .decodeBase64(StringUtils.getBytesUtf8(tempValue)));" + NL + "            } else {" + NL + "                return null;" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public void put(String key, Object value) {" + NL + "            if (value == null)" + NL + "                return;" + NL + "            job.set(GLOBALVAR_PARAMS_PREFIX + key, StringUtils" + NL + "                    .newStringUtf8(Base64.encodeBase64(SerializationUtils" + NL + "                            .serialize((Serializable) value))));" + NL + "        }" + NL + "" + NL + "        public void putLocal(String key, Object value) {" + NL + "            map.put(key, value);" + NL + "        }" + NL + "" + NL + "        public Object getLocal(String key) {" + NL + "            return map.get(key);" + NL + "        }" + NL + "    }" + NL + "" + NL + "    // create and load default properties" + NL + "    private java.util.Properties defaultProps = new java.util.Properties();" + NL + "" + NL + "    public static class ContextProperties extends java.util.Properties {" + NL + "" + NL + "        private static final long serialVersionUID = 1L;" + NL + "" + NL + "        public static final String CONTEXT_FILE_NAME = \"talend.context.fileName\";" + NL + "        public static final String CONTEXT_KEYS = \"talend.context.keys\";" + NL + "        public static final String CONTEXT_PARAMS_PREFIX = \"talend.context.params.\";" + NL + "        public static final String CONTEXT_PARENT_KEYS = \"talend.context.parent.keys\";" + NL + "        public static final String CONTEXT_PARENT_PARAMS_PREFIX = \"talend.context.parent.params.\";" + NL + "" + NL + "        public ContextProperties(java.util.Properties properties){" + NL + "            super(properties);" + NL + "        }" + NL + "" + NL + "        public ContextProperties(){" + NL + "            super();" + NL + "        }" + NL + "" + NL + "        public ContextProperties(Configuration job){" + NL + "            super();" + NL + "            String contextFileName = (String) job.get(CONTEXT_FILE_NAME);" + NL + "            try {" + NL + "                if(contextFileName != null && !\"\".equals(contextFileName)){" + NL + "                    java.io.File contextFile = new java.io.File(contextFileName);" + NL + "                    if (contextFile.exists()) {" + NL + "                        java.io.InputStream contextIn = contextFile.toURI().toURL().openStream();" + NL + "                        this.load(contextIn);" + NL + "                        contextIn.close();" + NL + "                    } else {" + NL + "                        java.io.InputStream contextIn = ";
  protected final String TEXT_31 = ".class.getClassLoader().getResourceAsStream(\"";
  protected final String TEXT_32 = "/";
  protected final String TEXT_33 = "/contexts/\"+contextFileName);" + NL + "                        if(contextIn != null){" + NL + "                            this.load(contextIn);" + NL + "                            contextIn.close();" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "                Object contextKeys = job.get(CONTEXT_KEYS);" + NL + "                if (contextKeys != null) {" + NL + "                    java.util.StringTokenizer st = new java.util.StringTokenizer(contextKeys.toString(), \" \");" + NL + "                    while (st.hasMoreTokens()) {" + NL + "                        String contextKey = st.nextToken();" + NL + "                        if((String) job.get(CONTEXT_PARAMS_PREFIX + contextKey) != null){" + NL + "                            this.put(contextKey, job.get(CONTEXT_PARAMS_PREFIX + contextKey));" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "                Object contextParentKeys = job.get(CONTEXT_PARENT_KEYS);" + NL + "                if (contextParentKeys != null) {" + NL + "                    java.util.StringTokenizer st = new java.util.StringTokenizer(contextParentKeys.toString(), \" \");" + NL + "                    while (st.hasMoreTokens()) {" + NL + "                        String contextKey = st.nextToken();" + NL + "                        if((String)job.get(CONTEXT_PARENT_PARAMS_PREFIX + contextKey) != null){" + NL + "                            this.put(contextKey, job.get(CONTEXT_PARENT_PARAMS_PREFIX + contextKey));" + NL + "                        }" + NL + "                    }" + NL + "                }" + NL + "" + NL + "                this.loadValue(null,job);" + NL + "            } catch (java.io.IOException ie) {" + NL + "                System.err.println(\"Could not load context \" + contextFileName);" + NL + "                ie.printStackTrace();" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public void synchronizeContext(){";
  protected final String TEXT_34 = NL + "                if(";
  protected final String TEXT_35 = " != null){";
  protected final String TEXT_36 = NL + "                        String pattern_";
  protected final String TEXT_37 = " = \"yyyy-MM-dd HH:mm:ss\";" + NL + "                        String value_";
  protected final String TEXT_38 = " = \"";
  protected final String TEXT_39 = "\";" + NL + "                        String[] parts_";
  protected final String TEXT_40 = " = value_";
  protected final String TEXT_41 = ".split(\";\");" + NL + "                        if(parts_";
  protected final String TEXT_42 = ".length > 1){" + NL + "                            pattern_";
  protected final String TEXT_43 = " = parts_";
  protected final String TEXT_44 = "[0];" + NL + "                            this.setProperty(\"";
  protected final String TEXT_45 = "\", pattern_";
  protected final String TEXT_46 = " + \";\" + FormatterUtils.format_DateInUTC(";
  protected final String TEXT_47 = ", pattern_";
  protected final String TEXT_48 = "));" + NL + "                        }else{" + NL + "                            this.setProperty(\"";
  protected final String TEXT_49 = "\", FormatterUtils.format_DateInUTC(";
  protected final String TEXT_50 = ", pattern_";
  protected final String TEXT_51 = "));" + NL + "                        }";
  protected final String TEXT_52 = NL + "                        this.setProperty(\"";
  protected final String TEXT_53 = "\", ";
  protected final String TEXT_54 = ".toString());";
  protected final String TEXT_55 = NL + "                }";
  protected final String TEXT_56 = NL + "        }" + NL;
  protected final String TEXT_57 = NL + "                public String ";
  protected final String TEXT_58 = ";" + NL + "                public String get";
  protected final String TEXT_59 = "(){" + NL + "                    return this.";
  protected final String TEXT_60 = ";" + NL + "                }";
  protected final String TEXT_61 = NL + "                public ";
  protected final String TEXT_62 = " ";
  protected final String TEXT_63 = ";" + NL + "                public ";
  protected final String TEXT_64 = " get";
  protected final String TEXT_65 = "(){" + NL + "                    return this.";
  protected final String TEXT_66 = ";" + NL + "                }";
  protected final String TEXT_67 = NL + "        public void loadValue(java.util.Properties context_param, Configuration job){";
  protected final String TEXT_68 = NL + "                    String pwd_";
  protected final String TEXT_69 = "_value = this.getProperty(\"";
  protected final String TEXT_70 = "\");" + NL + "                    this.";
  protected final String TEXT_71 = " = null;" + NL + "                    if(pwd_";
  protected final String TEXT_72 = "_value!=null) {" + NL + "                        //no need to decrypt if it come from program argument or parent job runtime" + NL + "                        if((context_param!=null && context_param.containsKey(\"";
  protected final String TEXT_73 = "\"))" + NL + "                         || (job!=null && job.get(CONTEXT_PARAMS_PREFIX + \"";
  protected final String TEXT_74 = "\") != null)" + NL + "                         || (job!=null && job.get(CONTEXT_PARENT_PARAMS_PREFIX + \"";
  protected final String TEXT_75 = "\") != null)){" + NL + "                            this.";
  protected final String TEXT_76 = " = pwd_";
  protected final String TEXT_77 = "_value;" + NL + "                        } else if (!pwd_";
  protected final String TEXT_78 = "_value.isEmpty()) {" + NL + "                            try {" + NL + "                                this.";
  protected final String TEXT_79 = " = routines.system.PasswordEncryptUtil.decryptPassword(pwd_";
  protected final String TEXT_80 = "_value);" + NL + "                            } catch (java.lang.RuntimeException e) {" + NL + "                                //do nothing" + NL + "                            }" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_81 = NL + "                    try{" + NL + "                        String context_";
  protected final String TEXT_82 = "_value = this.getProperty(\"";
  protected final String TEXT_83 = "\");" + NL + "                        if (context_";
  protected final String TEXT_84 = "_value == null){" + NL + "                            context_";
  protected final String TEXT_85 = "_value = \"\";" + NL + "                        }" + NL + "                        int context_";
  protected final String TEXT_86 = "_pos = context_";
  protected final String TEXT_87 = "_value.indexOf(\";\");" + NL + "                        String context_";
  protected final String TEXT_88 = "_pattern =  \"yyyy-MM-dd HH:mm:ss\";" + NL + "                        if(context_";
  protected final String TEXT_89 = "_pos > -1){" + NL + "                            context_";
  protected final String TEXT_90 = "_pattern = context_";
  protected final String TEXT_91 = "_value.substring(0, context_";
  protected final String TEXT_92 = "_pos);" + NL + "                            context_";
  protected final String TEXT_93 = "_value = context_";
  protected final String TEXT_94 = "_value.substring(context_";
  protected final String TEXT_95 = "_pos + 1);" + NL + "                        }" + NL + "" + NL + "                        this.";
  protected final String TEXT_96 = "=(java.util.Date)(new java.text.SimpleDateFormat(context_";
  protected final String TEXT_97 = "_pattern).parse(context_";
  protected final String TEXT_98 = "_value));" + NL + "" + NL + "                    }catch(java.text.ParseException e){" + NL + "                        this.";
  protected final String TEXT_99 = "=null;" + NL + "                    }";
  protected final String TEXT_100 = NL + "                    this.";
  protected final String TEXT_101 = "=(";
  protected final String TEXT_102 = ") this.getProperty(\"";
  protected final String TEXT_103 = "\");";
  protected final String TEXT_104 = NL + "                     this.";
  protected final String TEXT_105 = "= new java.text.StringCharacterIterator(this.getProperty(\"";
  protected final String TEXT_106 = "\")).first();";
  protected final String TEXT_107 = NL + "                     try{" + NL + "                         this.";
  protected final String TEXT_108 = "=routines.system.BigDataParserUtils.parseTo_";
  protected final String TEXT_109 = "(this.getProperty(\"";
  protected final String TEXT_110 = "\"));" + NL + "                     }catch(NumberFormatException e){" + NL + "                         this.";
  protected final String TEXT_111 = "=null;" + NL + "                      }";
  protected final String TEXT_112 = NL + "        }" + NL + "    }" + NL + "    private ContextProperties context = new ContextProperties();" + NL + "    public ContextProperties getContext() {" + NL + "        return this.context;" + NL + "    }" + NL;
  protected final String TEXT_113 = NL + "        public static class TalendSparkStreamingListener extends" + NL + "                org.apache.spark.streaming.ui.StreamingJobProgressListener {" + NL + "" + NL + "            private int batchCompleted = 0;" + NL + "            private int batchStarted = 0;" + NL + "            private String lastProcessingDelay = \"\";" + NL + "            private String lastSchedulingDelay = \"\";" + NL + "            private String lastTotalDelay = \"\";" + NL + "" + NL + "" + NL + "            public TalendSparkStreamingListener(org.apache.spark.streaming.StreamingContext ssc) {" + NL + "                super(ssc);" + NL + "                StatBean sb = runStat.createSparkStreamingStatBean();" + NL + "                sb.setSubjobId(\"1\");" + NL + "                sb.setBatchCompleted(this.batchCompleted);" + NL + "                sb.setBatchStarted(this.batchStarted);" + NL + "                sb.setLastProcessingDelay(this.lastProcessingDelay);" + NL + "                sb.setLastSchedulingDelay(this.lastSchedulingDelay);" + NL + "                sb.setLastTotalDelay(this.lastTotalDelay);" + NL + "                runStat.updateSparkStreamingData(sb);" + NL + "            }" + NL + "" + NL + "            @Override" + NL + "            public void onBatchStarted(org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted batchStarted) {" + NL + "                super.onBatchStarted(batchStarted);" + NL + "                this.batchStarted = this.batchStarted + 1;" + NL + "                StatBean sb = runStat.createSparkStreamingStatBean();" + NL + "                sb.setSubjobId(\"1\");" + NL + "                sb.setBatchCompleted(this.batchCompleted);" + NL + "                sb.setBatchStarted(this.batchStarted);" + NL + "                sb.setLastProcessingDelay(this.lastProcessingDelay);" + NL + "                sb.setLastSchedulingDelay(this.lastSchedulingDelay);" + NL + "                sb.setLastTotalDelay(this.lastTotalDelay);" + NL + "                runStat.updateSparkStreamingData(sb);" + NL + "            }" + NL + "" + NL + "            @Override" + NL + "            public void onBatchCompleted(org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted batchCompleted) {" + NL + "                super.onBatchCompleted(batchCompleted);";
  protected final String TEXT_114 = NL + "                        scala.Option<org.apache.spark.streaming.ui.BatchUIData> lastCompletedBatch = this.lastCompletedBatch();";
  protected final String TEXT_115 = NL + "                        scala.Option<org.apache.spark.streaming.scheduler.BatchInfo> lastCompletedBatch = this.lastCompletedBatch();";
  protected final String TEXT_116 = NL + "                synchronized(lastCompletedBatch) {" + NL + "                    this.batchCompleted = this.batchCompleted + 1;" + NL + "                    if (!lastCompletedBatch.isEmpty()) this.lastProcessingDelay = lastCompletedBatch.get().processingDelay().get() + \"\";" + NL + "                    if (!lastCompletedBatch.isEmpty()) this.lastSchedulingDelay = lastCompletedBatch.get().schedulingDelay().get() + \"\";" + NL + "                    if (!lastCompletedBatch.isEmpty()) this.lastTotalDelay = lastCompletedBatch.get().totalDelay().get() + \"\";" + NL + "                    StatBean sb = runStat.createSparkStreamingStatBean();" + NL + "                    sb.setSubjobId(\"1\");" + NL + "                    sb.setBatchCompleted(this.batchCompleted);" + NL + "                    sb.setBatchStarted(this.batchStarted);" + NL + "                    sb.setLastProcessingDelay(this.lastProcessingDelay);" + NL + "                    sb.setLastSchedulingDelay(this.lastSchedulingDelay);" + NL + "                    sb.setLastTotalDelay(this.lastTotalDelay);" + NL + "                    runStat.updateSparkStreamingData(sb);" + NL + "                }" + NL + "            }" + NL + "        }" + NL + "" + NL + "        private static SparkStreamingRunStat runStat = new SparkStreamingRunStat();";
  protected final String TEXT_117 = NL + NL + "    private final static String jobVersion = \"";
  protected final String TEXT_118 = "\";" + NL + "    private final static String jobName = \"";
  protected final String TEXT_119 = "\";" + NL + "    private final static String projectName = \"";
  protected final String TEXT_120 = "\";" + NL + "    public Integer errorCode = null;" + NL + "" + NL + "    private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();" + NL + "    private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(" + NL + "            new java.io.BufferedOutputStream(baos));" + NL + "" + NL + "    private static String currentComponent = \"\";" + NL + "" + NL + "    public String getExceptionStackTrace() {" + NL + "        if (\"failure\".equals(this.getStatus())) {" + NL + "            errorMessagePS.flush();" + NL + "            return baos.toString();" + NL + "        }" + NL + "        return null;" + NL + "    }" + NL + "" + NL + "    //should be remove later" + NL + "    public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {" + NL + "" + NL + "    }" + NL;
  protected final String TEXT_121 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    Vector v = (Vector) codeGenArgument.getArgument();
    IProcess process = (IProcess)v.get(0);
    String version = (String)v.get(1);
    java.util.Map<Integer, String> jobs = (java.util.Map<Integer, String>)v.get(2);
    java.util.List<String> cacheNodes = (java.util.List<String>)v.get(3);
    INode sparkConfig = (INode)v.get(4);
    org.talend.hadoop.distribution.ESparkVersion sparkVersion
            = org.talend.hadoop.distribution.spark.SparkVersionUtil.getSparkVersion(sparkConfig);

    boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(process, "__LOG4J_ACTIVATE__"));

    String sparkMode = ElementParameterParser.getValue(sparkConfig, "__SPARK_MODE__");

    boolean useLocalMode = "true".equals(ElementParameterParser.getValue(process, "__SPARK_LOCAL_MODE__"));
    boolean isCustom = false;

    boolean isExecutedThroughSparkJobServer = false;
    boolean isExecutedThroughLivy = false;

    org.talend.hadoop.distribution.component.SparkStreamingComponent sparkStreamingDistrib = null;

    if(!useLocalMode) {
        String sparkDistribution = ElementParameterParser.getValue(sparkConfig, "__DISTRIBUTION__");
        String sparkVersionStr = ElementParameterParser.getValue(sparkConfig, "__SPARK_VERSION__");
        try {
            sparkStreamingDistrib = (org.talend.hadoop.distribution.component.SparkStreamingComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(sparkDistribution, sparkVersionStr);
        } catch (java.lang.Exception e) {
            e.printStackTrace();
            return "";
        }

        isCustom = sparkStreamingDistrib != null && sparkStreamingDistrib instanceof org.talend.hadoop.distribution.custom.CustomDistribution;
        isExecutedThroughSparkJobServer = !isCustom && sparkStreamingDistrib != null && sparkStreamingDistrib.isExecutedThroughSparkJobServer();
        isExecutedThroughLivy = !isCustom && sparkStreamingDistrib != null && sparkStreamingDistrib.isExecutedThroughLivy();
    }

    List< ? extends INode> processNodes = (List< ? extends INode>)process.getGeneratingNodes();
    List<IContextParameter> params = new ArrayList<IContextParameter>();
    params=process.getContextManager().getDefaultContext().getContextParameterList();

    String className = process.getName();
    boolean isTestContainer = ProcessUtils.isTestContainer(process);
    if (isTestContainer) {
        className = className + "Test";
    }

    boolean stats = codeGenArgument.isStatistics();

    
//?
IBrandingService service=(IBrandingService)GlobalServiceRegister.getDefault().getService(IBrandingService.class);
if(service instanceof AbstractBrandingService){

    stringBuffer.append(TEXT_1);
    stringBuffer.append(((AbstractBrandingService) service).getJobLicenseHeader(version));
    
}
    String jobFolderName = "";
    IProcess baseProcess = ProcessUtils.getTestContainerBaseProcess(process);
    if (baseProcess != null) {
        jobFolderName = JavaResourcesHelper.getJobFolderName(baseProcess.getName(), baseProcess.getVersion()) + '.';
    }
    jobFolderName = jobFolderName + JavaResourcesHelper.getJobFolderName(process.getName(), process.getVersion());
    String packageName = codeGenArgument.getCurrentProjectName().toLowerCase() + "." + jobFolderName;

    stringBuffer.append(TEXT_2);
    stringBuffer.append( packageName );
    stringBuffer.append(TEXT_3);
    for (String routine : CodeGeneratorRoutine.getRequiredRoutineName(process)) {
    if(!routine.equals(ITalendSynchronizer.TEMPLATE)){
    stringBuffer.append(TEXT_4);
    stringBuffer.append(routine);
    stringBuffer.append(TEXT_5);
      }
}
    stringBuffer.append(TEXT_6);
    
if (org.talend.hadoop.distribution.ESparkVersion.SPARK_2_0.compareTo(sparkVersion) > 0) {
    
    stringBuffer.append(TEXT_7);
    
} else {
    
    stringBuffer.append(TEXT_8);
    
}

if(ProcessUtils.isTestContainer(process)) {
				
    stringBuffer.append(TEXT_9);
    
}

    stringBuffer.append(TEXT_10);
    stringBuffer.append(ElementParameterParser.getValue(process, "__HEADER_IMPORT__") );
    stringBuffer.append(TEXT_11);
    stringBuffer.append(ElementParameterParser.getValue(process, "__FOOTER_IMPORT__") );
    stringBuffer.append(TEXT_12);
    
    List<INode> nodesWithImport = process.getNodesWithImport();
    if(nodesWithImport != null) {
        for(INode node:nodesWithImport){

    stringBuffer.append(TEXT_13);
    stringBuffer.append(node.getUniqueName() );
    stringBuffer.append(TEXT_14);
    stringBuffer.append(ElementParameterParser.getValue(node, "__IMPORT__") );
    stringBuffer.append(TEXT_15);
          }
    }

    stringBuffer.append(TEXT_16);
    stringBuffer.append(className );
    stringBuffer.append(TEXT_17);
    stringBuffer.append(ElementParameterParser.getValue(process, "__PURPOSE__") );
    stringBuffer.append(TEXT_18);
    stringBuffer.append(ElementParameterParser.getValue(process, "__DESCRIPTION__") );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(sparkVersion);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(ElementParameterParser.getValue(process, "__AUTHOR__") );
    stringBuffer.append(TEXT_21);
    stringBuffer.append(version );
    stringBuffer.append(TEXT_22);
    stringBuffer.append(ElementParameterParser.getValue(process, "__STATUS__") );
    stringBuffer.append(TEXT_23);
    stringBuffer.append(className);
    stringBuffer.append(isExecutedThroughSparkJobServer?" extends spark.jobserver.JavaSparkJob":"");
    stringBuffer.append(TEXT_24);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_25);
     if (isTestContainer) { 
    stringBuffer.append(TEXT_26);
     } 
    stringBuffer.append(TEXT_27);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_29);
     } 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(className);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(codeGenArgument.getCurrentProjectName().toLowerCase());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(jobFolderName);
    stringBuffer.append(TEXT_33);
    
            for(IContextParameter ctxParam : params){
                String cParaName = ctxParam.getName();
                
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_35);
    if(ctxParam.getType().equals("id_Date")){
    stringBuffer.append(TEXT_36);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_38);
    stringBuffer.append(ctxParam.getValue() );
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_40);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_46);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_48);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_51);
    }else{
    stringBuffer.append(TEXT_52);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cParaName );
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    
            }
            
    stringBuffer.append(TEXT_56);
    
        for(IContextParameter ctxParam : params){
            if(ctxParam.getType().equals("id_List Of Value") || ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory")){
            
    stringBuffer.append(TEXT_57);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_60);
    
            }else{
            
    stringBuffer.append(TEXT_61);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_62);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(Character.toUpperCase(ctxParam.getName().charAt(0)) + ctxParam.getName().substring(1));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_66);
    
            }
        }
        
    stringBuffer.append(TEXT_67);
    
            for(IContextParameter ctxParam : params){
                if (ctxParam.getType().equals("id_Password")) {
                
    stringBuffer.append(TEXT_68);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_80);
    
                    continue;
                }
                String typeToGenerate ="String";
                if( !(ctxParam.getType().equals("id_File") || ctxParam.getType().equals("id_Directory") ||ctxParam.getType().equals("id_List Of Value"))){
                   typeToGenerate=JavaTypesManager.getTypeToGenerate(ctxParam.getType(),true);
                }
                if(typeToGenerate.equals("java.util.Date")){
                
    stringBuffer.append(TEXT_81);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_99);
    
                }else if(typeToGenerate.equals("Object")||typeToGenerate.equals("String")||typeToGenerate.equals("java.lang.String")){
                
    stringBuffer.append(TEXT_100);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_103);
    
                }else if(typeToGenerate.equals("Character")&&ctxParam.getName()!=null){
                 
    stringBuffer.append(TEXT_104);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_106);
    
                }else{
                 
    stringBuffer.append(TEXT_107);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(ctxParam.getName());
    stringBuffer.append(TEXT_111);
    
                }
            }
            
    stringBuffer.append(TEXT_112);
    
    if((stats || isLog4jEnabled) && !isExecutedThroughSparkJobServer && !isExecutedThroughLivy) {
        boolean isSpark13 = (sparkVersion == org.talend.hadoop.distribution.ESparkVersion.SPARK_1_3);

    stringBuffer.append(TEXT_113);
    
                    if(!isSpark13) {
                
    stringBuffer.append(TEXT_114);
    
                    } else {
                
    stringBuffer.append(TEXT_115);
    
                    }
                
    stringBuffer.append(TEXT_116);
    
    }

    stringBuffer.append(TEXT_117);
    stringBuffer.append(process.getVersion() );
    stringBuffer.append(TEXT_118);
    stringBuffer.append(codeGenArgument.getJobName() );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(codeGenArgument.getCurrentProjectName() );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(TEXT_121);
    return stringBuffer.toString();
  }
}
