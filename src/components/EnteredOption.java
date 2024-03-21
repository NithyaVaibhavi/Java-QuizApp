package components;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EnteredOption {
        private String selectedOption;
        private String questionNo;
        private int is_correct;
        private int optionNum;
        public EnteredOption(String selectedOption, String questionNo,int is_correct,int optionNum) {
                this.selectedOption = selectedOption;
                this.questionNo = questionNo;
                this.is_correct=is_correct;
        }

        public EnteredOption(String selectedOption, String questionNo,int optionNum) {
                this.selectedOption = selectedOption;
                this.questionNo = questionNo;
                this.optionNum=optionNum;
        }

        public int getOptionNum() {
                return optionNum;
        }

        public void setOptionNum(int optionNum) {
                this.optionNum = optionNum;
        }

        public int getIs_correct() {
                return is_correct;
        }

        public void setIs_correct(int is_correct) {
                this.is_correct = is_correct;
        }

        public String getSelectedOption() {
                return selectedOption;
        }

        public void setSelectedOption(String selectedOption) {
                this.selectedOption = selectedOption;
        }

        public String getQuestionNo() {
                return questionNo;
        }

        public void setQuestionNo(String questionNo) {
                this.questionNo = questionNo;
        }

        @Override
        public String toString() {
                return "EnteredOption{" +
                            "selectedOption='" + selectedOption + '\'' +
                            ", questionNo='" + questionNo + '\'' +
                            ", is_correct=" + is_correct +
                            '}';
        }

        public static void selectedOption(String questionNo) throws IOException {
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the option:");
                String ans=br.readLine();
                int optionNum = switch (ans) {
                        case "a" -> 1;
                        case "b" -> 2;
                        case "c" -> 3;
                        case "d" -> 4;
                        default -> 0;
                };
                EnteredOption entAns=new EnteredOption(ans,questionNo,optionNum);
                AppConnections.insertSelectedOptionsIntoDB(entAns);
        }
        public static void modifyUserSelectedOption(String questionNo) throws IOException {
                BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter the option:");
                String ans=br.readLine();
                int optionNum = switch (ans) {
                        case "a" -> 1;
                        case "b" -> 2;
                        case "c" -> 3;
                        case "d" -> 4;
                        default -> 0;
                };
                AppConnections.modifyUserAnswersInDB(questionNo,ans,optionNum);
        }
}
