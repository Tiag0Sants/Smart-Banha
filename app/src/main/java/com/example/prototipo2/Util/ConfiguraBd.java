package com.example.prototipo2.Util;

import com.google.firebase.auth.FirebaseAuth;

public class ConfiguraBd {


        private static FirebaseAuth auth;

        public static FirebaseAuth Firebaseutenticacao() {
        if (auth == null){
            auth = FirebaseAuth.getInstance();
        }
        return auth;
    }
    }



