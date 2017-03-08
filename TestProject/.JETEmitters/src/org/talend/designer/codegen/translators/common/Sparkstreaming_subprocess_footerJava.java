package org.talend.designer.codegen.translators.common;

import java.util.Vector;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class Sparkstreaming_subprocess_footerJava
{
  protected static String nl;
  public static synchronized Sparkstreaming_subprocess_footerJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Sparkstreaming_subprocess_footerJava result = new Sparkstreaming_subprocess_footerJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "\t    } catch(java.lang.Exception e) {" + NL + "\t        ";
  protected final String TEXT_2 = NL + "                if (junitGlobalMap.get(\"tests.log\") == null) {" + NL + "                    junitGlobalMap.put(\"tests.log\", new String());" + NL + "                }" + NL + "                if (junitGlobalMap.get(\"tests.nbFailure\") == null) {" + NL + "                    junitGlobalMap.put(\"tests.nbFailure\", new Integer(0));" + NL + "                }" + NL + "                junitGlobalMap.put(\"tests.nbFailure\"," + NL + "                        ((Integer) junitGlobalMap.get(\"tests.nbFailure\")) + 1);" + NL + "                junitGlobalMap.put(\"tests.log\", \"Error on job: \" + e);";
  protected final String TEXT_3 = NL + "\t    \tthrow e;" + NL + "\t    }" + NL + "    }";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
	CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
	Vector v = (Vector) codeGenArgument.getArgument();
	INode sparkConfig = (INode)v.get(0);


    stringBuffer.append(TEXT_1);
    
            // Validate result of test cases
            org.talend.core.model.process.IProcess process = sparkConfig.getProcess();
            boolean isTestContainer = org.talend.core.model.process.ProcessUtils.isTestContainer(process);
            if (isTestContainer) {
                
    stringBuffer.append(TEXT_2);
    
            }
            
    stringBuffer.append(TEXT_3);
    return stringBuffer.toString();
  }
}
