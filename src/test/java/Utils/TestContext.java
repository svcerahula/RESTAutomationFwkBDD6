package Utils;

import java.util.HashMap;

public class TestContext {
    public static ThreadLocal<HashMap<Object,Object>> testData;

    public  TestContext() {
        testData = ThreadLocal.withInitial(HashMap::new);
    }

    public void setContext(Object key, Object value) {
        testData.get().put(key,value);
    }

    public Object getContextValue(Object value) {
        return testData.get().get(value);
    }
}
