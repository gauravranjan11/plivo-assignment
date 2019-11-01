# springboot-docker-redis-ratelimit

Find more details about how to set up, develop, and operate this service:

### Development Set up 
* This is a docker based project. To run this you only need to have docker running on you system.
Please follow [Docker Setup](https://docs.docker.com/docker-for-mac/install/) to do that. 
* Once docker is up and running on you mac you can simply run below command to start the application.
```docker-compose up```
* It would pull docker images for mysql and redis and would start the app on port 8080.
You should see something like below. 
```
$ docker-compose up 
Starting plivo-mysql ... done
Starting plivo-redis ... done
Starting plivoassignment_app_1 ... done
Attaching to plivo-redis, plivo-mysql, plivoassignment_app_1
plivo-redis    | 1:C 07 Jul 2019 19:30:57.157 # oO0OoO0OoO0Oo Redis is starting oO0OoO0OoO0Oo
plivo-redis    | 1:C 07 Jul 2019 19:30:57.157 # Redis version=5.0.5, bits=64, commit=00000000, modified=0, pid=1, just started
plivo-redis    | 1:C 07 Jul 2019 19:30:57.157 # Configuration loaded
plivo-redis    | 1:M 07 Jul 2019 19:30:57.158 * Running mode=standalone, port=6379.
plivo-redis    | 1:M 07 Jul 2019 19:30:57.158 # WARNING: The TCP backlog setting of 511 cannot be enforced because /proc/sys/net/core/somaxconn is set to the lower value of 128.
plivo-redis    | 1:M 07 Jul 2019 19:30:57.158 # Server initialized
plivo-redis    | 1:M 07 Jul 2019 19:30:57.158 # WARNING you have Transparent Huge Pages (THP) support enabled in your kernel. This will create latency and memory usage issues with Redis. To fix this issue run the command 'echo never > /sys/kernel/mm/transparent_hugepage/enabled' as root, and add it to your /etc/rc.local in order to retain the setting after a reboot. Redis must be restarted after THP is disabled.
plivo-redis    | 1:M 07 Jul 2019 19:30:57.158 * Ready to accept connections
plivo-mysql    | [Entrypoint] MySQL Docker Image 5.7.26-1.1.11
plivo-mysql    | [Entrypoint] Starting MySQL 5.7.26-1.1.11
app_1          | [INFO] Scanning for projects...
app_1          | [INFO] 
app_1          | [INFO] ------------------------< com.plivo:assignment >------------------------
app_1          | [INFO] Building assignment 1.0-SNAPSHOT
app_1          | [INFO] --------------------------------[ jar ]---------------------------------
app_1          | [INFO] 
app_1          | [INFO] --- maven-clean-plugin:3.1.0:clean (default-clean) @ assignment ---
app_1          | [INFO] Deleting /app/target
app_1          | [INFO] 
app_1          | [INFO] >>> spring-boot-maven-plugin:2.1.1.RELEASE:run (default-cli) > test-compile @ assignment >>>
app_1          | [INFO] 
app_1          | [INFO] --- maven-resources-plugin:3.1.0:resources (default-resources) @ assignment ---
app_1          | [INFO] Using 'UTF-8' encoding to copy filtered resources.
app_1          | [INFO] Copying 1 resource
app_1          | [INFO] Copying 2 resources
app_1          | [INFO] 
app_1          | [INFO] --- maven-compiler-plugin:3.8.0:compile (default-compile) @ assignment ---
app_1          | [INFO] Changes detected - recompiling the module!
app_1          | [INFO] Compiling 18 source files to /app/target/classes
app_1          | [INFO] 
app_1          | [INFO] --- maven-resources-plugin:3.1.0:testResources (default-testResources) @ assignment ---
app_1          | [INFO] Using 'UTF-8' encoding to copy filtered resources.
app_1          | [INFO] skip non existing resourceDirectory /app/src/test/resources
app_1          | [INFO] 
app_1          | [INFO] --- maven-compiler-plugin:3.8.0:testCompile (default-testCompile) @ assignment ---
app_1          | [INFO] Nothing to compile - all classes are up to date
app_1          | [INFO] 
app_1          | [INFO] <<< spring-boot-maven-plugin:2.1.1.RELEASE:run (default-cli) < test-compile @ assignment <<<
app_1          | [INFO] 
app_1          | [INFO] 
app_1          | [INFO] --- spring-boot-maven-plugin:2.1.1.RELEASE:run (default-cli) @ assignment ---
app_1          | 
app_1          |   .   ____          _            __ _ _
app_1          |  /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
app_1          | ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
app_1          |  \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
app_1          |   '  |____| .__|_| |_|_| |_\__, | / / / /
app_1          |  =========|_|==============|___/=/_/_/_/
app_1          |  :: Spring Boot ::        (v2.1.1.RELEASE)
app_1          | 
app_1          | 2019-07-07 19:31:29.751  INFO 1 --- [           main] com.plivo.assignment.Application         : Starting Application on 0f4c32009788 with PID 1 (/app/target/classes started by root in /app)
app_1          | 2019-07-07 19:31:29.766  INFO 1 --- [           main] com.plivo.assignment.Application         : No active profile set, falling back to default profiles: default
app_1          | 2019-07-07 19:31:32.421  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data repositories in DEFAULT mode.
app_1          | 2019-07-07 19:31:33.308  INFO 1 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 841ms. Found 2 repository interfaces.
app_1          | 2019-07-07 19:31:34.568  INFO 1 --- [           main] trationDelegate$BeanPostProcessorChecker : Bean 'org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration' of type [org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration$$EnhancerBySpringCGLIB$$f4144126] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)
app_1          | 2019-07-07 19:31:35.758  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
app_1          | 2019-07-07 19:31:35.822  INFO 1 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
app_1          | 2019-07-07 19:31:35.822  INFO 1 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet Engine: Apache Tomcat/9.0.13
app_1          | 2019-07-07 19:31:35.853  INFO 1 --- [           main] o.a.catalina.core.AprLifecycleListener   : The APR based Apache Tomcat Native library which allows optimal performance in production environments was not found on the java.library.path: [/usr/java/packages/lib/amd64:/usr/lib/x86_64-linux-gnu/jni:/lib/x86_64-linux-gnu:/usr/lib/x86_64-linux-gnu:/usr/lib/jni:/lib:/usr/lib]
app_1          | 2019-07-07 19:31:36.311  INFO 1 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
app_1          | 2019-07-07 19:31:36.312  INFO 1 --- [           main] o.s.web.context.ContextLoader            : Root WebApplicationContext: initialization completed in 6282 ms
app_1          | 2019-07-07 19:31:36.838  INFO 1 --- [           main] o.f.c.internal.license.VersionPrinter    : Flyway Community Edition 5.2.3 by Boxfuse
app_1          | 2019-07-07 19:31:36.870  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
app_1          | 2019-07-07 19:31:37.661  INFO 1 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
app_1          | 2019-07-07 19:31:37.674  INFO 1 --- [           main] o.f.c.internal.database.DatabaseFactory  : Database: jdbc:mysql://plivo-mysql:3306/sms-service (MySQL 5.7)
app_1          | 2019-07-07 19:31:37.978  INFO 1 --- [           main] o.f.core.internal.command.DbValidate     : Successfully validated 2 migrations (execution time 00:00.094s)
app_1          | 2019-07-07 19:31:37.997  INFO 1 --- [           main] o.f.core.internal.command.DbMigrate      : Current version of schema `sms-service`: 1.1
app_1          | 2019-07-07 19:31:38.000  INFO 1 --- [           main] o.f.core.internal.command.DbMigrate      : Schema `sms-service` is up to date. No migration necessary.
app_1          | 2019-07-07 19:31:38.442  INFO 1 --- [           main] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [
app_1          | 	name: default
app_1          | 	...]
app_1          | 2019-07-07 19:31:38.744  INFO 1 --- [           main] org.hibernate.Version                    : HHH000412: Hibernate Core {5.3.7.Final}
app_1          | 2019-07-07 19:31:38.749  INFO 1 --- [           main] org.hibernate.cfg.Environment            : HHH000206: hibernate.properties not found
app_1          | 2019-07-07 19:31:39.328  INFO 1 --- [           main] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.0.4.Final}
app_1          | 2019-07-07 19:31:39.989  INFO 1 --- [           main] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.MySQL57Dialect
app_1          | 2019-07-07 19:31:42.112  INFO 1 --- [           main] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
app_1          | 2019-07-07 19:31:43.196  INFO 1 --- [           main] io.lettuce.core.EpollProvider            : Starting without optional epoll library
app_1          | 2019-07-07 19:31:43.200  INFO 1 --- [           main] io.lettuce.core.KqueueProvider           : Starting without optional kqueue library
app_1          | 2019-07-07 19:31:44.646  INFO 1 --- [           main] o.h.h.i.QueryTranslatorFactoryInitiator  : HHH000397: Using ASTQueryTranslatorFactory
app_1          | 2019-07-07 19:31:45.555  INFO 1 --- [           main] o.s.s.concurrent.ThreadPoolTaskExecutor  : Initializing ExecutorService 'applicationTaskExecutor'
app_1          | 2019-07-07 19:31:45.713  WARN 1 --- [           main] aWebConfiguration$JpaWebMvcConfiguration : spring.jpa.open-in-view is enabled by default. Therefore, database queries may be performed during view rendering. Explicitly configure spring.jpa.open-in-view to disable this warning
app_1          | 2019-07-07 19:31:46.325  WARN 1 --- [           main] o.s.b.a.f.FreeMarkerAutoConfiguration    : Cannot find template location(s): [classpath:/templates/] (please add some templates, check your FreeMarker configuration, or set spring.freemarker.checkTemplateLocation=false)
app_1          | 2019-07-07 19:31:47.081  INFO 1 --- [           main] o.s.s.web.DefaultSecurityFilterChain     : Creating filter chain: any request, [org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter@62b1c08c, org.springframework.security.web.context.SecurityContextPersistenceFilter@a4f6565, org.springframework.security.web.header.HeaderWriterFilter@5eb5c35b, org.springframework.security.web.authentication.logout.LogoutFilter@39c578f2, org.springframework.security.web.authentication.www.BasicAuthenticationFilter@3b41a211, org.springframework.security.web.savedrequest.RequestCacheAwareFilter@78fbb8cb, org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter@689eea14, org.springframework.security.web.authentication.AnonymousAuthenticationFilter@590df9e3, org.springframework.security.web.session.SessionManagementFilter@662c5038, org.springframework.security.web.access.ExceptionTranslationFilter@6f1cb23b, org.springframework.security.web.access.intercept.FilterSecurityInterceptor@11619e8c]
app_1          | 2019-07-07 19:31:47.316  INFO 1 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
app_1          | 2019-07-07 19:31:47.326  INFO 1 --- [           main] com.plivo.assignment.Application         : Started Application in 19.017 seconds (JVM running for 48.474)

```

* Once its up you can test with following urls
##### Invalid From Request
```
curl -X POST \
  http://localhost:8080/outbound/sms \
  -H 'authorization: Basic cGxpdm8xOjIwUzBLUE5PSU0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"to":"4924195509198",
	"from":"3253280315f",
	"text":"Hello"
}'

```
##### Response
```
{
    "errors": [
        {
            "message": "",
            "error": "from is invalid"
        }
    ]
}
```
##### ‘from’ parameter is not present in the phone_number table for this specific account you used for the basic authentication
```
curl -X POST \
  http://localhost:8080/outbound/sms \
  -H 'authorization: Basic cGxpdm8xOjIwUzBLUE5PSU0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"to":"4924195509198",
	"from":"32532803156",
	"text":"Hello"
}'

```
##### Response
```
{
    "message": "",
    "error": "from parameter not found"
}
```
##### Rate limit

```
curl -X POST \
  http://localhost:8080/outbound/sms \
  -H 'authorization: Basic cGxpdm8xOjIwUzBLUE5PSU0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"to":"4924195509198",
	"from":"3253280315",
	"text":"Hello "
}'
```
##### Response
```
{
       "message": "",
       "error": "limit reached for from 3253280315"
}

```
##### STOP Request

```
curl -X POST \
  http://localhost:8080/outbound/sms \
  -H 'authorization: Basic cGxpdm8xOjIwUzBLUE5PSU0=' \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
	"to":"4924195509198",
	"from":"3253280315",
	"text":"STOP"
}'

```
##### Response
```
{
    "message": "",
    "error": "sms from 3253280315 to 4924195509198 blocked by STOP request"
}
```
