package components;

public class Answers {
        private String questionNo;
        private String optionNo;
        private int optionNum;

        public Answers(String questionNo, String optionNo,int optionNum) {
                this.questionNo = questionNo;
                this.optionNo = optionNo;
                this.optionNum=optionNum;
        }

        public int getOptionNum() {
                return optionNum;
        }

        public void setOptionNum(int optionNum) {
                this.optionNum = optionNum;
        }

        public String getQuestionNo() {
                return questionNo;
        }

        public void setQuestionNo(String questionNo) {
                this.questionNo = questionNo;
        }

        public String getOptionNo() {
                return optionNo;
        }

        public void setOptionNo(String optionNo) {
                this.optionNo = optionNo;
        }

        @Override
        public String toString() {
                return "Answers{" +
                            "questionNo='" + questionNo + '\'' +
                            ", optionNo='" + optionNo + '\'' +
                            ", optionNum=" + optionNum +
                            '}';
        }
}
