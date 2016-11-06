package com.test.ng

import com.ani.TestDetails
import com.ani.user.User1;;

class BuildDistributor {
	
	static void main(def args) {
		println("Hello Groovy")
		
		println findAllPropertiesForClas()
		
//		println findAllPropertiesForClassWithAnotation( a, TestDetails )
	}
	
	def findAllPropertiesForClassWithAnotation( obj, annotClass ) {
		obj.properties.findAll { prop ->
		  obj.getClass().declaredFields.find {
			it.name == prop.key && annotClass in it.declaredAnnotations*.annotationType()
		  }
		}
	  }
	
	def findAllPropertiesForClas() {
			println("whattt")
	  }

	def findAllPropertiesForClass(obj) {
		User1.class.annotations.collect {
			println(it.getName())
		}
	  }
}
