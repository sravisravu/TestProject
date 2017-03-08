package org.talend.designer.codegen.translators.processing;

import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.INode;
import org.talend.designer.common.BigDataCodeGeneratorArgument;
import org.talend.designer.common.tfilterrow.TFilterRowUtil;
import org.talend.designer.common.tfilterrow.TFilterRowUtil.LogicalOperator;

public class TFilterRowSparkcodeJava
{
  protected static String nl;
  public static synchronized TFilterRowSparkcodeJava create(String lineSeparator)
  {
    nl = lineSeparator;
    TFilterRowSparkcodeJava result = new TFilterRowSparkcodeJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + "        public static class Filter_";
  protected final String TEXT_3 = "_";
  protected final String TEXT_4 = " implements org.talend.bigdata.dataflow.hmap.filter.Condition<";
  protected final String TEXT_5 = ">, org.talend.bigdata.dataflow.hmap.PostProcessor<";
  protected final String TEXT_6 = "> {" + NL + "\t\t  ";
  protected final String TEXT_7 = NL + "\t\t  public static class Filter_";
  protected final String TEXT_8 = "_";
  protected final String TEXT_9 = " implements org.talend.bigdata.dataflow.hmap.filter.Condition<";
  protected final String TEXT_10 = "> {" + NL + "\t\t  ";
  protected final String TEXT_11 = NL + "            /** Default serial version UID. */" + NL + "            private static final long serialVersionUID = 1L;" + NL + "" + NL + "            private final ContextProperties context;" + NL;
  protected final String TEXT_12 = NL + "            \tprivate boolean matches = true;";
  protected final String TEXT_13 = NL + NL + "            private String rejectCause;" + NL + "" + NL + "            Filter_";
  protected final String TEXT_14 = "_";
  protected final String TEXT_15 = "(JobConf job) {" + NL + "\t\t\t\t\tcontext = new ContextProperties(job);" + NL + "            }" + NL + "" + NL + "            public boolean evaluate(";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = ") {" + NL + "            \tclearCondition();" + NL + "            \t";
  protected final String TEXT_18 = NL + "            \tif(";
  protected final String TEXT_19 = "){" + NL + "            \t\treturn true;" + NL + "            \t}else{" + NL + "            \t\tappendToRejectCause(\"";
  protected final String TEXT_20 = "\");" + NL + "            \t}";
  protected final String TEXT_21 = NL + "               return false;";
  protected final String TEXT_22 = NL + "            \tif(!(";
  protected final String TEXT_23 = ")){" + NL + "            \t\tmatches = false;" + NL + "            \t\tappendToRejectCause(\"";
  protected final String TEXT_24 = "\");" + NL + "            \t}" + NL + "            \t";
  protected final String TEXT_25 = NL + "            \t\treturn matches;" + NL + "            \t";
  protected final String TEXT_26 = NL + "            }" + NL;
  protected final String TEXT_27 = NL + "\t\t      public void postProcess(";
  protected final String TEXT_28 = " record) {" + NL + "\t\t      \trecord.setErrorMessage(rejectCause);" + NL + "\t\t      }" + NL + "\t\t      ";
  protected final String TEXT_29 = NL + NL + "           \tpublic String getRejectCause(){" + NL + "            \treturn rejectCause;" + NL + "            }" + NL + "" + NL + "            private void appendToRejectCause(String rejectReason){" + NL + "            \tif(rejectCause == null){" + NL + "            \t\trejectCause = rejectReason;" + NL + "            \t}else{" + NL + "            \t\trejectCause += \"|\" + rejectReason;" + NL + "            \t}" + NL + "            }" + NL + "" + NL + "            private void clearCondition(){" + NL + "            \tif(rejectCause != null){" + NL + "            \t\trejectCause = null;" + NL + "            \t}" + NL + "            \t";
  protected final String TEXT_30 = NL + "            \t\t\tmatches = true;" + NL + "            \t";
  protected final String TEXT_31 = NL + "            }" + NL + "        }";
  protected final String TEXT_32 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
final BigDataCodeGeneratorArgument codeGenArgument = (BigDataCodeGeneratorArgument)argument;
INode node = (INode)codeGenArgument.getArgument();
String cid = node.getUniqueName();

TFilterRowUtil tFilterRowUtil = new TFilterRowUtil(node);

/**
 * Helper class for generating Filter conditions.
 */
class TFilterRowSparkHelper {
    public void generateFilterCondition(TFilterRowUtil tFilterRowUtil, String cid) {

        String inStructName = codeGenArgument.getRecordStructName(tFilterRowUtil.getIncomingConnection());
        String rejectStructName = codeGenArgument.getRecordStructName(tFilterRowUtil.getRejectConnection());

        // Ignore any
        if (tFilterRowUtil.getConditions() == null || tFilterRowUtil.getConditions().size() < 1) {
				return;
        }

        String connName = tFilterRowUtil.getIncomingConnection().getName();
        LogicalOperator operator = tFilterRowUtil.getLogicalOperator();
        
    stringBuffer.append(TEXT_1);
    
        if(tFilterRowUtil.getRejectConnection() != null) {
        
    stringBuffer.append(TEXT_2);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_4);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(rejectStructName);
    stringBuffer.append(TEXT_6);
    
		  } else {
		  
    stringBuffer.append(TEXT_7);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_10);
    
		  }
		  
    stringBuffer.append(TEXT_11);
    
            	if (LogicalOperator.AND.equals(operator)){
            
    stringBuffer.append(TEXT_12);
    
            	}
            
    stringBuffer.append(TEXT_13);
    stringBuffer.append(cid);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(inStructName);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(connName);
    stringBuffer.append(TEXT_17);
    
            	if (LogicalOperator.OR.equals(operator)) {
            		for(int i=0; i < tFilterRowUtil.getConditions().size(); i++){
            			String filteringExpression = tFilterRowUtil.getFilteringExpression(i);
            			String rejectReason = tFilterRowUtil.getRejectReason(i);
            	
    stringBuffer.append(TEXT_18);
    stringBuffer.append(filteringExpression);
    stringBuffer.append(TEXT_19);
    stringBuffer.append(rejectReason);
    stringBuffer.append(TEXT_20);
    
               	} // end for
               
    stringBuffer.append(TEXT_21);
    
               } else {
               	for(int i=0; i < tFilterRowUtil.getConditions().size(); i++){
            			String filteringExpression = tFilterRowUtil.getFilteringExpression(i);
            			String rejectReason = tFilterRowUtil.getRejectReason(i);
            	
    stringBuffer.append(TEXT_22);
    stringBuffer.append(filteringExpression);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(rejectReason);
    stringBuffer.append(TEXT_24);
    
            		} // end for
            	
    stringBuffer.append(TEXT_25);
    
               } // end else
               
    stringBuffer.append(TEXT_26);
    
            if(tFilterRowUtil.getRejectConnection() != null) {
            
    stringBuffer.append(TEXT_27);
    stringBuffer.append(rejectStructName);
    stringBuffer.append(TEXT_28);
    
		      }
		      
    stringBuffer.append(TEXT_29);
    
            		if (LogicalOperator.AND.equals(operator)){
           		
    stringBuffer.append(TEXT_30);
    
            		}
            	
    stringBuffer.append(TEXT_31);
    
    } // end TFilterRowSparkHelper.generateFilterCondition()
} // end class TFilterRowSparkHelper

TFilterRowSparkHelper helper = new TFilterRowSparkHelper();
helper.generateFilterCondition(tFilterRowUtil, cid);

    stringBuffer.append(TEXT_32);
    return stringBuffer.toString();
  }
}
