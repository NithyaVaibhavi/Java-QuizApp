package components;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AppConnections {
        public static boolean insertQuestionsIntoDB(Questions ques){
                boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q="insert into questions(question,questionNo) values(?,?)";
                        //prepared Statement
                        PreparedStatement pstmt=con.prepareStatement(q);
                        //set values of parameters
                        pstmt.setString(1,ques.getQuestion());
                        pstmt.setString(2,ques.getQuestionNo());
                        //execute
                        pstmt.executeUpdate();
                        flag=true;
                        pstmt.close();
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public  static boolean deleteQuestionsFromDB(int questionNo){
                boolean flag=false;
                try{
                        Connection con=CP.createC();
                        String q1="delete from questions where questionNo=?";
                        PreparedStatement pstmt1=con.prepareStatement(q1);
                        pstmt1.setInt(1, questionNo);
                        pstmt1.executeUpdate();
                        pstmt1.close();

                        String q2="delete from question_options where questionNo=?";
                        PreparedStatement pstmt2=con.prepareStatement(q2);
                        pstmt2.setInt(1, questionNo);
                        pstmt2.executeUpdate();
                        pstmt2.close();

                        String q3="delete from answers where questionNo=?";
                        PreparedStatement pstmt3=con.prepareStatement(q3);
                        pstmt3.setInt(1, questionNo);
                        pstmt3.executeUpdate();
                        pstmt3.close();

                        con.close();
                        flag=true;
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static boolean modifyQuestionInDB(int questionNo,String question){
                //jdbc code
                boolean flag=false;
                try{
                        Connection con=CP.createC();
                        String q1="update questions set question=? where questionNo=?";
                        //prepared statement
                        PreparedStatement pstmt1=con.prepareStatement(q1);
                        //set the values of parameters
                        pstmt1.setString(1,question);
                        pstmt1.setInt(2,questionNo);
                        //execute
                        pstmt1.executeUpdate();
                        pstmt1.close();

                        con.close();
                        flag=true;
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static boolean modifyOptionsInDB(int questionNo,int optionNum,String option){
                //jdbc code
                boolean flag=false;
                try{
                        Connection con=CP.createC();
                        String q1="update question_options set question_options=? where optionNum=? and questionNo=?";
                        //prepared statement
                        PreparedStatement pstmt1=con.prepareStatement(q1);
                        //set the values of parameters
                        pstmt1.setInt(2,optionNum);
                        pstmt1.setString(1,option);
                        pstmt1.setInt(3,questionNo);
                        //execute
                        pstmt1.executeUpdate();
                        pstmt1.close();

                        con.close();
                        flag=true;
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static boolean modifyAnswersInDB(int questionNo,int optionNo,String option){
                //jdbc code
                boolean flag=false;
                try{
                        Connection con=CP.createC();
                        String q1="update answers set optionNum=? , optionNo=?  where  questionNo=?";
                        //prepared statement
                        PreparedStatement pstmt1=con.prepareStatement(q1);
                        //set the values of parameters
                        pstmt1.setInt(1,optionNo);
                        pstmt1.setString(2,option);
                        pstmt1.setInt(3,questionNo);

                        //execute
                        pstmt1.executeUpdate();
                        pstmt1.close();

                        con.close();
                        flag=true;
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static  boolean insertOptionsIntoDB(Options options){
                boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q="insert into question_options(questionNo,question_options,optionNum) values(?,?,?)";
                        //prepared Statement
                        PreparedStatement pstmt=con.prepareStatement(q);
                        //set values of parameters
                        pstmt.setString(1,options.getQuestionNo());
                        pstmt.setString(2,options.getOption());
                        pstmt.setInt(3,options.getOptionNo());
                        //execute
                        pstmt.executeUpdate();
                        flag=true;
                        pstmt.close();
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static boolean insertIntoAnswer(Answers ans){
                boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q="insert into answers(questionNo,optionNo,optionNum) values(?,?,?)";
                        //prepared Statement
                        PreparedStatement pstmt=con.prepareStatement(q);
                        //set values of parameters
                        pstmt.setString(1,ans.getQuestionNo());
                        pstmt.setString(2,ans.getOptionNo());
                        pstmt.setInt(3,ans.getOptionNum());
                        //execute
                        pstmt.executeUpdate();
                        flag=true;
                        pstmt.close();
                }catch(Exception e){
                        System.out.println(e);
                }
                return flag;
        }

        public static void getQuestionFromDB(){
//                boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q1="select * from questions";
                        String q2="select * from question_options";
                        //prepared Statement

                        Statement stmt1=con.createStatement();
                        ResultSet set1=stmt1.executeQuery(q1);

                        Statement stmt2=con.createStatement();
                        ResultSet set2=stmt2.executeQuery(q2);

                        //arraylists
                        ArrayList <Integer> questionsNo=new ArrayList<>();
                        ArrayList <String> questions=new ArrayList<>();
                        ArrayList <String> option =new ArrayList<>();

                        while (set1.next()) {
                                int no= set1.getInt("questionNo");
                                String s1=set1.getString("question");
                                questionsNo.add(no);
                                questions.add(s1);
                        }
                        while(set2.next()){
                                String s2=set2.getString("question_options");
                                option.add(s2);
                        }
                        int size=questionsNo.size();
                        int k=4;
                        int l=0;
                        for(int i=0;i<size;i++){
                                int currentQuestionNo = questionsNo.get(i); // Get the current question number
                                System.out.println("Question Number: " + currentQuestionNo);
                                System.out.println("Question: "+"\n"+questions.get(i));
                                System.out.println("Options:");

                                for(int j=l;j<k;j++){
                                        System.out.println(option.get(j));
                                        l++;
                                }
                                k=k+4;
                                System.out.println("-------------------------------------------------------------------------------------------");
                                EnteredOption.selectedOption(String.valueOf(currentQuestionNo));
                                System.out.println("-------------------------------------------------------------------------------------------");
                        }
                        stmt1.close();
                        stmt2.close();

                }catch(Exception e){
                        System.out.println(e);
                }
                //return flag;
        }

        public static void insertSelectedOptionsIntoDB(EnteredOption entAns){
                //boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q="insert into selected_answers(questionNo,selectedAns,optionNum) values(?,?,?)";
                        //prepared Statement
                        PreparedStatement pstmt=con.prepareStatement(q);
                        //PreparedStatement pstmt1=con.prepareStatement(q1);
                        //set values of parameters
                        pstmt.setString(1,entAns.getQuestionNo());
                        pstmt.setString(2,entAns.getSelectedOption());
                        pstmt.setInt(3,entAns.getOptionNum());
                        pstmt.executeUpdate();
                        //flag=true;

                        pstmt.close();
                }catch(Exception e){
                        System.out.println(e);
                }
        }



        public static void truncateSelected_AnswersTableInDB(){
                try{
                        Connection con=CP.createC();
                        String q4="truncate table selected_answers";

                        Statement stmt4=con.createStatement();
                        stmt4.executeUpdate(q4);

                        stmt4.close();
                } catch (Exception e) {
                        System.out.println(e);
                }
        }

        public static int evaluateQuestions(){
                //boolean flag=false;
                int marks=0;
                try {

                        Connection con=CP.createC();
                        String q1="select * from answers";
                        String q2="select * from selected_answers";

                        Statement stmt1=con.createStatement();
                        Statement stmt2=con.createStatement();

                        ResultSet set1=stmt1.executeQuery(q1);
                        ResultSet set2=stmt2.executeQuery(q2);

                        //marks for calculation

                        while(set1.next() && set2.next()){
                                int ans1= set1.getInt("optionNum");
                                int ans2=set2.getInt("optionNum");
                                if(ans1==ans2){
                                        marks=marks+10;
                                }
                        }
                        //flag=true;
                        stmt1.close();
                        stmt2.close();

                }catch(Exception e){
                        System.out.println(e);
                }
                return marks;
        }

        public static void displayFinalQuestionsForUser(){
//                boolean flag=false;
                try {

                        Connection con=CP.createC();
                        String q1="select * from questions";
                        String q2="select * from question_options";
                        String q3="select * from selected_answers";
                        //prepared Statement

                        Statement stmt1=con.createStatement();
                        ResultSet set1=stmt1.executeQuery(q1);

                        Statement stmt2=con.createStatement();
                        ResultSet set2=stmt2.executeQuery(q2);

                        Statement stmt3=con.createStatement();
                        ResultSet set3=stmt3.executeQuery(q3);

                        //arraylists
                        ArrayList <Integer> questionsNo=new ArrayList<>();
                        ArrayList <String> questions=new ArrayList<>();
                        ArrayList <String> option =new ArrayList<>();
                        ArrayList <String> selopt=new ArrayList<>();

                        while (set1.next()) {
                                int no= set1.getInt("questionNo");
                                String s1=set1.getString("question");
                                questionsNo.add(no);
                                questions.add(s1);
                        }
                        while(set2.next()){
                                String s2=set2.getString("question_options");
                                option.add(s2);
                        }
                        while(set3.next()){
                                String s3=set3.getString("selectedAns");
                                selopt.add(s3);
                        }
                        int size=questionsNo.size();
                        int k=4;
                        int l=0;
                        for(int i=0;i<size;i++){
                                int currentQuestionNo = questionsNo.get(i);
                                System.out.println("Question Number: " + currentQuestionNo);
                                System.out.println("Question: "+"\n"+questions.get(i));
                                System.out.println("Options:");

                                for(int j=l;j<k;j++){
                                        System.out.println(option.get(j));
                                        l++;
                                }
                                k=k+4;
                                System.out.println(selopt.get(i));
                                System.out.println("-------------------------------------------------------------------------------------------");

                        }
                        stmt1.close();
                        stmt3.close();
                        stmt2.close();

                }catch(Exception e){
                        System.out.println(e);
                }
        }

        public static void modifyUserAnswersInDB(String questionNo, String optionNo, int optionNum){
                //jdbc code

                try{
                        Connection con=CP.createC();
                        String q1="update selected_answers set selectedAns=? , optionNum=?  where  questionNo=?";
                        //prepared statement
                        PreparedStatement pstmt1=con.prepareStatement(q1);
                        //set the values of parameters
                        pstmt1.setString(1,optionNo);
                        pstmt1.setInt(2,optionNum);
                        pstmt1.setString(3,questionNo);

                        //execute
                        pstmt1.executeUpdate();
                        pstmt1.close();

                        con.close();
                }catch(Exception e){
                        System.out.println(e);
                }
        }

        public static String score(int marks){
                String grade="";
                if(marks>=90){
                        grade="O";
                }
                else if(marks>=80){
                        grade="A";
                }
                else if(marks>=70){
                        grade="B";
                }
                else if(marks>=60){
                        grade="c";
                }
                else if(marks>=50){
                        grade="D";
                }
                else if(marks>=40){
                        grade="E";
                }
                else{
                        grade="F";
                }
                return grade;
        }
        public static void pdfGeneration(String op){

                String filepath="output.pdf";
                Document doc=new Document();
                try{
                        PdfWriter.getInstance(doc,new FileOutputStream(filepath));
                        doc.open();
                        Paragraph paragraph=new Paragraph("SCORE CARD",createFont());
                        paragraph.setAlignment(Element.ALIGN_CENTER);
                        doc.add(paragraph);
                        doc.add(new Paragraph(op));
                        doc.close();
                        System.out.println("Pdf generation successful");

                } catch (DocumentException | FileNotFoundException e) {
                        throw new RuntimeException(e);
                }

        }
        private static Font createFont() {
                return FontFactory.getFont(FontFactory.HELVETICA, 20);
        }
}
