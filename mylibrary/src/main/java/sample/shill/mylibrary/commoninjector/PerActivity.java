package sample.shill.mylibrary.commoninjector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by we on 2016/12/5.
 */
/*
@Scope //注明是Scope  通过@Scope定义的一个新的注解。
@Documented  //标记在文档
@Retention(RUNTIME)  // 运行时级别
public @interface Singleton {}
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
