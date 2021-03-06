## 增加依赖:

```
<dependency>
	<groupId>com.shiwen</groupId>
	<artifactId>spring-boot-starter-shiro-redis</artifactId>
	<version>${version}</version>
</dependency>
```

## 前缀：shiro.filter.chain 过滤器链：注意map后面的 | 

```
shiro:
  filter:
    chain:
      map: |
	/login=anon
        /logout=restLogout
        /**=restAuthc
       	
redis pool 配置：shiro.redis.config 例如
shiro:
  redis:
    host: 192.168.10.168
```


## 可配置属性包括以下内容：   
   
	host = "127.0.0.1"
	int port = 6379;         
	long expire = 1800L;     
	long timeout;            
	String password;         
	int database = 0; 


## 设置cookie相关 shiro.cookie.
包括如下内容：

	private String domain;
	private String path = "/";
	private String sessionIdCookieName;
	private String rememberMeCookieName;


## 配置示例

```
shiro:
  filter:
    chain:
      map:
        anon: /login
  cookie:
    path: /
    session-id-cookie-name: shiro-auth
  redis:
    database: 1
    host: 192.168.10.168
    port: 6379
  filters:
    filter-spring-bean-names:
    - restAuthc
  realms:
    realm-spring-bean-names:
    - userRealm
    - emailRealm

```

# spring-boot-starter-shiro-redis
