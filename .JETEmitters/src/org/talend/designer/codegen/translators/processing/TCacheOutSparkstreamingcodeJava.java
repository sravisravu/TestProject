package org.talend.designer.codegen.translators.processing;

public class TCacheOutSparkstreamingcodeJava
{
  protected static String nl;
  public static synchronized TCacheOutSparkstreamingcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TCacheOutSparkstreamingcodeJava result = new TCacheOutSparkstreamingcodeJava();
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
