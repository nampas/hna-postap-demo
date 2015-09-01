/**
 *
 */
package com.holynamespostap.demo.storage;

/**
 * @author alcheng
 *
 */
public class StorageFactory {

	public static final String CLOUDSTORAGE = "CLOUD";
	public static final String MEMORYSTORAGE = "MEMORY";

	private static StorageInterface storageInstance = null;

	public static void initializeStorage(String storageType) {
		if(storageType.equalsIgnoreCase(CLOUDSTORAGE)){
			storageInstance = new CloudStorage();
		}
		else if(storageType.equalsIgnoreCase(MEMORYSTORAGE)){
			storageInstance = new MemoryStorage();
		}
	}

	public static StorageInterface getInstance() {
		return storageInstance;
	}
}
