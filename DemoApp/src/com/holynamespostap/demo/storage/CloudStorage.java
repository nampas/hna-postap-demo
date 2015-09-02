/**
 *
 */
package com.holynamespostap.demo.storage;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.holynamespostap.demo.dataModel.CollegeApplicationCategoryModel;
import com.holynamespostap.demo.dataModel.CollegeApplicationModel;
import com.microsoft.azure.documentdb.ConnectionPolicy;
import com.microsoft.azure.documentdb.ConsistencyLevel;
import com.microsoft.azure.documentdb.Database;
import com.microsoft.azure.documentdb.Document;
import com.microsoft.azure.documentdb.DocumentClient;
import com.microsoft.azure.documentdb.DocumentClientException;
import com.microsoft.azure.documentdb.DocumentCollection;
import com.microsoft.azure.documentdb.RequestOptions;

/**
 * @author alcheng
 *
 */
public class CloudStorage implements StorageInterface {

	private static final String HOST = "https://hnapostap20152016.documents.azure.com:443/";
	private static final String MASTER_KEY = "d9Ps6ydkWHwd2X4hSX19VPOKssiNkyRn+1xxxOXXPVlDv4+1QXrCxrlqkgXUp6b07+p62Lz2QK0bUGk78xTSeA==";;
	private static final String DATABASE_ID = "collegeapplicationdemodb";
	private static final String COLLECTION_ID = "applications";
	private static final String APPLICATION_ENTITY_NAME = "CollegeApplication";

	// Cache for documentClient object
	private static DocumentClient documentClient;

	// Cache for the database object, so we don't have to query for it to
    // retrieve self links.
    private static Database database;

    // Cache for the collection object, so we don't have to query for it to
    // retrieve self links.
    private static DocumentCollection applicationCollection;

    private static Gson gson = new Gson();

	public CloudStorage(){
	    if (documentClient == null) {
	        documentClient = new DocumentClient(HOST, MASTER_KEY,
	                ConnectionPolicy.GetDefault(), ConsistencyLevel.Session);
	    }

	    database = this.initializeDatabase();
	    applicationCollection = this.initializeApplicationCollection();
	}


	@Override
	public ArrayList<CollegeApplicationModel> getApplications() {

		ArrayList<CollegeApplicationModel> apps = new ArrayList<CollegeApplicationModel>();
		 // Retrieve the CollegeApplicationModel documents
	    List<Document> documentList = documentClient
	            .queryDocuments(applicationCollection.getSelfLink(),
	                    "SELECT * FROM root r WHERE r.entityType = '"+ APPLICATION_ENTITY_NAME + "'",
	                    null).getQueryIterable().toList();

	    // De-serialize the documents in to application.
	    for (Document appDocument : documentList) {
	        apps.add(gson.fromJson(appDocument.toString(),
	        		CollegeApplicationModel.class));
	    }

	    return apps;
	}

	@Override
	public ArrayList<CollegeApplicationModel> getApplications(String username) {

		ArrayList<CollegeApplicationModel> allApplications = this.getApplications();

		ArrayList<CollegeApplicationModel> filteredApp = new ArrayList<CollegeApplicationModel>();
		for(int i = 0; i < allApplications.size(); i++)
		{
			if(allApplications.get(i).getUsername().equalsIgnoreCase(username)){
				filteredApp.add(allApplications.get(i));
			}
		}

		return filteredApp;
	}

	@Override
	public boolean updateApplication(CollegeApplicationModel application) {
		// Retrieve the document from the database
	    Document appDoc = getDocumentById(application.getID());
	    if(appDoc == null){
	    	return false;
	    }

	    Document updatedDoc = new Document(gson.toJson(application));

	    updatedDoc.set("entityType", APPLICATION_ENTITY_NAME);

	    try {
	        // Persist/replace the updated document.
	        documentClient.replaceDocument(appDoc.getSelfLink(), updatedDoc,
	                null);
	    } catch (DocumentClientException e) {
	        e.printStackTrace();
	        return false;
	    }

	    return true;
	}

	@Override
	public CollegeApplicationModel addApplication(String university, CollegeApplicationCategoryModel category,
			String username) {

		CollegeApplicationModel app = new CollegeApplicationModel(university, category, username);
		Document appDoc = new Document(gson.toJson(app));

	    // Annotate the document as a TodoItem for retrieval (so that we can
	    // store multiple entity types in the collection).
		appDoc.set("entityType", APPLICATION_ENTITY_NAME);

	    try {
	        // Persist the document using the DocumentClient.
	    	appDoc = documentClient.createDocument(
	               applicationCollection.getSelfLink(), appDoc, null,
	                false).getResource();
	    } catch (DocumentClientException e) {
	        e.printStackTrace();
	        return null;
	    }

	    return gson.fromJson(appDoc.toString(), CollegeApplicationModel.class);
	}

	@Override
	public boolean deleteApplication(CollegeApplicationModel application) {
	    Document appDoc = getDocumentById(application.getID());

	    try {
	        // Delete the document by self link.
	        documentClient.deleteDocument(appDoc.getSelfLink(), null);
	    } catch (DocumentClientException e) {
	        e.printStackTrace();
	        return false;
	    }

	    return true;
	}

	public void deleteAllApplications(){
		ArrayList<CollegeApplicationModel> apps = this.getApplications();
		for(int i = 0; i < apps.size(); i++){
			this.deleteApplication(apps.get(i));
		}
	}

    private Database initializeDatabase() {
        if (database == null) {
            // Get the database if it exists
            List<Database> databaseList = documentClient
                    .queryDatabases(
                            "SELECT * FROM root r WHERE r.id='" + DATABASE_ID
                                    + "'", null).getQueryIterable().toList();

            if (databaseList.size() > 0) {
                // Cache the database object so we won't have to query for it
                // later to retrieve the selfLink.
                database = databaseList.get(0);
            } else {
                // Create the database if it doesn't exist.
                database = createDatabase(DATABASE_ID);
            }
        }

        return database;
    }

	private Database createDatabase(String databaseId) {
		try {
		    Database databaseDefinition = new Database();
		    databaseDefinition.setId(databaseId);

		    return documentClient.createDatabase(
		            databaseDefinition, null).getResource();
		} catch (DocumentClientException e) {
		    e.printStackTrace();
		}

		return null;
	}

    private DocumentCollection initializeApplicationCollection() {
        if (applicationCollection == null) {
            // Get the collection if it exists.
            List<DocumentCollection> collectionList = documentClient
                    .queryCollections(
                            database.getSelfLink(),
                            "SELECT * FROM root r WHERE r.id='" + COLLECTION_ID
                                    + "'", null).getQueryIterable().toList();

            if (collectionList.size() > 0) {
                // Cache the collection object so we won't have to query for it
                // later to retrieve the selfLink.
                applicationCollection = collectionList.get(0);
            } else {
                // Create the collection if it doesn't exist.
            	applicationCollection = createCollection(COLLECTION_ID);
            }
        }

        return applicationCollection;
    }

	private DocumentCollection createCollection(String collectionId) {
		try {
		    DocumentCollection collectionDefinition = new DocumentCollection();
		    collectionDefinition.setId(collectionId);

		    // Configure the new collection performance tier to S1.
		    RequestOptions requestOptions = new RequestOptions();
		    requestOptions.setOfferType("S1");

		    return documentClient.createCollection(
		            database.getSelfLink(),
		            collectionDefinition, requestOptions).getResource();
		} catch (DocumentClientException e) {
		    // TODO: Something has gone terribly wrong - the app wasn't
		    // able to query or create the collection.
		    // Verify your connection, endpoint, and key.
		    e.printStackTrace();
		}

		return null;
	}


	private Document getDocumentById(String id) {
	    // Retrieve the document using the DocumentClient.
	    List<Document> documentList = documentClient
	            .queryDocuments(applicationCollection.getSelfLink(),
	                    "SELECT * FROM root r WHERE r.id='" + id + "'", null)
	            .getQueryIterable().toList();

	    if (documentList.size() > 0) {
	        return documentList.get(0);
	    } else {
	        return null;
	    }
	}

	private CollegeApplicationModel getApplicationById(String id) {
	    // Retrieve the document by id using our helper method.
	    Document appDocument = getDocumentById(id);

	    if (appDocument != null) {
	        // De-serialize the document in to a CollegeApplicationModel.
	        return gson.fromJson(appDocument.toString(), CollegeApplicationModel.class);
	    } else {
	        return null;
	    }
	}
}
