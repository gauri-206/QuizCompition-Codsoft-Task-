import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static int score = 0;
    private static int currentQuestionIndex = 0;
    private static ArrayList<QuizQuestion> questions = new ArrayList<>();

    public static void main(String[] args) {
        initializeQuestions();

        startQuiz();

    }

    private static void initializeQuestions() {
        questions.add(new QuizQuestion("What is the capital of France?", 
                new ArrayList<>(List.of("A. Berlin", "B. Paris", "C. Rome", "D. Madrid")), 1));
        questions.add(new QuizQuestion("Which planet is known as the Red Planet?", 
                new ArrayList<>(List.of("A. Earth", "B. Mars", "C. Jupiter", "D. Venus")), 1));
    }

    private static void startQuiz() {
        Timer timer = new Timer();
        Scanner scanner = new Scanner(System.in);

        for (QuizQuestion question : questions) {
            System.out.println(question.question);
            for (String option : question.options) {
                System.out.println(option);
            }

            // Set up a timer for each question
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    System.out.println("Time's up! ");
                    evaluateAnswer(-1); // -1 indicates time's up
                    System.out.println("\nQuiz completed! Your final score: " + score + "/" + questions.size());

                }
            };
            timer.schedule(task, 10000); // 10 seconds timer

            System.out.print("Your answer (enter the option): ");
            String userAnswer = scanner.nextLine().toUpperCase();
            

            task.cancel();

            evaluateAnswer(getOptionIndex(userAnswer));
        }

        System.out.println("\nQuiz completed! Your final score: " + score + "/" + questions.size());
        scanner.close();
        

    }

    private static void evaluateAnswer(int userAnswerIndex) {
        QuizQuestion currentQuestion = questions.get(currentQuestionIndex);

        if (userAnswerIndex == currentQuestion.correctOption) {
            System.out.println("Correct!\n");
            score++;
        } else if (userAnswerIndex == -1) {
            System.out.println("Time's up! The correct answer was: " +
                    currentQuestion.options.get(currentQuestion.correctOption));
        } else {
            System.out.println("Incorrect! The correct answer was: " +
                    currentQuestion.options.get(currentQuestion.correctOption) + "\n");
        }

        currentQuestionIndex++;
    }

    private static int getOptionIndex(String option) {
        // Convert option letter to index (A: 0, B: 1, C: 2, D: 3)
        return option.charAt(0) - 'A';
    }
}

