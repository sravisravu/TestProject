package org.talend.designer.codegen.translators.databases.hbase;

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

public class THBaseInputMrcodeJava
{
  protected static String nl;
  public static synchronized THBaseInputMrcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    THBaseInputMrcodeJava result = new THBaseInputMrcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "        org.apache.hadoop.hbase.client.Result result;" + NL + "        while (null != (result = getNextResult())) {" + NL + "            try {" + NL + "                if (validateResult(result, value));" + NL + "                    return true;" + NL + "            } catch (Exception e) {";
  protected final String TEXT_2 = NL + "                    LOG.error(e);";
  protected final String TEXT_3 = NL + "                    throw new IOException(e.getMessage());";
  protected final String TEXT_4 = NL + "            }" + NL + "        }" + NL + "        return false;";
  protected final String TEXT_5 = NL + "        public boolean validateResult(org.apache.hadoop.hbase.client.Result result,";
  protected final String TEXT_6 = NL + "                ";
  protected final String TEXT_7 = " value) throws IOException {" + NL + "            org.apache.hadoop.hbase.io.ImmutableBytesWritable rowKey = new org.apache.hadoop.hbase.io.ImmutableBytesWritable();" + NL + "            rowKey.set(result.getRow());" + NL + "            lastSuccessfulRow = rowKey.get();" + NL + "" + NL + "            byte[] rowResult = null;" + NL + "            String temp = null;";
  protected final String TEXT_8 = NL + "                rowResult = result.getValue(" + NL + "                        org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_9 = ")," + NL + "                        org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_10 = "\"));" + NL + "                temp = org.apache.hadoop.hbase.util.Bytes.toString(rowResult);" + NL + "" + NL + "                if (temp != null && temp.length() > 0) {";
  protected final String TEXT_11 = NL + "                        ";
  protected final String TEXT_12 = " = temp.toString();";
  protected final String TEXT_13 = NL + "                        ";
  protected final String TEXT_14 = " = rowResult;";
  protected final String TEXT_15 = NL + "                        ";
  protected final String TEXT_16 = " = BigDataParserUtils.parseTo_Date(temp, ";
  protected final String TEXT_17 = ");";
  protected final String TEXT_18 = NL + "                        ";
  protected final String TEXT_19 = " = org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_20 = NL + "                        ";
  protected final String TEXT_21 = " = (char)org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_22 = NL + "                        ";
  protected final String TEXT_23 = " = org.apache.hadoop.hbase.util.Bytes.to";
  protected final String TEXT_24 = "(rowResult);";
  protected final String TEXT_25 = NL + "                        ";
  protected final String TEXT_26 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_27 = "(temp);";
  protected final String TEXT_28 = NL + "                } else {";
  protected final String TEXT_29 = NL + "                        ";
  protected final String TEXT_30 = " = ";
  protected final String TEXT_31 = ";";
  protected final String TEXT_32 = NL + "                        ";
  protected final String TEXT_33 = " = null;";
  protected final String TEXT_34 = NL + "                        throw new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_35 = "' in '";
  protected final String TEXT_36 = "' connection, value is invalid or this column should be nullable or have a default value.\");";
  protected final String TEXT_37 = NL + "                }";
  protected final String TEXT_38 = NL + "            return true;" + NL + "        }";
  protected final String TEXT_39 = NL;
  protected final String TEXT_40 = NL + NL + "public static class ";
  protected final String TEXT_41 = "InputFormat implements InputFormat<NullWritable, ";
  protected final String TEXT_42 = ">," + NL + "        JobConfigurable {" + NL + "" + NL + "    ContextProperties context;" + NL + "    private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "    private byte[][] inputColumns;" + NL + "" + NL + "    public void configure(JobConf job) {" + NL + "        context = new ContextProperties(job);" + NL + "        JobConf hbaseJob = new JobConf(job);" + NL + "        hbaseJob.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_43 = ");" + NL + "        hbaseJob.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_44 = ");" + NL;
  protected final String TEXT_45 = NL + "            hbaseJob.set(\"zookeeper.znode.parent\",";
  protected final String TEXT_46 = ");";
  protected final String TEXT_47 = NL + "            hbaseJob.set(\"hbase.table.namespace.mappings\", ";
  protected final String TEXT_48 = ");";
  protected final String TEXT_49 = NL + "            hbaseJob.set(";
  protected final String TEXT_50 = ", ";
  protected final String TEXT_51 = ");";
  protected final String TEXT_52 = NL + NL + "        try {" + NL + "            this.hTable = new org.apache.hadoop.hbase.client.HTable(hbaseJob, ";
  protected final String TEXT_53 = ");" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(e);" + NL + "        }" + NL;
  protected final String TEXT_54 = NL + "        this.inputColumns = new byte[";
  protected final String TEXT_55 = "][];";
  protected final String TEXT_56 = NL + "            this.inputColumns[";
  protected final String TEXT_57 = "] = org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_58 = NL + "                    ";
  protected final String TEXT_59 = NL + "                    + org.apache.hadoop.hbase.KeyValue.COLUMN_FAMILY_DELIMITER" + NL + "                    + \"";
  protected final String TEXT_60 = "\");";
  protected final String TEXT_61 = NL + "    }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {" + NL + "        if (this.hTable == null) {" + NL + "            throw new IOException(\"No table was provided\");" + NL + "        }" + NL + "        byte[][] startKeys = this.hTable.getStartKeys();" + NL + "        if (startKeys == null || startKeys.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one region\");" + NL + "        }" + NL + "        if (this.inputColumns == null || this.inputColumns.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one column\");" + NL + "        }" + NL + "        int realNumSplits = numSplits > startKeys.length ? startKeys.length : numSplits;" + NL + "        InputSplit[] splits = new InputSplit[realNumSplits];" + NL + "        int middle = startKeys.length / realNumSplits;" + NL + "        int startPos = 0;" + NL + "        for (int i = 0; i < realNumSplits; i++) {" + NL + "            int lastPos = startPos + middle;" + NL + "            lastPos = startKeys.length % realNumSplits > i ? lastPos + 1 : lastPos;" + NL + "            String regionLocation = hTable.getRegionLocation(startKeys[startPos]).getHostname();" + NL + "            splits[i] = new HBaseTableSplit(this.hTable.getTableName(), startKeys[startPos]," + NL + "                    ((i + 1) < realNumSplits) ? startKeys[lastPos] : org.apache.hadoop.hbase.HConstants.EMPTY_START_ROW, regionLocation);" + NL + "            startPos = lastPos;" + NL + "        }" + NL + "        return splits;" + NL + "    }" + NL + "" + NL + "    public RecordReader getRecordReader(InputSplit inputsplit, JobConf job, Reporter reporter) throws IOException {" + NL + "       return new HBaseTableRecordReader((HBaseTableSplit) inputsplit, job, this.hTable, this.inputColumns);" + NL + "    }" + NL + "" + NL + "    protected static class HBaseTableRecordReader implements RecordReader<NullWritable, ";
  protected final String TEXT_62 = "> {" + NL + "" + NL + "        private JobConf job;" + NL + "" + NL + "        private HBaseTableSplit split;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.ResultScanner scanner;" + NL + "" + NL + "        private byte[] startRow;" + NL + "" + NL + "        private byte[] endRow;" + NL + "" + NL + "        private byte[] lastSuccessfulRow;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "" + NL + "        private byte[][] inputColumns;" + NL + "" + NL + "        private org.apache.hadoop.hbase.filter.FilterList filterList;" + NL + "" + NL + "        ContextProperties context;" + NL + "" + NL + "        protected HBaseTableRecordReader(HBaseTableSplit split, JobConf job, org.apache.hadoop.hbase.client.HTable hTable, byte[][] inputColumns) throws IOException {" + NL + "            context = new ContextProperties(job);" + NL + "            this.hTable = hTable;" + NL + "            this.inputColumns = inputColumns;" + NL + "            this.split = split;" + NL + "            this.job = job;" + NL + "            this.startRow = split.getStartRow();" + NL + "            this.endRow = split.getEndRow();" + NL + "            initFilter();" + NL + "            restart(startRow);" + NL + "        }" + NL + "" + NL + "        private void initFilter(){";
  protected final String TEXT_63 = NL + "                    String [] multipleValues = null;" + NL + "                    byte [][] multipleBytesValues = null;";
  protected final String TEXT_64 = NL + "                this.filterList = new org.apache.hadoop.hbase.filter.FilterList(" + NL + "                        org.apache.hadoop.hbase.filter.FilterList.Operator.";
  protected final String TEXT_65 = ");" + NL + "                org.apache.hadoop.hbase.filter.Filter filter = null;";
  protected final String TEXT_66 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_67 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_68 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_69 = "," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_70 = "));";
  protected final String TEXT_71 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_72 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_73 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_74 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_75 = "));";
  protected final String TEXT_76 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_77 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_78 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_79 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_80 = "));";
  protected final String TEXT_81 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_82 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_83 = ")));";
  protected final String TEXT_84 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_85 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_86 = "));";
  protected final String TEXT_87 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_88 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_89 = "));";
  protected final String TEXT_90 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_91 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_92 = ")));";
  protected final String TEXT_93 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_94 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_95 = "));";
  protected final String TEXT_96 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_97 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_98 = "));";
  protected final String TEXT_99 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_100 = "));";
  protected final String TEXT_101 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnPrefixFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_102 = "));";
  protected final String TEXT_103 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_104 = "));";
  protected final String TEXT_105 = NL + "                        if (";
  protected final String TEXT_106 = "!=null && !\"\".equals(";
  protected final String TEXT_107 = ")) {" + NL + "                            multipleValues = ";
  protected final String TEXT_108 = ".split(\",\");" + NL + "                            multipleBytesValues = new byte [multipleValues.length] [];" + NL + "                            for (int i = 0; i < multipleValues.length; i++) {" + NL + "                                multipleBytesValues[i] = org.apache.hadoop.hbase.util.Bytes.toBytes(multipleValues[i]);" + NL + "                            }" + NL + "                            filter =  new org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter(multipleBytesValues);" + NL + "                        }";
  protected final String TEXT_109 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_110 = "));";
  protected final String TEXT_111 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnRangeFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_112 = ".split(\",\")[0])," + NL + "                                true," + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_113 = ".split(\",\")[1])," + NL + "                                true);";
  protected final String TEXT_114 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_115 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_116 = ")));";
  protected final String TEXT_117 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_118 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_119 = "));";
  protected final String TEXT_120 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_121 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_122 = "));";
  protected final String TEXT_123 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_124 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_125 = ")));";
  protected final String TEXT_126 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_127 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_128 = "));";
  protected final String TEXT_129 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_130 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_131 = "));";
  protected final String TEXT_132 = NL + "                    this.filterList.addFilter(filter);";
  protected final String TEXT_133 = NL + "        }" + NL + "" + NL + "        private void setFilter(org.apache.hadoop.hbase.client.Scan scan){" + NL + "            if (this.filterList != null) {" + NL + "                scan.setFilter(this.filterList);" + NL + "                scan.setCacheBlocks(false);" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public void restart(byte[] firstRow) throws IOException {" + NL + "            org.apache.hadoop.hbase.client.Scan scan;" + NL + "            if ((endRow != null) && (endRow.length > 0)) {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow, endRow);" + NL + "            } else {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow);" + NL + "            }" + NL + "            for (byte[] column : inputColumns) {" + NL + "                byte[][] fq = org.apache.hadoop.hbase.KeyValue.parseColumn(column);" + NL + "                if (fq.length > 1 && fq[1] != null && fq[1].length > 0) {" + NL + "                    scan.addColumn(fq[0], fq[1]);" + NL + "                } else {" + NL + "                    scan.addFamily(fq[0]);" + NL + "                }" + NL + "            }" + NL + "            setFilter(scan);" + NL + "            this.scanner = hTable.getScanner(scan);" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            this.scanner.close();" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_134 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_135 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "            // This should be the ordinal tuple in the range;" + NL + "            // not clear how to calculate..." + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            // Depends on the total number of tuples and getPos" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_136 = " value) throws IOException {";
  protected final String TEXT_137 = NL + "        }" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.Result getNextResult() throws IOException {";
  protected final String TEXT_138 = NL + "                org.apache.hadoop.hbase.client.Result result = this.scanner.next();";
  protected final String TEXT_139 = NL + "                org.apache.hadoop.hbase.client.Result result;" + NL + "                try {" + NL + "                    result = this.scanner.next();" + NL + "                } catch (IOException e) {" + NL + "                    if (lastSuccessfulRow == null) {" + NL + "                        restart(startRow);" + NL + "                    } else {" + NL + "                        restart(lastSuccessfulRow);" + NL + "                        this.scanner.next();" + NL + "                    }" + NL + "                    result = this.scanner.next();" + NL + "                }";
  protected final String TEXT_140 = NL + "            return (result == null || result.size() == 0) ? null : result;" + NL + "        }" + NL;
  protected final String TEXT_141 = NL + "    }" + NL + "" + NL + "    protected static class HBaseTableSplit implements InputSplit, Comparable<HBaseTableSplit> {" + NL + "" + NL + "        private byte[] m_tableName;" + NL + "" + NL + "        private byte[] m_startRow;" + NL + "" + NL + "        private byte[] m_endRow;" + NL + "" + NL + "        private String m_regionLocation;" + NL + "" + NL + "        public HBaseTableSplit() {" + NL + "            this(org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, \"\");" + NL + "        }" + NL + "" + NL + "        public HBaseTableSplit(byte[] tableName, byte[] startRow, byte[] endRow, final String location) {" + NL + "            this.m_tableName = tableName;" + NL + "            this.m_startRow = startRow;" + NL + "            this.m_endRow = endRow;" + NL + "            this.m_regionLocation = location;" + NL + "        }" + NL + "" + NL + "        public byte[] getTableName() {" + NL + "            return this.m_tableName;" + NL + "        }" + NL + "" + NL + "        public byte[] getStartRow() {" + NL + "            return this.m_startRow;" + NL + "        }" + NL + "" + NL + "        public byte[] getEndRow() {" + NL + "            return this.m_endRow;" + NL + "        }" + NL + "" + NL + "        public String getRegionLocation() {" + NL + "            return this.m_regionLocation;" + NL + "        }" + NL + "" + NL + "        public String[] getLocations() {" + NL + "            return new String[] { this.m_regionLocation };" + NL + "        }" + NL + "" + NL + "        public long getLength() {" + NL + "            // Not clear how to obtain this... seems to be used only for sorting splits" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public void readFields(DataInput in) throws IOException {" + NL + "            this.m_tableName = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_startRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_endRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_regionLocation = org.apache.hadoop.hbase.util.Bytes.toString(org.apache.hadoop.hbase.util.Bytes.readByteArray(in));" + NL + "        }" + NL + "" + NL + "        public void write(DataOutput out) throws IOException {" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_tableName);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_startRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_endRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, org.apache.hadoop.hbase.util.Bytes.toBytes(this.m_regionLocation));" + NL + "        }" + NL + "" + NL + "        public String toString() {" + NL + "            return m_regionLocation + \":\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_startRow) + \",\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_endRow);" + NL + "        }" + NL + "" + NL + "        public int compareTo(HBaseTableSplit o) {" + NL + "            return org.apache.hadoop.hbase.util.Bytes.compareTo(getStartRow(), o.getStartRow());" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_142 = NL + "        org.apache.hadoop.hbase.client.Result result;" + NL + "        while (null != (result = getNextResult()) {" + NL + "            try {" + NL + "                if (validateResult(result, value)));" + NL + "                    continue;" + NL + "            } catch (Exception e) {" + NL + "                value.invalidInputLine = record.toString();" + NL + "                value.errorCode = e.toString();" + NL + "                return true;" + NL + "            }" + NL + "        }" + NL + "        return false;";
  protected final String TEXT_143 = NL + "        public boolean validateResult(org.apache.hadoop.hbase.client.Result result,";
  protected final String TEXT_144 = NL + "                ";
  protected final String TEXT_145 = " value) throws IOException {" + NL + "            org.apache.hadoop.hbase.io.ImmutableBytesWritable rowKey = new org.apache.hadoop.hbase.io.ImmutableBytesWritable();" + NL + "            rowKey.set(result.getRow());" + NL + "            lastSuccessfulRow = rowKey.get();" + NL + "" + NL + "            byte[] rowResult = null;" + NL + "            String temp = null;";
  protected final String TEXT_146 = NL + "                rowResult = result.getValue(" + NL + "                        org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_147 = ")," + NL + "                        org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_148 = "\"));" + NL + "                temp = org.apache.hadoop.hbase.util.Bytes.toString(rowResult);" + NL + "" + NL + "                if (temp != null && temp.length() > 0) {";
  protected final String TEXT_149 = NL + "                        ";
  protected final String TEXT_150 = " = temp.toString();";
  protected final String TEXT_151 = NL + "                        ";
  protected final String TEXT_152 = " = rowResult;";
  protected final String TEXT_153 = NL + "                        ";
  protected final String TEXT_154 = " = BigDataParserUtils.parseTo_Date(temp, ";
  protected final String TEXT_155 = ");";
  protected final String TEXT_156 = NL + "                        ";
  protected final String TEXT_157 = " = org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_158 = NL + "                        ";
  protected final String TEXT_159 = " = (char)org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_160 = NL + "                        ";
  protected final String TEXT_161 = " = org.apache.hadoop.hbase.util.Bytes.to";
  protected final String TEXT_162 = "(rowResult);";
  protected final String TEXT_163 = NL + "                        ";
  protected final String TEXT_164 = " = BigDataParserUtils.parseTo_";
  protected final String TEXT_165 = "(temp);";
  protected final String TEXT_166 = NL + "                } else {";
  protected final String TEXT_167 = NL + "                        ";
  protected final String TEXT_168 = " = ";
  protected final String TEXT_169 = ";";
  protected final String TEXT_170 = NL + "                        ";
  protected final String TEXT_171 = " = null;";
  protected final String TEXT_172 = NL + "                        throw new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_173 = "' in '";
  protected final String TEXT_174 = "' connection, value is invalid or this column should be nullable or have a default value.\");";
  protected final String TEXT_175 = NL + "                }";
  protected final String TEXT_176 = NL + "            return true;" + NL + "        }";
  protected final String TEXT_177 = NL;
  protected final String TEXT_178 = NL + NL + "public static class ";
  protected final String TEXT_179 = "InputFormat implements InputFormat<NullWritable, ";
  protected final String TEXT_180 = ">," + NL + "        JobConfigurable {" + NL + "" + NL + "    ContextProperties context;" + NL + "    private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "    private byte[][] inputColumns;" + NL + "" + NL + "    public void configure(JobConf job) {" + NL + "        context = new ContextProperties(job);" + NL + "        JobConf hbaseJob = new JobConf(job);" + NL + "        hbaseJob.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_181 = ");" + NL + "        hbaseJob.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_182 = ");" + NL;
  protected final String TEXT_183 = NL + "            hbaseJob.set(\"zookeeper.znode.parent\",";
  protected final String TEXT_184 = ");";
  protected final String TEXT_185 = NL + "            hbaseJob.set(\"hbase.table.namespace.mappings\", ";
  protected final String TEXT_186 = ");";
  protected final String TEXT_187 = NL + "            hbaseJob.set(";
  protected final String TEXT_188 = ", ";
  protected final String TEXT_189 = ");";
  protected final String TEXT_190 = NL + NL + "        try {" + NL + "            this.hTable = new org.apache.hadoop.hbase.client.HTable(hbaseJob, ";
  protected final String TEXT_191 = ");" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(e);" + NL + "        }" + NL;
  protected final String TEXT_192 = NL + "        this.inputColumns = new byte[";
  protected final String TEXT_193 = "][];";
  protected final String TEXT_194 = NL + "            this.inputColumns[";
  protected final String TEXT_195 = "] = org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_196 = NL + "                    ";
  protected final String TEXT_197 = NL + "                    + org.apache.hadoop.hbase.KeyValue.COLUMN_FAMILY_DELIMITER" + NL + "                    + \"";
  protected final String TEXT_198 = "\");";
  protected final String TEXT_199 = NL + "    }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {" + NL + "        if (this.hTable == null) {" + NL + "            throw new IOException(\"No table was provided\");" + NL + "        }" + NL + "        byte[][] startKeys = this.hTable.getStartKeys();" + NL + "        if (startKeys == null || startKeys.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one region\");" + NL + "        }" + NL + "        if (this.inputColumns == null || this.inputColumns.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one column\");" + NL + "        }" + NL + "        int realNumSplits = numSplits > startKeys.length ? startKeys.length : numSplits;" + NL + "        InputSplit[] splits = new InputSplit[realNumSplits];" + NL + "        int middle = startKeys.length / realNumSplits;" + NL + "        int startPos = 0;" + NL + "        for (int i = 0; i < realNumSplits; i++) {" + NL + "            int lastPos = startPos + middle;" + NL + "            lastPos = startKeys.length % realNumSplits > i ? lastPos + 1 : lastPos;" + NL + "            String regionLocation = hTable.getRegionLocation(startKeys[startPos]).getHostname();" + NL + "            splits[i] = new HBaseTableSplit(this.hTable.getTableName(), startKeys[startPos]," + NL + "                    ((i + 1) < realNumSplits) ? startKeys[lastPos] : org.apache.hadoop.hbase.HConstants.EMPTY_START_ROW, regionLocation);" + NL + "            startPos = lastPos;" + NL + "        }" + NL + "        return splits;" + NL + "    }" + NL + "" + NL + "    public RecordReader getRecordReader(InputSplit inputsplit, JobConf job, Reporter reporter) throws IOException {" + NL + "       return new HBaseTableRecordReader((HBaseTableSplit) inputsplit, job, this.hTable, this.inputColumns);" + NL + "    }" + NL + "" + NL + "    protected static class HBaseTableRecordReader implements RecordReader<NullWritable, ";
  protected final String TEXT_200 = "> {" + NL + "" + NL + "        private JobConf job;" + NL + "" + NL + "        private HBaseTableSplit split;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.ResultScanner scanner;" + NL + "" + NL + "        private byte[] startRow;" + NL + "" + NL + "        private byte[] endRow;" + NL + "" + NL + "        private byte[] lastSuccessfulRow;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "" + NL + "        private byte[][] inputColumns;" + NL + "" + NL + "        private org.apache.hadoop.hbase.filter.FilterList filterList;" + NL + "" + NL + "        ContextProperties context;" + NL + "" + NL + "        protected HBaseTableRecordReader(HBaseTableSplit split, JobConf job, org.apache.hadoop.hbase.client.HTable hTable, byte[][] inputColumns) throws IOException {" + NL + "            context = new ContextProperties(job);" + NL + "            this.hTable = hTable;" + NL + "            this.inputColumns = inputColumns;" + NL + "            this.split = split;" + NL + "            this.job = job;" + NL + "            this.startRow = split.getStartRow();" + NL + "            this.endRow = split.getEndRow();" + NL + "            initFilter();" + NL + "            restart(startRow);" + NL + "        }" + NL + "" + NL + "        private void initFilter(){";
  protected final String TEXT_201 = NL + "                    String [] multipleValues = null;" + NL + "                    byte [][] multipleBytesValues = null;";
  protected final String TEXT_202 = NL + "                this.filterList = new org.apache.hadoop.hbase.filter.FilterList(" + NL + "                        org.apache.hadoop.hbase.filter.FilterList.Operator.";
  protected final String TEXT_203 = ");" + NL + "                org.apache.hadoop.hbase.filter.Filter filter = null;";
  protected final String TEXT_204 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_205 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_206 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_207 = "," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_208 = "));";
  protected final String TEXT_209 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_210 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_211 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_212 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_213 = "));";
  protected final String TEXT_214 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_215 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_216 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_217 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_218 = "));";
  protected final String TEXT_219 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_220 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_221 = ")));";
  protected final String TEXT_222 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_223 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_224 = "));";
  protected final String TEXT_225 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_226 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_227 = "));";
  protected final String TEXT_228 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_229 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_230 = ")));";
  protected final String TEXT_231 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_232 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_233 = "));";
  protected final String TEXT_234 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_235 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_236 = "));";
  protected final String TEXT_237 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_238 = "));";
  protected final String TEXT_239 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnPrefixFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_240 = "));";
  protected final String TEXT_241 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_242 = "));";
  protected final String TEXT_243 = NL + "                        if (";
  protected final String TEXT_244 = "!=null && !\"\".equals(";
  protected final String TEXT_245 = ")) {" + NL + "                            multipleValues = ";
  protected final String TEXT_246 = ".split(\",\");" + NL + "                            multipleBytesValues = new byte [multipleValues.length] [];" + NL + "                            for (int i = 0; i < multipleValues.length; i++) {" + NL + "                                multipleBytesValues[i] = org.apache.hadoop.hbase.util.Bytes.toBytes(multipleValues[i]);" + NL + "                            }" + NL + "                            filter =  new org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter(multipleBytesValues);" + NL + "                        }";
  protected final String TEXT_247 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_248 = "));";
  protected final String TEXT_249 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnRangeFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_250 = ".split(\",\")[0])," + NL + "                                true," + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_251 = ".split(\",\")[1])," + NL + "                                true);";
  protected final String TEXT_252 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_253 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_254 = ")));";
  protected final String TEXT_255 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_256 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_257 = "));";
  protected final String TEXT_258 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_259 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_260 = "));";
  protected final String TEXT_261 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_262 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_263 = ")));";
  protected final String TEXT_264 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_265 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_266 = "));";
  protected final String TEXT_267 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_268 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_269 = "));";
  protected final String TEXT_270 = NL + "                    this.filterList.addFilter(filter);";
  protected final String TEXT_271 = NL + "        }" + NL + "" + NL + "        private void setFilter(org.apache.hadoop.hbase.client.Scan scan){" + NL + "            if (this.filterList != null) {" + NL + "                scan.setFilter(this.filterList);" + NL + "                scan.setCacheBlocks(false);" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public void restart(byte[] firstRow) throws IOException {" + NL + "            org.apache.hadoop.hbase.client.Scan scan;" + NL + "            if ((endRow != null) && (endRow.length > 0)) {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow, endRow);" + NL + "            } else {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow);" + NL + "            }" + NL + "            for (byte[] column : inputColumns) {" + NL + "                byte[][] fq = org.apache.hadoop.hbase.KeyValue.parseColumn(column);" + NL + "                if (fq.length > 1 && fq[1] != null && fq[1].length > 0) {" + NL + "                    scan.addColumn(fq[0], fq[1]);" + NL + "                } else {" + NL + "                    scan.addFamily(fq[0]);" + NL + "                }" + NL + "            }" + NL + "            setFilter(scan);" + NL + "            this.scanner = hTable.getScanner(scan);" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            this.scanner.close();" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_272 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_273 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "            // This should be the ordinal tuple in the range;" + NL + "            // not clear how to calculate..." + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            // Depends on the total number of tuples and getPos" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_274 = " value) throws IOException {";
  protected final String TEXT_275 = NL + "        }" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.Result getNextResult() throws IOException {";
  protected final String TEXT_276 = NL + "                org.apache.hadoop.hbase.client.Result result = this.scanner.next();";
  protected final String TEXT_277 = NL + "                org.apache.hadoop.hbase.client.Result result;" + NL + "                try {" + NL + "                    result = this.scanner.next();" + NL + "                } catch (IOException e) {" + NL + "                    if (lastSuccessfulRow == null) {" + NL + "                        restart(startRow);" + NL + "                    } else {" + NL + "                        restart(lastSuccessfulRow);" + NL + "                        this.scanner.next();" + NL + "                    }" + NL + "                    result = this.scanner.next();" + NL + "                }";
  protected final String TEXT_278 = NL + "            return (result == null || result.size() == 0) ? null : result;" + NL + "        }" + NL;
  protected final String TEXT_279 = NL + "    }" + NL + "" + NL + "    protected static class HBaseTableSplit implements InputSplit, Comparable<HBaseTableSplit> {" + NL + "" + NL + "        private byte[] m_tableName;" + NL + "" + NL + "        private byte[] m_startRow;" + NL + "" + NL + "        private byte[] m_endRow;" + NL + "" + NL + "        private String m_regionLocation;" + NL + "" + NL + "        public HBaseTableSplit() {" + NL + "            this(org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, \"\");" + NL + "        }" + NL + "" + NL + "        public HBaseTableSplit(byte[] tableName, byte[] startRow, byte[] endRow, final String location) {" + NL + "            this.m_tableName = tableName;" + NL + "            this.m_startRow = startRow;" + NL + "            this.m_endRow = endRow;" + NL + "            this.m_regionLocation = location;" + NL + "        }" + NL + "" + NL + "        public byte[] getTableName() {" + NL + "            return this.m_tableName;" + NL + "        }" + NL + "" + NL + "        public byte[] getStartRow() {" + NL + "            return this.m_startRow;" + NL + "        }" + NL + "" + NL + "        public byte[] getEndRow() {" + NL + "            return this.m_endRow;" + NL + "        }" + NL + "" + NL + "        public String getRegionLocation() {" + NL + "            return this.m_regionLocation;" + NL + "        }" + NL + "" + NL + "        public String[] getLocations() {" + NL + "            return new String[] { this.m_regionLocation };" + NL + "        }" + NL + "" + NL + "        public long getLength() {" + NL + "            // Not clear how to obtain this... seems to be used only for sorting splits" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public void readFields(DataInput in) throws IOException {" + NL + "            this.m_tableName = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_startRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_endRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_regionLocation = org.apache.hadoop.hbase.util.Bytes.toString(org.apache.hadoop.hbase.util.Bytes.readByteArray(in));" + NL + "        }" + NL + "" + NL + "        public void write(DataOutput out) throws IOException {" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_tableName);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_startRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_endRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, org.apache.hadoop.hbase.util.Bytes.toBytes(this.m_regionLocation));" + NL + "        }" + NL + "" + NL + "        public String toString() {" + NL + "            return m_regionLocation + \":\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_startRow) + \",\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_endRow);" + NL + "        }" + NL + "" + NL + "        public int compareTo(HBaseTableSplit o) {" + NL + "            return org.apache.hadoop.hbase.util.Bytes.compareTo(getStartRow(), o.getStartRow());" + NL + "        }" + NL + "    }" + NL + "}";
  protected final String TEXT_280 = NL + "        org.apache.hadoop.hbase.client.Result result;" + NL + "        try {" + NL + "            try {" + NL + "                result = this.scanner.next();" + NL + "            } catch (IOException e) {" + NL + "                if (lastSuccessfulRow == null) {" + NL + "                    restart(startRow);" + NL + "                } else {" + NL + "                    restart(lastSuccessfulRow);" + NL + "                    this.scanner.next();" + NL + "                }" + NL + "                result = this.scanner.next();" + NL + "            }" + NL + "" + NL + "            if (result != null && result.size() > 0) {" + NL + "                value.set(result);" + NL + "                org.apache.hadoop.hbase.io.ImmutableBytesWritable rowKey = new org.apache.hadoop.hbase.io.ImmutableBytesWritable();" + NL + "                rowKey.set(result.getRow());" + NL + "                lastSuccessfulRow = rowKey.get();" + NL + "                return true;" + NL + "            }" + NL + "            return false;" + NL + "        } catch (IOException ioe) {" + NL + "            throw ioe;" + NL + "        }";
  protected final String TEXT_281 = NL + "public static class ";
  protected final String TEXT_282 = " extends org.apache.hadoop.io.ObjectWritable {" + NL + "}" + NL;
  protected final String TEXT_283 = NL + NL + "public static class ";
  protected final String TEXT_284 = "InputFormat implements InputFormat<NullWritable, ";
  protected final String TEXT_285 = ">," + NL + "        JobConfigurable {" + NL + "" + NL + "    ContextProperties context;" + NL + "    private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "    private byte[][] inputColumns;" + NL + "" + NL + "    public void configure(JobConf job) {" + NL + "        context = new ContextProperties(job);" + NL + "        JobConf hbaseJob = new JobConf(job);" + NL + "        hbaseJob.set(\"hbase.zookeeper.quorum\", ";
  protected final String TEXT_286 = ");" + NL + "        hbaseJob.set(\"hbase.zookeeper.property.clientPort\", ";
  protected final String TEXT_287 = ");" + NL;
  protected final String TEXT_288 = NL + "            hbaseJob.set(\"zookeeper.znode.parent\",";
  protected final String TEXT_289 = ");";
  protected final String TEXT_290 = NL + "            hbaseJob.set(\"hbase.table.namespace.mappings\", ";
  protected final String TEXT_291 = ");";
  protected final String TEXT_292 = NL + "            hbaseJob.set(";
  protected final String TEXT_293 = ", ";
  protected final String TEXT_294 = ");";
  protected final String TEXT_295 = NL + NL + "        try {" + NL + "            this.hTable = new org.apache.hadoop.hbase.client.HTable(hbaseJob, ";
  protected final String TEXT_296 = ");" + NL + "        } catch (Exception e) {" + NL + "            throw new RuntimeException(e);" + NL + "        }" + NL;
  protected final String TEXT_297 = NL + "        this.inputColumns = new byte[";
  protected final String TEXT_298 = "][];";
  protected final String TEXT_299 = NL + "            this.inputColumns[";
  protected final String TEXT_300 = "] = org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_301 = NL + "                    ";
  protected final String TEXT_302 = NL + "                    + org.apache.hadoop.hbase.KeyValue.COLUMN_FAMILY_DELIMITER" + NL + "                    + \"";
  protected final String TEXT_303 = "\");";
  protected final String TEXT_304 = NL + "    }" + NL + "" + NL + "    public InputSplit[] getSplits(JobConf job, int numSplits) throws IOException {" + NL + "        if (this.hTable == null) {" + NL + "            throw new IOException(\"No table was provided\");" + NL + "        }" + NL + "        byte[][] startKeys = this.hTable.getStartKeys();" + NL + "        if (startKeys == null || startKeys.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one region\");" + NL + "        }" + NL + "        if (this.inputColumns == null || this.inputColumns.length == 0) {" + NL + "            throw new IOException(\"Expecting at least one column\");" + NL + "        }" + NL + "        int realNumSplits = numSplits > startKeys.length ? startKeys.length : numSplits;" + NL + "        InputSplit[] splits = new InputSplit[realNumSplits];" + NL + "        int middle = startKeys.length / realNumSplits;" + NL + "        int startPos = 0;" + NL + "        for (int i = 0; i < realNumSplits; i++) {" + NL + "            int lastPos = startPos + middle;" + NL + "            lastPos = startKeys.length % realNumSplits > i ? lastPos + 1 : lastPos;" + NL + "            String regionLocation = hTable.getRegionLocation(startKeys[startPos]).getHostname();" + NL + "            splits[i] = new HBaseTableSplit(this.hTable.getTableName(), startKeys[startPos]," + NL + "                    ((i + 1) < realNumSplits) ? startKeys[lastPos] : org.apache.hadoop.hbase.HConstants.EMPTY_START_ROW, regionLocation);" + NL + "            startPos = lastPos;" + NL + "        }" + NL + "        return splits;" + NL + "    }" + NL + "" + NL + "    public RecordReader getRecordReader(InputSplit inputsplit, JobConf job, Reporter reporter) throws IOException {" + NL + "       return new HBaseTableRecordReader((HBaseTableSplit) inputsplit, job, this.hTable, this.inputColumns);" + NL + "    }" + NL + "" + NL + "    protected static class HBaseTableRecordReader implements RecordReader<NullWritable, ";
  protected final String TEXT_305 = "> {" + NL + "" + NL + "        private JobConf job;" + NL + "" + NL + "        private HBaseTableSplit split;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.ResultScanner scanner;" + NL + "" + NL + "        private byte[] startRow;" + NL + "" + NL + "        private byte[] endRow;" + NL + "" + NL + "        private byte[] lastSuccessfulRow;" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.HTable hTable;" + NL + "" + NL + "        private byte[][] inputColumns;" + NL + "" + NL + "        private org.apache.hadoop.hbase.filter.FilterList filterList;" + NL + "" + NL + "        ContextProperties context;" + NL + "" + NL + "        protected HBaseTableRecordReader(HBaseTableSplit split, JobConf job, org.apache.hadoop.hbase.client.HTable hTable, byte[][] inputColumns) throws IOException {" + NL + "            context = new ContextProperties(job);" + NL + "            this.hTable = hTable;" + NL + "            this.inputColumns = inputColumns;" + NL + "            this.split = split;" + NL + "            this.job = job;" + NL + "            this.startRow = split.getStartRow();" + NL + "            this.endRow = split.getEndRow();" + NL + "            initFilter();" + NL + "            restart(startRow);" + NL + "        }" + NL + "" + NL + "        private void initFilter(){";
  protected final String TEXT_306 = NL + "                    String [] multipleValues = null;" + NL + "                    byte [][] multipleBytesValues = null;";
  protected final String TEXT_307 = NL + "                this.filterList = new org.apache.hadoop.hbase.filter.FilterList(" + NL + "                        org.apache.hadoop.hbase.filter.FilterList.Operator.";
  protected final String TEXT_308 = ");" + NL + "                org.apache.hadoop.hbase.filter.Filter filter = null;";
  protected final String TEXT_309 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_310 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_311 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_312 = "," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_313 = "));";
  protected final String TEXT_314 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_315 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_316 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_317 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_318 = "));";
  protected final String TEXT_319 = NL + "                            filter = new org.apache.hadoop.hbase.filter.SingleColumnValueFilter(" + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_320 = ")," + NL + "                                    org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_321 = ")," + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_322 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_323 = "));";
  protected final String TEXT_324 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_325 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_326 = ")));";
  protected final String TEXT_327 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_328 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_329 = "));";
  protected final String TEXT_330 = NL + "                            filter = new org.apache.hadoop.hbase.filter.FamilyFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_331 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_332 = "));";
  protected final String TEXT_333 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_334 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_335 = ")));";
  protected final String TEXT_336 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_337 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_338 = "));";
  protected final String TEXT_339 = NL + "                            filter = new org.apache.hadoop.hbase.filter.QualifierFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_340 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_341 = "));";
  protected final String TEXT_342 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_343 = "));";
  protected final String TEXT_344 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnPrefixFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_345 = "));";
  protected final String TEXT_346 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_347 = "));";
  protected final String TEXT_348 = NL + "                        if (";
  protected final String TEXT_349 = "!=null && !\"\".equals(";
  protected final String TEXT_350 = ")) {" + NL + "                            multipleValues = ";
  protected final String TEXT_351 = ".split(\",\");" + NL + "                            multipleBytesValues = new byte [multipleValues.length] [];" + NL + "                            for (int i = 0; i < multipleValues.length; i++) {" + NL + "                                multipleBytesValues[i] = org.apache.hadoop.hbase.util.Bytes.toBytes(multipleValues[i]);" + NL + "                            }" + NL + "                            filter =  new org.apache.hadoop.hbase.filter.MultipleColumnPrefixFilter(multipleBytesValues);" + NL + "                        }";
  protected final String TEXT_352 = NL + "                            scan.addFamily(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_353 = "));";
  protected final String TEXT_354 = NL + "                        filter = new org.apache.hadoop.hbase.filter.ColumnRangeFilter(" + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_355 = ".split(\",\")[0])," + NL + "                                true," + NL + "                                org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_356 = ".split(\",\")[1])," + NL + "                                true);";
  protected final String TEXT_357 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_358 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_359 = ")));";
  protected final String TEXT_360 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_361 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_362 = "));";
  protected final String TEXT_363 = NL + "                            filter = new org.apache.hadoop.hbase.filter.RowFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_364 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_365 = "));";
  protected final String TEXT_366 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_367 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.BinaryComparator(" + NL + "                                            org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_368 = ")));";
  protected final String TEXT_369 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_370 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.RegexStringComparator(";
  protected final String TEXT_371 = "));";
  protected final String TEXT_372 = NL + "                            filter = new org.apache.hadoop.hbase.filter.ValueFilter(" + NL + "                                    org.apache.hadoop.hbase.filter.CompareFilter.CompareOp.";
  protected final String TEXT_373 = "," + NL + "                                    new org.apache.hadoop.hbase.filter.SubstringComparator(";
  protected final String TEXT_374 = "));";
  protected final String TEXT_375 = NL + "                    this.filterList.addFilter(filter);";
  protected final String TEXT_376 = NL + "        }" + NL + "" + NL + "        private void setFilter(org.apache.hadoop.hbase.client.Scan scan){" + NL + "            if (this.filterList != null) {" + NL + "                scan.setFilter(this.filterList);" + NL + "                scan.setCacheBlocks(false);" + NL + "            }" + NL + "        }" + NL + "" + NL + "        public void restart(byte[] firstRow) throws IOException {" + NL + "            org.apache.hadoop.hbase.client.Scan scan;" + NL + "            if ((endRow != null) && (endRow.length > 0)) {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow, endRow);" + NL + "            } else {" + NL + "                scan = new org.apache.hadoop.hbase.client.Scan(firstRow);" + NL + "            }" + NL + "            for (byte[] column : inputColumns) {" + NL + "                byte[][] fq = org.apache.hadoop.hbase.KeyValue.parseColumn(column);" + NL + "                if (fq.length > 1 && fq[1] != null && fq[1].length > 0) {" + NL + "                    scan.addColumn(fq[0], fq[1]);" + NL + "                } else {" + NL + "                    scan.addFamily(fq[0]);" + NL + "                }" + NL + "            }" + NL + "            setFilter(scan);" + NL + "            this.scanner = hTable.getScanner(scan);" + NL + "        }" + NL + "" + NL + "        public void close() throws IOException {" + NL + "            this.scanner.close();" + NL + "        }" + NL + "" + NL + "        public NullWritable createKey() {" + NL + "            return NullWritable.get();" + NL + "        }" + NL + "" + NL + "        public ";
  protected final String TEXT_377 = " createValue() {" + NL + "            return new ";
  protected final String TEXT_378 = "();" + NL + "        }" + NL + "" + NL + "        public long getPos() throws IOException {" + NL + "            // This should be the ordinal tuple in the range;" + NL + "            // not clear how to calculate..." + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public float getProgress() throws IOException {" + NL + "            // Depends on the total number of tuples and getPos" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public boolean next(NullWritable key, ";
  protected final String TEXT_379 = " value) throws IOException {";
  protected final String TEXT_380 = NL + "        }" + NL + "" + NL + "        private org.apache.hadoop.hbase.client.Result getNextResult() throws IOException {";
  protected final String TEXT_381 = NL + "                org.apache.hadoop.hbase.client.Result result = this.scanner.next();";
  protected final String TEXT_382 = NL + "                org.apache.hadoop.hbase.client.Result result;" + NL + "                try {" + NL + "                    result = this.scanner.next();" + NL + "                } catch (IOException e) {" + NL + "                    if (lastSuccessfulRow == null) {" + NL + "                        restart(startRow);" + NL + "                    } else {" + NL + "                        restart(lastSuccessfulRow);" + NL + "                        this.scanner.next();" + NL + "                    }" + NL + "                    result = this.scanner.next();" + NL + "                }";
  protected final String TEXT_383 = NL + "            return (result == null || result.size() == 0) ? null : result;" + NL + "        }" + NL;
  protected final String TEXT_384 = NL + "    }" + NL + "" + NL + "    protected static class HBaseTableSplit implements InputSplit, Comparable<HBaseTableSplit> {" + NL + "" + NL + "        private byte[] m_tableName;" + NL + "" + NL + "        private byte[] m_startRow;" + NL + "" + NL + "        private byte[] m_endRow;" + NL + "" + NL + "        private String m_regionLocation;" + NL + "" + NL + "        public HBaseTableSplit() {" + NL + "            this(org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, org.apache.hadoop.hbase.HConstants.EMPTY_BYTE_ARRAY, \"\");" + NL + "        }" + NL + "" + NL + "        public HBaseTableSplit(byte[] tableName, byte[] startRow, byte[] endRow, final String location) {" + NL + "            this.m_tableName = tableName;" + NL + "            this.m_startRow = startRow;" + NL + "            this.m_endRow = endRow;" + NL + "            this.m_regionLocation = location;" + NL + "        }" + NL + "" + NL + "        public byte[] getTableName() {" + NL + "            return this.m_tableName;" + NL + "        }" + NL + "" + NL + "        public byte[] getStartRow() {" + NL + "            return this.m_startRow;" + NL + "        }" + NL + "" + NL + "        public byte[] getEndRow() {" + NL + "            return this.m_endRow;" + NL + "        }" + NL + "" + NL + "        public String getRegionLocation() {" + NL + "            return this.m_regionLocation;" + NL + "        }" + NL + "" + NL + "        public String[] getLocations() {" + NL + "            return new String[] { this.m_regionLocation };" + NL + "        }" + NL + "" + NL + "        public long getLength() {" + NL + "            // Not clear how to obtain this... seems to be used only for sorting splits" + NL + "            return 0;" + NL + "        }" + NL + "" + NL + "        public void readFields(DataInput in) throws IOException {" + NL + "            this.m_tableName = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_startRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_endRow = org.apache.hadoop.hbase.util.Bytes.readByteArray(in);" + NL + "            this.m_regionLocation = org.apache.hadoop.hbase.util.Bytes.toString(org.apache.hadoop.hbase.util.Bytes.readByteArray(in));" + NL + "        }" + NL + "" + NL + "        public void write(DataOutput out) throws IOException {" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_tableName);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_startRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, this.m_endRow);" + NL + "            org.apache.hadoop.hbase.util.Bytes.writeByteArray(out, org.apache.hadoop.hbase.util.Bytes.toBytes(this.m_regionLocation));" + NL + "        }" + NL + "" + NL + "        public String toString() {" + NL + "            return m_regionLocation + \":\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_startRow) + \",\" + org.apache.hadoop.hbase.util.Bytes.toStringBinary(m_endRow);" + NL + "        }" + NL + "" + NL + "        public int compareTo(HBaseTableSplit o) {" + NL + "            return org.apache.hadoop.hbase.util.Bytes.compareTo(getStartRow(), o.getStartRow());" + NL + "        }" + NL + "    }" + NL + "}" + NL + "" + NL + "" + NL + "public static class ";
  protected final String TEXT_385 = "_InputMapper extends MapReduceBase" + NL + "        implements Mapper<NullWritable, ";
  protected final String TEXT_386 = ", NullWritable, WritableComparable>{" + NL + "    private ContextProperties context;" + NL + "    private GlobalVar globalMap;" + NL + "    public MultipleOutputs mos_";
  protected final String TEXT_387 = ";" + NL + "    private ";
  protected final String TEXT_388 = "Struct ";
  protected final String TEXT_389 = " = null;" + NL + "    private ";
  protected final String TEXT_390 = "Struct ";
  protected final String TEXT_391 = " = null;" + NL + "" + NL + "    public void configure(JobConf job_";
  protected final String TEXT_392 = "){" + NL + "        context = new ContextProperties(job_";
  protected final String TEXT_393 = ");" + NL + "        globalMap = new GlobalVar(job_";
  protected final String TEXT_394 = ");" + NL + "        mos_";
  protected final String TEXT_395 = " = new MultipleOutputs(job_";
  protected final String TEXT_396 = ");" + NL;
  protected final String TEXT_397 = NL + "        ";
  protected final String TEXT_398 = " = new ";
  protected final String TEXT_399 = "Struct();";
  protected final String TEXT_400 = NL + "        ";
  protected final String TEXT_401 = " = new ";
  protected final String TEXT_402 = "Struct();" + NL;
  protected final String TEXT_403 = NL + "                try {" + NL + "                    FileSystem fs = FileSystem.get(job_";
  protected final String TEXT_404 = ");" + NL + "                    Path path = new Path(" + NL + "                            \"/tmp/";
  protected final String TEXT_405 = "\"" + NL + "                            + \"/tMROutput_";
  protected final String TEXT_406 = "\"" + NL + "                            + \"/";
  protected final String TEXT_407 = "\");" + NL + "                    fs.mkdirs(path);" + NL + "                } catch (Exception ex_";
  protected final String TEXT_408 = ") {" + NL + "                    throw new RuntimeException(ex_";
  protected final String TEXT_409 = ".getMessage());" + NL + "                }";
  protected final String TEXT_410 = NL + "    }" + NL + "" + NL + "    public void map(NullWritable key_";
  protected final String TEXT_411 = ", ";
  protected final String TEXT_412 = " value_";
  protected final String TEXT_413 = "," + NL + "            OutputCollector<NullWritable, WritableComparable> output_";
  protected final String TEXT_414 = ", Reporter reporter_";
  protected final String TEXT_415 = ") throws IOException{" + NL + "        org.apache.hadoop.hbase.client.Result result = (org.apache.hadoop.hbase.client.Result)value_";
  protected final String TEXT_416 = ".get();" + NL + "        byte[] rowResult = null;" + NL + "        String temp = null;" + NL + "        try {";
  protected final String TEXT_417 = NL + "                rowResult = result.getValue(org.apache.hadoop.hbase.util.Bytes.toBytes(";
  protected final String TEXT_418 = "), org.apache.hadoop.hbase.util.Bytes.toBytes(\"";
  protected final String TEXT_419 = "\"));" + NL + "                temp = org.apache.hadoop.hbase.util.Bytes.toString(rowResult);" + NL + "                if(temp!=null && temp.length() > 0) {";
  protected final String TEXT_420 = NL + "                        ";
  protected final String TEXT_421 = " = temp.toString();";
  protected final String TEXT_422 = NL + "                        ";
  protected final String TEXT_423 = "=rowResult;";
  protected final String TEXT_424 = NL + "                        ";
  protected final String TEXT_425 = "=BigDataParserUtils.parseTo_Date(temp, ";
  protected final String TEXT_426 = ");";
  protected final String TEXT_427 = NL + "                        ";
  protected final String TEXT_428 = "=org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_429 = NL + "                        ";
  protected final String TEXT_430 = "=(char)org.apache.hadoop.hbase.util.Bytes.toInt(rowResult);";
  protected final String TEXT_431 = NL + "                        ";
  protected final String TEXT_432 = "=org.apache.hadoop.hbase.util.Bytes.to";
  protected final String TEXT_433 = "(rowResult);";
  protected final String TEXT_434 = NL + "                        ";
  protected final String TEXT_435 = "=BigDataParserUtils.parseTo_";
  protected final String TEXT_436 = "(temp);";
  protected final String TEXT_437 = NL + "                }else{";
  protected final String TEXT_438 = NL + "                        ";
  protected final String TEXT_439 = " = ";
  protected final String TEXT_440 = ";";
  protected final String TEXT_441 = NL + "                        ";
  protected final String TEXT_442 = " = null;";
  protected final String TEXT_443 = NL + "                        throw new RuntimeException(\"Value is empty for column : '";
  protected final String TEXT_444 = "' in '";
  protected final String TEXT_445 = "' connection, value is invalid or this column should be nullable or have a default value.\");";
  protected final String TEXT_446 = NL + "                }";
  protected final String TEXT_447 = NL + "            output_";
  protected final String TEXT_448 = ".collect(NullWritable.get(), ";
  protected final String TEXT_449 = ");" + NL + "        } catch  (Exception e) {";
  protected final String TEXT_450 = NL + "            ";
  protected final String TEXT_451 = ".invalidInputLine = value_";
  protected final String TEXT_452 = ".toString();";
  protected final String TEXT_453 = NL + "            ";
  protected final String TEXT_454 = ".errorCode = e.toString();" + NL + "            mos_";
  protected final String TEXT_455 = ".getCollector(\"";
  protected final String TEXT_456 = "\", reporter_";
  protected final String TEXT_457 = ")" + NL + "                    .collect(NullWritable.get(), ";
  protected final String TEXT_458 = ");" + NL + "        }" + NL + "    }" + NL + "" + NL + "    public void close() throws IOException{" + NL + "        mos_";
  protected final String TEXT_459 = ".close();" + NL + "    }" + NL + "} // end of ";
  protected final String TEXT_460 = "_InputMapper" + NL + NL;

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

String tableName = ElementParameterParser.getValue(node, "__TABLE__");
boolean isByFilter = ("true").equals(ElementParameterParser.getValue(node, "__IS_BY_FILTER__"));
List<Map<String, String>> filterMapping = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__FILTER__");
String logical = ElementParameterParser.getValue(node,"__LOGICAL_OP__");
String distribution = ElementParameterParser.getValue(node,"__DISTRIBUTION__");
String version = ElementParameterParser.getValue(node,"__HBASE_VERSION__");
String folder = ElementParameterParser.getValue(node,"__FILENAME__");
final boolean dieOnError = "true".equals(ElementParameterParser.getValue(node, "__DIE_ON_ERROR__"));
final List<Map<String, String>> mapping = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node,"__MAPPING__");
List<Map<String, String>> hbaseParameters = (List<Map<String,String>>) ElementParameterParser.getObjectValue(node,"__HBASE_PARAMETERS__");

org.talend.hadoop.distribution.component.HBaseComponent hbaseDistrib = null;
try {
    hbaseDistrib = (org.talend.hadoop.distribution.component.HBaseComponent) org.talend.hadoop.distribution.DistributionFactory.buildDistribution(distribution, version);
} catch (java.lang.Exception e) {
    e.printStackTrace();
    return "";
}

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
final String mainName = mainConn.getName();
final String mainRecord = codeGenArgument.getRecordStructName(mainConn);
final String recordStruct = mainRecord;
final List<IMetadataColumn> mainColumns = mainConn.getMetadataTable().getListColumns();
final boolean finalIsLog4jEnabled = isLog4jEnabled;

class NextRecordGenerator {
    /** Generate the implementation for the RecordReader.next() method. */
    public void generate() {
        
    stringBuffer.append(TEXT_1);
     if(finalIsLog4jEnabled) { 
    stringBuffer.append(TEXT_2);
     } 
    
                if (dieOnError) {
                    
    stringBuffer.append(TEXT_3);
    
                }
                
    stringBuffer.append(TEXT_4);
    
    }
    public void generateHelperMethods() {
        
    stringBuffer.append(TEXT_5);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_7);
    
            for (int i = 0; i < mapping.size(); i++) {
                Map<String, String> map = mapping.get(i);
                String family_column= map.get("FAMILY_COLUMN");
                IMetadataColumn column = mainColumns.get(i);
                String columnName = column.getLabel();
                String defaultValue = column.getDefault();
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                boolean isPrimitiveType = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
                String toAssign = "value." + columnName;

                
    stringBuffer.append(TEXT_8);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_10);
     if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) { 
    stringBuffer.append(TEXT_11);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_12);
     } else if(javaType == JavaTypesManager.BYTE_ARRAY) { 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_14);
     }else if(javaType == JavaTypesManager.DATE) { 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_17);
     }else if(isPrimitiveType && javaType == JavaTypesManager.INTEGER) { 
    stringBuffer.append(TEXT_18);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_19);
     }else if(isPrimitiveType && javaType == JavaTypesManager.CHARACTER) { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_21);
     } else if(isPrimitiveType && (javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.LONG
                                || javaType == JavaTypesManager.FLOAT || javaType == JavaTypesManager.DOUBLE)) { 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_24);
     }else {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_27);
     } 
    stringBuffer.append(TEXT_28);
    
                    String default_Value = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, defaultValue);
                    if (default_Value != null && !"null".equals(default_Value)) {
                        
    stringBuffer.append(TEXT_29);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(default_Value);
    stringBuffer.append(TEXT_31);
    
                    } else if (!JavaTypesManager.isJavaPrimitiveType(javaType,column.isNullable())) {
                        
    stringBuffer.append(TEXT_32);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_33);
    
                    } else {
                        
    stringBuffer.append(TEXT_34);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_36);
    
                    }
                    
    stringBuffer.append(TEXT_37);
    
            }
            
    stringBuffer.append(TEXT_38);
    
    }
}

NextRecordGenerator nextRecordImplementation = new NextRecordGenerator();

    stringBuffer.append(TEXT_39);
    

String zookeeperQuorum = ElementParameterParser.getValue(node, "__ZOOKEEPER_QUORUM__");
String zookeeperClientPort = ElementParameterParser.getValue(node, "__ZOOKEEPER_CLIENT_PORT__");
boolean setZNodeParent = "true".equals(ElementParameterParser.getValue(node, "__SET_ZNODE_PARENT__"));
String zNodeParent = ElementParameterParser.getValue(node, "__ZNODE_PARENT__");


boolean setTableNsMapping = "true".equals(ElementParameterParser.getValue(node, "__SET_TABLE_NS_MAPPING__"));
String tableNsMapping = ElementParameterParser.getValue(node, "__TABLE_NS_MAPPING__");
boolean useTableNsMapping = setTableNsMapping && ((tableNsMapping != null) && (!tableNsMapping.equals("")));


    stringBuffer.append(TEXT_40);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(zookeeperQuorum);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(zookeeperClientPort);
    stringBuffer.append(TEXT_44);
    
        if(setZNodeParent) {
            
    stringBuffer.append(TEXT_45);
    stringBuffer.append(zNodeParent);
    stringBuffer.append(TEXT_46);
    
        }

        if(hbaseDistrib.doSupportMapRDB() && useTableNsMapping){
            
    stringBuffer.append(TEXT_47);
    stringBuffer.append(tableNsMapping);
    stringBuffer.append(TEXT_48);
    
        }

        for (Map<String,String> param : hbaseParameters) {
            
    stringBuffer.append(TEXT_49);
    stringBuffer.append(param.get("PROPERTY"));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(param.get("VALUE"));
    stringBuffer.append(TEXT_51);
    
        }
        
    stringBuffer.append(TEXT_52);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_53);
    
        
    stringBuffer.append(TEXT_54);
    stringBuffer.append(mapping.size());
    stringBuffer.append(TEXT_55);
    
        for (int i = 0; i < mapping.size(); i++) {
            Map<String, String> map = mapping.get(i);
            IMetadataColumn column = mainColumns.get(i);
            //String schema_column = map.get("SCHEMA_COLUMN");//no use, index is enough
            String family_column= map.get("FAMILY_COLUMN");
            
    stringBuffer.append(TEXT_56);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_59);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_60);
    
        }
        
    stringBuffer.append(TEXT_61);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_62);
    
            if (isByFilter && filterMapping.size() > 0) {
                boolean hasMultipleColumnPrefixFilterType = false;
                for (int i=0; i < filterMapping.size(); i++) {
                    Map<String, String> filterMap = filterMapping.get(i);
                    String filterType = filterMap.get("FILTER_TYPE");
                    if("MultipleColumnPrefixFilter".equals(filterType)){
                        hasMultipleColumnPrefixFilterType = true;
                        break;
                    }
                }
                if (hasMultipleColumnPrefixFilterType) {
                    
    stringBuffer.append(TEXT_63);
    
                }
                
    stringBuffer.append(TEXT_64);
    stringBuffer.append(logical);
    stringBuffer.append(TEXT_65);
    
                for (int j=0; j<filterMapping.size(); j++){
                    Map<String, String> filterMap = filterMapping.get(j);
                    //"SingleColumnValueFilter","FamilyFilter","QualifierFilter",
                    // "ColumnPrefixFilter","MultipleColumnPrefixFilter","MultipleColumnPrefixFilter","RowFilter"
                    String filterType = filterMap.get("FILTER_TYPE");
                    String filterColumn = filterMap.get("FILTER_COLUMN");
                    String filterFamily = filterMap.get("FILTER_FAMILY");
                    //"EQUAL","GREATER","GREATER_OR_EQUAL","LESS","LESS_OR_EQUAL","NO_OP","NOT_EQUAL",
                    String filterOperation = filterMap.get("FILTER_OPERATOR");
                    String filterValue = filterMap.get("FILTER_VALUE");
                    String filterComparatorType = filterMap.get("FILTER_COMPARATOR_TYPE");
                    //"BinaryComparator" ,"RegexStringComparator" ,"SubstringComparator"
                    if("SingleColumnValueFilter".equals(filterType)) {
                        // "filterValue" is column value,like: "1" ,"2" ...
                        // return whole row (all columns) value
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_66);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_70);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_71);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_75);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_76);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_79);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_80);
    
                        }
                    } else if("FamilyFilter".equals(filterType)) {
                        // "Filter Family" is family name ,like: "id_family","name_family"....，
                        // return columns which mapping in "Filter Family",filter other columns
                        if("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_81);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_83);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_84);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_86);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_87);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_89);
    
                        }
                    } else if("QualifierFilter".equals(filterType)) {
                        // "Filter Column" is column name,like:"id" or "name" ....
                        // then you will get meet codition column value ,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_90);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_92);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_93);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_95);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_96);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_98);
    
                        }
                    } else if("ColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is column name,like:"id" or "name" ....,
                        // return column value,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_99);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_100);
    
                        }
                        
    stringBuffer.append(TEXT_101);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_102);
    
                    } else if("MultipleColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is for column name ,like:"id,name" ,"id,name,sex".... ,
                        // return column value,filter other columns
                        if(filterFamily!=null && !"".equals(filterFamily)){
                            
    stringBuffer.append(TEXT_103);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_104);
    
                        }
                        
    stringBuffer.append(TEXT_105);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_108);
    
                    } else if("ColumnRangeFilter".equals(filterType)) {
                        // "Filter Column" value is tow columns name,like: "id,name" ,"id,sex" ....,
                        // return columns value ,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_109);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_110);
    
                        }
                        
    stringBuffer.append(TEXT_111);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_113);
    
                    } else if("RowFilter".equals(filterType)) {
                        // "Filter Value" is rowkey value,like "1" ,"car"....,return whole row (all columns)
                        if ("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_114);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_116);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_117);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_119);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_120);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_122);
    
                        }
                    } else if("ValueFilter".equals(filterType)) {
                        // "Filter Value" is any columns value,like "1" ,"car" .... ,
                        // return only the meet codition value,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_123);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_125);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_126);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_128);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_129);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_131);
    
                        }
                    }
                    
    stringBuffer.append(TEXT_132);
    
                }
            }
            
    stringBuffer.append(TEXT_133);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_136);
    
            // Use a specific callback to generate the appropriate next record.
            nextRecordImplementation.generate();
            
    stringBuffer.append(TEXT_137);
    
            if (dieOnError) {
                
    stringBuffer.append(TEXT_138);
    
            } else {
                
    stringBuffer.append(TEXT_139);
    
            }
            
    stringBuffer.append(TEXT_140);
    
        // Any required helper methods here.
        nextRecordImplementation.generateHelperMethods();
        
    stringBuffer.append(TEXT_141);
    
} else if (rejConn != null && mainConn == null) {
    
    
// The name of the reject row.
final String rejName = rejConn.getName();
final String rejRecord = codeGenArgument.getRecordStructName(rejConn);
final String recordStruct = rejRecord;

// We don't what to get the metadata of the main flow, so we check if the current metadata is not the reject flow
final List<IMetadataColumn> mainColumns;
{
    List<IMetadataColumn> mainColumnsTmp = null;
    for (IMetadataTable md : node.getMetadataList()) {
        if (!"REJECT".equals(md.getTableName())) {
            mainColumnsTmp = md.getListColumns();
            break;
        }
    }
    mainColumns = mainColumnsTmp;
}
if (mainColumns == null)
    return "";

class NextRecordGenerator {
    /** Generate the implementation for the RecordReader.next() method. */
    public void generate() {
        
    stringBuffer.append(TEXT_142);
    
    }
    public void generateHelperMethods() {
        
    stringBuffer.append(TEXT_143);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_145);
    
            for (int i = 0; i < mapping.size(); i++) {
                Map<String, String> map = mapping.get(i);
                String family_column= map.get("FAMILY_COLUMN");
                IMetadataColumn column = mainColumns.get(i);
                String columnName = column.getLabel();
                String defaultValue = column.getDefault();
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                boolean isPrimitiveType = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
                String toAssign = "value." + columnName;

                
    stringBuffer.append(TEXT_146);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_148);
     if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) { 
    stringBuffer.append(TEXT_149);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_150);
     } else if(javaType == JavaTypesManager.BYTE_ARRAY) { 
    stringBuffer.append(TEXT_151);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_152);
     }else if(javaType == JavaTypesManager.DATE) { 
    stringBuffer.append(TEXT_153);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_154);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_155);
     }else if(isPrimitiveType && javaType == JavaTypesManager.INTEGER) { 
    stringBuffer.append(TEXT_156);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_157);
     }else if(isPrimitiveType && javaType == JavaTypesManager.CHARACTER) { 
    stringBuffer.append(TEXT_158);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_159);
     } else if(isPrimitiveType && (javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.LONG
                                || javaType == JavaTypesManager.FLOAT || javaType == JavaTypesManager.DOUBLE)) { 
    stringBuffer.append(TEXT_160);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_162);
     }else {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_165);
     } 
    stringBuffer.append(TEXT_166);
    
                    String default_Value = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, defaultValue);
                    if (default_Value != null && !"null".equals(default_Value)) {
                        
    stringBuffer.append(TEXT_167);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_168);
    stringBuffer.append(default_Value);
    stringBuffer.append(TEXT_169);
    
                    } else if (!JavaTypesManager.isJavaPrimitiveType(javaType,column.isNullable())) {
                        
    stringBuffer.append(TEXT_170);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_171);
    
                    } else {
                        
    stringBuffer.append(TEXT_172);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_174);
    
                    }
                    
    stringBuffer.append(TEXT_175);
    
            }
            
    stringBuffer.append(TEXT_176);
    
    }
}

NextRecordGenerator nextRecordImplementation = new NextRecordGenerator();

    stringBuffer.append(TEXT_177);
    

String zookeeperQuorum = ElementParameterParser.getValue(node, "__ZOOKEEPER_QUORUM__");
String zookeeperClientPort = ElementParameterParser.getValue(node, "__ZOOKEEPER_CLIENT_PORT__");
boolean setZNodeParent = "true".equals(ElementParameterParser.getValue(node, "__SET_ZNODE_PARENT__"));
String zNodeParent = ElementParameterParser.getValue(node, "__ZNODE_PARENT__");


boolean setTableNsMapping = "true".equals(ElementParameterParser.getValue(node, "__SET_TABLE_NS_MAPPING__"));
String tableNsMapping = ElementParameterParser.getValue(node, "__TABLE_NS_MAPPING__");
boolean useTableNsMapping = setTableNsMapping && ((tableNsMapping != null) && (!tableNsMapping.equals("")));


    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(zookeeperQuorum);
    stringBuffer.append(TEXT_181);
    stringBuffer.append(zookeeperClientPort);
    stringBuffer.append(TEXT_182);
    
        if(setZNodeParent) {
            
    stringBuffer.append(TEXT_183);
    stringBuffer.append(zNodeParent);
    stringBuffer.append(TEXT_184);
    
        }

        if(hbaseDistrib.doSupportMapRDB() && useTableNsMapping){
            
    stringBuffer.append(TEXT_185);
    stringBuffer.append(tableNsMapping);
    stringBuffer.append(TEXT_186);
    
        }

        for (Map<String,String> param : hbaseParameters) {
            
    stringBuffer.append(TEXT_187);
    stringBuffer.append(param.get("PROPERTY"));
    stringBuffer.append(TEXT_188);
    stringBuffer.append(param.get("VALUE"));
    stringBuffer.append(TEXT_189);
    
        }
        
    stringBuffer.append(TEXT_190);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_191);
    
        
    stringBuffer.append(TEXT_192);
    stringBuffer.append(mapping.size());
    stringBuffer.append(TEXT_193);
    
        for (int i = 0; i < mapping.size(); i++) {
            Map<String, String> map = mapping.get(i);
            IMetadataColumn column = mainColumns.get(i);
            //String schema_column = map.get("SCHEMA_COLUMN");//no use, index is enough
            String family_column= map.get("FAMILY_COLUMN");
            
    stringBuffer.append(TEXT_194);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_198);
    
        }
        
    stringBuffer.append(TEXT_199);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_200);
    
            if (isByFilter && filterMapping.size() > 0) {
                boolean hasMultipleColumnPrefixFilterType = false;
                for (int i=0; i < filterMapping.size(); i++) {
                    Map<String, String> filterMap = filterMapping.get(i);
                    String filterType = filterMap.get("FILTER_TYPE");
                    if("MultipleColumnPrefixFilter".equals(filterType)){
                        hasMultipleColumnPrefixFilterType = true;
                        break;
                    }
                }
                if (hasMultipleColumnPrefixFilterType) {
                    
    stringBuffer.append(TEXT_201);
    
                }
                
    stringBuffer.append(TEXT_202);
    stringBuffer.append(logical);
    stringBuffer.append(TEXT_203);
    
                for (int j=0; j<filterMapping.size(); j++){
                    Map<String, String> filterMap = filterMapping.get(j);
                    //"SingleColumnValueFilter","FamilyFilter","QualifierFilter",
                    // "ColumnPrefixFilter","MultipleColumnPrefixFilter","MultipleColumnPrefixFilter","RowFilter"
                    String filterType = filterMap.get("FILTER_TYPE");
                    String filterColumn = filterMap.get("FILTER_COLUMN");
                    String filterFamily = filterMap.get("FILTER_FAMILY");
                    //"EQUAL","GREATER","GREATER_OR_EQUAL","LESS","LESS_OR_EQUAL","NO_OP","NOT_EQUAL",
                    String filterOperation = filterMap.get("FILTER_OPERATOR");
                    String filterValue = filterMap.get("FILTER_VALUE");
                    String filterComparatorType = filterMap.get("FILTER_COMPARATOR_TYPE");
                    //"BinaryComparator" ,"RegexStringComparator" ,"SubstringComparator"
                    if("SingleColumnValueFilter".equals(filterType)) {
                        // "filterValue" is column value,like: "1" ,"2" ...
                        // return whole row (all columns) value
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_204);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_206);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_208);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_209);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_210);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_211);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_212);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_213);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_214);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_216);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_218);
    
                        }
                    } else if("FamilyFilter".equals(filterType)) {
                        // "Filter Family" is family name ,like: "id_family","name_family"....，
                        // return columns which mapping in "Filter Family",filter other columns
                        if("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_219);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_221);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_222);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_224);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_225);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_227);
    
                        }
                    } else if("QualifierFilter".equals(filterType)) {
                        // "Filter Column" is column name,like:"id" or "name" ....
                        // then you will get meet codition column value ,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_228);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_229);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_230);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_231);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_233);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_234);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_236);
    
                        }
                    } else if("ColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is column name,like:"id" or "name" ....,
                        // return column value,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_237);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_238);
    
                        }
                        
    stringBuffer.append(TEXT_239);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_240);
    
                    } else if("MultipleColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is for column name ,like:"id,name" ,"id,name,sex".... ,
                        // return column value,filter other columns
                        if(filterFamily!=null && !"".equals(filterFamily)){
                            
    stringBuffer.append(TEXT_241);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_242);
    
                        }
                        
    stringBuffer.append(TEXT_243);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_246);
    
                    } else if("ColumnRangeFilter".equals(filterType)) {
                        // "Filter Column" value is tow columns name,like: "id,name" ,"id,sex" ....,
                        // return columns value ,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_247);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_248);
    
                        }
                        
    stringBuffer.append(TEXT_249);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_251);
    
                    } else if("RowFilter".equals(filterType)) {
                        // "Filter Value" is rowkey value,like "1" ,"car"....,return whole row (all columns)
                        if ("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_252);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_254);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_255);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_257);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_258);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_260);
    
                        }
                    } else if("ValueFilter".equals(filterType)) {
                        // "Filter Value" is any columns value,like "1" ,"car" .... ,
                        // return only the meet codition value,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_261);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_263);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_264);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_266);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_267);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_269);
    
                        }
                    }
                    
    stringBuffer.append(TEXT_270);
    
                }
            }
            
    stringBuffer.append(TEXT_271);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_274);
    
            // Use a specific callback to generate the appropriate next record.
            nextRecordImplementation.generate();
            
    stringBuffer.append(TEXT_275);
    
            if (dieOnError) {
                
    stringBuffer.append(TEXT_276);
    
            } else {
                
    stringBuffer.append(TEXT_277);
    
            }
            
    stringBuffer.append(TEXT_278);
    
        // Any required helper methods here.
        nextRecordImplementation.generateHelperMethods();
        
    stringBuffer.append(TEXT_279);
    
} else {
    
    
// The name of the main row.
final String mainName = mainConn.getName();
final String rejName = rejConn.getName();
final String mainRecord = codeGenArgument.getRecordStructName(mainConn);
final String recordStruct = mainName + "TemporaryStruct";
final List<IMetadataColumn> mainColumns = mainConn.getMetadataTable().getListColumns();

class NextRecordGenerator {
    /** Generate the implementation for the RecordReader.next() method. */
    public void generate() {
        
    stringBuffer.append(TEXT_280);
    
    }
    public void generateHelperMethods() {
    }
}
NextRecordGenerator nextRecordImplementation = new NextRecordGenerator();

// With both main and reject flows, the object passed to the mapper is not a
// parsed structure, but a Result.  The mapper is responsible for sorting the
// result output into the main or reject flow.


    stringBuffer.append(TEXT_281);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_282);
    

String zookeeperQuorum = ElementParameterParser.getValue(node, "__ZOOKEEPER_QUORUM__");
String zookeeperClientPort = ElementParameterParser.getValue(node, "__ZOOKEEPER_CLIENT_PORT__");
boolean setZNodeParent = "true".equals(ElementParameterParser.getValue(node, "__SET_ZNODE_PARENT__"));
String zNodeParent = ElementParameterParser.getValue(node, "__ZNODE_PARENT__");


boolean setTableNsMapping = "true".equals(ElementParameterParser.getValue(node, "__SET_TABLE_NS_MAPPING__"));
String tableNsMapping = ElementParameterParser.getValue(node, "__TABLE_NS_MAPPING__");
boolean useTableNsMapping = setTableNsMapping && ((tableNsMapping != null) && (!tableNsMapping.equals("")));


    stringBuffer.append(TEXT_283);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(zookeeperQuorum);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(zookeeperClientPort);
    stringBuffer.append(TEXT_287);
    
        if(setZNodeParent) {
            
    stringBuffer.append(TEXT_288);
    stringBuffer.append(zNodeParent);
    stringBuffer.append(TEXT_289);
    
        }

        if(hbaseDistrib.doSupportMapRDB() && useTableNsMapping){
            
    stringBuffer.append(TEXT_290);
    stringBuffer.append(tableNsMapping);
    stringBuffer.append(TEXT_291);
    
        }

        for (Map<String,String> param : hbaseParameters) {
            
    stringBuffer.append(TEXT_292);
    stringBuffer.append(param.get("PROPERTY"));
    stringBuffer.append(TEXT_293);
    stringBuffer.append(param.get("VALUE"));
    stringBuffer.append(TEXT_294);
    
        }
        
    stringBuffer.append(TEXT_295);
    stringBuffer.append(tableName);
    stringBuffer.append(TEXT_296);
    
        
    stringBuffer.append(TEXT_297);
    stringBuffer.append(mapping.size());
    stringBuffer.append(TEXT_298);
    
        for (int i = 0; i < mapping.size(); i++) {
            Map<String, String> map = mapping.get(i);
            IMetadataColumn column = mainColumns.get(i);
            //String schema_column = map.get("SCHEMA_COLUMN");//no use, index is enough
            String family_column= map.get("FAMILY_COLUMN");
            
    stringBuffer.append(TEXT_299);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_300);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_303);
    
        }
        
    stringBuffer.append(TEXT_304);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_305);
    
            if (isByFilter && filterMapping.size() > 0) {
                boolean hasMultipleColumnPrefixFilterType = false;
                for (int i=0; i < filterMapping.size(); i++) {
                    Map<String, String> filterMap = filterMapping.get(i);
                    String filterType = filterMap.get("FILTER_TYPE");
                    if("MultipleColumnPrefixFilter".equals(filterType)){
                        hasMultipleColumnPrefixFilterType = true;
                        break;
                    }
                }
                if (hasMultipleColumnPrefixFilterType) {
                    
    stringBuffer.append(TEXT_306);
    
                }
                
    stringBuffer.append(TEXT_307);
    stringBuffer.append(logical);
    stringBuffer.append(TEXT_308);
    
                for (int j=0; j<filterMapping.size(); j++){
                    Map<String, String> filterMap = filterMapping.get(j);
                    //"SingleColumnValueFilter","FamilyFilter","QualifierFilter",
                    // "ColumnPrefixFilter","MultipleColumnPrefixFilter","MultipleColumnPrefixFilter","RowFilter"
                    String filterType = filterMap.get("FILTER_TYPE");
                    String filterColumn = filterMap.get("FILTER_COLUMN");
                    String filterFamily = filterMap.get("FILTER_FAMILY");
                    //"EQUAL","GREATER","GREATER_OR_EQUAL","LESS","LESS_OR_EQUAL","NO_OP","NOT_EQUAL",
                    String filterOperation = filterMap.get("FILTER_OPERATOR");
                    String filterValue = filterMap.get("FILTER_VALUE");
                    String filterComparatorType = filterMap.get("FILTER_COMPARATOR_TYPE");
                    //"BinaryComparator" ,"RegexStringComparator" ,"SubstringComparator"
                    if("SingleColumnValueFilter".equals(filterType)) {
                        // "filterValue" is column value,like: "1" ,"2" ...
                        // return whole row (all columns) value
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_309);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_310);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_313);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_314);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_317);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_318);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_319);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_320);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_322);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_323);
    
                        }
                    } else if("FamilyFilter".equals(filterType)) {
                        // "Filter Family" is family name ,like: "id_family","name_family"....，
                        // return columns which mapping in "Filter Family",filter other columns
                        if("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_324);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_326);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_327);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_328);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_329);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_330);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_331);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_332);
    
                        }
                    } else if("QualifierFilter".equals(filterType)) {
                        // "Filter Column" is column name,like:"id" or "name" ....
                        // then you will get meet codition column value ,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_333);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_334);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_335);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_336);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_338);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_339);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_340);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_341);
    
                        }
                    } else if("ColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is column name,like:"id" or "name" ....,
                        // return column value,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_342);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_343);
    
                        }
                        
    stringBuffer.append(TEXT_344);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_345);
    
                    } else if("MultipleColumnPrefixFilter".equals(filterType)) {
                        // "Filter Column" value is for column name ,like:"id,name" ,"id,name,sex".... ,
                        // return column value,filter other columns
                        if(filterFamily!=null && !"".equals(filterFamily)){
                            
    stringBuffer.append(TEXT_346);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_347);
    
                        }
                        
    stringBuffer.append(TEXT_348);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_350);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_351);
    
                    } else if("ColumnRangeFilter".equals(filterType)) {
                        // "Filter Column" value is tow columns name,like: "id,name" ,"id,sex" ....,
                        // return columns value ,filter other columns
                        if (filterFamily!=null && !"".equals(filterFamily)) {
                            
    stringBuffer.append(TEXT_352);
    stringBuffer.append(filterFamily);
    stringBuffer.append(TEXT_353);
    
                        }
                        
    stringBuffer.append(TEXT_354);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_355);
    stringBuffer.append(filterColumn);
    stringBuffer.append(TEXT_356);
    
                    } else if("RowFilter".equals(filterType)) {
                        // "Filter Value" is rowkey value,like "1" ,"car"....,return whole row (all columns)
                        if ("BinaryComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_357);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_358);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_359);
    
                        } else if ("RegexStringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_360);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_361);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_362);
    
                        } else if("SubstringComparator".equals(filterComparatorType)) {
                            
    stringBuffer.append(TEXT_363);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_364);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_365);
    
                        }
                    } else if("ValueFilter".equals(filterType)) {
                        // "Filter Value" is any columns value,like "1" ,"car" .... ,
                        // return only the meet codition value,filter other columns
                        if("BinaryComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_366);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_368);
    
                        }else if ("RegexStringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_369);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_371);
    
                        }else if("SubstringComparator".equals(filterComparatorType)){
                            
    stringBuffer.append(TEXT_372);
    stringBuffer.append(filterOperation);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(filterValue);
    stringBuffer.append(TEXT_374);
    
                        }
                    }
                    
    stringBuffer.append(TEXT_375);
    
                }
            }
            
    stringBuffer.append(TEXT_376);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_379);
    
            // Use a specific callback to generate the appropriate next record.
            nextRecordImplementation.generate();
            
    stringBuffer.append(TEXT_380);
    
            if (dieOnError) {
                
    stringBuffer.append(TEXT_381);
    
            } else {
                
    stringBuffer.append(TEXT_382);
    
            }
            
    stringBuffer.append(TEXT_383);
    
        // Any required helper methods here.
        nextRecordImplementation.generateHelperMethods();
        
    stringBuffer.append(TEXT_384);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_393);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_394);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_395);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_396);
    stringBuffer.append(TEXT_397);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_398);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_401);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_402);
    
        // Force the creation of the output directory
        for (INode virtualNode : node.getProcess().getGraphicalNodes()) {
            if (virtualNode.getUniqueName().equals(cid)) {
                
    stringBuffer.append(TEXT_403);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_404);
    stringBuffer.append(node.getProcess().getName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(virtualNode.getUniqueName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(virtualNode.getOutgoingConnections("REJECT").get(0).getName());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_408);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_409);
    
                break;
            }
        }
        
    stringBuffer.append(TEXT_410);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_411);
    stringBuffer.append(recordStruct);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_414);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_415);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_416);
    
            for(int i = 0; i < mapping.size(); i++){
                Map<String, String> map = mapping.get(i);
                String family_column= map.get("FAMILY_COLUMN");
                IMetadataColumn column = mainColumns.get(i);
                String columnName = column.getLabel();
                String defaultValue = column.getDefault();
                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                boolean isPrimitiveType = JavaTypesManager.isJavaPrimitiveType(javaType, column.isNullable());
                String toAssign = mainName + "." + columnName;
                
    stringBuffer.append(TEXT_417);
    stringBuffer.append(family_column);
    stringBuffer.append(TEXT_418);
    stringBuffer.append(column.getOriginalDbColumnName());
    stringBuffer.append(TEXT_419);
    
                    if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {
                    
    stringBuffer.append(TEXT_420);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_421);
    
                    }else if(javaType == JavaTypesManager.BYTE_ARRAY){
                    
    stringBuffer.append(TEXT_422);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_423);
    
                    }else if(javaType == JavaTypesManager.DATE){
                    
    stringBuffer.append(TEXT_424);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_425);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_426);
    
                    }else if(isPrimitiveType && javaType == JavaTypesManager.INTEGER){
                    
    stringBuffer.append(TEXT_427);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_428);
    
                    }else if(isPrimitiveType && javaType == JavaTypesManager.CHARACTER){
                    
    stringBuffer.append(TEXT_429);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_430);
    
                    }else if(isPrimitiveType && (javaType == JavaTypesManager.SHORT || javaType == JavaTypesManager.LONG || javaType == JavaTypesManager.FLOAT || javaType == JavaTypesManager.DOUBLE)) {
                    
    stringBuffer.append(TEXT_431);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_432);
    stringBuffer.append(javaType.getNullableClass().getSimpleName());
    stringBuffer.append(TEXT_433);
    
                    }else{
                    
    stringBuffer.append(TEXT_434);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_436);
    
                    }
                    
    stringBuffer.append(TEXT_437);
    
                    String default_Value = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate, defaultValue);
                    if (column.isNullable() && default_Value != null && !"null".equals(default_Value)) {
                    
    stringBuffer.append(TEXT_438);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_439);
    stringBuffer.append(default_Value);
    stringBuffer.append(TEXT_440);
    
                    } else if (column.isNullable() && !JavaTypesManager.isJavaPrimitiveType(javaType,column.isNullable())) {
                    
    stringBuffer.append(TEXT_441);
    stringBuffer.append(toAssign);
    stringBuffer.append(TEXT_442);
    
                    } else {
                    
    stringBuffer.append(TEXT_443);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_444);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_445);
    
                    }
                    
    stringBuffer.append(TEXT_446);
    
            }
            
    stringBuffer.append(TEXT_447);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_448);
    stringBuffer.append(mainName);
    stringBuffer.append(TEXT_449);
    stringBuffer.append(TEXT_450);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_452);
    stringBuffer.append(TEXT_453);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_454);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_457);
    stringBuffer.append(rejName);
    stringBuffer.append(TEXT_458);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_460);
    
}

    return stringBuffer.toString();
  }
}
