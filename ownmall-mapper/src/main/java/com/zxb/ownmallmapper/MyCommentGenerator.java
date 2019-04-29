package com.zxb.ownmallmapper;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.Field;
import org.mybatis.generator.api.dom.java.TopLevelClass;
import org.mybatis.generator.internal.DefaultCommentGenerator;
import org.mybatis.generator.internal.util.StringUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

/**
 * 自定义注释生成器
 */
public class MyCommentGenerator extends DefaultCommentGenerator {
    private  boolean addRemarkComments = false;
    private Properties properties;

    public MyCommentGenerator(Properties properties){
        properties = new Properties();
    }
    /**
     * 设置用户配置参数
     * @param properties
     */
    @Override
    public void addConfigurationProperties(Properties properties) {
//        super.addConfigurationProperties(properties);
//        this.addRemarkComments = StringUtility.isTrue(properties.getProperty("addRemarkComments"));
        this.properties.putAll(properties);
    }


    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        String author = properties.getProperty("author");
        String dateFormat = properties.getProperty("dataFormat","yyyy-MM-dd");
        SimpleDateFormat dateFormatter = new SimpleDateFormat(dateFormat);

        //获取表注释
        String remarks = introspectedTable.getRemarks();
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine("*"+remarks);
        topLevelClass.addJavaDocLine("*");
        topLevelClass.addJavaDocLine("*@author"+ author);
        topLevelClass.addJavaDocLine("* @date" + dateFormatter.format(new Date()));
        topLevelClass.addJavaDocLine("*/");
    }

    /**
     * 给字段添加注释
     * @param field
     * @param introspectedTable
     */
    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        String remarks = introspectedColumn.getRemarks();
        //根据参数和备注信息，判断是否添加备注信息
//        if(addRemarkComments&&StringUtility.stringHasValue(remarks)){
//            //文档注释开始
//            field.addJavaDocLine("/**");
//            //获取数据库字段的备注信息
//            String[] remarkLines = remarks.split(System.getProperty("line.separator"));
//            for (String remarkLine:remarkLines){
//                field.addJavaDocLine("*"+remarkLine);
//            }
//            addJavadocTag(field,false);
//            field.addJavaDocLine("*/");
//        }

        field.addJavaDocLine("*" + remarks);
        field.addJavaDocLine("*/");
    }


}
