package com.social.socialdukan.Student.fragment.Event.FAQ;

public class Faq_model {

    public String question;
    public String answer;

    public Faq_model() {
    }


    public Faq_model(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
