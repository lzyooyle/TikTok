## 5.1 Spring AOP 底层原理
+ 1.和AspectJ静态代理不同，Spring AOP使用动态代理，通过动态代理，AOP框架不会修改源代码，取而代之的是，它可以暂时为了内存中的方法，生成一个AOP对象
+ 它包含了目标对象的所有方法，在指定的切点做增强处理，可以调用原对象的方法
+ 2.Spring AOP的动态代理，有两个主要的方式：JDK动态代理和CGLIB动态代理，JDK动态代理通过反射收到被代理类，被代理类需要实现一个接口，JDK动态代理的
+ 核心是InvocationHandler接口和代理类，如果目标类没有实现这个接口，Spring AOP会选择使用CGLIB作为目标类的动态代理
+ 3.CGLIB(代码生成库)是一个代码生成类库，可以在运行时动态生成一个类的子类，注意CGLIB需要通过继承来实现动态代理，所以如果一个类被标注了final
+ 它就不能使用CGLIB动态代理了
## SpringBoot和SpringCloud的区别
+ SpringBoot是快速开发的Spring框架，SpringCloud是完整的微服务框架
