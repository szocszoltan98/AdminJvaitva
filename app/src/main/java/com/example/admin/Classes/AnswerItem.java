package com.example.admin.Classes;

public class AnswerItem {
    public String name;
    public String answer;
    public String groupId;
    public String question;

    public AnswerItem() {
    }
    public AnswerItem(String name, String answer) {
        this.name = name;
        this.answer = answer;


    }

    public AnswerItem(String name, String answer, String groupId, String question) {
        this.name = name;
        this.answer = answer;
        this.question=question;
        this.groupId=groupId;

    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}

