package org.talend.designer.codegen.translators.databases.db_jdbc;

public class TJDBCLookupInputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TJDBCLookupInputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TJDBCLookupInputSparkstreamingconfigJava result = new TJDBCLookupInputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    return stringBuffer.toString();
  }
}
