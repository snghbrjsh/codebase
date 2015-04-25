package com.insignia.couch;

import java.util.concurrent.CountDownLatch;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;
import com.couchbase.client.java.CouchbaseCluster;
import com.couchbase.client.java.document.JsonDocument;
import com.couchbase.client.java.document.json.JsonObject;

public class CouchClient {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(1);
		Cluster cluster = CouchbaseCluster.create("127.0.0.1");
		final Bucket bucket = cluster.openBucket("default");

//		JsonObject user = JsonObject.empty()
//			    .put("firstname", "Pramod")
//			    .put("lastname", "Prasad")
//			    .put("unit", "RCLADT")
//			    .put("age", 27);
//
//		JsonDocument doc = JsonDocument.create("pramodgslv14", user);
//		JsonDocument saved = bucket.upsert(doc);
//		System.out.println("saved: "+saved);

//		JsonDocument loaded = bucket.get("contactinsignia");
//		if (loaded == null) {
//		    System.err.println("Document not found!");
//		} else {
//		    loaded.content().put("age", 55);
//		    JsonDocument updated = bucket.replace(loaded);
//		    System.out.println("Updated: " + updated.id());
//		}

		bucket
	    .async()
	    .get("contactinsignia")
	    .flatMap(new Func1<JsonDocument, Observable<JsonDocument>>() {
	        @Override
	        public Observable<JsonDocument> call(final JsonDocument loaded) {
	            loaded.content().put("age", 52);
	            System.out.println("loaded: "+loaded);
	            return bucket.async().replace(loaded);
	        }
	    })
	    .subscribe(new Action1<JsonDocument>() {
	        @Override
	        public void call(final JsonDocument updated) {
	            System.out.println("Updated: " + updated.id());
	        }
	    });

		cluster.disconnect();
	}

}
