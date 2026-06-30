package com.backend.nexaquiz.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.backend.nexaquiz.dto.request.QuizSubmissionRequest;
import com.backend.nexaquiz.dto.response.*;
import com.backend.nexaquiz.entity.enums.QuizAttemptStatus;
import org.springframework.stereotype.Service;

import com.backend.nexaquiz.dto.request.QuizRequest;
import com.backend.nexaquiz.entity.*;
import com.backend.nexaquiz.entity.enums.QuizStatus;
import com.backend.nexaquiz.exception.ResourceNotFoundException;
import com.backend.nexaquiz.repository.*;
import com.backend.nexaquiz.service.QuizService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;
    private final SubjectRepository subjectRepository;
    private final TopicRepository topicRepository;
    private final BatchRepository batchRepository;
    private final BatchStudentRepository batchStudentRepository;
    private final QuizQuestionRepository quizQuestionRepository;
    private final QuestionRepository questionRepository;
    private final QuizAttemptRepository quizAttemptRepository;
    private final UserRepository userRepository;
    private final QuizAnswerRepository quizAnswerRepository;


    @Override
    public QuizResponse createQuiz(
            QuizRequest request) {

        Batch batch =
                batchRepository.findById(
                                request.getBatchId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Batch Not Found"));

        Subject subject =
                subjectRepository.findById(
                                request.getSubjectId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Subject not found"));

        Topic topic =
                topicRepository.findById(
                                request.getTopicId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Topic not found"));

        Quiz quiz = Quiz.builder()
                .title(request.getTitle())
                .description(
                        request.getDescription())
                .durationMinutes(
                        request.getDurationMinutes())
                .scheduledAt(
                        request.getScheduledAt())
                .totalQuestions(
                        request.getTotalQuestions())
                .totalMarks(
                        request.getTotalQuestions())
                .status(
                        QuizStatus.DRAFT)
                .batch(batch)
                .subject(subject)
                .topic(topic)
                .active(true)
                .build();

        quiz = quizRepository.save(quiz);

        List<Question> questions =

                questionRepository
                        .findByTopic_IdAndActiveTrue(
                                topic.getId()
                        );

        int limit = Math.min(
                request.getTotalQuestions(),
                questions.size()
        );

        for(int i=0;i<limit;i++){

            QuizQuestion quizQuestion =

                    QuizQuestion.builder()
                            .quiz(quiz)
                            .question(
                                    questions.get(i)
                            )
                            .questionOrder(
                                    i + 1
                            )
                            .build();

            quizQuestionRepository.save(
                    quizQuestion
            );
        }

        return map(quiz);
    }

    @Override
    public List<QuizResponse> getAllQuizzes() {

        return quizRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public QuizResponse getQuizById(Long id) {

        return map(
                quizRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Quiz not found")));
    }

    @Override
    public QuizResponse publishQuiz(Long id) {

        Quiz quiz =
                quizRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Quiz not found"));

        quiz.setStatus(QuizStatus.PUBLISHED);

        return map(
                quizRepository.save(quiz));
    }

    @Override
    public void deleteQuiz(Long id) {

        Quiz quiz =
                quizRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Quiz not found"));

        quiz.setActive(false);

        quizRepository.save(quiz);
    }

    private QuizResponse map(
            Quiz quiz) {

        return QuizResponse.builder()

                .id(
                        quiz.getId())

                .title(
                        quiz.getTitle())

                .description(
                        quiz.getDescription())

                .durationMinutes(
                        quiz.getDurationMinutes())

                .totalQuestions(
                        quiz.getTotalQuestions())

                .totalMarks(
                        quiz.getTotalMarks())

                .batchId(
                        quiz.getBatch().getId())

                .batchName(
                        quiz.getBatch().getName())

                .subjectId(
                        quiz.getSubject().getId())

                .subjectName(
                        quiz.getSubject().getName())

                .topicId(
                        quiz.getTopic().getId())

                .topicName(
                        quiz.getTopic().getName())

                .scheduledAt(
                        quiz.getScheduledAt())

                .status(
                        quiz.getStatus())

                .build();
    }

    @Override
    public QuizResponse updateQuiz(
            Long id,
            QuizRequest request) {

        Quiz quiz =
                quizRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Quiz Not Found"));

        Batch batch =
                batchRepository.findById(
                                request.getBatchId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Batch Not Found"));

        Subject subject =
                subjectRepository.findById(
                                request.getSubjectId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Subject Not Found"));

        Topic topic =
                topicRepository.findById(
                                request.getTopicId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Topic Not Found"));

        quiz.setTitle(
                request.getTitle());

        quiz.setDescription(
                request.getDescription());

        quiz.setDurationMinutes(
                request.getDurationMinutes());

        quiz.setScheduledAt(
                request.getScheduledAt());

        quiz.setTotalQuestions(
                request.getTotalQuestions());

        quiz.setTotalMarks(
                request.getTotalQuestions());

        quiz.setBatch(batch);

        quiz.setSubject(subject);

        quiz.setTopic(topic);

        return map(
                quizRepository.save(quiz));
    }

    @Override
    public List<QuizResponse>
    getQuizzesForStudent(
            Long studentId) {

        List<BatchStudent> batchStudents =

                batchStudentRepository
                        .findByStudent_Id(
                                studentId
                        );

        if(batchStudents.isEmpty()){

            throw new RuntimeException(
                    "Student Not Assigned To Any Batch"
            );

        }

        List<Long> batchIds =

                batchStudents.stream()

                        .map(bs ->
                                bs.getBatch().getId()
                        )

                        .toList();

        return quizRepository

                .findByBatch_IdInAndStatusAndActiveTrueAndScheduledAtGreaterThanEqual(

                        batchIds,

                        QuizStatus.PUBLISHED,

                        LocalDate.now()
                                .atStartOfDay()

                )

                .stream()

                .map(quiz -> {

                    QuizResponse response =
                            map(quiz);

                    boolean attempted =

                            quizAttemptRepository
                                    .existsByQuizIdAndStudentId(

                                            quiz.getId(),

                                            studentId

                                    );

                    response.setAttempted(
                            attempted
                    );

                    response.setAvailable(

                            LocalDateTime.now()
                                    .isAfter(
                                            quiz.getScheduledAt()
                                    )

                    );

                    return response;

                })

                .toList();
    }

    @Override
    public List<QuestionResponse>
    getQuizQuestions(
            Long quizId) {

        return quizQuestionRepository

                .findByQuizIdOrderByQuestionOrderAsc(
                        quizId
                )

                .stream()

                .map(q -> {

                    Question question =
                            q.getQuestion();

                    return QuestionResponse
                            .builder()

                            .id(
                                    question.getId()
                            )

                            .questionText(
                                    question.getQuestionText()
                            )

                            .optionA(
                                    question.getOptionA()
                            )

                            .optionB(
                                    question.getOptionB()
                            )

                            .optionC(
                                    question.getOptionC()
                            )

                            .optionD(
                                    question.getOptionD()
                            )

                            .codeSnippet(
                                    question.getCodeSnippet()
                            )

                            .difficultyLevel(
                                    question.getDifficultyLevel()
                            )

                            .build();

                })

                .toList();
    }

    @Override
    public QuizResultResponse submitQuiz(
            Long quizId,
            QuizSubmissionRequest request) {

        Quiz quiz =
                quizRepository.findById(
                                quizId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Quiz Not Found"));

        User student =
                userRepository.findById(
                                request.getStudentId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Student Not Found"));

        boolean alreadyAttempted =

                quizAttemptRepository
                        .existsByQuizIdAndStudentId(

                                quizId,

                                student.getId()

                        );

        if(alreadyAttempted){

            throw new RuntimeException(
                    "Quiz Already Attempted"
            );
        }

        List<QuizQuestion> quizQuestions =

                quizQuestionRepository
                        .findByQuizIdOrderByQuestionOrderAsc(
                                quizId
                        );

        int correct = 0;

        int wrong = 0;

        int total =
                quizQuestions.size();

        QuizAttempt attempt =

                QuizAttempt.builder()

                        .quiz(quiz)

                        .student(student)

                        .startTime(
                                LocalDateTime.now()
                        )

                        .endTime(
                                LocalDateTime.now()
                        )

                        .status(
                                QuizAttemptStatus.SUBMITTED
                        )

                        .build();

        attempt =
                quizAttemptRepository.save(
                        attempt
                );

        for(QuizQuestion quizQuestion :
                quizQuestions){

            Question question =
                    quizQuestion.getQuestion();

            String selectedAnswer =

                    request.getAnswers()
                            .get(
                                    question.getId()
                            );

            boolean isCorrect =

                    selectedAnswer != null

                            &&

                            selectedAnswer.equalsIgnoreCase(

                                    question.getCorrectAnswer()

                            );

            if(isCorrect){

                correct++;

            }
            else{

                wrong++;

            }

            QuizAnswer quizAnswer =

                    QuizAnswer.builder()

                            .attempt(
                                    attempt
                            )

                            .question(
                                    question
                            )

                            .selectedAnswer(

                                    selectedAnswer == null

                                            ?

                                            ""

                                            :

                                            selectedAnswer

                            )

                            .correctAnswer(
                                    question.getCorrectAnswer()
                            )

                            .correct(
                                    isCorrect
                            )

                            .build();

            quizAnswerRepository.save(
                    quizAnswer
            );
        }

        double percentage =

                total == 0

                        ?

                        0

                        :

                        ((double)correct / total) * 100;

        attempt.setTotalQuestions(
                total
        );

        attempt.setCorrectAnswers(
                correct
        );

        attempt.setWrongAnswers(
                wrong
        );

        attempt.setScore(
                correct
        );

        attempt.setPercentage(
                percentage
        );

        attempt.setEndTime(
                LocalDateTime.now()
        );

        quizAttemptRepository.save(
                attempt
        );

        return QuizResultResponse
                .builder()

                .totalQuestions(
                        total
                )

                .correctAnswers(
                        correct
                )

                .wrongAnswers(
                        wrong
                )

                .score(
                        correct
                )

                .percentage(
                        percentage
                )

                .build();
    }

    @Override
    public List<QuizAttemptResponse>
    getStudentAttempts(
            Long studentId) {

        return quizAttemptRepository

                .findByStudentIdOrderByCreatedAtDesc(
                        studentId
                )

                .stream()

                .map(attempt ->

                        QuizAttemptResponse
                                .builder()

                                .attemptId(
                                        attempt.getId()
                                )

                                .quizTitle(
                                        attempt.getQuiz()
                                                .getTitle()
                                )

                                .score(
                                        attempt.getScore()
                                )

                                .percentage(
                                        attempt.getPercentage()
                                )

                                .correctAnswers(
                                        attempt.getCorrectAnswers()
                                )

                                .wrongAnswers(
                                        attempt.getWrongAnswers()
                                )

                                .build()

                )

                .toList();
    }

    @Override
    public StudentPerformanceResponse
    getStudentPerformance(
            Long studentId) {

        List<QuizAttempt> attempts =

                quizAttemptRepository
                        .findByStudentIdOrderByCreatedAtDesc(
                                studentId
                        );

        List<QuizAttemptResponse> response =

                new ArrayList<>();

        for(QuizAttempt attempt : attempts){

            response.add(

                    QuizAttemptResponse
                            .builder()

                            .attemptId(
                                    attempt.getId()
                            )

                            .quizTitle(
                                    attempt.getQuiz()
                                            .getTitle()
                            )

                            .subjectName(
                                    attempt.getQuiz()
                                            .getSubject()
                                            .getName()
                            )

                            .topicName(
                                    attempt.getQuiz()
                                            .getTopic()
                                            .getName()
                            )

                            .quizDate(
                                    attempt.getCreatedAt()
                                            .toLocalDate()
                                            .toString()
                            )

                            .totalMarks(
                                    attempt.getQuiz()
                                            .getTotalMarks()
                            )

                            .marksObtained(
                                    attempt.getScore()
                            )

                            .score(
                                    attempt.getScore()
                            )

                            .correctAnswers(
                                    attempt.getCorrectAnswers()
                            )

                            .wrongAnswers(
                                    attempt.getWrongAnswers()
                            )

                            .percentage(
                                    attempt.getPercentage()
                            )

                            .status(
                                    "COMPLETED"
                            )

                            .build()

            );

        }

        BatchStudent batchStudent =

                batchStudentRepository
                        .findFirstByStudent_Id(
                                studentId
                        )
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Student Not Assigned To Batch"
                                        )
                        );

        List<Quiz> batchQuizzes =

                quizRepository
                        .findByBatch_IdAndStatusAndActiveTrue(

                                batchStudent
                                        .getBatch()
                                        .getId(),

                                QuizStatus.PUBLISHED

                        );

        for(Quiz quiz : batchQuizzes){

            boolean attempted =

                    attempts.stream()

                            .anyMatch(

                                    attempt ->

                                            attempt.getQuiz()
                                                    .getId()

                                                    .equals(

                                                            quiz.getId()

                                                    )

                            );

            if(!attempted &&

                    quiz.getScheduledAt()
                            .isBefore(
                                    LocalDateTime.now()
                            )){

                response.add(

                        QuizAttemptResponse
                                .builder()

                                .quizTitle(
                                        quiz.getTitle()
                                )

                                .subjectName(
                                        quiz.getSubject()
                                                .getName()
                                )

                                .topicName(
                                        quiz.getTopic()
                                                .getName()
                                )

                                .quizDate(
                                        quiz.getScheduledAt()
                                                .toLocalDate()
                                                .toString()
                                )

                                .totalMarks(
                                        quiz.getTotalMarks()
                                )

                                .marksObtained(
                                        0
                                )

                                .score(
                                        0
                                )

                                .correctAnswers(
                                        0
                                )

                                .wrongAnswers(
                                        0
                                )

                                .percentage(
                                        0.0
                                )

                                .status(
                                        "MISSED"
                                )

                                .build()

                );

            }

        }

        double average =

                attempts.stream()

                        .mapToDouble(
                                QuizAttempt::getPercentage
                        )

                        .average()

                        .orElse(0);

        double best =

                attempts.stream()

                        .mapToDouble(
                                QuizAttempt::getPercentage
                        )

                        .max()

                        .orElse(0);

        return StudentPerformanceResponse
                .builder()

                .totalQuizzes(
                        attempts.size()
                )

                .averageScore(
                        average
                )

                .bestScore(
                        best
                )

                .attempts(
                        response
                )

                .build();
    }

    @Override
    public QuizAnalyticsResponse
    getQuizAnalytics(
            Long quizId) {

        Quiz quiz =

                quizRepository
                        .findById(quizId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Quiz Not Found"
                                        )
                        );

        List<Object[]> results =

                quizAttemptRepository
                        .getQuizAnalytics(
                                quizId
                        );

        Object[] result =

                results.isEmpty()

                        ?

                        new Object[]{
                                0L,
                                0.0,
                                0.0,
                                0.0
                        }

                        :

                        results.get(0);

        return QuizAnalyticsResponse
                .builder()

                .quizId(
                        quiz.getId()
                )

                .quizTitle(
                        quiz.getTitle()
                )

                .totalAttempts(

                        ((Number) result[0])
                                .intValue()

                )

                .averageScore(

                        result[1] == null

                                ?

                                0

                                :

                                ((Number) result[1])
                                        .doubleValue()

                )

                .highestScore(

                        result[2] == null

                                ?

                                0

                                :

                                ((Number) result[2])
                                        .doubleValue()

                )

                .lowestScore(

                        result[3] == null

                                ?

                                0

                                :

                                ((Number) result[3])
                                        .doubleValue()

                )

                .build();
    }

    @Override
    public List<QuizAttemptAnalyticsResponse>
    getQuizAttemptHistory(
            Long quizId) {

        return quizAttemptRepository

                .findByQuizIdOrderByCreatedAtDesc(
                        quizId
                )

                .stream()

                .map(attempt ->

                        QuizAttemptAnalyticsResponse
                                .builder()

                                .studentName(

                                        attempt.getStudent()
                                                .getFirstName()

                                                + " "

                                                +

                                                attempt.getStudent()
                                                        .getLastName()

                                )

                                .score(
                                        attempt.getScore()
                                )

                                .percentage(
                                        attempt.getPercentage()
                                )

                                .attemptedAt(
                                        attempt.getCreatedAt()
                                )

                                .build()

                )

                .toList();
    }

    @Override
    public StudentProfileResponse
    getStudentProfile(
            Long studentId) {

        User student =

                userRepository
                        .findById(studentId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Student Not Found"
                                        )
                        );

        BatchStudent batchStudent =

                batchStudentRepository
                        .findFirstByStudent_Id(
                                studentId
                        )
                        .orElse(null);

        List<QuizAttempt> attempts =

                quizAttemptRepository
                        .findByStudentId(
                                studentId
                        );

        double average =

                attempts.stream()

                        .mapToDouble(
                                QuizAttempt::getPercentage
                        )

                        .average()

                        .orElse(0);

        double best =

                attempts.stream()

                        .mapToDouble(
                                QuizAttempt::getPercentage
                        )

                        .max()

                        .orElse(0);

        return StudentProfileResponse
                .builder()

                .studentId(
                        student.getId()
                )

                .fullName(

                        student.getFirstName()

                                + " "

                                +

                                student.getLastName()

                )

                .email(
                        student.getEmail()
                )

                .batchName(

                        batchStudent != null

                                ?

                                batchStudent
                                        .getBatch()
                                        .getName()

                                :

                                "Not Assigned"

                )

                .totalQuizzes(
                        attempts.size()
                )

                .averageScore(
                        average
                )

                .bestScore(
                        best
                )

                .build();
    }

    @Override
    public List<QuizReviewResponse>
    getAttemptReview(
            Long attemptId) {

        return quizAnswerRepository

                .findByAttemptId(
                        attemptId
                )

                .stream()

                .map(answer -> {

                    Question question =
                            answer.getQuestion();

                    return QuizReviewResponse
                            .builder()

                            .questionText(
                                    question.getQuestionText()
                            )

                            .optionA(
                                    question.getOptionA()
                            )

                            .optionB(
                                    question.getOptionB()
                            )

                            .optionC(
                                    question.getOptionC()
                            )

                            .optionD(
                                    question.getOptionD()
                            )

                            .codeSnippet(
                                    question.getCodeSnippet()
                            )

                            .selectedAnswer(
                                    answer.getSelectedAnswer()
                            )

                            .correctAnswer(
                                    answer.getCorrectAnswer()
                            )

                            .correct(
                                    answer.getCorrect()
                            )

                            .build();

                })

                .toList();
    }

    @Override
    public StudentDashboardResponse
    getStudentDashboard(
            Long studentId) {

        long completedQuizzes =

                quizAttemptRepository
                        .findByStudentId(
                                studentId
                        )
                        .size();

        long availableQuizzes =

                getQuizzesForStudent(
                        studentId
                )
                        .size();

        Double averageScore =

                quizAttemptRepository
                        .findByStudentId(
                                studentId
                        )
                        .stream()

                        .mapToDouble(

                                attempt ->

                                        attempt.getPercentage() == null

                                                ?

                                                0

                                                :

                                                attempt.getPercentage()

                        )

                        .average()

                        .orElse(0);

        User student =

                userRepository
                        .findById(studentId)
                        .orElseThrow(
                                () ->
                                        new RuntimeException(
                                                "Student Not Found"
                                        )
                        );

        return StudentDashboardResponse
                .builder()

                .studentName(

                        student.getFirstName()

                                + " "

                                +

                                student.getLastName()

                )

                .availableQuizzes(
                        (int) availableQuizzes
                )

                .completedQuizzes(
                        (int) completedQuizzes
                )

                .averageScore(
                        averageScore
                )

                .build();
    }

    @Override
    public List<AdminResultResponse> getAllResults() {

        return quizAttemptRepository

                .findAllByOrderByCreatedAtDesc()

                .stream()

                .map(attempt ->

                        AdminResultResponse

                                .builder()

                                .attemptId(
                                        attempt.getId()
                                )

                                .studentName(

                                        attempt.getStudent()
                                                .getFirstName()

                                                + " "

                                                + attempt.getStudent()
                                                .getLastName()

                                )

                                .batchName(

                                        attempt.getQuiz()
                                                .getBatch()
                                                .getName()

                                )

                                .quizTitle(

                                        attempt.getQuiz()
                                                .getTitle()

                                )

                                .subjectName(

                                        attempt.getQuiz()
                                                .getSubject()
                                                .getName()

                                )

                                .topicName(

                                        attempt.getQuiz()
                                                .getTopic()
                                                .getName()

                                )

                                .score(
                                        attempt.getScore()
                                )

                                .percentage(
                                        attempt.getPercentage()
                                )

                                .correctAnswers(
                                        attempt.getCorrectAnswers()
                                )

                                .wrongAnswers(
                                        attempt.getWrongAnswers()
                                )

                                .status(
                                        attempt.getStatus()
                                                .name()
                                )

                                .submittedDate(

                                        attempt.getCreatedAt()
                                                .toLocalDate()
                                                .toString()

                                )

                                .build()

                )

                .toList();

    }
}