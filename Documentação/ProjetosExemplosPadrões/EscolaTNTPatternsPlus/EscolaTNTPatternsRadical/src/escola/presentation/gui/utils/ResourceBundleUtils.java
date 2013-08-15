package escola.presentation.gui.utils;

import java.util.ResourceBundle;


public class ResourceBundleUtils {
    
    private static ResourceBundleUtils instance;
    private ResourceBundle bundle;
    
    private ResourceBundleUtils(){
        bundle = ResourceBundle.getBundle("escola.presentation.gui.guilabels");
        
    }
    
    public static ResourceBundleUtils getInstance(){
        
        if(instance == null){
            instance = new ResourceBundleUtils();
        }
        return instance;
    }
    
    
    public String getMessage(String key){
        return bundle.getString(key);
    }
    

}
