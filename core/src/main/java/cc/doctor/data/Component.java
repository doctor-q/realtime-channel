package cc.doctor.data;

public interface Component {
    /**
     * a component must have a name
     */
    String name();

    /**
     * init component
     */
    void onInit();

    /**
     * component run
     */
    void run();

    /**
     * destroy component
     */
    void onDestroy();
}
