## Android(Java)代码生成技术--JavaPoet初体验之手动实现依赖注入


### 关于分支说明
    ----master分支 （主分支）
    ----develop分支 （开发分支）
    ----dev/具体分支

### 关于Tag说明

#### V1.0
这个Tag 是简单实现了HelloAnnotation Demo实现了自动生成HelloWorld.java 并能在MainActivity中调用HelloWorld.str();
最基本的实现了Aop功能

#### V1.1
  目前开发的版本(develop分支），具体要模仿ButterKnife等功能，实现快速注解等功能。持续更新中的。



### 前言
> 相信大家在平常的开发中，依赖注入这个词没少听说过吧，比如做安卓开发的，使用的Butterknife、Greendao等等第三方库，都是使用的一种叫做编译期代码即时生成的技术，然后我们可以利用编译生成的类来辅助我们的开发，减少我们的工作量，这个技术听上去感觉挺高大上的，编译期间代码生成，这该怎么做到啊，好像从来没有从哪听说编译还能生成代码的，下面让我们来看看这门神奇的技术！

###  编译期代码生成原理 基于JavaPoet技术

  首先在这之前，我们可能或多或少了解到一个叫JavaPoet的技术，首先我们从它开始，我们来到它的源码，你会发现它好像并没有做什么高深的事情，总共才十几个类，而且大多数只是做了一些类的封装，提供一些接口方便使用，然后还提供了一个工具类供我们使用，如图，它的源码仅仅只有下面几个类而已.

###  自动生成代码的原因

这时候就迷了，难道编译期生成代码不是这个库帮我们完成的吗？
准确的说确实不是的，那它的功能是什么呢？它其实只是完成了我们所说的一半的功能，就是代码生成，而且是简单易懂的代码生成，因为它做了封装，对一些常用的代码生成需求基本都提供了相应的方法，大大减少了代码复杂度。那和我们想要的需求来比，少了一个编译期间生成，那这个怎么做呢？

### 关于 AbstractProcessor

其实要实现编译期间做一些事情，这个工作Java已经帮我们做好了，我们先来看一个类AbstractProcessor，这个类可能平常不会用到，但是也没关系，我们简单了解一下它，首先你可以把它理解为一个抽象的注解处理器，它位于javax.annotation.processing.AbstractProcessor;下面，是用于在编译时扫描和处理注解的类，我们要实现类似ButterKnife这样的功能，首先定义一个自己的注解处理器，然后继承它即可，如下

```
        public class MyProcessor extends AbstractProcessor{
            @Override
            public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
                return false;
            }
        }
```


### 更多文章详情

 [原文链接：Android(Java)代码生成技术--JavaPoet初体验之手动实现依赖注入](https://blog.csdn.net/hq942845204/article/details/81185693)

部分文章简介：
![blog文章截图](https://raw.githubusercontent.com/lixiaodaoaaa/publicSharePic/master/%E6%88%AA%E5%B1%8F2019-11-2815.49.54.png)

