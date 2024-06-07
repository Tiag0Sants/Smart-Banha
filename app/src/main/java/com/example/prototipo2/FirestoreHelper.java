package com.example.prototipo2;

import android.util.Log;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class FirestoreHelper {
    private static final String TAG = "FirestoreHelper";
    private FirebaseFirestore db;

    public FirestoreHelper() {
        db = FirebaseFirestore.getInstance();
    }

    public void getUserData(String email, final FirestoreCallback callback) {
        db.collection("users").document(email.replace(".", "_"))
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            String name = document.getString("name");
                            callback.onCallback(name);
                        } else {
                            Log.d(TAG, "No such document");
                            callback.onFailure("No such document");
                        }
                    } else {
                        Log.d(TAG, "get failed with ", task.getException());
                        callback.onFailure("get failed with " + task.getException());
                    }
                });
    }

    public interface FirestoreCallback {
        void onCallback(String name);
        void onFailure(String error);
    }
}
