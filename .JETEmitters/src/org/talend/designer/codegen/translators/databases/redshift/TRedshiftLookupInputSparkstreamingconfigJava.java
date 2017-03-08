package org.talend.designer.codegen.translators.databases.redshift;

public class TRedshiftLookupInputSparkstreamingconfigJava
{
  protected static String nl;
  public static synchronized TRedshiftLookupInputSparkstreamingconfigJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TRedshiftLookupInputSparkstreamingconfigJava result = new TRedshiftLookupInputSparkstreamingconfigJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    return stringBuffer.toString();
  }
}
