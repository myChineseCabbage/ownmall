package com.zxb.ownmallmapper;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    public static  void  main(String[] args) throws Exception{
        //MBG执行过程中的警告信息
        List<String> warnings = new ArrayList<String>();
        //当生成的代码重复时覆盖源代码
        boolean overwrite = true;
        //读取我们的MBG配置文件
        InputStream is = Generator.class.getResourceAsStream("/generatorConfig.xml");
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration configuration = cp.parseConfiguration(is);
        is.close();

        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        //创建MBG
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(configuration,callback,warnings);
        //执行生成代码
        myBatisGenerator.generate(null);
        //输出警告信息

        for(String waraning:warnings){
            System.out.println(waraning);
        }
    }
}
