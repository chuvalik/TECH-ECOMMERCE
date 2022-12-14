package com.example.feature_details.data.remote

import com.example.feature_details.data.remote.model.CloudDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class DetailsFirestoreImpl : DetailsFirestore {

    private val firestore = FirebaseFirestore.getInstance()

    override suspend fun fetchCloudData(id: String): CloudDataSource? {
        return firestore
            .collection(PRODUCT_COLLECTION)
            .document(id)
            .get()
            .await()
            .toObject(CloudDataSource::class.java)
    }

    private companion object {
        const val PRODUCT_COLLECTION = "products"
    }
}