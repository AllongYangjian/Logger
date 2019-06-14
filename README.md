# Logger
日志jar包,latest version is 1.0.4
## To get a Git project into your build
### gradle
Step1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
            maven { url 'https://www.jitpack.io' }
        }
}
```
  
Setp2:Add the dependency 
```
dependencies {
    implementation 'com.github.AllongYangjian:Logger:Tag'
}

```

### maven

```
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://www.jitpack.io</url>
    </repository>
</repositories>
	
```

```
<dependency>
    <groupId>com.github.AllongYangjian</groupId>
        <artifactId>Logger</artifactId>
    <version>Tag</version>
</dependency>

```

## how to use of this library

```
//init int in Application 
LoggerManager.getInstance().init(getApplicationContext()); 
```

```
public class Test{
    private Logger looger = LoggerFactory.getLogger(Test.class);
    
    public void testLogger(){
        logger.debug("print hello world");
    }
}
```


