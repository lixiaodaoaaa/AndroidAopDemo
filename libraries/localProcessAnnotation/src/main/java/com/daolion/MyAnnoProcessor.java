package com.daolion;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.Collections;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-11-22
       Time     :  15:20
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

@AutoService(Processor.class)
public class MyAnnoProcessor extends AbstractProcessor {

    private Filer filer;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        for (TypeElement element : set) {//遍历扫描到的注解集合
            if (element.getQualifiedName().toString().equals(HelloAnnotation.class.getCanonicalName())) {
                // 当前注解是我们自定义的注解，也就是HelloAnnotation时，执行下列代码
                TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")//声明类名为HelloWorld
                        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)//声明类的修饰符为 public final
                        .addMethod(getMethodSpec("hello1", "Hello"))//为HelloWorld类添加名为hello1的方法，返回值为Hello
                        .addMethod(getMethodSpec("hello2", "Java"))//同上
                        .addMethod(getMethodSpec("hello3", "Poet!"))//同上
                        .build();

                try {
                    // 建立 com.aiiage.testjavapoet.HelloWorld.java 对象
                    JavaFile javaFile = JavaFile.builder("com.daolion.my", helloWorld)
                            .addFileComment(" This codes are generated automatically. Do not modify!")
                            .build();
                    // 写入文件
                    javaFile.writeTo(filer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }



    /**
     * @param methodStr  方法名
     * @param returnStr  返回值
     * @return
     */
    private static MethodSpec getMethodSpec(String methodStr, String returnStr) {
        return MethodSpec.methodBuilder(methodStr)
                .addModifiers(Modifier.PUBLIC, Modifier.STATIC)//指定方法修饰符为 public static
                .returns(String.class) //指定返回值为String类型
                .addStatement("return $S", returnStr) //拼接返回值语句
                .build();
    }


    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Collections.singleton(HelloAnnotation.class.getCanonicalName());
    }



    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

}
