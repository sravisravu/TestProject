package org.talend.designer.codegen.translators.mapreduce.input;

import java.util.List;
import java.util.Map;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;

public class TAvroInputMrcodeJava
{
  protected static String nl;
  public static synchronized TAvroInputMrcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TAvroInputMrcodeJava result = new TAvroInputMrcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "public static class ";
  protected final String TEXT_2 = "InputFormat extends FileInputFormat<NullWritable, ";
  protected final String TEXT_3 = "> {" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public final static String EXT = \".avro\";" + NL + "" + NL + "    protected org.apache.hadoop.fs.FileStatus[] listStatus(JobConf conf) throws IOException {" + NL + "        List<org.apache.hadoop.fs.FileStatus> result = new java.util.ArrayList<org.apache.hadoop.fs.FileStatus>();" + NL + "        for (org.apache.hadoop.fs.FileStatus file : super.listStatus(conf)) {" + NL + "              if (file.getPath().getName().endsWith(EXT)) {" + NL + "                result.add(file);" + NL + "            }" + NL + "        }" + NL + "        return result.toArray(new org.apache.hadoop.fs.FileStatus[result.size()]);" + NL + "      }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits)" + NL + "            throws IOException {" + NL + "        context = new ContextProperties(job);" + NL + "        job.set(\"mapred.input.dir\", ";
  protected final String TEXT_4 = ");" + NL + "        return super.getSplits(job, numSplits);" + NL + "    }" + NL + "" + NL + "    public RecordReader<NullWritable, ";
  protected final String TEXT_5 = "> getRecordReader(" + NL + "            InputSplit split, JobConf job, Reporter reporter)" + NL + "            throws IOException {" + NL + "        reporter.setStatus(split.toString());" + NL + "        return new AvroRecordReader(job, (FileSplit) split);" + NL + "    }" + NL + "" + NL + "    protected static class AvroRecordReader implements" + NL + "        RecordReader<NullWritable, ";
  protected final String TEXT_6 = "> {" + NL + "" + NL + "        private ContextProperties context;" + NL + "        private org.apache.avro.file.FileReader<org.apache.avro.generic.GenericRecord> reader;" + NL + "        private long start;" + NL + "        private long end;" + NL + "        org.apache.avro.generic.GenericRecord record = null;" + NL + "" + NL + "        protected AvroRecordReader(JobConf job, FileSplit split)" + NL + "                throws IOException {" + NL + "            this.context = new ContextProperties(job);" + NL + "" + NL + "            // Create the schema from the output structure." + NL + "            org.apache.avro.Schema schema;" + NL + "            {" + NL + "                List<org.apache.avro.Schema.Field> fields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "                List<org.apache.avro.Schema> unionSchema = null;";
  protected final String TEXT_7 = NL + "                        fields.add(new org.apache.avro.Schema.Field(" + NL + "                                \"";
  protected final String TEXT_8 = "\"," + NL + "                                org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_9 = ")," + NL + "                                null, null));";
  protected final String TEXT_10 = NL + "                        unionSchema = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_11 = "));" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));" + NL + "                        fields.add(new org.apache.avro.Schema.Field(" + NL + "                                \"";
  protected final String TEXT_12 = "\"," + NL + "                                org.apache.avro.Schema.createUnion(unionSchema)," + NL + "                                null, null));";
  protected final String TEXT_13 = NL + "                schema = org.apache.avro.Schema.createRecord(fields);" + NL + "            }" + NL + "" + NL + "            // Create a file reader." + NL + "            org.apache.avro.io.DatumReader<org.apache.avro.generic.GenericRecord> datumReader =" + NL + "                    new org.apache.avro.generic.GenericDatumReader<org.apache.avro.generic.GenericRecord>(schema);" + NL + "            reader = org.apache.avro.file.DataFileReader.openReader(" + NL + "                    new org.apache.avro.mapred.FsInput(split.getPath(), job)," + NL + "                    datumReader);" + NL + "" + NL + "            // Sync to start." + NL + "            reader.sync(split.getStart());" + NL + "            this.start = reader.tell();" + NL + "            this.end = split.getStart() + split.getLength();" + NL + "" + NL + "            record = new org.apache.avro.generic.GenericData.Record(schema);" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_14 = " value) throws IOException {" + NL + "            while (reader.hasNext() && !reader.pastSync(end)) {" + NL + "                try {" + NL + "                    record = reader.next(record);" + NL + "                    if (validateRecord(value));" + NL + "                        return true;" + NL + "                } catch (ArrayIndexOutOfBoundsException e) {";
  protected final String TEXT_15 = NL + "                        LOG.error(e);";
  protected final String TEXT_16 = NL + "                        throw new IOException(e.getMessage());";
  protected final String TEXT_17 = NL + "                        return false;";
  protected final String TEXT_18 = NL + "                } catch (Exception e) {";
  protected final String TEXT_19 = NL + "                        LOG.error(e);";
  protected final String TEXT_20 = NL + "                        throw new IOException(e.getMessage());";
  protected final String TEXT_21 = NL + "                }" + NL + "            }" + NL + "            return false;" + NL + "        }" + NL + "" + NL + "        private boolean validateRecord(";
  protected final String TEXT_22 = " value) throws Exception {" + NL + "            Object columnObject = null;" + NL;
  protected final String TEXT_23 = NL + "                columnObject = record.get(\"";
  protected final String TEXT_24 = "\");" + NL + "                if (columnObject != null) {" + NL;
  protected final String TEXT_25 = NL + "                    ";
  protected final String TEXT_26 = " = columnObject.toString();";
  protected final String TEXT_27 = NL + "                    ";
  protected final String TEXT_28 = " = ((Integer)columnObject).byteValue();";
  protected final String TEXT_29 = NL + "                    ";
  protected final String TEXT_30 = " = ((Integer)columnObject).shortValue();";
  protected final String TEXT_31 = NL + "                    ";
  protected final String TEXT_32 = " = (Character)(char)(int)(Integer)columnObject;";
  protected final String TEXT_33 = NL + "                    ";
  protected final String TEXT_34 = " = ((java.nio.ByteBuffer)columnObject).array();";
  protected final String TEXT_35 = NL + "                    ";
  protected final String TEXT_36 = " = new java.math.BigDecimal(columnObject.toString());";
  protected final String TEXT_37 = NL + "                    ";
  protected final String TEXT_38 = " = new java.util.Date((Long)columnObject);";
  protected final String TEXT_39 = NL + "                    ";
  protected final String TEXT_40 = " = (";
  protected final String TEXT_41 = ")columnObject;";
  protected final String TEXT_42 = NL;
  protected final String TEXT_43 = NL + "                    } else { ";
  protected final String TEXT_44 = " = ";
  protected final String TEXT_45 = ";";
  protected final String TEXT_46 = NL + "                }";
  protected final String TEXT_47 = NL + "            return true;" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_48 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_49 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "             return reader.tell();" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            if (reader != null) {" + NL + "                reader.close();" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            if (start == end) {" + NL + "                return 0.0f;" + NL + "            } else {" + NL + "                return Math.min(1.0f," + NL + "                                (getPos() - start) / (float) (end - start));" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_50 = NL + NL + "public static class ";
  protected final String TEXT_51 = "InputFormat extends FileInputFormat<NullWritable, ";
  protected final String TEXT_52 = "> {" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public final static String EXT = \".avro\";" + NL + "" + NL + "    protected org.apache.hadoop.fs.FileStatus[] listStatus(JobConf conf) throws IOException {" + NL + "        List<org.apache.hadoop.fs.FileStatus> result = new java.util.ArrayList<org.apache.hadoop.fs.FileStatus>();" + NL + "        for (org.apache.hadoop.fs.FileStatus file : super.listStatus(conf)) {" + NL + "              if (file.getPath().getName().endsWith(EXT)) {" + NL + "                result.add(file);" + NL + "            }" + NL + "        }" + NL + "        return result.toArray(new org.apache.hadoop.fs.FileStatus[result.size()]);" + NL + "      }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits)" + NL + "            throws IOException {" + NL + "        context = new ContextProperties(job);" + NL + "        job.set(\"mapred.input.dir\", ";
  protected final String TEXT_53 = ");" + NL + "        return super.getSplits(job, numSplits);" + NL + "    }" + NL + "" + NL + "    public RecordReader<NullWritable, ";
  protected final String TEXT_54 = "> getRecordReader(" + NL + "            InputSplit split, JobConf job, Reporter reporter)" + NL + "            throws IOException {" + NL + "        reporter.setStatus(split.toString());" + NL + "        return new AvroRecordReader(job, (FileSplit) split);" + NL + "    }" + NL + "" + NL + "    protected static class AvroRecordReader implements" + NL + "        RecordReader<NullWritable, ";
  protected final String TEXT_55 = "> {" + NL + "" + NL + "        private ContextProperties context;" + NL + "        private org.apache.avro.file.FileReader<org.apache.avro.generic.GenericRecord> reader;" + NL + "        private long start;" + NL + "        private long end;" + NL + "        org.apache.avro.generic.GenericRecord record = null;" + NL + "" + NL + "        protected AvroRecordReader(JobConf job, FileSplit split)" + NL + "                throws IOException {" + NL + "            this.context = new ContextProperties(job);" + NL + "" + NL + "            // Create the schema from the output structure." + NL + "            org.apache.avro.Schema schema;" + NL + "            {" + NL + "                List<org.apache.avro.Schema.Field> fields = new java.util.ArrayList<org.apache.avro.Schema.Field>();" + NL + "                List<org.apache.avro.Schema> unionSchema = null;";
  protected final String TEXT_56 = NL + "                        fields.add(new org.apache.avro.Schema.Field(" + NL + "                                \"";
  protected final String TEXT_57 = "\"," + NL + "                                org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_58 = ")," + NL + "                                null, null));";
  protected final String TEXT_59 = NL + "                        unionSchema = new java.util.ArrayList<org.apache.avro.Schema>();" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.";
  protected final String TEXT_60 = "));" + NL + "                        unionSchema.add(org.apache.avro.Schema.create(org.apache.avro.Schema.Type.NULL));" + NL + "                        fields.add(new org.apache.avro.Schema.Field(" + NL + "                                \"";
  protected final String TEXT_61 = "\"," + NL + "                                org.apache.avro.Schema.createUnion(unionSchema)," + NL + "                                null, null));";
  protected final String TEXT_62 = NL + "                schema = org.apache.avro.Schema.createRecord(fields);" + NL + "            }" + NL + "" + NL + "            // Create a file reader." + NL + "            org.apache.avro.io.DatumReader<org.apache.avro.generic.GenericRecord> datumReader =" + NL + "                    new org.apache.avro.generic.GenericDatumReader<org.apache.avro.generic.GenericRecord>(schema);" + NL + "            reader = org.apache.avro.file.DataFileReader.openReader(" + NL + "                    new org.apache.avro.mapred.FsInput(split.getPath(), job)," + NL + "                    datumReader);" + NL + "" + NL + "            // Sync to start." + NL + "            reader.sync(split.getStart());" + NL + "            this.start = reader.tell();" + NL + "            this.end = split.getStart() + split.getLength();" + NL + "" + NL + "            record = new org.apache.avro.generic.GenericData.Record(schema);" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_63 = " value) throws IOException {" + NL + "            while (reader.hasNext() && !reader.pastSync(end)) {" + NL + "                try {" + NL + "                    record = reader.next(record);" + NL + "                    if (validateRecord(value));" + NL + "                        continue;" + NL + "                } catch (ArrayIndexOutOfBoundsException e) {";
  protected final String TEXT_64 = NL + "                        LOG.error(e);";
  protected final String TEXT_65 = NL + "                    return false;" + NL + "                } catch (Exception e) {" + NL + "                    value.invalidInputLine = record.toString();" + NL + "                    value.errorCode = e.toString();" + NL + "                    return true;" + NL + "                }" + NL + "            }" + NL + "            return false;" + NL + "        }" + NL + "" + NL + "        private boolean validateRecord(";
  protected final String TEXT_66 = " value) throws Exception {" + NL + "            Object columnObject = null;" + NL;
  protected final String TEXT_67 = NL + "                columnObject = record.get(\"";
  protected final String TEXT_68 = "\");" + NL + "                if (columnObject != null) {" + NL;
  protected final String TEXT_69 = NL + "                    ";
  protected final String TEXT_70 = " = columnObject.toString();";
  protected final String TEXT_71 = NL + "                    ";
  protected final String TEXT_72 = " = ((Integer)columnObject).byteValue();";
  protected final String TEXT_73 = NL + "                    ";
  protected final String TEXT_74 = " = ((Integer)columnObject).shortValue();";
  protected final String TEXT_75 = NL + "                    ";
  protected final String TEXT_76 = " = (Character)(char)(int)(Integer)columnObject;";
  protected final String TEXT_77 = NL + "                    ";
  protected final String TEXT_78 = " = ((java.nio.ByteBuffer)columnObject).array();";
  protected final String TEXT_79 = NL + "                    ";
  protected final String TEXT_80 = " = new java.math.BigDecimal(columnObject.toString());";
  protected final String TEXT_81 = NL + "                    ";
  protected final String TEXT_82 = " = new java.util.Date((Long)columnObject);";
  protected final String TEXT_83 = NL + "                    ";
  protected final String TEXT_84 = " = (";
  protected final String TEXT_85 = ")columnObject;";
  protected final String TEXT_86 = NL;
  protected final String TEXT_87 = NL + "                    } else { ";
  protected final String TEXT_88 = " = ";
  protected final String TEXT_89 = ";";
  protected final String TEXT_90 = NL + "                }";
  protected final String TEXT_91 = NL + "            return true;" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_92 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_93 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "             return reader.tell();" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            if (reader != null) {" + NL + "                reader.close();" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            if (start == end) {" + NL + "                return 0.0f;" + NL + "            } else {" + NL + "                return Math.min(1.0f," + NL + "                                (getPos() - start) / (float) (end - start));" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_94 = NL + "public static class ";
  protected final String TEXT_95 = " extends org.apache.hadoop.io.ObjectWritable {" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_96 = "InputFormat extends FileInputFormat<NullWritable, ";
  protected final String TEXT_97 = "> {" + NL + "    private ContextProperties context;" + NL + "" + NL + "    public final static String EXT = \".avro\";" + NL + "" + NL + "    protected org.apache.hadoop.fs.FileStatus[] listStatus(JobConf conf) throws IOException {" + NL + "        List<org.apache.hadoop.fs.FileStatus> result = new java.util.ArrayList<org.apache.hadoop.fs.FileStatus>();" + NL + "        for (org.apache.hadoop.fs.FileStatus file : super.listStatus(conf)) {" + NL + "              if (file.getPath().getName().endsWith(EXT)) {" + NL + "                result.add(file);" + NL + "            }" + NL + "        }" + NL + "        return result.toArray(new org.apache.hadoop.fs.FileStatus[result.size()]);" + NL + "      }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits)" + NL + "            throws IOException {" + NL + "        context = new ContextProperties(job);" + NL + "        job.set(\"mapred.input.dir\", ";
  protected final String TEXT_98 = ");" + NL + "        return super.getSplits(job, numSplits);" + NL + "    }" + NL + "" + NL + "    public RecordReader<NullWritable, ";
  protected final String TEXT_99 = "> getRecordReader(" + NL + "            InputSplit split, JobConf job, Reporter reporter)" + NL + "            throws IOException {" + NL + "        reporter.setStatus(split.toString());" + NL + "        return new AvroRecordReader(job, (FileSplit) split);" + NL + "    }" + NL + "" + NL + "    protected static class AvroRecordReader implements" + NL + "        RecordReader<NullWritable, ";
  protected final String TEXT_100 = "> {" + NL + "" + NL + "        private ContextProperties context;" + NL + "        private org.apache.avro.file.FileReader<org.apache.avro.generic.GenericRecord> reader;" + NL + "        private long start;" + NL + "        private long end;" + NL + "        org.apache.avro.generic.GenericRecord record = null;" + NL + "" + NL + "        protected AvroRecordReader(JobConf job, FileSplit split)" + NL + "                throws IOException {" + NL + "            this.context = new ContextProperties(job);" + NL + "" + NL + "            // Create a file reader." + NL + "            org.apache.avro.io.DatumReader<org.apache.avro.generic.GenericRecord> datumReader =" + NL + "                    new org.apache.avro.generic.GenericDatumReader<org.apache.avro.generic.GenericRecord>();" + NL + "            reader = org.apache.avro.file.DataFileReader.openReader(" + NL + "                    new org.apache.avro.mapred.FsInput(split.getPath(), job)," + NL + "                    datumReader);" + NL + "" + NL + "            // Get the schema from the file." + NL + "            org.apache.avro.Schema schema = reader.getSchema();" + NL + "" + NL + "             // Sync to start." + NL + "            reader.sync(split.getStart());" + NL + "            this.start = reader.tell();" + NL + "            this.end = split.getStart() + split.getLength();" + NL + "" + NL + "            record = new org.apache.avro.generic.GenericData.Record(schema);" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_101 = " value) throws IOException {" + NL + "            if (!reader.hasNext() || reader.pastSync(end)) {" + NL + "                return false;" + NL + "            }" + NL + "            value.set(reader.next(record));" + NL + "            return true;" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_102 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_103 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "             return reader.tell();" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            if (reader != null) {" + NL + "                reader.close();" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            if (start == end) {" + NL + "                return 0.0f;" + NL + "            } else {" + NL + "                return Math.min(1.0f," + NL + "                                (getPos() - start) / (float) (end - start));" + NL + "            }" + NL + "        }" + NL + "    }" + NL + "}" + NL + "" + NL + "public static class ";
  protected final String TEXT_104 = "_InputMapper extends MapReduceBase" + NL + "        implements Mapper<NullWritable, ";
  protected final String TEXT_105 = ", NullWritable, WritableComparable>{" + NL + "    private ContextProperties context;" + NL + "    private GlobalVar globalMap;" + NL + "    public MultipleOutputs mos_";
  protected final String TEXT_106 = ";" + NL + "    private ";
  protected final String TEXT_107 = "Struct ";
  protected final String TEXT_108 = " = null;" + NL + "    private ";
  protected final String TEXT_109 = "Struct ";
  protected final String TEXT_110 = " = null;" + NL + "" + NL + "    public void configure(JobConf job_";
  protected final String TEXT_111 = "){" + NL + "        context = new ContextProperties(job_";
  protected final String TEXT_112 = ");" + NL + "        globalMap = new GlobalVar(job_";
  protected final String TEXT_113 = ");" + NL + "        mos_";
  protected final String TEXT_114 = " = new MultipleOutputs(job_";
  protected final String TEXT_115 = ");" + NL;
  protected final String TEXT_116 = NL + "        ";
  protected final String TEXT_117 = " = new ";
  protected final String TEXT_118 = "Struct();";
  protected final String TEXT_119 = NL + "        ";
  protected final String TEXT_120 = " = new ";
  protected final String TEXT_121 = "Struct();" + NL;
  protected final String TEXT_122 = NL + "                try {" + NL + "                    FileSystem fs = FileSystem.get(job_";
  protected final String TEXT_123 = ");" + NL + "                    Path path = new Path(" + NL + "                            \"/tmp/";
  protected final String TEXT_124 = "\"" + NL + "                            + \"/tMROutput_";
  protected final String TEXT_125 = "\"" + NL + "                            + \"/";
  protected final String TEXT_126 = "\");" + NL + "                    fs.mkdirs(path);" + NL + "                } catch (Exception ex_";
  protected final String TEXT_127 = ") {" + NL + "                    throw new RuntimeException(ex_";
  protected final String TEXT_128 = ".getMessage());" + NL + "                }";
  protected final String TEXT_129 = NL + "    }" + NL + "" + NL + "    public void map(NullWritable key_";
  protected final String TEXT_130 = ", ";
  protected final String TEXT_131 = " value_";
  protected final String TEXT_132 = "," + NL + "            OutputCollector<NullWritable, WritableComparable> output_";
  protected final String TEXT_133 = ", Reporter reporter_";
  protected final String TEXT_134 = ") throws IOException{" + NL + "        try {" + NL + "            org.apache.avro.generic.GenericRecord record = (org.apache.avro.generic.GenericRecord) value_";
  protected final String TEXT_135 = ".get();" + NL + "            Object columnObject = null;" + NL;
  protected final String TEXT_136 = NL + "                columnObject = record.get(\"";
  protected final String TEXT_137 = "\");" + NL + "                if(columnObject != null) {" + NL;
  protected final String TEXT_138 = NL + "                    ";
  protected final String TEXT_139 = " = columnObject.toString();";
  protected final String TEXT_140 = NL + "                    ";
  protected final String TEXT_141 = " = ((Integer)columnObject).byteValue();";
  protected final String TEXT_142 = NL + "                    ";
  protected final String TEXT_143 = " = ((Integer)columnObject).shortValue();";
  protected final String TEXT_144 = NL + "                    ";
  protected final String TEXT_145 = " = (Character)(char)(int)(Integer)columnObject;";
  protected final String TEXT_146 = NL + "                    ";
  protected final String TEXT_147 = " = ((java.nio.ByteBuffer)columnObject).array();";
  protected final String TEXT_148 = NL + "                    ";
  protected final String TEXT_149 = " = new java.math.BigDecimal(columnObject.toString());";
  protected final String TEXT_150 = NL + "                    ";
  protected final String TEXT_151 = " = new java.util.Date((Long)columnObject);";
  protected final String TEXT_152 = NL + "                    ";
  protected final String TEXT_153 = " = (";
  protected final String TEXT_154 = ")columnObject;";
  protected final String TEXT_155 = NL;
  protected final String TEXT_156 = NL + "                    } else { ";
  protected final String TEXT_157 = " = ";
  protected final String TEXT_158 = ";";
  protected final String TEXT_159 = NL + "                }";
  protected final String TEXT_160 = NL + "            output_";
  protected final String TEXT_161 = ".collect(NullWritable.get(), ";
  protected final String TEXT_162 = ");" + NL + "        } catch (Exception e) {";
  protected final String TEXT_163 = NL + "            ";
  protected final String TEXT_164 = ".invalidInputLine = value_";
  protected final String TEXT_165 = ".toString();";
  protected final String TEXT_166 = NL + "            ";
  protected final String TEXT_167 = ".errorCode = e.toString();" + NL + "            mos_";
  protected final String TEXT_168 = ".getCollector(\"";
  protected final String TEXT_169 = "\", reporter_";
  protected final String TEXT_170 = ")" + NL + "                    .collect(NullWritable.get(), ";
  protected final String TEXT_171 = ");" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public void close() throws IOException{" + NL + "        mos_";
  protected final String TEXT_172 = ".close();" + NL + "    }" + NL + "} // end of ";
  protected final String TEXT_173 = "_InputMapper" + NL + NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument) argument;
INode node = (INode) codeGenArgument.getArgument();
String cid = node.getUniqueName();

boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

// Fail fast when no output connections exist.
List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
if (conns == null || conns.size() == 0)
    return "";

// Parameters
String folder = ElementParameterParser.getValue(node,"__FILENAME__");
boolean dieOnError = "true".equals(ElementParameterParser.getValue(node,
        "__DIE_ON_ERROR__"));

// Get the output connections.
List< ? extends IConnection> rejConns = node.getOutgoingConnections("REJECT");
List< ? extends IConnection> mainConns = node.getOutgoingConnections("FLOW");
IConnection mainConn = mainConns == null || mainConns.size() == 0
        ? null : mainConns.get(0);
IConnection rejConn = rejConns == null || rejConns.size() == 0
        ? null : rejConns.get(0);

// Fail fast on bad main connection style.
if (mainConn != null && !mainConn.getLineStyle().hasConnectionCategory(
        IConnectionCategory.DATA)) {
    return "";
}

// Fail fast on no connections.
if (mainConn == null && rejConn == null) {
    return "";
}

if (mainConn != null && rejConn == null) {
    
    
// The name of the main row.
String mainName = mainConn.getName();
String mainRecord = codeGenArgument.getRecordStructName(mainConn);
String recordStruct = mainRecord;
List<IMetadataColumn> mainColumns = mainConn.getMetadataTable().getListColumns();

java.util.Map<JavaType,String> talendTypeToAvroType = new java.util.HashMap<JavaType,String>();
talendTypeToAvroType.put(JavaTypesManager.STRING, "STRING");
talendTypeToAvroType.put(JavaTypesManager.CHARACTER, "INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE_ARRAY, "BYTES");
talendTypeToAvroType.put(JavaTypesManager.INTEGER, "INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE, "INT");
talendTypeToAvroType.put(JavaTypesManager.SHORT, "INT");
talendTypeToAvroType.put(JavaTypesManager.LONG, "LONG");
talendTypeToAvroType.put(JavaTypesManager.FLOAT, "FLOAT");
talendTypeToAvroType.put(JavaTypesManager.DOUBLE, "DOUBLE");
talendTypeToAvroType.put(JavaTypesManager.BOOLEAN, "BOOLEAN");
talendTypeToAvroType.put(JavaTypesManager.BIGDECIMAL, "STRING");
talendTypeToAvroType.put(JavaTypesManager.DATE, "LONG");


    stringBuffer.append(TEXT_1);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_6);
    
                // Create a compatible schema for the generic record
                for (IMetadataColumn column : mainColumns) {
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                    String columnName = column.getLabel();
                    boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
                    String avroType = talendTypeToAvroType.get(javaType);

                    if(isPrimitive) {
                        
    stringBuffer.append(TEXT_7);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(avroType);
    stringBuffer.append(TEXT_9);
    
                    } else {
                        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(avroType);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_12);
    
                    }
                }
                
    stringBuffer.append(TEXT_13);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_14);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_15);
     } 
    
                    if (dieOnError) {
                        
    stringBuffer.append(TEXT_16);
    
                    } else {
                        
    stringBuffer.append(TEXT_17);
    
                    }
                    
    stringBuffer.append(TEXT_18);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_19);
     } 
    
                    if (dieOnError) {
                        
    stringBuffer.append(TEXT_20);
    
                    }
                    
    stringBuffer.append(TEXT_21);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_22);
    
            for (IMetadataColumn column : mainColumns) {
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                String objectTypeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String columnName = column.getLabel();
                String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
                String toAssign = " value." + columnName;
                
    stringBuffer.append(TEXT_23);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_24);
    if(javaType == JavaTypesManager.STRING) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_26);
    } else if(javaType == JavaTypesManager.BYTE) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_28);
    } else if(javaType == JavaTypesManager.SHORT) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_30);
    } else if(javaType == JavaTypesManager.CHARACTER) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_32);
    } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_34);
    } else if(javaType == JavaTypesManager.BIGDECIMAL) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_36);
    } else if(javaType == JavaTypesManager.DATE) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_38);
    } else {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(objectTypeToGenerate);
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    
                if (defaultValue != null && defaultValue.length() > 0) {
                    
    stringBuffer.append(TEXT_43);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_45);
    
                }
                
    stringBuffer.append(TEXT_46);
    
            }
            
    stringBuffer.append(TEXT_47);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_49);
    
} else if (rejConn != null && mainConn == null) {
    
    
// The name of the reject row.
String rejName = rejConn.getName();
String rejRecord = codeGenArgument.getRecordStructName(rejConn);
String recordStruct = rejRecord;

// We don't what to get the metadata of the main flow, so we check if the current metadata is not the reject flow
List<IMetadataColumn> mainColumns = null;
for (IMetadataTable md : node.getMetadataList()) {
    if (!"REJECT".equals(md.getTableName())) {
        mainColumns = md.getListColumns();
        break;
    }
}
if (mainColumns == null)
    return "";

java.util.Map<JavaType,String> talendTypeToAvroType = new java.util.HashMap<JavaType,String>();
talendTypeToAvroType.put(JavaTypesManager.STRING, "STRING");
talendTypeToAvroType.put(JavaTypesManager.CHARACTER, "INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE_ARRAY, "BYTES");
talendTypeToAvroType.put(JavaTypesManager.INTEGER, "INT");
talendTypeToAvroType.put(JavaTypesManager.BYTE, "INT");
talendTypeToAvroType.put(JavaTypesManager.SHORT, "INT");
talendTypeToAvroType.put(JavaTypesManager.LONG, "LONG");
talendTypeToAvroType.put(JavaTypesManager.FLOAT, "FLOAT");
talendTypeToAvroType.put(JavaTypesManager.DOUBLE, "DOUBLE");
talendTypeToAvroType.put(JavaTypesManager.BOOLEAN, "BOOLEAN");
talendTypeToAvroType.put(JavaTypesManager.BIGDECIMAL, "STRING");
talendTypeToAvroType.put(JavaTypesManager.DATE, "LONG");


    stringBuffer.append(TEXT_50);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_51);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_52);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_53);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_55);
    
                // Create a compatible schema for the generic record
                for (IMetadataColumn column : mainColumns) {
                    JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                    String columnName = column.getLabel();
                    boolean isPrimitive = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
                    String avroType = talendTypeToAvroType.get(javaType);

                    if(isPrimitive) {
                        
    stringBuffer.append(TEXT_56);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(avroType);
    stringBuffer.append(TEXT_58);
    
                    } else {
                        
    stringBuffer.append(TEXT_59);
    stringBuffer.append(avroType);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_61);
    
                    }
                }
                
    stringBuffer.append(TEXT_62);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_63);
     if(isLog4jEnabled) { 
    stringBuffer.append(TEXT_64);
     } 
    stringBuffer.append(TEXT_65);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_66);
    
            for (IMetadataColumn column : mainColumns) {
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                String objectTypeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String columnName = column.getLabel();
                String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
                String toAssign = objectTypeToGenerate + " unused_" + columnName;
                
    stringBuffer.append(TEXT_67);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_68);
    if(javaType == JavaTypesManager.STRING) {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_70);
    } else if(javaType == JavaTypesManager.BYTE) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_72);
    } else if(javaType == JavaTypesManager.SHORT) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_74);
    } else if(javaType == JavaTypesManager.CHARACTER) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_76);
    } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_78);
    } else if(javaType == JavaTypesManager.BIGDECIMAL) {
    stringBuffer.append(TEXT_79);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_80);
    } else if(javaType == JavaTypesManager.DATE) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_82);
    } else {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(objectTypeToGenerate);
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    
				if (defaultValue != null && defaultValue.length() > 0) {
                    
    stringBuffer.append(TEXT_87);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_89);
    
                }
                
    stringBuffer.append(TEXT_90);
    
            }
            
    stringBuffer.append(TEXT_91);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_93);
    
} else {
    
    
// The name of the main row.
String mainName = mainConn.getName();
String rejName = rejConn.getName();
String mainRecord = codeGenArgument.getRecordStructName(mainConn);
String recordStruct = mainName + "TemporaryStruct";
List<IMetadataColumn> mainColumns = mainConn.getMetadataTable().getListColumns();


    stringBuffer.append(TEXT_94);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(folder);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_121);
    
        // Force the creation of the output directory
        for (INode virtualNode : node.getProcess().getGraphicalNodes()) {
            if (virtualNode.getUniqueName().equals(cid)) {
                
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(node.getProcess().getName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(virtualNode.getUniqueName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(virtualNode.getOutgoingConnections("REJECT").get(0).getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_128);
    
                break;
            }
        }
        
    stringBuffer.append(TEXT_129);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_132);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_135);
    
            for (IMetadataColumn column : mainColumns) {
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                String objectTypeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), true);
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String columnName = column.getLabel();
                String defaultValue = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, column.getDefault());
                String toAssign = mainName + "." + columnName;
                
    stringBuffer.append(TEXT_136);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_137);
    if(javaType == JavaTypesManager.STRING) {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_139);
    } else if(javaType == JavaTypesManager.BYTE) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_141);
    } else if(javaType == JavaTypesManager.SHORT) {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_143);
    } else if(javaType == JavaTypesManager.CHARACTER) {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_145);
    } else if(javaType == JavaTypesManager.BYTE_ARRAY) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_147);
    } else if(javaType == JavaTypesManager.BIGDECIMAL) {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_149);
    } else if(javaType == JavaTypesManager.DATE) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(objectTypeToGenerate);
    stringBuffer.append(TEXT_154);
    }
    stringBuffer.append(TEXT_155);
    
                if (defaultValue != null && defaultValue.length() > 0) {
                    
    stringBuffer.append(TEXT_156);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(defaultValue);
    stringBuffer.append(TEXT_158);
    
                }
                
    stringBuffer.append(TEXT_159);
    
            }
            
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_162);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(TEXT_166);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_170);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_173);
    
}

    return stringBuffer.toString();
  }
}
