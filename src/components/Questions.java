package components;

public class Questions {
        private  int qid;
        private String question;
        private String questionNo;

        public Questions(int qid, String question, String questionNo) {
                this.qid = qid;
                this.question = question;
                this.questionNo = questionNo;
        }

        public Questions(String question, String questionNo) {
                this.question = question;
                this.questionNo = questionNo;
        }

        public int getQid() {
                return qid;
        }

        public void setQid(int qid) {
                this.qid = qid;
        }

        public String getQuestion() {
                return question;
        }

        public void setQuestion(String question) {
                this.question = question;
        }

        public String getQuestionNo() {
                return questionNo;
        }

        public void setQuestionNo(String questionNo) {
                this.questionNo = questionNo;
        }

        @Override
        public String toString() {
                return "Questions{" +
                            ", question='" + question + '\'' +
                            ", questionNo='" + questionNo + '\'' +
                            '}';
        }
}
