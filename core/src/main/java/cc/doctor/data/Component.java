package cc.doctor.data;

public interface Component extends Named {
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
