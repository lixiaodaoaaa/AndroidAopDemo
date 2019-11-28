package com.daolion;

import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-11-26
       Time     :  17:16
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */
public class TestProcessAnotation {



    public static void main(String[] args) {
        generating();
    }


    public static void generating() {
        TypeSpec helloWorld = TypeSpec.classBuilder("HelloWorld")//声明类名为HelloWorld
                .addModifiers(Modifier.PUBLIC, Modifier.FINAL)//声明类的修饰符为 public final
                .addMethod(getMethodSpec("hello1", "Hello"))//为HelloWorld类添加名为hello1的方法，返回值为Hello
                .addMethod(getMethodSpec("hello2", "Java"))//同上
                .addMethod(getMethodSpec("hello3", "Poet!"))//同上
                .build();

        JavaFile javaFile = JavaFile.builder("com.daolion.new", helloWorld)
                .build();
        try {
            javaFile.writeTo(System.out);
        } catch (Exception e) {

        }

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

}
