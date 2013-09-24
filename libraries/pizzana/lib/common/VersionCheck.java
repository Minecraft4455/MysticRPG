package pizzana.lib.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;
import cpw.mods.fml.common.versioning.ComparableVersion;
import cpw.mods.fml.relauncher.FMLInjectionData;

/**
 * 
 * The version checker. To use this version check, you need to call
 * updateCheck(String mod, String version) method on your mods
 * pre-initialization. Example bellow:
 * 
 * VersionCheck.updateCheck("MyModName",
 * MyMod.class.getAnnotation(Mod.class).version());
 * 
 * @author PizzAna
 * @credits Some of the code by ChickenBones
 * 
 */
public class VersionCheck {

    private static ArrayList<String> updates = new ArrayList<String>();

    static class ThreadUpdateCheck extends Thread {
        private final URL url;
        private final String mod;
        private final ComparableVersion version;

        public ThreadUpdateCheck(URL url, String mod, ComparableVersion version) {
            this.url = url;
            this.mod = mod;
            this.version = version;

            setName("Remote Version Checker");
        }

        @Override
        public void run() {
            try {
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(5000);
                conn.setReadTimeout(5000);
                BufferedReader read = new BufferedReader(new InputStreamReader(
                        conn.getInputStream()));
                String ret = read.readLine();
                read.close();
                if (!ret.startsWith("Ret: ")) {
                    System.err.println("Failed to check update for " + mod
                            + " returned: " + ret);
                    return;
                }
                ComparableVersion newversion = new ComparableVersion(
                        ret.substring(5));
                if (newversion.compareTo(version) > 0) {
                    addUpdateMessage("Version " + newversion + " of " + mod
                            + " is available");
                }
            } catch (SocketTimeoutException ste) {
            } catch (UnknownHostException uhe) {
            } catch (IOException iox) {
                iox.printStackTrace();
            }
        }

    }

    public static void tick() {
        Minecraft mc = Minecraft.getMinecraft();
        if (!mc.inGameHasFocus)
            return;

        synchronized (updates) {
            for (String s : updates) {
                mc.thePlayer.addChatMessage(s);
            }
            updates.clear();
        }
    }

    public static void addUpdateMessage(String s) {
        synchronized (updates) {
            updates.add(s);
        }
    }

    public static String mcVersion() {
        return (String) FMLInjectionData.data()[4];
    }

    public static void updateCheck(String mod, String version, String remote) {
        try {
            new ThreadUpdateCheck(new URL(remote), mod, new ComparableVersion(
                    version)).start();
        } catch (MalformedURLException e) {
        }
    }

    /*
     * public static void updateCheck(String mod) { updateCheck(mod,
     * Loader.instance().getIndexedModList().get(mod).getVersion(), null); }
     */

}
