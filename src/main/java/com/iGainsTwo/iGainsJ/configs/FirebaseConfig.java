package com.iGainsTwo.iGainsJ.configs;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseConfig {
    public void configureFirebaseConnection() throws IOException {
        FileInputStream serviceAccount =
                new FileInputStream("classpath:config/i-gains-firebase-adminsdk-libs7-9754974a77.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://i-gains-default-rtdb.europe-west1.firebasedatabase.app")
                .build();

        FirebaseApp.initializeApp(options);

    }
}
