## 5.6 Spring IoC【控制反转】DI【依赖注入】
+ IOC是控制反转的缩写，它不是一种技术，它是一种设计思想，和一项重要的面向对象编程的规则
+ Spring是通过IOC容器，来管理所有Java对象的实例和初始化的，控制对象之间的依赖，我们把被IOC容器管理的Java对象称为Spring beans
+ 这和用new关键字创建Java对象没有什么不同，在传统的Java应用中，当类想要调用另一个类的属性或方法时，它通常会用new关键字
+ 创建一个对象来调用属性和方法，这意味着传统开发中，控制对象创建的时程序员
## 5.5 Spring 和 SpringBoot 之间到底有啥区别
+ Spring是什么，Spring对每个人都很熟悉了，简而言之：Spring框架为开发Java应用，提供了广泛的基础性的支持，它包含了很多不错的特性像依赖注入
+ 和开箱即用模块，比如：SpringJDBC，SpringMVC，SpringSecurity，SpringAOP，SpringTest这些模块缩短了开发的时间，提高了开发效率
+ 举个例子：早期的JavaWeb开发，我们需要写很多代码，插入记录到数据库，通过SpringJDBC的JDBCTemplate模块，我们只需要几行代码
+ 什么是SpringBoot，SpringBoot是基于Spring框架的扩展，消除了Spring应用中XML的设置，提供了更快，更有效的方式，一些SpringBoot的特性：
+ 1.创建独立的Spring应用
+ 2.内嵌了Tomcat，Jetty，Undertow容器，不再需要部署war包文件
+ 3.提供了场景简化了构建配置，自动配置Spring应用
+ 4.提供了生产指标，比如健壮性检查，外部配置
+ 5.无代码生成和不需要XML配置
## 5.4 Spring Bean的注入方式
+ 当你在一个bean中，调用另一个bean的时候，需要依赖注入
+ @Configuration:标注这个类是一个配置类
+ @ComponentScan("包")：指定哪个包，就扫描识别这个包下的注解
+ @Autowired：Bean的自动装配，可以标注在类的属性，方法，构造器上
+ @Component：将一个普通类标注为bean，将其加入容器，是一个单例
+ @Bean：定义一个Bean对象，加入到Spring容器中
+ @Order(数字)：容器加载bean的优先级，数字越小，优先级越高
+ 注入方式：
+ 通过构造方法注入，通过Set方法注入，通过属性注入，通过集合注入beans
## 5.3 Spring中Bean的生命周期是怎样的
+ 1.通过构造器创建一个bean实例
+ 2.为bean的属性设置值和引用其它bean
+ 3.将bean实例交给bean的前置处理器
+ 4.调用bean的初始化方法
+ 5.将bean实例交给bean的后置处理器
+ 6.使用bean
+ 7.当容器关闭的时候，调用bean的销毁方法
## 5.2 谈谈自己对于Spring AOP的理解
+ 1.将程序中一些交叉的业务逻辑（安全，日志，事务），封装成一个切面，然后将其注入到目标对象（具体的业务逻辑）
+ 2.AOP可以增强功能，比如在一个方法被执行之前或之后，做一些事情
## 5.1 Spring AOP 底层原理
+ 1.和AspectJ静态代理不同，Spring AOP使用动态代理，通过动态代理，AOP框架不会修改源代码，取而代之的是，它可以暂时为了内存中的方法，生成一个AOP对象
+ 它包含了目标对象的所有方法，在指定的切点做增强处理，可以调用原对象的方法
+ 2.Spring AOP的动态代理，有两个主要的方式：JDK动态代理和CGLIB动态代理，JDK动态代理通过反射接受被代理类，被代理类需要实现一个接口，JDK动态代理的
+ 核心是InvocationHandler接口和代理类，如果目标类没有实现这个接口，Spring AOP会选择使用CGLIB作为目标类的动态代理
+ 3.CGLIB(代码生成库)是一个代码生成类库，可以在运行时动态生成一个类的子类，注意CGLIB需要通过继承来实现动态代理，所以如果一个类被标注了final
+ 它就不能使用CGLIB动态代理了
+ 简单版：
+ 1.如果这个对象被代理了，就要实现一个接口，Spring AOP使用JDK Proxy创建了一个代理对象
+ 2.如果这个对象没有实现这个接口，你就不能使用JDK Proxy代理它，Spring AOP使用CGLIB生成一个类的子类，作为代理对象
## SpringBoot和SpringCloud的区别
+ SpringBoot是快速开发的Spring框架，SpringCloud是完整的微服务框架
