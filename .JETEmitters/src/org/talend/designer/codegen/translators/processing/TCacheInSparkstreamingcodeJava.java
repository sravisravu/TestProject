package org.talend.designer.codegen.translators.processing;

public class TCacheInSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TCacheInSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCacheInSparkstreamingcodeJava result = new TCacheInSparkstreamingcodeJava();
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
