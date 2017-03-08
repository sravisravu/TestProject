package org.talend.designer.codegen.translators.common;

import java.util.Vector;
import java.util.List;
import org.talend.designer.codegen.config.CodeGeneratorArgument;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.process.INode;
import org.talend.core.model.metadata.IMetadataTable;

public class Header_additional_templateJava
{
  protected static String nl;
  public static synchronized Header_additional_templateJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Header_additional_templateJava result = new Header_additional_templateJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    private IPaasObject iPaasObject;" + NL;
  protected final String TEXT_2 = NL + "    public class IPaasFieldImpl implements IPaasField {" + NL + "" + NL + "        private String name;" + NL + "        private String type;" + NL + "        private int length;" + NL + "        private String defaultValue;" + NL + "" + NL + "        public IPaasFieldImpl(String name) {" + NL + "            this.name = name;" + NL + "        }" + NL + "" + NL + "        public String getType() {" + NL + "            return this.type;" + NL + "        }" + NL + "" + NL + "        public void setType(String type) {" + NL + "            this.type = type;" + NL + "        }" + NL + "" + NL + "        public String getName() {" + NL + "            return this.name;" + NL + "        }" + NL + "" + NL + "        public void setName(String name) {" + NL + "            this.name = name;" + NL + "        }" + NL + "" + NL + "        public String getDefault() {" + NL + "            return this.defaultValue;" + NL + "        }" + NL + "" + NL + "        public void setDefault(String defaultValue) {" + NL + "            this.defaultValue = defaultValue;" + NL + "        }" + NL + "" + NL + "        public int getLength() {" + NL + "            return this.length;" + NL + "        }" + NL + "" + NL + "        public void setLength(int length) {" + NL + "            this.length = length;" + NL + "        }" + NL + "    }";
  protected final String TEXT_3 = NL + "    public TalendJob newTalendStepTemplate(IPaasObject iPaasObject) {" + NL + "        //TODO: impelement like newTalendESBJob";
  protected final String TEXT_4 = NL + "        ";
  protected final String TEXT_5 = " talendStepTemplate = new ";
  protected final String TEXT_6 = "();" + NL + "        talendStepTemplate.iPaasObject = iPaasObject;" + NL + "        return talendStepTemplate;" + NL + "    }" + NL;
  protected final String TEXT_7 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
Vector v = (Vector) codeGenArgument.getArgument();
IProcess process = (IProcess) v.get(0);

boolean talendStepTemplate = !process.getNodesOfType("tActionInput").isEmpty() || !process.getNodesOfType("tActionOutput").isEmpty() || !process.getNodesOfType("tActionReject").isEmpty();
if (talendStepTemplate) {
    boolean hasDynamic = false;
    for (INode node : process.getNodesOfType("tActionInput")) {
        List<IMetadataTable> metadatas = node.getMetadataList();
        if (null != metadatas && !metadatas.isEmpty()) {
            if (metadatas.get(0).isDynamicSchema()) {
                hasDynamic = true;
                break;
            }
        }
    }
    if (!hasDynamic) {
        for (INode node : process.getNodesOfType("tActionOutput")) {
            List<IMetadataTable> metadatas = node.getMetadataList();
            if (null != metadatas && !metadatas.isEmpty()) {
                if (metadatas.get(0).isDynamicSchema()) {
                   hasDynamic = true;
                   break;
                }
            }
        }
    }

    stringBuffer.append(TEXT_1);
     if (hasDynamic) { 
    stringBuffer.append(TEXT_2);
     } 
    stringBuffer.append(TEXT_3);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_5);
    stringBuffer.append(process.getName());
    stringBuffer.append(TEXT_6);
     } // end if (talendStepTemplate) 
    stringBuffer.append(TEXT_7);
    return stringBuffer.toString();
  }
}
