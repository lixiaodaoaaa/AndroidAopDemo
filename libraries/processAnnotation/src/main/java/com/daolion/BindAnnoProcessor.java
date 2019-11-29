package com.daolion;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeSpec;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.tools.Diagnostic;

/*
    ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓ 
       Author   :  lixiaodaoaaa
       Date     :  2019-11-22
       Time     :  15:20
    ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛
 */

@AutoService(Processor.class)
public class BindAnnoProcessor extends AbstractProcessor {

    private Filer filer;
    //用于记录需要绑定的View的名称和对应的id
    private Map<TypeElement,Set<ViewInfo>> typeElementWithViewInfoMap = new HashMap<>();
    private Messager messager;
    private Elements elementsUtils;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);
        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        elementsUtils = processingEnv.getElementUtils();
    }


    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {

        //找到所有标注 BindViewAnnotation 元素的Elements;
        Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(BindViewAnnotation.class);
        //没有被标注则返回：
        if (elements.size() == 0 || elements == null) {
            return true;
        }
        for (Element element : elements) {

            if (element.getKind() != ElementKind.FIELD) {
                messager.printMessage(Diagnostic.Kind.ERROR,
                        "bindViewAnnotation must assigned as variableElement", element);
                System.out.println("bindViewAnnotation must assigned as variableElement ,please check your code");
                continue;
            }

            // 强转
            VariableElement variableElement = (VariableElement) element;
            TypeElement activityElement = (TypeElement) element.getEnclosingElement();
            //得到包名;
            String qualifiedName = activityElement.getQualifiedName().toString();
            // 获取类名
            String className = activityElement.getSimpleName().toString();
            // 获取注解类的包名，将生成后的代码放置一个包名下
            String packageName = elementsUtils.getPackageOf(activityElement).getQualifiedName().toString();
            //com.daolion.MainActivity
            ClassName hostName = ClassName.bestGuess(qualifiedName);
            // 获取变量的名字
            String variableName = variableElement.getSimpleName().toString();
            // 获取变量的类型
            String variableType = variableElement.asType().toString();
            // 获取注解的值，也就是变量的id
            //@BindView(R.id.tv_remind_text) TextView tv_remind_text;
            BindViewAnnotation annotation = element.getAnnotation(BindViewAnnotation.class);
            int id = annotation.value();

            // 生成代码
            // 手动拼接示例代码
            // javapoet框架生成代码
            MethodSpec method = MethodSpec.methodBuilder("inject")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(void.class)
                    .addAnnotation(Override.class)
                    .addParameter(hostName, "host")
                    .addCode("" +
                            "if(host instanceof android.app.Activity)" +
                            "{" +
                            "host." + variableName + " = (" + variableType + ")(" + "((android.app.Activity)host).findViewById(" + id + "));" +
                            "}" +
                            ""
                    ).build();


            ClassName interfaceName = ClassName.bestGuess("com.daolion.ViewInjector");
            TypeSpec classType = TypeSpec.classBuilder(className + "_ViewInjector")
                    .addModifiers(Modifier.PUBLIC)
                    .addMethod(method)
                    .addSuperinterface(ParameterizedTypeName.get(interfaceName, hostName))
                    .build();

            JavaFile javaFile = JavaFile.builder(packageName, classType)
                    .addFileComment("this file don't modify")
                    .build();
            try {
                javaFile.writeTo(filer);
            } catch (IOException e) {
                e.printStackTrace();
            }

            Set<ViewInfo> viewInfos = typeElementWithViewInfoMap.get(activityElement);
            if (viewInfos == null) {
                Set<ViewInfo> viewInfoSet = new HashSet<>();
                typeElementWithViewInfoMap.put(activityElement, viewInfoSet);
            }
        }

        //获取所在类的信息

        //按类存入map中
        return true;
    }


    private void addElement(Map<Element,List<Element>> map, Element clazz, Element field) {
        List<Element> list = map.get(clazz);
        if (list == null) {
            list = new ArrayList<>();
            map.put(clazz, list);
        }
        list.add(field);
    }


    /**
     * @param methodStr 方法名
     * @param returnStr 返回值
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
        Set<String> antations = new HashSet<>();
        antations.add(HelloAnnotation.class.getCanonicalName());
        antations.add(BindViewAnnotation.class.getCanonicalName());
        return antations;
    }


    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }


    //要绑定的View的信息载体
    public class ViewInfo {
        //@BindView(R.id.tv_remind_text) TextView tv_remind_text;
        String viewName;    //view的变量名
        int id; //xml中的id

        public ViewInfo(String viewName, int id) {
            this.viewName = viewName;
            this.id = id;
        }
    }

}
