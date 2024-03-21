package components;

public class Options {
        private String questionNo;
        private String option;
        private int optionNo;

        public Options(String questionNo, String option,int optionNo) {
                this.questionNo = questionNo;
                this.option = option;
                this.optionNo=optionNo;
        }

        public String getQuestionNo() {
                return questionNo;
        }

        public int getOptionNo() {
                return optionNo;
        }

        public void setOptionNo(int optionNo) {
                this.optionNo = optionNo;
        }

        public void setQuestionNo(String questionNo) {
                this.questionNo = questionNo;
        }

        public String getOption() {
                return option;
        }

        public void setOption(String option) {
                this.option = option;
        }

        @Override
        public String toString() {
                return "Options{" +
                            "questionNo='" + questionNo + '\'' +
                            ", option='" + option + '\'' +
                            ", optionNo=" + optionNo +
                            '}';
        }
}
