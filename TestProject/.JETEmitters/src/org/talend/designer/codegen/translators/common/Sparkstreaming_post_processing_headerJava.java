package org.talend.designer.codegen.translators.common;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.talend.core.model.metadata.IMetadataColumn;
import org.talend.core.model.metadata.IMetadataTable;
import org.talend.core.model.metadata.types.JavaType;
import org.talend.core.model.metadata.types.JavaTypesManager;
import org.talend.core.model.process.EConnectionType;
import org.talend.core.model.process.ElementParameterParser;
import org.talend.core.model.process.IConnection;
import org.talend.core.model.process.IConnectionCategory;
import org.talend.core.model.process.IHashConfiguration;
import org.talend.core.model.process.IHashableColumn;
import org.talend.core.model.process.IHashableInputConnections;
import org.talend.core.model.process.INode;
import org.talend.core.model.process.IProcess;
import org.talend.core.model.utils.NodeUtil;
import org.talend.core.utils.TalendQuoteUtils;
import org.talend.designer.codegen.config.NodesSubTree;
import org.talend.designer.codegen.config.CodeGeneratorArgument;

public class Sparkstreaming_post_processing_headerJava
{
  protected static String nl;
  public static synchronized Sparkstreaming_post_processing_headerJava create(String lineSeparator)
  {
    nl = lineSeparator;
    Sparkstreaming_post_processing_headerJava result = new Sparkstreaming_post_processing_headerJava();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "    public void ";
  protected final String TEXT_2 = "PostProcess(final org.apache.spark.streaming.api.java.JavaStreamingContext ctx, GlobalVar globalMap) throws java.lang.Exception {" + NL + "        FileSystem fs = FileSystem.get(ctx.sparkContext().hadoopConfiguration());" + NL + "        final JobConf job = new JobConf(ctx.sparkContext().hadoopConfiguration());" + NL + "" + NL + "        try {";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
    CodeGeneratorArgument codeGenArgument = (CodeGeneratorArgument) argument;
    Vector v = (Vector) codeGenArgument.getArgument();
    NodesSubTree subTree = (NodesSubTree)v.get(1);

    stringBuffer.append(TEXT_1);
    stringBuffer.append(subTree.getName());
    stringBuffer.append(TEXT_2);
    return stringBuffer.toString();
  }
}
