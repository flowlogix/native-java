
package me.lprimak.mp.custom;






/**
 * Main entry point of the application.
 * <p>
 * Note that this class is required when using modules as the module main class must be in a package that is either exported
 * or opened by the module, see {@link java.lang.module.ModuleDescriptor#read(java.io.InputStream, java.util.function.Supplier)}.
 * <p>
 * This class provides a proper module main class and calls the {@link io.helidon.Main#main(String[]) built-in main class}.
 */
public class Main {


    /**
    * Cannot be instantiated.
    */
    private Main() {
    }


    
    /**
     * Main method. Starts CDI (and the application).
     *
     * @param args ignored
     */
    public static void main(String[] args) {
                io.helidon.Main.main(args);
    }

}