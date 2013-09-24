package pizzana.lib.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import cpw.mods.fml.common.registry.LanguageRegistry;

/**
 * 
 * This is used for localizations. Info found in Localization.txt file.
 * 
 * @author PizzAna
 * 
 */
public class LangUtil {

    public void loadLang(String path, boolean isXML, String file,
            String listFile) {

        try {

            InputStream is = getClass().getResourceAsStream(path + listFile);
            BufferedReader in = new BufferedReader(new InputStreamReader(is));

            while (in.ready()) {

                String lang = in.readLine();
                LanguageRegistry.instance().loadLocalization(
                        path + lang + file, lang, isXML);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
