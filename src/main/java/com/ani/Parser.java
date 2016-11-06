package com.ani;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Parser {
	
    private static final char PKG_SEPARATOR = '.';

    private static final String CLASS_FILE_SUFFIX = ".class";

    private static final String BAD_PACKAGE_ERROR = "Unable to get resources from path '%s'. Are you sure the package '%s' exists?";

	
	public static void main(String[] args) {
		ArrayList<String> testlist = new ArrayList<String>();
		testlist.add("1");
		testlist.add("2");
		
		ArrayList<String> tagList = new ArrayList<String>();
		
		List<Class<?>> classes = find("com.ani.user");
		
		for (Class<?> testClass : classes) {
			System.out.println(testClass.getName());
			
			Annotation[] annotations = testClass.getAnnotations();
			for (Annotation annotation: annotations) {
				if(annotation instanceof TestDetails){
					TestDetails td = (TestDetails) annotation;
					if (testlist.contains(td.testUID())) {
						Collections.addAll(tagList, td.tags());
					}
				}
			}
		}
		
		for (String ss: tagList) {
			System.out.println(ss);
		}
		
	}
	
    public static List<Class<?>> find(String scannedPackage) {
        String scannedPath = scannedPackage.replace(PKG_SEPARATOR, '/');
        URL scannedUrl = Thread.currentThread().getContextClassLoader().getResource(scannedPath);
        if (scannedUrl == null) {
        	throw new IllegalArgumentException(String.format(BAD_PACKAGE_ERROR, scannedPath, scannedPackage));
        }
        File scannedDir = new File(scannedUrl.getFile());
        List<Class<?>> classes = new ArrayList<Class<?>>();
        for (File file : scannedDir.listFiles()) {
            classes.addAll(find(file, scannedPackage));
        }
        return classes;
    }

    private static List<Class<?>> find(File file, String scannedPackage) {
        List<Class<?>> classes = new ArrayList<Class<?>>();
        String resource = scannedPackage + PKG_SEPARATOR + file.getName();
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                classes.addAll(find(child, resource));
            }
        } else if (resource.endsWith(CLASS_FILE_SUFFIX)) {
            int endIndex = resource.length() - CLASS_FILE_SUFFIX.length();
            String className = resource.substring(0, endIndex);
            try {
                classes.add(Class.forName(className));
            } catch (ClassNotFoundException ignore) {
            }
        }
        return classes;
    }
    
    public static List<List<String>> getBuildParams(String model, String mac, String group) {
    	List<List<String>> buildList = new ArrayList<>();
    	
    	ArrayList<String> build1 = new ArrayList<String>();
    	ArrayList<String> build2 = new ArrayList<String>();
    	
    	build1.add("try");
    	build1.add("try1");
    	
    	build2.add("fry");
    	build2.add("fry1");
    	
    	return buildList;
    }
    
}
