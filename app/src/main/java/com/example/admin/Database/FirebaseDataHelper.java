package com.example.admin.Database;

import com.example.admin.Classes.Groups;
import com.example.admin.Classes.QuestionItem;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseDataHelper {
    public static ArrayList<QuestionItem> questions = new ArrayList<>();

    public FirebaseDataHelper() {

    }

    public static class Instance {
        static FirebaseDatabase database = FirebaseDatabase.getInstance();
        static DatabaseReference adminReference = database.getReference().child("Groups");
        static DatabaseReference questionsReference = database.getReference().child("Questions");
        static DatabaseReference answerReference = database.getReference().child("Answers");


        public static String CreateNewGroup(String groupId) {
            Groups group = new Groups(groupId);
            String key = adminReference.push().getKey();
            adminReference.child(key).setValue(group);
            return key;
        }

        public static void CreateQuestions(String groupId, ArrayList<QuestionItem> questions) {
            for (QuestionItem question : questions) {
                InsertQuestion(question, groupId);
            }
        }

        public static void InsertQuestion(QuestionItem question, String groupId) {
            String questionKey = questionsReference.push().getKey();
            question.SetGroupId(groupId);
            questionsReference.child(questionKey).setValue(question);
        }

    }

}