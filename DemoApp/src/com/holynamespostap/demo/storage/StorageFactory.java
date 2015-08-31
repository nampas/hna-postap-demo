/**
 * 
 */
package com.holynamespostap.demo.storage;

/**
 * @author alcheng
 *
 */
public class StorageFactory {

	public static String CLOUDSTORAGE = "CLOUD";
	public static String MEMORYSTORAGE = "MEMORY";
	
	private static StorageInterface storageInstance = null;
	
	public static void initializeStorage(String storageType)
	{
		if(storageType.equalsIgnoreCase(CLOUDSTORAGE)){
			storageInstance = new CloudStorage();
		}
		else if(storageType.equalsIgnoreCase(MEMORYSTORAGE)){
			storageInstance = new MemoryStorage();
		}
	}
	
	public static StorageInterface GetInstance(){
		return storageInstance;
	}
}
