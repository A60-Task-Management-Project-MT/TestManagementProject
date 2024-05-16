import com.company.oop.test.menagement.core.TaskManagementEngineImpl;

public class Startup {
    public static void main(String[] args) {
        TaskManagementEngineImpl engine = new TaskManagementEngineImpl();
        engine.start();
    }
}