## 操作日志使用手册
## 1. 介绍
paas-service项目的操作日志功能基于Spring Aop实现，在**OperationLogAop**类中解析所有标注了`@OperationLog`的接口方法。
**OperationLogAop**会获取接口的请求参数和返回值放入context上下文中，在接口方法运行的过程中，开发人员可以使用**OperationLogContextUtil**.setLogData()方法向context中注入参数，以供后续解析。
在接口方法执行完成之后，**OperationLogAop**会使用Spel解析器，根据`operation_log_model`表中的操作日志模板，解析上下文中的存放的所有数据，并生成操作日志内容，然后异步插入数据库。
## 2.	使用
### 2.1使用`@EnableOperationLog`开启操作日志功能
在任意的Spring配置类或者SpringBoot的Application启动类上使用`@EnableOperationLog`来开启操作日志功能，否则即使使用了`@OperationLog`也不会生效，如下：
```java
@EnableOperationLog
@Configuration
public class OperationLogConfig {
}
```
或者
```java
@EnableOperationLog
@SpringBootApplication
public class OperationLogApplication {
    public static void main(String[] args) {
        SpringApplication.run(OperationLogApplication.class, args);
    }
}
```
### 2.2	使用`@OperationLog`标注接口方法
在任意Spring 容器管理的类中都可以使用，比如controller或者service组件，下面以Controller举例子：

```java
@OperationLog("login")
@PostMapping("/login/{userName}")
public UserDto login(@PathVariable("userName") String userName, @RequestParam("password") String password, @RequestBody UserDto userDto) {
    return new UserDto(1, "ice", 28, System.currentTimeMillis(), "广东佛山", "BOY", "NORMAL");
}
```

### 2.3 在`operation_log_model`表中配置操作日志模板内容
在**2.2**的例子中，`@OperationLog` 注解的`value`值对应`operation_log_model`表中的`model_key`字段，Aop根据`model_key`读取出来的模板内容是`content`字段，由开发人员自己配置，比如此例子中key为login的模板内容可以设置为：
> '用户' + #p0 + '在' + T(System).currentTimeMillis() + '登录, id是' + #p2?.id + ', 性别是' + #ret?.sex

这段内容是一个完整的SPEL表达式，`#p0`、`#p1`、`#p2`代表着接口的第几个参数，`#ret`代表接口的返回值，所以`#p0`的值为参数列表中的`userName`的值，`#p1`的值为`password`的值，`#p2.id`的值为`userDto`参数对象的`id`属性的值，`#ret.sex`的值为此接口方法的返回值`UserDto`对象的`sex`属性值。

> ***ps:*** 
> 1. 在`#p2.id`和`#ret.sex`中间加一个问号，写成`#p2?.id`和`#ret?.sex`，可以防止`#p2`和`#ret`为`null`时，解析SPEL抛出空指针异常*
> 2. `T(System).currentTimeMillis()`这种用法是SPEL支持的调用指定类的指定方法的语法，其实SPEL支持的特性还有很多，可以参考[SPEL官方文档](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/expressions.html) 和 [SPEL详细介绍](https://blog.csdn.net/zhoudaxia/article/details/38174169)，基于SPEL可以做到不修改一行代码，也能对操作日志的内容作任意拓展，方便以后需求变更

最终，此示例的运行结果为：

```java
用户iceAAA在1580976319536登录, id是2, 性别是BOY
```

### 2.4 使用`OperationLogContextUtil.setLogData()`动态注入数据
操作日志模板中的所需要的数据除了来自接口请求参数和返回值之外，也可能来自于接口方法的执行过程中产生的数据，Aop会使用`OperationLogContextUtil`将context对象在接口方法执行之前放入当前线程的上下文中。所以，开发人员可以在接口方法的执行过程中使用`OperationLogContextUtil.setLogData()`将所需的数据放进context对象中，在接口方法执行完毕之后，Aop中的Spel解析器会从context取出所有数据，结合模板内容中的spel表达式进行解析，得出操作日志内容，异步插入数据库。以下是完整示例：

```java
@OperationLog("login")
    @PostMapping("/login/{userName}")
    public UserDto login(@PathVariable("userName") String userName, @RequestParam("password") String password,
                         @RequestBody UserDto userDto) {
        OperationLogContextUtil.setLogData("login", new JSONObject().fluentPut("runtime", "qqq"));
        return new UserDto(1, "ice", 28, System.currentTimeMillis(), "广东佛山", "BOY", "NORMAL");
    }
```
上述示例中`OperationLogContextUtil.setLogData("login", new JSONObject().fluentPut("runtime", "qqq"));`在接口运行时向context对象中放入了一个名为runtime的属性，值为qqq，此时，开发人员可以将`operation_log_model`表的content修改为：

>'用户' + #p0 + '在' + T(System).currentTimeMillis() + '登录, id是' + #p2?.id + ', 性别是' + #ret?.sex + '，此外在运行时产生了数据：' + #runtime

> ***ps:***
> `setLogData()`方法导致开发人员可以将任意命名的属性设置到`context`中，为了避免冲突，建议不要使用已有的请求参数和返回值的属性名，比如`p0、p1、p2……pn、ret`等

最终，此示例的运行结果为

```java
用户iceAAA在1580976319536登录, id是2, 性别是BOY，此外在运行时产生了数据：qqq
```

### 参考文章
[SPEL官方文档](https://docs.spring.io/spring/docs/4.2.x/spring-framework-reference/html/expressions.html) 
[SPEL详细介绍](https://blog.csdn.net/zhoudaxia/article/details/38174169)