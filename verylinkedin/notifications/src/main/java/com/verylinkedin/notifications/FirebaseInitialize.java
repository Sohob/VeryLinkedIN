package com.verylinkedin.notifications;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.*;

import org.json.simple.JSONObject;

public class FirebaseInitialize {

    public static void initialize() throws IOException {
        JSONObject json = new JSONObject();
        json.put("type", "service_account");
        json.put(   "project_id", "very-ff6f1");
        json.put( "private_key_id", "4242f16c54a0cc5623ddf1b71060c717a110868b");
        json.put("private_key", "-----BEGIN PRIVATE KEY-----\nMIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDsqIbD9N55wFVk\npaB8UHU553ESbOf7qsZDjMeX/5zjRAbA7yyDRsmm3EDxMQf1fGBqat/y2W0ehk7o\ngb5HEsDJGdZVl513rn+89Frbo7QXrvIRhfqQEOktxtPiTG3UY2EVekoAhTnEqZhJ\nlT5JAhwqf4LflWWV8YWLTU1oBQuqrwe3tGdKkSGaqzJnD33ERPGR3mhaqH+9xfgP\nmgJ4HrmamJYzWZXwOtAIGcz+PekfjUsDI1MVyFJrVui8biPrrk5Yn8v+yuvKeyDX\nsSTDbuszDVhzpCmxY5CVANLxtaEONuvGdiPRC97YlFkqhZz7ljPh37Al3tuYPhVE\nbx6ag1MBAgMBAAECggEAB6K2WmszpGT+vFUVtP0Zkyky/j+ySm3roLFRnUdbhf+j\nYiU1X/TwO6h+lemZkqOVLsnM+PZn7WSy9hB9lah2vOZPAor2Qy7Ho06VAeIpxNvZ\nppQWjgBlrZrtKi+CGaitMLa13Ydu4jgAmFsu28csJnjfvZwB35Ef2GtBlJoZEsQs\nDX6qfHkkIPxcBQepq4qYcSw3ZCGZ2nzwkOtYMuBVWFtnxW79myReORZyUSluYrs0\nt3zJe+SbpKvAS2cf7OrVav0tP6pqPys/LRfNAFtVpkt8/10FrRuYsa81VSzhiKAB\nDMuDccMVuTa8iyKs1eKxntYtdYOVMRPQwzBfzDa5kwKBgQD82yrRuYHPWMmCwT8V\ngO2DXyMAsYYsAfuqck1ssV4FYrOT/SN+YJpkccUMJfN2hBRO+yfbwLJkbeRtZ8AD\nnw79VtICIfAVosD7bvaHcsjN0w7Kaf33qmeP5MdS6/Oahcp+AKbzzYPq8cADx/Ji\n6mY0ll9rjStF6jMRBRr1nyDr0wKBgQDvmc1TFUBnAVpznGi/h2kbx0Oo/umMM41m\nJeAnVbsrJTg6WL6nIfECBUUlnx3EfZ44LdDad0bGyl5y9VkoGmhugj8UhU1g1E++\n0ps/CzxcQSpoKT7rPzAWFWOwxEtaT9G+AU6VYCUpjz2xWjHkGdchy7zEPYgYmKxr\nszxPpx4lWwKBgFbHcR6hJ+fddOsnH1tl8B3SHNU0F/QqihCpQeE3zijTAwabLf9F\ncDdgxFuy+qeGFOI2WCuXKqJFBMx3evLDOAXCMjkBJMCIJusj3gAGaNwav0zAq6TA\nGuLLhYzz5Zr2p9QurR2nPsr3RQsHKCzCfWk24nCq9lr8UaJZujLp9gZ5AoGAfXj9\nnVSztT1wMLrn3+T5liJzvDxB8bnNgoCzMeYjbiS/nU58COotW/GGlH0PCab/BEx5\n4u4zkkbHz0vMtMOfjL+IaSalrt+AI14HDqzDn0xA+Osgl/EvLWFmI1uoWlMSc34b\nuP3weCMbG5UwK1Oc5YXcBKh0bO0G3h+upcfOcaECgYBlRBgThrSbtUdKa0O/wo/S\n/tJU8HB/3ZzM5qutz6R7tNx/LaxLnUO6AMyeWe7kWYG+OsiTo3J8db6haNX9Pvoe\n5PRQqKkszXP9aYe9YT1tVJC5oeXPh53EZXivYagJE+K2sDJbcSyq2SguDfRUx2NJ\n5Wc3eysaHpap3hjmkHwOcw==\n-----END PRIVATE KEY-----\n");
        json.put( "client_email", "firebase-adminsdk-d3ock@very-ff6f1.iam.gserviceaccount.com");
        json.put(  "client_id","118214536506541560946");
        json.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
        json.put( "token_uri", "https://oauth2.googleapis.com/token");
        json.put("auth_provider_x509_cert_url", "https://www.googleapis.com/oauth2/v1/certs");
        json.put( "client_x509_cert_url", "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-d3ock%40very-ff6f1.iam.gserviceaccount.com");

//        json.put("type", "service_account");
//        json.put(  "project_id", "linkedin-e3570");
//        json.put( "private_key_id","7f0a78f242a05edd702f41a7b505dc706dcd5518");
//        json.put("private_key","-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCGUWBvljCZ1EQ+\nUcqgnDNE/YKigjI70J0Aj2FS401+PA37XxkYUSeTzz5vADuWjBaH0HPmq0SgP3Vj\n9IbotZu9XpR7cz1iTibwmChSQA+7RSpO979RVi8B+uZMa7x/WSESn/raElSUGszo\nhcsDFQETSq1FOTr1CeOoEN4xAxp1iectyzimL8vJwc/VwCW5heNMvv6rGFO88pYN\nLkcd+wb1+fzrPdyaYIzaWOO3b7TfM1xbNeIzC5wkfpQ5D/8s3OXAg3xP0zSIM0Xm\n/arjeMNF4gYpfSd9uDuZvAASk7DoWyusXrjRC3zlKsQfpC+2RyIoARr4KMj6OZtT\nUrOSuxuTAgMBAAECggEAIni1Er5NZ9VZ1FKiebjM0apsQe+/G6nM+W1S0XaDl1+r\nSPWdl6eQTDd2cv9wy5DVYXLGpA8VdDkFFCuYChY9y4p/+fTX7qhXBw1N39oQD4RP\nOOgNYj8Hnm75duh5fodt3P1Q4HGu4jnEl/JqaTaVSZGzSV5IOsYYdrQpihOdUUe9\nbYNpzAtsmqgevHMq6GH0/kVs7rot14LeJbZIg0nNsIVwLyctPFnDHvF+mVU83Hj5\nt7ZgtmHCDybstjqk0r/8+qR+G965CEY9bz3L4vgh6z3eyxupM03y3ZRF37gf6qQi\nPAFs9i7cQUDqB0ByQu8GYp7pzOHC83MxCcKhMDTMoQKBgQC6yfcmGvNgFS1b1vKo\nJzfbiXNDMFSgHYVLJaZJn3Hf0SotzMq0oGadw/N/GQciBqcHDU+eZRt00KRbPo8t\nI7vFXjFbr+wK/BQPKVtA70kqYSG5ZUuMpOfO55e1//vHFcd9wLrrczis37xA2jGh\n/NakeMGybpx2t5colcMJ1DJtYQKBgQC4FjjL6BHU6lXE94lSBHmtQjxF3IIje370\nF+7oRqJIPFU+UWjx582a2M4KW03Z6CjCHrEdw7+b5QKJM/w/7cQUnd8E5SStWUQu\nWsYz6OB5vT/wFurg4l7NL3MOR3/on6vfx80I7CEtCBHicx/WhofEB/puOamrOmP5\n5BjE+9WZcwKBgQCzb4MRwwAbRBy/xfxzlsEtD5x/ZfVVnyZGK1PUE+yDst5PAxWT\ncSdC1VObvAJDKJrZxmq+4af/jtLkqHNITLR8BGFsD9K0NrZaXkNAXN4vbQHFpM5v\nvJbHB2mRN+i5tm1em3+04YoHYCLrNfBKCaZ0d2CztVhvHyyBm2Syk3sh4QKBgA4J\n887pJeL89AmPbzOID9d8w6f4Rxx5oJbw9FFWga3MnBuijcRVD0MN4bi8edVydRlI\nye0hLo2vC2B3THd7pIaF+6od54wo0KBQX+k5i1T9b9DZjpQT73LqeXXPUhvlgXAR\nhrt1dpo+kU6ZxQXRQK1chma9kwjx7QnzsUfkhMetAoGAQ4anVy0/O3V81PBVWX++\ngYj1Vmaqy1fs5IK+abk0cPF/Gjy59Y33X2JL4t/c/DIAeNvd4v/qnbdbjz/flfRr\n6ZvGpsKUPFYq9rHa7XvqR37MlhE+J2DXBXv1zSGJh2sEWzm6ruyrCbDPMiMpdrFa\n7ECWXhNYBQ0b9wC+Xx5P02g=\n-----END PRIVATE KEY-----\n");
//        json.put( "client_email", "firebase-adminsdk-jd42k@linkedin-e3570.iam.gserviceaccount.com");
//        json.put(  "client_id","105200470059480726314");
//        json.put("auth_uri", "https://accounts.google.com/o/oauth2/auth");
//        json.put(  "token_uri","https://oauth2.googleapis.com/token");
//        json.put( "auth_provider_x509_cert_url","https://www.googleapis.com/oauth2/v1/certs");
//        json.put( "client_x509_cert_url","https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-jd42k%40linkedin-e3570.iam.gserviceaccount.com");
        String str =json.toString();
        InputStream is = new ByteArrayInputStream(str.getBytes());
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(is))
                .setDatabaseUrl("https://linkedin-e3570-default-rtdb.europe-west1.firebasedatabase.app")
                .build();
        if(FirebaseApp.getApps().isEmpty()){
            FirebaseApp.initializeApp(options);
        }

    }
}
