package eg.edu.alexu.csd.oop.game.cs.DynamicLinkage;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import eg.edu.alexu.csd.oop.game.cs.Factory.ConcreteShape;

public class Dynamic {
	@SuppressWarnings("unchecked")
	public Class<? extends ConcreteShape> getShape(String str) {
		File l = new File(str);
		// to get classes names
		try {
			List<String> classNames = new ArrayList<String>();
			@SuppressWarnings("resource")
			ZipInputStream zip = new ZipInputStream(new FileInputStream(str));
			for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
				if (!entry.isDirectory() && entry.getName().endsWith(".class")) {
					// This ZipEntry represents a class. Now, what class does it represent?
					String className = entry.getName().replace('/', '.'); // including ".class"
					classNames.add(className.substring(0, className.length() - ".class".length()));
				}
			}

			// to create class of jar
			URL fileURL = l.toURI().toURL();
			String jarURL = "jar:" + fileURL + "!/";
			URL urls[] = { new URL(jarURL) };
			URLClassLoader ucl = new URLClassLoader(urls);
			Class<ConcreteShape> classToLoad = (Class<ConcreteShape>) Class.forName(classNames.get(0), true, ucl);
			return classToLoad;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
