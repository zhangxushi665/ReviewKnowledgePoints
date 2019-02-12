package com.zj.reviewknowledgepoints.javaCode;

/**
 * Created by zj on 2019-02-12 10:35.
 * java 注解
 * https://www.jianshu.com/p/596d389282a0
 */
public class Annotation_Class {

    /**
     *   要理解Annotation的作用，先理解java中元数据的概念
     *
     *      1.元数据概念
     *          元数据是关于数据的数据，在编程语言上下文中，元数据是添加到程序元素如方法、字段、类和包上额外的信息。对数据进行说明描述的数据
     *
     *      2.元数据的作用
     *          可以用于创建文档（根据程序元素上的注释创建文档）
     *          跟踪代码中的依赖性（可以声明方法是重载，依赖父类的方法）
     *          执行编译时检查（可声明是否编译期检测），代码分析
     *
     *      3.java平台元数据
     *          Annotation就是java平台的元数据，该机制允许在java代码中添加自定义注释，并允许通过反射，以编程方式访问元数据注释。通过提供为程序元素（类、方法等）
     *              附加额外数据的标准方法，元数据功能具有简化和改进需用应用程序开发领域的潜在能力，其中包括配置管理、框架实现和代码生成。
     *
     *   Annotation：
     *      1.注解概念：
     *          就是上述元数据的作用。
     *          Annotation能被用来为程序元素（类、方法、成员变量等）设置元数据。
     *          Annotation不影响程序代码的执行，无论增加,删除Annotation，代码都始终如一的执行
     *          如果希望让程序中的Annotation起一定的作用，只有通过解析工具或编译工具对Annotation中的信息进行解析和处理
     *
     *      2.内建注解（主要作用：编译检查）
     *        java提供了多种内建注解，常用的有@override、@Deprecated、@SuppressWarnings、@Functionallnterface（用户告知编译器，检查这个接口，保证这个接口是函数式接口，即只能包含一个抽象方法，否则就会编译错误）
     *
     *      3.元Annotation
     *          java.lang.annotation包下提供了6个元Annotation，其中5个都是用于修饰其他Annotation定义的
     *
     *              了解如何自定义Annotation： 当一个接口直接继承java.lang.annotation.Annotation接口时，仍是接口，而并非注解，要想自定义注解类型，只能通过@interface关键字方式，其实通过该方式会隐含的继承Annotation接口
     *          - @Documented  编写文档
     *
     *          - @Inherited 指定被它修饰的Annotation将具有继承性---如果某个类使用@XXXX注解（定义该Annotation时使用了@inherited修饰）修饰，则其子类将自动的被@XXXX修饰
     *
     *          - @Retention 表示该注解类型的注解保留时长，当注解类型声明中没有@Rentention元注解，则默认保留策略为RententionPolicy.Class
     *
     *          -@Target 表示该注解类型的所使用的程序元素类型。当注解类型声明中没有@Target注解，则默认为可使用所有的程序元素。
     *              Annotation_type - 注解类型声明
     *              Constructor - 构造方法声明
     *              field - 字段声明
     *              local_variable - 局部变量声明
     *              method - 方法声明
     *              package - 包声明
     *              parameter - 参数声明
     *              type - 类、接口（包括注解类型）或枚举声明
     *
     *
     *   自定义注解：
     *
     *          自定义注解中定义成员变量的规则：
     *              其定义是以无形参的方法形式来声明的，即 注解方法不带参数，例如 name（）
     *              注解方法返回值类型：基本类型、String、Enums、Annotation以及前面这些类型的数组类型
     *              注解方法可有默认值
     *
     *          当然注解中可以不存在成员变量，在使用解析注解进行操作时，仅以是否包含该注解进行操作。当注解中成员变量时，若没有默认值，需要在使用注解时，指定成员变量的值
     *
     *
     *    注解解析（通过反射机制）
     *
     *        通过反射技术来解析自定义注解，位于包java.lang.reflect。其中有一个接口AnnotatedElement，该接口主要有如下几个实现类
     *              Class、Constructor、field、method、package
     *        核心方法：
     *              T              getAnnotation（Class annotationClass） 当存在该元素的指定类型注解，则返回相应的注释，否则返回null
     *              Annotation[]   getAnnotations（） 返回此元素上存在的所有注解
     *              Annotation[]   getDeclaredAnnotations（） 返回直接存在此元素上的所有注解
     *              boolean        isAnnotationPresent（Class<? extends Annotation> annotationsClass）当存在该元素的指定类型注解，则返回true，否则返回false
     *
     *        因此，当获取某个类的Class对象，然后获取其field、method等对象，通过上述4个方法提取其中的注解，然后获取注解的详细信息
     *
     *        
     *
     *
     */

}
