## 案例说明
这是一个单体应用的任务管理应用实例
服务启动后，可以通过 http://{ip}:8080/index.html 访问任务管理页面

## 系统文件介绍
- .workbench
  - 不可删除，云开发平台应用部署配置文件
  - fcRouteDefault，「路由/函数入口」配置入口，当应用采用FC计算服务时需要用到。
  - serviceConfig，还没有开放到「部署配置」管理界面上，一些特殊小众的部署配置。

- Dockerfile
  - 镜像构建文件，在不了解里面的机制之前不要去修改它，能覆盖绝大多数场景。

- prepare.sh
  - 镜像构建准备脚本，一些镜像构建前一些参数需要准备。当前包含2部分：应用配置准备 和 容器启动命名准备。

## 基础脚手架简介
-  1、该工程是一个SpringBoot工程；
-  2、pom.xml说明，如下配置不要修改，是云开发平台的约束:
```
   <properties>
        <applicationName>${project.artifactId}</applicationName>
        <spring-boot.version>2.2.6.RELEASE</spring-boot.version>
   </properties>
   ...
   <build>
      <finalName>${applicationName}</finalName>
      <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <version>${spring-boot.version}</version>
            <configuration>
                <!--需要替换您的springboot启动类-->
                <mainClass>com.alibaba.sca.temp.web.Application</mainClass>
                <layout>ZIP</layout>
            </configuration>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
      </plugins>
      ...
    </build>
```
   最好再加上java编译版本，1.8：（可选）
```
   <build>
      <plugins>
           <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.1</version>
                <configuration>
                    <source>1.8</source>
                    <target>1.8</target>
                    <encoding>UTF-8</encoding>
                </configuration>
            </plugin>
            ...
        </plugins>
    </build>
```

- 3、在CloudIDE启动：在cloudide的右下角Termial中的命令行中，输入启动命名：mvn spring-boot:run ,验证是否能在IDE容器中启动成功，启动成功后在IDE左下角有一个“预览”功能，可以把云端IDE启动的服务在本地浏览器中访问到，则说明CloudIDE配置是OK的。


   
    