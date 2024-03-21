package components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Login {

        private static String userName="";
        private static String email="";

        public static int login() throws IOException{
                System.out.println("Welcome to Quiz App\n--------------------");
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("1 : Admin\n2 : User\n3 : Exit");
                int c=Integer.parseInt(br.readLine());
                return c;
        }
        static class QuizTImerTask extends TimerTask{
                public void run(){
                        System.out.println("Time's up!! Quiz has ended. ");
                        //code for evaluation
                        System.out.println("EVALUATING...");
                        //Thread.sleep(quizTimeLimit*1000);
                        int marks= AppConnections.evaluateQuestions();
                        System.out.println("-------------------------------------------------------");

                        System.out.println("Name:"+userName+"\n"+"Email:"+ email);
                        System.out.println("You're Score:\n"+marks);
                        String grade=AppConnections.score(marks);

                        System.out.println("Grade:"+grade);
                        AppConnections.truncateSelected_AnswersTableInDB();
                        //pdf file generate
                        String output="\nYou have successfully completed the Quiz"+"\n\n"+
                                    "Name: "+userName+"\n\n"+"Email: "+email+"\n\n"+
                                    "Score: "+marks+"\n\n"+"Grade:"+grade;
                        AppConnections.pdfGeneration(output);
                        System.exit(0);
                }
        }

        public static void main(String[] args) throws IOException {
               int c=Login.login();
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                if(c==1){
                        //admin details
                        System.out.println("Enter Admin Name:");
                        String adminName = br.readLine();
                        System.out.println("Enter Admin Number:");
                        String adminId = br.readLine();
                       Admin admin=new Admin(adminName,adminId);
                }
                else if(c==2){
                        //user data
                        System.out.println("Enter Your Name :");
                        userName=br.readLine();
                        System.out.println("Enter your Email:");
                        email=br.readLine();

                }
                while(true){
                        if(c==1){
                                while(true){
                                        System.out.println("1 : Entering Questions\n2 : Delete Questions\n3 : Modify Questions\n4 : Exit ");
                                        int g= Integer.parseInt(br.readLine());
                                        if(g==1){//entering code
                                                System.out.println("Enter Number of Questions:");
                                                int n= Integer.parseInt(br.readLine());
                                                while(n>0){
                                                        //navigate to admin class
                                                        System.out.println("Enter Question Number:");
                                                        String  questionNo= br.readLine();
                                                        System.out.println("Enter Question:");
                                                        String question=br.readLine();
                                                        Questions ques=new Questions(question,questionNo);
                                                        boolean f=AppConnections.insertQuestionsIntoDB(ques);
                                                        if(f){
                                                                System.out.println("Question added");
                                                        }
                                                        else{
                                                                System.out.println("Something went wrong;");
                                                        }
                                                        System.out.println(ques);

                                                        //enter options for the questions
                                                        int k=1;
                                                        while(k<=4){
                                                                System.out.println("Enter options:");
                                                                String op=br.readLine();
                                                                Options option=new Options(questionNo,op,k);
                                                                boolean f1=AppConnections.insertOptionsIntoDB(option);
                                                                if(f1){
                                                                        System.out.println("Option added");
                                                                }else {
                                                                        System.out.println("Something went wrong");
                                                                }
                                                                //System.out.println(option);
                                                                k++;
                                                        }

                                                        //correct answers options
                                                        System.out.println("Enter Correct Option:");
                                                        String ans=br.readLine();
                                                        System.out.println("Enter Option Number");
                                                        int j= Integer.parseInt(br.readLine());
                                                        Answers answer=new Answers(questionNo,ans,j);
                                                        boolean f2=AppConnections.insertIntoAnswer(answer);
                                                        if(f2){
                                                                System.out.println("Answer added");
                                                        }
                                                        else{
                                                                System.out.println("Something went wrong");
                                                        }
                                                        //System.out.println(answer);
                                                        n--;
                                                }
                                        }
                                        else if(g==2){
                                                //delete question code
                                                System.out.println("Enter the question number to be deleted:");
                                                int qst= Integer.parseInt(br.readLine());
                                                boolean f4=AppConnections.deleteQuestionsFromDB(qst);
                                                if(f4){
                                                        System.out.println("Question Deleted");
                                                }
                                                else{
                                                        System.out.println("Something went wrong");
                                                }
                                        }
                                        else if(g==3){
                                                //modify question code

                                                while(true){
                                                        System.out.println("1 : Edit Questions\n2 : Edit Options \n3 : Edit Answers\n4 : Exit");
                                                        int n= Integer.parseInt(br.readLine());
                                                        if(n==1){
                                                                //modify questions table
                                                                System.out.println("Enter question number to modify:");
                                                                int l= Integer.parseInt(br.readLine());
                                                                System.out.println("Enter new question:");
                                                                String quest=br.readLine();
                                                                boolean f5=AppConnections.modifyQuestionInDB(l,quest);
                                                                if(f5){
                                                                        System.out.println("question modify successful");
                                                                }
                                                                else{
                                                                        System.out.println("Something went wrong");
                                                                }
                                                        }
                                                        else if(n==2){
                                                                //modify option_questions table
                                                                System.out.println("Enter question number:");
                                                                int l= Integer.parseInt(br.readLine());
                                                                System.out.println("Enter option number:");
                                                                int optionNo= Integer.parseInt(br.readLine());
                                                                System.out.println("Enter option:");
                                                                String opt= br.readLine();
                                                                boolean f6=AppConnections.modifyOptionsInDB(l,optionNo,opt);
                                                                if(f6){
                                                                        System.out.println("Option modify successful");
                                                                }
                                                                else{
                                                                        System.out.println("Something went wrong");
                                                                }

                                                        }
                                                        else if(n==3){
                                                                //modify answers table
                                                                System.out.println("Enter question number:");
                                                                int l= Integer.parseInt(br.readLine());
                                                                System.out.println("Enter option number:");
                                                                int optionNum= Integer.parseInt(br.readLine());
                                                                System.out.println("Enter correct option:");
                                                                String opt=br.readLine();
                                                                boolean f7=AppConnections.modifyAnswersInDB(l,optionNum,opt);
                                                                if(f7){
                                                                        System.out.println("Answer modify successful");
                                                                }
                                                                else{
                                                                        System.out.println("Something went wrong");
                                                                }
                                                        }
                                                        else if(n==4){
                                                                break;
                                                        }
                                                }

                                        }
                                        else if(g==4){
                                                //exit
                                                System.exit(0);
                                                break;
                                        }

                                }

                        }
                        else if(c==2){
                                //user code
                                System.out.println("1 : Start Quiz\n2 : Exit");
                                int w=Integer.parseInt(br.readLine());
                                if(w==1){
                                        System.out.println("EXAM STARTS NOW:\n------------------");
                                        System.out.println("You can modify you're options before time runs out");
                                        System.out.println("-------------------------------------------------------------------------------------------");
                                        Timer timer = new Timer();
                                        final int quizTimeLimit=60*10;
                                        timer.schedule(new QuizTImerTask(),quizTimeLimit*1000);
                                        AppConnections.getQuestionFromDB();
                                        System.out.println("-------------------------------------------------------------------------------------------");
                                        AppConnections.displayFinalQuestionsForUser();
                                        //code to modify
                                        while(true){
                                                System.out.println("1 : Modify You're Answer\n2 : Exit");
                                                int k= Integer.parseInt(br.readLine());
                                                if(k==1){
                                                        System.out.println("Enter Question Number:");
                                                        String qst=br.readLine();
                                                        EnteredOption.modifyUserSelectedOption(qst);

                                                }
                                                else if(k==2){
                                                        //timer.cancel();
                                                        QuizTImerTask time=new QuizTImerTask();
                                                        time.run();
                                                        break;
                                                }
                                        }


                                }
                                else if(w==2){
                                        break;
                                }
                        }
                        else if(c==3){
                                break;
                        }

                }

        }


}
