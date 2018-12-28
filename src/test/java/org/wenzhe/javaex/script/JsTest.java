package org.wenzhe.javaex.script;

import org.junit.Test;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * Created by wenzhe on 2016/11/16.
 */
public class JsTest {

  @Test
  public void testJs() throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript");
    if (engine == null) {
      throw new RuntimeException("Cannot find JavaScript engine");
    }
    //engine.eval("println ('Hello!'); ");
    engine.put("name", "wz20161116074801");
    engine.put("out", "newPath");
    engine.eval("var now = new Date().toString();\n" +
            "var pattern = /$\\w+?([0-9]{14}/;\n" +
            "var out = pattern.test(name); ");
    System.out.println(engine.get("out"));
  }

  @Test
  public void testGroovy() throws ScriptException {
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("groovy");
    if (engine == null) {
      throw new RuntimeException("Cannot find Groovy engine");
    }
    //engine.eval("println ('Hello!'); ");
    engine.put("name", "wz20161116074801");
    engine.put("out", "newPath");
    engine.eval("out = \"$name new Date().toString()\"");
    System.out.println(engine.get("out"));
  }
}
