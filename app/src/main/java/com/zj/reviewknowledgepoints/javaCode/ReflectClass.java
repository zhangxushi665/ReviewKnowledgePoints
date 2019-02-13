package com.zj.reviewknowledgepoints.javaCode;

/**
 * Created by zj on 2019-02-12 11:39.
 *  java 反射
 *  https://www.cnblogs.com/rocomp/p/4781987.html
 *  https://blog.csdn.net/u013534071/article/details/80254247
 */
public class ReflectClass {

    /**
     *   1. Class类的使用：
     *          - 万事皆对象，创建每一个类都是对象，即类本身就是java.lang.Class类的实例对象。但是这些对象都不需要new出来，因为java.lang.Class类的构造方法是私有的
     *          - 任何一个类都是Class类的实例对象。这个实例对象有三种表示方式：
     *              1. Class c1 = Student.class // 实际告诉我们任何一个类都有一个隐含的静态成员变量class
     *              2. Class c2 = stu.getClass() // 已知该类的对象通过getClass方法
     *              3. Class c3 = Class.forName("类的全名") // 会有一个ClassNotFoundException异常
     *       值得注意的是： 执行 c1==c2 ，会返回true； 因为不管c1还是c2都代表这student类的类类型
     *                   一个类可能是Class类的一个实例对象，可以通过类的类类型创建该类的对象实例，即通过c1或者c2创建student实例
     *                     Student stu = (Student)c1.newInstance(); // 前提是必须要有无参构造，因为该语句会调用其无参构造
     *
     *    2. 动态加载
     *          1. 编译时加载类是静态加载类，
     *                  new创建对象时静态加载类，在编译时刻就需要加载所有可能使用到的类，如果有一个用不了，那么整个文件都无法通过编译
     *
     *          2.运行时加载类是动态加载类，
     *                  Class c = Class.forName("类全名")，不仅仅表示了该类的类型，还表示了动态加载类，编译不会报错，在运行时才会加载，使用接口标准能更方便动态加载类的实现。
     *                      功能性的类尽量使用动态加载，而不用静态加载。
     *                      很多软件比如qq、360的在线升级，并不需要重新编译文件，只是动态的加载新的东西
     *
     *
     *     3.获取方法的信息
     *          1.基本的数据类型，void关键字都存在类类型
     *              Class c1 = int.class
     *              Class c2 = String.class // String类的类类型，可以理解为编译生成的那个String.class字节码文件
     *              Class c3 = doublie.class
     *              Class c4 = void.class
     *
     *          2.Class类的基本API操作
     *              Class c = obj.getClass()
     *                  c.getName() -> 类的名称
     *                  Method[] ms = c.getMethods() -> 获取所有public的函数，包括从父类继承而来的
     *                  Method[] ms = c.getDeclaredMethods() -> 获取的是该类自己声明的方法，不问访问权限
     *
     *                  ms[i].getReturnType() -> 得到方法放回值类型的类类型
     *                  ms[i].getName() -> 获取方法名称
     *                  Class[] paraTypes = ms[i].getParameterTypes() -> 获取的参数类型，得到的是参数列表的类型的类类型
     *
     *     4.获取成员变量构造函数信息
     *
     *                  - 成员变量也是对象，是java.lang.reflect.Field这个类的对象
     *                      getFeilds() -> 获取是所有public的成员变量的信息
     *                      getDeclareFeilds() -> 获取该类制剂声明的成员变量信息
     *
     *                      Fields[] fs = c.getDeclaredFields()
     *                      Class fieldType = fs[i].getType() -> fieldType.getName() 获取成员变量的类型的类类型
     *
     *                      fs.getName() -> 获取成员变量的名称
     *
     *                  - 构造函数也是对象， java.lang.Constructor中封装了构造函数的信息
     *                       getConstructor() -> 获取所有的public方法
     *                       getDeclaredConstructors() -> 得到所有的构造函数
     *
     *                       Constructors[] cs = c.getDeclaredConstructors()
     *                       Class[] paramTypes = cs[i].getParameterTypes() // 获取构造函数的参数列表，得到是参数类的类类型
     *
     *      5.方法反射的基本操作
     *              1.如何获取某个方法
     *                  方法的名称和方法参数列表才能唯一决定某个方法
     *                      Mehthod m = c.getDeclaredMethod("方法名",可变参数列表(参数类型.class))
     *              2. 方法的反射操作
     *                      m.invoked(对象，参数列表)
     *                      方法如果没有返回值，返回null，如果有返回值返回Object类型，然后在强制类型转换为原函数的返回值类型
     *
     *       6. 通过反射了解集合泛型的本质
     *
     *               ArrayList list1 = new ArrayList();
     *               ArrayList<String> list2 = new ArrayList<>();
     *
     *               Class c1 = list1.getClass();
     *               Class c2 = list2.getClass();
     *
     *               c1 == c2 -> true
     *
     *
     *              因为反射的操作都是编译之后的操作，也就是运行时操作，c1 == c2 返回true 说明编译之后集合的泛型是去泛型化的，
     *                  那么我们可以理解为，java集合汇总的泛型，是用于防止错误类型元素输入的，通过了编译阶段后，泛型就不存在了。
     *                  可以验证，我们绕过编译，用反射动态的在list2
     *                  中add一个int是可以成功的，只是这时因为list2中存储了多个不同数据类型的数据，就不能用for-each来遍历了，会抛出类型转化错误异常ClassCastException
     *
     *        ============================================================================================================================================
     *
     *        7. 补充资料
     *
     *              关于java类加载器内容的详解
     *                  1. 类的加载
     *                      当程序要使用某个类时，如果该类还未被加载到内存时，则系统就会通过加载，连接，初始化三步来实现对这个类进行初始化
     *
     *                      -加载 ：就是指将class文件读入内存中，并为之创建一个Class对象，任何类被使用时系统都会建立一个Class对象
     *
     *                      -链接 ：
     *                             验证：确保被加载类的正确
     *                             准备：负责为类的静态成员分配内存，并设置默认初始化值
     *                             解析：将类中的符号引用替换为直接引用
     *
     *                      -初始化 ：
     *                              局部变量保存在栈区：必须手动初始化
     *                              new的对象保存在堆区：虚拟机会进行默认初始化，基本数据类型初始化为0，引用类型初始化为null
     *
     *                   2.类加载的时机（只加载一次）
     *                          以下时机仅表示第一次的时候
     *                              1.创建类的实例的时候
     *                              2.访问类的静态变量的时候
     *                              3.调用类的静态方法时候
     *                              4.使用反射方式来强制创建某个类或者接口对应的java.lang.Class对象
     *                              5.初始化某个类的子类的时候
     *                              6.直接使用java.exe命令来运行某个主类
     *
     *                   3.类的加载
     *                          负责将.class文件加载到内存中，并为之生成对应的Class对象
     *                          虽然我们在开发过程中不需要关心类加载机制，但是了解这个机制能更好的理解程序的运行
     *
     *                   4.类加载器的组成
     *                          1.Bootstrap ClassLoader 根类加载器
     *                              也被称为引导类加载器，负责java核心类的加载，比如system类，在JDK中JRE的lib目录下rt.jar文件中的类
     *
     *                          2.Extension ClassLoader 扩展类加载器
     *                              负责jre的扩展目录中jar包的加载，在jdk中jre的lib目录下ext目录
     *
     *                          3.System ClassLoader 系统类加载器
     *                              负责在jvm启动时加载来自java命令的class文件，以及classpath环境变量所指定的jar包和类的路径，主要是我们开发者自己写的类
     *
     *
     *          ==============================================================================================================================
     *
     *          补充：
     *              -双亲委派机制
     *                  某个特定的列加载器在接到加载类的请求时，首先将加载任务委托给父类加载器，父类加载器又将加载任务向上委托，直到最父类加载器，
     *                      如果最父类加载器可以完成加载任务，就成功返回，如果不能就向下传递委托任务，由其子类加载器完成任务
     *
     *              - 好处： 保证java核心库的安全性(例如：用户自己写了个java.lang.String类就会因为双亲委派机制不能被加载，就不会破坏原生String类加载)
     *
     *              - 代理模式： 与双亲模式机制相反，代理模式是先自己尝试加载，如果无法加载则向上传递。tomcat就是代理模式
     *
     *
     *          ===============================================================================================================================
     *
     *          补充：
     *              类的加载机制：
     *                  1.全盘负责：当一个类加载器负责加载某个Class时，该Class所依赖的和引用的其他Class也将由该加载器负责载入，除非显式指定另外一个类加载器来载入
     *
     *                  2.双亲委派模型：（见上处）
     *
     *                  3.缓存机制 ： 所有加载过的Class都会被缓存，当程序需要使用某个Class时，类加载器先从缓存区寻找该Class，只有当缓存区不存在，系统才会去读取该Class对应的二进制数据，并将其转换成Class对象，存入缓存区
     *                              这也是为什么修改了Class后，必须重启jvm，程序修改才会生效
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     */
}
