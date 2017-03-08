package org.talend.designer.codegen.translators.databases.oracle;

import org.talend.core.model.process.INode;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.metadata.builder.database.ExtractMetaDataUtils;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.metadata.types.JavaType;
import java.util.Map;
import java.util.List;

public class TOracleValidRowsBeginJava
{
  protected static String nl;
  public static synchronized TOracleValidRowsBeginJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TOracleValidRowsBeginJava result = new TOracleValidRowsBeginJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_4 = " - Retrieving records from the datasource.\");" + NL + "\t\t\t";
  protected final String TEXT_5 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_6 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_7 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_8 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_9 = " - Retrieved records count: \"+ globalMap.get(\"";
  protected final String TEXT_10 = "_NB_LINE\") + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_12 = " - Retrieved records count: \"+ nb_line_";
  protected final String TEXT_13 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_14 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_15 = " - Written records count: \" + nb_line_";
  protected final String TEXT_16 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\tfinal StringBuffer log4jSb_";
  protected final String TEXT_18 = " = new StringBuffer();" + NL + "\t\t\t";
  protected final String TEXT_19 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_20 = " - Retrieving the record \" + (nb_line_";
  protected final String TEXT_21 = ") + \".\");" + NL + "\t\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_23 = " - Writing the record \" + nb_line_";
  protected final String TEXT_24 = " + \" to the file.\");" + NL + "\t\t\t";
  protected final String TEXT_25 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_26 = " - Processing the record \" + nb_line_";
  protected final String TEXT_27 = " + \".\");" + NL + "\t\t\t";
  protected final String TEXT_28 = NL + "\t\t\t\tlog.debug(\"";
  protected final String TEXT_29 = " - Processed records count: \" + nb_line_";
  protected final String TEXT_30 = " + \" .\");" + NL + "\t\t\t";
  protected final String TEXT_31 = NL + "\t\t\t\tif(conn_";
  protected final String TEXT_32 = " != null) {" + NL + "\t\t\t\t\tif(conn_";
  protected final String TEXT_33 = ".getMetaData() != null) {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\tlog.debug(\"";
  protected final String TEXT_35 = " - Uses an existing connection ";
  protected final String TEXT_36 = ".\");" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_37 = NL + "\t\t\t\t\t\tlog.debug(\"";
  protected final String TEXT_38 = " - Uses an existing connection with username '\" + conn_";
  protected final String TEXT_39 = ".getMetaData().getUserName() + \"'. Connection URL: \" + conn_";
  protected final String TEXT_40 = ".getMetaData().getURL() + \".\");" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_41 = NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_42 = NL + "\t\t\tconn_";
  protected final String TEXT_43 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_44 = ", dbUser_";
  protected final String TEXT_45 = ", dbPwd_";
  protected final String TEXT_46 = ");" + NL + "\t\t\t";
  protected final String TEXT_47 = NL + "\t\t\tconn_";
  protected final String TEXT_48 = ".rollback();" + NL + "\t\t\t";
  protected final String TEXT_49 = NL + "\t\t\tconn_";
  protected final String TEXT_50 = ".commit();" + NL + "\t\t\t";
  protected final String TEXT_51 = NL + "\t\t\tconn_";
  protected final String TEXT_52 = ".close();" + NL + "\t\t\t";
  protected final String TEXT_53 = NL + "\t\t\t\tconn_";
  protected final String TEXT_54 = ".setAutoCommit(";
  protected final String TEXT_55 = ");" + NL + "\t\t\t";
  protected final String TEXT_56 = NL + "\t\t\t\tlog.";
  protected final String TEXT_57 = "(\"";
  protected final String TEXT_58 = " - \" + ";
  protected final String TEXT_59 = ".getMessage());" + NL + "\t\t\t";
  protected final String TEXT_60 = NL + "\t    \t\tlog.";
  protected final String TEXT_61 = "(\"";
  protected final String TEXT_62 = "\");" + NL + "\t\t\t";
  protected final String TEXT_63 = NL + "\t\t\t\tpstmt_";
  protected final String TEXT_64 = ".executeBatch();" + NL + "\t\t\t";
  protected final String TEXT_65 = NL + "\t\t\t\tint countSum_";
  protected final String TEXT_66 = " = 0;" + NL + "\t\t\t\tfor(int countEach_";
  protected final String TEXT_67 = ": pstmt_";
  protected final String TEXT_68 = ".executeBatch()) {" + NL + "\t\t\t\t\tcountSum_";
  protected final String TEXT_69 = " += (countEach_";
  protected final String TEXT_70 = " < 0 ? 0 : ";
  protected final String TEXT_71 = ");" + NL + "\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_72 = NL + "            java.util.Map<String, routines.system.TalendDataSource> dataSources_";
  protected final String TEXT_73 = " = (java.util.Map<String, routines.system.TalendDataSource>) globalMap.get(KEY_DB_DATASOURCES);" + NL + "            if (null != dataSources_";
  protected final String TEXT_74 = ") {";
  protected final String TEXT_75 = NL + "                conn_";
  protected final String TEXT_76 = " = dataSources_";
  protected final String TEXT_77 = ".get(";
  protected final String TEXT_78 = ").getConnection();" + NL + "            } else {";
  protected final String TEXT_79 = NL + "\t\t\t\tString driverClass_";
  protected final String TEXT_80 = " = \"";
  protected final String TEXT_81 = "\";" + NL + "\t\t\t    java.lang.Class.forName(driverClass_";
  protected final String TEXT_82 = ");" + NL + "\t\t\t   \tString dbUser_";
  protected final String TEXT_83 = " = ";
  protected final String TEXT_84 = ";" + NL + "\t\t\t   \t" + NL + "        \t\t";
  protected final String TEXT_85 = NL + "        \t\t" + NL + "        \t\t";
  protected final String TEXT_86 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_87 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_88 = ");";
  protected final String TEXT_89 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_90 = " = ";
  protected final String TEXT_91 = "; ";
  protected final String TEXT_92 = NL + "\t\t\t   \t" + NL + "\t\t        String dbPwd_";
  protected final String TEXT_93 = " = decryptedPassword_";
  protected final String TEXT_94 = ";" + NL + "\t\t        " + NL + "\t\t\t\t";
  protected final String TEXT_95 = NL + "\t\t\t\t";
  protected final String TEXT_96 = NL + "\t\t\t\tconn_";
  protected final String TEXT_97 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_98 = ",dbUser_";
  protected final String TEXT_99 = ",dbPwd_";
  protected final String TEXT_100 = ");";
  protected final String TEXT_101 = NL + "\t\t\t\tconn_";
  protected final String TEXT_102 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_103 = ");";
  protected final String TEXT_104 = NL + "\t\t        ";
  protected final String TEXT_105 = NL + "\t\t    }";
  protected final String TEXT_106 = NL + "\t\t\tjava.sql.Statement stmt_";
  protected final String TEXT_107 = " = conn_";
  protected final String TEXT_108 = ".createStatement();";
  protected final String TEXT_109 = NL + "\t\t\tjava.sql.Statement stmt_";
  protected final String TEXT_110 = " = conn_";
  protected final String TEXT_111 = ".createStatement(java.sql.ResultSet.TYPE_FORWARD_ONLY," + NL + "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tjava.sql.ResultSet.CONCUR_READ_ONLY);" + NL + "\t\t\t";
  protected final String TEXT_112 = NL + "\t\t\t\t\ttmpContent_";
  protected final String TEXT_113 = " = rs_";
  protected final String TEXT_114 = ".getString(";
  protected final String TEXT_115 = "column_index_";
  protected final String TEXT_116 = ");";
  protected final String TEXT_117 = NL + "                        if(tmpContent_";
  protected final String TEXT_118 = " != null) {" + NL + "                            tmpContent_";
  protected final String TEXT_119 = " = tmpContent_";
  protected final String TEXT_120 = ";" + NL + "                        }";
  protected final String TEXT_121 = NL + "                    if(tmpContent_";
  protected final String TEXT_122 = " != null && tmpContent_";
  protected final String TEXT_123 = ".length() > 0) {\t\t\t  \t";
  protected final String TEXT_124 = NL + "                        ";
  protected final String TEXT_125 = ".";
  protected final String TEXT_126 = " = tmpContent_";
  protected final String TEXT_127 = ".charAt(0);\t\t\t  \t\t" + NL + "                    } else {\t\t\t  \t";
  protected final String TEXT_128 = "\t\t\t  \t    " + NL + "                            if(tmpContent_";
  protected final String TEXT_129 = " == null) {\t\t\t  \t   \t";
  protected final String TEXT_130 = NL + "                                ";
  protected final String TEXT_131 = ".";
  protected final String TEXT_132 = " = null;\t\t\t  \t\t\t" + NL + "                            } else {\t\t\t  \t\t";
  protected final String TEXT_133 = NL + "                                ";
  protected final String TEXT_134 = ".";
  protected final String TEXT_135 = " = '\\0';\t\t\t  \t\t\t" + NL + "                            }";
  protected final String TEXT_136 = "\t\t\t  \t\t" + NL + "                            if((\"\").equals(tmpContent_";
  protected final String TEXT_137 = ")) {\t\t\t  \t\t";
  protected final String TEXT_138 = NL + "                                ";
  protected final String TEXT_139 = ".";
  protected final String TEXT_140 = " = '\\0';\t\t\t  \t\t\t" + NL + "                            } else {" + NL + "        \t\t\t  \t\t\tthrow new RuntimeException(" + NL + "        \t\t\t\t\t\t\t\"Value is empty for column : '";
  protected final String TEXT_141 = "' in '";
  protected final String TEXT_142 = "' connection, value is invalid or this column should be nullable or have a default value.\");\t\t\t\t\t\t\t" + NL + "                            }\t\t\t  \t\t";
  protected final String TEXT_143 = NL + "                    }";
  protected final String TEXT_144 = NL + "\t\t\t";
  protected final String TEXT_145 = ".";
  protected final String TEXT_146 = " = routines.system.JDBCUtil.getDate(rs_";
  protected final String TEXT_147 = ", ";
  protected final String TEXT_148 = "column_index_";
  protected final String TEXT_149 = ");";
  protected final String TEXT_150 = NL + "        \t";
  protected final String TEXT_151 = ".";
  protected final String TEXT_152 = " = routines.system.JDBCUtil.getString(rs_";
  protected final String TEXT_153 = ", ";
  protected final String TEXT_154 = "column_index_";
  protected final String TEXT_155 = ", ";
  protected final String TEXT_156 = "false";
  protected final String TEXT_157 = "true";
  protected final String TEXT_158 = ");";
  protected final String TEXT_159 = NL + "            if(rs_";
  protected final String TEXT_160 = ".getObject(";
  protected final String TEXT_161 = "column_index_";
  protected final String TEXT_162 = ") != null) {";
  protected final String TEXT_163 = NL + "                ";
  protected final String TEXT_164 = ".";
  protected final String TEXT_165 = " = rs_";
  protected final String TEXT_166 = ".get";
  protected final String TEXT_167 = "(";
  protected final String TEXT_168 = "column_index_";
  protected final String TEXT_169 = ");" + NL + "            } else {";
  protected final String TEXT_170 = NL + "                    ";
  protected final String TEXT_171 = ".";
  protected final String TEXT_172 = " = null;";
  protected final String TEXT_173 = NL + "                    throw new RuntimeException(\"Null value in non-Nullable column\");";
  protected final String TEXT_174 = NL + "            }";
  protected final String TEXT_175 = NL;
  protected final String TEXT_176 = NL;
  protected final String TEXT_177 = NL + "\t\t\t\tString url_";
  protected final String TEXT_178 = " = null;";
  protected final String TEXT_179 = NL + "\t\t\t\t\turl_";
  protected final String TEXT_180 = " = ";
  protected final String TEXT_181 = ";\t\t";
  protected final String TEXT_182 = NL + "\t\t\t\t\turl_";
  protected final String TEXT_183 = " = \"jdbc:oracle:thin:@\" + ";
  protected final String TEXT_184 = " + \":\" + ";
  protected final String TEXT_185 = " + \":\" + ";
  protected final String TEXT_186 = ";";
  protected final String TEXT_187 = NL + "\t\t\t\t\turl_";
  protected final String TEXT_188 = " = \"jdbc:oracle:thin:@(description=(address=(protocol=tcp)(host=\" + ";
  protected final String TEXT_189 = " + \")(port=\" + ";
  protected final String TEXT_190 = " + \"))(connect_data=(service_name=\" + ";
  protected final String TEXT_191 = " + \")))\";";
  protected final String TEXT_192 = NL + "\t\t\t\t\turl_";
  protected final String TEXT_193 = " = \"jdbc:oracle:oci8:@\" + ";
  protected final String TEXT_194 = ";";
  protected final String TEXT_195 = NL + "\t\t\t\t\turl_";
  protected final String TEXT_196 = " = ";
  protected final String TEXT_197 = ";";
  protected final String TEXT_198 = NL;
  protected final String TEXT_199 = NL + "\t\t\tString driverClass_";
  protected final String TEXT_200 = " = \"";
  protected final String TEXT_201 = "\";" + NL + "\t\t\tjava.lang.Class.forName(driverClass_";
  protected final String TEXT_202 = ");" + NL + "\t\t\t";
  protected final String TEXT_203 = NL + "\t\t\tString dbUser_";
  protected final String TEXT_204 = " = ";
  protected final String TEXT_205 = ";" + NL + "\t\t\t";
  protected final String TEXT_206 = NL + "            ";
  protected final String TEXT_207 = " " + NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_208 = " = routines.system.PasswordEncryptUtil.decryptPassword(";
  protected final String TEXT_209 = ");";
  protected final String TEXT_210 = NL + "\tfinal String decryptedPassword_";
  protected final String TEXT_211 = " = ";
  protected final String TEXT_212 = "; ";
  protected final String TEXT_213 = NL + "   \t\t\t" + NL + "\t\t\t" + NL + "\t\t\tString dbPwd_";
  protected final String TEXT_214 = " = decryptedPassword_";
  protected final String TEXT_215 = ";";
  protected final String TEXT_216 = NL + "\t\t\tconn_";
  protected final String TEXT_217 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_218 = ");";
  protected final String TEXT_219 = NL + "\t\t\tconn_";
  protected final String TEXT_220 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_221 = ",dbUser_";
  protected final String TEXT_222 = ",dbPwd_";
  protected final String TEXT_223 = ");";
  protected final String TEXT_224 = NL + "\t\t\tjava.util.Properties atnParamsPrope_";
  protected final String TEXT_225 = " = new java.util.Properties();" + NL + "\t\t\tatnParamsPrope_";
  protected final String TEXT_226 = ".put(\"user\",dbUser_";
  protected final String TEXT_227 = ");" + NL + "\t\t\tatnParamsPrope_";
  protected final String TEXT_228 = ".put(\"password\",dbPwd_";
  protected final String TEXT_229 = ");" + NL + "\t\t\tif(";
  protected final String TEXT_230 = " != null && !\"\\\"\\\"\".equals(";
  protected final String TEXT_231 = ") && !\"\".equals(";
  protected final String TEXT_232 = ")){" + NL + "                 atnParamsPrope_";
  protected final String TEXT_233 = ".load(new java.io.ByteArrayInputStream(";
  protected final String TEXT_234 = ".replace(\"&\", \"\\n\").getBytes()));" + NL + "            }" + NL + "\t\t\tconn_";
  protected final String TEXT_235 = " = java.sql.DriverManager.getConnection(url_";
  protected final String TEXT_236 = ", atnParamsPrope_";
  protected final String TEXT_237 = ");";
  protected final String TEXT_238 = NL + "\t\t\t\tjava.sql.Statement stmtGetTZ_";
  protected final String TEXT_239 = " = conn_";
  protected final String TEXT_240 = ".createStatement();" + NL + "\t\t\t\tjava.sql.ResultSet rsGetTZ_";
  protected final String TEXT_241 = " = stmtGetTZ_";
  protected final String TEXT_242 = ".executeQuery(\"select sessiontimezone from dual\");" + NL + "\t\t\t\tString sessionTimezone_";
  protected final String TEXT_243 = " = java.util.TimeZone.getDefault().getID();" + NL + "\t\t\t\twhile (rsGetTZ_";
  protected final String TEXT_244 = ".next()) {" + NL + "\t\t\t\t\tsessionTimezone_";
  protected final String TEXT_245 = " = rsGetTZ_";
  protected final String TEXT_246 = ".getString(1);" + NL + "\t\t\t\t}" + NL + "\t\t\t\t((oracle.jdbc.OracleConnection)conn_";
  protected final String TEXT_247 = ").setSessionTimeZone(sessionTimezone_";
  protected final String TEXT_248 = ");" + NL + "\t\t\t";
  protected final String TEXT_249 = NL + "\t\t\t\tif(((oracle.jdbc.OracleConnection)conn_";
  protected final String TEXT_250 = ").getSessionTimeZone() == null){" + NL + "\t\t\t\t\tjava.sql.Statement stmtGetTZ_";
  protected final String TEXT_251 = " = conn_";
  protected final String TEXT_252 = ".createStatement();" + NL + "\t\t\t\t\tjava.sql.ResultSet rsGetTZ_";
  protected final String TEXT_253 = " = stmtGetTZ_";
  protected final String TEXT_254 = ".executeQuery(\"select sessiontimezone from dual\");" + NL + "\t\t\t\t\tString sessionTimezone_";
  protected final String TEXT_255 = " = java.util.TimeZone.getDefault().getID();" + NL + "\t\t\t\t\twhile (rsGetTZ_";
  protected final String TEXT_256 = ".next()) {" + NL + "\t\t\t\t\t\tsessionTimezone_";
  protected final String TEXT_257 = " = rsGetTZ_";
  protected final String TEXT_258 = ".getString(1);" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t\t((oracle.jdbc.OracleConnection)conn_";
  protected final String TEXT_259 = ").setSessionTimeZone(sessionTimezone_";
  protected final String TEXT_260 = ");" + NL + "\t\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_261 = NL + "\t\t\t\tstmt_";
  protected final String TEXT_262 = ".setFetchSize(";
  protected final String TEXT_263 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_264 = NL + "\t\t\t\tjava.io.Reader reader_";
  protected final String TEXT_265 = " = rs_";
  protected final String TEXT_266 = ".getCharacterStream(";
  protected final String TEXT_267 = ");" + NL + "\t\t\t\tjava.io.BufferedReader br_";
  protected final String TEXT_268 = " = null;" + NL + "\t\t\t\tif(reader_";
  protected final String TEXT_269 = " != null) {" + NL + "\t\t\t\t\tbr_";
  protected final String TEXT_270 = " = new java.io.BufferedReader(reader_";
  protected final String TEXT_271 = ");\t\t\t\t \t\t\t\t\t\t\t" + NL + " \t\t\t\t\tStringBuffer content_";
  protected final String TEXT_272 = " = new StringBuffer();" + NL + " \t\t\t\t\tint c_";
  protected final String TEXT_273 = " =0;" + NL + " \t\t\t\t\twhile( (c_";
  protected final String TEXT_274 = " = br_";
  protected final String TEXT_275 = ".read()) != -1) {" + NL + " \t\t\t\t\t\tcontent_";
  protected final String TEXT_276 = ".append((char)c_";
  protected final String TEXT_277 = ");" + NL + "\t\t\t\t\t}" + NL + " \t\t\t\t\tbr_";
  protected final String TEXT_278 = ".close();" + NL + "\t\t\t\t\t";
  protected final String TEXT_279 = ".";
  protected final String TEXT_280 = " = content_";
  protected final String TEXT_281 = ".toString();" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\t";
  protected final String TEXT_282 = ".";
  protected final String TEXT_283 = " = null;" + NL + "\t\t\t\t}\t\t\t\t \t\t\t\t\t\t";
  protected final String TEXT_284 = NL + "\t\t\t \t\ttmpContent_";
  protected final String TEXT_285 = " = rs_";
  protected final String TEXT_286 = ".getString(";
  protected final String TEXT_287 = ");" + NL + "\t\t\t\t\tif(tmpContent_";
  protected final String TEXT_288 = " != null) {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_289 = ".";
  protected final String TEXT_290 = " = tmpContent_";
  protected final String TEXT_291 = ";" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_292 = ".";
  protected final String TEXT_293 = " = null;" + NL + "\t\t\t\t\t}\t\t\t\t \t\t\t\t\t\t";
  protected final String TEXT_294 = NL + "\t\t\tbyte [] tmpBytes_";
  protected final String TEXT_295 = " = rs_";
  protected final String TEXT_296 = ".getBytes(";
  protected final String TEXT_297 = ");" + NL + "\t\t\tif(tmpBytes_";
  protected final String TEXT_298 = " != null) {" + NL + "\t\t\t\t";
  protected final String TEXT_299 = ".";
  protected final String TEXT_300 = " = tmpBytes_";
  protected final String TEXT_301 = ";" + NL + "\t\t\t} else {" + NL + "\t\t\t\t";
  protected final String TEXT_302 = ".";
  protected final String TEXT_303 = " = null;" + NL + "\t\t\t}\t\t";
  protected final String TEXT_304 = NL + "\t\t\tjava.sql.Timestamp timestamp = rs_";
  protected final String TEXT_305 = ".getTimestamp(";
  protected final String TEXT_306 = ");" + NL + "\t\t\tif(timestamp != null) {" + NL + "\t\t\t\t";
  protected final String TEXT_307 = ".";
  protected final String TEXT_308 = " = new java.util.Date(timestamp.getTime());" + NL + "\t\t\t} else {" + NL + "\t\t\t\t";
  protected final String TEXT_309 = ".";
  protected final String TEXT_310 = " =  null;" + NL + "\t\t\t}\t\t\t ";
  protected final String TEXT_311 = NL + "\t\t\t\tObject tmpObj_";
  protected final String TEXT_312 = "_";
  protected final String TEXT_313 = " = rs_";
  protected final String TEXT_314 = ".getObject(";
  protected final String TEXT_315 = ");" + NL + "\t\t\t\tif(tmpObj_";
  protected final String TEXT_316 = "_";
  protected final String TEXT_317 = " != null) {";
  protected final String TEXT_318 = NL + "\t\t\t\t\t";
  protected final String TEXT_319 = ".";
  protected final String TEXT_320 = " = oracle.xdb.XMLType.createXML((oracle.sql.OPAQUE) tmpObj_";
  protected final String TEXT_321 = "_";
  protected final String TEXT_322 = ").getStringVal();";
  protected final String TEXT_323 = NL + "\t\t\t\t\t";
  protected final String TEXT_324 = ".";
  protected final String TEXT_325 = " = tmpObj_";
  protected final String TEXT_326 = "_";
  protected final String TEXT_327 = ";";
  protected final String TEXT_328 = NL + "\t\t\t\t} else {";
  protected final String TEXT_329 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_330 = ".";
  protected final String TEXT_331 = " = null;";
  protected final String TEXT_332 = "\t" + NL + "\t\t\t\t\t\tthrow new RuntimeException(\"Null value in non-Nullable column\");";
  protected final String TEXT_333 = NL + "\t\t\t\t}";
  protected final String TEXT_334 = NL + "\t\t\t\t\t";
  protected final String TEXT_335 = ".";
  protected final String TEXT_336 = " = rs_";
  protected final String TEXT_337 = ".get";
  protected final String TEXT_338 = "(";
  protected final String TEXT_339 = ");" + NL + "\t\t\t\t\t";
  protected final String TEXT_340 = NL + "\t\t\t\t\t\tif(!";
  protected final String TEXT_341 = ".";
  protected final String TEXT_342 = " && rs_";
  protected final String TEXT_343 = ".getObject(";
  protected final String TEXT_344 = ") == null){" + NL + "\t\t\t\t\t";
  protected final String TEXT_345 = NL + "\t\t\t\t\t\tif(";
  protected final String TEXT_346 = ".";
  protected final String TEXT_347 = " == null){" + NL + "\t\t\t\t\t";
  protected final String TEXT_348 = NL + "\t\t\t\t\t\tif(";
  protected final String TEXT_349 = ".";
  protected final String TEXT_350 = " == 0 && rs_";
  protected final String TEXT_351 = ".getObject(";
  protected final String TEXT_352 = ") == null){" + NL + "\t\t\t\t\t";
  protected final String TEXT_353 = NL + "\t\t\t\t\t\tif(rs_";
  protected final String TEXT_354 = ".getObject(";
  protected final String TEXT_355 = ") == null){" + NL + "\t\t\t\t\t";
  protected final String TEXT_356 = NL + "\t\t\t\t";
  protected final String TEXT_357 = NL + "\t\t\t\t\tif(rs_";
  protected final String TEXT_358 = ".getObject(";
  protected final String TEXT_359 = ") != null) {" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_360 = ".";
  protected final String TEXT_361 = " = rs_";
  protected final String TEXT_362 = ".get";
  protected final String TEXT_363 = "(";
  protected final String TEXT_364 = ");" + NL + "\t\t\t\t\t} else {" + NL + "\t\t\t\t";
  protected final String TEXT_365 = NL + "\t\t\t\t\t\t";
  protected final String TEXT_366 = ".";
  protected final String TEXT_367 = " = null;";
  protected final String TEXT_368 = "\t" + NL + " \t\t\t\t\t\tthrow new RuntimeException(\"Null value in non-Nullable column\");";
  protected final String TEXT_369 = NL + "\t\t\t\t\t}";
  protected final String TEXT_370 = NL;
  protected final String TEXT_371 = NL + "\t\t    int nb_line_";
  protected final String TEXT_372 = " = 0;" + NL + "\t\t    java.sql.Connection conn_";
  protected final String TEXT_373 = " = null;";
  protected final String TEXT_374 = NL + "\t\t        conn_";
  protected final String TEXT_375 = " = (java.sql.Connection)globalMap.get(\"";
  protected final String TEXT_376 = "\");";
  protected final String TEXT_377 = NL + "\t\t        \tif (null == conn_";
  protected final String TEXT_378 = ") {" + NL + "\t\t\t\t\t\tjava.util.Map<String, routines.system.TalendDataSource> dataSources_";
  protected final String TEXT_379 = " = (java.util.Map<String, routines.system.TalendDataSource>) globalMap.get(KEY_DB_DATASOURCES);" + NL + "\t\t\t\t\t\tconn_";
  protected final String TEXT_380 = " = dataSources_";
  protected final String TEXT_381 = ".get(";
  protected final String TEXT_382 = ").getConnection();" + NL + "\t\t\t\t\t\t//globalMap.put(\"";
  protected final String TEXT_383 = "\", conn_";
  protected final String TEXT_384 = ");" + NL + "\t\t        \t}" + NL + "\t\t        ";
  protected final String TEXT_385 = NL + "\t\t\t\t";
  protected final String TEXT_386 = NL + "\t\t\t\t";
  protected final String TEXT_387 = NL + "             boolean currentAutoCommitMode";
  protected final String TEXT_388 = " =  conn_";
  protected final String TEXT_389 = ".getAutoCommit();" + NL + "             conn_";
  protected final String TEXT_390 = ".setAutoCommit(true);" + NL + "             try{";
  protected final String TEXT_391 = NL + "                           try{" + NL + "                               conn_";
  protected final String TEXT_392 = ".createStatement().execute(\"BEGIN QUERY LOGGING WITH SQL,OBJECTS ON \" + ";
  protected final String TEXT_393 = ");" + NL + "                           }catch(Exception e){" + NL + "                                 System.err.println(e.getMessage());" + NL + "                           }";
  protected final String TEXT_394 = NL + "                 conn_";
  protected final String TEXT_395 = ".createStatement().execute(" + NL + "              \"SET QUERY_BAND='\" +";
  protected final String TEXT_396 = NL + "               ";
  protected final String TEXT_397 = " + \"=\" + ";
  protected final String TEXT_398 = " + \";\" +";
  protected final String TEXT_399 = " \"' FOR SESSION\"" + NL + "           );" + NL + "              }catch(Exception e){" + NL + "               System.err.println(e.getMessage());" + NL + "      }" + NL + "      conn_";
  protected final String TEXT_400 = ".setAutoCommit(currentAutoCommitMode";
  protected final String TEXT_401 = ");";
  protected final String TEXT_402 = NL + "\t\t    ";
  protected final String TEXT_403 = NL + NL + "\t\t    String dbquery_";
  protected final String TEXT_404 = " = ";
  protected final String TEXT_405 = ";" + NL + "\t\t\t";
  protected final String TEXT_406 = NL + "                log.debug(\"";
  protected final String TEXT_407 = " - Executing the query: '\"+dbquery_";
  protected final String TEXT_408 = "+\"'.\");" + NL + "\t\t\t";
  protected final String TEXT_409 = NL;
  protected final String TEXT_410 = NL + "                       globalMap.put(\"";
  protected final String TEXT_411 = "_QUERY\",dbquery_";
  protected final String TEXT_412 = ");";
  protected final String TEXT_413 = NL + "                       globalMap.put(\"";
  protected final String TEXT_414 = "_QUERY\",dbquery_";
  protected final String TEXT_415 = ");";
  protected final String TEXT_416 = NL + NL + "\t\t    java.sql.ResultSet rs_";
  protected final String TEXT_417 = " = null;" + NL + "\t\ttry{" + NL + "\t\t    rs_";
  protected final String TEXT_418 = " = stmt_";
  protected final String TEXT_419 = ".executeQuery(dbquery_";
  protected final String TEXT_420 = ");" + NL + "\t\t    java.sql.ResultSetMetaData rsmd_";
  protected final String TEXT_421 = " = rs_";
  protected final String TEXT_422 = ".getMetaData();" + NL + "\t\t    int colQtyInRs_";
  protected final String TEXT_423 = " = rsmd_";
  protected final String TEXT_424 = ".getColumnCount();" + NL;
  protected final String TEXT_425 = NL + "\t\t    routines.system.Dynamic dcg_";
  protected final String TEXT_426 = " =  new routines.system.Dynamic();" + NL + "\t\t    dcg_";
  protected final String TEXT_427 = ".setDbmsId(\"";
  protected final String TEXT_428 = "\");" + NL + "\t\t    List<String> listSchema_";
  protected final String TEXT_429 = "=new java.util.ArrayList<String>();" + NL + "\t\t    ";
  protected final String TEXT_430 = NL + "\t\t    \t    listSchema_";
  protected final String TEXT_431 = ".add(\"";
  protected final String TEXT_432 = "\");" + NL + "\t    \t    ";
  protected final String TEXT_433 = NL + NL + "\t\t\tint fixedColumnCount_";
  protected final String TEXT_434 = " = ";
  protected final String TEXT_435 = ";" + NL + "" + NL + "            for (int i = ";
  protected final String TEXT_436 = "; i <= rsmd_";
  protected final String TEXT_437 = ".getColumnCount()-";
  protected final String TEXT_438 = "; i++) {" + NL + "                if (!(listSchema_";
  protected final String TEXT_439 = ".contains(rsmd_";
  protected final String TEXT_440 = ".getColumnLabel(i).toUpperCase()) )) {" + NL + "                \troutines.system.DynamicMetadata dcm_";
  protected final String TEXT_441 = "=new routines.system.DynamicMetadata();" + NL + "                \tdcm_";
  protected final String TEXT_442 = ".setName(rsmd_";
  protected final String TEXT_443 = ".getColumnLabel(i));" + NL + "                \tdcm_";
  protected final String TEXT_444 = ".setDbName(rsmd_";
  protected final String TEXT_445 = ".getColumnName(i));" + NL + "                \tdcm_";
  protected final String TEXT_446 = ".setType(routines.system.Dynamic.getTalendTypeFromDBType(\"";
  protected final String TEXT_447 = "\", rsmd_";
  protected final String TEXT_448 = ".getColumnTypeName(i).toUpperCase(), rsmd_";
  protected final String TEXT_449 = ".getPrecision(i), rsmd_";
  protected final String TEXT_450 = ".getScale(i)));" + NL + "                \tdcm_";
  protected final String TEXT_451 = ".setDbType(rsmd_";
  protected final String TEXT_452 = ".getColumnTypeName(i));" + NL + "                \tdcm_";
  protected final String TEXT_453 = ".setDbTypeId(rsmd_";
  protected final String TEXT_454 = ".getColumnType(i));";
  protected final String TEXT_455 = NL + "                \tdcm_";
  protected final String TEXT_456 = ".setFormat(";
  protected final String TEXT_457 = ");";
  protected final String TEXT_458 = NL + "\t\t\tif(\"LONG\".equals(rsmd_";
  protected final String TEXT_459 = ".getColumnTypeName(i).toUpperCase())) {" + NL + "\t\t\t\tString length = MetadataTalendType.getDefaultDBTypes(\"oracle_id\", \"LONG\", MetadataTalendType.DEFAULT_LENGTH);" + NL + "\t\t\t\tif(length!=null && !(\"\".equals(length))) {" + NL + "\t\t\t\t\tdcm_";
  protected final String TEXT_460 = ".setLength(Integer.parseInt(length));" + NL + "\t\t\t\t} else {" + NL + "\t\t\t\t\tdcm_";
  protected final String TEXT_461 = ".setLength(rsmd_";
  protected final String TEXT_462 = ".getPrecision(i));" + NL + "\t\t\t\t}" + NL + "\t\t\t} else {" + NL + "\t\t\t\tdcm_";
  protected final String TEXT_463 = ".setLength(rsmd_";
  protected final String TEXT_464 = ".getPrecision(i));" + NL + "\t\t\t}";
  protected final String TEXT_465 = NL + "\t\t\tdcm_";
  protected final String TEXT_466 = ".setLength(rsmd_";
  protected final String TEXT_467 = ".getPrecision(i));";
  protected final String TEXT_468 = NL + "                \tdcm_";
  protected final String TEXT_469 = ".setPrecision(rsmd_";
  protected final String TEXT_470 = ".getScale(i));" + NL + "                \tdcm_";
  protected final String TEXT_471 = ".setNullable(rsmd_";
  protected final String TEXT_472 = ".isNullable(i) == 0 ? false : true);" + NL + "                \tdcm_";
  protected final String TEXT_473 = ".setKey(false);" + NL + "                \tdcm_";
  protected final String TEXT_474 = ".setSourceType(DynamicMetadata.sourceTypes.database);" + NL + "                \tdcm_";
  protected final String TEXT_475 = ".setColumnPosition(i);" + NL + "                \tdcg_";
  protected final String TEXT_476 = ".metadatas.add(dcm_";
  protected final String TEXT_477 = ");" + NL + "                }" + NL + "            }";
  protected final String TEXT_478 = NL + "\t\t    String tmpContent_";
  protected final String TEXT_479 = " = null;" + NL + "\t\t    ";
  protected final String TEXT_480 = NL + "\t\t    \tint column_index_";
  protected final String TEXT_481 = " =1;" + NL + "\t\t    ";
  protected final String TEXT_482 = NL + "\t\t    ";
  protected final String TEXT_483 = NL + "\t\t    \tlog.debug(\"";
  protected final String TEXT_484 = " - Retrieving records from the database.\");" + NL + "\t\t    ";
  protected final String TEXT_485 = NL + "\t\t    while (rs_";
  protected final String TEXT_486 = ".next()) {" + NL + "\t\t        nb_line_";
  protected final String TEXT_487 = "++;" + NL + "\t\t        ";
  protected final String TEXT_488 = NL + "\t\t\t\t\t\t\t\t\tcolumn_index_";
  protected final String TEXT_489 = " = ";
  protected final String TEXT_490 = " + dcg_";
  protected final String TEXT_491 = ".getColumnCount();" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_492 = NL + "\t\t\t\t\t\t\t\t\tcolumn_index_";
  protected final String TEXT_493 = " = ";
  protected final String TEXT_494 = ";" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_495 = NL + "\t\t\t\t\t\t\t";
  protected final String TEXT_496 = NL + "\t\t\t\t\t\t\tif(colQtyInRs_";
  protected final String TEXT_497 = " < ";
  protected final String TEXT_498 = "column_index_";
  protected final String TEXT_499 = ") {" + NL + "\t\t\t\t\t\t\t\t";
  protected final String TEXT_500 = ".";
  protected final String TEXT_501 = " = ";
  protected final String TEXT_502 = ";" + NL + "\t\t\t\t\t\t\t} else {";
  protected final String TEXT_503 = NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_504 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_505 = NL + "\t\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_506 = NL + "\t\t                            ";
  protected final String TEXT_507 = ".";
  protected final String TEXT_508 = " = (List)rs_";
  protected final String TEXT_509 = ".getObject(";
  protected final String TEXT_510 = "column_index_";
  protected final String TEXT_511 = ");";
  protected final String TEXT_512 = NL + "\t                         \t\t";
  protected final String TEXT_513 = NL + "\t                         \t\t";
  protected final String TEXT_514 = NL + "\t                         \t\t";
  protected final String TEXT_515 = NL + "                                    oracle.sql.STRUCT jGeomStruct = (oracle.sql.STRUCT) rs_";
  protected final String TEXT_516 = ".getObject(";
  protected final String TEXT_517 = "column_index_";
  protected final String TEXT_518 = ");" + NL + "                                    if (jGeomStruct != null) {" + NL + "                                    oracle.spatial.geometry.JGeometry jGeom = oracle.spatial.geometry.JGeometry.load(jGeomStruct);" + NL + "                                    oracle.spatial.util.WKT wkt = new oracle.spatial.util.WKT();" + NL + "                                    String wktValue = new String(wkt.fromJGeometry(jGeom));" + NL;
  protected final String TEXT_519 = NL + "                                    ";
  protected final String TEXT_520 = ".";
  protected final String TEXT_521 = " = new Geometry(wktValue);" + NL + "                                    } else {";
  protected final String TEXT_522 = NL + "                                      ";
  protected final String TEXT_523 = ".";
  protected final String TEXT_524 = " = null;" + NL + "                                    }";
  protected final String TEXT_525 = NL + "                                        if (";
  protected final String TEXT_526 = ".";
  protected final String TEXT_527 = " != null) {";
  protected final String TEXT_528 = NL + "                                        ";
  protected final String TEXT_529 = ".";
  protected final String TEXT_530 = ".setEPSG(";
  protected final String TEXT_531 = ");" + NL + "                                        }";
  protected final String TEXT_532 = NL + "\t\t\t\t\t\t\t\t\t";
  protected final String TEXT_533 = NL + "                                  ";
  protected final String TEXT_534 = ".";
  protected final String TEXT_535 = "=dcg_";
  protected final String TEXT_536 = ";";
  protected final String TEXT_537 = NL + "\t\t\t\t\t\t\t\t\t\tList<String> list_";
  protected final String TEXT_538 = " = new java.util.ArrayList<String>();" + NL + "\t\t\t\t\t    \t\t\t\tfor(int i_";
  protected final String TEXT_539 = " = ";
  protected final String TEXT_540 = "; i_";
  protected final String TEXT_541 = "  <= rsmd_";
  protected final String TEXT_542 = ".getColumnCount(); i_";
  protected final String TEXT_543 = " ++){" + NL + "\t\t\t\t\t\t \t\t\t\t\tif (\"NTEXT\".equals(rsmd_";
  protected final String TEXT_544 = ".getColumnTypeName(i_";
  protected final String TEXT_545 = " ).toUpperCase())) {" + NL + "\t\t\t\t\t\t\t\t\t\t\t\tnet.sourceforge.jtds.jdbc.ClobImpl clob_";
  protected final String TEXT_546 = " = (net.sourceforge.jtds.jdbc.ClobImpl) rs_";
  protected final String TEXT_547 = ".getClob(i_";
  protected final String TEXT_548 = ");" + NL + "\t\t\t\t\t\t\t\t\t\t\t\tif(clob_";
  protected final String TEXT_549 = "!=null){" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t\tnet.sourceforge.jtds.jdbc.TalendNTextImpl tNTextImpl_";
  protected final String TEXT_550 = " = new net.sourceforge.jtds.jdbc.TalendNTextImpl(clob_";
  protected final String TEXT_551 = ");" + NL + "\t\t\t\t\t\t\t  \t\t\t\t\t\tlist_";
  protected final String TEXT_552 = ".add(tNTextImpl_";
  protected final String TEXT_553 = ".getValue());" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t}else{" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t\tlist_";
  protected final String TEXT_554 = ".add(null);" + NL + "\t\t\t\t\t\t\t\t\t\t\t\t}" + NL + "\t\t\t\t\t\t   \t\t\t\t\t}" + NL + "   \t \t\t\t\t    \t\t\t\t}" + NL + "                                 \t\t routines.system.DynamicUtils.readColumnsFromDatabase_Mssql(";
  protected final String TEXT_555 = ".";
  protected final String TEXT_556 = ", rs_";
  protected final String TEXT_557 = ", fixedColumnCount_";
  protected final String TEXT_558 = ",list_";
  protected final String TEXT_559 = ",";
  protected final String TEXT_560 = ");";
  protected final String TEXT_561 = NL + "\t\t\t\t\t\t\t\t\t\troutines.system.DynamicUtils.readColumnsFromDatabase_Access(";
  protected final String TEXT_562 = ".";
  protected final String TEXT_563 = ", rs_";
  protected final String TEXT_564 = ", fixedColumnCount_";
  protected final String TEXT_565 = ",";
  protected final String TEXT_566 = ");";
  protected final String TEXT_567 = NL + "                                \t\t routines.system.DynamicUtils.readColumnsFromDatabase(";
  protected final String TEXT_568 = ".";
  protected final String TEXT_569 = ", rs_";
  protected final String TEXT_570 = ", fixedColumnCount_";
  protected final String TEXT_571 = ",";
  protected final String TEXT_572 = ");";
  protected final String TEXT_573 = NL + "                                  Object geom = rs_";
  protected final String TEXT_574 = ".getObject(";
  protected final String TEXT_575 = "column_index_";
  protected final String TEXT_576 = ");" + NL + "                                  if (geom != null) {" + NL + "                                  \torg.postgis.Geometry o =" + NL + "                                      org.postgis.PGgeometry.geomFromString(geom.toString());" + NL + "                                \tStringBuffer sb = new StringBuffer();" + NL + "                                \to.outerWKT(sb, false);" + NL + "                                \t";
  protected final String TEXT_577 = ".";
  protected final String TEXT_578 = " = new Geometry(sb.toString());" + NL + "                                  } else {" + NL + "                                  \t";
  protected final String TEXT_579 = ".";
  protected final String TEXT_580 = " = null;" + NL + "                                  }";
  protected final String TEXT_581 = NL + "\t\t                          ";
  protected final String TEXT_582 = NL + "\t\t                    }";
  protected final String TEXT_583 = NL + "\t\t\t\t\t";
  protected final String TEXT_584 = NL + "\t\t\t\t\t\tlog.debug(\"";
  protected final String TEXT_585 = " - Retrieving the record \" + nb_line_";
  protected final String TEXT_586 = " + \".\");" + NL + "\t\t\t\t\t";
  protected final String TEXT_587 = NL + "\t\t                            ";
  protected final String TEXT_588 = ".";
  protected final String TEXT_589 = " = ";
  protected final String TEXT_590 = ".";
  protected final String TEXT_591 = ";" + NL + "\t\t                            ";
  protected final String TEXT_592 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    
	//this util class use by set log4j debug paramters
	class DefaultLog4jFileUtil {
	
		INode node = null;
	    String cid = null;
 		boolean isLog4jEnabled = false;
 		String label = null;
 		
 		public DefaultLog4jFileUtil(){
 		}
 		public DefaultLog4jFileUtil(INode node) {
 			this.node = node;
 			this.cid = node.getUniqueName();
 			this.label = cid;
			this.isLog4jEnabled = ("true").equals(org.talend.core.model.process.ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
 		}
 		
 		public void setCid(String cid) {
 			this.cid = cid;
 		}
 		
		//for all tFileinput* components 
		public void startRetriveDataInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_3);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_4);
    
			}
		}
		
		public void retrievedDataNumberInfo() {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_5);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_7);
    
			}
		}
		
		public void retrievedDataNumberInfoFromGlobalMap(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_8);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_10);
    
			}
		}
		
		//for all tFileinput* components 
		public void retrievedDataNumberInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_11);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_13);
    
			}
		}
		
		public void writeDataFinishInfo(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_14);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_16);
    
			}
		}
		
 		//TODO delete it and remove all log4jSb parameter from components
		public void componentStartInfo(INode node) {
			if (isLog4jEnabled) {
			
    stringBuffer.append(TEXT_17);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_18);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node,boolean hasIncreased) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_19);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(cid);
    stringBuffer.append(hasIncreased?"":"+1");
    stringBuffer.append(TEXT_21);
    
			}
		}
		
		//TODO rename or delete it
		public void debugRetriveData(INode node) {
			debugRetriveData(node,true);
		}
		
		//TODO rename or delete it
		public void debugWriteData(INode node) {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_22);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_24);
    
			}
		}
		
		public void logCurrentRowNumberInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_25);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_26);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_27);
    
			}
		}
		
		public void logDataCountInfo() {
			if(isLog4jEnabled){
			
    stringBuffer.append(TEXT_28);
    stringBuffer.append(label);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_30);
    
			}
		}
	}
	
	final DefaultLog4jFileUtil log4jFileUtil = new DefaultLog4jFileUtil((INode)(((org.talend.designer.codegen.config.CodeGeneratorArgument)argument).getArgument()));
	
    
	class DefaultLog4jCodeGenerateUtil extends DefaultLog4jFileUtil{

 		String connection = "";
 		boolean hasInit = false;
 		String dataAction ;
 		String dataOperationPrefix;
		String useBatchSize;
		String batchSize;
		String dbSchema;
 		boolean logCommitCounter = false;

		public DefaultLog4jCodeGenerateUtil(){
		}

		public DefaultLog4jCodeGenerateUtil(INode node) {
			super(node);
	    	init();
		}

	    public void beforeComponentProcess(INode node){
	    	this.node = node;
	    	init();
	    }

		private void init() {
			if(hasInit){
				return;
			}
 			this.cid = node.getUniqueName();
			this.isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));
			String useConn = ElementParameterParser.getValue(node,"__USE_EXISTING_CONNECTION__");
			if(useConn == null || "".equals(useConn) || "true".equals(useConn)){
				connection = ElementParameterParser.getValue(node,"__CONNECTION__");
				if(!"".equals(connection)){
					connection = "'" + connection+"' ";
				}
			}
			//for output
			dataAction = ElementParameterParser.getValue(node,"__DATA_ACTION__");
			if(dataAction != null && !("").equals(dataAction)){
				logCommitCounter=true;
			}
			useBatchSize = ElementParameterParser.getValue(node, "__USE_BATCH_SIZE__");
			batchSize =ElementParameterParser.getValue(node, "__BATCH_SIZE__");
			hasInit = true;
		}

		public void debugDriverClassName() {
			logInfo(node,"debug",cid+" - Driver ClassName: \"+driverClass_"+cid+"+\".");
		}

		public void debugConnectionParams(INode node) {
			beforeComponentProcess(node);
			debugDriverClassName();
		}

		public void useExistConnection(INode node){
			beforeComponentProcess(node);
			if(isLog4jEnabled) {
			
    stringBuffer.append(TEXT_31);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_33);
    if (cid.startsWith("tImpala") || cid.startsWith("tHive")) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(connection );
    stringBuffer.append(TEXT_36);
    } else {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    
			}
		}

		public void connect(INode node){
			beforeComponentProcess(node);
			connect();
		}

		public void connect(){
			connect_begin();
			
    stringBuffer.append(TEXT_42);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_44);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_46);
    
			connect_end();
		}

		public void connect_begin(){
			logInfo(node,"debug",cid+" - Connection attempt to '\" + url_"+cid+" + \"' with the username '\" + dbUser_"+cid+" + \"'.");
		}

		public void connect_begin_noUser(){
			logInfo(node,"debug",cid+" - Connection attempt to '\" + url_"+cid+" + \"'.");
		}

		public void connect_end(){
			logInfo(node,"debug",cid+" - Connection to '\" + url_"+cid+" + \"' has succeeded.");
		}

		public void rollback(INode node){
			beforeComponentProcess(node);
			logInfo(node,"debug",cid+" - Connection "+connection+"starting to rollback.");
			
    stringBuffer.append(TEXT_47);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_48);
    
			logInfo(node,"debug",cid+" - Connection "+connection+"rollback has succeeded.");
		}

		public void commit(INode node){
			beforeComponentProcess(node);
			commit();
		}

		private void commit(){
			commit_begin();
			
    stringBuffer.append(TEXT_49);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_50);
    
			commit_end();
		}

		private void commit_begin(){
			if(logCommitCounter){
				logInfo(node,"debug",cid+" - Connection "+connection+"starting to commit \" + commitCounter_"+cid+"+ \" records.");
			}else{
				logInfo(node,"debug",cid+" - Connection "+connection+"starting to commit.");
			}
		}
		private void commit_end(){
			logInfo(node,"debug",cid+" - Connection "+connection+"commit has succeeded.");
		}

		public void close(INode node){
			beforeComponentProcess(node);
			close();
		}

		private void close(){
			close_begin();
			
    stringBuffer.append(TEXT_51);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_52);
    
			close_end();
		}

		public void close_begin(){
			logInfo(node,"debug",cid+" - Closing the connection "+connection+"to the database.");
		}
		public void close_end(){
			logInfo(node,"debug",cid+" - Connection "+connection+"to the database closed.");
		}

		public void autoCommit(INode node,boolean autoCommit){
			beforeComponentProcess(node);
			logInfo(node,"debug",cid+" - Connection is set auto commit to '"+autoCommit+"'.");
			
    stringBuffer.append(TEXT_53);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(autoCommit);
    stringBuffer.append(TEXT_55);
    
		}

		public void query(INode node){
			beforeComponentProcess(node);
			//for input
	 		String dbquery= ElementParameterParser.getValue(node, "__QUERY__");
			dbquery = dbquery.replaceAll("\n"," ");
			dbquery = dbquery.replaceAll("\r"," ");
			logInfo(node,"debug",cid+" - Executing the query: '\" + "+dbquery +" + \"'.");
		}

		public void retrieveRecordsCount(INode node){
			beforeComponentProcess(node);
			logInfo(node,"debug",cid+" - Retrieved records count: \"+nb_line_"+cid+" + \" .");
		}

		public void logError(INode node,String logLevel,String exception){
	    	beforeComponentProcess(node);
	    	if(isLog4jEnabled){
	    	
    stringBuffer.append(TEXT_56);
    stringBuffer.append(logLevel);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(exception);
    stringBuffer.append(TEXT_59);
    
			}
	    }

	    public void logError(INode node,String logLevel){
	    	logError(node,logLevel,"e");
	    }
	    
	    public void logInfo(INode node,String logLevel,String message){
	    	beforeComponentProcess(node);
	    	if(isLog4jEnabled){
	    	
    stringBuffer.append(TEXT_60);
    stringBuffer.append(logLevel);
    stringBuffer.append(TEXT_61);
    stringBuffer.append(message);
    stringBuffer.append(TEXT_62);
    
			}
	    }
		/**
		*batchType :
		*			1: do not get return value of executeBatch();
		*			2: get return value of executeBatch();
		*
		*/
		public void executeBatch(INode node,int batchType){
			beforeComponentProcess(node);
			boolean logBatch = ("true").equals(useBatchSize) && !("").equals(batchSize) && !("0").equals(batchSize);
			if(logBatch){
				logInfo(node,"debug",cid+" - Executing the "+dataAction+" batch.");
			}
			if(batchType==1){
			
    stringBuffer.append(TEXT_63);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_64);
    
			}else if(batchType==2){
				boolean isMysqlBatchInsert = false;
				if ((node.getUniqueName().contains("tMysqlOutput")||node.getUniqueName().contains("tAmazonMysqlOutput")) && ("INSERT").equals(dataAction)) {
					isMysqlBatchInsert = true;
				}
			
    stringBuffer.append(TEXT_65);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_66);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_67);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_68);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_69);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(isMysqlBatchInsert? "1" : "countEach_"+cid );
    stringBuffer.append(TEXT_71);
    
			}
			if(logBatch){
				logInfo(node,"debug",cid+" - The "+dataAction+" batch execution has succeeded.");
			}
		}
	}

	DefaultLog4jCodeGenerateUtil log4jCodeGenerateUtil = new DefaultLog4jCodeGenerateUtil();

    
	//this util class use by connection component
	class DefaultDBInputUtil {
	
		protected String cid ;
		protected String dbproperties ;
		protected String dbhost;
		protected String dbport;
		protected String dbname;
		protected String dbuser;
		protected String dbpwd ;
		protected boolean isLog4jEnabled;
		protected boolean isDynamic;
		protected DefaultLog4jCodeGenerateUtil log4jCodeGenerateUtil;

		public void beforeComponentProcess(INode node){
			cid = node.getUniqueName();
			List<IMetadataTable> metadatas = node.getMetadataList();
			if ((metadatas != null) && (metadatas.size()>0)) {
				IMetadataTable metadata = metadatas.get(0);
				if (metadata != null) {
					isDynamic = metadata.isDynamicSchema();
				}
			}
			log4jCodeGenerateUtil = new DefaultLog4jCodeGenerateUtil();
		}

		public void afterUseExistConnection(INode node) {
		}
		
		public String getDirverClassName(INode node){
			return "";
		}
	    
	    public void afterUseNewConnection(INode node) {
	    }
	    
	    public void setURL(INode node) {
	    }
	    
		public void createConnection(INode node) {
			this.createConnection(node, true);
		}

		public void createConnection(INode node, boolean needUserAndPassword) {
			cid = node.getUniqueName();
			dbproperties = ElementParameterParser.getValue(node, "__PROPERTIES__");
			dbhost = ElementParameterParser.getValue(node, "__HOST__");
	    	dbport = ElementParameterParser.getValue(node, "__PORT__");
	    	dbname = ElementParameterParser.getValue(node, "__DBNAME__");
	    	dbuser = ElementParameterParser.getValue(node, "__USER__");

	 		boolean specify_alias = "true".equals(ElementParameterParser.getValue(node, "__SPECIFY_DATASOURCE_ALIAS__"));
 		if(specify_alias){

    stringBuffer.append(TEXT_72);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_73);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_74);
    
                String alias = ElementParameterParser.getValue(node, "__DATASOURCE_ALIAS__");

    stringBuffer.append(TEXT_75);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_77);
    stringBuffer.append((null != alias && !("".equals(alias)))?alias:"\"\"");
    stringBuffer.append(TEXT_78);
    
		}

    stringBuffer.append(TEXT_79);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(this.getDirverClassName(node) );
    stringBuffer.append(TEXT_81);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_83);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_84);
    
        		String passwordFieldName = "__PASS__";
        		
    stringBuffer.append(TEXT_85);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_87);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_88);
    } else {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_90);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_93);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_94);
    this.setURL(node);
    stringBuffer.append(TEXT_95);
    
				log4jCodeGenerateUtil.debugConnectionParams(node);
				log4jCodeGenerateUtil.connect_begin();
				
    
			if(needUserAndPassword) {

    stringBuffer.append(TEXT_96);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_97);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_98);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_100);
    
			} else {

    stringBuffer.append(TEXT_101);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_103);
    
			}
			log4jCodeGenerateUtil.connect_end();

    stringBuffer.append(TEXT_104);
    this.afterUseNewConnection(node);
    
			if(specify_alias){

    stringBuffer.append(TEXT_105);
    
			}
		}
		
		public String getQueryString(INode node) {
			String dbquery= ElementParameterParser.getValue(node, "__QUERY__");
			dbquery = org.talend.core.model.utils.NodeUtil.replaceCRLFInMEMO_SQL(dbquery);
			
			return dbquery;
		}
		
		public void createStatement(INode node) {

    stringBuffer.append(TEXT_106);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_107);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_108);
    
		}
		public void createMinValueStatement(INode node){

    stringBuffer.append(TEXT_109);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_110);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_111);
    }
		public String mappingType(String typeToGenerate) {
		
            if(("byte[]").equals(typeToGenerate)) {
                return "Bytes";
            } else if(("java.util.Date").equals(typeToGenerate)) {
                return "Timestamp";
            } else if(("Integer").equals(typeToGenerate)) {
               return "Int";
            } else {
                return typeToGenerate.substring(0,1).toUpperCase()+typeToGenerate.substring(1);
            }
		}
		//-----------according schema type to generate ResultSet
		public void generateStringCharAndCharacterSet(String firstConnName, IMetadataColumn column, int currentColNo,
					String trimMethod, String typeToGenerate, boolean whetherTrimAllCol, boolean whetherTrimCol) {

    stringBuffer.append(TEXT_112);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_114);
    if(isDynamic){
    stringBuffer.append(TEXT_115);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_116);
    
                    if(whetherTrimAllCol || whetherTrimCol) {

    stringBuffer.append(TEXT_117);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(cid);
    stringBuffer.append(trimMethod);
    stringBuffer.append(TEXT_120);
    
                    }

    stringBuffer.append(TEXT_121);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_125);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_127);
    
                        if(("Character").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_128);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(TEXT_130);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_131);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_134);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_135);
    
                        } else {

    stringBuffer.append(TEXT_136);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_142);
    
                        }

    stringBuffer.append(TEXT_143);
    
		}
		
	    public void generateTimestampResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {

    stringBuffer.append(TEXT_144);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_145);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_147);
    if(isDynamic){
    stringBuffer.append(TEXT_148);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_149);
    
	    }
	    
	    public void generateStringResultSet(String firstConnName, IMetadataColumn column, int currentColNo, String trimMethod) {

    stringBuffer.append(TEXT_150);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_151);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_153);
    if(isDynamic){
    stringBuffer.append(TEXT_154);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_155);
    if(trimMethod==null||trimMethod.isEmpty()){
    stringBuffer.append(TEXT_156);
    } else {
    stringBuffer.append(TEXT_157);
    }
    stringBuffer.append(TEXT_158);
    
	    }
	    
	    public void generateDoubleResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {
	    	generateOthersResultSet(firstConnName, column, currentColNo, "Double");
	    }
	    
	    public void generateBooleanResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {
	    	generateOthersResultSet(firstConnName, column, currentColNo, "Boolean");
	    }
	    
	    public void generateBytesResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {
	    }
	    
	    public void generateOthersResultSet(String firstConnName, IMetadataColumn column, int currentColNo, String typeToGenerate) {

    stringBuffer.append(TEXT_159);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_160);
    if(isDynamic){
    stringBuffer.append(TEXT_161);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_162);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_164);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_166);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_167);
    if(isDynamic){
    stringBuffer.append(TEXT_168);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_169);
    
                if(column.isNullable()) {
                    
    stringBuffer.append(TEXT_170);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_172);
    
                } else {
                    
    stringBuffer.append(TEXT_173);
        
                }
                
    stringBuffer.append(TEXT_174);
    
	    }
	    //---------end according schema type to generate ResultSet
	    
		public void afterComponentProcess(INode node){
	    }
	}//end DefaultDBInputUtil class
	
	DefaultDBInputUtil dbInputBeginUtil = new DefaultDBInputUtil();
	
	

    stringBuffer.append(TEXT_175);
    
	// This class should be extended by the DB specific tXXXInvalid(Valid)Rows components.
	// The father class DefaultDBInputUtil exists in tXXXInput component. So the tXXXInput_Begin.javajet template
	// should be included in tXXXInvalid(Valid)Rows components as well as the current file.
	abstract class DefaultDBValidInputUtil extends DefaultDBInputUtil{

		protected INode node = null;
		
		protected String getIdentifierQuote(){
			return "";
		}
		
		public void beforeComponentProcess(INode node){
			super.beforeComponentProcess(node);
			this.node = node;
		}
		
		public String getQueryString(INode node){
			String queryString = "";
			String validationType = ElementParameterParser.getValue(node, "__VALIDATION_TYPE__");
			if("PATTERN".equals(validationType)){	
				queryString = getQueryStringForPattern();
			}else if("DQ_RULE".equals(validationType)){
				queryString = ElementParameterParser.getValue(node, "__QUERY__");
				queryString = queryString.replaceAll("\r"," ").replaceAll("\n"," ");
			}
			return queryString;
		}
		
		private String getQueryStringForPattern(){
			StringBuilder sb = new StringBuilder();
			sb.append('\"');
			sb.append(getSelectString());
			sb.append(getWhereString());
			sb.append(getDataFilter());
			sb.append('\"');
			return sb.toString();
		}
		  
		protected String getSelectString(){
		    Boolean useExistingConn = (Boolean)ElementParameterParser.getObjectValue(node, "__USE_EXISTING_CONNECTION__");
			String schema = ElementParameterParser.getValue(node, "__SCHEMA_DB__");
			String table = ElementParameterParser.getValue(node, "__TABLE__");
			String columnNames = null;
            StringBuilder sb = new StringBuilder("SELECT ");
			for(IMetadataColumn column : getAllColumnsFromSchema()) {
				String ColumnLabel = column.getOriginalDbColumnName().isEmpty()?column.getLabel():column.getOriginalDbColumnName();
				if(schema != null && !schema.trim().equals("") && !schema.trim().equals("\"\"")){
                    sb.append(handleContextModeOrAddQuotes(schema)).append(".");
                }	            
	            sb.append(handleContextModeOrAddQuotes(table)).append(".");
	            sb.append(getIdentifierQuote()).append(ColumnLabel).append(getIdentifierQuote());
				sb.append(", ");
			}
			if(sb.length() > 2){
			    sb.deleteCharAt(sb.length() - 2);
			}
			sb.append(" FROM ");
			if(schema != null && !schema.trim().equals("") && !schema.trim().equals("\"\"")){
				sb.append(handleContextModeOrAddQuotes(schema)).append(".");
			}
			sb.append(handleContextModeOrAddQuotes(table));
			return sb.toString();
		}

		protected abstract String getWhereString();
		
		protected String getAnalyzedColumn(){
            String schema = ElementParameterParser.getValue(node, "__SCHEMA_DB__");
            String table = ElementParameterParser.getValue(node, "__TABLE__");
            String analyzedColumn = ElementParameterParser.getValue(node, "__ANALYZED_COLUMN__");
            
			StringBuilder sb = new StringBuilder();
            if(schema != null && !schema.trim().equals("") && !schema.trim().equals("\"\"")){
                sb.append(handleContextModeOrAddQuotes(schema)).append(".");
            }
            sb.append(handleContextModeOrAddQuotes(table)).append(".");
            sb.append(getIdentifierQuote()).append(getOriginalDBColumnName(analyzedColumn)).append(getIdentifierQuote());
			return sb.toString();
		}
		
		protected String getPatternString(){
			boolean bCustomPattern = "true".equals(ElementParameterParser.getValue(node, "__CUSTOM_PATTERN__"));
			String dqPattern = "";
			if (bCustomPattern){
				dqPattern = ElementParameterParser.getValue(node, "__DQ_PATTERN__");
				// prepare pattern string for quoted query.				
				if(dqPattern.startsWith("\"\'")){ // if starts with a double quote and a single quote, keep only the single quotes
					dqPattern = dqPattern.substring(1);
					dqPattern = dqPattern.endsWith("\"")?dqPattern.substring(0,dqPattern.length()-1):dqPattern;
				}else{
					dqPattern = dqPattern.replace("\\", "\\\\");
				}
				dqPattern = dqPattern.replace("\"", "\\\""); // replace JAVA escape characters
			}else{
				dqPattern = ElementParameterParser.getValue(node, "__PATTERN_LIST__");
			}
			return dqPattern;
		}		

		private String getDataFilter(){
			String strWhereClause = ElementParameterParser.getValue(node, "__WHERE_CLAUSE__");
			
			StringBuilder sb = new StringBuilder();
			if (null != strWhereClause){
				//Remove first & last quote symbol.
				strWhereClause=strWhereClause.trim();
				if (strWhereClause.startsWith("\"")) {
					strWhereClause = strWhereClause.substring(1);
				}
				if (strWhereClause.endsWith("\"")) {
					strWhereClause = strWhereClause.substring(0, strWhereClause.length() - 1);
				}
				if(!strWhereClause.equals("")){
					sb.append(" AND ").append(strWhereClause);
				}
			}
			return sb.toString();
		}
		
		protected String getOriginalDBColumnName(String analyzedColumn){
			List<IMetadataTable> metadatas = node.getMetadataList();
			if ((metadatas != null) && (metadatas.size()>0)) {
				IMetadataTable metadata = metadatas.get(0);
				if (metadata != null) {
					// use original db column name
					for(IMetadataColumn column : metadata.getListColumns()) {
						String dbColumnName = null;
						if (analyzedColumn.equals(column.getLabel())){
							dbColumnName = column.getOriginalDbColumnName();
							if (null != dbColumnName && !"".equals(dbColumnName)){
									analyzedColumn = dbColumnName;
							}
							break;
						}
					}
				}
			}
			return analyzedColumn;
		}
		
		protected List<IMetadataColumn> getAllColumnsFromSchema(){
			List<IMetadataTable> metadatas = node.getMetadataList();
			if ((metadatas!=null) && (metadatas.size() > 0)) {
				IMetadataTable metadata = metadatas.get(0);
				if (metadata != null) {
					return metadata.getListColumns();
	  			}
	  		}
	  		return null;
		}
		
		protected String handleContextModeOrAddQuotes(String param) { 
			if (param.startsWith("context.")) { 
				return getIdentifierQuote() + "\" + " + param + " + \"" + getIdentifierQuote(); 
			}else{
				return param.replace("\"", getIdentifierQuote());
			}
		}
		
	}//end DefaultDBValidInputUtil class
		
		

    stringBuffer.append(TEXT_176);
    
	class ValidRowsInputBeginUtil extends DefaultDBValidInputUtil{
	
		protected String getIdentifierQuote(){
			return "\\\"";
		}
		
		public void setURL(INode node) {
			
			String localServiceName = ElementParameterParser.getValue(node, "__LOCAL_SERVICE_NAME__");
			String connectionType = ElementParameterParser.getValue(node, "__CONNECTION_TYPE__");
			String jdbcURL = ElementParameterParser.getValue(node, "__JDBC_URL__");
			String rac_url = ElementParameterParser.getValue(node, "__RAC_URL__");

    stringBuffer.append(TEXT_177);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_178);
    
				if("ORACLE_RAC".equals(connectionType)) {

    stringBuffer.append(TEXT_179);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_180);
    stringBuffer.append(rac_url);
    stringBuffer.append(TEXT_181);
    
				} else if(("ORACLE_SID").equals(connectionType)) {

    stringBuffer.append(TEXT_182);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_183);
    stringBuffer.append(dbhost);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(dbport);
    stringBuffer.append(TEXT_185);
    stringBuffer.append(dbname);
    stringBuffer.append(TEXT_186);
    
				} else if(("ORACLE_SERVICE_NAME").equals(connectionType)) {

    stringBuffer.append(TEXT_187);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_188);
    stringBuffer.append(dbhost);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(dbport);
    stringBuffer.append(TEXT_190);
    stringBuffer.append(dbname);
    stringBuffer.append(TEXT_191);
    
				} else if(("ORACLE_OCI").equals(connectionType)) {

    stringBuffer.append(TEXT_192);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_193);
    stringBuffer.append(localServiceName);
    stringBuffer.append(TEXT_194);
    
				}else if(("ORACLE_WALLET").equals(connectionType)) {

    stringBuffer.append(TEXT_195);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(jdbcURL);
    stringBuffer.append(TEXT_197);
    
				}

    stringBuffer.append(TEXT_198);
    
		}
		
		public String getDirverClassName(INode node){
			String dbVersion =  ElementParameterParser.getValue(node, "__DB_VERSION__"); 
			if("ORACLE_11".equals(dbVersion) || "ORACLE_11-6".equals(dbVersion) || "ORACLE_12".equals(dbVersion) ){
				return "oracle.jdbc.OracleDriver";	
			}else {
				return "oracle.jdbc.driver.OracleDriver";	
			}
		}		

		protected String getWhereString(){
			StringBuilder sb = new StringBuilder(" WHERE ");
			sb.append(" REGEXP_LIKE (").append(getAnalyzedColumn()).append(", ").append(getPatternString()).append(")"); 
			return sb.toString();
		}
		
		public void createConnection(INode node) {
			cid = node.getUniqueName();
			dbproperties = ElementParameterParser.getValue(node, "__PROPERTIES__");
			dbhost = ElementParameterParser.getValue(node, "__HOST__");
			dbport = ElementParameterParser.getValue(node, "__PORT__");
			dbname = ElementParameterParser.getValue(node, "__DBNAME__");
			dbuser = ElementParameterParser.getValue(node, "__USER__");
	 		String connectionType = ElementParameterParser.getValue(node, "__CONNECTION_TYPE__");
	 		String dbVersion =  ElementParameterParser.getValue(node, "__DB_VERSION__"); 

    stringBuffer.append(TEXT_199);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_200);
    stringBuffer.append(this.getDirverClassName(node) );
    stringBuffer.append(TEXT_201);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_202);
    this.setURL(node);
    stringBuffer.append(TEXT_203);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_204);
    stringBuffer.append(dbuser);
    stringBuffer.append(TEXT_205);
    
            String passwordFieldName = "__PASS__";
            
    stringBuffer.append(TEXT_206);
    if (ElementParameterParser.canEncrypt(node, passwordFieldName)) {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_208);
    stringBuffer.append(ElementParameterParser.getEncryptedValue(node, passwordFieldName));
    stringBuffer.append(TEXT_209);
    } else {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_211);
    stringBuffer.append( ElementParameterParser.getValue(node, passwordFieldName));
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_214);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_215);
    
			log4jCodeGenerateUtil.debugConnectionParams(node);	
			log4jCodeGenerateUtil.connect_begin();
			if(("ORACLE_WALLET").equals(connectionType)) {

    stringBuffer.append(TEXT_216);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_217);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_218);
    
			}else if (dbproperties == null || ("\"\"").equals(dbproperties) || ("").equals(dbproperties)) {

    stringBuffer.append(TEXT_219);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_220);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_221);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_222);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_223);
    
			} else {

    stringBuffer.append(TEXT_224);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_225);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_226);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_227);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_228);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_229);
    stringBuffer.append( dbproperties );
    stringBuffer.append(TEXT_230);
    stringBuffer.append( dbproperties );
    stringBuffer.append(TEXT_231);
    stringBuffer.append( dbproperties );
    stringBuffer.append(TEXT_232);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_233);
    stringBuffer.append(dbproperties);
    stringBuffer.append(TEXT_234);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_235);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_237);
    
			}
			log4jCodeGenerateUtil.connect_end();
			if(!"ORACLE_8".equals(dbVersion)){
    stringBuffer.append(TEXT_238);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_239);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_241);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_242);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_243);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_246);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_247);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_248);
    
			}
		}
		
		public void afterUseExistConnection(INode node) {
			String dbVersion =  ElementParameterParser.getValue(node, "__DB_VERSION__");
			if(!"ORACLE_8".equals(dbVersion)){
    stringBuffer.append(TEXT_249);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_250);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_251);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_253);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_255);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_256);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_259);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_260);
    
			}
		}
		
		public void createStatement(INode node) {
			
			String useCursor= ElementParameterParser.getValue(node, "__USE_CURSOR__");
			String cursorSize= ElementParameterParser.getValue(node, "__CURSOR_SIZE__");
			if(!("true").equals(useCursor)) {
				 super.createStatement(node);
			}else {
				super.createMinValueStatement(node);

    stringBuffer.append(TEXT_261);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_262);
    stringBuffer.append(cursorSize );
    stringBuffer.append(TEXT_263);
    
			}
		}
		//-----------according schema type to generate ResultSet
		public void generateStringResultSet(String firstConnName, IMetadataColumn column, int currentColNo, String trimMethod) {
			String columnType = column.getType();
			if(columnType != null && ("CLOB").equals(columnType)) {

    stringBuffer.append(TEXT_264);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_266);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_276);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_279);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_283);
    
			} else {

    stringBuffer.append(TEXT_284);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_286);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_287);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(cid);
    stringBuffer.append(trimMethod);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_293);
    
			}
		}
		
	   	public void generateBytesResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {

    stringBuffer.append(TEXT_294);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_295);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_296);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_297);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_299);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_303);
    
		}
		
		public void generateTimestampResultSet(String firstConnName, IMetadataColumn column, int currentColNo) {
			boolean noNullValues = ("true").equals(ElementParameterParser.getValue(node, "__NO_NULL_VALUES__"));	
			if(noNullValues){

    stringBuffer.append(TEXT_304);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_305);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_309);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_310);
    
			}else{
				super.generateTimestampResultSet(firstConnName,column,currentColNo);
			}
		}

	   	public void generateOthersResultSet(String firstConnName, IMetadataColumn column, int currentColNo, String typeToGenerate) {
		   	boolean noNullValues = ("true").equals(ElementParameterParser.getValue(node, "__NO_NULL_VALUES__"));
	   		boolean isConvert = ("true").equals(ElementParameterParser.getValue(node, "__IS_CONVERT_XMLTYPE__"));	
			List<Map<String, String>> convertColumns = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__CONVERT_XMLTYPE__");  
	   		
			if(("Object").equals(typeToGenerate)){
			
				boolean needConvert = false;
	   			String convertToJavaType = "";
				if (isConvert && convertColumns.size() > 0) {
					for(java.util.Map<String, String> convertcolumn : convertColumns) {
						if (convertcolumn.get("REFCOL").equals(column.getLabel()) ) {
							needConvert = true;
							convertToJavaType = convertcolumn.get("CONVETTYPE");
						}
					}
				} 
				

    stringBuffer.append(TEXT_311);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_312);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_313);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_314);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_315);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_317);
    
				if (needConvert && "String".equals(convertToJavaType)) {

    stringBuffer.append(TEXT_318);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_319);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_322);
    
				} else {

    stringBuffer.append(TEXT_323);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_326);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_327);
    
				}

    stringBuffer.append(TEXT_328);
    
					if(column.isNullable()) {

    stringBuffer.append(TEXT_329);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_331);
    
					} else {

    stringBuffer.append(TEXT_332);
    	
					}

    stringBuffer.append(TEXT_333);
    
			} else {
				if(noNullValues){

    stringBuffer.append(TEXT_334);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_335);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_337);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_339);
    
					if(("Boolean").equals(typeToGenerate)){
					
    stringBuffer.append(TEXT_340);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_343);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_344);
    
					}else if(("Bytes").equals(typeToGenerate) || ("BigDecimal").equals(typeToGenerate)){
					
    stringBuffer.append(TEXT_345);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_347);
    
					}else if(("Byte").equals(typeToGenerate) || ("Double").equals(typeToGenerate) || ("Float").equals(typeToGenerate) || ("Int").equals(typeToGenerate) || ("Long").equals(typeToGenerate) || ("Short").equals(typeToGenerate)){
					
    stringBuffer.append(TEXT_348);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_351);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_352);
    
					}else{
					
    stringBuffer.append(TEXT_353);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_354);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_355);
    
					}
					
    stringBuffer.append(TEXT_356);
    
				}else{
				
    stringBuffer.append(TEXT_357);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_358);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_360);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_362);
    stringBuffer.append(typeToGenerate);
    stringBuffer.append(TEXT_363);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_364);
    
				}
				
    		
				if(column.isNullable()) {

    stringBuffer.append(TEXT_365);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_366);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_367);
    
				} else {

    stringBuffer.append(TEXT_368);
    	
				}

    stringBuffer.append(TEXT_369);
    
			}
		}		
		//---------end according schema type to generate ResultSet
	}//end class
	
	dbInputBeginUtil = new ValidRowsInputBeginUtil();

    stringBuffer.append(TEXT_370);
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	INode node = (INode)codeGenArgument.getArgument();
	String cid = node.getUniqueName();
	String type = ElementParameterParser.getValue(node, "__TYPE__");
	String dbhost = ElementParameterParser.getValue(node, "__HOST__");
	String dbport = ElementParameterParser.getValue(node, "__PORT__");
	String dbname = ElementParameterParser.getValue(node, "__DBNAME__");
	String dbproperties = ElementParameterParser.getValue(node, "__PROPERTIES__");
	String dbuser = ElementParameterParser.getValue(node, "__USER__");

	String dbencoding = ElementParameterParser.getValue(node, "__ENCODING__");
	String enableStream = ElementParameterParser.getValue(node, "__ENABLE_STREAM__");
	String dbms=ElementParameterParser.getValue(node, "__MAPPING__");
	if("tJDBCInput".equals(node.getComponent().getName())) {
		boolean isEnableMapping = ("true").equals(ElementParameterParser.getValue(node, "__ENABLE_MAPPING__"));
		if (!isEnableMapping) {
			dbms = "";
		}
	}
	boolean isLog4jEnabled = ("true").equals(ElementParameterParser.getValue(node.getProcess(), "__LOG4J_ACTIVATE__"));

    boolean whetherTrimAllCol = ("true").equals(ElementParameterParser.getValue(node, "__TRIM_ALL_COLUMN__"));
    List<Map<String, String>> trimColumnList = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__TRIM_COLUMN__");

    dbInputBeginUtil.beforeComponentProcess(node);
    int dynamic_index=-1;
	List<IMetadataTable> metadatas = node.getMetadataList();
	if ((metadatas != null) && (metadatas.size()>0)) {
		IMetadataTable metadata = metadatas.get(0);
		if (metadata != null) {

    stringBuffer.append(TEXT_371);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_372);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_373);
    
		    String useExistingConn = ElementParameterParser.getValue(node,"__USE_EXISTING_CONNECTION__");
		    if(("true").equals(useExistingConn)) {
		        String connection = ElementParameterParser.getValue(node,"__CONNECTION__");
		        String conn = "conn_" + connection;
                if(connection.matches("^.*?tAmazonAuroraConnection_\\d+$")){
                      conn += "_in";
                }

    stringBuffer.append(TEXT_374);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_375);
    stringBuffer.append(conn);
    stringBuffer.append(TEXT_376);
    
                INode connectionNode = null;
                for (INode processNode : node.getProcess().getGeneratingNodes()) {
                	String nodeUniqueName = processNode.getUniqueName();
                	if(connection.equals(nodeUniqueName) || (connection+"_in").equals(nodeUniqueName)) {
                		connectionNode = processNode;
                		break;
                	}
                }
		        boolean specify_alias = "true".equals(ElementParameterParser.getValue(connectionNode, "__SPECIFY_DATASOURCE_ALIAS__"));
		        if(specify_alias){
	                String alias = ElementParameterParser.getValue(connectionNode, "__DATASOURCE_ALIAS__");

    stringBuffer.append(TEXT_377);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_381);
    stringBuffer.append((null != alias && !("".equals(alias)))?alias:"\"\"");
    stringBuffer.append(TEXT_382);
    stringBuffer.append(conn);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_384);
    
		        }
		        
    stringBuffer.append(TEXT_385);
    log4jCodeGenerateUtil.useExistConnection(node);
    
				dbInputBeginUtil.afterUseExistConnection(node);

		    } else {
				dbInputBeginUtil.createConnection(node);
				if ("teradata_id".equalsIgnoreCase(dbms)) {

    stringBuffer.append(TEXT_386);
    
      boolean queryBand = "true".equals(ElementParameterParser.getValue(node, "__QUERY_BAND__"));
      boolean activateQueryLogging = "true".equals(ElementParameterParser.getValue(node, "__ACTIVATE_QUERY_LOGGING__"));
      String usrName = ElementParameterParser.getValue(node, "__USER__");
      List<Map<String, String>> queryBandList = (List<Map<String,String>>)ElementParameterParser.getObjectValue(node, "__QUERY_BAND_PARAMETERS__");
      if(queryBand){
    stringBuffer.append(TEXT_387);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_388);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_389);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_390);
    
                    if(activateQueryLogging){
                        
    stringBuffer.append(TEXT_391);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_392);
    stringBuffer.append( usrName );
    stringBuffer.append(TEXT_393);
    
                    }
                  
    stringBuffer.append(TEXT_394);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_395);
    
            for(Map<String, String> map : queryBandList) {

    stringBuffer.append(TEXT_396);
    stringBuffer.append( map.get("KEY") );
    stringBuffer.append(TEXT_397);
    stringBuffer.append( map.get("VALUE") );
    stringBuffer.append(TEXT_398);
    
            }

    stringBuffer.append(TEXT_399);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_400);
    stringBuffer.append( cid );
    stringBuffer.append(TEXT_401);
    }
    
				}
		    }

    stringBuffer.append(TEXT_402);
    dbInputBeginUtil.createStatement(node);
    stringBuffer.append(TEXT_403);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_404);
    stringBuffer.append(dbInputBeginUtil.getQueryString(node));
    stringBuffer.append(TEXT_405);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_406);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_407);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
     
                if(cid.matches("^.*?tAmazonAuroraInput_\\d+_in$")){
                    // why 3: ==> "_in".length()
                    
    stringBuffer.append(TEXT_410);
    stringBuffer.append( cid.substring(0,cid.length() - 3) );
    stringBuffer.append(TEXT_411);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_412);
    
                }else{
                    
    stringBuffer.append(TEXT_413);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_414);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_415);
    
            }
             
    stringBuffer.append(TEXT_416);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_417);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_418);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_419);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_421);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_422);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_423);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_424);
    
		    List< ? extends IConnection> conns = node.getOutgoingSortedConnections();
		    List<IMetadataColumn> columnList = metadata.getListColumns();
		    boolean isDynamic = metadata.isDynamicSchema();
		    if(isDynamic){
		    	String DynamicDatePattern = "\"dd-MM-yyyy\"";
		    	for(IMetadataColumn column : columnList) {
		    		if("id_Dynamic".equals(column.getTalendType())) {
		    			DynamicDatePattern = column.getPattern();
		    			break;
		    		}
		    	}
		    
    stringBuffer.append(TEXT_425);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_426);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_427);
    stringBuffer.append(dbms );
    stringBuffer.append(TEXT_428);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_429);
    

		    for(int i=0; i< columnList.size(); i++) {
		        if(columnList.get(i).getTalendType().equals("id_Dynamic")){
		            dynamic_index = i+1;
		        }else{
		            IMetadataColumn column = columnList.get(i);
		            String columName = column.getOriginalDbColumnName();
		            if(columName==null || "".equals(columName)){
		                columName = column.getLabel();
		            }
	            
    stringBuffer.append(TEXT_430);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_431);
    stringBuffer.append(columName.toUpperCase());
    stringBuffer.append(TEXT_432);
    
		    	}
			}
		    
    stringBuffer.append(TEXT_433);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_434);
    stringBuffer.append(dynamic_index-1);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(dynamic_index);
    stringBuffer.append(TEXT_436);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_437);
    stringBuffer.append(columnList.size()-dynamic_index);
    stringBuffer.append(TEXT_438);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_439);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_441);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_442);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_444);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_445);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_446);
    stringBuffer.append(dbms );
    stringBuffer.append(TEXT_447);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_448);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_449);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_450);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_452);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_453);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_454);
     if((DynamicDatePattern!=null) && (!"".equals(DynamicDatePattern)) && (!"\"\"".equals(DynamicDatePattern))) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(DynamicDatePattern);
    stringBuffer.append(TEXT_457);
     } 
    
		if (("oracle_id".equalsIgnoreCase(dbms))) {

    stringBuffer.append(TEXT_458);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_460);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_461);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_462);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_464);
    
		} else {

    stringBuffer.append(TEXT_465);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_466);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_467);
    
		}

    stringBuffer.append(TEXT_468);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_469);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_470);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_471);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_472);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_473);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_474);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_475);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_476);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_477);
    
		    }
		    
    stringBuffer.append(TEXT_478);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_479);
    if(isDynamic){
    stringBuffer.append(TEXT_480);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_481);
    }
    stringBuffer.append(TEXT_482);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_483);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_484);
    }
    stringBuffer.append(TEXT_485);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_486);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_487);
    
		        if(conns != null && conns.size() > 0) {
		            IConnection conn = conns.get(0);
		            String firstConnName = conn.getName();
		            int currentColNo = 1;
		            for(IMetadataColumn column : columnList) {
		                boolean whetherTrimCol = false;
		                if((trimColumnList != null && trimColumnList.size() > 0) && !whetherTrimAllCol) {
		                    for(Map<String, String> trimColumn : trimColumnList) {
		                        if(column.getLabel().equals(trimColumn.get("SCHEMA_COLUMN"))) {
		                            if(("true").equals(trimColumn.get("TRIM"))) {
		                                whetherTrimCol = true;
		                                break;
		                            }
		                        }
		                    }
		                }
		                String trimMethod = "";
		                if(whetherTrimAllCol || whetherTrimCol) {
		                    trimMethod = ".trim()";
		                }
		                String columnType = column.getType();

		                String typeToGenerate = JavaTypesManager.getTypeToGenerate(column.getTalendType(), column.isNullable());
		                String defVal = JavaTypesManager.getDefaultValueFromJavaType(typeToGenerate);
		                if(conn.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA)) {
							if(isDynamic){
								if(!("Dynamic").equals(typeToGenerate) && dynamic_index < currentColNo) {
								
    stringBuffer.append(TEXT_488);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_489);
    stringBuffer.append(currentColNo-1);
    stringBuffer.append(TEXT_490);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_491);
    }else{
    stringBuffer.append(TEXT_492);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_493);
    stringBuffer.append(currentColNo);
    stringBuffer.append(TEXT_494);
    }
    stringBuffer.append(TEXT_495);
    
							}
							
    stringBuffer.append(TEXT_496);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_497);
    if(isDynamic){
    stringBuffer.append(TEXT_498);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_499);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_500);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_501);
    stringBuffer.append(defVal);
    stringBuffer.append(TEXT_502);
    
								typeToGenerate = dbInputBeginUtil.mappingType(typeToGenerate);

								if(("Char").equals(typeToGenerate) || ("Character").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_503);
    dbInputBeginUtil.generateStringCharAndCharacterSet(firstConnName, column,currentColNo, trimMethod, typeToGenerate, whetherTrimAllCol, whetherTrimCol);
    
								} else if(("Timestamp").equals(typeToGenerate)) {
									if("vertica_id".equalsIgnoreCase(dbms) && "DATE".equalsIgnoreCase(column.getType())){

    stringBuffer.append(TEXT_504);
    dbInputBeginUtil.generateOthersResultSet(firstConnName, column,  currentColNo,  "Date");
    
									}else{

    stringBuffer.append(TEXT_505);
    dbInputBeginUtil.generateTimestampResultSet(firstConnName, column, currentColNo);
    
									}
		                         } else if (("List").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_506);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_507);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_509);
    if(isDynamic){
    stringBuffer.append(TEXT_510);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_511);
    
		                        } else if(("String").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_512);
    dbInputBeginUtil.generateStringResultSet(firstConnName, column, currentColNo,trimMethod);
    
								} else if(("Double").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_513);
    dbInputBeginUtil.generateDoubleResultSet(firstConnName, column, currentColNo);
    
								} else if(("Boolean").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_514);
    dbInputBeginUtil.generateBooleanResultSet(firstConnName, column, currentColNo);
    
								} else if("Geometry".equals(typeToGenerate) && type.indexOf("ORACLE") >= 0) {

    stringBuffer.append(TEXT_515);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_516);
    if(isDynamic){
    stringBuffer.append(TEXT_517);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_518);
    stringBuffer.append(TEXT_519);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_520);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(TEXT_522);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_523);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_524);
    
                                    String sourceCRS = ElementParameterParser.getValue(node,"__CRS__");
                                    String forceCRS = ElementParameterParser.getValue(node,"__FORCE_CRS__");
                                    if (forceCRS.equals("true")) {

    stringBuffer.append(TEXT_525);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_526);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(TEXT_528);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_529);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(sourceCRS);
    stringBuffer.append(TEXT_531);
    
                                    }
								} else if(("Bytes").equals(typeToGenerate) && (columnType != null && (("LONG RAW").equals(columnType) || ("RAW").equals(columnType)))) {//oracle

    stringBuffer.append(TEXT_532);
    dbInputBeginUtil.generateBytesResultSet(firstConnName, column, currentColNo);
    
								} else if(("Dynamic").equals(typeToGenerate)) {

    stringBuffer.append(TEXT_533);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_534);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_535);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_536);
     //for bug TDI-20886
									boolean trim = whetherTrimAllCol || whetherTrimCol;
									if ("id_MSSQL".equalsIgnoreCase(dbms)) {

    stringBuffer.append(TEXT_537);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_538);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_539);
    stringBuffer.append(metadata.getListColumns().size());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_541);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_542);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_543);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_544);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_545);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_546);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_547);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_548);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_549);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_550);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_551);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_552);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_553);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_554);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_555);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_556);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_557);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_558);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_559);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_560);
    
									}else if("access_id".equalsIgnoreCase(dbms)){

    stringBuffer.append(TEXT_561);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_562);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_564);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_565);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_566);
    
									}else{

    stringBuffer.append(TEXT_567);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_568);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_570);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_571);
    stringBuffer.append(trim);
    stringBuffer.append(TEXT_572);
    
									}
								} else if(typeToGenerate.equals("Geometry")) {

    stringBuffer.append(TEXT_573);
    stringBuffer.append(cid );
    stringBuffer.append(TEXT_574);
    if(isDynamic){
    stringBuffer.append(TEXT_575);
    stringBuffer.append(cid);
    }else{
    stringBuffer.append(currentColNo);
    }
    stringBuffer.append(TEXT_576);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_577);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_579);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_580);
    
                 	            } else {

    stringBuffer.append(TEXT_581);
    dbInputBeginUtil.generateOthersResultSet( firstConnName, column,  currentColNo,  typeToGenerate);
    
		                        }

    stringBuffer.append(TEXT_582);
    
		                    currentColNo++;
		                }
		            }

    stringBuffer.append(TEXT_583);
    if(isLog4jEnabled){
    stringBuffer.append(TEXT_584);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_585);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_586);
    }
    
		            if(conns.size() > 1) {
		                for(int connNO = 1 ; connNO < conns.size() ; connNO++) {
		                    IConnection conn2 = conns.get(connNO);
		                    if((conn2.getName().compareTo(firstConnName) != 0) && (conn2.getLineStyle().hasConnectionCategory(IConnectionCategory.DATA))) {
		                        for(IMetadataColumn column:columnList){
		                            
    stringBuffer.append(TEXT_587);
    stringBuffer.append(conn2.getName());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(firstConnName);
    stringBuffer.append(TEXT_590);
    stringBuffer.append(column.getLabel());
    stringBuffer.append(TEXT_591);
    
		                        }
		                    }
		                }
		            }
		        }
		}
	}

    stringBuffer.append(TEXT_592);
    return stringBuffer.toString();
  }
}
