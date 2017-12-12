/* Homework 10
 * @author NinnYu Chin
 * Instructions:	Write a program that can handle plugins.
				Your plugin interface is:

				public abstract class myplugin{
					public void whoSaysHello(){}
					public void whoSaysBye(){}
				}

				Create two separate plugins from that abstract class. Place them in a
				directory called plugin.

				Your main program will search through the plugin directory looking for
				plugin names, it'll then give the user an option whether they want to
				execute the first plugin or the second plugin (print the plugin names on
				the screen) And depending on the choice, it'll execute the two functions
				in the chosen plugin.
 */

import java.io.*;
import java.util.*;
import java.lang.reflect.*;
import java.net.*;

public class PluginMain {
	public static void main (String[] args) throws Throwable {
		Scanner sc = new Scanner(System.in);
		File f = new File("/Users/potatopaloozac/Desktop/plugin");
		File[] flist = f.listFiles();
		
		System.out.println("List of available plugins: ");
		for (int i=0; i<flist.length; i++) {
			if ((!flist[i].getName().contains("my")) && (!flist[i].getName().contains("java")) )
				System.out.println(flist[i].getName().replace(".class", ""));
		}
		
		System.out.println("Which do you want to run?");
		String choice = sc.next();
		
		URLClassLoader myurlclass = new URLClassLoader(new URL[] { f.toURI().toURL() } );
		Class<?> myclass = myurlclass.loadClass(choice);
		Object obj = myclass.newInstance();
		Method m1 = myclass.getMethod("whoSaysHello"), m2 = myclass.getMethod("whoSaysBye");
		m1.invoke(obj);
		m2.invoke(obj);
		
		sc.close();
		myurlclass.close();
	}
}