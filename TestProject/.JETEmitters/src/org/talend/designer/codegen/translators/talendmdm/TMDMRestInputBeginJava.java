package org.talend.designer.codegen.translators.talendmdm;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.List;
import java.util.Map;

public class TMDMRestInputBeginJava
{
  protected static String nl;
  public static synchronized TMDMRestInputBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TMDMRestInputBeginJava result = new TMDMRestInputBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "                if(log.is";
  protected final String TEXT_3 = "Enabled())";
  protected final String TEXT_4 = NL + "            log.";
  protected final String TEXT_5 = "(\"";
  protected final String TEXT_6 = " - \" ";
  protected final String TEXT_7 = " + ";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = ");";
  protected final String TEXT_10 = NL + "            StringBuilder ";
  protected final String TEXT_11 = " = new StringBuilder();";
  protected final String TEXT_12 = NL + "            ";
  protected final String TEXT_13 = ".append(\"Parameters:\");";
  protected final String TEXT_14 = NL + "                    ";
  protected final String TEXT_15 = ".append(\"";
  protected final String TEXT_16 = "\" + \" = \" + String.valueOf(";
  protected final String TEXT_17 = ").substring(0, 4) + \"...\");     ";
  protected final String TEXT_18 = NL + "                    ";
  protected final String TEXT_19 = ".append(\"";
  protected final String TEXT_20 = "\" + \" = \" + ";
  protected final String TEXT_21 = ");";
  protected final String TEXT_22 = NL + "                ";
  protected final String TEXT_23 = ".append(\" | \");";
  protected final String TEXT_24 = NL + "            StringBuilder ";
  protected final String TEXT_25 = " = new StringBuilder();    ";
  protected final String TEXT_26 = NL + "                    ";
  protected final String TEXT_27 = ".append(";
  protected final String TEXT_28 = ".";
  protected final String TEXT_29 = ");";
  protected final String TEXT_30 = NL + "                    if(";
  protected final String TEXT_31 = ".";
  protected final String TEXT_32 = " == null){";
  protected final String TEXT_33 = NL + "                        ";
  protected final String TEXT_34 = ".append(\"<null>\");" + NL + "                    }else{";
  protected final String TEXT_35 = NL + "                        ";
  protected final String TEXT_36 = ".append(";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = ");" + NL + "                    }   ";
  protected final String TEXT_39 = NL + "                ";
  protected final String TEXT_40 = ".append(\"|\");";
  protected final String TEXT_41 = NL + "                    class QueryDelegater_";
  protected final String TEXT_42 = " {" + NL + "" + NL + "                        private org.json.simple.parser.JSONParser parser = new org.json.simple.parser.JSONParser();" + NL + "" + NL + "                        private org.json.simple.JSONObject queryObject;" + NL + "" + NL + "                        private String dataCluster;" + NL + "" + NL + "                        private String containerType;" + NL + "" + NL + "                        private boolean retrieveRawData;" + NL + "" + NL + "                        private String acceptType;" + NL + "" + NL + "                        private Long orgStart;" + NL + "" + NL + "                        private Long orgLimit;" + NL + "" + NL + "                        private int fetchSize;" + NL + "" + NL + "                        private String transactionId;" + NL + "" + NL + "                        private String sessionId;" + NL + "" + NL + "                        private org.apache.cxf.jaxrs.client.WebClient  webClient;" + NL + "" + NL + "                        public QueryDelegater_";
  protected final String TEXT_43 = "(java.util.Map<String, String> connConfig, String dataCluster, String containerType, String queryText," + NL + "                            boolean retrieveRawData, String acceptType, int fetchSize) {" + NL + "                            this.initQueryObject(queryText);" + NL + "                            this.dataCluster = dataCluster;" + NL + "                            this.containerType = containerType;" + NL + "                            this.retrieveRawData = retrieveRawData;" + NL + "                            this.acceptType = acceptType;" + NL + "                            this.fetchSize = fetchSize;" + NL + "                            this.transactionId = connConfig.get(\"transactionId\");" + NL + "                            this.sessionId = connConfig.get(\"sessionId\");" + NL + "                            webClient = org.apache.cxf.jaxrs.client.WebClient.create(connConfig.get(\"url\")," + NL + "                                java.util.Arrays.asList(new com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider())," + NL + "                                connConfig.get(\"username\"), connConfig.get(\"password\"), null);" + NL + "                        }" + NL + "" + NL + "                        private void initQueryObject(String queryText) {" + NL + "                            try {" + NL + "                                queryObject = (org.json.simple.JSONObject) parser.parse(queryText.replaceAll(\"'\", \"\\\"\"));" + NL + "                                org.json.simple.JSONObject select = (org.json.simple.JSONObject) queryObject.get(\"select\");" + NL + "                                orgStart = (Long) select.get(\"start\");" + NL + "                                orgLimit = (Long) select.get(\"limit\");" + NL + "                            } catch (org.json.simple.parser.ParseException e) {";
  protected final String TEXT_44 = NL + "                                throw new RuntimeException(\"Query text is not a valid JSON string.\", e);";
  protected final String TEXT_45 = NL + "                                e.printStackTrace();";
  protected final String TEXT_46 = NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        @SuppressWarnings(\"unchecked\")" + NL + "                        public org.json.simple.JSONObject getCountQuery() {" + NL + "                            org.json.simple.JSONObject countQuery = null;" + NL + "                            if (queryObject != null) {" + NL + "                                org.json.simple.JSONObject orgSelect = (org.json.simple.JSONObject) queryObject.get(\"select\");" + NL + "                                org.json.simple.JSONArray orgFrom = (org.json.simple.JSONArray) orgSelect.get(\"from\");" + NL + "                                org.json.simple.JSONObject orgWhere = (org.json.simple.JSONObject) orgSelect.get(\"where\");" + NL + "                                org.json.simple.JSONObject select = new org.json.simple.JSONObject();" + NL + "                                if (orgFrom != null)" + NL + "                                    select.put(\"from\", orgFrom);" + NL + "                                if (orgWhere != null)" + NL + "                                    select.put(\"where\", orgSelect.get(\"where\"));" + NL + "                                try {" + NL + "                                    select.put(\"fields\", parser.parse(\"[{\\\"count\\\":{}}]\"));" + NL + "                                } catch (org.json.simple.parser.ParseException e) {";
  protected final String TEXT_47 = NL + "                                    throw new RuntimeException(\"Failed to get count query JSON.\", e);";
  protected final String TEXT_48 = NL + "                                    e.printStackTrace();";
  protected final String TEXT_49 = NL + "                                }" + NL + "                                countQuery = new org.json.simple.JSONObject();" + NL + "                                countQuery.put(\"select\", select);" + NL + "                            }" + NL + "                            return countQuery;" + NL + "                        }" + NL + "" + NL + "                        public String getStickySession() {" + NL + "                            String stickySession = System.getProperty(\"sticky_session\");" + NL + "                            if(stickySession == null) {" + NL + "                                stickySession = \"JSESSIONID\";" + NL + "                            }" + NL + "                            return stickySession;" + NL + "                        }" + NL + "" + NL + "                        public String executeQuery(org.json.simple.JSONObject queryObj, boolean isCount) {" + NL + "                            webClient.path(\"data/\" + dataCluster + \"/query\").query(\"container\", containerType).type(\"application/json\");" + NL + "                            if(transactionId != null) {" + NL + "                                webClient.header(\"transaction-id\", transactionId);" + NL + "                            }" + NL + "                            if(sessionId != null) {" + NL + "                                webClient.header(\"Cookie\", getStickySession() + \"=\" + sessionId);" + NL + "                            }" + NL + "                            if (isCount) {" + NL + "                                webClient.accept(\"application/xml\");" + NL + "                            } else {" + NL + "                                if (retrieveRawData) {" + NL + "                                    webClient.accept(acceptType);" + NL + "                                } else {" + NL + "                                    webClient.accept(\"application/json\");" + NL + "                                }" + NL + "                            }";
  protected final String TEXT_50 = NL + "                            try {" + NL + "                                javax.ws.rs.core.Response response = webClient.put(queryObj.toJSONString());" + NL + "                                String queryResult = response.readEntity(String.class);" + NL + "                                int statusCode = response.getStatus();" + NL + "                                webClient.reset();" + NL + "                                if (statusCode == 200) {" + NL + "                                    return queryResult;" + NL + "                                } else {";
  protected final String TEXT_51 = NL + "                                    if (statusCode == 401) {" + NL + "                                        throw new RuntimeException(\"Unauthorized! Invalid username and password.\");" + NL + "                                    } else if (statusCode == 404) {" + NL + "                                        throw new RuntimeException(\"Not found! URL is not correct.\");" + NL + "                                    } else if (statusCode == 500 && org.apache.commons.lang.StringUtils.isBlank(dataCluster)) {" + NL + "                                        throw new RuntimeException(\"Data container is empty or null.\");" + NL + "                                    } else {" + NL + "                                        throw new RuntimeException(queryResult);" + NL + "                                    }";
  protected final String TEXT_52 = NL + "                                    if (statusCode == 401) {";
  protected final String TEXT_53 = NL + "                                    } else if (statusCode == 404) {";
  protected final String TEXT_54 = NL + "                                    } else if (statusCode == 500 && org.apache.commons.lang.StringUtils.isBlank(dataCluster)) {";
  protected final String TEXT_55 = NL + "                                    } else {";
  protected final String TEXT_56 = NL + "                                    }" + NL + "                                    return null;";
  protected final String TEXT_57 = NL + "                                }" + NL + "                            } catch(Exception e) {";
  protected final String TEXT_58 = NL + "                                throw new RuntimeException(\"Unexpected exception.\", e);";
  protected final String TEXT_59 = NL + "                                e.printStackTrace();" + NL + "                                return null;";
  protected final String TEXT_60 = NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        public int getTotalCount() {" + NL + "                            int totalCount = -1;" + NL + "                            org.json.simple.JSONObject countQuery = getCountQuery();" + NL + "                            if (countQuery != null) {" + NL + "                                String dbCountResult = executeQuery(countQuery, true);" + NL + "                                if (dbCountResult != null) {" + NL + "                                    int dbTotalCount = Integer.parseInt(dbCountResult.replaceAll(\"<results><result><count>\", \"\").replaceAll(\"</count></result></results>\", \"\"));" + NL + "                                    if (orgStart != null) {" + NL + "                                        dbTotalCount -= orgStart.intValue();" + NL + "                                    }" + NL + "                                    if (orgLimit == null || orgLimit > dbTotalCount) {" + NL + "                                        totalCount = dbTotalCount;" + NL + "                                    } else {" + NL + "                                        totalCount = orgLimit.intValue();" + NL + "                                    }" + NL + "                                }" + NL + "                            }" + NL + "                            return totalCount;" + NL + "                        }" + NL + "" + NL + "                        @SuppressWarnings(\"unchecked\")" + NL + "                        public String getPagedResult(int totalCount, int loopInd) {" + NL + "                            org.json.simple.JSONObject select = (org.json.simple.JSONObject)queryObject.get(\"select\");" + NL + "                            if(fetchSize > 0) {" + NL + "                                int start = fetchSize * loopInd + (orgStart == null ? 0 : orgStart.intValue());" + NL + "                                int limit = totalCount > fetchSize * (loopInd + 1) ? fetchSize : totalCount - fetchSize * loopInd;" + NL + "                                select.put(\"start\", start);" + NL + "                                select.put(\"limit\", limit);" + NL + "                            }" + NL + "                            return executeQuery(queryObject, false);" + NL + "                        }" + NL + "" + NL + "                        public int getTotalLoop(int totalCount) {" + NL + "                            if(totalCount <= 0) {" + NL + "                                return 0;" + NL + "                            } else if(fetchSize <= 0) {" + NL + "                                return 1;" + NL + "                            } else {" + NL + "                                return (int) Math.ceil((totalCount + fetchSize - 1) / fetchSize);" + NL + "                            }" + NL + "                        }" + NL + "                    }" + NL;
  protected final String TEXT_61 = NL + "                    class XMLStreamUnwrapper_";
  protected final String TEXT_62 = " implements java.util.Enumeration<String> {" + NL + "" + NL + "                        private static final int RECORD_LEVEL = 1;" + NL + "" + NL + "                        private javax.xml.stream.XMLEventReader reader;" + NL + "" + NL + "                        private ResettableStringWriter_";
  protected final String TEXT_63 = " stringWriter = new ResettableStringWriter_";
  protected final String TEXT_64 = "();" + NL + "" + NL + "                        private javax.xml.stream.XMLOutputFactory xmlOutputFactory;" + NL + "" + NL + "                        private int level = 0;" + NL + "" + NL + "                        public XMLStreamUnwrapper_";
  protected final String TEXT_65 = "(java.io.InputStream stream) {" + NL + "                            try {" + NL + "                                reader = javax.xml.stream.XMLInputFactory.newFactory().createXMLEventReader(stream);" + NL + "                                // Skip to first record" + NL + "                                while (reader.hasNext() && level < RECORD_LEVEL) {" + NL + "                                    final javax.xml.stream.events.XMLEvent event = reader.nextEvent();" + NL + "                                    if (event.isStartElement()) {" + NL + "                                        level++;" + NL + "                                    }" + NL + "                                }" + NL + "                                xmlOutputFactory = javax.xml.stream.XMLOutputFactory.newFactory();" + NL + "                            } catch (javax.xml.stream.XMLStreamException e) {";
  protected final String TEXT_66 = NL + "                                throw new RuntimeException(\"Unexpected parsing configuration error.\", e);";
  protected final String TEXT_67 = NL + "                                e.printStackTrace();";
  protected final String TEXT_68 = NL + "                            }" + NL + "                        }" + NL + "" + NL + "                        @Override" + NL + "                        public boolean hasMoreElements() {" + NL + "                            moveToNext();" + NL + "                            return stringWriter.getBuffer().length() > 0;" + NL + "                        }" + NL + "" + NL + "                        @Override" + NL + "                        public String nextElement() {" + NL + "                            return stringWriter.reset();" + NL + "                        }" + NL + "" + NL + "                        /**" + NL + "                         * Moves to next record in stream and stores it in {@link #stringWriter}." + NL + "                         */" + NL + "                        private void moveToNext() {" + NL + "                            try {" + NL + "                                javax.xml.stream.XMLStreamWriter writer = xmlOutputFactory.createXMLStreamWriter(stringWriter);" + NL + "                                boolean hasMadeChanges;" + NL + "                                do {" + NL + "                                    if (!reader.hasNext()) {" + NL + "                                        break;" + NL + "                                    }" + NL + "                                    hasMadeChanges = false; // Keep a state to skip line feeds" + NL + "                                    final javax.xml.stream.events.XMLEvent event = reader.nextEvent();" + NL + "                                    if (event.isEndElement()) {" + NL + "                                        level--;" + NL + "                                    } else if (event.isStartElement()) {" + NL + "                                        level++;" + NL + "                                    } else if (event.isEndDocument()) {" + NL + "                                        level--;" + NL + "                                    }" + NL + "                                    if (level >= RECORD_LEVEL) {" + NL + "                                        if (event.isEndElement()) {" + NL + "                                            writer.writeEndElement();" + NL + "                                            hasMadeChanges = true;" + NL + "                                        } else if (event.isStartElement()) {" + NL + "                                            final javax.xml.stream.events.StartElement startElement = event.asStartElement();" + NL + "                                            final javax.xml.namespace.QName name = startElement.getName();" + NL + "                                            writer.writeStartElement(name.getNamespaceURI(), name.getLocalPart());" + NL + "                                            // Declare namespaces (if any)" + NL + "                                            final java.util.Iterator namespaces = startElement.getNamespaces();" + NL + "                                            while (namespaces.hasNext()) {" + NL + "                                                javax.xml.stream.events.Namespace namespace = (javax.xml.stream.events.Namespace) namespaces.next();" + NL + "                                                writer.writeNamespace(namespace.getPrefix(), namespace.getNamespaceURI());" + NL + "                                            }" + NL + "                                            // Write attributes" + NL + "                                            final java.util.Iterator attributes = startElement.getAttributes();" + NL + "                                            while (attributes.hasNext()) {" + NL + "                                                javax.xml.stream.events.Attribute attribute = (javax.xml.stream.events.Attribute) attributes.next();" + NL + "                                                javax.xml.namespace.QName attributeName = attribute.getName();" + NL + "                                                String value = attribute.getValue();" + NL + "                                                if (attributeName.getNamespaceURI() != null && attributeName.getNamespaceURI().length() > 0) {" + NL + "                                                    writer.writeAttribute(attributeName.getLocalPart(), value);" + NL + "                                                } else {" + NL + "                                                    writer.writeAttribute(attributeName.getNamespaceURI(), attributeName.getLocalPart(), value);" + NL + "                                                }" + NL + "                                            }" + NL + "                                            hasMadeChanges = true;" + NL + "                                        } else if (event.isCharacters()) {" + NL + "                                            final String text = event.asCharacters().getData().trim();" + NL + "                                            if (!text.isEmpty()) {" + NL + "                                                writer.writeCharacters(text);" + NL + "                                                hasMadeChanges = true;" + NL + "                                            }" + NL + "                                        }" + NL + "                                    }" + NL + "                                } while (level > RECORD_LEVEL || !hasMadeChanges);" + NL + "                                writer.flush();" + NL + "                            } catch (javax.xml.stream.XMLStreamException e) {";
  protected final String TEXT_69 = NL + "                                throw new RuntimeException(\"Unexpected parsing exception.\", e);";
  protected final String TEXT_70 = NL + "                                e.printStackTrace();";
  protected final String TEXT_71 = NL + "                            }" + NL + "                        }" + NL + "                        class ResettableStringWriter_";
  protected final String TEXT_72 = " extends java.io.StringWriter {" + NL + "" + NL + "                            private java.io.StringWriter delegate = new java.io.StringWriter();" + NL + "" + NL + "                            private int currentLength = 0;" + NL + "" + NL + "                            private int maxLength = -1;" + NL + "" + NL + "                            public ResettableStringWriter_";
  protected final String TEXT_73 = "() {" + NL + "                            }" + NL + "" + NL + "                            public String reset() {" + NL + "                                if (currentLength > maxLength) {" + NL + "                                    maxLength = currentLength;" + NL + "                                }" + NL + "                                currentLength = 0;" + NL + "                                String result = delegate.toString();" + NL + "                                delegate = new java.io.StringWriter(maxLength);" + NL + "                                return result;" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void write(int c) {" + NL + "                                currentLength++;" + NL + "                                delegate.write(c);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void write(char[] cbuf, int off, int len) {" + NL + "                                currentLength += len;" + NL + "                                delegate.write(cbuf, off, len);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void write(String str) {" + NL + "                                currentLength += str.length();" + NL + "                                delegate.write(str);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void write(String str, int off, int len) {" + NL + "                                currentLength += len;" + NL + "                                delegate.write(str, off, len);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void write(char[] cbuf) throws java.io.IOException {" + NL + "                                currentLength += cbuf.length;" + NL + "                                delegate.write(cbuf);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public java.io.StringWriter append(CharSequence csq) {" + NL + "                                currentLength += csq.length();" + NL + "                                return delegate.append(csq);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public java.io.StringWriter append(CharSequence csq, int start, int end) {" + NL + "                                currentLength += csq.length();" + NL + "                                return delegate.append(csq, start, end);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public java.io.StringWriter append(char c) {" + NL + "                                currentLength++;" + NL + "                                return delegate.append(c);" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public String toString() {" + NL + "                                return delegate.toString();" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public StringBuffer getBuffer() {" + NL + "                                return delegate.getBuffer();" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void flush() {" + NL + "                                delegate.flush();" + NL + "                            }" + NL + "" + NL + "                            @Override" + NL + "                            public void close() throws java.io.IOException {" + NL + "                                delegate.close();" + NL + "                            }" + NL + "                        }" + NL + "                    }";
  protected final String TEXT_74 = NL + NL + "                    int nb_line_";
  protected final String TEXT_75 = " = 0;" + NL + "                    java.util.Map<String, String> connConfig_";
  protected final String TEXT_76 = " = new java.util.HashMap<String, String>();";
  protected final String TEXT_77 = NL + "                        String murl_";
  protected final String TEXT_78 = " = (String)globalMap.get(\"mdmUrl_";
  protected final String TEXT_79 = "\");" + NL + "                        if(murl_";
  protected final String TEXT_80 = ".endsWith(\"?wsdl\")) {" + NL + "                            murl_";
  protected final String TEXT_81 = " = murl_";
  protected final String TEXT_82 = ".substring(0, murl_";
  protected final String TEXT_83 = ".length() - 5);" + NL + "                        }" + NL + "                        String username_";
  protected final String TEXT_84 = " = (String)globalMap.get(\"username_";
  protected final String TEXT_85 = "\");" + NL + "                        String password_";
  protected final String TEXT_86 = " = (String)globalMap.get(\"password_";
  protected final String TEXT_87 = "\");" + NL + "" + NL + "                        connConfig_";
  protected final String TEXT_88 = ".put(\"url\", murl_";
  protected final String TEXT_89 = ".replace(\"/soap\", \"/rest\"));" + NL + "                        connConfig_";
  protected final String TEXT_90 = ".put(\"username\", username_";
  protected final String TEXT_91 = ");" + NL + "                        connConfig_";
  protected final String TEXT_92 = ".put(\"password\", password_";
  protected final String TEXT_93 = ");" + NL + "" + NL + "                        if((Boolean)globalMap.get(\"useTransaction_";
  protected final String TEXT_94 = "\")) {" + NL + "                            String transKey_";
  protected final String TEXT_95 = " = \"";
  protected final String TEXT_96 = "_\" + Thread.currentThread().getThreadGroup().getName();" + NL + "                            com.talend.mdm.transaction.client.MDMTransaction mdmTransaction_";
  protected final String TEXT_97 = " = (com.talend.mdm.transaction.client.MDMTransaction)globalMap.get(transKey_";
  protected final String TEXT_98 = ");" + NL + "                            if(mdmTransaction_";
  protected final String TEXT_99 = " == null){" + NL + "                                String turl_";
  protected final String TEXT_100 = " = com.talend.mdm.transaction.client.MDMTransactionClient.getMDMTransactionURL(murl_";
  protected final String TEXT_101 = ", true);" + NL + "                                if((Boolean)globalMap.get(\"useClientTranId_";
  protected final String TEXT_102 = "\")) {" + NL + "                                    String sessionID_";
  protected final String TEXT_103 = " = com.talend.mdm.transaction.client.MDMTransactionClient.getSessionID(turl_";
  protected final String TEXT_104 = ",username_";
  protected final String TEXT_105 = ",password_";
  protected final String TEXT_106 = ");" + NL + "                                    mdmTransaction_";
  protected final String TEXT_107 = " = new com.talend.mdm.transaction.client.MDMTransaction();" + NL + "                                    mdmTransaction_";
  protected final String TEXT_108 = ".setUrl(turl_";
  protected final String TEXT_109 = ");" + NL + "                                    mdmTransaction_";
  protected final String TEXT_110 = ".setId(\"";
  protected final String TEXT_111 = "_\" + java.util.UUID.randomUUID());" + NL + "                                    mdmTransaction_";
  protected final String TEXT_112 = ".setUsername(username_";
  protected final String TEXT_113 = ");" + NL + "                                    mdmTransaction_";
  protected final String TEXT_114 = ".setPassword(password_";
  protected final String TEXT_115 = ");" + NL + "                                    mdmTransaction_";
  protected final String TEXT_116 = ".setSessionId(sessionID_";
  protected final String TEXT_117 = ");" + NL + "                                } else {";
  protected final String TEXT_118 = NL + "                                    mdmTransaction_";
  protected final String TEXT_119 = " = com.talend.mdm.transaction.client.MDMTransactionClient.newTransaction(turl_";
  protected final String TEXT_120 = ",username_";
  protected final String TEXT_121 = ",password_";
  protected final String TEXT_122 = ");" + NL + "                                }" + NL + "                                globalMap.put(transKey_";
  protected final String TEXT_123 = ", mdmTransaction_";
  protected final String TEXT_124 = ");";
  protected final String TEXT_125 = NL + "                            }" + NL + "                            connConfig_";
  protected final String TEXT_126 = ".put(\"transactionId\", mdmTransaction_";
  protected final String TEXT_127 = ".getId());" + NL + "                            connConfig_";
  protected final String TEXT_128 = ".put(\"sessionId\", mdmTransaction_";
  protected final String TEXT_129 = ".getSessionId());" + NL + "                        }";
  protected final String TEXT_130 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_131 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_132 = ");";
  protected final String TEXT_133 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_134 = " = ";
  protected final String TEXT_135 = "; ";
  protected final String TEXT_136 = NL + "                        connConfig_";
  protected final String TEXT_137 = ".put(\"url\", ";
  protected final String TEXT_138 = ");" + NL + "                        connConfig_";
  protected final String TEXT_139 = ".put(\"username\", ";
  protected final String TEXT_140 = ");" + NL + "                        connConfig_";
  protected final String TEXT_141 = ".put(\"password\", decryptedPassword_";
  protected final String TEXT_142 = ");";
  protected final String TEXT_143 = NL + NL + "                    String dataCluster_";
  protected final String TEXT_144 = " = ";
  protected final String TEXT_145 = ";" + NL + "                    String containerType_";
  protected final String TEXT_146 = " = \"";
  protected final String TEXT_147 = "\";" + NL + "                    String queryText_";
  protected final String TEXT_148 = " = ";
  protected final String TEXT_149 = ";" + NL + "                    boolean retrieveRawData_";
  protected final String TEXT_150 = " = ";
  protected final String TEXT_151 = ";" + NL + "                    String acceptType_";
  protected final String TEXT_152 = " = \"";
  protected final String TEXT_153 = "\";" + NL + "                    int fetchSize_";
  protected final String TEXT_154 = " = ";
  protected final String TEXT_155 = ";" + NL + "" + NL + "                    QueryDelegater_";
  protected final String TEXT_156 = " queryDelegater_";
  protected final String TEXT_157 = " = new QueryDelegater_";
  protected final String TEXT_158 = "(connConfig_";
  protected final String TEXT_159 = ", dataCluster_";
  protected final String TEXT_160 = ", containerType_";
  protected final String TEXT_161 = ", queryText_";
  protected final String TEXT_162 = ", retrieveRawData_";
  protected final String TEXT_163 = ", acceptType_";
  protected final String TEXT_164 = ", fetchSize_";
  protected final String TEXT_165 = ");" + NL + "                    int totalCount_";
  protected final String TEXT_166 = " = queryDelegater_";
  protected final String TEXT_167 = ".getTotalCount();" + NL + "                    int totalLoop_";
  protected final String TEXT_168 = " = queryDelegater_";
  protected final String TEXT_169 = ".getTotalLoop(totalCount_";
  protected final String TEXT_170 = ");" + NL;
  protected final String TEXT_171 = NL + NL + "                    for(int loopInd_";
  protected final String TEXT_172 = " = 0; loopInd_";
  protected final String TEXT_173 = " < totalLoop_";
  protected final String TEXT_174 = "; loopInd_";
  protected final String TEXT_175 = " ++) {" + NL + "                        String pagedResult_";
  protected final String TEXT_176 = " = queryDelegater_";
  protected final String TEXT_177 = ".getPagedResult(totalCount_";
  protected final String TEXT_178 = ", loopInd_";
  protected final String TEXT_179 = ");";
  protected final String TEXT_180 = NL + "                        if(pagedResult_";
  protected final String TEXT_181 = " != null) {";
  protected final String TEXT_182 = NL + "                            java.util.List<String> items_";
  protected final String TEXT_183 = " = new java.util.ArrayList<String>();" + NL + "                            try {" + NL + "                                java.io.InputStream content_";
  protected final String TEXT_184 = " = new java.io.ByteArrayInputStream(pagedResult_";
  protected final String TEXT_185 = ".getBytes());" + NL + "                                XMLStreamUnwrapper_";
  protected final String TEXT_186 = " tokenizer_";
  protected final String TEXT_187 = " = new XMLStreamUnwrapper_";
  protected final String TEXT_188 = "(content_";
  protected final String TEXT_189 = ");" + NL + "                                while (tokenizer_";
  protected final String TEXT_190 = ".hasMoreElements()) {" + NL + "                                    items_";
  protected final String TEXT_191 = ".add(tokenizer_";
  protected final String TEXT_192 = ".nextElement());" + NL + "                                }" + NL + "                            } catch (Exception e) {";
  protected final String TEXT_193 = NL + "                                throw new RuntimeException(\"Parsing query XML result failed.\", e);";
  protected final String TEXT_194 = NL + "                                e.printStackTrace();";
  protected final String TEXT_195 = NL + "                            }";
  protected final String TEXT_196 = NL + "                            java.util.List<org.json.simple.JSONObject> items_";
  protected final String TEXT_197 = " = new java.util.ArrayList<org.json.simple.JSONObject>();" + NL + "                            org.json.simple.parser.JSONParser parser_";
  protected final String TEXT_198 = " = new org.json.simple.parser.JSONParser();" + NL + "                            try {" + NL + "                                org.json.simple.JSONArray array_";
  protected final String TEXT_199 = " = (org.json.simple.JSONArray) parser_";
  protected final String TEXT_200 = ".parse(pagedResult_";
  protected final String TEXT_201 = ");";
  protected final String TEXT_202 = NL + "                                for(Object obj_";
  protected final String TEXT_203 = " : array_";
  protected final String TEXT_204 = ") {" + NL + "                                    org.json.simple.JSONObject jsonObj_";
  protected final String TEXT_205 = " = (org.json.simple.JSONObject) obj_";
  protected final String TEXT_206 = ";" + NL + "                                    items_";
  protected final String TEXT_207 = ".add((org.json.simple.JSONObject)jsonObj_";
  protected final String TEXT_208 = ".get(jsonObj_";
  protected final String TEXT_209 = ".keySet().iterator().next()));" + NL + "                                }";
  protected final String TEXT_210 = NL + "                                items_";
  protected final String TEXT_211 = " = array_";
  protected final String TEXT_212 = ";";
  protected final String TEXT_213 = NL + "                            } catch (org.json.simple.parser.ParseException e) {";
  protected final String TEXT_214 = NL + "                                throw new RuntimeException(\"Parsing query JSON result failed.\", e);";
  protected final String TEXT_215 = NL + "                                e.printStackTrace();";
  protected final String TEXT_216 = NL + "                            }";
  protected final String TEXT_217 = NL + "                        for(int i_";
  protected final String TEXT_218 = "=0; i_";
  protected final String TEXT_219 = " < items_";
  protected final String TEXT_220 = ".size(); i_";
  protected final String TEXT_221 = "++) {" + NL + "                            nb_line_";
  protected final String TEXT_222 = " ++;";
  protected final String TEXT_223 = NL + "                            ";
  protected final String TEXT_224 = ".";
  protected final String TEXT_225 = " = items_";
  protected final String TEXT_226 = ".get(i_";
  protected final String TEXT_227 = ").toString();";
  protected final String TEXT_228 = NL + "                                String obj_";
  protected final String TEXT_229 = "_";
  protected final String TEXT_230 = " = null;" + NL + "                                if (items_";
  protected final String TEXT_231 = ".get(i_";
  protected final String TEXT_232 = ").containsKey(\"";
  protected final String TEXT_233 = "\")) {" + NL + "                                    obj_";
  protected final String TEXT_234 = "_";
  protected final String TEXT_235 = " = (String)items_";
  protected final String TEXT_236 = ".get(i_";
  protected final String TEXT_237 = ").get(\"";
  protected final String TEXT_238 = "\");" + NL + "                                } else if(items_";
  protected final String TEXT_239 = ".get(i_";
  protected final String TEXT_240 = ").containsKey(\"";
  protected final String TEXT_241 = "\")) {" + NL + "                                    obj_";
  protected final String TEXT_242 = "_";
  protected final String TEXT_243 = " = (String)items_";
  protected final String TEXT_244 = ".get(i_";
  protected final String TEXT_245 = ").get(\"";
  protected final String TEXT_246 = "\");" + NL + "                                } else {" + NL + "                                    obj_";
  protected final String TEXT_247 = "_";
  protected final String TEXT_248 = " = (String)items_";
  protected final String TEXT_249 = ".get(i_";
  protected final String TEXT_250 = ").get(\"";
  protected final String TEXT_251 = "\");" + NL + "                                }" + NL + "                                if(org.apache.commons.lang.StringUtils.isEmpty(obj_";
  protected final String TEXT_252 = "_";
  protected final String TEXT_253 = ")) {" + NL + "                                    obj_";
  protected final String TEXT_254 = "_";
  protected final String TEXT_255 = " = ";
  protected final String TEXT_256 = ";" + NL + "                                }";
  protected final String TEXT_257 = NL + "                                    ";
  protected final String TEXT_258 = ".";
  protected final String TEXT_259 = " = obj_";
  protected final String TEXT_260 = "_";
  protected final String TEXT_261 = ";";
  protected final String TEXT_262 = NL + "                                        ";
  protected final String TEXT_263 = ".";
  protected final String TEXT_264 = " = org.apache.commons.lang.StringUtils.isEmpty(obj_";
  protected final String TEXT_265 = "_";
  protected final String TEXT_266 = ") ? null : ParserUtils.parseTo_Date(obj_";
  protected final String TEXT_267 = "_";
  protected final String TEXT_268 = ", ";
  protected final String TEXT_269 = ");";
  protected final String TEXT_270 = NL + "                                        ";
  protected final String TEXT_271 = ".";
  protected final String TEXT_272 = " = org.apache.commons.lang.StringUtils.isEmpty(obj_";
  protected final String TEXT_273 = "_";
  protected final String TEXT_274 = ") ? null : ParserUtils.parseTo_";
  protected final String TEXT_275 = "(obj_";
  protected final String TEXT_276 = "_";
  protected final String TEXT_277 = ");";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
class BasicLogUtil{
    protected String cid  = "";
    protected org.talend.core.model.process.INode node = null;
    protected boolean log4jEnabled = false;
    private String logID = "";
    
    private BasicLogUtil(){}
    
    public BasicLogUtil(org.talend.core.model.process.INode node){
        this.node = node;
        String cidx = this.node.getUniqueName();
        if(cidx.matches("^.*?tAmazonAuroraOutput_\\d+_out$")){
             cidx = cidx.substring(0,cidx.length()-4);// 4 ==> "_out".length();
        }
        this.cid = cidx;
        this.log4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(this.node.getProcess(), "__LOG4J_ACTIVATE__"));
        this.log4jEnabled = this.log4jEnabled &&
                            this.node.getComponent().isLog4JEnabled() && !"JOBLET".equals(node.getComponent().getComponentType().toString());
        this.logID = this.cid;
    }
    
    public String var(String varName){
        return varName + "_" + this.cid;
    }
    public String str(String content){
        return "\"" + content + "\"";
    }
    
    public void info(String... message){
        log4j("info", message);
    }
    
    public void debug(String... message){
        log4j("debug", message);
    }
    
    public void warn(String... message){
        log4j("warn", message);
    }
    
    public void error(String... message){
        log4j("error", message);
    }
    
    public void fatal(String... message){
        log4j("fatal", message);
    }
    
    public void trace(String... message){
        log4j("trace", message);
    }
    java.util.List<String> checkableList = java.util.Arrays.asList(new String[]{"info", "debug", "trace"});     
    public void log4j(String level, String... messages){
        if(this.log4jEnabled){
            if(checkableList.contains(level)){
            
    stringBuffer.append(TEXT_2);
    stringBuffer.append(level.substring(0, 1).toUpperCase() + level.substring(1));
    stringBuffer.append(TEXT_3);
    
            }
            
    stringBuffer.append(TEXT_4);
    stringBuffer.append(level);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(logID);
    stringBuffer.append(TEXT_6);
    for(String message : messages){
    stringBuffer.append(TEXT_7);
    stringBuffer.append(message);
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    
        }
    }
    
    public boolean isActive(){
        return this.log4jEnabled;
    }
}

class LogUtil extends BasicLogUtil{
    
    private LogUtil(){
    }
    
    public LogUtil(org.talend.core.model.process.INode node){
        super(node);
    }
    
    public void startWork(){
        debug(str("Start to work."));
    }
    
    public void endWork(){
        debug(str("Done."));
    }
    
    public void logIgnoredException(String exception){
        warn(exception);
    }
    
    public void logPrintedException(String exception){
        error(exception);
    }
    
    public void logException(String exception){
        fatal(exception);
    }
    
    public void logCompSetting(){
        if(log4jEnabled){
        
    stringBuffer.append(TEXT_10);
    stringBuffer.append(var("log4jParamters"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(var("log4jParamters"));
    stringBuffer.append(TEXT_13);
    
            java.util.Set<org.talend.core.model.process.EParameterFieldType> ignoredParamsTypes = new java.util.HashSet<org.talend.core.model.process.EParameterFieldType>(); 
            ignoredParamsTypes.addAll(
                java.util.Arrays.asList(
                    org.talend.core.model.process.EParameterFieldType.SCHEMA_TYPE,
                    org.talend.core.model.process.EParameterFieldType.LABEL,
                    org.talend.core.model.process.EParameterFieldType.EXTERNAL,
                    org.talend.core.model.process.EParameterFieldType.MAPPING_TYPE,
                    org.talend.core.model.process.EParameterFieldType.IMAGE,
                    org.talend.core.model.process.EParameterFieldType.TNS_EDITOR,
                    org.talend.core.model.process.EParameterFieldType.WSDL2JAVA,
                    org.talend.core.model.process.EParameterFieldType.GENERATEGRAMMARCONTROLLER,
                    org.talend.core.model.process.EParameterFieldType.GENERATE_SURVIVORSHIP_RULES_CONTROLLER,
                    org.talend.core.model.process.EParameterFieldType.REFRESH_REPORTS,
                    org.talend.core.model.process.EParameterFieldType.BROWSE_REPORTS,
                    org.talend.core.model.process.EParameterFieldType.PALO_DIM_SELECTION,
                    org.talend.core.model.process.EParameterFieldType.GUESS_SCHEMA,
                    org.talend.core.model.process.EParameterFieldType.MATCH_RULE_IMEX_CONTROLLER,
                    org.talend.core.model.process.EParameterFieldType.MEMO_PERL,
                    org.talend.core.model.process.EParameterFieldType.DBTYPE_LIST,
                    org.talend.core.model.process.EParameterFieldType.VERSION,
                    org.talend.core.model.process.EParameterFieldType.TECHNICAL,
                    org.talend.core.model.process.EParameterFieldType.ICON_SELECTION,
                    org.talend.core.model.process.EParameterFieldType.JAVA_COMMAND,
                    org.talend.core.model.process.EParameterFieldType.TREE_TABLE,
                    org.talend.core.model.process.EParameterFieldType.VALIDATION_RULE_TYPE,
                    org.talend.core.model.process.EParameterFieldType.DCSCHEMA,
                    org.talend.core.model.process.EParameterFieldType.SURVIVOR_RELATION,
                    org.talend.core.model.process.EParameterFieldType.REST_RESPONSE_SCHEMA_TYPE
                    )
            );
            for(org.talend.core.model.process.IElementParameter ep : org.talend.core.model.utils.NodeUtil.getDisplayedParameters(node)){
                if(!ep.isLog4JEnabled() || ignoredParamsTypes.contains(ep.getFieldType())){
                    continue;
                }
                String name = ep.getName();
                if(org.talend.core.model.process.EParameterFieldType.PASSWORD.equals(ep.getFieldType())){
                    String epName = "__" + name + "__";
                    String password = "";
                    if(org.talend.core.model.process.ElementParameterParser.canEncrypt(node, epName)){
                        password = org.talend.core.model.process.ElementParameterParser.getEncryptedValue(node, epName);
                    }else{
                        String passwordValue = org.talend.core.model.process.ElementParameterParser.getValue(node, epName);
                        if (passwordValue == null || "".equals(passwordValue.trim())) {// for the value which empty
                            passwordValue = "\"\"";
                        } 
                        password = "routines.system.PasswordEncryptUtil.encryptPassword(" + passwordValue + ")";
                    } 
                    
    stringBuffer.append(TEXT_14);
    stringBuffer.append(var("log4jParamters"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(password);
    stringBuffer.append(TEXT_17);
    
                }else{
                    String value = org.talend.core.model.utils.NodeUtil.getNormalizeParameterValue(node, ep);
                    
    stringBuffer.append(TEXT_18);
    stringBuffer.append(var("log4jParamters"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(value);
    stringBuffer.append(TEXT_21);
    
                }   
                
    stringBuffer.append(TEXT_22);
    stringBuffer.append(var("log4jParamters"));
    stringBuffer.append(TEXT_23);
    
            }
        }
        debug(var("log4jParamters"));
    }
    
    //no use for now, because we log the data by rowStruct
    public void traceData(String rowStruct, java.util.List<org.talend.core.model.metadata.IMetadataColumn> columnList, String nbline){
        if(log4jEnabled){
        
    stringBuffer.append(TEXT_24);
    stringBuffer.append(var("log4jSb"));
    stringBuffer.append(TEXT_25);
    
            for(org.talend.core.model.metadata.IMetadataColumn column : columnList){
                org.talend.core.model.metadata.types.JavaType javaType = org.talend.core.model.metadata.types.JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                String columnName = column.getLabel();
                boolean isPrimit = org.talend.core.model.metadata.types.JavaTypesManager.isJavaPrimitiveType(column.getTalendType(), column.isNullable());
                if(isPrimit){
                
    stringBuffer.append(TEXT_26);
    stringBuffer.append(var("log4jSb"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_29);
    
                }else{
                
    stringBuffer.append(TEXT_30);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(var("log4jSb"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(var("log4jSb"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(rowStruct);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(columnName);
    stringBuffer.append(TEXT_38);
    
                }
                
    stringBuffer.append(TEXT_39);
    stringBuffer.append(var("log4jSb"));
    stringBuffer.append(TEXT_40);
    
            }
        }
        trace(str("Content of the record "), nbline, str(": "), var("log4jSb"));
        
    
    }
}

class LogHelper{
    
    java.util.Map<String, String> pastDict = null;
    
    public LogHelper(){
        pastDict = new java.util.HashMap<String, String>();
        pastDict.put("insert", "inserted");
        pastDict.put("update", "updated");
        pastDict.put("delete", "deleted");
        pastDict.put("upsert", "upserted");
    }   
    
    public String upperFirstChar(String data){ 
        return data.substring(0, 1).toUpperCase() + data.substring(1);
    }
    
    public String toPastTense(String data){
        return pastDict.get(data);
    }
}
LogHelper logHelper = new LogHelper();

LogUtil log = null;

    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
INode node = (INode)codeGenArgument.getArgument();
LogUtil logUtil = new LogUtil(node);
String cid = node.getUniqueName();
boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
boolean dieOnError = ("true").equals(ElementParameterParser.getValue(node,"__DIE_ON_ERROR__"));
List<IMetadataTable> metadatas = node.getMetadataList();
if ((metadatas != null) && (metadatas.size() > 0)) {
    IMetadataTable metadata = metadatas.get(0);

    if (metadata != null) {

        List<IMetadataColumn> columnList = metadata.getListColumns();
        List<? extends IConnection> outgoingConns = node.getOutgoingSortedConnections();

        // if output columns are defined
        if (outgoingConns != null && outgoingConns.size() > 0) {

            IConnection outgoingConn = outgoingConns.get(0);
            if(outgoingConn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) { // start 1
                boolean useExistingConn = ("true").equals(ElementParameterParser.getValue(node,"__USE_EXISTING_CONNECTION__"));
                String connection = ElementParameterParser.getValue(node,"__CONNECTION__");
                String conn = "TMDMService_" + connection;
                String trans = "mdmTrans_" + connection;
                String mdmUrl = ElementParameterParser.getValue(node, "__MDMURL__");
                String username = ElementParameterParser.getValue(node, "__USERNAME__");
                String dataCluster = ElementParameterParser.getValue(node, "__DATACLUSTER__");
                String containerType = ElementParameterParser.getValue(node, "__CONTAINER_TYPE__");
                boolean retrieveRawData = ("true").equals(ElementParameterParser.getValue(node,"__RETRIEVE_RAW_DATA__"));
                String xmlField = ElementParameterParser.getValue(node, "__XMLFIELD__");
                String acceptType = retrieveRawData ? ElementParameterParser.getValue(node, "__ACCEPT_TYPE__") : "application/json";
                boolean returnXml = ("application/xml").equals(acceptType);
                String queryText = ElementParameterParser.getValue(node, "__QUERY_TEXT__");
                String fetchSize = ElementParameterParser.getValue(node, "__FETCHSIZE__");
                
    stringBuffer.append(TEXT_41);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_43);
    if(dieOnError){
    stringBuffer.append(TEXT_44);
    }else{
     logUtil.error("\"Query text is not a valid JSON string.\"");
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    if(dieOnError){
    stringBuffer.append(TEXT_47);
    }else{
     logUtil.error("\"Failed to get count query JSON.\"");
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
     logUtil.debug("\"Execute query:\" + queryObj.toJSONString()");
    stringBuffer.append(TEXT_50);
    if(dieOnError){
    stringBuffer.append(TEXT_51);
    }else{
    stringBuffer.append(TEXT_52);
     logUtil.error("\"Unauthorized! Invalid username and password.\"");
    stringBuffer.append(TEXT_53);
     logUtil.error("\"Not found! URL is not correct.\"");
    stringBuffer.append(TEXT_54);
     logUtil.error("\"Data container is empty or null.\"");
    stringBuffer.append(TEXT_55);
     logUtil.error("queryResult");
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    if(dieOnError){
    stringBuffer.append(TEXT_58);
    }else{
     logUtil.error("\"Unexpected exception.\" + e.getMessage()");
    stringBuffer.append(TEXT_59);
    }
    stringBuffer.append(TEXT_60);
    if(retrieveRawData && returnXml){
    stringBuffer.append(TEXT_61);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_62);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_64);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_65);
    if(dieOnError){
    stringBuffer.append(TEXT_66);
    }else{
     logUtil.error("\"Unexpected parsing configuration error.\"+ e.getMessage()");
    stringBuffer.append(TEXT_67);
    }
    stringBuffer.append(TEXT_68);
    if(dieOnError){
    stringBuffer.append(TEXT_69);
    }else{
     logUtil.error("\"Unexpected parsing exception.\" + e.getMessage()");
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_73);
    }
    stringBuffer.append(TEXT_74);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_76);
    if(useExistingConn){
    stringBuffer.append(TEXT_77);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_78);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_80);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_84);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_85);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_86);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_87);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_88);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_90);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_91);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_94);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_95);
    stringBuffer.append(trans);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_100);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_101);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_104);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_105);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_108);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_111);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_114);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_116);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_117);
     logUtil.debug("\"Attempt to get a remote transaction from url: \" + murl_" + cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_120);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_123);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_124);
     logUtil.debug("\"Got transaction successfully with key=\" + transKey_" + cid);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_127);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_129);
    }else{
     String passwordFieldName = "__PASSWORD__"; 
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_134);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_135);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_137);
    stringBuffer.append(mdmUrl);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_139);
    stringBuffer.append(username );
    stringBuffer.append(TEXT_140);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_141);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_144);
    stringBuffer.append(dataCluster);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_146);
    stringBuffer.append(containerType);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(queryText);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(retrieveRawData);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_152);
    stringBuffer.append(acceptType);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_154);
    stringBuffer.append(fetchSize);
    stringBuffer.append(TEXT_155);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_156);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_157);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_158);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_160);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_162);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_163);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_164);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_166);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_167);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_169);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_170);
     logUtil.info("\"Get item count: \" + totalCount_" + cid);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_172);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_173);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_174);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_175);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_176);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_178);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_179);
     logUtil.debug("\"Query page\" + loopInd_" + cid + " + \" result from server:\" + pagedResult_" + cid);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_181);
    if(retrieveRawData && returnXml){
    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_183);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_184);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_185);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_186);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_188);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_189);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_190);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_191);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_192);
    if(dieOnError){
    stringBuffer.append(TEXT_193);
    }else{
     logUtil.error("\"Parsing query XML result failed.\" + e.getMessage()");
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(TEXT_195);
    }else{
    stringBuffer.append(TEXT_196);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_197);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_198);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_200);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_201);
    if(!retrieveRawData){
    stringBuffer.append(TEXT_202);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_204);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_205);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_206);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_208);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_209);
    }else{
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_211);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    if(dieOnError){
    stringBuffer.append(TEXT_214);
    }else{
     logUtil.error("\"Parsing query JSON result failed.\" + e.getMessage()");
    stringBuffer.append(TEXT_215);
    }
    stringBuffer.append(TEXT_216);
    }
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_218);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_222);
    if(retrieveRawData){
    stringBuffer.append(TEXT_223);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_224);
    stringBuffer.append(xmlField );
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_227);
    }else {
                            for(IMetadataColumn column : columnList){
                                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
                                JavaType javaType = JavaTypesManager.getJavaTypeFromId(column.getTalendType());
                                String patternValue = column.getPattern() == null || column.getPattern().trim().length() == 0 ? null : column.getPattern();
                                String defaultValue = column.getDefault();
                                String defaultValueToSet = (column.isNullable() || (defaultValue != null && defaultValue.length() > 0)) ? defaultValue : "\"" + JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate) + "\"";
                                String labelValue = column.getLabel();
                                String key1 = labelValue.toLowerCase(); // id
                                String key2 = "metadata:" + key1; // metadata:timestamp
                                String key3 = key2.replaceAll("_", ""); // metadata:taskid
                        
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_229);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_230);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_231);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_232);
    stringBuffer.append(key1);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_234);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_237);
    stringBuffer.append(key1);
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_240);
    stringBuffer.append(key2);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_242);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_245);
    stringBuffer.append(key2);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_247);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_248);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_250);
    stringBuffer.append(key3);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_252);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_254);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(defaultValueToSet);
    stringBuffer.append(TEXT_256);
     if(javaType == JavaTypesManager.STRING || javaType == JavaTypesManager.OBJECT) {//_1 
    stringBuffer.append(TEXT_257);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_258);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_260);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_261);
    } else {//_1
                                    if(javaType == JavaTypesManager.DATE) {//_2  
    stringBuffer.append(TEXT_262);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_263);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_265);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_267);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(patternValue);
    stringBuffer.append(TEXT_269);
    } else {//_2  
    stringBuffer.append(TEXT_270);
    stringBuffer.append(outgoingConn.getName() );
    stringBuffer.append(TEXT_271);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_273);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(typeToGenerate );
    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_276);
    stringBuffer.append(labelValue);
    stringBuffer.append(TEXT_277);
    }//_2
                                }//_1
                            }
                }
            }
        }
    }
}

    return stringBuffer.toString();
  }
}
