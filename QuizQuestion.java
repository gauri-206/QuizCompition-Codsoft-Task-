import java.util.ArrayList;

class QuizQuestion {
    String question;
    ArrayList<String> options;
    int correctOption;

    QuizQuestion(String question, ArrayList<String> options, int correctOption) {
        this.question = question;
        this.options = options;
        this.correctOption = correctOption;
    }
}

